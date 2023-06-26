package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.p059a.HttpRequest;

import java.util.HashMap;

/* renamed from: com.sds.android.sdk.lib.request.j */
/* loaded from: classes.dex */
public class PostContentRequest<R extends BaseResult> extends Request<R> {

    /* renamed from: a */
    private String f2418a;

    public PostContentRequest(Class<R> cls, String str, String str2) {
        super(cls, str);
        this.f2418a = str2;
    }

    @Override // com.sds.android.sdk.lib.request.Request
    /* renamed from: a */
    protected HttpRequest.Response mo8541a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        return HttpRequest.m8714a(str, hashMap, this.f2418a);
    }
}
