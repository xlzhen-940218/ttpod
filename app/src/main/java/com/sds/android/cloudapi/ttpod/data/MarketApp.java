package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


/* loaded from: classes.dex */
public class MarketApp extends AppBaseInfo {
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
}
