package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.sds.android.ttpod.framework.p106a.ViewUtils;

/* loaded from: classes.dex */
public class GestureView extends View {

    /* renamed from: a */
    private InterfaceC1238d f4310a;

    /* renamed from: b */
    private InterfaceC1235a f4311b;

    /* renamed from: c */
    private InterfaceC1236b f4312c;

    /* renamed from: d */
    private InterfaceC1237c f4313d;

    /* renamed from: e */
    private PointF f4314e;

    /* renamed from: f */
    private PointF f4315f;

    /* renamed from: g */
    private C1239e f4316g;

    /* renamed from: h */
    private C1240f f4317h;

    /* renamed from: i */
    private boolean f4318i;

    /* renamed from: com.sds.android.ttpod.component.landscape.GestureView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1235a {
        /* renamed from: a */
        void mo6183a(double d);
    }

    /* renamed from: com.sds.android.ttpod.component.landscape.GestureView$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1236b {
        /* renamed from: b */
        void mo6178b(double d);
    }

    /* renamed from: com.sds.android.ttpod.component.landscape.GestureView$c */
    /* loaded from: classes.dex */
    public interface InterfaceC1237c {
        /* renamed from: a */
        void mo6181a(int i);
    }

    /* renamed from: com.sds.android.ttpod.component.landscape.GestureView$d */
    /* loaded from: classes.dex */
    public interface InterfaceC1238d {
        /* renamed from: a */
        void mo6182a(float f, float f2, float f3, float f4);
    }

    /* renamed from: com.sds.android.ttpod.component.landscape.GestureView$f */
    /* loaded from: classes.dex */
    public static class C1240f {

        /* renamed from: a */
        private View f4326a;

        /* renamed from: b */
        private View.OnLongClickListener f4327b;

        /* renamed from: c */
        private float f4328c;

        /* renamed from: d */
        private float f4329d;

        /* renamed from: e */
        private float f4330e;

        /* renamed from: f */
        private boolean f4331f;

        public C1240f(View view) {
            this.f4326a = view;
            float f = view.getResources().getDisplayMetrics().density * 15.0f;
            this.f4330e = f * f;
        }

        /* renamed from: a */
        public void m6379a(MotionEvent motionEvent) {
            this.f4328c = motionEvent.getX();
            this.f4329d = motionEvent.getY();
            this.f4331f = false;
        }

        /* renamed from: b */
        public void m6376b(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (((x - this.f4328c) * (x - this.f4328c)) + ((y - this.f4329d) * (y - this.f4329d)) >= this.f4330e) {
                this.f4331f = true;
            }
        }

        /* renamed from: a */
        public boolean m6380a() {
            if (this.f4331f || this.f4327b == null) {
                return false;
            }
            return this.f4327b.onLongClick(this.f4326a);
        }

        /* renamed from: b */
        public void m6377b() {
            this.f4331f = true;
        }

        /* renamed from: a */
        public void m6378a(View.OnLongClickListener onLongClickListener) {
            this.f4327b = onLongClickListener;
            this.f4326a.setLongClickable(true);
        }
    }

    /* renamed from: com.sds.android.ttpod.component.landscape.GestureView$e */
    /* loaded from: classes.dex */
    public static class C1239e {

        /* renamed from: a */
        private View f4319a;

        /* renamed from: b */
        private View.OnClickListener f4320b;

        /* renamed from: c */
        private float f4321c;

        /* renamed from: d */
        private float f4322d;

        /* renamed from: e */
        private float f4323e;

        /* renamed from: f */
        private long f4324f;

        /* renamed from: g */
        private boolean f4325g;

        public C1239e(View view) {
            this.f4319a = view;
            float f = view.getResources().getDisplayMetrics().density * 15.0f;
            this.f4323e = f * f;
        }

        /* renamed from: a */
        public void m6384a(MotionEvent motionEvent) {
            this.f4321c = motionEvent.getX();
            this.f4322d = motionEvent.getY();
            this.f4324f = System.currentTimeMillis();
            this.f4325g = false;
        }

        /* renamed from: b */
        public void m6382b(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (((x - this.f4321c) * (x - this.f4321c)) + ((y - this.f4322d) * (y - this.f4322d)) >= this.f4323e) {
                this.f4325g = true;
            }
        }

        /* renamed from: c */
        public void m6381c(MotionEvent motionEvent) {
            if (!this.f4325g && System.currentTimeMillis() - this.f4324f < ViewConfiguration.getLongPressTimeout()) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (((x - this.f4321c) * (x - this.f4321c)) + ((y - this.f4322d) * (y - this.f4322d)) < this.f4323e && this.f4320b != null) {
                    this.f4320b.onClick(this.f4319a);
                }
            }
        }

        /* renamed from: a */
        public void m6385a() {
            this.f4325g = true;
        }

        /* renamed from: a */
        public void m6383a(View.OnClickListener onClickListener) {
            this.f4320b = onClickListener;
            this.f4319a.setClickable(true);
        }
    }

    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GestureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4314e = new PointF();
        this.f4315f = new PointF();
        this.f4318i = false;
    }

    public void setGestureTranslation(InterfaceC1238d interfaceC1238d) {
        this.f4310a = interfaceC1238d;
    }

    public void setGestureRotate(InterfaceC1235a interfaceC1235a) {
        this.f4311b = interfaceC1235a;
    }

    public void setGestureScale(InterfaceC1236b interfaceC1236b) {
        this.f4312c = interfaceC1236b;
    }

    public void setGestureState(InterfaceC1237c interfaceC1237c) {
        this.f4313d = interfaceC1237c;
    }

    public void setTTPodClickListener(View.OnClickListener onClickListener) {
        if (this.f4316g == null) {
            this.f4316g = new C1239e(this);
        }
        this.f4316g.m6383a(onClickListener);
    }

    public void setTTPodLongClickListener(View.OnLongClickListener onLongClickListener) {
        if (this.f4317h == null) {
            this.f4317h = new C1240f(this);
        }
        this.f4317h.m6378a(onLongClickListener);
    }

    @Override // android.view.View
    public boolean performLongClick() {
        if (this.f4317h != null) {
            this.f4317h.m6380a();
        }
        return super.performLongClick();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.f4316g != null) {
                    this.f4316g.m6384a(motionEvent);
                }
                if (this.f4317h != null) {
                    this.f4317h.m6379a(motionEvent);
                }
                if (this.f4313d != null) {
                    this.f4313d.mo6181a(33);
                    break;
                }
                break;
            case 1:
                if (this.f4316g != null) {
                    this.f4316g.m6381c(motionEvent);
                }
                if (this.f4313d != null) {
                    this.f4313d.mo6181a(32);
                    break;
                }
                break;
            case 2:
                if (this.f4316g != null) {
                    this.f4316g.m6382b(motionEvent);
                }
                if (this.f4317h != null) {
                    this.f4317h.m6376b(motionEvent);
                }
                if (this.f4318i && motionEvent.getPointerCount() >= 2) {
                    m6388a(motionEvent);
                }
                ViewUtils.m4638a((View) this, true);
                break;
            case 6:
            case 262:
                this.f4318i = false;
                if (this.f4313d != null) {
                    this.f4313d.mo6181a(31);
                    break;
                }
                break;
            case 261:
                if (this.f4316g != null) {
                    this.f4316g.m6385a();
                }
                if (this.f4317h != null) {
                    this.f4317h.m6377b();
                }
                if (motionEvent.getPointerCount() >= 2) {
                    this.f4318i = true;
                    if (this.f4313d != null) {
                        this.f4313d.mo6181a(30);
                    }
                    m6387a(motionEvent, this.f4314e, this.f4315f);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private void m6387a(MotionEvent motionEvent, PointF pointF, PointF pointF2) {
        try {
            pointF.x = motionEvent.getX(motionEvent.getPointerId(0));
            pointF.y = motionEvent.getY(motionEvent.getPointerId(0));
            pointF2.x = motionEvent.getX(motionEvent.getPointerId(1));
            pointF2.y = motionEvent.getY(motionEvent.getPointerId(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private PointF m6389a(PointF pointF, PointF pointF2) {
        return new PointF(pointF2.x - pointF.x, pointF2.y - pointF.y);
    }

    /* renamed from: a */
    private double m6390a(PointF pointF) {
        return Math.sqrt((pointF.x * pointF.x) + (pointF.y * pointF.y));
    }

    /* renamed from: b */
    private double m6386b(PointF pointF) {
        return (Math.atan2(pointF.y, pointF.x) * 180.0d) / 3.141592653589793d;
    }

    /* renamed from: a */
    private void m6388a(MotionEvent motionEvent) {
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        m6387a(motionEvent, pointF, pointF2);
        PointF m6389a = m6389a(this.f4314e, pointF);
        PointF m6389a2 = m6389a(this.f4315f, pointF2);
        PointF m6389a3 = m6389a(this.f4314e, this.f4315f);
        PointF m6389a4 = m6389a(pointF, pointF2);
        double m6390a = m6390a(m6389a3);
        double m6390a2 = m6390a(m6389a4);
        double m6386b = m6386b(m6389a3);
        double m6386b2 = m6386b(m6389a4);
        if (this.f4310a != null) {
            this.f4310a.mo6182a(m6389a.x, m6389a.y, m6389a2.x, m6389a2.y);
        }
        if (this.f4312c != null) {
            this.f4312c.mo6178b(m6390a2 - m6390a);
        }
        if (this.f4311b != null) {
            double d = m6386b2 - m6386b;
            if (d > 180.0d) {
                d -= 360.0d;
            } else if (d < -180.0d) {
                d += 360.0d;
            }
            this.f4311b.mo6183a(d);
        }
    }
}
