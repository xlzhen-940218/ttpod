package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.UnicomFlow;
import com.sds.android.sdk.lib.request.BaseResult;

/* loaded from: classes.dex */
public class UnicomFlowResult extends BaseResult {
    @SerializedName(value = "data")
    private UnicomFlow mUnicomFlow = new UnicomFlow();

    public UnicomFlow getUnicomFlow() {
        return this.mUnicomFlow;
    }
}
