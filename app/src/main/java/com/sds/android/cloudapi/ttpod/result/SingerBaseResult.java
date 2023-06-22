package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;

/* loaded from: classes.dex */
public class SingerBaseResult extends BaseResult {
    @SerializedName(value = "pageCount")
    private int mPageCount;
    @SerializedName(value = "totalCount")
    private int mTotalCount;

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public int getPageCount() {
        return this.mPageCount;
    }
}
