package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.b */
/* loaded from: classes.dex */
public class ClipBitmapDrawable extends BitmapDrawable {

    /* renamed from: a */
    private final Rect f6525a;

    /* renamed from: b */
    private boolean f6526b;

    public ClipBitmapDrawable(Resources resources, Bitmap bitmap, int i, int i2, int i3, int i4) {
        super(resources, bitmap);
        this.f6525a = new Rect();
        this.f6526b = false;
        m3750a(i, i2, i3, i4);
    }

    public ClipBitmapDrawable(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
        this.f6525a = new Rect();
        this.f6526b = false;
    }

    /* renamed from: a */
    public void m3750a(int i, int i2, int i3, int i4) {
        this.f6525a.set(i, i2, i3, i4);
        this.f6526b = (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? false : true;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (!this.f6526b) {
            m3751a(rect.width(), rect.height());
            this.f6526b = false;
        }
    }

    /* renamed from: a */
    public void m3751a(float f, float f2) {
        int i;
        int intrinsicWidth = getIntrinsicWidth();
        int intrinsicHeight = getIntrinsicHeight();
        if (intrinsicWidth > 0 && intrinsicHeight > 0 && f2 > 0.0f && f > 0.0f) {
            float f3 = intrinsicWidth / f;
            float f4 = intrinsicHeight / f2;
            if (f4 > f3) {
                intrinsicHeight = (int) Math.ceil(f2 * f3);
                i = 0;
            } else if (f4 < f3) {
                int i2 = (int) (f * f4);
                i = (intrinsicWidth - i2) >> 1;
                intrinsicWidth = i + i2;
            } else {
                i = 0;
            }
            m3750a(i, 0, intrinsicWidth, intrinsicHeight);
        }
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f6525a.isEmpty()) {
            super.draw(canvas);
            return;
        }
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, this.f6525a, getBounds(), getPaint());
        }
    }
}
