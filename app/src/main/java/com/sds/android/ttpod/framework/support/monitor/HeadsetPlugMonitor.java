package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.sdk.core.statistic.SSystemEvent;

/* loaded from: classes.dex */
public class HeadsetPlugMonitor extends BroadcastReceiver {

    /* renamed from: a */
    private static final long f7237a = System.currentTimeMillis();

    /* renamed from: b */
    private InterfaceC2081a f7238b;

    /* renamed from: com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2081a {
        /* renamed from: c */
        void mo2258c();

        /* renamed from: d */
        void mo2257d();
    }

    /* renamed from: a */
    public static IntentFilter m2261a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(VIPPolicy.Entry.MAX_LIMIT);
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
        return intentFilter;
    }

    /* renamed from: a */
    public void m2260a(InterfaceC2081a interfaceC2081a) {
        this.f7238b = interfaceC2081a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (this.f7238b != null) {
            if ("android.intent.action.HEADSET_PLUG".equals(action)) {
                if (System.currentTimeMillis() - f7237a > 1500) {
                    if (intent.getIntExtra("state", -1) == 1) {
                        this.f7238b.mo2257d();
                        m2259a("headset_plugged");
                        return;
                    }
                    this.f7238b.mo2258c();
                    m2259a("headset_unplugged");
                }
            } else if ("android.media.AUDIO_BECOMING_NOISY".equals(action)) {
                this.f7238b.mo2258c();
                m2259a("headset_unplugged");
            }
        }
    }

    /* renamed from: a */
    private void m2259a(String str) {
        new SSystemEvent("SYS_HEADSET", str).post();
    }
}
