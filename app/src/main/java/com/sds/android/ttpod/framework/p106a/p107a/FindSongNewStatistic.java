package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.core.statistic.KVStatisticEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.StringUtils;
import java.util.UUID;

/* renamed from: com.sds.android.ttpod.framework.a.a.e */
/* loaded from: classes.dex */
public class FindSongNewStatistic {

    /* renamed from: a */
    private static String f5566a;

    /* renamed from: b */
    private static long f5567b;

    /* renamed from: c */
    private static String f5568c;

    /* renamed from: a */
    public static void m5227a(String str) {
        StatisticUtils.m4913a(349, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: b */
    public static void m5224b(String str) {
        StatisticUtils.m4913a(350, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: c */
    public static void m5222c(String str) {
        StatisticUtils.m4913a(351, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: d */
    public static void m5220d(String str) {
        StatisticUtils.m4913a(354, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: e */
    public static void m5218e(String str) {
        StatisticUtils.m4913a(355, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: f */
    public static void m5217f(String str) {
        StatisticUtils.m4913a(356, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: g */
    public static void m5216g(String str) {
        StatisticUtils.m4913a(357, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: h */
    public static void m5215h(String str) {
        StatisticUtils.m4913a(358, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: a */
    public static void m5231a() {
        f5566a = UUID.randomUUID().toString();
        m5213j("home");
    }

    /* renamed from: a */
    public static void m5228a(long j, String str) {
        f5567b = j;
        f5568c = str;
    }

    /* renamed from: b */
    public static void m5225b() {
        StatisticUtils.m4907a("find_music", "listen", "home-top", 0L, 0L, String.valueOf(f5567b), f5568c);
    }

    /* renamed from: c */
    public static void m5223c() {
        StatisticUtils.m4907a("find_music", "listen", "first-publish", 0L, 0L, String.valueOf(f5567b), f5568c);
    }

    /* renamed from: d */
    public static void m5221d() {
        StatisticUtils.m4907a("find_music", "listen", "category-" + f5568c, 0L, 0L, String.valueOf(f5567b), f5568c);
    }

    /* renamed from: e */
    public static String m5219e() {
        return "find_music";
    }

    /* renamed from: a */
    public static void m5229a(long j, int i) {
        m5226a("home-top", "tj_reveal", 0L, i, String.valueOf(j), "");
    }

    /* renamed from: a */
    public static void m5230a(int i, long j) {
        StatisticUtils.m4916a(i, (int) StatisticHelper.DELAY_SEND, j, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: j */
    private static void m5213j(String str) {
        StatisticUtils.m4910a("find_music", "tj_show", str + "_" + f5566a);
    }

    /* renamed from: a */
    private static void m5226a(String str, String str2, long j, long j2, String str3, String str4) {
        StatisticUtils.m4907a("find_music", str2, str + "_" + f5566a, j, j2, str3, str4);
    }

    /* renamed from: i */
    public static boolean m5214i(String str) {
        return !StringUtils.m8346a(str) && str.startsWith("song-tj-songlist_");
    }
}
