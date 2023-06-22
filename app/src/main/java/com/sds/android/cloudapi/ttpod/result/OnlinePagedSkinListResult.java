package com.sds.android.cloudapi.ttpod.result;

import com.google.gson.annotations.SerializedName;

import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class OnlinePagedSkinListResult extends BaseResult implements Serializable {
    @SerializedName(value = "data")
    private OnlinePagedSkinListData mData;
    @SerializedName(value = "extra")
    private OnlineSkinListResult.OnlineThemeListExtra mExtra;

    public String getMainUrl() {
        return this.mExtra.getPicUrl();
    }

    public OnlineSkinListResult.OnlineThemeListExtra getExtra() {
        return this.mExtra;
    }

    public OnlinePagedSkinListData getData() {
        return this.mData;
    }

    /* loaded from: classes.dex */
    public static class OnlinePagedSkinListData implements Serializable {
        @SerializedName(value = "id")
        private int mId;
        @SerializedName(value = "name")
        private String mName;
        @SerializedName(value = "orderNum")
        private int mOrderNum;
        @SerializedName(value = "recommendPicUrl")
        private String mRecommendPicUrl;
        @SerializedName(value = "skins")
        private ArrayList<OnlineSkinItem> mSkins;
        @SerializedName(value = "skins_count")
        private int mSkinsCount;
        @SerializedName(value = "status")
        private int mStatus;

        public int getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public int getSkinsCount() {
            return this.mSkinsCount;
        }

        public String getRecommendPicUrl() {
            return this.mRecommendPicUrl;
        }

        public int getStatus() {
            return this.mStatus;
        }

        public int getOrderNum() {
            return this.mOrderNum;
        }

        public ArrayList<OnlineSkinItem> getSkins() {
            return this.mSkins;
        }
    }
}
