package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class Comment implements BaseId, Serializable {
    private static final String KEY_COMMENT_ID = "comment_id";
    private static final String KEY_CREATE_TIME = "create_at";
    private static final String KEY_ID = "id";
    private static final String KEY_REPLY_TO = "reply_to";
    private static final String KEY_TWEET = "tweet";
    private static final String KEY_USER = "user";
    @SerializedName(value = KEY_COMMENT_ID)
    private long mCommentId;
    @SerializedName(value = KEY_CREATE_TIME)
    private long mCreateTimeInSecond;
    @SerializedName(value = "id")
    private long mId;
    @SerializedName(value = KEY_REPLY_TO)
    private TTPodUser mReplyTo;
    @SerializedName(value = KEY_TWEET)
    private String mTweet;
    @SerializedName(value = KEY_USER)
    private TTPodUser mUser;

    public Comment() {
    }

    public Comment(long j, long j2, String str, TTPodUser tTPodUser, TTPodUser tTPodUser2, long j3) {
        this.mId = j;
        this.mCreateTimeInSecond = j2;
        this.mTweet = str;
        this.mUser = tTPodUser;
        this.mReplyTo = tTPodUser2;
        this.mCommentId = j3;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.BaseId
    public long getId() {
        return this.mId;
    }

    public long getCommentId() {
        return this.mCommentId;
    }

    public TTPodUser getUser() {
        return this.mUser;
    }

    public TTPodUser getReplyTo() {
        return this.mReplyTo;
    }

    public String getTweet() {
        return this.mTweet;
    }

    public long getCreateTimeInSecond() {
        return this.mCreateTimeInSecond;
    }
}
