package com.voicedragon.musicclient.orm.playlist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmSongMenu.TABLENAME)
/* loaded from: classes.dex */
public class OrmSongMenu {
    public static final String DURATION = "duration";
    public static final String KEY = "key";
    public static final String NAME = "name";
    public static final String NUM = "num";
    public static final String TABLEKEY = "singername";
    public static final String TABLENAME = "songmenus";
    public static final String _ID = "_id";
    @DatabaseField(columnName = "_id", generatedId = true)
    private int _id;
    @DatabaseField(columnName = "duration")
    private long duration;
    @DatabaseField(columnName = "key")
    private String key;
    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(columnName = NUM)
    private int num;

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
