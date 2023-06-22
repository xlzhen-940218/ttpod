package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.cloudapi.ttpod.result.CirclePosterListResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHandpickResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.HotSongOnlineMediaItemsResultNew;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ComposedModuleContent implements Serializable {
    @SerializedName(value = "poster")
    private CirclePosterListResult mCirclePosters = new CirclePosterListResult();
    @SerializedName(value = "operationzone")
    private OperationZoneResult mOperationZone = new OperationZoneResult();
    @SerializedName(value = "banner")
    private FindSongHandpickResult mBanner = new FindSongHandpickResult();
    @SerializedName(value = "hotlist")
    private FindSongHotListResultNew mHotList = new FindSongHotListResultNew();
    @SerializedName(value = "hotsong")
    private HotSongOnlineMediaItemsResultNew mHotSongs = new HotSongOnlineMediaItemsResultNew();

    public CirclePosterListResult getCirclePosters() {
        return this.mCirclePosters;
    }

    public OperationZoneResult getOperationZone() {
        return this.mOperationZone;
    }

    public FindSongHandpickResult getBanner() {
        return this.mBanner;
    }

    public FindSongHotListResultNew getHotList() {
        return this.mHotList;
    }

    public HotSongOnlineMediaItemsResultNew getHotSongs() {
        return this.mHotSongs;
    }
}
