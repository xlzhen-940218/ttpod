package com.voicedragon.musicclient.orm.social;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = OrmUser.TABLE_NAME)
/* loaded from: classes.dex */
public class OrmUser {
    public static final String GENDER = "gender";
    public static final String ICON_URL = "icon_url";
    public static final String RELATIONSHIP = "relationship";
    public static final int RELATIONSHIP_ALL = 1;
    public static final int RELATIONSHIP_FOLLOWED_BY_ME = 2;
    public static final int RELATIONSHIP_MY_FAN = 3;
    public static final int RELATIONSHIP_NONE = 0;
    public static final String SCORE = "score";
    public static final String TABLE_NAME = "user";
    public static final String TAG = "tag";
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "user_name";
    @DatabaseField(columnName = GENDER)
    private int gender;
    @DatabaseField(columnName = "icon_url")
    private String iconUrl;
    @DatabaseField(columnName = RELATIONSHIP)
    private int relationship;
    @DatabaseField(columnName = "score")
    private int score;
    @DatabaseField(columnName = TAG)
    private String tag;
    @DatabaseField(columnName = "_id", id = true)
    private String userID;
    @DatabaseField(columnName = "user_name")
    private String userName;

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

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getRelationship() {
        return this.relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public boolean isFollowedByMe() {
        return this.relationship == 1 || this.relationship == 2;
    }

    public boolean isMyFan() {
        return this.relationship == 1 || this.relationship == 3;
    }
}
