package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class GameTucaoComment {
    @SerializedName(value = "content")
    private String mContent;
    @SerializedName(value = "ip")
    private String mIp;
    @SerializedName(value = "revert_time")
    private String mRevertTime;
    @SerializedName(value = "t_id")
    private int mTid;

    public int getTid() {
        return this.mTid;
    }

    public String getIp() {
        return this.mIp;
    }

    public String getRevertTime() {
        return this.mRevertTime;
    }

    public String getContent() {
        return this.mContent;
    }
}
