package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class FirstPublishAlbumData implements Serializable {
    private static final String KEY_DESC = "desc";
    private static final String KEY_ID = "_id";
    private static final String KEY_MSG_ID = "msg_id";
    private static final String KEY_PIC = "pic";
    private static final String KEY_PIC_URL = "pic_url";
    private static final String KEY_STARTING = "starting";
    private static final String KEY_TITLE = "title";
    private static final String KEY_WEEK = "week";
    private static final String KEY_YEAR = "year";
    @SerializedName(value = KEY_STARTING)
    private boolean mFirstPublish;
    @SerializedName(value = KEY_ID)
    private long mId;
    @SerializedName(value = KEY_MSG_ID)
    private long mMsgId;
    @SerializedName(value = KEY_WEEK)
    private int mWeek;
    @SerializedName(value = "year")
    private int mYear;
    @SerializedName(value = "desc")
    private String mDesc = "";
    @SerializedName(value = "pic_url")
    private String mPicUrlNew = "";
    @SerializedName(value = "title")
    private String mTitle = "";
    @SerializedName(value = "pic")
    private String mPicUrl = "";

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
