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

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.openudid.OpenUDIDManager;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;
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
    private static String f2432a;

    /* renamed from: b */
    private static String f2433b;

    /* renamed from: c */
    private static String f2434c;

    /* renamed from: d */
    private static String f2435d;

    /* renamed from: e */
    private static final String f2436e = File.separator + ".";

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
        f2433b = absolutePath;
    }

    /* renamed from: a */
    public static void m8525a(Context context) {
        C0602a.m8515a(context);
        C0604c.m8484a(context);
        C0603b.m8497a(context);
        f2432a = context.getPackageName();
        m8517g();
    }

    @TargetApi(11)
    /* renamed from: g */
    private static void m8517g() {
        if (SDKVersionUtils.m8371c()) {
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
        return f2432a;
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
            if (SDKVersionUtils.m8372b()) {
                return file.getUsableSpace();
            }
            StatFs statFs = new StatFs(file.getPath());
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        }

        /* renamed from: a */
        public static File m8471a(Context context) {
            File externalCacheDir = SDKVersionUtils.m8373a() ? context.getExternalCacheDir() : null;
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
        public static String m8467b() {
            return EnvironmentUtils.f2433b;
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
                while (FileUtils.m8414b(str2 + i)) {
                    i++;
                }
                File m8407e = FileUtils.m8407e(str2 + i);
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
            if (StringUtils.m8346a(m8460d) || !FileUtils.m8419a(m8460d)) {
                return EnvironmentUtils.f2433b + File.separator + MediaStoreOld.AUTHORITY;
            }
            if (SDKVersionUtils.m8365i()) {
                return EnvironmentUtils.f2435d;
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
            if (!StringUtils.m8346a(EnvironmentUtils.f2434c)) {
                str = EnvironmentUtils.f2434c;
            } else {
                try {
                    String unused2 = EnvironmentUtils.f2434c = m8457g(context);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (!StringUtils.m8346a(EnvironmentUtils.f2434c)) {
                    str = EnvironmentUtils.f2434c;
                } else {
                    String unused3 = EnvironmentUtils.f2434c = m8463c();
                    if (!StringUtils.m8346a(EnvironmentUtils.f2434c)) {
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
                str = m8467b();
            } else if (EnumC0607a.SECOND_SD_CARD == enumC0607a) {
                str = m8460d(context);
            }
            if (StringUtils.m8346a(str)) {
                str = "";
            }
            return new StringBuffer(str).append(File.separator).append("Android").append(File.separator).append("data").append(File.separator).append(EnvironmentUtils.m8526a()).toString();
        }

        /* renamed from: e */
        private static String m8459e(Context context) {
            if (EnvironmentUtils.f2435d == null) {
                m8458f(context);
            }
            if (StringUtils.m8346a(EnvironmentUtils.f2435d)) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(File.separator);
            stringBuffer.append("Android");
            stringBuffer.append(File.separator);
            stringBuffer.append("data");
            stringBuffer.append(File.separator);
            stringBuffer.append(EnvironmentUtils.f2432a);
            return EnvironmentUtils.f2435d.replaceAll(stringBuffer.toString(), "");
        }

        /* renamed from: f */
        private static String m8458f(Context context) {
            if (EnvironmentUtils.f2435d != null) {
                return EnvironmentUtils.f2435d;
            }
            File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
            if (externalFilesDirs == null || externalFilesDirs.length < 2 || externalFilesDirs[1] == null) {
                String unused = EnvironmentUtils.f2435d = "";
            } else {
                try {
                    try {
                        String unused2 = EnvironmentUtils.f2435d = externalFilesDirs[1].getCanonicalPath().replaceAll(File.separator + "files", "");
                        if (EnvironmentUtils.f2435d == null) {
                            String unused3 = EnvironmentUtils.f2435d = "";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (EnvironmentUtils.f2435d == null) {
                            String unused4 = EnvironmentUtils.f2435d = "";
                        }
                    }
                } catch (Throwable th) {
                    if (EnvironmentUtils.f2435d == null) {
                        String unused5 = EnvironmentUtils.f2435d = "";
                    }
                    throw th;
                }
            }
            return EnvironmentUtils.f2435d;
        }

        /* renamed from: g */
        private static String m8457g(Context context) throws Exception {
            String[] strArr;
            if (SDKVersionUtils.m8371c()) {
                StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
                for (String str : (String[]) storageManager.getClass().getMethod("getVolumePaths", null)
                        .invoke(storageManager, null)) {
                    File file = new File(str);
                    if (file.canWrite() && !FileUtils.m8408d(str, EnvironmentUtils.f2433b)) {
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
            for (File file : File.listRoots()[0].listFiles(new FileFilter() { // from class: com.sds.android.sdk.lib.util.EnvironmentUtils.d.1
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
            if (!m8461c(str) && !FileUtils.m8408d(str, EnvironmentUtils.f2433b)) {
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
            return str.contains(EnvironmentUtils.f2436e);
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
    public static class C0604c {

        /* renamed from: a */
        private static final int[] f2456a = {0, 0, 0, 3, 0, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 3};

        /* renamed from: b */
        private static String f2457b = "";

        /* renamed from: c */
        private static String f2458c = "";

        /* renamed from: d */
        private static String f2459d = "";

        /* renamed from: e */
        private static NetworkInfo f2460e;

        /* renamed from: f */
        private static int f2461f;

        /* renamed from: g */
        private static ConnectivityManager f2462g;

        /* renamed from: a */
        public static void m8484a(Context context) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            f2457b = telephonyManager.getDeviceId();
            if (f2457b == null) {
                f2457b = "";
            }
            f2458c = telephonyManager.getSubscriberId();
            if (f2458c == null) {
                f2458c = "";
            }
            f2459d = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
            if (f2459d == null) {
                f2459d = "";
            }
            f2461f = f2456a[m8480b(context)];
            f2462g = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            f2460e = f2462g.getActiveNetworkInfo();
        }

        /* renamed from: a */
        public static String m8485a() {
            return f2457b;
        }

        /* renamed from: b */
        public static String m8481b() {
            return f2458c;
        }

        /* renamed from: c */
        public static String m8478c() {
            return f2459d;
        }

        /* renamed from: d */
        public static int m8476d() {
            int i = f2461f;
            if (f2462g == null) {
                return 1;
            }
            f2460e = f2462g.getActiveNetworkInfo();
            if (!m8483a(f2460e)) {
                return -1;
            }
            if (m8475d(f2460e)) {
                return 2;
            }
            if (m8477c(f2460e)) {
                return 1;
            }
            return i;
        }

        /* renamed from: e */
        public static boolean m8474e() {
            return m8483a(f2462g.getActiveNetworkInfo());
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
        private static int m8480b(Context context) {

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
        private static boolean m8483a(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected();
        }

        /* renamed from: b */
        private static boolean m8479b(NetworkInfo networkInfo) {
            return networkInfo.getType() == 0;
        }

        /* renamed from: c */
        private static boolean m8477c(NetworkInfo networkInfo) {
            return m8479b(networkInfo) && !StringUtils.m8346a(Proxy.getDefaultHost());
        }

        /* renamed from: d */
        private static boolean m8475d(NetworkInfo networkInfo) {
            return networkInfo.getType() == 1;
        }
    }

    /* renamed from: com.sds.android.sdk.lib.util.EnvironmentUtils$a */
    /* loaded from: classes.dex */
    public static class C0602a {

        /* renamed from: a */
        private static String f2437a = "";

        /* renamed from: b */
        private static String f2438b = "";

        /* renamed from: c */
        private static boolean f2439c = false;

        /* renamed from: d */
        private static boolean f2440d = false;

        /* renamed from: e */
        private static boolean f2441e = false;

        /* renamed from: f */
        private static boolean f2442f = true;

        /* renamed from: g */
        private static boolean f2443g = true;

        /* renamed from: h */
        private static boolean f2444h = true;

        /* renamed from: i */
        private static String f2445i = "";

        /* renamed from: j */
        private static String f2446j = "";

        /* renamed from: k */
        private static String f2447k = "";

        /* renamed from: l */
        private static String f2448l = "";

        /* renamed from: m */
        private static String f2449m = "";

        /* renamed from: n */
        private static String f2450n = "";

        /* renamed from: o */
        private static boolean f2451o = false;

        /* renamed from: p */
        private static Map<String, String> f2452p;

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
                    f2452p = XmlUtils.m8332a(inputStream);
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
                f2437a = f2452p.get("app_version");
                f2439c = m8513a(f2452p.get("verification_enable"), false);
                f2438b = f2452p.get("version_name");
                f2440d = m8513a(f2452p.get("url_print_enable"), false);
                f2441e = m8513a(f2452p.get("test_mode"), false);
                f2442f = m8513a(f2452p.get("app_checkupdate_enable"), true);
                f2443g = m8513a(f2452p.get("log_enable"), true);
                f2444h = m8513a(f2452p.get("ad_sdk_enable"), true);
                f2448l = f2452p.get("update_category");
                f2449m = f2452p.get("no_ad_channels");
                f2450n = f2452p.get("no_shortcut_channels");
                f2451o = m8513a(f2452p.get("360union_enable"), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtils.m8379d("Config", "Build:" + f2447k);
            LogUtils.m8379d("Config", "AppVersion:" + f2437a);
            LogUtils.m8379d("Config", "VerificationEnable:" + f2439c);
            LogUtils.m8379d("Config", "VersionName:" + f2438b);
            LogUtils.m8379d("Config", "UrlPrintEnable:" + f2440d);
            LogUtils.m8379d("Config", "TestMode:" + f2441e);
            LogUtils.m8379d("Config", "AppCheckUpdateEnable:" + f2442f);
            LogUtils.m8379d("Config", "LogEnable:" + f2443g);
            LogUtils.m8379d("Config", "AdSdkEnable:" + f2444h);
            LogUtils.m8379d("Config", "UpdateCategory:" + f2448l);
            LogUtils.m8379d("Config", "360UnionEnable:" + f2451o);
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
            String str2 = FeedbackItem.STATUS_WAITING;
            Throwable th;
            BufferedReader bufferedReader2 = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("channel")));
            } catch (Exception e2) {
                bufferedReader = null;
                e = e2;
                str = FeedbackItem.STATUS_WAITING;
            } catch (Throwable th1) {
                th = th1;
                if (bufferedReader2 != null) {
                }
                //throw th;
            }
            try {
                try {
                    str = m8514a(FeedbackItem.STATUS_WAITING, bufferedReader.readLine());
                } catch (Exception e3) {
                    e = e3;
                    str = FeedbackItem.STATUS_WAITING;
                }
                try {
                    str2 = m8514a(FeedbackItem.STATUS_WAITING, bufferedReader.readLine());
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
                    f2446j = str2;
                    f2445i = str;
                }
                f2446j = str2;
                f2445i = str;
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
                                    f2447k = str;
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
                        str = FeedbackItem.STATUS_WAITING;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e6) {
                exc = e6;
                str = FeedbackItem.STATUS_WAITING;
            }
            f2447k = str;
        }

        /* renamed from: a */
        public static boolean m8516a() {
            return f2442f;
        }

        /* renamed from: b */
        public static String m8512b() {
            return f2445i;
        }

        /* renamed from: c */
        public static String m8510c() {
            return f2446j;
        }

        /* renamed from: d */
        public static String m8508d() {
            return f2447k;
        }

        /* renamed from: e */
        public static String m8506e() {
            return f2437a;
        }

        /* renamed from: f */
        public static String m8505f() {
            return f2438b;
        }

        /* renamed from: g */
        public static boolean m8504g() {
            return f2439c;
        }

        /* renamed from: h */
        public static boolean m8503h() {
            return f2441e;
        }

        /* renamed from: i */
        public static boolean m8502i() {
            return f2441e;
        }

        /* renamed from: j */
        public static String m8501j() {
            return f2450n;
        }

        /* renamed from: a */
        private static boolean m8513a(String str, boolean z) {
            if ("false".equalsIgnoreCase(str)) {
                return false;
            }
            if ("true".equalsIgnoreCase(str)) {
                return true;
            }
            return z;
        }
    }

    /* renamed from: com.sds.android.sdk.lib.util.EnvironmentUtils$b */
    /* loaded from: classes.dex */
    public static class C0603b {

        /* renamed from: a */
        private static String f2453a;

        /* renamed from: b */
        private static HashMap<String, Object> f2454b = new HashMap<>();

        /* renamed from: c */
        private static JSONObject f2455c;

        /* renamed from: a */
        public static void m8497a(Context context) {
            try {
                OpenUDIDManager.m8571a(context);
                f2454b.put("openudid", OpenUDIDManager.m8572a());
                String replaceAll = C0604c.m8478c().replaceAll("[-:]", "");
                f2454b.put("hid", SecurityUtils.C0612d.m8352a(replaceAll));
                String m8485a = C0604c.m8485a();
                HashMap<String, Object> hashMap = f2454b;
                if (!StringUtils.m8346a(m8485a)) {
                    replaceAll = m8485a;
                }
                hashMap.put("uid", replaceAll);
                f2454b.put("mid", URLEncoder.encode(Build.MODEL, "UTF-8"));
                f2454b.put("imsi", URLEncoder.encode(C0604c.m8481b(), "UTF-8"));
                f2454b.put("s", "s200");
                f2454b.put("splus", URLEncoder.encode(Build.VERSION.RELEASE + "/" + Build.VERSION.SDK_INT, "UTF-8"));
                f2454b.put("rom", URLEncoder.encode(Build.FINGERPRINT, "UTF-8"));
                f2454b.put("v", "v" + C0602a.m8506e());
                f2454b.put("f", "f" + C0602a.m8512b());
                f2454b.put("alf", "alf" + C0602a.m8510c());
                f2454b.put("active", Integer.valueOf(m8490c(context) ? 1 : 0));
                f2454b.put("net", 0);
                f2454b.put("tid", new Long(0L));
                f2454b.put("resolution", m8493b(context));
                List<String> m8335c = StringUtils.m8335c(context.getPackageName(), ".");
                int size = m8335c.size();
                if (size > 0) {
                    f2454b.put(OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY, m8335c.get(size - 1));
                }
                f2453a = UTDevice.getUtdid(context);
                f2454b.put("utdid", f2453a);
                f2455c = new JSONObject(f2454b);
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
            return f2453a;
        }

        /* renamed from: b */
        public static String m8494b() {
            return (String) f2454b.get("s");
        }

        /* renamed from: c */
        public static String m8491c() {
            return (String) f2454b.get("v");
        }

        /* renamed from: d */
        public static String m8489d() {
            return (String) f2454b.get("f");
        }

        /* renamed from: e */
        public static HashMap<String, Object> m8488e() {
            f2454b.put("net", Integer.valueOf(C0604c.m8476d()));
            return f2454b;
        }

        /* renamed from: f */
        public static JSONObject m8487f() {
            try {
                f2455c.put("net", C0604c.m8476d());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return f2455c;
        }

        /* renamed from: a */
        public static void m8498a(long j) {
            f2454b.put("tid", Long.valueOf(j));
            try {
                f2455c.put("tid", j);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /* renamed from: g */
        public static long m8486g() {
            return ((Long) f2454b.get("tid")).longValue();
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
