package com.sds.android.ttpod.framework;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

import java.io.File;

/* renamed from: com.sds.android.ttpod.framework.a */
/* loaded from: classes.dex */
public class TTPodConfig {

    /* renamed from: a */
    private static boolean f5560a = false;

    /* renamed from: b */
    private static String f5561b;

    /* renamed from: c */
    private static String f5562c;

    /* renamed from: a */
    public static void m5307a(boolean z) {
        f5562c = EnvironmentUtils.C0605d.getSdcardPath() + File.separator + "ttpod";
        f5561b = f5562c + File.separator + "cache";
        if (z) {
            FileUtils.m8406f(m5300h());
            FileUtils.m8406f(m5299i());
            FileUtils.m8406f(m5297k());
            FileUtils.m8407e(m5297k() + File.separator + ".nomedia");
            FileUtils.m8406f(m5296l());
            FileUtils.m8406f(m5294n());
            FileUtils.m8406f(m5301g());
            FileUtils.m8407e(m5301g() + File.separator + ".nomedia");
            FileUtils.m8406f(m5298j());
            FileUtils.m8406f(m5290r());
            FileUtils.m8406f(m5289s());
            FileUtils.m8406f(m5288t());
            FileUtils.m8406f(m5302f());
            FileUtils.m8406f(m5303e());
            FileUtils.m8406f(m5295m());
            FileUtils.m8407e(m5295m() + File.separator + ".nomedia");
            FileUtils.m8406f(m5309a());
        }
    }

    /* renamed from: a */
    public static String m5309a() {
        return EnvironmentUtils.C0605d.getSdcardPath() + File.separator + "TTPOD_LOG";
    }

    /* renamed from: b */
    public static boolean m5306b() {
        return f5560a;
    }

    /* renamed from: c */
    public static void m5305c() {
        f5560a = true;
    }

    /* renamed from: d */
    public static String m5304d() {
        return f5561b;
    }

    /* renamed from: e */
    public static String m5303e() {
        return f5562c + File.separator + "effect";
    }

    /* renamed from: f */
    public static String m5302f() {
        return f5561b + File.separator + ".effect";
    }

    /* renamed from: g */
    public static String m5301g() {
        return f5561b + File.separator + "media";
    }

    /* renamed from: h */
    public static String m5300h() {
        return f5561b + File.separator + "image";
    }

    /* renamed from: i */
    public static String m5299i() {
        return f5561b + File.separator + "object";
    }

    /* renamed from: j */
    public static String m5298j() {
        return f5561b + File.separator + ".tmp";
    }

    /* renamed from: k */
    public static String m5297k() {
        return f5562c + File.separator + "splash";
    }

    /* renamed from: l */
    public static String m5296l() {
        return f5562c + File.separator + "MyMusics";
    }

    /* renamed from: m */
    public static String m5295m() {
        return f5562c + File.separator + "bkgs";
    }

    /* renamed from: n */
    public static String m5294n() {
        return f5562c + File.separator + "skin";
    }

    /* renamed from: o */
    public static String m5293o() {
        return f5562c + File.separator + "bkgs";
    }

    /* renamed from: p */
    public static String m5292p() {
        return "bkgs";
    }

    /* renamed from: q */
    public static String m5291q() {
        return f5562c + File.separator + "song";
    }

    /* renamed from: r */
    public static String m5290r() {
        return f5562c + File.separator + "lyric";
    }

    /* renamed from: s */
    public static String m5289s() {
        return f5562c + File.separator + "artist";
    }

    /* renamed from: t */
    public static String m5288t() {
        return f5561b + File.separator + "embed";
    }

    /* renamed from: u */
    public static String m5287u() {
        return f5562c + File.separator + "art";
    }

    /* renamed from: v */
    public static String m5286v() {
        return f5562c + File.separator + "Equalizer";
    }

    /* renamed from: w */
    public static String m5285w() {
        return f5562c + File.separator + OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY;
    }

    /* renamed from: x */
    public static String m5284x() {
        return f5562c + File.separator + "landscape";
    }

    /* renamed from: y */
    public static String m5283y() {
        return f5562c + File.separator + "mv";
    }

    /* renamed from: z */
    public static String m5282z() {
        return m5283y() + File.separator + ".cache";
    }

    /* renamed from: A */
    public static String m5312A() {
        return f5562c + File.separator + "image";
    }

    /* renamed from: B */
    public static String m5311B() {
        return f5562c;
    }

    /* renamed from: a */
    public static String m5308a(Long l) {
        return Preferences.m2983aX() + File.separator + l;
    }

    /* renamed from: C */
    public static String m5310C() {
        return Preferences.m2983aX() + File.separator + "audio.tmp";
    }
}
