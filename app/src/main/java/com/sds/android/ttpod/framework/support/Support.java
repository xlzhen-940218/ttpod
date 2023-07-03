package com.sds.android.ttpod.framework.support;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;


import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.p126h.UnicomProxyData;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.sds.android.ttpod.framework.support.c */
/* loaded from: classes.dex */
public class Support {

    /* renamed from: a */
    protected static UnicomProxyData f7133a = null;

    /* renamed from: b */
    protected static boolean f7134b = false;

    /* renamed from: c */
    protected Context context;

    /* renamed from: e */
    protected ISupportService iSupportService;

    /* renamed from: d */
    protected boolean isExitCommand = false;

    /* renamed from: f */
    protected ConcurrentLinkedQueue<SupportCallback> f7138f = new ConcurrentLinkedQueue<>();

    /* renamed from: g */
    protected C2064a f7139g = new C2064a();

    /* renamed from: h */
    protected ArrayList<Object> f7140h = new ArrayList<>();

    /* renamed from: i */
    protected List<DownloadTaskInfo> f7141i = new ArrayList();

    /* renamed from: j */
    protected ServiceConnection f7142j = new ServiceConnection() { // from class: com.sds.android.ttpod.framework.support.c.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Support.this.iSupportService = ISupportService.AbstractBinderC2061a.m2509a(iBinder);
            for (DownloadTaskInfo downloadTaskInfo : Support.this.f7141i) {
                Support.this.m2496a(downloadTaskInfo);
            }
            Support.this.f7141i.clear();
            Iterator it = new ArrayList(Support.this.f7138f).iterator();
            while (it.hasNext()) {
                ((SupportCallback) it.next()).mo2448a();
            }
            Support.this.m2506C();
            Support.this.m2499a(Support.f7133a, Support.f7134b);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Support.this.iSupportService = null;
            Iterator<SupportCallback> it = Support.this.f7138f.iterator();
            while (it.hasNext()) {
                it.next().m2442c();
            }
            Support.this.context.unbindService(Support.this.f7142j);
            LogUtils.info("Support", "音效:onServiceDisconnected");
        }
    };

    public Support(Context context) {
        DebugUtils.m8426a(context, "context");
        this.context = context;
    }

    /* renamed from: a */
    public void m2499a(UnicomProxyData unicomProxyData, boolean z) {
        f7133a = unicomProxyData;
        f7134b = z;
        LogUtils.debug("Support", "support bindProxy is mSupportService:" + this.iSupportService);
        if (this.iSupportService != null && unicomProxyData != null) {
            try {
                this.iSupportService.mo2389a(unicomProxyData.getHostname(), unicomProxyData.getHttpPort()
                        , unicomProxyData.getTcpPort(), unicomProxyData.getUsername(), unicomProxyData.getPassword(), z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo2497a(SupportCallback supportCallback) {
        try {
            m2476c(supportCallback);
            this.f7138f.add(supportCallback);
            if (m2452x()) {
                supportCallback.mo2448a();
            } else {
                m2450z();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo2505a() {
        throw new IllegalStateException("must be Override");
    }

    /* renamed from: c */
    public void mo2474c(String str, String str2) {
        throw new IllegalStateException("must be Override");
    }

    /* renamed from: d */
    public void m2473d() {
        try {
            this.f7138f.clear();
            m2508A();
            this.context.stopService(new Intent(this.context, SupportService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m2482b(SupportCallback supportCallback) {
        try {
            m2476c(supportCallback);
            this.f7138f.remove(supportCallback);
            if (this.f7138f.size() > 0) {
                m2508A();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: z */
    private void m2450z() {
        this.context.bindService(new Intent(this.context, SupportService.class), this.f7142j, Context.BIND_AUTO_CREATE);
        this.context.registerReceiver(this.f7139g, this.f7139g.m2449a());
    }

    /* renamed from: A */
    private void m2508A() {
        this.context.unbindService(this.f7142j);
        this.context.unregisterReceiver(this.f7139g);
        this.iSupportService = null;
    }

    /* renamed from: e */
    public void m2471e() {
        m2477c(m2490a("exit_command"));
    }

    /* renamed from: a */
    public void mo2489a(String str, String str2) {
        m2477c(m2490a("play_command").putExtra("group", str).putExtra("media_source", str2));
    }

    /* renamed from: b */
    public void mo2479b(String str, String str2) {
        DebugUtils.m8426a(str2, "mediaID");
        m2477c(m2490a("sync_command").putExtra("group", str).putExtra("media_source", str2));
    }

    /* renamed from: f */
    public void m2470f() {
        try {
            this.iSupportService.mo2395a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: g */
    public void m2469g() {
        m2477c(m2490a("pause_command"));
    }

    /* renamed from: h */
    public void m2468h() {
        m2477c(m2490a("resume_command"));
    }

    /* renamed from: b */
    public void mo2485b() {
        //new //SSystemEvent("SYS_PLAY", "previous").append("type", "user").post();
        m2477c(m2490a("previous_command"));
    }

    /* renamed from: c */
    public void mo2478c() {
        //new //SSystemEvent("SYS_PLAY", "next").append("type", "user").post();
        m2477c(m2490a("next_command"));
    }

    /* renamed from: i */
    public void m2467i() {
        m2477c(m2490a("resume_image_switcher"));
    }

    /* renamed from: j */
    public void m2466j() {
        m2477c(m2490a("pause_image_switcher"));
    }

    /* renamed from: k */
    public Integer m2465k() {
        try {
            if (this.iSupportService != null) {
                return Integer.valueOf(this.iSupportService.mo2378c());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m2507B();
    }

    /* renamed from: B */
    private Integer m2507B() {
        String m2850o = Preferences.getPositionInfo();
        String str = Preferences.getMediaId() + File.pathSeparator;
        if (!StringUtils.isEmpty(m2850o) && m2850o.startsWith(str)) {
            try {
                return Integer.valueOf(m2850o.substring(str.length()));
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public void m2491a(Integer num) {
        try {
            this.iSupportService.mo2394a(num.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: l */
    public float m2464l() {
        try {
            if (this.iSupportService != null) {
                return this.iSupportService.mo2376d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0f;
    }

    /* renamed from: m */
    public PlayStatus m2463m() {
        try {
            return PlayStatus.values()[this.iSupportService.mo2383b()];
        } catch (Exception e) {
            e.printStackTrace();
            return PlayStatus.STATUS_STOPPED;
        }
    }

    /* renamed from: n */
    public void m2462n() {
        m2477c(m2490a("reload_all_audioeffect_command"));
    }

    /* renamed from: a */
    public void m2504a(int i) {
        m2477c(m2490a("reload_audioeffect_command").putExtra("effect_type", i));
    }

    /* renamed from: o */
    public void m2461o() {
        m2477c(m2490a("reset_audioeffect_command"));
    }

    /* renamed from: p */
    public void m2460p() {
        m2477c(m2490a("start_mini_lyric_command"));
    }

    /* renamed from: q */
    public void m2459q() {
        m2477c(m2490a("stop_mini_lyric_command"));
    }

    /* renamed from: a */
    public void m2494a(MediaItem mediaItem, String str, String str2) {
        m2493a(mediaItem, str, str2, "picture_type");
    }

    /* renamed from: b */
    public void m2480b(MediaItem mediaItem, String singer, String songName) {
        m2493a(mediaItem, singer, songName, "lyric_type");
    }

    /* renamed from: a */
    private void m2493a(MediaItem mediaItem, String str, String str2, String str3) {
        m2477c(m2490a("search_lyric_pic_command").putExtra("type", str3).putExtra("artist_parameter", str).putExtra("title_parameter", str2).putExtra("media", (Parcelable) mediaItem));
    }

    /* renamed from: a */
    public void m2495a(ResultData.Item item, MediaItem mediaItem) {
        m2477c(m2490a("download_lyric_pic_command").putExtra("type", "lyric_type").putExtra("media", (Parcelable) mediaItem).putExtra("item", item));
    }

    /* renamed from: r */
    public int m2458r() {
        try {
            return this.iSupportService.mo2374e();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: a */
    public boolean m2486a(int[] iArr, int i) {
        try {
            return this.iSupportService.mo2384a(iArr, i);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */


    /* renamed from: a */
    public void m2488a(Map map) {
        try {
            if (this.iSupportService == null) {
                this.f7140h.add(map);
            } else {
                this.iSupportService.mo2387a(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public void m2506C() {
        Iterator<Object> it = this.f7140h.iterator();
        while (it.hasNext()) {
            Object next = it.next();if (next instanceof Map) {
                m2488a((Map) next);
            }
        }
        this.f7140h.clear();
        this.f7140h.trimToSize();
    }

    /* renamed from: s */
    public AudioEffectParam m2457s() {
        try {
            return (AudioEffectParam) JSONUtils.fromJson(this.iSupportService.mo2369j(), AudioEffectParam.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: t */
    public void m2456t() {
        try {
            this.iSupportService.mo2372g();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: u */
    public void m2455u() {
        try {
            this.iSupportService.mo2371h();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m2492a(Boolean bool) {
        try {
            this.iSupportService.mo2386a(bool.booleanValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m2501a(AudioEffectItem audioEffectItem, boolean z) {
        try {
            this.iSupportService.mo2388a(JSONUtils.toJson(audioEffectItem), z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: v */
    public MediaItem m2454v() {
        try {
            return this.iSupportService.mo2373f();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void m2496a(DownloadTaskInfo downloadTaskInfo) {
        try {
            if (m2452x()) {
                this.iSupportService.mo2391a(downloadTaskInfo);
            } else {
                this.f7141i.add(downloadTaskInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public DownloadTaskInfo m2481b(DownloadTaskInfo downloadTaskInfo) {
        try {
            if (m2452x()) {
                return this.iSupportService.mo2381b(downloadTaskInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: c */
    public DownloadTaskInfo m2475c(DownloadTaskInfo downloadTaskInfo) {
        try {
            if (m2452x()) {
                return this.iSupportService.mo2377c(downloadTaskInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: d */
    public int m2472d(DownloadTaskInfo downloadTaskInfo) {
        if (this.iSupportService != null) {
            try {
                return this.iSupportService.mo2375d(downloadTaskInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /* renamed from: a */
    protected void mo2502a(Intent intent) {
        MediaItem mediaItem = (MediaItem) intent.getExtras().get("mediaItem");
        Iterator<SupportCallback> it = this.f7138f.iterator();
        while (it.hasNext()) {
            it.next().mo2446a(mediaItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo2483b(Intent intent) {
        PlayStatus playStatus = PlayStatus.values()[intent.getIntExtra("play_status", PlayStatus.STATUS_STOPPED.ordinal())];
        if (playStatus == PlayStatus.STATUS_ERROR) {
            Iterator<SupportCallback> it = this.f7138f.iterator();
            while (it.hasNext()) {
                it.next().mo2447a(intent.getIntExtra("play_error_code", 0));
            }
        }
        Iterator<SupportCallback> it2 = this.f7138f.iterator();
        while (it2.hasNext()) {
            it2.next().mo2445a(playStatus);
        }
    }

    /* renamed from: w */
    public long m2453w() {
        try {
            return this.iSupportService.mo2368k();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /* renamed from: a */
    public void m2503a(long j) {
        try {
            this.iSupportService.mo2393a(j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: x */
    public boolean m2452x() {
        return this.iSupportService != null;
    }

    /* renamed from: a */
    public List<DownloadTaskInfo> m2487a(int[] iArr) {
        try {
            return this.iSupportService.mo2385a(iArr);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    /* renamed from: b */
    public void m2484b(long j) {
        try {
            this.iSupportService.mo2382b(j);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: y */
    public void m2451y() {
        try {
            this.iSupportService.mo2367l();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Intent m2490a(String str) {
        return new Intent(this.context, SupportService.class).putExtra("command", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m2477c(Intent intent) {
        if (this.context != null && !this.isExitCommand) {
            this.isExitCommand = "exit_command".equals(intent.getStringExtra("command"));
            this.context.startService(intent);
        }
    }

    /* renamed from: c */
    private void m2476c(SupportCallback supportCallback) {
        if (supportCallback == null) {
            throw new NullPointerException("SupportCallback must not be null.");
        }
        if (this.f7138f.contains(supportCallback)) {
            throw new IllegalArgumentException(supportCallback.getClass().getName() + " callback is added already.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Support.java */
    /* renamed from: com.sds.android.ttpod.framework.support.c$a */
    /* loaded from: classes.dex */
    public class C2064a extends BroadcastReceiver {
        private C2064a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Action.PLAY_MEDIA_CHANGED.equals(action)) {
                Support.this.mo2502a(intent);
            } else if (Action.UPDATE_MEDIA_DURATION.equals(action)) {
                Iterator<SupportCallback> it = Support.this.f7138f.iterator();
                while (it.hasNext()) {
                    it.next().mo2444a(intent.getStringExtra("media_id"), intent.getIntExtra("media_duration", 0));
                }
            } else if (Action.PLAY_BUFFERING_STARTED.equals(action)) {
                Iterator<SupportCallback> it2 = Support.this.f7138f.iterator();
                while (it2.hasNext()) {
                    it2.next().mo2443b();
                }
            } else if (Action.PLAY_BUFFERING_DONE.equals(action)) {
                Iterator<SupportCallback> it3 = Support.this.f7138f.iterator();
                while (it3.hasNext()) {
                    it3.next().mo2443b();
                }
            } else if (Action.PLAY_STATUS_CHANGED.equals(action)) {
                Support.this.mo2483b(intent);
            } else if (Action.EXIT.equals(action)) {
                Support.this.f7138f.clear();
                if (BaseApplication.getApplication() != null) {
                    BaseApplication.getApplication().mo4636b();
                }
            }
        }

        /* renamed from: a */
        public IntentFilter m2449a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.PLAY_MEDIA_CHANGED);
            intentFilter.addAction(Action.PLAY_STATUS_CHANGED);
            intentFilter.addAction(Action.PLAY_BUFFERING_STARTED);
            intentFilter.addAction(Action.PLAY_BUFFERING_DONE);
            intentFilter.addAction(Action.UPDATE_MEDIA_DURATION);
            intentFilter.addAction(Action.EXIT);
            intentFilter.addAction(Action.PREFERENCES_CHANGED);
            return intentFilter;
        }
    }
}
