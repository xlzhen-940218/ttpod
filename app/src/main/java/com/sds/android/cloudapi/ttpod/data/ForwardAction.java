package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ForwardAction implements Serializable {
    @SerializedName(value = "value")
    private String mValue = "";
    @SerializedName(value = "type")
    private int mType = Integer.MIN_VALUE;

    public String getValue() {
        return this.mValue;
    }

    public int getType() {
        return this.mType;
    }
}
