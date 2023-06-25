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
public class AudioEffectChangedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Action.AUDIOEFFECT_CHANGED.equals(intent.getAction())) {
            CommandCenter.getInstance().m4604a(new Command(CommandID.AUDIOEFFECT_CHANGED, new Object[0]), ModuleID.MONITOR);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static IntentFilter m4131a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.AUDIOEFFECT_CHANGED);
        return intentFilter;
    }
}
