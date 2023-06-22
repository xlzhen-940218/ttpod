package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class AroundTTPodUser extends TTPodUser {
    private static final String KEY_DISTANCE = "distance";
    @SerializedName(value = KEY_DISTANCE)
    private int mDistance;

    public int getDistance() {
        return this.mDistance;
    }

    public void setDistance(int i) {
        this.mDistance = i;
    }
}
