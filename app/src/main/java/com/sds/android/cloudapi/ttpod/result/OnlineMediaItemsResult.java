package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.request.ExtraDataListResult;

/* loaded from: classes.dex */
public class OnlineMediaItemsResult extends ExtraDataListResult<OnlineMediaItem> {
    @SerializedName(value = "pages")
    private int mPages;
    @SerializedName(value = "rows")
    private int mRows;

    public int getPages() {
        return this.mPages;
    }

    public int getCount() {
        return this.mRows;
    }
}
