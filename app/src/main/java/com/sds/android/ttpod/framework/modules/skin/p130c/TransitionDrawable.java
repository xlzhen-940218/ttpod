package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.SystemClock;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.m */
/* loaded from: classes.dex */
public class TransitionDrawable extends LayerDrawable implements Drawable.Callback {
    /**
     * A transition is about to start.
     */
    private static final int TRANSITION_STARTING = 0;
    /**
     * The transition has started and the animation is in progress
     */
    private static final int TRANSITION_RUNNING = 1;
    /**
     * No transition will be applied
     */
    private static final int TRANSITION_NONE = 2;
    /* renamed from: b */
    private static final Drawable f6546b = new ColorDrawable(0);

    /* renamed from: a */
    private int mTransitionState;

    /* renamed from: c */
    private boolean mReverse;

    /* renamed from: d */
    private long mStartTimeMillis;

    /* renamed from: e */
    private int mFrom;

    /* renamed from: f */
    private int mTo;

    /* renamed from: g */
    private int mDuration;

    /* renamed from: h */
    private int mOriginalDuration;

    /* renamed from: i */
    private int mAlpha;

    /* renamed from: j */
    private boolean mCrossFade;

    /* renamed from: k */
    private Drawable f6556k;

    public TransitionDrawable() {
        super(new Drawable[]{f6546b, f6546b});
        this.mTransitionState = TRANSITION_NONE;
        this.mAlpha = 0;
        for (int numberOfLayers = getNumberOfLayers() - 1; numberOfLayers >= 0; numberOfLayers--) {
            super.setId(numberOfLayers, numberOfLayers);
        }
    }

    @Override // android.graphics.drawable.LayerDrawable
    public void setId(int i, int i2) {
    }

    @Override // android.graphics.drawable.LayerDrawable
    public boolean setDrawableByLayerId(int i, Drawable drawable) {
        if (drawable == null) {
            drawable = f6546b;
        }
        boolean drawableByLayerId = super.setDrawableByLayerId(i, drawable);
        if (drawableByLayerId && drawable.getBounds().isEmpty()) {
            drawable.setBounds(getBounds());
        }
        return drawableByLayerId;
    }

    /* renamed from: a */
    private void startTransition(int durationMillis) {
        this.mFrom = 0;
        this.mTo = 255;
        this.mAlpha = 0;
        this.mDuration = durationMillis;
        this.mOriginalDuration = durationMillis;
        this.mReverse = false;
        this.mTransitionState = TRANSITION_STARTING;
        invalidateSelf();
    }

    /* renamed from: a */
    public void setDrawable(Drawable drawable) {
        if (!m3706c()) {
            setDrawableByLayerId(1, drawable);
            startTransition(1000);
        }
        if (drawable == null) {
            drawable = f6546b;
        }
        this.f6556k = drawable;
    }

    /* renamed from: b */
    public void m3707b(Drawable drawable) {
        resetTransition();
        m3712a();
        setDrawableByLayerId(0, drawable);
        setDrawableByLayerId(1, drawable);
    }

    /* renamed from: a */
    public void m3712a() {
        this.f6556k = null;
    }

    /* renamed from: c */
    private boolean m3706c() {
        return this.f6556k != null;
    }

    /* renamed from: b */
    public void resetTransition() {
        this.mAlpha = 0;
        this.mTransitionState = TRANSITION_NONE;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        Drawable drawable;
        switch (this.mTransitionState) {
            case 0:
                this.mStartTimeMillis = SystemClock.uptimeMillis();
                this.mTransitionState = TRANSITION_RUNNING;
                z = false;
                break;
            case 1:
                if (this.mStartTimeMillis < 0) {
                    z = true;
                    break;
                } else {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.mStartTimeMillis)) / this.mDuration;
                    z = uptimeMillis >= 1.0f;
                    this.mAlpha = (int) ((Math.min(uptimeMillis, 1.0f) * (this.mTo - this.mFrom)) + this.mFrom);
                    break;
                }
            default:
                z = true;
                break;
        }
        int i = this.mAlpha;
        boolean z2 = this.mCrossFade;
        Drawable drawable2 = getDrawable(0);
        Drawable drawable3 = getDrawable(1);
        if (z) {
            if (drawable2 != drawable3) {
                setDrawableByLayerId(0, drawable3);
                drawable = drawable3;
            } else {
                drawable = drawable2;
            }
            drawable.draw(canvas);
            if (m3706c()) {
                if (drawable != this.f6556k) {
                    Drawable drawable4 = this.f6556k;
                    this.f6556k = null;
                    setDrawable(drawable4);
                    return;
                }
                this.f6556k = null;
                return;
            }
            return;
        }
        if (z2) {
            drawable2.setAlpha(255 - i);
        }
        drawable2.draw(canvas);
        if (z2) {
            drawable2.setAlpha(255);
        }
        if (i > 0) {
            drawable3.setAlpha(i);
            drawable3.draw(canvas);
            drawable3.setAlpha(255);
        }
        invalidateSelf();
    }

    /* renamed from: a */
    public void setCrossFade(boolean z) {
        this.mCrossFade = z;
    }
}
