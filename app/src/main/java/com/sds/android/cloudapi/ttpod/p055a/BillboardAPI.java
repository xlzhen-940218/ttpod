package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.BillboardsResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;

/* renamed from: com.sds.android.cloudapi.ttpod.a.c */
/* loaded from: classes.dex */
public class BillboardAPI {
    /* renamed from: a */
    public static Request<BillboardsResult> m8934a(int i) {
        return new GetMethodRequest(BillboardsResult.class, "http://so.ard.iyyin.com/sug", "billboard").putParams("size", Integer.valueOf(i));
    }
}
