package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AudioEffectUserExp implements Serializable {
    @SerializedName(value = "add")
    private int mAdd = 0;
    @SerializedName(value = "bind")
    private int mBind = 0;
    @SerializedName(value = "bind_by")
    private int mBindBy = 0;
    @SerializedName(value = "pick")
    private int mPick = 0;
    @SerializedName(value = "pick_by")
    private int mPickBy = 0;
    @SerializedName(value = "total")
    private int mTotal = 0;

    public int getAdd() {
        return this.mAdd;
    }

    public int getBind() {
        return this.mBind;
    }

    public int getBindBy() {
        return this.mBindBy;
    }

    public int getPick() {
        return this.mPick;
    }

    public int getPickBy() {
        return this.mPickBy;
    }

    public int getTotal() {
        return this.mTotal;
    }
}
