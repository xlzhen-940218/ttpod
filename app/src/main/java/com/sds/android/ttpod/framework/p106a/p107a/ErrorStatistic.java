package com.sds.android.ttpod.framework.p106a.p107a;


import com.sds.android.sdk.core.statistic.SSystemEvent;


/* renamed from: com.sds.android.ttpod.framework.a.a.d */
/* loaded from: classes.dex */
public class ErrorStatistic {
    /* renamed from: a */
    public static void m5239a(String str, String str2) {
        new SSystemEvent("SYS_EXCEPTION", str).append("uri", str2).post();
    }

    /* renamed from: a */
    public static void m5242a(String str) {
        StatisticUtils.m4910a("error", "lyric", str);
    }

    /* renamed from: b */
    public static void m5237b(String str) {
        StatisticUtils.m4910a("error", "picture", str);
    }

    /* renamed from: c */
    public static void m5236c(String str) {
        StatisticUtils.m4910a("error", "online", str);
    }

    /* renamed from: d */
    public static void m5235d(String str) {
        StatisticUtils.m4910a("error", "ad_sdk", str);
    }

    /* renamed from: e */
    public static void m5234e(String str) {
        StatisticUtils.m4910a("error", "user", str);
    }

    /* renamed from: f */
    public static void m5233f(String str) {
        StatisticUtils.m4910a("error", "update", str);
    }

    /* renamed from: g */
    public static void m5232g(String str) {
        StatisticUtils.m4910a("error", "music_circle", str);
        new SSystemEvent("SYS_EXCEPTION", "music_circle").append("uri", str).post();
    }

    /* renamed from: a */
    public static void m5243a(long j) {
        StatisticUtils.m4909a("error", "not_url", "song", j);
    }

    /* renamed from: b */
    public static void m5238b(long j) {
        StatisticUtils.m4909a("error", "not_url", "download", j);
    }

    /* renamed from: a */
    public static void m5245a() {
        StatisticUtils.m4910a("error", "crash", "error");
    }

    /* renamed from: a */
    public static void m5244a(int i) {
        StatisticUtils.m4909a("error", "song", "local", i);
    }

    /* renamed from: a */
    public static void m5241a(String str, int i, String str2) {
        StatisticUtils.m4907a("error", "song", str, i, 0L, str2, null);
    }

    /* renamed from: a */
    public static void m5240a(String str, long j, String str2) {
        StatisticUtils.m4907a("error", "song", str, -12L, j, str2, null);
    }
}
