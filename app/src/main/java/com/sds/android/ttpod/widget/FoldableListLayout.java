package com.sds.android.ttpod.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.widget.p141b.FoldShading;
import com.sds.android.ttpod.widget.p141b.SimpleFoldShading;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes.dex */
public class FoldableListLayout extends FrameLayout implements GestureDetector.OnGestureListener {

    /* renamed from: a */
    private static final FrameLayout.LayoutParams f7649a = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    /* renamed from: b */
    private InterfaceC2187b f7650b;

    /* renamed from: c */
    private BaseAdapter f7651c;

    /* renamed from: d */
    private float f7652d;

    /* renamed from: e */
    private float f7653e;

    /* renamed from: f */
    private float f7654f;

    /* renamed from: g */
    private FoldableItemLayout f7655g;

    /* renamed from: h */
    private FoldableItemLayout f7656h;

    /* renamed from: i */
    private FoldShading f7657i;

    /* renamed from: j */
    private SparseArray<FoldableItemLayout> f7658j;

    /* renamed from: k */
    private Queue<FoldableItemLayout> f7659k;

    /* renamed from: l */
    private boolean f7660l;

    /* renamed from: m */
    private ObjectAnimator f7661m;

    /* renamed from: n */
    private long f7662n;

    /* renamed from: o */
    private int f7663o;

    /* renamed from: p */
    private boolean f7664p;

    /* renamed from: q */
    private GestureDetector f7665q;

    /* renamed from: r */
    private RunnableC2186a f7666r;

    /* renamed from: s */
    private float f7667s;

    /* renamed from: t */
    private boolean f7668t;

    /* renamed from: u */
    private float f7669u;

    /* renamed from: v */
    private float f7670v;

    /* renamed from: w */
    private float f7671w;

    /* renamed from: x */
    private DataSetObserver f7672x;

    /* renamed from: com.sds.android.ttpod.widget.FoldableListLayout$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2187b {
        /* renamed from: a */
        void m1743a(float f, boolean z);
    }

    public FoldableListLayout(Context context) {
        super(context);
        this.f7658j = new SparseArray<>();
        this.f7659k = new LinkedList();
        this.f7660l = true;
        this.f7669u = 1.33f;
        this.f7672x = new DataSetObserver() { // from class: com.sds.android.ttpod.widget.FoldableListLayout.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                FoldableListLayout.this.m1752b();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                super.onInvalidated();
                FoldableListLayout.this.m1752b();
            }
        };
        m1756a(context);
    }

    public FoldableListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7658j = new SparseArray<>();
        this.f7659k = new LinkedList();
        this.f7660l = true;
        this.f7669u = 1.33f;
        this.f7672x = new DataSetObserver() { // from class: com.sds.android.ttpod.widget.FoldableListLayout.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                FoldableListLayout.this.m1752b();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                super.onInvalidated();
                FoldableListLayout.this.m1752b();
            }
        };
        m1756a(context);
    }

    public FoldableListLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7658j = new SparseArray<>();
        this.f7659k = new LinkedList();
        this.f7660l = true;
        this.f7669u = 1.33f;
        this.f7672x = new DataSetObserver() { // from class: com.sds.android.ttpod.widget.FoldableListLayout.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                FoldableListLayout.this.m1752b();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                super.onInvalidated();
                FoldableListLayout.this.m1752b();
            }
        };
        m1756a(context);
    }

    /* renamed from: a */
    private void m1756a(Context context) {
        this.f7665q = new GestureDetector(context, this);
        this.f7665q.setIsLongpressEnabled(false);
        if (SDKVersionUtils.m8371c()) {
            this.f7661m = ObjectAnimator.ofFloat(this, "foldRotation", 0.0f);
            this.f7667s = ViewConfiguration.get(context).getScaledPagingTouchSlop();
        }
        this.f7666r = new RunnableC2186a();
        this.f7657i = new SimpleFoldShading();
        postDelayed(new Runnable() { // from class: com.sds.android.ttpod.widget.FoldableListLayout.1
            @Override // java.lang.Runnable
            public void run() {
                FoldableListLayout.this.f7666r.m1745c();
            }
        }, 2000L);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.f7655g != null) {
            this.f7655g.draw(canvas);
        }
        if (this.f7656h != null) {
            this.f7656h.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        return getCount() > 0;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f7660l && m1755a(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f7660l && m1755a(motionEvent);
    }

    public void setOnFoldRotationListener(InterfaceC2187b interfaceC2187b) {
        this.f7650b = interfaceC2187b;
    }

    public void setFoldShading(FoldShading foldShading) {
        this.f7657i = foldShading;
    }

    public void setGesturesEnabled(boolean z) {
        this.f7660l = z;
    }

    protected void setScrollFactor(float f) {
        this.f7669u = f;
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        if (this.f7651c != null) {
            this.f7651c.unregisterDataSetObserver(this.f7672x);
        }
        this.f7651c = baseAdapter;
        if (this.f7651c != null) {
            this.f7651c.registerDataSetObserver(this.f7672x);
        }
        m1752b();
    }

    public BaseAdapter getAdapter() {
        return this.f7651c;
    }

    public int getCount() {
        if (this.f7651c == null) {
            return 0;
        }
        return this.f7651c.getCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m1752b() {
        int count = getCount();
        this.f7653e = 0.0f;
        this.f7654f = count != 0 ? (count - 1) * 180 : 0.0f;
        m1749c();
        setFoldRotation(this.f7652d);
    }

    public final void setFoldRotation(float f) {
        m1758a(f, false);
    }

    /* renamed from: a */
    protected void m1758a(float f, boolean z) {
        if (z) {
            this.f7661m.cancel();
            this.f7666r.m1748a();
        }
        float min = Math.min(Math.max(this.f7653e, f), this.f7654f);
        this.f7652d = min;
        int i = (int) (min / 180.0f);
        float f2 = min % 180.0f;
        int count = getCount();
        boolean z2 = i < count;
        boolean z3 = i + 1 < count;
        FoldableItemLayout m1751b = z2 ? m1751b(i) : null;
        FoldableItemLayout m1751b2 = z3 ? m1751b(i + 1) : null;
        if (z2) {
            m1751b.setFoldRotation(f2);
            m1753a(m1751b, i);
        }
        if (z3) {
            m1751b2.setFoldRotation(f2 - 180.0f);
            m1753a(m1751b2, i + 1);
        }
        if (f2 <= 90.0f) {
            this.f7655g = m1751b2;
            this.f7656h = m1751b;
        } else {
            this.f7655g = m1751b;
            this.f7656h = m1751b2;
        }
        if (this.f7650b != null) {
            this.f7650b.m1743a(min, z);
        }
        invalidate();
    }

    /* renamed from: a */
    protected void m1753a(FoldableItemLayout foldableItemLayout, int i) {
    }

    public float getFoldRotation() {
        return this.f7652d;
    }

    /* renamed from: b */
    private FoldableItemLayout m1751b(int i) {
        FoldableItemLayout foldableItemLayout = this.f7658j.get(i);
        if (foldableItemLayout == null) {
            foldableItemLayout = this.f7659k.poll();
            if (foldableItemLayout == null) {
                int size = this.f7658j.size();
                int i2 = 0;
                int i3 = i;
                while (i2 < size) {
                    int keyAt = this.f7658j.keyAt(i2);
                    if (Math.abs(i - keyAt) <= Math.abs(i - i3)) {
                        keyAt = i3;
                    }
                    i2++;
                    i3 = keyAt;
                }
                if (Math.abs(i3 - i) > 2) {
                    foldableItemLayout = this.f7658j.get(i3);
                    this.f7658j.remove(i3);
                    foldableItemLayout.getBaseLayout().removeAllViews();
                }
            }
            if (foldableItemLayout == null) {
                foldableItemLayout = new FoldableItemLayout(getContext());
                foldableItemLayout.setFoldShading(this.f7657i);
                addView(foldableItemLayout, f7649a);
            }
            foldableItemLayout.getBaseLayout().addView(this.f7651c.getView(i, null, foldableItemLayout.getBaseLayout()), f7649a);
            this.f7658j.put(i, foldableItemLayout);
        }
        return foldableItemLayout;
    }

    /* renamed from: c */
    private void m1749c() {
        int size = this.f7658j.size();
        for (int i = 0; i < size; i++) {
            FoldableItemLayout valueAt = this.f7658j.valueAt(i);
            valueAt.getBaseLayout().removeAllViews();
            this.f7659k.offer(valueAt);
        }
        this.f7658j.clear();
    }

    /* renamed from: a */
    public void m1757a(int i) {
        float max = Math.max(0, Math.min(i, getCount() - 1)) * 180;
        float foldRotation = getFoldRotation();
        this.f7666r.m1748a();
        this.f7661m.cancel();
        this.f7661m.setFloatValues(foldRotation, max);
        this.f7661m.setDuration((long) Math.abs((600.0f * (max - foldRotation)) / 180.0f)).start();
    }

    /* renamed from: a */
    protected void m1759a() {
        m1757a((int) ((getFoldRotation() + 90.0f) / 180.0f));
    }

    /* renamed from: a */
    private boolean m1755a(MotionEvent motionEvent) {
        long eventTime = motionEvent.getEventTime();
        int actionMasked = motionEvent.getActionMasked();
        if (this.f7662n == eventTime && this.f7663o == actionMasked) {
            return this.f7664p;
        }
        this.f7662n = eventTime;
        this.f7663o = actionMasked;
        if (getCount() > 0) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.offsetLocation(0.0f, getTranslationY());
            this.f7664p = this.f7665q.onTouchEvent(obtain);
            obtain.recycle();
        } else {
            this.f7664p = false;
        }
        if (actionMasked == 1 && !this.f7666r.m1746b()) {
            m1759a();
        }
        return this.f7664p;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        this.f7668t = false;
        this.f7661m.cancel();
        this.f7666r.m1748a();
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float y = motionEvent.getY() - motionEvent2.getY();
        int height = getHeight();
        if (!this.f7668t && Math.abs(y) > this.f7667s && height != 0) {
            this.f7668t = true;
            this.f7670v = getFoldRotation();
            this.f7671w = y;
        }
        if (this.f7668t) {
            m1758a((((y - this.f7671w) * (180.0f * this.f7669u)) / height) + this.f7670v, true);
        }
        return this.f7668t;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        int height = getHeight();
        if (height == 0) {
            return false;
        }
        float f3 = ((-f2) / height) * 180.0f;
        return this.f7666r.m1747a(Math.signum(f3) * Math.max(600.0f, Math.abs(f3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.widget.FoldableListLayout$a */
    /* loaded from: classes.dex */
    public class RunnableC2186a implements Runnable {

        /* renamed from: b */
        private final Handler f7676b;

        /* renamed from: c */
        private boolean f7677c;

        /* renamed from: d */
        private long f7678d;

        /* renamed from: e */
        private float f7679e;

        /* renamed from: f */
        private float f7680f;

        /* renamed from: g */
        private float f7681g;

        private RunnableC2186a() {
            this.f7676b = new Handler();
        }

        @Override // java.lang.Runnable
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            this.f7678d = currentTimeMillis;
            float max = Math.max(this.f7680f, Math.min(FoldableListLayout.this.getFoldRotation() + ((this.f7679e / 1000.0f) * ((float) (currentTimeMillis - this.f7678d))), this.f7681g));
            FoldableListLayout.this.setFoldRotation(max);
            if (max != this.f7680f && max != this.f7681g) {
                m1744d();
            } else {
                m1748a();
            }
        }

        /* renamed from: d */
        private void m1744d() {
            this.f7676b.removeCallbacks(this);
            this.f7676b.postDelayed(this, 10L);
            this.f7677c = true;
        }

        /* renamed from: a */
        void m1748a() {
            this.f7676b.removeCallbacks(this);
            this.f7677c = false;
        }

        /* renamed from: b */
        boolean m1746b() {
            return this.f7677c;
        }

        /* renamed from: a */
        boolean m1747a(float f) {
            float foldRotation = FoldableListLayout.this.getFoldRotation();
            if (foldRotation % 180.0f == 0.0f) {
                return false;
            }
            this.f7678d = System.currentTimeMillis();
            this.f7679e = f;
            this.f7680f = ((int) (foldRotation / 180.0f)) * 180;
            this.f7681g = this.f7680f + 180.0f;
            m1744d();
            return true;
        }

        /* renamed from: c */
        void m1745c() {
            this.f7678d = System.currentTimeMillis();
            this.f7680f = 0.0f;
            this.f7681g = this.f7680f + 180.0f;
            this.f7679e = 100.0f;
            if (FoldableListLayout.this.getCount() > 1) {
                m1744d();
            }
        }
    }
}
