package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.UnicomFlowResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.StringUtils;

/* renamed from: com.sds.android.cloudapi.ttpod.a.z */
/* loaded from: classes.dex */
public class UnicomFlowAPI {
    /* renamed from: a */
    public static Request<UnicomFlowResult> m8823a() {
        return new GetMethodRequest(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/config");
    }

    /* renamed from: a */
    public static Request<UnicomFlowResult> m8820a(String str, String str2) {
        GetMethodRequest getMethodRequest = new GetMethodRequest(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/trial");
        getMethodRequest.m8537b("phone", str);
        getMethodRequest.m8537b("verify_code", str2);
        return getMethodRequest;
    }

    /* renamed from: a */
    public static Request<UnicomFlowResult> m8819a(String str, String str2, String str3, String str4) {
        GetMethodRequest getMethodRequest = new GetMethodRequest(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/open");
        getMethodRequest.m8537b("phone", str);
        getMethodRequest.m8537b("imsi", str4);
        if (!StringUtils.isEmpty(str2)) {
            getMethodRequest.m8537b("verify_code", str2);
        }
        if (!StringUtils.isEmpty(str3)) {
            getMethodRequest.m8537b("token", str3);
        }
        return getMethodRequest;
    }

    /* renamed from: a */
    public static Request<UnicomFlowResult> m8822a(String str, int i) {
        GetMethodRequest getMethodRequest = new GetMethodRequest(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/sendcode");
        getMethodRequest.m8537b("phone", str);
        getMethodRequest.m8537b("type", Integer.valueOf(i));
        return getMethodRequest;
    }

    /* renamed from: b */
    public static Request<UnicomFlowResult> m8818b(String str, String str2) {
        GetMethodRequest getMethodRequest = new GetMethodRequest(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/check");
        getMethodRequest.m8537b("phone", str);
        getMethodRequest.m8537b("imsi", str2);
        return getMethodRequest;
    }

    /* renamed from: a */
    public static Request<UnicomFlowResult> m8821a(String str, int i, String str2, String str3) {
        GetMethodRequest getMethodRequest = new GetMethodRequest(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/unsubscribe");
        getMethodRequest.m8537b("phone", str);
        getMethodRequest.m8537b("feedback_msg", str2);
        getMethodRequest.m8537b("feedback_id", Integer.valueOf(i));
        getMethodRequest.m8537b("verify_code", str3);
        return getMethodRequest;
    }

    /* renamed from: c */
    public static Request<UnicomFlowResult> m8817c(String str, String str2) {
        GetMethodRequest getMethodRequest = new GetMethodRequest(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/auth/token");
        getMethodRequest.m8537b("unikey", str);
        if (!StringUtils.isEmpty(str2)) {
            getMethodRequest.m8537b("imsi", str2);
        }
        return getMethodRequest;
    }
}
