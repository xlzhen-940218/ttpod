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
public class SeekBarExpansion extends SeekBar {

    /* renamed from: a */
    private int f6854a;

    /* renamed from: b */
    private boolean f6855b;

    /* renamed from: c */
    private SeekBar.OnSeekBarChangeListener f6856c;

    /* renamed from: d */
    private C2004a f6857d;

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
    public void setPadding(int i, int i2, int i3, int i4) {
        if (this.f6854a == 1) {
            super.setPadding(i2, i, i4, i3);
        } else {
            super.setPadding(i, i2, i3, i4);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        if (this.f6854a == 1) {
            canvas.rotate(-90.0f);
            canvas.translate(-getHeight(), 0.0f);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f6854a == 1) {
            setMeasuredDimension(Math.max(getMeasuredWidth(), this.f6857d == null ? 0 : this.f6857d.getIntrinsicWidth()) + getPaddingTop() + getPaddingBottom(), getMeasuredHeight());
        }
    }

    public void setOrientation(int i) {
        if (this.f6854a != i) {
            this.f6854a = i;
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
        return this.f6854a;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.f6854a != 1) {
            i2 = i;
            i = i2;
        }
        super.onSizeChanged(i2, i, i3, i4);
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
            progressDrawable.setBounds(0, 0, (i2 - paddingLeft) - paddingRight, (i - paddingTop) - paddingBottom);
        }
        if (this.f6857d != null) {
            this.f6857d.m3368a(i2, i);
        }
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        super.setOnSeekBarChangeListener(onSeekBarChangeListener);
        this.f6856c = onSeekBarChangeListener;
    }

    /* renamed from: a */
    private boolean m3374a(MotionEvent motionEvent) {
        int x;
        int y;
        int action = motionEvent.getAction();
        C2004a c2004a = this.f6857d;
        if ((action == 0 || action == 1) && c2004a != null) {
            if (this.f6854a == 1) {
                x = getHeight() - ((int) motionEvent.getY());
                y = (int) motionEvent.getX();
            } else {
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
            }
            return c2004a.getBounds().contains(x, y);
        }
        return true;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && (!this.f6855b || isPressed() || m3374a(motionEvent))) {
            if (this.f6854a == 1) {
                switch (motionEvent.getAction()) {
                    case 0:
                        setPressed(true);
                        m3375a();
                        m3370b(motionEvent);
                        return true;
                    case 1:
                        m3370b(motionEvent);
                        m3371b();
                        setPressed(false);
                        invalidate();
                        return true;
                    case 2:
                        m3370b(motionEvent);
                        m3369c();
                        return true;
                    case 3:
                        m3371b();
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
    private void m3370b(MotionEvent motionEvent) {
        float f;
        int height = getHeight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i = (height - paddingTop) - paddingBottom;
        int y = height - ((int) motionEvent.getY());
        if (y < paddingTop) {
            f = 0.0f;
        } else if (y > height - paddingBottom) {
            f = 1.0f;
        } else {
            f = (y - paddingTop) / i;
        }
        setProgress((int) ((f * getMax()) + 0.0f));
    }

    /* renamed from: a */
    private void m3375a() {
        if (this.f6856c != null) {
            this.f6856c.onStartTrackingTouch(this);
        }
    }

    /* renamed from: b */
    private void m3371b() {
        if (this.f6856c != null) {
            this.f6856c.onStopTrackingTouch(this);
        }
    }

    /* renamed from: c */
    private void m3369c() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable drawable) {
        if (this.f6857d == null || this.f6857d.f6861b != drawable) {
            this.f6857d = new C2004a(drawable);
            super.setThumb(this.f6857d);
            setThumbOffset(Math.max(0, this.f6857d != null ? this.f6857d.getIntrinsicWidth() : 0) >> 1);
        }
    }

    public void setDragChangeProgressOnly(boolean z) {
        this.f6855b = z;
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
    public class C2004a extends Drawable {

        /* renamed from: b */
        private Drawable f6861b;

        /* renamed from: c */
        private int f6862c;

        /* renamed from: d */
        private int f6863d;

        public C2004a(Drawable drawable) {
            this.f6861b = drawable;
            this.f6862c = this.f6861b.getIntrinsicHeight();
            this.f6863d = this.f6861b.getIntrinsicWidth();
        }

        /* renamed from: a */
        void m3368a(int i, int i2) {
            int intrinsicWidth = this.f6861b.getIntrinsicWidth();
            int intrinsicHeight = this.f6861b.getIntrinsicHeight();
            this.f6862c = Math.min(i2, intrinsicHeight);
            this.f6863d = (int) (intrinsicWidth * (this.f6862c / intrinsicHeight));
            this.f6863d = Math.min(this.f6863d, this.f6862c);
            Rect bounds = getBounds();
            setBounds(bounds.left, bounds.top, bounds.left + this.f6863d, bounds.top + this.f6862c);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            this.f6861b.draw(canvas);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.f6861b.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.f6861b.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.f6861b.setColorFilter(colorFilter);
        }

        @Override // android.graphics.drawable.Drawable
        public void clearColorFilter() {
            this.f6861b.clearColorFilter();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable.Callback getCallback() {
            return this.f6861b.getCallback();
        }

        @Override // android.graphics.drawable.Drawable
        public int getChangingConfigurations() {
            return this.f6861b.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable.ConstantState getConstantState() {
            return this.f6861b.getConstantState();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable getCurrent() {
            return this.f6861b.getCurrent();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.f6862c;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.f6863d;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return this.f6861b.getMinimumHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return this.f6861b.getMinimumWidth();
        }

        @Override // android.graphics.drawable.Drawable
        public boolean getPadding(Rect rect) {
            return this.f6861b.getPadding(rect);
        }

        @Override // android.graphics.drawable.Drawable
        public int[] getState() {
            return this.f6861b.getState();
        }

        @Override // android.graphics.drawable.Drawable
        public Region getTransparentRegion() {
            return this.f6861b.getTransparentRegion();
        }

        @Override // android.graphics.drawable.Drawable
        public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
            this.f6861b.inflate(resources, xmlPullParser, attributeSet);
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            this.f6861b.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public boolean isStateful() {
            return this.f6861b.isStateful();
        }

        @Override // android.graphics.drawable.Drawable
        public void jumpToCurrentState() {
            this.f6861b.jumpToCurrentState();
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable mutate() {
            return this.f6861b.mutate();
        }

        @Override // android.graphics.drawable.Drawable
        public void scheduleSelf(Runnable runnable, long j) {
            this.f6861b.scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int i, int i2, int i3, int i4) {
            int height;
            if (SeekBarExpansion.this.f6854a == 1) {
                int max = SeekBarExpansion.this.getMax();
                i = (int) ((max > 0 ? SeekBarExpansion.this.getProgress() / max : 0.0f) * ((SeekBarExpansion.this.getThumbOffset() << 1) + (((SeekBarExpansion.this.getHeight() - SeekBarExpansion.this.getPaddingLeft()) - SeekBarExpansion.this.getPaddingRight()) - this.f6863d)));
                i3 = i + this.f6863d;
                height = (((SeekBarExpansion.this.getWidth() - SeekBarExpansion.this.getPaddingTop()) - SeekBarExpansion.this.getPaddingBottom()) - this.f6862c) / 2;
            } else {
                height = (((SeekBarExpansion.this.getHeight() - SeekBarExpansion.this.getPaddingTop()) - SeekBarExpansion.this.getPaddingBottom()) - this.f6862c) / 2;
            }
            int i5 = this.f6862c + height;
            this.f6861b.setBounds(i, height, i3, i5);
            super.setBounds(i, height, i3, i5);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(Rect rect) {
            setBounds(rect.left, rect.top, rect.right, rect.bottom);
        }

        @Override // android.graphics.drawable.Drawable
        public void setChangingConfigurations(int i) {
            this.f6861b.setChangingConfigurations(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(int i, PorterDuff.Mode mode) {
            this.f6861b.setColorFilter(i, mode);
        }

        @Override // android.graphics.drawable.Drawable
        public void setDither(boolean z) {
            this.f6861b.setDither(z);
        }

        @Override // android.graphics.drawable.Drawable
        public void setFilterBitmap(boolean z) {
            this.f6861b.setFilterBitmap(z);
        }

        @Override // android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            return this.f6861b.setState(iArr);
        }

        @Override // android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            return this.f6861b.setVisible(z, z2);
        }

        @Override // android.graphics.drawable.Drawable
        public void unscheduleSelf(Runnable runnable) {
            this.f6861b.unscheduleSelf(runnable);
        }

        public boolean equals(Object obj) {
            return this.f6861b.equals(obj);
        }

        public int hashCode() {
            return this.f6861b.hashCode();
        }

        public String toString() {
            return this.f6861b.toString();
        }
    }
}
