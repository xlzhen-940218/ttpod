package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.sdk.lib.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class AlbumItem implements Serializable {
    @SerializedName(value = "desc")
    private String mDesc;
    @SerializedName(value = "_id")
    private long mId;
    @SerializedName(value = "lang")
    private String mLang;
    @SerializedName(value = "name")
    private String mName;
    @SerializedName(value = "pic200")
    private String mPic200;
    @SerializedName(value = "pic500")
    private String mPic500;
    @SerializedName(value = "publish_time")
    private String mPublishTime;
    @SerializedName(value = "singer_name")
    private String mSingerName;
    @SerializedName(value = "song_ids")
    private String mSongIds;

    public long getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public String getPublishTime() {
        return this.mPublishTime;
    }

    public List<Long> getSongIds() {
        return StringUtils.stringToLongArray(this.mSongIds, ",");
    }

    public String getLang() {
        return this.mLang;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getPic200() {
        return this.mPic200;
    }

    public String getPic500() {
        return this.mPic500;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setSingerName(String str) {
        this.mSingerName = str;
    }

    public void setPublishTime(String str) {
        this.mPublishTime = str;
    }

    public void setSongIds(String str) {
        this.mSongIds = str;
    }

    public void setLang(String str) {
        this.mLang = str;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public void setPic200(String str) {
        this.mPic200 = str;
    }

    public void setPic500(String str) {
        this.mPic500 = str;
    }
}
