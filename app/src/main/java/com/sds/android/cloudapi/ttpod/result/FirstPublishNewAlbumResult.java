package com.sds.android.cloudapi.ttpod.result;
import com.google.gson.annotations.SerializedName;

import com.sds.android.cloudapi.ttpod.data.FirstPublishAlbumData;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FirstPublishNewAlbumResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    @SerializedName(value = "data")
    private ArrayList<FirstPublishAlbumData> mAlbumList = new ArrayList<>();
    @SerializedName(value = KEY_PAGE_COUNT)
    private int mPageCount = 0;
    @SerializedName(value = KEY_ROWS)
    private int mRows = 0;

    public int getPageCount() {
        return this.mPageCount;
    }

    public ArrayList<FirstPublishAlbumData> getDataList() {
        return this.mAlbumList;
    }

    public int getRows() {
        return this.mRows;
    }
}
