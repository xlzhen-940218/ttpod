package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;



/* loaded from: classes.dex */
public class GameTucaoApp extends AppBaseInfo {
    @SerializedName(value = "app_id")
    private int mAppId;
    @SerializedName(value = "browser_count")
    private int mBrowserCount;
    @SerializedName(value = "category_name")
    private String mCategoryName;
    @SerializedName(value = "content")
    private String mContent;
    @SerializedName(value = "t_id")
    private int mId;
    @SerializedName(value = "index")
    private int mIndex;
    @SerializedName(value = "large_logo")
    private String mLargeLogo;
    @SerializedName(value = "publish_time")
    private String mPublishTime;
    @SerializedName(value = "revert_count")
    private int mRevertCount;
    @SerializedName(value = "small_logo")
    private String mSmallLogo;
    @SerializedName(value = "state")
    private String mState;
    @SerializedName(value = "summary")
    private String mSummary;
    @SerializedName(value = "tips_word")
    private String mTipsWord;
    @SerializedName(value = "title")
    private String mTitle;

    @Override // com.sds.android.cloudapi.ttpod.data.AppBaseInfo
    public int getId() {
        return this.mId;
    }

    public int getAppId() {
        return this.mAppId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getTipsWord() {
        return this.mTipsWord;
    }

    public String getLargeLogo() {
        return this.mLargeLogo;
    }

    public String getSmallLogo() {
        return this.mSmallLogo;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public int getRevertCount() {
        return this.mRevertCount;
    }

    public int getBrowserCount() {
        return this.mBrowserCount;
    }

    public String getSummary() {
        return this.mSummary;
    }

    public String getContent() {
        return this.mContent;
    }

    public String getPublishTime() {
        return this.mPublishTime;
    }

    public String getState() {
        return this.mState;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }
}
