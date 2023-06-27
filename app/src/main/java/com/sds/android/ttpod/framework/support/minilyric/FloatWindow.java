package com.sds.android.ttpod.framework.support.minilyric;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class FloatWindow {

    /* renamed from: a */
    private final Context f7158a;

    /* renamed from: b */
    private final WindowManager f7159b;

    /* renamed from: c */
    private boolean f7160c;

    /* renamed from: d */
    private View f7161d;

    /* renamed from: e */
    private View f7162e;

    /* renamed from: f */
    private boolean f7163f;

    /* renamed from: g */
    private int f7164g;

    /* renamed from: h */
    private boolean f7165h;

    /* renamed from: i */
    private boolean f7166i;

    /* renamed from: j */
    private boolean f7167j;

    /* renamed from: k */
    private int f7168k;

    /* renamed from: l */
    private int f7169l;

    /* renamed from: m */
    private int f7170m;

    /* renamed from: n */
    private int f7171n;

    /* renamed from: o */
    private int f7172o;

    /* renamed from: p */
    private int f7173p;

    /* renamed from: q */
    private final Handler f7174q;

    /* renamed from: r */
    private InterfaceC2071a f7175r;

    /* renamed from: s */
    private boolean f7176s;

    /* renamed from: t */
    private int f7177t;

    /* renamed from: u */
    private int f7178u;

    /* renamed from: v */
    private int f7179v;

    /* renamed from: w */
    private WeakReference<View> f7180w;

    /* renamed from: x */
    private ViewTreeObserver.OnScrollChangedListener f7181x;

    /* renamed from: y */
    private Runnable f7182y;

    /* renamed from: z */
    private Runnable f7183z;

    /* renamed from: com.sds.android.ttpod.framework.support.minilyric.FloatWindow$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2071a {
        /* renamed from: a */
        void m2345a();
    }

    public FloatWindow(Context context, AttributeSet attributeSet) {
        this.f7164g = 0;
        this.f7165h = true;
        this.f7166i = false;
        this.f7167j = true;
        this.f7174q = new Handler(Looper.getMainLooper());
        this.f7176s = false;
        this.f7181x = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.sds.android.ttpod.framework.support.minilyric.FloatWindow.1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                if (((View) FloatWindow.this.f7180w.get()) != null && FloatWindow.this.f7162e != null) {
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) FloatWindow.this.f7162e.getLayoutParams();
                    FloatWindow.this.m2362a(layoutParams.x, layoutParams.y, -1, -1, true);
                }
            }
        };
        this.f7182y = new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.FloatWindow.2
            @Override // java.lang.Runnable
            public void run() {
                FloatWindow.this.m2348e();
            }
        };
        this.f7183z = new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.FloatWindow.3
            @Override // java.lang.Runnable
            public void run() {
                FloatWindow.this.m2347f();
            }
        };
        this.f7158a = context;
        this.f7159b = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public FloatWindow() {
        this(null, 0, 0);
    }

    public FloatWindow(View view, int i, int i2) {
        this(view, i, i2, false);
    }

    public FloatWindow(View view, int i, int i2, boolean z) {
        this.f7164g = 0;
        this.f7165h = true;
        this.f7166i = false;
        this.f7167j = true;
        this.f7174q = new Handler(Looper.getMainLooper());
        this.f7176s = false;
        this.f7181x = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.sds.android.ttpod.framework.support.minilyric.FloatWindow.1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                if (((View) FloatWindow.this.f7180w.get()) != null && FloatWindow.this.f7162e != null) {
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) FloatWindow.this.f7162e.getLayoutParams();
                    FloatWindow.this.m2362a(layoutParams.x, layoutParams.y, -1, -1, true);
                }
            }
        };
        this.f7182y = new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.FloatWindow.2
            @Override // java.lang.Runnable
            public void run() {
                FloatWindow.this.m2348e();
            }
        };
        this.f7183z = new Runnable() { // from class: com.sds.android.ttpod.framework.support.minilyric.FloatWindow.3
            @Override // java.lang.Runnable
            public void run() {
                FloatWindow.this.m2347f();
            }
        };
        this.f7158a = view.getContext();
        this.f7159b = (WindowManager) this.f7158a.getSystemService(Context.WINDOW_SERVICE);
        m2361a(view);
        m2356b(i);
        m2365a(i2);
        m2359a(z);
    }

    /* renamed from: a */
    public void m2361a(View view) {
        if (!m2366a()) {
            this.f7161d = view;
        }
    }

    /* renamed from: a */
    public void m2359a(boolean z) {
        this.f7163f = z;
    }

    /* renamed from: b */
    public void m2354b(boolean z) {
        this.f7165h = z;
    }

    /* renamed from: a */
    public void m2365a(int i) {
        this.f7172o = i;
    }

    /* renamed from: b */
    public void m2356b(int i) {
        this.f7169l = i;
    }

    /* renamed from: a */
    public boolean m2366a() {
        return this.f7160c;
    }

    /* renamed from: a */
    public void m2364a(int i, int i2, int i3) {
        this.f7177t = i;
        this.f7178u = i2;
        this.f7179v = i3;
    }

    /* renamed from: b */
    public void m2357b() {
        this.f7174q.post(this.f7182y);
    }

    /* renamed from: d */
    @SuppressLint("WrongConstant")
    private WindowManager.LayoutParams m2350d() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 83;
        this.f7170m = this.f7169l;
        layoutParams.width = this.f7169l;
        this.f7173p = this.f7172o;
        layoutParams.height = this.f7172o;
        layoutParams.format = -3;
        layoutParams.flags = m2352c(layoutParams.flags);
        layoutParams.type = 2003;
        layoutParams.token = null;
        layoutParams.setTitle("FloatWindow:" + Integer.toHexString(hashCode()));
        return layoutParams;
    }

    /* renamed from: c */
    private int m2352c(int i) {
        int i2 = (-426521) & i;
        if (this.f7176s) {
            i2 |= 32768;
        }
        if (!this.f7163f) {
            i2 |= 8;
            if (this.f7164g == 1) {
                i2 |= 131072;
            }
        } else if (this.f7164g == 2) {
            i2 |= 131072;
        }
        if (!this.f7165h) {
            i2 |= 16;
        }
        if (this.f7166i) {
            i2 |= AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START;
        }
        if (!this.f7167j) {
            return i2 | 512;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m2348e() {
        if (!m2366a() && this.f7161d != null) {
            this.f7160c = true;
            WindowManager.LayoutParams m2350d = m2350d();
            this.f7162e = this.f7161d;
            if (this.f7177t == 0) {
                this.f7177t = 83;
            }
            m2350d.gravity = this.f7177t;
            m2350d.x = this.f7178u;
            m2350d.y = this.f7179v;
            if (this.f7162e.getParent() != null) {
                this.f7159b.removeView(this.f7162e);
            }
            try {
                this.f7159b.addView(this.f7162e, m2350d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m2347f() {
        if (m2366a() && this.f7162e != null) {
            m2346g();
            if (this.f7162e.getParent() != null) {
                this.f7159b.removeView(this.f7162e);
                if (this.f7162e != this.f7161d && (this.f7162e instanceof ViewGroup)) {
                    ((ViewGroup) this.f7162e).removeView(this.f7161d);
                }
            }
            this.f7162e = null;
            this.f7160c = false;
            if (this.f7175r != null) {
                this.f7175r.m2345a();
            }
        }
    }

    /* renamed from: c */
    public void m2353c() {
        this.f7174q.post(this.f7183z);
    }

    /* renamed from: a */
    private boolean m2358a(boolean z, WindowManager.LayoutParams layoutParams) {
        int m2352c = m2352c(layoutParams.flags);
        if (m2352c != layoutParams.flags) {
            layoutParams.flags = m2352c;
            return true;
        }
        return z;
    }

    /* renamed from: a */
    public void m2363a(int i, int i2, int i3, int i4) {
        m2362a(i, i2, i3, i4, false);
    }

    /* renamed from: a */
    public void m2362a(int i, int i2, int i3, int i4, boolean z) {
        WindowManager.LayoutParams layoutParams;
        boolean z2 = true;
        if (i3 != -1) {
            this.f7170m = i3;
            m2356b(i3);
        }
        if (i4 != -1) {
            this.f7173p = i4;
            m2365a(i4);
        }
        if (m2366a() && this.f7161d != null && (layoutParams = (WindowManager.LayoutParams) this.f7162e.getLayoutParams()) != null) {
            int i5 = this.f7168k < 0 ? this.f7168k : this.f7170m;
            if (i3 != -1 && layoutParams.width != i5) {
                this.f7170m = i5;
                layoutParams.width = i5;
                z = true;
            }
            int i6 = this.f7171n < 0 ? this.f7171n : this.f7173p;
            if (i4 != -1 && layoutParams.height != i6) {
                this.f7173p = i6;
                layoutParams.height = i6;
                z = true;
            }
            if (layoutParams.x != i) {
                layoutParams.x = i;
                z = true;
            }
            if (layoutParams.y != i2) {
                layoutParams.y = i2;
                z = true;
            }
            int m2352c = m2352c(layoutParams.flags);
            if (m2352c != layoutParams.flags) {
                layoutParams.flags = m2352c;
            } else {
                z2 = z;
            }
            if (m2358a(z2, layoutParams)) {
                this.f7159b.updateViewLayout(this.f7162e, layoutParams);
            }
        }
    }

    /* renamed from: g */
    private void m2346g() {
        WeakReference<View> weakReference = this.f7180w;
        View view = weakReference != null ? weakReference.get() : null;
        if (view != null) {
            view.getViewTreeObserver().removeOnScrollChangedListener(this.f7181x);
        }
        this.f7180w = null;
    }
}
