package com.sds.android.ttpod.component.p091g.p092a;

import androidx.core.internal.view.SupportMenu;

/* renamed from: com.sds.android.ttpod.component.g.a.b */
/* loaded from: classes.dex */
public class Directives {

    /* renamed from: a */
    private int f4154a = 0;

    /* renamed from: b */
    private int[] f4155b;

    public Directives(int[] iArr) {
        this.f4155b = iArr;
    }

    /* renamed from: a */
    public boolean m6584a() {
        return this.f4154a < this.f4155b.length && this.f4155b[this.f4154a] != -1;
    }

    /* renamed from: a */
    public void m6583a(C1217a c1217a) {
        c1217a.m6577a(this.f4154a);
        this.f4154a += c1217a.m6570f();
    }

    /* renamed from: b */
    public C1217a m6580b() {
        return new C1217a();
    }

    /* renamed from: a */
    public void m6582a(C1217a c1217a, int i) {
        c1217a.m6577a(i);
    }

    /* renamed from: c */
    public void m6579c() {
        this.f4154a = 0;
    }

    /* compiled from: Directives.java */
    /* renamed from: com.sds.android.ttpod.component.g.a.b$a */
    /* loaded from: classes.dex */
    public final class C1217a {

        /* renamed from: b */
        private int f4157b;

        /* renamed from: c */
        private int f4158c;

        /* renamed from: d */
        private int f4159d;

        /* renamed from: e */
        private int f4160e;

        private C1217a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m6577a(int i) {
            int i2;
            this.f4159d = i;
            int i3 = i + 1;
            this.f4157b = Directives.this.f4155b[i];
            if (this.f4157b != 0 && this.f4157b != -1 && i3 < Directives.this.f4155b.length) {
                i2 = i3 + 1;
                this.f4158c = Directives.this.f4155b[i3];
            } else {
                this.f4158c = 0;
                i2 = i3;
            }
            this.f4160e = i2;
        }

        /* renamed from: a */
        public boolean m6578a() {
            return this.f4160e < this.f4159d + m6570f() && this.f4160e < Directives.this.f4155b.length;
        }

        /* renamed from: b */
        public int m6574b() {
            int[] iArr = Directives.this.f4155b;
            int i = this.f4160e;
            this.f4160e = i + 1;
            return iArr[i];
        }

        /* renamed from: c */
        public boolean m6573c() {
            return (this.f4157b & SupportMenu.CATEGORY_MASK) != 0;
        }

        /* renamed from: d */
        public int m6572d() {
            return this.f4157b;
        }

        /* renamed from: e */
        public int m6571e() {
            return this.f4159d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public int m6570f() {
            if (this.f4157b == 0) {
                return 1;
            }
            return this.f4158c + 2;
        }
    }
}
