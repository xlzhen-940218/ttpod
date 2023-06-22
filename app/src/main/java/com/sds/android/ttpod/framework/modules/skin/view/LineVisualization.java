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
import com.sds.android.ttpod.framework.modules.skin.p129b.SAnalyzer;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.view.a */
/* loaded from: classes.dex */
public class LineVisualization extends View {

    /* renamed from: a */
    private Drawable f6880a;

    /* renamed from: b */
    private Drawable f6881b;

    /* renamed from: c */
    private InterfaceC2008a f6882c;

    /* renamed from: d */
    private int f6883d;

    /* renamed from: e */
    private int f6884e;

    /* renamed from: f */
    private int f6885f;

    /* renamed from: g */
    private int f6886g;

    /* renamed from: h */
    private int f6887h;

    /* renamed from: i */
    private final Rect f6888i;

    /* renamed from: j */
    private final Rect f6889j;

    /* renamed from: k */
    private int[] f6890k;

    /* renamed from: l */
    private long[] f6891l;

    /* renamed from: m */
    private int f6892m;

    /* renamed from: n */
    private int f6893n;

    /* renamed from: o */
    private int f6894o;

    /* renamed from: p */
    private boolean f6895p;

    /* renamed from: q */
    private int f6896q;

    /* renamed from: r */
    private int f6897r;

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
    public interface InterfaceC2008a {
        /* renamed from: a */
        void mo3350a();

        /* renamed from: b */
        void mo3349b();
    }

    public LineVisualization(Context context) {
        super(context);
        this.f6884e = 5;
        this.f6885f = -1;
        this.f6886g = 553648127;
        this.f6887h = 1627389951;
        this.f6888i = new Rect();
        this.f6889j = new Rect();
        this.f6892m = 5;
        this.f6893n = 5;
        this.f6894o = 0;
        this.f6895p = false;
        this.f6901v = new Paint();
        this.f6902w = new Canvas();
    }

    public void setNumberOfLine(int i) {
        int max = Math.max(Math.min(i, 128), 4);
        synchronized (this) {
            this.f6894o = max;
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
        return this.f6894o;
    }

    public void setLineDrawable(Drawable drawable) {
        if (this.f6880a != drawable) {
            this.f6880a = drawable;
            m3351d();
        }
    }

    public void setDotDrawable(Drawable drawable) {
        if (this.f6881b != drawable) {
            this.f6881b = drawable;
            m3351d();
        }
    }

    public void setReflectionHeight(int i) {
        if (i != this.f6883d) {
            this.f6883d = i;
            m3351d();
        }
    }

    /* renamed from: a */
    private void m3356a() {
        if (this.f6899t != null) {
            int height = this.f6899t.getHeight();
            this.f6901v.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            this.f6901v.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, height, this.f6887h, this.f6886g, Shader.TileMode.CLAMP));
        }
    }

    public void setDivideHeight(int i) {
        if (this.f6884e != i) {
            this.f6884e = i;
            m3353b();
        }
    }

    /* renamed from: a */
    public void m3355a(int i, int i2) {
        if (this.f6886g != i || this.f6887h != i2) {
            this.f6886g = i;
            this.f6887h = i2;
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
                i = this.f6894o;
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
            int min = Math.min(this.f6894o, iArr.length >> 1);
            int height = this.f6898s.getHeight();
            int width = this.f6898s.getWidth();
            int abs = Math.abs(this.f6885f);
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
                i += this.f6892m + width;
            }
            canvas.drawBitmap(this.f6899t, 0.0f, getTopPaddingOffset(), (Paint) null);
            if (this.f6883d > 0) {
                canvas2.setBitmap(this.f6900u);
                canvas2.drawBitmap(this.f6899t, 0.0f, 0.0f, (Paint) null);
                canvas2.drawRect(0.0f, 0.0f, this.f6896q, height, this.f6901v);
                canvas.save();
                canvas.translate(0.0f, getTopPaddingOffset() + this.f6884e + height + this.f6883d);
                canvas.scale(1.0f, -(this.f6883d / height));
                canvas.drawBitmap(this.f6900u, 0.0f, getTopPaddingOffset(), (Paint) null);
                canvas.restore();
            }
        }
    }

    public void setLineWidth(int i) {
        if (i > 0 && this.f6893n != i) {
            int i2 = this.f6893n;
            setNumberOfLine((this.f6896q + this.f6892m) / (this.f6893n + this.f6892m));
            m3353b();
        }
    }

    public void setDotHeight(int i) {
        if (i > 0 && this.f6885f != i) {
            this.f6885f = i;
        }
    }

    public void setLineDivideWidth(int i) {
        if (i >= 0 && i != this.f6892m) {
            this.f6892m = i;
            setNumberOfLine((this.f6896q + this.f6892m) / (this.f6893n + this.f6892m));
        }
    }

    /* renamed from: b */
    private void m3353b() {
        int i = this.f6893n;
        int i2 = this.f6897r - (this.f6883d > 0 ? this.f6884e + this.f6883d : 0);
        if (this.f6898s == null || this.f6898s.getWidth() != i || this.f6898s.getHeight() != i2) {
            m3352c();
            if (i > 0 && i2 > 0) {
                this.f6898s = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                try {
                    this.f6899t = Bitmap.createBitmap(this.f6896q, i2, Bitmap.Config.ARGB_8888);
                } catch (OutOfMemoryError e) {
                    this.f6899t = Bitmap.createBitmap(this.f6896q, i2, Bitmap.Config.ARGB_4444);
                }
                if (this.f6883d > 0) {
                    try {
                        this.f6900u = Bitmap.createBitmap(this.f6896q, i2, Bitmap.Config.ARGB_8888);
                    } catch (OutOfMemoryError e2) {
                        this.f6900u = Bitmap.createBitmap(this.f6896q, i2, Bitmap.Config.ARGB_4444);
                    }
                }
                m3351d();
                m3356a();
            }
        }
    }

    /* renamed from: c */
    private void m3352c() {
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
        m3352c();
    }

    /* renamed from: d */
    private void m3351d() {
        if (this.f6898s != null) {
            int width = this.f6898s.getWidth();
            int height = this.f6898s.getHeight();
            Canvas canvas = new Canvas(this.f6898s);
            if (this.f6885f < 0 && this.f6881b != null) {
                this.f6885f = -Math.max(this.f6881b.getIntrinsicHeight(), Math.max(this.f6893n >> 2, 1));
            }
            int abs = Math.abs(this.f6885f);
            if (this.f6880a != null) {
                this.f6880a.setBounds(0, abs, width, height);
                this.f6880a.draw(canvas);
            }
            if (this.f6881b != null && abs > 0) {
                canvas.save();
                canvas.clipRect(0, 0, width, abs);
                this.f6881b.setBounds(0, 0, width, abs);
                this.f6881b.draw(canvas);
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
        if (this.f6897r != paddingTop || this.f6896q != paddingLeft) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof SAnalyzer.C1979a) {
                SAnalyzer.C1979a c1979a = (SAnalyzer.C1979a) layoutParams;
                this.f6884e = c1979a.m3836g(measuredHeight);
                this.f6885f = c1979a.m3833j(measuredHeight);
                this.f6892m = c1979a.m3835h(measuredWidth);
                this.f6893n = c1979a.m3834i(measuredWidth);
                this.f6883d = c1979a.m3837f(measuredHeight);
            }
            if (paddingLeft != this.f6896q) {
                this.f6896q = measuredWidth;
                setNumberOfLine((this.f6896q + this.f6892m) / (this.f6893n + this.f6892m));
            }
            if (paddingTop != this.f6897r) {
                this.f6897r = measuredHeight;
                m3353b();
            }
            invalidate();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        InterfaceC2008a interfaceC2008a = this.f6882c;
        if (interfaceC2008a != null && i != visibility) {
            if (i == 0) {
                interfaceC2008a.mo3350a();
            } else if (visibility == 0) {
                interfaceC2008a.mo3349b();
            }
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        boolean isEnabled = isEnabled();
        super.setEnabled(z);
        InterfaceC2008a interfaceC2008a = this.f6882c;
        if (interfaceC2008a != null && z != isEnabled) {
            if (z) {
                interfaceC2008a.mo3350a();
            } else {
                interfaceC2008a.mo3349b();
            }
        }
    }

    public void setOnActiveListener(InterfaceC2008a interfaceC2008a) {
        this.f6882c = interfaceC2008a;
    }
}
