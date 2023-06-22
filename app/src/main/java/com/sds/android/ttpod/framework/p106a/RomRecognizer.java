package com.sds.android.ttpod.framework.p106a;

import com.sds.android.sdk.lib.util.EnvironmentUtils;

/* renamed from: com.sds.android.ttpod.framework.a.p */
/* loaded from: classes.dex */
public class RomRecognizer {
    /* renamed from: a */
    public static boolean m4655a() {
        return m4654a("xiaomi");
    }

    /* renamed from: a */
    private static boolean m4654a(String str) {
        return ((String) EnvironmentUtils.C0603b.m8488e().get("rom")).toLowerCase().contains(str);
    }
}
