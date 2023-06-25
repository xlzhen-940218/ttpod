package com.sds.android.ttpod.common.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;

/* loaded from: classes.dex */
public class IconTextView extends View {

    /* renamed from: w */
    private static final Matrix.ScaleToFit[] f3583w = {Matrix.ScaleToFit.FILL, Matrix.ScaleToFit.START, Matrix.ScaleToFit.CENTER, Matrix.ScaleToFit.END};

    /* renamed from: a */
    private int f3584a;

    /* renamed from: b */
    private int f3585b;

    /* renamed from: c */
    private Matrix f3586c;

    /* renamed from: d */
    private Matrix f3587d;

    /* renamed from: e */
    private ImageView.ScaleType f3588e;

    /* renamed from: f */
    private RectF f3589f;

    /* renamed from: g */
    private RectF f3590g;

    /* renamed from: h */
    private Drawable drawable;

    /* renamed from: i */
    private Drawable f3592i;

    /* renamed from: j */
    private TextPaint f3593j;

    /* renamed from: k */
    private boolean f3594k;

    /* renamed from: l */
    private int f3595l;

    /* renamed from: m */
    private String f3596m;

    /* renamed from: n */
    private ColorStateList f3597n;

    /* renamed from: o */
    private String f3598o;

    /* renamed from: p */
    private ColorStateList f3599p;

    /* renamed from: q */
    private String bkgText;

    /* renamed from: r */
    private int bkgTextColor;

    /* renamed from: s */
    private InterfaceC1067a f3602s;

    /* renamed from: t */
    private boolean f3603t;

    /* renamed from: u */
    private boolean f3604u;

    /* renamed from: v */
    private View.OnClickListener f3605v;

    /* renamed from: com.sds.android.ttpod.common.widget.IconTextView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1067a {
        /* renamed from: a */
        void mo7202a(IconTextView iconTextView, boolean z, boolean z2);
    }

    public IconTextView(Context context) {
        super(context);
        this.f3589f = new RectF();
        this.f3590g = new RectF();
        this.f3605v = new View.OnClickListener() { // from class: com.sds.android.ttpod.common.widget.IconTextView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IconTextView.this.m7208a(!IconTextView.this.f3604u, true);
            }
        };
        m7216a(context, (AttributeSet) null, 0);
    }

    public IconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3589f = new RectF();
        this.f3590g = new RectF();
        this.f3605v = new View.OnClickListener() { // from class: com.sds.android.ttpod.common.widget.IconTextView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IconTextView.this.m7208a(!IconTextView.this.f3604u, true);
            }
        };
        m7216a(context, attributeSet, 0);
    }

    public IconTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3589f = new RectF();
        this.f3590g = new RectF();
        this.f3605v = new View.OnClickListener() { // from class: com.sds.android.ttpod.common.widget.IconTextView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IconTextView.this.m7208a(!IconTextView.this.f3604u, true);
            }
        };
        m7216a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m7216a(Context context, AttributeSet attributeSet, int i) {
        int textSize;
        CharSequence text;
        ColorStateList textColor;
        int applyDimension = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14.0f, context.getResources().getDisplayMetrics());
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.IconTextView, i, 0);
            text = null;
            textSize = applyDimension;
            textColor = null;
            for (int indexCount = obtainStyledAttributes != null ? obtainStyledAttributes.getIndexCount() - 1 : -1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == 2) {
                    text = obtainStyledAttributes.getText(R.styleable.IconTextView_text);
                } else if (index == 0) {
                    textColor = obtainStyledAttributes.getColorStateList(R.styleable.IconTextView_textColor);
                } else if (index == 1) {
                    textSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IconTextView_textSize, textSize);
                    this.f3594k = true;
                } else if (index == 4) {
                    if (obtainStyledAttributes.getText(R.styleable.IconTextView_bkgText) != null)
                        this.bkgText = obtainStyledAttributes.getText(R.styleable.IconTextView_bkgText).toString();
                } else if (index == 3) {
                    if (obtainStyledAttributes.getColorStateList(R.styleable.IconTextView_bkgTextColor) != null)
                        this.bkgTextColor = obtainStyledAttributes.getColorStateList(R.styleable.IconTextView_bkgTextColor).getDefaultColor();
                }
            }
        } else {
            textSize = applyDimension;
            text = null;
            textColor = null;
        }
        this.f3587d = new Matrix();
        this.f3588e = ImageView.ScaleType.FIT_CENTER;
        this.f3593j = new TextPaint(1);
        this.f3593j.setTextAlign(Paint.Align.CENTER);
        this.f3593j.setTypeface(DisplayUtils.m7230a());
        if (textColor == null) {
            textColor = ColorStateList.valueOf(-65281);
        }
        setTextColor(textColor);
        setTextSize(textSize);
        setText(text != null ? text.toString() : null);
    }

    public void setCheckable(boolean z) {
        this.f3603t = z;
        setClickable(z);
        setOnClickListener(z ? this.f3605v : null);
    }

    public void setText(String str) {
        this.f3596m = str;
        this.drawable = null;
        this.f3592i = null;
        requestLayout();
        invalidate();
    }

    public void setText(int i) {
        setText(getContext().getString(i));
    }

    /* renamed from: a */
    public void m7209a(String str, String str2) {
        this.f3596m = str;
        this.f3598o = str2;
        this.drawable = null;
        this.f3592i = null;
        requestLayout();
        invalidate();
    }

    /* renamed from: a */
    public void m7217a(int i, int i2) {
        Resources resources = getContext().getResources();
        m7209a(resources.getString(i), resources.getString(i2));
    }

    public void setTextColor(int i) {
        setTextColor(ColorStateList.valueOf(i));
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            throw new NullPointerException();
        }
        this.f3597n = colorStateList;
        invalidate();
    }

    /* renamed from: a */
    public void m7215a(ColorStateList colorStateList, ColorStateList colorStateList2) {
        if (colorStateList == null) {
            if (colorStateList2 != null) {
                setTextColor(colorStateList2);
                return;
            }
            return;
        }
        this.f3597n = colorStateList;
        this.f3599p = colorStateList2;
        invalidate();
    }

    public void setBkgTextColor(int i) {
        this.bkgTextColor = i;
        invalidate();
    }

    public void setImageResource(int i) {
        if (i != 0) {
            setImageDrawable(getContext().getResources().getDrawable(i));
        } else {
            setImageDrawable(null);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        if (this.drawable != drawable) {
            int i = this.f3584a;
            int i2 = this.f3585b;
            m7213a(drawable);
            if (i != this.f3584a || i2 != this.f3585b) {
                requestLayout();
            }
            invalidate();
        }
        this.f3596m = null;
    }

    /* renamed from: a */
    private void m7213a(Drawable drawable) {
        if (this.drawable != null) {
            this.drawable.setCallback(null);
            unscheduleDrawable(this.drawable);
        }
        this.drawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
            drawable.setVisible(getVisibility() == View.VISIBLE, true);
            this.f3584a = drawable.getIntrinsicWidth();
            this.f3585b = drawable.getIntrinsicHeight();
            m7204c();
            return;
        }
        this.f3584a = -1;
        this.f3585b = -1;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (m7218a()) {
            m7205b(canvas);
        } else {
            m7214a(canvas);
        }
    }

    /* renamed from: a */
    private boolean m7218a() {
        return StringUtils.isEmpty(this.f3596m) && StringUtils.isEmpty(this.f3598o);
    }

    /* renamed from: a */
    private void m7214a(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int width = (getWidth() - paddingLeft) - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        if (this.f3594k) {
            canvas.save();
            canvas.clipRect(paddingLeft, getPaddingTop(), paddingLeft + width, height);
        }
        if (!StringUtils.isEmpty(this.bkgText)) {
            this.f3593j.setColor(this.bkgTextColor);
            canvas.drawText(this.bkgText, (width >> 1) + paddingLeft, height - this.f3595l, this.f3593j);
        }
        ColorStateList colorStateList = (this.f3603t && this.f3604u && this.f3599p != null) ? this.f3599p : this.f3597n;
        this.f3593j.setColor(colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor()));
        String str = (this.f3603t && this.f3604u) ? this.f3598o : this.f3596m;
        if (str == null) {
            str = "";
        }
        canvas.drawText(str, paddingLeft + (width >> 1), height - this.f3595l, this.f3593j);
        if (this.f3594k) {
            canvas.restore();
        }
    }

    /* renamed from: b */
    private void m7205b(Canvas canvas) {
        Drawable drawable = (this.f3603t && this.f3604u) ? this.f3592i : this.drawable;
        if (drawable != null && this.f3584a != 0 && this.f3585b != 0) {
            int paddingTop = getPaddingTop();
            int paddingLeft = getPaddingLeft();
            if (this.f3586c == null && paddingTop == 0 && paddingLeft == 0) {
                drawable.draw(canvas);
                return;
            }
            int saveCount = canvas.getSaveCount();
            canvas.save();
            canvas.translate(paddingLeft, paddingTop);
            if (this.f3586c != null) {
                canvas.concat(this.f3586c);
            }
            drawable.draw(canvas);
            canvas.restoreToCount(saveCount);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (m7218a()) {
            m7206b(i, i2);
        } else {
            m7203c(i, i2);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0) {
            int paddingLeft = i - (getPaddingLeft() + getPaddingRight());
            int paddingTop = i2 - (getPaddingTop() + getPaddingBottom());
            m7204c();
            if (!this.f3594k) {
                setTextSize(Math.min(paddingLeft, paddingTop));
            }
            m7207b();
        }
    }

    private void setTextSize(float f) {
        this.f3593j.setTextSize(f);
    }

    /* renamed from: b */
    private void m7207b() {
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        Paint.FontMetrics fontMetrics = this.f3593j.getFontMetrics();
        this.f3595l = (int) (((height - (fontMetrics.bottom - fontMetrics.top)) / 2.0f) + fontMetrics.bottom + 0.5f);
    }

    /* renamed from: a */
    private static Matrix.ScaleToFit m7212a(ImageView.ScaleType scaleType) {
        return f3583w[scaleType.ordinal() - 1];
    }

    /* renamed from: b */
    private void m7206b(int i, int i2) {
        int i3;
        int i4 = 0;
        if (this.drawable == null) {
            this.f3584a = -1;
            this.f3585b = -1;
            i3 = 0;
        } else {
            int i5 = this.f3584a;
            i4 = this.f3585b;
            if (i5 <= 0) {
                i5 = 1;
            }
            if (i4 <= 0) {
                i4 = 1;
                i3 = i5;
            } else {
                i3 = i5;
            }
        }
        setMeasuredDimension(resolveSize(Math.max(i3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i), resolveSize(Math.max(i4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2));
    }

    /* renamed from: c */
    private void m7204c() {
        float min;
        float f;
        float f2;
        float f3 = 0.0f;
        if (this.drawable != null) {
            int i = this.f3584a;
            int i2 = this.f3585b;
            int paddingTop = getPaddingTop();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int height = (getHeight() - paddingTop) - getPaddingBottom();
            boolean z = (i < 0 || width == i) && (i2 < 0 || height == i2);
            if (i <= 0 || i2 <= 0 || ImageView.ScaleType.FIT_XY == this.f3588e) {
                this.drawable.setBounds(0, 0, width, height);
                this.f3586c = null;
                return;
            }
            this.drawable.setBounds(0, 0, i, i2);
            if (ImageView.ScaleType.MATRIX == this.f3588e) {
                if (this.f3587d.isIdentity()) {
                    this.f3586c = null;
                } else {
                    this.f3586c = this.f3587d;
                }
            } else if (z) {
                this.f3586c = null;
            } else if (ImageView.ScaleType.CENTER == this.f3588e) {
                this.f3586c = this.f3587d;
                this.f3586c.setTranslate((int) (((width - i) * 0.5f) + 0.5f), (int) (((height - i2) * 0.5f) + 0.5f));
            } else if (ImageView.ScaleType.CENTER_CROP == this.f3588e) {
                this.f3586c = this.f3587d;
                if (i * height > width * i2) {
                    f = height / i2;
                    f2 = (width - (i * f)) * 0.5f;
                } else {
                    f = width / i;
                    f2 = 0.0f;
                    f3 = (height - (i2 * f)) * 0.5f;
                }
                this.f3586c.setScale(f, f);
                this.f3586c.postTranslate((int) (f2 + 0.5f), (int) (f3 + 0.5f));
            } else if (ImageView.ScaleType.CENTER_INSIDE == this.f3588e) {
                this.f3586c = this.f3587d;
                if (i <= width && i2 <= height) {
                    min = 1.0f;
                } else {
                    min = Math.min(width / i, height / i2);
                }
                this.f3586c.setScale(min, min);
                this.f3586c.postTranslate((int) (((width - (i * min)) * 0.5f) + 0.5f), (int) (((height - (i2 * min)) * 0.5f) + 0.5f));
            } else {
                this.f3589f.set(0.0f, 0.0f, i, i2);
                this.f3590g.set(0.0f, 0.0f, width, height);
                this.f3586c = this.f3587d;
                this.f3586c.setRectToRect(this.f3589f, this.f3590g, m7212a(this.f3588e));
            }
        }
    }

    /* renamed from: c */
    private void m7203c(int i, int i2) {
        int max;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        if (mode != 1073741824) {
            int max2 = Math.max((this.f3593j != null ? (int) (this.f3593j.measureText(this.f3596m) + 0.5f) : 0) + paddingRight + paddingLeft, getSuggestedMinimumWidth());
            size = mode == Integer.MIN_VALUE ? Math.min(size, max2) : max2;
        }
        if (mode2 == 1073741824) {
            max = size2;
        } else {
            max = Math.max(paddingTop + paddingBottom + (this.f3593j != null ? (int) (this.f3593j.getTextSize() + 0.5f) : 0), getSuggestedMinimumHeight());
            if (mode2 == Integer.MIN_VALUE) {
                max = Math.min(max, size2);
            }
        }
        setMeasuredDimension(size, max);
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (m7218a()) {
            if (this.drawable != null && this.drawable.isStateful()) {
                this.drawable.setState(getDrawableState());
                invalidate();
            }
            if (this.f3603t && this.f3604u && this.f3592i != null && this.f3592i.isStateful()) {
                this.f3592i.setState(getDrawableState());
                invalidate();
                return;
            }
            return;
        }
        if (this.f3597n != null && this.f3597n.isStateful()) {
            invalidate();
        }
        if (this.f3603t && this.f3604u && this.f3599p != null && this.f3599p.isStateful()) {
            invalidate();
        }
    }

    public void setChecked(boolean z) {
        m7208a(z, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7208a(boolean z, boolean z2) {
        if (this.f3604u != z) {
            this.f3604u = z;
            invalidate();
            if (this.f3602s != null) {
                this.f3602s.mo7202a(this, this.f3604u, z2);
            }
        }
    }

    @Override // android.view.View
    public void startAnimation(Animation animation) {
        if (DisplayUtils.m7220h() > 160) {
            super.startAnimation(animation);
        }
    }

    @Override // android.view.View
    public void clearAnimation() {
        if (DisplayUtils.m7220h() > 160) {
            super.clearAnimation();
        }
    }

    @Override // android.view.View
    public void setAnimation(Animation animation) {
        if (DisplayUtils.m7220h() > 160) {
            super.setAnimation(animation);
        }
    }

    public void setOnCheckedChangeListener(InterfaceC1067a interfaceC1067a) {
        this.f3602s = interfaceC1067a;
    }
}
