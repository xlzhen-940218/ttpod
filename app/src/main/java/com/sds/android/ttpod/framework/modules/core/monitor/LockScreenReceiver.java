package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;

/* renamed from: com.sds.android.ttpod.framework.modules.core.monitor.a */
/* loaded from: classes.dex */
class LockScreenReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        CommandCenter.getInstance().execute(new Command(CommandID.RECEIVED_LOCK_SCREEN_ACTION, intent));
    }

    /* renamed from: a */
    public static IntentFilter m4121a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction(Action.STOP_LOCK_SCREEN);
        intentFilter.addAction(Action.START_ENTRY);
        intentFilter.addAction(Action.CALL_STATE_RINGING);
        return intentFilter;
    }
}
