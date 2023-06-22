package com.sds.android.ttpod.component.soundsearch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.voicedragon.musicclient.record.DoresoMusicTrack;

import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.io.Serializable;

/* loaded from: classes.dex */
public final class SoundSearchInfo implements Parcelable, Serializable {
    @SerializedName(value = "md5")

    /* renamed from: b */
    private String md5;
    @SerializedName(value = "name")

    /* renamed from: c */
    private String name;
    @SerializedName(value = "artist")

    /* renamed from: d */
    private String artist;
    @SerializedName(value = "album")

    /* renamed from: e */
    private String album;
    @SerializedName(value = "rating")

    /* renamed from: f */
    private double rating;
    @SerializedName(value = "offset")

    /* renamed from: g */
    private long offset;

    /* renamed from: h */
    private MediaItem mediaItem;

    /* renamed from: a */
    public static final SoundSearchInfo soundSearchInfo = new SoundSearchInfo();
    public static final Parcelable.Creator<SoundSearchInfo> CREATOR = new Parcelable.Creator<SoundSearchInfo>() { // from class: com.sds.android.ttpod.component.soundsearch.SoundSearchInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public SoundSearchInfo createFromParcel(Parcel parcel) {
            return new SoundSearchInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public SoundSearchInfo[] newArray(int i) {
            return new SoundSearchInfo[i];
        }
    };

    /* renamed from: a */
    public String m5829a() {
        return this.md5;
    }

    /* renamed from: b */
    public double m5827b() {
        return this.rating;
    }

    /* renamed from: c */
    public String m5826c() {
        return this.artist;
    }

    /* renamed from: d */
    public long m5825d() {
        return this.offset;
    }

    /* renamed from: e */
    public String m5824e() {
        return this.name;
    }

    /* renamed from: a */
    public void m5828a(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    /* renamed from: f */
    public MediaItem m5823f() {
        return this.mediaItem;
    }

    public boolean equals(Object obj) {
        return (obj instanceof SoundSearchInfo) && this.md5.equals(((SoundSearchInfo) obj).m5829a());
    }

    public int hashCode() {
        return this.md5.hashCode();
    }

    public String toString() {
        return "SoundSearchInfo{mMd5='" + this.md5 + "', mRating=" + this.rating + ", mArtist='" + this.artist + "', mOffset=" + this.offset + ", mAlbum='" + this.album + "', mName='" + this.name + "'}";
    }

    private SoundSearchInfo(Parcel parcel) {
        this.name = "";
        this.artist = "";
        this.album = "";
        if (parcel != null) {
            this.md5 = parcel.readString();
            this.name = parcel.readString();
            this.artist = parcel.readString();
            this.album = parcel.readString();
            this.rating = parcel.readDouble();
            this.offset = parcel.readLong();
            this.mediaItem = (MediaItem) parcel.readSerializable();
        }
    }

    public SoundSearchInfo() {
        this.name = "";
        this.artist = "";
        this.album = "";
    }

    public SoundSearchInfo(DoresoMusicTrack doresoMusicTrack) {
        this.name = "";
        this.artist = "";
        this.album = "";
        this.md5 = doresoMusicTrack.getMd5();
        this.name = doresoMusicTrack.getName();
        this.artist = doresoMusicTrack.getArtist();
        this.album = doresoMusicTrack.getAlbum();
        this.rating = doresoMusicTrack.getRating();
        this.offset = doresoMusicTrack.getOffset();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.md5);
        parcel.writeString(this.name);
        parcel.writeString(this.artist);
        parcel.writeString(this.album);
        parcel.writeDouble(this.rating);
        parcel.writeLong(this.offset);
        parcel.writeSerializable(this.mediaItem);
    }
}
