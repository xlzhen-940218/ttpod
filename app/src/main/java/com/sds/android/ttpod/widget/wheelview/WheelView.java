package com.sds.android.ttpod.widget.wheelview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.sds.android.ttpod.widget.wheelview.p142a.WheelViewAdapter;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class WheelView extends View {

    /* renamed from: a */
    private static final int[] f8409a = {-1, -1996488705, 0x00ffffff};

    /* renamed from: b */
    private int f8410b;

    /* renamed from: c */
    private int f8411c;

    /* renamed from: d */
    private int f8412d;

    /* renamed from: e */
    private GradientDrawable f8413e;

    /* renamed from: f */
    private GradientDrawable f8414f;

    /* renamed from: g */
    private WheelScroller f8415g;

    /* renamed from: h */
    private boolean f8416h;

    /* renamed from: i */
    private int f8417i;

    /* renamed from: j */
    private boolean f8418j;

    /* renamed from: k */
    private LinearLayout f8419k;

    /* renamed from: l */
    private int f8420l;

    /* renamed from: m */
    private WheelViewAdapter f8421m;

    /* renamed from: n */
    private WheelRecycle f8422n;

    /* renamed from: o */
    private List<OnWheelChangedListener> f8423o;

    /* renamed from: p */
    private List<OnWheelScrollListener> f8424p;

    /* renamed from: q */
    private List<OnWheelClickedListener> f8425q;

    /* renamed from: r */
    private WheelScroller.InterfaceC2298a f8426r;

    /* renamed from: s */
    private DataSetObserver f8427s;

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8410b = 0;
        this.f8411c = 5;
        this.f8412d = 0;
        this.f8418j = false;
        this.f8422n = new WheelRecycle(this);
        this.f8423o = new LinkedList();
        this.f8424p = new LinkedList();
        this.f8425q = new LinkedList();
        this.f8426r = new WheelScroller.InterfaceC2298a() { // from class: com.sds.android.ttpod.widget.wheelview.WheelView.1
            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: a */
            public void mo1136a() {
                WheelView.this.f8416h = true;
                WheelView.this.m1202a();
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: a */
            public void mo1135a(int i2) {
                WheelView.this.m1190b(i2);
                int height = WheelView.this.getHeight();
                if (WheelView.this.f8417i > height) {
                    WheelView.this.f8417i = height;
                    WheelView.this.f8415g.m1152a();
                } else if (WheelView.this.f8417i < (-height)) {
                    WheelView.this.f8417i = -height;
                    WheelView.this.f8415g.m1152a();
                }
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: b */
            public void mo1134b() {
                if (WheelView.this.f8416h) {
                    WheelView.this.m1191b();
                    WheelView.this.f8416h = false;
                }
                WheelView.this.f8417i = 0;
                WheelView.this.invalidate();
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: c */
            public void mo1133c() {
                if (Math.abs(WheelView.this.f8417i) > 1) {
                    WheelView.this.f8415g.m1150a(WheelView.this.f8417i, 0);
                }
            }
        };
        this.f8427s = new DataSetObserver() { // from class: com.sds.android.ttpod.widget.wheelview.WheelView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                WheelView.this.m1192a(false);
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                WheelView.this.m1192a(true);
            }
        };
        m1198a(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8410b = 0;
        this.f8411c = 5;
        this.f8412d = 0;
        this.f8418j = false;
        this.f8422n = new WheelRecycle(this);
        this.f8423o = new LinkedList();
        this.f8424p = new LinkedList();
        this.f8425q = new LinkedList();
        this.f8426r = new WheelScroller.InterfaceC2298a() { // from class: com.sds.android.ttpod.widget.wheelview.WheelView.1
            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: a */
            public void mo1136a() {
                WheelView.this.f8416h = true;
                WheelView.this.m1202a();
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: a */
            public void mo1135a(int i2) {
                WheelView.this.m1190b(i2);
                int height = WheelView.this.getHeight();
                if (WheelView.this.f8417i > height) {
                    WheelView.this.f8417i = height;
                    WheelView.this.f8415g.m1152a();
                } else if (WheelView.this.f8417i < (-height)) {
                    WheelView.this.f8417i = -height;
                    WheelView.this.f8415g.m1152a();
                }
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: b */
            public void mo1134b() {
                if (WheelView.this.f8416h) {
                    WheelView.this.m1191b();
                    WheelView.this.f8416h = false;
                }
                WheelView.this.f8417i = 0;
                WheelView.this.invalidate();
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: c */
            public void mo1133c() {
                if (Math.abs(WheelView.this.f8417i) > 1) {
                    WheelView.this.f8415g.m1150a(WheelView.this.f8417i, 0);
                }
            }
        };
        this.f8427s = new DataSetObserver() { // from class: com.sds.android.ttpod.widget.wheelview.WheelView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                WheelView.this.m1192a(false);
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                WheelView.this.m1192a(true);
            }
        };
        m1198a(context);
    }

    public WheelView(Context context) {
        super(context);
        this.f8410b = 0;
        this.f8411c = 5;
        this.f8412d = 0;
        this.f8418j = false;
        this.f8422n = new WheelRecycle(this);
        this.f8423o = new LinkedList();
        this.f8424p = new LinkedList();
        this.f8425q = new LinkedList();
        this.f8426r = new WheelScroller.InterfaceC2298a() { // from class: com.sds.android.ttpod.widget.wheelview.WheelView.1
            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: a */
            public void mo1136a() {
                WheelView.this.f8416h = true;
                WheelView.this.m1202a();
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: a */
            public void mo1135a(int i2) {
                WheelView.this.m1190b(i2);
                int height = WheelView.this.getHeight();
                if (WheelView.this.f8417i > height) {
                    WheelView.this.f8417i = height;
                    WheelView.this.f8415g.m1152a();
                } else if (WheelView.this.f8417i < (-height)) {
                    WheelView.this.f8417i = -height;
                    WheelView.this.f8415g.m1152a();
                }
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: b */
            public void mo1134b() {
                if (WheelView.this.f8416h) {
                    WheelView.this.m1191b();
                    WheelView.this.f8416h = false;
                }
                WheelView.this.f8417i = 0;
                WheelView.this.invalidate();
            }

            @Override // com.sds.android.ttpod.widget.wheelview.WheelScroller.InterfaceC2298a
            /* renamed from: c */
            public void mo1133c() {
                if (Math.abs(WheelView.this.f8417i) > 1) {
                    WheelView.this.f8415g.m1150a(WheelView.this.f8417i, 0);
                }
            }
        };
        this.f8427s = new DataSetObserver() { // from class: com.sds.android.ttpod.widget.wheelview.WheelView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                WheelView.this.m1192a(false);
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                WheelView.this.m1192a(true);
            }
        };
        m1198a(context);
    }

    /* renamed from: a */
    private void m1198a(Context context) {
        this.f8415g = new WheelScroller(getContext(), this.f8426r);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.f8415g.m1148a(interpolator);
    }

    public int getVisibleItems() {
        return this.f8411c;
    }

    public void setVisibleItems(int i) {
        this.f8411c = i;
    }

    public WheelViewAdapter getViewAdapter() {
        return this.f8421m;
    }

    public void setViewAdapter(WheelViewAdapter wheelViewAdapter) {
        if (this.f8421m != null) {
            this.f8421m.m1164b(this.f8427s);
        }
        this.f8421m = wheelViewAdapter;
        if (this.f8421m != null) {
            this.f8421m.m1166a(this.f8427s);
        }
        m1192a(true);
    }

    /* renamed from: a */
    protected void m1200a(int i, int i2) {
        for (OnWheelChangedListener onWheelChangedListener : this.f8423o) {
            onWheelChangedListener.m1163a(this, i, i2);
        }
    }

    /* renamed from: a */
    protected void m1202a() {
        for (OnWheelScrollListener onWheelScrollListener : this.f8424p) {
            onWheelScrollListener.m1161a(this);
        }
    }

    /* renamed from: b */
    protected void m1191b() {
        for (OnWheelScrollListener onWheelScrollListener : this.f8424p) {
            onWheelScrollListener.m1160b(this);
        }
    }

    /* renamed from: a */
    protected void m1201a(int i) {
        for (OnWheelClickedListener onWheelClickedListener : this.f8425q) {
            onWheelClickedListener.m1162a(this, i);
        }
    }

    public int getCurrentItem() {
        return this.f8410b;
    }

    /* renamed from: a */
    public void m1199a(int i, boolean z) {
        int i2;
        if (this.f8421m != null && this.f8421m.m1168a() != 0) {
            int m1168a = this.f8421m.m1168a();
            if (i < 0 || i >= m1168a) {
                if (this.f8418j) {
                    while (i < 0) {
                        i += m1168a;
                    }
                    i %= m1168a;
                } else {
                    return;
                }
            }
            if (i != this.f8410b) {
                if (z) {
                    int i3 = i - this.f8410b;
                    if (!this.f8418j || (i2 = (m1168a + Math.min(i, this.f8410b)) - Math.max(i, this.f8410b)) >= Math.abs(i3)) {
                        i2 = i3;
                    } else if (i3 >= 0) {
                        i2 = -i2;
                    }
                    m1189b(i2, 0);
                    return;
                }
                this.f8417i = 0;
                int i4 = this.f8410b;
                this.f8410b = i;
                m1200a(i4, this.f8410b);
                invalidate();
            }
        }
    }

    public void setCurrentItem(int i) {
        m1199a(i, false);
    }

    /* renamed from: c */
    public boolean m1184c() {
        return this.f8418j;
    }

    public void setCyclic(boolean z) {
        this.f8418j = z;
        m1192a(false);
    }

    /* renamed from: a */
    public void m1192a(boolean z) {
        if (z) {
            this.f8422n.m1153c();
            if (this.f8419k != null) {
                this.f8419k.removeAllViews();
            }
            this.f8417i = 0;
        } else if (this.f8419k != null) {
            this.f8422n.m1156a(this.f8419k, this.f8420l, new ItemsRange());
        }
        invalidate();
    }

    /* renamed from: d */
    private void m1179d() {
        if (this.f8413e == null) {
            this.f8413e = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, f8409a);
        }
        if (this.f8414f == null) {
            this.f8414f = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, f8409a);
        }
    }

    /* renamed from: a */
    private int m1196a(LinearLayout linearLayout) {
        if (linearLayout != null && linearLayout.getChildAt(0) != null) {
            this.f8412d = linearLayout.getChildAt(0).getMeasuredHeight();
        }
        return Math.max((this.f8412d * this.f8411c) - ((this.f8412d * 10) / 50), getSuggestedMinimumHeight());
    }

    private int getItemHeight() {
        if (this.f8412d != 0) {
            return this.f8412d;
        }
        if (this.f8419k != null && this.f8419k.getChildAt(0) != null) {
            this.f8412d = this.f8419k.getChildAt(0).getHeight();
            return this.f8412d;
        }
        return getHeight() / this.f8411c;
    }

    /* renamed from: c */
    private int m1182c(int i, int i2) {
        m1179d();
        this.f8419k.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.f8419k.measure(View.MeasureSpec.makeMeasureSpec(i, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.f8419k.getMeasuredWidth();
        if (i2 != 1073741824) {
            int max = Math.max(measuredWidth + 20, getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= max) {
                i = max;
            }
        }
        this.f8419k.measure(View.MeasureSpec.makeMeasureSpec(i - 20, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        return i;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        m1173h();
        int m1182c = m1182c(size, mode);
        if (mode2 != 1073741824) {
            int m1196a = m1196a(this.f8419k);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(m1196a, size2) : m1196a;
        }
        setMeasuredDimension(m1182c, size2);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        m1177d(i3 - i, i4 - i2);
    }

    /* renamed from: d */
    private void m1177d(int i, int i2) {
        this.f8419k.layout(0, 0, i - 20, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f8421m != null && this.f8421m.m1168a() > 0) {
            m1175f();
            m1187b(canvas);
            m1181c(canvas);
        }
        m1197a(canvas);
    }

    /* renamed from: a */
    private void m1197a(Canvas canvas) {
        int itemHeight = (int) (2.5f * getItemHeight());
        this.f8413e.setBounds(0, 0, getWidth(), itemHeight);
        this.f8413e.draw(canvas);
        this.f8414f.setBounds(0, getHeight() - itemHeight, getWidth(), getHeight());
        this.f8414f.draw(canvas);
    }

    /* renamed from: b */
    private void m1187b(Canvas canvas) {
        canvas.save();
        canvas.translate(10.0f, (-(((this.f8410b - this.f8420l) * getItemHeight()) + ((getItemHeight() - getHeight()) / 2))) + this.f8417i);
        this.f8419k.draw(canvas);
        canvas.restore();
    }

    /* renamed from: c */
    private void m1181c(Canvas canvas) {
        int height = getHeight() / 2;
        int itemHeight = (int) ((getItemHeight() / 2) * 1.2d);
        Paint paint = new Paint();
        paint.setColor(-13715221);
        paint.setStrokeWidth(2.0f);
        canvas.drawLine(0.0f, height - itemHeight, getWidth(), height - itemHeight, paint);
        canvas.drawLine(0.0f, height + itemHeight, getWidth(), height + itemHeight, paint);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int itemHeight;
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 1:
                if (!this.f8416h) {
                    int y = ((int) motionEvent.getY()) - (getHeight() / 2);
                    if (y > 0) {
                        itemHeight = y + (getItemHeight() / 2);
                    } else {
                        itemHeight = y - (getItemHeight() / 2);
                    }
                    int itemHeight2 = itemHeight / getItemHeight();
                    if (itemHeight2 != 0 && m1183c(this.f8410b + itemHeight2)) {
                        m1201a(itemHeight2 + this.f8410b);
                        break;
                    }
                }
                break;
            case 2:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
        }
        return this.f8415g.m1149a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m1190b(int i) {
        int i2;
        int i3;
        int i4;
        this.f8417i += i;
        int itemHeight = getItemHeight();
        int i5 = this.f8417i / itemHeight;
        int i6 = this.f8410b - i5;
        int m1168a = this.f8421m.m1168a();
        int i7 = this.f8417i % itemHeight;
        if (Math.abs(i7) <= itemHeight / 2) {
            i7 = 0;
        }
        if (this.f8418j && m1168a > 0) {
            if (i7 > 0) {
                i4 = i6 - 1;
                i3 = i5 + 1;
            } else if (i7 < 0) {
                i4 = i6 + 1;
                i3 = i5 - 1;
            } else {
                i4 = i6;
                i3 = i5;
            }
            while (i4 < 0) {
                i4 += m1168a;
            }
            i2 = i4 % m1168a;
        } else if (i6 < 0) {
            i3 = this.f8410b;
            i2 = 0;
        } else if (i6 >= m1168a) {
            i3 = (this.f8410b - m1168a) + 1;
            i2 = m1168a - 1;
        } else if (i6 > 0 && i7 > 0) {
            i2 = i6 - 1;
            i3 = i5 + 1;
        } else if (i6 >= m1168a - 1 || i7 >= 0) {
            i2 = i6;
            i3 = i5;
        } else {
            i2 = i6 + 1;
            i3 = i5 - 1;
        }
        int i8 = this.f8417i;
        if (i2 != this.f8410b) {
            m1199a(i2, false);
        } else {
            invalidate();
        }
        this.f8417i = i8 - (i3 * itemHeight);
        if (this.f8417i > getHeight()) {
            this.f8417i = (this.f8417i % getHeight()) + getHeight();
        }
    }

    /* renamed from: b */
    public void m1189b(int i, int i2) {
        this.f8415g.m1150a((getItemHeight() * i) - this.f8417i, i2);
    }

    private ItemsRange getItemsRange() {
        if (getItemHeight() == 0) {
            return null;
        }
        int i = this.f8410b;
        int i2 = 1;
        while (getItemHeight() * i2 < getHeight()) {
            i--;
            i2 += 2;
        }
        if (this.f8417i != 0) {
            if (this.f8417i > 0) {
                i--;
            }
            int itemHeight = this.f8417i / getItemHeight();
            i -= itemHeight;
            i2 = (int) (i2 + 1 + Math.asin(itemHeight));
        }
        return new ItemsRange(i, i2);
    }

    /* renamed from: e */
    private boolean m1176e() {
        boolean z;
        ItemsRange itemsRange = getItemsRange();
        if (this.f8419k != null) {
            int m1156a = this.f8422n.m1156a(this.f8419k, this.f8420l, itemsRange);
            z = this.f8420l != m1156a;
            this.f8420l = m1156a;
        } else {
            m1174g();
            z = true;
        }
        if (!z) {
            z = (this.f8420l == itemsRange.m1172a() && this.f8419k.getChildCount() == itemsRange.m1169c()) ? false : true;
        }
        if (this.f8420l > itemsRange.m1172a() && this.f8420l <= itemsRange.m1170b()) {
            int i = this.f8420l;
            while (true) {
                i--;
                if (i < itemsRange.m1172a() || !m1188b(i, true)) {
                    break;
                }
                this.f8420l = i;
            }
        } else {
            this.f8420l = itemsRange.m1172a();
        }
        int i2 = this.f8420l;
        for (int childCount = this.f8419k.getChildCount(); childCount < itemsRange.m1169c(); childCount++) {
            if (!m1188b(this.f8420l + childCount, false) && this.f8419k.getChildCount() == 0) {
                i2++;
            }
        }
        this.f8420l = i2;
        return z;
    }

    /* renamed from: f */
    private void m1175f() {
        if (m1176e()) {
            m1182c(getWidth(), 1073741824);
            m1177d(getWidth(), getHeight());
        }
    }

    /* renamed from: g */
    private void m1174g() {
        if (this.f8419k == null) {
            this.f8419k = new LinearLayout(getContext());
            this.f8419k.setOrientation(1);
        }
    }

    /* renamed from: h */
    private void m1173h() {
        if (this.f8419k != null) {
            this.f8422n.m1156a(this.f8419k, this.f8420l, new ItemsRange());
        } else {
            m1174g();
        }
        int i = this.f8411c / 2;
        for (int i2 = this.f8410b + i; i2 >= this.f8410b - i; i2--) {
            if (m1188b(i2, true)) {
                this.f8420l = i2;
            }
        }
    }

    /* renamed from: b */
    private boolean m1188b(int i, boolean z) {
        View m1178d = m1178d(i);
        if (m1178d != null) {
            if (z) {
                this.f8419k.addView(m1178d, 0);
            } else {
                this.f8419k.addView(m1178d);
            }
            return true;
        }
        return false;
    }

    /* renamed from: c */
    private boolean m1183c(int i) {
        return this.f8421m != null && this.f8421m.m1168a() > 0 && (this.f8418j || (i >= 0 && i < this.f8421m.m1168a()));
    }

    /* renamed from: d */
    private View m1178d(int i) {
        if (this.f8421m == null || this.f8421m.m1168a() == 0) {
            return null;
        }
        int m1168a = this.f8421m.m1168a();
        if (!m1183c(i)) {
            return this.f8421m.m1165a(this.f8422n.m1154b(), this.f8419k);
        }
        while (i < 0) {
            i += m1168a;
        }
        return this.f8421m.m1167a(i % m1168a, this.f8422n.m1159a(), this.f8419k);
    }
}
