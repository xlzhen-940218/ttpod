package com.sds.android.ttpod.component.lockscreen.p101a.p103b;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.b.c */
/* loaded from: classes.dex */
public abstract class Property<T, V> {

    /* renamed from: a */
    private final String f4716a;

    /* renamed from: b */
    private final Class<V> f4717b;

    /* renamed from: a */
    public abstract V mo5963a(T t);

    public Property(Class<V> cls, String str) {
        this.f4716a = str;
        this.f4717b = cls;
    }

    /* renamed from: a */
    public void mo5962a(T t, V v) {
        throw new UnsupportedOperationException("Property " + m5964a() + " is read-only");
    }

    /* renamed from: a */
    public String m5964a() {
        return this.f4716a;
    }
}
