package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.LyricItem;
import com.sds.android.sdk.lib.request.DataListResult;

/* loaded from: classes.dex */
public class LyricResult extends DataListResult<LyricItem> {
    @SerializedName(value = "code")
    private int mCode;
    @SerializedName(value = "pages")
    private int mPages;
    @SerializedName(value = "rows")
    private int mRows;

    public void setPages(int i) {
        this.mPages = i;
    }

    public void setRows(int i) {
        this.mRows = i;
    }

    @Override // com.sds.android.sdk.lib.request.BaseResult
    public int getCode() {
        return this.mCode;
    }

    @Override // com.sds.android.sdk.lib.request.BaseResult
    public void setCode(int i) {
        this.mCode = i;
    }

    public int getPages() {
        return this.mPages;
    }

    public int getRows() {
        return this.mRows;
    }
}
