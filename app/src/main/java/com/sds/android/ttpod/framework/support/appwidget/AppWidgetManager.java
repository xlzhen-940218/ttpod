package com.sds.android.ttpod.framework.support.appwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.framework.support.p134a.Player;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;

/* loaded from: classes.dex */
public final class AppWidgetManager {

    /* renamed from: a */
    private static AppWidgetManager f7118a = null;

    /* renamed from: b */
    private Monitor f7119b = null;

    /* renamed from: c */
    private Preferences.InterfaceC2031a f7120c = new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.framework.support.appwidget.AppWidgetManager.1
        @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
        /* renamed from: a */
        public void mo2553a(PreferencesID preferencesID) {
            if (preferencesID == PreferencesID.CURRENT_ARTIST_BITMAP_PATH) {
                LogUtils.debug("AppWidgetManager", "CURRENT_ARTIST_BITMAP_PATH");
                AppWidgetPreference.m2525a().m2514d(Preferences.m3014a(Player.getInstance().getMediaItem()));
                AppWidgetProviderBase.m2535b();
            } else if (preferencesID == PreferencesID.PLAY_MODE) {
                AppWidgetPreference.m2525a().m2524a(Preferences.m2862l());
                AppWidgetProviderBase.m2535b();
            } else if (preferencesID == PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED) {
                AppWidgetPreference.m2525a().m2521a(Preferences.m2838r());
                AppWidgetProviderBase.m2535b();
            }
        }
    };

    /* renamed from: a */
    public static AppWidgetManager m2557a() {
        synchronized (AppWidgetManager.class) {
            if (f7118a == null) {
                f7118a = new AppWidgetManager();
            }
        }
        return f7118a;
    }

    /* renamed from: b */
    public void m2555b() {
        Preferences.m3019a(PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.f7120c);
        Preferences.m3019a(PreferencesID.PLAY_MODE, this.f7120c);
        Preferences.m3019a(PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.f7120c);
        this.f7119b = new Monitor();
        BaseApplication.getApplication().registerReceiver(this.f7119b, Monitor.m2552a());
    }

    /* renamed from: c */
    public void m2554c() {
        Preferences.m2938b(PreferencesID.CURRENT_ARTIST_BITMAP_PATH, this.f7120c);
        Preferences.m2938b(PreferencesID.PLAY_MODE, this.f7120c);
        Preferences.m2938b(PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.f7120c);
        AppWidgetProviderBase.m2551a();
        BaseApplication.getApplication().unregisterReceiver(this.f7119b);
    }

    /* renamed from: a */
    public boolean m2556a(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        if ("switch_play_mode_command".equals(stringExtra)) {
            Preferences.m3018a(PlayMode.values()[(Preferences.m2862l().ordinal() + 1) % PlayMode.values().length]);
        } else if (!"switch_desktop_lyric_hide_show_command".equals(stringExtra)) {
            return false;
        } else {
            Preferences.m2879g(Preferences.m2838r() ? false : true);
        }
        return true;
    }

    /* loaded from: classes.dex */
    public static class Monitor extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtils.debug("AppWidgetManager", action);
            if (Action.PLAY_STATUS_CHANGED.equals(action)) {
                PlayStatus m2604h = Player.getInstance().m2604h();
                AppWidgetPreference.m2525a().m2523a(m2604h);
                AppWidgetProviderBase.m2530b(m2604h);
                AppWidgetProviderBase.m2538a(m2604h);
            } else if (Action.PLAY_MEDIA_CHANGED.equals(action)) {
                LogUtils.debug("AppWidgetManager", "PLAY_MEDIA_CHANGED");
                MediaItem m2606g = Player.getInstance().getMediaItem();
                AppWidgetPreference m2525a = AppWidgetPreference.m2525a();
                if (m2606g != null && !m2606g.isNull()) {
                    m2525a.m2522a(m2606g.getArtist());
                    m2525a.m2519b(m2606g.getAlbum());
                    m2525a.m2516c(m2606g.getTitle());
                    try {
                        AppWidgetProviderBase.m2535b();
                    } catch (NoClassDefFoundError e) {
                        e.printStackTrace();
                    }
                }
            } else if (Action.APP_WIDGET_QUERY.equals(action)) {
                LogUtils.debug("AppWidgetManager", "APP_WIDGET_QUERY");
                AppWidgetProviderBase.m2535b();
            } else if (Action.APP_WIDGET_ENABLE_CHANGED.equals(action)) {
                LogUtils.debug("AppWidgetManager", "APP_WIDGET_ENABLE_CHANGED");
                AppWidgetPreference.m2525a().m2518b(intent.getBooleanExtra("app_widget_enable", false));
                AppWidgetProviderBase.m2530b(Player.getInstance().m2604h());
            }
        }

        /* renamed from: a */
        static IntentFilter m2552a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.APP_WIDGET_QUERY);
            intentFilter.addAction(Action.PLAY_STATUS_CHANGED);
            intentFilter.addAction(Action.PLAY_MEDIA_CHANGED);
            intentFilter.addAction(Action.APP_WIDGET_ENABLE_CHANGED);
            return intentFilter;
        }
    }
}
