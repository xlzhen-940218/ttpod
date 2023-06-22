package com.voicedragon.musicclient.orm.playlist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmLocal.TABLE_NAME)
/* loaded from: classes.dex */
public class OrmLocal {
    public static final String ALBUM = "album";
    public static final String ALBUMID = "albumid";
    public static final String DISPLAY = "display";
    public static final String FIRST = "first";

    /* renamed from: ID */
    public static final String f1063ID = "id";
    public static final String ISSEND = "isSend";
    public static final String KEY = "key";
    public static final String MD5 = "md5";
    public static final String MUSIC_PATH = "music_path";
    public static final String PIC_PATH = "pic_path";
    public static final String SINGER = "singer";
    public static final String SINGERFIRST = "singerfirst";
    public static final String TABLE_NAME = "localdir";
    public static final String TIME = "time";
    public static final String TITLE = "title";
    public static final String _ID = "_id";
    @DatabaseField(columnName = "_id")
    private long _id;
    @DatabaseField(columnName = "album")
    private String album;
    @DatabaseField(columnName = "albumid")
    private long albumid;
    @DatabaseField(columnName = "display")
    private String display;
    @DatabaseField(columnName = "time")
    private long duration;
    @DatabaseField(columnName = "first")
    private String first;
    @DatabaseField(columnName = "id", generatedId = true)

    /* renamed from: id */
    private int f1064id;
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
    @DatabaseField(columnName = SINGERFIRST)
    private String singerfirst;
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
        return this.f1064id;
    }

    public void setId(int id) {
        this.f1064id = id;
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

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getSingerfirst() {
        return this.singerfirst;
    }

    public void setSingerfirst(String singerfirst) {
        this.singerfirst = singerfirst;
    }
}
