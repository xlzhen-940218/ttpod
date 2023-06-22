package com.sds.android.ttpod.activities.musiccircle.radar;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.radar.b */
/* loaded from: classes.dex */
public class RadarAnimation extends Animation {

    /* renamed from: a */
    private float f2862a = 359.0f;

    /* renamed from: b */
    private int f2863b;

    /* renamed from: c */
    private int f2864c;

    /* renamed from: d */
    private float f2865d;

    /* renamed from: e */
    private float f2866e;

    /* renamed from: f */
    private float f2867f;

    /* renamed from: g */
    private float f2868g;

    /* renamed from: h */
    private InterfaceC0831a f2869h;

    /* compiled from: RadarAnimation.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.radar.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0831a {
        /* renamed from: a */
        void mo7851a(float f);
    }

    public RadarAnimation() {
        this.f2863b = 0;
        this.f2864c = 0;
        this.f2865d = 0.0f;
        this.f2866e = 0.0f;
        this.f2863b = 1;
        this.f2864c = 1;
        this.f2865d = 0.5f;
        this.f2866e = 0.45f;
        setDuration(3000L);
    }

    /* renamed from: a */
    public void m7903a(InterfaceC0831a interfaceC0831a) {
        this.f2869h = interfaceC0831a;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.f2862a * f;
        this.f2869h.mo7851a(f2);
        transformation.getMatrix().setRotate(f2, this.f2867f, this.f2868g);
    }

    @Override // android.view.animation.Animation
    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.f2867f = resolveSize(this.f2863b, this.f2865d, i, i3);
        this.f2868g = resolveSize(this.f2864c, this.f2866e, i2, i4);
    }
}
