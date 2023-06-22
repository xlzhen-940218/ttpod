package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class RectangleImageView extends ImageView {

    /* renamed from: a */
    private float f7871a;

    public RectangleImageView(Context context) {
        super(context);
        this.f7871a = 1.54f;
    }

    public RectangleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7871a = 1.54f;
        m1571a(context, attributeSet);
    }

    public RectangleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7871a = 1.54f;
        m1571a(context, attributeSet);
    }

    /* renamed from: a */
    private void m1571a(Context context, AttributeSet attributeSet) {
        setAspectRatio(context.obtainStyledAttributes(attributeSet, R.styleable.RectangleImageView).getFloat(R.styleable.RectangleImageView_aspect_ratio, this.f7871a));
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        Drawable drawable;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 0) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT mode=" + mode + " width=" + size);
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (size / this.f7871a), 1073741824);
        super.onMeasure(i, makeMeasureSpec);
        if (getScaleType() == ImageView.ScaleType.FIT_CENTER && (drawable = getDrawable()) != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                int measuredWidth = getMeasuredWidth();
                getMeasuredHeight();
                View.MeasureSpec.getMode(makeMeasureSpec);
                setScaleType(ImageView.ScaleType.CENTER_CROP);
                setMeasuredDimension(measuredWidth, ((int) Math.ceil((intrinsicHeight * ((measuredWidth - getPaddingLeft()) - getPaddingRight())) / intrinsicWidth)) + getPaddingTop() + getPaddingBottom());
            }
        }
    }

    public void setAspectRatio(float f) {
        if (f > 0.0f) {
            this.f7871a = f;
        }
    }
}
