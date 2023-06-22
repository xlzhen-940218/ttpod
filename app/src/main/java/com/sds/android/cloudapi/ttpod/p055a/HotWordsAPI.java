package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.HotwordsResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;

/* renamed from: com.sds.android.cloudapi.ttpod.a.m */
/* loaded from: classes.dex */
public class HotWordsAPI {
    /* renamed from: a */
    public static Request<HotwordsResult> m8884a() {
        return new GetMethodRequest(HotwordsResult.class, "http://ayyc.ttpod.com").m8542a("search_words");
    }
}
