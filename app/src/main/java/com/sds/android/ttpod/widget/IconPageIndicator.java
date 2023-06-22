package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class IconPageIndicator extends View {

    /* renamed from: a */
    private Drawable f7713a;

    /* renamed from: b */
    private Drawable f7714b;

    /* renamed from: c */
    private int f7715c;

    /* renamed from: d */
    private int f7716d;

    public IconPageIndicator(Context context) {
        super(context);
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    public void m1724a(int i, int i2) {
        Resources resources = getContext().getResources();
        m1723a(resources.getDrawable(i), resources.getDrawable(i2));
    }

    /* renamed from: a */
    public void m1723a(Drawable drawable, Drawable drawable2) {
        this.f7713a = drawable;
        this.f7714b = drawable2;
        invalidate();
    }

    /* renamed from: a */
    public void m1725a(int i) {
        this.f7716d = i;
        if (this.f7716d >= this.f7715c) {
            this.f7716d = this.f7715c - 1;
        }
        invalidate();
    }

    /* renamed from: b */
    public void m1722b(int i) {
        if (i < 0) {
            i = 0;
        }
        if (this.f7715c != i) {
            this.f7715c = i;
            requestLayout();
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f7715c != 0 && this.f7714b != null && this.f7713a != null) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int intrinsicWidth = this.f7714b.getIntrinsicWidth();
            int i = intrinsicWidth >> 2;
            int width = ((getWidth() - (((getPaddingRight() + paddingLeft) + (this.f7715c * intrinsicWidth)) + ((this.f7715c - 1) * i))) >> 1) + paddingLeft;
            int i2 = 0;
            while (i2 < this.f7715c) {
                Drawable drawable = i2 == this.f7716d ? this.f7714b : this.f7713a;
                drawable.setBounds(width, paddingTop, width + intrinsicWidth, paddingTop + intrinsicWidth);
                drawable.draw(canvas);
                width += intrinsicWidth + i;
                i2++;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(m1721c(i), m1720d(i2));
    }

    /* renamed from: c */
    private int m1721c(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != 1073741824) {
            int intrinsicWidth = this.f7714b != null ? this.f7714b.getIntrinsicWidth() : 0;
            int paddingLeft = ((intrinsicWidth >> 2) * (this.f7715c - 1)) + getPaddingLeft() + getPaddingRight() + (this.f7715c * intrinsicWidth);
            if (mode == Integer.MIN_VALUE) {
                return Math.min(paddingLeft, size);
            }
            return paddingLeft;
        }
        return size;
    }

    /* renamed from: d */
    private int m1720d(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != 1073741824) {
            int intrinsicHeight = (this.f7714b != null ? this.f7714b.getIntrinsicHeight() : 0) + getPaddingBottom() + getPaddingTop();
            if (mode == Integer.MIN_VALUE) {
                return Math.min(intrinsicHeight, size);
            }
            return intrinsicHeight;
        }
        return size;
    }
}
