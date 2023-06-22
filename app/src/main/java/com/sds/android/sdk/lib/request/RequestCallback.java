package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.request.BaseResult;

/* renamed from: com.sds.android.sdk.lib.request.n */
/* loaded from: classes.dex */
public interface RequestCallback<R extends BaseResult> {
    void onRequestFailure(R r);

    void onRequestSuccess(R r);
}
