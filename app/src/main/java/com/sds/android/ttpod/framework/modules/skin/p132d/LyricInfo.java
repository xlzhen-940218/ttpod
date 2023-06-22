package com.sds.android.ttpod.framework.modules.skin.p132d;

import android.text.TextUtils;
import java.io.File;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.i */
/* loaded from: classes.dex */
public final class LyricInfo {

    /* renamed from: a */
    private String f6625a;

    /* renamed from: b */
    private String f6626b;

    /* renamed from: c */
    private String f6627c;

    /* renamed from: d */
    private String f6628d;

    /* renamed from: e */
    private String f6629e;

    /* renamed from: f */
    private long f6630f;

    /* renamed from: g */
    private long f6631g;

    /* renamed from: h */
    private long f6632h;

    /* renamed from: i */
    private long f6633i;

    public int hashCode() {
        return (((((((((this.f6629e == null ? 0 : this.f6629e.hashCode()) + (((this.f6628d == null ? 0 : this.f6628d.hashCode()) + (((this.f6626b == null ? 0 : this.f6626b.hashCode()) + (((this.f6627c == null ? 0 : this.f6627c.hashCode()) + 31) * 31)) * 31)) * 31)) * 31) + ((int) (this.f6630f ^ (this.f6630f >>> 1)))) * 31) + (this.f6625a != null ? this.f6625a.hashCode() : 0)) * 31) + ((int) (this.f6632h ^ (this.f6632h >>> 1)))) * 31) + ((int) (this.f6633i ^ (this.f6633i >>> 1)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LyricInfo lyricInfo = (LyricInfo) obj;
        if (TextUtils.equals(this.f6625a, lyricInfo.f6625a) && TextUtils.equals(this.f6626b, lyricInfo.f6626b) && TextUtils.equals(this.f6627c, lyricInfo.f6627c) && TextUtils.equals(this.f6628d, lyricInfo.f6628d) && TextUtils.equals(this.f6629e, lyricInfo.f6629e) && this.f6630f == lyricInfo.f6630f && this.f6633i == lyricInfo.f6633i) {
            return this.f6632h == lyricInfo.f6632h;
        }
        return false;
    }

    /* renamed from: a */
    public String m3666a() {
        return this.f6629e;
    }

    /* renamed from: a */
    public void m3663a(String str) {
        this.f6629e = str;
        this.f6633i = new File(str).lastModified();
    }

    /* renamed from: b */
    public long m3662b() {
        return this.f6633i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!LyricUtils.m3643a(this.f6625a)) {
            sb.append(String.format("[ti:%s]\n", this.f6625a));
        }
        if (!LyricUtils.m3643a(this.f6626b)) {
            sb.append(String.format("[ar:%s]\n", this.f6626b));
        }
        if (!LyricUtils.m3643a(this.f6627c)) {
            sb.append(String.format("[al:%s]\n", this.f6627c));
        }
        if (!LyricUtils.m3643a(this.f6628d)) {
            sb.append(String.format("[by:%s]\n", this.f6628d));
        }
        if (this.f6630f != 0) {
            sb.append(String.format("[offset:%d]\n", Long.valueOf(this.f6630f)));
        }
        if (this.f6632h != 0) {
            sb.append(String.format("[total:%d]\n", Long.valueOf(this.f6632h)));
        }
        return sb.toString();
    }

    /* renamed from: b */
    public void m3660b(String str) {
        this.f6625a = str;
    }

    /* renamed from: c */
    public void m3657c(String str) {
        this.f6626b = str;
    }

    /* renamed from: d */
    public void m3655d(String str) {
        this.f6627c = str;
    }

    /* renamed from: e */
    public void m3653e(String str) {
        this.f6628d = str;
    }

    /* renamed from: a */
    public void m3664a(long j) {
        this.f6630f = j;
    }

    /* renamed from: b */
    public void m3661b(long j) {
        this.f6631g = j;
    }

    /* renamed from: c */
    public void m3658c(long j) {
        this.f6632h = j;
    }

    /* renamed from: c */
    public long m3659c() {
        return this.f6630f;
    }

    /* renamed from: d */
    public long m3656d() {
        return this.f6631g;
    }

    /* renamed from: e */
    public long m3654e() {
        return this.f6632h;
    }

    /* renamed from: a */
    public int m3665a(int i) {
        this.f6631g += i;
        return (int) (this.f6631g - this.f6630f);
    }

    /* renamed from: f */
    public boolean m3652f() {
        boolean z = this.f6630f != this.f6631g;
        if (z) {
            this.f6630f = this.f6631g;
        }
        return z;
    }

    /* renamed from: g */
    public int m3651g() {
        int i = (int) (this.f6630f - this.f6631g);
        this.f6631g = this.f6630f;
        return i;
    }
}
