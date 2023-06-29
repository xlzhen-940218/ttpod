package com.sds.android.ttpod.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import java.util.Locale;

/* loaded from: classes.dex */
public class SlidingTabHost extends HorizontalScrollView {

    /* renamed from: c */
    private static final int[] textAttr = {android.R.attr.textSize, android.R.attr.textColor};

    /* renamed from: A */
    private int dividerPaddingStrokeWidth;

    /* renamed from: B */
    private int f7988B;

    /* renamed from: C */
    private ColorStateList colorStateList;

    /* renamed from: D */
    private Typeface f7990D;

    /* renamed from: E */
    private int f7991E;

    /* renamed from: F */
    private int f7992F;

    /* renamed from: G */
    private int tabBackground;

    /* renamed from: H */
    private Locale locale;

    /* renamed from: I */
    private boolean f7995I;

    /* renamed from: J */
    private Rect f7996J;

    /* renamed from: K */
    private boolean f7997K;

    /* renamed from: a */
    protected ViewPager.OnPageChangeListener onPageChangeListener;

    /* renamed from: b */
    protected int f7999b;

    /* renamed from: d */
    private final LinearLayout.LayoutParams f8000d;

    /* renamed from: e */
    private final LinearLayout.LayoutParams f8001e;

    /* renamed from: f */
    private final OnPageChangeListener f8002f;

    /* renamed from: g */
    private LinearLayout f8003g;

    /* renamed from: h */
    private ViewPager viewPager;

    /* renamed from: i */
    private int f8005i;

    /* renamed from: j */
    private float f8006j;

    /* renamed from: k */
    private Paint underlinePaint;

    /* renamed from: l */
    private Paint dividerPaddingPaint;

    /* renamed from: m */
    private boolean f8009m;

    /* renamed from: n */
    private int indicatorColor;

    /* renamed from: o */
    private Drawable drawable;

    /* renamed from: p */
    private int underlineColor;

    /* renamed from: q */
    private int dividerColor;

    /* renamed from: r */
    private boolean shouldExpand;

    /* renamed from: s */
    private boolean textAllCaps;

    /* renamed from: t */
    private int scrollOffset;

    /* renamed from: u */
    private int indicatorHeight;

    /* renamed from: v */
    private int tabHeight;

    /* renamed from: w */
    private int indicatorPaddingBottom;

    /* renamed from: x */
    private int underlineHeight;

    /* renamed from: y */
    private int dividerPadding;

    /* renamed from: z */
    private int tabPaddingLeftRight;

    /* renamed from: com.sds.android.ttpod.widget.SlidingTabHost$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2244a {
        /* renamed from: b */
        int mo1459b(int i);

        /* renamed from: c */
        int mo1458c(int i);
    }

    public SlidingTabHost(Context context) {
        this(context, null);
    }

    public SlidingTabHost(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @SuppressLint("ResourceType")
    public SlidingTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8002f = new OnPageChangeListener();
        this.f7999b = 0;
        this.f8006j = 0.0f;
        this.f8009m = false;
        this.indicatorColor = -10066330;
        this.drawable = null;
        this.underlineColor = 0;
        this.dividerColor = 436207616;
        this.shouldExpand = false;
        this.textAllCaps = true;
        this.scrollOffset = -1;
        this.indicatorHeight = 8;
        this.tabHeight = -1;
        this.indicatorPaddingBottom = 0;
        this.underlineHeight = 2;
        this.dividerPadding = 0;
        this.tabPaddingLeftRight = 5;
        this.dividerPaddingStrokeWidth = 0;
        this.f7988B = 12;
        this.colorStateList = ColorStateList.valueOf(-10066330);
        this.f7990D = null;
        this.f7991E = 0;
        this.f7992F = 0;
        this.tabBackground = R.drawable.xml_background_tab_item;
        this.f7996J = new Rect();
        this.f7997K = false;
        setFillViewport(true);
        setWillNotDraw(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (this.scrollOffset > 0) {
            this.scrollOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.scrollOffset, displayMetrics);
        }
        this.indicatorHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.indicatorHeight, displayMetrics);
        this.indicatorPaddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.indicatorPaddingBottom, displayMetrics);
        this.underlineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.underlineHeight, displayMetrics);
        this.dividerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.dividerPadding, displayMetrics);
        this.tabPaddingLeftRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.tabPaddingLeftRight, displayMetrics);
        this.dividerPaddingStrokeWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.dividerPaddingStrokeWidth, displayMetrics);
        this.f7988B = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.f7988B, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, textAttr);
        this.f7988B = obtainStyledAttributes.getDimensionPixelSize(0, this.f7988B);
        @SuppressLint("ResourceType") ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(1);
        if (colorStateList != null) {
            this.colorStateList = colorStateList;
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingTabHost);
        this.indicatorColor = obtainStyledAttributes2.getColor(R.styleable.SlidingTabHost_indicatorColor, this.indicatorColor);
        this.underlineColor = obtainStyledAttributes2.getColor(R.styleable.SlidingTabHost_underlineColor, this.underlineColor);
        this.dividerColor = obtainStyledAttributes2.getColor(R.styleable.SlidingTabHost_dividerColor, this.dividerColor);
        this.indicatorHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SlidingTabHost_indicatorHeight, this.indicatorHeight);
        this.indicatorPaddingBottom = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SlidingTabHost_indicatorPaddingBottom, this.indicatorPaddingBottom);
        this.underlineHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SlidingTabHost_underlineHeight, this.underlineHeight);
        this.dividerPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SlidingTabHost_dividerPadding, this.dividerPadding);
        this.tabPaddingLeftRight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SlidingTabHost_tabPaddingLeftRight, this.tabPaddingLeftRight);
        this.tabBackground = obtainStyledAttributes2.getResourceId(R.styleable.SlidingTabHost_tabBackground, this.tabBackground);
        this.shouldExpand = obtainStyledAttributes2.getBoolean(R.styleable.SlidingTabHost_shouldExpand, this.shouldExpand);
        this.scrollOffset = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SlidingTabHost_scrollOffset, this.scrollOffset);
        this.textAllCaps = obtainStyledAttributes2.getBoolean(R.styleable.SlidingTabHost_textAllCaps, this.textAllCaps);
        this.tabHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SlidingTabHost_tabHeight, this.tabHeight);
        obtainStyledAttributes2.recycle();
        this.underlinePaint = new Paint();
        this.underlinePaint.setAntiAlias(true);
        this.underlinePaint.setStyle(Paint.Style.FILL);
        this.dividerPaddingPaint = new Paint();
        this.dividerPaddingPaint.setAntiAlias(true);
        this.dividerPaddingPaint.setStrokeWidth(this.dividerPaddingStrokeWidth);
        this.f8000d = new LinearLayout.LayoutParams(-2, -1);
        this.f8001e = new LinearLayout.LayoutParams(0, -1, 1.0f);
        if (this.locale == null) {
            this.locale = getResources().getConfiguration().locale;
        }
        this.f8003g = new LinearLayout(context);
        this.f8003g.setOrientation(0);
        this.f8003g.setLayoutParams(new FrameLayout.LayoutParams(-1, this.tabHeight));
        addView(this.f8003g);
        postDelayed(new Runnable() { // from class: com.sds.android.ttpod.widget.SlidingTabHost.1
            @Override // java.lang.Runnable
            public void run() {
                SlidingTabHost.this.f8003g.getHitRect(SlidingTabHost.this.f7996J);
            }
        }, 200L);
    }

    public void setTabLayoutAverageSpace(boolean z) {
        this.f7995I = z;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        if (this.viewPager.getAdapter() instanceof SlidingTabFragmentPagerAdapter) {
            ((SlidingTabFragmentPagerAdapter) this.viewPager.getAdapter()).m7427a(this.viewPager.getCurrentItem());
        }
        viewPager.setOnPageChangeListener(this.f8002f);
        mo1479a();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return this.onPageChangeListener;
    }

    /* renamed from: a */
    public void mo1479a() {
        m1472a(this.viewPager.getAdapter());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m1472a(PagerAdapter pagerAdapter) {
        this.f8003g.removeAllViews();
        this.f8005i = pagerAdapter.getCount();
        if (getCurrentItem() >= this.f8005i) {
            setCurrentItem(this.f8005i - 1);
        }
        if (pagerAdapter instanceof InterfaceC2244a) {
            for (int i = 0; i < this.f8005i; i++) {
                m1476a(i, ((InterfaceC2244a) pagerAdapter).mo1458c(i), ((InterfaceC2244a) pagerAdapter).mo1459b(i), pagerAdapter.getPageTitle(i));
            }
        } else {
            for (int i2 = 0; i2 < this.f8005i; i2++) {
                m1474a(i2, pagerAdapter.getPageTitle(i2).toString(), 0);
            }
        }
        m1468b();
        this.f8009m = false;
        this.f7999b = getCurrentItem();
        m1477a(this.f7999b, 0);
        m1473a(this.f7999b, true);
    }

    protected int getCurrentItem() {
        return this.viewPager.getCurrentItem();
    }

    protected void setCurrentItem(int i) {
        this.viewPager.setCurrentItem(i);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f7996J.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            this.f7997K = false;
            return super.onInterceptTouchEvent(motionEvent);
        }
        this.f7997K = true;
        return true;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.f7997K && super.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    public void m1475a(int i, CharSequence charSequence) {
        View childAt = this.f8003g.getChildAt(i);
        if (childAt instanceof TextView) {
            ((TextView) childAt).setText(charSequence);
            return;
        }
        throw new IllegalArgumentException("Tab at index:" + i + "is not a TextTab");
    }

    /* renamed from: a */
    private void m1476a(int i, int i2, int i3, CharSequence charSequence) {
        if (i2 != 0) {
            m1474a(i, getContext().getText(i2), i3);
        } else if (charSequence == null) {
            m1466b(i, i3);
        } else {
            m1474a(i, charSequence, i3);
        }
    }

    /* renamed from: a */
    private void m1474a(final int i, CharSequence charSequence, int i2) {
        TextView textView = new TextView(getContext());
        textView.setText(charSequence);
        textView.setFocusable(true);
        textView.setGravity(17);
        textView.setSingleLine();
        textView.setCompoundDrawablesWithIntrinsicBounds(i2, 0, 0, 0);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.SlidingTabHost.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SlidingTabHost.this.mo1478a(i);
            }
        });
        this.f8003g.addView(textView);
    }

    /* renamed from: b */
    private void m1466b(final int i, int i2) {
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setFocusable(true);
        imageButton.setImageResource(i2);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.SlidingTabHost.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SlidingTabHost.this.mo1478a(i);
            }
        });
        this.f8003g.addView(imageButton);
    }

    /* renamed from: a */
    protected void mo1478a(int i) {
        setCurrentItem(i);
    }

    /* renamed from: b */
    private void m1468b() {
        for (int i = 0; i < this.f8005i; i++) {
            View childAt = this.f8003g.getChildAt(i);
            childAt.setLayoutParams(this.f7995I ? this.f8001e : this.f8000d);
            childAt.setBackgroundResource(this.tabBackground);
            if (this.shouldExpand) {
                childAt.setPadding(0, 0, 0, 0);
            } else {
                childAt.setPadding(this.tabPaddingLeftRight, 0, this.tabPaddingLeftRight, 0);
            }
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, this.f7988B);
                textView.setTypeface(this.f7990D, this.f7991E);
                textView.setTextColor(this.colorStateList);
                if (this.textAllCaps) {
                    if (SDKVersionUtils.sdkThan14()) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.locale));
                    }
                }
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.shouldExpand && MeasureSpec.getMode(i) != MeasureSpec.UNSPECIFIED) {
            int measuredWidth = getMeasuredWidth();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f8005i; i4++) {
                i3 += this.f8003g.getChildAt(i4).getMeasuredWidth();
            }
            if (!this.f8009m && i3 > 0 && measuredWidth > 0) {
                if (i3 <= measuredWidth) {
                    for (int i5 = 0; i5 < this.f8005i; i5++) {
                        this.f8003g.getChildAt(i5).setLayoutParams(this.f8001e);
                    }
                }
                this.f8009m = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m1477a(int i, int i2) {
        if (this.f8005i != 0) {
            if (this.scrollOffset < 0) {
                this.scrollOffset = this.f8003g.getChildAt(0).getWidth();
            }
            int left = this.f8003g.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.scrollOffset;
            }
            if (left != this.f7992F) {
                this.f7992F = left;
                scrollTo(left, 0);
            }
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        View childAt = null;
        Bitmap bitmap;
        super.onDraw(canvas);
        if (!isInEditMode() && this.f8005i != 0) {
            int height = getHeight();
            if (this.drawable == null || (this.drawable instanceof ColorDrawable)) {
                this.indicatorColor = this.drawable == null ? this.indicatorColor : ThemeManager.m3272a((ColorDrawable) this.drawable);
                this.underlinePaint.setColor(this.indicatorColor);
                View childAt2 = this.f8003g.getChildAt(this.f7999b);
                float left = childAt2.getLeft();
                float right = childAt2.getRight();
                if (this.f8006j > 0.0f && this.f7999b < this.f8005i - 1) {
                    View childAt3 = this.f8003g.getChildAt(this.f7999b + 1);
                    left = (left * (1.0f - this.f8006j)) + (childAt3.getLeft() * this.f8006j);
                    right = (childAt3.getRight() * this.f8006j) + ((1.0f - this.f8006j) * right);
                }
                canvas.drawRect(left, (height - this.indicatorHeight) - this.indicatorPaddingBottom, right, height - this.indicatorPaddingBottom, this.underlinePaint);
                this.underlinePaint.setColor(this.underlineColor);
                canvas.drawRect(0.0f, height - this.underlineHeight, this.f8003g.getWidth(), height, this.underlinePaint);
            } else {
                float left2 = this.f8003g.getChildAt(this.f7999b).getLeft();
                float left3 = (this.f8006j <= 0.0f || this.f7999b >= this.f8005i + (-1)) ? left2 : (left2 * (1.0f - this.f8006j)) + (this.f8003g.getChildAt(this.f7999b + 1).getLeft() * this.f8006j);
                if ((this.drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) this.drawable).getBitmap()) != null) {
                    canvas.drawBitmap(bitmap, (Rect) null, new RectF(left3, 0.0f, childAt.getWidth() + left3, childAt.getHeight()), (Paint) null);
                }
            }
            this.dividerPaddingPaint.setColor(this.dividerColor);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.f8005i - 1) {
                    View childAt4 = this.f8003g.getChildAt(i2);
                    if (this.dividerPaddingStrokeWidth > 0) {
                        canvas.drawLine(childAt4.getRight(), this.dividerPadding, childAt4.getRight(), height - this.dividerPadding, this.dividerPaddingPaint);
                    }
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m1467b(int i) {
        if (this.f7999b != i) {
            m1473a(this.f7999b, false);
            this.f7999b = i;
            m1473a(this.f7999b, true);
        }
    }

    /* renamed from: a */
    private void m1473a(int i, boolean z) {
        if (i >= 0 && i < this.f8003g.getChildCount()) {
            this.f8003g.getChildAt(i).setSelected(z);
        }
    }

    /* renamed from: com.sds.android.ttpod.widget.SlidingTabHost$b */
    /* loaded from: classes.dex */
    private class OnPageChangeListener implements ViewPager.OnPageChangeListener {
        private OnPageChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            SlidingTabHost.this.m1467b(i);
            SlidingTabHost.this.f8006j = f;
            View childAt = SlidingTabHost.this.f8003g.getChildAt(i);
            if (childAt != null) {
                SlidingTabHost.this.m1477a(i, (int) (childAt.getWidth() * f));
                SlidingTabHost.this.invalidate();
                if (SlidingTabHost.this.onPageChangeListener != null) {
                    SlidingTabHost.this.onPageChangeListener.onPageScrolled(i, f, i2);
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                SlidingTabHost.this.m1477a(SlidingTabHost.this.getCurrentItem(), 0);
            }
            if (SlidingTabHost.this.onPageChangeListener != null) {
                SlidingTabHost.this.onPageChangeListener.onPageScrollStateChanged(i);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (SlidingTabHost.this.onPageChangeListener != null) {
                SlidingTabHost.this.onPageChangeListener.onPageSelected(i);
                if (SlidingTabHost.this.viewPager.getAdapter() instanceof SlidingTabFragmentPagerAdapter) {
                    ((SlidingTabFragmentPagerAdapter) SlidingTabHost.this.viewPager.getAdapter()).m7427a(i);
                }
            }
        }
    }

    public void setIndicatorColor(int i) {
        this.indicatorColor = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.indicatorColor = getResources().getColor(i);
        invalidate();
    }

    public void setIndicatorDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int i) {
        this.indicatorHeight = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public void setUnderlineColor(int i) {
        this.underlineColor = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.underlineColor = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.underlineColor;
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.dividerColor = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public void setUnderlineHeight(int i) {
        this.underlineHeight = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    public void setDividerPadding(int i) {
        this.dividerPadding = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public void setScrollOffset(int i) {
        this.scrollOffset = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public void setShouldExpand(boolean z) {
        this.shouldExpand = z;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return this.shouldExpand;
    }

    public void setAllCaps(boolean z) {
        this.textAllCaps = z;
    }

    public void setTextSize(int i) {
        this.f7988B = i;
        m1468b();
    }

    public int getTextSize() {
        return this.f7988B;
    }

    public void setTextColor(int i) {
        this.colorStateList = ColorStateList.valueOf(i);
        m1468b();
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.colorStateList = colorStateList;
            m1468b();
        }
    }

    public void setTextColorResource(int i) {
        this.colorStateList = getResources().getColorStateList(i);
        m1468b();
    }

    public int getTextColor() {
        return this.colorStateList.getDefaultColor();
    }

    public void setTabBackground(int i) {
        this.tabBackground = i;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
    }

    public int getTabBackground() {
        return this.tabBackground;
    }

    public void setTabPaddingLeftRight(int i) {
        this.tabPaddingLeftRight = i;
        m1468b();
    }

    public int getTabPaddingLeftRight() {
        return this.tabPaddingLeftRight;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f7999b = savedState.f8028a;
        requestLayout();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f8028a = this.f7999b;
        return savedState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.sds.android.ttpod.widget.SlidingTabHost.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a */
        private int f8028a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f8028a = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f8028a);
        }
    }
}
