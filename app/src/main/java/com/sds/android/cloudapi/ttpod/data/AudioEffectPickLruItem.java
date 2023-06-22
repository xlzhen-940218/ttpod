package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class AudioEffectPickLruItem {
    @SerializedName(value = "_id")
    private long mId = 0;
    @SerializedName(value = User.KEY_NICK_NAME)
    private String mNickName = "";
    @SerializedName(value = "timestamp")
    private long mTimeStamp = 0;

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public void setTimeStamp(long j) {
        this.mTimeStamp = j;
    }
}
