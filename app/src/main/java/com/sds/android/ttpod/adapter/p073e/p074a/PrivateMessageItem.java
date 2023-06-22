package com.sds.android.ttpod.adapter.p073e.p074a;

import com.sds.android.cloudapi.ttpod.data.TTPodUser;

/* renamed from: com.sds.android.ttpod.adapter.e.a.e */
/* loaded from: classes.dex */
public class PrivateMessageItem {

    /* renamed from: a */
    private TTPodUser f3305a;

    /* renamed from: b */
    private long f3306b;

    /* renamed from: c */
    private String f3307c;

    /* renamed from: d */
    private int f3308d;

    /* renamed from: e */
    private String f3309e;

    public PrivateMessageItem(TTPodUser tTPodUser, String str, long j, String str2, int i) {
        this.f3305a = tTPodUser;
        this.f3306b = j;
        this.f3307c = str2;
        this.f3308d = i;
        this.f3309e = str;
    }

    /* renamed from: a */
    public String m7512a() {
        return this.f3307c;
    }

    /* renamed from: b */
    public long m7511b() {
        return this.f3306b;
    }

    /* renamed from: c */
    public int m7510c() {
        return this.f3308d;
    }

    /* renamed from: d */
    public String m7509d() {
        return this.f3309e;
    }

    /* renamed from: e */
    public TTPodUser m7508e() {
        return this.f3305a;
    }
}
