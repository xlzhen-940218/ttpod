package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;

/* loaded from: classes.dex */
public class SearchEventReceiver extends BroadcastReceiver {
    /* renamed from: a */
    public static IntentFilter m4123a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.LYRIC_PIC_OPERATE_RESULT);
        intentFilter.addAction(Action.SWITCH_ARTIST_BITMAP);
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        CommandCenter.getInstance().execute(new Command(CommandID.RECEIVED_SEARCH_EVENT, intent));
    }
}
