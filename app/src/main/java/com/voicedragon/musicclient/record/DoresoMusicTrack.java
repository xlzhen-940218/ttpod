package com.voicedragon.musicclient.record;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class DoresoMusicTrack implements Parcelable {
    public static final Creator<DoresoMusicTrack> CREATOR = new Creator<DoresoMusicTrack>() { // from class: com.voicedragon.musicclient.record.DoresoMusicTrack.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DoresoMusicTrack createFromParcel(Parcel parcel) {
            DoresoMusicTrack track = new DoresoMusicTrack();
            track.setName(parcel.readString());
            track.setArtist(parcel.readString());
            track.setAlbum(parcel.readString());
            track.setMd5(parcel.readString());
            track.setOffset(parcel.readLong());
            track.setRating(parcel.readDouble());
            track.setDuration(parcel.readLong());
            track.setMusicpath(parcel.readString());
            track.setType(parcel.readInt());
            return track;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DoresoMusicTrack[] newArray(int size) {
            return new DoresoMusicTrack[size];
        }
    };
    private String album;
    private String artist;
    private long duration;

    /* renamed from: id */
    private String f1070id;
    private String md5;
    private String musicpath;
    private String name;
    private long offset;
    private double rating;
    private int type;

    public String getId() {
        return this.f1070id;
    }

    public void setId(String id) {
        this.f1070id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getMd5() {
        if (this.md5 == null) {
            this.md5 = "";
        }
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getOffset() {
        return this.offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMusicpath() {
        return this.musicpath;
    }

    public void setMusicpath(String musicpath) {
        this.musicpath = musicpath;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static Creator<DoresoMusicTrack> getCreator() {
        return CREATOR;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.artist);
        dest.writeString(this.album);
        dest.writeString(this.md5);
        dest.writeLong(this.offset);
        dest.writeDouble(this.rating);
        dest.writeLong(this.duration);
        dest.writeString(this.musicpath);
        dest.writeInt(this.type);
    }
}
