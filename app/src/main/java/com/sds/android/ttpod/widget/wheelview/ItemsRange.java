package com.sds.android.ttpod.widget.wheelview;

/* renamed from: com.sds.android.ttpod.widget.wheelview.a */
/* loaded from: classes.dex */
public class ItemsRange {

    /* renamed from: a */
    private int f8430a;

    /* renamed from: b */
    private int f8431b;

    public ItemsRange() {
        this(0, 0);
    }

    public ItemsRange(int i, int i2) {
        this.f8430a = i;
        this.f8431b = i2;
    }

    /* renamed from: a */
    public int m1172a() {
        return this.f8430a;
    }

    /* renamed from: b */
    public int m1170b() {
        return (m1172a() + m1169c()) - 1;
    }

    /* renamed from: c */
    public int m1169c() {
        return this.f8431b;
    }

    /* renamed from: a */
    public boolean m1171a(int i) {
        return i >= m1172a() && i <= m1170b();
    }
}
