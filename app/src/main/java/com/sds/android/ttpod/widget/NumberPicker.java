package com.sds.android.ttpod.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.ttpod.R;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class NumberPicker extends LinearLayout {

    /* renamed from: a */
    private static final C2220k f7758a = new C2220k();

    /* renamed from: ag */
    private static final char[] f7759ag = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785};

    /* renamed from: A */
    private int f7760A;

    /* renamed from: B */
    private final Scroller f7761B;

    /* renamed from: C */
    private final Scroller f7762C;

    /* renamed from: D */
    private int f7763D;

    /* renamed from: E */
    private RunnableC2218i f7764E;

    /* renamed from: F */
    private RunnableC2212c f7765F;

    /* renamed from: G */
    private RunnableC2211b f7766G;

    /* renamed from: H */
    private float f7767H;

    /* renamed from: I */
    private long f7768I;

    /* renamed from: J */
    private float f7769J;

    /* renamed from: K */
    private VelocityTracker f7770K;

    /* renamed from: L */
    private int f7771L;

    /* renamed from: M */
    private int f7772M;

    /* renamed from: N */
    private int f7773N;

    /* renamed from: O */
    private boolean f7774O;

    /* renamed from: P */
    private final int solidColor;

    /* renamed from: Q */
    private final boolean f7776Q;

    /* renamed from: R */
    private final Drawable selectionDivider;

    /* renamed from: S */
    private final int selectionDividerHeight;

    /* renamed from: T */
    private int f7779T;

    /* renamed from: U */
    private boolean f7780U;

    /* renamed from: V */
    private boolean f7781V;

    /* renamed from: W */
    private int f7782W;

    /* renamed from: Z */
    private int f7783Z;

    /* renamed from: aa */
    private int f7784aa;

    /* renamed from: ab */
    private boolean f7785ab;

    /* renamed from: ac */
    private boolean f7786ac;

    /* renamed from: ad */
    private C2219j f7787ad;

    /* renamed from: ae */
    private final RunnableC2217h f7788ae;

    /* renamed from: af */
    private int f7789af;

    /* renamed from: b */
    private final ImageButton f7790b;

    /* renamed from: c */
    private final ImageButton f7791c;

    /* renamed from: d */
    private final EditText f7792d;

    /* renamed from: e */
    private final int selectionDividersDistance;

    /* renamed from: f */
    private final int internalMinHeight;

    /* renamed from: g */
    private final int internalMaxHeight;

    /* renamed from: h */
    private final int internalMinWidth;

    /* renamed from: i */
    private int internalMaxWidth;

    /* renamed from: j */
    private final boolean f7798j;

    /* renamed from: k */
    private final int f7799k;

    /* renamed from: l */
    private int f7800l;

    /* renamed from: m */
    private String[] f7801m;

    /* renamed from: n */
    private int f7802n;

    /* renamed from: o */
    private int f7803o;

    /* renamed from: p */
    private int f7804p;

    /* renamed from: q */
    private InterfaceC2216g f7805q;

    /* renamed from: r */
    private InterfaceC2215f f7806r;

    /* renamed from: s */
    private InterfaceC2213d f7807s;

    /* renamed from: t */
    private long f7808t;

    /* renamed from: u */
    private final SparseArray<String> f7809u;

    /* renamed from: v */
    private final int[] f7810v;

    /* renamed from: w */
    private final Paint f7811w;

    /* renamed from: x */
    private final Drawable virtualButtonPressedDrawable;

    /* renamed from: y */
    private int f7813y;

    /* renamed from: z */
    private int f7814z;

    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$d */
    /* loaded from: classes.dex */
    public interface InterfaceC2213d {
        /* renamed from: a */
        String mo1596a(int i);
    }

    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$f */
    /* loaded from: classes.dex */
    public interface InterfaceC2215f {
        /* renamed from: a */
        void m1605a(NumberPicker numberPicker, int i);
    }

    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$g */
    /* loaded from: classes.dex */
    public interface InterfaceC2216g {
        /* renamed from: a */
        void m1604a(NumberPicker numberPicker, int i, int i2);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, byte] */
    /* renamed from: a */
    static /* synthetic */ boolean m1669a(NumberPicker numberPicker, int i) {
        byte r0 = (byte) ((numberPicker.f7785ab ? 1 : 0) ^ i);
        numberPicker.f7785ab = r0 == 0x01;
        return r0 == 0x01;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, byte] */
    /* renamed from: b */
    static /* synthetic */ boolean m1654b(NumberPicker numberPicker, int i) {
        byte r0 = (byte) ((numberPicker.f7786ac ? 1 : 0) ^ i);
        numberPicker.f7786ac = r0 == 0x01;
        return r0 == 0x01;
    }

    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$k */
    /* loaded from: classes.dex */
    private static class C2220k implements InterfaceC2213d {

        /* renamed from: b */
        char f7837b;

        /* renamed from: c */
        Formatter f7838c;

        /* renamed from: a */
        final StringBuilder f7836a = new StringBuilder();

        /* renamed from: d */
        final Object[] f7839d = new Object[1];

        C2220k() {
            m1595a(Locale.getDefault());
        }

        /* renamed from: a */
        private void m1595a(Locale locale) {
            this.f7838c = m1593c(locale);
            this.f7837b = m1594b(locale);
        }

        @Override // com.sds.android.ttpod.widget.NumberPicker.InterfaceC2213d
        /* renamed from: a */
        public String mo1596a(int i) {
            Locale locale = Locale.getDefault();
            if (this.f7837b != m1594b(locale)) {
                m1595a(locale);
            }
            this.f7839d[0] = Integer.valueOf(i);
            this.f7836a.delete(0, this.f7836a.length());
            this.f7838c.format("%02d", this.f7839d);
            return this.f7838c.toString();
        }

        /* renamed from: b */
        private static char m1594b(Locale locale) {
            return new DecimalFormatSymbols(locale).getZeroDigit();
        }

        /* renamed from: c */
        private Formatter m1593c(Locale locale) {
            return new Formatter(this.f7836a, locale);
        }
    }

    public static final InterfaceC2213d getTwoDigitFormatter() {
        return f7758a;
    }

    public NumberPicker(Context context) {
        this(context, null);
    }

    public NumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.numberPickerStyle);
    }

    @SuppressLint("ResourceType")
    public NumberPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.f7808t = 300L;
        this.f7809u = new SparseArray<>();
        this.f7810v = new int[3];
        this.f7814z = Integer.MIN_VALUE;
        this.f7779T = 0;
        this.f7789af = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NumberPicker, i, 0);
        @SuppressLint("ResourceType") int internalLayout = obtainStyledAttributes.getResourceId(R.styleable.NumberPicker_internalLayout, 0);
        this.f7776Q = internalLayout != 0;
        this.solidColor = obtainStyledAttributes.getColor(R.styleable.NumberPicker_solidColor, 0);
        this.selectionDivider = obtainStyledAttributes.getDrawable(R.styleable.NumberPicker_selectionDivider);
        this.selectionDividerHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_selectionDividerHeight, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.0f, getResources().getDisplayMetrics()));
        this.selectionDividersDistance = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_selectionDividersDistance, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48.0f, getResources().getDisplayMetrics()));
        this.internalMinHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_internalMinHeight, -1);
        this.internalMaxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_internalMaxHeight, -1);
        if (this.internalMinHeight != -1 && this.internalMaxHeight != -1 && this.internalMinHeight > this.internalMaxHeight) {
            throw new IllegalArgumentException("minHeight > maxHeight");
        }
        this.internalMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_internalMinWidth, -1);
        this.internalMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NumberPicker_internalMaxWidth, -1);
        if (this.internalMinWidth != -1 && this.internalMaxWidth != -1 && this.internalMinWidth > this.internalMaxWidth) {
            throw new IllegalArgumentException("minWidth > maxWidth");
        }
        this.f7798j = this.internalMaxWidth == -1;
        this.virtualButtonPressedDrawable = obtainStyledAttributes.getDrawable(R.styleable.NumberPicker_virtualButtonPressedDrawable);
        obtainStyledAttributes.recycle();
        this.f7788ae = new RunnableC2217h();
        setWillNotDraw(!this.f7776Q);
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(internalLayout, (ViewGroup) this, true);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.NumberPicker.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NumberPicker.this.m1645d();
                NumberPicker.this.f7792d.clearFocus();
                if (view.getId() == R.id.np__increment) {
                    NumberPicker.this.m1663a(true);
                } else {
                    NumberPicker.this.m1663a(false);
                }
            }
        };
        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() { // from class: com.sds.android.ttpod.widget.NumberPicker.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                NumberPicker.this.m1645d();
                NumberPicker.this.f7792d.clearFocus();
                if (view.getId() == R.id.np__increment) {
                    NumberPicker.this.m1662a(true, 0L);
                } else {
                    NumberPicker.this.m1662a(false, 0L);
                }
                return true;
            }
        };
        if (!this.f7776Q) {
            this.f7790b = (ImageButton) findViewById(R.id.np__increment);
            this.f7790b.setOnClickListener(onClickListener);
            this.f7790b.setOnLongClickListener(onLongClickListener);
        } else {
            this.f7790b = null;
        }
        if (!this.f7776Q) {
            this.f7791c = (ImageButton) findViewById(R.id.np__decrement);
            this.f7791c.setOnClickListener(onClickListener);
            this.f7791c.setOnLongClickListener(onLongClickListener);
        } else {
            this.f7791c = null;
        }
        this.f7792d = (EditText) findViewById(R.id.np__numberpicker_input);
        this.f7792d.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sds.android.ttpod.widget.NumberPicker.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    NumberPicker.this.f7792d.selectAll();
                } else {
                    NumberPicker.this.m1677a();
                }
            }
        });
        this.f7792d.setFilters(new InputFilter[]{new C2214e()});
        this.f7792d.setRawInputType(2);
        this.f7792d.setImeOptions(6);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f7771L = viewConfiguration.getScaledTouchSlop();
        this.f7772M = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f7773N = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
        this.f7799k = (int) this.f7792d.getTextSize();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(this.f7799k);
        paint.setTypeface(this.f7792d.getTypeface());
        paint.setColor(this.f7792d.getTextColors().getColorForState(ENABLED_STATE_SET, -1));
        this.f7811w = paint;
        if (Build.VERSION.SDK_INT >= 11) {
            this.f7761B = new Scroller(getContext(), null, true);
        } else {
            this.f7761B = new Scroller(getContext(), null);
        }
        this.f7762C = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
        m1630i();
        if (Build.VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    /* renamed from: a */
    public void m1677a() {
        this.f7792d.setSelection(0, 0);
        m1672a(this.f7792d);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!this.f7776Q) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth2 = this.f7792d.getMeasuredWidth();
        int measuredHeight2 = this.f7792d.getMeasuredHeight();
        int i5 = (measuredWidth - measuredWidth2) / 2;
        int i6 = (measuredHeight - measuredHeight2) / 2;
        this.f7792d.layout(i5, i6, measuredWidth2 + i5, measuredHeight2 + i6);
        if (z) {
            m1634g();
            m1632h();
            this.f7782W = ((getHeight() - this.selectionDividersDistance) / 2) - this.selectionDividerHeight;
            this.f7783Z = this.f7782W + (this.selectionDividerHeight * 2) + this.selectionDividersDistance;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (!this.f7776Q) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(m1675a(i, this.internalMaxWidth), m1675a(i2, this.internalMaxHeight));
        setMeasuredDimension(m1657b(this.internalMinWidth, getMeasuredWidth(), i), m1657b(this.internalMinHeight, getMeasuredHeight(), i2));
    }

    /* renamed from: a */
    private boolean m1671a(Scroller scroller) {
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i = this.f7814z - ((this.f7760A + finalY) % this.f7813y);
        if (i != 0) {
            if (Math.abs(i) > this.f7813y / 2) {
                if (i > 0) {
                    i -= this.f7813y;
                } else {
                    i += this.f7813y;
                }
            }
            scrollBy(0, i + finalY);
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f7776Q && isEnabled()) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    m1622m();
                    this.f7792d.setVisibility(View.INVISIBLE);
                    float y = motionEvent.getY();
                    this.f7767H = y;
                    this.f7769J = y;
                    this.f7768I = motionEvent.getEventTime();
                    this.f7780U = false;
                    this.f7781V = false;
                    if (this.f7767H < this.f7782W) {
                        if (this.f7779T == 0) {
                            this.f7788ae.m1602a(2);
                        }
                    } else if (this.f7767H > this.f7783Z && this.f7779T == 0) {
                        this.f7788ae.m1602a(1);
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                    if (!this.f7761B.isFinished()) {
                        this.f7761B.forceFinished(true);
                        this.f7762C.forceFinished(true);
                        m1676a(0);
                        return true;
                    } else if (!this.f7762C.isFinished()) {
                        this.f7761B.forceFinished(true);
                        this.f7762C.forceFinished(true);
                        return true;
                    } else if (this.f7767H < this.f7782W) {
                        m1645d();
                        m1662a(false, ViewConfiguration.getLongPressTimeout());
                        return true;
                    } else if (this.f7767H > this.f7783Z) {
                        m1645d();
                        m1662a(true, ViewConfiguration.getLongPressTimeout());
                        return true;
                    } else {
                        this.f7781V = true;
                        m1626k();
                        return true;
                    }
                default:
                    return false;
            }
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.f7776Q) {
            if (this.f7770K == null) {
                this.f7770K = VelocityTracker.obtain();
            }
            this.f7770K.addMovement(motionEvent);
            switch (motionEvent.getAction() & 255) {
                case 1:
                    m1624l();
                    m1628j();
                    this.f7788ae.m1603a();
                    VelocityTracker velocityTracker = this.f7770K;
                    velocityTracker.computeCurrentVelocity(1000, this.f7773N);
                    int yVelocity = (int) velocityTracker.getYVelocity();
                    if (Math.abs(yVelocity) > this.f7772M) {
                        m1659b(yVelocity);
                        m1676a(2);
                    } else {
                        int y = (int) motionEvent.getY();
                        int abs = (int) Math.abs(y - this.f7767H);
                        long eventTime = motionEvent.getEventTime() - this.f7768I;
                        ViewConfiguration.getTapTimeout();
                        if (abs <= this.f7771L) {
                            if (this.f7781V) {
                                this.f7781V = false;
                                m1651c();
                            } else {
                                int i = (y / this.f7813y) - 1;
                                if (i > 0) {
                                    m1663a(true);
                                    this.f7788ae.m1601b(1);
                                } else if (i < 0) {
                                    m1663a(false);
                                    this.f7788ae.m1601b(2);
                                }
                            }
                        } else {
                            m1620n();
                        }
                        m1676a(0);
                    }
                    this.f7770K.recycle();
                    this.f7770K = null;
                    return true;
                case 2:
                    if (this.f7780U) {
                        return true;
                    }
                    float y2 = motionEvent.getY();
                    if (this.f7779T != 1) {
                        if (((int) Math.abs(y2 - this.f7767H)) > this.f7771L) {
                            m1622m();
                            m1676a(1);
                        }
                    } else {
                        scrollBy(0, (int) (y2 - this.f7769J));
                        invalidate();
                    }
                    this.f7769J = y2;
                    return true;
                default:
                    return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 1:
            case 3:
                m1622m();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {
            case 19:
            case 20:
                if (this.f7776Q) {
                    switch (keyEvent.getAction()) {
                        case 0:
                            if (this.f7774O || keyCode == 20 ? getValue() < getMaxValue() : getValue() > getMinValue()) {
                                requestFocus();
                                this.f7789af = keyCode;
                                m1622m();
                                if (this.f7761B.isFinished()) {
                                    m1663a(keyCode == 20);
                                    return true;
                                }
                                return true;
                            }
                            break;
                        case 1:
                            if (this.f7789af == keyCode) {
                                this.f7789af = -1;
                                return true;
                            }
                            break;
                    }
                }
                break;
            case 23:
            case 66:
                m1622m();
                break;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 1:
            case 3:
                m1622m();
                break;
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i;
        if (!this.f7776Q) {
            return super.dispatchHoverEvent(motionEvent);
        }
        if (((AccessibilityManager) getContext().getSystemService(Context.ACCESSIBILITY_SERVICE)).isEnabled()) {
            int y = (int) motionEvent.getY();
            if (y < this.f7782W) {
                i = 3;
            } else if (y > this.f7783Z) {
                i = 1;
            } else {
                i = 2;
            }
            int action = motionEvent.getAction() & 255;
            C2219j supportAccessibilityNodeProvider = getSupportAccessibilityNodeProvider();
            switch (action) {
                case 7:
                    if (this.f7784aa != i && this.f7784aa != -1) {
                        supportAccessibilityNodeProvider.m1598a(this.f7784aa, 256);
                        supportAccessibilityNodeProvider.m1598a(i, 128);
                        this.f7784aa = i;
                        supportAccessibilityNodeProvider.m1597a(i, 64, null);
                        break;
                    }
                    break;
                case 9:
                    supportAccessibilityNodeProvider.m1598a(i, 128);
                    this.f7784aa = i;
                    supportAccessibilityNodeProvider.m1597a(i, 64, null);
                    break;
                case 10:
                    supportAccessibilityNodeProvider.m1598a(i, 256);
                    this.f7784aa = -1;
                    break;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.f7761B;
        if (scroller.isFinished()) {
            scroller = this.f7762C;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (this.f7763D == 0) {
            this.f7763D = scroller.getStartY();
        }
        scrollBy(0, currY - this.f7763D);
        this.f7763D = currY;
        if (scroller.isFinished()) {
            m1656b(scroller);
        } else {
            invalidate();
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!this.f7776Q) {
            this.f7790b.setEnabled(z);
        }
        if (!this.f7776Q) {
            this.f7791c.setEnabled(z);
        }
        this.f7792d.setEnabled(z);
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        int[] iArr = this.f7810v;
        if (!this.f7774O && i2 > 0 && iArr[1] <= this.f7802n) {
            this.f7760A = this.f7814z;
        } else if (!this.f7774O && i2 < 0 && iArr[1] >= this.f7803o) {
            this.f7760A = this.f7814z;
        } else {
            this.f7760A += i2;
            while (this.f7760A - this.f7814z > this.f7800l) {
                this.f7760A -= this.f7813y;
                m1652b(iArr);
                m1673a(iArr[1], true);
                if (!this.f7774O && iArr[1] <= this.f7802n) {
                    this.f7760A = this.f7814z;
                }
            }
            while (this.f7760A - this.f7814z < (-this.f7800l)) {
                this.f7760A += this.f7813y;
                m1661a(iArr);
                m1673a(iArr[1], true);
                if (!this.f7774O && iArr[1] >= this.f7803o) {
                    this.f7760A = this.f7814z;
                }
            }
        }
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.solidColor;
    }

    public void setOnValueChangedListener(InterfaceC2216g interfaceC2216g) {
        this.f7805q = interfaceC2216g;
    }

    public void setOnScrollListener(InterfaceC2215f interfaceC2215f) {
        this.f7806r = interfaceC2215f;
    }

    public void setFormatter(InterfaceC2213d interfaceC2213d) {
        if (interfaceC2213d != this.f7807s) {
            this.f7807s = interfaceC2213d;
            m1637f();
            m1630i();
        }
    }

    public void setValue(int i) {
        m1673a(i, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m1651c() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            if (this.f7776Q) {
                this.f7792d.setVisibility(View.VISIBLE);
            }
            this.f7792d.requestFocus();
            inputMethodManager.showSoftInput(this.f7792d, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m1645d() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive(this.f7792d)) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            if (this.f7776Q) {
                this.f7792d.setVisibility(View.INVISIBLE);
            }
        }
    }

    /* renamed from: e */
    private void m1640e() {
        int i;
        int i2 = 0;
        if (this.f7798j) {
            if (this.f7801m == null) {
                float f = 0.0f;
                int i3 = 0;
                while (i3 <= 9) {
                    float measureText = this.f7811w.measureText(m1636f(i3));
                    if (measureText <= f) {
                        measureText = f;
                    }
                    i3++;
                    f = measureText;
                }
                for (int i4 = this.f7803o; i4 > 0; i4 /= 10) {
                    i2++;
                }
                i = (int) (i2 * f);
            } else {
                int length = this.f7801m.length;
                i = 0;
                for (int i5 = 0; i5 < length; i5++) {
                    float measureText2 = this.f7811w.measureText(this.f7801m[i5]);
                    if (measureText2 > i) {
                        i = (int) measureText2;
                    }
                }
            }
            int paddingLeft = i + this.f7792d.getPaddingLeft() + this.f7792d.getPaddingRight();
            if (this.internalMaxWidth != paddingLeft) {
                if (paddingLeft > this.internalMinWidth) {
                    this.internalMaxWidth = paddingLeft;
                } else {
                    this.internalMaxWidth = this.internalMinWidth;
                }
                invalidate();
            }
        }
    }

    public boolean getWrapSelectorWheel() {
        return this.f7774O;
    }

    public void setWrapSelectorWheel(boolean z) {
        boolean z2 = this.f7803o - this.f7802n >= this.f7810v.length;
        if ((!z || z2) && z != this.f7774O) {
            this.f7774O = z;
        }
    }

    public void setOnLongPressUpdateInterval(long j) {
        this.f7808t = j;
    }

    public int getValue() {
        return this.f7804p;
    }

    public int getMinValue() {
        return this.f7802n;
    }

    public void setMinValue(int i) {
        if (this.f7802n != i) {
            if (i < 0) {
                throw new IllegalArgumentException("minValue must be >= 0");
            }
            this.f7802n = i;
            if (this.f7802n > this.f7804p) {
                this.f7804p = this.f7802n;
            }
            setWrapSelectorWheel(this.f7803o - this.f7802n > this.f7810v.length);
            m1637f();
            m1630i();
            m1640e();
            invalidate();
        }
    }

    public int getMaxValue() {
        return this.f7803o;
    }

    public void setMaxValue(int i) {
        if (this.f7803o != i) {
            if (i < 0) {
                throw new IllegalArgumentException("maxValue must be >= 0");
            }
            this.f7803o = i;
            if (this.f7803o < this.f7804p) {
                this.f7804p = this.f7803o;
            }
            setWrapSelectorWheel(this.f7803o - this.f7802n > this.f7810v.length);
            m1637f();
            m1630i();
            m1640e();
            invalidate();
        }
    }

    public String[] getDisplayedValues() {
        return this.f7801m;
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.f7801m != strArr) {
            this.f7801m = strArr;
            if (this.f7801m != null) {
                this.f7792d.setRawInputType(524289);
            } else {
                this.f7792d.setRawInputType(2);
            }
            m1630i();
            m1637f();
            m1640e();
        }
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m1622m();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        if (!this.f7776Q) {
            super.onDraw(canvas);
            return;
        }
        float right = (getRight() - getLeft()) / 2;
        float f = this.f7760A;
        if (this.virtualButtonPressedDrawable != null && this.f7779T == 0) {
            if (this.f7786ac) {
                this.virtualButtonPressedDrawable.setState(PRESSED_ENABLED_STATE_SET);
                this.virtualButtonPressedDrawable.setBounds(0, 0, getRight(), this.f7782W);
                this.virtualButtonPressedDrawable.draw(canvas);
            }
            if (this.f7785ab) {
                this.virtualButtonPressedDrawable.setState(PRESSED_ENABLED_STATE_SET);
                this.virtualButtonPressedDrawable.setBounds(0, this.f7783Z, getRight(), getBottom());
                this.virtualButtonPressedDrawable.draw(canvas);
            }
        }
        int[] iArr = this.f7810v;
        float f2 = f;
        for (int i = 0; i < iArr.length; i++) {
            String str = this.f7809u.get(iArr[i]);
            if (i != 1 || this.f7792d.getVisibility() != View.VISIBLE) {
                canvas.drawText(str, right, f2, this.f7811w);
            }
            f2 += this.f7813y;
        }
        if (this.selectionDivider != null) {
            int i2 = this.f7782W;
            this.selectionDivider.setBounds(0, i2, getRight(), this.selectionDividerHeight + i2);
            this.selectionDivider.draw(canvas);
            int i3 = this.f7783Z;
            this.selectionDivider.setBounds(0, i3 - this.selectionDividerHeight, getRight(), i3);
            this.selectionDivider.draw(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(NumberPicker.class.getName());
        accessibilityEvent.setScrollable(true);
        accessibilityEvent.setScrollY((this.f7802n + this.f7804p) * this.f7813y);
        accessibilityEvent.setMaxScrollY((this.f7803o - this.f7802n) * this.f7813y);
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (!this.f7776Q) {
            return super.getAccessibilityNodeProvider();
        }
        if (this.f7787ad == null) {
            this.f7787ad = new C2219j();
        }
        return this.f7787ad.f7834a;
    }

    /* renamed from: a */
    private int m1675a(int i, int i2) {
        if (i2 != -1) {
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            switch (mode) {
                case Integer.MIN_VALUE /* -2147483648 */:
                    return View.MeasureSpec.makeMeasureSpec(Math.min(size, i2), MeasureSpec.EXACTLY);
                case 0:
                    return View.MeasureSpec.makeMeasureSpec(i2, MeasureSpec.EXACTLY);
                case 1073741824:
                    return i;
                default:
                    throw new IllegalArgumentException("Unknown measure mode: " + mode);
            }
        }
        return i;
    }

    /* renamed from: b */
    private int m1657b(int i, int i2, int i3) {
        if (i != -1) {
            return m1674a(Math.max(i, i2), i3, 0);
        }
        return i2;
    }

    /* renamed from: a */
    public static int m1674a(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        switch (mode) {
            case Integer.MIN_VALUE /* -2147483648 */:
                if (size < i) {
                    i = size | 16777216;
                    break;
                }
                break;
            case 1073741824:
                i = size;
                break;
        }
        return ((-16777216) & i3) | i;
    }

    /* renamed from: f */
    private void m1637f() {
        this.f7809u.clear();
        int[] iArr = this.f7810v;
        int value = getValue();
        for (int i = 0; i < this.f7810v.length; i++) {
            int i2 = (i - 1) + value;
            if (this.f7774O) {
                i2 = m1650c(i2);
            }
            iArr[i] = i2;
            m1644d(iArr[i]);
        }
    }

    /* renamed from: a */
    private void m1673a(int i, boolean z) {
        int min;
        if (this.f7804p != i) {
            if (this.f7774O) {
                min = m1650c(i);
            } else {
                min = Math.min(Math.max(i, this.f7802n), this.f7803o);
            }
            int i2 = this.f7804p;
            this.f7804p = min;
            m1630i();
            if (z) {
                m1658b(i2, min);
            }
            m1637f();
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1663a(boolean z) {
        if (this.f7776Q) {
            this.f7792d.setVisibility(View.INVISIBLE);
            if (!m1671a(this.f7761B)) {
                m1671a(this.f7762C);
            }
            this.f7763D = 0;
            if (z) {
                this.f7761B.startScroll(0, 0, 0, -this.f7813y, 300);
            } else {
                this.f7761B.startScroll(0, 0, 0, this.f7813y, 300);
            }
            invalidate();
        } else if (z) {
            m1673a(this.f7804p + 1, true);
        } else {
            m1673a(this.f7804p - 1, true);
        }
    }

    /* renamed from: g */
    private void m1634g() {
        m1637f();
        int[] iArr = this.f7810v;
        this.f7800l = (int) ((((getBottom() - getTop()) - (iArr.length * this.f7799k)) / iArr.length) + 0.5f);
        this.f7813y = this.f7799k + this.f7800l;
        this.f7814z = (this.f7792d.getBaseline() + this.f7792d.getTop()) - (this.f7813y * 1);
        this.f7760A = this.f7814z;
        m1630i();
    }

    /* renamed from: h */
    private void m1632h() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.f7799k) / 2);
    }

    /* renamed from: b */
    private void m1656b(Scroller scroller) {
        if (scroller == this.f7761B) {
            if (!m1620n()) {
                m1630i();
            }
            m1676a(0);
        } else if (this.f7779T != 1) {
            m1630i();
        }
    }

    /* renamed from: a */
    private void m1676a(int i) {
        if (this.f7779T != i) {
            this.f7779T = i;
            if (this.f7806r != null) {
                this.f7806r.m1605a(this, i);
            }
        }
    }

    /* renamed from: b */
    private void m1659b(int i) {
        this.f7763D = 0;
        if (i > 0) {
            this.f7761B.fling(0, 0, 0, i, 0, 0, 0, VIPPolicy.Entry.MAX_LIMIT);
        } else {
            this.f7761B.fling(0, VIPPolicy.Entry.MAX_LIMIT, 0, i, 0, 0, 0, VIPPolicy.Entry.MAX_LIMIT);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public int m1650c(int i) {
        if (i > this.f7803o) {
            return (this.f7802n + ((i - this.f7803o) % (this.f7803o - this.f7802n))) - 1;
        }
        if (i < this.f7802n) {
            return (this.f7803o - ((this.f7802n - i) % (this.f7803o - this.f7802n))) + 1;
        }
        return i;
    }

    /* renamed from: a */
    private void m1661a(int[] iArr) {
        for (int i = 0; i < iArr.length - 1; i++) {
            iArr[i] = iArr[i + 1];
        }
        int i2 = iArr[iArr.length - 2] + 1;
        if (this.f7774O && i2 > this.f7803o) {
            i2 = this.f7802n;
        }
        iArr[iArr.length - 1] = i2;
        m1644d(i2);
    }

    /* renamed from: b */
    private void m1652b(int[] iArr) {
        for (int length = iArr.length - 1; length > 0; length--) {
            iArr[length] = iArr[length - 1];
        }
        int i = iArr[1] - 1;
        if (this.f7774O && i < this.f7802n) {
            i = this.f7803o;
        }
        iArr[0] = i;
        m1644d(i);
    }

    /* renamed from: d */
    private void m1644d(int i) {
        String str;
        SparseArray<String> sparseArray = this.f7809u;
        if (sparseArray.get(i) == null) {
            if (i < this.f7802n || i > this.f7803o) {
                str = "";
            } else if (this.f7801m != null) {
                str = this.f7801m[i - this.f7802n];
            } else {
                str = m1639e(i);
            }
            sparseArray.put(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public String m1639e(int i) {
        return this.f7807s != null ? this.f7807s.mo1596a(i) : m1636f(i);
    }

    /* renamed from: a */
    private void m1672a(View view) {
        String valueOf = String.valueOf(((TextView) view).getText());
        if (TextUtils.isEmpty(valueOf)) {
            m1630i();
        } else {
            m1673a(m1664a(valueOf.toString()), true);
        }
    }

    /* renamed from: i */
    private boolean m1630i() {
        String m1639e = this.f7801m == null ? m1639e(this.f7804p) : this.f7801m[this.f7804p - this.f7802n];
        if (!TextUtils.isEmpty(m1639e) && !m1639e.equals(this.f7792d.getText().toString())) {
            this.f7792d.setText(m1639e);
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private void m1658b(int i, int i2) {
        if (this.f7805q != null) {
            this.f7805q.m1604a(this, i, this.f7804p);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1662a(boolean z, long j) {
        if (this.f7765F == null) {
            this.f7765F = new RunnableC2212c();
        } else {
            removeCallbacks(this.f7765F);
        }
        this.f7765F.m1606a(z);
        postDelayed(this.f7765F, j);
    }

    /* renamed from: j */
    private void m1628j() {
        if (this.f7765F != null) {
            removeCallbacks(this.f7765F);
        }
    }

    /* renamed from: k */
    private void m1626k() {
        if (this.f7766G == null) {
            this.f7766G = new RunnableC2211b();
        } else {
            removeCallbacks(this.f7766G);
        }
        postDelayed(this.f7766G, ViewConfiguration.getLongPressTimeout());
    }

    /* renamed from: l */
    private void m1624l() {
        if (this.f7766G != null) {
            removeCallbacks(this.f7766G);
        }
    }

    /* renamed from: m */
    private void m1622m() {
        if (this.f7765F != null) {
            removeCallbacks(this.f7765F);
        }
        if (this.f7764E != null) {
            removeCallbacks(this.f7764E);
        }
        if (this.f7766G != null) {
            removeCallbacks(this.f7766G);
        }
        this.f7788ae.m1603a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int m1664a(String str) {
        if (this.f7801m == null) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
            }
        } else {
            for (int i = 0; i < this.f7801m.length; i++) {
                str = str.toLowerCase();
                if (this.f7801m[i].toLowerCase().startsWith(str)) {
                    return i + this.f7802n;
                }
            }
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e2) {
            }
        }
        return this.f7802n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m1649c(int i, int i2) {
        if (this.f7764E == null) {
            this.f7764E = new RunnableC2218i();
        } else {
            removeCallbacks(this.f7764E);
        }
        this.f7764E.f7832b = i;
        this.f7764E.f7833c = i2;
        post(this.f7764E);
    }

    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$e */
    /* loaded from: classes.dex */
    class C2214e extends NumberKeyListener {
        C2214e() {
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 1;
        }

        @Override // android.text.method.NumberKeyListener
        protected char[] getAcceptedChars() {
            return NumberPicker.f7759ag;
        }

        @Override // android.text.method.NumberKeyListener, android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            String valueOf = null;
            String[] strArr;
            if (NumberPicker.this.f7801m == null) {
                CharSequence filter = super.filter(charSequence, i, i2, spanned, i3, i4);
                if (filter == null) {
                    filter = charSequence.subSequence(i, i2);
                }
                String str = String.valueOf(spanned.subSequence(0, i3)) + ((Object) filter) + ((Object) spanned.subSequence(i4, spanned.length()));
                if ("".equals(str)) {
                    return str;
                }
                if (NumberPicker.this.m1664a(str) > NumberPicker.this.f7803o) {
                    return "";
                }
                return filter;
            }
            if (TextUtils.isEmpty(String.valueOf(charSequence.subSequence(i, i2)))) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i3)) + ((Object) valueOf) + ((Object) spanned.subSequence(i4, spanned.length()));
            String lowerCase = String.valueOf(str2).toLowerCase();
            for (String str3 : NumberPicker.this.f7801m) {
                if (str3.toLowerCase().startsWith(lowerCase)) {
                    NumberPicker.this.m1649c(str2.length(), str3.length());
                    return str3.subSequence(i3, str3.length());
                }
            }
            return "";
        }
    }

    /* renamed from: n */
    private boolean m1620n() {
        int i = this.f7814z - this.f7760A;
        if (i != 0) {
            this.f7763D = 0;
            if (Math.abs(i) > this.f7813y / 2) {
                i += i > 0 ? -this.f7813y : this.f7813y;
            }
            this.f7762C.startScroll(0, 0, 0, i, 800);
            invalidate();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$h */
    /* loaded from: classes.dex */
    public class RunnableC2217h implements Runnable {

        /* renamed from: b */
        private final int f7827b = 1;

        /* renamed from: c */
        private final int f7828c = 2;

        /* renamed from: d */
        private int f7829d;

        /* renamed from: e */
        private int f7830e;

        RunnableC2217h() {
        }

        /* renamed from: a */
        public void m1603a() {
            this.f7830e = 0;
            this.f7829d = 0;
            NumberPicker.this.removeCallbacks(this);
            if (NumberPicker.this.f7785ab) {
                NumberPicker.this.f7785ab = false;
                NumberPicker.this.invalidate(0, NumberPicker.this.f7783Z, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
            }
            NumberPicker.this.f7786ac = false;
            if (NumberPicker.this.f7786ac) {
                NumberPicker.this.invalidate(0, 0, NumberPicker.this.getRight(), NumberPicker.this.f7782W);
            }
        }

        /* renamed from: a */
        public void m1602a(int i) {
            m1603a();
            this.f7830e = 1;
            this.f7829d = i;
            NumberPicker.this.postDelayed(this, ViewConfiguration.getTapTimeout());
        }

        /* renamed from: b */
        public void m1601b(int i) {
            m1603a();
            this.f7830e = 2;
            this.f7829d = i;
            NumberPicker.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            switch (this.f7830e) {
                case 1:
                    switch (this.f7829d) {
                        case 1:
                            NumberPicker.this.f7785ab = true;
                            NumberPicker.this.invalidate(0, NumberPicker.this.f7783Z, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                            return;
                        case 2:
                            NumberPicker.this.f7786ac = true;
                            NumberPicker.this.invalidate(0, 0, NumberPicker.this.getRight(), NumberPicker.this.f7782W);
                            return;
                        default:
                            return;
                    }
                case 2:
                    switch (this.f7829d) {
                        case 1:
                            if (!NumberPicker.this.f7785ab) {
                                NumberPicker.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
                            }
                            NumberPicker.m1669a(NumberPicker.this, 1);
                            NumberPicker.this.invalidate(0, NumberPicker.this.f7783Z, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                            return;
                        case 2:
                            if (!NumberPicker.this.f7786ac) {
                                NumberPicker.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
                            }
                            NumberPicker.m1654b(NumberPicker.this, 1);
                            NumberPicker.this.invalidate(0, 0, NumberPicker.this.getRight(), NumberPicker.this.f7782W);
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$i */
    /* loaded from: classes.dex */
    public class RunnableC2218i implements Runnable {

        /* renamed from: b */
        private int f7832b;

        /* renamed from: c */
        private int f7833c;

        RunnableC2218i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.f7792d.setSelection(this.f7832b, this.f7833c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$c */
    /* loaded from: classes.dex */
    public class RunnableC2212c implements Runnable {

        /* renamed from: b */
        private boolean f7824b;

        RunnableC2212c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m1606a(boolean z) {
            this.f7824b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.m1663a(this.f7824b);
            NumberPicker.this.postDelayed(this, NumberPicker.this.f7808t);
        }
    }

    /* loaded from: classes.dex */
    public static class CustomEditText extends androidx.appcompat.widget.AppCompatEditText {
        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // android.widget.TextView
        public void onEditorAction(int i) {
            super.onEditorAction(i);
            if (i == 6) {
                clearFocus();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$b */
    /* loaded from: classes.dex */
    public class RunnableC2211b implements Runnable {
        RunnableC2211b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.m1651c();
            NumberPicker.this.f7780U = true;
        }
    }

    private C2219j getSupportAccessibilityNodeProvider() {
        return new C2219j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$j */
    /* loaded from: classes.dex */
    public class C2219j {

        /* renamed from: a */
        C2210a f7834a;

        private C2219j() {
            if (Build.VERSION.SDK_INT >= 16) {
                this.f7834a = new C2210a();
            }
        }

        /* renamed from: a */
        public boolean m1597a(int i, int i2, Bundle bundle) {
            if (this.f7834a != null) {
                return this.f7834a.performAction(i, i2, bundle);
            }
            return false;
        }

        /* renamed from: a */
        public void m1598a(int i, int i2) {
            if (this.f7834a != null) {
                this.f7834a.m1616a(i, i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.widget.NumberPicker$a */
    /* loaded from: classes.dex */
    public class C2210a extends AccessibilityNodeProvider {

        /* renamed from: b */
        private final Rect f7819b = new Rect();

        /* renamed from: c */
        private final int[] f7820c = new int[2];

        /* renamed from: d */
        private int f7821d = Integer.MIN_VALUE;

        C2210a() {
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            switch (i) {
                case -1:
                    return m1615a(NumberPicker.this.getScrollX(), NumberPicker.this.getScrollY(), NumberPicker.this.getScrollX() + (NumberPicker.this.getRight() - NumberPicker.this.getLeft()), NumberPicker.this.getScrollY() + (NumberPicker.this.getBottom() - NumberPicker.this.getTop()));
                case 0:
                default:
                    return super.createAccessibilityNodeInfo(i);
                case 1:
                    return m1613a(1, m1608e(), NumberPicker.this.getScrollX(), NumberPicker.this.f7783Z - NumberPicker.this.selectionDividerHeight, (NumberPicker.this.getRight() - NumberPicker.this.getLeft()) + NumberPicker.this.getScrollX(), (NumberPicker.this.getBottom() - NumberPicker.this.getTop()) + NumberPicker.this.getScrollY());
                case 2:
                    return m1618a();
                case 3:
                    return m1613a(3, m1609d(), NumberPicker.this.getScrollX(), NumberPicker.this.getScrollY(), (NumberPicker.this.getRight() - NumberPicker.this.getLeft()) + NumberPicker.this.getScrollX(), NumberPicker.this.selectionDividerHeight + NumberPicker.this.f7782W);
            }
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            if (TextUtils.isEmpty(str)) {
                return Collections.emptyList();
            }
            String lowerCase = str.toLowerCase();
            ArrayList arrayList = new ArrayList();
            switch (i) {
                case -1:
                    m1612a(lowerCase, 3, arrayList);
                    m1612a(lowerCase, 2, arrayList);
                    m1612a(lowerCase, 1, arrayList);
                    return arrayList;
                case 0:
                default:
                    return super.findAccessibilityNodeInfosByText(str, i);
                case 1:
                case 2:
                case 3:
                    m1612a(lowerCase, i, arrayList);
                    return arrayList;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            switch (i) {
                case -1:
                    switch (i2) {
                        case 64:
                            if (this.f7821d != i) {
                                this.f7821d = i;
                                NumberPicker.this.performAccessibilityAction(64, null);
                                return true;
                            }
                            return false;
                        case 128:
                            if (this.f7821d == i) {
                                this.f7821d = Integer.MIN_VALUE;
                                NumberPicker.this.performAccessibilityAction(128, null);
                                return true;
                            }
                            return false;
                        case 4096:
                            if (NumberPicker.this.isEnabled()) {
                                if (NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() < NumberPicker.this.getMaxValue()) {
                                    NumberPicker.this.m1663a(true);
                                    return true;
                                }
                                return false;
                            }
                            return false;
                        case 8192:
                            if (NumberPicker.this.isEnabled()) {
                                if (NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() > NumberPicker.this.getMinValue()) {
                                    NumberPicker.this.m1663a(false);
                                    return true;
                                }
                                return false;
                            }
                            return false;
                    }
                case 1:
                    switch (i2) {
                        case 16:
                            if (NumberPicker.this.isEnabled()) {
                                NumberPicker.this.m1663a(true);
                                m1616a(i, 1);
                                return true;
                            }
                            return false;
                        case 64:
                            if (this.f7821d != i) {
                                this.f7821d = i;
                                m1616a(i, 32768);
                                NumberPicker.this.invalidate(0, NumberPicker.this.f7783Z, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                                return true;
                            }
                            return false;
                        case 128:
                            if (this.f7821d == i) {
                                this.f7821d = Integer.MIN_VALUE;
                                m1616a(i, 65536);
                                NumberPicker.this.invalidate(0, NumberPicker.this.f7783Z, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                                return true;
                            }
                            return false;
                        default:
                            return false;
                    }
                case 2:
                    switch (i2) {
                        case 1:
                            if (!NumberPicker.this.isEnabled() || NumberPicker.this.f7792d.isFocused()) {
                                return false;
                            }
                            return NumberPicker.this.f7792d.requestFocus();
                        case 2:
                            if (NumberPicker.this.isEnabled() && NumberPicker.this.f7792d.isFocused()) {
                                NumberPicker.this.f7792d.clearFocus();
                                return true;
                            }
                            return false;
                        case 16:
                            if (NumberPicker.this.isEnabled()) {
                                NumberPicker.this.m1651c();
                                return true;
                            }
                            return false;
                        case 64:
                            if (this.f7821d != i) {
                                this.f7821d = i;
                                m1616a(i, 32768);
                                NumberPicker.this.f7792d.invalidate();
                                return true;
                            }
                            return false;
                        case 128:
                            if (this.f7821d == i) {
                                this.f7821d = Integer.MIN_VALUE;
                                m1616a(i, 65536);
                                NumberPicker.this.f7792d.invalidate();
                                return true;
                            }
                            return false;
                        default:
                            return NumberPicker.this.f7792d.performAccessibilityAction(i2, bundle);
                    }
                case 3:
                    switch (i2) {
                        case 16:
                            if (NumberPicker.this.isEnabled()) {
                                NumberPicker.this.m1663a(i == 1);
                                m1616a(i, 1);
                                return true;
                            }
                            return false;
                        case 64:
                            if (this.f7821d != i) {
                                this.f7821d = i;
                                m1616a(i, 32768);
                                NumberPicker.this.invalidate(0, 0, NumberPicker.this.getRight(), NumberPicker.this.f7782W);
                                return true;
                            }
                            return false;
                        case 128:
                            if (this.f7821d == i) {
                                this.f7821d = Integer.MIN_VALUE;
                                m1616a(i, 65536);
                                NumberPicker.this.invalidate(0, 0, NumberPicker.this.getRight(), NumberPicker.this.f7782W);
                                return true;
                            }
                            return false;
                        default:
                            return false;
                    }
            }
            return super.performAction(i, i2, bundle);
        }

        /* renamed from: a */
        public void m1616a(int i, int i2) {
            switch (i) {
                case 1:
                    if (m1610c()) {
                        m1614a(i, i2, m1608e());
                        return;
                    }
                    return;
                case 2:
                    m1617a(i2);
                    return;
                case 3:
                    if (m1611b()) {
                        m1614a(i, i2, m1609d());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        private void m1617a(int i) {
            if (((AccessibilityManager) NumberPicker.this.getContext().getSystemService(Context.ACCESSIBILITY_SERVICE)).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
                NumberPicker.this.f7792d.onInitializeAccessibilityEvent(obtain);
                NumberPicker.this.f7792d.onPopulateAccessibilityEvent(obtain);
                obtain.setSource(NumberPicker.this, 2);
                NumberPicker.this.requestSendAccessibilityEvent(NumberPicker.this, obtain);
            }
        }

        /* renamed from: a */
        private void m1614a(int i, int i2, String str) {
            if (((AccessibilityManager) NumberPicker.this.getContext().getSystemService(Context.ACCESSIBILITY_SERVICE)).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
                obtain.setClassName(Button.class.getName());
                obtain.setPackageName(NumberPicker.this.getContext().getPackageName());
                obtain.getText().add(str);
                obtain.setEnabled(NumberPicker.this.isEnabled());
                obtain.setSource(NumberPicker.this, i);
                NumberPicker.this.requestSendAccessibilityEvent(NumberPicker.this, obtain);
            }
        }

        /* renamed from: a */
        private void m1612a(String str, int i, List<AccessibilityNodeInfo> list) {
            switch (i) {
                case 1:
                    String m1608e = m1608e();
                    if (!TextUtils.isEmpty(m1608e) && m1608e.toString().toLowerCase().contains(str)) {
                        list.add(createAccessibilityNodeInfo(1));
                        return;
                    }
                    return;
                case 2:
                    Editable text = NumberPicker.this.f7792d.getText();
                    if (TextUtils.isEmpty(text) || !text.toString().toLowerCase().contains(str)) {
                        Editable text2 = NumberPicker.this.f7792d.getText();
                        if (!TextUtils.isEmpty(text2) && text2.toString().toLowerCase().contains(str)) {
                            list.add(createAccessibilityNodeInfo(2));
                            return;
                        }
                        return;
                    }
                    list.add(createAccessibilityNodeInfo(2));
                    return;
                case 3:
                    String m1609d = m1609d();
                    if (!TextUtils.isEmpty(m1609d) && m1609d.toString().toLowerCase().contains(str)) {
                        list.add(createAccessibilityNodeInfo(3));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        private AccessibilityNodeInfo m1618a() {
            AccessibilityNodeInfo createAccessibilityNodeInfo = NumberPicker.this.f7792d.createAccessibilityNodeInfo();
            createAccessibilityNodeInfo.setSource(NumberPicker.this, 2);
            if (this.f7821d != 2) {
                createAccessibilityNodeInfo.addAction(64);
            }
            if (this.f7821d == 2) {
                createAccessibilityNodeInfo.addAction(128);
            }
            return createAccessibilityNodeInfo;
        }

        /* renamed from: a */
        private AccessibilityNodeInfo m1613a(int i, String str, int i2, int i3, int i4, int i5) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(Button.class.getName());
            obtain.setPackageName(NumberPicker.this.getContext().getPackageName());
            obtain.setSource(NumberPicker.this, i);
            obtain.setParent(NumberPicker.this);
            obtain.setText(str);
            obtain.setClickable(true);
            obtain.setLongClickable(true);
            obtain.setEnabled(NumberPicker.this.isEnabled());
            Rect rect = this.f7819b;
            rect.set(i2, i3, i4, i5);
            obtain.setBoundsInParent(rect);
            int[] iArr = this.f7820c;
            NumberPicker.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            if (this.f7821d != i) {
                obtain.addAction(64);
            }
            if (this.f7821d == i) {
                obtain.addAction(128);
            }
            if (NumberPicker.this.isEnabled()) {
                obtain.addAction(16);
            }
            return obtain;
        }

        /* renamed from: a */
        private AccessibilityNodeInfo m1615a(int i, int i2, int i3, int i4) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(NumberPicker.class.getName());
            obtain.setPackageName(NumberPicker.this.getContext().getPackageName());
            obtain.setSource(NumberPicker.this);
            if (m1611b()) {
                obtain.addChild(NumberPicker.this, 3);
            }
            obtain.addChild(NumberPicker.this, 2);
            if (m1610c()) {
                obtain.addChild(NumberPicker.this, 1);
            }
            obtain.setParent((View) NumberPicker.this.getParentForAccessibility());
            obtain.setEnabled(NumberPicker.this.isEnabled());
            obtain.setScrollable(true);
            if (this.f7821d != -1) {
                obtain.addAction(64);
            }
            if (this.f7821d == -1) {
                obtain.addAction(128);
            }
            if (NumberPicker.this.isEnabled()) {
                if (NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() < NumberPicker.this.getMaxValue()) {
                    obtain.addAction(4096);
                }
                if (NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() > NumberPicker.this.getMinValue()) {
                    obtain.addAction(8192);
                }
            }
            return obtain;
        }

        /* renamed from: b */
        private boolean m1611b() {
            return NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() > NumberPicker.this.getMinValue();
        }

        /* renamed from: c */
        private boolean m1610c() {
            return NumberPicker.this.getWrapSelectorWheel() || NumberPicker.this.getValue() < NumberPicker.this.getMaxValue();
        }

        /* renamed from: d */
        private String m1609d() {
            int i = NumberPicker.this.f7804p - 1;
            if (NumberPicker.this.f7774O) {
                i = NumberPicker.this.m1650c(i);
            }
            if (i >= NumberPicker.this.f7802n) {
                return NumberPicker.this.f7801m == null ? NumberPicker.this.m1639e(i) : NumberPicker.this.f7801m[i - NumberPicker.this.f7802n];
            }
            return null;
        }

        /* renamed from: e */
        private String m1608e() {
            int i = NumberPicker.this.f7804p + 1;
            if (NumberPicker.this.f7774O) {
                i = NumberPicker.this.m1650c(i);
            }
            if (i <= NumberPicker.this.f7803o) {
                return NumberPicker.this.f7801m == null ? NumberPicker.this.m1639e(i) : NumberPicker.this.f7801m[i - NumberPicker.this.f7802n];
            }
            return null;
        }
    }

    /* renamed from: f */
    private static String m1636f(int i) {
        return String.format(Locale.getDefault(), "%d", Integer.valueOf(i));
    }
}
