package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class MessageCollectItem implements BaseId {
    private static final String KEY_ID = "msg_id";
    private static final String KEY_TIMESTAMP = "timestamp";
    @SerializedName(value = KEY_ID)
    private long mId = -1;
    @SerializedName(value = KEY_TIMESTAMP)
    private long mTimeStamp = 0;

    @Override // com.sds.android.cloudapi.ttpod.data.BaseId
    public long getId() {
        return this.mId;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }
}
