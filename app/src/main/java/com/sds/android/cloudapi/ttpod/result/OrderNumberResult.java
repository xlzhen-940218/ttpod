package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;
import com.sds.android.sdk.lib.request.BaseResult;

/* loaded from: classes.dex */
public class OrderNumberResult extends BaseResult {
    @SerializedName(value = "data")
    private Data mData;

    public Data getData() {
        return this.mData;
    }

    /* loaded from: classes.dex */
    public static class Data {
        @SerializedName(value = "order_id")
        private String mOrderId;
        @SerializedName(value = "order_info")
        private String mOrderInfo;
        @SerializedName(value = "user_id")
        private int mUserId;

        public long getUserId() {
            return this.mUserId;
        }

        public String getOrderId() {
            return this.mOrderId;
        }

        public String getOrderInfo() {
            return this.mOrderInfo;
        }
    }
}
