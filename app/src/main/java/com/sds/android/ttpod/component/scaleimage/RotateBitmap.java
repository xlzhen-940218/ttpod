package com.sds.android.ttpod.component.scaleimage;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* renamed from: com.sds.android.ttpod.component.scaleimage.c */
/* loaded from: classes.dex */
public class RotateBitmap {

    /* renamed from: a */
    private Bitmap f4849a;

    /* renamed from: b */
    private int f4850b = 0;

    public RotateBitmap(Bitmap bitmap) {
        this.f4849a = bitmap;
    }

    /* renamed from: a */
    public void m5836a(int i) {
        this.f4850b = i;
    }

    /* renamed from: a */
    public int m5837a() {
        return this.f4850b;
    }

    /* renamed from: b */
    public Bitmap m5834b() {
        return this.f4849a;
    }

    /* renamed from: a */
    public void m5835a(Bitmap bitmap) {
        this.f4849a = bitmap;
    }

    /* renamed from: c */
    public Matrix m5833c() {
        Matrix matrix = new Matrix();
        if (this.f4850b != 0) {
            matrix.preTranslate(-(this.f4849a.getWidth() / 2), -(this.f4849a.getHeight() / 2));
            matrix.postRotate(this.f4850b);
            matrix.postTranslate(m5830f() / 2, m5831e() / 2);
        }
        return matrix;
    }

    /* renamed from: d */
    public boolean m5832d() {
        return (this.f4850b / 90) % 2 != 0;
    }

    /* renamed from: e */
    public int m5831e() {
        return m5832d() ? this.f4849a.getWidth() : this.f4849a.getHeight();
    }

    /* renamed from: f */
    public int m5830f() {
        return m5832d() ? this.f4849a.getHeight() : this.f4849a.getWidth();
    }
}
