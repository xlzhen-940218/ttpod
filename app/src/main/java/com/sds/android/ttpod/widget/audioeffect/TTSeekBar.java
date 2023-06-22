package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.ImageView;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class TTSeekBar extends ImageView {

    /* renamed from: a */
    private Drawable knobIcon;

    /* renamed from: b */
    private final Rect f8232b;

    /* renamed from: c */
    private Rect f8233c;

    /* renamed from: d */
    private int offset;

    /* renamed from: e */
    private int slideProgress;

    /* renamed from: f */
    private int slideMaxProgress;

    /* renamed from: g */
    private float f8237g;

    /* renamed from: h */
    private InterfaceC2262a f8238h;

    /* renamed from: com.sds.android.ttpod.widget.audioeffect.TTSeekBar$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2262a {
        /* renamed from: a */
        void mo1368a(TTSeekBar tTSeekBar, int i);

        /* renamed from: b */
        void mo1367b(TTSeekBar tTSeekBar, int i);

        /* renamed from: c */
        void mo1366c(TTSeekBar tTSeekBar, int i);
    }

    public TTSeekBar(Context context) {
        super(context);
        this.f8232b = new Rect();
        this.f8233c = new Rect(0, 0, 50, 50);
        this.slideMaxProgress = 100;
    }

    public TTSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TTSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8232b = new Rect();
        this.f8233c = new Rect(0, 0, 50, 50);
        this.slideMaxProgress = 100;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TTSeekBar, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (index) {
                case 0:
                    this.knobIcon = obtainStyledAttributes.getDrawable(R.styleable.TTSeekBar_knobIcon);
                    break;
                case 1:
                    this.offset = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TTSeekBar_offset, 0);
                    break;
                case 2:
                    this.slideProgress = obtainStyledAttributes.getInt(R.styleable.TTSeekBar_slideProgress, 0);
                    break;
                case 3:
                    this.slideMaxProgress = obtainStyledAttributes.getInt(R.styleable.TTSeekBar_slideMaxProgress, 100);
                    break;
            }
        }
        if (this.knobIcon != null) {
            this.knobIcon.setCallback(this);
            Rect m1370a = m1370a(this.knobIcon);
            if (m1370a != null) {
                this.f8233c = m1370a;
            }
        }
    }

    public void setKnob(int i) {
        Drawable drawable = getResources().getDrawable(i);
        if (drawable != null && this.knobIcon != drawable) {
            this.knobIcon = drawable;
            this.knobIcon.setCallback(this);
            Rect m1370a = m1370a(this.knobIcon);
            if (m1370a != null) {
                this.f8233c = m1370a;
            }
        }
    }

    /* renamed from: a */
    public void m1371a(int i, int i2) {
        float f = getResources().getDisplayMetrics().density;
        int i3 = (int) (i * f);
        int i4 = (int) (f * i2);
        if (this.f8233c.width() != i3 || this.f8233c.height() != i4) {
            this.f8233c.set(0, 0, i3, i4);
        }
    }

    public void setOffset(int i) {
        int i2 = (int) (getResources().getDisplayMetrics().density * i);
        if (this.offset != i2) {
            this.offset = i2;
        }
    }

    public void setProgressMax(int i) {
        if (i > 0) {
            this.slideMaxProgress = i;
            this.f8237g = ((getHeight() - this.offset) - this.offset) / this.slideMaxProgress;
        }
    }

    public void setProgress(int i) {
        if (i >= 0 && this.slideProgress != i) {
            this.slideProgress = i;
            invalidate();
        }
    }

    public void setProgressEvent(InterfaceC2262a interfaceC2262a) {
        this.f8238h = interfaceC2262a;
    }

    public int getProgress() {
        return this.slideProgress;
    }

    public int getProgressMax() {
        return this.slideMaxProgress;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        Drawable drawable = this.knobIcon;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
        super.drawableStateChanged();
    }

    @Override // android.widget.ImageView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.knobIcon || super.verifyDrawable(drawable);
    }

    /* renamed from: a */
    private Rect m1370a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            return new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        } else if (drawable instanceof StateListDrawable) {
            return m1370a(((StateListDrawable) drawable).getCurrent());
        } else {
            return null;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f8237g = ((i2 - this.offset) - this.offset) / this.slideMaxProgress;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.knobIcon != null) {
            int width = (getWidth() / 2) - (this.f8233c.width() / 2);
            int height = ((int) ((this.f8237g * this.slideProgress) + this.offset)) - (this.f8233c.height() / 2);
            this.f8232b.set(this.f8233c);
            this.f8232b.offset(width, height);
            this.knobIcon.setBounds(this.f8232b);
            this.knobIcon.draw(canvas);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.knobIcon == null) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (this.knobIcon.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    setPressed(true);
                    if (this.f8238h != null) {
                        this.f8238h.mo1368a(this, this.slideProgress);
                        return true;
                    }
                    return true;
                }
                return false;
            case 1:
            case 3:
                setPressed(false);
                if (this.f8238h != null) {
                    this.f8238h.mo1366c(this, this.slideProgress);
                    return true;
                }
                return true;
            case 2:
                m1369a(motionEvent);
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                    return true;
                }
                return true;
            default:
                return true;
        }
    }

    /* renamed from: a */
    private void m1369a(MotionEvent motionEvent) {
        int y = ((int) motionEvent.getY()) - this.offset;
        if (y < 0) {
            y = 0;
        }
        int i = (int) (y / this.f8237g);
        if (i > this.slideMaxProgress) {
            i = this.slideMaxProgress;
        }
        if (i != this.slideProgress) {
            this.slideProgress = i;
            if (this.f8238h != null) {
                this.f8238h.mo1367b(this, this.slideProgress);
            }
            postInvalidate();
        }
    }
}
