package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class SingerCounts implements Serializable {
    @SerializedName(value = "albumsCount")
    private int mAlbumsCount = 0;
    @SerializedName(value = "songsCount")
    private int mSongsCount = 0;

    public int getAlbumsCount() {
        return this.mAlbumsCount;
    }

    public int getSongsCount() {
        return this.mSongsCount;
    }
}
