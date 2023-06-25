package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.HashMap;

/* renamed from: com.sds.android.sdk.lib.request.g */
/* loaded from: classes.dex */
public class GetMethodRequest<R extends BaseResult> extends MethodRequest<R> {
    public GetMethodRequest(Class<R> cls, String str, String str2) {
        super(cls, str, str2);
    }

    public GetMethodRequest(Class<R> cls, String str) {
        super(cls, str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.sdk.lib.request.Request
    /* renamed from: a */
    public String mo8547a() {
        return StringUtils.m8342a("/", super.mo8547a(), m8554b());
    }

    @Override // com.sds.android.sdk.lib.request.Request
    /* renamed from: a */
    protected HttpRequest.C0586a mo8541a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        LogUtils.info("GetMethodRequest", "doHttpRequest in lookNetProblem url=%s", str);
        try {
            return HttpRequest.m8713a(str, hashMap, hashMap2);
        } catch (Exception e) {
            LogUtils.info("GetMethodRequest", "doHttpRequest exception lookNetProblem url=%s exception=%s", str, e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
