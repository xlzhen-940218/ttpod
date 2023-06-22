package com.sds.android.ttpod.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(11)
/* loaded from: classes.dex */
public class GuideAnimatorView extends View implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private Bitmap f7688a;

    /* renamed from: b */
    private Bitmap[] f7689b;

    /* renamed from: c */
    private  ArrayList<GuideHolder> f7690c;

    /* renamed from: d */
    private long f7691d;

    public GuideAnimatorView(Context context) {
        super(context);
    }

    public GuideAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDrawableResourceId(int... iArr) {
        this.f7688a = null;
        int length = iArr != null ? iArr.length : 0;
        this.f7689b = new Bitmap[length];
        for (int i = 0; i < length; i++) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(iArr[i]);
            if (this.f7688a == null) {
                this.f7688a = bitmapDrawable.getBitmap();
            }
            this.f7689b[i] = bitmapDrawable.getBitmap();
        }
    }

    /* renamed from: b */
    private GuideHolder m1738b(int i, int i2) {
        GuideHolder guideHolder = new GuideHolder(this.f7689b);
        guideHolder.setPaint(new Paint(1));
        guideHolder.setX(i);
        guideHolder.setY(i2);
        return guideHolder;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<GuideHolder> it = this.f7690c.iterator();
        while (it.hasNext()) {
            GuideHolder next = it.next();
            canvas.save();
            canvas.translate(next.getX(), next.getY());
            next.drawBitmap(canvas);
            canvas.restore();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            m1740a((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return false;
    }

    /* renamed from: a */
    public boolean m1740a(int i, int i2) {
        GuideHolder m1738b = m1738b(i, i2);
        int y = m1738b.getY();
        int height = this.f7688a.getHeight();
        int height2 = getHeight() - (height >> 1);
        if (height2 - y < height) {
            return false;
        }
        this.f7690c.add(m1738b);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(m1738b, "width", 0, this.f7688a.getWidth());
        ofInt.setDuration(300L);
        ofInt.addUpdateListener(this);
        ObjectAnimator ofInt2 = ObjectAnimator.ofInt(m1738b, "height", 0, this.f7688a.getHeight());
        ofInt2.setDuration(300L);
        ofInt2.addUpdateListener(this);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(m1738b, "alpha", 0.0f, 1.0f);
        ofFloat.setDuration(300L);
        ofFloat.addUpdateListener(this);
        ObjectAnimator ofInt3 = ObjectAnimator.ofInt(m1738b, "y", y, height2);
        ofInt3.setDuration(500L);
        ofInt3.setInterpolator(new AccelerateInterpolator());
        ofInt3.addUpdateListener(this);
        ObjectAnimator ofInt4 = ObjectAnimator.ofInt(m1738b, "x", m1738b.getX(), (-this.f7688a.getWidth()) / 2);
        ofInt4.setDuration(1500L);
        ofInt4.setInterpolator(new AnticipateInterpolator());
        ofInt4.addListener(new AnimatorListenerAdapter() { // from class: com.sds.android.ttpod.widget.GuideAnimatorView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ((GuideHolder) ((ObjectAnimator) animator).getTarget()).setAnimationEnabled(true);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                GuideAnimatorView.this.f7690c.remove(((ObjectAnimator) animator).getTarget());
                GuideAnimatorView.this.invalidate();
            }
        });
        ofInt4.addUpdateListener(this);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofInt3).after(ofFloat);
        animatorSet.play(ofFloat).with(ofInt);
        animatorSet.play(ofFloat).with(ofInt2);
        animatorSet.play(ofInt4).after(ofInt3);
        animatorSet.start();
        return true;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        long nanoTime = System.nanoTime();
        if (nanoTime - this.f7691d > 50000000) {
            this.f7691d = nanoTime;
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class GuideHolder {
        private static final float FLOAT_AMEND_VALUE = 0.5f;
        private static final float MAX_COLOR_VALUE = 255.0f;
        private boolean mAnimationEnabled;
        private Rect mBitmapRect;
        private Bitmap[] mBitmaps;
        private int mCurIndex;
        private int mHeight;
        private Paint mPaint;
        private int mWidth;

        /* renamed from: mX */
        private int f7693mX;

        /* renamed from: mY */
        private int f7694mY;

        public void setPaint(Paint paint) {
            this.mPaint = paint;
        }

        public Paint getPaint() {
            return this.mPaint;
        }

        public void setX(int i) {
            this.f7693mX = i;
        }

        public int getX() {
            return this.f7693mX;
        }

        public void setY(int i) {
            this.f7694mY = i;
        }

        public int getY() {
            return this.f7694mY;
        }

        public void setAlpha(float f) {
            this.mPaint.setAlpha((int) ((MAX_COLOR_VALUE * f) + FLOAT_AMEND_VALUE));
        }

        public int getWidth() {
            return this.mWidth;
        }

        public void setWidth(int i) {
            this.mWidth = i;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public void setHeight(int i) {
            this.mHeight = i;
        }

        public GuideHolder(Bitmap... bitmapArr) {
            this.mBitmaps = bitmapArr;
            this.mBitmapRect = new Rect(0, 0, this.mBitmaps[0].getWidth(), this.mBitmaps[0].getHeight());
        }

        public void drawBitmap(Canvas canvas) {
            int i = this.mWidth >> 1;
            int i2 = this.mHeight >> 1;
            if (this.mWidth >= this.mBitmapRect.width()) {
                canvas.drawBitmap(this.mBitmaps[this.mCurIndex], -i, -i2, this.mPaint);
            } else {
                canvas.drawBitmap(this.mBitmaps[this.mCurIndex], this.mBitmapRect, new Rect(-i, -i2, i, i2), this.mPaint);
            }
            if (this.mAnimationEnabled) {
                this.mCurIndex++;
                if (this.mCurIndex >= this.mBitmaps.length) {
                    this.mCurIndex = 0;
                }
            }
        }

        public void setAnimationEnabled(boolean z) {
            this.mAnimationEnabled = z;
            if (!z) {
                this.mCurIndex = 0;
            }
        }
    }
}
