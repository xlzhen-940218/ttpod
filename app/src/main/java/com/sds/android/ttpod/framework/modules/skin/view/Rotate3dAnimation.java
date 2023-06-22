package com.sds.android.ttpod.framework.modules.skin.view;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Transformation;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.view.c */
/* loaded from: classes.dex */
public class Rotate3dAnimation extends android.view.animation.Animation {

    /* renamed from: a */
    private float f6903a;

    /* renamed from: b */
    private float f6904b;

    /* renamed from: c */
    private float f6905c;

    /* renamed from: d */
    private float f6906d;

    /* renamed from: e */
    private float f6907e;

    /* renamed from: f */
    private boolean f6908f;

    /* renamed from: g */
    private Camera f6909g;

    /* renamed from: h */
    private int f6910h;

    /* renamed from: i */
    private int f6911i;

    /* renamed from: j */
    private float f6912j;

    /* renamed from: k */
    private float f6913k;

    public Rotate3dAnimation(float f, float f2, float f3, float f4, float f5, boolean z) {
        this.f6910h = 0;
        this.f6911i = 0;
        this.f6912j = 0.0f;
        this.f6913k = 0.0f;
        this.f6903a = f;
        this.f6904b = f2;
        this.f6912j = f3;
        this.f6913k = f4;
        this.f6905c = this.f6912j;
        this.f6906d = this.f6913k;
        this.f6907e = f5;
        this.f6908f = z;
    }

    public Rotate3dAnimation(float f, float f2, float f3, boolean z) {
        this.f6910h = 0;
        this.f6911i = 0;
        this.f6912j = 0.0f;
        this.f6913k = 0.0f;
        this.f6903a = f;
        this.f6904b = f2;
        this.f6910h = 1;
        this.f6911i = 1;
        this.f6912j = 0.5f;
        this.f6913k = 0.5f;
        this.f6907e = f3;
        this.f6908f = z;
    }

    @Override // android.view.animation.Animation
    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.f6909g = new Camera();
        this.f6905c = resolveSize(this.f6910h, this.f6912j, i, i3);
        this.f6906d = resolveSize(this.f6911i, this.f6913k, i2, i4);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.f6903a;
        float f3 = f2 + ((this.f6904b - f2) * f);
        float f4 = this.f6905c;
        float f5 = this.f6906d;
        Camera camera = this.f6909g;
        Matrix matrix = transformation.getMatrix();
        camera.save();
        if (this.f6908f) {
            camera.translate(0.0f, 0.0f, this.f6907e * f);
        } else {
            camera.translate(0.0f, 0.0f, this.f6907e * (1.0f - f));
        }
        camera.rotateY(f3);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-f4, -f5);
        matrix.postTranslate(f4, f5);
    }
}
