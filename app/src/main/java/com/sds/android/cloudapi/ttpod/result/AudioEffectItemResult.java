package com.sds.android.cloudapi.ttpod.result;


import com.google.gson.annotations.SerializedName;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class AudioEffectItemResult extends BaseResult {
    @SerializedName(value = "data")
    private List<AudioEffectItem> mAudioEffect = new LinkedList();
    @SerializedName(value = "all_page")
    private int mAllPage = 0;
    @SerializedName(value = "count")
    private int mCount = 0;

    public List<AudioEffectItem> getEffectList() {
        return this.mAudioEffect;
    }

    public int getAllPage() {
        return this.mAllPage;
    }

    public int getCount() {
        return this.mCount;
    }
}
