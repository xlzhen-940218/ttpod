package com.sds.android.ttpod.component.apshare;


import com.google.gson.annotations.SerializedName;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.component.apshare.d */
/* loaded from: classes.dex */
public class ExchangedItem implements Serializable {
    @SerializedName(value = "_id")

    /* renamed from: a */
    private String f3708a;
    @SerializedName(value = "_data")

    /* renamed from: b */
    private String f3709b;
    @SerializedName(value = "title")

    /* renamed from: c */
    private String f3710c;
    @SerializedName(value = "mime_type")

    /* renamed from: d */
    private String f3711d;
    @SerializedName(value = "artist")

    /* renamed from: e */
    private String f3712e;
    @SerializedName(value = "album")

    /* renamed from: f */
    private String f3713f;
    @SerializedName(value = "_size")

    /* renamed from: g */
    private String f3714g;
    @SerializedName(value = "duration")

    /* renamed from: h */
    private String f3715h;
    @SerializedName(value = "sender")

    /* renamed from: i */
    private String f3716i;
    @SerializedName(value = "receiver")

    /* renamed from: j */
    private String f3717j;
    @SerializedName(value = "receiver_ip")

    /* renamed from: k */
    private String f3718k;

    public ExchangedItem(MediaItem mediaItem, String str, String str2, String str3) {
        this.f3708a = mediaItem.getID();
        this.f3709b = mediaItem.getLocalDataSource();
        this.f3710c = mediaItem.getTitle();
        this.f3711d = mediaItem.getMimeType();
        this.f3712e = mediaItem.getArtist();
        this.f3713f = mediaItem.getAlbum();
        this.f3714g = String.valueOf(mediaItem.getSize());
        this.f3715h = String.valueOf(mediaItem.getDuration());
        this.f3716i = str;
        this.f3717j = str2;
        this.f3718k = str3;
    }

    public ExchangedItem(TransmittableMediaItem transmittableMediaItem) {
        this(transmittableMediaItem.m5772a(), transmittableMediaItem.m5764c(), transmittableMediaItem.m5762d(), transmittableMediaItem.m5760e());
    }

    public ExchangedItem() {
        this(MediaItem.MEDIA_ITEM_NULL, "", "", "");
    }

    /* renamed from: a */
    public String m7103a() {
        return this.f3710c;
    }

    /* renamed from: b */
    public String m7102b() {
        return this.f3711d;
    }

    /* renamed from: c */
    public String m7101c() {
        return this.f3712e;
    }

    /* renamed from: d */
    public String m7100d() {
        return this.f3713f;
    }

    /* renamed from: e */
    public String m7099e() {
        return this.f3714g;
    }

    /* renamed from: f */
    public String m7098f() {
        return this.f3715h;
    }

    public String toString() {
        return "_id: " + this.f3708a + ", title: " + this.f3710c + ", "
                + "mime_type" + ": " + this.f3711d + ", _data: " + this.f3709b
                + ", album: " + this.f3713f + ", artist: " + this.f3712e + ", " + "_size" + ": "
                + this.f3714g + ", " + "duration" + ": " + this.f3715h + ", sender: "
                + this.f3716i + ", " + "receiver" + ": " + this.f3717j + ", receiver_ip: " + this.f3718k;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExchangedItem) {
            ExchangedItem exchangedItem = (ExchangedItem) obj;
            return this.f3710c.equals(exchangedItem.m7103a()) && this.f3711d.equals(exchangedItem.m7102b()) && this.f3713f.equals(exchangedItem.m7100d()) && this.f3712e.equals(exchangedItem.m7101c()) && this.f3714g.equals(exchangedItem.m7099e()) && this.f3715h.equals(exchangedItem.m7098f());
        }
        return false;
    }

    public int hashCode() {
        return this.f3710c.hashCode() & this.f3711d.hashCode() & this.f3713f.hashCode() & this.f3712e.hashCode() & this.f3714g.hashCode() & this.f3715h.hashCode();
    }

    /* renamed from: g */
    public TransmittableMediaItem m7097g() {
        int i;
        NumberFormatException e;
        long j;
        int i2;
        try {
            i = Integer.valueOf(this.f3715h).intValue();
        } catch (NumberFormatException e2) {
            i = 0;
            e = e2;
        }
        try {
            j = Long.valueOf(this.f3714g).longValue();
            i2 = i;
        } catch (NumberFormatException e3) {
            e = e3;
            e.printStackTrace();
            j = 0;
            i2 = i;
            MediaItem mediaItem = new MediaItem(this.f3708a, 0L, this.f3709b, "", this.f3710c, this.f3712e, this.f3713f, "", "", this.f3711d, 0, Integer.valueOf(i2), 0, 0, 0, 0, 0, 0, "", 0, 0, 0L, 0L, 0L, false, "", "");
            mediaItem.setSize(j);
            TransmittableMediaItem transmittableMediaItem = new TransmittableMediaItem(mediaItem);
            transmittableMediaItem.m5765b(this.f3716i);
            transmittableMediaItem.m5763c(this.f3717j);
            transmittableMediaItem.m5761d(this.f3718k);
            return transmittableMediaItem;
        }
        MediaItem mediaItem2 = new MediaItem(this.f3708a, 0L, this.f3709b, "", this.f3710c, this.f3712e, this.f3713f, "", "", this.f3711d, 0, Integer.valueOf(i2), 0, 0, 0, 0, 0, 0, "", 0, 0, 0L, 0L, 0L, false, "", "");
        mediaItem2.setSize(j);
        TransmittableMediaItem transmittableMediaItem2 = new TransmittableMediaItem(mediaItem2);
        transmittableMediaItem2.m5765b(this.f3716i);
        transmittableMediaItem2.m5763c(this.f3717j);
        transmittableMediaItem2.m5761d(this.f3718k);
        return transmittableMediaItem2;
    }
}
