package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


/* loaded from: classes.dex */
public class CallStateReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static final String f6076a = CallStateReceiver.class.getSimpleName();

    /* renamed from: b */
    private PhoneStateListener f6077b;

    /* renamed from: a */
    public static IntentFilter m4130a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        return intentFilter;
    }

    public CallStateReceiver(PhoneStateListener phoneStateListener) {
        this.f6077b = phoneStateListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String stringExtra = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = intent.getStringExtra("incoming_number");
        }
        int callState = telephonyManager.getCallState();
        this.f6077b.onCallStateChanged(callState, stringExtra);
        m4129a(callState);
    }

    /* renamed from: a */
    private void m4129a(int i) {
        //new //SSystemEvent("SYS_PHONE_STATE", String.valueOf(i)).append("process_id", Integer.valueOf(Process.myPid())).post();
    }
}
