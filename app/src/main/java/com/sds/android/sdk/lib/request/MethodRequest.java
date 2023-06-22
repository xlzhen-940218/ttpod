package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.request.BaseResult;

/* renamed from: com.sds.android.sdk.lib.request.i */
/* loaded from: classes.dex */
public abstract class MethodRequest<R extends BaseResult> extends Request<R> {

    /* renamed from: a */
    private String f2417a;

    public MethodRequest(Class<R> cls, String str, String str2) {
        super(cls, str);
        this.f2417a = str2;
    }

    /* renamed from: b */
    public String m8554b() {
        return this.f2417a;
    }
}
