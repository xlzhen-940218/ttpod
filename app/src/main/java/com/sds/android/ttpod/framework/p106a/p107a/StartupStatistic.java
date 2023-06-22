package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.framework.a.a.r */
/* loaded from: classes.dex */
public class StartupStatistic {

    /* renamed from: a */
    private static boolean f5617a;

    /* renamed from: b */
    private static long f5618b;

    /* renamed from: a */
    public static void m4924a() {
        if (f5617a) {
            f5617a = false;
            long currentTimeMillis = System.currentTimeMillis();
            f5617a = false;
            SessionStatisticEvent m4903b = StatisticUtils.m4903b("start_time", "time", "ttpod", 0L);
            m4903b.put("start_time", currentTimeMillis - f5618b);
            m4903b.put("time", f5618b);
            m4903b.complete();
            LogUtils.m8386a("StartupStatistic", "start_time = %s", Long.valueOf(currentTimeMillis - f5618b));
            LogUtils.m8386a("StartupStatistic", "time = %s", Long.valueOf(f5618b));
            StatisticUtils.m4912a(m4903b);
            new SSystemEvent("SYS_STARTUP", "end").post();
        }
    }

    /* renamed from: b */
    public static void m4922b() {
        if (f5617a) {
            f5617a = false;
            LogUtils.m8388a("StartupStatistic", "startup hide");
            StatisticUtils.m4910a("startup", "hide", "ttpod");
            new SSystemEvent("SYS_STARTUP", "ttpod.hide").post();
        }
    }

    /* renamed from: c */
    public static void m4920c() {
        f5617a = true;
        f5618b = System.currentTimeMillis();
    }

    /* renamed from: a */
    public static void m4923a(String str) {
        f5617a = true;
        new SSystemEvent("SYS_STARTUP", "start").append("origin", str).post();
    }

    /* renamed from: d */
    public static void m4919d() {
        StatisticUtils.m4910a("splash", "show", "splash");
        new SSystemEvent("SYS_STARTUP", "splash.show").post();
    }

    /* renamed from: b */
    public static void m4921b(String str) {
        long longValue = Preferences.m2927bb().longValue();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - longValue > 2000) {
            LogUtils.m8381c("StartupStatistic", "startUp origin " + str);
            Preferences.m2934b(Long.valueOf(currentTimeMillis));
            StatisticUtils.m4909a("startup", "startup", "startup_" + str, 1L);
        }
    }
}
