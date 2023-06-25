package com.sds.android.ttpod.media.mediastore;

import android.os.Parcel;
import android.os.Parcelable;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class MediaItem implements Parcelable, Serializable {
    public static final String MIMETYPE_MV = "[MV]";
    private String mAlbum;
    private String mArtist;
    private Integer mBitRate;
    private int mCensorLevel;
    private Integer mChannels;
    private String mComment;
    private String mComposer;
    private Long mDateAddedInMills;
    private Long mDateLastAccessInMills;
    private Long mDateModifiedInMills;
    private Integer mDuration;
    private Integer mErrorStatus;
    private String mExtra;
    private boolean mFav;
    private String mFolder;
    private String mGenre;
    private Integer mGrade;
    private String mGroupID;
    private boolean mHasOutList;
    private String mID;
    private String mLocalDataSource;
    private String mMimeType;
    private ArrayList<OnlineMediaItem.OutListItem> mOutList;
    private Integer mSampleRate;
    private long mSize;
    private Long mSongID;
    private String mSongIdGenById;
    private Integer mStartTime;
    private String mTitle;
    private String mTitleKey;
    private Integer mTrack;
    private Integer mUseCount;
    private Integer mYear;
    public static final MediaItem MEDIA_ITEM_NULL = new MediaItem("", 0L, null, "", "", "", "", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, 0L, 0L, 0L, false, "", "");
    public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator<MediaItem>() { // from class: com.sds.android.ttpod.media.mediastore.MediaItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaItem createFromParcel(Parcel parcel) {
            return new MediaItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaItem[] newArray(int i) {
            return new MediaItem[i];
        }
    };

    public MediaItem(String ID, Long songId, String localDataSource, String folder, String title
            , String artist, String album, String genre, String composer, String mimetype
            , Integer startTime, Integer duration, Integer track, Integer year, Integer grade
            , Integer bitrate, Integer sample_rate, Integer channels, String comment, Integer errorStatus
            , Integer useCount
            , Long dateAddedInMills, Long dateModifiedInMills, Long dateLastAccessInMills, boolean fav
            , String extra, String groupId) {
        this.mID = ID;
        this.mSongID = songId;
        this.mLocalDataSource = localDataSource;
        this.mFolder = folder;
        this.mTitle = title;
        this.mArtist = artist;
        this.mAlbum = album;
        this.mGenre = genre;
        this.mComment = comment;
        this.mComposer = composer;
        this.mMimeType = mimetype;
        this.mDateModifiedInMills = dateModifiedInMills;
        this.mDateAddedInMills = dateAddedInMills;
        this.mDateLastAccessInMills = dateLastAccessInMills;
        this.mGrade = grade;
        this.mBitRate = bitrate;
        this.mSampleRate = sample_rate;
        this.mChannels = channels;
        this.mTrack = track;
        this.mYear = year;
        this.mStartTime = startTime;
        this.mDuration = duration;
        this.mUseCount = useCount;
        this.mErrorStatus = errorStatus;
        this.mFav = fav;
        this.mExtra = extra;
        this.mGroupID = groupId;
        if (this.mID == null) {
            if (this.mLocalDataSource == null && this.mSongID == null && extra == null) {
                throw new IllegalArgumentException("invalid mediaItem, DataSource and SongId are null!");
            }
            if (this.mLocalDataSource == null && this.mSongID == null && extra != null) {
                this.mID = genIDWithExtra(extra);
            } else {
                this.mID = this.mLocalDataSource == null ? genIDWithSongID(this.mSongID) : genIDWithMediaSourceAndStartTime(this.mLocalDataSource, this.mStartTime);
            }
        }
    }

    private MediaItem(Parcel parcel) {
        readFromParcel(parcel);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        if (this.mID == null || mediaItem.mID == null) {
            return true;
        }
        return this.mID.equals(mediaItem.mID);
    }

    public int hashCode() {
        if (this.mID != null) {
            return this.mID.hashCode();
        }
        return 0;
    }

    public String getID() {
        return this.mID;
    }

    void setID(String str) {
        this.mID = str;
    }

    public Long getSongID() {
        return this.mSongID;
    }

    public void setSongID(Long l) {
        this.mSongID = l;
    }

    public String getLocalDataSource() {
        return this.mLocalDataSource;
    }

    public void setLocalDataSource(String str) {
        this.mLocalDataSource = str;
    }

    public String getFolder() {
        return this.mFolder;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
        this.mTitleKey = null;
    }

    public String getTitleKey() {
        if (this.mTitleKey == null) {
            if (this.mTitle == null || StringUtils.m8344a(this.mTitle, "<unknown>")) {
                this.mTitleKey = "";
            } else {
                this.mTitleKey = PinyinUtils.buildKey(this.mTitle);
            }
        }
        return this.mTitleKey;
    }

    public long getSize() {
        return this.mSize;
    }

    public void setSize(long j) {
        this.mSize = j;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    public String getGenre() {
        return this.mGenre;
    }

    public void setGenre(String str) {
        this.mGenre = str;
    }

    public String getComposer() {
        return this.mComposer;
    }

    public void setComposer(String str) {
        this.mComposer = str;
    }

    public String getComment() {
        return this.mComment == null ? "" : this.mComment;
    }

    public void setComment(String str) {
        this.mComment = str;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public void setMimeType(String str) {
        this.mMimeType = str;
    }

    public Long getDateModifiedInMills() {
        return this.mDateModifiedInMills;
    }

    public Long getDateAddedInMills() {
        return this.mDateAddedInMills;
    }

    public Long getDateLastAccessInMills() {
        return this.mDateLastAccessInMills;
    }

    public void setDateLastPlayInMills(Long l) {
        this.mDateLastAccessInMills = l;
    }

    public Integer getGrade() {
        return this.mGrade;
    }

    public void setGrade(Integer num) {
        this.mGrade = num;
    }

    public Integer getBitRate() {
        return this.mBitRate;
    }

    public void setBitRate(Integer num) {
        this.mBitRate = num;
    }

    public Integer getSampleRate() {
        return this.mSampleRate;
    }

    public void setSampleRate(Integer num) {
        this.mSampleRate = num;
    }

    public Integer getChannels() {
        return this.mChannels;
    }

    public void setChannels(Integer num) {
        this.mChannels = num;
    }

    public Integer getTrack() {
        return this.mTrack;
    }

    public void setTrack(Integer num) {
        this.mTrack = num;
    }

    public Integer getYear() {
        return this.mYear;
    }

    public void setYear(Integer num) {
        this.mYear = num;
    }

    public Integer getStartTime() {
        return this.mStartTime;
    }

    public Integer getDuration() {
        return this.mDuration;
    }

    public void setDuration(Integer num) {
        this.mDuration = num;
    }

    public Integer getUseCount() {
        return this.mUseCount;
    }

    public Integer getErrorStatus() {
        return this.mErrorStatus;
    }

    public void setErrorStatus(Integer num) {
        this.mErrorStatus = num;
    }

    public boolean getFav() {
        return this.mFav;
    }

    public void setFav(boolean z) {
        this.mFav = z;
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    public void setGroupID(String str) {
        this.mGroupID = str;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public void setExtra(String str) {
        this.mExtra = str;
    }

    public boolean isOnline() {
        if (this.mSongID != null && StringUtils.isEmpty(this.mSongIdGenById)) {
            this.mSongIdGenById = genIDWithSongID(this.mSongID);
        }
        return this.mID.equals(this.mSongIdGenById);
    }

    public boolean isThirdParty() {
        return this.mExtra != null && this.mID.equals(genIDWithExtra(this.mExtra));
    }

    public boolean containMV() {
        return this.mMimeType != null && this.mMimeType.contains(MIMETYPE_MV);
    }

    public long getArtistID() {
        assertOnlineMedia();
        if (this.mExtra != null) {
            try {
                return ((OnlineMediaItem) JSONUtils.fromJson(this.mExtra, OnlineMediaItem.class)).getArtistId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0L;
    }

    public void setOutListList(ArrayList<OnlineMediaItem.OutListItem> arrayList) {
        this.mOutList = arrayList;
    }

    public ArrayList<OnlineMediaItem.OutListItem> getOutList() {
        return this.mOutList;
    }

    public void setHasOutList(boolean z) {
        this.mHasOutList = z;
    }

    public boolean hasOutList() {
        return this.mHasOutList;
    }

    public int getCensorLevel() {
        return this.mCensorLevel;
    }

    public void setCensorLevel(int i) {
        this.mCensorLevel = i;
    }

    public AudioQuality quality() {
        return AudioQuality.quality(this.mBitRate.intValue());
    }

    public boolean isNull() {
        return equals(MEDIA_ITEM_NULL);
    }

    public static String genIDWithSongID(Long l) {
        if (l == null) {
            throw new IllegalArgumentException("SongId must not be null!");
        }
        return SecurityUtils.C0610b.m8361a(String.valueOf(l));
    }

    public static String genIDWithExtra(String str) {
        if (str == null) {
            throw new IllegalArgumentException("extra must not be null!");
        }
        return SecurityUtils.C0610b.m8361a(str);
    }

    static String genIDWithMediaSourceAndStartTime(String str, Integer num) {
        if (str == null && num == null) {
            throw new IllegalArgumentException("SongId must not be null!");
        }
        return SecurityUtils.C0610b.m8361a(str + num);
    }

    private void assertOnlineMedia() {
        if (EnvironmentUtils.C0602a.m8502i() && !isOnline()) {
            throw new IllegalArgumentException("not Online Media");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mID);
        parcel.writeString(this.mSongID == null ? null : this.mSongID.toString());
        parcel.writeString(this.mLocalDataSource);
        parcel.writeString(this.mFolder);
        parcel.writeString(this.mTitle);
        parcel.writeLong(this.mSize);
        parcel.writeString(this.mArtist);
        parcel.writeString(this.mAlbum);
        parcel.writeString(this.mGenre);
        parcel.writeString(this.mComposer);
        parcel.writeString(this.mComment);
        parcel.writeString(this.mMimeType);
        parcel.writeString(this.mDateModifiedInMills == null ? null : this.mDateModifiedInMills.toString());
        parcel.writeString(this.mDateAddedInMills == null ? null : this.mDateAddedInMills.toString());
        parcel.writeString(this.mDateLastAccessInMills == null ? null : this.mDateLastAccessInMills.toString());
        parcel.writeString(this.mGrade == null ? null : this.mGrade.toString());
        parcel.writeString(this.mBitRate == null ? null : this.mBitRate.toString());
        parcel.writeString(this.mSampleRate == null ? null : this.mSampleRate.toString());
        parcel.writeString(this.mChannels == null ? null : this.mChannels.toString());
        parcel.writeString(this.mTrack == null ? null : this.mTrack.toString());
        parcel.writeString(this.mYear == null ? null : this.mYear.toString());
        parcel.writeString(this.mStartTime == null ? null : this.mStartTime.toString());
        parcel.writeString(this.mDuration == null ? null : this.mDuration.toString());
        parcel.writeString(this.mUseCount == null ? null : this.mUseCount.toString());
        parcel.writeString(this.mErrorStatus != null ? this.mErrorStatus.toString() : null);
        parcel.writeInt(this.mFav ? 1 : 0);
        parcel.writeString(this.mGroupID);
        parcel.writeString(this.mExtra);
    }

    private void readFromParcel(Parcel parcel) {
        this.mID = parcel.readString();
        this.mSongID = readLong(parcel.readString());
        this.mLocalDataSource = parcel.readString();
        this.mFolder = parcel.readString();
        this.mTitle = parcel.readString();
        this.mSize = parcel.readLong();
        this.mArtist = parcel.readString();
        this.mAlbum = parcel.readString();
        this.mGenre = parcel.readString();
        this.mComposer = parcel.readString();
        this.mComment = parcel.readString();
        this.mMimeType = parcel.readString();
        this.mDateModifiedInMills = readLong(parcel.readString());
        this.mDateAddedInMills = readLong(parcel.readString());
        this.mDateLastAccessInMills = readLong(parcel.readString());
        this.mGrade = readInt(parcel.readString());
        this.mBitRate = readInt(parcel.readString());
        this.mSampleRate = readInt(parcel.readString());
        this.mChannels = readInt(parcel.readString());
        this.mTrack = readInt(parcel.readString());
        this.mYear = readInt(parcel.readString());
        this.mStartTime = readInt(parcel.readString());
        this.mDuration = readInt(parcel.readString());
        this.mUseCount = readInt(parcel.readString());
        this.mErrorStatus = readInt(parcel.readString());
        this.mFav = parcel.readInt() > 0;
        this.mGroupID = parcel.readString();
        this.mExtra = parcel.readString();
    }

    private Long readLong(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (Exception e) {
            return null;
        }
    }

    private Integer readInt(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception e) {
            return null;
        }
    }
}
