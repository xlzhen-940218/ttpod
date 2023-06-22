package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class SingerTagExtra implements Serializable {
    @SerializedName(value = "count")
    private long mCount;
    @SerializedName(value = "status")
    private int mStatus;
    @SerializedName(value = "tag")
    private SingerTag mTag = new SingerTag();

    public int getStatus() {
        return this.mStatus;
    }

    public long getCount() {
        return this.mCount;
    }

    public SingerTag getSingerTag() {
        return this.mTag;
    }
}
