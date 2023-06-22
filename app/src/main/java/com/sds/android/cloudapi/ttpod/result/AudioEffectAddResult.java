package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;

/* loaded from: classes.dex */
public class AudioEffectAddResult extends BaseResult {
    @SerializedName(value = "data")
    private String mResult = "";

    public void setAeId(String str) {
        this.mResult = str;
    }

    public String getAeId() {
        return this.mResult;
    }
}
