package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


/* loaded from: classes.dex */
public class SystemNotice {
    private static final String KEY_ACTION = "action";
    private static final String KEY_MSG = "msg";
    private static final String KEY_PIC = "pic";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    @SerializedName(value = "action")
    private int mAction;
    @SerializedName(value = "msg")
    private String mMessage;
    @SerializedName(value = "pic")
    private String mPicture;
    @SerializedName(value = KEY_TIME_STAMP)
    private long mTimeStamp;
    @SerializedName(value = "title")
    private String mTitle;
    @SerializedName(value = "url")
    private String mUrl;

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public int getAction() {
        return this.mAction;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getPicture() {
        return this.mPicture;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
