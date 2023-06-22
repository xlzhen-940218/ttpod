package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

/* loaded from: classes.dex */
public class OnlineSkinItem implements Serializable {
    @SerializedName(value = "author")
    private String mAuthor;
    @SerializedName(value = "dateCreated")
    private long mDateCreated;
    @SerializedName(value = "down")
    private long mDown;
    @SerializedName(value = "downUrl")
    private String mDownUrl;
    @SerializedName(value = "size")
    private String mFileSizeStr;
    @SerializedName(value = "id")
    private String mId;
    @SerializedName(value = "recommendName")
    private String mName;
    @SerializedName(value = "recommendPicUrl")
    private String mRecommendPicUrl;
    private String mThumbnailUrl;
    @SerializedName(value = "type")
    private String mType;
    @SerializedName(value = "ver")
    private String mVersion;
    @SerializedName(value = "vsNum")
    private String mVersionNumber;

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getSkinUrl() {
        return this.mDownUrl;
    }

    public void setSkinUrl(String str) {
        this.mDownUrl = str;
    }

    public String getRecommendPicUrl() {
        return this.mRecommendPicUrl;
    }

    public String getPictureUrl() {
        return this.mThumbnailUrl;
    }

    public void setPictureUrl(String str) {
        this.mThumbnailUrl = str;
    }

    public String getType() {
        return this.mType;
    }

    public String getFileSizeStr() {
        return this.mFileSizeStr;
    }

    public String getVersionNumber() {
        return this.mVersionNumber;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public long getDown() {
        return this.mDown;
    }

    public long getDateCreated() {
        return this.mDateCreated;
    }

    public String getVersion() {
        return this.mVersion;
    }
}
