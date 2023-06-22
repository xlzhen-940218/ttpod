package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class OnlineMusicSubCategoryResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    @SerializedName(value = "data")
    private ArrayList<SubCategoryData> mSubCategoryList = null;
    @SerializedName(value = KEY_PAGE_COUNT)
    private int mPages = 0;
    @SerializedName(value = KEY_ROWS)
    private int mRows = 0;

    public ArrayList<SubCategoryData> getSubCategoryList() {
        return this.mSubCategoryList;
    }

    public int getPages() {
        return this.mPages;
    }

    public int getRows() {
        return this.mRows;
    }

    /* loaded from: classes.dex */
    public static class SubCategoryData implements Serializable {
        private static final String KEY_CATEGORY_ID = "_id";
        private static final String KEY_COUNT = "count";
        private static final String KEY_DETAIL = "desc";
        private static final String KEY_LARGE_PIC_URL = "large_pic_url";
        private static final String KEY_NAME = "name";
        private static final String KEY_PIC_URL = "pic_url";
        private static final String KEY_URL = "url";
        private String mParentName = null;
        @SerializedName(value = KEY_CATEGORY_ID)
        private long mId = 0;
        @SerializedName(value = "name")
        private String mName = null;
        @SerializedName(value = KEY_COUNT)
        private int mCount = 0;
        @SerializedName(value = "url")
        private String mUrl = null;
        @SerializedName(value = "pic_url")
        private String mPicUrl = null;
        @SerializedName(value = KEY_LARGE_PIC_URL)
        private String mLargePicUrl = null;
        @SerializedName(value = "desc")
        private String mDetail = null;

        public String getParentName() {
            return this.mParentName;
        }

        public void setParentName(String str) {
            this.mParentName = str;
        }

        public String getLargePicUrl() {
            return this.mLargePicUrl;
        }

        public String getPicUrl() {
            return this.mPicUrl;
        }

        public long getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public int getCount() {
            return this.mCount;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public String getDetail() {
            return this.mDetail;
        }

        public void setDetail(String str) {
            this.mDetail = str;
        }

        public void setId(long j) {
            this.mId = j;
        }

        public void setName(String str) {
            this.mName = str;
        }

        public void setCount(int i) {
            this.mCount = i;
        }

        public void setUrl(String str) {
            this.mUrl = str;
        }

        public void setPicUrl(String str) {
            this.mPicUrl = str;
        }

        public void setLargePicUrl(String str) {
            this.mLargePicUrl = str;
        }
    }
}
