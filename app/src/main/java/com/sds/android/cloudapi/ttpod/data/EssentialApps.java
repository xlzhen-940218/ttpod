package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class EssentialApps {
    @SerializedName(value = "artifacts")
    private ArrayList<MarketApp> mArtifacts;
    @SerializedName(value = "category_id")
    private int mCategoryId;
    @SerializedName(value = "category_name")
    private String mCategoryName;

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }

    public ArrayList<MarketApp> getArtifacts() {
        return this.mArtifacts;
    }
}
