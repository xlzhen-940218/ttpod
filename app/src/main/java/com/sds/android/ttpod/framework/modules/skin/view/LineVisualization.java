package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.skin.serialskin.SAnalyzer;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.view.a */
/* loaded from: classes.dex */
public class LineVisualization extends View {

    /* renamed from: a */
    private Drawable lineDrawable;

    /* renamed from: b */
    private Drawable dotDrawable;

    /* renamed from: c */
    private OnActiveListener onActiveListener;

    /* renamed from: d */
    private int reflectionHeight;

    /* renamed from: e */
    private int divideHeight;

    /* renamed from: f */
    private int dotHeight;

    /* renamed from: g */
    private int reflectionMaskStartColor;

    /* renamed from: h */
    private int reflectionMaskEndColor;

    /* renamed from: i */
    private final Rect f6888i;

    /* renamed from: j */
    private final Rect f6889j;

    /* renamed from: k */
    private int[] f6890k;

    /* renamed from: l */
    private long[] f6891l;

    /* renamed from: m */
    private int lineDivideWidth;

    /* renamed from: n */
    private int lineWidth;

    /* renamed from: o */
    private int numberOfLine;

    /* renamed from: p */
    private boolean f6895p;

    /* renamed from: q */
    private int width;

    /* renamed from: r */
    private int height;

    /* renamed from: s */
    private Bitmap f6898s;

    /* renamed from: t */
    private Bitmap f6899t;

    /* renamed from: u */
    private Bitmap f6900u;

    /* renamed from: v */
    private final Paint f6901v;

    /* renamed from: w */
    private Canvas f6902w;

    /* compiled from: LineVisualization.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.view.a$a */
    /* loaded from: classes.dex */
    public interface OnActiveListener {
        /* renamed from: a */
        void active();

        /* renamed from: b */
        void inactive();
    }

    public LineVisualization(Context context) {
        super(context);
        this.divideHeight = 5;
        this.dotHeight = -1;
        this.reflectionMaskStartColor = 553648127;
        this.reflectionMaskEndColor = 1627389951;
        this.f6888i = new Rect();
        this.f6889j = new Rect();
        this.lineDivideWidth = 5;
        this.lineWidth = 5;
        this.numberOfLine = 0;
        this.f6895p = false;
        this.f6901v = new Paint();
        this.f6902w = new Canvas();
    }

    public void setNumberOfLine(int i) {
        int max = Math.max(Math.min(i, 128), 4);
        synchronized (this) {
            this.numberOfLine = max;
            int i2 = max << 1;
            if (this.f6890k == null || this.f6890k.length < i2) {
                this.f6890k = new int[i2];
            }
            if (this.f6891l == null || this.f6891l.length < i2) {
                this.f6891l = new long[i2];
            }
        }
    }

    public int getNumberOfLine() {
        return this.numberOfLine;
    }

    public void setLineDrawable(Drawable drawable) {
        if (this.lineDrawable != drawable) {
            this.lineDrawable = drawable;
            m3351d();
        }
    }

    public void setDotDrawable(Drawable drawable) {
        if (this.dotDrawable != drawable) {
            this.dotDrawable = drawable;
            m3351d();
        }
    }

    public void setReflectionHeight(int i) {
        if (i != this.reflectionHeight) {
            this.reflectionHeight = i;
            m3351d();
        }
    }

    /* renamed from: a */
    private void m3356a() {
        if (this.f6899t != null) {
            int height = this.f6899t.getHeight();
            this.f6901v.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            this.f6901v.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, height, this.reflectionMaskEndColor, this.reflectionMaskStartColor, Shader.TileMode.CLAMP));
        }
    }

    public void setDivideHeight(int i) {
        if (this.divideHeight != i) {
            this.divideHeight = i;
            m3353b();
        }
    }

    /* renamed from: a */
    public void setReflectionMaskColors(int reflectionMaskStartColor, int reflectionMaskEndColor) {
        if (this.reflectionMaskStartColor != reflectionMaskStartColor || this.reflectionMaskEndColor != reflectionMaskEndColor) {
            this.reflectionMaskStartColor = reflectionMaskStartColor;
            this.reflectionMaskEndColor = reflectionMaskEndColor;
            m3356a();
        }
    }

    /* renamed from: a */
    public void m3354a(int[] iArr) {
        int[] iArr2;
        long[] jArr;
        int i;
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        float f = (height / 100) * 0.9f;
        if (height > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this) {
                iArr2 = this.f6890k;
                jArr = this.f6891l;
                i = this.numberOfLine;
            }
            if (iArr != null && iArr.length > 0) {
                this.f6895p = true;
                int min = Math.min(iArr.length, i);
                for (int i2 = 0; i2 < min; i2++) {
                    int i3 = i2 << 1;
                    int i4 = i3 + 1;
                    int i5 = (int) (iArr[i2] * f);
                    if (i5 > iArr2[i3]) {
                        iArr2[i3] = i5;
                        jArr[i3] = 0;
                    } else if (iArr2[i3] > 0) {
                        if (jArr[i3] == 0) {
                            jArr[i3] = currentTimeMillis;
                        } else {
                            float f2 = ((float) (currentTimeMillis - jArr[i3])) * 0.05f;
                            if (f2 > 0.0f) {
                                iArr2[i3] = (short) Math.max(0, Math.round(iArr2[i3] - f2));
                            }
                        }
                    }
                    if (iArr2[i3] > iArr2[i4]) {
                        iArr2[i4] = iArr2[i3];
                        jArr[i4] = 0;
                    } else if (jArr[i4] == 0) {
                        jArr[i4] = 500 + currentTimeMillis;
                    } else {
                        float f3 = ((float) (currentTimeMillis - jArr[i4])) * 0.05f;
                        if (f3 > 0.0f) {
                            iArr2[i4] = (short) Math.max(iArr2[i3], Math.round(iArr2[i4] - f3));
                        }
                    }
                }
            } else if (this.f6895p) {
                this.f6895p = false;
                for (int i6 = 0; i6 < i; i6++) {
                    int i7 = i6 << 1;
                    int i8 = i7 + 1;
                    if (iArr2[i7] > 0) {
                        this.f6895p = true;
                        if (jArr[i7] == 0) {
                            jArr[i7] = currentTimeMillis;
                        } else {
                            float f4 = ((float) (currentTimeMillis - jArr[i7])) * 0.05f;
                            if (f4 > 0.0f) {
                                iArr2[i7] = (short) Math.max(0, Math.round(iArr2[i7] - f4));
                            }
                        }
                    }
                    if (iArr2[i8] > 0) {
                        this.f6895p = true;
                        if (jArr[i8] == 0) {
                            jArr[i8] = 500 + currentTimeMillis;
                        } else {
                            float f5 = ((float) (currentTimeMillis - jArr[i8])) * 0.05f;
                            if (f5 > 0.0f) {
                                iArr2[i8] = (short) Math.max(0, Math.round(iArr2[i8] - f5));
                            }
                        }
                    }
                }
                if (!this.f6895p) {
                    return;
                }
            } else {
                return;
            }
            postInvalidate();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int[] iArr = this.f6890k;
        if (iArr != null && this.f6898s != null) {
            int min = Math.min(this.numberOfLine, iArr.length >> 1);
            int height = this.f6898s.getHeight();
            int width = this.f6898s.getWidth();
            int abs = Math.abs(this.dotHeight);
            int i = 0;
            Canvas canvas2 = this.f6902w;
            canvas2.setBitmap(this.f6899t);
            this.f6899t.eraseColor(0);
            for (int i2 = 0; i2 < min; i2++) {
                int i3 = i2 << 1;
                int i4 = i3 + 1;
                int i5 = height - iArr[i3];
                this.f6889j.set(i, i5, i + width, height);
                this.f6888i.set(0, i5, width, height);
                canvas2.drawBitmap(this.f6898s, this.f6888i, this.f6889j, (Paint) null);
                if (abs > 0) {
                    int i6 = height - iArr[i4];
                    this.f6889j.set(i, i6 - abs, i + width, i6);
                    this.f6888i.set(0, 0, width, abs);
                    canvas2.drawBitmap(this.f6898s, this.f6888i, this.f6889j, (Paint) null);
                }
                i += this.lineDivideWidth + width;
            }
            canvas.drawBitmap(this.f6899t, 0.0f, getTopPaddingOffset(), (Paint) null);
            if (this.reflectionHeight > 0) {
                canvas2.setBitmap(this.f6900u);
                canvas2.drawBitmap(this.f6899t, 0.0f, 0.0f, (Paint) null);
                canvas2.drawRect(0.0f, 0.0f, this.width, height, this.f6901v);
                canvas.save();
                canvas.translate(0.0f, getTopPaddingOffset() + this.divideHeight + height + this.reflectionHeight);
                canvas.scale(1.0f, -(this.reflectionHeight / height));
                canvas.drawBitmap(this.f6900u, 0.0f, getTopPaddingOffset(), (Paint) null);
                canvas.restore();
            }
        }
    }

    public void setLineWidth(int i) {
        if (i > 0 && this.lineWidth != i) {
            this.lineWidth = i;
            setNumberOfLine((this.width + this.lineDivideWidth) / (this.lineWidth + this.lineDivideWidth));
            m3353b();
        }
    }

    public void setDotHeight(int i) {
        if (i > 0 && this.dotHeight != i) {
            this.dotHeight = i;
        }
    }

    public void setLineDivideWidth(int i) {
        if (i >= 0 && i != this.lineDivideWidth) {
            this.lineDivideWidth = i;
            setNumberOfLine((this.width + this.lineDivideWidth) / (this.lineWidth + this.lineDivideWidth));
        }
    }

    /* renamed from: b */
    private void m3353b() {
        int i = this.lineWidth;
        int i2 = this.height - (this.reflectionHeight > 0 ? this.divideHeight + this.reflectionHeight : 0);
        if (this.f6898s == null || this.f6898s.getWidth() != i || this.f6898s.getHeight() != i2) {
            recycle();
            if (i > 0 && i2 > 0) {
                this.f6898s = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                try {
                    this.f6899t = Bitmap.createBitmap(this.width, i2, Bitmap.Config.ARGB_8888);
                } catch (OutOfMemoryError e) {
                    this.f6899t = Bitmap.createBitmap(this.width, i2, Bitmap.Config.ARGB_4444);
                }
                if (this.reflectionHeight > 0) {
                    try {
                        this.f6900u = Bitmap.createBitmap(this.width, i2, Bitmap.Config.ARGB_8888);
                    } catch (OutOfMemoryError e2) {
                        this.f6900u = Bitmap.createBitmap(this.width, i2, Bitmap.Config.ARGB_4444);
                    }
                }
                m3351d();
                m3356a();
            }
        }
    }

    /* renamed from: c */
    private void recycle() {
        if (this.f6898s != null) {
            this.f6898s.recycle();
            this.f6898s = null;
        }
        if (this.f6899t != null) {
            this.f6899t.recycle();
            this.f6899t = null;
        }
        if (this.f6900u != null) {
            this.f6900u.recycle();
            this.f6900u = null;
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m3353b();
        m3351d();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycle();
    }

    /* renamed from: d */
    private void m3351d() {
        if (this.f6898s != null) {
            int width = this.f6898s.getWidth();
            int height = this.f6898s.getHeight();
            Canvas canvas = new Canvas(this.f6898s);
            if (this.dotHeight < 0 && this.dotDrawable != null) {
                this.dotHeight = -Math.max(this.dotDrawable.getIntrinsicHeight(), Math.max(this.lineWidth >> 2, 1));
            }
            int abs = Math.abs(this.dotHeight);
            if (this.lineDrawable != null) {
                this.lineDrawable.setBounds(0, abs, width, height);
                this.lineDrawable.draw(canvas);
            }
            if (this.dotDrawable != null && abs > 0) {
                canvas.save();
                canvas.clipRect(0, 0, width, abs);
                this.dotDrawable.setBounds(0, 0, width, abs);
                this.dotDrawable.draw(canvas);
                canvas.restore();
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        if (this.height != paddingTop || this.width != paddingLeft) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof SAnalyzer.SAnalyzerLayoutParams) {
                SAnalyzer.SAnalyzerLayoutParams params = (SAnalyzer.SAnalyzerLayoutParams) layoutParams;
                this.divideHeight = params.getDivideHeight(measuredHeight);
                this.dotHeight = params.getDotHeight(measuredHeight);
                this.lineDivideWidth = params.getLineDivideWidth(measuredWidth);
                this.lineWidth = params.getLineWidth(measuredWidth);
                this.reflectionHeight = params.getReflectionHeight(measuredHeight);
            }
            if (paddingLeft != this.width) {
                this.width = measuredWidth;
                setNumberOfLine((this.width + this.lineDivideWidth) / (this.lineWidth + this.lineDivideWidth));
            }
            if (paddingTop != this.height) {
                this.height = measuredHeight;
                m3353b();
            }
            invalidate();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        OnActiveListener onActiveListener = this.onActiveListener;
        if (onActiveListener != null && i != visibility) {
            if (i == 0) {
                onActiveListener.active();
            } else if (visibility == 0) {
                onActiveListener.inactive();
            }
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        boolean isEnabled = isEnabled();
        super.setEnabled(z);
        OnActiveListener onActiveListener = this.onActiveListener;
        if (onActiveListener != null && z != isEnabled) {
            if (z) {
                onActiveListener.active();
            } else {
                onActiveListener.inactive();
            }
        }
    }

    public void setOnActiveListener(OnActiveListener onActiveListener) {
        this.onActiveListener = onActiveListener;
    }
}
