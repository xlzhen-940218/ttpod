package com.sds.android.sdk.lib.request;

import com.google.gson.annotations.SerializedName;

/* renamed from: com.sds.android.sdk.lib.request.c */
/* loaded from: classes.dex */
public class DataResult<D> extends BaseResult {
    @SerializedName(value = "data")
    private D mData;

    public D getData() {
        return this.mData;
    }

    public void setData(D d) {
        this.mData = d;
    }
}
