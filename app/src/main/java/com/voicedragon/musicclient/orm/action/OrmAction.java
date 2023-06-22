package com.voicedragon.musicclient.orm.action;

import com.j256.ormlite.field.DatabaseField;

/* loaded from: classes.dex */
public class OrmAction {

    /* renamed from: ID */
    public static final String f1057ID = "id";
    public static final String METHOD = "method";
    public static final String PARAM = "param";
    public static final String TABLE_NAME = "actioninfo";
    public static final String TIME = "time";
    public static final String UID = "uid";
    public static final String UUID = "uuid";
    @DatabaseField(columnName = "id", generatedId = true)

    /* renamed from: id */
    private int f1058id;
    @DatabaseField(columnName = METHOD)
    private int method;
    @DatabaseField(columnName = PARAM)
    private String param;
    @DatabaseField(columnName = "time")
    private long time;
    @DatabaseField(columnName = UID)
    private String uid;
    @DatabaseField(columnName = UUID)
    private String uuid;

    public int getId() {
        return this.f1058id;
    }

    public void setId(int id) {
        this.f1058id = id;
    }

    public int getMethod() {
        return this.method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getParam() {
        return this.param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
