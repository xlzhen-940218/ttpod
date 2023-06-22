package com.sds.android.ttpod.component.scaleimage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* renamed from: com.sds.android.ttpod.component.scaleimage.b */
/* loaded from: classes.dex */
public class ImageScaleGestureDetector {

    /* renamed from: a */
    private final Context f4828a;

    /* renamed from: b */
    private final InterfaceC1330a f4829b;

    /* renamed from: c */
    private boolean f4830c;

    /* renamed from: d */
    private MotionEvent f4831d;

    /* renamed from: e */
    private MotionEvent f4832e;

    /* renamed from: f */
    private float f4833f;

    /* renamed from: g */
    private float f4834g;

    /* renamed from: h */
    private float f4835h;

    /* renamed from: i */
    private float f4836i;

    /* renamed from: j */
    private float f4837j;

    /* renamed from: k */
    private float f4838k;

    /* renamed from: l */
    private float f4839l;

    /* renamed from: m */
    private float f4840m;

    /* renamed from: n */
    private float f4841n;

    /* renamed from: o */
    private float f4842o;

    /* renamed from: p */
    private float f4843p;

    /* renamed from: q */
    private long f4844q;

    /* renamed from: r */
    private final float f4845r;

    /* renamed from: s */
    private float f4846s;

    /* renamed from: t */
    private float f4847t;

    /* renamed from: u */
    private boolean f4848u;

    /* compiled from: ImageScaleGestureDetector.java */
    /* renamed from: com.sds.android.ttpod.component.scaleimage.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1330a {
        /* renamed from: a */
        boolean mo5840a(ImageScaleGestureDetector imageScaleGestureDetector);

        /* renamed from: a */
        boolean mo5839a(ImageScaleGestureDetector imageScaleGestureDetector, float f, float f2);

        /* renamed from: b */
        void mo5838b(ImageScaleGestureDetector imageScaleGestureDetector);
    }

    /* compiled from: ImageScaleGestureDetector.java */
    /* renamed from: com.sds.android.ttpod.component.scaleimage.b$b */
    /* loaded from: classes.dex */
    public static class C1331b implements InterfaceC1330a {
        @Override // com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.InterfaceC1330a
        /* renamed from: a */
        public boolean mo5839a(ImageScaleGestureDetector imageScaleGestureDetector, float f, float f2) {
            return false;
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.InterfaceC1330a
        /* renamed from: a */
        public boolean mo5840a(ImageScaleGestureDetector imageScaleGestureDetector) {
            return true;
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.InterfaceC1330a
        /* renamed from: b */
        public void mo5838b(ImageScaleGestureDetector imageScaleGestureDetector) {
        }
    }

    public ImageScaleGestureDetector(Context context, InterfaceC1330a interfaceC1330a) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f4828a = context;
        this.f4829b = interfaceC1330a;
        this.f4845r = viewConfiguration.getScaledEdgeSlop();
    }

    /* renamed from: a */
    public boolean m5846a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        try {
            if (!this.f4830c) {
                switch (action & 255) {
                    case 2:
                        if (this.f4848u) {
                            boolean m5847a = m5847a(motionEvent.getRawX(), motionEvent.getRawY(), this.f4845r, this.f4846s, this.f4847t);
                            boolean m5847a2 = m5847a(m5845a(motionEvent, 1), m5842b(motionEvent, 1), this.f4845r, this.f4846s, this.f4847t);
                            if (!m5847a && !m5847a2) {
                                this.f4848u = false;
                                this.f4830c = this.f4829b.mo5840a(this);
                                break;
                            }
                        }
                        break;
                    case 5:
                        if (motionEvent.getPointerCount() >= 2) {
                            DisplayMetrics displayMetrics = this.f4828a.getResources().getDisplayMetrics();
                            this.f4846s = displayMetrics.widthPixels - this.f4845r;
                            this.f4847t = displayMetrics.heightPixels - this.f4845r;
                            m5841c();
                            this.f4831d = MotionEvent.obtain(motionEvent);
                            this.f4844q = 0L;
                            m5843b(motionEvent);
                            boolean m5847a3 = m5847a(motionEvent.getRawX(), motionEvent.getRawY(), this.f4845r, this.f4846s, this.f4847t);
                            boolean m5847a4 = m5847a(m5845a(motionEvent, 1), m5842b(motionEvent, 1), this.f4845r, this.f4846s, this.f4847t);
                            if (m5847a3 || m5847a4) {
                                this.f4848u = true;
                                break;
                            } else {
                                this.f4830c = this.f4829b.mo5840a(this);
                                break;
                            }
                        }
                        break;
                }
            } else {
                switch (action & 255) {
                    case 2:
                        if (motionEvent.getPointerCount() >= 2) {
                            m5843b(motionEvent);
                            this.f4833f = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                            this.f4834g = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                            if (this.f4842o / this.f4843p > 0.67f && this.f4829b.mo5839a(this, this.f4833f, this.f4834g)) {
                                this.f4831d.recycle();
                                this.f4831d = MotionEvent.obtain(motionEvent);
                                break;
                            }
                        }
                        break;
                    case 3:
                        if (!this.f4848u) {
                            this.f4829b.mo5838b(this);
                        }
                        m5841c();
                        break;
                    case 6:
                        m5843b(motionEvent);
                        if (!this.f4848u) {
                            this.f4829b.mo5838b(this);
                        }
                        m5841c();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /* renamed from: a */
    private boolean m5847a(float f, float f2, float f3, float f4, float f5) {
        return f < f3 || f2 < f3 || f > f4 || f2 > f5;
    }

    /* renamed from: a */
    private static float m5845a(MotionEvent motionEvent, int i) {
        return (motionEvent.getRawX() - motionEvent.getX()) + motionEvent.getX(i);
    }

    /* renamed from: b */
    private static float m5842b(MotionEvent motionEvent, int i) {
        return (motionEvent.getRawY() - motionEvent.getY()) + motionEvent.getY(i);
    }

    /* renamed from: b */
    private void m5843b(MotionEvent motionEvent) {
        if (this.f4832e != null) {
            this.f4832e.recycle();
        }
        this.f4832e = MotionEvent.obtain(motionEvent);
        this.f4839l = -1.0f;
        this.f4840m = -1.0f;
        this.f4841n = -1.0f;
        MotionEvent motionEvent2 = this.f4831d;
        float x = motionEvent2.getX(0);
        float y = motionEvent2.getY(0);
        float x2 = motionEvent2.getX(1);
        float y2 = motionEvent2.getY(1);
        float x3 = motionEvent.getX(0);
        float y3 = motionEvent.getY(0);
        float x4 = motionEvent.getX(1);
        this.f4835h = x2 - x;
        this.f4836i = y2 - y;
        this.f4837j = x4 - x3;
        this.f4838k = motionEvent.getY(1) - y3;
        this.f4844q = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.f4842o = motionEvent.getPressure(0) + motionEvent.getPressure(1);
        this.f4843p = motionEvent2.getPressure(1) + motionEvent2.getPressure(0);
    }

    /* renamed from: c */
    private void m5841c() {
        if (this.f4831d != null) {
            this.f4831d.recycle();
            this.f4831d = null;
        }
        if (this.f4832e != null) {
            this.f4832e.recycle();
            this.f4832e = null;
        }
        this.f4848u = false;
        this.f4830c = false;
    }

    /* renamed from: a */
    public boolean m5848a() {
        return this.f4830c;
    }

    /* renamed from: b */
    public float m5844b() {
        if (this.f4839l == -1.0f) {
            float f = this.f4837j;
            float f2 = this.f4838k;
            this.f4839l = (float) Math.sqrt((f * f) + (f2 * f2));
        }
        if (this.f4840m == -1.0f) {
            float f3 = this.f4835h;
            float f4 = this.f4836i;
            this.f4840m = (float) Math.sqrt((f3 * f3) + (f4 * f4));
        }
        if (this.f4841n == -1.0f) {
            this.f4841n = this.f4839l / this.f4840m;
        }
        return this.f4841n;
    }
}
