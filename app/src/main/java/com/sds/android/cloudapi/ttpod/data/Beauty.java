package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;

/* loaded from: classes.dex */
public class Beauty {
    @SerializedName(value = "bean_count_total")
    private long mBean;
    @SerializedName(value = "_id")
    private long mId;
    @SerializedName(value = "L")
    private int mLevel;
    @SerializedName(value = "live")
    private boolean mLive;
    @SerializedName(value = User.KEY_NICK_NAME)
    private String mNickName;
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl;
    @SerializedName(value = "xy_star_id")
    private long mStarId;
    @SerializedName(value = "visiter_count")
    private long mVisitorCount;

    public long getId() {
        return this.mId;
    }

    public long getBean() {
        return this.mBean;
    }

    public boolean isLive() {
        return this.mLive;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public long getVisitorCount() {
        return this.mVisitorCount;
    }

    public long getStarId() {
        return this.mStarId;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public int getLevel() {
        return this.mLevel;
    }
}
