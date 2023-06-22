package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.AssociateWordsResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.EnvironmentUtils;

/* renamed from: com.sds.android.cloudapi.ttpod.a.b */
/* loaded from: classes.dex */
public class AssociateWordsAPI {
    /* renamed from: a */
    public static Request<AssociateWordsResult> m8935a(String str) {
        return new GetMethodRequest(AssociateWordsResult.class, "http://so.ard.iyyin.com/suggest.do").m8537b("q", str).m8537b("v", EnvironmentUtils.C0603b.m8491c());
    }
}
