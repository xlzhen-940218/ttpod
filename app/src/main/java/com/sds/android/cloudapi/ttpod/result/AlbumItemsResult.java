package com.sds.android.cloudapi.ttpod.result;


import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.sdk.lib.request.DataListResult;

/* loaded from: classes.dex */
public class AlbumItemsResult extends DataListResult<AlbumItem> {
    @SerializedName(value = "all_page")
    private int mAllPage;
    @SerializedName(value = "count")
    private int mCount;

    public int getCount() {
        return this.mCount;
    }

    public int getAllPage() {
        return this.mAllPage;
    }
}
