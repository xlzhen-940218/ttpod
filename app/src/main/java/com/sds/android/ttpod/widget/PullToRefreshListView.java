package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import com.sds.android.ttpod.widget.PullToRefreshHelper;

/* loaded from: classes.dex */
public class PullToRefreshListView extends ListView implements PullToRefreshHelper.InterfaceC2285b {

    /* renamed from: a */
    private PullToRefreshHelper f7857a;

    /* renamed from: b */
    private View f7858b;

    public PullToRefreshListView(Context context) {
        super(context);
        m1581a(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m1581a(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m1581a(context);
    }

    /* renamed from: a */
    private void m1581a(Context context) {
        this.f7857a = new PullToRefreshHelper(this, this);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f7857a.m1256a(i);
    }

    @Override // android.widget.ListView
    public void addHeaderView(View view, Object obj, boolean z) {
        super.addHeaderView(view, obj, z);
        if (view != null && this.f7858b == null) {
            m1580a(view);
        }
    }

    /* renamed from: a */
    public void m1580a(View view) {
        this.f7858b = view;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (isEnabled() && getChildCount() > 0 && this.f7857a.m1255a(motionEvent)) || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return (isEnabled() && getChildCount() > 0 && this.f7857a.m1249b(motionEvent)) || super.onTouchEvent(motionEvent);
    }

    @Override // com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2285b
    /* renamed from: a */
    public boolean mo1247a() {
        View childAt;
        return getFirstVisiblePosition() == 0 && getChildCount() > 0 && (childAt = getChildAt(0)) != null && childAt.getTop() == getPaddingTop();
    }

    @Override // com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2285b
    public View getActionView() {
        return this.f7858b;
    }

    public void setOnPullRefreshListener(PullToRefreshHelper.InterfaceC2284a interfaceC2284a) {
        this.f7857a.m1254a(interfaceC2284a);
    }

    public void setMaxHeaderHeight(int i) {
        this.f7857a.m1256a(i);
    }
}
