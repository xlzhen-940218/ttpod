package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MusicCircleFirstPublish implements Serializable {
    private static final String KEY_MSG_ID = "msg_id";
    private static final String KEY_PIC = "pic";
    private static final String KEY_STARTING = "starting";
    private static final String KEY_TITLE = "title";
    @SerializedName(value = KEY_STARTING)
    private boolean mFirstPublish;
    @SerializedName(value = KEY_MSG_ID)
    private long mMsgId;
    @SerializedName(value = "pic")
    private String mPicUrl;
    @SerializedName(value = "title")
    private String mTitle;

    public long getMsgId() {
        return this.mMsgId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isFirstPublish() {
        return this.mFirstPublish;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }
}
