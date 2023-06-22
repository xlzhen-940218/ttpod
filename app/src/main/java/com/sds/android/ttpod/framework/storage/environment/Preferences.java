package com.sds.android.ttpod.framework.storage.environment;

import android.content.Context;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.core.p113b.AutoDownloadNetworkType;
import com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeSensitivityType;
import com.sds.android.ttpod.framework.modules.skin.AllLocalSkinListLoader;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.sds.android.ttpod.framework.storage.environment.b */
/* loaded from: classes.dex */
public class Preferences {

    /* renamed from: a */
    private static final String f6989a = EnvironmentContentProvider.f6981a;

    /* renamed from: b */
    private static Context f6990b = null;

    /* compiled from: Preferences.java */
    /* renamed from: com.sds.android.ttpod.framework.storage.environment.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2031a {
        /* renamed from: a */
        void mo2553a(PreferencesID preferencesID);
    }

    /* renamed from: a */
    public static void m3024a(Context context) {
        f6990b = context;
        AccessHelper.m3103a(context);
    }

    /* renamed from: a */
    public static boolean m3028a() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SPLASH_AUDIO_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: b */
    public static String m2944b() {
        return AccessHelper.m3092a(f6989a, PreferencesID.KTV_URL_DOMAIN.name(), (String) null);
    }

    /* renamed from: a */
    public static void m3012a(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.KTV_URL_DOMAIN.name(), str);
    }

    /* renamed from: c */
    public static String m2904c() {
        return AccessHelper.m3092a(f6989a, PreferencesID.KTV_CHECK_CODE.name(), (String) null);
    }

    /* renamed from: b */
    public static void m2933b(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.KTV_CHECK_CODE.name(), str);
    }

    /* renamed from: d */
    public static String m2898d() {
        return AccessHelper.m3092a(f6989a, PreferencesID.KTV_ROOM_INFO.name(), (String) null);
    }

    /* renamed from: a */
    public static void m3025a(long j) {
        AccessHelper.m3093a(f6989a, PreferencesID.KTV_USER_ID.name(), Long.valueOf(j));
    }

    /* renamed from: e */
    public static long m2892e() {
        return AccessHelper.m3097a(f6989a, PreferencesID.KTV_USER_ID.name(), 0L);
    }

    /* renamed from: c */
    public static void m2900c(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.KTV_ROOM_INFO.name(), str);
    }

    /* renamed from: a */
    public static void m3007a(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SPLASH_AUDIO_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: b */
    public static void m2929b(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.LYRIC_PIC_MATCH_BANNER.name(), Boolean.valueOf(z));
    }

    /* renamed from: f */
    public static boolean m2886f() {
        return AccessHelper.m3096a(f6989a, PreferencesID.LYRIC_PIC_MATCH_BANNER.name(), (Boolean) true);
    }

    /* renamed from: g */
    public static Set<String> m2882g() {
        Set<String> m3091a = AccessHelper.m3091a(f6989a, PreferencesID.MEDIA_SCAN_EXCLUDE_FOLDER_SET.name(), (Set<String>) null);
        String m8467b = EnvironmentUtils.C0605d.m8467b();
        if (m3091a == null) {
            m3091a = new HashSet<>();
            m3091a.add("/system");
            m3091a.add(m8467b + "/media/audio/Ringtones");
            m3091a.add(m8467b + "/media/audio/Notifications");
            m3091a.add(m8467b + "/Ringtones");
            m3091a.add(m8467b + "/Notifications");
            m3091a.add(m8467b + "/NaviOne");
            m3091a.add(m8467b + "/Android");
            m3091a.add(m8467b + "/gameloft");
            m3091a.add(m8467b + "/gameloft");
            m3091a.add(m8467b + "/mj fairy land");
            m3091a.add(m8467b + "/recordings");
            m3091a.add(m8467b + "/My Documents/My Recordings");
            m3091a.add(m8467b + "/glu");
            m3091a.add(m8467b + "/sf_iv_data");
            m3091a.add(m8467b + "/miliao/audio");
            m3091a.add(m8467b + "/yy");
            m3091a.add(m8467b + "/weichang");
            m3091a.add(m8467b + "/MIUI/sound_recorder");
            m3091a.add(m8467b + "/Tencent/MicroMsg");
            m3091a.add(m8467b + "/Tencent/MobileQQ");
        }
        if (!m3091a.contains(m8467b + "/µUTONAVI")) {
            m3091a.add(m8467b + "/µUTONAVI");
            m3091a.add(m8467b + "/µAIDU");
            if (!m8467b.equals("/storage/extSdCard")) {
                m3091a.add("/storage/extSdCard/media/audio/Ringtones");
                m3091a.add("/storage/extSdCard/media/audio/Notifications");
                m3091a.add("/storage/extSdCard/Ringtones");
                m3091a.add("/storage/extSdCard/Notifications");
                m3091a.add("/storage/extSdCard/NaviOne");
                m3091a.add("/storage/extSdCard/Android");
                m3091a.add("/storage/extSdCard/gameloft");
                m3091a.add("/storage/extSdCard/gameloft");
                m3091a.add("/storage/extSdCard/mj fairy land");
                m3091a.add("/storage/extSdCard/recordings");
                m3091a.add("/storage/extSdCard/My Documents/My Recordings");
                m3091a.add("/storage/extSdCard/glu");
                m3091a.add("/storage/extSdCard/sf_iv_data");
                m3091a.add("/storage/extSdCard/miliao/audio");
                m3091a.add("/storage/extSdCard/yy");
                m3091a.add("/storage/extSdCard/weichang");
                m3091a.add("/storage/extSdCard/MIUI/sound_recorder");
                m3091a.add("/storage/extSdCard/Tencent/MicroMsg");
                m3091a.add("/storage/extSdCard/Tencent/MobileQQ");
                m3091a.add("/storage/extSdCard/µUTONAVI");
                m3091a.add("/storage/extSdCard/µAIDU");
            }
        }
        return m3091a;
    }

    /* renamed from: a */
    public static void m3008a(Set<String> set) {
        AccessHelper.m3085b(f6989a, PreferencesID.MEDIA_SCAN_EXCLUDE_FOLDER_SET.name(), set);
    }

    /* renamed from: h */
    public static boolean m2878h() {
        return AccessHelper.m3096a(f6989a, PreferencesID.MEDIA_SCAN_EXCLUDE_DURATION_LESS_THAN_60.name(), (Boolean) true);
    }

    /* renamed from: c */
    public static void m2899c(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.MEDIA_SCAN_EXCLUDE_DURATION_LESS_THAN_60.name(), Boolean.valueOf(z));
    }

    /* renamed from: i */
    public static boolean m2874i() {
        return AccessHelper.m3096a(f6989a, PreferencesID.MEDIA_SCAN_EXCLUDE_AMR_MID.name(), (Boolean) true);
    }

    /* renamed from: d */
    public static void m2893d(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.MEDIA_SCAN_EXCLUDE_AMR_MID.name(), Boolean.valueOf(z));
    }

    /* renamed from: j */
    public static boolean m2870j() {
        return AccessHelper.m3096a(f6989a, PreferencesID.MEDIA_SCAN_EXCLUDE_HIDDEN_FOLDER.name(), (Boolean) true);
    }

    /* renamed from: e */
    public static void m2887e(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.MEDIA_SCAN_EXCLUDE_HIDDEN_FOLDER.name(), Boolean.valueOf(z));
    }

    /* renamed from: k */
    public static Set<String> m2866k() {
        return AccessHelper.m3091a(f6989a, PreferencesID.MEDIA_SCAN_AUTO_FOLDER_SET.name(), (Set<String>) null);
    }

    /* renamed from: b */
    public static void m2930b(Set<String> set) {
        AccessHelper.m3085b(f6989a, PreferencesID.MEDIA_SCAN_AUTO_FOLDER_SET.name(), set);
    }

    /* renamed from: l */
    public static PlayMode m2862l() {
        return PlayMode.values()[AccessHelper.m3098a(f6989a, PreferencesID.PLAY_MODE.name(), PlayMode.REPEAT.ordinal())];
    }

    /* renamed from: a */
    public static void m3018a(PlayMode playMode) {
        AccessHelper.m3094a(f6989a, PreferencesID.PLAY_MODE.name(), Integer.valueOf(playMode.ordinal()));
    }

    /* renamed from: m */
    public static String m2858m() {
        return AccessHelper.m3092a(f6989a, PreferencesID.PLAYING_GROUPID.name(), MediaStorage.GROUP_ID_ALL_LOCAL);
    }

    /* renamed from: d */
    public static void m2894d(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.PLAYING_GROUPID.name(), str);
    }

    /* renamed from: n */
    public static String m2854n() {
        return AccessHelper.m3092a(f6989a, PreferencesID.PLAYING_MEDIAID.name(), (String) null);
    }

    /* renamed from: e */
    public static void m2888e(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.PLAYING_MEDIAID.name(), str);
    }

    /* renamed from: o */
    public static String m2850o() {
        return AccessHelper.m3092a(f6989a, PreferencesID.LAST_PLAY_POSITION_INFO.name(), "");
    }

    /* renamed from: f */
    public static void m2884f(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.LAST_PLAY_POSITION_INFO.name(), str);
    }

    /* renamed from: a */
    public static void m3026a(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.SLEEP_DELAY_TIME.name(), Integer.valueOf(i));
    }

    /* renamed from: p */
    public static int m2846p() {
        return AccessHelper.m3098a(f6989a, PreferencesID.SLEEP_DELAY_TIME.name(), 30);
    }

    /* renamed from: f */
    public static void m2883f(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_AUTO_ORIENTATE_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: q */
    public static boolean m2842q() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_AUTO_ORIENTATE_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: g */
    public static void m2879g(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: r */
    public static boolean m2838r() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: h */
    public static void m2875h(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_LOCK_SCREEN_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: i */
    public static boolean m2871i(boolean z) {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_LOCK_SCREEN_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: j */
    public static void m2867j(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_HEADSET_UNPLUG_AUTO_STOP_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: s */
    public static boolean m2834s() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_HEADSET_UNPLUG_AUTO_STOP_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: k */
    public static void m2863k(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_HEADSET_PLUG_AUTO_PLAY_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: t */
    public static boolean m2830t() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_HEADSET_PLUG_AUTO_PLAY_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: l */
    public static void m2859l(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_HEADSET_CONTROL_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: u */
    public static boolean m2826u() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_HEADSET_CONTROL_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: m */
    public static void m2855m(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_HEADSET_EXCHANGE_LONG_CLICK_DOUBLE_CLICK_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: v */
    public static boolean m2822v() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_HEADSET_EXCHANGE_LONG_CLICK_DOUBLE_CLICK_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: n */
    public static void m2851n(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHAKE_SWITCH_SONG_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: w */
    public static boolean m2818w() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHAKE_SWITCH_SONG_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: a */
    public static void m3021a(ShakeSensitivityType shakeSensitivityType) {
        AccessHelper.m3094a(f6989a, PreferencesID.SHAKE_PLAY_SENSITIVITY.name(), Integer.valueOf(shakeSensitivityType.ordinal()));
    }

    /* renamed from: x */
    public static ShakeSensitivityType m2815x() {
        return ShakeSensitivityType.values()[AccessHelper.m3098a(f6989a, PreferencesID.SHAKE_PLAY_SENSITIVITY.name(), ShakeSensitivityType.EASY_SHAKE.ordinal())];
    }

    /* renamed from: o */
    public static void m2847o(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_BACKLIGHT_LIST_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: y */
    public static boolean m2813y() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_BACKLIGHT_LIST_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: p */
    public static void m2843p(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_BACKLIGHT_PLAYER_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: z */
    public static boolean m2811z() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_BACKLIGHT_PLAYER_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: q */
    public static void m2839q(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_BACKLIGHT_LOCKSCREEN_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: A */
    public static boolean m3080A() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_BACKLIGHT_LOCKSCREEN_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: r */
    public static void m2835r(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_BACKLIGHT_LANDSCAPE_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: B */
    public static boolean m3078B() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_BACKLIGHT_LANDSCAPE_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: s */
    public static void m2831s(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_PLAY_WHILE_STARTING_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: C */
    public static boolean m3076C() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_PLAY_WHILE_STARTING_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: t */
    public static void m2827t(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_AIRPLANE_WHILE_SLEEPING_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: D */
    public static boolean m3074D() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_AIRPLANE_WHILE_SLEEPING_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: u */
    public static void m2823u(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_INNER_PICTURE_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: E */
    public static boolean m3072E() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_INNER_PICTURE_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: v */
    public static void m2819v(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_HIDE_NOTICE_BAR_WHILE_LOCKSCREEN_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: F */
    public static boolean m3070F() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_HIDE_NOTICE_BAR_WHILE_LOCKSCREEN_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: w */
    public static void m2816w(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_RECEIVE_PUSH_MESSAGE_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: G */
    public static boolean m3068G() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_RECEIVE_PUSH_MESSAGE_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: x */
    public static void m2814x(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_ONLY_USE_WIFI.name(), Boolean.valueOf(z));
    }

    /* renamed from: H */
    public static boolean m3066H() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_ONLY_USE_WIFI.name(), (Boolean) false);
    }

    /* renamed from: a */
    public static void m3017a(AudioQuality audioQuality) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUDITION_QUALITY_2G.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    /* renamed from: I */
    public static AudioQuality m3064I() {
        int m3098a = AccessHelper.m3098a(f6989a, PreferencesID.AUDITION_QUALITY_2G.name(), AudioQuality.COMPRESSED.ordinal());
        if (m3098a == AudioQuality.LOSSLESS.ordinal()) {
            m3098a--;
        }
        return AudioQuality.values()[m3098a];
    }

    /* renamed from: b */
    public static void m2937b(AudioQuality audioQuality) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUDITION_QUALITY_3G.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    /* renamed from: J */
    public static AudioQuality m3062J() {
        int m3098a = AccessHelper.m3098a(f6989a, PreferencesID.AUDITION_QUALITY_3G.name(), AudioQuality.STANDARD.ordinal());
        if (m3098a == AudioQuality.LOSSLESS.ordinal()) {
            m3098a--;
        }
        return AudioQuality.values()[m3098a];
    }

    /* renamed from: c */
    public static void m2901c(AudioQuality audioQuality) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUDITION_QUALITY_WIFI.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    /* renamed from: K */
    public static AudioQuality m3060K() {
        int m3098a = AccessHelper.m3098a(f6989a, PreferencesID.AUDITION_QUALITY_WIFI.name(), AudioQuality.STANDARD.ordinal());
        return (m3098a == AudioQuality.COMPRESSED.ordinal() || m3098a == AudioQuality.STANDARD.ordinal() || m3098a == AudioQuality.SUPER.ordinal()) ? AudioQuality.values()[m3098a] : AudioQuality.STANDARD;
    }

    /* renamed from: d */
    public static void m2895d(AudioQuality audioQuality) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUTO_DOWNLOAD_AUDIO_QUALITY.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    /* renamed from: L */
    public static AudioQuality m3058L() {
        int m3098a = AccessHelper.m3098a(f6989a, PreferencesID.AUTO_DOWNLOAD_AUDIO_QUALITY.name(), AudioQuality.UNDEFINED.ordinal());
        return (m3098a == AudioQuality.UNDEFINED.ordinal() || m3098a == AudioQuality.COMPRESSED.ordinal() || m3098a == AudioQuality.STANDARD.ordinal() || m3098a == AudioQuality.SUPER.ordinal() || m3098a == AudioQuality.LOSSLESS.ordinal()) ? AudioQuality.values()[m3098a] : AudioQuality.STANDARD;
    }

    /* renamed from: e */
    public static void m2889e(AudioQuality audioQuality) {
        AccessHelper.m3094a(f6989a, PreferencesID.ALBUM_DOWNLOAD_AUDIO_QUALITY.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    /* renamed from: M */
    public static AudioQuality m3056M() {
        int m3098a = AccessHelper.m3098a(f6989a, PreferencesID.ALBUM_DOWNLOAD_AUDIO_QUALITY.name(), AudioQuality.LOSSLESS.ordinal());
        return (m3098a == AudioQuality.COMPRESSED.ordinal() || m3098a == AudioQuality.STANDARD.ordinal() || m3098a == AudioQuality.SUPER.ordinal() || m3098a == AudioQuality.LOSSLESS.ordinal()) ? AudioQuality.values()[m3098a] : AudioQuality.LOSSLESS;
    }

    /* renamed from: a */
    public static void m3020a(AutoDownloadNetworkType autoDownloadNetworkType) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUTO_DOWNLOAD_AUDIO_NETWORK.name(), Integer.valueOf(autoDownloadNetworkType.ordinal()));
    }

    /* renamed from: g */
    public static void m2880g(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.AUDIO_DOWNLOAD_FOLDER_PATH.name(), str);
    }

    /* renamed from: N */
    public static String m3054N() {
        return AccessHelper.m3092a(f6989a, PreferencesID.AUDIO_DOWNLOAD_FOLDER_PATH.name(), TTPodConfig.m5291q());
    }

    /* renamed from: b */
    public static void m2939b(AutoDownloadNetworkType autoDownloadNetworkType) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUTO_DOWNLOAD_LYRIC_NETWORK.name(), Integer.valueOf(autoDownloadNetworkType.ordinal()));
    }

    /* renamed from: O */
    public static AutoDownloadNetworkType m3052O() {
        return AutoDownloadNetworkType.values()[AccessHelper.m3098a(f6989a, PreferencesID.AUTO_DOWNLOAD_LYRIC_NETWORK.name(), AutoDownloadNetworkType.ALL.ordinal())];
    }

    /* renamed from: b */
    public static void m2942b(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.LYRIC_HIGHLIGHT_COLOR.name(), Integer.valueOf(i));
    }

    /* renamed from: P */
    public static int m3050P() {
        return AccessHelper.m3098a(f6989a, PreferencesID.LYRIC_HIGHLIGHT_COLOR.name(), -1);
    }

    /* renamed from: c */
    public static void m2903c(int i) {
        AccessHelper.m3086b(f6989a, PreferencesID.LYRIC_FONT_SIZE.name(), i + "");
    }

    /* renamed from: Q */
    public static int m3048Q() {
        String m3092a = AccessHelper.m3092a(f6989a, PreferencesID.LYRIC_FONT_SIZE.name(), "");
        if (m3092a == null || m3092a.length() <= 0) {
            return 0;
        }
        try {
            return Integer.parseInt(m3092a);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: R */
    public static boolean m3046R() {
        return AccessHelper.m3096a(f6989a, PreferencesID.LYRIC_KALA_OK_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: y */
    public static void m2812y(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.LYRIC_KALA_OK_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: S */
    public static int m3044S() {
        return AccessHelper.m3098a(f6989a, PreferencesID.LYRIC_ALIGNMENT.name(), -1);
    }

    /* renamed from: T */
    public static boolean m3042T() {
        return AccessHelper.m3096a(f6989a, PreferencesID.LYRIC_FADE.name(), (Boolean) true);
    }

    /* renamed from: U */
    public static int m3040U() {
        return AccessHelper.m3098a(f6989a, PreferencesID.PLAYER_LAST_DISPLAY_PANEL.name(), 1);
    }

    /* renamed from: d */
    public static void m2897d(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.PLAYER_LAST_DISPLAY_PANEL.name(), Integer.valueOf(i));
    }

    /* renamed from: h */
    public static void m2876h(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.SKIN_PATH.name(), str);
    }

    /* renamed from: V */
    public static String m3038V() {
        return AccessHelper.m3092a(f6989a, PreferencesID.SKIN_PATH.name(), (String) null);
    }

    /* renamed from: W */
    public static String m3036W() {
        return "assets://" + AllLocalSkinListLoader.m3872a().m3571b();
    }

    /* renamed from: i */
    public static void m2872i(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.BACKGROUND_PATH.name(), str);
    }

    /* renamed from: X */
    public static String m3034X() {
        return AccessHelper.m3092a(f6989a, PreferencesID.BACKGROUND_PATH.name(), "follow_skin");
    }

    /* renamed from: Y */
    public static boolean m3032Y() {
        return m3034X().startsWith("follow_skin");
    }

    /* renamed from: Z */
    public static int m3030Z() {
        return AccessHelper.m3098a(f6989a, PreferencesID.MINI_LYRIC_FONT_SIZE.name(), 20);
    }

    /* renamed from: e */
    public static void m2891e(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.MINI_LYRIC_FONT_SIZE.name(), Integer.valueOf(i));
    }

    /* renamed from: aa */
    public static boolean m2980aa() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_MINI_LYRIC_LOCKED_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: z */
    public static void m2810z(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_MINI_LYRIC_LOCKED_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: ab */
    public static int m2978ab() {
        return AccessHelper.m3098a(f6989a, PreferencesID.MINI_LYRIC_COLOR_STYLE.name(), 20);
    }

    /* renamed from: f */
    public static void m2885f(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.MINI_LYRIC_COLOR_STYLE.name(), Integer.valueOf(i));
    }

    /* renamed from: g */
    public static void m2881g(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.MINI_LYRIC_Y_POSITION.name(), Integer.valueOf(i));
    }

    /* renamed from: ac */
    public static int m2976ac() {
        return AccessHelper.m3098a(f6989a, PreferencesID.MINI_LYRIC_Y_POSITION.name(), DisplayUtils.m7224d() >> 2);
    }

    /* renamed from: ad */
    public static boolean m2974ad() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_CLOUD_AUDIO_EFFECT_ENABLED.name(), (Boolean) false);
    }

    /* renamed from: A */
    public static void m3079A(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_CLOUD_AUDIO_EFFECT_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: B */
    public static void m3077B(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.FORCE_REQUEST_AUDIO_EFFECT.name(), Boolean.valueOf(z));
    }

    /* renamed from: ae */
    public static boolean m2972ae() {
        return AccessHelper.m3096a(f6989a, PreferencesID.FORCE_REQUEST_AUDIO_EFFECT.name(), (Boolean) false);
    }

    /* renamed from: h */
    public static void m2877h(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.CURRENT_REVERB_NUM.name(), Integer.valueOf(i));
    }

    /* renamed from: af */
    public static int m2970af() {
        return AccessHelper.m3098a(f6989a, PreferencesID.CURRENT_REVERB_NUM.name(), 0);
    }

    /* renamed from: ag */
    public static String m2968ag() {
        return AccessHelper.m3092a(f6989a, PreferencesID.EQUALIZER_SETTING.name(), (String) null);
    }

    /* renamed from: j */
    public static void m2868j(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.EQUALIZER_SETTING.name(), str);
    }

    /* renamed from: ah */
    public static String m2966ah() {
        return AccessHelper.m3092a(f6989a, PreferencesID.CUSTOM_EQUALIZER_SETTING.name(), (String) null);
    }

    /* renamed from: k */
    public static void m2864k(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.CUSTOM_EQUALIZER_SETTING.name(), str);
    }

    /* renamed from: ai */
    public static int m2964ai() {
        return AccessHelper.m3098a(f6989a, PreferencesID.REVERB_PRESET.name(), 0);
    }

    /* renamed from: i */
    public static void m2873i(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.REVERB_PRESET.name(), Integer.valueOf(i));
    }

    /* renamed from: aj */
    public static int m2962aj() {
        return AccessHelper.m3098a(f6989a, PreferencesID.BASSBOOST_STRENGTH.name(), 0);
    }

    /* renamed from: j */
    public static void m2869j(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.BASSBOOST_STRENGTH.name(), Integer.valueOf(i));
    }

    /* renamed from: C */
    public static void m3075C(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.EFFECT_IS_EDIT.name(), Boolean.valueOf(z));
    }

    /* renamed from: ak */
    public static boolean m2960ak() {
        return AccessHelper.m3096a(f6989a, PreferencesID.EFFECT_IS_EDIT.name(), (Boolean) false);
    }

    /* renamed from: al */
    public static boolean m2959al() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_BOOSTLIMIT_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: D */
    public static void m3073D(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_BOOSTLIMIT_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: am */
    public static int m2958am() {
        return AccessHelper.m3098a(f6989a, PreferencesID.TREBLEBOOST_STRENGTH.name(), 0);
    }

    /* renamed from: k */
    public static void m2865k(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.TREBLEBOOST_STRENGTH.name(), Integer.valueOf(i));
    }

    /* renamed from: an */
    public static int m2957an() {
        return AccessHelper.m3098a(f6989a, PreferencesID.VIRTUALIZER_STRENGTH.name(), 0);
    }

    /* renamed from: l */
    public static void m2861l(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.VIRTUALIZER_STRENGTH.name(), Integer.valueOf(i));
    }

    /* renamed from: ao */
    public static float m2956ao() {
        return AccessHelper.m3099a(f6989a, PreferencesID.CHANNEL_BALANCE.name(), 0.0f);
    }

    /* renamed from: a */
    public static void m3027a(float f) {
        AccessHelper.m3095a(f6989a, PreferencesID.CHANNEL_BALANCE.name(), Float.valueOf(f));
    }

    /* renamed from: a */
    public static void m3010a(String str, String str2) {
        if (str.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX)) {
            str = MediaStorage.GROUP_ID_ARTIST_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
            str = MediaStorage.GROUP_ID_FOLDER_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_GENRE_PREFIX)) {
            str = MediaStorage.GROUP_ID_GENRE_PREFIX;
        }
        AccessHelper.m3086b(f6989a, PreferencesID.SORT_ORDER_PREFIX.name() + str, str2);
    }

    /* renamed from: l */
    public static String m2860l(String str) {
        String str2 = "title_key";
        if (MediaStorage.GROUP_ID_RECENTLY_ADD.equals(str)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC;
        } else if (MediaStorage.GROUP_ID_RECENTLY_PLAY.equals(str)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_PLAY_TIME_DESC;
        } else if (MediaStorage.GROUP_ID_FAV_LOCAL.equals(str) || str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) || MediaStorage.GROUP_ID_EFFECT_LOCAL.equals(str) || str.startsWith(MediaStorage.GROUP_ID_EFFECT_ONLINE) || MediaStorage.GROUP_ID_FAV.equals(str)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC;
        } else if (str.equals(MediaStorage.GROUP_ID_DOWNLOAD)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC;
        } else if (str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) && !MediaStorage.GROUP_ID_ALL_LOCAL.equals(str)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_CUSTOM;
        } else if (str.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX)) {
            str = MediaStorage.GROUP_ID_ARTIST_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
            str = MediaStorage.GROUP_ID_FOLDER_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_GENRE_PREFIX)) {
            str = MediaStorage.GROUP_ID_GENRE_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX)) {
            str2 = "track";
        }
        return AccessHelper.m3092a(f6989a, PreferencesID.SORT_ORDER_PREFIX.name() + str, str2);
    }

    /* renamed from: ap */
    public static boolean m2955ap() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_FIRST_USE_AUDIO_EFFECT.name(), (Boolean) true);
    }

    /* renamed from: E */
    public static void m3071E(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_FIRST_USE_AUDIO_EFFECT.name(), Boolean.valueOf(z));
    }

    /* renamed from: aq */
    public static TTPodUser m2954aq() {
        String m3092a = AccessHelper.m3092a(f6989a, PreferencesID.USER_INFO.name(), (String) null);
        if (StringUtils.m8346a(m3092a)) {
            return null;
        }
        return (TTPodUser) JSONUtils.fromJson(m3092a, TTPodUser.class);
    }

    /* renamed from: a */
    public static void m3022a(User user) {
        AccessHelper.m3086b(f6989a, PreferencesID.USER_INFO.name(), user == null ? null : JSONUtils.toJson(user));
    }

    /* renamed from: a */
    public static void m3013a(Long l) {
        AccessHelper.m3093a(f6989a, PreferencesID.LATEST_UPDATE_VERSION_TIME.name(), l);
    }

    /* renamed from: ar */
    public static Long m2953ar() {
        return Long.valueOf(AccessHelper.m3097a(f6989a, PreferencesID.LATEST_UPDATE_VERSION_TIME.name(), 0L));
    }

    /* renamed from: as */
    public static String m2952as() {
        return AccessHelper.m3092a(f6989a, PreferencesID.IGNORE_UPDATE_VERSION.name(), "1.0.0");
    }

    /* renamed from: at */
    public static boolean m2951at() {
        return AccessHelper.m3096a(f6989a, PreferencesID.HEADSET_PLUG_TIP_DONE.name(), (Boolean) false);
    }

    /* renamed from: F */
    public static void m3069F(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.HEADSET_PLUG_TIP_DONE.name(), Boolean.valueOf(z));
    }

    /* renamed from: au */
    public static int m2950au() {
        return AccessHelper.m3098a(f6989a, PreferencesID.LANDSCAPE_EFFECT_INDEX.name(), 0);
    }

    /* renamed from: m */
    public static void m2857m(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.LANDSCAPE_EFFECT_INDEX.name(), Integer.valueOf(i));
    }

    /* renamed from: av */
    public static boolean m2949av() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_NEW_LANDSCAPE.name(), (Boolean) false);
    }

    /* renamed from: G */
    public static void m3067G(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_NEW_LANDSCAPE.name(), Boolean.valueOf(z));
    }

    /* renamed from: aw */
    public static boolean m2948aw() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_FOLLOWED_TTPOD_SINA_WEIBO.name(), (Boolean) false);
    }

    /* renamed from: H */
    public static void m3065H(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_FOLLOWED_TTPOD_SINA_WEIBO.name(), Boolean.valueOf(z));
    }

    /* renamed from: ax */
    public static boolean m2947ax() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_NEED_RESUME_PLAY_STATUS_FROM_SOUND_SEACH.name(), (Boolean) false);
    }

    /* renamed from: I */
    public static void m3063I(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_NEED_RESUME_PLAY_STATUS_FROM_SOUND_SEACH.name(), Boolean.valueOf(z));
    }

    /* renamed from: ay */
    public static String m2946ay() {
        return AccessHelper.m3092a(f6989a, PreferencesID.PUSH_CLIENT_ID_LAST_RECORDED_TIME.name(), (String) null);
    }

    /* renamed from: m */
    public static void m2856m(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.PUSH_CLIENT_ID_LAST_RECORDED_TIME.name(), str);
    }

    /* renamed from: az */
    public static String m2945az() {
        return AccessHelper.m3092a(f6989a, PreferencesID.PUSH_CLIENT_ID.name(), (String) null);
    }

    /* renamed from: n */
    public static void m2852n(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.PUSH_CLIENT_ID.name(), str);
    }

    /* renamed from: J */
    public static void m3061J(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_DESKTOP_LYRIC_SETTING_TIP_SHOW.name(), Boolean.valueOf(z));
    }

    /* renamed from: aA */
    public static boolean m3006aA() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_DESKTOP_LYRIC_SETTING_TIP_SHOW.name(), (Boolean) true);
    }

    /* renamed from: K */
    public static void m3059K(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_MUSICCIRCLE_FIND_FRIEND_GUIDE.name(), Boolean.valueOf(z));
    }

    /* renamed from: aB */
    public static int m3005aB() {
        return AccessHelper.m3098a(f6989a, PreferencesID.IMAGE_DOWN_AMOUNT_WIFI.name(), 5);
    }

    /* renamed from: aC */
    public static int m3004aC() {
        return AccessHelper.m3098a(f6989a, PreferencesID.IMAGE_DOWN_AMOUNT_2G.name(), 2);
    }

    /* renamed from: n */
    public static void m2853n(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.IMAGE_DOWN_AMOUNT_WIFI.name(), Integer.valueOf(i));
    }

    /* renamed from: o */
    public static void m2849o(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.IMAGE_DOWN_AMOUNT_2G.name(), Integer.valueOf(i));
    }

    /* renamed from: aD */
    public static boolean m3003aD() {
        return AccessHelper.m3096a(f6989a, PreferencesID.ARTIST_PIC_PLAY.name(), (Boolean) true);
    }

    /* renamed from: L */
    public static void m3057L(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.ARTIST_PIC_PLAY.name(), Boolean.valueOf(z));
    }

    /* renamed from: aE */
    public static String m3002aE() {
        return AccessHelper.m3092a(f6989a, PreferencesID.LATEST_UPDATE_VERSION.name(), FeedbackItem.STATUS_WAITING);
    }

    /* renamed from: o */
    public static void m2848o(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.LATEST_UPDATE_VERSION.name(), str);
    }

    /* renamed from: p */
    public static void m2844p(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.APP_VERSION.name(), str);
    }

    /* renamed from: aF */
    public static String m3001aF() {
        return AccessHelper.m3092a(f6989a, PreferencesID.APP_VERSION.name(), "");
    }

    /* renamed from: aG */
    public static boolean m3000aG() {
        return !EnvironmentUtils.C0602a.m8506e().equals(m3001aF());
    }

    /* renamed from: q */
    public static void m2840q(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.INSTALL_INFO.name(), str);
    }

    /* renamed from: aH */
    public static String m2999aH() {
        return AccessHelper.m3092a(f6989a, PreferencesID.INSTALL_INFO.name(), "");
    }

    /* renamed from: M */
    public static void m3055M(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_IP_SUPPORT.name(), Boolean.valueOf(z));
    }

    /* renamed from: aI */
    public static boolean m2998aI() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_IP_SUPPORT.name(), (Boolean) true);
    }

    /* renamed from: N */
    public static void m3053N(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SEARCH_RESTRICTED.name(), Boolean.valueOf(z));
    }

    /* renamed from: aJ */
    public static boolean m2997aJ() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SEARCH_RESTRICTED.name(), (Boolean) true);
    }

    /* renamed from: O */
    public static void m3051O(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_360_GUIDE.name(), Boolean.valueOf(z));
    }

    /* renamed from: P */
    public static void m3049P(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_360_UNION.name(), Boolean.valueOf(z));
    }

    /* renamed from: Q */
    public static void m3047Q(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_VIEW_PAGER_GUIDE_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: aK */
    public static boolean m2996aK() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_VIEW_PAGER_GUIDE_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: R */
    public static void m3045R(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_SLIDING_GUIDE_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: aL */
    public static boolean m2995aL() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_SLIDING_GUIDE_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: aM */
    public static boolean m2994aM() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_DOWNLOAD_HIGHLIGHT.name(), (Boolean) false);
    }

    /* renamed from: S */
    public static void m3043S(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_DOWNLOAD_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    /* renamed from: T */
    public static void m3041T(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_MV_DOWNLOAD_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    /* renamed from: U */
    public static void m3039U(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_APP_DOWNLOAD_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    /* renamed from: aN */
    public static boolean m2993aN() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_APP_DOWNLOAD_HIGHLIGHT.name(), (Boolean) false);
    }

    /* renamed from: aO */
    public static boolean m2992aO() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_EFFECT_HIGHLIGHT.name(), (Boolean) true);
    }

    /* renamed from: V */
    public static void m3037V(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_EFFECT_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    /* renamed from: aP */
    public static boolean m2991aP() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_FAVORITE_HIGHLIGHT.name(), (Boolean) false);
    }

    /* renamed from: W */
    public static void m3035W(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_FAVORITE_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    /* renamed from: aQ */
    public static boolean m2990aQ() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_BACKGROUND_STATE.name(), (Boolean) false);
    }

    /* renamed from: X */
    public static void m3033X(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_BACKGROUND_STATE.name(), Boolean.valueOf(z));
    }

    /* renamed from: a */
    public static void m3019a(PreferencesID preferencesID, InterfaceC2031a interfaceC2031a) {
        m3023a(f6990b, preferencesID, interfaceC2031a);
    }

    /* renamed from: a */
    public static void m3023a(Context context, PreferencesID preferencesID, InterfaceC2031a interfaceC2031a) {
        DebugUtils.m8426a(preferencesID, "preferencesID");
        DebugUtils.m8425a(Boolean.valueOf(preferencesID.isNotifyChanged()), preferencesID.name() + ".mSendChangedNotify", Boolean.TRUE, "Boolean.TRUE");
        AccessHelper.m3102a(context, preferencesID, interfaceC2031a);
    }

    /* renamed from: b */
    public static void m2938b(PreferencesID preferencesID, InterfaceC2031a interfaceC2031a) {
        m2940b(f6990b, preferencesID, interfaceC2031a);
    }

    /* renamed from: b */
    public static void m2940b(Context context, PreferencesID preferencesID, InterfaceC2031a interfaceC2031a) {
        DebugUtils.m8426a(preferencesID, "preferencesID");
        DebugUtils.m8425a(Boolean.valueOf(preferencesID.isNotifyChanged()), preferencesID.name() + ".mSendChangedNotify", Boolean.TRUE, "Boolean.TRUE");
        AccessHelper.m3088b(context, preferencesID, interfaceC2031a);
    }

    /* renamed from: a */
    public static void m3011a(String str, MediaItem mediaItem) {
        if (mediaItem != null && !mediaItem.isNull()) {
            AccessHelper.m3086b(f6989a, PreferencesID.CURRENT_ARTIST_BITMAP_PATH.name(), mediaItem.getID() + str);
        }
    }

    /* renamed from: a */
    public static String m3014a(MediaItem mediaItem) {
        if (mediaItem != null && !mediaItem.isNull()) {
            String m3092a = AccessHelper.m3092a(f6989a, PreferencesID.CURRENT_ARTIST_BITMAP_PATH.name(), "");
            if (m3092a.startsWith(mediaItem.getID())) {
                return m3092a.substring(mediaItem.getID().length());
            }
        }
        return "";
    }

    /* renamed from: b */
    public static void m2932b(String str, MediaItem mediaItem) {
        if (mediaItem != null && !mediaItem.isNull()) {
            AccessHelper.m3086b(f6989a, PreferencesID.CURRENT_LYRIC_PATH.name(), mediaItem.getID() + str);
        }
    }

    /* renamed from: b */
    public static String m2935b(MediaItem mediaItem) {
        if (mediaItem != null && !mediaItem.isNull()) {
            String m3092a = AccessHelper.m3092a(f6989a, PreferencesID.CURRENT_LYRIC_PATH.name(), "");
            if (m3092a.startsWith(mediaItem.getID())) {
                return m3092a.substring(mediaItem.getID().length());
            }
        }
        return "";
    }

    /* renamed from: aR */
    public static int m2989aR() {
        return AccessHelper.m3098a(f6989a, PreferencesID.NOTIFICATION_PRIORITY.name(), 0);
    }

    /* renamed from: p */
    public static void m2845p(int i) {
        if (i < -2 || i > 2) {
            throw new IllegalArgumentException("priority should be <= NotificationUtils.NOTIFICATION_PRIORITY_MAXor >= NotificationUtils.NOTIFICATION_PRIORITY_MIN");
        }
        AccessHelper.m3094a(f6989a, PreferencesID.NOTIFICATION_PRIORITY.name(), Integer.valueOf(i));
    }

    /* renamed from: aS */
    public static boolean m2988aS() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_PREVIOUS_IN_NOTIFICATION_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: Y */
    public static void m3031Y(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_PREVIOUS_IN_NOTIFICATION_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: aT */
    public static boolean m2987aT() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_CLOSE_IN_NOTIFICATION_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: Z */
    public static void m3029Z(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_CLOSE_IN_NOTIFICATION_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: aU */
    public static boolean m2986aU() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_NOTIFICATION_WHILE_PAUSED_ENABLED.name(), (Boolean) true);
    }

    /* renamed from: aa */
    public static void m2979aa(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_NOTIFICATION_WHILE_PAUSED_ENABLED.name(), Boolean.valueOf(z));
    }

    /* renamed from: q */
    public static void m2841q(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUDIO_FADE_LENGTH.name(), Integer.valueOf(i));
    }

    /* renamed from: aV */
    public static int m2985aV() {
        return AccessHelper.m3098a(f6989a, PreferencesID.AUDIO_FADE_LENGTH.name(), 500);
    }

    /* renamed from: r */
    public static void m2837r(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUDIO_FADE_PALY_PAUSE_LENGTH.name(), Integer.valueOf(i));
    }

    /* renamed from: aW */
    public static int m2984aW() {
        return AccessHelper.m3098a(f6989a, PreferencesID.AUDIO_FADE_PALY_PAUSE_LENGTH.name(), 500);
    }

    /* renamed from: r */
    public static void m2836r(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.CACHED_MEDIA_FOLDER_PATH.name(), str);
    }

    /* renamed from: aX */
    public static String m2983aX() {
        return AccessHelper.m3092a(f6989a, PreferencesID.CACHED_MEDIA_FOLDER_PATH.name(), TTPodConfig.m5301g());
    }

    /* renamed from: s */
    public static void m2833s(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUDIO_FADE_SEEK_LENGTH.name(), Integer.valueOf(i));
    }

    /* renamed from: aY */
    public static int m2982aY() {
        return AccessHelper.m3098a(f6989a, PreferencesID.AUDIO_FADE_SEEK_LENGTH.name(), 500);
    }

    /* renamed from: aZ */
    public static int m2981aZ() {
        return AccessHelper.m3098a(f6989a, PreferencesID.AUDIO_SESSION_ID.name(), 0);
    }

    /* renamed from: t */
    public static void m2829t(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.AUDIO_SESSION_ID.name(), Integer.valueOf(i));
    }

    /* renamed from: s */
    public static void m2832s(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.ONLINE_ORIGIN.name(), str);
    }

    /* renamed from: ba */
    public static String m2928ba() {
        return AccessHelper.m3092a(f6989a, PreferencesID.ONLINE_ORIGIN.name(), "");
    }

    /* renamed from: bb */
    public static Long m2927bb() {
        return Long.valueOf(AccessHelper.m3097a(f6989a, PreferencesID.VALID_STARTUP_TIME.name(), 0L));
    }

    /* renamed from: b */
    public static void m2934b(Long l) {
        AccessHelper.m3093a(f6989a, PreferencesID.VALID_STARTUP_TIME.name(), l);
    }

    /* renamed from: t */
    public static void m2828t(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.ONLINE_MEDIA_LIST_GROUP_NAME.name(), str);
    }

    /* renamed from: bc */
    public static String m2926bc() {
        return AccessHelper.m3092a(f6989a, PreferencesID.ONLINE_MEDIA_LIST_GROUP_NAME.name(), "");
    }

    /* renamed from: bd */
    public static int m2925bd() {
        return AccessHelper.m3098a(f6989a, PreferencesID.APP_RUNNING_STATE.name(), 0);
    }

    /* renamed from: u */
    public static void m2825u(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.APP_RUNNING_STATE.name(), Integer.valueOf(i));
    }

    /* renamed from: b */
    public static void m2941b(long j) {
        AccessHelper.m3093a(f6989a, PreferencesID.APP_STARTUP_TIME.name(), Long.valueOf(j));
    }

    /* renamed from: be */
    public static long m2924be() {
        return AccessHelper.m3097a(f6989a, PreferencesID.APP_STARTUP_TIME.name(), 0L);
    }

    /* renamed from: c */
    public static void m2902c(long j) {
        AccessHelper.m3093a(f6989a, PreferencesID.APP_EXIT_TIME.name(), Long.valueOf(j));
    }

    /* renamed from: bf */
    public static long m2923bf() {
        return AccessHelper.m3097a(f6989a, PreferencesID.APP_EXIT_TIME.name(), 0L);
    }

    /* renamed from: bg */
    public static String m2922bg() {
        return AccessHelper.m3092a(f6989a, PreferencesID.HOMEPAGE_ELEMENT_SETTING.name(), "");
    }

    /* renamed from: u */
    public static void m2824u(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.HOMEPAGE_ELEMENT_SETTING.name(), str);
    }

    /* renamed from: bh */
    public static boolean m2921bh() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_SHOW_UNICOM_FLOW_HIGHLIGHT.name(), (Boolean) true);
    }

    /* renamed from: ab */
    public static void m2977ab(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_SHOW_UNICOM_FLOW_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    /* renamed from: a */
    public static void m3009a(Date date) {
        AccessHelper.m3093a(f6989a, PreferencesID.UNICOM_FLOW_CHECK_LAST_TIME.name(), Long.valueOf(date.getTime()));
    }

    /* renamed from: b */
    public static void m2931b(Date date) {
        AccessHelper.m3093a(f6989a, PreferencesID.UNICOM_FLOW_CONFIG_LAST_TIME.name(), Long.valueOf(date.getTime()));
    }

    /* renamed from: bi */
    public static Date m2920bi() {
        return new Date(AccessHelper.m3097a(f6989a, PreferencesID.UNICOM_FLOW_CONFIG_LAST_TIME.name(), 0L));
    }

    /* renamed from: ac */
    public static void m2975ac(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.UNICOM_FLOW_PROXY_MV_DIALOG.name(), Boolean.valueOf(z));
    }

    /* renamed from: bj */
    public static boolean m2919bj() {
        return AccessHelper.m3096a(f6989a, PreferencesID.UNICOM_FLOW_PROXY_MV_DIALOG.name(), (Boolean) true);
    }

    /* renamed from: bk */
    public static boolean m2918bk() {
        return AccessHelper.m3096a(f6989a, PreferencesID.UNICOM_FLOW_SONG_MV_DIALOG.name(), (Boolean) true);
    }

    /* renamed from: ad */
    public static void m2973ad(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.UNICOM_FLOW_SONG_MV_DIALOG.name(), Boolean.valueOf(z));
    }

    /* renamed from: bl */
    public static float m2917bl() {
        return AccessHelper.m3099a(f6989a, PreferencesID.UNICOM_UNOPEN_FLOW_SIZE.name(), 30.0f);
    }

    /* renamed from: b */
    public static void m2943b(float f) {
        AccessHelper.m3095a(f6989a, PreferencesID.UNICOM_UNOPEN_FLOW_SIZE.name(), Float.valueOf(f));
    }

    /* renamed from: ae */
    public static void m2971ae(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.UNICOM_FLOW_2G_3G_MV_DIALOG.name(), Boolean.valueOf(z));
    }

    /* renamed from: bm */
    public static boolean m2916bm() {
        return AccessHelper.m3096a(f6989a, PreferencesID.UNICOM_FLOW_2G_3G_MV_DIALOG.name(), (Boolean) true);
    }

    /* renamed from: af */
    public static void m2969af(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.IS_POPUP_MONTHEND_DIALOG.name(), Boolean.valueOf(z));
    }

    /* renamed from: bn */
    public static boolean m2915bn() {
        return AccessHelper.m3096a(f6989a, PreferencesID.IS_POPUP_MONTHEND_DIALOG.name(), (Boolean) true);
    }

    /* renamed from: v */
    public static void m2821v(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.MV_CHECK_TYPE.name(), Integer.valueOf(i));
    }

    /* renamed from: bo */
    public static int m2914bo() {
        return AccessHelper.m3098a(f6989a, PreferencesID.MV_CHECK_TYPE.name(), 0);
    }

    /* renamed from: bp */
    public static boolean m2913bp() {
        return AccessHelper.m3096a(f6989a, PreferencesID.APP_RECOMMEND_MENU_VISIBLE.name(), (Boolean) true);
    }

    /* renamed from: ag */
    public static void m2967ag(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.APP_RECOMMEND_MENU_VISIBLE.name(), Boolean.valueOf(z));
    }

    /* renamed from: bq */
    public static boolean m2912bq() {
        return AccessHelper.m3096a(f6989a, PreferencesID.RECOMMEND_ENABLE.name(), (Boolean) true);
    }

    /* renamed from: ah */
    public static void m2965ah(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.RECOMMEND_ENABLE.name(), Boolean.valueOf(z));
    }

    /* renamed from: ai */
    public static void m2963ai(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.DOWNLOAD_CHOSE_DIALOG_ENABLE.name(), Boolean.valueOf(z));
    }

    /* renamed from: br */
    public static boolean m2911br() {
        return AccessHelper.m3096a(f6989a, PreferencesID.DOWNLOAD_CHOSE_DIALOG_ENABLE.name(), (Boolean) true);
    }

    /* renamed from: bs */
    public static boolean m2910bs() {
        return AccessHelper.m3096a(f6989a, PreferencesID.SHOW_BACKGROUND_MORE.name(), (Boolean) false);
    }

    /* renamed from: aj */
    public static void m2961aj(boolean z) {
        AccessHelper.m3087b(f6989a, PreferencesID.SHOW_BACKGROUND_MORE.name(), Boolean.valueOf(z));
    }

    /* renamed from: bt */
    public static long m2909bt() {
        return AccessHelper.m3097a(f6989a, PreferencesID.BACKGROUND_MORE_CREATE_TIME.name(), 0L);
    }

    /* renamed from: d */
    public static void m2896d(long j) {
        AccessHelper.m3093a(f6989a, PreferencesID.BACKGROUND_MORE_CREATE_TIME.name(), Long.valueOf(j));
    }

    /* renamed from: e */
    public static void m2890e(long j) {
        AccessHelper.m3093a(f6989a, PreferencesID.FEEDBACK_LAST_UPDATE_TIME.name(), Long.valueOf(j));
    }

    /* renamed from: bu */
    public static long m2908bu() {
        return AccessHelper.m3097a(f6989a, PreferencesID.FEEDBACK_LAST_UPDATE_TIME.name(), 0L);
    }

    /* renamed from: bv */
    public static boolean m2907bv() {
        return AccessHelper.m3096a(f6989a, PreferencesID.FEEDBACK_HAS_UPDATE.name(), (Boolean) false);
    }

    /* renamed from: w */
    public static void m2817w(int i) {
        AccessHelper.m3094a(f6989a, PreferencesID.LAST_OPEN_RECOMMEND_PAGE_TIME.name(), Integer.valueOf(i));
    }

    /* renamed from: bw */
    public static int m2906bw() {
        return AccessHelper.m3098a(f6989a, PreferencesID.LAST_OPEN_RECOMMEND_PAGE_TIME.name(), 0);
    }

    /* renamed from: a */
    public static void m3015a(GroupType groupType, String str) {
        String m2936b = m2936b(groupType);
        if (m2936b != null) {
            AccessHelper.m3086b(f6989a, PreferencesID.GROUP_SORT_ORDER_PREFIX.name() + m2936b, str);
        }
    }

    /* renamed from: b */
    private static String m2936b(GroupType groupType) {
        if (groupType == GroupType.DEFAULT_ARTIST) {
            return MediaStorage.GROUP_ID_ARTIST_PREFIX;
        }
        if (groupType == GroupType.DEFAULT_ALBUM) {
            return MediaStorage.GROUP_ID_ALBUM_PREFIX;
        }
        if (groupType != GroupType.DEFAULT_FOLDER) {
            return null;
        }
        return MediaStorage.GROUP_ID_FOLDER_PREFIX;
    }

    /* renamed from: a */
    public static String m3016a(GroupType groupType) {
        String m2936b = m2936b(groupType);
        return m2936b != null ? AccessHelper.m3092a(f6989a, PreferencesID.GROUP_SORT_ORDER_PREFIX.name() + m2936b, "") : "";
    }

    /* renamed from: bx */
    public static String m2905bx() {
        return AccessHelper.m3092a(f6989a, PreferencesID.LAST_CMMUSIC_INIT_SUCCESS_IMSI.name(), "");
    }

    /* renamed from: v */
    public static void m2820v(String str) {
        AccessHelper.m3086b(f6989a, PreferencesID.LAST_CMMUSIC_INIT_SUCCESS_IMSI.name(), str);
    }
}
