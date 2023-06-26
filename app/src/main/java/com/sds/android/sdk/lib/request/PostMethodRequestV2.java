package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.HashMap;

/* renamed from: com.sds.android.sdk.lib.request.l */
/* loaded from: classes.dex */
public class PostMethodRequestV2<R extends BaseResult> extends PostMethodRequest<R> {
    public PostMethodRequestV2(Class<R> cls, String str, String str2) {
        super(cls, str, str2);
    }

    public PostMethodRequestV2(Class<R> cls, String str) {
        super(cls, str);
    }

    @Override // com.sds.android.sdk.lib.request.PostMethodRequest, com.sds.android.sdk.lib.request.Request
    /* renamed from: a */
    protected HttpRequest.Response doHttpRequest(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        try {
            return HttpRequest.m8703b(str, hashMap, hashMap2);
        } catch (Exception e) {
            LogUtils.error("PostMethodRequestV2", "%s create arguments error, cause by %s", e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.sdk.lib.request.Request
    /* renamed from: c */
    public String mo8536c() {
        String b = getMethod();
        String c = super.mo8536c();
        if (!StringUtils.isEmpty(b)) {
            return StringUtils.spliceStringAndArray("/", c, b);
        }
        return c;
    }
}
