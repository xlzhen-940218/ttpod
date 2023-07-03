package com.sds.android.ttpod.framework.support.p134a;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.api.OnlineMediaItemAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;

import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.modules.skin.p130c.DateTimeUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.monitor.CallMonitor;
import com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor;
import com.sds.android.ttpod.framework.support.monitor.LockScreenMonitor;
import com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver;
import com.sds.android.ttpod.media.audiofx.EffectDetect;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.MediaPlayerNotificationInfo;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.player.TTAudioTrack;
import java.io.File;

/* renamed from: com.sds.android.ttpod.framework.support.a.f */
/* loaded from: classes.dex */
public final class Player implements HeadsetPlugMonitor.InterfaceC2081a, LockScreenMonitor.InterfaceC2082a, CallMonitor.InterfaceC2086a {

    /* renamed from: e */
    private static final String CACHE_MEDIA_PATH = TTPodConfig.getCacheMediaPath();

    /* renamed from: s */
    private static Player instance = null;

    /* renamed from: a */
    private InterfaceC2055b f7088a;

    /* renamed from: b */
    private InterfaceC2056c f7089b;

    /* renamed from: c */
    private InterfaceC2054a f7090c;

    /* renamed from: d */
    private long currentTimeMillis;

    /* renamed from: i */
    private HeadsetPlugMonitor headsetPlugMonitor;

    /* renamed from: j */
    private CallMonitor callMonitor;

    /* renamed from: k */
    private LockScreenMonitor lockScreenMonitor;

    /* renamed from: l */
    private PlayStatus playStatus;

    /* renamed from: m */
    private MediaPlayerProxy mediaPlayerProxy;

    /* renamed from: n */
    private PowerManager.WakeLock wakeLock;

    /* renamed from: o */
    private MediaSelector mediaSelector;

    /* renamed from: p */
    private AudioEffectLoader audioEffectLoader;

    /* renamed from: q */
    private int f7103q;

    /* renamed from: r */
    private int f7104r;

    /* renamed from: f */
    private String f7092f = null;

    /* renamed from: g */
    private String f7093g = null;

    /* renamed from: h */
    private String cacheMediaPath = "";

    /* renamed from: t */
    private boolean f7105t = false;

    /* renamed from: u */
    private boolean f7106u = false;

    /* renamed from: v */
    private Context context = null;

    /* renamed from: w */
    private MediaPlayerProxy.InterfaceC2042a f7108w = new MediaPlayerProxy.InterfaceC2042a() { // from class: com.sds.android.ttpod.framework.support.a.f.2
        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2042a
        /* renamed from: a */
        public void mo2577a(long j) {
            if (Player.this.f7090c != null) {
                Player.this.f7090c.mo2560a(j);
            }
        }
    };

    /* renamed from: x */
    private MediaPlayerProxy.InterfaceC2044c f7109x = new MediaPlayerProxy.InterfaceC2044c() { // from class: com.sds.android.ttpod.framework.support.a.f.3

        /* renamed from: b */
        private RequestCallback<OnlineMediaItemsResult> f7115b = new RequestCallback<OnlineMediaItemsResult>() { // from class: com.sds.android.ttpod.framework.support.a.f.3.1
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(OnlineMediaItemsResult onlineMediaItemsResult) {
                if (onlineMediaItemsResult.getDataList().size() > 0) {
                    OnlineMediaItem onlineMediaItem = onlineMediaItemsResult.getDataList().get(0);
                    MediaItem m4716a = MediaItemUtils.m4716a(onlineMediaItem);
                    m2571a(m4716a.getExtra());
                    OnlineMediaItem.Url m4689a = OnlineMediaItemUtils.m4689a(onlineMediaItem, EnvironmentUtils.DeviceConfig.hasNetwork());
                    if (m4689a != null) {
                        try {
                            Player.this.mediaPlayerProxy.m2707b();
                            Player.this.mediaPlayerProxy.m2711a(m4689a.getUrl(), TTPodConfig.getAudioTmp(), m4716a.getSongID());
                        } catch (Exception e) {
                            Player.this.processPlayError(-100);
                        }
                    }
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OnlineMediaItemsResult onlineMediaItemsResult) {
                Player.this.processPlayError(-100);
            }
        };

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: a */
        public void mo2576a() {
            Player.this.f7103q = 0;
            MediaItem m2606g = Player.this.getMediaItem();
            if (m2606g != null && m2606g.getStartTime().intValue() != 0) {
                Player.this.mediaPlayerProxy.m2726a(m2606g.getStartTime().intValue(), m2606g.getStartTime().intValue() + m2606g.getDuration().intValue());
            }
            int m2640H = Player.this.getMediaItemId();
            if (m2640H != 0) {
                Player.this.mediaPlayerProxy.m2727a(m2640H);
            }
            if (!EffectDetect.usingAudioPlus()) {
                boolean m2743c = Player.this.audioEffectLoader.m2743c();
                Player.this.audioEffectLoader.m2751a(false);
                Player.this.audioEffectLoader.m2754a((Boolean) false);
                Player.this.audioEffectLoader.m2746b((Boolean) false);
                Player.this.audioEffectLoader.m2755a(m2606g, 0);
                Player.this.audioEffectLoader.m2754a(Boolean.valueOf(m2743c));
            } else {
                int audioSessionId = TTAudioTrack.audioSessionId();
                Preferences.m2829t(audioSessionId);
                Intent intent = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
                intent.putExtra("android.media.extra.AUDIO_SESSION", audioSessionId);
                intent.putExtra("android.media.extra.PACKAGE_NAME", Player.this.context.getPackageName());
                Player.this.context.sendBroadcast(intent);
            }
            if (Player.this.callMonitor.m2239a(Player.this.context)) {
                Player.this.mediaPlayerProxy.m2698d();
                return;
            }
            Player.this.playStatus = PlayStatus.STATUS_PLAYING;
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: b */
        public void mo2570b() {
            Preferences.getPositionInfo("");
            Player.this.m2637K();
            Player.this.m2583u();
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: c */
        public void mo2569c() {
            Player.this.m2637K();
            Player.this.m2582v();
            Player.this.m2639I();
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: d */
        public void mo2568d() {
            //new //SSystemEvent("SYS_PLAY", "next").append("type", "system").post();
            Player.this.m2637K();
            Player.this.mediaSelector.m2652e();
            Player.this.m2638J();
            Player.this.m2592n();
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: a */
        public void mo2574a(int i, int i2, MediaPlayerNotificationInfo mediaPlayerNotificationInfo) {
            LogUtils.error("Player", "onError:" + i);
            MediaItem m2606g = Player.this.getMediaItem();
            DebugUtils.m8426a(m2606g, "playingMediaItem");
            if (m2606g != null) {
                if (!m2606g.isOnline() || !StringUtils.isEmpty(m2606g.getLocalDataSource())) {
                    Player.this.processPlayError(i);
                    //ErrorStatistic.m5244a(i);
                } else if (EnvironmentUtils.DeviceConfig.hasNetwork() == -1) {
                    Player.this.processPlayError(-34);
                } else if (!m2575a(i) || Player.this.f7104r >= 5) {
                    String str = "";
                    String str2 = "";
                    if (mediaPlayerNotificationInfo != null) {
                        str = mediaPlayerNotificationInfo.getURL();
                        str2 = mediaPlayerNotificationInfo.getIP();
                    }
                    if (-12 == i) {
                        Player.this.processPlayError(i);
                        long m8469a = EnvironmentUtils.C0605d.m8469a(new File(Player.CACHE_MEDIA_PATH));
                        //ErrorStatistic.m5240a(str, m8469a, m2564h());
                        //new //SSystemEvent("SYS_PLAY", "error").append("uri", str).append("storage_state", m2564h()).append("usable_space", Long.valueOf(m8469a)).append("error_code", Integer.valueOf(i)).post();
                        return;
                    }
                    if (-34 != i) {
                        i2 = i;
                    }
                    Player.this.processPlayError(i2);
                    //ErrorStatistic.m5241a(str, i2, str2);
                } else {
                    m2572a(m2606g.getSongID());
                }
            }
        }

        /* renamed from: h */
        private String m2564h() {
            if (!EnvironmentUtils.C0605d.m8472a() && !EnvironmentUtils.C0605d.m8468a(EnvironmentUtils.C0605d.getSdcardPath())) {
                return "storage does not exist";
            }
            if (!FileUtils.isDir(TTPodConfig.getTTPodPath())) {
                return "/sdcard/ttpod not exist";
            }
            if (FileUtils.isDir(TTPodConfig.getCachePath())) {
                if (!FileUtils.isDir(Player.CACHE_MEDIA_PATH)) {
                    return "/sdcard/ttpod/cache/media not exist";
                }
                return "/sdcard/ttpod/cache/media exist";
            }
            return "/sdcard/ttpod/cache not exist";
        }

        /* renamed from: a */
        private void m2572a(Long l) {
            Player.m2591n(Player.this);
            LogUtils.error("Player", "onError: request Url:" + Player.this.f7104r + " songId:" + l);
            if (Player.this.f7104r < 4) {
                OnlineMediaItemAPI.m8868a(l).m8544a(this.f7115b);
            } else {
                OnlineMediaItemAPI.m8865b(l).m8544a(this.f7115b);
            }
        }

        /* renamed from: a */
        private boolean m2575a(int i) {
            return i == -34 || i == -21 || i == -6 || i == -15 || i == -36 || i == -57 || i == -54 || i == -18;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m2571a(String str) {
            try {
                MediaItem m2655b = Player.this.mediaSelector.getMediaItem();
                if (m2655b != null && !StringUtils.equals(str, m2655b.getExtra())) {
                    m2655b.setExtra(str);
                    MediaStorage.updateMediaItem(Player.this.context, m2655b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: e */
        public void mo2567e() {
            if (System.currentTimeMillis() - Player.this.currentTimeMillis > 4000) {
                Player.this.currentTimeMillis = System.currentTimeMillis();
                Player.this.context.sendBroadcast(new Intent(Action.PLAY_BUFFERING_STARTED));
            }
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: f */
        public void mo2566f() {
            Player.this.context.sendBroadcast(new Intent(Action.PLAY_BUFFERING_DONE));
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: g */
        public void mo2565g() {
            MediaItem m2606g;
            String m5310C = TTPodConfig.getAudioTmp();
            if (FileUtils.m8419a(m5310C) && (m2606g = Player.this.getMediaItem()) != null && m2606g.isOnline()) {
                FileUtils.m8413b(TTPodConfig.getSongIdPath(m2606g.getSongID()), m5310C);
            }
        }
    };

    /* renamed from: y */
    private MediaPlayerProxy.InterfaceC2043b f7110y = new MediaPlayerProxy.InterfaceC2043b() { // from class: com.sds.android.ttpod.framework.support.a.f.4
        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2043b
        /* renamed from: a */
        public void mo2561a(int i) {
            MediaItem m2606g = Player.this.getMediaItem();
            if (m2606g != null && m2606g.getStartTime() != null && m2606g.getStartTime().intValue() == 0) {
                Player.this.m2610e(i);
            }
        }
    };

    /* compiled from: Player.java */
    /* renamed from: com.sds.android.ttpod.framework.support.a.f$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2054a {
        /* renamed from: a */
        void mo2560a(long j);
    }

    /* compiled from: Player.java */
    /* renamed from: com.sds.android.ttpod.framework.support.a.f$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2055b {
        /* renamed from: a */
        void mo2559a(MediaItem mediaItem);
    }

    /* compiled from: Player.java */
    /* renamed from: com.sds.android.ttpod.framework.support.a.f$c */
    /* loaded from: classes.dex */
    public interface InterfaceC2056c {
        /* renamed from: a */
        void mo2558a();
    }

    /* renamed from: n */
    static /* synthetic */ int m2591n(Player player) {
        int i = player.f7104r;
        player.f7104r = i + 1;
        return i;
    }

    /* renamed from: a */
    public void m2632a(InterfaceC2056c interfaceC2056c) {
        this.f7089b = interfaceC2056c;
    }

    /* renamed from: a */
    public void m2633a(InterfaceC2055b interfaceC2055b) {
        this.f7088a = interfaceC2055b;
    }

    /* renamed from: a */
    public void m2634a(InterfaceC2054a interfaceC2054a) {
        this.f7090c = interfaceC2054a;
    }

    /* renamed from: a */
    public void m2625a(String str, int i, String str2, boolean z) {
        LogUtils.debug("Player", "unicom flow setProxyServerParameter ip:" + str + " port:" + i + " authenkey:" + str2 + " useProxy:" + z);
        this.mediaPlayerProxy.m2713a(str, i, str2, z);
    }

    /* renamed from: a */
    public boolean m2635a(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        MediaItem m2655b = this.mediaSelector.getMediaItem();
        if ("start_command".equals(stringExtra)) {
            if (m2655b != null) {
                if (m2655b.isOnline()) {
                    //OnlineMediaStatistic.m5041b(m2655b.getSongID().longValue(), 1);
                }
                m2638J();
                m2592n();
            } else {
                m2642F();
            }
            this.audioEffectLoader.m2755a(this.mediaSelector.getMediaItem(), 0);
        } else if ("previous_command".equals(stringExtra)) {
            if (m2655b != null && m2655b.isOnline()) {
                //OnlineMediaStatistic.m5041b(m2655b.getSongID().longValue(), 1);
            }
            m2643E();
        } else if ("next_command".equals(stringExtra)) {
            if (m2655b != null && m2655b.isOnline()) {
                //OnlineMediaStatistic.m5041b(m2655b.getSongID().longValue(), 1);
            }
            m2642F();
        } else if ("pause_command".equals(stringExtra)) {
            m2646B();
        } else if ("resume_command".equals(stringExtra)) {
            m2644D();
        } else if ("play_command".equals(stringExtra)) {
            this.f7103q = 0;
            MediaItem mediaItem = (MediaItem) intent.getExtras().get("mediaItem");
            if (mediaItem != null) {
                m2641G();
                this.mediaSelector.m2659a(mediaItem);
                m2638J();
            }
            if (!StringUtils.isEmpty(intent.getStringExtra("group")) || !StringUtils.isEmpty(intent.getStringExtra("media_source"))) {
                this.mediaSelector.m2657a(intent.getStringExtra("group"), intent.getStringExtra("media_source"));
            }
            m2592n();
        } else if ("sync_command".equals(stringExtra)) {
            String stringExtra2 = intent.getStringExtra("group");
            String stringExtra3 = intent.getStringExtra("media_source");
            if (StringUtils.equals(stringExtra2, MediaStorage.GROUP_ID_FAV) || stringExtra2.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
                TTPodUser m2954aq = Preferences.m2954aq();
                EnvironmentUtils.UUIDConfig.m8498a(m2954aq != null ? m2954aq.getUserId() : 0L);
            }
            MediaItem mediaItem2 = (MediaItem) intent.getExtras().get("mediaItem");
            if ((mediaItem2 != null && !mediaItem2.equals(this.mediaSelector.getMediaItem())) || !StringUtils.equals(Preferences.getLocalGroupId(), stringExtra2)) {
                this.mediaSelector.m2659a(mediaItem2);
                m2638J();
            }
            this.mediaSelector.m2657a(stringExtra2, stringExtra3 == null ? Preferences.getMediaId() : stringExtra3);
        } else if ("play_pause_command".equals(stringExtra)) {
            m2645C();
        } else if ("stop_command".equals(stringExtra)) {
            m2594m();
        } else if ("reload_all_audioeffect_command".equals(stringExtra)) {
            m2581w();
        } else if ("reload_audioeffect_command".equals(stringExtra)) {
            m2616c(intent.getIntExtra("effect_type", 0));
        } else if (!"reset_audioeffect_command".equals(stringExtra)) {
            return false;
        } else {
            m2580x();
        }
        return true;
    }

    /* renamed from: a */
    public void m2624a(String str, String str2) {
        if (StringUtils.equals(str, MediaStorage.GROUP_ID_FAV) || str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
            TTPodUser m2954aq = Preferences.m2954aq();
            EnvironmentUtils.UUIDConfig.m8498a(m2954aq != null ? m2954aq.getUserId() : 0L);
        }
        if (!StringUtils.equals(Preferences.getLocalGroupId(), str)) {
            m2638J();
        }
        MediaSelector mediaSelector = this.mediaSelector;
        if (str2 == null) {
            str2 = Preferences.getMediaId();
        }
        mediaSelector.m2657a(str, str2);
    }

    /* renamed from: s */
    private void m2585s() {
        this.headsetPlugMonitor = new HeadsetPlugMonitor();
        this.headsetPlugMonitor.m2260a(this);
        this.context.registerReceiver(this.headsetPlugMonitor, HeadsetPlugMonitor.m2261a());
        this.callMonitor = new CallMonitor();
        this.callMonitor.m2238a(this.context, this);
        this.lockScreenMonitor = new LockScreenMonitor(this);
        this.context.registerReceiver(this.lockScreenMonitor, LockScreenMonitor.m2256a());
        MediaButtonReceiver.m2252b();
    }

    /* renamed from: t */
    private void m2584t() {
        this.headsetPlugMonitor.m2260a((HeadsetPlugMonitor.InterfaceC2081a) null);
        this.context.unregisterReceiver(this.headsetPlugMonitor);
        this.callMonitor.m2238a(this.context, (CallMonitor.InterfaceC2086a) null);
        this.context.unregisterReceiver(this.lockScreenMonitor);
    }

    /* renamed from: e */
    public static Player getInstance() {
        synchronized (Player.class) {
            if (instance == null) {
                instance = new Player();
            }
        }
        return instance;
    }

    /* renamed from: a */
    public void m2636a(Context context) {
        this.context = context;
        this.mediaPlayerProxy = new MediaPlayerProxy(context);
        this.mediaPlayerProxy.m2721a(this.f7109x);
        this.mediaPlayerProxy.m2723a(this.f7108w);
        this.mediaPlayerProxy.m2722a(this.f7110y);
        this.mediaSelector = new MediaSelector(context);
        this.audioEffectLoader = new AudioEffectLoader(context, this.mediaPlayerProxy);
        this.audioEffectLoader.m2755a(getMediaItem(), 0);
        m2585s();
    }

    /* renamed from: f */
    public void m2608f() {
        this.mediaPlayerProxy.m2721a((MediaPlayerProxy.InterfaceC2044c) null);
        this.mediaPlayerProxy.m2723a((MediaPlayerProxy.InterfaceC2042a) null);
        this.mediaPlayerProxy.m2722a((MediaPlayerProxy.InterfaceC2043b) null);
        this.mediaPlayerProxy.m2681i();
        if (EffectDetect.usingAudioPlus()) {
            Intent intent = new Intent("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
            intent.putExtra("android.media.extra.AUDIO_SESSION", TTAudioTrack.audioSessionId());
            intent.putExtra("android.media.extra.PACKAGE_NAME", this.context.getPackageName());
            this.context.sendBroadcast(intent);
        }
        m2584t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public void m2583u() {
        if (this.wakeLock == null) {
            this.wakeLock = ((PowerManager) this.context.getSystemService(Context.POWER_SERVICE)).newWakeLock(1, getClass().getName());
            this.wakeLock.acquire();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public void m2582v() {
        if (this.wakeLock != null) {
            this.wakeLock.release();
            this.wakeLock = null;
        }
    }

    /* renamed from: g */
    public MediaItem getMediaItem() {
        return this.mediaSelector.getMediaItem();
    }

    /* renamed from: h */
    public PlayStatus m2604h() {
        return this.mediaPlayerProxy.getPlayStatus();
    }

    /* renamed from: i */
    public int m2602i() {
        return this.mediaPlayerProxy.getPlayStatus() == PlayStatus.STATUS_STOPPED ? getMediaItemId() : this.mediaPlayerProxy.m2687g();
    }

    /* renamed from: j */
    public float m2600j() {
        return this.mediaPlayerProxy.m2684h();
    }

    /* renamed from: b */
    public void m2620b(int i) {
        if (this.mediaPlayerProxy.getPlayStatus() == PlayStatus.STATUS_STOPPED && getMediaItem() != null) {
            Preferences.getPositionInfo(Preferences.getMediaId() + File.pathSeparator + i);
        } else {
            this.mediaPlayerProxy.m2727a(i);
        }
    }

    /* renamed from: k */
    public int m2598k() {
        return this.mediaSelector.m2662a();
    }

    /* renamed from: a */
    public boolean m2622a(int[] iArr, int i) {
        return this.mediaPlayerProxy.m2709a(iArr, i);
    }

    /* renamed from: a */
    public boolean m2621a(short[] sArr, int i) {
        return this.mediaPlayerProxy.m2708a(sArr, i);
    }

    /* renamed from: l */
    public String m2596l() {
        return this.audioEffectLoader.m2740d();
    }

    /* renamed from: w */
    private void m2581w() {
        this.mediaSelector.getMediaItem();
        this.audioEffectLoader.m2755a((MediaItem) null, 8);
    }

    /* renamed from: x */
    private void m2580x() {
        this.mediaSelector.getMediaItem();
        this.audioEffectLoader.m2738e();
    }

    /* renamed from: c */
    private void m2616c(int i) {
        this.mediaSelector.getMediaItem();
        this.audioEffectLoader.m2755a((MediaItem) null, i);
    }

    /* renamed from: m */
    public void m2594m() {
        m2639I();
        this.mediaPlayerProxy.m2707b();
        m2637K();
    }

    /* renamed from: y */
    private boolean m2579y() {
        return EnvironmentUtils.C0605d.m8469a(new File(EnvironmentUtils.C0605d.getSdcardPath())) < 3145728;
    }

    /* renamed from: z */
    private void m2578z() {
        if (this.f7093g == null) {
            this.f7093g = this.f7092f + File.separator + CACHE_MEDIA_PATH.substring(CACHE_MEDIA_PATH.indexOf("ttpod"));
            FileUtils.createFolder(this.f7093g);
        }
    }

    /* renamed from: n */
    public void m2592n() {
        MediaItem queryMediaItemBySongID;
        MediaButtonReceiver.m2254a();
        this.f7104r = 0;
        try {
            this.mediaPlayerProxy.m2707b();
            MediaItem m2606g = getMediaItem();
            if (m2606g != null) {
                String localDataSource = m2606g.getLocalDataSource();
                if (StringUtils.isEmpty(localDataSource) && m2606g.getSongID().longValue() > 0 && (queryMediaItemBySongID = MediaStorage.queryMediaItemBySongID(this.context, MediaStorage.GROUP_ID_ALL_LOCAL, m2606g.getSongID())) != null) {
                    localDataSource = queryMediaItemBySongID.getLocalDataSource();
                }
                if (FileUtils.isFile(localDataSource)) {
                    this.mediaPlayerProxy.m2712a(localDataSource, (Long) null);
                    //LocalMediaStatistic.m5199a(localDataSource, System.nanoTime());
                    //new //SSystemEvent("SYS_PLAY", "start").append("play_type", "local").append("song_id", localDataSource).post();
                } else {
                    if (m2606g.isOnline()) {
                        m2627a(m2606g);
                    } else if (m2606g.getID().equals(SecurityUtils.MD5Hex.stringToMD5Hex(m2606g.getExtra()))) {
                        this.mediaPlayerProxy.m2711a(m2606g.getExtra(), TTPodConfig.getAudioTmp(), (Long) null);
                    } else {
                        processPlayError(-99);
                    }
                    final String m2730a = this.mediaPlayerProxy.m2730a();
                    TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.support.a.f.1
                        @Override // java.lang.Runnable
                        public void run() {
                            long m8405g = FileUtils.m8405g(Player.CACHE_MEDIA_PATH);
                            if (m8405g > 209715200 || EnvironmentUtils.C0605d.m8469a(new File(Player.CACHE_MEDIA_PATH)) <= 52428800) {
                                long j = m8405g - 41943040;
                                long j2 = j > 0 ? j : 0L;
                                String str = Player.CACHE_MEDIA_PATH;
                                if (j2 <= 209715200) {
                                    j2 = 209715200;
                                }
                                FileUtils.m8417a(str, j2, new String[]{TTPodConfig.getAudioTmp(), m2730a});
                            }
                        }
                    });
                }
            } else {
                m2582v();
                LogUtils.debug("Player", "PLAYLIST_IS_EMPTY");
                this.context.sendBroadcast(new Intent(Action.PLAYLIST_IS_EMPTY));
            }
            this.f7105t = true;
        } catch (Exception e) {
            LogUtils.error("Player", "processPlayError above MAX_ERROR_COUNT");
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m2627a(MediaItem mediaItem) {
        String str;
        long longValue = mediaItem.getSongID().longValue();
        int m2598k = m2598k() + 1;
        //SSystemEvent //SSystemEvent = //new //SSystemEvent("SYS_PLAY", "start");
        //SSystemEvent.append("song_id", Long.valueOf(longValue));
        String m2926bc = Preferences.getOnlineMediaListGroupName();
        if (!StringUtils.isEmpty(m2926bc) && m2926bc.startsWith("音乐圈_")) {
            //SSystemEvent.append(BaseFragment.KEY_SONG_LIST_ID, m2926bc.substring("音乐圈_".length()));
        }
        //OnlineMediaStatistic.m5050a(longValue, System.nanoTime());
        //OnlineMediaStatistic.m5036c(longValue, m2598k);
        String str2 = Preferences.getCacheMediaFolderPath() + File.separator + mediaItem.getSongID();
        if (FileUtils.isFile(str2)) {
            new File(str2).setLastModified(System.currentTimeMillis());
            //OnlineMediaStatistic.m5039b(longValue, true);
            //SSystemEvent.append("play_type", "cache").post();
            try {
                this.mediaPlayerProxy.m2712a(str2, mediaItem.getSongID());
                return;
            } catch (Exception e) {
                LogUtils.error("Player", "processPlayError above MAX_ERROR_COUNT");
                e.printStackTrace();
                return;
            }
        }
        if (m2579y()) {
            this.f7092f = EnvironmentUtils.C0605d.m8460d(this.context);
            if (SDKVersionUtils.sdkThan19()) {
                this.f7092f = EnvironmentUtils.C0605d.m8470a(this.context, EnvironmentUtils.C0605d.EnumC0607a.SECOND_SD_CARD);
            }
            if (StringUtils.isEmpty(this.f7092f)) {
                this.context.sendBroadcast(new Intent(Action.PLAY_STATUS_CHANGED).putExtra("play_status", PlayStatus.STATUS_ERROR.ordinal()).putExtra("play_error_code", -5000));
                return;
            } else {
                m2578z();
                str = this.f7093g;
            }
        } else {
            str = CACHE_MEDIA_PATH;
        }
        if (!str.equals(this.cacheMediaPath)) {
            this.cacheMediaPath = str;
            Preferences.setCacheMediaFolderPath(str);
        }
        //OnlineMediaStatistic.m5039b(longValue, false);
        //SSystemEvent.append("play_type", "online").post();
        OnlineMediaItem.Url m4689a = OnlineMediaItemUtils.m4689a((OnlineMediaItem) JSONUtils.fromJson(mediaItem.getExtra(), OnlineMediaItem.class), EnvironmentUtils.DeviceConfig.hasNetwork());
        if (m4689a != null) {
            try {
                this.mediaPlayerProxy.m2711a(m4689a.getUrl(), TTPodConfig.getAudioTmp(), mediaItem.getSongID());
            } catch (Exception e2) {
                LogUtils.error("Player", "processPlayError above MAX_ERROR_COUNT");
                e2.printStackTrace();
            }
            m2610e((int) DateTimeUtils.m3746a(m4689a.getDuration()));
            return;
        }
        processPlayError(-100);
    }

    /* renamed from: A */
    private void m2647A() {
        try {
            MediaItem m2655b = this.mediaSelector.getMediaItem();
            if (!m2655b.isOnline()) {
                m2655b.setErrorStatus(1);
                MediaStorage.updateMediaItem(this.context, m2655b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void processPlayError(int i) {
        LogUtils.error("Player", "processPlayError");
        if (this.f7103q < 5) {
            this.f7103q++;
            //new //SSystemEvent("SYS_PLAY", "next").append("type", "system").post();
            this.context.startService(new Intent(this.context, SupportService.class).putExtra("command", "next_command"));
        } else {
            m2582v();
            LogUtils.error("Player", "processPlayError above MAX_ERROR_COUNT");
        }
        Preferences.getPositionInfo("");
        m2647A();
        this.context.sendBroadcast(new Intent(Action.PLAY_STATUS_CHANGED).putExtra("play_status", PlayStatus.STATUS_ERROR.ordinal()).putExtra("play_error_code", i));
    }

    /* renamed from: B */
    private void m2646B() {
        if (this.f7105t) {
            this.mediaPlayerProxy.m2702c();
        }
    }

    /* renamed from: C */
    private void m2645C() {
        PlayStatus m2690f = this.mediaPlayerProxy.getPlayStatus();
        if (m2690f == PlayStatus.STATUS_PLAYING) {
            m2646B();
        } else if (m2690f == PlayStatus.STATUS_PAUSED) {
            m2644D();
        } else if (getMediaItem() == null) {
            m2642F();
        } else {
            m2592n();
        }
    }

    /* renamed from: D */
    private void m2644D() {
        this.f7106u = false;
        this.mediaPlayerProxy.m2694e();
    }

    /* renamed from: E */
    private void m2643E() {
        m2641G();
        this.mediaSelector.m2654c();
        m2638J();
        m2592n();
    }

    /* renamed from: F */
    private void m2642F() {
        m2641G();
        this.mediaSelector.m2653d();
        m2638J();
        m2592n();
    }

    /* renamed from: G */
    private void m2641G() {
        if (this.mediaSelector.getMediaItem() != null) {
            Preferences.getPositionInfo("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public int getMediaItemId() {
        if (getMediaItem() != null) {
            String positionInfo = Preferences.getPositionInfo();
            String idPath = getMediaItem().getID() + File.pathSeparator;
            if (StringUtils.isEmpty(positionInfo) || !positionInfo.startsWith(idPath)) {
                return 0;
            }
            try {
                return Integer.valueOf(positionInfo.substring(idPath.length())).intValue();
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public void m2639I() {
        if (getMediaItem() != null) {
            Preferences.getPositionInfo(getMediaItem().getID() + File.pathSeparator + m2602i());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m2610e(int i) {
        getMediaItem().setDuration(Integer.valueOf(i));
        Intent intent = new Intent(Action.UPDATE_MEDIA_DURATION);
        intent.putExtra("media_id", getMediaItem().getID());
        intent.putExtra("media_duration", i);
        this.context.sendBroadcast(intent);
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor.InterfaceC2081a
    /* renamed from: c */
    public void mo2258c() {
        //StatisticUtils.m4909a("startup", "headset", "unplugged", 1L);
        if (Preferences.m2834s()) {
            this.f7106u = true;
            m2646B();
        }
        this.context.sendBroadcast(new Intent(Action.PLAY_HEADSET_UNPLUG));
        MediaButtonReceiver.m2254a();
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor.InterfaceC2081a
    /* renamed from: d */
    public void mo2257d() {
        this.f7106u = false;
        //StatisticUtils.m4909a("startup", "headset", "plugged", 1L);
        if (this.callMonitor.m2239a(this.context)) {
            if (Preferences.m2830t()) {
                PlayStatus m2690f = this.mediaPlayerProxy.getPlayStatus();
                if (m2690f == PlayStatus.STATUS_PAUSED) {
                    m2644D();
                } else if (m2690f != PlayStatus.STATUS_PLAYING) {
                    m2592n();
                }
            }
            this.context.sendBroadcast(new Intent(Action.PLAY_HEADSET_PLUG));
        }
        MediaButtonReceiver.m2254a();
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.CallMonitor.InterfaceC2086a
    /* renamed from: a */
    public void mo2234a() {
        if (this.playStatus == PlayStatus.STATUS_PLAYING && !this.f7106u) {
            m2644D();
        }
        this.playStatus = null;
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.CallMonitor.InterfaceC2086a
    /* renamed from: b */
    public void mo2233b() {
        if (this.playStatus == null) {
            this.playStatus = this.mediaPlayerProxy.getPlayStatus();
            if (this.playStatus == PlayStatus.STATUS_PLAYING) {
                m2646B();
            }
        }
    }

    /* renamed from: o */
    public String m2590o() {
        return this.audioEffectLoader.m2763a();
    }

    /* renamed from: p */
    public void m2588p() {
        this.audioEffectLoader.m2755a(getMediaItem(), 0);
    }

    /* renamed from: q */
    public void m2587q() {
        this.audioEffectLoader.m2750b();
    }

    /* renamed from: a */
    public void m2626a(Boolean bool) {
        this.audioEffectLoader.m2754a(bool);
    }

    /* renamed from: a */
    public void m2623a(String str, boolean z) {
        AudioEffectItem audioEffectItem = (AudioEffectItem) JSONUtils.fromJson(str, AudioEffectItem.class);
        if (audioEffectItem != null) {
            this.audioEffectLoader.m2759a(audioEffectItem, z);
        }
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.LockScreenMonitor.InterfaceC2082a
    /* renamed from: a */
    public void mo2255a(int i) {
        this.mediaPlayerProxy.m2685g(i == 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public void m2638J() {
        MediaItem m2606g = getMediaItem();
        if (this.f7088a != null) {
            this.f7088a.mo2559a(m2606g);
        }
        m2617b(m2606g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K */
    public void m2637K() {
        if (this.f7089b != null) {
            this.f7089b.mo2558a();
        }
        m2617b(getMediaItem());
    }

    /* renamed from: b */
    private void m2617b(MediaItem mediaItem) {
        if (SDKVersionUtils.sdkThan11() && mediaItem != null) {
            this.context.sendStickyBroadcast(new Intent("com.android.music.metachanged").putExtra("id", mediaItem.getID()).putExtra("artist", mediaItem.getArtist()).putExtra("album", mediaItem.getAlbum()).putExtra("track", mediaItem.getTitle()).putExtra("playing", this.mediaPlayerProxy.getPlayStatus() == PlayStatus.STATUS_PLAYING).putExtra("isfavorite", mediaItem.getFav()));
        }
    }
}
