package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class AppHotWords {
    public static final int SEARCH_RANKING_DOWN = -1;
    public static final int SEARCH_RANKING_KEEP = 0;
    public static final int SEARCH_RANKING_NEW = 2;
    public static final int SEARCH_RANKING_UP = 1;
    @SerializedName(value = "ascend")
    private int mAscend;
    @SerializedName(value = "ascend_num")
    private int mAscendNum;
    @SerializedName(value = "id")
    private int mId;
    @SerializedName(value = "search_key_name")
    private String mSearchKeyName;

    public int getId() {
        return this.mId;
    }

    public String getSearchKeyName() {
        return this.mSearchKeyName;
    }

    public int getAscend() {
        return this.mAscend;
    }

    public int getAscendNum() {
        return this.mAscendNum;
    }
}
