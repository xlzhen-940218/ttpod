package com.sds.android.ttpod.framework.support.p134a;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.OnlineMediaItemAPI;
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
    private static final String f7086e = TTPodConfig.m5301g();

    /* renamed from: s */
    private static Player f7087s = null;

    /* renamed from: a */
    private InterfaceC2055b f7088a;

    /* renamed from: b */
    private InterfaceC2056c f7089b;

    /* renamed from: c */
    private InterfaceC2054a f7090c;

    /* renamed from: d */
    private long f7091d;

    /* renamed from: i */
    private HeadsetPlugMonitor f7095i;

    /* renamed from: j */
    private CallMonitor f7096j;

    /* renamed from: k */
    private LockScreenMonitor f7097k;

    /* renamed from: l */
    private PlayStatus f7098l;

    /* renamed from: m */
    private MediaPlayerProxy f7099m;

    /* renamed from: n */
    private PowerManager.WakeLock f7100n;

    /* renamed from: o */
    private MediaSelector f7101o;

    /* renamed from: p */
    private AudioEffectLoader f7102p;

    /* renamed from: q */
    private int f7103q;

    /* renamed from: r */
    private int f7104r;

    /* renamed from: f */
    private String f7092f = null;

    /* renamed from: g */
    private String f7093g = null;

    /* renamed from: h */
    private String f7094h = "";

    /* renamed from: t */
    private boolean f7105t = false;

    /* renamed from: u */
    private boolean f7106u = false;

    /* renamed from: v */
    private Context f7107v = null;

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
                    OnlineMediaItem.Url m4689a = OnlineMediaItemUtils.m4689a(onlineMediaItem, EnvironmentUtils.DeviceConfig.m8476d());
                    if (m4689a != null) {
                        try {
                            Player.this.f7099m.m2707b();
                            Player.this.f7099m.m2711a(m4689a.getUrl(), TTPodConfig.m5310C(), m4716a.getSongID());
                        } catch (Exception e) {
                            Player.this.m2613d(-100);
                        }
                    }
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OnlineMediaItemsResult onlineMediaItemsResult) {
                Player.this.m2613d(-100);
            }
        };

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: a */
        public void mo2576a() {
            Player.this.f7103q = 0;
            MediaItem m2606g = Player.this.m2606g();
            if (m2606g != null && m2606g.getStartTime().intValue() != 0) {
                Player.this.f7099m.m2726a(m2606g.getStartTime().intValue(), m2606g.getStartTime().intValue() + m2606g.getDuration().intValue());
            }
            int m2640H = Player.this.m2640H();
            if (m2640H != 0) {
                Player.this.f7099m.m2727a(m2640H);
            }
            if (!EffectDetect.usingAudioPlus()) {
                boolean m2743c = Player.this.f7102p.m2743c();
                Player.this.f7102p.m2751a(false);
                Player.this.f7102p.m2754a((Boolean) false);
                Player.this.f7102p.m2746b((Boolean) false);
                Player.this.f7102p.m2755a(m2606g, 0);
                Player.this.f7102p.m2754a(Boolean.valueOf(m2743c));
            } else {
                int audioSessionId = TTAudioTrack.audioSessionId();
                Preferences.m2829t(audioSessionId);
                Intent intent = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
                intent.putExtra("android.media.extra.AUDIO_SESSION", audioSessionId);
                intent.putExtra("android.media.extra.PACKAGE_NAME", Player.this.f7107v.getPackageName());
                Player.this.f7107v.sendBroadcast(intent);
            }
            if (Player.this.f7096j.m2239a(Player.this.f7107v)) {
                Player.this.f7099m.m2698d();
                return;
            }
            Player.this.f7098l = PlayStatus.STATUS_PLAYING;
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
            Player.this.f7101o.m2652e();
            Player.this.m2638J();
            Player.this.m2592n();
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: a */
        public void mo2574a(int i, int i2, MediaPlayerNotificationInfo mediaPlayerNotificationInfo) {
            LogUtils.error("Player", "onError:" + i);
            MediaItem m2606g = Player.this.m2606g();
            DebugUtils.m8426a(m2606g, "playingMediaItem");
            if (m2606g != null) {
                if (!m2606g.isOnline() || !StringUtils.isEmpty(m2606g.getLocalDataSource())) {
                    Player.this.m2613d(i);
                    //ErrorStatistic.m5244a(i);
                } else if (EnvironmentUtils.DeviceConfig.m8476d() == -1) {
                    Player.this.m2613d(-34);
                } else if (!m2575a(i) || Player.this.f7104r >= 5) {
                    String str = "";
                    String str2 = "";
                    if (mediaPlayerNotificationInfo != null) {
                        str = mediaPlayerNotificationInfo.getURL();
                        str2 = mediaPlayerNotificationInfo.getIP();
                    }
                    if (-12 == i) {
                        Player.this.m2613d(i);
                        long m8469a = EnvironmentUtils.C0605d.m8469a(new File(Player.f7086e));
                        //ErrorStatistic.m5240a(str, m8469a, m2564h());
                        //new //SSystemEvent("SYS_PLAY", "error").append("uri", str).append("storage_state", m2564h()).append("usable_space", Long.valueOf(m8469a)).append("error_code", Integer.valueOf(i)).post();
                        return;
                    }
                    if (-34 != i) {
                        i2 = i;
                    }
                    Player.this.m2613d(i2);
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
            if (!FileUtils.isDir(TTPodConfig.m5311B())) {
                return "/sdcard/ttpod not exist";
            }
            if (FileUtils.isDir(TTPodConfig.m5304d())) {
                if (!FileUtils.isDir(Player.f7086e)) {
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
                MediaItem m2655b = Player.this.f7101o.m2655b();
                if (m2655b != null && !StringUtils.equals(str, m2655b.getExtra())) {
                    m2655b.setExtra(str);
                    MediaStorage.updateMediaItem(Player.this.f7107v, m2655b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: e */
        public void mo2567e() {
            if (System.currentTimeMillis() - Player.this.f7091d > 4000) {
                Player.this.f7091d = System.currentTimeMillis();
                Player.this.f7107v.sendBroadcast(new Intent(Action.PLAY_BUFFERING_STARTED));
            }
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: f */
        public void mo2566f() {
            Player.this.f7107v.sendBroadcast(new Intent(Action.PLAY_BUFFERING_DONE));
        }

        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2044c
        /* renamed from: g */
        public void mo2565g() {
            MediaItem m2606g;
            String m5310C = TTPodConfig.m5310C();
            if (FileUtils.m8419a(m5310C) && (m2606g = Player.this.m2606g()) != null && m2606g.isOnline()) {
                FileUtils.m8413b(TTPodConfig.m5308a(m2606g.getSongID()), m5310C);
            }
        }
    };

    /* renamed from: y */
    private MediaPlayerProxy.InterfaceC2043b f7110y = new MediaPlayerProxy.InterfaceC2043b() { // from class: com.sds.android.ttpod.framework.support.a.f.4
        @Override // com.sds.android.ttpod.framework.support.p134a.MediaPlayerProxy.InterfaceC2043b
        /* renamed from: a */
        public void mo2561a(int i) {
            MediaItem m2606g = Player.this.m2606g();
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
        this.f7099m.m2713a(str, i, str2, z);
    }

    /* renamed from: a */
    public boolean m2635a(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        MediaItem m2655b = this.f7101o.m2655b();
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
            this.f7102p.m2755a(this.f7101o.m2655b(), 0);
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
                this.f7101o.m2659a(mediaItem);
                m2638J();
            }
            if (!StringUtils.isEmpty(intent.getStringExtra("group")) || !StringUtils.isEmpty(intent.getStringExtra("media_source"))) {
                this.f7101o.m2657a(intent.getStringExtra("group"), intent.getStringExtra("media_source"));
            }
            m2592n();
        } else if ("sync_command".equals(stringExtra)) {
            String stringExtra2 = intent.getStringExtra("group");
            String stringExtra3 = intent.getStringExtra("media_source");
            if (StringUtils.equals(stringExtra2, MediaStorage.GROUP_ID_FAV) || stringExtra2.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
                TTPodUser m2954aq = Preferences.m2954aq();
                EnvironmentUtils.C0603b.m8498a(m2954aq != null ? m2954aq.getUserId() : 0L);
            }
            MediaItem mediaItem2 = (MediaItem) intent.getExtras().get("mediaItem");
            if ((mediaItem2 != null && !mediaItem2.equals(this.f7101o.m2655b())) || !StringUtils.equals(Preferences.getLocalGroupId(), stringExtra2)) {
                this.f7101o.m2659a(mediaItem2);
                m2638J();
            }
            this.f7101o.m2657a(stringExtra2, stringExtra3 == null ? Preferences.getMediaId() : stringExtra3);
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
            EnvironmentUtils.C0603b.m8498a(m2954aq != null ? m2954aq.getUserId() : 0L);
        }
        if (!StringUtils.equals(Preferences.getLocalGroupId(), str)) {
            m2638J();
        }
        MediaSelector mediaSelector = this.f7101o;
        if (str2 == null) {
            str2 = Preferences.getMediaId();
        }
        mediaSelector.m2657a(str, str2);
    }

    /* renamed from: s */
    private void m2585s() {
        this.f7095i = new HeadsetPlugMonitor();
        this.f7095i.m2260a(this);
        this.f7107v.registerReceiver(this.f7095i, HeadsetPlugMonitor.m2261a());
        this.f7096j = new CallMonitor();
        this.f7096j.m2238a(this.f7107v, this);
        this.f7097k = new LockScreenMonitor(this);
        this.f7107v.registerReceiver(this.f7097k, LockScreenMonitor.m2256a());
        MediaButtonReceiver.m2252b();
    }

    /* renamed from: t */
    private void m2584t() {
        this.f7095i.m2260a((HeadsetPlugMonitor.InterfaceC2081a) null);
        this.f7107v.unregisterReceiver(this.f7095i);
        this.f7096j.m2238a(this.f7107v, (CallMonitor.InterfaceC2086a) null);
        this.f7107v.unregisterReceiver(this.f7097k);
    }

    /* renamed from: e */
    public static Player m2611e() {
        synchronized (Player.class) {
            if (f7087s == null) {
                f7087s = new Player();
            }
        }
        return f7087s;
    }

    /* renamed from: a */
    public void m2636a(Context context) {
        this.f7107v = context;
        this.f7099m = new MediaPlayerProxy(context);
        this.f7099m.m2721a(this.f7109x);
        this.f7099m.m2723a(this.f7108w);
        this.f7099m.m2722a(this.f7110y);
        this.f7101o = new MediaSelector(context);
        this.f7102p = new AudioEffectLoader(context, this.f7099m);
        this.f7102p.m2755a(m2606g(), 0);
        m2585s();
    }

    /* renamed from: f */
    public void m2608f() {
        this.f7099m.m2721a((MediaPlayerProxy.InterfaceC2044c) null);
        this.f7099m.m2723a((MediaPlayerProxy.InterfaceC2042a) null);
        this.f7099m.m2722a((MediaPlayerProxy.InterfaceC2043b) null);
        this.f7099m.m2681i();
        if (EffectDetect.usingAudioPlus()) {
            Intent intent = new Intent("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
            intent.putExtra("android.media.extra.AUDIO_SESSION", TTAudioTrack.audioSessionId());
            intent.putExtra("android.media.extra.PACKAGE_NAME", this.f7107v.getPackageName());
            this.f7107v.sendBroadcast(intent);
        }
        m2584t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public void m2583u() {
        if (this.f7100n == null) {
            this.f7100n = ((PowerManager) this.f7107v.getSystemService("power")).newWakeLock(1, getClass().getName());
            this.f7100n.acquire();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public void m2582v() {
        if (this.f7100n != null) {
            this.f7100n.release();
            this.f7100n = null;
        }
    }

    /* renamed from: g */
    public MediaItem m2606g() {
        return this.f7101o.m2655b();
    }

    /* renamed from: h */
    public PlayStatus m2604h() {
        return this.f7099m.m2690f();
    }

    /* renamed from: i */
    public int m2602i() {
        return this.f7099m.m2690f() == PlayStatus.STATUS_STOPPED ? m2640H() : this.f7099m.m2687g();
    }

    /* renamed from: j */
    public float m2600j() {
        return this.f7099m.m2684h();
    }

    /* renamed from: b */
    public void m2620b(int i) {
        if (this.f7099m.m2690f() == PlayStatus.STATUS_STOPPED && m2606g() != null) {
            Preferences.getPositionInfo(Preferences.getMediaId() + File.pathSeparator + i);
        } else {
            this.f7099m.m2727a(i);
        }
    }

    /* renamed from: k */
    public int m2598k() {
        return this.f7101o.m2662a();
    }

    /* renamed from: a */
    public boolean m2622a(int[] iArr, int i) {
        return this.f7099m.m2709a(iArr, i);
    }

    /* renamed from: a */
    public boolean m2621a(short[] sArr, int i) {
        return this.f7099m.m2708a(sArr, i);
    }

    /* renamed from: l */
    public String m2596l() {
        return this.f7102p.m2740d();
    }

    /* renamed from: w */
    private void m2581w() {
        this.f7101o.m2655b();
        this.f7102p.m2755a((MediaItem) null, 8);
    }

    /* renamed from: x */
    private void m2580x() {
        this.f7101o.m2655b();
        this.f7102p.m2738e();
    }

    /* renamed from: c */
    private void m2616c(int i) {
        this.f7101o.m2655b();
        this.f7102p.m2755a((MediaItem) null, i);
    }

    /* renamed from: m */
    public void m2594m() {
        m2639I();
        this.f7099m.m2707b();
        m2637K();
    }

    /* renamed from: y */
    private boolean m2579y() {
        return EnvironmentUtils.C0605d.m8469a(new File(EnvironmentUtils.C0605d.getSdcardPath())) < 3145728;
    }

    /* renamed from: z */
    private void m2578z() {
        if (this.f7093g == null) {
            this.f7093g = this.f7092f + File.separator + f7086e.substring(f7086e.indexOf("ttpod"));
            FileUtils.m8406f(this.f7093g);
        }
    }

    /* renamed from: n */
    public void m2592n() {
        MediaItem queryMediaItemBySongID;
        MediaButtonReceiver.m2254a();
        this.f7104r = 0;
        try {
            this.f7099m.m2707b();
            MediaItem m2606g = m2606g();
            if (m2606g != null) {
                String localDataSource = m2606g.getLocalDataSource();
                if (StringUtils.isEmpty(localDataSource) && m2606g.getSongID().longValue() > 0 && (queryMediaItemBySongID = MediaStorage.queryMediaItemBySongID(this.f7107v, MediaStorage.GROUP_ID_ALL_LOCAL, m2606g.getSongID())) != null) {
                    localDataSource = queryMediaItemBySongID.getLocalDataSource();
                }
                if (FileUtils.m8414b(localDataSource)) {
                    this.f7099m.m2712a(localDataSource, (Long) null);
                    //LocalMediaStatistic.m5199a(localDataSource, System.nanoTime());
                    //new //SSystemEvent("SYS_PLAY", "start").append("play_type", "local").append("song_id", localDataSource).post();
                } else {
                    if (m2606g.isOnline()) {
                        m2627a(m2606g);
                    } else if (m2606g.getID().equals(SecurityUtils.C0610b.m8361a(m2606g.getExtra()))) {
                        this.f7099m.m2711a(m2606g.getExtra(), TTPodConfig.m5310C(), (Long) null);
                    } else {
                        m2613d(-99);
                    }
                    final String m2730a = this.f7099m.m2730a();
                    TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.support.a.f.1
                        @Override // java.lang.Runnable
                        public void run() {
                            long m8405g = FileUtils.m8405g(Player.f7086e);
                            if (m8405g > 209715200 || EnvironmentUtils.C0605d.m8469a(new File(Player.f7086e)) <= 52428800) {
                                long j = m8405g - 41943040;
                                long j2 = j > 0 ? j : 0L;
                                String str = Player.f7086e;
                                if (j2 <= 209715200) {
                                    j2 = 209715200;
                                }
                                FileUtils.m8417a(str, j2, new String[]{TTPodConfig.m5310C(), m2730a});
                            }
                        }
                    });
                }
            } else {
                m2582v();
                LogUtils.debug("Player", "PLAYLIST_IS_EMPTY");
                this.f7107v.sendBroadcast(new Intent(Action.PLAYLIST_IS_EMPTY));
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
        String m2926bc = Preferences.m2926bc();
        if (!StringUtils.isEmpty(m2926bc) && m2926bc.startsWith("音乐圈_")) {
            //SSystemEvent.append(BaseFragment.KEY_SONG_LIST_ID, m2926bc.substring("音乐圈_".length()));
        }
        //OnlineMediaStatistic.m5050a(longValue, System.nanoTime());
        //OnlineMediaStatistic.m5036c(longValue, m2598k);
        String str2 = Preferences.m2983aX() + File.separator + mediaItem.getSongID();
        if (FileUtils.m8414b(str2)) {
            new File(str2).setLastModified(System.currentTimeMillis());
            //OnlineMediaStatistic.m5039b(longValue, true);
            //SSystemEvent.append("play_type", "cache").post();
            try {
                this.f7099m.m2712a(str2, mediaItem.getSongID());
                return;
            } catch (Exception e) {
                LogUtils.error("Player", "processPlayError above MAX_ERROR_COUNT");
                e.printStackTrace();
                return;
            }
        }
        if (m2579y()) {
            this.f7092f = EnvironmentUtils.C0605d.m8460d(this.f7107v);
            if (SDKVersionUtils.m8365i()) {
                this.f7092f = EnvironmentUtils.C0605d.m8470a(this.f7107v, EnvironmentUtils.C0605d.EnumC0607a.SECOND_SD_CARD);
            }
            if (StringUtils.isEmpty(this.f7092f)) {
                this.f7107v.sendBroadcast(new Intent(Action.PLAY_STATUS_CHANGED).putExtra("play_status", PlayStatus.STATUS_ERROR.ordinal()).putExtra("play_error_code", -5000));
                return;
            } else {
                m2578z();
                str = this.f7093g;
            }
        } else {
            str = f7086e;
        }
        if (!str.equals(this.f7094h)) {
            this.f7094h = str;
            Preferences.m2836r(str);
        }
        //OnlineMediaStatistic.m5039b(longValue, false);
        //SSystemEvent.append("play_type", "online").post();
        OnlineMediaItem.Url m4689a = OnlineMediaItemUtils.m4689a((OnlineMediaItem) JSONUtils.fromJson(mediaItem.getExtra(), OnlineMediaItem.class), EnvironmentUtils.DeviceConfig.m8476d());
        if (m4689a != null) {
            try {
                this.f7099m.m2711a(m4689a.getUrl(), TTPodConfig.m5310C(), mediaItem.getSongID());
            } catch (Exception e2) {
                LogUtils.error("Player", "processPlayError above MAX_ERROR_COUNT");
                e2.printStackTrace();
            }
            m2610e((int) DateTimeUtils.m3746a(m4689a.getDuration()));
            return;
        }
        m2613d(-100);
    }

    /* renamed from: A */
    private void m2647A() {
        try {
            MediaItem m2655b = this.f7101o.m2655b();
            if (!m2655b.isOnline()) {
                m2655b.setErrorStatus(1);
                MediaStorage.updateMediaItem(this.f7107v, m2655b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m2613d(int i) {
        LogUtils.error("Player", "processPlayError");
        if (this.f7103q < 5) {
            this.f7103q++;
            //new //SSystemEvent("SYS_PLAY", "next").append("type", "system").post();
            this.f7107v.startService(new Intent(this.f7107v, SupportService.class).putExtra("command", "next_command"));
        } else {
            m2582v();
            LogUtils.error("Player", "processPlayError above MAX_ERROR_COUNT");
        }
        Preferences.getPositionInfo("");
        m2647A();
        this.f7107v.sendBroadcast(new Intent(Action.PLAY_STATUS_CHANGED).putExtra("play_status", PlayStatus.STATUS_ERROR.ordinal()).putExtra("play_error_code", i));
    }

    /* renamed from: B */
    private void m2646B() {
        if (this.f7105t) {
            this.f7099m.m2702c();
        }
    }

    /* renamed from: C */
    private void m2645C() {
        PlayStatus m2690f = this.f7099m.m2690f();
        if (m2690f == PlayStatus.STATUS_PLAYING) {
            m2646B();
        } else if (m2690f == PlayStatus.STATUS_PAUSED) {
            m2644D();
        } else if (m2606g() == null) {
            m2642F();
        } else {
            m2592n();
        }
    }

    /* renamed from: D */
    private void m2644D() {
        this.f7106u = false;
        this.f7099m.m2694e();
    }

    /* renamed from: E */
    private void m2643E() {
        m2641G();
        this.f7101o.m2654c();
        m2638J();
        m2592n();
    }

    /* renamed from: F */
    private void m2642F() {
        m2641G();
        this.f7101o.m2653d();
        m2638J();
        m2592n();
    }

    /* renamed from: G */
    private void m2641G() {
        if (this.f7101o.m2655b() != null) {
            Preferences.getPositionInfo("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public int m2640H() {
        if (m2606g() != null) {
            String m2850o = Preferences.getPositionInfo();
            String str = m2606g().getID() + File.pathSeparator;
            if (StringUtils.isEmpty(m2850o) || !m2850o.startsWith(str)) {
                return 0;
            }
            try {
                return Integer.valueOf(m2850o.substring(str.length())).intValue();
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public void m2639I() {
        if (m2606g() != null) {
            Preferences.getPositionInfo(m2606g().getID() + File.pathSeparator + m2602i());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m2610e(int i) {
        m2606g().setDuration(Integer.valueOf(i));
        Intent intent = new Intent(Action.UPDATE_MEDIA_DURATION);
        intent.putExtra("media_id", m2606g().getID());
        intent.putExtra("media_duration", i);
        this.f7107v.sendBroadcast(intent);
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor.InterfaceC2081a
    /* renamed from: c */
    public void mo2258c() {
        //StatisticUtils.m4909a("startup", "headset", "unplugged", 1L);
        if (Preferences.m2834s()) {
            this.f7106u = true;
            m2646B();
        }
        this.f7107v.sendBroadcast(new Intent(Action.PLAY_HEADSET_UNPLUG));
        MediaButtonReceiver.m2254a();
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor.InterfaceC2081a
    /* renamed from: d */
    public void mo2257d() {
        this.f7106u = false;
        //StatisticUtils.m4909a("startup", "headset", "plugged", 1L);
        if (this.f7096j.m2239a(this.f7107v)) {
            if (Preferences.m2830t()) {
                PlayStatus m2690f = this.f7099m.m2690f();
                if (m2690f == PlayStatus.STATUS_PAUSED) {
                    m2644D();
                } else if (m2690f != PlayStatus.STATUS_PLAYING) {
                    m2592n();
                }
            }
            this.f7107v.sendBroadcast(new Intent(Action.PLAY_HEADSET_PLUG));
        }
        MediaButtonReceiver.m2254a();
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.CallMonitor.InterfaceC2086a
    /* renamed from: a */
    public void mo2234a() {
        if (this.f7098l == PlayStatus.STATUS_PLAYING && !this.f7106u) {
            m2644D();
        }
        this.f7098l = null;
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.CallMonitor.InterfaceC2086a
    /* renamed from: b */
    public void mo2233b() {
        if (this.f7098l == null) {
            this.f7098l = this.f7099m.m2690f();
            if (this.f7098l == PlayStatus.STATUS_PLAYING) {
                m2646B();
            }
        }
    }

    /* renamed from: o */
    public String m2590o() {
        return this.f7102p.m2763a();
    }

    /* renamed from: p */
    public void m2588p() {
        this.f7102p.m2755a(m2606g(), 0);
    }

    /* renamed from: q */
    public void m2587q() {
        this.f7102p.m2750b();
    }

    /* renamed from: a */
    public void m2626a(Boolean bool) {
        this.f7102p.m2754a(bool);
    }

    /* renamed from: a */
    public void m2623a(String str, boolean z) {
        AudioEffectItem audioEffectItem = (AudioEffectItem) JSONUtils.fromJson(str, AudioEffectItem.class);
        if (audioEffectItem != null) {
            this.f7102p.m2759a(audioEffectItem, z);
        }
    }

    @Override // com.sds.android.ttpod.framework.support.monitor.LockScreenMonitor.InterfaceC2082a
    /* renamed from: a */
    public void mo2255a(int i) {
        this.f7099m.m2685g(i == 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public void m2638J() {
        MediaItem m2606g = m2606g();
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
        m2617b(m2606g());
    }

    /* renamed from: b */
    private void m2617b(MediaItem mediaItem) {
        if (SDKVersionUtils.checkVersionThanAndroid11() && mediaItem != null) {
            this.f7107v.sendStickyBroadcast(new Intent("com.android.music.metachanged").putExtra("id", mediaItem.getID()).putExtra("artist", mediaItem.getArtist()).putExtra("album", mediaItem.getAlbum()).putExtra("track", mediaItem.getTitle()).putExtra("playing", this.f7099m.m2690f() == PlayStatus.STATUS_PLAYING).putExtra("isfavorite", mediaItem.getFav()));
        }
    }
}
