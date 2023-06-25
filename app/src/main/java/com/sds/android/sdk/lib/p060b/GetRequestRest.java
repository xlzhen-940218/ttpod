package com.sds.android.sdk.lib.p060b;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.LogUtils;
import java.util.HashMap;

/* renamed from: com.sds.android.sdk.lib.b.d */
/* loaded from: classes.dex */
public class GetRequestRest extends RequestRest {
    public GetRequestRest(String str) {
        super(str);
    }

    @Override // com.sds.android.sdk.lib.p060b.RequestRest
    /* renamed from: a */
    protected HttpRequest.C0586a mo8669a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        LogUtils.info("GetRequestRest", "doHttpRequest in lookNetProblem url=%s", str);
        try {
            return HttpRequest.m8713a(str, hashMap, hashMap2);
        } catch (Exception e) {
            LogUtils.info("GetRequestRest", "doHttpRequest exception lookNetProblem url=%s exception=%s", str, e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
