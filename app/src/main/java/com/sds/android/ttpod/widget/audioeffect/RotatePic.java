package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.p106a.C1780b;

/* loaded from: classes.dex */
public class RotatePic extends View {

    /* renamed from: e */
    static final /* synthetic */ boolean f8216e;

    /* renamed from: i */
    private static final double[] f8217i;

    /* renamed from: j */
    private static final float[] f8218j;

    /* renamed from: a */
    protected int f8219a;

    /* renamed from: b */
    protected int f8220b;

    /* renamed from: c */
    protected int f8221c;

    /* renamed from: d */
    protected int f8222d;

    /* renamed from: f */
    private Bitmap f8223f;

    /* renamed from: g */
    private Bitmap f8224g;

    /* renamed from: h */
    private Paint f8225h;

    /* renamed from: k */
    private Point[] f8226k;

    /* renamed from: l */
    private int f8227l;

    /* renamed from: m */
    private Point f8228m;

    /* renamed from: n */
    private float f8229n;

    /* renamed from: o */
    private int f8230o;

    static {
        f8216e = !RotatePic.class.desiredAssertionStatus();
        f8217i = new double[]{225.0d, 198.0d, 171.0d, 144.0d, 117.0d, 90.0d, 63.0d, 36.0d, 9.0d, 342.0d, 315.0d, 288.0d, 261.0d, 234.0d, 207.0d};
        f8218j = new float[]{225.0f, 252.0f, 279.0f, 306.0f, 333.0f, 360.0f, 27.0f, 54.0f, 81.0f, 108.0f, 135.0f};
    }

    /* renamed from: a */
    protected void mo1372a() {
    }

    public void setBackground(Bitmap bitmap) {
        this.f8223f = bitmap;
        if (this.f8223f != null) {
            m1374a(Math.min(this.f8223f.getWidth() / 2, this.f8223f.getHeight() / 2) - (this.f8230o * this.f8229n));
        }
        invalidate();
    }

    public void setCalibration(int i) {
        if (!f8216e && f8218j.length != 11) {
            throw new AssertionError();
        }
        if (i >= 0 && i < 11) {
            this.f8227l = i;
            postInvalidate();
        }
    }

    public RotatePic(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f8223f = null;
        this.f8224g = null;
        this.f8225h = null;
        this.f8226k = new Point[14];
        this.f8227l = 5;
        this.f8228m = new Point();
        this.f8229n = getResources().getDisplayMetrics().density;
        this.f8219a = 20;
        this.f8220b = DisplayUtils.m7229a(14);
        this.f8221c = DisplayUtils.m7229a(6);
        this.f8222d = R.drawable.effect_circle_green;
        mo1372a();
        C1780b c1780b = new C1780b();
        c1780b.m4776a(false);
        Resources resources = getResources();
        setBackground(c1780b.m4790a(resources, this.f8222d));
        this.f8224g = c1780b.m4790a(resources, this.f8222d);
        this.f8225h = new Paint();
        this.f8225h.setAntiAlias(true);
        this.f8225h.setStyle(Paint.Style.STROKE);
        this.f8225h.setStrokeWidth(this.f8220b);
        this.f8225h.setColor(Color.parseColor("#232526"));
    }

    /* renamed from: a */
    private void m1374a(float f) {
        for (int i = 0; i < 14; i++) {
            double cos = Math.cos(Math.toRadians(f8217i[i])) * f;
            double sin = Math.sin(Math.toRadians(f8217i[i])) * f;
            this.f8226k[i] = null;
            this.f8226k[i] = new Point(((int) cos) + this.f8228m.x, this.f8228m.y - ((int) sin));
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f8228m.x = ((i3 - i) + 1) / 2;
        this.f8228m.y = ((i4 - i2) + 1) / 2;
        if (this.f8223f != null) {
            m1374a(Math.min(this.f8223f.getWidth() / 2, this.f8223f.getHeight() / 2) - (this.f8230o * this.f8229n));
        }
    }

    public void setRadiusOffset(int i) {
        this.f8230o = i;
    }

    /* renamed from: a */
    private void m1373a(Canvas canvas) {
        if (this.f8224g != null) {
            canvas.save();
            int width = this.f8228m.x - (this.f8224g.getWidth() / 2);
            int height = this.f8228m.y - (this.f8224g.getHeight() / 2);
            canvas.drawBitmap(this.f8224g, width, height, (Paint) null);
            canvas.drawArc(new RectF(this.f8221c + width, this.f8221c + height, (width + this.f8224g.getWidth()) - this.f8221c, (height + this.f8224g.getHeight()) - this.f8221c), 90.0f, (this.f8227l * 36) - 360, false, this.f8225h);
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m1373a(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.f8223f != null) {
            size = this.f8223f.getWidth();
            size2 = this.f8223f.getHeight();
        }
        setMeasuredDimension((int) (size + (this.f8219a * this.f8229n)), (int) (size2 + (this.f8219a * this.f8229n)));
    }
}
