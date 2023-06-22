package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.core.statistic.StatisticHelper;
import java.util.UUID;

/* renamed from: com.sds.android.ttpod.framework.a.a.m */
/* loaded from: classes.dex */
public class RankStatistic {

    /* renamed from: a */
    private static String f5612a;

    /* renamed from: a */
    public static String m4968a() {
        return f5612a;
    }

    /* renamed from: b */
    public static String m4962b() {
        return "find_music";
    }

    /* renamed from: c */
    public static void m4959c() {
        f5612a = UUID.randomUUID().toString();
        m4965a("home");
    }

    /* renamed from: a */
    public static void m4966a(int i, String str, int i2) {
        m4964a("rank-list-play", i, str);
        StatisticUtils.m4917a(83, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: b */
    public static void m4961b(int i, String str, int i2) {
        m4964a("rank-detail", i, str);
        StatisticUtils.m4917a(84, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: a */
    public static void m4967a(int i, String str) {
        m4964a("rank-detail-play", i, str);
    }

    /* renamed from: a */
    public static void m4963a(String str, boolean z, int i) {
        m4960b("rank_" + str, z, i);
    }

    /* renamed from: b */
    private static void m4960b(String str, boolean z, int i) {
        StatisticUtils.m4908a("find_music", "collect", str + "_" + f5612a, z ? 0L : 1L, i);
    }

    /* renamed from: a */
    private static void m4965a(String str) {
        StatisticUtils.m4910a("find_music", "rank_show", str + "_" + f5612a);
    }

    /* renamed from: a */
    private static void m4964a(String str, int i, String str2) {
        StatisticUtils.m4907a("find_music", "rank_show", str + "_" + f5612a, 0L, 0L, String.valueOf(i), str2);
    }
}
