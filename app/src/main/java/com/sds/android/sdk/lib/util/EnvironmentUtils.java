package com.sds.android.sdk.lib.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.sds.android.sdk.lib.openudid.OpenUDIDManager;
import com.sds.android.ttpod.media.mediastore.old.MediaStoreOld;
import com.ta.utdid2.device.UTDevice;


import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility class for environment-related information like SD card paths, device config, and app config.
 * De-obfuscated version.
 */
public class EnvironmentUtils {

    private static String appPackageName;

    private static String sdcardPath;

    private static String secondarySdcardPath;

    private static String externalPath;

    private static final String DOT_SEPARATOR = File.separator + ".";

    static {
        String absolutePath;
        File file = new File("sdcard");
        if (!file.exists() || !file.canWrite()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (!externalStorageDirectory.canWrite()) {
                File[] listFiles = externalStorageDirectory.getParentFile().listFiles();
                if (listFiles != null) {
                    for (File listFile : listFiles) {
                        if (listFile.isDirectory() && listFile.canWrite()) {
                            file = listFile;
                            break;
                        }
                    }
                }
            }
            file = externalStorageDirectory;
        }
        try {
            absolutePath = file.getCanonicalPath();
        } catch (Exception e) {
            absolutePath = file.getAbsolutePath();
        }
        sdcardPath = absolutePath;
    }

    /**
     * Initializes all configuration modules.
     */
    public static void init(Context context) {
        AppConfig.init(context);
        DeviceConfig.init(context);
        UUIDConfig.init(context);
        appPackageName = context.getPackageName();
        setDefaultExecutor();
    }

    @TargetApi(11)
    private static void setDefaultExecutor() {
        if (SDKVersionUtils.sdkThan11()) {
            try {
                Method method = AsyncTask.class.getMethod("setDefaultExecutor", Executor.class);
                @SuppressLint("SoonBlockedPrivateApi") 
                Field declaredField = ThreadPoolExecutor.class.getDeclaredField("defaultHandler");
                declaredField.setAccessible(true);
                declaredField.set(null, new ThreadPoolExecutor.DiscardOldestPolicy());
                method.invoke(null, (ThreadPoolExecutor) AsyncTask.THREAD_POOL_EXECUTOR);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getAppPackageName() {
        return appPackageName;
    }

    /**
     * Storage-related configuration and utilities.
     */
    public static class StorageConfig {

        private static ArrayList<String> storagePaths = new ArrayList<>();

        public enum SdcardType {
            FIRST_SD_CARD,
            SECOND_SD_CARD
        }

        public static boolean isExternalStorageMounted() {
            return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        }

        public static long getUsableSpace(File file) {
            if (SDKVersionUtils.sdkThan9()) {
                return file.getUsableSpace();
            }
            StatFs statFs = new StatFs(file.getPath());
            return (long) statFs.getBlockSize() * (long) statFs.getAvailableBlocks();
        }

        public static File getExternalCacheDir(Context context) {
            File externalCacheDir = SDKVersionUtils.sdkThan8() ? context.getExternalCacheDir() : null;
            if (externalCacheDir == null) {
                externalCacheDir = new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
            }
            if (!externalCacheDir.exists()) {
                externalCacheDir.mkdirs();
            }
            return externalCacheDir.isDirectory() ? externalCacheDir : null;
        }

        public static String getSdcardPath() {
            return EnvironmentUtils.sdcardPath;
        }

        public static String getCacheDirPath(Context context) {
            File file = null;
            if (isExternalStorageMounted()) {
                file = getExternalCacheDir(context);
            }
            return file != null ? file.getAbsolutePath() : context.getCacheDir().getAbsolutePath();
        }

        public static boolean isPathWritable(String path) {
            File file = new File(path);
            if (file.canWrite()) {
                String testPathBase = path + File.separator;
                int i = 0;
                while (FileUtils.isFile(testPathBase + i)) {
                    i++;
                }
                File testFile = FileUtils.createFile(testPathBase + i);
                if (testFile != null) {
                    testFile.delete();
                    return true;
                }
            }
            return false;
        }

        public static String getMediaStoreAuthorityPath(Context context) {
            String secondaryPath = getSecondarySdcardPath(context);
            if (StringUtils.isEmpty(secondaryPath) || !FileUtils.exists(secondaryPath)) {
                return EnvironmentUtils.sdcardPath + File.separator + MediaStoreOld.AUTHORITY;
            }
            if (SDKVersionUtils.sdkThan19()) {
                return EnvironmentUtils.externalPath;
            }
            return secondaryPath + File.separator + MediaStoreOld.AUTHORITY;
        }

        public static String getSecondarySdcardPath(Context context) {
            if (EnvironmentUtils.secondarySdcardPath != null) {
                return EnvironmentUtils.secondarySdcardPath;
            }
            try {
                EnvironmentUtils.secondarySdcardPath = getSecondarySdcardPathFromExternalFiles(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (StringUtils.isEmpty(EnvironmentUtils.secondarySdcardPath)) {
                try {
                    EnvironmentUtils.secondarySdcardPath = getSecondarySdcardPathFromVolumePaths(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (StringUtils.isEmpty(EnvironmentUtils.secondarySdcardPath)) {
                    EnvironmentUtils.secondarySdcardPath = scanForStoragePaths();
                }
            }
            return EnvironmentUtils.secondarySdcardPath;
        }

        public static String getDataPath(Context context, SdcardType type) {
            String path = null;
            if (SdcardType.FIRST_SD_CARD == type) {
                path = getSdcardPath();
            } else if (SdcardType.SECOND_SD_CARD == type) {
                path = getSecondarySdcardPath(context);
            }
            if (StringUtils.isEmpty(path)) {
                path = "";
            }
            return path + File.separator + "Android" + File.separator + "data" + File.separator + EnvironmentUtils.getAppPackageName();
        }

        private static String getSecondarySdcardPathFromExternalFiles(Context context) {
            if (EnvironmentUtils.externalPath == null) {
                initExternalPath(context);
            }
            if (StringUtils.isEmpty(EnvironmentUtils.externalPath)) {
                return "";
            }
            String suffix = File.separator + "Android" + File.separator + "data" + File.separator + EnvironmentUtils.appPackageName;
            return EnvironmentUtils.externalPath.replaceAll(suffix, "");
        }

        private static String initExternalPath(Context context) {
            if (EnvironmentUtils.externalPath != null) {
                return EnvironmentUtils.externalPath;
            }
            File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
            if (externalFilesDirs == null || externalFilesDirs.length < 2 || externalFilesDirs[1] == null) {
                EnvironmentUtils.externalPath = "";
            } else {
                try {
                    EnvironmentUtils.externalPath = externalFilesDirs[1].getCanonicalPath().replaceAll(File.separator + "files", "");
                } catch (IOException e) {
                    e.printStackTrace();
                    EnvironmentUtils.externalPath = "";
                }
            }
            return EnvironmentUtils.externalPath;
        }

        private static String getSecondarySdcardPathFromVolumePaths(Context context) throws Exception {
            if (SDKVersionUtils.sdkThan11()) {
                StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
                String[] paths = (String[]) storageManager.getClass().getMethod("getVolumePaths").invoke(storageManager);
                if (paths != null) {
                    for (String path : paths) {
                        File file = new File(path);
                        if (file.canWrite() && !FileUtils.testDirPermissions(path, EnvironmentUtils.sdcardPath)) {
                            return file.getCanonicalPath();
                        }
                    }
                }
            }
            return "";
        }

        private static String scanForStoragePaths() {
            if (!storagePaths.isEmpty()) {
                return storagePaths.get(0);
            }
            File root = new File("/");
            File[] rootFiles = root.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return !file.getName().contains("dev");
                }
            });
            if (rootFiles != null) {
                for (File file : rootFiles) {
                    if (file.isDirectory() && file.canRead() && file.canWrite()) {
                        checkAndAddStoragePath(file);
                    } else {
                        File[] subFiles = file.listFiles();
                        if (subFiles != null) {
                            for (File subFile : subFiles) {
                                checkAndAddStoragePath(subFile);
                            }
                        }
                    }
                }
            }
            return storagePaths.isEmpty() ? "" : storagePaths.get(0);
        }

        private static void checkAndAddStoragePath(File file) {
            if (file != null && file.canRead() && file.canWrite() && getUsableSpace(file) > 3145728) { // > 3MB
                addPathIfUnique(file.getAbsolutePath());
            }
        }

        private static void addPathIfUnique(String path) {
            if (!isDotPath(path) && !FileUtils.testDirPermissions(path, EnvironmentUtils.sdcardPath)) {
                if (storagePaths.isEmpty()) {
                    storagePaths.add(path);
                    return;
                }
                boolean exists = false;
                for (String p : storagePaths) {
                    if (p.contains(path) || path.contains(p)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    storagePaths.add(path);
                }
            }
        }

        private static boolean isDotPath(String path) {
            return path.contains(EnvironmentUtils.DOT_SEPARATOR);
        }
    }

    /**
     * Native CPU information utility.
     */
    public static class CPU {
        public static native int armArch();

        public static native int cpuFamily();

        public static native int cpuFeatures();

        static {
            try {
                System.loadLibrary("environmentutils_cpu");
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Device-specific configuration and identifiers.
     */
    public static class DeviceConfig {

        private static final int[] NETWORK_TYPE_MAPPING = {0, 0, 0, 3, 0, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 3};

        private static String deviceId = "";

        private static String subscriberId = "";

        private static String macAddress = "";

        private static int initialNetworkType;

        private static ConnectivityManager connectivityManager;

        @SuppressLint("HardwareIds")
        public static void init(Context context) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                deviceId = telephonyManager.getDeviceId();
            } catch (SecurityException ex) {
                ex.printStackTrace();
            }
            if (deviceId == null) {
                deviceId = "";
            }
            try {
                subscriberId = telephonyManager.getSubscriberId();
            } catch (SecurityException ex) {
                ex.printStackTrace();
            }
            if (subscriberId == null) {
                subscriberId = "";
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null && wifiManager.getConnectionInfo() != null) {
                macAddress = wifiManager.getConnectionInfo().getMacAddress();
            }
            if (macAddress == null) {
                macAddress = "";
            }
            initialNetworkType = NETWORK_TYPE_MAPPING[getTelephonyNetworkType(context)];
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        public static String getDeviceId() {
            return deviceId;
        }

        public static String getSubscriberId() {
            return subscriberId;
        }

        public static String getMacAddress() {
            return macAddress;
        }

        /**
         * Returns the current network type.
         * -1: No connection, 1: Mobile (WAP), 2: WiFi, 3: Mobile (Broadband)
         */
        public static int getNetworkType() {
            if (connectivityManager == null) {
                return 1;
            }
            NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
            if (activeInfo == null || !activeInfo.isConnected()) {
                return -1;
            }
            if (activeInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return 2;
            }
            if (isMobileWap(activeInfo)) {
                return 1;
            }
            return initialNetworkType;
        }

        public static boolean isConnected() {
            NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
            return activeInfo != null && activeInfo.isConnected();
        }

        public static String getIPAddress() {
            return getIPAddress(true);
        }

        private static String getIPAddress(boolean useIPv4) {
            try {
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();
                        if (!addr.isLoopbackAddress()) {
                            String sAddr = addr.getHostAddress();
                            boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                            if (useIPv4) {
                                if (isIPv4) return sAddr;
                            } else {
                                if (!isIPv4) {
                                    int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                    return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        private static int getTelephonyNetworkType(Context context) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return 0;
            }
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int type = tm.getNetworkType();
            return (type < 0 || type >= NETWORK_TYPE_MAPPING.length) ? 0 : type;
        }

        private static boolean isMobileWap(NetworkInfo info) {
            return info.getType() == ConnectivityManager.TYPE_MOBILE && !StringUtils.isEmpty(Proxy.getDefaultHost());
        }
    }

    /**
     * Application-specific configuration loaded from assets.
     */
    public static class AppConfig {

        private static String appVersion = "";
        private static String versionName = "";
        private static boolean verificationEnable = false;
        private static boolean urlPrintEnable = false;
        private static boolean testMode = false;
        private static boolean checkUpdateEnable = true;
        private static boolean logEnable = true;
        private static boolean adSdkEnable = true;
        private static String channelType = "";
        private static String channelNumber = "";
        private static String build = "";
        private static String updateCategory = "";
        private static String noAdChannels = "";
        private static String noShortcutChannels = "";
        private static boolean qihooUnionEnable = false;
        private static Map<String, String> configMaps;

        public static void init(Context context) {
            loadConfig(context);
            loadBuildInfo(context);
            loadChannelInfo(context);
        }

        private static void loadConfig(Context context) {
            InputStream is = null;
            try {
                is = context.getAssets().open("config");
                configMaps = XmlUtils.m8332a(is);
                parseConfigParams();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static void parseConfigParams() {
            if (configMaps == null) return;
            try {
                appVersion = configMaps.get("app_version");
                verificationEnable = stringToBool(configMaps.get("verification_enable"), false);
                versionName = configMaps.get("version_name");
                urlPrintEnable = stringToBool(configMaps.get("url_print_enable"), false);
                testMode = stringToBool(configMaps.get("test_mode"), false);
                checkUpdateEnable = stringToBool(configMaps.get("app_checkupdate_enable"), true);
                logEnable = stringToBool(configMaps.get("log_enable"), true);
                adSdkEnable = stringToBool(configMaps.get("ad_sdk_enable"), true);
                updateCategory = configMaps.get("update_category");
                noAdChannels = configMaps.get("no_ad_channels");
                noShortcutChannels = configMaps.get("no_shortcut_channels");
                qihooUnionEnable = stringToBool(configMaps.get("360union_enable"), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtils.info("Config", "AppVersion:" + appVersion);
        }

        private static void loadChannelInfo(Context context) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(context.getAssets().open("channel")));
                channelType = parseChannelPart("0", reader.readLine());
                channelNumber = parseChannelPart("0", reader.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static String parseChannelPart(String defaultValue, String line) {
            if (line != null) {
                String trim = line.trim();
                int idx = trim.lastIndexOf('_');
                if (idx > -1) {
                    return trim.substring(idx + 1);
                }
            }
            return defaultValue;
        }

        private static void loadBuildInfo(Context context) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(context.getAssets().open("build")));
                String line = reader.readLine();
                if (line != null) {
                    build = line.trim();
                }
            } catch (Exception e) {
                e.printStackTrace();
                build = "0";
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static boolean getAppCheckUpdateEnable() {
            return checkUpdateEnable;
        }

        public static String getChannelType() {
            return channelType;
        }

        public static String getChannelNumber() {
            return channelNumber;
        }

        public static String getBuild() {
            return build;
        }

        public static String getAppVersion() {
            return appVersion;
        }

        public static String getVersionName() {
            return versionName;
        }

        public static boolean getVerificationEnable() {
            return verificationEnable;
        }

        public static boolean getTestMode() {
            return testMode;
        }

        public static String getNoShortcutChannels() {
            return noShortcutChannels;
        }

        private static boolean stringToBool(String str, boolean def) {
            if ("false".equalsIgnoreCase(str)) return false;
            if ("true".equalsIgnoreCase(str)) return true;
            return def;
        }
    }

    /**
     * Unique identifier configuration.
     */
    public static class UUIDConfig {

        private static String utdid;
        private static HashMap<String, Object> uuidMaps = new HashMap<>();
        private static JSONObject uuidJsonObject;

        public static void init(Context context) {
            try {
                OpenUDIDManager.m8571a(context);
                uuidMaps.put("openudid", OpenUDIDManager.m8572a());
                String mac = DeviceConfig.getMacAddress().replaceAll("[-:]", "");
                uuidMaps.put("hid", SecurityUtils.TEA.encrypt(mac));
                String id = DeviceConfig.getDeviceId();
                uuidMaps.put("uid", !StringUtils.isEmpty(id) ? id : mac);
                uuidMaps.put("mid", URLEncoder.encode(Build.MODEL, "UTF-8"));
                uuidMaps.put("imsi", URLEncoder.encode(DeviceConfig.getSubscriberId(), "UTF-8"));
                uuidMaps.put("s", "s200");
                uuidMaps.put("splus", URLEncoder.encode(Build.VERSION.RELEASE + "/" + Build.VERSION.SDK_INT, "UTF-8"));
                uuidMaps.put("rom", URLEncoder.encode(Build.FINGERPRINT, "UTF-8"));
                uuidMaps.put("v", "v" + AppConfig.getAppVersion());
                uuidMaps.put("f", "f" + AppConfig.getChannelType());
                uuidMaps.put("alf", "alf" + AppConfig.getChannelNumber());
                uuidMaps.put("active", isFirstActive(context) ? 1 : 0);
                uuidMaps.put("net", 0);
                uuidMaps.put("tid", 0L);
                uuidMaps.put("resolution", getResolution(context));
                
                String pkg = context.getPackageName();
                int idx = pkg.lastIndexOf('.');
                if (idx > 0) {
                    uuidMaps.put("app", pkg.substring(idx + 1));
                }
                
                utdid = UTDevice.getUtdid(context);
                uuidMaps.put("utdid", utdid);
                uuidJsonObject = new JSONObject(uuidMaps);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        private static String getResolution(Context context) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm.widthPixels + "x" + dm.heightPixels;
        }

        public static String getUtdid() {
            return utdid;
        }

        public static String getS() {
            return (String) uuidMaps.get("s");
        }

        public static String getV() {
            return (String) uuidMaps.get("v");
        }

        public static String getF() {
            return (String) uuidMaps.get("f");
        }

        public static HashMap<String, Object> getUuidMaps() {
            uuidMaps.put("net", DeviceConfig.getNetworkType());
            return uuidMaps;
        }

        public static JSONObject getUuidJsonObject() {
            try {
                uuidJsonObject.put("net", DeviceConfig.getNetworkType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return uuidJsonObject;
        }

        public static void setTid(long tid) {
            uuidMaps.put("tid", tid);
            try {
                uuidJsonObject.put("tid", tid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public static long getTid() {
            return (Long) uuidMaps.get("tid");
        }

        private static boolean isFirstActive(Context context) {
            try {
                Closeable c = context.openFileInput("flag");
                close(c);
                return false;
            } catch (Exception e) {
                try {
                    close(context.openFileOutput("flag", Context.MODE_PRIVATE));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return true;
            }
        }

        private static void close(Closeable c) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
