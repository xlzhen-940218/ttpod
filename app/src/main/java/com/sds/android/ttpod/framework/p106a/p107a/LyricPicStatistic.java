package com.sds.android.ttpod.framework.p106a.p107a;

/* renamed from: com.sds.android.ttpod.framework.a.a.i */
/* loaded from: classes.dex */
public class LyricPicStatistic {
    /* renamed from: a */
    public static void m5078a(String str, Long l, String str2, String str3, Integer num) {
        StatisticUtils.m4907a("lyric_pic", "search", str, num.intValue(), l == null ? 0L : l.longValue(), str2, str3);
    }

    /* renamed from: a */
    public static void m5079a(String str, int i, String str2, String str3, Integer num) {
        StatisticUtils.m4907a("lyric_pic", "search", str, num.intValue(), i, str2, str3);
    }
}
