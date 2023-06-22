package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.common.p083b.DisplayUtils;

/* loaded from: classes.dex */
public class SoundSearchAnimView extends View {

    /* renamed from: f */
    private static final int[] f8030f = {0, 120, 240, 90, 210, 330, 10, 130, 250};

    /* renamed from: a */
    private int f8031a;

    /* renamed from: b */
    private Paint f8032b;

    /* renamed from: c */
    private int f8033c;

    /* renamed from: d */
    private int f8034d;

    /* renamed from: e */
    private int f8035e;

    /* renamed from: g */
    private double f8036g;

    /* renamed from: h */
    private int f8037h;

    /* renamed from: i */
    private int f8038i;

    /* renamed from: j */
    private int f8039j;

    /* renamed from: k */
    private int f8040k;

    /* renamed from: l */
    private int f8041l;

    public SoundSearchAnimView(Context context) {
        this(context, null);
    }

    public SoundSearchAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8031a = 5;
        this.f8033c = 0;
        this.f8034d = 0;
        this.f8035e = 0;
        this.f8037h = 100;
        this.f8038i = 100;
        this.f8039j = 0;
        this.f8040k = 0;
        this.f8041l = 0;
        this.f8032b = new Paint();
        this.f8032b.setAntiAlias(true);
    }

    public void setVolume(double d) {
        this.f8036g = d;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        this.f8040k = size >> 1;
        this.f8041l = size2 >> 1;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.f8033c++;
        if (this.f8033c >= 360) {
            this.f8033c -= 360;
        }
        for (int i = 0; i < 3; i++) {
            m1455a(canvas, this.f8040k, this.f8041l, 85, this.f8033c + f8030f[i], 80, 20);
        }
        this.f8034d += 2;
        if (this.f8034d >= 360) {
            this.f8034d -= 360;
        }
        for (int i2 = 0; i2 < 3; i2++) {
            m1455a(canvas, this.f8040k, this.f8041l, 95, this.f8034d + f8030f[i2 + 3], 110, 15);
        }
        this.f8039j++;
        if (this.f8039j >= 4) {
            this.f8038i = ((int) (this.f8036g * 150.0d)) + 100;
            this.f8031a = (this.f8038i - this.f8037h) / 4;
            this.f8039j -= 4;
        } else {
            this.f8037h += this.f8031a;
        }
        if (this.f8037h < 100) {
            this.f8037h = 100;
        }
        if (this.f8037h > 300) {
            this.f8037h = 300;
        }
        this.f8035e += 3;
        if (this.f8035e >= 360) {
            this.f8035e -= 360;
        }
        for (int i3 = 0; i3 < 3; i3++) {
            m1455a(canvas, this.f8040k, this.f8041l, this.f8037h + (i3 * 40), this.f8035e + f8030f[i3 + 6], 60, 30);
        }
    }

    /* renamed from: a */
    private void m1455a(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6) {
        this.f8032b.setColor(Color.argb(i6, 135, 206, 250));
        int m7229a = DisplayUtils.m7229a(i3);
        canvas.drawArc(new RectF(i - m7229a, i2 - m7229a, i + m7229a, m7229a + i2), i4, i5, true, this.f8032b);
    }
}
