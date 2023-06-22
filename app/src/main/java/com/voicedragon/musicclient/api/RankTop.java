package com.voicedragon.musicclient.api;

/* loaded from: classes.dex */
public class RankTop {
    private String artist;
    private String bid;
    private String content;
    private String description;
    private String md5;
    private String pic_url;
    private String title;
    private int type_id;

    public int getType_id() {
        return this.type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getBid() {
        return this.bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic_url() {
        return this.pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = String.valueOf(pic_url) + "&pic_type=android";
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
