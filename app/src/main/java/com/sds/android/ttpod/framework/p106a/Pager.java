package com.sds.android.ttpod.framework.p106a;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.a.n */
/* loaded from: classes.dex */
public class Pager {

    /* renamed from: a */
    private int f5682a = 1;

    /* renamed from: b */
    private int f5683b = this.f5682a;

    /* renamed from: c */
    private int f5684c = 1;

    /* renamed from: d */
    private boolean f5685d = true;

    /* renamed from: a */
    public void m4668a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("startIndex must >= 0");
        }
        this.f5682a = i;
    }

    /* renamed from: b */
    public void m4665b(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("total must >= 1");
        }
        this.f5684c = i;
    }

    /* renamed from: c */
    public void m4663c(int i) {
        if (i < this.f5682a || i > m4658g()) {
            throw new IllegalArgumentException("currentIndex must be >= PageStart and <= pageEnd");
        }
        this.f5683b = i;
    }

    /* renamed from: a */
    public int m4669a() {
        return this.f5683b;
    }

    /* renamed from: b */
    public int m4666b() {
        return this.f5682a;
    }

    /* renamed from: c */
    public int m4664c() {
        return this.f5684c;
    }

    /* renamed from: d */
    public int m4662d() {
        this.f5685d = false;
        return this.f5683b + 1;
    }

    /* renamed from: e */
    public void m4660e() {
        this.f5683b++;
        this.f5685d = false;
    }

    /* renamed from: d */
    public boolean m4661d(int i) {
        return i > m4658g();
    }

    /* renamed from: f */
    public boolean m4659f() {
        return this.f5685d && this.f5683b == this.f5682a;
    }

    /* renamed from: g */
    public int m4658g() {
        return (this.f5682a + this.f5684c) - 1;
    }

    /* renamed from: h */
    public boolean m4657h() {
        return this.f5683b < this.f5684c;
    }

    /* renamed from: a */
    public static <Data> List<List<Data>> m4667a(List<Data> list, int i) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("invalid datas");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("pageSize must be > 0");
        }
        int size = list.size();
        int ceil = (int) Math.ceil(size / i);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < ceil - 1; i2++) {
            arrayList.add(list.subList(i2 * i, (i2 + 1) * i));
        }
        arrayList.add(list.subList((ceil - 1) * i, size));
        return arrayList;
    }
}
