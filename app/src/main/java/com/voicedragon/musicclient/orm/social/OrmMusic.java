package com.voicedragon.musicclient.orm.social;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmMusic.TABLE_NAME)
/* loaded from: classes.dex */
public class OrmMusic {
    public static final String ARTIST = "artist";
    public static final String COVER_URL = "cover_url";
    public static final String MUSIC_ID = "_id";
    public static final String TABLE_NAME = "music";
    public static final String TITLE = "title";
    @DatabaseField(columnName = "artist")
    private String artist;
    @DatabaseField(columnName = COVER_URL)
    private String coverUrl;
    @DatabaseField(columnName = "_id", id = true)
    private String musicID;
    @DatabaseField(columnName = "title")
    private String title;

    public String getMusicID() {
        return this.musicID;
    }

    public void setMusicID(String musicID) {
        this.musicID = musicID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
