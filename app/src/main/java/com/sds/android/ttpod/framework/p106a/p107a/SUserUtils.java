package com.sds.android.ttpod.framework.p106a.p107a;


import com.sds.android.sdk.core.statistic.SEngine;
import com.sds.android.sdk.core.statistic.SUserEvent;

/* renamed from: com.sds.android.ttpod.framework.a.a.p */
/* loaded from: classes.dex */
public class SUserUtils {
    @Deprecated
    /* renamed from: a */
    public static void m4954a(String str, int i, int i2, int i3) {
        new SUserEvent(str, i, i2, i3).post();
    }

    @Deprecated
    /* renamed from: a */
    public static void m4953a(String str, SAction sAction, SPage sPage, SPage sPage2) {
        m4954a(str, sAction.getValue(), sPage.getValue(), sPage2.getValue());
    }

    /* renamed from: b */
    public static void m4952b(String str, int i, int i2, int i3) {
        SUserEvent sUserEvent = new SUserEvent(str, i, i2, i3);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    /* renamed from: b */
    public static void m4951b(String str, SAction sAction, SPage sPage, SPage sPage2) {
        m4952b(str, sAction.getValue(), sPage.getValue(), sPage2.getValue());
    }

    /* renamed from: a */
    public static void m4956a(SAction sAction, SPage sPage) {
        m4952b("PAGE_CLICK", sAction.getValue(), 0, sPage.getValue());
    }

    /* renamed from: a */
    public static void m4957a(int i, SPage sPage) {
        m4952b("PAGE_CLICK", i, 0, sPage.getValue());
    }

    /* renamed from: a */
    public static void m4958a(int i) {
        SEngine.Page.updatePageProperties("position", Integer.valueOf(i + 1));
    }

    /* renamed from: a */
    public static void m4955a(SAction sAction, boolean z) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", sAction.getValue(), 0, 0);
        sUserEvent.setPageParameter(true);
        sUserEvent.append("status", Integer.valueOf(z ? 1 : 0));
        sUserEvent.post();
    }
}
