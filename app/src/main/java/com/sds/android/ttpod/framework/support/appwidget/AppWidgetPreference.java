package com.sds.android.ttpod.framework.support.appwidget;

import android.content.SharedPreferences;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.player.PlayStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.ttpod.framework.support.appwidget.a */
/* loaded from: classes.dex */
public final class AppWidgetPreference {

    /* renamed from: b */
    private static AppWidgetPreference f7130b = null;

    /* renamed from: a */
    private SharedPreferences f7131a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AppWidgetPreference m2525a() {
        if (f7130b == null) {
            f7130b = new AppWidgetPreference();
        }
        return f7130b;
    }

    AppWidgetPreference() {
        this.f7131a = null;
        this.f7131a = BaseApplication.getApplication().getSharedPreferences("appwidget_preference", 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2522a(String str) {
        this.f7131a.edit().putString("media_artist", str).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public String m2520b() {
        return this.f7131a.getString("media_artist", BaseApplication.getApplication().getString(R.string.unknown));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2519b(String str) {
        this.f7131a.edit().putString("media_album", str).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public String m2517c() {
        return this.f7131a.getString("media_album", BaseApplication.getApplication().getString(R.string.unknown));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m2516c(String str) {
        this.f7131a.edit().putString("media_title", str).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public String m2515d() {
        return this.f7131a.getString("media_title", BaseApplication.getApplication().getString(R.string.unknown));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public void m2514d(String str) {
        this.f7131a.edit().putString("current_artist_bitmap_path", str).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public String m2513e() {
        return this.f7131a.getString("current_artist_bitmap_path", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2521a(boolean z) {
        this.f7131a.edit().putBoolean("is_show_desktop_lyric_enabled", z).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean m2512f() {
        return this.f7131a.getBoolean("is_show_desktop_lyric_enabled", true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2518b(boolean z) {
        this.f7131a.edit().putBoolean("appwidget_enabled", z).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean m2511g() {
        return this.f7131a.getBoolean("appwidget_enabled", false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2524a(PlayMode playMode) {
        this.f7131a.edit().putInt("play_mode", playMode.ordinal()).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public PlayMode m2510h() {
        return PlayMode.values()[this.f7131a.getInt("play_mode", PlayMode.REPEAT.ordinal())];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2523a(PlayStatus playStatus) {
        this.f7131a.edit().putInt("play_status", playStatus.ordinal()).commit();
    }
}
