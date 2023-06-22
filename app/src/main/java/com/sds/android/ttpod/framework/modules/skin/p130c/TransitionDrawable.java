package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.SystemClock;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.m */
/* loaded from: classes.dex */
public class TransitionDrawable extends LayerDrawable implements Drawable.Callback {

    /* renamed from: b */
    private static final Drawable f6546b = new ColorDrawable(0);

    /* renamed from: a */
    private int f6547a;

    /* renamed from: c */
    private boolean f6548c;

    /* renamed from: d */
    private long f6549d;

    /* renamed from: e */
    private int f6550e;

    /* renamed from: f */
    private int f6551f;

    /* renamed from: g */
    private int f6552g;

    /* renamed from: h */
    private int f6553h;

    /* renamed from: i */
    private int f6554i;

    /* renamed from: j */
    private boolean f6555j;

    /* renamed from: k */
    private Drawable f6556k;

    public TransitionDrawable() {
        super(new Drawable[]{f6546b, f6546b});
        this.f6547a = 2;
        this.f6554i = 0;
        for (int numberOfLayers = getNumberOfLayers() - 1; numberOfLayers >= 0; numberOfLayers--) {
            super.setId(numberOfLayers, numberOfLayers);
        }
    }

    @Override // android.graphics.drawable.LayerDrawable
    public void setId(int i, int i2) {
    }

    @Override // android.graphics.drawable.LayerDrawable
    public boolean setDrawableByLayerId(int i, Drawable drawable) {
        if (drawable == null) {
            drawable = f6546b;
        }
        boolean drawableByLayerId = super.setDrawableByLayerId(i, drawable);
        if (drawableByLayerId && drawable.getBounds().isEmpty()) {
            drawable.setBounds(getBounds());
        }
        return drawableByLayerId;
    }

    /* renamed from: a */
    private void m3711a(int i) {
        this.f6550e = 0;
        this.f6551f = 255;
        this.f6554i = 0;
        this.f6552g = i;
        this.f6553h = i;
        this.f6548c = false;
        this.f6547a = 0;
        invalidateSelf();
    }

    /* renamed from: a */
    public void m3710a(Drawable drawable) {
        if (!m3706c()) {
            setDrawableByLayerId(1, drawable);
            m3711a(1000);
        }
        if (drawable == null) {
            drawable = f6546b;
        }
        this.f6556k = drawable;
    }

    /* renamed from: b */
    public void m3707b(Drawable drawable) {
        m3708b();
        m3712a();
        setDrawableByLayerId(0, drawable);
        setDrawableByLayerId(1, drawable);
    }

    /* renamed from: a */
    public void m3712a() {
        this.f6556k = null;
    }

    /* renamed from: c */
    private boolean m3706c() {
        return this.f6556k != null;
    }

    /* renamed from: b */
    public void m3708b() {
        this.f6554i = 0;
        this.f6547a = 2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        Drawable drawable;
        switch (this.f6547a) {
            case 0:
                this.f6549d = SystemClock.uptimeMillis();
                this.f6547a = 1;
                z = false;
                break;
            case 1:
                if (this.f6549d < 0) {
                    z = true;
                    break;
                } else {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.f6549d)) / this.f6552g;
                    z = uptimeMillis >= 1.0f;
                    this.f6554i = (int) ((Math.min(uptimeMillis, 1.0f) * (this.f6551f - this.f6550e)) + this.f6550e);
                    break;
                }
            default:
                z = true;
                break;
        }
        int i = this.f6554i;
        boolean z2 = this.f6555j;
        Drawable drawable2 = getDrawable(0);
        Drawable drawable3 = getDrawable(1);
        if (z) {
            if (drawable2 != drawable3) {
                setDrawableByLayerId(0, drawable3);
                drawable = drawable3;
            } else {
                drawable = drawable2;
            }
            drawable.draw(canvas);
            if (m3706c()) {
                if (drawable != this.f6556k) {
                    Drawable drawable4 = this.f6556k;
                    this.f6556k = null;
                    m3710a(drawable4);
                    return;
                }
                this.f6556k = null;
                return;
            }
            return;
        }
        if (z2) {
            drawable2.setAlpha(255 - i);
        }
        drawable2.draw(canvas);
        if (z2) {
            drawable2.setAlpha(255);
        }
        if (i > 0) {
            drawable3.setAlpha(i);
            drawable3.draw(canvas);
            drawable3.setAlpha(255);
        }
        invalidateSelf();
    }

    /* renamed from: a */
    public void m3709a(boolean z) {
        this.f6555j = z;
    }
}
