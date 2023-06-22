package com.voicedragon.musicclient.orm.download;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "library")
/* loaded from: classes.dex */
public class OrmDownloadEntry {
    public static final String ALBUM_ID = "album_id";
    public static final String ALBUM_NAME = "album_name";
    public static final String ALBUM_RATING = "album_rating";
    public static final String ALBUM_TRACK_NUM = "album_track_num";
    public static final String ARTIST_NAME = "artist_name";
    public static final String DOWNLOADED = "downloaded";
    public static final String MD5 = "album_image";
    public static final String TRACK_AUDIO_ID = "track_audioid";
    public static final String TRACK_DURATION = "track_duration";
    public static final String TRACK_ID = "track_id";
    public static final String TRACK_NAME = "track_name";
    public static final String TRACK_STREAM = "track_stream";
    public static final String TRACK_URL = "track_url";
    @DatabaseField(columnName = ALBUM_ID)
    private int albumID;
    @DatabaseField(columnName = ALBUM_NAME)
    private String albumName;
    @DatabaseField(columnName = ALBUM_TRACK_NUM)
    private int albumTrackNum;
    @DatabaseField(columnName = ARTIST_NAME)
    private String artistName;
    @DatabaseField(columnName = DOWNLOADED)
    private int downloadState;
    private long downloadedSize;
    @DatabaseField(columnName = MD5)
    private String md5;
    private long totalSize;
    @DatabaseField(columnName = TRACK_AUDIO_ID)
    private long trackAudioID;
    @DatabaseField(columnName = TRACK_DURATION)
    private int trackDuration;
    @DatabaseField(columnName = TRACK_ID, unique = true)
    private long trackID;
    @DatabaseField(columnName = TRACK_NAME)
    private String trackName;
    @DatabaseField(columnName = TRACK_STREAM)
    private String trackStream;
    @DatabaseField(columnName = TRACK_URL)
    private String trackUrl;

    public long getTrackID() {
        return this.trackID;
    }

    public void setTrackID(long trackID) {
        this.trackID = trackID;
    }

    public int getDownloadState() {
        return this.downloadState;
    }

    public void setDownloadState(int downloadState) {
        this.downloadState = downloadState;
    }

    public String getTrackName() {
        return this.trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getTrackDuration() {
        return this.trackDuration;
    }

    public void setTrackDuration(int trackDuration) {
        this.trackDuration = trackDuration;
    }

    public String getTrackUrl() {
        return this.trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public String getTrackStream() {
        return this.trackStream;
    }

    public void setTrackStream(String trackStream) {
        this.trackStream = trackStream;
    }

    public long getTrackAudioID() {
        return this.trackAudioID;
    }

    public void setTrackAudioID(long trackAudioID) {
        this.trackAudioID = trackAudioID;
    }

    public int getAlbumID() {
        return this.albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getAlbumTrackNum() {
        return this.albumTrackNum;
    }

    public void setAlbumTrackNum(int albumTrackNum) {
        this.albumTrackNum = albumTrackNum;
    }

    public long getDownloadedSize() {
        return this.downloadedSize;
    }

    public void setDownloadedSize(long downloadedSize) {
        this.downloadedSize = downloadedSize;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public String getProgressString() {
        if (this.totalSize == 0) {
            return "0%";
        }
        return String.valueOf(String.valueOf((this.downloadedSize * 100) / this.totalSize)) + "%";
    }

    public int hashCode() {
        int result = (this.md5 == null ? 0 : this.md5.hashCode()) + 31;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            OrmDownloadEntry other = (OrmDownloadEntry) obj;
            return this.md5 == null ? other.md5 == null : this.md5.equals(other.md5);
        }
        return false;
    }
}
