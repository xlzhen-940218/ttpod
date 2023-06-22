package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SingerPicItem implements Serializable {
    @SerializedName(value = "alias_names")
    private String mAliasNames;
    @SerializedName(value = "_id")
    private long mId;
    @SerializedName(value = "pic_size_map")
    private HashMap<Integer, Integer> mPicSizeMap = new HashMap<>();
    @SerializedName(value = "pic_url_pattern")
    private String mPicUrlPattern;
    @SerializedName(value = "singer_id")
    private long mSingerId;
    @SerializedName(value = "singer_name")
    private String mSingerName;

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public void setSingerName(String str) {
        this.mSingerName = str;
    }

    public String getAliasNames() {
        return this.mAliasNames;
    }

    public void setAliasNames(String str) {
        this.mAliasNames = str;
    }

    public long getSingerId() {
        return this.mSingerId;
    }

    public void setSingerId(long j) {
        this.mSingerId = j;
    }

    public HashMap<Integer, Integer> getPicSizeMap() {
        return this.mPicSizeMap;
    }

    public void setPicSizeMap(HashMap<Integer, Integer> hashMap) {
        this.mPicSizeMap = hashMap;
    }

    public String getPicUrlPattern() {
        return this.mPicUrlPattern;
    }

    public void setPicUrlPattern(String str) {
        this.mPicUrlPattern = str;
    }
}
