package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.sds.android.ttpod.R;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class RatioFrameLayout extends FrameLayout {

    /* renamed from: a */
    private int ratioWidth;

    /* renamed from: b */
    private int ratioHeight;

    /* renamed from: c */
    private float designWidth;

    /* renamed from: d */
    private boolean scaleChild;

    /* renamed from: e */
    private ArrayList<Rect> f7864e;

    public RatioFrameLayout(Context context) {
        super(context);
        this.ratioWidth = 1;
        this.ratioHeight = 1;
        this.designWidth = 1.0f;
        m1576a(context, null, 0);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ratioWidth = 1;
        this.ratioHeight = 1;
        this.designWidth = 1.0f;
        m1576a(context, attributeSet, 0);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ratioWidth = 1;
        this.ratioHeight = 1;
        this.designWidth = 1.0f;
        m1576a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m1576a(Context context, AttributeSet attributeSet, int i) {
        m1575b(context, attributeSet, i);
    }

    /* renamed from: b */
    private void m1575b(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioView, i, 0);
        if (obtainStyledAttributes != null) {
            for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == 1) {
                    this.ratioHeight = obtainStyledAttributes.getInteger(R.styleable.RatioView_ratio_height, this.ratioHeight);
                } else if (index == 0) {
                    this.ratioWidth = obtainStyledAttributes.getInteger(R.styleable.RatioView_ratio_width, this.ratioWidth);
                } else if (index == 2) {
                    this.designWidth = obtainStyledAttributes.getDimension(R.styleable.RatioView_design_width, this.designWidth);
                } else if (index == 3) {
                    this.scaleChild = obtainStyledAttributes.getBoolean(R.styleable.RatioView_scale_child, this.scaleChild);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        if (this.scaleChild && this.f7864e == null) {
            this.f7864e = new ArrayList<>(childCount);
            for (int i3 = 0; i3 < childCount; i3++) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getChildAt(i3).getLayoutParams();
                this.f7864e.add(new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin));
            }
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.scaleChild) {
            m1577a(size);
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec((size * this.ratioHeight) / this.ratioWidth, MeasureSpec.EXACTLY));
    }

    /* renamed from: a */
    private void m1577a(int i) {
        if (this.f7864e != null && !this.f7864e.isEmpty()) {
            float f = 1.0f + ((i - this.designWidth) / this.designWidth);
            if (f >= 0.0f) {
                for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                    View childAt = getChildAt(childCount);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                    Rect rect = this.f7864e.get(childCount);
                    marginLayoutParams.leftMargin = (int) (rect.left * f);
                    marginLayoutParams.topMargin = (int) (rect.top * f);
                    marginLayoutParams.rightMargin = (int) (rect.right * f);
                    marginLayoutParams.bottomMargin = (int) (rect.bottom * f);
                    if (childAt instanceof DynamicSize) {
                        ((DynamicSize) childAt).mo1365a(f);
                    }
                }
            }
        }
    }
}
