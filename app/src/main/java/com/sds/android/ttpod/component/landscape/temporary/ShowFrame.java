package com.sds.android.ttpod.component.landscape.temporary;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.landscape.temporary.a */
/* loaded from: classes.dex */
public final class ShowFrame {

    /* renamed from: d */
    private static ShowFrame f4617d;

    /* renamed from: a */
    private long f4618a;

    /* renamed from: b */
    private int f4619b = 0;

    /* renamed from: c */
    private ArrayList<InterfaceC1279a> f4620c = new ArrayList<>();

    /* compiled from: ShowFrame.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.temporary.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1279a {
        /* renamed from: a */
        void m6089a(float f);
    }

    /* renamed from: a */
    public static ShowFrame m6094a() {
        if (f4617d == null) {
            f4617d = new ShowFrame();
        }
        return f4617d;
    }

    private ShowFrame() {
    }

    /* renamed from: b */
    public void m6093b() {
        this.f4620c.clear();
    }

    /* renamed from: c */
    public void m6092c() {
        m6093b();
        f4617d = null;
    }

    /* renamed from: d */
    public void m6091d() {
        this.f4619b = 0;
    }

    /* renamed from: e */
    public void m6090e() {
        if (this.f4620c.size() > 0) {
            if (this.f4619b == 0) {
                this.f4618a = System.currentTimeMillis();
                this.f4619b = 1;
            } else if (this.f4619b == 50) {
                long currentTimeMillis = System.currentTimeMillis();
                int i = 50000 / ((int) (currentTimeMillis - this.f4618a));
                Iterator<InterfaceC1279a> it = this.f4620c.iterator();
                while (it.hasNext()) {
                    InterfaceC1279a next = it.next();
                    if (next != null) {
                        next.m6089a(i);
                    }
                }
                this.f4618a = currentTimeMillis;
                this.f4619b = 1;
            } else {
                this.f4619b++;
            }
        }
    }
}
