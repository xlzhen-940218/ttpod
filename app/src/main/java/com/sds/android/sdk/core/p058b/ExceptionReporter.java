package com.sds.android.sdk.core.p058b;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/* renamed from: com.sds.android.sdk.core.b.b */
/* loaded from: classes.dex */
public class ExceptionReporter {

    /* compiled from: ExceptionReporter.java */
    /* renamed from: com.sds.android.sdk.core.b.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0575a {
        /* renamed from: a */
        void mo6637a(boolean z);
    }

    /* renamed from: a */
    public static void m8750a(final Context context, final String str) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.sds.android.sdk.core.b.b.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                String obj = stringWriter.toString();
                LogUtils.m8381c("ExceptionReporter", "TTPod_Crash_Exception:\n" + obj);
                if (context != null && EnvironmentUtils.C0604c.m8474e()) {
                    Intent intent = new Intent(str);
                    intent.putExtra("android.intent.extra.SUBJECT", th.toString());
                    intent.putExtra("android.intent.extra.TEXT", obj);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                System.exit(0);
            }
        });
    }

    /* renamed from: a */
    public static void m8748a(String str, String str2, final InterfaceC0575a interfaceC0575a) {
        try {
            ExceptionReportAPI.m8753a(m8749a(str, str2)).m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.sdk.core.b.b.2
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                public void onRequestSuccess(BaseResult baseResult) {
                    interfaceC0575a.mo6637a(baseResult.isSuccess());
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                public void onRequestFailure(BaseResult baseResult) {
                    interfaceC0575a.mo6637a(baseResult.isSuccess());
                }
            });
        } catch (Exception e) {
            interfaceC0575a.mo6637a(false);
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static HashMap<String, Object> m8749a(String str, String str2) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("time", m8747b());
        hashMap.put("package", EnvironmentUtils.m8526a());
        hashMap.put("v", EnvironmentUtils.C0602a.m8506e());
        hashMap.put("f", "f" + EnvironmentUtils.C0602a.m8512b());
        hashMap.put("mid", Build.MANUFACTURER + "#" + Build.MODEL);
        hashMap.put("splus", Build.VERSION.RELEASE);
        hashMap.put("s", EnvironmentUtils.C0603b.m8488e().get("s"));
        hashMap.put("rom", Build.PRODUCT);
        hashMap.put("build", "#" + EnvironmentUtils.C0602a.m8508d());
        hashMap.put("memory", m8751a());
        hashMap.put("message", str2);
        hashMap.put("name", str);
        return hashMap;
    }

    /* renamed from: a */
    private static String m8751a() {
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        return "Runtime:{total:" + decimalFormat.format(Runtime.getRuntime().totalMemory() / 1048576.0d) + ",free:" + decimalFormat.format(Runtime.getRuntime().freeMemory() / 1048576.0d) + ",max:" + decimalFormat.format(Runtime.getRuntime().maxMemory() / 1048576.0d) + "},HeapAllocated:" + decimalFormat.format(Debug.getNativeHeapAllocatedSize() / 1048576.0d);
    }

    /* renamed from: b */
    private static String m8747b() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
