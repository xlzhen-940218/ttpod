package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import android.view.animation.Interpolator;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.f */
/* loaded from: classes.dex */
public abstract class Keyframe implements Cloneable {

    /* renamed from: a */
    float f4634a;

    /* renamed from: b */
    Class f4635b;

    /* renamed from: d */
    private Interpolator f4637d = null;

    /* renamed from: c */
    boolean f4636c = false;

    /* renamed from: a */
    public abstract void mo6071a(Object obj);

    /* renamed from: b */
    public abstract Object mo6070b();

    @Override // 
    /* renamed from: e */
    public abstract Keyframe clone();

    /* renamed from: a */
    public static Keyframe m6078a(float f, int i) {
        return new C1285b(f, i);
    }

    /* renamed from: a */
    public static Keyframe m6080a(float f) {
        return new C1285b(f);
    }

    /* renamed from: a */
    public static Keyframe m6079a(float f, float f2) {
        return new C1284a(f, f2);
    }

    /* renamed from: b */
    public static Keyframe m6076b(float f) {
        return new C1284a(f);
    }

    /* renamed from: a */
    public boolean m6081a() {
        return this.f4636c;
    }

    /* renamed from: c */
    public float m6075c() {
        return this.f4634a;
    }

    /* renamed from: d */
    public Interpolator m6074d() {
        return this.f4637d;
    }

    /* renamed from: a */
    public void m6077a(Interpolator interpolator) {
        this.f4637d = interpolator;
    }

    /* compiled from: Keyframe.java */
    /* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.f$b */
    /* loaded from: classes.dex */
    static class C1285b extends Keyframe {

        /* renamed from: d */
        int f4639d;

        C1285b(float f, int i) {
            this.f4634a = f;
            this.f4639d = i;
            this.f4635b = Integer.TYPE;
            this.f4636c = true;
        }

        C1285b(float f) {
            this.f4634a = f;
            this.f4635b = Integer.TYPE;
        }

        /* renamed from: f */
        public int m6068f() {
            return this.f4639d;
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Keyframe
        /* renamed from: b */
        public Object mo6070b() {
            return Integer.valueOf(this.f4639d);
        }

        @Override
        public Keyframe clone() {
            C1285b c1285b = new C1285b(m6075c(), this.f4639d);
            c1285b.m6077a(m6074d());
            return c1285b;
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Keyframe
        /* renamed from: a */
        public void mo6071a(Object obj) {
            if (obj != null && obj.getClass() == Integer.class) {
                this.f4639d = ((Integer) obj).intValue();
                this.f4636c = true;
            }
        }


    }

    /* compiled from: Keyframe.java */
    /* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.f$a */
    /* loaded from: classes.dex */
    static class C1284a extends Keyframe {

        /* renamed from: d */
        float f4638d;

        C1284a(float f, float f2) {
            this.f4634a = f;
            this.f4638d = f2;
            this.f4635b = Float.TYPE;
            this.f4636c = true;
        }

        C1284a(float f) {
            this.f4634a = f;
            this.f4635b = Float.TYPE;
        }

        /* renamed from: f */
        public float m6073f() {
            return this.f4638d;
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Keyframe
        /* renamed from: b */
        public Object mo6070b() {
            return Float.valueOf(this.f4638d);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Keyframe
        /* renamed from: a */
        public void mo6071a(Object obj) {
            if (obj != null && obj.getClass() == Float.class) {
                this.f4638d = ((Float) obj).floatValue();
                this.f4636c = true;
            }
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Keyframe
        /* renamed from: g */
        public C1284a clone() {
            C1284a c1284a = new C1284a(m6075c(), this.f4638d);
            c1284a.m6077a(m6074d());
            return c1284a;
        }
    }
}
