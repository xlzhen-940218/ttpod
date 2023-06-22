package com.sds.android.ttpod.framework.support;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.p134a.MediaSelector;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.support.a */
/* loaded from: classes.dex */
public final class FastSwitchSupport extends Support {

    /* renamed from: k */
    private MediaSelector f7003k;

    /* renamed from: l */
    private PlayStatus f7004l;

    /* renamed from: m */
    private Handler f7005m;

    /* renamed from: n */
    private Runnable f7006n;

    public FastSwitchSupport(Context context) {
        super(context);
        this.f7005m = new Handler(Looper.getMainLooper());
        this.f7006n = new Runnable() { // from class: com.sds.android.ttpod.framework.support.a.1
            @Override // java.lang.Runnable
            public void run() {
                FastSwitchSupport.this.f7135c.startService(new Intent(FastSwitchSupport.this.f7135c, SupportService.class).putExtra("command", "play_command").putExtras(FastSwitchSupport.this.m2766A()));
            }
        };
    }

    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: a */
    public void mo2497a(SupportCallback supportCallback) {
        super.mo2497a(supportCallback);
        if (this.f7003k == null) {
            this.f7003k = new MediaSelector(this.f7135c);
        }
    }

    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: a */
    public void mo2505a() {
        if (this.f7137e == null) {
            this.f7135c.bindService(new Intent(this.f7135c, SupportService.class), this.f7142j, Context.BIND_AUTO_CREATE);
            LogUtils.m8379d("Support", "音效：重现绑定service");
        }
        if (MediaStorage.queryMediaItem(BaseApplication.getApplication(), Preferences.m2858m(), Preferences.m2854n()) == null) {
            this.f7003k.m2652e();
        }
        Iterator<SupportCallback> it = this.f7138f.iterator();
        while (it.hasNext()) {
            it.next().mo2446a(this.f7003k.m2655b());
        }
        m2477c(m2490a("start_command").putExtras(m2766A()));
    }

    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: a */
    public void mo2489a(String str, String str2) {
        this.f7003k.m2657a(str, str2);
        this.f7135c.startService(new Intent(this.f7135c, SupportService.class).putExtra("command", "play_command").putExtra("group", str).putExtra("media_source", str2).putExtras(m2766A()));
    }

    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: b */
    public void mo2479b(String str, String str2) {
        this.f7003k.m2657a(str, str2);
        this.f7135c.startService(new Intent(this.f7135c, SupportService.class).putExtra("command", "sync_command").putExtra("group", str).putExtra("media_source", str2).putExtras(m2766A()));
    }

    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: c */
    public void mo2474c(String str, String str2) {
        MediaSelector mediaSelector = this.f7003k;
        if (str2 == null) {
            str2 = Preferences.m2854n();
        }
        mediaSelector.m2657a(str, str2);
    }

    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: b */
    public void mo2485b() {
        this.f7003k.m2654c();
        m2764z();
        Iterator<SupportCallback> it = this.f7138f.iterator();
        while (it.hasNext()) {
            it.next().mo2446a(this.f7003k.m2655b());
        }
        this.f7005m.removeCallbacks(this.f7006n);
        this.f7005m.postDelayed(this.f7006n, 300L);
    }

    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: c */
    public void mo2478c() {
        this.f7003k.m2653d();
        m2764z();
        Iterator<SupportCallback> it = this.f7138f.iterator();
        while (it.hasNext()) {
            it.next().mo2446a(this.f7003k.m2655b());
        }
        this.f7005m.removeCallbacks(this.f7006n);
        this.f7005m.postDelayed(this.f7006n, 300L);
    }

    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: a */
    protected void mo2502a(Intent intent) {
        MediaItem mediaItem = (MediaItem) intent.getExtras().get("mediaItem");
        this.f7003k.m2659a(mediaItem);
        Iterator<SupportCallback> it = this.f7138f.iterator();
        while (it.hasNext()) {
            it.next().mo2446a(mediaItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.support.Support
    /* renamed from: b */
    public void mo2483b(Intent intent) {
        this.f7004l = PlayStatus.values()[intent.getIntExtra("play_status", PlayStatus.STATUS_STOPPED.ordinal())];
        super.mo2483b(intent);
    }

    /* renamed from: z */
    private void m2764z() {
        if (this.f7004l == PlayStatus.STATUS_PLAYING) {
            m2470f();
            this.f7004l = PlayStatus.STATUS_STOPPED;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A */
    public Bundle m2766A() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("mediaItem", this.f7003k.m2655b());
        return bundle;
    }
}
