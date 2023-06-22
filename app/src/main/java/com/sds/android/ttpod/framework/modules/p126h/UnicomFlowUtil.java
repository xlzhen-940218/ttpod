package com.sds.android.ttpod.framework.modules.p126h;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Base64;

import androidx.core.app.ActivityCompat;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.TTPodApplication;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/* renamed from: com.sds.android.ttpod.framework.modules.h.e */
/* loaded from: classes.dex */
public class UnicomFlowUtil {

    /* renamed from: a */
    private static final String f6301a = UnicomFlowModule.class.getSimpleName();

    /* renamed from: a */
    public static boolean m3957a() {
        boolean m3231H = Cache.m3218a().m3231H();
        boolean m3230I = Cache.m3218a().m3230I();
        boolean m3946f = m3946f();
        LogUtils.m8388a(f6301a, "unicom flow isUnicomFlowEnable enable:" + m3231H + "  usable:" + m3230I + "  is unicom sdcard:" + m3946f);
        return m3231H && m3230I && m3946f;
    }

    /* renamed from: b */
    public static boolean m3951b() {
        return Cache.m3218a().m3138z() != UnicomFlowStatus.OPEN.ordinal() && m3957a();
    }

    /* renamed from: c */
    public static boolean m3949c() {
        int m3138z = Cache.m3218a().m3138z();
        int m3139y = Cache.m3218a().m3139y();
        LogUtils.m8388a(f6301a, "is need use proxy open status:" + m3138z + "  trial status:" + m3139y);
        return m3138z == UnicomFlowStatus.OPEN.ordinal() || m3138z == UnicomFlowStatus.UNSUBSCRIBE.ordinal() || m3139y == FlowTrialStatus.TRIAL.ordinal();
    }

    /* renamed from: a */
    public static String m3953a(String str, String str2) {
        try {
            if (!SDKVersionUtils.m8373a()) {
                return "MzAwMDAwNDU1MDpCREFBQUQ5QjczOUQzQjNG";
            }
            return Base64.encodeToString((str + ":" + str2).getBytes(), 0).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "MzAwMDAwNDU1MDpCREFBQUQ5QjczOUQzQjNG";
        }
    }

    /* renamed from: a */
    public static double m3955a(long j) {
        return m3956a(j / 1048576.0d);
    }

    /* renamed from: a */
    public static double m3956a(double d) {
        return new BigDecimal(d).setScale(2, 4).doubleValue();
    }

    /* renamed from: d */
    public static boolean m3948d() {
        return m3944h() && m3949c();
    }

    /* renamed from: e */
    public static boolean m3947e() {
        return EnvironmentUtils.C0604c.m8476d() == 1;
    }

    /* renamed from: f */
    public static boolean m3946f() {
        LogUtils.m8388a(f6301a, "imsi" + EnvironmentUtils.C0604c.m8481b());
        return EnvironmentUtils.C0604c.m8481b().startsWith("46001") || EnvironmentUtils.C0604c.m8481b().startsWith("46006");
    }

    /* renamed from: g */
    public static boolean m3945g() {
        LogUtils.m8388a(f6301a, "is use gprs network type:" + EnvironmentUtils.C0604c.m8476d());
        return EnvironmentUtils.C0604c.m8476d() == 0 || 3 == EnvironmentUtils.C0604c.m8476d() || 1 == EnvironmentUtils.C0604c.m8476d();
    }

    /* renamed from: h */
    public static boolean m3944h() {
        return m3957a() && m3945g();
    }

    /* renamed from: i */
    public static int m3943i() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(5) - calendar.get(5);
    }

    /* renamed from: j */
    public static boolean m3942j() {
        return Calendar.getInstance().get(5) >= 20;
    }

    /* renamed from: k */
    public static boolean m3941k() {
        return Calendar.getInstance().get(5) < 15;
    }

    /* renamed from: l */
    public static String m3940l() {
        String m3238A = Cache.m3218a().m3238A();
        if (StringUtils.m8346a(m3238A)) {
            return m3939m();
        }
        return m3238A;
    }

    /* renamed from: m */
    public static String m3939m() {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.getApplication().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            str = "";
        } else {
            if (ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.READ_PHONE_NUMBERS)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(TTPodApplication.getApplication(), Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {

                return "";
            }
            str = telephonyManager.getLine1Number();
            if (!StringUtils.m8346a(str) && str.length() > 11) {
                str = str.substring(str.length() - 11);
            }
        }
        return StringUtils.m8346a(str) ? "" : str;
    }

    /* renamed from: a */
    public static void m3952a(boolean z, String str) {
        BaseApplication m4635c = BaseApplication.getApplication();
        final NotificationManager notificationManager = (NotificationManager) m4635c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1000);
        Notification notification = new Notification();
        PendingIntent activity = PendingIntent.getActivity(m4635c, 0, new Intent(), PendingIntent.FLAG_IMMUTABLE);
        if (z) {
            notification.icon = R.drawable.img_notification_unicom_open;
        } else {
            notification.icon = R.drawable.img_notification_unicom_close;
        }
        notification.tickerText = str;
        notification.when = System.currentTimeMillis();
        //notification.setLatestEventInfo(m4635c, "联通流量畅听", str, activity);
        notification.flags |= 16;
        notificationManager.notify(1000, notification);
        new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.h.e.1
            @Override // java.lang.Runnable
            public void run() {
                notificationManager.cancel(1000);
            }
        }, 5000L);
    }

    /* renamed from: n */
    public static void m3938n() {
        ((NotificationManager) BaseApplication.getApplication().getSystemService(Context.NOTIFICATION_SERVICE)).cancel(1000);
    }

    /* renamed from: a */
    public static void m3954a(Context context) {
        if (m3948d()) {
            m3952a(HttpRequest.m8704b(), context.getString(HttpRequest.m8704b() ? R.string.unicom_flow_open : R.string.unicom_flow_close));
        }
    }

    /* renamed from: b */
    public static void m3950b(Context context) {
        if (HttpRequest.m8704b()) {
            m3952a(HttpRequest.m8704b(), context.getString(R.string.unicom_flow_close));
        }
    }

    /* renamed from: o */
    public static Date m3937o() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2100);
        return calendar.getTime();
    }

    /* renamed from: p */
    public static void m3936p() {
        Cache.m3218a().m3202a(m3934r());
    }

    /* renamed from: q */
    public static void m3935q() {
        Cache.m3218a().m3179c(m3934r());
    }

    /* renamed from: r */
    private static Date m3934r() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }
}
