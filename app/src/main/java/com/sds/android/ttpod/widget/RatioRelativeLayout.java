package com.sds.android.ttpod.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class RatioRelativeLayout extends RelativeLayout {

    /* renamed from: a */
    private int ratio_width;

    /* renamed from: b */
    private int ratio_height;

    public RatioRelativeLayout(Context context) {
        super(context);
        this.ratio_width = 1;
        this.ratio_height = 1;
        m1573a(context, null, 0);
    }

    public RatioRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ratio_width = 1;
        this.ratio_height = 1;
        m1573a(context, attributeSet, 0);
    }

    @TargetApi(11)
    public RatioRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ratio_width = 1;
        this.ratio_height = 1;
        m1573a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m1573a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioView, i, 0);
        if (obtainStyledAttributes != null) {
            for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == 0) {
                    this.ratio_width = obtainStyledAttributes.getInteger(R.styleable.RatioView_ratio_width, this.ratio_width);
                } else if (index == 1) {
                    this.ratio_height = obtainStyledAttributes.getInteger(R.styleable.RatioView_ratio_height, this.ratio_height);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec((this.ratio_height * size) / this.ratio_width, MeasureSpec.EXACTLY);
        }
        super.onMeasure(i, i2);
    }
}
