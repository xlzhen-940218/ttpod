package com.voicedragon.musicclient.orm.playlist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmFavorite.TABLE_NAME)
/* loaded from: classes.dex */
public class OrmFavorite {
    public static final String ABLUM = "ablum";
    public static final String ARTIST = "artist";

    /* renamed from: ID */
    public static final String f1061ID = "id";
    public static final String MD5 = "md5";
    public static final String TABLE_NAME = "newfavourite";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    @DatabaseField(columnName = ABLUM)
    private String ablum;
    @DatabaseField(columnName = "artist")
    private String artist;
    @DatabaseField(columnName = "id", generatedId = true)

    /* renamed from: id */
    private int f1062id;
    @DatabaseField(columnName = "md5")
    private String md5;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "type")
    private int type;

    public int getId() {
        return this.f1062id;
    }

    public void setId(int id) {
        this.f1062id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAblum() {
        return this.ablum;
    }

    public void setAblum(String ablum) {
        this.ablum = ablum;
    }
}
