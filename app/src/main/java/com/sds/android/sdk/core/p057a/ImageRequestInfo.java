package com.sds.android.sdk.core.p057a;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.sds.android.sdk.core.p057a.ImageCache;
import com.sds.android.sdk.lib.util.SecurityUtils;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.sdk.core.a.e */
/* loaded from: classes.dex */
public final class ImageRequestInfo {

    /* renamed from: a */
    private ImageCache.InterfaceC0565a f2288a;

    /* renamed from: b */
    private int f2289b;

    /* renamed from: c */
    private int f2290c;

    /* renamed from: d */
    private String f2291d;

    /* renamed from: e */
    private String f2292e;

    /* renamed from: f */
    private String f2293f;

    /* renamed from: g */
    private Bitmap f2294g;

    /* renamed from: h */
    private ImageView.ScaleType f2295h;

    public ImageRequestInfo(String str, String str2, String str3, int i, int i2, ImageView.ScaleType scaleType, ImageCache.InterfaceC0565a interfaceC0565a) {
        if (interfaceC0565a == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
        this.f2288a = interfaceC0565a;
        this.f2291d = str;
        this.f2293f = str2;
        this.f2292e = str3;
        this.f2289b = i;
        this.f2290c = i2;
        this.f2295h = scaleType;
    }

    /* renamed from: a */
    public ImageCache.InterfaceC0565a m8786a() {
        return this.f2288a;
    }

    /* renamed from: b */
    public String m8784b() {
        return this.f2291d;
    }

    /* renamed from: c */
    public String m8783c() {
        return this.f2292e;
    }

    /* renamed from: d */
    public String m8782d() {
        return this.f2293f + File.separator + (this.f2292e == null ? SecurityUtils.C0610b.m8359b(this.f2291d) : this.f2292e);
    }

    /* renamed from: e */
    public int m8781e() {
        return this.f2289b;
    }

    /* renamed from: f */
    public int m8780f() {
        return this.f2290c;
    }

    /* renamed from: g */
    public Bitmap m8779g() {
        return this.f2294g;
    }

    /* renamed from: a */
    public void m8785a(Bitmap bitmap) {
        this.f2294g = bitmap;
    }

    /* renamed from: h */
    public ImageView.ScaleType m8778h() {
        return this.f2295h;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageRequestInfo imageRequestInfo = (ImageRequestInfo) obj;
        if (this.f2291d != null) {
            if (!this.f2291d.equals(imageRequestInfo.f2291d)) {
                return false;
            }
        } else if (imageRequestInfo.f2291d != null) {
            return false;
        }
        if (this.f2292e != null) {
            if (!this.f2292e.equals(imageRequestInfo.f2292e)) {
                return false;
            }
        } else if (imageRequestInfo.f2292e != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.f2291d != null ? this.f2291d.hashCode() : 0) + (((((this.f2288a.hashCode() * 31) + this.f2289b) * 31) + this.f2290c) * 31)) * 31) + (this.f2292e != null ? this.f2292e.hashCode() : 0);
    }
}
