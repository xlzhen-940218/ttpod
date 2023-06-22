package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;import com.google.gson.annotations.SerializedName;


/* loaded from: classes.dex */
public class GameTucaoCommentExtra {
    @SerializedName(value = "all_page")
    private int mAllPage;
    @SerializedName(value = "count")
    private int mCount;

    public int getCount() {
        return this.mCount;
    }

    public int getAllPage() {
        return this.mAllPage;
    }
}
