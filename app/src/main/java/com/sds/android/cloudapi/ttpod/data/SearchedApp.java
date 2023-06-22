package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


/* loaded from: classes.dex */
public class SearchedApp extends AppBaseInfo {
    @SerializedName(value = "score")
    private int mScore;

    public int getScore() {
        return this.mScore;
    }
}
