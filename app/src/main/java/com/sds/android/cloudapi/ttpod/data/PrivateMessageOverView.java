package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class PrivateMessageOverView implements Serializable {
    private static final String KEY_LAST_MODIFIED = "last_modified";
    private static final String KEY_LAST_MSG = "last_msg";
    private static final String KEY_PM_ID = "pm_id";
    private static final String KEY_UNREAD_COUNT = "unread_count";
    private static final String KEY_USER = "user";
    @SerializedName(value = KEY_LAST_MODIFIED)
    private long mLastModified;
    @SerializedName(value = KEY_LAST_MSG)
    private String mLastMsg;
    @SerializedName(value = KEY_PM_ID)
    private String mPmId;
    @SerializedName(value = KEY_UNREAD_COUNT)
    private int mUnreadCount;
    @SerializedName(value = KEY_USER)
    private TTPodUser mUser;

    public String getPmId() {
        return this.mPmId;
    }

    public TTPodUser getUser() {
        return this.mUser;
    }

    public int getUnreadCount() {
        return this.mUnreadCount;
    }

    public long getLastModified() {
        return this.mLastModified;
    }

    public String getLastMsg() {
        return this.mLastMsg;
    }
}
