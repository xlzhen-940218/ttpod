package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.sds.android.ttpod.media.mediastore.old.MediaStoreOld;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class RelatedPost implements Serializable {
    @SerializedName(value = "audit")
    private long mAudit;
    @SerializedName(value = "comment_count")
    private long mCommentCount;
    @SerializedName(value = "create_at")
    private long mCreateAt;
    @SerializedName(value = "favorite_count")
    private long mFavoriteCount;
    @SerializedName(value = "id")
    private long mId;
    @SerializedName(value = "listener_count")
    private long mListenerCount;
    @SerializedName(value = "songlistid")
    private long mSongListId;
    @SerializedName(value = "songlistname")
    private String mSongListName;
    @SerializedName(value = "tweet")
    private String mTweet;
    @SerializedName(value = "type")
    private long mType;
    @SerializedName(value = "song")
    private RelatedPostSong mRelatedPostSong = new RelatedPostSong();
    @SerializedName(value = "user")
    private RelatedPostUser mRelatedPostUser = new RelatedPostUser();
    @SerializedName(value = "songlist")
    private ArrayList<RelatedPostItem> mRelatedSongList = new ArrayList<>();
    @SerializedName(value = "pics")
    private ArrayList<String> mPicsUrl = new ArrayList<>();

    public long getListenerCount() {
        return this.mListenerCount;
    }

    public long getId() {
        return this.mId;
    }

    public long getSongListId() {
        return this.mSongListId;
    }

    public String getTweet() {
        return this.mTweet;
    }

    public long getCreateAt() {
        return this.mCreateAt;
    }

    public long getAudit() {
        return this.mAudit;
    }

    public long getFavoriteCount() {
        return this.mFavoriteCount;
    }

    public String getSongListName() {
        return this.mSongListName;
    }

    public long getType() {
        return this.mType;
    }

    public RelatedPostSong getRelatedPostSong() {
        return this.mRelatedPostSong;
    }

    public RelatedPostUser getRelatedPostUser() {
        return this.mRelatedPostUser;
    }

    public ArrayList<RelatedPostItem> getRelatedSongList() {
        return this.mRelatedSongList;
    }

    public ArrayList<String> getPicsUrl() {
        return this.mPicsUrl;
    }

    /* loaded from: classes.dex */
    public class RelatedPostSong implements Serializable {
        @SerializedName(value = "_id")
        private long mId;
        @SerializedName(value = "pick_count")
        private long mPickCount;
        @SerializedName(value = "singer_id")
        private long mSingerId;
        @SerializedName(value = "singer_name")
        private String mSingerName;
        @SerializedName(value = "song_id")
        private long mSongId;
        @SerializedName(value = "song_name")
        private String mSongName;

        public RelatedPostSong() {
        }

        public long getId() {
            return this.mId;
        }

        public long getSingerId() {
            return this.mSingerId;
        }

        public String getSingerName() {
            return this.mSingerName;
        }

        public String getSongName() {
            return this.mSongName;
        }

        public long getPickCount() {
            return this.mPickCount;
        }

        public long getSongId() {
            return this.mSongId;
        }
    }

    /* loaded from: classes.dex */
    public class RelatedPostUser implements Serializable {
        @SerializedName(value = "_id")
        private long mId;
        @SerializedName(value = "label")
        private String mLabel;
        @SerializedName(value = User.KEY_NICK_NAME)
        private String mNickName;
        @SerializedName(value = User.KEY_AVATAR)
        private String mPic;
        @SerializedName(value = User.KEY_USER_ID)
        private long mTuid;
        @SerializedName(value = "v")
        private boolean mVip;

        public RelatedPostUser() {
        }

        public long getTuid() {
            return this.mTuid;
        }

        public long getId() {
            return this.mId;
        }

        public String getNickName() {
            return this.mNickName;
        }

        public String getPic() {
            return this.mPic;
        }

        public boolean isVip() {
            return this.mVip;
        }

        public String getLabel() {
            return this.mLabel;
        }
    }

    /* loaded from: classes.dex */
    public class RelatedPostItem implements Serializable {
        @SerializedName(value = "pick_count")
        private long mPickCount;
        @SerializedName(value = "song_id")
        private long mSongId;

        public RelatedPostItem() {
        }

        public long getPickCount() {
            return this.mPickCount;
        }

        public long getSongId() {
            return this.mSongId;
        }
    }
}
