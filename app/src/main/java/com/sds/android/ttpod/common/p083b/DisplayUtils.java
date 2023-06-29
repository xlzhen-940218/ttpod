package com.sds.android.ttpod.common.p083b;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

/* renamed from: com.sds.android.ttpod.common.b.b */
/* loaded from: classes.dex */
public class DisplayUtils {

    /* renamed from: a */
    private static DisplayMetrics displayMetrics = null;

    /* renamed from: b */
    private static Configuration configuration = null;

    /* renamed from: c */
    private static Typeface iconFontTTF;

    /* renamed from: d */
    private static Typeface uniSansLightTTF;

    /* renamed from: a */
    public static void init(Context context) {
        displayMetrics = context.getResources().getDisplayMetrics();
        configuration = context.getResources().getConfiguration();
        iconFontTTF = Typeface.createFromAsset(context.getAssets(), "fonts/IconFont.ttf");
        uniSansLightTTF = Typeface.createFromAsset(context.getAssets(), "fonts/Uni Sans Light.ttf");
    }

    /* renamed from: a */
    public static void setConfiguration(Context context, Configuration configuration) {
        displayMetrics = context.getResources().getDisplayMetrics();
        DisplayUtils.configuration = configuration;
    }

    /* renamed from: a */
    public static Typeface getIconFontTTF() {
        return iconFontTTF;
    }

    /* renamed from: b */
    public static Typeface getUniSansLightTTF() {
        return uniSansLightTTF;
    }

    /* renamed from: c */
    public static int getWidth() {
        return displayMetrics.widthPixels;
    }

    /* renamed from: d */
    public static int getHeight() {
        return displayMetrics.heightPixels;
    }

    /* renamed from: e */
    public static float getDensity() {
        return displayMetrics.density;
    }

    /* renamed from: f */
    public static int getDensityDpi() {
        return displayMetrics.densityDpi;
    }

    /* renamed from: a */
    public static int dp2px(int dp) {
        return (int) ((dp > 0 ? 0.5f : -0.5f) + (displayMetrics.density * dp));
    }

    /* renamed from: g */
    public static String getDpiName() {
        switch (getDpi()) {
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
    public static int getDpi() {
        int i = displayMetrics.densityDpi;
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
