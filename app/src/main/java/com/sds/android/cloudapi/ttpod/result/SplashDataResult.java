package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.SplashItem;
import com.sds.android.sdk.lib.request.DataListResult;

/* loaded from: classes.dex */
public class SplashDataResult extends DataListResult<SplashItem> {
    @SerializedName(value = "version")
    private int mVersion;

    public int getVersion() {
        return this.mVersion;
    }
}
