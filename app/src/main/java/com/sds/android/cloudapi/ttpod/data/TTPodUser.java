package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


/* loaded from: classes.dex */
public class TTPodUser extends User {
    @SerializedName(value = "followers_count")
    private int mFollowersCount;
    @SerializedName(value = "followers_rank")
    private int mFollowersRank;
    @SerializedName(value = "followings_count")
    private int mFollowingsCount;
    @SerializedName(value = "v")
    private boolean mIsVerified;

    public boolean isVerified() {
        return this.mIsVerified;
    }

    public int getFollowersRank() {
        return this.mFollowersRank;
    }

    public int getFollowersCount() {
        return this.mFollowersCount;
    }

    public int getFollowingsCount() {
        return this.mFollowingsCount;
    }

    public void setFollowersCount(int i) {
        this.mFollowersCount = i;
    }

    public void setFollowingsCount(int i) {
        this.mFollowingsCount = i;
    }
}
