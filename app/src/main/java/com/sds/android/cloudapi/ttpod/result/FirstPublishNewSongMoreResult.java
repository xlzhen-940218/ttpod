package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FirstPublishNewSongMoreResult extends BaseResult {
    private static final String KEY_DATA = "data";
    private static final String KEY_PAGE_COUNT = "pages";
    private static final String KEY_ROWS = "rows";
    private static final String KEY_SINGLES = "singles";
    @SerializedName(value = KEY_SINGLES)
    private ArrayList<MusicCircleFirstPublish> mSingleList = null;
    @SerializedName(value = "data")
    private ArrayList<AlbumData> mAlbumList = null;
    @SerializedName(value = KEY_PAGE_COUNT)
    private int mPageCount = 0;
    @SerializedName(value = KEY_ROWS)
    private int mRows = 0;

    /* loaded from: classes.dex */
    public static class AlbumData implements Serializable {
        private static final String KEY_DESC = "desc";
        private static final String KEY_ID = "_id";
        private static final String KEY_MSG_ID = "msg_id";
        private static final String KEY_PIC = "pic";
        private static final String KEY_PIC_URL = "pic_url";
        private static final String KEY_STARTING = "starting";
        private static final String KEY_TITLE = "title";
        private static final String KEY_WEEK = "week";
        private static final String KEY_YEAR = "year";
        @SerializedName(value = "desc")
        private String mDesc;
        @SerializedName(value = KEY_STARTING)
        private boolean mFirstPublish;
        @SerializedName(value = KEY_ID)
        private long mId;
        @SerializedName(value = KEY_MSG_ID)
        private long mMsgId;
        @SerializedName(value = "pic")
        private String mPicUrl;
        @SerializedName(value = "pic_url")
        private String mPicUrlNew;
        @SerializedName(value = "title")
        private String mTitle;
        @SerializedName(value = KEY_WEEK)
        private int mWeek;
        @SerializedName(value = "year")
        private int mYear;

        public long getId() {
            return this.mId;
        }

        public String getDesc() {
            return this.mDesc;
        }

        public String getPicUrlNew() {
            return this.mPicUrlNew;
        }

        public int getWeek() {
            return this.mWeek;
        }

        public int getYear() {
            return this.mYear;
        }

        public long getMsgId() {
            return this.mMsgId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public boolean isFirstPublish() {
            return this.mFirstPublish;
        }

        public String getPicUrl() {
            return this.mPicUrl;
        }
    }

    public int getPageCount() {
        return this.mPageCount;
    }

    public ArrayList<MusicCircleFirstPublish> getSingleList() {
        return this.mSingleList;
    }

    public ArrayList<AlbumData> getDataList() {
        return this.mAlbumList;
    }

    public int getRows() {
        return this.mRows;
    }
}
