package com.sds.android.ttpod.activities.musiccircle.radar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.radar.a */
/* loaded from: classes.dex */
public class DrawView extends View {

    /* renamed from: a */
    private final int f2849a;

    /* renamed from: b */
    private Paint f2850b;

    /* renamed from: c */
    private InterfaceC0830a f2851c;

    /* renamed from: d */
    private float f2852d;

    /* renamed from: e */
    private float f2853e;

    /* renamed from: f */
    private float f2854f;

    /* renamed from: g */
    private float f2855g;

    /* renamed from: h */
    private float f2856h;

    /* renamed from: i */
    private float f2857i;

    /* renamed from: j */
    private Timer f2858j;

    /* renamed from: k */
    private Handler f2859k;

    /* compiled from: DrawView.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.radar.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0830a {
        /* renamed from: a */
        void mo7850a(int i, int i2, int i3);
    }

    /* renamed from: a */
    static /* synthetic */ float m7913a(DrawView drawView, double d) {
        float f = (float) (drawView.f2856h - d);
        drawView.f2856h = f;
        return f;
    }

    /* renamed from: b */
    static /* synthetic */ float m7911b(DrawView drawView, double d) {
        float f = (float) (drawView.f2856h + d);
        drawView.f2856h = f;
        return f;
    }

    /* renamed from: c */
    static /* synthetic */ float m7909c(DrawView drawView, double d) {
        float f = (float) (drawView.f2857i - d);
        drawView.f2857i = f;
        return f;
    }

    /* renamed from: d */
    static /* synthetic */ float m7907d(DrawView drawView, double d) {
        float f = (float) (drawView.f2857i + d);
        drawView.f2857i = f;
        return f;
    }

    public DrawView(Context context, int i, InterfaceC0830a interfaceC0830a) {
        super(context);
        this.f2850b = new Paint();
        this.f2850b.setAntiAlias(true);
        this.f2850b.setColor(-1);
        this.f2850b.setStrokeWidth(3.0f);
        this.f2858j = new Timer();
        this.f2859k = new Handler() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        if (DrawView.this.f2852d - DrawView.this.f2853e > 1.0E-5d) {
                            if (Math.abs(DrawView.this.f2856h - DrawView.this.f2853e) > 2.5d) {
                                DrawView.m7913a(DrawView.this, 1.0d);
                            }
                        } else if (Math.abs(DrawView.this.f2856h - DrawView.this.f2853e) > 2.5d) {
                            DrawView.m7911b(DrawView.this, 1.0d);
                        }
                        if (DrawView.this.f2854f - DrawView.this.f2855g > 1.0E-5d) {
                            if (Math.abs(DrawView.this.f2857i - DrawView.this.f2855g) > 2.5d) {
                                DrawView.m7909c(DrawView.this, 1.0d);
                            }
                        } else if (Math.abs(DrawView.this.f2857i - DrawView.this.f2855g) > 2.5d) {
                            DrawView.m7907d(DrawView.this, 1.0d);
                        }
                        DrawView.this.invalidate();
                        return;
                    default:
                        return;
                }
            }
        };
        this.f2851c = interfaceC0830a;
        this.f2849a = i;
    }

    /* renamed from: a */
    public void m7916a() {
        this.f2858j.cancel();
        this.f2859k.removeMessages(1);
    }

    /* renamed from: a */
    public void m7915a(float f, float f2, float f3, float f4) {
        this.f2852d = f;
        this.f2853e = f2;
        this.f2854f = f3;
        this.f2855g = f4;
        this.f2856h = this.f2852d;
        this.f2857i = this.f2854f;
        this.f2858j.schedule(new TimerTask() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.a.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                DrawView.this.f2859k.sendMessage(DrawView.this.f2859k.obtainMessage(1));
            }
        }, 0L, 5L);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = this.f2850b;
        canvas.drawLine(this.f2852d, this.f2854f, this.f2856h, this.f2857i, paint);
        if (Math.abs(this.f2857i - this.f2855g) < 2.5d && Math.abs(this.f2856h - this.f2853e) < 2.5d) {
            canvas.drawCircle(this.f2856h, this.f2857i, 3.0f, paint);
            this.f2858j.cancel();
            this.f2851c.mo7850a(this.f2849a, (int) this.f2856h, (int) this.f2857i);
        }
    }
}
