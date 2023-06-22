package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

/* loaded from: classes.dex */
public class FeedbackItem implements Serializable {
    public static final String STATUS_RECORDED = "3";
    public static final String STATUS_SHELVED = "4";
    public static final String STATUS_SOLVED = "2";
    public static final String STATUS_SOLVING = "1";
    public static final String STATUS_WAITING = "0";
    @SerializedName(value = "addTime")
    private long mAddTime;
    @SerializedName(value = "build")
    private String mBuildNum;
    @SerializedName(value = "contactWay")
    private String mContactWay;
    @SerializedName(value = "id")
    private String mId;
    @SerializedName(value = "ip")
    private String mIp;
    private boolean mIsUnread;
    @SerializedName(value = "proposalContent")
    private String mProposalContent;
    @SerializedName(value = "resol")
    private String mResolution;
    @SerializedName(value = "status")
    private int mStatus;
    @SerializedName(value = "lastUpdated")
    private long mTimeUpdated;

    public FeedbackItem(String str, String str2, String str3) {
        this.mProposalContent = str;
        this.mIp = str2;
        this.mContactWay = str3;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getBuildNum() {
        return this.mBuildNum;
    }

    public String getResolution() {
        return this.mResolution;
    }

    public String getContactWay() {
        return this.mContactWay;
    }

    public String getProposalContent() {
        return this.mProposalContent;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public long getAddTime() {
        return this.mAddTime;
    }

    public long getLastUpdated() {
        return this.mTimeUpdated;
    }

    public void setLastUpdated(long j) {
        this.mTimeUpdated = j;
    }

    public void setUnread(boolean z) {
        this.mIsUnread = z;
    }

    public boolean isUnread() {
        return this.mIsUnread;
    }
}
