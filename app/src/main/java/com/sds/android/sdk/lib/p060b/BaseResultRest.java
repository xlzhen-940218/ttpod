package com.sds.android.sdk.lib.p060b;

import java.io.Serializable;

/* renamed from: com.sds.android.sdk.lib.b.b */
/* loaded from: classes.dex */
public class BaseResultRest<T> implements Serializable {

    /* renamed from: a */
    private String f2346a = null;

    /* renamed from: b */
    private int f2347b;

    /* renamed from: c */
    private String f2348c;

    /* renamed from: d */
    private int f2349d;

    /* renamed from: b */
    public String m8681b() {
        return this.f2346a;
    }

    /* renamed from: a */
    public void m8682a(String str) {
        this.f2346a = str;
    }

    public String toString() {
        return getClass().getSimpleName() + "{mCode=" + this.f2347b + ", mContent='" + this.f2348c + "'}";
    }

    public BaseResultRest() {
    }

    public BaseResultRest(int i, String str) {
        this.f2347b = i;
        this.f2348c = str;
    }

    /* renamed from: c */
    public int m8678c() {
        return this.f2347b;
    }

    /* renamed from: d */
    public boolean m8677d() {
        return mo8683a(this.f2347b);
    }

    /* renamed from: a */
    protected boolean mo8683a(int i) {
        return this.f2347b == 200;
    }

    /* renamed from: b */
    public void m8680b(int i) {
        this.f2347b = i;
    }

    /* renamed from: e */
    public String m8676e() {
        return this.f2348c == null ? "" : this.f2348c;
    }

    /* renamed from: b */
    public void m8679b(String str) {
        this.f2348c = str;
    }

    /* renamed from: f */
    public int m8675f() {
        return this.f2349d;
    }
}
