package com.voicedragon.musicclient.orm.playlist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmSongMenuSongs.TABLENAME)
/* loaded from: classes.dex */
public class OrmSongMenuSongs {
    public static final String ALBUM = "album";
    public static final String ALBUMID = "albumid";
    public static final String DISPLAY = "display";
    public static final String FIRST = "first";
    public static final String KEY = "key";
    public static final String MD5 = "md5";
    public static final String MUSIC_PATH = "music_path";
    public static final String PIC_PATH = "pic_path";
    public static final String SINGER = "singer";
    public static final String TABLENAME = "songmenu_songs";
    public static final String TIME = "time";
    public static final String TITLE = "title";
    public static final String _ID = "_id";
    @DatabaseField(columnName = "_id", generatedId = true)
    private long _id;
    @DatabaseField(columnName = "album")
    private String album;
    @DatabaseField(columnName = "albumid")
    private long albumid;
    @DatabaseField(columnName = "display")
    private String display;
    @DatabaseField(columnName = "first")
    private String first;
    @DatabaseField(columnName = "id")

    /* renamed from: id */
    private int f1065id;
    @DatabaseField(columnName = "key")
    private String key;
    @DatabaseField(columnName = "md5")
    private String md5;
    @DatabaseField(columnName = "music_path")
    private String music_path;
    @DatabaseField(columnName = "pic_path")
    private String pic_path;
    @DatabaseField(columnName = "singer")
    private String singer;
    @DatabaseField(columnName = "time")
    private long time;
    @DatabaseField(columnName = "title")
    private String title;

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return this.f1065id;
    }

    public void setId(int id) {
        this.f1065id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return this.singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long get_id() {
        return this._id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getAlbumid() {
        return this.albumid;
    }

    public void setAlbumid(long albumid) {
        this.albumid = albumid;
    }

    public String getPic_path() {
        return this.pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getMusic_path() {
        return this.music_path;
    }

    public void setMusic_path(String music_path) {
        this.music_path = music_path;
    }
}
