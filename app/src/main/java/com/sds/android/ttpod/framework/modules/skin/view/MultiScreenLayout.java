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
import com.sds.android.ttpod.framework.p106a.BitmapUtils;

/* loaded from: classes.dex */
public class MultiScreenLayout extends SkinAbsoluteLayout {

    /* renamed from: a */
    private int dp5;

    /* renamed from: b */
    private int maxScreen;

    /* renamed from: c */
    private int f6829c;

    /* renamed from: d */
    private float distanceX;

    /* renamed from: e */
    private int currentOffsetX;

    /* renamed from: f */
    private Scroller scroller;

    /* renamed from: g */
    private int scaledTouchSlop;

    /* renamed from: h */
    private int f6834h;

    /* renamed from: i */
    private ScreenChangeListener screenChangeListener;

    /* renamed from: j */
    private int currentScreen;

    /* renamed from: k */
    private int f6837k;

    /* renamed from: l */
    private VelocityTracker velocityTracker;

    /* renamed from: m */
    private boolean enableBoundaryBounce;

    /* renamed from: n */
    private boolean enableBackgroundOffset;

    /* renamed from: o */
    private C2000a f6841o;

    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout$b */
    /* loaded from: classes.dex */
    public interface ScreenChangeListener {
        /* renamed from: a */
        void changed(int i, int i2);
    }

    /* renamed from: a */
    public void m3406a(int i) {
        int max = Math.max(0, Math.min(i, this.maxScreen - 1));
        if (this.f6834h <= 0) {
            int i2 = this.currentScreen;
            if (i2 != max) {
                if (isLayoutRequested()) {
                    this.f6837k = max;
                    return;
                }
                this.f6837k = -1;
                this.currentScreen = max;
                if (this.screenChangeListener != null) {
                    this.screenChangeListener.changed(this.currentScreen, i2);
                    return;
                }
                return;
            }
            return;
        }
        if (!this.scroller.isFinished()) {
            this.scroller.abortAnimation();
        }
        int i3 = ((-max) * this.f6834h) - this.currentOffsetX;
        if (max != this.currentScreen || i3 != 0) {
            this.f6837k = max;
            this.scroller.startScroll(this.currentOffsetX, 0, i3, 0, Math.abs(i3 >> 1));
            invalidate();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout, android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    public int getCurrentScreen() {
        return this.currentScreen;
    }

    public int getCurrentOffsetX() {
        return this.currentOffsetX;
    }

    public int getMaxScreen() {
        return this.maxScreen;
    }

    public void setMaxScreen(int i) {
        this.maxScreen = i;
    }

    public void setEnableBoundaryBounce(boolean z) {
        this.enableBoundaryBounce = z;
    }

    public void setEnableBackgroundOffset(boolean z) {
        this.enableBackgroundOffset = z;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 2 || this.f6829c == 0) {
            float x = motionEvent.getX();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    this.distanceX = x;
                    this.f6829c = this.scroller.isFinished() ? 0 : 1;
                    break;
                case 1:
                case 3:
                    this.f6829c = 0;
                    break;
                case 2:
                    if (((int) Math.abs(x - this.distanceX)) > this.scaledTouchSlop) {
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
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        switch (action) {
            case 0:
                if (!this.scroller.isFinished()) {
                    this.scroller.abortAnimation();
                }
                this.distanceX = x;
                break;
            case 1:
            case 3:
                if (this.f6829c == 1) {
                    if (this.velocityTracker != null) {
                        VelocityTracker velocityTracker = this.velocityTracker;
                        velocityTracker.computeCurrentVelocity(1000);
                        int xVelocity = (int) velocityTracker.getXVelocity();
                        if (xVelocity > 500) {
                            m3406a(this.currentScreen - 1);
                        } else if (xVelocity < -500) {
                            m3406a(this.currentScreen + 1);
                        } else {
                            m3407a();
                        }
                        this.velocityTracker.recycle();
                        this.velocityTracker = null;
                    }
                } else if (isClickable() && action == 1) {
                    performClick();
                }
                this.f6829c = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                int i = (int) (this.distanceX - x);
                this.distanceX = x;
                if (this.f6829c != 1 && Math.abs(i) > this.dp5) {
                    this.f6829c = 1;
                }
                if (this.f6829c == 1 && (this.enableBoundaryBounce || ((i < 0 && this.currentScreen > 0) || (i > 0 && this.currentScreen < this.maxScreen - 1)))) {
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
        int i2 = this.currentScreen;
        int i3 = this.f6834h * this.currentScreen;
        if (this.currentOffsetX < 0) {
            i = i3 + this.currentOffsetX;
        } else {
            i = this.currentOffsetX - i3;
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
        this.maxScreen = 1;
        this.currentOffsetX = 0;
        this.screenChangeListener = null;
        this.currentScreen = -1;
        this.f6837k = 0;
        this.enableBoundaryBounce = true;
        this.enableBackgroundOffset = true;
        this.f6841o = new C2000a();
        m3405a(context);
    }

    public MultiScreenLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxScreen = 1;
        this.currentOffsetX = 0;
        this.screenChangeListener = null;
        this.currentScreen = -1;
        this.f6837k = 0;
        this.enableBoundaryBounce = true;
        this.enableBackgroundOffset = true;
        this.f6841o = new C2000a();
        m3405a(context);
    }

    public MultiScreenLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maxScreen = 1;
        this.currentOffsetX = 0;
        this.screenChangeListener = null;
        this.currentScreen = -1;
        this.f6837k = 0;
        this.enableBoundaryBounce = true;
        this.enableBackgroundOffset = true;
        this.f6841o = new C2000a();
        m3405a(context);
    }

    /* renamed from: a */
    private void m3405a(Context context) {
        this.scroller = new Scroller(context, new AccelerateDecelerateInterpolator());
        this.scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() << 1;
        setAlwaysDrawnWithCacheEnabled(true);
        this.dp5 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                , 5.0f, context.getResources().getDisplayMetrics());
    }

    public void setScreenChangeListener(ScreenChangeListener screenChangeListener) {
        this.screenChangeListener = screenChangeListener;
    }

    public ScreenChangeListener getScreenChangeListener() {
        return this.screenChangeListener;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        this.f6841o.setBackground(drawable);
        setWillNotDraw(drawable == null);
    }

    public void setSecondBackgroundBitmap(Bitmap bitmap) {
        this.f6841o.setSecondBackgroundBitmap(bitmap);
    }

    public void setEnableSecondBackground(boolean z) {
        this.f6841o.setEnableSecondBackground(z);
    }

    public void setSecondBackgroundBlurRadius(int i) {
        this.f6841o.setSecondBackgroundBlurRadius(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int i5 = this.currentScreen;
        if (this.f6837k != -1 && this.f6837k != this.currentScreen) {
            this.currentScreen = this.f6837k;
            this.f6837k = -1;
        }
        super.onLayout(changed, left, top, right, bottom);
        if (this.screenChangeListener != null) {
            if (i5 != this.currentScreen || changed) {
                this.screenChangeListener.changed(this.currentScreen, i5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout
    /* renamed from: a */
    public void layout(boolean z, int i, int i2, int i3, int i4) {
        super.layout(z, i, i2, i3, i4);
        if (z) {
            this.f6834h = i3 - i;
            int i5 = this.currentScreen * this.f6834h;
            setChildPositionOffset(i5);
            this.f6841o.m3396a(i5, true);
        }
    }

    private void setChildPositionOffset(int i) {
        if (this.currentOffsetX == 0) {
            this.currentOffsetX = 0;
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
            this.currentOffsetX += i2;
        }
        invalidate();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.scroller.computeScrollOffset()) {
            int currX = this.currentOffsetX - this.scroller.getCurrX();
            this.f6841o.m3386b(currX);
            m3402b(currX);
        } else if (this.f6837k != -1) {
            int i = this.currentScreen;
            if (this.currentScreen != this.f6837k) {
                this.currentScreen = this.f6837k;
            }
            if (this.screenChangeListener != null) {
                this.screenChangeListener.changed(this.currentScreen, i);
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
        private TransitionDrawable transitionDrawable;

        /* renamed from: d */
        private MovableBitmapDrawable f6845d;

        /* renamed from: e */
        private Drawable f6846e;

        /* renamed from: f */
        private Drawable f6847f;

        /* renamed from: g */
        private Bitmap secondBackgroundBitmap;

        /* renamed from: h */
        private boolean enableSecondBackground;

        /* renamed from: i */
        private int secondBackgroundBlurRadius;

        /* renamed from: j */
        private Rect f6851j;

        private C2000a() {
            this.transitionDrawable = null;
            this.f6845d = null;
            this.enableSecondBackground = false;
            this.f6851j = new Rect();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void setBackground(Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                if (MultiScreenLayout.this.enableBackgroundOffset) {
                    if (!(drawable instanceof MovableBitmapDrawable)) {
                        drawable = new MovableBitmapDrawable(MultiScreenLayout.this.getResources()
                                , ((BitmapDrawable) drawable).getBitmap());
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
            if (this.enableSecondBackground) {
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
        public void setSecondBackgroundBlurRadius(int i) {
            this.secondBackgroundBlurRadius = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m3395a(Bitmap bitmap) {
            BitmapDrawable bitmapDrawable = null;
            if (bitmap != null) {
                if (MultiScreenLayout.this.enableBackgroundOffset) {
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
        public void setSecondBackgroundBitmap(final Bitmap bitmap) {
            if (this.enableSecondBackground) {
                if (this.secondBackgroundBitmap != bitmap) {
                    this.secondBackgroundBitmap = bitmap;
                    if (this.secondBackgroundBlurRadius > 1 && bitmap != null) {
                        if (this.f6843b != null) {
                            this.f6843b.cancel(true);
                        }
                        this.f6843b = new AsyncTask<Object, Object, Bitmap>() { // from class: com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout.a.1
                            /* JADX INFO: Access modifiers changed from: protected */
                            @Override // android.os.AsyncTask
                            /* renamed from: a */
                            public Bitmap doInBackground(Object... objArr) {
                                try {
                                    return BitmapUtils.m4791a(MultiScreenLayout.this.getContext(), bitmap, C2000a.this.secondBackgroundBlurRadius);
                                } catch (Throwable th) {
                                    th.printStackTrace();
                                    return null;
                                }
                            }

                            /* JADX INFO: Access modifiers changed from: protected */
                            @Override // android.os.AsyncTask
                            /* renamed from: a */
                            public void onPostExecute(Bitmap bitmap2) {
                                if (bitmap2 != null && bitmap == C2000a.this.secondBackgroundBitmap) {
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
        public void setEnableSecondBackground(boolean z) {
            this.enableSecondBackground = z;
        }

        /* renamed from: c */
        private void m3380c(Drawable drawable) {
            if (drawable instanceof MovableBitmapDrawable) {
                this.f6845d = (MovableBitmapDrawable) drawable;
                m3396a(-MultiScreenLayout.this.currentOffsetX, true);
            } else {
                this.f6845d = null;
            }
            if (this.transitionDrawable == null) {
                this.transitionDrawable = new TransitionDrawable();
                this.transitionDrawable.setCrossFade(true);
                MultiScreenLayout.super.setBackground(this.transitionDrawable);
            }
            if (MultiScreenLayout.this.getGlobalVisibleRect(this.f6851j)) {
                this.transitionDrawable.setDrawable(drawable);
            } else {
                this.transitionDrawable.m3707b(drawable);
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
                    int i2 = MultiScreenLayout.this.f6834h * (MultiScreenLayout.this.maxScreen - 1);
                    if ((z || (MultiScreenLayout.this.currentOffsetX <= 0 && MultiScreenLayout.this.currentOffsetX >= (-i2))) && i != 0) {
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
