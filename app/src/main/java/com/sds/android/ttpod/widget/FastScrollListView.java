package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class FastScrollListView extends ListView {

    /* renamed from: a */
    private Object f7648a;

    public FastScrollListView(Context context) {
        super(context);
    }

    public FastScrollListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FastScrollListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f7648a == null) {
            m1762a();
        }
        boolean z = false;
        if (this.f7648a != null && m1760b()) {
            z = true;
            m1761a(null);
        }
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (z) {
            m1761a(this.f7648a);
        }
        return onInterceptTouchEvent;
    }

    /* renamed from: a */
    private void m1762a() {
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mFastScroller");
            declaredField.setAccessible(true);
            this.f7648a = declaredField.get(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private boolean m1760b() {
        try {
            Field declaredField = this.f7648a.getClass().getDeclaredField("mState");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this.f7648a);
            if (obj instanceof Number) {
                return ((Number) obj).intValue() == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: a */
    private void m1761a(Object obj) {
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mFastScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
