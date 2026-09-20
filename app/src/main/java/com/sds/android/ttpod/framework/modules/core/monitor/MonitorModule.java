package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import java.lang.reflect.Method;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.core.monitor.b */
/* loaded from: classes.dex */
public final class MonitorModule extends BaseModule {

    /* renamed from: a */
    private SDCardMountReceiver f6078a;

    /* renamed from: b */
    private SystemMediaScanStartedReceiver f6079b;

    /* renamed from: c */
    private NetworkTypeChangeReceiver f6080c;

    /* renamed from: d */
    private PushClientIdReceiver f6081d;

    /* renamed from: e */
    private CallStateReceiver f6082e;

    /* renamed from: f */
    private LockScreenReceiver f6083f;

    /* renamed from: g */
    private SearchEventReceiver searchEventReceiver;

    /* renamed from: h */
    private DownloadStateReceiver f6085h;

    /* renamed from: i */
    private HeadsetPlugReceiver f6086i;

    /* renamed from: j */
    private AudioEffectChangedReceiver f6087j;

    /* renamed from: k */
    private PhoneStateListener f6088k = new PhoneStateListener() { // from class: com.sds.android.ttpod.framework.modules.core.monitor.b.1

        /* renamed from: b */
        private int f6090b = -1;

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            if (i != this.f6090b) {
                LogUtils.debug("MonitorModule", "onCallStateChanged");
                this.f6090b = i;
                switch (i) {
                    case 1:
                        MonitorModule.sContext.sendBroadcast(new Intent(Action.CALL_STATE_RINGING));
                        return;
                    default:
                        return;
                }
            }
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.MONITOR;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        this.f6078a = new SDCardMountReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6078a, SDCardMountReceiver.m4124a());
        this.f6079b = new SystemMediaScanStartedReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6079b, SystemMediaScanStartedReceiver.m4122a());
        this.f6080c = new NetworkTypeChangeReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6080c, NetworkTypeChangeReceiver.m4126a());
        this.f6081d = new PushClientIdReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6081d, PushClientIdReceiver.m4125a());
        this.f6082e = new CallStateReceiver(this.f6088k);
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6082e, CallStateReceiver.m4130a());
        this.f6083f = new LockScreenReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6083f, LockScreenReceiver.m4121a());
        this.searchEventReceiver = new SearchEventReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.searchEventReceiver, SearchEventReceiver.m4123a());
        this.f6085h = new DownloadStateReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6085h, DownloadStateReceiver.m4128a());
        this.f6086i = new HeadsetPlugReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6086i, HeadsetPlugReceiver.m4127a());
        this.f6087j = new AudioEffectChangedReceiver();
        com.sds.android.ttpod.framework.base.ReceiverUtils.registerReceiver(sContext, this.f6087j, AudioEffectChangedReceiver.m4131a());
        m4119b();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onDestroy() {
        super.onDestroy();
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6079b);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6078a);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6080c);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6081d);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6082e);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6083f);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.searchEventReceiver);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6085h);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6086i);
        com.sds.android.ttpod.framework.base.ReceiverUtils.unregisterReceiver(sContext, this.f6087j);
        m4118c();
    }

    /* renamed from: b */
    private void m4119b() {
        try {
            ((TelephonyManager) sContext.getSystemService("phone")).listen(this.f6088k, 32);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m4118c() {
        try {
            ((TelephonyManager) sContext.getSystemService("phone")).listen(this.f6088k, 0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
