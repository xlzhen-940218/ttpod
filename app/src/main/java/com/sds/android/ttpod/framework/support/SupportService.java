package com.sds.android.ttpod.framework.support;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.provider.Settings;
import android.widget.Toast;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;





import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseService;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.NotificationUtils;
import com.sds.android.ttpod.framework.storage.database.SqliteDbWrapper;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.support.appwidget.AppWidgetManager;
import com.sds.android.ttpod.framework.support.download.DownloadTaskFacade;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.download.ManagerWrapper;
import com.sds.android.ttpod.framework.support.minilyric.MiniLyricManager;
import com.sds.android.ttpod.framework.support.monitor.AppInstallMonitor;
import com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver;
import com.sds.android.ttpod.framework.support.monitor.NetworkBroadcast;
import com.sds.android.ttpod.framework.support.p134a.Player;
import com.sds.android.ttpod.framework.support.search.SearchManager;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.util.List;

/* loaded from: classes.dex */
public class SupportService extends BaseService implements Player.InterfaceC2054a, Player.InterfaceC2055b, Player.InterfaceC2056c, SearchManager.InterfaceC2089b {

    /* renamed from: a */
    private static long f6992a;

    /* renamed from: b */
    private AppInstallMonitor f6993b;

    /* renamed from: c */
    private NetworkBroadcast f6994c;

    /* renamed from: d */
    private SupportServiceStub f6995d;

    /* renamed from: e */
    private boolean f6996e = false;

    /* renamed from: f */
    private DownloadTaskFacade f6997f = new DownloadTaskFacade(new SqliteDbWrapper(), new ManagerWrapper());

    /* renamed from: g */
    private Preferences.InterfaceC2031a f6998g = new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.framework.support.SupportService.1
        @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
        /* renamed from: a */
        public void mo2553a(PreferencesID preferencesID) {
            if (preferencesID == PreferencesID.IS_HEADSET_CONTROL_ENABLED) {
                MediaButtonReceiver.m2252b();
            } else if (preferencesID == PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED) {
                if (Preferences.m2838r()) {
                    MiniLyricManager.m2344a().m2341a(Preferences.m2935b(Player.getInstance().getMediaItem()));
                } else {
                    MiniLyricManager.m2344a().m2336c();
                }
            } else if (preferencesID == PreferencesID.USER_INFO) {
                TTPodUser m2954aq = Preferences.m2954aq();
                EnvironmentUtils.UUIDConfig.m8498a(m2954aq != null ? m2954aq.getUserId() : 0L);
            } else if (PreferencesID.NOTIFICATION_PRIORITY == preferencesID) {
                SupportService.this.m4620a(15121720);
                SupportService.this.m2769r();
            } else {
                SupportService.this.m2769r();
            }
        }
    };

    /* renamed from: h */
    private Runnable f6999h = new Runnable() { // from class: com.sds.android.ttpod.framework.support.SupportService.2
        @Override // java.lang.Runnable
        public void run() {
            LogUtils.error("SupportService", "Sleep Runnable");
            m2767a();
            SupportService.this.m2772o();
        }

        /* renamed from: a */
        private void m2767a() {
            try {
                LogUtils.error("SupportService", "handleSleep");
                if (Preferences.m3074D()) {
                    Settings.System.putInt(BaseApplication.getApplication().getContentResolver(), "airplane_mode_on", 1);
                    BaseApplication.getApplication().sendBroadcast(new Intent("android.intent.action.AIRPLANE_MODE").putExtra("state", true));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            LogUtils.error("SupportService", "sleep goodbye");
            Toast.makeText(BaseApplication.getApplication(), R.string.sleep_goodbye, Toast.LENGTH_SHORT).show();
        }
    };

    /* renamed from: i */
    private Handler f7000i = new Handler();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.f6995d == null) {
            this.f6995d = new SupportServiceStub(this);
        }
        return this.f6995d;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f6997f.m2439a();
        //OnlineMediaStatistic.m5045a(Preferences.m2928ba());
        m2771p();
        MediaItem m2606g = Player.getInstance().getMediaItem();
        if (m2606g != null) {
            m2792b(m2606g);
        }
        sendBroadcast(new Intent(Action.LAUNCHER));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            m2787c(intent);
            m2809a(intent);
        }
        return super.onStartCommand(intent, i, i2);
    }

    /* renamed from: a */
    private void m2809a(Intent intent) {
        if (!m2795b(intent) && !Player.getInstance().m2635a(intent) && !AppWidgetManager.m2557a().m2556a(intent) && !MiniLyricManager.m2344a().m2343a(intent)) {
            SearchManager.m2232a().m2231a(intent);
        }
    }

    /* renamed from: b */
    private boolean m2795b(Intent intent) {
        if (StringUtils.equals(intent.getStringExtra("command"), "exit_command")) {
            m2772o();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public void m2772o() {
        UnicomFlowUtil.m3938n();
        LogUtils.error("SupportService", "handle intent exit");
        m2776k();
        if (Player.getInstance().m2604h() != PlayStatus.STATUS_STOPPED) {
            Player.getInstance().m2594m();
        }
        this.f6997f.m2424b();
        sendBroadcast(new Intent(Action.EXIT));
        stopSelf();
    }

    /* renamed from: c */
    private void m2787c(Intent intent) {
        if (m2784d(intent)) {
            String stringExtra = intent.getStringExtra("command");
            if (StringUtils.equals(stringExtra, "play_pause_command")) {
                PlayStatus m2604h = Player.getInstance().m2604h();
                if (m2604h == PlayStatus.STATUS_PLAYING) {
                    //LocalStatistic.m5095k();
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_NOTIFY_PAUSE.getValue(), SPage.PAGE_SETTING_NOTIFICATION.getValue(), SPage.PAGE_NONE.getValue()).post();
                } else if (m2604h == PlayStatus.STATUS_PAUSED || m2604h == PlayStatus.STATUS_STOPPED) {
                    //LocalStatistic.m5096j();
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_NOTIFY_START.getValue(), SPage.PAGE_SETTING_NOTIFICATION.getValue(), SPage.PAGE_NONE.getValue()).post();
                }
            } else if (StringUtils.equals(stringExtra, "previous_command")) {
                //LocalStatistic.m5098h();
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_NOTIFY_PREV.getValue(), SPage.PAGE_SETTING_NOTIFICATION.getValue(), SPage.PAGE_NONE.getValue()).post();
            } else if (StringUtils.equals(stringExtra, "next_command")) {
                //LocalStatistic.m5097i();
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_NOTIFY_NEXT.getValue(), SPage.PAGE_SETTING_NOTIFICATION.getValue(), SPage.PAGE_NONE.getValue()).post();
            } else if (StringUtils.equals(stringExtra, "exit_command")) {
                //LocalStatistic.m5086t();
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_NOTIFY_EXIT.getValue(), SPage.PAGE_SETTING_NOTIFICATION.getValue(), SPage.PAGE_NONE.getValue()).post();
            }
        }
    }

    /* renamed from: d */
    private boolean m2784d(Intent intent) {
        return StringUtils.equals(intent == null ? null : intent.getStringExtra("key_origin"), "notification");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();

        m2770q();
        m4620a(15121720);
        NotificationUtils.m4697a();
        //AppRuntimeStatistic.m5272a(//AppRuntimeStatistic.EnumC1767a.EXIT_STATE);
        //SSystemEvent //SSystemEvent = //new //SSystemEvent("SYS_STARTUP", "exit");
        //SSystemEvent.setPostStrategy(SPostStrategy.IMMEDIATELAY_WRITE_FILE);
        //SSystemEvent.post();
        //SEngine.unbindFromService(getApplicationContext());
        //StatisticUtils.m4918a();
        Process.killProcess(Process.myPid());
    }

    /* renamed from: p */
    private void m2771p() {
        Player.getInstance().m2636a(getApplication());
        Player.getInstance().m2633a((Player.InterfaceC2055b) this);
        Player.getInstance().m2632a((Player.InterfaceC2056c) this);
        Player.getInstance().m2634a((Player.InterfaceC2054a) this);
        SearchManager.m2232a().m2230a(this);
        AppWidgetManager.m2557a().m2555b();
        MiniLyricManager.m2344a().m2334d();
        Preferences.m3019a(PreferencesID.IS_SHOW_NOTIFICATION_WHILE_PAUSED_ENABLED, this.f6998g);
        Preferences.m3019a(PreferencesID.IS_SHOW_PREVIOUS_IN_NOTIFICATION_ENABLED, this.f6998g);
        Preferences.m3019a(PreferencesID.IS_SHOW_CLOSE_IN_NOTIFICATION_ENABLED, this.f6998g);
        Preferences.m3019a(PreferencesID.IS_HEADSET_CONTROL_ENABLED, this.f6998g);
        Preferences.m3019a(PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.f6998g);
        Preferences.m3019a(PreferencesID.USER_INFO, this.f6998g);
        Preferences.m3019a(PreferencesID.NOTIFICATION_PRIORITY, this.f6998g);
        this.f6993b = new AppInstallMonitor();
        this.f6994c = new NetworkBroadcast(this);
        registerReceiver(this.f6993b, AppInstallMonitor.m2265a());
        registerReceiver(this.f6994c, NetworkBroadcast.m2243a());
    }

    /* renamed from: q */
    private void m2770q() {
        unregisterReceiver(this.f6993b);
        unregisterReceiver(this.f6994c);
        this.f6993b = null;
        this.f6994c = null;
        Preferences.m2938b(PreferencesID.IS_SHOW_NOTIFICATION_WHILE_PAUSED_ENABLED, this.f6998g);
        Preferences.m2938b(PreferencesID.IS_SHOW_PREVIOUS_IN_NOTIFICATION_ENABLED, this.f6998g);
        Preferences.m2938b(PreferencesID.IS_SHOW_CLOSE_IN_NOTIFICATION_ENABLED, this.f6998g);
        Preferences.m2938b(PreferencesID.IS_HEADSET_CONTROL_ENABLED, this.f6998g);
        Preferences.m2938b(PreferencesID.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.f6998g);
        Preferences.m2938b(PreferencesID.USER_INFO, this.f6998g);
        Preferences.m2938b(PreferencesID.NOTIFICATION_PRIORITY, this.f6998g);
        SearchManager.m2232a().m2230a((SearchManager.InterfaceC2089b) null);
        SearchManager.m2232a().m2222b();
        MiniLyricManager.m2344a().m2332e();
        AppWidgetManager.m2557a().m2554c();
        Player.getInstance().m2632a((Player.InterfaceC2056c) null);
        Player.getInstance().m2633a((Player.InterfaceC2055b) null);
        Player.getInstance().m2634a((Player.InterfaceC2054a) null);
        Player.getInstance().m2608f();
    }

    @Override // com.sds.android.ttpod.framework.support.p134a.Player.InterfaceC2055b
    /* renamed from: a */
    public void mo2559a(MediaItem mediaItem) {
        m2790b(Preferences.getLocalGroupId(), mediaItem);
        m2792b(mediaItem);
        m2768s();
    }

    @Override // com.sds.android.ttpod.framework.support.p134a.Player.InterfaceC2054a
    /* renamed from: a */
    public void mo2560a(long j) {
        if (HttpRequest.m8701c()) {
            f6992a += j;
            LogUtils.debug("SupportService", "unicom flow play flow changed: flowSize:" + j + "  totalSize:" + f6992a);
        }
    }

    /* renamed from: b */
    private void m2792b(MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("mediaItem", mediaItem);
        sendBroadcast(new Intent(Action.PLAY_MEDIA_CHANGED).putExtra("song_title", mediaItem != null ? mediaItem.getTitle() : "").putExtra("play_status", Player.getInstance().m2604h().ordinal()).putExtras(bundle));
    }

    @Override // com.sds.android.ttpod.framework.support.p134a.Player.InterfaceC2056c
    /* renamed from: a */
    public void mo2558a() {
        LogUtils.debug("SupportService", "onPlayStatusChanged");
        MediaItem m2606g = Player.getInstance().getMediaItem();
        sendBroadcast(new Intent(Action.PLAY_STATUS_CHANGED).putExtra("play_status", Player.getInstance().m2604h().ordinal()).putExtra("song_title", m2606g != null ? m2606g.getTitle() : ""));
        m2769r();
    }

    @Override // com.sds.android.ttpod.framework.support.search.SearchManager.InterfaceC2089b
    /* renamed from: b */
    public void mo2190b() {
        m2769r();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m2789c() {
        Player.getInstance().m2594m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public int m2785d() {
        return Player.getInstance().m2604h().ordinal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public int m2782e() {
        return Player.getInstance().m2602i();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public float m2781f() {
        return Player.getInstance().m2600j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2797b(int i) {
        Player.getInstance().m2620b(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public int m2780g() {
        return Player.getInstance().m2598k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m2799a(int[] iArr, int i) {
        return Player.getInstance().m2622a(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m2798a(short[] sArr, int i) {
        return Player.getInstance().m2621a(sArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public String m2779h() {
        return Player.getInstance().m2596l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public void m2778i() {
        Player.getInstance().m2588p();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public void m2777j() {
        Player.getInstance().m2587q();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2801a(boolean z) {
        Player.getInstance().m2626a(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2802a(String str, boolean z) {
        Player.getInstance().m2623a(str, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2805a(String str) {
        this.f6997f.m2418b(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2791b(String str) {
        this.f6997f.m2428a(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2806a(DownloadTaskInfo downloadTaskInfo) {
        this.f6997f.m2435a(downloadTaskInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public DownloadTaskInfo m2793b(DownloadTaskInfo downloadTaskInfo) {
        return this.f6997f.m2421b(downloadTaskInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public DownloadTaskInfo m2786c(DownloadTaskInfo downloadTaskInfo) {
        return this.f6997f.m2415c(downloadTaskInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public int m2783d(DownloadTaskInfo downloadTaskInfo) {
        return this.f6997f.m2413d(downloadTaskInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public List<DownloadTaskInfo> m2800a(int[] iArr) {
        return this.f6997f.m2425a(iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2796b(long j) {
        LogUtils.error("SupportService", "startSleepMode");
        this.f7000i.removeCallbacks(this.f6999h);
        this.f7000i.postDelayed(this.f6999h, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: k */
    public void m2776k() {
        LogUtils.error("SupportService", "stopSleepMode");
        this.f7000i.removeCallbacks(this.f6999h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: l */
    public String m2775l() {
        return Player.getInstance().m2590o();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: m */
    public MediaItem m2774m() {
        return Player.getInstance().getMediaItem();
    }

    /* renamed from: a */
    public void m2804a(String str, int i, int i2, String str2, String str3, boolean z) {
        LogUtils.debug("SupportService", "unicom flow Support Service bindProxy:" + z);
        this.f6996e = z;
        if (z) {
            HttpRequest.m8715a(str, i, str2, str3);
        }
        HttpRequest.m8705a(z);
        Player.getInstance().m2625a(str, i2, UnicomFlowUtil.m3953a(str2, str3), z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: n */
    public long m2773n() {
        long m8718a = f6992a + HttpRequest.getContentLength() ;
        LogUtils.debug("SupportService", "unicom flow support http proxy:" + HttpRequest.isProxy() + "  service proxy:" + this.f6996e);
        LogUtils.debug("SupportService", "unicom flow get flow size :" + m8718a);
        return m8718a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m2788c(long j) {
        f6992a = j;
        HttpRequest.setContentLength(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public void m2769r() {
        if (Player.getInstance().m2604h() == PlayStatus.STATUS_PAUSED && !Preferences.m2986aU()) {
            m4620a(15121720);
            return;
        }
        MediaItem m2606g = Player.getInstance().getMediaItem();
        if (m2606g != null) {
            m4619a(15121720, m2803a((String) null, m2606g));
        }
    }

    /* renamed from: s */
    private void m2768s() {
        MediaItem m2606g = Player.getInstance().getMediaItem();
        if (m2606g != null) {
            String title = m2606g.getTitle();
            String artist = m2606g.getArtist();
            if (TTTextUtils.isValidateMediaString(artist)) {
                title = artist + " - " + title;
            }
            m4619a(15121720, m2803a(title, m2606g));
        }
    }

    /* renamed from: a */
    private Notification m2803a(String str, MediaItem mediaItem) {
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        PendingIntent pendingIntent3;
        PendingIntent pendingIntent4;
        Bitmap bitmap;
        Throwable th;
        DebugUtils.m8426a(mediaItem, "mediaItem");
        if (SDKVersionUtils.m8368f()) {
            pendingIntent4 = PendingIntent.getService(BaseApplication.getApplication(), 0, new Intent(BaseApplication.getApplication(), SupportService.class).setAction("previous_command").putExtra("command", "previous_command").putExtra("key_origin", "notification"), PendingIntent.FLAG_IMMUTABLE);
            pendingIntent3 = PendingIntent.getService(BaseApplication.getApplication(), 0, new Intent(BaseApplication.getApplication(), SupportService.class).setAction("play_pause_command").putExtra("command", "play_pause_command").putExtra("key_origin", "notification"), PendingIntent.FLAG_IMMUTABLE);
            pendingIntent2 = PendingIntent.getService(BaseApplication.getApplication(), 0, new Intent(BaseApplication.getApplication(), SupportService.class).setAction("next_command").putExtra("command", "next_command").putExtra("key_origin", "notification"), PendingIntent.FLAG_IMMUTABLE);
            pendingIntent = PendingIntent.getService(BaseApplication.getApplication(), 0, new Intent(BaseApplication.getApplication(), SupportService.class).setAction("exit_command").putExtra("command", "exit_command").putExtra("key_origin", "notification"), PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = null;
            pendingIntent2 = null;
            pendingIntent3 = null;
            pendingIntent4 = null;
        }
        String substring = SearchManager.m2232a().m2219c().startsWith(mediaItem.getID()) ? SearchManager.m2232a().m2219c().substring(mediaItem.getID().length()) : null;
        try {
            Bitmap m8445a = StringUtils.isEmpty(substring) ? null : BitmapUtils.m8445a(substring, DisplayUtils.m7229a(128));
            if (m8445a == null) {
                try {
                    m8445a = BitmapFactory.decodeResource(getResources(), SDKVersionUtils.checkVersionThanAndroid11() ? R.drawable.img_notification_artist_default_hashoneycomb : R.drawable.img_notification_artist_default, null);
                } catch (Throwable th2) {
                    bitmap = m8445a;
                    th = th2;
                    th.printStackTrace();
                    Notification m4692a = NotificationUtils.m4692a(BaseApplication.getApplication()
                            , Player.getInstance().m2604h(), mediaItem.getTitle()
                            , TTTextUtils.validateString(getApplicationContext(), mediaItem.getArtist())
                            , TTTextUtils.validateString(getApplicationContext(), mediaItem.getAlbum())
                            , bitmap, PendingIntent.getActivity(this, 0
                                    , new Intent(Action.START_ENTRY).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0)
                            , pendingIntent4, pendingIntent3, pendingIntent2, pendingIntent);
                    m4692a.flags |= 32;
                    m4692a.tickerText = str;
                    return m4692a;
                }
            }
            bitmap = m8445a;
        } catch (Throwable th3) {
            bitmap = null;
            th = th3;
        }
        Notification notification = NotificationUtils.m4692a(BaseApplication.getApplication(), Player.getInstance().m2604h(), mediaItem.getTitle(), TTTextUtils.validateString(getApplicationContext(), mediaItem.getArtist()), TTTextUtils.validateString(getApplicationContext(), mediaItem.getAlbum()), bitmap, PendingIntent.getActivity(this, 0, new Intent(Action.START_ENTRY).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                , PendingIntent.FLAG_IMMUTABLE), pendingIntent4, pendingIntent3, pendingIntent2, pendingIntent);
        notification.flags |= 32;
        notification.tickerText = str;
        return notification;
    }

    /* renamed from: b */
    private void m2790b(String str, MediaItem mediaItem) {
        if (!StringUtils.equals(str, MediaStorage.GROUP_ID_RECENTLY_PLAY) && mediaItem != null) {
            List<String> queryMediaIDs = MediaStorage.queryMediaIDs(this, MediaStorage.GROUP_ID_RECENTLY_PLAY, MediaStorage.MEDIA_ORDER_BY_PLAY_TIME_DESC);
            if (queryMediaIDs.size() >= 100) {
                MediaStorage.deleteMediaItem(this, MediaStorage.GROUP_ID_RECENTLY_PLAY, queryMediaIDs.get(queryMediaIDs.size() - 1));
            }
            mediaItem.setDateLastPlayInMills(Long.valueOf(System.currentTimeMillis()));
            if (!StringUtils.equals(mediaItem.getGroupID(), MediaStorage.GROUP_ID_ONLINE_TEMPORARY)) {
                MediaStorage.updateMediaItem(this, mediaItem);
            }
            MediaStorage.deleteMediaItem(this, MediaStorage.GROUP_ID_RECENTLY_PLAY, mediaItem.getID());
            MediaStorage.insertMediaItem(this, MediaStorage.GROUP_ID_RECENTLY_PLAY, mediaItem);
        }
    }
}
