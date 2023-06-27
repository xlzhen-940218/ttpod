package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.core.download.Task;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;

/* loaded from: classes.dex */
public class DownloadStateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Action.DOWNLOAD_TASK_STATE_CHANGED.equals(intent.getAction())) {
            DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) intent.getParcelableExtra("download_task");
            Task.ErrorCodeType enumC0579b = null;
            int intExtra = intent.getIntExtra("download_error", -1);
            if (intExtra != -1) {
                enumC0579b = Task.ErrorCodeType.values()[intExtra];
            }
            CommandCenter.getInstance().m4595b(new Command(CommandID.DOWNLOAD_STATE_CHANGED, downloadTaskInfo, enumC0579b), ModuleID.MONITOR);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static IntentFilter m4128a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.DOWNLOAD_TASK_STATE_CHANGED);
        return intentFilter;
    }
}
