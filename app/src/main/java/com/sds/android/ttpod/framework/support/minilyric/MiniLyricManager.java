package com.sds.android.ttpod.framework.support.minilyric;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.p132d.LyricParser;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.p134a.Player;
import com.sds.android.ttpod.media.player.PlayStatus;

/* loaded from: classes.dex */
public final class MiniLyricManager {

    /* renamed from: a */
    private static MiniLyric f7187a;

    /* renamed from: b */
    private static MiniLyricManager f7188b;

    /* renamed from: c */
    private static MiniLyricMonitor f7189c;

    /* renamed from: d */
    private static volatile boolean f7190d = true;

    /* renamed from: e */
    private static volatile boolean f7191e = false;

    /* renamed from: f */
    private static volatile boolean f7192f = false;

    /* renamed from: g */
    private Runnable f7193g = new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.MiniLyricManager.1
        @Override // java.lang.Runnable
        public void run() {
            LogUtils.debug("MiniLyricManager", "mRefreshRunnable");
            while (!MiniLyricManager.f7190d) {
                if (!MiniLyricManager.f7192f) {
                    MiniLyricManager.f7188b.m2339b();
                }
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    boolean unused = MiniLyricManager.f7190d = true;
                }
            }
        }
    };

    private MiniLyricManager() {
        LogUtils.error("MiniLyricManager", "stopMiniLyric");
        f7187a = new MiniLyric(BaseApplication.getApplication());
        f7187a.m2323a();
    }

    /* renamed from: j */
    private void m2327j() {
        f7189c = new MiniLyricMonitor(new MiniLyricMonitor.InterfaceC2074a() { // from class: com.sds.android.ttpod.framework.support.minilyric.MiniLyricManager.2
            @Override // com.sds.android.ttpod.framework.support.minilyric.MiniLyricManager.MiniLyricMonitor.InterfaceC2074a
            /* renamed from: a */
            public void mo2324a(boolean z) {
                MiniLyricManager.this.m2337b(z);
            }
        });
        BaseApplication.getApplication().registerReceiver(f7189c, MiniLyricMonitor.m2325a());
    }

    /* renamed from: k */
    private void m2326k() {
        BaseApplication.getApplication().unregisterReceiver(f7189c);
        f7189c = null;
    }

    /* renamed from: a */
    public static MiniLyricManager m2344a() {
        MiniLyricManager miniLyricManager;
        synchronized (MiniLyricManager.class) {
            if (f7188b == null) {
                f7188b = new MiniLyricManager();
            }
            miniLyricManager = f7188b;
        }
        return miniLyricManager;
    }

    /* renamed from: a */
    public boolean m2343a(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        if ("start_mini_lyric_command".equals(stringExtra)) {
            m2341a(Preferences.m2935b(Player.getInstance().getMediaItem()));
        } else if ("stop_mini_lyric_command".equals(stringExtra)) {
            m2336c();
        } else {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public void m2339b() {
        if (f7187a != null) {
            f7187a.m2319a(Player.getInstance().m2602i());
        }
    }

    /* renamed from: b */
    private void m2338b(String str) {
        LogUtils.debug("MiniLyricManager", "tryStart");
        if (!f7191e) {
            Lyric m3647b = LyricParser.m3647b(str);
            Object[] objArr = new Object[2];
            objArr[0] = str;
            objArr[1] = Boolean.valueOf(m3647b != null);
            LogUtils.debug("MiniLyricManager", "tryStart parseLyric lyricPath=%s lyric!=null_%b", objArr);
            if (!f7187a.m2310b() && m3647b == null) {
                m2340a(true);
            } else {
                m2342a(m3647b);
            }
        }
    }

    /* renamed from: a */
    public void m2341a(String str) {
        if (PlayStatus.STATUS_PLAYING == Player.getInstance().m2604h() && Preferences.m2838r() && BaseApplication.getApplication().m4627k()) {
            LogUtils.error("MiniLyricManager", "lyricPath = " + str);
            m2338b(str);
        }
    }

    /* renamed from: c */
    public void m2336c() {
        LogUtils.debug("MiniLyricManager", "stopMiniLyric");
        m2340a(true);
    }

    /* renamed from: a */
    void m2342a(Lyric lyric) {
        LogUtils.debug("MiniLyricManager", "entry Start");
        f7187a.m2317a(lyric);
        if (f7190d) {
            LogUtils.debug("MiniLyricManager", "start");
            f7190d = false;
            f7187a.m2296e();
            new Thread(this.f7193g).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2340a(boolean z) {
        f7190d = true;
        f7187a.m2312a(z);
    }

    /* renamed from: d */
    public void m2334d() {
        m2327j();
    }

    /* renamed from: e */
    public void m2332e() {
        LogUtils.debug("MiniLyricManager", "unInit");
        m2326k();
        f7191e = true;
        f7187a.m2312a(true);
        f7187a.m2293f();
        f7190d = true;
        this.f7193g = null;
        f7188b = null;
    }

    /* renamed from: b */
    public void m2337b(boolean z) {
        f7187a.lockStateNotification(z, true);
        if (!f7187a.m2304c()) {
            if (z) {
                m2340a(false);
            } else {
                m2342a((Lyric) null);
            }
        }
    }

    /* renamed from: f */
    public void m2331f() {
        LogUtils.debug("MiniLyricManager", "PlayStatus = " + Player.getInstance().m2604h());
        if (PlayStatus.STATUS_PLAYING == Player.getInstance().m2604h()) {
            f7188b.m2341a(Preferences.m2935b(Player.getInstance().getMediaItem()));
        } else {
            f7188b.m2336c();
        }
    }

    /* loaded from: classes.dex */
    public static class MiniLyricMonitor extends BroadcastReceiver {

        /* renamed from: a */
        private InterfaceC2074a f7196a;

        /* renamed from: com.sds.android.ttpod.framework.support.minilyric.MiniLyricManager$MiniLyricMonitor$a */
        /* loaded from: classes.dex */
        public interface InterfaceC2074a {
            /* renamed from: a */
            void mo2324a(boolean z);
        }

        public MiniLyricMonitor(InterfaceC2074a interfaceC2074a) {
            this.f7196a = null;
            this.f7196a = interfaceC2074a;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtils.error("MiniLyricManager", "onReceive action=" + action);
            if (Action.MINI_LYRIC_LOCK_STATUS_CHANGED.equals(action)) {
                boolean booleanExtra = intent.getBooleanExtra("is_locked", false);
                Preferences.m2810z(booleanExtra);
                this.f7196a.mo2324a(booleanExtra);
            } else if (Action.PLAY_STATUS_CHANGED.equals(action)) {
                MiniLyricManager.m2344a().m2331f();
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                boolean unused = MiniLyricManager.f7192f = true;
            } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                boolean unused2 = MiniLyricManager.f7192f = false;
            }
        }

        /* renamed from: a */
        public static IntentFilter m2325a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.MINI_LYRIC_LOCK_STATUS_CHANGED);
            intentFilter.addAction(Action.PLAY_STATUS_CHANGED);
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            return intentFilter;
        }
    }
}
