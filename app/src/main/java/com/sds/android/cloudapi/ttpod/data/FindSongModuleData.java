package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.cloudapi.ttpod.result.CirclePosterListResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHandpickResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FindSongModuleData implements Serializable {
    @SerializedName(value = "posters")
    private ArrayList<CirclePosterListResult> mCirclePosts = new ArrayList<>();
    @SerializedName(value = "songlists")
    private ArrayList<FindSongHotListResultNew> mSongLists = new ArrayList<>();
    @SerializedName(value = "promotionzones")
    private ArrayList<FindSongHandpickResult> mZones = new ArrayList<>();
    @SerializedName(value = "banners")
    private ArrayList<OperationZoneResult> mBanners = new ArrayList<>();

    public ArrayList<CirclePosterListResult> getPosts() {
        return this.mCirclePosts;
    }

    public ArrayList<FindSongHandpickResult> getZones() {
        return this.mZones;
    }

    public ArrayList<FindSongHotListResultNew> getSongLists() {
        return this.mSongLists;
    }

    public ArrayList<OperationZoneResult> getBanners() {
        return this.mBanners;
    }

    public void setmCirclePosts(ArrayList<CirclePosterListResult> mCirclePosts) {
        this.mCirclePosts = mCirclePosts;
    }

    public void setmSongLists(ArrayList<FindSongHotListResultNew> mSongLists) {
        this.mSongLists = mSongLists;
    }

    public void setmZones(ArrayList<FindSongHandpickResult> mZones) {
        this.mZones = mZones;
    }

    public void setmBanners(ArrayList<OperationZoneResult> mBanners) {
        this.mBanners = mBanners;
    }
}
