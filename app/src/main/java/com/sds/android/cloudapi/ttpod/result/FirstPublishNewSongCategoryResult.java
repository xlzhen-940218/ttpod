package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FirstPublishNewSongCategoryResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    @SerializedName(value = "data")
    private ArrayList<MusicCircleFirstPublish> mSingleList = new ArrayList<>();
    @SerializedName(value = KEY_PAGE_COUNT)
    private int mPageCount = 0;
    @SerializedName(value = KEY_ROWS)
    private int mRows = 0;

    public int getPageCount() {
        return this.mPageCount;
    }

    public ArrayList<MusicCircleFirstPublish> getSingleList() {
        return this.mSingleList;
    }

    public int getRows() {
        return this.mRows;
    }
}
