package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class AlbumTag implements Serializable {
    private static final String KEY_COUNT = "count";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TAG = "tag";
    @SerializedName(value = KEY_COUNT)
    private long mCount;
    @SerializedName(value = "status")
    private int mStatus;
    @SerializedName(value = KEY_TAG)
    private TagData mTagItem;

    public int getStatus() {
        return this.mStatus;
    }

    public long getCount() {
        return this.mCount;
    }

    public TagData getTagItem() {
        if (this.mTagItem == null) {
            this.mTagItem = new TagData();
        }
        return this.mTagItem;
    }
}
