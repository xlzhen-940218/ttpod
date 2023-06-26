package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;
import java.io.Serializable;

/* loaded from: classes.dex */
public class FindSongCommonCategory implements Serializable {
    @SerializedName(value = "details")
    private String mDetail;
    @SerializedName(value = "id")
    private int mId;
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl;
    @SerializedName(value = "style")
    private int mStyle;
    @SerializedName(value = "time")
    private String mTime;
    @SerializedName(value = "title")
    private String mTitle;
    @SerializedName(value = "type")
    private int mType;

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getDetail() {
        return this.mDetail;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public void setStyle(int i) {
        this.mStyle = i;
    }

    public void setmDetail(String mDetail) {
        this.mDetail = mDetail;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }

    public void setmStyle(int mStyle) {
        this.mStyle = mStyle;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}
