package com.sds.android.ttpod.component.scaleimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ImageViewTouch extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    protected Matrix f4795a;

    /* renamed from: b */
    protected Matrix f4796b;

    /* renamed from: c */
    protected final Matrix f4797c;

    /* renamed from: d */
    protected final RotateBitmap f4798d;

    /* renamed from: e */
    protected Handler f4799e;

    /* renamed from: f */
    private final float[] f4800f;

    /* renamed from: g */
    private int f4801g;

    /* renamed from: h */
    private int f4802h;

    /* renamed from: i */
    private float f4803i;

    /* renamed from: j */
    private float f4804j;

    /* renamed from: k */
    private float f4805k;

    /* renamed from: l */
    private Runnable f4806l;

    /* renamed from: a */
    public float m5886a() {
        return this.f4803i;
    }

    /* renamed from: b */
    public float m5873b() {
        return this.f4804j;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f4801g = i3 - i;
        this.f4802h = i4 - i2;
        Runnable runnable = this.f4806l;
        if (runnable != null) {
            this.f4806l = null;
            runnable.run();
        }
        if (this.f4798d.m5834b() != null) {
            m5877a(this.f4798d, this.f4795a);
            setImageMatrix(m5866e());
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        m5881a(bitmap, 0);
    }

    /* renamed from: a */
    private void m5881a(Bitmap bitmap, int i) {
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setDither(true);
        }
        this.f4798d.m5834b();
        this.f4798d.m5835a(bitmap);
        this.f4798d.m5836a(i);
    }

    /* renamed from: c */
    public void m5870c() {
        m5880a((Bitmap) null, true);
    }

    /* renamed from: a */
    public void m5880a(Bitmap bitmap, boolean z) {
        m5876a(new RotateBitmap(bitmap), z);
    }

    /* renamed from: a */
    public void m5876a(final RotateBitmap rotateBitmap, final boolean z) {
        if (getWidth() <= 0) {
            this.f4806l = new Runnable() { // from class: com.sds.android.ttpod.component.scaleimage.ImageViewTouch.1
                @Override // java.lang.Runnable
                public void run() {
                    ImageViewTouch.this.m5876a(rotateBitmap, z);
                }
            };
            return;
        }
        if (rotateBitmap.m5834b() != null) {
            m5877a(rotateBitmap, this.f4795a);
            m5881a(rotateBitmap.m5834b(), rotateBitmap.m5837a());
        } else {
            this.f4795a.reset();
            setImageBitmap(null);
        }
        if (z) {
            this.f4796b.reset();
        }
        setImageMatrix(m5866e());
        this.f4805k = m5879a(this.f4795a);
        this.f4803i = 2.0f;
        this.f4804j = 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m5875a(boolean z, boolean z2) {
        m5874a(z, z2, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m5874a(boolean z, boolean z2, boolean z3) {
        float f = 0;
        float f2 = 0.0f;
        if (this.f4798d.m5834b() != null) {
            Matrix m5866e = m5866e();
            RectF rectF = new RectF(0.0f, 0.0f, this.f4798d.m5834b().getWidth(), this.f4798d.m5834b().getHeight());
            m5866e.mapRect(rectF);
            float height = rectF.height();
            float width = rectF.width();
            if (z2) {
                int height2 = getHeight();
                if (height < height2) {
                    f = ((height2 - height) / 2.0f) - rectF.top;
                } else if (rectF.top > 0.0f) {
                    f = -rectF.top;
                } else if (rectF.bottom < height2) {
                    f = getHeight() - rectF.bottom;
                }
                if (z) {
                    int width2 = getWidth();
                    if (width < width2) {
                        f2 = ((width2 - width) / 2.0f) - rectF.left;
                    } else if (rectF.left > 0.0f) {
                        f2 = -rectF.left;
                    } else if (rectF.right < width2) {
                        f2 = width2 - rectF.right;
                    }
                }
                m5884a(f2, f);
                if (z3) {
                    setImageMatrix(m5866e());
                    return;
                }
                return;
            }
            f = 0.0f;
            if (z) {
            }
            m5884a(f2, f);
            if (z3) {
            }
        }
    }

    public ImageViewTouch(Context context) {
        super(context);
        this.f4795a = new Matrix();
        this.f4796b = new Matrix();
        this.f4797c = new Matrix();
        this.f4800f = new float[9];
        this.f4798d = new RotateBitmap(null);
        this.f4801g = -1;
        this.f4802h = -1;
        this.f4799e = new Handler();
        this.f4806l = null;
        m5865f();
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4795a = new Matrix();
        this.f4796b = new Matrix();
        this.f4797c = new Matrix();
        this.f4800f = new float[9];
        this.f4798d = new RotateBitmap(null);
        this.f4801g = -1;
        this.f4802h = -1;
        this.f4799e = new Handler();
        this.f4806l = null;
        m5865f();
    }

    /* renamed from: f */
    private void m5865f() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    /* renamed from: a */
    protected float m5878a(Matrix matrix, int i) {
        matrix.getValues(this.f4800f);
        return this.f4800f[i];
    }

    /* renamed from: a */
    protected float m5879a(Matrix matrix) {
        return m5878a(matrix, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public float m5868d() {
        return m5879a(this.f4796b);
    }

    /* renamed from: a */
    private void m5877a(RotateBitmap rotateBitmap, Matrix matrix) {
        float width = getWidth();
        float height = getHeight();
        float m5830f = rotateBitmap.m5830f();
        float m5831e = rotateBitmap.m5831e();
        matrix.reset();
        float min = Math.min(width / m5830f, height / m5831e);
        matrix.postConcat(rotateBitmap.m5833c());
        matrix.postScale(min, min);
        matrix.postTranslate((width - (m5830f * min)) / 2.0f, (height - (m5831e * min)) / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public Matrix m5866e() {
        this.f4797c.set(this.f4795a);
        this.f4797c.postConcat(this.f4796b);
        return this.f4797c;
    }

    /* renamed from: a */
    protected void m5883a(float f, float f2, float f3) {
        if (f > this.f4803i) {
            f = this.f4803i;
        }
        float m5868d = f / m5868d();
        this.f4796b.postScale(m5868d, m5868d, f2, f3);
        setImageMatrix(m5866e());
        m5875a(true, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m5885a(float f) {
        m5883a(f, getWidth() / 2.0f, getHeight() / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void m5871b(float f, float f2, float f3) {
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        m5872b(width - f2, height - f3);
        m5883a(f, width, height);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m5869c(float f, float f2, float f3) {
        float m5868d = f / m5868d();
        this.f4796b.postScale(m5868d, m5868d, f2, f3);
        setImageMatrix(m5866e());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public void m5867d(float f, float f2, float f3) {
        float m5868d = f / m5868d();
        this.f4796b.postScale(m5868d, m5868d, f2, f3);
        m5866e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m5882a(float f, float f2, float f3, float f4) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, f3, f4);
        scaleAnimation.setDuration(500L);
        startAnimation(scaleAnimation);
    }

    /* renamed from: a */
    protected void m5884a(float f, float f2) {
        this.f4796b.postTranslate(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void m5872b(float f, float f2) {
        m5884a(f, f2);
        setImageMatrix(m5866e());
    }
}
