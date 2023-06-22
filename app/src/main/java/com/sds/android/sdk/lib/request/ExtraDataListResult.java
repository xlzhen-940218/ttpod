package com.sds.android.sdk.lib.request;

import com.google.gson.annotations.SerializedName;

/* renamed from: com.sds.android.sdk.lib.request.e */
/* loaded from: classes.dex */
public class ExtraDataListResult<D> extends DataListResult<D> {
    @SerializedName(value = "extra")
    private Extra mExtra = new Extra();

    public Extra getExtra() {
        return this.mExtra;
    }
}
