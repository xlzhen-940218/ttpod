package com.sds.android.ttpod.media.mediastore;

/* loaded from: classes.dex */
public class ExchangeOrderEntity {
    private String mGroupID;
    private String mMediaID1;
    private String mMediaID2;

    public ExchangeOrderEntity(String str, String str2, String str3) {
        this.mGroupID = str;
        this.mMediaID1 = str2;
        this.mMediaID2 = str3;
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    public String getMediaID1() {
        return this.mMediaID1;
    }

    public String getMediaID2() {
        return this.mMediaID2;
    }
}
