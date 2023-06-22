package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;


/* loaded from: classes.dex */
public class PostFileResult extends BaseResult {
    @SerializedName(value = "url")
    private String mFileUrl;

    public String getUrl() {
        return this.mFileUrl;
    }
}
