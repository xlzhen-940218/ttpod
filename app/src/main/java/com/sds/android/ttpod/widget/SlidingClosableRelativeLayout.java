package com.sds.android.ttpod.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SlidingClosableRelativeLayout extends RelativeLayout {

    /* renamed from: c */
    private static final Interpolator f7946c = new Interpolator() { // from class: com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };

    /* renamed from: A */
    private int f7947A;

    /* renamed from: B */
    private int f7948B;

    /* renamed from: C */
    private boolean f7949C;

    /* renamed from: D */
    private InterfaceC2238b f7950D;

    /* renamed from: E */
    private InterfaceC2237a f7951E;

    /* renamed from: F */
    private InterfaceC2239c f7952F;

    /* renamed from: G */
    private Bitmap f7953G;

    /* renamed from: H */
    private Rect f7954H;

    /* renamed from: I */
    private boolean f7955I;

    /* renamed from: J */
    private List<Rect> f7956J;

    /* renamed from: a */
    protected int f7957a;

    /* renamed from: b */
    private boolean f7958b;

    /* renamed from: d */
    private int f7959d;

    /* renamed from: e */
    private boolean f7960e;

    /* renamed from: f */
    private boolean f7961f;

    /* renamed from: g */
    private int f7962g;

    /* renamed from: h */
    private float f7963h;

    /* renamed from: i */
    private float f7964i;

    /* renamed from: j */
    private Scroller f7965j;

    /* renamed from: k */
    private boolean f7966k;

    /* renamed from: l */
    private boolean f7967l;

    /* renamed from: m */
    private int f7968m;

    /* renamed from: n */
    private float f7969n;

    /* renamed from: o */
    private float f7970o;

    /* renamed from: p */
    private VelocityTracker f7971p;

    /* renamed from: q */
    private int f7972q;

    /* renamed from: r */
    private int f7973r;

    /* renamed from: s */
    private int f7974s;

    /* renamed from: t */
    private int f7975t;

    /* renamed from: u */
    private Drawable f7976u;

    /* renamed from: v */
    private Drawable f7977v;

    /* renamed from: w */
    private Drawable f7978w;

    /* renamed from: x */
    private Drawable f7979x;

    /* renamed from: y */
    private int f7980y;

    /* renamed from: z */
    private int f7981z;

    /* renamed from: com.sds.android.ttpod.widget.SlidingClosableRelativeLayout$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2237a {
        /* renamed from: a */
        void mo1483a();
    }

    /* renamed from: com.sds.android.ttpod.widget.SlidingClosableRelativeLayout$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2238b {
        /* renamed from: a */
        void m1482a();
    }

    /* renamed from: com.sds.android.ttpod.widget.SlidingClosableRelativeLayout$c */
    /* loaded from: classes.dex */
    public interface InterfaceC2239c {
        /* renamed from: a */
        void mo1481a();

        /* renamed from: b */
        void mo1480b();
    }

    public SlidingClosableRelativeLayout(Context context) {
        super(context);
        this.f7958b = false;
        this.f7959d = 0;
        this.f7967l = true;
        this.f7968m = 3;
        this.f7957a = -1;
        this.f7947A = 0;
        this.f7948B = 0;
        this.f7949C = true;
        this.f7954H = new Rect();
        this.f7955I = true;
        this.f7956J = new ArrayList();
        m1529a(context, (AttributeSet) null);
    }

    public SlidingClosableRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7958b = false;
        this.f7959d = 0;
        this.f7967l = true;
        this.f7968m = 3;
        this.f7957a = -1;
        this.f7947A = 0;
        this.f7948B = 0;
        this.f7949C = true;
        this.f7954H = new Rect();
        this.f7955I = true;
        this.f7956J = new ArrayList();
        m1529a(context, attributeSet);
    }

    public SlidingClosableRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7958b = false;
        this.f7959d = 0;
        this.f7967l = true;
        this.f7968m = 3;
        this.f7957a = -1;
        this.f7947A = 0;
        this.f7948B = 0;
        this.f7949C = true;
        this.f7954H = new Rect();
        this.f7955I = true;
        this.f7956J = new ArrayList();
        m1529a(context, attributeSet);
    }

    /* renamed from: a */
    @SuppressLint("ResourceType")
    void m1529a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingClosableRelativeLayout);
            setInitSlidingOpenState(obtainStyledAttributes.getBoolean(R.styleable.SlidingClosableRelativeLayout_open_state, true));
            setSlidingCloseMode(obtainStyledAttributes.getInt(R.styleable.SlidingClosableRelativeLayout_close_mode, 3));
            obtainStyledAttributes.recycle();
        }
        setWillNotDraw(true);
        setDescendantFocusability(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
        setFocusable(true);
        this.f7965j = new Scroller(context, f7946c);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f7962g = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.f7972q = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f7973r = viewConfiguration.getScaledMaximumFlingVelocity();
        float f = context.getResources().getDisplayMetrics().density;
        this.f7974s = (int) (80.0f * f);
        this.f7975t = (int) (2.0f * f);
        this.f7980y = (int) (5.0f * f);
        this.f7981z = (int) (f * 5.0f);
        if (ViewCompat.getImportantForAccessibility(this) == ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
            ViewCompat.setImportantForAccessibility(this, ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);
        }
    }

    /* renamed from: a */
    public void m1530a(int i, int i2, int i3, int i4) {
        this.f7954H.set(i, i2, i3, i4);
    }

    public void setEnableScrollingMask(boolean z) {
        this.f7967l = z;
    }

    public void setOnSlidingOpenListener(InterfaceC2238b interfaceC2238b) {
        this.f7950D = interfaceC2238b;
    }

    public void setOnSlidingCloseListener(InterfaceC2237a interfaceC2237a) {
        this.f7951E = interfaceC2237a;
    }

    public void setEnableMarginOpen(boolean z) {
        this.f7955I = z;
    }

    public void setOnSlidingScrollListener(InterfaceC2239c interfaceC2239c) {
        this.f7952F = interfaceC2239c;
    }

    /* renamed from: a */
    private void m1525a(MotionEvent motionEvent, int i) {
        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        this.f7969n = MotionEventCompat.getX(motionEvent, findPointerIndex);
        this.f7970o = MotionEventCompat.getY(motionEvent, findPointerIndex);
    }

    /* renamed from: i */
    private void m1493i() {
        this.f7963h = this.f7969n;
        this.f7964i = this.f7970o;
    }

    private void setScrollState(int i) {
        if (this.f7959d != i) {
            this.f7959d = i;
        }
    }

    private void setMotionState(int i) {
        if (this.f7947A != i) {
            this.f7947A = i;
        }
    }

    /* renamed from: a */
    public boolean m1534a() {
        return this.f7947A == 1;
    }

    public void setSlidingCloseMode(int i) {
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        this.f7968m = i;
        if (this.f7980y <= 0) {
            this.f7976u = null;
            this.f7978w = null;
            this.f7977v = null;
            this.f7979x = null;
            return;
        }
        if (!m1519b()) {
            i2 = 0;
            i3 = 0;
        } else if (this.f7976u == null || this.f7978w == null) {
            i3 = R.drawable.xml_shadow_left;
            i2 = R.drawable.xml_shadow_right;
        } else {
            return;
        }
        if (!m1511c()) {
            i4 = 0;
        } else if (this.f7977v == null || this.f7979x == null) {
            i4 = R.drawable.xml_shadow_top;
            i5 = R.drawable.xml_shadow_bottom;
        } else {
            return;
        }
        m1504d(i3, i4, i2, i5);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 4 || i == 8) {
            m1527a((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            setSlidingCloseMode(this.f7968m);
        }
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && i3 - i > 0 && i4 - i2 > 0) {
            if (this.f7948B == 2) {
                if (!m1507c(false)) {
                    m1487o();
                }
            } else if (this.f7948B == 1 && !m1499e(false)) {
                m1488n();
            }
            this.f7948B = -1;
        }
    }

    /* renamed from: a */
    public void m1528a(Rect rect) {
        this.f7956J.add(rect);
    }

    /* renamed from: b */
    public boolean m1516b(Rect rect) {
        for (Rect rect2 : this.f7956J) {
            if (rect2 == rect) {
                this.f7956J.remove(rect2);
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private boolean m1526a(MotionEvent motionEvent) {
        for (Rect rect : this.f7956J) {
            if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.f7960e = false;
            this.f7961f = false;
            this.f7957a = -1;
            m1489m();
            return false;
        }
        if (action != 0) {
            if (this.f7960e) {
                return true;
            }
            if (this.f7961f) {
                return false;
            }
        }
        switch (action) {
            case 0:
                if (!m1503d(motionEvent)) {
                    return super.onInterceptTouchEvent(motionEvent);
                }
                if (m1526a(motionEvent)) {
                    return super.onInterceptTouchEvent(motionEvent);
                }
                this.f7957a = MotionEventCompat.getPointerId(motionEvent, 0);
                m1525a(motionEvent, this.f7957a);
                m1493i();
                this.f7961f = false;
                this.f7965j.computeScrollOffset();
                if (this.f7959d == 2 && m1509c(this.f7965j.getCurrX(), this.f7965j.getCurrY(), this.f7965j.getFinalX(), this.f7965j.getFinalY())) {
                    this.f7965j.abortAnimation();
                    this.f7960e = true;
                    setScrollState(1);
                    break;
                } else {
                    m1486p();
                    this.f7960e = false;
                    break;
                }
            case 2:
                int i = this.f7957a;
                if (i != -1) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
                    float x = MotionEventCompat.getX(motionEvent, findPointerIndex);
                    float y = MotionEventCompat.getY(motionEvent, findPointerIndex);
                    float f = x - this.f7969n;
                    float f2 = y - this.f7970o;
                    if (m1518b(f, f2) || (m1510c(f, f2) && m1522a(this, false, (int) f, (int) f2, (int) x, (int) y))) {
                        this.f7969n = x;
                        this.f7970o = y;
                        m1493i();
                        this.f7961f = true;
                        return false;
                    }
                    if (m1533a(f, f2)) {
                        m1505d(x, y);
                    }
                    if (this.f7960e && m1497f(x, y)) {
                        ViewCompat.postInvalidateOnAnimation(this);
                        break;
                    }
                }
                break;
            case 6:
                m1508c(motionEvent);
                break;
            default:
                m1490l();
                break;
        }
        m1515b(motionEvent);
        return this.f7960e;
    }

    public void setCacheEnable(boolean z) {
        this.f7958b = z;
    }

    private void setScrollingCacheEnabled(boolean z) {
        boolean z2;
        if (this.f7966k != z) {
            this.f7966k = z;
            this.f7953G = null;
            if (this.f7958b) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    if (childAt.getVisibility() != View.GONE) {
                        if (SDKVersionUtils.m8365i()) {
                            childAt.setDrawingCacheEnabled(z);
                        } else {
                            if (SDKVersionUtils.m8371c()) {
                                z2 = !childAt.isHardwareAccelerated();
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                childAt.setDrawingCacheEnabled(z);
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: j */
    private void m1492j() {
        this.f7960e = true;
        m1485q();
        if (this.f7959d == 3) {
            setMotionState(1);
        }
        setScrollState(1);
        setScrollingCacheEnabled(true);
    }

    /* renamed from: d */
    private void m1505d(float f, float f2) {
        this.f7969n = m1500e(f, this.f7963h);
        this.f7970o = m1500e(f2, this.f7964i);
        m1492j();
    }

    /* renamed from: k */
    private void m1491k() {
        this.f7960e = false;
        this.f7961f = false;
        this.f7957a = -1;
        m1489m();
    }

    /* renamed from: l */
    private void m1490l() {
        m1491k();
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (scrollX != 0 || scrollY != 0) {
            if (Math.abs(scrollX) > (getWidth() >> 1) || Math.abs(scrollY) > (getHeight() >> 1)) {
                m1502d(true);
            } else {
                setScrollState(2);
                this.f7965j.startScroll(scrollX, scrollY, -scrollX, -scrollY, 600);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            setScrollState(0);
            m1484r();
        }
        this.f7953G = null;
    }

    /* renamed from: m */
    private void m1489m() {
        if (this.f7971p != null) {
            this.f7971p.recycle();
            this.f7971p = null;
        }
    }

    /* renamed from: b */
    private void m1515b(MotionEvent motionEvent) {
        if (this.f7971p == null) {
            this.f7971p = VelocityTracker.obtain();
        }
        if (this.f7971p != null) {
            this.f7971p.addMovement(motionEvent);
        }
    }

    /* renamed from: c */
    private void m1508c(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.f7957a) {
            this.f7957a = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? 1 : 0);
            m1525a(motionEvent, this.f7957a);
        }
    }

    /* renamed from: d */
    private boolean m1503d(MotionEvent motionEvent) {
        boolean z = true;
        if (this.f7959d != 3) {
            if (this.f7968m == 0 || getChildCount() <= 0) {
                z = false;
            }
            return z;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int width = getWidth();
        int height = getHeight();
        if (!this.f7954H.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
            if (!this.f7955I) {
                return false;
            }
            if ((!m1506d() || x >= this.f7981z) && ((!m1501e() || x >= width || x <= width - this.f7981z) && ((!m1498f() || y >= this.f7981z) && (!m1495g() || y >= height || y <= height - this.f7981z)))) {
                return false;
            }
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!m1503d(motionEvent)) {
            m1489m();
            return super.onTouchEvent(motionEvent);
        } else if (motionEvent.getAction() != 0 || motionEvent.getEdgeFlags() == 0) {
            m1515b(motionEvent);
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.f7965j.abortAnimation();
                    m1492j();
                    this.f7957a = MotionEventCompat.getPointerId(motionEvent, 0);
                    m1525a(motionEvent, this.f7957a);
                    m1493i();
                    break;
                case 1:
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.f7957a);
                    float x = MotionEventCompat.getX(motionEvent, findPointerIndex);
                    float y = MotionEventCompat.getY(motionEvent, findPointerIndex);
                    if (this.f7960e) {
                        VelocityTracker velocityTracker = this.f7971p;
                        velocityTracker.computeCurrentVelocity(1000, this.f7973r);
                        int xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.f7957a);
                        int yVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.f7957a);
                        int i = (int) (x - this.f7963h);
                        int i2 = (int) (y - this.f7964i);
                        if (this.f7954H.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) && !this.f7949C) {
                            m1512b(true);
                            m1491k();
                            break;
                        } else if (m1517b(xVelocity, yVelocity, i, i2)) {
                            m1491k();
                            break;
                        } else {
                            m1490l();
                            break;
                        }
                    }
                    break;
                case 2:
                    if (!this.f7960e) {
                        int findPointerIndex2 = MotionEventCompat.findPointerIndex(motionEvent, this.f7957a);
                        float x2 = MotionEventCompat.getX(motionEvent, findPointerIndex2);
                        float y2 = MotionEventCompat.getY(motionEvent, findPointerIndex2);
                        if (m1533a(Math.abs(x2 - this.f7969n), Math.abs(y2 - this.f7970o))) {
                            m1505d(x2, y2);
                        }
                    }
                    if (this.f7960e) {
                        int findPointerIndex3 = MotionEventCompat.findPointerIndex(motionEvent, this.f7957a);
                        z = m1497f(MotionEventCompat.getX(motionEvent, findPointerIndex3), MotionEventCompat.getY(motionEvent, findPointerIndex3));
                        break;
                    }
                    break;
                case 3:
                    if (this.f7960e) {
                        m1490l();
                        break;
                    }
                    break;
                case 5:
                    this.f7957a = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                    m1525a(motionEvent, this.f7957a);
                    break;
                case 6:
                    m1508c(motionEvent);
                    m1525a(motionEvent, this.f7957a);
                    break;
            }
            if (z) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: e */
    private float m1500e(float f, float f2) {
        return f - f2 > 0.0f ? this.f7962g + f2 : f2 - this.f7962g;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.f7965j.isFinished() && this.f7965j.computeScrollOffset()) {
            scrollTo(this.f7965j.getCurrX(), this.f7965j.getCurrY());
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        }
        m1486p();
    }

    /* renamed from: n */
    private void m1488n() {
        setScrollState(0);
        if (!this.f7949C) {
            this.f7949C = true;
            if (this.f7947A == 1 && this.f7950D != null) {
                this.f7950D.m1482a();
            }
        }
    }

    /* renamed from: o */
    private void m1487o() {
        setScrollState(3);
        if (this.f7949C) {
            this.f7949C = false;
            if (this.f7951E != null) {
                this.f7951E.mo1483a();
            }
        }
    }

    /* renamed from: p */
    private void m1486p() {
        if (this.f7959d == 2) {
            setScrollingCacheEnabled(false);
            this.f7965j.abortAnimation();
            if (this.f7947A == 2 || Math.abs(getScrollX()) >= getWidth() || Math.abs(getScrollY()) >= getHeight()) {
                m1487o();
            } else {
                m1488n();
            }
            setMotionState(0);
            m1484r();
        }
    }

    /* renamed from: f */
    private boolean m1497f(float f, float f2) {
        this.f7969n = f;
        this.f7970o = f2;
        return m1531a((int) (f - this.f7969n), (int) (f2 - this.f7970o));
    }

    /* renamed from: a */
    private boolean m1522a(View view, boolean z, int i, int i2, int i3, int i4) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = i3 + view.getScrollX();
            int scrollY = i4 + view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (scrollX >= childAt.getLeft() && scrollX < childAt.getRight() && scrollY >= childAt.getTop() && scrollY < childAt.getBottom() && m1522a(childAt, true, i, i2, scrollX - childAt.getLeft(), scrollY - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && m1524a(view, i, i2);
    }

    /* renamed from: b */
    protected boolean m1517b(int i, int i2, int i3, int i4) {
        boolean z = Math.abs(i3) > this.f7974s;
        boolean z2 = Math.abs(i4) > this.f7974s;
        if ((z && m1506d() && i < (-this.f7972q)) || ((z && m1501e() && i > this.f7972q) || ((z2 && m1498f() && i2 < (-this.f7972q)) || (z2 && m1495g() && i2 > this.f7972q)))) {
            m1502d(true);
            return true;
        } else if ((z && m1519b() && Math.abs(i) > this.f7972q) || (z2 && m1511c() && Math.abs(i2) > this.f7972q)) {
            m1496f(true);
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: a */
    protected boolean m1531a(int i, int i2) {
        int scrollX = getScrollX() - i;
        int scrollY = getScrollY() - i2;
        if ((!m1506d() && scrollX > 0) || ((!m1501e() && scrollX < 0) || !m1519b())) {
            i = 0;
        }
        if ((!m1498f() && scrollY > 0) || ((!m1495g() && scrollY < 0) || !m1511c())) {
            i2 = 0;
        }
        if (i == 0 && i2 == 0) {
            return false;
        }
        scrollBy(-i, -i2);
        return true;
    }

    /* renamed from: c */
    protected boolean m1509c(int i, int i2, int i3, int i4) {
        return m1519b() ? Math.abs(i3 - i) > this.f7975t : Math.abs(i4 - i2) > this.f7975t;
    }

    /* renamed from: a */
    protected boolean m1533a(float f, float f2) {
        return (this.f7959d == 3 && (Math.abs(f) > ((float) this.f7962g) || Math.abs(f2) > ((float) this.f7962g))) || (m1506d() && f < ((float) (-this.f7962g))) || ((m1501e() && f > ((float) this.f7962g)) || ((m1498f() && f2 < ((float) (-this.f7962g))) || (m1495g() && f2 > ((float) this.f7962g))));
    }

    /* renamed from: b */
    protected boolean m1518b(float f, float f2) {
        boolean z = false;
        boolean m1519b = m1519b();
        boolean m1511c = m1511c();
        if (m1519b && !m1511c) {
            return Math.abs(f2) > ((float) this.f7962g);
        } else if (!m1519b && m1511c) {
            return Math.abs(f) > ((float) this.f7962g);
        } else {
            if (!m1519b || !m1511c) {
                z = true;
            }
            return z;
        }
    }

    /* renamed from: c */
    protected boolean m1510c(float f, float f2) {
        return m1519b() ? f != 0.0f : m1511c() && f2 != 0.0f;
    }

    /* renamed from: a */
    protected boolean m1524a(View view, int i, int i2) {
        if (m1519b()) {
            return ViewCompat.canScrollHorizontally(view, -i);
        }
        return m1511c() && ViewCompat.canScrollVertically(view, -i2);
    }

    public int getSlidingCloseMode() {
        return this.f7968m;
    }

    /* renamed from: b */
    public boolean m1519b() {
        return m1506d() || m1501e();
    }

    /* renamed from: c */
    public boolean m1511c() {
        return m1495g() || m1498f();
    }

    /* renamed from: d */
    public boolean m1506d() {
        return (this.f7968m & 1) == 1;
    }

    /* renamed from: e */
    public boolean m1501e() {
        return (this.f7968m & 2) == 2;
    }

    /* renamed from: f */
    public boolean m1498f() {
        return (this.f7968m & 4) == 4;
    }

    /* renamed from: g */
    public boolean m1495g() {
        return (this.f7968m & 8) == 8;
    }

    /* renamed from: h */
    public boolean m1494h() {
        return this.f7948B != 2 && this.f7949C;
    }

    /* renamed from: a */
    public void m1520a(final boolean z) {
        setMotionState(2);
        post(new Runnable() { // from class: com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.2
            @Override // java.lang.Runnable
            public void run() {
                SlidingClosableRelativeLayout.this.m1502d(z);
            }
        });
    }

    /* renamed from: c */
    private boolean m1507c(boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        int width = getWidth();
        int height = getHeight();
        if (m1511c()) {
            i = m1498f() ? height : -height;
            i2 = 0;
        } else if (m1519b()) {
            i2 = m1501e() ? -width : width;
            i = 0;
        } else {
            i = 0;
            i2 = 0;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (scrollX > 0) {
            i3 = width - scrollX;
        } else {
            i3 = scrollX < 0 ? (-scrollX) - width : i2;
        }
        if (scrollY > 0) {
            i4 = height - scrollY;
        } else {
            i4 = scrollY < 0 ? (-scrollY) - height : i;
        }
        if (i3 == 0 && i4 == 0) {
            return false;
        }
        if (z && isShown()) {
            this.f7965j.startScroll(scrollX, scrollY, i3, i4, 600);
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        scrollBy(i3, i4);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m1502d(boolean z) {
        boolean z2 = this.f7959d == 1;
        setScrollState(2);
        if (m1507c(z)) {
            if (!z2) {
                m1485q();
                return;
            }
            return;
        }
        m1486p();
    }

    public void setInitSlidingOpenState(boolean z) {
        if (this.f7948B >= 0) {
            this.f7948B = z ? 1 : 2;
        }
    }

    /* renamed from: e */
    private boolean m1499e(boolean z) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (z && isShown()) {
            this.f7965j.startScroll(scrollX, scrollY, -scrollX, -scrollY, 600);
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        scrollBy(-scrollX, -scrollY);
        return false;
    }

    /* renamed from: b */
    public void m1512b(final boolean z) {
        setMotionState(1);
        post(new Runnable() { // from class: com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.3
            @Override // java.lang.Runnable
            public void run() {
                SlidingClosableRelativeLayout.this.m1496f(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m1496f(boolean z) {
        boolean z2 = this.f7959d == 1;
        setScrollState(2);
        if (m1499e(z)) {
            if (!z2) {
                m1485q();
                return;
            }
            return;
        }
        m1486p();
    }

    /* renamed from: q */
    private void m1485q() {
        if (this.f7952F != null) {
            this.f7952F.mo1481a();
        }
    }

    /* renamed from: r */
    private void m1484r() {
        if (this.f7952F != null) {
            this.f7952F.mo1480b();
        }
    }

    /* renamed from: a */
    public void m1527a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        this.f7976u = drawable;
        this.f7977v = drawable2;
        this.f7978w = drawable3;
        this.f7979x = drawable4;
    }

    /* renamed from: d */
    public void m1504d(int i, int i2, int i3, int i4) {
        m1527a(m1532a(i), m1532a(i2), m1532a(i3), m1532a(i4));
    }

    public void setShadowWidth(int i) {
        this.f7980y = i;
    }

    /* renamed from: a */
    private Drawable m1532a(int i) {
        if (i == 0) {
            return null;
        }
        return getResources().getDrawable(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        float width;
        float min;
        if (this.f7968m != 0 && this.f7959d != 0 && this.f7980y > 0 && getChildCount() == 1) {
            View childAt = getChildAt(0);
            if (childAt.getVisibility() == View.VISIBLE) {
                if (this.f7967l) {
                    if (m1511c()) {
                        width = getHeight();
                        min = Math.min(Math.abs(getScrollY()), width);
                    } else {
                        width = getWidth();
                        min = Math.min(Math.abs(getScrollX()), width);
                    }
                    if (min != 0.0f && width != 0.0f) {
                        canvas.drawARGB((int) (176.0f - ((min * 176.0f) / width)), 0, 0, 0);
                    }
                }
                m1523a(childAt, canvas);
                if (m1514b(childAt, canvas)) {
                    return;
                }
            }
        }
        super.dispatchDraw(canvas);
    }

    /* renamed from: b */
    private boolean m1514b(View view, Canvas canvas) {
        if (this.f7953G == null) {
            this.f7953G = view.getDrawingCache();
        }
        Bitmap bitmap = this.f7953G;
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        }
        canvas.drawBitmap(bitmap, view.getLeft(), view.getTop(), (Paint) null);
        return true;
    }

    /* renamed from: a */
    public void m1523a(View view, Canvas canvas) {
        int left = view.getLeft();
        int top = view.getTop();
        int right = view.getRight();
        int bottom = view.getBottom();
        int i = left - this.f7980y;
        int i2 = this.f7980y + right;
        int i3 = top - this.f7980y;
        int i4 = this.f7980y + bottom;
        if (m1519b()) {
            if (this.f7976u != null) {
                this.f7976u.setBounds(i, top, left, bottom);
                this.f7976u.draw(canvas);
            }
            if (this.f7978w != null) {
                this.f7978w.setBounds(right, top, i2, bottom);
                this.f7978w.draw(canvas);
            }
        }
        if (m1511c()) {
            if (this.f7977v != null) {
                this.f7977v.setBounds(left, i3, right, top);
                this.f7977v.draw(canvas);
            }
            if (this.f7979x != null) {
                this.f7979x.setBounds(left, bottom, right, i4);
                this.f7979x.draw(canvas);
            }
        }
    }
}
