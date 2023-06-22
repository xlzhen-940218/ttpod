package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.sds.android.ttpod.framework.modules.core.monitor.CallStateReceiver;

/* renamed from: com.sds.android.ttpod.framework.support.monitor.a */
/* loaded from: classes.dex */
public class CallMonitor {

    /* renamed from: c */
    private InterfaceC2086a f7247c;

    /* renamed from: d */
    private boolean f7248d = false;

    /* renamed from: e */
    private int f7249e = -1;

    /* renamed from: b */
    private final PhoneStateListener f7246b = new PhoneStateListener() { // from class: com.sds.android.ttpod.framework.support.monitor.a.1
        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            if (CallMonitor.this.f7249e != i) {
                CallMonitor.this.f7249e = i;
                if (CallMonitor.this.f7247c != null) {
                    if (i == 0) {
                        CallMonitor.this.f7247c.mo2234a();
                    } else {
                        CallMonitor.this.f7247c.mo2233b();
                    }
                }
            }
        }
    };

    /* renamed from: a */
    private final BroadcastReceiver f7245a = new CallStateReceiver(this.f7246b);

    /* compiled from: CallMonitor.java */
    /* renamed from: com.sds.android.ttpod.framework.support.monitor.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2086a {
        /* renamed from: a */
        void mo2234a();

        /* renamed from: b */
        void mo2233b();
    }

    /* renamed from: a */
    public boolean m2239a(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getCallState() == 0;
    }

    /* renamed from: a */
    public void m2238a(Context context, InterfaceC2086a interfaceC2086a) {
        this.f7247c = interfaceC2086a;
        if (this.f7247c != null && !this.f7248d) {
            this.f7248d = true;
            context.registerReceiver(this.f7245a, CallStateReceiver.m4130a());
            ((TelephonyManager) context.getSystemService("phone")).listen(this.f7246b, 32);
        } else if (this.f7247c == null && this.f7248d) {
            this.f7248d = false;
            context.unregisterReceiver(this.f7245a);
            ((TelephonyManager) context.getSystemService("phone")).listen(this.f7246b, 0);
            m2240a();
        }
    }

    /* renamed from: a */
    private void m2240a() {
        this.f7249e = -1;
    }
}
