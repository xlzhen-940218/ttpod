package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class ModifyFitCenterImageView extends ImageView {

    /* renamed from: a */
    private boolean f6826a;

    public ModifyFitCenterImageView(Context context) {
        super(context);
    }

    public ModifyFitCenterImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ModifyFitCenterImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setScaleByHeight(boolean z) {
        this.f6826a = z;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        Drawable drawable;
        super.onMeasure(i, i2);
        if (this.f6826a) {
            Drawable drawable2 = getDrawable();
            if (drawable2 != null) {
                int intrinsicWidth = drawable2.getIntrinsicWidth();
                int intrinsicHeight = drawable2.getIntrinsicHeight();
                if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                    int measuredWidth = getMeasuredWidth();
                    int measuredHeight = getMeasuredHeight();
                    int paddingTop = ((intrinsicWidth * ((measuredHeight - getPaddingTop()) - getPaddingBottom())) / intrinsicHeight) + getPaddingLeft() + getPaddingRight();
                    if (measuredWidth != paddingTop) {
                        setMeasuredDimension(paddingTop, measuredHeight);
                    }
                }
            }
        } else if (getScaleType() == ImageView.ScaleType.FIT_CENTER && (drawable = getDrawable()) != null) {
            int intrinsicWidth2 = drawable.getIntrinsicWidth();
            int intrinsicHeight2 = drawable.getIntrinsicHeight();
            if (intrinsicWidth2 > 0 && intrinsicHeight2 > 0) {
                int measuredWidth2 = getMeasuredWidth();
                int measuredHeight2 = getMeasuredHeight();
                int ceil = ((int) Math.ceil((intrinsicHeight2 * ((measuredWidth2 - getPaddingLeft()) - getPaddingRight())) / intrinsicWidth2)) + getPaddingTop() + getPaddingBottom();
                if (View.MeasureSpec.getMode(i2) == 0 || measuredHeight2 <= ceil) {
                    setMeasuredDimension(measuredWidth2, ceil);
                } else {
                    setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
        }
    }
}
