package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/* loaded from: classes.dex */
public class NewNoticeCount implements Serializable {
    private static final String KEY_NEW_COMMENT_COUNT = "new_comment_count";
    private static final String KEY_NEW_FOLLOWER_COUNT = "new_follower_count";
    private static final String KEY_NEW_PRIVATE_LETTERS_COUNT = "new_private_letters_count";
    private static final String KEY_NEW_REPOST_COUNT = "new_repost_count";
    private static final String KEY_SYSTEM_NOTICE_COUNT = "new_system_notice_count";
    @SerializedName(value = KEY_NEW_COMMENT_COUNT)
    private int mNewCommentCount;
    @SerializedName(value = KEY_NEW_FOLLOWER_COUNT)
    private int mNewFollowerCount;
    @SerializedName(value = KEY_NEW_PRIVATE_LETTERS_COUNT)
    private int mNewPrivateLettersCount;
    @SerializedName(value = KEY_NEW_REPOST_COUNT)
    private int mNewRepostCount;
    @SerializedName(value = KEY_SYSTEM_NOTICE_COUNT)
    private int mSystemNoticeCount;

    public int getNewMessageTotalCount() {
        return this.mNewCommentCount + this.mNewRepostCount + this.mSystemNoticeCount + this.mNewFollowerCount + this.mNewPrivateLettersCount;
    }

    public int getNewCommentCount() {
        return this.mNewCommentCount;
    }

    public int getNewRepostCount() {
        return this.mNewRepostCount;
    }

    public void setNewCommentCount(int i) {
        this.mNewCommentCount = i;
    }

    public void setNewRepostCount(int i) {
        this.mNewRepostCount = i;
    }

    public int getSystemNoticeCount() {
        return this.mSystemNoticeCount;
    }

    public void setSystemNoticeCount(int i) {
        this.mSystemNoticeCount = i;
    }

    public int getNewFollowerCount() {
        return this.mNewFollowerCount;
    }

    public void setNewFollowerCount(int i) {
        this.mNewFollowerCount = i;
    }

    public int getmNewPrivateLettersCount() {
        return this.mNewPrivateLettersCount;
    }

    public void setNewPrivateLettersCount(int i) {
        this.mNewPrivateLettersCount = i;
    }
}
