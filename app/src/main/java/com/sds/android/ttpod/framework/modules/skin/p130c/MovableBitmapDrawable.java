package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.g */
/* loaded from: classes.dex */
public class MovableBitmapDrawable extends BitmapDrawable {

    /* renamed from: a */
    private int f6529a;

    /* renamed from: b */
    private int f6530b;

    /* renamed from: c */
    private final Rect f6531c;

    /* renamed from: d */
    private int height;

    /* renamed from: e */
    private int width;

    /* renamed from: f */
    private boolean f6534f;

    public MovableBitmapDrawable(Resources resources, Bitmap bitmap) {
        this(resources, bitmap, false);
    }

    public MovableBitmapDrawable(Resources resources, Bitmap bitmap, boolean z) {
        super(resources, bitmap);
        this.f6529a = 0;
        this.f6530b = 0;
        this.f6531c = new Rect();
        this.f6534f = false;
        this.height = super.getIntrinsicHeight();
        this.width = super.getIntrinsicWidth();
        this.f6534f = z;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        int intrinsicWidth = super.getIntrinsicWidth();
        int intrinsicHeight = super.getIntrinsicHeight();
        float max = Math.max(rect.width() / intrinsicWidth, rect.height() / intrinsicHeight);
        this.width = (int) (intrinsicWidth * max);
        this.height = (int) (max * intrinsicHeight);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.width;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.height;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            this.f6531c.set(0, 0, this.width, this.height);
            Rect bounds = getBounds();
            if (this.f6534f) {
                int width = bounds.width() - this.f6531c.width();
                this.f6531c.offset(width > 0 ? width / 2 : 0, this.f6530b);
            } else {
                this.f6531c.offset(this.f6529a, 0);
            }
            canvas.drawBitmap(bitmap, (Rect) null, this.f6531c, getPaint());
        }
    }

    /* renamed from: a */
    public void m3726a(int i) {
        this.f6529a = i;
    }

    /* renamed from: a */
    public int m3727a() {
        return this.f6529a;
    }
}
