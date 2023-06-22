package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class Billboards implements Serializable {
    @SerializedName(value = "hit")
    private int mHit;
    @SerializedName(value = "hot")
    private int mHot;
    @SerializedName(value = "val")
    private String mWord;

    public String getWord() {
        return this.mWord;
    }

    public int getHit() {
        return this.mHit;
    }

    public int getHot() {
        return this.mHot;
    }
}
