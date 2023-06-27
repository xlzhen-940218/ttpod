package com.sds.android.cloudapi.ttpod.api;

import com.sds.android.cloudapi.ttpod.result.AssociateWordsResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.EnvironmentUtils;

/* renamed from: com.sds.android.cloudapi.ttpod.a.b */
/* loaded from: classes.dex */
public class AssociateWordsAPI {
    /* renamed from: a */
    public static Request<AssociateWordsResult> m8935a(String str) {
        return new GetMethodRequest(AssociateWordsResult.class, "http://so.ard.iyyin.com/suggest.do").putParams("q", str).putParams("v", EnvironmentUtils.UUIDConfig.m8491c());
    }
}
