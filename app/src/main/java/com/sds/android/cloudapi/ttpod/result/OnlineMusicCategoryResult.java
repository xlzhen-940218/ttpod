package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class OnlineMusicCategoryResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    @SerializedName(value = "data")
    private ArrayList<CategoryData> mCategoryList = null;
    @SerializedName(value = KEY_PAGE_COUNT)
    private int mPages = 0;
    @SerializedName(value = KEY_ROWS)
    private int mRows = 0;

    public ArrayList<CategoryData> getCategoryList() {
        return this.mCategoryList;
    }

    public int getPageCount() {
        return this.mPages;
    }

    public int getRows() {
        return this.mRows;
    }

    /* loaded from: classes.dex */
    public static class CategoryData implements Serializable {
        private static final String KEY_CATEGORY_ID = "_id";
        private static final String KEY_COUNT = "count";
        private static final String KEY_NAME = "name";
        @SerializedName(value = KEY_CATEGORY_ID)
        private long mId = 0;
        @SerializedName(value = "name")
        private String mName = null;
        @SerializedName(value = KEY_COUNT)
        private int mCount = 0;

        public long getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public int getCount() {
            return this.mCount;
        }
    }
}
