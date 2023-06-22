package com.voicedragon.musicclient.orm.history;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmUnidentify.TABLE_NAME)
/* loaded from: classes.dex */
public class OrmUnidentify {
    public static final String DATA = "data";
    public static final String DATE = "date";
    public static final String DESC = "desc";
    public static final String DURATION = "duration";

    /* renamed from: ID */
    public static final String f1060ID = "_id";
    public static final String IS_UPLOADING = "isUploading";
    public static final String MARK = "mark";
    public static final String MD5 = "md5";
    public static final String SUCCESS = "success";
    public static final String TABLE_NAME = "unidentify";
    public static final String TYPE = "type";
    @DatabaseField(columnName = "_id", id = true)
    private long _id;
    @DatabaseField(columnName = "data", dataType = DataType.BYTE_ARRAY)
    private byte[] data;
    @DatabaseField(columnName = "date")
    private int date;
    @DatabaseField(columnName = "desc")
    private String desc;
    @DatabaseField(columnName = "duration")
    private int duration;
    @DatabaseField(columnName = "mark")
    private int mark;
    @DatabaseField(columnName = "md5")
    private String md5;
    @DatabaseField(columnName = "success")
    private int success;
    @DatabaseField(columnName = "type")
    private int type;

    public long get_id() {
        return this._id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSuccess() {
        return this.success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getMark() {
        return this.mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
