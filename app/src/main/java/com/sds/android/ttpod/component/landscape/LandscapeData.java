package com.sds.android.ttpod.component.landscape;

import android.content.Context;

/* renamed from: com.sds.android.ttpod.component.landscape.b */
/* loaded from: classes.dex */
public class LandscapeData {

    /* renamed from: a */
    private static float f4380a;

    /* renamed from: b */
    private static int f4381b;

    /* renamed from: c */
    private static int f4382c;

    /* renamed from: d */
    private static int f4383d;

    /* renamed from: e */
    private static int f4384e;

    /* renamed from: f */
    private static int f4385f;

    /* renamed from: g */
    private static int f4386g;

    /* renamed from: h */
    private static float f4387h;

    /* renamed from: i */
    private static float f4388i;

    /* renamed from: j */
    private static float f4389j;

    /* renamed from: a */
    public static void m6335a(Context context) {
        f4380a = context.getResources().getDisplayMetrics().density;
        f4381b = context.getResources().getDisplayMetrics().widthPixels;
        f4382c = context.getResources().getDisplayMetrics().heightPixels;
        if ((Math.min(f4382c, f4381b) / f4380a) - 320.0f < 0.1f) {
            f4383d = 24;
            f4384e = 26;
            f4385f = 22;
            f4386g = 3;
            f4387h = 0.12f;
            f4388i = 0.45f;
            return;
        }
        f4383d = 26;
        f4384e = 30;
        f4385f = 24;
        f4386g = 5;
        f4387h = 0.12f;
        f4388i = 0.23f;
    }

    /* renamed from: a */
    public static float m6337a() {
        return f4380a;
    }

    /* renamed from: b */
    public static int m6334b() {
        return f4381b;
    }

    /* renamed from: c */
    public static int m6333c() {
        return f4382c;
    }

    /* renamed from: d */
    public static int m6332d() {
        return f4383d;
    }

    /* renamed from: e */
    public static int m6331e() {
        return f4384e;
    }

    /* renamed from: f */
    public static int m6330f() {
        return f4385f;
    }

    /* renamed from: g */
    public static int m6329g() {
        return f4386g;
    }

    /* renamed from: h */
    public static float m6328h() {
        return f4387h;
    }

    /* renamed from: i */
    public static float m6327i() {
        return f4388i;
    }

    /* renamed from: a */
    public static void m6336a(float f) {
        f4389j = f;
    }

    /* renamed from: j */
    public static float m6326j() {
        return f4389j;
    }
}
