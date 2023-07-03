package com.sds.android.sdk.lib.p060b;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import java.util.HashMap;

/* renamed from: com.sds.android.sdk.lib.b.e */
/* loaded from: classes.dex */
public class PostRequestRest extends RequestRest {

    /* renamed from: a */
    private String f2352a;

    public PostRequestRest(String str, String str2) {
        super(str);
        this.f2352a = str2;
    }

    @Override // com.sds.android.sdk.lib.p060b.RequestRest
    /* renamed from: a */
    protected HttpRequest.Response doHttpRequest(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        return HttpRequest.m8714a(str, hashMap, this.f2352a);
    }
}
