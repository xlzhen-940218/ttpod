package com.sds.android.ttpod.framework.modules.search;


import com.sds.android.sdk.lib.p061c.p062a.p063a.Field;

/* loaded from: classes.dex */
public class SearchMediaLinkInfo {
    public static final int DB_VERSION = 16777216;
    private String mArtist;
    private Long mArtistSearchTime;
    private String mLyricPath;
    private Long mLyricSearchTime;
    private String mMediaId;

    @Field(m8643a = 0, m8642b = 16777216, m8641c = true)
    public String getMediaId() {
        return this.mMediaId;
    }

    public void setMediaId(String str) {
        this.mMediaId = str;
    }

    @Field(m8643a = 1, m8642b = 16777216)
    public String getLyricPath() {
        return this.mLyricPath;
    }

    public void setLyricPath(String str) {
        this.mLyricPath = str;
    }

    @Field(m8643a = 2, m8642b = 16777216)
    public Long getLyricSearchTime() {
        return this.mLyricSearchTime;
    }

    public void setLyricSearchTime(Long l) {
        this.mLyricSearchTime = l;
    }

    @Field(m8643a = 3, m8642b = 16777216)
    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    @Field(m8643a = 4, m8642b = 16777216)
    public Long getArtistSearchTime() {
        return this.mArtistSearchTime;
    }

    public void setArtistSearchTime(Long l) {
        this.mArtistSearchTime = l;
    }
}
