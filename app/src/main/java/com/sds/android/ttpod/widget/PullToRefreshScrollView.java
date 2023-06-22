package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.widget.PullToRefreshHelper;

/* loaded from: classes.dex */
public class PullToRefreshScrollView extends ScrollView implements PullToRefreshHelper.InterfaceC2285b {

    /* renamed from: a */
    private PullToRefreshHelper f7859a;

    public PullToRefreshScrollView(Context context) {
        super(context);
        m1579a(context);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m1579a(context);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m1579a(context);
    }

    /* renamed from: a */
    private void m1579a(Context context) {
        this.f7859a = new PullToRefreshHelper(this, this);
        this.f7859a.m1256a(DisplayUtils.m7225c());
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setMinTrigerRefreshHeight(int i) {
        this.f7859a.m1256a(i);
    }

    /* renamed from: b */
    private boolean m1578b() {
        return getChildCount() > 0 && (getChildAt(0) instanceof ViewGroup) && ((ViewGroup) getChildAt(0)).getChildCount() > 0;
    }

    private View getHeaderView() {
        return ((ViewGroup) getChildAt(0)).getChildAt(0);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.f7859a.m1255a(motionEvent)) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.f7859a.m1249b(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2285b
    /* renamed from: a */
    public boolean mo1247a() {
        return getScrollY() == 0;
    }

    @Override // com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2285b
    public View getActionView() {
        if (m1578b()) {
            return ((ViewGroup) getChildAt(0)).getChildAt(0);
        }
        return null;
    }

    public void setOnPullRefreshListener(PullToRefreshHelper.InterfaceC2284a interfaceC2284a) {
        this.f7859a.m1254a(interfaceC2284a);
    }
}
