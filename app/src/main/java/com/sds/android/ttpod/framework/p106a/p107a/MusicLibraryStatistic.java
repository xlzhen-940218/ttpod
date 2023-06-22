package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.StringUtils;
import java.util.UUID;

/* renamed from: com.sds.android.ttpod.framework.a.a.k */
/* loaded from: classes.dex */
public class MusicLibraryStatistic {

    /* renamed from: a */
    private static String f5581a;

    /* renamed from: b */
    private static String f5582b;

    /* renamed from: a */
    public static void m5067a(String str) {
        f5582b = str;
    }

    /* renamed from: a */
    public static String m5069a() {
        return f5581a;
    }

    /* renamed from: b */
    public static void m5064b() {
        f5581a = UUID.randomUUID().toString();
        m5062b("home");
    }

    /* renamed from: a */
    public static void m5065a(boolean z) {
        if (!StringUtils.m8346a(f5582b)) {
            StatisticUtils.m4908a("find_music", "collect", f5582b, z ? 0L : 1L, OnlineMediaStatistic.m5029f());
        }
    }

    /* renamed from: c */
    public static void m5061c() {
        if (!StringUtils.m8346a(f5582b)) {
            StatisticUtils.m4908a("find_music", "listen", f5582b, 0L, OnlineMediaStatistic.m5029f());
        }
    }

    /* renamed from: d */
    public static void m5059d() {
        StatisticUtils.m4917a(86, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: a */
    public static void m5068a(int i, String str) {
        m5066a("like-play", i, str);
    }

    /* renamed from: b */
    public static void m5063b(int i, String str) {
        m5066a("category", i, str);
    }

    /* renamed from: c */
    public static void m5060c(int i, String str) {
        m5066a("singer", i, str);
    }

    /* renamed from: d */
    public static void m5058d(int i, String str) {
        m5066a("radio", i, str);
    }

    /* renamed from: e */
    public static void m5056e(int i, String str) {
        m5066a("mv", i, str);
    }

    /* renamed from: f */
    public static void m5055f(int i, String str) {
        m5066a("category-son", i, str);
    }

    /* renamed from: b */
    private static void m5062b(String str) {
        StatisticUtils.m4910a("find_music", "library_show", str + "_" + f5581a);
    }

    /* renamed from: a */
    private static void m5066a(String str, int i, String str2) {
        StatisticUtils.m4907a("find_music", "library_show", str + "_" + f5581a, 0L, 0L, String.valueOf(i), str2);
    }

    /* renamed from: e */
    public static String m5057e() {
        return "find_music";
    }
}
