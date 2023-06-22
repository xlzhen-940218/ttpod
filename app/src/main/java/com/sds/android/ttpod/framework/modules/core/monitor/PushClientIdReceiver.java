package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.DateUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.StatisticUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;


/* loaded from: classes.dex */
public class PushClientIdReceiver extends BroadcastReceiver {
    /* renamed from: a */
    public static IntentFilter m4125a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.PUSH_CLIENT_ID_BROADCAST);
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Action.PUSH_CLIENT_ID_BROADCAST)) {
            String stringExtra = intent.getStringExtra("client_id");
            if (!StringUtils.m8346a(stringExtra)) {
                String m8431a = DateUtils.m8431a(0, "-");
                if (!StringUtils.m8344a(m8431a, Preferences.m2946ay())) {
                    StatisticUtils.m4907a("push", "login", "gexin", 0L, 0L, stringExtra, null);
                    new SUserEvent("PAGE_CLICK", SAction.ACTION_PUSH_RECEIVE_CID.getValue(), SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue()).append("cid", stringExtra).post();
                    Preferences.m2856m(m8431a);
                }
                if (!StringUtils.m8344a(stringExtra, Preferences.m2945az())) {
                    Preferences.m2852n(stringExtra);
                }
            }
        }
    }
}
