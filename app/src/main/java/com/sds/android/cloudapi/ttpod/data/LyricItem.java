package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class LyricItem implements Serializable {
    @SerializedName(value = "content_url")
    private String mContentUrl;
    @SerializedName(value = "duration_ms")
    private long mDurationMs;
    @SerializedName(value = "_id")
    private long mId;
    @SerializedName(value = "singer_name")
    private String mSingerName;
    @SerializedName(value = "song_name")
    private String mSongName;
    @SerializedName(value = "trc")
    private boolean mTrc;

    public boolean isTrc() {
        return this.mTrc;
    }

    public void setTrc(boolean z) {
        this.mTrc = z;
    }

    public long getDurationMilliseconds() {
        return this.mDurationMs;
    }

    public void setDurationMilliseconds(long j) {
        this.mDurationMs = j;
    }

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public void setSingerName(String str) {
        this.mSingerName = str;
    }

    public String getSongName() {
        return this.mSongName;
    }

    public void setSongName(String str) {
        this.mSongName = str;
    }

    public String getContentUrl() {
        return this.mContentUrl;
    }

    public void setContentUrl(String str) {
        this.mContentUrl = str;
    }
}
