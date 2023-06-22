package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class FindSongCategory extends FindSongCommonCategory implements Serializable {
    @SerializedName(value = "category")
    private int mCategoryId;
    @SerializedName(value = "count")
    private int mCount;

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public void setCategoryId(int i) {
        this.mCategoryId = i;
    }

    public int getCount() {
        return this.mCount;
    }
}
