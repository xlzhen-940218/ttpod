package com.sds.android.ttpod.framework.modules.core.p121g;

import android.content.Context;
import android.content.SharedPreferences;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.core.p113b.AutoDownloadNetworkType;
import com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeSensitivityType;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.AudioQuality;

/* renamed from: com.sds.android.ttpod.framework.modules.core.g.a */
/* loaded from: classes.dex */
public class OldPreferenceCompact {
    /* renamed from: a */
    public static boolean m4147a() {
        SharedPreferences sharedPreferences = BaseApplication.getApplication().getSharedPreferences("com.sds.android.ttpod_preferences", 4);
        return sharedPreferences == null || !sharedPreferences.contains("play_on_headset_plug");
    }

    /* renamed from: b */
    public static void m4144b() {
        BaseApplication m4635c = BaseApplication.getApplication();
        SharedPreferences sharedPreferences = m4635c.getSharedPreferences("com.sds.android.ttpod_preferences", 4);
        m4146a(m4635c);
        m4143b(m4635c);
        m4140c(m4635c);
        if (sharedPreferences != null && sharedPreferences.contains("play_on_headset_plug")) {
            m4145a(sharedPreferences);
        }
    }

    /* renamed from: a */
    private static void m4146a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("playback", 4);
        if (sharedPreferences != null && sharedPreferences.contains("playingmode")) {
            Preferences.m3018a(PlayMode.values()[sharedPreferences.getInt("playingmode", 0)]);
            Preferences.m2979aa(sharedPreferences.getBoolean("show_notification_when_paused", true));
            Preferences.m3031Y(sharedPreferences.getBoolean("show_previous_in_notification", true));
            sharedPreferences.edit().clear().commit();
        }
    }

    /* renamed from: a */
    private static void m4145a(SharedPreferences sharedPreferences) {
        m4139c(sharedPreferences);
        m4142b(sharedPreferences);
        m4138d(sharedPreferences);
        m4137e(sharedPreferences);
        m4136f(sharedPreferences);
        m4135g(sharedPreferences);
        sharedPreferences.edit().clear().commit();
    }

    /* renamed from: b */
    private static void m4143b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("landscape", 4);
        if (sharedPreferences != null && sharedPreferences.contains("enable_landscape")) {
            Preferences.m2883f(sharedPreferences.getBoolean("enable_landscape", false));
            Preferences.m2857m(sharedPreferences.getInt("landscape_effect_index", 0));
            sharedPreferences.edit().clear().commit();
        }
    }

    /* renamed from: c */
    private static void m4140c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("minilyric", 4);
        if (sharedPreferences != null && sharedPreferences.contains("enable_mini_lyric")) {
            Preferences.m2879g(sharedPreferences.getBoolean("enable_mini_lyric", true));
            sharedPreferences.edit().clear().commit();
        }
    }

    /* renamed from: b */
    private static void m4142b(SharedPreferences sharedPreferences) {
        Preferences.m2875h(sharedPreferences.getBoolean("lock_screen_flag", true));
    }

    /* renamed from: c */
    private static void m4139c(SharedPreferences sharedPreferences) {
        Preferences.m2863k(Boolean.valueOf(sharedPreferences.getBoolean("play_on_headset_plug", false)).booleanValue());
        Preferences.m2867j(Boolean.valueOf(sharedPreferences.getBoolean("pause_on_headset_draw", true)).booleanValue());
        Preferences.m2859l(Boolean.valueOf(sharedPreferences.getBoolean("headset_flag", true)).booleanValue());
        Preferences.m2855m(Boolean.valueOf(sharedPreferences.getBoolean("switch_headset_action", false)).booleanValue());
    }

    /* renamed from: d */
    private static void m4138d(SharedPreferences sharedPreferences) {
        ShakeSensitivityType shakeSensitivityType;
        Preferences.m2851n(Boolean.valueOf(sharedPreferences.getBoolean("shake_song_flag", false)).booleanValue());
        int i = sharedPreferences.getInt("sensor_sensitivity_item_index", 1);
        if (i == 0) {
            shakeSensitivityType = ShakeSensitivityType.SMOOTH_SHAKE;
        } else if (i == 1) {
            shakeSensitivityType = ShakeSensitivityType.EASY_SHAKE;
        } else if (i == 2) {
            shakeSensitivityType = ShakeSensitivityType.NORMAL_SHAKE;
        } else if (i == 3) {
            shakeSensitivityType = ShakeSensitivityType.HARD_SHAKE;
        } else {
            shakeSensitivityType = ShakeSensitivityType.EXTREME_SHAKE;
        }
        Preferences.m3021a(shakeSensitivityType);
    }

    /* renamed from: e */
    private static void m4137e(SharedPreferences sharedPreferences) {
        Preferences.m2847o(sharedPreferences.getBoolean("light_list", false));
        Preferences.m2843p(sharedPreferences.getBoolean("light_play", true));
        Preferences.m2839q(sharedPreferences.getBoolean("light_lockscreen", false));
        Preferences.m2835r(sharedPreferences.getBoolean("light_landscape", true));
    }

    /* renamed from: f */
    private static void m4136f(SharedPreferences sharedPreferences) {
        AudioQuality audioQuality = AudioQuality.values()[sharedPreferences.getInt("key_online_media_audition_quality", 0)];
        Preferences.m3017a(audioQuality);
        Preferences.m2937b(audioQuality);
        Preferences.m2901c(audioQuality);
        Preferences.m2895d(AudioQuality.values()[sharedPreferences.getInt("key_favorite_download_quality", 2)]);
        int i = sharedPreferences.getInt("key_favorite_download_network", 1);
        AutoDownloadNetworkType autoDownloadNetworkType = AutoDownloadNetworkType.DISABLE;
        if (i == 1) {
            autoDownloadNetworkType = AutoDownloadNetworkType.WIFI;
        } else if (i == 2) {
            autoDownloadNetworkType = AutoDownloadNetworkType.ALL;
        }
        Preferences.m3020a(autoDownloadNetworkType);
        m4141c();
        AutoDownloadNetworkType autoDownloadNetworkType2 = AutoDownloadNetworkType.DISABLE;
        String string = sharedPreferences.getString("lyric_download", "always");
        if ("always".equals(string)) {
            autoDownloadNetworkType2 = AutoDownloadNetworkType.ALL;
        } else if ("wifi".equals(string)) {
            autoDownloadNetworkType2 = AutoDownloadNetworkType.WIFI;
        }
        Preferences.m2939b(autoDownloadNetworkType2);
        Preferences.m3057L(sharedPreferences.getBoolean("prefer_artist_pic_play", true));
        Preferences.m2823u(sharedPreferences.getBoolean("prefer_show_embed_art", false));
    }

    /* renamed from: c */
    private static void m4141c() {
        String string = BaseApplication.getApplication().getSharedPreferences("mediascan", 4).getString("download_media_folder", "");
        if (!StringUtils.m8346a(string)) {
            Preferences.m2880g(string);
        }
    }

    /* renamed from: g */
    private static void m4135g(SharedPreferences sharedPreferences) {
        Preferences.m2831s(sharedPreferences.getBoolean("auto_play", false));
        Preferences.m2827t(sharedPreferences.getBoolean("auto_airplane", false));
        Preferences.m2816w(sharedPreferences.getBoolean("enable_push", true));
        Preferences.m2819v(sharedPreferences.getBoolean("enable_notification_bar", true));
    }
}
