package com.sds.android.ttpod.fragment.apshare;

import androidx.core.os.EnvironmentCompat;
import com.sds.android.ttpod.adapter.p069a.Velocity;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* renamed from: com.sds.android.ttpod.fragment.apshare.a */
/* loaded from: classes.dex */
public class TransmittableMediaItem {

    /* renamed from: a */
    private MediaItem f4931a;

    /* renamed from: b */
    private int f4932b;

    /* renamed from: c */
    private EnumC1381a f4933c;

    /* renamed from: d */
    private Velocity f4934d;

    /* renamed from: e */
    private String f4935e;

    /* renamed from: f */
    private String f4936f;

    /* renamed from: g */
    private String f4937g;

    /* renamed from: h */
    private long f4938h;

    /* renamed from: i */
    private String f4939i;

    /* renamed from: a */
    public MediaItem m5772a() {
        return this.f4931a;
    }

    public TransmittableMediaItem(MediaItem mediaItem, int i, EnumC1381a enumC1381a) {
        this.f4933c = EnumC1381a.TRANSMIT_IDLE;
        this.f4931a = mediaItem;
        this.f4932b = i;
        this.f4933c = enumC1381a;
        this.f4934d = null;
        this.f4935e = "";
        this.f4936f = "";
    }

    public TransmittableMediaItem(MediaItem mediaItem) {
        this(mediaItem, 0, EnumC1381a.TRANSMIT_IDLE);
    }

    /* renamed from: b */
    public String m5766b() {
        return this.f4939i;
    }

    /* renamed from: a */
    public void m5767a(String str) {
        this.f4939i = str;
    }

    /* renamed from: c */
    public String m5764c() {
        return this.f4935e;
    }

    /* renamed from: b */
    public void m5765b(String str) {
        if (str == null && str.equals("")) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        this.f4935e = str;
    }

    /* renamed from: d */
    public String m5762d() {
        return this.f4936f;
    }

    /* renamed from: c */
    public void m5763c(String str) {
        if (str == null && str.equals("")) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        this.f4936f = str;
    }

    /* renamed from: e */
    public String m5760e() {
        return this.f4937g;
    }

    /* renamed from: d */
    public void m5761d(String str) {
        if (str == null && str.equals("")) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        this.f4937g = str;
    }

    /* renamed from: f */
    public Velocity m5759f() {
        return this.f4934d;
    }

    /* renamed from: a */
    public void m5769a(Velocity velocity) {
        this.f4934d = velocity;
    }

    /* renamed from: a */
    public void m5770a(long j) {
        this.f4938h = j;
    }

    /* renamed from: g */
    public int m5758g() {
        return this.f4932b;
    }

    /* renamed from: a */
    public void m5771a(int i) {
        this.f4932b = i;
    }

    /* renamed from: h */
    public EnumC1381a m5757h() {
        return this.f4933c;
    }

    /* renamed from: a */
    public void m5768a(EnumC1381a enumC1381a) {
        this.f4933c = enumC1381a;
    }

    public String toString() {
        return "title: " + this.f4931a.getTitle() + ", sender: " + this.f4935e + ", receiver: " + this.f4936f;
    }

    /* compiled from: TransmittableMediaItem.java */
    /* renamed from: com.sds.android.ttpod.fragment.apshare.a$a */
    /* loaded from: classes.dex */
    public enum EnumC1381a {
        TRANSMIT_IDLE(0),
        WAITING(1),
        TRANSMITTING(2),
        TRANSMIT_FINISHED(3),
        TRANSMIT_FAILED(4),
        TRANSMIT_CANCELLED(5);
        
        private int mValue;

        EnumC1381a(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }

        public EnumC1381a valueOf(int i) {
            return (i < 0 || i > 3) ? TRANSMIT_IDLE : values()[i];
        }
    }
}
