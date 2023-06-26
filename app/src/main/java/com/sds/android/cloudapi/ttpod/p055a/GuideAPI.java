package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.GuideResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;

/* renamed from: com.sds.android.cloudapi.ttpod.a.l */
/* loaded from: classes.dex */
public class GuideAPI {
    /* renamed from: a */
    public static Request<GuideResult> m8885a(int i, String str, String str2, int i2) {
        GetMethodRequest getMethodRequest = new GetMethodRequest(GuideResult.class, "http://api.busdh.com/market-api/splash/log");
        getMethodRequest.putParams("app_id", Integer.valueOf(i));
        getMethodRequest.putParams("f", str);
        getMethodRequest.putParams("v", str2);
        getMethodRequest.putParams("code", Integer.valueOf(i2));
        return getMethodRequest;
    }
}
