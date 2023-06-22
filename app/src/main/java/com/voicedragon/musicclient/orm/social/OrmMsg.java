package com.voicedragon.musicclient.orm.social;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmMsg.TABLE_NAME)
/* loaded from: classes.dex */
public class OrmMsg {
    public static final String CONTENT = "content";
    public static final String MSG_ID = "_id";
    public static final String OBJ_UID = "obj_uid";
    public static final String POST_TIME = "post_time";
    public static final String READ = "read";
    public static final String SEND_BY_ME = "send_by_me";
    public static final String TABLE_NAME = "message";
    public static final String USER_ICON = "user_icon";
    public static final String USER_NAME = "user_name";
    @DatabaseField(columnName = "content")
    private String content;
    @DatabaseField(columnName = "_id", id = true)
    private String msgID;
    @DatabaseField(columnName = OBJ_UID)
    private String objUserID;
    @DatabaseField(columnName = "post_time")
    private long postTime;
    @DatabaseField(columnName = READ)
    private boolean read;
    @DatabaseField(columnName = SEND_BY_ME)
    private boolean sendByMe;
    @DatabaseField(columnName = USER_ICON)
    private String userIcon;
    @DatabaseField(columnName = "user_name")
    private String userName;

    public String getMsgID() {
        return this.msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getObjUserID() {
        return this.objUserID;
    }

    public void setObjUserID(String objUserID) {
        this.objUserID = objUserID;
    }

    public boolean isSendByMe() {
        return this.sendByMe;
    }

    public void setSendByMe(boolean sendByMe) {
        this.sendByMe = sendByMe;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return this.userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public long getPostTime() {
        return this.postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
