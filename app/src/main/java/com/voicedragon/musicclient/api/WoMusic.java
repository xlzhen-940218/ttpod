package com.voicedragon.musicclient.api;

/* loaded from: classes.dex */
public class WoMusic {
    private String artist;
    private String musicUrl;
    private String songid;
    private String title;

    public String getMusicUrl() {
        return this.musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getSongid() {
        return this.songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
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
}
