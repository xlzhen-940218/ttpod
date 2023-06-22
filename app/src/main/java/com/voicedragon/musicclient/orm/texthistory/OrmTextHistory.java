package com.voicedragon.musicclient.orm.texthistory;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmTextHistory.TABLE_NAME)
/* loaded from: classes.dex */
public class OrmTextHistory {
    public static final String CONTENT = "desc";

    /* renamed from: ID */
    public static final String f1066ID = "_id";
    public static final String TABLE_NAME = "searchhistory";
    @DatabaseField(columnName = "desc")
    private String content;
    @DatabaseField(columnName = "_id", generatedId = true)

    /* renamed from: id */
    private long f1067id;

    public long getId() {
        return this.f1067id;
    }

    public void setId(long id) {
        this.f1067id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
