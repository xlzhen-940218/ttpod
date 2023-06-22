package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class MarketAppCategory {
    @SerializedName(value = "artifact_count")
    private int mArtifactCount;
    @SerializedName(value = "category_id")
    private int mCategoryId;
    @SerializedName(value = "category_logo")
    private String mCategoryLogo;
    @SerializedName(value = "category_name")
    private String mCategoryName;
    @SerializedName(value = "sub_categories")
    private ArrayList<MarketAppCategory> mSubCategories;

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }

    public int getArtifactCount() {
        return this.mArtifactCount;
    }

    public String getCategoryLogoUrl() {
        return this.mCategoryLogo;
    }

    public ArrayList<MarketAppCategory> getSubCategories() {
        return this.mSubCategories;
    }
}
