package com.sds.android.ttpod.framework.support.p134a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.audiofx.IEffectHandle;
import com.sds.android.ttpod.media.audiofx.SystemEffectHandle;
import com.sds.android.ttpod.media.audiofx.TTEffectHandle;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import com.sds.android.ttpod.media.player.IMediaPlayer;
import com.sds.android.ttpod.media.player.MediaPlayerNotificationInfo;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.player.SystemMediaPlayer;
import com.sds.android.ttpod.media.player.TTMediaPlayer;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;

/* renamed from: com.sds.android.ttpod.framework.support.a.b */
/* loaded from: classes.dex */
public final class MediaPlayerProxy {

    /* renamed from: d */
    private boolean f7037d;

    /* renamed from: f */
    private String f7039f;

    /* renamed from: g */
    private int f7040g;

    /* renamed from: h */
    private String f7041h;

    /* renamed from: k */
    private float f7044k;

    /* renamed from: l */
    private float f7045l;

    /* renamed from: o */
    private String f7048o;

    /* renamed from: p */
    private Long f7049p;

    /* renamed from: q */
    private volatile TTMediaPlayer f7050q;

    /* renamed from: r */
    private SystemMediaPlayer f7051r;

    /* renamed from: s */
    private IMediaPlayer f7052s;

    /* renamed from: v */
    private IEffectHandle f7055v;

    /* renamed from: w */
    private final byte[] f7056w;

    /* renamed from: x */
    private InterfaceC2044c f7057x;

    /* renamed from: y */
    private InterfaceC2042a f7058y;

    /* renamed from: z */
    private InterfaceC2043b f7059z;

    /* renamed from: a */
    private short[] f7034a = new short[512];

    /* renamed from: b */
    private C2045d f7035b = new C2045d();

    /* renamed from: c */
    private int f7036c = 0;

    /* renamed from: e */
    private float f7038e = 1.0f;

    /* renamed from: i */
    private boolean f7042i = false;

    /* renamed from: j */
    private boolean f7043j = false;

    /* renamed from: m */
    private String f7046m = "";

    /* renamed from: n */
    private PlayStatus playStatus = PlayStatus.STATUS_STOPPED;

    /* renamed from: t */
    private volatile TTEffectHandle f7053t = null;

    /* renamed from: u */
    private SystemEffectHandle f7054u = null;

    /* renamed from: A */
    private TTMediaPlayer.OnMediaPlayerNotifyEventListener f7029A = new TTMediaPlayer.OnMediaPlayerNotifyEventListener() { // from class: com.sds.android.ttpod.framework.support.a.b.1
        @Override // com.sds.android.ttpod.media.player.TTMediaPlayer.OnMediaPlayerNotifyEventListener
        public void onMediaPlayerNotify(int i, int i2, int i3, Object obj) {
            LogUtils.debug("MediaPlayerProxy", "MsgId:" + i);
            switch (i) {
                case 1:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_PREPARE");
                    MediaPlayerProxy.this.m2668q();
                    return;
                case 2:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_PLAY");
                    MediaPlayerProxy.this.playStatus = PlayStatus.STATUS_PLAYING;
                    MediaPlayerProxy.this.m2729a(Preferences.m2956ao());
                    if (MediaPlayerProxy.this.f7057x != null) {
                        MediaPlayerProxy.this.f7057x.mo2570b();
                        return;
                    }
                    return;
                case 3:
                    MediaPlayerProxy.this.m2669p();
                    return;
                case 4:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_PAUSE");
                    return;
                case 5:
                    if (MediaPlayerProxy.this.f7058y != null) {
                        MediaPlayerProxy.this.f7058y.mo2577a(i3);
                    }
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_CLOSE");
                    return;
                case 6:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_EXCEPTION");
                    MediaPlayerProxy.this.m2725a(i2, i3, (MediaPlayerNotificationInfo) obj);
                    return;
                case 7:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_UPDATE_DURATION");
                    if (MediaPlayerProxy.this.f7059z != null) {
                        MediaPlayerProxy.this.f7059z.mo2561a(MediaPlayerProxy.this.f7052s.duration());
                    }
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //OnlineMediaStatistic.m5023j(MediaPlayerProxy.this.f7049p.longValue(), MediaPlayerProxy.this.f7052s.duration() / 1000);
                        //new //SSystemEvent("SYS_PLAY", "update_duration").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).append("duration", Integer.valueOf(MediaPlayerProxy.this.f7052s.duration() / 1000)).post();
                        return;
                    }
                    return;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                default:
                    return;
                case 16:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_BUFFERING_START");
                    if (MediaPlayerProxy.this.f7057x != null) {
                        MediaPlayerProxy.this.f7057x.mo2567e();
                    }
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //OnlineMediaStatistic.m5051a(MediaPlayerProxy.this.f7049p.longValue(), MediaPlayerProxy.this.m2687g());
                        //new //SSystemEvent("SYS_PLAY", "block_start").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).post();
                        return;
                    }
                    return;
                case 17:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_BUFFERING_DONE");
                    if (MediaPlayerProxy.this.f7057x != null) {
                        MediaPlayerProxy.this.f7057x.mo2566f();
                    }
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //new //SSystemEvent("SYS_PLAY", "block_done").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).post();
                        return;
                    }
                    return;
                case 18:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_DNS_DONE");
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //OnlineMediaStatistic.m5025h(MediaPlayerProxy.this.f7049p.longValue(), System.nanoTime());
                        //new //SSystemEvent("SYS_PLAY", "dns_done").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).post();
                        return;
                    }
                    return;
                case 19:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_CONNECT_DONE");
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //OnlineMediaStatistic.m5024i(MediaPlayerProxy.this.f7049p.longValue(), System.nanoTime());
                        //new //SSystemEvent("SYS_PLAY", "connect_done").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).post();
                        return;
                    }
                    return;
                case 20:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_HTTP_HEADER_RECEIVED");
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //OnlineMediaStatistic.m5026g(MediaPlayerProxy.this.f7049p.longValue(), System.nanoTime());
                        //new //SSystemEvent("SYS_PLAY", "header_received").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).post();
                        return;
                    }
                    return;
                case 21:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_START_RECEIVE_DATA");
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //OnlineMediaStatistic.m5022k(MediaPlayerProxy.this.f7049p.longValue(), System.nanoTime());
                        //new //SSystemEvent("SYS_PLAY", "receive_data_start").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).post();
                        return;
                    }
                    return;
                case 22:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_PREFETCH_COMPLETED");
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //OnlineMediaStatistic.m5030e(MediaPlayerProxy.this.f7049p.longValue(), System.nanoTime());
                        //new //SSystemEvent("SYS_PLAY", "receive_data_end").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).post();
                        return;
                    }
                    return;
                case 23:
                    LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_CACHE_COMPLETED");
                    if (MediaPlayerProxy.this.f7057x != null) {
                        MediaPlayerProxy.this.f7057x.mo2565g();
                    }
                    if (MediaPlayerProxy.this.f7049p != null) {
                        //OnlineMediaStatistic.m5035c(MediaPlayerProxy.this.f7049p.longValue(), System.nanoTime());
                        //new //SSystemEvent("SYS_PLAY", "cache_done").append("song_id", MediaPlayerProxy.this.f7049p).append("play_type", MediaPlayerProxy.this.f7046m).post();
                        return;
                    }
                    return;
            }
        }
    };

    /* renamed from: B */
    private MediaPlayer.OnPreparedListener f7030B = new MediaPlayer.OnPreparedListener() { // from class: com.sds.android.ttpod.framework.support.a.b.2
        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            if (MediaPlayerProxy.this.f7036c != 0) {
                MediaPlayerProxy.this.f7052s.setPosition(MediaPlayerProxy.this.f7036c);
                MediaPlayerProxy.this.f7036c = 0;
            }
            if (MediaPlayerProxy.this.f7057x != null) {
                MediaPlayerProxy.this.f7057x.mo2576a();
            }
        }
    };

    /* renamed from: C */
    private MediaPlayer.OnCompletionListener f7031C = new MediaPlayer.OnCompletionListener() { // from class: com.sds.android.ttpod.framework.support.a.b.3
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            MediaPlayerProxy.this.playStatus = PlayStatus.STATUS_STOPPED;
            if (MediaPlayerProxy.this.f7057x != null) {
                MediaPlayerProxy.this.f7057x.mo2568d();
            }
        }
    };

    /* renamed from: D */
    private MediaPlayer.OnBufferingUpdateListener f7032D = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.sds.android.ttpod.framework.support.a.b.4
        @Override // android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        }
    };

    /* renamed from: E */
    private MediaPlayer.OnErrorListener f7033E = new MediaPlayer.OnErrorListener() { // from class: com.sds.android.ttpod.framework.support.a.b.5
        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            MediaPlayerProxy.this.playStatus = PlayStatus.STATUS_STOPPED;
            if (MediaPlayerProxy.this.f7057x != null) {
                MediaPlayerProxy.this.f7057x.mo2574a(i, 0, null);
                return true;
            }
            return true;
        }
    };

    /* compiled from: MediaPlayerProxy.java */
    /* renamed from: com.sds.android.ttpod.framework.support.a.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2042a {
        /* renamed from: a */
        void mo2577a(long j);
    }

    /* compiled from: MediaPlayerProxy.java */
    /* renamed from: com.sds.android.ttpod.framework.support.a.b$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2043b {
        /* renamed from: a */
        void mo2561a(int i);
    }

    /* compiled from: MediaPlayerProxy.java */
    /* renamed from: com.sds.android.ttpod.framework.support.a.b$c */
    /* loaded from: classes.dex */
    public interface InterfaceC2044c {
        /* renamed from: a */
        void mo2576a();

        /* renamed from: a */
        void mo2574a(int i, int i2, MediaPlayerNotificationInfo mediaPlayerNotificationInfo);

        /* renamed from: b */
        void mo2570b();

        /* renamed from: c */
        void mo2569c();

        /* renamed from: d */
        void mo2568d();

        /* renamed from: e */
        void mo2567e();

        /* renamed from: f */
        void mo2566f();

        /* renamed from: g */
        void mo2565g();
    }

    /* renamed from: a */
    private static byte[] m2724a(Context context) {
        byte[] bArr;
        Exception e;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(EnvironmentUtils.m8526a(), 64);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byteArrayOutputStream = new ByteArrayOutputStream();
            Signature[] signatureArr = packageInfo.signatures;
            for (Signature signature : signatureArr) {
                byteArrayOutputStream.write(messageDigest.digest(signature.toByteArray()));
            }
            bArr = byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            bArr = null;
            e = e2;
        }
        try {
            byteArrayOutputStream.close();
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return bArr;
        }
        return bArr;
    }

    public MediaPlayerProxy(Context context) {
        boolean z = false;
        this.f7044k = 1.0f;
        this.f7045l = 1.0f;
        try {
            if (EnvironmentUtils.CPU.cpuFamily() == 1 && (EnvironmentUtils.CPU.cpuFeatures() & 18) == 0) {
                z = true;
            }
            this.f7037d = z;
        } catch (Throwable th) {
            this.f7037d = true;
            th.printStackTrace();
        }
        this.f7056w = m2724a(context);
        this.f7052s = m2682h(this.f7037d);
        float m2956ao = Preferences.m2956ao();
        this.f7044k = 1.0f - m2956ao;
        this.f7045l = m2956ao + 1.0f;
    }

    /* renamed from: h */
    private IMediaPlayer m2682h(boolean z) {
        m2671n();
        if (!z) {
            try {
                this.f7050q = this.f7050q == null ? m2675l() : this.f7050q;
                this.f7053t = this.f7053t == null ? new TTEffectHandle() : this.f7053t;
                this.f7055v = this.f7053t;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (z || this.f7050q == null) {
            this.f7051r = m2673m();
            if (this.f7054u == null) {
                try {
                    this.f7054u = new SystemEffectHandle(this.f7051r.getAudioSessionId());
                } catch (Throwable th2) {
                    this.f7054u = new SystemEffectHandle(0);
                    th2.printStackTrace();
                }
                this.f7055v = this.f7054u;
            }
            return this.f7051r;
        }
        return this.f7050q;
    }

    /* renamed from: a */
    public void m2712a(String str, Long l) throws Exception {
        this.f7036c = 0;
        this.f7052s = m2682h(this.f7037d);
        this.f7052s.setDataSourceAsync(str);
        this.f7048o = str;
        this.f7049p = l;
        if (this.f7049p != null) {
            this.f7046m = "cache";
        } else {
            this.f7046m = "local";
        }
    }

    /* renamed from: a */
    public void m2711a(String str, String str2, Long l) throws Exception {
        this.f7036c = 0;
        this.f7052s = m2682h(this.f7037d);
        if (this.f7043j) {
            this.f7043j = false;
            this.f7052s.setProxyServerConfig(this.f7039f, this.f7040g, this.f7041h, this.f7042i);
        }
        this.f7052s.setCacheFilePath(str2);
        this.f7052s.setDataSourceAsync(str);
        this.f7048o = str;
        this.f7049p = l;
        this.f7046m = "online";
    }

    /* renamed from: a */
    public String m2730a() {
        return this.f7048o;
    }

    /* renamed from: b */
    public void m2707b() {
        if (this.f7049p != null) {
            //OnlineMediaStatistic.m5035c(this.f7049p.longValue(), System.nanoTime());
            //OnlineMediaStatistic.m5040b(this.f7049p.longValue(), m2687g() / 1000);
            //OnlineMediaStatistic.m5032d(this.f7049p.longValue(), this.f7052s.getBufferSize());
            try {
                //OnlineMediaStatistic.m5052a(this.f7049p.longValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //new //SSystemEvent("SYS_PLAY", "stop").append("song_id", this.f7049p).append("buffer_size", Integer.valueOf(this.f7052s.getBufferSize())).append("time_played", Integer.valueOf(m2687g() / 1000)).append("play_type", this.f7046m).post();
        } else if (FileUtils.m8414b(this.f7048o)) {
            //LocalMediaStatistic.m5194d(this.f7048o, m2687g() / 1000);
            try {
                //LocalMediaStatistic.m5200a(this.f7048o);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            //new //SSystemEvent("SYS_PLAY", "stop").append("song_id", this.f7048o).append("buffer_size", Integer.valueOf(this.f7052s.getBufferSize())).append("time_played", Integer.valueOf(m2687g() / 1000)).append("play_type", this.f7046m).post();
        }
        if (PlayStatus.STATUS_PLAYING == this.playStatus || (this.f7052s instanceof TTMediaPlayer)) {
            this.f7052s.stop();
        }
        this.playStatus = PlayStatus.STATUS_STOPPED;
    }

    /* renamed from: c */
    public void m2702c() {
        this.f7052s.pause();
        this.playStatus = PlayStatus.STATUS_PAUSED;
        if (this.f7057x != null) {
            this.f7057x.mo2569c();
        }
        //SSystemEvent //SSystemEvent = //new //SSystemEvent("SYS_PLAY", "pause");
        if (this.f7049p != null) {
            //SSystemEvent.append("song_id", this.f7049p).append("play_type", this.f7046m).post();
        } else if (FileUtils.m8419a(this.f7048o)) {
            //SSystemEvent.append("song_id", this.f7048o).append("play_type", this.f7046m).post();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m2679j() {
        this.f7052s.pause();
        if (this.f7052s == this.f7051r && this.f7057x != null) {
            this.playStatus = PlayStatus.STATUS_PAUSED;
            this.f7057x.mo2569c();
        }
    }

    /* renamed from: d */
    public void m2698d() {
        this.f7052s.play();
        if (this.f7052s == this.f7051r) {
            this.playStatus = PlayStatus.STATUS_PLAYING;
            if (this.f7057x != null) {
                this.f7057x.mo2570b();
            }
        }
    }

    /* renamed from: e */
    public void m2694e() {
        m2677k();
        //SSystemEvent //SSystemEvent = //new //SSystemEvent("SYS_PLAY", "resume");
        if (this.f7049p != null) {
            //SSystemEvent.append("song_id", this.f7049p).append("play_type", this.f7046m).post();
        } else if (FileUtils.m8419a(this.f7048o)) {
            //SSystemEvent.append("song_id", this.f7048o).append("play_type", this.f7046m).post();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m2677k() {
        this.f7052s.resume();
        if (this.f7052s == this.f7051r && this.f7057x != null) {
            this.playStatus = PlayStatus.STATUS_PLAYING;
            this.f7057x.mo2570b();
        }
    }

    /* renamed from: f */
    public PlayStatus getPlayStatus() {
        return this.playStatus;
    }

    /* renamed from: g */
    public int m2687g() {
        if (this.f7052s != null) {
            return this.f7052s.getPosition();
        }
        return 0;
    }

    /* renamed from: h */
    public float m2684h() {
        if (this.playStatus == PlayStatus.STATUS_PLAYING || this.playStatus == PlayStatus.STATUS_PAUSED) {
            return this.f7052s.getBufferPercent();
        }
        return 0.0f;
    }

    /* renamed from: a */
    public void m2727a(int i) {
        int bufferedPercent = this.f7052s.bufferedPercent();
        if (bufferedPercent <= 0 || (bufferedPercent * this.f7052s.duration()) / 100 >= i) {
            this.f7052s.setPosition(i);
            if (this.playStatus == PlayStatus.STATUS_PLAYING) {
                this.f7035b.m2665a(Preferences.getAudioFadeSeekLength() / 10, 1);
            }
        }
    }

    /* renamed from: a */
    public void m2726a(int i, int i2) {
        this.f7052s.setPlayRange(i, i2);
    }

    /* renamed from: a */
    public void m2713a(String str, int i, String str2, boolean z) {
        this.f7039f = str;
        this.f7040g = i;
        this.f7041h = str2;
        this.f7042i = z;
        this.f7043j = true;
    }

    /* renamed from: l */
    private TTMediaPlayer m2675l() {
        TTMediaPlayer tTMediaPlayer = new TTMediaPlayer(this.f7056w, "/data/data/" + EnvironmentUtils.m8526a() + "/lib");
        tTMediaPlayer.setOnMediaPlayerNotifyEventListener(this.f7029A);
        return tTMediaPlayer;
    }

    /* renamed from: m */
    private SystemMediaPlayer m2673m() {
        SystemMediaPlayer systemMediaPlayer = new SystemMediaPlayer();
        systemMediaPlayer.setOnPreparedListener(this.f7030B);
        systemMediaPlayer.setOnCompletionListener(this.f7031C);
        systemMediaPlayer.setOnBufferingUpdateListener(this.f7032D);
        systemMediaPlayer.setOnErrorListener(this.f7033E);
        return systemMediaPlayer;
    }

    /* renamed from: n */
    private void m2671n() {
        if (this.f7054u != null) {
            this.f7054u.release();
            this.f7054u = null;
        }
        if (this.f7051r != null) {
            this.f7051r.setOnPreparedListener(null);
            this.f7051r.setOnCompletionListener(null);
            this.f7051r.setOnBufferingUpdateListener(null);
            this.f7051r.setOnErrorListener(null);
            this.f7051r.release();
            this.f7051r = null;
        }
    }

    /* renamed from: o */
    private void m2670o() {
        if (this.f7053t != null) {
            this.f7053t.release();
            this.f7053t = null;
        }
        if (this.f7050q != null) {
            this.f7050q.setOnMediaPlayerNotifyEventListener(null);
            this.f7050q.release();
            this.f7050q = null;
        }
    }

    /* renamed from: a */
    public void m2721a(InterfaceC2044c interfaceC2044c) {
        this.f7057x = interfaceC2044c;
    }

    /* renamed from: a */
    public void m2723a(InterfaceC2042a interfaceC2042a) {
        this.f7058y = interfaceC2042a;
    }

    /* renamed from: a */
    public void m2722a(InterfaceC2043b interfaceC2043b) {
        this.f7059z = interfaceC2043b;
    }

    /* renamed from: i */
    public void m2681i() {
        m2671n();
        m2670o();
        this.f7052s = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public void m2669p() {
        LogUtils.debug("statistic_MediaPlayerProxy", "MEDIA_COMPLETE");
        this.playStatus = PlayStatus.STATUS_STOPPED;
        if (this.f7049p != null) {
            //OnlineMediaStatistic.m5048a(this.f7049p.longValue(), true);
            //new //SSystemEvent("SYS_PLAY", "complete").append("song_id", this.f7049p).append("play_type", this.f7046m).post();
        } else if (FileUtils.m8414b(this.f7048o)) {
            //LocalMediaStatistic.m5198a(this.f7048o, true);
            //new //SSystemEvent("SYS_PLAY", "complete").append("song_id", this.f7048o).append("play_type", this.f7046m).post();
        }
        if (this.f7057x != null) {
            this.f7057x.mo2568d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2725a(int i, int i2, MediaPlayerNotificationInfo mediaPlayerNotificationInfo) {
        this.playStatus = PlayStatus.STATUS_STOPPED;
        if (this.f7048o.startsWith("/")) {
            this.f7036c = this.f7052s.getPosition();
            this.f7052s = m2682h(true);
            try {
                this.f7052s.setDataSourceAsync(this.f7048o);
            } catch (Exception e) {
                e.printStackTrace();
                if (this.f7057x != null) {
                    this.f7057x.mo2574a(i, i2, mediaPlayerNotificationInfo);
                }
            }
            //new //SSystemEvent("SYS_PLAY", "error").append("song_id", this.f7048o).append("error_code", Integer.valueOf(i)).append("play_type", this.f7046m).post();
            return;
        }
        if (this.f7057x != null) {
            this.f7057x.mo2574a(i, i2, mediaPlayerNotificationInfo);
        }
        String str = "";
        String str2 = "";
        if (mediaPlayerNotificationInfo != null) {
            str = mediaPlayerNotificationInfo.getURL();
            str2 = mediaPlayerNotificationInfo.getIP();
        }
        //new //SSystemEvent("SYS_PLAY", "error").append("song_id", this.f7049p).append("uri", str).append("error_code", Integer.valueOf(i)).append("response_code", Integer.valueOf(i2)).append("ip", str2).append("play_type", this.f7046m).post();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public void m2668q() {
        this.f7055v.reset();
        if (this.f7057x != null) {
            this.f7057x.mo2576a();
        }
        m2728a(0.0f, 0.0f);
        if (this.f7049p != null) {
            //OnlineMediaStatistic.m5030e(this.f7049p.longValue(), System.nanoTime());
            //OnlineMediaStatistic.m5028f(this.f7049p.longValue(), this.f7052s.getFileSize());
            //OnlineMediaStatistic.m5023j(this.f7049p.longValue(), this.f7052s.duration() / 1000);
            //OnlineMediaStatistic.m5049a(this.f7049p.longValue(), this.f7048o);
            //new //SSystemEvent("SYS_PLAY", "prepare").append("song_id", this.f7049p).append("file_size", Integer.valueOf(this.f7052s.getFileSize())).append("duration", Integer.valueOf(this.f7052s.duration() / 1000)).append("uri", this.f7048o).append("play_type", this.f7046m).post();
        } else if (FileUtils.m8414b(this.f7048o)) {
            //LocalMediaStatistic.m5195c(this.f7048o, this.f7052s.getFileSize());
            //LocalMediaStatistic.m5196b(this.f7048o, this.f7052s.duration() / 1000);
            //StatisticUtils.m4910a("song", "listen_info", "local");
            //new //SSystemEvent("SYS_PLAY", "prepare").append("song_id", this.f7048o).append("file_size", Integer.valueOf(this.f7052s.getFileSize())).append("duration", Integer.valueOf(this.f7052s.duration() / 1000)).append("uri", this.f7048o).append("play_type", this.f7046m).post();
        }
    }

    /* renamed from: a */
    public boolean m2709a(int[] iArr, int i) {
        if (iArr.length >= i && this.f7052s.getCurFreq(this.f7034a, 512) == 0) {
            if (this.f7052s == this.f7050q) {
                return Normalizer.m2648a(iArr, i, this.f7034a, 512) == 0;
            }
            for (int i2 = 0; i2 < i; i2++) {
                iArr[i2] = this.f7034a[i2];
            }
            Normalizer.m2649a(iArr, i);
            Normalizer.m2649a(iArr, i);
            Normalizer.m2649a(iArr, i);
            Normalizer.m2649a(iArr, i);
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public boolean m2708a(short[] sArr, int i) {
        return sArr.length >= i && this.f7052s.getCurWave(sArr, i) == 0;
    }

    /* renamed from: a */
    public void m2710a(boolean z) {
        this.f7055v.setEqualizerEnabled(z);
    }

    /* renamed from: a */
    public void m2714a(TTEqualizer.Settings settings) {
        this.f7055v.setEqualizer(settings);
    }

    /* renamed from: b */
    public void m2703b(boolean z) {
        this.f7055v.setBassBoostEnabled(z);
    }

    /* renamed from: b */
    public void m2706b(int i) {
        this.f7055v.setBassBoost(i);
    }

    /* renamed from: c */
    public void m2699c(boolean z) {
        this.f7055v.setBoostLimitEnabled(z);
    }

    /* renamed from: d */
    public void m2695d(boolean z) {
        this.f7055v.setTrebleBoostEnabled(z);
    }

    /* renamed from: c */
    public void m2701c(int i) {
        this.f7055v.setTrebleBoost(i);
    }

    /* renamed from: e */
    public void m2691e(boolean z) {
        this.f7055v.setVirtualizerEnabled(z);
    }

    /* renamed from: d */
    public void m2697d(int i) {
        this.f7055v.setVirtualizer(i);
    }

    /* renamed from: f */
    public void m2688f(boolean z) {
        this.f7055v.setReverbEnabled(z);
    }

    /* renamed from: e */
    public void m2693e(int i) {
        this.f7055v.setReverb(i);
    }

    /* renamed from: a */
    public void m2729a(float f) {
        if (this.playStatus != PlayStatus.STATUS_STOPPED) {
            this.f7052s.setChannelBalance(f);
        }
        this.f7044k = 1.0f - f;
        this.f7045l = 1.0f + f;
    }

    /* renamed from: g */
    public void m2685g(boolean z) {
        this.f7052s.setAudioEffectLowDelay(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2728a(float f, float f2) {
        this.f7052s.setVolume(f, f2);
    }

    /* compiled from: MediaPlayerProxy.java */
    /* renamed from: com.sds.android.ttpod.framework.support.a.b$d */
    /* loaded from: classes.dex */
    public class C2045d {

        /* renamed from: b */
        private int f7066b;

        /* renamed from: c */
        private int f7067c;

        /* renamed from: d */
        private float f7068d;

        /* renamed from: e */
        private float f7069e;

        /* renamed from: f */
        private float f7070f = 1.0f;

        /* renamed from: g */
        private Handler f7071g;

        /* renamed from: h */
        private int f7072h;

        public C2045d() {
            this.f7071g = new Handler(Looper.getMainLooper()) { // from class: com.sds.android.ttpod.framework.support.a.b.d.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    C2045d.this.m2664a(message);
                }
            };
        }

        /* renamed from: a */
        public void m2666a(int i, float f, int i2) {
            if (i > 0) {
                this.f7066b = i;
                this.f7068d = f;
                this.f7072h = i2;
                this.f7071g.removeCallbacksAndMessages(null);
                this.f7070f = 0.0f;
                MediaPlayerProxy.this.f7044k = 0.0f;
                MediaPlayerProxy.this.f7045l = 0.0f;
                m2667a();
                if (i2 == 0) {
                    MediaPlayerProxy.this.m2677k();
                }
                if (i2 == 1) {
                    this.f7071g.sendEmptyMessageDelayed(0, 200L);
                } else {
                    this.f7071g.sendEmptyMessageDelayed(0, this.f7066b);
                }
            } else if (i2 == 0) {
                this.f7070f = 1.0f;
                float m2956ao = Preferences.m2956ao();
                MediaPlayerProxy.this.f7044k = 1.0f - m2956ao;
                MediaPlayerProxy.this.f7045l = m2956ao + 1.0f;
                m2667a();
                MediaPlayerProxy.this.m2677k();
            }
        }

        /* renamed from: a */
        public void m2665a(int i, int i2) {
            m2666a(i, 0.1f, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m2664a(Message message) {
            switch (message.what) {
                case 0:
                    this.f7070f += this.f7068d;
                    float m2956ao = Preferences.m2956ao();
                    MediaPlayerProxy.this.f7044k = Math.min(this.f7070f, 1.0f - m2956ao);
                    MediaPlayerProxy.this.f7045l = Math.min(this.f7070f, 1.0f + m2956ao);
                    if (MediaPlayerProxy.this.f7044k + 1.0E-5f < 1.0f - m2956ao || MediaPlayerProxy.this.f7045l + 1.0E-5f < 1.0f + m2956ao) {
                        this.f7071g.sendEmptyMessageDelayed(0, this.f7066b);
                        break;
                    } else {
                        this.f7070f = 1.0f;
                        MediaPlayerProxy.this.f7044k = 1.0f - m2956ao;
                        MediaPlayerProxy.this.f7045l = m2956ao + 1.0f;
                        break;
                    }
                case 1:
                    this.f7070f -= this.f7069e;
                    MediaPlayerProxy.this.f7044k = Math.max(MediaPlayerProxy.this.f7044k - this.f7069e, 0.0f);
                    MediaPlayerProxy.this.f7045l = Math.max(MediaPlayerProxy.this.f7045l - this.f7069e, 0.0f);
                    if (MediaPlayerProxy.this.f7044k > 1.0E-5f || MediaPlayerProxy.this.f7045l > 1.0E-5f) {
                        this.f7071g.sendEmptyMessageDelayed(1, this.f7067c);
                        break;
                    } else {
                        this.f7070f = 0.0f;
                        MediaPlayerProxy.this.f7044k = 0.0f;
                        MediaPlayerProxy.this.f7045l = 0.0f;
                        if (this.f7072h == 0) {
                            MediaPlayerProxy.this.m2679j();
                            break;
                        }
                    }
                    break;
            }
            m2667a();
        }

        /* renamed from: a */
        private void m2667a() {
            MediaPlayerProxy.this.m2728a(MediaPlayerProxy.this.f7044k, MediaPlayerProxy.this.f7045l);
        }
    }
}
