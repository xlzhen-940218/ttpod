package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;

@RemoteViews.RemoteView
/* loaded from: classes.dex */
public class CenterLayout extends ViewGroup {

    /* renamed from: a */
    private int f7484a;

    /* renamed from: b */
    private int f7485b;

    /* renamed from: c */
    private int f7486c;

    /* renamed from: d */
    private int f7487d;

    /* renamed from: e */
    private int f7488e;

    /* renamed from: f */
    private int f7489f;

    public CenterLayout(Context context) {
        super(context);
        this.f7484a = 0;
        this.f7485b = 0;
        this.f7486c = 0;
        this.f7487d = 0;
    }

    public CenterLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7484a = 0;
        this.f7485b = 0;
        this.f7486c = 0;
        this.f7487d = 0;
    }

    public CenterLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7484a = 0;
        this.f7485b = 0;
        this.f7486c = 0;
        this.f7487d = 0;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int childCount = getChildCount();
        measureChildren(i, i2);
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != View.GONE) {
                C2163a c2163a = (C2163a) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight() + c2163a.m1901b();
                i3 = Math.max(i6, c2163a.m1902a() + childAt.getMeasuredWidth());
                i4 = Math.max(i7, measuredHeight);
            } else {
                i3 = i6;
                i4 = i7;
            }
            i5++;
            i7 = i4;
            i6 = i3;
        }
        setMeasuredDimension(resolveSize(Math.max(this.f7484a + this.f7485b + i6, getSuggestedMinimumWidth()), i), resolveSize(Math.max(this.f7486c + this.f7487d + i7, getSuggestedMinimumHeight()), i2));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth;
        int measuredHeight;
        int childCount = getChildCount();
        this.f7488e = getMeasuredWidth();
        this.f7489f = getMeasuredHeight();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != View.GONE) {
                C2163a c2163a = (C2163a) childAt.getLayoutParams();
                int m1902a = this.f7484a + c2163a.m1902a();
                if (c2163a.width > 0) {
                    measuredWidth = m1902a + ((int) ((this.f7488e - c2163a.width) / 2.0d));
                } else {
                    measuredWidth = m1902a + ((int) ((this.f7488e - childAt.getMeasuredWidth()) / 2.0d));
                }
                int m1901b = this.f7486c + c2163a.m1901b();
                if (c2163a.height > 0) {
                    measuredHeight = ((int) ((this.f7489f - c2163a.height) / 2.0d)) + m1901b;
                } else {
                    measuredHeight = ((int) ((this.f7489f - childAt.getMeasuredHeight()) / 2.0d)) + m1901b;
                }
                childAt.layout(measuredWidth, measuredHeight, childAt.getMeasuredWidth() + measuredWidth, childAt.getMeasuredHeight() + measuredHeight);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2163a;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C2163a(layoutParams);
    }

    /* renamed from: com.sds.android.ttpod.widget.CenterLayout$a */
    /* loaded from: classes.dex */
    public static class C2163a extends ViewGroup.LayoutParams {

        /* renamed from: a */
        private int f7490a;

        /* renamed from: b */
        private int f7491b;

        public C2163a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        /* renamed from: a */
        public int m1902a() {
            return this.f7490a;
        }

        /* renamed from: b */
        public int m1901b() {
            return this.f7491b;
        }
    }
}
