package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.AroundUserListResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;


/* renamed from: com.sds.android.cloudapi.ttpod.a.w */
/* loaded from: classes.dex */
public class ShakeAPI {
    /* renamed from: a */
    public static Request<AroundUserListResult> m8831a(String str, float f, float f2) {
        return new GetMethodRequest(AroundUserListResult.class, "http://v1.ard.yy.itlily.com/shake").putParams("access_token", str).putParams("lng", Float.valueOf(f)).putParams("lat", Float.valueOf(f2));
    }
}
