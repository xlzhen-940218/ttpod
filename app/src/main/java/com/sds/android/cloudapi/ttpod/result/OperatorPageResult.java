package com.sds.android.cloudapi.ttpod.result;


import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class OperatorPageResult extends BaseResult {
    @SerializedName(value = "data")
    private Data mData;

    public Data getData() {
        return this.mData;
    }

    /* loaded from: classes.dex */
    public static class Data {
        @SerializedName(value = "entries")
        private List<Entries> mEntries;
        @SerializedName(value = "jump_url")
        private String mJumpUrl;
        @SerializedName(value = "recommend")
        private int mRecommend;

        public int getRecommend() {
            return this.mRecommend;
        }

        public String getJumpUrl() {
            return this.mJumpUrl;
        }

        public List<Entries> getEntries() {
            return this.mEntries == null ? new ArrayList() : this.mEntries;
        }
    }

    /* loaded from: classes.dex */
    public static class Entries {
        @SerializedName(value = "name")
        private String mName;
        @SerializedName(value = "value")
        private String mValue;

        public String getName() {
            return this.mName;
        }

        public String getValue() {
            return this.mValue;
        }
    }
}
