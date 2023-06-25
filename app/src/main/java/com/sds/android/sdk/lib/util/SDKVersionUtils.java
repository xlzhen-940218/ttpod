package com.sds.android.sdk.lib.util;

import android.os.Build;

/* renamed from: com.sds.android.sdk.lib.util.i */
/* loaded from: classes.dex */
public class SDKVersionUtils {
    /* renamed from: a */
    public static boolean m8373a() {
        return Build.VERSION.SDK_INT >= 8;
    }

    /* renamed from: b */
    public static boolean m8372b() {
        return Build.VERSION.SDK_INT >= 9;
    }

    /* renamed from: c */
    public static boolean checkVersionThanAndroid11() {
        return Build.VERSION.SDK_INT >= 11;
    }

    /* renamed from: d */
    public static boolean m8370d() {
        return Build.VERSION.SDK_INT >= 12;
    }

    /* renamed from: e */
    public static boolean m8369e() {
        return Build.VERSION.SDK_INT >= 13;
    }

    /* renamed from: f */
    public static boolean m8368f() {
        return Build.VERSION.SDK_INT >= 14;
    }

    /* renamed from: g */
    public static boolean m8367g() {
        return Build.VERSION.SDK_INT >= 16;
    }

    /* renamed from: h */
    public static boolean m8366h() {
        return Build.VERSION.SDK_INT >= 17;
    }

    /* renamed from: i */
    public static boolean m8365i() {
        return Build.VERSION.SDK_INT >= 19;
    }
}
