package com.voicedragon.musicclient.orm.social;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmComment.TABLE_NAME)
/* loaded from: classes.dex */
public class OrmComment {
    public static final String COMMENT_ID = "_id";
    public static final String COMMENT_TYPE = "type";
    public static final String CONTENT = "content";
    public static final String ICON_URL = "icon_url";
    public static final String ORDINARY = "ordinary";
    public static final String POST_TIME = "post_time";
    public static final String SCORE = "score";
    public static final String TABLE_NAME = "comment";
    public static final String TOPIC_ID = "topic_id";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    @DatabaseField(columnName = "_id", id = true, uniqueCombo = true)
    private String commentID;
    @DatabaseField(columnName = "content")
    private String content;
    @DatabaseField(columnName = "icon_url")
    private String iconUrl;
    @DatabaseField(columnName = ORDINARY)
    private boolean ordinary;
    @DatabaseField(columnName = "post_time")
    private long postTime;
    @DatabaseField(columnName = "score")
    private int score;
    @DatabaseField(columnName = TOPIC_ID)
    private String topicID;
    @DatabaseField(columnName = "type", uniqueCombo = true)
    private String type;
    @DatabaseField(columnName = "user_id")
    private String userID;
    @DatabaseField(columnName = "user_name")
    private String userName;

    public String getCommentID() {
        return this.commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOrdinary() {
        return this.ordinary;
    }

    public void setOrdinary(boolean ordinary) {
        this.ordinary = ordinary;
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

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTopicID() {
        return this.topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String toString() {
        return "OrmComment [commentID=" + this.commentID + ", postTime=" + this.postTime + ", content=" + this.content + ", score=" + this.score + ", topicID=" + this.topicID + ", userID=" + this.userID + ", userName=" + this.userName + ", iconUrl=" + this.iconUrl + "]";
    }
}
