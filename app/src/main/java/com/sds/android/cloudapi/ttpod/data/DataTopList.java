package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class DataTopList {
    @SerializedName(value = "list")
    private ArrayList<MarketApp> mListApps = new ArrayList<>();
    @SerializedName(value = "top")
    private ArrayList<MarketApp> mTopApps = new ArrayList<>();

    public ArrayList<MarketApp> getListApps() {
        return this.mListApps;
    }

    public ArrayList<MarketApp> getTopApps() {
        return this.mTopApps;
    }
}
