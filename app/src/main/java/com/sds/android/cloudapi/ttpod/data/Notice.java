package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class Notice implements Serializable {
    private static final String KEY_COMMENT = "c_comment";
    private static final String KEY_NOTICE_ID = "notice_id";
    private static final String KEY_NOTICE_TYPE = "notice_type";
    private static final String KEY_ORIGIN_COMMENT = "comment";
    private static final String KEY_ORIGIN_POST = "msg";
    private static final String KEY_POST = "r_msg";
    private static final String KEY_READED = "readed";
    private static final String KEY_TIMESTAMP = "timestamp";
    @SerializedName(value = KEY_COMMENT)
    private Comment mComment;
    @SerializedName(value = KEY_NOTICE_ID)
    private String mNoticeId;
    @SerializedName(value = KEY_NOTICE_TYPE)
    private int mNoticeType;
    @SerializedName(value = "comment")
    private Comment mOriginComment;
    @SerializedName(value = "msg")
    private Post mOriginPost;
    @SerializedName(value = KEY_POST)
    private Post mPost;
    @SerializedName(value = KEY_READED)
    private int mReadStatus;
    @SerializedName(value = KEY_TIMESTAMP)
    private long mTimeStamp;

    public String getNoticeId() {
        return this.mNoticeId;
    }

    public int getNoticeType() {
        return this.mNoticeType;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public boolean getReadStatus() {
        return this.mReadStatus != 0;
    }

    public void setReadStatus() {
        this.mReadStatus = 1;
    }

    public Comment getOriginComment() {
        return this.mOriginComment;
    }

    public Comment getComment() {
        return this.mComment;
    }

    public Post getOriginPost() {
        return this.mOriginPost;
    }

    public Post getPost() {
        return this.mPost;
    }
}
