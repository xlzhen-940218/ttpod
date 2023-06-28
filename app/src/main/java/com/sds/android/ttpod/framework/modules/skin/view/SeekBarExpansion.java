package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.SeekBar;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class SeekBarExpansion extends androidx.appcompat.widget.AppCompatSeekBar {

    /* renamed from: a */
    private int orientation;

    /* renamed from: b */
    private boolean dragChangeProgressOnly;

    /* renamed from: c */
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;

    /* renamed from: d */
    private ThumbDrawable thumbDrawable;

    public SeekBarExpansion(Context context) {
        super(context);
    }

    public SeekBarExpansion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SeekBarExpansion(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    public void setPadding(int left, int top, int right, int bottom) {
        if (this.orientation == 1) {
            super.setPadding(top, left, bottom, right);
        } else {
            super.setPadding(left, top, right, bottom);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        if (this.orientation == 1) {
            canvas.rotate(-90.0f);
            canvas.translate(-getHeight(), 0.0f);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.orientation == 1) {
            setMeasuredDimension(Math.max(getMeasuredWidth(), this.thumbDrawable == null ? 0 : this.thumbDrawable.getIntrinsicWidth()) + getPaddingTop() + getPaddingBottom(), getMeasuredHeight());
        }
    }

    public void setOrientation(int orientation) {
        if (this.orientation != orientation) {
            this.orientation = orientation;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            if (paddingLeft != paddingTop || paddingRight != paddingBottom) {
                setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.orientation;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (this.orientation != 1) {
            h = w;
            w = h;
        }
        super.onSizeChanged(h, w, oldw, oldh);
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int thumbOffset = getThumbOffset();
        if (paddingLeft < thumbOffset || paddingRight < thumbOffset) {
            setThumbOffset(Math.min(paddingLeft, paddingRight));
        }
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, (h - paddingLeft) - paddingRight, (w - paddingTop) - paddingBottom);
        }
        if (this.thumbDrawable != null) {
            this.thumbDrawable.m3368a(h, w);
        }
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        super.setOnSeekBarChangeListener(onSeekBarChangeListener);
        this.onSeekBarChangeListener = onSeekBarChangeListener;
    }

    /* renamed from: a */
    private boolean isTouchThumb(MotionEvent motionEvent) {
        int x;
        int y;
        int action = motionEvent.getAction();

        if ((action == 0 || action == 1) && thumbDrawable != null) {
            if (this.orientation == 1) {
                x = getHeight() - ((int) motionEvent.getY());
                y = (int) motionEvent.getX();
            } else {
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
            }
            return thumbDrawable.getBounds().contains(x, y);
        }
        return true;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && (!this.dragChangeProgressOnly || isPressed() || isTouchThumb(motionEvent))) {
            if (this.orientation == 1) {
                switch (motionEvent.getAction()) {
                    case 0:
                        setPressed(true);
                        onStartTrackingTouch();
                        setPosition(motionEvent);
                        return true;
                    case 1:
                        setPosition(motionEvent);
                        onStopTrackingTouch();
                        setPressed(false);
                        invalidate();
                        return true;
                    case 2:
                        setPosition(motionEvent);
                        requestDisallowInterceptTouchEvent();
                        return true;
                    case 3:
                        onStopTrackingTouch();
                        setPressed(false);
                        invalidate();
                        return true;
                    default:
                        return true;
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    /* renamed from: b */
    private void setPosition(MotionEvent motionEvent) {
        float position;
        int height = getHeight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i = (height - paddingTop) - paddingBottom;
        int y = height - ((int) motionEvent.getY());
        if (y < paddingTop) {
            position = 0.0f;
        } else if (y > height - paddingBottom) {
            position = 1.0f;
        } else {
            position = (y - paddingTop) / i;
        }
        setProgress((int) ((position * getMax()) + 0.0f));
    }

    /* renamed from: a */
    private void onStartTrackingTouch() {
        if (this.onSeekBarChangeListener != null) {
            this.onSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    /* renamed from: b */
    private void onStopTrackingTouch() {
        if (this.onSeekBarChangeListener != null) {
            this.onSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    /* renamed from: c */
    private void requestDisallowInterceptTouchEvent() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable thumb) {
        if(thumb == null)
            return;
        if (this.thumbDrawable == null || this.thumbDrawable.drawable != thumb) {
            this.thumbDrawable = new ThumbDrawable(thumb);
            super.setThumb(this.thumbDrawable);
            setThumbOffset(Math.max(0, this.thumbDrawable != null ? this.thumbDrawable.getIntrinsicWidth() : 0) >> 1);
        }
    }

    public void setDragChangeProgressOnly(boolean dragChangeProgressOnly) {
        this.dragChangeProgressOnly = dragChangeProgressOnly;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public void onRtlPropertiesChanged(final int i) {
        if (SDKVersionUtils.m8366h()) {
            post(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.skin.view.SeekBarExpansion.1
                @Override // java.lang.Runnable
                public void run() {
                    SeekBarExpansion.super.onRtlPropertiesChanged(i);
                }
            });
        } else {
            super.onRtlPropertiesChanged(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.SeekBarExpansion$a */
    /* loaded from: classes.dex */
    public class ThumbDrawable extends Drawable {

        /* renamed from: b */
        private Drawable drawable;

        /* renamed from: c */
        private int intrinsicHeight;

        /* renamed from: d */
        private int intrinsicWidth;

        public ThumbDrawable(Drawable drawable) {
            this.drawable = drawable;
            this.intrinsicHeight = this.drawable.getIntrinsicHeight();
            this.intrinsicWidth = this.drawable.getIntrinsicWidth();
        }

        /* renamed from: a */
        void m3368a(int i, int i2) {
            int intrinsicWidth = this.drawable.getIntrinsicWidth();
            int intrinsicHeight = this.drawable.getIntrinsicHeight();
            this.intrinsicHeight = Math.min(i2, intrinsicHeight);
            this.intrinsicWidth = (int) (intrinsicWidth * (this.intrinsicHeight / intrinsicHeight));
            this.intrinsicWidth = Math.min(this.intrinsicWidth, this.intrinsicHeight);
            Rect bounds = getBounds();
            setBounds(bounds.left, bounds.top, bounds.left + this.intrinsicWidth, bounds.top + this.intrinsicHeight);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            this.drawable.draw(canvas);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.drawable.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.drawable.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.drawable.setColorFilter(colorFilter);
        }

        @Override // android.graphics.drawable.Drawable
        public void clearColorFilter() {
            this.drawable.clearColorFilter();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable.Callback getCallback() {
            return this.drawable.getCallback();
        }

        @Override // android.graphics.drawable.Drawable
        public int getChangingConfigurations() {
            return this.drawable.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable.ConstantState getConstantState() {
            return this.drawable.getConstantState();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable getCurrent() {
            return this.drawable.getCurrent();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.intrinsicHeight;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.intrinsicWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return this.drawable.getMinimumHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return this.drawable.getMinimumWidth();
        }

        @Override // android.graphics.drawable.Drawable
        public boolean getPadding(Rect rect) {
            return this.drawable.getPadding(rect);
        }

        @Override // android.graphics.drawable.Drawable
        public int[] getState() {
            return this.drawable.getState();
        }

        @Override // android.graphics.drawable.Drawable
        public Region getTransparentRegion() {
            return this.drawable.getTransparentRegion();
        }

        @Override // android.graphics.drawable.Drawable
        public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
            this.drawable.inflate(resources, xmlPullParser, attributeSet);
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            this.drawable.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public boolean isStateful() {
            return this.drawable.isStateful();
        }

        @Override // android.graphics.drawable.Drawable
        public void jumpToCurrentState() {
            this.drawable.jumpToCurrentState();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable mutate() {
            return this.drawable.mutate();
        }

        @Override // android.graphics.drawable.Drawable
        public void scheduleSelf(Runnable runnable, long j) {
            this.drawable.scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int i, int i2, int i3, int i4) {
            int height;
            if (SeekBarExpansion.this.orientation == 1) {
                int max = SeekBarExpansion.this.getMax();
                i = (int) ((max > 0 ? SeekBarExpansion.this.getProgress() / max : 0.0f) * ((SeekBarExpansion.this.getThumbOffset() << 1) + (((SeekBarExpansion.this.getHeight() - SeekBarExpansion.this.getPaddingLeft()) - SeekBarExpansion.this.getPaddingRight()) - this.intrinsicWidth)));
                i3 = i + this.intrinsicWidth;
                height = (((SeekBarExpansion.this.getWidth() - SeekBarExpansion.this.getPaddingTop()) - SeekBarExpansion.this.getPaddingBottom()) - this.intrinsicHeight) / 2;
            } else {
                height = (((SeekBarExpansion.this.getHeight() - SeekBarExpansion.this.getPaddingTop()) - SeekBarExpansion.this.getPaddingBottom()) - this.intrinsicHeight) / 2;
            }
            int i5 = this.intrinsicHeight + height;
            this.drawable.setBounds(i, height, i3, i5);
            super.setBounds(i, height, i3, i5);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(Rect rect) {
            setBounds(rect.left, rect.top, rect.right, rect.bottom);
        }

        @Override // android.graphics.drawable.Drawable
        public void setChangingConfigurations(int i) {
            this.drawable.setChangingConfigurations(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(int i, PorterDuff.Mode mode) {
            this.drawable.setColorFilter(i, mode);
        }

        @Override // android.graphics.drawable.Drawable
        public void setDither(boolean z) {
            this.drawable.setDither(z);
        }

        @Override // android.graphics.drawable.Drawable
        public void setFilterBitmap(boolean z) {
            this.drawable.setFilterBitmap(z);
        }

        @Override // android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            return this.drawable.setState(iArr);
        }

        @Override // android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            return this.drawable.setVisible(z, z2);
        }

        @Override // android.graphics.drawable.Drawable
        public void unscheduleSelf(Runnable runnable) {
            this.drawable.unscheduleSelf(runnable);
        }

        public boolean equals(Object obj) {
            return this.drawable.equals(obj);
        }

        public int hashCode() {
            return this.drawable.hashCode();
        }

        public String toString() {
            return this.drawable.toString();
        }
    }
}
