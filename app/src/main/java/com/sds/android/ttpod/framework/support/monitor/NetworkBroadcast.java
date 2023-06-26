package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowModule;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.support.SupportService;

/* loaded from: classes.dex */
public class NetworkBroadcast extends BroadcastReceiver {

    /* renamed from: a */
    private static final String f7243a = NetworkBroadcast.class.getSimpleName();

    /* renamed from: b */
    private SupportService f7244b;

    public NetworkBroadcast(SupportService supportService) {
        this.f7244b = supportService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (EnvironmentUtils.DeviceConfig.m8474e() && UnicomFlowUtil.m3946f()) {
            m2241c();
            UnicomFlowUtil.m3954a(context);
            m2242b();
        }
    }

    /* renamed from: b */
    private void m2242b() {
        boolean m3945g = UnicomFlowUtil.m3945g();
        HttpRequest.m8702b(m3945g);
    }

    /* renamed from: c */
    private void m2241c() {
        if (this.f7244b != null) {
            String str = UnicomFlowUtil.m3947e() ? UnicomFlowModule.PROXY_WAP_HOST : UnicomFlowModule.PROXY_HOST;
            boolean m3948d = UnicomFlowUtil.m3948d();
            LogUtils.debug(f7243a, "network change check unicom flow status isUseProxy:" + m3948d);
            this.f7244b.m2804a(str, UnicomFlowModule.HTTP_PROXY_PORT.intValue(), UnicomFlowModule.TCP_PROXY_PORT.intValue(), UnicomFlowModule.USERNAME, UnicomFlowModule.PASSWORD, m3948d);
        }
    }

    /* renamed from: a */
    public static IntentFilter m2243a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        return intentFilter;
    }
}
