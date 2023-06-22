package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;


import com.sds.android.ttpod.component.scaleimage.ScaleImageActivity;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class RecommendPost implements Serializable {
    @SerializedName(value = "author")
    private String mAuthor;
    @SerializedName(value = "desc")
    private String mDesc;
    @SerializedName(value = "action")
    private ForwardAction mForwardAction;
    @SerializedName(value = "id")
    private long mId;
    @SerializedName(value = "listen_count")
    private String mListenCount;
    @SerializedName(value = "song")
    private OnlineMediaItem mMediaItem;
    @SerializedName(value = "name")
    private String mName;
    @SerializedName(value = "order")
    private int mOrder;
    @SerializedName(value = ScaleImageActivity.KEY_PIC_URL)
    private String mPicUrl;
    @SerializedName(value = "reason")
    private String mReason;
    @SerializedName(value = "songlist")
    private ArrayList<OnlineMediaItem> mSongList;
    private long mTime;
    @SerializedName(value = "type")
    private int mType;

    public RecommendPost() {
        this.mReason = "";
        this.mForwardAction = new ForwardAction();
        this.mListenCount = "";
        this.mAuthor = "";
        this.mName = "";
        this.mDesc = "";
        this.mPicUrl = "";
    }

    public RecommendPost(long j, int i, String str, String str2, String str3, String str4, String str5, String str6, int i2) {
        this.mReason = "";
        this.mForwardAction = new ForwardAction();
        this.mListenCount = "";
        this.mAuthor = "";
        this.mName = "";
        this.mDesc = "";
        this.mPicUrl = "";
        this.mId = j;
        this.mOrder = i;
        this.mReason = str;
        this.mListenCount = str2;
        this.mAuthor = str3;
        this.mName = str4;
        this.mDesc = str5;
        this.mPicUrl = str6;
        this.mType = i2;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public String getReason() {
        return this.mReason;
    }

    public ForwardAction getForwardAction() {
        return this.mForwardAction;
    }

    public void setForwardAction(ForwardAction forwardAction) {
        this.mForwardAction = forwardAction;
    }

    public String getListenCount() {
        return this.mListenCount;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public String getName() {
        return this.mName;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public int getType() {
        return this.mType;
    }

    public long getId() {
        return this.mId;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    public ArrayList<OnlineMediaItem> getSongList() {
        return this.mSongList;
    }

    public void setSongList(ArrayList<OnlineMediaItem> arrayList) {
        this.mSongList = arrayList;
    }

    public OnlineMediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public void setMediaItem(OnlineMediaItem onlineMediaItem) {
        this.mMediaItem = onlineMediaItem;
    }
}
