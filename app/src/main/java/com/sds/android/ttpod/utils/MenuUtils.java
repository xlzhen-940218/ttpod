package com.sds.android.ttpod.utils;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.GlobalMenuItem;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.audiofx.EffectDetect;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.a.o */
/* loaded from: classes.dex */
public final class MenuUtils {

    /* renamed from: a */
    private static ArrayList<GlobalMenuItem> f2504a = new ArrayList<>();

    /* renamed from: a */
    public static List<GlobalMenuItem> m8257a() {
        f2504a.clear();
        f2504a.add(new GlobalMenuItem(2, 0, R.string.menu_scan_media, ThemeElement.SETTING_SCANNING_IMAGE, R.string.icon_menu_scan_media));
        f2504a.add(new GlobalMenuItem(1, 0, R.string.sleep_mode, ThemeElement.SETTING_SLEEP_IMAGE, R.string.icon_sleep_mode));
        f2504a.add(new GlobalMenuItem(4, 0, R.string.theme_background, ThemeElement.SETTING_THEME_IMAGE, R.string.icon_skin));
        f2504a.add(new GlobalMenuItem(6, 0, EffectDetect.usingAudioPlus() ? R.string.audio_plus : R.string.audio_effect, ThemeElement.SETTING_AUDIO_EFFECT_IMAGE, R.string.icon_audio_effect));
        f2504a.add(new GlobalMenuItem(3, 0, R.string.repeat_play, ThemeElement.SETTING_PLAY_LOOP_IMAGE, R.string.icon_repeat_play));
        f2504a.add(new GlobalMenuItem(8, 0, R.string.recognize, ThemeElement.SETTING_RECOGNIZE_IMAGE, R.string.icon_recognize));
        f2504a.add(new GlobalMenuItem(0, 0, R.string.setting, ThemeElement.SETTING_ICON_IMAGE, R.string.icon_setting));
        f2504a.add(new GlobalMenuItem(10, 0, R.string.exit, ThemeElement.SETTING_EXIT_IMAGE, R.string.icon_exit));
        f2504a.add(new GlobalMenuItem(11, 0, R.string.menu_ktv, ThemeElement.SETTING_KTV_IMAGE, R.string.icon_menu_ktv));
        f2504a.add(new GlobalMenuItem(7, 0, R.string.share_fast_send, ThemeElement.SETTING_SHARE_SONG_IMAGE, R.string.icon_share_fast_send));
        f2504a.add(new GlobalMenuItem(13, 0, R.string.ttpod_fm, ThemeElement.SETTING_TTPOD_FM_IMAGE, R.string.icon_radio));
        f2504a.add(new GlobalMenuItem(9, 0, R.string.market_app, ThemeElement.SETTING_RECOMMEND_IMAGE, R.string.icon_market_app));
        return f2504a;
    }
}
