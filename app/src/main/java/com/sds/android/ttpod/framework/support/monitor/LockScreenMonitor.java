package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.lib.util.DebugUtils;

/* loaded from: classes.dex */
public class LockScreenMonitor extends BroadcastReceiver {

    /* renamed from: a */
    private InterfaceC2082a f7239a;

    /* renamed from: com.sds.android.ttpod.framework.support.monitor.LockScreenMonitor$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2082a {
        /* renamed from: a */
        void mo2255a(int i);
    }

    public LockScreenMonitor(InterfaceC2082a interfaceC2082a) {
        DebugUtils.m8426a(interfaceC2082a, "screenStateChangeListener");
        this.f7239a = interfaceC2082a;
    }

    /* renamed from: a */
    public static IntentFilter m2256a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.SCREEN_OFF".equals(action)) {
            this.f7239a.mo2255a(0);
        } else if ("android.intent.action.SCREEN_ON".equals(action)) {
            this.f7239a.mo2255a(1);
        }
    }
}
