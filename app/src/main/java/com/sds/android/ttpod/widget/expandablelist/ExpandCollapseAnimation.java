package com.sds.android.ttpod.widget.expandablelist;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/* renamed from: com.sds.android.ttpod.widget.expandablelist.a */
/* loaded from: classes.dex */
public class ExpandCollapseAnimation extends Animation {

    /* renamed from: a */
    private View f8334a;

    /* renamed from: b */
    private int f8335b;

    /* renamed from: c */
    private int f8336c;

    /* renamed from: d */
    private LinearLayout.LayoutParams f8337d;

    public ExpandCollapseAnimation(View view, int i) {
        this.f8334a = view;
        this.f8335b = this.f8334a.getMeasuredHeight();
        this.f8337d = (LinearLayout.LayoutParams) view.getLayoutParams();
        this.f8336c = i;
        if (this.f8336c == 0) {
            this.f8337d.bottomMargin = -this.f8335b;
        } else {
            this.f8337d.bottomMargin = 0;
        }
        view.setVisibility(View.VISIBLE);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        if (f < 1.0f) {
            if (this.f8336c == 0) {
                this.f8337d.bottomMargin = (-this.f8335b) + ((int) (this.f8335b * f));
            } else {
                this.f8337d.bottomMargin = -((int) (this.f8335b * f));
            }
            this.f8334a.requestLayout();
        } else if (this.f8336c == 0) {
            this.f8337d.bottomMargin = 0;
            this.f8334a.requestLayout();
        } else {
            this.f8337d.bottomMargin = -this.f8335b;
            this.f8334a.setVisibility(View.GONE);
            this.f8334a.requestLayout();
        }
    }
}
