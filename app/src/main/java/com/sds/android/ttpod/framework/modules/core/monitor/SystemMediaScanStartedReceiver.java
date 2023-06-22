package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.Set;

/* loaded from: classes.dex */
public class SystemMediaScanStartedReceiver extends BroadcastReceiver {
    /* renamed from: a */
    public static IntentFilter m4122a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentFilter.addDataScheme(FeedbackMessage.TYPE_FILE);
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtils.m8381c("SystemMediaScanStartedReceiver", "receive:" + intent.getAction());
        if ("android.intent.action.MEDIA_SCANNER_STARTED".equals(intent.getAction())) {
            try {
                Set<String> m2866k = Preferences.m2866k();
                if (m2866k != null && m2866k.size() > 0) {
                    CommandCenter.m4607a().m4606a(new Command(CommandID.START_SCAN, m2866k, MediaStorage.GROUP_ID_ALL_LOCAL));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
