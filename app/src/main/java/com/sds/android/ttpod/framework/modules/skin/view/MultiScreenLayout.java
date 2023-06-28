package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Scroller;
import com.sds.android.ttpod.framework.modules.skin.SkinLayoutParams;
import com.sds.android.ttpod.framework.modules.skin.p130c.ClipBitmapDrawable;
import com.sds.android.ttpod.framework.modules.skin.p130c.MovableBitmapDrawable;
import com.sds.android.ttpod.framework.modules.skin.p130c.TransitionDrawable;
import com.sds.android.ttpod.framework.p106a.C1780b;

/* loaded from: classes.dex */
public class MultiScreenLayout extends SkinAbsoluteLayout {

    /* renamed from: a */
    private int f6827a;

    /* renamed from: b */
    private int f6828b;

    /* renamed from: c */
    private int f6829c;

    /* renamed from: d */
    private float f6830d;

    /* renamed from: e */
    private int f6831e;

    /* renamed from: f */
    private Scroller f6832f;

    /* renamed from: g */
    private int f6833g;

    /* renamed from: h */
    private int f6834h;

    /* renamed from: i */
    private InterfaceC2002b f6835i;

    /* renamed from: j */
    private int f6836j;

    /* renamed from: k */
    private int f6837k;

    /* renamed from: l */
    private VelocityTracker f6838l;

    /* renamed from: m */
    private boolean f6839m;

    /* renamed from: n */
    private boolean f6840n;

    /* renamed from: o */
    private C2000a f6841o;

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2002b {
        /* renamed from: a */
        void mo3376a(int i, int i2);
    }

    /* renamed from: a */
    public void m3406a(int i) {
        int max = Math.max(0, Math.min(i, this.f6828b - 1));
        if (this.f6834h <= 0) {
            int i2 = this.f6836j;
            if (i2 != max) {
                if (isLayoutRequested()) {
                    this.f6837k = max;
                    return;
                }
                this.f6837k = -1;
                this.f6836j = max;
                if (this.f6835i != null) {
                    this.f6835i.mo3376a(this.f6836j, i2);
                    return;
                }
                return;
            }
            return;
        }
        if (!this.f6832f.isFinished()) {
            this.f6832f.abortAnimation();
        }
        int i3 = ((-max) * this.f6834h) - this.f6831e;
        if (max != this.f6836j || i3 != 0) {
            this.f6837k = max;
            this.f6832f.startScroll(this.f6831e, 0, i3, 0, Math.abs(i3 >> 1));
            invalidate();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout, android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    public int getCurrentScreen() {
        return this.f6836j;
    }

    public int getCurrentOffsetX() {
        return this.f6831e;
    }

    public int getMaxScreen() {
        return this.f6828b;
    }

    public void setMaxScreen(int i) {
        this.f6828b = i;
    }

    public void setEnableBoundaryBounce(boolean z) {
        this.f6839m = z;
    }

    public void setEnableBackgroundOffset(boolean z) {
        this.f6840n = z;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 2 || this.f6829c == 0) {
            float x = motionEvent.getX();
            switch (action) {
                case 0:
                    this.f6830d = x;
                    this.f6829c = this.f6832f.isFinished() ? 0 : 1;
                    break;
                case 1:
                case 3:
                    this.f6829c = 0;
                    break;
                case 2:
                    if (((int) Math.abs(x - this.f6830d)) > this.f6833g) {
                        this.f6829c = 1;
                        break;
                    } else {
                        this.f6829c = 0;
                        break;
                    }
            }
            return this.f6829c != 0;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f6838l == null) {
            this.f6838l = VelocityTracker.obtain();
        }
        this.f6838l.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        switch (action) {
            case 0:
                if (!this.f6832f.isFinished()) {
                    this.f6832f.abortAnimation();
                }
                this.f6830d = x;
                break;
            case 1:
            case 3:
                if (this.f6829c == 1) {
                    if (this.f6838l != null) {
                        VelocityTracker velocityTracker = this.f6838l;
                        velocityTracker.computeCurrentVelocity(1000);
                        int xVelocity = (int) velocityTracker.getXVelocity();
                        if (xVelocity > 500) {
                            m3406a(this.f6836j - 1);
                        } else if (xVelocity < -500) {
                            m3406a(this.f6836j + 1);
                        } else {
                            m3407a();
                        }
                        this.f6838l.recycle();
                        this.f6838l = null;
                    }
                } else if (isClickable() && action == 1) {
                    performClick();
                }
                this.f6829c = 0;
                break;
            case 2:
                int i = (int) (this.f6830d - x);
                this.f6830d = x;
                if (this.f6829c != 1 && Math.abs(i) > this.f6827a) {
                    this.f6829c = 1;
                }
                if (this.f6829c == 1 && (this.f6839m || ((i < 0 && this.f6836j > 0) || (i > 0 && this.f6836j < this.f6828b - 1)))) {
                    this.f6841o.m3386b(i);
                    m3402b(i);
                    break;
                }
                break;
        }
        return true;
    }

    /* renamed from: a */
    private void m3407a() {
        int i;
        int i2 = this.f6836j;
        int i3 = this.f6834h * this.f6836j;
        if (this.f6831e < 0) {
            i = i3 + this.f6831e;
        } else {
            i = this.f6831e - i3;
        }
        if (i != 0) {
            int i4 = this.f6834h >> 1;
            if (i > i4) {
                m3406a(i2 - 1);
            } else if (i < (-i4)) {
                m3406a(i2 + 1);
            } else {
                m3406a(i2);
            }
        }
    }

    public MultiScreenLayout(Context context) {
        super(context);
        this.f6828b = 1;
        this.f6831e = 0;
        this.f6835i = null;
        this.f6836j = -1;
        this.f6837k = 0;
        this.f6839m = true;
        this.f6840n = true;
        this.f6841o = new C2000a();
        m3405a(context);
    }

    public MultiScreenLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6828b = 1;
        this.f6831e = 0;
        this.f6835i = null;
        this.f6836j = -1;
        this.f6837k = 0;
        this.f6839m = true;
        this.f6840n = true;
        this.f6841o = new C2000a();
        m3405a(context);
    }

    public MultiScreenLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6828b = 1;
        this.f6831e = 0;
        this.f6835i = null;
        this.f6836j = -1;
        this.f6837k = 0;
        this.f6839m = true;
        this.f6840n = true;
        this.f6841o = new C2000a();
        m3405a(context);
    }

    /* renamed from: a */
    private void m3405a(Context context) {
        this.f6832f = new Scroller(context, new AccelerateDecelerateInterpolator());
        this.f6833g = ViewConfiguration.get(context).getScaledTouchSlop() << 1;
        setAlwaysDrawnWithCacheEnabled(true);
        this.f6827a = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5.0f, context.getResources().getDisplayMetrics());
    }

    public void setScreenChangeListener(InterfaceC2002b interfaceC2002b) {
        this.f6835i = interfaceC2002b;
    }

    public InterfaceC2002b getScreenChangeListener() {
        return this.f6835i;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        this.f6841o.m3394a(drawable);
        setWillNotDraw(drawable == null);
    }

    public void setSecondBackgroundBitmap(Bitmap bitmap) {
        this.f6841o.m3385b(bitmap);
    }

    public void setEnableSecondBackground(boolean z) {
        this.f6841o.m3387a(z);
    }

    public void setSecondBackgroundBlurRadius(int i) {
        this.f6841o.m3397a(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = this.f6836j;
        if (this.f6837k != -1 && this.f6837k != this.f6836j) {
            this.f6836j = this.f6837k;
            this.f6837k = -1;
        }
        super.onLayout(z, i, i2, i3, i4);
        if (this.f6835i != null) {
            if (i5 != this.f6836j || z) {
                this.f6835i.mo3376a(this.f6836j, i5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout
    /* renamed from: a */
    public void mo3363a(boolean z, int i, int i2, int i3, int i4) {
        super.mo3363a(z, i, i2, i3, i4);
        if (z) {
            this.f6834h = i3 - i;
            int i5 = this.f6836j * this.f6834h;
            setChildPositionOffset(i5);
            this.f6841o.m3396a(i5, true);
        }
    }

    private void setChildPositionOffset(int i) {
        if (this.f6831e == 0) {
            this.f6831e = 0;
            m3402b(i);
        }
    }

    /* renamed from: b */
    private void m3402b(int i) {
        if (i != 0) {
            int i2 = -i;
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                SkinLayoutParams m3559a = SkinLayoutParams.m3559a(childAt);
                if (m3559a != null && m3559a.m3546g() >= 0) {
                    m3559a.m3560a(i2, 0);
                    childAt.offsetLeftAndRight(i2);
                }
            }
            this.f6831e += i2;
        }
        invalidate();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f6832f.computeScrollOffset()) {
            int currX = this.f6831e - this.f6832f.getCurrX();
            this.f6841o.m3386b(currX);
            m3402b(currX);
        } else if (this.f6837k != -1) {
            int i = this.f6836j;
            if (this.f6836j != this.f6837k) {
                this.f6836j = this.f6837k;
            }
            if (this.f6835i != null) {
                this.f6835i.mo3376a(this.f6836j, i);
            }
            this.f6837k = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout$a */
    /* loaded from: classes.dex */
    public final class C2000a {

        /* renamed from: b */
        private AsyncTask<Object, Object, Bitmap> f6843b;

        /* renamed from: c */
        private TransitionDrawable f6844c;

        /* renamed from: d */
        private MovableBitmapDrawable f6845d;

        /* renamed from: e */
        private Drawable f6846e;

        /* renamed from: f */
        private Drawable f6847f;

        /* renamed from: g */
        private Bitmap f6848g;

        /* renamed from: h */
        private boolean f6849h;

        /* renamed from: i */
        private int f6850i;

        /* renamed from: j */
        private Rect f6851j;

        private C2000a() {
            this.f6844c = null;
            this.f6845d = null;
            this.f6849h = false;
            this.f6851j = new Rect();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m3394a(Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                if (MultiScreenLayout.this.f6840n) {
                    if (!(drawable instanceof MovableBitmapDrawable)) {
                        drawable = new MovableBitmapDrawable(MultiScreenLayout.this.getResources(), ((BitmapDrawable) drawable).getBitmap());
                    }
                } else {
                    drawable = m3379d(drawable);
                }
            }
            if (this.f6846e != drawable) {
                this.f6846e = drawable;
                if (this.f6847f == null) {
                    m3380c(drawable);
                }
            }
        }

        /* renamed from: b */
        private void m3384b(Drawable drawable) {
            if (this.f6849h) {
                if (this.f6847f != drawable) {
                    this.f6847f = drawable;
                    if (drawable == null) {
                        drawable = this.f6846e;
                    }
                    m3380c(drawable);
                    return;
                }
                return;
            }
            m3398a();
        }

        /* renamed from: a */
        private void m3398a() {
            if (this.f6847f != null) {
                this.f6847f = null;
                m3380c(this.f6846e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m3397a(int i) {
            this.f6850i = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m3395a(Bitmap bitmap) {
            BitmapDrawable bitmapDrawable = null;
            if (bitmap != null) {
                if (MultiScreenLayout.this.f6840n) {
                    bitmapDrawable = new MovableBitmapDrawable(MultiScreenLayout.this.getResources(), bitmap);
                } else {
                    BitmapDrawable bitmapDrawable2 = this.f6846e instanceof BitmapDrawable ? (BitmapDrawable) this.f6846e : null;
                    if (bitmapDrawable2 == null || (bitmapDrawable2.getTileModeX() == null && bitmapDrawable2.getTileModeY() == null)) {
                        bitmapDrawable = new ClipBitmapDrawable(MultiScreenLayout.this.getResources(), bitmap);
                    } else {
                        bitmapDrawable = new BitmapDrawable(MultiScreenLayout.this.getResources(), bitmap);
                        bitmapDrawable.setTileModeXY(bitmapDrawable2.getTileModeX(), bitmapDrawable2.getTileModeY());
                    }
                }
            }
            m3384b(bitmapDrawable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m3385b(final Bitmap bitmap) {
            if (this.f6849h) {
                if (this.f6848g != bitmap) {
                    this.f6848g = bitmap;
                    if (this.f6850i > 1 && bitmap != null) {
                        if (this.f6843b != null) {
                            this.f6843b.cancel(true);
                        }
                        this.f6843b = new AsyncTask<Object, Object, Bitmap>() { // from class: com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout.a.1
                            /* JADX INFO: Access modifiers changed from: protected */
                            @Override // android.os.AsyncTask
                            /* renamed from: a */
                            public Bitmap doInBackground(Object... objArr) {
                                try {
                                    return C1780b.m4791a(MultiScreenLayout.this.getContext(), bitmap, C2000a.this.f6850i);
                                } catch (Throwable th) {
                                    th.printStackTrace();
                                    return null;
                                }
                            }

                            /* JADX INFO: Access modifiers changed from: protected */
                            @Override // android.os.AsyncTask
                            /* renamed from: a */
                            public void onPostExecute(Bitmap bitmap2) {
                                if (bitmap2 != null && bitmap == C2000a.this.f6848g) {
                                    C2000a.this.m3395a(bitmap2);
                                }
                            }
                        };
                        this.f6843b.execute(new Object[0]);
                        return;
                    }
                    m3395a(bitmap);
                    return;
                }
                return;
            }
            m3398a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m3387a(boolean z) {
            this.f6849h = z;
        }

        /* renamed from: c */
        private void m3380c(Drawable drawable) {
            if (drawable instanceof MovableBitmapDrawable) {
                this.f6845d = (MovableBitmapDrawable) drawable;
                m3396a(-MultiScreenLayout.this.f6831e, true);
            } else {
                this.f6845d = null;
            }
            if (this.f6844c == null) {
                this.f6844c = new TransitionDrawable();
                this.f6844c.m3709a(true);
                MultiScreenLayout.super.setBackground(this.f6844c);
            }
            if (MultiScreenLayout.this.getGlobalVisibleRect(this.f6851j)) {
                this.f6844c.m3710a(drawable);
            } else {
                this.f6844c.m3707b(drawable);
            }
        }

        /* renamed from: d */
        private Drawable m3379d(Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if (bitmapDrawable.getTileModeX() == null && bitmapDrawable.getTileModeY() == null && !(bitmapDrawable instanceof ClipBitmapDrawable)) {
                    return new ClipBitmapDrawable(MultiScreenLayout.this.getResources(), bitmapDrawable.getBitmap());
                }
                return drawable;
            }
            return drawable;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m3396a(int i, boolean z) {
            if (this.f6845d != null) {
                this.f6845d.setBounds(0, 0, MultiScreenLayout.this.getWidth(), MultiScreenLayout.this.getHeight());
                int intrinsicWidth = MultiScreenLayout.this.f6834h - this.f6845d.getIntrinsicWidth();
                if (intrinsicWidth < 0) {
                    int i2 = MultiScreenLayout.this.f6834h * (MultiScreenLayout.this.f6828b - 1);
                    if ((z || (MultiScreenLayout.this.f6831e <= 0 && MultiScreenLayout.this.f6831e >= (-i2))) && i != 0) {
                        if (i2 == 0) {
                            i2 = 1;
                        }
                        int abs = ((Math.abs(i * intrinsicWidth) + i2) - 1) / i2;
                        int m3727a = (z ? 0 : this.f6845d.m3727a()) + (i > 0 ? -abs : abs);
                        if (m3727a > 0) {
                            m3727a = 0;
                        }
                        if (m3727a < intrinsicWidth) {
                            m3727a = intrinsicWidth;
                        }
                        this.f6845d.m3726a(m3727a);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m3386b(int i) {
            m3396a(i, false);
        }
    }
}
