package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DailyRecommend implements Serializable {
    private static final long serialVersionUID = 8046350060729611541L;
    @SerializedName(value = "id")
    private long mId = 0;
    @SerializedName(value = "weight")
    private long mWeight = 0;
    @SerializedName(value = "types")
    private List<String> mRecommendTypes = new ArrayList();

    public long getId() {
        return this.mId;
    }

    public long getWeight() {
        return this.mWeight;
    }

    public List<String> getRecommendTypes() {
        return this.mRecommendTypes;
    }
}
