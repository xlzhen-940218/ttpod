package com.voicedragon.musicclient.orm.social;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;

/* loaded from: classes.dex */
public class OrmTopic implements Parcelable {
    public static final String ARTIST = "artist";
    public static final String ARTWORK = "artwork_url";
    public static final String C1_CONTENT = "c1_content";
    public static final String C1_ICON_URL = "c1_icon_url";
    public static final String C1_ID = "c1_id";
    public static final String C1_POST_TIME = "c1_post_time";
    public static final String C1_SCORE = "c1_score";
    public static final String C1_USER_ID = "c1_user_id";
    public static final String C1_USER_NAME = "c1_user_name";
    public static final String CONTENT = "content";
    public static final Creator<OrmTopic> CREATOR = new Creator<OrmTopic>() { // from class: com.voicedragon.musicclient.orm.social.OrmTopic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OrmTopic[] newArray(int size) {
            return new OrmTopic[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OrmTopic createFromParcel(Parcel parcel) {
            OrmTopic topic = new OrmTopic();
            topic.setTopicID(parcel.readString());
            topic.setOriginalTopicID(parcel.readString());
            topic.setOriginalUserID(parcel.readString());
            topic.setOriginalUserName(parcel.readString());
            topic.setOriginalIconUrl(parcel.readString());
            topic.setPostTime(parcel.readLong());
            topic.setContent(parcel.readString());
            topic.setTotalListened(parcel.readInt());
            topic.setTotalLoved(parcel.readInt());
            topic.setTotalShared(parcel.readInt());
            topic.setTotalComments(parcel.readInt());
            topic.setLoved(parcel.readInt() == 0);
            topic.setUserID(parcel.readString());
            topic.setUserName(parcel.readString());
            topic.setIconUrl(parcel.readString());
            topic.setFollowed(parcel.readInt() == 0);
            topic.setMusicID(parcel.readString());
            topic.setTitle(parcel.readString());
            topic.setArtist(parcel.readString());
            topic.setArtworkUrl(parcel.readString());
            topic.setC1ID(parcel.readString());
            topic.setC1PostTime(parcel.readLong());
            topic.setC1Content(parcel.readString());
            topic.setC1Score(parcel.readInt());
            topic.setC1UserID(parcel.readString());
            topic.setC1UserName(parcel.readString());
            topic.setC1IconUrl(parcel.readString());
            topic.setFav_time(parcel.readLong());
            return topic;
        }
    };
    public static final String FAV_TIME = "fav_time";
    public static final String FOLLOWED = "followed";
    public static final String ICON_URL = "icon_url";
    public static final String LOVED = "loved";
    public static final String MUSIC_ID = "music_id";
    public static final String ORIGINAL_ICON_URL = "original_icon_url";
    public static final String ORIGINAL_TOPIC_ID = "original_id";
    public static final String ORIGINAL_USER_ID = "original_user_id";
    public static final String ORIGINAL_USER_NAME = "original_user_name";
    public static final String POST_TIME = "post_time";
    public static final String TITLE = "title";
    public static final String TOPIC_ID = "_id";
    public static final String TOTAL_COMMENTS = "total_comments";
    public static final String TOTAL_LISTENED = "total_listened";
    public static final String TOTAL_LOVED = "total_loved";
    public static final String TOTAL_SHARED = "total_shared";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    @DatabaseField(columnName = "artist")
    private String artist;
    @DatabaseField(columnName = ARTWORK)
    private String artworkUrl;
    @DatabaseField(columnName = C1_CONTENT)
    private String c1Content;
    @DatabaseField(columnName = C1_ID)
    private String c1ID;
    @DatabaseField(columnName = C1_ICON_URL)
    private String c1IconUrl;
    @DatabaseField(columnName = C1_POST_TIME)
    private long c1PostTime;
    @DatabaseField(columnName = C1_SCORE)
    private int c1Score;
    @DatabaseField(columnName = C1_USER_ID)
    private String c1UserID;
    @DatabaseField(columnName = C1_USER_NAME)
    private String c1UserName;
    @DatabaseField(columnName = "content")
    private String content;
    @DatabaseField(columnName = FAV_TIME)
    private long fav_time;
    @DatabaseField(columnName = FOLLOWED)
    private boolean followed;
    @DatabaseField(columnName = "icon_url")
    private String iconUrl;
    @DatabaseField(columnName = LOVED)
    private boolean loved;
    @DatabaseField(columnName = "music_id")
    private String musicID;
    @DatabaseField(columnName = ORIGINAL_ICON_URL)
    private String originalIconUrl;
    @DatabaseField(columnName = ORIGINAL_TOPIC_ID)
    private String originalTopicID;
    @DatabaseField(columnName = ORIGINAL_USER_ID)
    private String originalUserID;
    @DatabaseField(columnName = ORIGINAL_USER_NAME)
    private String originalUserName;
    @DatabaseField(columnName = "post_time")
    private long postTime;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "_id", id = true)
    private String topicID;
    @DatabaseField(columnName = TOTAL_COMMENTS)
    private int totalComments;
    @DatabaseField(columnName = TOTAL_LISTENED)
    private int totalListened;
    @DatabaseField(columnName = TOTAL_LOVED)
    private int totalLoved;
    @DatabaseField(columnName = TOTAL_SHARED)
    private int totalShared;
    @DatabaseField(columnName = "user_id")
    private String userID;
    @DatabaseField(columnName = "user_name")
    private String userName;

    public String getTopicID() {
        return this.topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getOriginalTopicID() {
        return this.originalTopicID;
    }

    public void setOriginalTopicID(String originalTopicID) {
        this.originalTopicID = originalTopicID;
    }

    public String getOriginalUserID() {
        return this.originalUserID;
    }

    public void setOriginalUserID(String originalUserID) {
        this.originalUserID = originalUserID;
    }

    public String getOriginalUserName() {
        return this.originalUserName;
    }

    public void setOriginalUserName(String originalUserName) {
        this.originalUserName = originalUserName;
    }

    public String getOriginalIconUrl() {
        return this.originalIconUrl;
    }

    public void setOriginalIconUrl(String originalIconUrl) {
        this.originalIconUrl = originalIconUrl;
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

    public int getTotalListened() {
        return this.totalListened;
    }

    public void setTotalListened(int totalListened) {
        this.totalListened = totalListened;
    }

    public int getTotalLoved() {
        return this.totalLoved;
    }

    public void setTotalLoved(int totalLoved) {
        this.totalLoved = totalLoved;
    }

    public int getTotalShared() {
        return this.totalShared;
    }

    public void setTotalShared(int totalShared) {
        this.totalShared = totalShared;
    }

    public int getTotalComments() {
        return this.totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    public boolean isLoved() {
        return this.loved;
    }

    public void setLoved(boolean loved) {
        this.loved = loved;
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

    public boolean isFollowed() {
        return this.followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public String getMusicID() {
        return this.musicID;
    }

    public void setMusicID(String musicID) {
        this.musicID = musicID;
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

    public String getArtworkUrl() {
        return this.artworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public String getC1ID() {
        return this.c1ID;
    }

    public void setC1ID(String c1id) {
        this.c1ID = c1id;
    }

    public long getC1PostTime() {
        return this.c1PostTime;
    }

    public void setC1PostTime(long c1PostTime) {
        this.c1PostTime = c1PostTime;
    }

    public String getC1Content() {
        return this.c1Content;
    }

    public void setC1Content(String c1Content) {
        this.c1Content = c1Content;
    }

    public int getC1Score() {
        return this.c1Score;
    }

    public void setC1Score(int c1Score) {
        this.c1Score = c1Score;
    }

    public String getC1UserID() {
        return this.c1UserID;
    }

    public void setC1UserID(String c1UserID) {
        this.c1UserID = c1UserID;
    }

    public String getC1UserName() {
        return this.c1UserName;
    }

    public void setC1UserName(String c1UserName) {
        this.c1UserName = c1UserName;
    }

    public String getC1IconUrl() {
        return this.c1IconUrl;
    }

    public void setC1IconUrl(String c1IconUrl) {
        this.c1IconUrl = c1IconUrl;
    }

    public long getFav_time() {
        return this.fav_time;
    }

    public void setFav_time(long fav_time) {
        this.fav_time = fav_time;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static Creator<OrmTopic> getCreator() {
        return CREATOR;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.topicID);
        dest.writeString(this.originalTopicID);
        dest.writeString(this.originalUserID);
        dest.writeString(this.originalUserName);
        dest.writeString(this.originalIconUrl);
        dest.writeLong(this.postTime);
        dest.writeString(this.content);
        dest.writeInt(this.totalListened);
        dest.writeInt(this.totalLoved);
        dest.writeInt(this.totalShared);
        dest.writeInt(this.totalComments);
        dest.writeInt(this.loved ? 0 : 1);
        dest.writeString(this.userID);
        dest.writeString(this.userName);
        dest.writeString(this.iconUrl);
        dest.writeInt(this.followed ? 0 : 1);
        dest.writeString(this.musicID);
        dest.writeString(this.title);
        dest.writeString(this.artist);
        dest.writeString(this.artworkUrl);
        dest.writeString(this.c1ID);
        dest.writeLong(this.c1PostTime);
        dest.writeString(this.c1Content);
        dest.writeInt(this.c1Score);
        dest.writeString(this.c1UserID);
        dest.writeString(this.c1UserName);
        dest.writeString(this.c1IconUrl);
        dest.writeLong(this.fav_time);
    }
}
