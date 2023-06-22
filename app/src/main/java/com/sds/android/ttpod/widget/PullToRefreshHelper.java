package com.sds.android.ttpod.widget;

import android.content.Context;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Scroller;

/* renamed from: com.sds.android.ttpod.widget.f */
/* loaded from: classes.dex */
public class PullToRefreshHelper {

    /* renamed from: a */
    private int f8341a;

    /* renamed from: b */
    private float f8342b;

    /* renamed from: c */
    private Scroller f8343c;

    /* renamed from: d */
    private int f8344d;

    /* renamed from: e */
    private Runnable f8345e;

    /* renamed from: f */
    private boolean f8346f;

    /* renamed from: g */
    private View f8347g;

    /* renamed from: h */
    private InterfaceC2285b f8348h;

    /* renamed from: i */
    private InterfaceC2284a f8349i;

    /* renamed from: j */
    private int f8350j = 0;

    /* compiled from: PullToRefreshHelper.java */
    /* renamed from: com.sds.android.ttpod.widget.f$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2284a {
        void onPullStateChanged(View view, int i);

        void onPullToRefresh(View view);
    }

    /* compiled from: PullToRefreshHelper.java */
    /* renamed from: com.sds.android.ttpod.widget.f$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2285b {
        /* renamed from: a */
        boolean mo1247a();

        View getActionView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PullToRefreshHelper(View view, InterfaceC2285b interfaceC2285b) {
        Context context = view.getContext();
        this.f8347g = view;
        this.f8343c = new Scroller(context, new AccelerateDecelerateInterpolator());
        this.f8348h = interfaceC2285b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1254a(InterfaceC2284a interfaceC2284a) {
        this.f8349i = interfaceC2284a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1256a(int i) {
        this.f8341a = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m1255a(MotionEvent motionEvent) {
        View actionView;
        if ((motionEvent.getAction() & 255) == 0 && (actionView = this.f8348h.getActionView()) != null) {
            this.f8346f = false;
            this.f8342b = motionEvent.getY();
            if (!this.f8343c.isFinished()) {
                this.f8343c.abortAnimation();
                return true;
            }
            ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
            this.f8344d = layoutParams == null ? actionView.getHeight() : layoutParams.height;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m1250b(int i) {
        this.f8350j = i;
        if (this.f8349i != null) {
            this.f8349i.onPullStateChanged(this.f8347g, this.f8350j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m1249b(MotionEvent motionEvent) {
        final ViewGroup.LayoutParams layoutParams;
        final View actionView = this.f8348h.getActionView();
        if (actionView == null || (layoutParams = actionView.getLayoutParams()) == null) {
            return false;
        }
        switch (motionEvent.getAction() & 255) {
            case 1:
            case 3:
                if (layoutParams.height != this.f8344d) {
                    if (this.f8350j == 2 && this.f8349i != null) {
                        this.f8349i.onPullToRefresh(this.f8347g);
                    }
                    this.f8343c.startScroll(0, layoutParams.height, 0, this.f8344d - layoutParams.height);
                    if (this.f8345e != null) {
                        this.f8347g.removeCallbacks(this.f8345e);
                        this.f8345e = null;
                    }
                    this.f8345e = new Runnable() { // from class: com.sds.android.ttpod.widget.f.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PullToRefreshHelper.this.f8343c.isFinished()) {
                                PullToRefreshHelper.this.f8345e = null;
                                PullToRefreshHelper.this.m1250b(0);
                                return;
                            }
                            if (PullToRefreshHelper.this.f8343c.computeScrollOffset()) {
                                layoutParams.height = PullToRefreshHelper.this.f8343c.getCurrY();
                                actionView.requestLayout();
                            }
                            ViewCompat.postOnAnimation(PullToRefreshHelper.this.f8347g, this);
                        }
                    };
                    ViewCompat.postOnAnimation(this.f8347g, this.f8345e);
                    return true;
                } else if (this.f8350j != 0) {
                    m1250b(0);
                    return false;
                } else {
                    return false;
                }
            case 2:
                float y = motionEvent.getY() - this.f8342b;
                this.f8342b = motionEvent.getY();
                int i = layoutParams.height;
                if (i > this.f8344d || this.f8348h.mo1247a()) {
                    float max = Math.max(this.f8344d, Math.min(y + i, this.f8341a));
                    if (max != i) {
                        layoutParams.height = (int) max;
                        actionView.requestLayout();
                    }
                    if (!this.f8346f) {
                        if (max != this.f8344d) {
                            motionEvent.setAction((motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | 3);
                            this.f8346f = true;
                            m1250b(1);
                            return false;
                        }
                        return false;
                    }
                    if (max == this.f8341a && this.f8350j != 2) {
                        m1250b(2);
                    } else if (max == this.f8344d && this.f8350j != 3) {
                        m1250b(3);
                    }
                    return true;
                }
                return false;
            case 4:
            default:
                return false;
            case 5:
            case 6:
                return layoutParams.height != this.f8344d || this.f8346f;
        }
    }
}
