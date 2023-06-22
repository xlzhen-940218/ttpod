package com.sds.android.ttpod.widget;

import android.content.Context;

import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.LogUtils;

/* loaded from: classes.dex */
public class SimpleGridView extends ViewGroup {

    /* renamed from: a */
    private int f7922a;

    /* renamed from: b */
    private int f7923b;

    public SimpleGridView(Context context) {
        super(context);
        this.f7923b = 3;
        m1548a();
    }

    public SimpleGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7923b = 3;
        m1548a();
    }

    public SimpleGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7923b = 3;
        m1548a();
    }

    public void setChildMargin(int i) {
        this.f7922a = i;
    }

    /* renamed from: a */
    private void m1548a() {
        this.f7922a = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, getContext().getResources().getDisplayMetrics());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        LogUtils.m8386a("SimpleGridView", "onLayout %b %d %d %d %d"
                , Boolean.valueOf(z), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < childCount) {
            View childAt = getChildAt(i6);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i5 == this.f7923b) {
                i7 = this.f7922a + measuredHeight + i7;
                i8 = 0;
                i5 = 0;
            }
            childAt.layout(i8, i7, i8 + measuredWidth, measuredHeight + i7);
            i5++;
            i6++;
            i8 = this.f7922a + measuredWidth + i8;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && size <= 0) {
            throw new IllegalStateException("Width must have an exact value or width size greate than 0 : " + Integer.toHexString(mode) + " width=" + size);
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((size - ((this.f7923b - 1) * this.f7922a)) / this.f7923b, MeasureSpec.EXACTLY);
        int childCount = getChildCount();
        int i6 = 0;
        int i7 = 0;
        while (i6 < childCount) {
            View childAt = getChildAt(i6);
            int i8 = childAt.getLayoutParams().height;
            if (i8 == -1) {
                i4 = 1073741824;
                i3 = size2;
            } else if (i8 == -2) {
                i4 = mode2 == 0 ? 0 : Integer.MIN_VALUE;
                i3 = size2;
            } else {
                i3 = i8;
                i4 = 1073741824;
            }
            childAt.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(i3, i4));
            i6++;
            i7 = Math.max(childAt.getMeasuredHeight(), i7);
        }
        if (childCount > 0) {
            int i9 = ((childCount - 1) / this.f7923b) + 1;
            i5 = ((i9 * this.f7922a) + (i7 * i9)) - this.f7922a;
        }
        setMeasuredDimension(size, i5);
    }

    public void setNumColumns(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("列数不能小于1");
        }
        this.f7923b = i;
    }
}
