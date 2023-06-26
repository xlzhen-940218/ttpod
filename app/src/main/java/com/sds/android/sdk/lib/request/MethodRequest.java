package com.sds.android.sdk.lib.request;

/* renamed from: com.sds.android.sdk.lib.request.i */
/* loaded from: classes.dex */
public abstract class MethodRequest<R extends BaseResult> extends Request<R> {

    /* renamed from: a */
    private String method;

    public MethodRequest(Class<R> cls, String url, String method) {
        super(cls, url);
        this.method = method;
    }

    /* renamed from: b */
    public String getMethod() {
        return this.method;
    }
}
