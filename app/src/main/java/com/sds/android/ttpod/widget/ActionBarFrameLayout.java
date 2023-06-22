package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class ActionBarFrameLayout extends FrameLayout {

    /* renamed from: a */
    private Rect f7473a;

    /* renamed from: b */
    private boolean f7474b;

    public ActionBarFrameLayout(Context context) {
        super(context);
        this.f7473a = null;
        this.f7474b = false;
    }

    public ActionBarFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7473a = null;
        this.f7474b = false;
    }

    public void setTouchArea(Rect rect) {
        this.f7473a = rect;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f7473a == null) {
            return false;
        }
        if (this.f7473a.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            this.f7474b = false;
            return super.onInterceptTouchEvent(motionEvent);
        }
        this.f7474b = true;
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.f7474b && super.onTouchEvent(motionEvent);
    }
}
