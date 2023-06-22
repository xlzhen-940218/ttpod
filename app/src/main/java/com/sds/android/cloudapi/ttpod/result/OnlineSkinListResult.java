package com.sds.android.cloudapi.ttpod.result;
import com.google.gson.annotations.SerializedName;
import android.text.TextUtils;

import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.lib.request.BaseResult;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class OnlineSkinListResult extends BaseResult implements Serializable {
    @SerializedName(value = "allPage")
    private int mAllPage;
    @SerializedName(value = "extra")
    private OnlineThemeListExtra mExtra;
    @SerializedName(value = "data")
    private ArrayList<OnlineSkinItem> mThemeItems;

    public int getAllPage() {
        return this.mAllPage;
    }

    public String getMainUrl() {
        String str = this.mExtra == null ? "" : this.mExtra.mPicUrl;
        return TextUtils.isEmpty(str) ? "http://api.skin.ttpod.com/skin" : str;
    }

    public OnlineThemeListExtra getExtra() {
        return this.mExtra;
    }

    public ArrayList<OnlineSkinItem> getSkinItems() {
        return this.mThemeItems;
    }

    /* loaded from: classes.dex */
    public static class OnlineThemeListExtra implements Serializable {
        @SerializedName(value = "picurl")
        private String mPicUrl;

        public String getPicUrl() {
            return this.mPicUrl;
        }
    }
}
