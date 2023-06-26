package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class ThemeThumbnailView extends AppCompatImageView {

    /* renamed from: a */
    private float heightFactor;

    /* renamed from: b */
    private int backgroundColor;

    public ThemeThumbnailView(Context context) {
        super(context);
        initView(context, null);
    }

    public ThemeThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context, attributeSet);
    }

    public ThemeThumbnailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context, attributeSet);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                int ceil = ((int) Math.ceil(((measuredWidth - getPaddingLeft()) - getPaddingRight()) * this.heightFactor)) + getPaddingTop() + getPaddingBottom();
                if (measuredHeight != ceil) {
                    setMeasuredDimension(measuredWidth, ceil);
                }
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect clipBounds = canvas.getClipBounds();
        clipBounds.bottom--;
        clipBounds.right--;
        Paint paint = new Paint();
        paint.setColor(this.backgroundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        canvas.drawRect(clipBounds, paint);
    }

    /* renamed from: a */
    private void initView(Context context, AttributeSet attributeSet) {
        this.backgroundColor = getResources().getColor(R.color.skin_background_bounder_color);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ThemeThumbnailView);
            this.heightFactor = obtainStyledAttributes.getFloat(R.styleable.ThemeThumbnailView_heightFactor, 14.0f) / obtainStyledAttributes.getFloat(0, 9.0f);
            obtainStyledAttributes.recycle();
        }
    }
}
