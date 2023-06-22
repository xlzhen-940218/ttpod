package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class OnlineMediaItemId {
    private static final String KEY_SONG_IDS = "song_ids";
    @SerializedName(value = KEY_SONG_IDS)
    private ArrayList<Long> mOnlineMediaItemIdList = new ArrayList<>();

    public List<Long> getOnlineMediaItemIdList() {
        return this.mOnlineMediaItemIdList;
    }
}
