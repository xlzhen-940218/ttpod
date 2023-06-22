package com.sds.android.ttpod.cmmusic.p080d;

import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;

/* renamed from: com.sds.android.ttpod.cmmusic.d.a */
/* loaded from: classes.dex */
public class CmmusicStatistic {
    /* renamed from: a */
    public static void m7314a(int i, int i2) {
        SUserUtils.m4954a("PAGE_CLICK", 1501, i, i2);
    }

    /* renamed from: b */
    public static void m7311b(int i, int i2) {
        SUserUtils.m4954a("PAGE_CLICK", 1503, i, i2);
    }

    /* renamed from: c */
    public static void m7308c(int i, int i2) {
        SUserUtils.m4954a("PAGE_CLICK", 1504, i, i2);
    }

    /* renamed from: d */
    public static void m7306d(int i, int i2) {
        SUserUtils.m4954a("PAGE_CLICK", 1505, i, i2);
    }

    /* renamed from: e */
    public static void m7305e(int i, int i2) {
        SUserUtils.m4954a("PAGE_CLICK", 1506, i, i2);
    }

    /* renamed from: f */
    public static void m7304f(int i, int i2) {
        SUserUtils.m4954a("PAGE_CLICK", 1508, i, i2);
    }

    /* renamed from: a */
    public static void m7313a(String str) {
        new SUserEvent("PAGE_CLICK", 1502, SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue()).append("location=", str).post();
    }

    /* renamed from: b */
    public static void m7310b(String str) {
        new SUserEvent("PAGE_CLICK", 1507, SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue()).append("keyword=", str).post();
    }

    /* renamed from: c */
    public static void m7307c(String str) {
        new SUserEvent("PAGE_CLICK", 1509, SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue()).append("song_name=", str).post();
    }

    /* renamed from: a */
    public static void m7312a(String str, String str2) {
        new SUserEvent("PAGE_CLICK", 1510, SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue()).append("module_name=", str).append("song_name=", str2).post();
    }

    /* renamed from: b */
    public static void m7309b(String str, String str2) {
        new SUserEvent("PAGE_CLICK", 1511, SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue()).append("module_name=", str).append("song_name=", str2).post();
    }
}
