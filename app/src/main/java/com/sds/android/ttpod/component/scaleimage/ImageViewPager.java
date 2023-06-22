package com.sds.android.ttpod.component.scaleimage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.ParcelableCompat;
import androidx.core.os.ParcelableCompatCreatorCallbacks;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.Comparator;

/* loaded from: classes.dex */
public class ImageViewPager extends ViewGroup {

    /* renamed from: a */
    private static final Comparator<C1317b> f4749a = new Comparator<C1317b>() { // from class: com.sds.android.ttpod.component.scaleimage.ImageViewPager.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(C1317b c1317b, C1317b c1317b2) {
            return c1317b.f4793c - c1317b2.f4793c;
        }
    };

    /* renamed from: b */
    private static final Interpolator f4750b = new Interpolator() { // from class: com.sds.android.ttpod.component.scaleimage.ImageViewPager.2
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2) + 1.0f;
        }
    };

    /* renamed from: A */
    private VelocityTracker f4751A;

    /* renamed from: B */
    private int f4752B;

    /* renamed from: C */
    private int f4753C;

    /* renamed from: D */
    private float f4754D;

    /* renamed from: E */
    private float f4755E;

    /* renamed from: F */
    private int f4756F;

    /* renamed from: G */
    private boolean f4757G;

    /* renamed from: H */
    private EdgeEffectCompat f4758H;

    /* renamed from: I */
    private EdgeEffectCompat f4759I;

    /* renamed from: J */
    private boolean f4760J;

    /* renamed from: K */
    private InterfaceC1318c f4761K;

    /* renamed from: L */
    private int f4762L;

    /* renamed from: c */
    private final ArrayList<C1317b> f4763c;

    /* renamed from: d */
    private ImagePagerAdapter f4764d;

    /* renamed from: e */
    private int f4765e;

    /* renamed from: f */
    private int f4766f;

    /* renamed from: g */
    private Parcelable f4767g;

    /* renamed from: h */
    private ClassLoader f4768h;

    /* renamed from: i */
    private Scroller f4769i;

    /* renamed from: j */
    private ImagePagerAdapter.InterfaceC1329a f4770j;

    /* renamed from: k */
    private int f4771k;

    /* renamed from: l */
    private Drawable f4772l;

    /* renamed from: m */
    private int f4773m;

    /* renamed from: n */
    private int f4774n;

    /* renamed from: o */
    private boolean f4775o;

    /* renamed from: p */
    private boolean f4776p;

    /* renamed from: q */
    private boolean f4777q;

    /* renamed from: r */
    private boolean f4778r;

    /* renamed from: s */
    private int f4779s;

    /* renamed from: t */
    private boolean f4780t;

    /* renamed from: u */
    private boolean f4781u;

    /* renamed from: v */
    private int f4782v;

    /* renamed from: w */
    private float f4783w;

    /* renamed from: x */
    private float f4784x;

    /* renamed from: y */
    private float f4785y;

    /* renamed from: z */
    private int f4786z;

    /* renamed from: com.sds.android.ttpod.component.scaleimage.ImageViewPager$c */
    /* loaded from: classes.dex */
    public interface InterfaceC1318c {
        /* renamed from: a */
        void mo5864a(int i);

        /* renamed from: a */
        void mo5863a(int i, float f, int i2);

        /* renamed from: a */
        void mo5862a(int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.component.scaleimage.ImageViewPager$b */
    /* loaded from: classes.dex */
    public class C1317b {

        /* renamed from: b */
        private Object f4792b;

        /* renamed from: c */
        private int f4793c;

        /* renamed from: d */
        private boolean f4794d;

        private C1317b() {
        }
    }

    public ImageViewPager(Context context) {
        super(context);
        this.f4763c = new ArrayList<>();
        this.f4766f = -1;
        this.f4767g = null;
        this.f4768h = null;
        this.f4779s = 1;
        this.f4786z = -1;
        this.f4760J = true;
        this.f4762L = 0;
        m5918a();
    }

    public ImageViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4763c = new ArrayList<>();
        this.f4766f = -1;
        this.f4767g = null;
        this.f4768h = null;
        this.f4779s = 1;
        this.f4786z = -1;
        this.f4760J = true;
        this.f4762L = 0;
        m5918a();
    }

    /* renamed from: a */
    void m5918a() {
        setWillNotDraw(false);
        setDescendantFocusability(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
        setFocusable(true);
        Context context = getContext();
        this.f4769i = new Scroller(context, f4750b);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f4782v = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.f4752B = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f4753C = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f4758H = new EdgeEffectCompat(context);
        this.f4759I = new EdgeEffectCompat(context);
        this.f4754D = context.getResources().getDisplayMetrics().density * 2500.0f;
        this.f4755E = 0.4f;
        this.f4756F = Math.round(getResources().getDisplayMetrics().density * 40.0f);
    }

    private void setScrollState(int i) {
        if (this.f4762L != i) {
            this.f4762L = i;
            if (this.f4761K != null) {
                this.f4761K.mo5864a(i);
            }
        }
    }

    public void setAdapter(ImagePagerAdapter imagePagerAdapter) {
        if (this.f4764d != null) {
            this.f4764d.m5852a((ImagePagerAdapter.InterfaceC1329a) null);
            this.f4764d.mo5856a(this);
            for (int i = 0; i < this.f4763c.size(); i++) {
                C1317b c1317b = this.f4763c.get(i);
                this.f4764d.mo5854a(this, c1317b.f4793c, c1317b.f4792b);
            }
            this.f4764d.mo5850b(this);
            this.f4763c.clear();
            removeAllViews();
            this.f4765e = 0;
            scrollTo(0, 0);
        }
        this.f4764d = imagePagerAdapter;
        if (this.f4764d != null) {
            if (this.f4770j == null) {
                this.f4770j = new C1316a();
            }
            this.f4764d.m5852a(this.f4770j);
            this.f4777q = false;
            if (this.f4766f >= 0) {
                this.f4764d.mo5857a(this.f4767g, this.f4768h);
                m5912a(this.f4766f, false, true);
                this.f4766f = -1;
                this.f4767g = null;
                this.f4768h = null;
                return;
            }
            m5906b();
        }
    }

    public ImagePagerAdapter getAdapter() {
        return this.f4764d;
    }

    public void setCurrentItem(int i) {
        this.f4777q = false;
        m5912a(i, !this.f4760J, false);
    }

    /* renamed from: a */
    public void m5913a(int i, boolean z) {
        this.f4777q = false;
        m5912a(i, z, false);
    }

    public int getCurrentItem() {
        return this.f4765e;
    }

    /* renamed from: a */
    void m5912a(int i, boolean z, boolean z2) {
        m5911a(i, z, z2, 0);
    }

    /* renamed from: a */
    void m5911a(int i, boolean z, boolean z2, int i2) {
        int i3 = this.f4765e;
        if (this.f4764d == null || this.f4764d.mo5858a() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (!z2 && this.f4765e == i && this.f4763c.size() != 0) {
            setScrollingCacheEnabled(false);
        } else {
            if (i < 0) {
                i = 0;
            } else if (i >= this.f4764d.mo5858a()) {
                i = this.f4764d.mo5858a() - 1;
            }
            int i4 = this.f4779s;
            if (i > this.f4765e + i4 || i < this.f4765e - i4) {
                for (int i5 = 0; i5 < this.f4763c.size(); i5++) {
                    this.f4763c.get(i5).f4794d = true;
                }
            }
            boolean z3 = this.f4765e != i;
            this.f4765e = i;
            m5906b();
            int width = (getWidth() + this.f4771k) * i;
            if (z) {
                m5915a(width, 0, i2);
                if (z3 && this.f4761K != null) {
                    this.f4761K.mo5862a(i, i3);
                    return;
                }
                return;
            }
            if (z3 && this.f4761K != null) {
                this.f4761K.mo5862a(i, i3);
            }
            m5902e();
            scrollTo(width, 0);
        }
    }

    public void setOnPageChangeListener(InterfaceC1318c interfaceC1318c) {
        this.f4761K = interfaceC1318c;
    }

    public int getOffscreenPageLimit() {
        return this.f4779s;
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            i = 1;
        }
        if (i != this.f4779s) {
            this.f4779s = i;
            m5906b();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.f4771k;
        this.f4771k = i;
        int width = getWidth();
        m5914a(width, width, i, i2);
        requestLayout();
    }

    public int getPageMargin() {
        return this.f4771k;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f4772l = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f4772l;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f4772l;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    /* renamed from: a */
    void m5915a(int i, int i2, int i3) {
        int i4;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i5 = i - scrollX;
        int i6 = i2 - scrollY;
        if (i5 == 0 && i6 == 0) {
            m5902e();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        this.f4778r = true;
        setScrollState(2);
        int abs = (int) ((Math.abs(i5) / (getWidth() + this.f4771k)) * 100.0f);
        int abs2 = Math.abs(i3);
        if (abs2 > 0) {
            i4 = (int) (((abs / (abs2 / this.f4754D)) * this.f4755E) + abs);
        } else {
            i4 = abs + 100;
        }
        this.f4769i.startScroll(scrollX, scrollY, i5, i6, Math.min(i4, 600));
        invalidate();
    }

    /* renamed from: a */
    void m5916a(int i, int i2) {
        C1317b c1317b = new C1317b();
        c1317b.f4793c = i;
        c1317b.f4792b = this.f4764d.mo5855a(this, i);
        if (i2 < 0) {
            this.f4763c.add(c1317b);
        } else {
            this.f4763c.add(i2, c1317b);
        }
    }

    /* renamed from: b */
    void m5906b() {
        C1317b c1317b;
        if (this.f4764d != null && !this.f4777q && getWindowToken() != null) {
            this.f4764d.mo5856a(this);
            int i = this.f4779s;
            int max = Math.max(0, this.f4765e - i);
            int min = Math.min(this.f4764d.mo5858a() - 1, i + this.f4765e);
            int i2 = 0;
            int i3 = -1;
            while (i2 < this.f4763c.size()) {
                C1317b c1317b2 = this.f4763c.get(i2);
                if ((c1317b2.f4793c < max || c1317b2.f4793c > min) && !c1317b2.f4794d) {
                    this.f4763c.remove(i2);
                    i2--;
                    this.f4764d.mo5854a(this, c1317b2.f4793c, c1317b2.f4792b);
                } else if (i3 < min && c1317b2.f4793c > max) {
                    int i4 = i3 + 1;
                    if (i4 < max) {
                        i4 = max;
                    }
                    while (i4 <= min && i4 < c1317b2.f4793c) {
                        m5916a(i4, i2);
                        i4++;
                        i2++;
                    }
                }
                int i5 = i2;
                int i6 = c1317b2.f4793c;
                int i7 = i5 + 1;
                i3 = i6;
                i2 = i7;
            }
            int i8 = this.f4763c.size() > 0 ? this.f4763c.get(this.f4763c.size() - 1).f4793c : -1;
            if (i8 < min) {
                int i9 = i8 + 1;
                if (i9 <= max) {
                    i9 = max;
                }
                while (i9 <= min) {
                    m5916a(i9, -1);
                    i9++;
                }
            }
            int i10 = 0;
            while (true) {
                if (i10 < this.f4763c.size()) {
                    if (this.f4763c.get(i10).f4793c != this.f4765e) {
                        i10++;
                    } else {
                        c1317b = this.f4763c.get(i10);
                        break;
                    }
                } else {
                    c1317b = null;
                    break;
                }
            }
            this.f4764d.m5849b(this, this.f4765e, c1317b != null ? c1317b.f4792b : null);
            this.f4764d.mo5850b(this);
            if (hasFocus()) {
                View findFocus = findFocus();
                C1317b m5905b = findFocus != null ? m5905b(findFocus) : null;
                if (m5905b == null || m5905b.f4793c != this.f4765e) {
                    for (int i11 = 0; i11 < getChildCount(); i11++) {
                        View childAt = getChildAt(i11);
                        C1317b m5908a = m5908a(childAt);
                        if (m5908a != null && m5908a.f4793c == this.f4765e && childAt.requestFocus(2)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() { // from class: com.sds.android.ttpod.component.scaleimage.ImageViewPager.SavedState.1
            @Override // android.support.v4.p008os.ParcelableCompatCreatorCallbacks
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.support.v4.p008os.ParcelableCompatCreatorCallbacks
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        });

        /* renamed from: a */
        private int f4787a;

        /* renamed from: b */
        private Parcelable f4788b;

        /* renamed from: c */
        private ClassLoader f4789c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f4787a);
            parcel.writeParcelable(this.f4788b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f4787a + "}";
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f4787a = parcel.readInt();
            this.f4788b = parcel.readParcelable(classLoader);
            this.f4789c = classLoader;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4787a = this.f4765e;
        if (this.f4764d != null) {
            savedState.f4788b = this.f4764d.mo5851b();
        }
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (this.f4764d != null) {
            this.f4764d.mo5857a(savedState.f4788b, savedState.f4789c);
            m5912a(savedState.f4787a, false, true);
            return;
        }
        this.f4766f = savedState.f4787a;
        this.f4767g = savedState.f4788b;
        this.f4768h = savedState.f4789c;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.f4775o) {
            addViewInLayout(view, i, layoutParams);
            view.measure(this.f4773m, this.f4774n);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    /* renamed from: a */
    C1317b m5908a(View view) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f4763c.size()) {
                C1317b c1317b = this.f4763c.get(i2);
                if (!this.f4764d.mo5853a(view, c1317b.f4792b)) {
                    i = i2 + 1;
                } else {
                    return c1317b;
                }
            } else {
                return null;
            }
        }
    }

    /* renamed from: b */
    C1317b m5905b(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent != this) {
                if (parent == null || !(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            } else {
                return m5908a(view);
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f4760J = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        this.f4773m = View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), MeasureSpec.EXACTLY);
        this.f4774n = View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), MeasureSpec.EXACTLY);
        this.f4775o = true;
        m5906b();
        this.f4775o = false;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != View.GONE) {
                childAt.measure(this.f4773m, this.f4774n);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            m5914a(i, i3, this.f4771k, this.f4771k);
        }
    }

    /* renamed from: a */
    private void m5914a(int i, int i2, int i3, int i4) {
        int i5 = i + i3;
        if (i2 > 0) {
            int scrollX = getScrollX();
            int i6 = i2 + i4;
            int i7 = (int) ((((scrollX % i6) / i6) + (scrollX / i6)) * i5);
            scrollTo(i7, getScrollY());
            if (!this.f4769i.isFinished()) {
                this.f4769i.startScroll(i7, 0, i5 * this.f4765e, 0, this.f4769i.getDuration() - this.f4769i.timePassed());
                return;
            }
            return;
        }
        int i8 = this.f4765e * i5;
        if (i8 != getScrollX()) {
            m5902e();
            scrollTo(i8, getScrollY());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f4775o = true;
        m5906b();
        this.f4775o = false;
        int childCount = getChildCount();
        int i5 = i3 - i;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            C1317b m5908a = m5908a(childAt);
            if (childAt.getVisibility() != View.GONE && m5908a != null) {
                int paddingLeft = (m5908a.f4793c * (this.f4771k + i5)) + getPaddingLeft();
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
            }
        }
        this.f4760J = false;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.f4769i.isFinished() && this.f4769i.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.f4769i.getCurrX();
            int currY = this.f4769i.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
            }
            if (this.f4761K != null) {
                int width = getWidth() + this.f4771k;
                int i = currX / width;
                int i2 = currX % width;
                this.f4761K.mo5863a(i, i2 / width, i2);
            }
            invalidate();
            return;
        }
        m5902e();
    }

    /* renamed from: e */
    private void m5902e() {
        boolean z = this.f4778r;
        if (z) {
            setScrollingCacheEnabled(false);
            this.f4769i.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.f4769i.getCurrX();
            int currY = this.f4769i.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
            }
            setScrollState(0);
        }
        this.f4777q = false;
        this.f4778r = false;
        boolean z2 = z;
        for (int i = 0; i < this.f4763c.size(); i++) {
            C1317b c1317b = this.f4763c.get(i);
            if (c1317b.f4794d) {
                z2 = true;
                c1317b.f4794d = false;
            }
        }
        if (z2) {
            m5906b();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.f4780t = false;
            this.f4781u = false;
            this.f4786z = -1;
            return false;
        }
        if (action != 0) {
            if (this.f4780t) {
                return true;
            }
            if (this.f4781u) {
                return false;
            }
        }
        switch (action) {
            case 0:
                this.f4783w = motionEvent.getX();
                this.f4784x = this.f4783w;
                this.f4785y = motionEvent.getY();
                this.f4786z = MotionEventCompat.getPointerId(motionEvent, 0);
                if (this.f4762L == 2) {
                    this.f4780t = true;
                    this.f4781u = false;
                    setScrollState(1);
                    break;
                } else {
                    m5902e();
                    this.f4780t = false;
                    this.f4781u = false;
                    break;
                }
            case 2:
                int i = this.f4786z;
                if (i != -1) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
                    float x = MotionEventCompat.getX(motionEvent, findPointerIndex);
                    float f = x - this.f4784x;
                    float abs = Math.abs(f);
                    float y = MotionEventCompat.getY(motionEvent, findPointerIndex);
                    float abs2 = Math.abs(y - this.f4785y);
                    int scrollX = getScrollX();
                    if ((f <= 0.0f || scrollX != 0) && f < 0.0f && this.f4764d != null && scrollX >= ((this.f4764d.mo5858a() - 1) * getWidth()) - 1) {
                    }
                    if (m5907a(this, false, (int) f, (int) x, (int) y)) {
                        this.f4784x = x;
                        this.f4783w = this.f4784x;
                        this.f4785y = y;
                        return false;
                    } else if (abs > this.f4782v && abs > abs2) {
                        this.f4780t = true;
                        setScrollState(1);
                        this.f4784x = x;
                        setScrollingCacheEnabled(true);
                        break;
                    } else if (abs2 > this.f4782v) {
                        this.f4781u = true;
                        break;
                    }
                }
                break;
            case 6:
                m5909a(motionEvent);
                break;
        }
        return this.f4780t;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        float f;
        boolean r0 = false;
        if (this.f4757G) {
            return true;
        }
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || this.f4764d == null || this.f4764d.mo5858a() == 0) {
            return false;
        }
        if (this.f4751A == null) {
            this.f4751A = VelocityTracker.obtain();
        }
        this.f4751A.addMovement(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                m5902e();
                this.f4783w = motionEvent.getX();
                this.f4784x = this.f4783w;
                this.f4786z = MotionEventCompat.getPointerId(motionEvent, 0);
                break;
            case 1:
                if (this.f4780t) {
                    VelocityTracker velocityTracker = this.f4751A;
                    velocityTracker.computeCurrentVelocity(1000, this.f4753C);
                    int xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.f4786z);
                    this.f4777q = true;
                    int width = this.f4771k + getWidth();
                    int scrollX = getScrollX();
                    int i = scrollX / width;
                    int i2 = scrollX % width;
                    if (xVelocity > 0.0f) {
                        if (width - i2 <= this.f4756F) {
                            i++;
                        }
                    } else if (i2 > this.f4756F) {
                        i++;
                    }
                    m5911a(i, true, true, xVelocity);
                    this.f4786z = -1;
                    m5901f();
                    r0 = this.f4758H.onRelease() | this.f4759I.onRelease();
                    break;
                }
                break;
            case 2:
                if (!this.f4780t) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.f4786z);
                    if (findPointerIndex < 0) {
                        return true;
                    }
                    float x = MotionEventCompat.getX(motionEvent, findPointerIndex);
                    float abs = Math.abs(x - this.f4784x);
                    float abs2 = Math.abs(MotionEventCompat.getY(motionEvent, findPointerIndex) - this.f4785y);
                    if (abs > this.f4782v && abs > abs2) {
                        this.f4780t = true;
                        this.f4784x = x;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                    }
                }
                if (this.f4780t) {
                    float x2 = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.f4786z));
                    float f2 = this.f4784x - x2;
                    this.f4784x = x2;
                    float scrollX2 = getScrollX() + f2;
                    int width2 = getWidth();
                    int i3 = width2 + this.f4771k;
                    int mo5858a = this.f4764d.mo5858a() - 1;
                    float max = Math.max(0, (this.f4765e - 1) * i3);
                    float min = Math.min(this.f4765e + 1, mo5858a) * i3;
                    if (scrollX2 < max) {
                        z = max == 0.0f ? this.f4758H.onPull((-scrollX2) / width2) : false;
                        f = max;
                    } else if (scrollX2 > min) {
                        z = min == ((float) (mo5858a * i3)) ? this.f4759I.onPull((scrollX2 - min) / width2) : false;
                        f = min;
                    } else {
                        z = false;
                        f = scrollX2;
                    }
                    this.f4784x += f - ((int) f);
                    scrollTo((int) f, getScrollY());
                    if (this.f4761K != null) {
                        int i4 = ((int) f) % i3;
                        this.f4761K.mo5863a(((int) f) / i3, i4 / i3, i4);
                    }
                    r0 = z;
                    break;
                }
                break;
            case 3:
                if (this.f4780t) {
                    m5912a(this.f4765e, true, true);
                    this.f4786z = -1;
                    m5901f();
                    r0 = this.f4758H.onRelease() | this.f4759I.onRelease();
                    break;
                }
                break;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.f4784x = MotionEventCompat.getX(motionEvent, actionIndex);
                this.f4786z = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            case 6:
                m5909a(motionEvent);
                this.f4784x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.f4786z));
                break;
        }
        if (r0) {
            invalidate();
        }
        return true;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean z = false;
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        if (overScrollMode == 0 || (overScrollMode == 1 && this.f4764d != null && this.f4764d.mo5858a() > 1)) {
            if (!this.f4758H.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), 0.0f);
                this.f4758H.setSize(height, getWidth());
                z = false | this.f4758H.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.f4759I.isFinished()) {
                int save2 = canvas.save();
                int width = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int mo5858a = this.f4764d != null ? this.f4764d.mo5858a() : 1;
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), ((-mo5858a) * (this.f4771k + width)) + this.f4771k);
                this.f4759I.setSize(height2, width);
                z |= this.f4759I.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.f4758H.finish();
            this.f4759I.finish();
        }
        if (z) {
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f4771k > 0 && this.f4772l != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            int i = scrollX % (this.f4771k + width);
            if (i != 0) {
                int i2 = (scrollX - i) + width;
                this.f4772l.setBounds(i2, 0, this.f4771k + i2, getHeight());
                this.f4772l.draw(canvas);
            }
        }
    }

    /* renamed from: a */
    private void m5909a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.f4786z) {
            int i = actionIndex == 0 ? 1 : 0;
            this.f4784x = MotionEventCompat.getX(motionEvent, i);
            this.f4786z = MotionEventCompat.getPointerId(motionEvent, i);
            if (this.f4751A != null) {
                this.f4751A.clear();
            }
        }
    }

    /* renamed from: f */
    private void m5901f() {
        this.f4780t = false;
        this.f4781u = false;
        if (this.f4751A != null) {
            this.f4751A.recycle();
            this.f4751A = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.f4776p != z) {
            this.f4776p = z;
        }
    }

    /* renamed from: a */
    protected boolean m5907a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom() && m5907a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && ViewCompat.canScrollHorizontally(view, -i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m5910a(keyEvent);
    }

    /* renamed from: a */
    public boolean m5910a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                return m5917a(17);
            case 22:
                return m5917a(66);
            case 61 /* 61 */:
                if (keyEvent.hasNoModifiers()) {
                    return m5917a(2);
                }
                if (!keyEvent.hasModifiers(1)) {
                    return false;
                }
                return m5917a(1);
            default:
                return false;
        }
    }

    /* renamed from: a */
    public boolean m5917a(int i) {
        boolean m5904c;
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        if (findNextFocus != null && findNextFocus != findFocus) {
            if (i == 17) {
                if (findFocus != null && findNextFocus.getLeft() >= findFocus.getLeft()) {
                    m5904c = m5904c();
                } else {
                    m5904c = findNextFocus.requestFocus();
                }
            } else {
                if (i == 66) {
                    if (findFocus != null && findNextFocus.getLeft() <= findFocus.getLeft()) {
                        m5904c = m5903d();
                    } else {
                        m5904c = findNextFocus.requestFocus();
                    }
                }
                m5904c = false;
            }
        } else if (i == 17 || i == 1) {
            m5904c = m5904c();
        } else {
            if (i == 66 || i == 2) {
                m5904c = m5903d();
            }
            m5904c = false;
        }
        if (m5904c) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return m5904c;
    }

    /* renamed from: c */
    boolean m5904c() {
        if (this.f4765e > 0) {
            m5913a(this.f4765e - 1, true);
            return true;
        }
        return false;
    }

    /* renamed from: d */
    boolean m5903d() {
        if (this.f4764d == null || this.f4765e >= this.f4764d.mo5858a() - 1) {
            return false;
        }
        m5913a(this.f4765e + 1, true);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        C1317b m5908a;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == View.VISIBLE && (m5908a = m5908a(childAt)) != null && m5908a.f4793c == this.f4765e) {
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
                arrayList.add(this);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        C1317b m5908a;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == View.VISIBLE && (m5908a = m5908a(childAt)) != null && m5908a.f4793c == this.f4765e) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        C1317b m5908a;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == View.VISIBLE && (m5908a = m5908a(childAt)) != null && m5908a.f4793c == this.f4765e && childAt.requestFocus(i, rect)) {
                return true;
            }
            i2 += i3;
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        C1317b m5908a;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == View.VISIBLE && (m5908a = m5908a(childAt)) != null && m5908a.f4793c == this.f4765e && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: com.sds.android.ttpod.component.scaleimage.ImageViewPager$a */
    /* loaded from: classes.dex */
    private class C1316a implements ImagePagerAdapter.InterfaceC1329a {
        private C1316a() {
        }
    }
}
