package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sds.android.ttpod.R;

@Deprecated
/* loaded from: classes.dex */
public class RoundRectImageView extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    protected final RectF f7872a;

    /* renamed from: b */
    private final PorterDuffXfermode f7873b;

    /* renamed from: c */
    private int cornerLength;

    /* renamed from: d */
    private int frameColor;

    /* renamed from: e */
    private int frameWidth;

    public RoundRectImageView(Context context) {
        super(context);
        this.f7872a = new RectF();
        this.f7873b = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.cornerLength = -1;
        this.frameColor = -1;
        this.frameWidth = 0;
    }

    public RoundRectImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7872a = new RectF();
        this.f7873b = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.cornerLength = -1;
        this.frameColor = -1;
        this.frameWidth = 0;
        m1570a(context, attributeSet, 0);
    }

    public RoundRectImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7872a = new RectF();
        this.f7873b = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.cornerLength = -1;
        this.frameColor = -1;
        this.frameWidth = 0;
        m1570a(context, attributeSet, i);
    }

    public void setRoundFrameColor(int i) {
        this.frameColor = i;
        invalidate();
    }

    /* renamed from: a */
    private void m1570a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundRectImageView, i, 0);
        if (obtainStyledAttributes != null) {
            for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == 0) {
                    this.cornerLength = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundRectImageView_cornerLength, this.cornerLength);
                } else if (index == 1) {
                    this.frameWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundRectImageView_frameWidth, this.frameWidth);
                } else if (index == 2) {
                    this.frameColor = obtainStyledAttributes.getColor(R.styleable.RoundRectImageView_frameColor, this.frameColor);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Paint paint;
        Drawable drawable = getDrawable();
        if ((drawable instanceof BitmapDrawable) && (paint = ((BitmapDrawable) drawable).getPaint()) != null) {
            int color = paint.getColor();
            Xfermode xfermode = paint.getXfermode();
            boolean isAntiAlias = paint.isAntiAlias();
            this.f7872a.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            canvas.saveLayer(this.f7872a, null, Canvas.ALL_SAVE_FLAG);
            int i = this.frameWidth;
            this.f7872a.inset(i, i);
            int i2 = this.cornerLength - (i * 2);
            paint.setAntiAlias(true);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            float width = this.cornerLength == -1 ? this.f7872a.width() : i2;
            float height = this.cornerLength == -1 ? this.f7872a.height() : i2;
            canvas.drawRoundRect(this.f7872a, width, height, paint);
            paint.setXfermode(this.f7873b);
            super.onDraw(canvas);
            if (this.frameWidth > 0) {
                float strokeWidth = paint.getStrokeWidth();
                Paint.Style style = paint.getStyle();
                int i3 = i / 2;
                this.f7872a.inset(-i3, -i3);
                m1569a(canvas, this.f7872a, i + width, height + i, paint);
                paint.setStrokeWidth(strokeWidth);
                paint.setStyle(style);
            }
            paint.setXfermode(xfermode);
            paint.setColor(color);
            paint.setAntiAlias(isAntiAlias);
            canvas.restore();
            return;
        }
        super.onDraw(canvas);
    }

    /* renamed from: a */
    private void m1569a(Canvas canvas, RectF rectF, float f, float f2, Paint paint) {
        paint.setXfermode(null);
        paint.setColor(this.frameColor);
        paint.setStrokeWidth(this.frameWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(rectF, f, f2, paint);
    }
}
