package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;import com.google.gson.annotations.SerializedName;

import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;

import java.io.Serializable;

/* loaded from: classes.dex */
public class IntroductionData implements Serializable {
    @SerializedName(value = "_id")
    private long mId;
    @SerializedName(value = "desc")
    private String mDesc = "";
    @SerializedName(value = "detail")
    private String mDetail = "";
    @SerializedName(value = "name")
    private String mName = "";
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl = "";

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public String getDetail() {
        return this.mDetail;
    }

    public void setDetail(String str) {
        this.mDetail = str;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public void setPicUrl(String str) {
        this.mPicUrl = str;
    }
}
