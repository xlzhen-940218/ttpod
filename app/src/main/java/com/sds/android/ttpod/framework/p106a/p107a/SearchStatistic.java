package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* renamed from: com.sds.android.ttpod.framework.a.a.q */
/* loaded from: classes.dex */
public class SearchStatistic {

    /* renamed from: a */
    private static String f5613a;

    /* renamed from: b */
    private static String f5614b;

    /* renamed from: c */
    private static String f5615c;

    /* renamed from: a */
    public static String m4950a() {
        return f5615c;
    }

    /* renamed from: a */
    public static void m4944a(String str) {
        f5615c = str;
    }

    /* renamed from: b */
    public static String m4942b() {
        return f5613a;
    }

    /* renamed from: b */
    public static void m4939b(String str) {
        f5613a = str;
    }

    /* renamed from: c */
    public static String m4938c() {
        return f5614b;
    }

    /* renamed from: c */
    public static void m4937c(String str) {
        f5614b = str;
    }

    /* renamed from: a */
    public static void m4946a(Integer num, String str) {
        m4945a(num, str, f5614b);
    }

    /* renamed from: a */
    public static void m4945a(Integer num, String str, String str2) {
        long j;
        if (num.intValue() == 1) {
            j = 0;
        } else if (num.intValue() == -1) {
            j = 2;
        } else {
            j = 1;
        }
        StatisticUtils.m4906a("search", "click", str, j, str2, f5615c);
    }

    /* renamed from: d */
    public static void m4935d(String str) {
        m4946a(1, str);
    }

    /* renamed from: a */
    public static void m4947a(Integer num) {
        m4935d(f5613a + "-album");
    }

    /* renamed from: b */
    public static void m4940b(Integer num) {
        m4946a(num, f5613a + "-album-detail");
    }

    /* renamed from: d */
    public static void m4936d() {
        m4935d(f5613a + "-album-play");
    }

    /* renamed from: e */
    public static void m4934e() {
        m4935d(f5613a + "-album-content");
    }

    /* renamed from: f */
    public static void m4933f() {
        m4935d(f5613a + "-album-download");
    }

    /* renamed from: g */
    public static void m4932g() {
        m4935d(f5613a + "-album-download-button");
    }

    /* renamed from: h */
    public static void m4931h() {
        m4935d(f5613a + "-album-select");
    }

    /* renamed from: i */
    public static void m4930i() {
        m4935d(f5613a + "-album-download-cancel");
    }

    /* renamed from: j */
    public static void m4929j() {
        m4935d(f5613a + "-album-download-define");
    }

    /* renamed from: a */
    public static void m4949a(AudioQuality audioQuality) {
        long j;
        switch (audioQuality) {
            case COMPRESSED:
                j = 0;
                break;
            case STANDARD:
                j = 2;
                break;
            case HIGH:
                j = 3;
                break;
            case SUPER:
                j = 4;
                break;
            case LOSSLESS:
                j = 5;
                break;
            default:
                j = 0;
                break;
        }
        StatisticUtils.m4908a("search", "click", f5613a + "-album-download-qualty", j, 0L);
    }

    /* renamed from: a */
    public static void m4948a(MediaItem mediaItem) {
        StatisticUtils.m4908a("search", "click", f5613a + "-third-show", mediaItem.hasOutList() ? 1L : 0L, 0L);
    }

    /* renamed from: b */
    public static void m4941b(MediaItem mediaItem) {
        StatisticUtils.m4908a("search", "click", f5613a + "-third-click", mediaItem.hasOutList() ? 1L : 0L, 0L);
    }

    /* renamed from: k */
    public static void m4928k() {
        StatisticUtils.m4910a("search", "refresh", "search-hotword");
    }

    /* renamed from: l */
    public static void m4927l() {
        StatisticUtils.m4910a("search", "recognizer", "recognizer-result");
    }

    /* renamed from: m */
    public static void m4926m() {
        StatisticUtils.m4910a("search", "recognizer", "recognizer-again");
    }

    /* renamed from: n */
    public static void m4925n() {
        StatisticUtils.m4910a("search", "recognizer", "recognizer-button");
    }

    /* renamed from: a */
    public static void m4943a(boolean z) {
        StatisticUtils.m4907a("search", "collect", OnlineMediaStatistic.m5043b(), z ? 0 : 1, OnlineMediaStatistic.m5029f(), f5614b, f5615c);
    }
}
