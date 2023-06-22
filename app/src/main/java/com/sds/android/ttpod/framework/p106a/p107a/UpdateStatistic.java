package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;

/* renamed from: com.sds.android.ttpod.framework.a.a.x */
/* loaded from: classes.dex */
public class UpdateStatistic {

    /* renamed from: a */
    private static boolean f5621a = false;

    /* renamed from: a */
    public static void m4800a() {
        if (!f5621a) {
            StatisticUtils.m4910a("update", "update", "auto_update");
            f5621a = true;
        }
    }

    /* renamed from: a */
    public static void m4799a(String str) {
        StatisticUtils.m4910a("update", str, "dialog_show");
    }

    /* renamed from: b */
    public static void m4798b() {
        StatisticUtils.m4910a("update", "update", "update_click");
    }

    /* renamed from: c */
    public static void m4797c() {
        StatisticUtils.m4910a("update", "update", "cancel_click");
    }

    /* renamed from: d */
    public static void m4796d() {
        StatisticUtils.m4910a("update", "360", "install_success");
    }

    /* renamed from: e */
    public static void m4795e() {
        StatisticUtils.m4910a("update", VersionUpdateConst.TYPE_BAIDU_UPDATE, "install_success");
    }

    /* renamed from: f */
    public static void m4794f() {
        StatisticUtils.m4910a("update", VersionUpdateConst.TYPE_WANDOUJIA_UPDATE, "install_success");
    }

    /* renamed from: g */
    public static void m4793g() {
        StatisticUtils.m4910a("update", VersionUpdateConst.TYPE_HIAPK_UPDATE, "install_success");
    }
}
