package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;


/* loaded from: classes.dex */
public class RadialProgress extends RadialProgressWidget {

    /* renamed from: l */
    private boolean f8127l;

    /* renamed from: m */
    private boolean f8128m;

    /* renamed from: n */
    private int f8129n;

    /* renamed from: o */
    private final float f8130o;

    /* renamed from: p */
    private final int f8131p;

    /* renamed from: q */
    private final int f8132q;

    /* renamed from: r */
    private int f8133r;

    /* renamed from: s */
    private final int f8134s;

    /* renamed from: t */
    private int f8135t;

    /* renamed from: u */
    private int f8136u;

    /* renamed from: v */
    private int f8137v;

    /* renamed from: w */
    private boolean f8138w;

    public RadialProgress(Context context) {
        this(context, null);
    }

    public RadialProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadialProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8127l = false;
        this.f8128m = false;
        this.f8129n = 100;
        this.f8130o = getResources().getDisplayMetrics().density;
        this.f8131p = (int) (this.f8130o * 4.0f);
        this.f8132q = (int) (this.f8130o * 20.0f);
        this.f8133r = 1;
        this.f8134s = (int) (this.f8130o * 280.0f);
        this.f8135t = 0;
        this.f8136u = 0;
        this.f8137v = 100;
        this.f8138w = false;
        m1410a();
    }

    /* renamed from: a */
    private void m1408a(MotionEvent motionEvent) {
        this.f8168f = (int) motionEvent.getX();
        this.f8169g = (int) motionEvent.getY();
        if (this.f8167e != null) {
            this.f8167e.m1400a();
        }
        if (m1406a((int) this.f8168f, (int) this.f8169g)) {
            this.f8127l = true;
        } else {
            this.f8127l = false;
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
        this.f8138w = false;
    }

    /* renamed from: a */
    private void m1410a() {
        if (this.f8130o * 10.0f == 10.0f) {
            this.f8135t = 50;
            this.f8133r = 1;
            this.f8137v = 78;
        } else if (this.f8130o * 10.0f == 15.0f) {
            this.f8135t = 110;
            this.f8133r = 0;
        } else if (this.f8130o * 10.0f == 20.0f) {
            this.f8135t = 240;
            this.f8133r = 2;
            this.f8137v = 100;
        } else if (this.f8130o * 10.0f == 30.0f) {
            this.f8135t = 300;
            this.f8133r = 3;
            this.f8137v = 400;
        } else {
            this.f8135t = 0;
            this.f8133r = 0;
        }
    }

    @Override // com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (this.f8166d) {
            switch (motionEvent.getAction()) {
                case 0:
                    m1408a(motionEvent);
                    return true;
                case 1:
                    if (this.f8167e != null) {
                        this.f8167e.m1398c();
                    }
                    this.f8172j = 0.0f;
                    this.f8173k = 0.0f;
                    this.f8127l = false;
                    this.f8128m = false;
                    this.f8138w = false;
                    return true;
                case 2:
                    if (this.f8167e != null) {
                        this.f8167e.m1399b();
                    }
                    if (this.f8127l) {
                        return true;
                    }
                    if (m1406a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                        this.f8128m = true;
                        this.f8136u = this.f8134s;
                    } else {
                        this.f8128m = false;
                        this.f8136u = this.f8135t;
                    }
                    int abs = Math.abs(((int) motionEvent.getX()) - ((int) this.f8168f));
                    int abs2 = Math.abs(((int) motionEvent.getY()) - ((int) this.f8169g));
                    if (Math.sqrt((abs * abs) + (abs2 * abs2)) >= 20.0d) {
                        this.f8170h = motionEvent.getX() - this.f8172j;
                        this.f8171i = motionEvent.getY() - this.f8173k;
                        int sqrt = (int) (Math.sqrt((this.f8170h * this.f8170h) + (this.f8171i * this.f8171i)) * 100.0d);
                        if (sqrt != 0 && this.f8138w) {
                            int m1409a = m1409a(new Point((int) this.f8172j, (int) this.f8173k), new Point(this.f8163a / 2, this.f8164b / 2), new Point((int) motionEvent.getX(), (int) motionEvent.getY()));
                            int i2 = m1409a / this.f8129n;
                            if ((i2 >= this.f8133r && Math.abs(m1409a) > this.f8136u) || (m1409a > 0 && i2 == 0 && sqrt > this.f8137v)) {
                                i = 1;
                            } else if ((i2 <= (-this.f8133r) && Math.abs(m1409a) > this.f8136u) || (m1409a < 0 && i2 == 0 && sqrt > this.f8137v)) {
                                i = -1;
                            }
                            if (i != 0) {
                                m1407a(i + this.f8165c);
                            }
                        }
                        this.f8172j = motionEvent.getX();
                        this.f8173k = motionEvent.getY();
                        this.f8138w = true;
                        return true;
                    }
                    return true;
                default:
                    return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private int m1409a(Point point, Point point2, Point point3) {
        Point point4 = new Point(point2.x - point.x, point2.y - point.y);
        Point point5 = new Point(point2.x - point3.x, point2.y - point3.y);
        return (point4.x * point5.y) - (point4.y * point5.x);
    }
}
