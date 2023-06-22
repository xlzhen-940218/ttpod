package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class PrivateMessageContent implements Serializable {
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_PM_ID = "pm_id";
    private static final String KEY_SENDER_ID = "sender";
    private static final String KEY_TIMESTAMP = "timestamp";
    @SerializedName(value = KEY_MESSAGE)
    private String mMessage;
    @SerializedName(value = KEY_PM_ID)
    private String mPmId;
    @SerializedName(value = KEY_SENDER_ID)
    private long mSenderId;
    @SerializedName(value = KEY_TIMESTAMP)
    private long mTimestamp;

    public String getPmId() {
        return this.mPmId;
    }

    public long getSenderId() {
        return this.mSenderId;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }
}
