package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.GlobalResult;
import com.sds.android.cloudapi.ttpod.result.OperatorPageResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import java.util.Map;

/* renamed from: com.sds.android.cloudapi.ttpod.a.k */
/* loaded from: classes.dex */
public class GlobalAPI {
    /* renamed from: a */
    public static Request<GlobalResult> m8887a() {
        GetMethodRequest getMethodRequest = new GetMethodRequest(GlobalResult.class, "http://client.api.ttpod.com/global");
        getMethodRequest.m8540a((Map<String, Object>) EnvironmentUtils.UUIDConfig.m8488e());
        getMethodRequest.m8545a(new Request.InterfaceC0601a() { // from class: com.sds.android.cloudapi.ttpod.a.k.1
            @Override // com.sds.android.sdk.lib.request.Request.InterfaceC0601a
            /* renamed from: a */
            public String mo8527a(String str) {
                if (str != null && str.startsWith("538ab")) {
                    return SecurityUtils.C0611c.m8355a(str);
                }
                return str;
            }
        });
        return getMethodRequest;
    }

    /* renamed from: a */
    public static Request<OperatorPageResult> m8886a(String str, String str2) {
        return new GetMethodRequest(OperatorPageResult.class, "http://api.busdh.com/market-api").m8542a("appgame/global").putParams("f", str).putParams("v", str2);
    }
}
