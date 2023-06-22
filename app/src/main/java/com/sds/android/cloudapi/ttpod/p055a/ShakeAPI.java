package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.AroundUserListResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;


/* renamed from: com.sds.android.cloudapi.ttpod.a.w */
/* loaded from: classes.dex */
public class ShakeAPI {
    /* renamed from: a */
    public static Request<AroundUserListResult> m8831a(String str, float f, float f2) {
        return new GetMethodRequest(AroundUserListResult.class, "http://v1.ard.yy.itlily.com/shake").m8537b("access_token", str).m8537b("lng", Float.valueOf(f)).m8537b("lat", Float.valueOf(f2));
    }
}
