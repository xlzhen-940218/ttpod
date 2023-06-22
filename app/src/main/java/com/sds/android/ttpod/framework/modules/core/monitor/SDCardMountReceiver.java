package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.TTPodConfig;

/* loaded from: classes.dex */
public class SDCardMountReceiver extends BroadcastReceiver {
    /* renamed from: a */
    public static IntentFilter m4124a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme(FeedbackMessage.TYPE_FILE);
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            LogUtils.m8381c("SDCardMountReceiver", action);
            TTPodConfig.m5307a(true);
        } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
            LogUtils.m8381c("SDCardMountReceiver", action);
        }
    }
}
