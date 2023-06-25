package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;

/* loaded from: classes.dex */
public class HeadsetPlugReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Action.PLAY_HEADSET_PLUG.equals(action)) {
            CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_HEADSET_PLUGGED, new Object[0]), ModuleID.MONITOR);
        } else if (Action.PLAY_HEADSET_UNPLUG.equals(action)) {
            CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_HEADSET_UNPLUGGED, new Object[0]), ModuleID.MONITOR);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static IntentFilter m4127a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.PLAY_HEADSET_PLUG);
        intentFilter.addAction(Action.PLAY_HEADSET_UNPLUG);
        return intentFilter;
    }
}
