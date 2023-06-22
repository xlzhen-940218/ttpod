package com.sds.android.ttpod.component.lockscreen.p101a.p104c.p105a;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.c.a.a */
/* loaded from: classes.dex */
public final class AnimatorProxy extends Animation {

    /* renamed from: a */
    public static final boolean f4718a;

    /* renamed from: b */
    private static final WeakHashMap<View, AnimatorProxy> f4719b;

    /* renamed from: c */
    private final WeakReference<View> f4720c;

    /* renamed from: e */
    private boolean f4722e;

    /* renamed from: g */
    private float f4724g;

    /* renamed from: h */
    private float f4725h;

    /* renamed from: i */
    private float f4726i;

    /* renamed from: j */
    private float f4727j;

    /* renamed from: k */
    private float f4728k;

    /* renamed from: n */
    private float f4731n;

    /* renamed from: o */
    private float f4732o;

    /* renamed from: d */
    private final Camera f4721d = new Camera();

    /* renamed from: f */
    private float f4723f = 1.0f;

    /* renamed from: l */
    private float f4729l = 1.0f;

    /* renamed from: m */
    private float f4730m = 1.0f;

    /* renamed from: p */
    private final RectF f4733p = new RectF();

    /* renamed from: q */
    private final RectF f4734q = new RectF();

    /* renamed from: r */
    private final Matrix f4735r = new Matrix();

    static {
        f4718a = Integer.valueOf(Build.VERSION.SDK).intValue() < 11;
        f4719b = new WeakHashMap<>();
    }

    /* renamed from: a */
    public static AnimatorProxy m5956a(View view) {
        AnimatorProxy animatorProxy = f4719b.get(view);
        if (animatorProxy == null || animatorProxy != view.getAnimation()) {
            AnimatorProxy animatorProxy2 = new AnimatorProxy(view);
            f4719b.put(view, animatorProxy2);
            return animatorProxy2;
        }
        return animatorProxy;
    }

    private AnimatorProxy(View view) {
        setDuration(0L);
        setFillAfter(true);
        view.setAnimation(this);
        this.f4720c = new WeakReference<>(view);
    }

    /* renamed from: a */
    public float m5961a() {
        return this.f4723f;
    }

    /* renamed from: a */
    public void m5960a(float f) {
        if (this.f4723f != f) {
            this.f4723f = f;
            View view = this.f4720c.get();
            if (view != null) {
                view.invalidate();
            }
        }
    }

    /* renamed from: b */
    public float m5955b() {
        return this.f4724g;
    }

    /* renamed from: b */
    public void m5954b(float f) {
        if (!this.f4722e || this.f4724g != f) {
            m5930o();
            this.f4722e = true;
            this.f4724g = f;
            m5929p();
        }
    }

    /* renamed from: c */
    public float m5952c() {
        return this.f4725h;
    }

    /* renamed from: c */
    public void m5951c(float f) {
        if (!this.f4722e || this.f4725h != f) {
            m5930o();
            this.f4722e = true;
            this.f4725h = f;
            m5929p();
        }
    }

    /* renamed from: d */
    public float m5950d() {
        return this.f4728k;
    }

    /* renamed from: d */
    public void m5949d(float f) {
        if (this.f4728k != f) {
            m5930o();
            this.f4728k = f;
            m5929p();
        }
    }

    /* renamed from: e */
    public float m5948e() {
        return this.f4726i;
    }

    /* renamed from: e */
    public void m5947e(float f) {
        if (this.f4726i != f) {
            m5930o();
            this.f4726i = f;
            m5929p();
        }
    }

    /* renamed from: f */
    public float m5946f() {
        return this.f4727j;
    }

    /* renamed from: f */
    public void m5945f(float f) {
        if (this.f4727j != f) {
            m5930o();
            this.f4727j = f;
            m5929p();
        }
    }

    /* renamed from: g */
    public float m5944g() {
        return this.f4729l;
    }

    /* renamed from: g */
    public void m5943g(float f) {
        if (this.f4729l != f) {
            m5930o();
            this.f4729l = f;
            m5929p();
        }
    }

    /* renamed from: h */
    public float m5942h() {
        return this.f4730m;
    }

    /* renamed from: h */
    public void m5941h(float f) {
        if (this.f4730m != f) {
            m5930o();
            this.f4730m = f;
            m5929p();
        }
    }

    /* renamed from: i */
    public int m5940i() {
        View view = this.f4720c.get();
        if (view == null) {
            return 0;
        }
        return view.getScrollX();
    }

    /* renamed from: a */
    public void m5959a(int i) {
        View view = this.f4720c.get();
        if (view != null) {
            view.scrollTo(i, view.getScrollY());
        }
    }

    /* renamed from: j */
    public int m5938j() {
        View view = this.f4720c.get();
        if (view == null) {
            return 0;
        }
        return view.getScrollY();
    }

    /* renamed from: b */
    public void m5953b(int i) {
        View view = this.f4720c.get();
        if (view != null) {
            view.scrollTo(view.getScrollX(), i);
        }
    }

    /* renamed from: k */
    public float m5936k() {
        return this.f4731n;
    }

    /* renamed from: i */
    public void m5939i(float f) {
        if (this.f4731n != f) {
            m5930o();
            this.f4731n = f;
            m5929p();
        }
    }

    /* renamed from: l */
    public float m5934l() {
        return this.f4732o;
    }

    /* renamed from: j */
    public void m5937j(float f) {
        if (this.f4732o != f) {
            m5930o();
            this.f4732o = f;
            m5929p();
        }
    }

    /* renamed from: m */
    public float m5932m() {
        View view = this.f4720c.get();
        if (view == null) {
            return 0.0f;
        }
        return view.getLeft() + this.f4731n;
    }

    /* renamed from: k */
    public void m5935k(float f) {
        View view = this.f4720c.get();
        if (view != null) {
            m5939i(f - view.getLeft());
        }
    }

    /* renamed from: n */
    public float m5931n() {
        View view = this.f4720c.get();
        if (view == null) {
            return 0.0f;
        }
        return view.getTop() + this.f4732o;
    }

    /* renamed from: l */
    public void m5933l(float f) {
        View view = this.f4720c.get();
        if (view != null) {
            m5937j(f - view.getTop());
        }
    }

    /* renamed from: o */
    private void m5930o() {
        View view = this.f4720c.get();
        if (view != null) {
            m5957a(this.f4733p, view);
        }
    }

    /* renamed from: p */
    private void m5929p() {
        View view = this.f4720c.get();
        if (view != null && view.getParent() != null) {
            RectF rectF = this.f4734q;
            m5957a(rectF, view);
            rectF.union(this.f4733p);
            ((View) view.getParent()).invalidate((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
    }

    /* renamed from: a */
    private void m5957a(RectF rectF, View view) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        Matrix matrix = this.f4735r;
        matrix.reset();
        m5958a(matrix, view);
        this.f4735r.mapRect(rectF);
        rectF.offset(view.getLeft(), view.getTop());
        if (rectF.right < rectF.left) {
            float f = rectF.right;
            rectF.right = rectF.left;
            rectF.left = f;
        }
        if (rectF.bottom < rectF.top) {
            float f2 = rectF.top;
            rectF.top = rectF.bottom;
            rectF.bottom = f2;
        }
    }

    /* renamed from: a */
    private void m5958a(Matrix matrix, View view) {
        float width = view.getWidth();
        float height = view.getHeight();
        boolean z = this.f4722e;
        float f = z ? this.f4724g : width / 2.0f;
        float f2 = z ? this.f4725h : height / 2.0f;
        float f3 = this.f4726i;
        float f4 = this.f4727j;
        float f5 = this.f4728k;
        if (f3 != 0.0f || f4 != 0.0f || f5 != 0.0f) {
            Camera camera = this.f4721d;
            camera.save();
            camera.rotateX(f3);
            camera.rotateY(f4);
            camera.rotateZ(-f5);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f, -f2);
            matrix.postTranslate(f, f2);
        }
        float f6 = this.f4729l;
        float f7 = this.f4730m;
        if (f6 != 1.0f || f7 != 1.0f) {
            matrix.postScale(f6, f7);
            matrix.postTranslate((-(f / width)) * ((f6 * width) - width), (-(f2 / height)) * ((f7 * height) - height));
        }
        matrix.postTranslate(this.f4731n, this.f4732o);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        View view = this.f4720c.get();
        if (view != null) {
            transformation.setAlpha(this.f4723f);
            m5958a(transformation.getMatrix(), view);
        }
    }
}
