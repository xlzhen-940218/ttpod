package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

/* loaded from: classes.dex */
public class RadioChannel implements Serializable {
    @SerializedName(value = "pic_url_240_200")
    private String m240X200PicUrl;
    @SerializedName(value = "details")
    private String mDetails;
    @SerializedName(value = "large_pic_url")
    private String mLargePicUrl;
    @SerializedName(value = "parentname")
    private String mParentName;
    @SerializedName(value = "quantity")
    private int mQuantity;
    @SerializedName(value = "small_pic_url")
    private String mSmallPicUrl;
    @SerializedName(value = "songlist_id")
    private int mSongListId;
    @SerializedName(value = "songlist_name")
    private String mSongListName;
    @SerializedName(value = "url")
    private String mUrl;

    public RadioChannel() {
    }

    public RadioChannel(int i, String str, String str2, String str3, String str4, String str5) {
        this.mSongListId = i;
        this.mParentName = str;
        this.mSongListName = str2;
        this.mLargePicUrl = str3;
        this.mSmallPicUrl = str4;
        this.m240X200PicUrl = str5;
    }

    public String getParentName() {
        return this.mParentName;
    }

    public void setParentName(String str) {
        this.mParentName = str;
    }

    public int getChannelId() {
        return this.mSongListId;
    }

    public String getChannelName() {
        return this.mSongListName;
    }

    public String getDetails() {
        return this.mDetails;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public String getLargePicUrl() {
        return this.mLargePicUrl;
    }

    public String getSmallPicUrl() {
        return this.mSmallPicUrl;
    }

    public String get240X200PicUrl() {
        return this.m240X200PicUrl;
    }

    public void setSongListId(int i) {
        this.mSongListId = i;
    }

    public void setSongListName(String str) {
        this.mSongListName = str;
    }

    public void set240X200PicUrl(String str) {
        this.m240X200PicUrl = str;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
