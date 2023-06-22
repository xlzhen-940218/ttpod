package com.sds.android.ttpod.common.p083b;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

/* renamed from: com.sds.android.ttpod.common.b.b */
/* loaded from: classes.dex */
public class DisplayUtils {

    /* renamed from: a */
    private static DisplayMetrics f3578a = null;

    /* renamed from: b */
    private static Configuration f3579b = null;

    /* renamed from: c */
    private static Typeface f3580c;

    /* renamed from: d */
    private static Typeface f3581d;

    /* renamed from: a */
    public static void m7228a(Context context) {
        f3578a = context.getResources().getDisplayMetrics();
        f3579b = context.getResources().getConfiguration();
        f3580c = Typeface.createFromAsset(context.getAssets(), "fonts/IconFont.ttf");
        f3581d = Typeface.createFromAsset(context.getAssets(), "fonts/Uni Sans Light.ttf");
    }

    /* renamed from: a */
    public static void m7227a(Context context, Configuration configuration) {
        f3578a = context.getResources().getDisplayMetrics();
        f3579b = configuration;
    }

    /* renamed from: a */
    public static Typeface m7230a() {
        return f3580c;
    }

    /* renamed from: b */
    public static Typeface m7226b() {
        return f3581d;
    }

    /* renamed from: c */
    public static int m7225c() {
        return f3578a.widthPixels;
    }

    /* renamed from: d */
    public static int m7224d() {
        return f3578a.heightPixels;
    }

    /* renamed from: e */
    public static float m7223e() {
        return f3578a.density;
    }

    /* renamed from: f */
    public static int m7222f() {
        return f3578a.densityDpi;
    }

    /* renamed from: a */
    public static int m7229a(int i) {
        return (int) ((i > 0 ? 0.5f : -0.5f) + (f3578a.density * i));
    }

    /* renamed from: g */
    public static String m7221g() {
        switch (m7220h()) {
            case 120:
                return "_ldpi";
            case 160:
                return "_mdpi";
            case 240:
                return "_hdpi";
            case 320:
                return "_xhdpi";
            case 480:
                return "_xxhdpi";
            default:
                return "";
        }
    }

    /* renamed from: h */
    public static int m7220h() {
        int i = f3578a.densityDpi;
        if (i <= 120) {
            return 120;
        }
        if (i <= 160) {
            return 160;
        }
        if (i <= 240) {
            return 240;
        }
        return (i > 320 && i > 400) ? 480 : 320;
    }
}
