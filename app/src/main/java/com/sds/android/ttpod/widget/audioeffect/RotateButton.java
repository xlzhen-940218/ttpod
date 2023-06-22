package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.p106a.C1780b;

/* loaded from: classes.dex */
public class RotateButton extends View {

    /* renamed from: a */
    static final /* synthetic */ boolean f8189a;

    /* renamed from: l */
    private static final double[] f8190l;

    /* renamed from: m */
    private static final float[] f8191m;

    /* renamed from: b */
    private Bitmap f8192b;

    /* renamed from: c */
    private Bitmap f8193c;

    /* renamed from: d */
    private Bitmap f8194d;

    /* renamed from: e */
    private Bitmap f8195e;

    /* renamed from: f */
    private Bitmap f8196f;

    /* renamed from: g */
    private Bitmap f8197g;

    /* renamed from: h */
    private Bitmap f8198h;

    /* renamed from: i */
    private Bitmap f8199i;

    /* renamed from: j */
    private Bitmap f8200j;

    /* renamed from: k */
    private Paint f8201k;

    /* renamed from: n */
    private Point[] f8202n;

    /* renamed from: o */
    private int f8203o;

    /* renamed from: p */
    private InterfaceC2261b f8204p;

    /* renamed from: q */
    private float f8205q;

    /* renamed from: r */
    private boolean f8206r;

    /* renamed from: s */
    private Point f8207s;

    /* renamed from: t */
    private String f8208t;

    /* renamed from: u */
    private String f8209u;

    /* renamed from: v */
    private float f8210v;

    /* renamed from: w */
    private boolean f8211w;

    /* renamed from: x */
    private Bitmap f8212x;

    /* renamed from: y */
    private int f8213y;

    /* renamed from: com.sds.android.ttpod.widget.audioeffect.RotateButton$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2261b {
        /* renamed from: a */
        void m1375a(RotateButton rotateButton, int i, int i2);
    }

    static {
        f8189a = !RotateButton.class.desiredAssertionStatus();
        f8190l = new double[]{225.0d, 198.0d, 171.0d, 144.0d, 117.0d, 90.0d, 63.0d, 36.0d, 9.0d, 342.0d, 315.0d, 288.0d, 261.0d, 234.0d, 207.0d};
        f8191m = new float[]{225.0f, 252.0f, 279.0f, 306.0f, 333.0f, 360.0f, 27.0f, 54.0f, 81.0f, 108.0f, 135.0f};
    }

    public void setLeftText(String str) {
        this.f8208t = str;
    }

    public void setRightText(String str) {
        this.f8209u = str;
    }

    public void setOnRotateChangeListener(InterfaceC2261b interfaceC2261b) {
        this.f8204p = interfaceC2261b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1394a(int i, int i2) {
        float m1377b = C2259a.m1377b(new Point(i, i2));
        if (m1377b <= f8191m[f8191m.length - 1] || m1377b >= 210.0f) {
            this.f8205q = m1377b;
        }
    }

    /* renamed from: a */
    public void m1393a(Bitmap bitmap, Bitmap bitmap2) {
        this.f8192b = bitmap;
        this.f8193c = bitmap2;
        invalidate();
    }

    /* renamed from: b */
    public void m1387b(Bitmap bitmap, Bitmap bitmap2) {
        this.f8194d = bitmap;
        this.f8195e = bitmap2;
        if (this.f8194d != null) {
            m1395a(Math.min(this.f8194d.getWidth() / 2, this.f8194d.getHeight() / 2) - (this.f8213y * this.f8210v));
        }
        invalidate();
    }

    public void setHalfReference(Bitmap bitmap) {
        this.f8212x = bitmap;
    }

    /* renamed from: c */
    public void m1384c(Bitmap bitmap, Bitmap bitmap2) {
        this.f8196f = bitmap;
        this.f8197g = bitmap2;
        invalidate();
    }

    public void setLeftRightBalance(boolean z) {
        this.f8211w = z;
    }

    public int getNumberOfCalibration() {
        return 11;
    }

    public void setCalibration(int i) {
        if (!f8189a && f8191m.length != 11) {
            throw new AssertionError();
        }
        if (i >= 0 && i < 11) {
            this.f8205q = f8191m[i];
            this.f8203o = i;
            postInvalidate();
        }
    }

    public RotateButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f8192b = null;
        this.f8193c = null;
        this.f8194d = null;
        this.f8195e = null;
        this.f8196f = null;
        this.f8197g = null;
        this.f8198h = null;
        this.f8199i = null;
        this.f8200j = null;
        this.f8201k = null;
        this.f8202n = new Point[14];
        this.f8203o = 5;
        this.f8204p = null;
        this.f8205q = 360.0f;
        this.f8206r = false;
        this.f8207s = new Point();
        this.f8208t = null;
        this.f8209u = null;
        this.f8210v = getResources().getDisplayMetrics().density;
        this.f8212x = null;
        setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.widget.audioeffect.RotateButton.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        ViewParent parent = RotateButton.this.getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                        RotateButton.this.f8206r = true;
                        RotateButton.this.postInvalidate();
                        ((Vibrator) RotateButton.this.getContext().getSystemService("vibrator")).vibrate(50L);
                        break;
                    case 1:
                        if (RotateButton.this.f8204p != null) {
                            RotateButton.this.f8204p.m1375a(RotateButton.this, RotateButton.this.f8203o, 10);
                        }
                        RotateButton.this.f8206r = false;
                        RotateButton.this.postInvalidate();
                        RotateButton.this.setCalibration(RotateButton.this.f8203o);
                        break;
                    case 2:
                        RotateButton.this.m1394a((int) (motionEvent.getX() - RotateButton.this.f8207s.x), (int) (-(motionEvent.getY() - RotateButton.this.f8207s.y)));
                        RotateButton.this.f8203o = RotateButton.this.m1396a();
                        RotateButton.this.postInvalidate();
                        RotateButton.this.setCalibration(RotateButton.this.f8203o);
                        break;
                    case 3:
                        RotateButton.this.f8206r = false;
                        RotateButton.this.postInvalidate();
                        RotateButton.this.setCalibration(RotateButton.this.f8203o);
                        break;
                }
                return true;
            }
        });
        setLongClickable(true);
        C1780b c1780b = new C1780b();
        c1780b.m4776a(false);
        Resources resources = getResources();
        m1387b(c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_normal), c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_pressed));
        m1393a(c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_indicator_normal), c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_indicator_pressed));
        m1384c(c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_graduation_normal), c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_graduation_pressed));
        this.f8208t = "-";
        this.f8209u = "+";
        this.f8213y = 2;
        this.f8198h = c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_up);
        this.f8199i = c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_down);
        this.f8200j = c1780b.m4790a(resources, R.drawable.img_background_effect_rotatebutton_ring);
        this.f8201k = new Paint();
        this.f8201k.setAntiAlias(true);
        this.f8201k.setStyle(Paint.Style.FILL);
        this.f8201k.setStrokeWidth(4.0f);
        this.f8201k.setColor(Color.parseColor("#1f2223"));
    }

    /* renamed from: a */
    private void m1395a(float f) {
        for (int i = 0; i < 14; i++) {
            double cos = Math.cos(Math.toRadians(f8190l[i])) * f;
            double sin = Math.sin(Math.toRadians(f8190l[i])) * f;
            this.f8202n[i] = null;
            this.f8202n[i] = new Point(((int) cos) + this.f8207s.x, this.f8207s.y - ((int) sin));
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f8207s.x = ((i3 - i) + 1) / 2;
        this.f8207s.y = ((i4 - i2) + 1) / 2;
        if (this.f8194d != null) {
            m1395a(Math.min(this.f8194d.getWidth() / 2, this.f8194d.getHeight() / 2) - (this.f8213y * this.f8210v));
        }
    }

    public void setRadiusOffset(int i) {
        this.f8213y = i;
    }

    /* renamed from: a */
    private void m1392a(Canvas canvas) {
        if (this.f8194d != null && this.f8195e != null) {
            canvas.save();
            if (this.f8206r) {
                canvas.drawBitmap(this.f8195e, this.f8207s.x - (this.f8195e.getWidth() / 2), this.f8207s.y - (this.f8195e.getHeight() / 2), (Paint) null);
            } else {
                canvas.drawBitmap(this.f8194d, this.f8207s.x - (this.f8194d.getWidth() / 2), this.f8207s.y - (this.f8194d.getHeight() / 2), (Paint) null);
            }
            canvas.restore();
        }
    }

    /* renamed from: b */
    private void m1386b(Canvas canvas) {
        if (this.f8200j != null) {
            canvas.save();
            int width = this.f8207s.x - (this.f8200j.getWidth() / 2);
            int height = this.f8207s.y - (this.f8200j.getHeight() / 2);
            canvas.drawBitmap(this.f8200j, width, height, (Paint) null);
            canvas.drawArc(new RectF(width - 2, height - 2, width + this.f8200j.getWidth() + 4, height + this.f8200j.getHeight() + 4), 30.0f, (this.f8203o * 24) - 240, true, this.f8201k);
            canvas.restore();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int m1396a() {
        int i = 0;
        while (true) {
            if (i >= 14) {
                i = 0;
                break;
            } else if (Math.abs(this.f8205q - (360.0d - f8190l[i])) < 13.5d || 360.0d - Math.abs(this.f8205q - (360.0d - f8190l[i])) < 13.5d) {
                break;
            } else {
                i++;
            }
        }
        if (i == 0) {
            return 10;
        }
        int i2 = i - 3;
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    /* renamed from: c */
    private void m1383c(Canvas canvas) {
        if (this.f8212x != null) {
            canvas.save();
            canvas.drawBitmap(this.f8212x, this.f8207s.x - (this.f8212x.getWidth() / 2), 0, (Paint) null);
            canvas.restore();
        }
    }

    /* renamed from: d */
    private void m1381d(Canvas canvas) {
        if (this.f8192b != null && this.f8193c != null) {
            canvas.save();
            canvas.rotate(this.f8205q, this.f8207s.x, this.f8207s.y);
            Bitmap bitmap = this.f8206r ? this.f8193c : this.f8192b;
            int width = this.f8207s.x - ((bitmap.getWidth() + 1) / 2);
            int height = this.f8207s.y - ((bitmap.getHeight() + 1) / 2);
            Paint paint = new Paint();
            paint.setFilterBitmap(true);
            canvas.drawBitmap(bitmap, width, height, paint);
            canvas.restore();
        }
    }

    /* renamed from: e */
    private void m1379e(Canvas canvas) {
        if (this.f8198h != null || this.f8199i != null) {
            canvas.save();
            float left = getLeft();
            float bottom = (getBottom() - this.f8199i.getHeight()) - (10.0f * this.f8210v);
            canvas.drawBitmap(this.f8199i, left, bottom, (Paint) null);
            canvas.drawBitmap(this.f8198h, (getWidth() - this.f8198h.getWidth()) - left, bottom, (Paint) null);
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m1386b(canvas);
        m1392a(canvas);
        m1383c(canvas);
        m1381d(canvas);
        m1379e(canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.widget.audioeffect.RotateButton$a */
    /* loaded from: classes.dex */
    public static class C2259a {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sds.android.ttpod.widget.audioeffect.RotateButton$a$a */
        /* loaded from: classes.dex */
        public enum EnumC2260a {
            QUAD_NONE,
            QUAD_ONE,
            QUAD_TWO,
            QUAD_THREE,
            QUAD_FOUR
        }

        /* renamed from: a */
        public static EnumC2260a m1378a(Point point) {
            if (point.x == 0 || point.y == 0) {
                return EnumC2260a.QUAD_NONE;
            }
            if (point.x > 0) {
                if (point.y > 0) {
                    return EnumC2260a.QUAD_ONE;
                }
                return EnumC2260a.QUAD_TWO;
            } else if (point.y < 0) {
                return EnumC2260a.QUAD_THREE;
            } else {
                return EnumC2260a.QUAD_FOUR;
            }
        }

        /* renamed from: b */
        public static int m1377b(Point point) {
            return (int) (m1376c(point) * 57.295780490442965d);
        }

        /* renamed from: c */
        private static double m1376c(Point point) {
            if (point.x == 0 && point.y == 0) {
                return 0.0d;
            }
            double asin = Math.asin(point.x / Math.sqrt((point.x * point.x) + (point.y * point.y)));
            switch (m1378a(point)) {
                case QUAD_ONE:
                    return asin;
                case QUAD_TWO:
                    return 3.1415926d - asin;
                case QUAD_THREE:
                    return 3.1415926d - asin;
                case QUAD_FOUR:
                    return asin + 6.2831852d;
                default:
                    if (point.x == 0 && point.y == 0) {
                        return 0.0d;
                    }
                    if (point.x == 0) {
                        return point.y > 0 ? 0.0d : 3.1415926d;
                    } else if (point.y == 0) {
                        if (point.x > 0) {
                            return 1.5707963d;
                        }
                        return 4.71238899230957d;
                    } else {
                        return asin;
                    }
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.f8194d != null) {
            size = this.f8194d.getWidth();
            size2 = this.f8194d.getHeight();
        }
        setMeasuredDimension((int) (size + (this.f8210v * 20.0f)), (int) (size2 + (this.f8210v * 20.0f)));
    }
}
