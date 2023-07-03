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

/* loaded from: classes.dex */
public class EnvironmentUtils {

    /* renamed from: a */
    private static String packageName;

    /* renamed from: b */
    private static String sdcardPath;

    /* renamed from: c */
    private static String f2434c;

    /* renamed from: d */
    private static String externalPath;

    /* renamed from: e */
    private static final String spearator = File.separator + ".";

    static {
        String absolutePath;
        File file = new File("sdcard");
        if (!file.exists() || !file.canWrite()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (!externalStorageDirectory.canWrite()) {
                File[] listFiles = externalStorageDirectory.getParentFile().listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].isDirectory() && listFiles[i].canWrite()) {
                        file = listFiles[i];
                        break;
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

    /* renamed from: a */
    public static void m8525a(Context context) {
        AppConfig.m8515a(context);
        DeviceConfig.initConfig(context);
        UUIDConfig.init(context);
        packageName = context.getPackageName();
        m8517g();
    }

    @TargetApi(11)
    /* renamed from: g */
    private static void m8517g() {
        if (SDKVersionUtils.sdkThan11()) {
            try {
                Method method = AsyncTask.class.getMethod("setDefaultExecutor", Executor.class);
                @SuppressLint("SoonBlockedPrivateApi") Field declaredField = ThreadPoolExecutor.class.getDeclaredField("defaultHandler");
                declaredField.setAccessible(true);
                declaredField.set(null, new ThreadPoolExecutor.DiscardOldestPolicy());
                method.invoke(null, (ThreadPoolExecutor) AsyncTask.THREAD_POOL_EXECUTOR);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static String m8526a() {
        return packageName;
    }

    /* renamed from: com.sds.android.sdk.lib.util.EnvironmentUtils$d */
    /* loaded from: classes.dex */
    public static class C0605d {

        /* renamed from: a */
        private static ArrayList<String> f2463a = new ArrayList<>();

        /* renamed from: com.sds.android.sdk.lib.util.EnvironmentUtils$d$a */
        /* loaded from: classes.dex */
        public enum EnumC0607a {
            FIRST_SD_CARD,
            SECOND_SD_CARD
        }

        /* renamed from: a */
        public static boolean m8472a() {
            return "mounted".equals(Environment.getExternalStorageState());
        }

        /* renamed from: a */
        public static long m8469a(File file) {
            if (SDKVersionUtils.sdkThan9()) {
                return file.getUsableSpace();
            }
            StatFs statFs = new StatFs(file.getPath());
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        }

        /* renamed from: a */
        public static File m8471a(Context context) {
            File externalCacheDir = SDKVersionUtils.sdkThan8() ? context.getExternalCacheDir() : null;
            if (externalCacheDir == null) {
                externalCacheDir = new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
            }
            externalCacheDir.mkdirs();
            if (externalCacheDir.isDirectory()) {
                return externalCacheDir;
            }
            return null;
        }

        /* renamed from: b */
        public static String getSdcardPath() {
            return EnvironmentUtils.sdcardPath;
        }

        /* renamed from: b */
        public static String m8466b(Context context) {
            File file = null;
            if (m8472a()) {
                file = m8471a(context);
            }
            return file != null ? file.getAbsolutePath() : context.getCacheDir().getAbsolutePath();
        }

        /* renamed from: a */
        public static boolean m8468a(String str) {
            if (new File(str).canWrite()) {
                String str2 = str + File.separator;
                int i = 0;
                while (FileUtils.isFile(str2 + i)) {
                    i++;
                }
                File m8407e = FileUtils.createFile(str2 + i);
                if (m8407e != null) {
                    m8407e.delete();
                    return true;
                }
                return false;
            }
            return false;
        }

        /* renamed from: c */
        public static String m8462c(Context context) {
            String m8460d = m8460d(context);
            if (StringUtils.isEmpty(m8460d) || !FileUtils.m8419a(m8460d)) {
                return EnvironmentUtils.sdcardPath + File.separator + MediaStoreOld.AUTHORITY;
            }
            if (SDKVersionUtils.sdkThan19()) {
                return EnvironmentUtils.externalPath;
            }
            return m8460d + File.separator + MediaStoreOld.AUTHORITY;
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0051 -> B:19:0x000a). Please submit an issue!!! */
        /* renamed from: d */
        public static String m8460d(Context context) {
            String str;
            if (EnvironmentUtils.f2434c != null) {
                return EnvironmentUtils.f2434c;
            }
            try {
                String unused = EnvironmentUtils.f2434c = m8459e(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!StringUtils.isEmpty(EnvironmentUtils.f2434c)) {
                str = EnvironmentUtils.f2434c;
            } else {
                try {
                    String unused2 = EnvironmentUtils.f2434c = m8457g(context);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (!StringUtils.isEmpty(EnvironmentUtils.f2434c)) {
                    str = EnvironmentUtils.f2434c;
                } else {
                    String unused3 = EnvironmentUtils.f2434c = m8463c();
                    if (!StringUtils.isEmpty(EnvironmentUtils.f2434c)) {
                        str = EnvironmentUtils.f2434c;
                    }
                    str = EnvironmentUtils.f2434c;
                }
            }
            return str;
        }

        /* renamed from: a */
        public static String m8470a(Context context, EnumC0607a enumC0607a) {
            String str = null;
            if (EnumC0607a.FIRST_SD_CARD == enumC0607a) {
                str = getSdcardPath();
            } else if (EnumC0607a.SECOND_SD_CARD == enumC0607a) {
                str = m8460d(context);
            }
            if (StringUtils.isEmpty(str)) {
                str = "";
            }
            return new StringBuffer(str).append(File.separator).append("Android").append(File.separator).append("data").append(File.separator).append(EnvironmentUtils.m8526a()).toString();
        }

        /* renamed from: e */
        private static String m8459e(Context context) {
            if (EnvironmentUtils.externalPath == null) {
                getExternalFilePath(context);
            }
            if (StringUtils.isEmpty(EnvironmentUtils.externalPath)) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(File.separator);
            stringBuffer.append("Android");
            stringBuffer.append(File.separator);
            stringBuffer.append("data");
            stringBuffer.append(File.separator);
            stringBuffer.append(EnvironmentUtils.packageName);
            return EnvironmentUtils.externalPath.replaceAll(stringBuffer.toString(), "");
        }

        /* renamed from: f */
        private static String getExternalFilePath(Context context) {
            if (EnvironmentUtils.externalPath != null) {
                return EnvironmentUtils.externalPath;
            }
            File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
            if (externalFilesDirs == null || externalFilesDirs.length < 2 || externalFilesDirs[1] == null) {
                String unused = EnvironmentUtils.externalPath = "";
            } else {
                try {
                    try {
                        String unused2 = EnvironmentUtils.externalPath = externalFilesDirs[1].getCanonicalPath().replaceAll(File.separator + "files", "");
                        if (EnvironmentUtils.externalPath == null) {
                            String unused3 = EnvironmentUtils.externalPath = "";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (EnvironmentUtils.externalPath == null) {
                            String unused4 = EnvironmentUtils.externalPath = "";
                        }
                    }
                } catch (Throwable th) {
                    if (EnvironmentUtils.externalPath == null) {
                        String unused5 = EnvironmentUtils.externalPath = "";
                    }
                    throw th;
                }
            }
            return EnvironmentUtils.externalPath;
        }

        /* renamed from: g */
        private static String m8457g(Context context) throws Exception {
            String[] strArr;
            if (SDKVersionUtils.sdkThan11()) {
                StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
                for (String str : (String[]) storageManager.getClass().getMethod("getVolumePaths", null)
                        .invoke(storageManager, null)) {
                    File file = new File(str);
                    if (file.canWrite() && !FileUtils.m8408d(str, EnvironmentUtils.sdcardPath)) {
                        return file.getCanonicalPath();
                    }
                }
            }
            return "";
        }

        /* renamed from: c */
        private static String m8463c() {
            File[] listFiles;
            if (!f2463a.isEmpty()) {
                return f2463a.get(0);
            }
            File[] files = File.listRoots();
            File[] subFiles = files[0].listFiles();
            if(subFiles == null){
                return "";
            }
            for (File file : files[0].listFiles(new FileFilter() { // from class: com.sds.android.sdk.lib.util.EnvironmentUtils.d.1
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    return !file2.getName().contains("dev");
                }
            })) {
                if (file.isDirectory() && file.canRead() && file.canWrite()) {
                    m8465b(file);
                } else {
                    File[] listFiles2 = file.listFiles();
                    if (listFiles2 != null) {
                        for (File file2 : listFiles2) {
                            m8465b(file2);
                        }
                    }
                }
            }
            if (f2463a.isEmpty()) {
                return "";
            }
            return f2463a.get(0);
        }

        /* renamed from: b */
        private static void m8465b(File file) {
            if (file != null && file.canRead() && file.canWrite() && m8469a(file) > 3145728) {
                m8464b(file.getAbsolutePath());
            }
        }

        /* renamed from: b */
        private static void m8464b(String str) {
            boolean z;
            if (!m8461c(str) && !FileUtils.m8408d(str, EnvironmentUtils.sdcardPath)) {
                if (f2463a.isEmpty()) {
                    f2463a.add(str);
                    return;
                }
                boolean z2 = false;
                Iterator<String> it = f2463a.iterator();
                while (true) {
                    z = z2;
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    z2 = (next.contains(str) || str.contains(next)) ? true : z;
                }
                if (!z) {
                    f2463a.add(str);
                }
            }
        }

        /* renamed from: c */
        private static boolean m8461c(String str) {
            return str.contains(EnvironmentUtils.spearator);
        }
    }

    /* loaded from: classes.dex */
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

    /* renamed from: com.sds.android.sdk.lib.util.EnvironmentUtils$c */
    /* loaded from: classes.dex */
    public static class DeviceConfig {

        /* renamed from: a */
        private static final int[] f2456a = {0, 0, 0, 3, 0, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 3};

        /* renamed from: b */
        private static String deviceId = "";

        /* renamed from: c */
        private static String subscriberId = "";

        /* renamed from: d */
        private static String macAddress = "";

        /* renamed from: e */
        private static NetworkInfo activeNetworkInfo;

        /* renamed from: f */
        private static int f2461f;

        /* renamed from: g */
        private static ConnectivityManager connectivityManager;

        /* renamed from: a */
        @SuppressLint("HardwareIds")
        public static void initConfig(Context context) {
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
            macAddress = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
            if (macAddress == null) {
                macAddress = "";
            }
            f2461f = f2456a[getNetworkType(context)];
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }

        /* renamed from: a */
        public static String getDeviceId() {
            return deviceId;
        }

        /* renamed from: b */
        public static String getSubscriberId() {
            return subscriberId;
        }

        /* renamed from: c */
        public static String getMacAddress() {
            return macAddress;
        }

        /* renamed from: d */
        public static int hasNetwork() {
            int i = f2461f;
            if (connectivityManager == null) {
                return 1;
            }
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (!isConnectedNetwork(activeNetworkInfo)) {
                return -1;
            }
            if (isConnected(activeNetworkInfo)) {
                return 2;
            }
            if (m8477c(activeNetworkInfo)) {
                return 1;
            }
            return i;
        }

        /* renamed from: e */
        public static boolean isConnected() {
            return isConnectedNetwork(connectivityManager.getActiveNetworkInfo());
        }

        /* renamed from: f */
        public static String m8473f() {
            return m8482a(true);
        }

        /* renamed from: a */
        private static String m8482a(boolean z) {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            String hostAddress = nextElement.getHostAddress();
                            if (z == InetAddressUtils.isIPv4Address(hostAddress)) {
                                return hostAddress;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        /* renamed from: b */
        private static int getNetworkType(Context context) {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                return 0;
            }
            int networkType = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkType();
            if (networkType < 0 || networkType >= f2456a.length) {
                return 0;
            }
            return networkType;
        }

        /* renamed from: a */
        private static boolean isConnectedNetwork(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected();
        }

        /* renamed from: b */
        private static boolean m8479b(NetworkInfo networkInfo) {
            return networkInfo.getType() == 0;
        }

        /* renamed from: c */
        private static boolean m8477c(NetworkInfo networkInfo) {
            return m8479b(networkInfo) && !StringUtils.isEmpty(Proxy.getDefaultHost());
        }

        /* renamed from: d */
        private static boolean isConnected(NetworkInfo networkInfo) {
            return networkInfo.getType() == 1;
        }
    }

    /* renamed from: com.sds.android.sdk.lib.util.EnvironmentUtils$a */
    /* loaded from: classes.dex */
    public static class AppConfig {

        /* renamed from: a */
        private static String appVersion = "";

        /* renamed from: b */
        private static String versionName = "";

        /* renamed from: c */
        private static boolean verificationEnable = false;

        /* renamed from: d */
        private static boolean urlPrintEnable = false;

        /* renamed from: e */
        private static boolean testMode = false;

        /* renamed from: f */
        private static boolean appcheckUpdateEnable = true;

        /* renamed from: g */
        private static boolean logEnable = true;

        /* renamed from: h */
        private static boolean adSdkEnable = true;

        /* renamed from: i */
        private static String channelType = "";

        /* renamed from: j */
        private static String channelNumber = "";

        /* renamed from: k */
        private static String build = "";

        /* renamed from: l */
        private static String updateCategory = "";

        /* renamed from: m */
        private static String noAdChannels = "";

        /* renamed from: n */
        private static String noShortcutChannels = "";

        /* renamed from: o */
        private static boolean qihooUnionEnable = false;

        /* renamed from: p */
        private static Map<String, String> configMaps;

        /* renamed from: a */
        public static void m8515a(Context context) {
            m8511b(context);
            m8507d(context);
            m8509c(context);
        }

        /* renamed from: b */
        private static void m8511b(Context context) {
            InputStream inputStream = null;
            try {
                try {
                    inputStream = context.getAssets().open("config");
                    configMaps = XmlUtils.m8332a(inputStream);
                    m8500k();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        }

        /* renamed from: k */
        private static void m8500k() {
            try {
                appVersion = configMaps.get("app_version");
                verificationEnable = stringToBool(configMaps.get("verification_enable"), false);
                versionName = configMaps.get("version_name");
                urlPrintEnable = stringToBool(configMaps.get("url_print_enable"), false);
                testMode = stringToBool(configMaps.get("test_mode"), false);
                appcheckUpdateEnable = stringToBool(configMaps.get("app_checkupdate_enable"), true);
                logEnable = stringToBool(configMaps.get("log_enable"), true);
                adSdkEnable = stringToBool(configMaps.get("ad_sdk_enable"), true);
                updateCategory = configMaps.get("update_category");
                noAdChannels = configMaps.get("no_ad_channels");
                noShortcutChannels = configMaps.get("no_shortcut_channels");
                qihooUnionEnable = stringToBool(configMaps.get("360union_enable"), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtils.info("Config", "Build:" + build);
            LogUtils.info("Config", "AppVersion:" + appVersion);
            LogUtils.info("Config", "VerificationEnable:" + verificationEnable);
            LogUtils.info("Config", "VersionName:" + versionName);
            LogUtils.info("Config", "UrlPrintEnable:" + urlPrintEnable);
            LogUtils.info("Config", "TestMode:" + testMode);
            LogUtils.info("Config", "AppCheckUpdateEnable:" + appcheckUpdateEnable);
            LogUtils.info("Config", "LogEnable:" + logEnable);
            LogUtils.info("Config", "AdSdkEnable:" + adSdkEnable);
            LogUtils.info("Config", "UpdateCategory:" + updateCategory);
            LogUtils.info("Config", "360UnionEnable:" + qihooUnionEnable);
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* renamed from: c */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static void m8509c(Context context) {
            BufferedReader bufferedReader = null;
            Exception e;
            String str;
            String str2 = "0";
            Throwable th;
            BufferedReader bufferedReader2 = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("channel")));
            } catch (Exception e2) {
                bufferedReader = null;
                e = e2;
                str = "0";
            } catch (Throwable th1) {
                th = th1;
                if (bufferedReader2 != null) {
                }
                //throw th;
            }
            try {
                try {
                    str = m8514a("0", bufferedReader.readLine());
                } catch (Exception e3) {
                    e = e3;
                    str = "0";
                }
                try {
                    str2 = m8514a("0", bufferedReader.readLine());
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    channelNumber = str2;
                    channelType = str;
                }
                channelNumber = str2;
                channelType = str;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                //throw th;
            }
        }

        /* renamed from: a */
        private static String m8514a(String str, String str2) {
            String trim;
            int lastIndexOf;
            if (str2 != null && (lastIndexOf = (trim = str2.trim()).lastIndexOf(95)) > -1) {
                return trim.substring(lastIndexOf + 1);
            }
            return str;
        }

        /* renamed from: d */
        private static void m8507d(Context context) {
            Exception exc;
            String str = null;
            Throwable th;
            BufferedReader bufferedReader = null;
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(context.getAssets().open("build")));
                    try {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine != null) {
                                try {
                                    str = readLine.trim();
                                } catch (Exception e) {
                                    str = readLine;
                                    bufferedReader = bufferedReader2;
                                    exc = e;
                                    exc.printStackTrace();
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                    build = str;
                                }
                            } else {
                                str = readLine;
                            }
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } catch (Throwable th1) {
                            th = th1;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        bufferedReader = bufferedReader2;
                        exc = e5;
                        str = "0";
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e6) {
                exc = e6;
                str = "0";
            }
            build = str;
        }

        /* renamed from: a */
        public static boolean getAppCheckUpdateEnable() {
            return appcheckUpdateEnable;
        }

        /* renamed from: b */
        public static String getChannelType() {
            return channelType;
        }

        /* renamed from: c */
        public static String getChannelNumber() {
            return channelNumber;
        }

        /* renamed from: d */
        public static String getBuild() {
            return build;
        }

        /* renamed from: e */
        public static String getAppVersion() {
            return appVersion;
        }

        /* renamed from: f */
        public static String getVersionName() {
            return versionName;
        }

        /* renamed from: g */
        public static boolean getVerificationEnable() {
            return verificationEnable;
        }

        /* renamed from: h */
        public static boolean m8503h() {
            return testMode;
        }

        /* renamed from: i */
        public static boolean getTestMode() {
            return testMode;
        }

        /* renamed from: j */
        public static String getNoShortcutChannels() {
            return noShortcutChannels;
        }

        /* renamed from: a */
        private static boolean stringToBool(String str, boolean result) {
            if ("false".equalsIgnoreCase(str)) {
                return false;
            }
            if ("true".equalsIgnoreCase(str)) {
                return true;
            }
            return result;
        }
    }

    /* renamed from: com.sds.android.sdk.lib.util.EnvironmentUtils$b */
    /* loaded from: classes.dex */
    public static class UUIDConfig {

        /* renamed from: a */
        private static String utdid;

        /* renamed from: b */
        private static HashMap<String, Object> uuidMaps = new HashMap<>();

        /* renamed from: c */
        private static JSONObject uuidJsonObject;

        /* renamed from: a */
        public static void init(Context context) {
            try {
                OpenUDIDManager.m8571a(context);
                uuidMaps.put("openudid", OpenUDIDManager.m8572a());
                String replaceAll = DeviceConfig.getMacAddress().replaceAll("[-:]", "");
                uuidMaps.put("hid", SecurityUtils.C0612d.m8352a(replaceAll));
                String m8485a = DeviceConfig.getDeviceId();
                HashMap<String, Object> hashMap = uuidMaps;
                if (!StringUtils.isEmpty(m8485a)) {
                    replaceAll = m8485a;
                }
                hashMap.put("uid", replaceAll);
                uuidMaps.put("mid", URLEncoder.encode(Build.MODEL, "UTF-8"));
                uuidMaps.put("imsi", URLEncoder.encode(DeviceConfig.getSubscriberId(), "UTF-8"));
                uuidMaps.put("s", "s200");
                uuidMaps.put("splus", URLEncoder.encode(Build.VERSION.RELEASE + "/" + Build.VERSION.SDK_INT, "UTF-8"));
                uuidMaps.put("rom", URLEncoder.encode(Build.FINGERPRINT, "UTF-8"));
                uuidMaps.put("v", "v" + AppConfig.getAppVersion());
                uuidMaps.put("f", "f" + AppConfig.getChannelType());
                uuidMaps.put("alf", "alf" + AppConfig.getChannelNumber());
                uuidMaps.put("active", Integer.valueOf(m8490c(context) ? 1 : 0));
                uuidMaps.put("net", 0);
                uuidMaps.put("tid", new Long(0L));
                uuidMaps.put("resolution", m8493b(context));
                List<String> m8335c = StringUtils.stringToArray(context.getPackageName(), ".");
                int size = m8335c.size();
                if (size > 0) {
                    uuidMaps.put("app", m8335c.get(size - 1));
                }
                utdid = UTDevice.getUtdid(context);
                uuidMaps.put("utdid", utdid);
                uuidJsonObject = new JSONObject(uuidMaps);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        /* renamed from: b */
        private static String m8493b(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
        }

        /* renamed from: a */
        public static String m8499a() {
            return utdid;
        }

        /* renamed from: b */
        public static String m8494b() {
            return (String) uuidMaps.get("s");
        }

        /* renamed from: c */
        public static String m8491c() {
            return (String) uuidMaps.get("v");
        }

        /* renamed from: d */
        public static String m8489d() {
            return (String) uuidMaps.get("f");
        }

        /* renamed from: e */
        public static HashMap<String, Object> m8488e() {
            uuidMaps.put("net", Integer.valueOf(DeviceConfig.hasNetwork()));
            return uuidMaps;
        }

        /* renamed from: f */
        public static JSONObject m8487f() {
            try {
                uuidJsonObject.put("net", DeviceConfig.hasNetwork());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return uuidJsonObject;
        }

        /* renamed from: a */
        public static void m8498a(long j) {
            uuidMaps.put("tid", Long.valueOf(j));
            try {
                uuidJsonObject.put("tid", j);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /* renamed from: g */
        public static long m8486g() {
            return ((Long) uuidMaps.get("tid")).longValue();
        }

        /* renamed from: c */
        private static boolean m8490c(Context context) {
            try {
                m8492b(context, "flag");
                return false;
            } catch (Exception e) {
                m8496a(context, "flag");
                return true;
            }
        }

        /* renamed from: a */
        private static void m8496a(Context context, String str) {
            try {
                m8495a(context.openFileOutput(str, 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* renamed from: b */
        private static void m8492b(Context context, String str) throws FileNotFoundException {
            m8495a(context.openFileInput(str));
        }

        /* renamed from: a */
        private static void m8495a(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
