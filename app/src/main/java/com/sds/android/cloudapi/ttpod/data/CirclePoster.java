package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class CirclePoster implements Serializable {
    private static final String KEY_MSG_ID = "msg_id";
    private static final String KEY_PIC = "pic";
    private static final String KEY_TYPE = "type";
    private static final String KEY_URL = "url";
    public static final int TYPE_CIRCLE = 8;
    public static final int TYPE_URL = 3;
    @SerializedName(value = "url")
    private String mContentUrl;
    @SerializedName(value = KEY_MSG_ID)
    private long mMsgId;
    @SerializedName(value = "pic")
    private String mPicUrl;
    @SerializedName(value = "type")
    private int mType;

    public int getType() {
        return this.mType;
    }

    public long getMsgId() {
        return this.mMsgId;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getContentUrl() {
        return this.mContentUrl;
    }
}
