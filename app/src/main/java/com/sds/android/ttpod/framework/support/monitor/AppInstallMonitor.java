package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.ThirdParty.ThirdPartyApp;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.framework.p106a.p107a.StatisticUtils;
import com.sds.android.ttpod.framework.p106a.p107a.UpdateStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* loaded from: classes.dex */
public class AppInstallMonitor extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.PACKAGE_ADDED".equals(intent.getAction()) && intent.getData() != null) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (!m2263a(schemeSpecificPart)) {
                if (VersionUpdateConst.PACKAGENAME_360.equals(schemeSpecificPart)) {
                    UpdateStatistic.m4796d();
                } else if (VersionUpdateConst.PACKAGENAME_WANDOUJIA.equals(schemeSpecificPart)) {
                    UpdateStatistic.m4794f();
                } else if (VersionUpdateConst.PACKAGENAME_BAIDU.equals(schemeSpecificPart)) {
                    UpdateStatistic.m4795e();
                } else if (VersionUpdateConst.PACKAGENAME_HIAPK.equals(schemeSpecificPart)) {
                    UpdateStatistic.m4793g();
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m2263a(String str) {
        String m2999aH = Preferences.m2999aH();
        if (StringUtils.m8346a(m2999aH)) {
            return false;
        }
        ThirdPartyApp thirdPartyApp = (ThirdPartyApp) JSONUtils.fromJson(m2999aH, ThirdPartyApp.class);
        if (thirdPartyApp != null && m2262a(thirdPartyApp.m8326a(), str) && m2264a(thirdPartyApp.m8321f()) && thirdPartyApp.m8320g()) {
            StatisticUtils.m4908a(thirdPartyApp.m8325b(), thirdPartyApp.m8324c(), thirdPartyApp.m8323d(), 1L, thirdPartyApp.m8319h());
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public boolean m2262a(String str, String str2) {
        if (StringUtils.m8346a(str)) {
            return false;
        }
        for (String str3 : str.split(",")) {
            if (str3.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private boolean m2264a(long j) {
        return ((double) (System.currentTimeMillis() - j)) < 300000.0d;
    }

    /* renamed from: a */
    public static IntentFilter m2265a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        return intentFilter;
    }
}
