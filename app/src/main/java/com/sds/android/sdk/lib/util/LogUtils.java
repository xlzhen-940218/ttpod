package com.sds.android.sdk.lib.util;

import android.os.Process;
import android.util.Log;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.sds.android.sdk.lib.util.f */
/* loaded from: classes.dex */
public final class LogUtils {

    /* renamed from: a */
    private static boolean enableLog = true;

    /* renamed from: b */
    private static boolean f2474b = false;

    /* renamed from: c */
    private static final SimpleDateFormat f2475c = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");

    /* renamed from: d */
    private static String f2476d;

    /* renamed from: a */
    public static void setEnableLog(boolean enableLog) {
        LogUtils.enableLog = enableLog;
    }

    /* renamed from: a */
    public static void debug(String str, String str2) {
        if (enableLog) {
            String str3 = "TTPOD:" + str2;
            Log.d(str, str3);
            m8377f(str, str3);
        }
    }

    /* renamed from: a */
    public static void debug(String str, String str2, Object... objArr) {
        if (enableLog) {
            String format = String.format("TTPOD:" + str2, objArr);
            Log.d(str, format);
            m8377f(str, format);
        }
    }

    /* renamed from: b */
    public static void warning(String str, String str2) {
        if (enableLog) {
            String str3 = "TTPOD:" + str2;
            Log.w(str, str3);
            m8377f(str, str3);
        }
    }

    /* renamed from: a */
    public static void warning(String str, String str2, Throwable th) {
        if (enableLog) {
            String str3 = "TTPOD:" + str2;
            Log.w(str, str3, th);
            m8377f(str, str3 + "\n" + Log.getStackTraceString(th));
        }
    }

    /* renamed from: c */
    public static void error(String str, String str2) {
        if (enableLog) {
            String str3 = "TTPOD:" + str2;
            Log.e(str, str3);
            m8377f(str, str3);
        }
    }

    /* renamed from: b */
    public static void error(String str, String str2, Throwable th) {
        if (enableLog) {
            String str3 = "TTPOD:" + str2;
            Log.e(str, str3, th);
            m8377f(str, str3 + "\n" + Log.getStackTraceString(th));
        }
    }

    /* renamed from: b */
    public static void error(String str, String str2, Object... objArr) {
        if (enableLog) {
            String format = String.format("TTPOD:" + str2, objArr);
            Log.e(str, format);
            m8377f(str, format);
        }
    }

    /* renamed from: d */
    public static void info(String str, String str2) {
        if (enableLog) {
            String str3 = "TTPOD:" + str2;
            Log.i(str, str3);
            m8377f(str, str3);
        }
    }

    /* renamed from: c */
    public static void info(String str, String str2, Object... objArr) {
        if (enableLog) {
            String format = String.format("TTPOD:" + str2, objArr);
            Log.i(str, format);
            m8377f(str, format);
        }
    }

    /* renamed from: e */
    public static void verbose(String str, String str2) {
        if (enableLog) {
            String str3 = "TTPOD:" + str2;
            Log.v(str, str3);
            m8377f(str, str3);
        }
    }

    /* renamed from: f */
    private static void m8377f(String str, String str2) {
        FileWriter fileWriter;
        Throwable th;
        if (f2474b) {
            try {
                fileWriter = new FileWriter(f2476d, true);
            } catch (Throwable th1) {
                th = th1;
                fileWriter = null;
            }
            try {
                fileWriter.write(String.format("%s pid=%d %s: %s\n", f2475c.format(new Date()), Integer.valueOf(Process.myPid()), str, str2));
                fileWriter.flush();
                fileWriter.close();
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
