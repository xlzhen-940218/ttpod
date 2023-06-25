package com.sds.android.ttpod.framework.support.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.RemoteViews;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.framework.support.p134a.Player;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.io.File;

/* loaded from: classes.dex */
public abstract class AppWidgetProviderBase extends AppWidgetProvider {

    /* renamed from: a */
    private static volatile boolean f7122a;

    /* renamed from: c */
    private static final C2060a[] f7123c = {new C2060a(R.layout.appwidget_main_4x1, "com.sds.android.ttpod.component.appwidget.DesktopPlayer1"), new C2060a(R.layout.appwidget_main_4x2, "com.sds.android.ttpod.component.appwidget.DesktopPlayer2"), new C2060a(R.layout.appwidget_main_4x4, "com.sds.android.ttpod.component.appwidget.DesktopPlayer4"), new C2060a(R.layout.appwidget_main_5x1, "com.sds.android.ttpod.component.appwidget.DesktopPlayer5x1"), new C2060a(R.layout.appwidget_main_5x2, "com.sds.android.ttpod.component.appwidget.DesktopPlayer5x2")};

    /* renamed from: d */
    private static Handler f7124d = new Handler(Looper.getMainLooper());

    /* renamed from: e */
    private static Runnable f7125e = new Runnable() { // from class: com.sds.android.ttpod.framework.support.appwidget.AppWidgetProviderBase.2
        @Override // java.lang.Runnable
        public void run() {
            Player m2611e = Player.m2611e();
            if (m2611e.m2604h() != PlayStatus.STATUS_PLAYING) {
                boolean unused = AppWidgetProviderBase.f7122a = false;
                return;
            }
            MediaItem m2606g = m2611e.m2606g();
            if (m2606g == null || m2606g.isNull()) {
                boolean unused2 = AppWidgetProviderBase.f7122a = false;
                return;
            }
            AppWidgetProviderBase.m2533b(m2611e.m2602i(), m2606g.getDuration().intValue(), m2611e.m2600j());
            boolean unused3 = AppWidgetProviderBase.f7122a = true;
            AppWidgetProviderBase.f7124d.postDelayed(this, 1000L);
        }
    };

    /* renamed from: b */
    private Handler f7126b = new Handler(Looper.myLooper()) { // from class: com.sds.android.ttpod.framework.support.appwidget.AppWidgetProviderBase.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Process.killProcess(Process.myPid());
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.framework.support.appwidget.AppWidgetProviderBase$a */
    /* loaded from: classes.dex */
    public static final class C2060a {

        /* renamed from: a */
        private int f7128a;

        /* renamed from: b */
        private ComponentName f7129b;

        private C2060a(int i, String str) {
            this.f7128a = i;
            this.f7129b = new ComponentName("com.sds.android.ttpod", str);
        }
    }

    /* renamed from: a */
    public static void m2551a() {
        C2060a[] c2060aArr;
        LogUtils.debug("AppWidgetProviderBase", "updateAppWidgetStop");
        android.appwidget.AppWidgetManager appWidgetManager = android.appwidget.AppWidgetManager.getInstance(BaseApplication.getApplication());
        for (C2060a c2060a : f7123c) {
            RemoteViews remoteViews = new RemoteViews(BaseApplication.getApplication().getPackageName(), c2060a.f7128a);
            m2542a(remoteViews, PlayStatus.STATUS_STOPPED);
            m2532b(remoteViews);
            try {
                appWidgetManager.updateAppWidget(c2060a.f7129b, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public static void m2535b() {
        C2060a[] c2060aArr;
        android.appwidget.AppWidgetManager appWidgetManager = android.appwidget.AppWidgetManager.getInstance(BaseApplication.getApplication());
        MediaItem m2606g = Player.m2611e().m2606g();
        Bitmap m8445a = BitmapUtils.m8445a(Preferences.m3014a(m2606g), DisplayUtils.m7229a(200));
        for (C2060a c2060a : f7123c) {
            RemoteViews remoteViews = new RemoteViews(BaseApplication.getApplication().getPackageName(), c2060a.f7128a);
            try {
                m2544a(remoteViews, m8445a);
            } catch (Exception e) {
                String m2537a = m2537a(Preferences.m3014a(m2606g));
                LogUtils.error("AppWidgetProviderBase", "setAlbumCoverToRemoteView e albumCoverFile = " + m2537a);
                m2541a(remoteViews, m2537a);
            }
            m2547a(remoteViews);
            m2532b(remoteViews);
            try {
                appWidgetManager.updateAppWidget(c2060a.f7129b, remoteViews);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static void m2547a(RemoteViews remoteViews) {
        MediaItem m2606g = Player.m2611e().m2606g();
        if (m2606g != null && !m2606g.isNull()) {
            m2540a(remoteViews, m2606g.getArtist(), m2606g.getAlbum(), m2606g.getTitle());
        }
        m2542a(remoteViews, Player.m2611e().m2604h());
        m2543a(remoteViews, Preferences.m2862l());
        m2539a(remoteViews, Preferences.m2838r());
    }

    /* renamed from: a */
    public static void m2538a(PlayStatus playStatus) {
        C2060a[] c2060aArr;
        LogUtils.error("AppWidgetProviderBase", "updatePlayStatus");
        android.appwidget.AppWidgetManager appWidgetManager = android.appwidget.AppWidgetManager.getInstance(BaseApplication.getApplication());
        for (C2060a c2060a : f7123c) {
            RemoteViews remoteViews = new RemoteViews(BaseApplication.getApplication().getPackageName(), c2060a.f7128a);
            m2542a(remoteViews, playStatus);
            m2532b(remoteViews);
            try {
                appWidgetManager.updateAppWidget(c2060a.f7129b, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static void m2540a(RemoteViews remoteViews, String str, String str2, String str3) {
        int layoutId = remoteViews.getLayoutId();
        if (R.layout.appwidget_main_4x1 != layoutId && R.layout.appwidget_main_5x1 != layoutId) {
            remoteViews.setTextViewText(R.id.text_artist, TTTextUtils.validateString(BaseApplication.getApplication(), str));
        }
        if (R.layout.appwidget_main_4x2 == layoutId || R.layout.appwidget_main_5x2 == layoutId) {
            remoteViews.setTextViewText(R.id.text_album, TTTextUtils.validateString(BaseApplication.getApplication(), str2));
        }
        remoteViews.setTextViewText(R.id.text_title, TTTextUtils.validateString(BaseApplication.getApplication(), str3));
        if (R.layout.appwidget_main_4x1 == layoutId || R.layout.appwidget_main_5x1 == layoutId) {
            remoteViews.setTextViewText(R.id.text_title, ((Object) TTTextUtils.validateString(BaseApplication.getApplication(), str)) + " - " + ((Object) TTTextUtils.validateString(BaseApplication.getApplication(), str3)));
        }
    }

    /* renamed from: a */
    private static void m2544a(RemoteViews remoteViews, Bitmap bitmap) throws Exception {
        if (bitmap != null) {
            remoteViews.setImageViewBitmap(R.id.image_album_cover, bitmap);
        } else {
            remoteViews.setImageViewResource(R.id.image_album_cover, R.drawable.img_appwidget_album_cover_normal);
        }
    }

    /* renamed from: a */
    private static void m2541a(RemoteViews remoteViews, String str) {
        File m8407e = FileUtils.m8407e(str);
        if (m8407e != null && m8407e.exists()) {
            remoteViews.setImageViewUri(R.id.image_album_cover, Uri.fromFile(m8407e));
        } else {
            remoteViews.setImageViewResource(R.id.image_album_cover, R.drawable.img_appwidget_album_cover_normal);
        }
    }

    /* renamed from: a */
    private static void m2543a(RemoteViews remoteViews, PlayMode playMode) {
        int layoutId = remoteViews.getLayoutId();
        if (R.layout.appwidget_main_4x1 != layoutId && R.layout.appwidget_main_5x1 != layoutId) {
            remoteViews.setImageViewResource(R.id.button_playmode, m2550a(playMode.ordinal()));
        }
    }

    /* renamed from: a */
    private static void m2542a(RemoteViews remoteViews, PlayStatus playStatus) {
        remoteViews.setImageViewResource(R.id.button_play_pause, PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.img_appwidget_pause : R.drawable.img_appwidget_play);
    }

    /* renamed from: a */
    private static void m2539a(RemoteViews remoteViews, boolean z) {
        remoteViews.setImageViewResource(R.id.button_minilyric, z ? R.drawable.img_appwidget_minilyric_on : R.drawable.img_appwidget_minilyric_off);
    }

    /* renamed from: a */
    private static String m2537a(String str) {
        String str2 = TTPodConfig.m5298j() + File.separator + "albumCoverFileTmp";
        Bitmap m8445a = BitmapUtils.m8445a(str, DisplayUtils.m7229a(200));
        if (m8445a != null) {
            BitmapUtils.m8450a(m8445a, str2);
            m8445a.recycle();
        }
        return str2;
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, android.appwidget.AppWidgetManager appWidgetManager, int[] iArr) {
        LogUtils.error("AppWidgetProviderBase", "onUpdate");
        AppWidgetProviderInfo appWidgetInfo = appWidgetManager.getAppWidgetInfo(iArr[0]);
        if (appWidgetInfo != null) {
            m2548a(context, appWidgetManager, appWidgetInfo.provider, appWidgetInfo.initialLayout);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onAppWidgetOptionsChanged(Context context, android.appwidget.AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        AppWidgetProviderInfo appWidgetInfo = appWidgetManager.getAppWidgetInfo(i);
        if (appWidgetInfo != null) {
            m2548a(context, appWidgetManager, appWidgetInfo.provider, appWidgetInfo.initialLayout);
        }
    }

    /* renamed from: a */
    private static void m2548a(Context context, android.appwidget.AppWidgetManager appWidgetManager, ComponentName componentName, int i) {
        LogUtils.error("AppWidgetProviderBase", "initAppWidget");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), i);
        AppWidgetPreference m2525a = AppWidgetPreference.m2525a();
        try {
            m2544a(remoteViews, BitmapUtils.m8445a(m2525a.m2513e(), DisplayUtils.m7229a(200)));
        } catch (Exception e) {
            String m2537a = m2537a(m2525a.m2513e());
            LogUtils.error("AppWidgetProviderBase", "setAlbumCoverToRemoteView e albumCoverFile = " + m2537a);
            m2541a(remoteViews, m2537a);
        }
        m2540a(remoteViews, m2525a.m2520b(), m2525a.m2517c(), m2525a.m2515d());
        m2542a(remoteViews, PlayStatus.STATUS_STOPPED);
        m2543a(remoteViews, m2525a.m2510h());
        m2539a(remoteViews, m2525a.m2512f());
        m2532b(remoteViews);
        try {
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        context.sendBroadcast(new Intent(Action.APP_WIDGET_QUERY));
    }

    /* renamed from: a */
    private static int m2550a(int i) {
        return new int[]{R.drawable.img_appwidget_playmode_repeat, R.drawable.img_appwidget_playmode_repeatone, R.drawable.img_appwidget_playmode_sequence, R.drawable.img_appwidget_playmode_shuffle}[i];
    }

    /* renamed from: b */
    private static void m2532b(RemoteViews remoteViews) {
        m2531b(remoteViews, 0, R.id.image_album_cover, Action.START_ENTRY);
        m2545a(remoteViews, 1, R.id.button_play_pause, "play_pause_command");
        m2545a(remoteViews, 2, R.id.button_play_prev, "previous_command");
        m2545a(remoteViews, 3, R.id.button_play_next, "next_command");
        m2545a(remoteViews, 4, R.id.button_playmode, "switch_play_mode_command");
        m2545a(remoteViews, 5, R.id.button_minilyric, "switch_desktop_lyric_hide_show_command");
    }

    /* renamed from: a */
    private static void m2545a(RemoteViews remoteViews, int i, int i2, String str) {
        remoteViews.setOnClickPendingIntent(i2, PendingIntent.getService(BaseApplication.getApplication()
                , i, new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", str)
                        .setAction(str), PendingIntent.FLAG_IMMUTABLE));
    }

    /* renamed from: b */
    private static void m2531b(RemoteViews remoteViews, int i, int i2, String str) {
        remoteViews.setOnClickPendingIntent(i2, PendingIntent.getActivity(BaseApplication.getApplication(), i, new Intent(str), PendingIntent.FLAG_MUTABLE));
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        LogUtils.error("AppWidgetProviderBase", intent.getAction());
        this.f7126b.removeMessages(0);
        this.f7126b.sendEmptyMessageDelayed(0, 30000L);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        super.onEnabled(context);
        LogUtils.error("AppWidgetProviderBase", "onEnabled");
        AppWidgetPreference.m2525a().m2518b(true);
        context.sendBroadcast(new Intent(Action.APP_WIDGET_ENABLE_CHANGED).putExtra("app_widget_enable", true));
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        super.onDisabled(context);
        LogUtils.error("AppWidgetProviderBase", "onDisabled");
        AppWidgetPreference.m2525a().m2518b(false);
        context.sendBroadcast(new Intent(Action.APP_WIDGET_ENABLE_CHANGED).putExtra("app_widget_enable", false));
    }

    /* renamed from: b */
    public static void m2530b(PlayStatus playStatus) {
        LogUtils.error("AppWidgetProviderBase", "updateProcessStatus");
        boolean m2511g = AppWidgetPreference.m2525a().m2511g();
        if (playStatus == PlayStatus.STATUS_PLAYING) {
            if (m2511g) {
                f7122a = true;
                f7124d.postDelayed(f7125e, 1000L);
                return;
            }
            f7122a = false;
            f7124d.removeCallbacks(f7125e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m2533b(int i, int i2, float f) {
        C2060a[] c2060aArr;
        LogUtils.error("AppWidgetProviderBase", "refreshProcess");
        int i3 = (int) (i2 * f);
        if (i3 < 0) {
            i3 = 0;
        }
        android.appwidget.AppWidgetManager appWidgetManager = android.appwidget.AppWidgetManager.getInstance(BaseApplication.getApplication());
        for (C2060a c2060a : f7123c) {
            RemoteViews remoteViews = new RemoteViews(BaseApplication.getApplication().getPackageName(), c2060a.f7128a);
            m2546a(remoteViews, i, i2, i3);
            m2547a(remoteViews);
            m2532b(remoteViews);
            try {
                appWidgetManager.updateAppWidget(c2060a.f7129b, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static void m2546a(RemoteViews remoteViews, int i, int i2, int i3) {
        remoteViews.setTextViewText(R.id.text_time_current, m2534b(i));
        remoteViews.setTextViewText(R.id.text_time_duration, m2534b(i2));
        remoteViews.setProgressBar(R.id.seekbar_progress, i2, i, false);
        remoteViews.setInt(R.id.seekbar_progress, "setSecondaryProgress", i3);
    }

    /* renamed from: b */
    private static String m2534b(int i) {
        int i2 = i / 1000;
        return m2528c(i2 / 60) + ":" + m2528c(i2 % 60);
    }

    /* renamed from: c */
    private static String m2528c(int i) {
        if (i < 0) {
            return "00";
        }
        if (i >= 0 && i < 10) {
            return FeedbackItem.STATUS_WAITING + i;
        }
        return "" + i;
    }
}
