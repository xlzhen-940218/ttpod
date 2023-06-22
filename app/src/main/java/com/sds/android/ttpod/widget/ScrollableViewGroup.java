package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.p106a.ViewUtils;

/* loaded from: classes.dex */
public class ScrollableViewGroup extends FrameLayout {

    /* renamed from: a */
    private int f7890a;

    /* renamed from: b */
    private int f7891b;

    /* renamed from: c */
    private int f7892c;

    /* renamed from: d */
    private int f7893d;

    /* renamed from: e */
    private int f7894e;

    /* renamed from: f */
    private boolean f7895f;

    /* renamed from: g */
    private Scroller f7896g;

    /* renamed from: h */
    private int f7897h;

    /* renamed from: i */
    private float f7898i;

    /* renamed from: j */
    private float f7899j;

    /* renamed from: k */
    private int f7900k;

    /* renamed from: l */
    private boolean f7901l;

    /* renamed from: m */
    private VelocityTracker f7902m;

    /* renamed from: n */
    private int f7903n;

    /* renamed from: o */
    private InterfaceC2225a f7904o;

    /* renamed from: p */
    private boolean f7905p;

    /* renamed from: q */
    private boolean f7906q;

    /* renamed from: r */
    private Handler f7907r;

    /* renamed from: com.sds.android.ttpod.widget.ScrollableViewGroup$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2225a {
        /* renamed from: a */
        void mo1554a(View view, int i);
    }

    public void setEnableAutoScroll(boolean z) {
        this.f7906q = z;
        this.f7907r.removeMessages(1);
        if (this.f7906q) {
            this.f7907r.sendEmptyMessageDelayed(1, 4000L);
        }
    }

    public ScrollableViewGroup(Context context) {
        super(context);
        this.f7890a = 0;
        this.f7891b = 0;
        this.f7894e = -1;
        this.f7895f = true;
        this.f7903n = 0;
        this.f7905p = false;
        this.f7906q = true;
        this.f7907r = new Handler() { // from class: com.sds.android.ttpod.widget.ScrollableViewGroup.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                LogUtils.m8388a("ScrollableViewGroup", "handleMessage: " + message.toString());
                if (!ScrollableViewGroup.this.f7905p && ScrollableViewGroup.this.getChildCount() > 1) {
                    ScrollableViewGroup.this.m1562a(ScrollableViewGroup.this.f7893d + 1);
                }
                if (ScrollableViewGroup.this.f7906q) {
                    ScrollableViewGroup.this.f7907r.removeMessages(1);
                    ScrollableViewGroup.this.f7907r.sendEmptyMessageDelayed(1, 4000L);
                }
            }
        };
        m1558c();
    }

    public ScrollableViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7890a = 0;
        this.f7891b = 0;
        this.f7894e = -1;
        this.f7895f = true;
        this.f7903n = 0;
        this.f7905p = false;
        this.f7906q = true;
        this.f7907r = new Handler() { // from class: com.sds.android.ttpod.widget.ScrollableViewGroup.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                LogUtils.m8388a("ScrollableViewGroup", "handleMessage: " + message.toString());
                if (!ScrollableViewGroup.this.f7905p && ScrollableViewGroup.this.getChildCount() > 1) {
                    ScrollableViewGroup.this.m1562a(ScrollableViewGroup.this.f7893d + 1);
                }
                if (ScrollableViewGroup.this.f7906q) {
                    ScrollableViewGroup.this.f7907r.removeMessages(1);
                    ScrollableViewGroup.this.f7907r.sendEmptyMessageDelayed(1, 4000L);
                }
            }
        };
        m1558c();
    }

    public ScrollableViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7890a = 0;
        this.f7891b = 0;
        this.f7894e = -1;
        this.f7895f = true;
        this.f7903n = 0;
        this.f7905p = false;
        this.f7906q = true;
        this.f7907r = new Handler() { // from class: com.sds.android.ttpod.widget.ScrollableViewGroup.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                LogUtils.m8388a("ScrollableViewGroup", "handleMessage: " + message.toString());
                if (!ScrollableViewGroup.this.f7905p && ScrollableViewGroup.this.getChildCount() > 1) {
                    ScrollableViewGroup.this.m1562a(ScrollableViewGroup.this.f7893d + 1);
                }
                if (ScrollableViewGroup.this.f7906q) {
                    ScrollableViewGroup.this.f7907r.removeMessages(1);
                    ScrollableViewGroup.this.f7907r.sendEmptyMessageDelayed(1, 4000L);
                }
            }
        };
        m1558c();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        LogUtils.m8386a("ScrollableViewGroup", "onLayout %b %d %d %d %d", Boolean.valueOf(z), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != View.GONE) {
                int measuredWidth = childAt.getMeasuredWidth();
                LogUtils.m8388a("ScrollableViewGroup", "onLayout childWidth=" + measuredWidth + " childHeighteight=" + childAt.getMeasuredHeight());
                childAt.layout(i5, 0, i5 + measuredWidth, childAt.getMeasuredHeight());
                i5 += measuredWidth;
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        LogUtils.m8388a("ScrollableViewGroup", String.format("onMeasure %08X %d %08X %d", Integer.valueOf(View.MeasureSpec.getMode(i)), Integer.valueOf(View.MeasureSpec.getSize(i)), Integer.valueOf(View.MeasureSpec.getMode(i2)), Integer.valueOf(View.MeasureSpec.getSize(i2))));
        super.onMeasure(i, i2);
    }

    /* renamed from: c */
    private void m1558c() {
        this.f7896g = new Scroller(getContext(), new DecelerateInterpolator());
        this.f7893d = this.f7892c;
        this.f7900k = ViewConfiguration.get(getContext()).getScaledTouchSlop() << 1;
    }

    public int getCurrentView() {
        return this.f7893d;
    }

    public void setCurrentView(int i) {
        this.f7893d = Math.max(0, Math.min(i, getChildCount() - 1));
        scrollTo(this.f7893d * getWidth(), 0);
        if (this.f7904o != null) {
            this.f7904o.mo1554a(this, this.f7893d);
        }
        invalidate();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f7896g.computeScrollOffset()) {
            int currX = this.f7896g.getCurrX();
            int currY = this.f7896g.getCurrY();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            if (currX != scrollX || currY != scrollY) {
                scrollTo(this.f7896g.getCurrX(), this.f7896g.getCurrY());
            } else {
                invalidate();
            }
        } else if (this.f7894e != -1) {
            this.f7893d = Math.max(0, Math.min(this.f7894e, getChildCount() - 1));
            this.f7894e = -1;
            m1560b();
            int scrollX2 = getScrollX();
            int scrollY2 = getScrollY();
            int width = this.f7893d * getWidth();
            if (scrollX2 != width) {
                scrollTo(width, scrollY2);
            }
            if (this.f7904o != null) {
                this.f7904o.mo1554a(this, this.f7893d);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (getChildCount() != 0) {
            if (this.f7897h != 1 && this.f7894e == -1) {
                View childAt = getChildAt(this.f7893d);
                if (childAt != null) {
                    drawChild(canvas, childAt, getDrawingTime());
                    return;
                }
                return;
            }
            long drawingTime = getDrawingTime();
            if (this.f7894e >= 0 && this.f7894e < getChildCount() && (Math.abs(this.f7893d - this.f7894e) == 1 || this.f7903n != 0)) {
                View childAt2 = getChildAt(this.f7893d);
                View childAt3 = getChildAt(this.f7894e);
                drawChild(canvas, childAt2, drawingTime);
                if (this.f7903n == 0) {
                    drawChild(canvas, childAt3, drawingTime);
                    return;
                } else if (this.f7903n != 0) {
                    canvas.save();
                    if (this.f7903n < 0) {
                        canvas.translate((-getWidth()) * getChildCount(), 0.0f);
                    } else {
                        canvas.translate(getWidth() * getChildCount(), 0.0f);
                    }
                    drawChild(canvas, childAt3, drawingTime);
                    canvas.restore();
                    return;
                } else {
                    return;
                }
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                drawChild(canvas, getChildAt(i), drawingTime);
            }
            if (this.f7903n != 0) {
                View childAt4 = getChildAt(this.f7903n < 0 ? childCount - 1 : 0);
                canvas.save();
                if (this.f7903n < 0) {
                    canvas.translate(-(getWidth() * childCount), 0.0f);
                } else {
                    canvas.translate(getWidth() * childCount, 0.0f);
                }
                drawChild(canvas, childAt4, drawingTime);
                canvas.restore();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        int indexOfChild = indexOfChild(view);
        if (indexOfChild == this.f7893d && this.f7896g.isFinished()) {
            return false;
        }
        m1562a(indexOfChild);
        return true;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        if (this.f7894e != -1) {
            i2 = this.f7894e;
        } else {
            i2 = this.f7893d;
        }
        if (i2 < getChildCount()) {
            getChildAt(i2).requestFocus(i, rect);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View view, int i) {
        if (i == 17) {
            if (getCurrentView() > 0) {
                m1562a(getCurrentView() - 1);
                return true;
            }
        } else if (i == 66 && getCurrentView() < getChildCount() - 1) {
            m1562a(getCurrentView() + 1);
            return true;
        }
        return super.dispatchUnhandledMove(view, i);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 2 || this.f7897h == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int abs = (int) Math.abs(x - this.f7898i);
            int abs2 = (int) Math.abs(y - this.f7899j);
            switch (action) {
                case 0:
                    this.f7898i = x;
                    this.f7899j = y;
                    this.f7901l = true;
                    this.f7897h = this.f7896g.isFinished() ? 0 : 1;
                    this.f7905p = true;
                    break;
                case 1:
                case 3:
                    this.f7897h = 0;
                    this.f7901l = false;
                    this.f7905p = false;
                    this.f7907r.removeMessages(1);
                    this.f7907r.sendEmptyMessageDelayed(1, 4000L);
                    break;
                case 2:
                    int i = this.f7900k;
                    boolean z = abs > i;
                    boolean z2 = abs2 > i;
                    if (z || z2) {
                        if (z) {
                            if (this.f7895f) {
                                this.f7897h = 1;
                                m1563a();
                            } else if (abs > (getWidth() >> 3)) {
                                this.f7897h = 1;
                            } else {
                                this.f7897h = 0;
                            }
                        }
                        if (this.f7901l) {
                            this.f7901l = false;
                            getChildAt(this.f7893d).cancelLongPress();
                            break;
                        }
                    }
                    break;
            }
            ViewUtils.m4638a(this, (abs <= this.f7900k && abs2 <= this.f7900k) || abs >= abs2);
            return this.f7897h != 0;
        }
        return true;
    }

    /* renamed from: a */
    void m1563a() {
    }

    /* renamed from: b */
    void m1560b() {
        this.f7903n = 0;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getChildCount() == 0) {
            return super.onTouchEvent(motionEvent);
        }
        if (this.f7902m == null) {
            this.f7902m = VelocityTracker.obtain();
        }
        this.f7902m.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        switch (action) {
            case 0:
                this.f7905p = true;
                this.f7896g.abortAnimation();
                this.f7898i = x;
                break;
            case 1:
            case 3:
                if (this.f7897h == 1) {
                    VelocityTracker velocityTracker = this.f7902m;
                    velocityTracker.computeCurrentVelocity(1000);
                    int xVelocity = (int) velocityTracker.getXVelocity();
                    if (xVelocity > 500) {
                        m1562a(this.f7893d - 1);
                    } else if (xVelocity < -500) {
                        m1562a(this.f7893d + 1);
                    } else {
                        m1556d();
                    }
                    if (this.f7902m != null) {
                        this.f7902m.recycle();
                        this.f7902m = null;
                    }
                }
                this.f7897h = 0;
                this.f7905p = false;
                this.f7907r.removeMessages(1);
                this.f7907r.sendEmptyMessageDelayed(1, 4000L);
                break;
            case 2:
                if (this.f7895f && this.f7897h == 1) {
                    int i = (int) (this.f7898i - x);
                    this.f7898i = x;
                    if (i < 0) {
                        if (getScrollX() <= 0) {
                            this.f7903n = -1;
                        }
                        scrollBy(i, 0);
                    } else if (i > 0) {
                        int right = (getChildAt(getChildCount() - 1).getRight() - getScrollX()) - getWidth();
                        if (right <= 0) {
                            this.f7903n = 1;
                            right += getWidth() << 1;
                        }
                        if (right > 0) {
                            scrollBy(Math.min(right, i), 0);
                        }
                    }
                }
                this.f7897h = 1;
                break;
        }
        if (action == 0 || action == 2) {
            ViewUtils.m4638a((View) this, true);
            return true;
        }
        return true;
    }

    /* renamed from: d */
    private void m1556d() {
        int width = getWidth();
        int scrollX = (width >> 1) + getScrollX();
        int childCount = getChildCount();
        if (scrollX < 0) {
            childCount = -1;
        } else if (scrollX <= width * childCount) {
            childCount = (getScrollX() + (width / 2)) / width;
        }
        m1562a(childCount);
    }

    /* renamed from: a */
    public void m1562a(int i) {
        if (!this.f7896g.isFinished()) {
            this.f7896g.abortAnimation();
        }
        boolean z = i != this.f7893d;
        int childCount = getChildCount() - 1;
        int width = (getWidth() * i) - getScrollX();
        if (z || width != 0) {
            m1563a();
            if (i < 0) {
                this.f7903n = -1;
                i = childCount;
            } else if (i > childCount) {
                this.f7903n = 1;
                i = 0;
            } else {
                this.f7903n = 0;
            }
            this.f7894e = i;
            View focusedChild = getFocusedChild();
            if (focusedChild != null && z && focusedChild == getChildAt(this.f7893d)) {
                focusedChild.clearFocus();
            }
            this.f7896g.startScroll(getScrollX(), 0, width, 0, 300);
            invalidate();
        }
    }

    public InterfaceC2225a getOnCurrentViewChangedListener() {
        return this.f7904o;
    }

    public void setOnCurrentViewChangedListener(InterfaceC2225a interfaceC2225a) {
        this.f7904o = interfaceC2225a;
    }

    public void setEnableRuntimeScrollMotion(boolean z) {
        this.f7895f = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f7907r.removeMessages(1);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}
