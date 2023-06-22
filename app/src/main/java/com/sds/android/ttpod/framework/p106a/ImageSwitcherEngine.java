package com.sds.android.ttpod.framework.p106a;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.support.p134a.Player;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.a.g */
/* loaded from: classes.dex */
public class ImageSwitcherEngine {

    /* renamed from: b */
    private static ImageSwitcherEngine f5642b = new ImageSwitcherEngine();

    /* renamed from: c */
    private String f5644c;

    /* renamed from: d */
    private int f5645d;

    /* renamed from: a */
    private ArrayList<String> f5643a = new ArrayList<>();

    /* renamed from: e */
    private boolean f5646e = true;

    /* renamed from: f */
    private Handler f5647f = new Handler() { // from class: com.sds.android.ttpod.framework.a.g.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 2) {
                if (!BaseApplication.getApplication().m4627k() && ImageSwitcherEngine.this.f5646e && Player.m2611e().m2604h() == PlayStatus.STATUS_PLAYING) {
                    ImageSwitcherEngine.this.m4720g();
                }
                ImageSwitcherEngine.this.m4719h();
            }
        }
    };

    /* renamed from: g */
    private Preferences.InterfaceC2031a f5648g = new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.framework.a.g.2
        @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
        /* renamed from: a */
        public void mo2553a(PreferencesID preferencesID) {
            boolean m3003aD = Preferences.m3003aD();
            LogUtils.m8386a("ImageSwitcherEngine", "onPreferencesChanged artistPic artistPicPlay=%b", Boolean.valueOf(m3003aD));
            if (m3003aD) {
                ImageSwitcherEngine.this.m4719h();
            } else {
                ImageSwitcherEngine.this.f5647f.removeCallbacksAndMessages(null);
            }
        }
    };

    /* renamed from: a */
    public void m4731a() {
        this.f5646e = true;
    }

    /* renamed from: b */
    public void m4728b() {
        this.f5646e = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m4720g() {
        this.f5645d++;
        int size = this.f5643a.size();
        if (this.f5645d >= size) {
            this.f5645d = 0;
        }
        String str = this.f5645d < size ? this.f5643a.get(this.f5645d) : null;
        Intent intent = new Intent(Action.SWITCH_ARTIST_BITMAP);
        intent.putExtra("media_id", this.f5644c);
        intent.putExtra("path", str);
        BaseApplication.getApplication().sendBroadcast(intent);
    }

    /* renamed from: c */
    public synchronized void m4726c() {
        this.f5643a.clear();
        this.f5647f.removeCallbacksAndMessages(null);
    }

    /* renamed from: d */
    public static ImageSwitcherEngine m4724d() {
        return f5642b;
    }

    /* renamed from: a */
    public synchronized void m4729a(String str, ArrayList<String> arrayList) {
        boolean z = false;
        synchronized (this) {
            MediaItem queryMediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), Preferences.m2858m(), Preferences.m2854n());
            if (queryMediaItem != null && !queryMediaItem.isNull() && StringUtils.m8344a(str, queryMediaItem.getID()) && (!this.f5643a.isEmpty() || (arrayList != null && !arrayList.isEmpty()))) {
                if (StringUtils.m8344a(this.f5644c, str) && arrayList != null && !this.f5643a.isEmpty() && arrayList.containsAll(this.f5643a)) {
                    z = true;
                }
                this.f5644c = str;
                this.f5643a.clear();
                if (arrayList != null) {
                    this.f5643a.addAll(arrayList);
                }
                boolean m3003aD = Preferences.m3003aD();
                if (z) {
                    if (!this.f5647f.hasMessages(2) && m3003aD) {
                        m4719h();
                    }
                } else {
                    this.f5645d = 0;
                    if (m3003aD) {
                        m4719h();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m4719h() {
        this.f5647f.removeCallbacksAndMessages(null);
        if (this.f5643a.size() > 1 && PlatformUtils.m4656a()) {
            this.f5647f.sendMessageDelayed(this.f5647f.obtainMessage(2), 15000L);
        }
    }

    /* renamed from: e */
    public void m4722e() {
        Preferences.m3019a(PreferencesID.ARTIST_PIC_PLAY, this.f5648g);
    }

    /* renamed from: f */
    public void m4721f() {
        Preferences.m2938b(PreferencesID.ARTIST_PIC_PLAY, this.f5648g);
    }
}
