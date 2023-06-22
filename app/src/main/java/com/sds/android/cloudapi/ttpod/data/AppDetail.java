package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class AppDetail extends AppBaseInfo {
    public static final int CHECKING = 2;
    public static final int SECURITY = 3;
    public static final int UN_AUTHENTICATION = 4;
    public static final int UN_CHECKED = 1;
    @SerializedName(value = "approve")
    private int mApprove;
    @SerializedName(value = "category_id")
    private int mCategoryId;
    @SerializedName(value = "category_name")
    private String mCategoryName;
    @SerializedName(value = "pics")
    private ArrayList<String> mPics;
    @SerializedName(value = "recommend_count")
    private int mRecommendCount;
    @SerializedName(value = "upload_date")
    private String mUpdateDate;
    @SerializedName(value = "upload_info")
    private String mUpdateInfo;

    public int getApprove() {
        return this.mApprove;
    }

    public String getUpdateDate() {
        return this.mUpdateDate;
    }

    public String getUpdateInfo() {
        return this.mUpdateInfo;
    }

    public int getRecommendCount() {
        return this.mRecommendCount;
    }

    public ArrayList<String> getPics() {
        return this.mPics;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }

    public int getCategoryId() {
        return this.mCategoryId;
    }
}
