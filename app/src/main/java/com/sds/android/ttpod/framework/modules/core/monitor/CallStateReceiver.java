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
        String stringExtra = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = intent.getStringExtra("incoming_number");
        }
        int callState = 0;
        String stateStr = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(stateStr)) {
            callState = TelephonyManager.CALL_STATE_RINGING;
        } else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(stateStr)) {
            callState = TelephonyManager.CALL_STATE_OFFHOOK;
        } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(stateStr)) {
            callState = TelephonyManager.CALL_STATE_IDLE;
        } else {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    callState = telephonyManager.getCallState();
                }
            } catch (Throwable th) {
                callState = 0;
            }
        }
        if (this.f6077b != null) {
            this.f6077b.onCallStateChanged(callState, stringExtra);
        }
        m4129a(callState);
    }

    /* renamed from: a */
    private void m4129a(int i) {
        //new //SSystemEvent("SYS_PHONE_STATE", String.valueOf(i)).append("process_id", Integer.valueOf(Process.myPid())).post();
    }
}
