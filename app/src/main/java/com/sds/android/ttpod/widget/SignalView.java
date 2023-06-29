package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class SignalView extends View {

    /* renamed from: a */
    private boolean f7909a;

    /* renamed from: b */
    private final int f7910b;

    /* renamed from: c */
    private int f7911c;

    /* renamed from: d */
    private int f7912d;

    /* renamed from: e */
    private final int f7913e;

    /* renamed from: f */
    private int f7914f;

    /* renamed from: g */
    private int[] f7915g;

    /* renamed from: h */
    private int f7916h;

    /* renamed from: i */
    private int f7917i;

    /* renamed from: j */
    private int f7918j;

    /* renamed from: k */
    private TimerTask f7919k;

    /* renamed from: l */
    private Timer f7920l;

    public void setLoop(boolean z) {
        this.f7909a = z;
    }

    public SignalView(Context context) {
        super(context);
        this.f7910b = 2;
        this.f7911c = Color.parseColor("#7ccded");
        this.f7912d = Color.parseColor("#F5FDFD");
        this.f7913e = 4;
        m1552a(context);
    }

    public SignalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7910b = 2;
        this.f7911c = Color.parseColor("#7ccded");
        this.f7912d = Color.parseColor("#F5FDFD");
        this.f7913e = 4;
        m1552a(context);
    }

    public SignalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7910b = 2;
        this.f7911c = Color.parseColor("#7ccded");
        this.f7912d = Color.parseColor("#F5FDFD");
        this.f7913e = 4;
        m1552a(context);
    }

    /* renamed from: a */
    private void m1553a() {
        int i = this.f7916h / 3;
        for (int i2 = 0; i2 < 4; i2++) {
            this.f7915g[i2] = -(i * i2);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        if (this.f7920l != null) {
            this.f7920l.cancel();
        }
        super.onDetachedFromWindow();
    }

    /* renamed from: a */
    private void m1552a(Context context) {
        this.f7914f = DisplayUtils.dp2px(60);
        this.f7916h = 20;
        this.f7915g = new int[4];
        m1553a();
        this.f7909a = true;
        m1549b();
    }

    /* renamed from: b */
    private void m1549b() {
        this.f7919k = new TimerTask() { // from class: com.sds.android.ttpod.widget.SignalView.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (SignalView.this.f7909a) {
                    SignalView.this.postInvalidate();
                }
            }
        };
        this.f7920l = new Timer();
        this.f7920l.schedule(this.f7919k, 50L, 50L);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        this.f7918j = height >> 1;
        this.f7917i = width >> 1;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);
        paint.setColor(this.f7911c);
        int min = ((Math.min(width, height) >> 1) - this.f7914f) / this.f7916h;
        for (int i = 0; i < 4; i++) {
            m1551a(canvas, paint, i, min);
        }
        if (this.f7915g[3] > this.f7916h) {
            m1553a();
            this.f7920l.cancel();
            m1549b();
        }
    }

    /* renamed from: a */
    private void m1551a(Canvas canvas, Paint paint, int i, int i2) {
        int i3;
        int i4 = this.f7915g[i];
        if (i4 <= this.f7916h) {
            if (i4 > 0) {
                int i5 = (i2 * i4) + this.f7914f;
                int i6 = this.f7916h >> 1;
                if (i4 <= i6) {
                    i3 = (int) ((i4 * 255.0f) / i6);
                } else {
                    i3 = (int) (((this.f7916h - i4) * 255.0f) / i6);
                }
                paint.setAlpha(i3);
                canvas.drawCircle(this.f7917i, this.f7918j, i5, paint);
            }
            int[] iArr = this.f7915g;
            iArr[i] = iArr[i] + 1;
        }
    }
}
