package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import com.sds.android.ttpod.widget.PullToRefreshHelper;

/* loaded from: classes.dex */
public class PullToRefreshExpandableListView extends ExpandableListView implements PullToRefreshHelper.InterfaceC2285b {

    /* renamed from: a */
    private PullToRefreshHelper f7855a;

    /* renamed from: b */
    private View f7856b;

    public PullToRefreshExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m1582b();
    }

    /* renamed from: b */
    private void m1582b() {
        this.f7855a = new PullToRefreshHelper(this, this);
    }

    public void setMaxHeaderHeight(int i) {
        this.f7855a.m1256a(i);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.f7855a.m1255a(motionEvent)) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.f7855a.m1249b(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2285b
    /* renamed from: a */
    public boolean mo1247a() {
        View childAt;
        return getFirstVisiblePosition() == 0 && getChildCount() > 0 && (childAt = getChildAt(0)) != null && childAt.getTop() == getPaddingTop();
    }

    @Override // com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2285b
    public View getActionView() {
        return this.f7856b;
    }

    public void setOnPullRefreshListener(PullToRefreshHelper.InterfaceC2284a interfaceC2284a) {
        this.f7855a.m1254a(interfaceC2284a);
    }
}
