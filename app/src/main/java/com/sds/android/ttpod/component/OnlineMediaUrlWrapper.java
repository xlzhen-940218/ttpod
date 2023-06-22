package com.sds.android.ttpod.component;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;

/* renamed from: com.sds.android.ttpod.component.b */
/* loaded from: classes.dex */
public class OnlineMediaUrlWrapper {

    /* renamed from: a */
    private EnumC1133a f3809a;

    /* renamed from: b */
    private OnlineMediaItem.Url f3810b;

    /* compiled from: OnlineMediaUrlWrapper.java */
    /* renamed from: com.sds.android.ttpod.component.b$a */
    /* loaded from: classes.dex */
    public enum EnumC1133a {
        MEDIA,
        VIDEO
    }

    public OnlineMediaUrlWrapper(EnumC1133a enumC1133a, OnlineMediaItem.Url url) {
        if (enumC1133a == null || url == null) {
            throw new IllegalArgumentException("type and url should not be null");
        }
        this.f3809a = enumC1133a;
        this.f3810b = url;
    }

    /* renamed from: a */
    public EnumC1133a m7014a() {
        return this.f3809a;
    }

    /* renamed from: b */
    public OnlineMediaItem.Url m7013b() {
        return this.f3810b;
    }
}
