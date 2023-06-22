package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;

/* renamed from: com.sds.android.ttpod.framework.a.a.j */
/* loaded from: classes.dex */
public class MVStatistic {

    /* renamed from: a */
    private static String f5580a;

    /* compiled from: MVStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.j$a */
    /* loaded from: classes.dex */
    public enum EnumC1771a {
        REQUEST_FAIL,
        DATA_NULL,
        REQUEST_NOT_RESULT,
        ALL_INSTALL,
        REQUEST_SUCCESS,
        INIT
    }

    /* renamed from: a */
    public static void m5077a() {
        StatisticUtils.m4910a("mv", "show", "down-storm-dlg");
    }

    /* renamed from: b */
    public static void m5074b() {
        StatisticUtils.m4910a("mv", "play", f5580a);
    }

    /* renamed from: c */
    public static void m5072c() {
        StatisticUtils.m4910a("mv", "click", f5580a);
    }

    /* renamed from: d */
    public static void m5070d() {
        StatisticUtils.m4910a("mv", "click", "mv_menu");
    }

    /* renamed from: a */
    public static void m5075a(boolean z) {
        StatisticUtils.m4909a("mv", "click", "down-storm-dlg", z ? 1L : 0L);
    }

    /* renamed from: b */
    public static void m5073b(boolean z) {
        StatisticUtils.m4909a("mv", OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY, "is-storm-valid", z ? 1L : 0L);
    }

    /* renamed from: c */
    public static void m5071c(boolean z) {
        StatisticUtils.m4909a("mv", OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY, "storm-installed", z ? 1L : 0L);
    }

    /* renamed from: a */
    public static void m5076a(String str) {
        f5580a = str;
    }
}
