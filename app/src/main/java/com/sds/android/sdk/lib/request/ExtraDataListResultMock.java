package com.sds.android.sdk.lib.request;


import com.google.gson.annotations.SerializedName;

/* renamed from: com.sds.android.sdk.lib.request.f */
/* loaded from: classes.dex */
public class ExtraDataListResultMock<D> extends ExtraDataListResult<D> {
    @SerializedName(value = "pages")
    private int mPages;
    @SerializedName(value = "rows")
    private int mRows;

    @Override // com.sds.android.sdk.lib.request.ExtraDataListResult
    public Extra getExtra() {
        return new Extra(this.mPages, this.mRows);
    }
}
