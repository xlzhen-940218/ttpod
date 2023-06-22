package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import android.graphics.drawable.Drawable;

import com.sds.android.sdk.lib.util.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Post implements BaseId, ISongListItem, Serializable {
    private static final String KEY_COMMENT_COUNT = "comment_count";
    private static final String KEY_CREATE_TIME = "create_at";
    private static final String KEY_FAVORITE_COUNT = "favorite_count";
    private static final String KEY_MEDIA = "song";
    private static final String KEY_PICS = "pics";
    private static final String KEY_POST_ID = "id";
    private static final String KEY_REPOSTED_MSG = "reposted_msg";
    private static final String KEY_REPOST_COUNT = "repost_count";
    private static final String KEY_SONGLIST_ID = "songlistid";
    private static final String KEY_SONGLIST_NAME = "songlistname";
    private static final String KEY_SONG_LIST = "songlist";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_TWEET = "tweet";
    private static final String KEY_TYPE = "type";
    private static final String KEY_USER = "user";
    @SerializedName(value = KEY_COMMENT_COUNT)
    private int mCommentCount;
    @SerializedName(value = KEY_CREATE_TIME)
    private long mCreateTimeInSecond;
    @SerializedName(value = KEY_FAVORITE_COUNT)
    private int mFavoriteCount;
    @SerializedName(value = KEY_MEDIA)
    private OnlineMediaItem mMediaItem;
    @SerializedName(value = "pics")
    private ArrayList<String> mPicList;
    @SerializedName(value = "id")
    private long mPostId;
    @SerializedName(value = KEY_REPOST_COUNT)
    private int mRepostCount;
    @SerializedName(value = KEY_REPOSTED_MSG)
    private Post mRepostedMsg;
    @SerializedName(value = KEY_SONG_LIST)
    private ArrayList<OnlineMediaItem> mSongList;
    @SerializedName(value = KEY_SONGLIST_ID)
    private long mSonglistId;
    @SerializedName(value = "type")
    private int mType;
    @SerializedName(value = KEY_USER)
    private LabeledTTPodUser mUser;
    @SerializedName(value = KEY_TWEET)
    private String mTweet = "";
    @SerializedName(value = KEY_SONGLIST_NAME)
    private String mSongListName = "";
    @SerializedName(value = KEY_TAGS)
    private String mTags = "";

    public int getFavoriteCount() {
        return this.mFavoriteCount;
    }

    public void increaseFavoriteCount() {
        this.mFavoriteCount++;
    }

    public void decreaseFavoriteCount() {
        if (this.mFavoriteCount > 0) {
            this.mFavoriteCount--;
        }
    }

    public Post getRepostedMsg() {
        return this.mRepostedMsg;
    }

    public int getRepostCount() {
        return this.mRepostCount;
    }

    public void setRepostCount(int i) {
        this.mRepostCount = i;
    }

    public void setPics(ArrayList<String> arrayList) {
        this.mPicList = arrayList;
    }

    public int getCommentCount() {
        return this.mCommentCount;
    }

    public void setCommentCount(int i) {
        this.mCommentCount = i;
    }

    public String getSongListName() {
        return this.mSongListName;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public CharSequence getTitleName() {
        return StringUtils.m8346a(this.mSongListName) ? this.mMediaItem != null ? this.mMediaItem.getTitle() + " - " + this.mMediaItem.getArtist() : "" : this.mSongListName;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public CharSequence getSubtitleName() {
        return null;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public int getIconResourceId() {
        return 0;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public Drawable getIcon() {
        return null;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.ISongListItem
    public int getSubItemCount() {
        if (this.mSongList == null || this.mSongList.isEmpty()) {
            return 1;
        }
        return this.mSongList.size();
    }

    public int getType() {
        return this.mType;
    }

    public long getSonglistId() {
        return this.mSonglistId;
    }

    public String getTweet() {
        return this.mTweet;
    }

    public ArrayList<OnlineMediaItem> getSongList() {
        return this.mSongList;
    }

    public ArrayList<String> getPicList() {
        return this.mPicList;
    }

    public long getPostId() {
        return this.mPostId;
    }

    public long getCreateTimeInSecond() {
        return this.mCreateTimeInSecond;
    }

    public LabeledTTPodUser getUser() {
        return this.mUser;
    }

    public OnlineMediaItem getMediaItem() {
        return this.mMediaItem;
    }

    @Override // com.sds.android.cloudapi.ttpod.data.BaseId
    public long getId() {
        return getPostId();
    }

    public String getTags() {
        return this.mTags;
    }
}
