package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

/* loaded from: classes.dex */
public class WeightedLinearLayout extends LinearLayout {
    public WeightedLinearLayout(Context context) {
        super(context);
    }

    public WeightedLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int i7 = displayMetrics.widthPixels;
        int i8 = displayMetrics.heightPixels;
        boolean z2 = i7 < i8;
        int mode = View.MeasureSpec.getMode(i);
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        float f = z2 ? 0.9f : 0.75f;
        float f2 = z2 ? 0.75f : 0.9f;
        if (mode != Integer.MIN_VALUE) {
            z = false;
            i3 = makeMeasureSpec2;
            i4 = makeMeasureSpec;
        } else {
            if (f <= 0.0f || measuredWidth <= (i6 = (int) (i7 * f))) {
                z = false;
            } else {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
                z = true;
            }
            if (f2 <= 0.0f || measuredHeight <= (i5 = (int) (i8 * f2))) {
                i3 = makeMeasureSpec2;
                i4 = makeMeasureSpec;
            } else {
                i3 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
                z = true;
                i4 = makeMeasureSpec;
            }
        }
        if (z) {
            super.onMeasure(i4, i3);
        }
    }
}
