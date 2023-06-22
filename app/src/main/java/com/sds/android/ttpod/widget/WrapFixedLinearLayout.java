package com.sds.android.ttpod.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* loaded from: classes.dex */
public class WrapFixedLinearLayout extends LinearLayout {
    public WrapFixedLinearLayout(Context context) {
        super(context);
        m1443a(context, null, 0);
    }

    public WrapFixedLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m1443a(context, attributeSet, 0);
    }

    @TargetApi(11)
    public WrapFixedLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m1443a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m1443a(Context context, AttributeSet attributeSet, int i) {
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        if (getOrientation() == 0 && getChildCount() == 2 && size > 0) {
            View childAt = getChildAt(0);
            View childAt2 = getChildAt(1);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) childAt2.getLayoutParams();
            int measuredWidth = childAt2.getMeasuredWidth();
            if (childAt2 instanceof ViewGroup) {
                childAt2.measure(size, i2);
                measuredWidth = childAt2.getMeasuredWidth();
            }
            int measuredWidth2 = childAt.getMeasuredWidth();
            int paddingLeft = layoutParams.rightMargin + measuredWidth + getPaddingLeft() + getPaddingRight() + measuredWidth2 + layoutParams.leftMargin + layoutParams2.leftMargin + layoutParams2.rightMargin;
            if (paddingLeft > size) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2 - (paddingLeft - size), 1073741824), i2);
            }
        }
    }
}
