package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class RadialProgressWidget extends View {

    /* renamed from: W */
    private static int f8139W = 50;

    /* renamed from: A */
    private boolean f8140A;

    /* renamed from: B */
    private boolean f8141B;

    /* renamed from: C */
    private float f8142C;

    /* renamed from: D */
    private float f8143D;

    /* renamed from: E */
    private int f8144E;

    /* renamed from: F */
    private int f8145F;

    /* renamed from: G */
    private int f8146G;

    /* renamed from: H */
    private String f8147H;

    /* renamed from: I */
    private InterfaceC2256b f8148I;

    /* renamed from: J */
    private Bitmap f8149J;

    /* renamed from: K */
    private int f8150K;

    /* renamed from: L */
    private int f8151L;

    /* renamed from: M */
    private int f8152M;

    /* renamed from: N */
    private Bitmap f8153N;

    /* renamed from: O */
    private int ringDoubleColor;

    /* renamed from: P */
    private float ringDoubleWidth;

    /* renamed from: Q */
    private int ringDoubleOutsideExtendValue;

    /* renamed from: R */
    private int ringDoubleInsideExtendValue;

    /* renamed from: S */
    private int radialTextBackground;

    /* renamed from: T */
    private Bitmap f8159T;

    /* renamed from: U */
    private float f8160U;

    /* renamed from: V */
    private float f8161V;

    /* renamed from: Z */
    private int f8162Z;

    /* renamed from: a */
    protected int f8163a;

    /* renamed from: b */
    protected int f8164b;

    /* renamed from: c */
    protected int f8165c;

    /* renamed from: d */
    protected boolean f8166d;

    /* renamed from: e */
    protected InterfaceC2255a f8167e;

    /* renamed from: f */
    protected float f8168f;

    /* renamed from: g */
    protected float f8169g;

    /* renamed from: h */
    protected float f8170h;

    /* renamed from: i */
    protected float f8171i;

    /* renamed from: j */
    protected float f8172j;

    /* renamed from: k */
    protected float f8173k;

    /* renamed from: l */
    private RectF f8174l;

    /* renamed from: m */
    private RectF f8175m;

    /* renamed from: n */
    private RectF f8176n;

    /* renamed from: o */
    private float f8177o;

    /* renamed from: p */
    private int f8178p;

    /* renamed from: q */
    private int f8179q;

    /* renamed from: r */
    private Paint f8180r;

    /* renamed from: s */
    private int f8181s;

    /* renamed from: t */
    private int borderColor;

    /* renamed from: u */
    private int f8183u;

    /* renamed from: v */
    private int f8184v;

    /* renamed from: w */
    private int ringShadowColor;

    /* renamed from: x */
    private float borderWidth;

    /* renamed from: y */
    private float f8187y;

    /* renamed from: z */
    private String f8188z;

    /* renamed from: com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2255a {
        /* renamed from: a */
        void m1400a();

        /* renamed from: b */
        void m1399b();

        /* renamed from: c */
        void m1398c();
    }

    /* renamed from: com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2256b {
        /* renamed from: a */
        void mo1397a(RadialProgressWidget radialProgressWidget, int i);
    }

    public RadialProgressWidget(Context context) {
        this(context, null);
    }

    public RadialProgressWidget(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadialProgressWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8166d = true;
        this.f8167e = null;
        this.f8168f = 0.0f;
        this.f8169g = 0.0f;
        this.f8170h = 0.0f;
        this.f8171i = 0.0f;
        this.f8172j = 0.0f;
        this.f8173k = 0.0f;
        this.f8174l = null;
        this.f8175m = null;
        this.f8176n = null;
        this.f8177o = 0.0f;
        this.f8179q = 240;
        this.f8180r = new Paint(1);
        this.f8181s = Color.parseColor("#FF636363");
        this.borderColor = 0xff000000;
        this.f8183u = -1;
        this.f8184v = -1;
        this.ringShadowColor = 0xff000000;
        this.borderWidth = 5.0f;
        this.f8187y = 2.0f;
        this.f8188z = null;
        this.f8140A = false;
        this.f8141B = false;
        this.f8142C = 0.0f;
        this.f8143D = 0.0f;
        this.f8144E = 0;
        this.f8145F = 0;
        this.f8146G = f8139W;
        this.f8147H = null;
        this.f8149J = null;
        this.f8150K = 0;
        this.f8151L = 0;
        this.ringDoubleColor = Color.parseColor("#222222");
        this.ringDoubleWidth = 2.0f;
        this.ringDoubleOutsideExtendValue = 2;
        this.ringDoubleInsideExtendValue = 4;
        this.f8160U = 1.0f;
        this.f8161V = 1.0f;
        this.f8162Z = 0;
        int pointerResource = R.drawable.img_radial_progress_pointer;
        int backgroundResource = R.drawable.img_radial_progress_bg;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RadialProgressWidget, i, 0);
            pointerResource = obtainStyledAttributes.getResourceId(R.styleable.RadialProgressWidget_pointerResource, R.drawable.img_radial_progress_pointer);
            backgroundResource = obtainStyledAttributes.getResourceId(R.styleable.RadialProgressWidget_backgroundResource, R.drawable.img_radial_progress_bg);
            this.ringShadowColor = obtainStyledAttributes.getColor(R.styleable.RadialProgressWidget_ringShadowColor, 0xff000000);
            this.ringDoubleColor = obtainStyledAttributes.getColor(R.styleable.RadialProgressWidget_ringDoubleColor, Color.parseColor("#222222"));
            this.ringDoubleWidth = obtainStyledAttributes.getFloat(R.styleable.RadialProgressWidget_ringDoubleWidth, 2.0f);
            this.borderWidth = obtainStyledAttributes.getFloat(R.styleable.RadialProgressWidget_radialBorderWidth, 5.0f);
            this.borderColor = obtainStyledAttributes.getColor(R.styleable.RadialProgressWidget_borderColor, 0xff000000);
            this.ringDoubleInsideExtendValue = obtainStyledAttributes.getInteger(R.styleable.RadialProgressWidget_ringDoubleInsideExtendValue, 4);
            this.ringDoubleOutsideExtendValue = obtainStyledAttributes.getInteger(R.styleable.RadialProgressWidget_ringDoubleOutsideExtendValue, 2);
            this.radialTextBackground = obtainStyledAttributes.getResourceId(R.styleable.RadialProgressWidget_radialTextBackground, 0);
        }
        this.f8149J = m1401d(pointerResource);
        this.f8153N = m1401d(backgroundResource);
        if (this.radialTextBackground != 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), this.radialTextBackground, options);
            options.inSampleSize = options.outWidth / options.outHeight;
            options.outWidth = (int) (options.outWidth * 0.8f);
            options.outHeight = (int) (options.outHeight * 0.8f);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inInputShareable = true;
            options.inPurgeable = true;
            this.f8159T = BitmapFactory.decodeResource(getResources(), this.radialTextBackground, options);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        this.f8180r.setStyle(Paint.Style.STROKE);
        this.f8180r.setStrokeWidth(this.borderWidth * getResources().getDisplayMetrics().density);
        this.f8180r.setColor(this.borderColor);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        if (this.f8153N != null) {
            canvas.save();
            canvas.scale(this.f8160U, this.f8160U);
            canvas.drawBitmap(this.f8153N, (int) ((this.f8163a / 2) - ((this.f8153N.getWidth() * this.f8160U) / 2.0f)), (int) ((this.f8164b / 2) - ((this.f8153N.getHeight() * this.f8160U) / 2.0f)), paint);
            canvas.restore();
        }
        if (this.f8165c <= f8139W) {
            f = ((this.f8165c * this.f8179q) / f8139W) + 0.01f;
            canvas.drawArc(this.f8174l, 30.0f, f - this.f8179q, false, this.f8180r);
        } else {
            f = 0.0f;
        }
        this.f8180r.setStyle(Paint.Style.STROKE);
        this.f8180r.setColor(this.ringDoubleColor);
        this.f8180r.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        this.f8180r.setStrokeWidth(this.ringDoubleWidth * getResources().getDisplayMetrics().density);
        if (this.f8175m != null) {
            canvas.drawArc(this.f8175m, 153.0f, 234.0f, false, this.f8180r);
        }
        if (this.f8176n != null) {
            canvas.drawArc(this.f8176n, 153.0f, 234.0f, false, this.f8180r);
        }
        if (this.f8140A || this.f8141B) {
            this.f8180r.setColor(this.f8183u);
            this.f8180r.setTextSize(this.f8142C);
            if (this.f8147H != null) {
                this.f8180r.setTypeface(Typeface.createFromAsset(getContext().getAssets(), this.f8147H));
            }
        }
        if (this.f8140A) {
            canvas.drawText(String.valueOf(this.f8144E) + "%", (this.f8163a / 2) - (this.f8180r.measureText(String.valueOf(this.f8144E) + "%") / 2.0f), (this.f8164b / 2) + (this.f8177o / 8.0f), this.f8180r);
        } else if (this.f8141B) {
            this.f8180r.setStrokeWidth(getResources().getDisplayMetrics().density * 3.0f);
            canvas.drawText(String.valueOf(this.f8165c), (this.f8163a / 2) - (this.f8180r.measureText(String.valueOf(this.f8165c)) / 2.0f), (this.f8164b / 2) + (this.f8177o / 8.0f), this.f8180r);
        }
        if (this.f8188z != null) {
            this.f8180r.setColor(this.f8184v);
            float measureText = this.f8180r.measureText(this.f8188z);
            this.f8180r.setTextSize(this.f8143D);
            canvas.drawText(this.f8188z, (this.f8163a / 2) - (measureText / 5.0f), (this.f8164b / 2) + (this.f8177o / 3.0f), this.f8180r);
        }
        if (this.f8159T != null) {
            canvas.drawBitmap(this.f8159T, ((this.f8163a / 2) - (this.f8159T.getWidth() / 2)) - 1, ((this.f8164b / 2) - (this.f8159T.getHeight() / 2)) + 1, this.f8180r);
        }
        if (this.f8149J != null) {
            canvas.save();
            canvas.rotate((239.0f + f) % 360.0f, this.f8163a / 2, this.f8163a / 2);
            canvas.scale(this.f8161V, this.f8161V);
            canvas.drawBitmap(this.f8149J, (int) ((this.f8163a / 2) - (((this.f8149J.getWidth() * this.f8161V) - 4.0f) / 2.0f)), (int) ((this.f8163a / 2) - (((this.f8149J.getHeight() * this.f8161V) + 10.0f) / 2.0f)), paint);
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > i2) {
            this.f8178p = i2;
            this.f8177o = (this.f8178p / 2) - (getPaddingTop() + getPaddingBottom());
        } else {
            this.f8178p = i;
            this.f8177o = (this.f8178p / 2) - (getPaddingLeft() + getPaddingRight());
        }
        this.f8163a = getWidth();
        this.f8164b = getHeight();
        int width = this.f8153N.getWidth();
        int height = this.f8153N.getHeight();
        if (this.f8163a < width || this.f8164b < height) {
            float min = Math.min((this.f8163a + 0.01f) / width, (this.f8164b + 0.01f) / height);
            Matrix matrix = new Matrix();
            matrix.postScale(min, min);
            Bitmap createBitmap = Bitmap.createBitmap(this.f8153N, 0, 0, width, height, matrix, true);
            this.f8153N.recycle();
            this.f8153N = createBitmap;
        } else {
            this.f8160U = (this.f8163a + 0.01f) / width;
        }
        int width2 = this.f8149J.getWidth();
        int height2 = this.f8149J.getHeight();
        if (this.f8163a < width2 || this.f8164b < height2) {
            float min2 = Math.min((this.f8163a + 0.01f) / width2, (this.f8164b + 0.01f) / height2);
            Matrix matrix2 = new Matrix();
            matrix2.postScale(min2, min2);
            Bitmap createBitmap2 = Bitmap.createBitmap(this.f8149J, 0, 0, width2, height2, matrix2, true);
            this.f8149J.recycle();
            this.f8149J = createBitmap2;
        } else {
            this.f8161V = (this.f8163a + 0.01f) / width2;
        }
        int width3 = (int) (((this.f8163a / 2) - ((this.f8153N.getWidth() * this.f8160U) / 2.0f)) + getPaddingLeft());
        int width4 = (int) (((this.f8163a / 2) + ((this.f8153N.getWidth() * this.f8160U) / 2.0f)) - getPaddingRight());
        int width5 = (int) (((this.f8164b / 2) - ((this.f8153N.getWidth() * this.f8160U) / 2.0f)) + getPaddingTop());
        int width6 = (int) (((this.f8164b / 2) + ((this.f8153N.getWidth() * this.f8160U) / 2.0f)) - getPaddingBottom());
        this.f8174l = new RectF(new Rect(width3, width5, width4, width6));
        if (this.ringDoubleOutsideExtendValue != 0) {
            this.f8175m = new RectF(new Rect(width3 - this.ringDoubleOutsideExtendValue, width5 - this.ringDoubleOutsideExtendValue, this.ringDoubleOutsideExtendValue + width4, this.ringDoubleOutsideExtendValue + width6));
        }
        if (this.ringDoubleInsideExtendValue != 0) {
            this.f8176n = new RectF(new Rect(width3 + this.ringDoubleInsideExtendValue, width5 + this.ringDoubleInsideExtendValue, width4 - this.ringDoubleInsideExtendValue, width6 - this.ringDoubleInsideExtendValue));
        }
        this.f8142C = this.f8177o / 2.0f;
        this.f8143D = this.f8177o / 5.0f;
        this.f8151L = (int) (this.f8177o * 0.5f);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f8149J != null) {
            this.f8149J.recycle();
            this.f8149J = null;
        }
        if (this.f8153N != null) {
            this.f8153N.recycle();
            this.f8153N = null;
        }
        if (this.f8159T != null) {
            this.f8159T.recycle();
            this.f8159T = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m1407a(int i) {
        if (i > f8139W - 1) {
            i = f8139W;
        } else if (i < 1) {
            i = 0;
        }
        if (this.f8152M != i) {
            this.f8152M = i;
            setCurrentValue(i);
            if (this.f8148I != null) {
                this.f8148I.mo1397a(this, i);
            }
            invalidate();
        }
    }

    /* renamed from: d */
    private Bitmap m1401d(int i) {
        try {
            return BitmapFactory.decodeResource(getResources(), i);
        } catch (OutOfMemoryError e) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            return BitmapFactory.decodeResource(getResources(), i, options);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean m1406a(int i, int i2) {
        return Math.pow((double) (i - (this.f8163a / 2)), 2.0d) + Math.pow((double) (i2 - (this.f8163a / 2)), 2.0d) > Math.pow((double) ((int) (this.f8174l.width() * 0.5f)), 2.0d);
    }

    /* renamed from: b */
    protected final boolean m1403b(int i, int i2) {
        return Math.pow((double) (i - (this.f8163a / 2)), 2.0d) + Math.pow((double) (i2 - (this.f8163a / 2)), 2.0d) < Math.pow((double) ((int) (this.f8174l.width() * 0.5f)), 2.0d);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f8166d) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f8168f = motionEvent.getX();
                    this.f8169g = motionEvent.getY();
                    if (this.f8167e != null) {
                        this.f8167e.m1400a();
                    }
                    if (!m1406a((int) this.f8168f, (int) this.f8169g)) {
                        this.f8150K = ((int) (this.f8163a * (Math.cos(Math.toRadians(30.0d)) + 1.0d))) / 2;
                        this.f8162Z = ((int) (this.f8163a * (1.0d - Math.sin(Math.toRadians(30.0d))))) / 2;
                        break;
                    }
                    break;
                case 1:
                    if (this.f8167e != null) {
                        this.f8167e.m1398c();
                    }
                    this.f8172j = 0.0f;
                    this.f8173k = 0.0f;
                    break;
                case 2:
                    if (this.f8167e != null) {
                        this.f8167e.m1399b();
                    }
                    int abs = Math.abs(((int) motionEvent.getX()) - ((int) this.f8168f));
                    int abs2 = Math.abs(((int) motionEvent.getY()) - ((int) this.f8169g));
                    if (Math.sqrt((abs * abs) + (abs2 * abs2)) >= 20.0d) {
                        if (this.f8172j == 0.0f && this.f8173k == 0.0f) {
                            this.f8172j = this.f8168f;
                            this.f8173k = this.f8169g;
                        }
                        this.f8170h = motionEvent.getX() - this.f8172j;
                        this.f8171i = motionEvent.getY() - this.f8173k;
                        this.f8172j = motionEvent.getX();
                        this.f8173k = motionEvent.getY();
                        if (!m1406a((int) this.f8168f, (int) this.f8169g) && m1403b((int) this.f8172j, (int) this.f8173k)) {
                            int m1405a = m1405a(new Point(this.f8150K, this.f8162Z), new Point(this.f8163a / 2, this.f8164b / 2), new Point((int) motionEvent.getX(), (int) motionEvent.getY()));
                            if (m1405a > this.f8179q) {
                                if (this.f8165c < 15) {
                                    m1405a = 0;
                                } else if (this.f8165c > 35) {
                                    m1405a = this.f8179q;
                                }
                            }
                            int i = (f8139W * m1405a) / this.f8179q;
                            if (i > f8139W) {
                                i = f8139W;
                            }
                            int i2 = i >= 0 ? i : 0;
                            LogUtils.debug("zz:", "tempValue= " + i2 + "   getCurrentValue= " + this.f8165c + "   angle=  " + m1405a);
                            if (Math.abs(i2 - this.f8165c) > 50) {
                                if (this.f8165c == 0 || this.f8165c == f8139W) {
                                    m1407a(this.f8165c);
                                    break;
                                } else if (!m1406a((int) this.f8172j, (int) this.f8173k)) {
                                    if (this.f8170h < 0.0f) {
                                        m1407a(getCurrentValue() - (((int) Math.abs(this.f8170h)) % 2));
                                        break;
                                    } else {
                                        m1407a(getCurrentValue() + (((int) Math.abs(this.f8170h)) % 2));
                                        break;
                                    }
                                }
                            } else {
                                m1407a(i2);
                                break;
                            }
                        }
                    }
                    break;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private int m1405a(Point point, Point point2, Point point3) {
        Point point4 = new Point(point2.x - point.x, point2.y - point.y);
        Point point5 = new Point(point2.x - point3.x, point2.y - point3.y);
        float atan2 = (float) Math.atan2((point4.x * point5.y) - (point4.y * point5.x), (point4.x * point5.x) + (point4.y * point5.y));
        return ((int) Math.toDegrees((double) atan2)) < 0 ? ((int) Math.toDegrees(atan2)) + 360 : (int) Math.toDegrees(atan2);
    }

    public int getCurrentValue() {
        return this.f8165c;
    }

    public void setOnRadialViewValueChanged(InterfaceC2256b interfaceC2256b) {
        this.f8148I = interfaceC2256b;
    }

    public void setCurrentValue(int i) {
        if (this.f8165c != i) {
            this.f8165c = i;
            invalidate();
        }
    }

    public static int getMaxValue() {
        return f8139W;
    }

    /* renamed from: b */
    public static int m1404b(int i) {
        return Math.min((f8139W * i) / 1000, f8139W);
    }

    /* renamed from: c */
    public static int m1402c(int i) {
        return Math.min((i * 1000) / f8139W, 1000);
    }

    public void setMaxValue(int i) {
        f8139W = i;
    }

    public int getBaseColor() {
        return this.f8181s;
    }

    public void setBaseColor(int i) {
        this.f8181s = i;
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
    }

    public int getCenterTextColor() {
        return this.f8183u;
    }

    public void setCenterTextColor(int i) {
        this.f8183u = i;
    }

    public int getSecondaryTextColor() {
        return this.f8184v;
    }

    public void setSecondaryTextColor(int i) {
        this.f8184v = i;
    }

    public int getShadowColor() {
        return this.ringShadowColor;
    }

    public void setShadowColor(int i) {
        this.ringShadowColor = i;
    }

    public float getBorderStrokeThickness() {
        return this.borderWidth;
    }

    public void setBorderStrokeThickness(float f) {
        this.borderWidth = f;
    }

    public float getShadowRadius() {
        return this.f8187y;
    }

    public void setShadowRadius(float f) {
        this.f8187y = f;
    }

    public String getSecondaryText() {
        return this.f8188z;
    }

    public void setSecondaryText(String str) {
        this.f8188z = str;
    }

    public void setShowPercentText(boolean z) {
        this.f8140A = z;
    }

    public float getCenterTextSize() {
        return this.f8142C;
    }

    public void setCenterTextSize(float f) {
        this.f8142C = f;
    }

    public float getSecondaryTextSize() {
        return this.f8143D;
    }

    public void setSecondaryTextSize(float f) {
        this.f8143D = f;
    }

    public void setTouchEnabled(boolean z) {
        this.f8166d = z;
    }

    public int getMinChangeValue() {
        return this.f8145F;
    }

    public void setMinChangeValue(int i) {
        this.f8145F = i;
    }

    public int getMaxChangeValue() {
        return this.f8146G;
    }

    public void setMaxChangeValue(int i) {
        this.f8146G = i;
    }

    public void setCircleBackgroundBitmap(Bitmap bitmap) {
        this.f8153N = bitmap;
    }

    public void setFontName(String str) {
        this.f8147H = str;
    }

    public void setMotionListener(InterfaceC2255a interfaceC2255a) {
        this.f8167e = interfaceC2255a;
    }
}
