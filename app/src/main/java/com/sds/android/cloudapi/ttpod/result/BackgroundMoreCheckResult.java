package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;

import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;

/* loaded from: classes.dex */
public class BackgroundMoreCheckResult extends BaseResult implements Serializable {
    @SerializedName(value = "data")
    private BackgroundMoreCheckData mData;
    @SerializedName(value = "ctime")
    private long mDateCreated;

    public BackgroundMoreCheckData getData() {
        return this.mData;
    }

    public long getCreateTime() {
        return this.mDateCreated;
    }

    /* loaded from: classes.dex */
    public class BackgroundMoreCheckData implements Serializable {
        @SerializedName(value = "name")
        private String mName;
        @SerializedName(value = "status")
        private int mStatus;

        public BackgroundMoreCheckData() {
        }

        public String getName() {
            return this.mName;
        }

        public int getStatus() {
            return this.mStatus;
        }
    }
}
