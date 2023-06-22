package com.voicedragon.musicclient.api;

/* renamed from: com.voicedragon.musicclient.api.MV */
/* loaded from: classes.dex */
public class MV {
    private String artist;
    private String imgUrl;
    private String mvName;
    private String mvUrl;

    public String getMvName() {
        return this.mvName;
    }

    public void setMvName(String mvName) {
        this.mvName = mvName;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMvUrl() {
        return this.mvUrl;
    }

    public void setMvUrl(String mvUrl) {
        this.mvUrl = mvUrl;
    }
}
