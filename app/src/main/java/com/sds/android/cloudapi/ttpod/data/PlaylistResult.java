package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.sds.android.sdk.lib.request.DataListResult;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class PlaylistResult extends DataListResult<PlaylistResult.PlaylistItem> {
    @SerializedName(value = "pages")
    private int mPages;
    @SerializedName(value = "rows")
    private int mRows;

    public int getPages() {
        return this.mPages;
    }

    public int getCount() {
        return this.mRows;
    }

    /* loaded from: classes.dex */
    public static class PlaylistItem implements Serializable {
        @SerializedName(value = "author")
        private String mAuthor;
        @SerializedName(value = "comment_count")
        private int mCommentCount;
        @SerializedName(value = "create_at")
        private long mCreateTime;
        @SerializedName(value = "desc")
        private String mDesc;
        @SerializedName(value = "_id")
        private int mId;
        @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
        private String mPicUrl;
        @SerializedName(value = "quan_id")
        private long mQuanId;
        @SerializedName(value = "song_list")
        private String mSongList;
        @SerializedName(value = "special_song_list")
        private List<Long> mSpecialSongList;
        @SerializedName(value = "tags")
        private List<String> mTags;
        @SerializedName(value = "title")
        private String mTitle;

        public int getId() {
            return this.mId;
        }

        public long getQuanId() {
            return this.mQuanId;
        }

        public long getCreateTime() {
            return this.mCreateTime;
        }

        public int getCommentCount() {
            return this.mCommentCount;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getAuthor() {
            return this.mAuthor;
        }

        public String getSongs() {
            return this.mSongList;
        }

        public String getDescription() {
            return this.mDesc;
        }

        public String getPicUrl() {
            return this.mPicUrl;
        }

        public List<String> getTagList() {
            return this.mTags;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PlaylistItem playlistItem = (PlaylistItem) obj;
            if (this.mTitle == null ? playlistItem.mTitle != null : !this.mTitle.equals(playlistItem.mTitle)) {
                return false;
            }
            if (this.mAuthor == null ? playlistItem.mAuthor != null : !this.mAuthor.equals(playlistItem.mAuthor)) {
                return false;
            }
            if (this.mSongList == null ? playlistItem.mSongList != null : !this.mSongList.equals(playlistItem.mSongList)) {
                return false;
            }
            if (this.mSpecialSongList == null ? playlistItem.mSpecialSongList != null : !this.mSpecialSongList.equals(playlistItem.mSpecialSongList)) {
                return false;
            }
            if (this.mDesc == null ? playlistItem.mDesc != null : !this.mDesc.equals(playlistItem.mDesc)) {
                return false;
            }
            if (this.mPicUrl == null ? playlistItem.mPicUrl != null : !this.mPicUrl.equals(playlistItem.mPicUrl)) {
                return false;
            }
            if (this.mTags == null ? playlistItem.mTags != null : !this.mTags.equals(playlistItem.mTags)) {
                return false;
            }
            return this.mId == playlistItem.mId && this.mCreateTime == playlistItem.mCreateTime && this.mCommentCount == playlistItem.mCommentCount;
        }

        public int hashCode() {
            return (((((((((this.mPicUrl != null ? this.mPicUrl.hashCode() : 0) + (((this.mDesc != null ? this.mDesc.hashCode() : 0) + (((this.mSpecialSongList != null ? this.mSpecialSongList.hashCode() : 0) + (((this.mSongList != null ? this.mSongList.hashCode() : 0) + (((this.mAuthor != null ? this.mAuthor.hashCode() : 0) + ((this.mTitle != null ? this.mTitle.hashCode() : 0) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.mTags != null ? this.mTags.hashCode() : 0)) * 31) + this.mId) * 31) + ((int) this.mCreateTime)) * 31) + this.mCommentCount;
        }

        public AlbumItem convertToAlbumItem() {
            AlbumItem albumItem = new AlbumItem();
            albumItem.setId(this.mId);
            albumItem.setName(this.mTitle);
            albumItem.setSingerName(this.mAuthor);
            albumItem.setPublishTime(createTime(this.mCreateTime));
            albumItem.setSongIds(this.mSongList);
            albumItem.setLang("中文");
            albumItem.setDesc(this.mDesc);
            albumItem.setPic500(this.mPicUrl);
            return albumItem;
        }

        private String createTime(long j) {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date(TimeUnit.SECONDS.toMillis(j)));
        }

        public int getSongListSize() {
            if (StringUtils.isEmpty(this.mSongList)) {
                return 0;
            }
            return this.mSongList.split(",").length;
        }
    }
}
