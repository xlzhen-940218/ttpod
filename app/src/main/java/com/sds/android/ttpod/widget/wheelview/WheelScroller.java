package com.sds.android.ttpod.widget.wheelview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.sds.android.cloudapi.ttpod.data.VIPPolicy;

/* renamed from: com.sds.android.ttpod.widget.wheelview.f */
/* loaded from: classes.dex */
public class WheelScroller {

    /* renamed from: a */
    private InterfaceC2298a f8435a;

    /* renamed from: b */
    private Context f8436b;

    /* renamed from: c */
    private GestureDetector f8437c;

    /* renamed from: d */
    private Scroller f8438d;

    /* renamed from: e */
    private int f8439e;

    /* renamed from: f */
    private float f8440f;

    /* renamed from: g */
    private boolean f8441g;

    /* renamed from: h */
    private GestureDetector.SimpleOnGestureListener f8442h = new GestureDetector.SimpleOnGestureListener() { // from class: com.sds.android.ttpod.widget.wheelview.f.1
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            WheelScroller.this.f8439e = 0;
            WheelScroller.this.f8438d.fling(0, WheelScroller.this.f8439e, 0, (int) (-f2), 0, 0, -2147483647, VIPPolicy.Entry.MAX_LIMIT);
            WheelScroller.this.m1151a(0);
            return true;
        }
    };

    /* renamed from: i */
    private Handler f8443i = new Handler() { // from class: com.sds.android.ttpod.widget.wheelview.f.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WheelScroller.this.f8438d.computeScrollOffset();
            int currY = WheelScroller.this.f8438d.getCurrY();
            int i = WheelScroller.this.f8439e - currY;
            WheelScroller.this.f8439e = currY;
            if (i != 0) {
                WheelScroller.this.f8435a.mo1135a(i);
            }
            if (Math.abs(currY - WheelScroller.this.f8438d.getFinalY()) < 1) {
                WheelScroller.this.f8438d.getFinalY();
                WheelScroller.this.f8438d.forceFinished(true);
            }
            if (!WheelScroller.this.f8438d.isFinished()) {
                WheelScroller.this.f8443i.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                WheelScroller.this.m1140d();
            } else {
                WheelScroller.this.m1145b();
            }
        }
    };

    /* compiled from: WheelScroller.java */
    /* renamed from: com.sds.android.ttpod.widget.wheelview.f$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2298a {
        /* renamed from: a */
        void mo1136a();

        /* renamed from: a */
        void mo1135a(int i);

        /* renamed from: b */
        void mo1134b();

        /* renamed from: c */
        void mo1133c();
    }

    public WheelScroller(Context context, InterfaceC2298a interfaceC2298a) {
        this.f8435a = interfaceC2298a;
        this.f8436b = context;
        this.f8437c = new GestureDetector(this.f8436b, this.f8442h);
        this.f8437c.setIsLongpressEnabled(false);
        this.f8438d = new Scroller(this.f8436b);
    }

    /* renamed from: a */
    public void m1148a(Interpolator interpolator) {
        this.f8438d.forceFinished(true);
        this.f8438d = new Scroller(this.f8436b, interpolator);
    }

    /* renamed from: a */
    public void m1150a(int i, int i2) {
        this.f8438d.forceFinished(true);
        this.f8439e = 0;
        this.f8438d.startScroll(0, 0, 0, i, i2 != 0 ? i2 : 400);
        m1151a(0);
        m1138e();
    }

    /* renamed from: a */
    public void m1152a() {
        this.f8438d.forceFinished(true);
    }

    /* renamed from: a */
    public boolean m1149a(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f8440f = motionEvent.getY();
                this.f8438d.forceFinished(true);
                m1142c();
                break;
            case 2:
                int y = (int) (motionEvent.getY() - this.f8440f);
                if (y != 0) {
                    m1138e();
                    this.f8435a.mo1135a(y);
                    this.f8440f = motionEvent.getY();
                    break;
                }
                break;
        }
        if (!this.f8437c.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            m1140d();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1151a(int i) {
        m1142c();
        this.f8443i.sendEmptyMessage(i);
    }

    /* renamed from: c */
    private void m1142c() {
        this.f8443i.removeMessages(0);
        this.f8443i.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m1140d() {
        this.f8435a.mo1133c();
        m1151a(1);
    }

    /* renamed from: e */
    private void m1138e() {
        if (!this.f8441g) {
            this.f8441g = true;
            this.f8435a.mo1136a();
        }
    }

    /* renamed from: b */
    void m1145b() {
        if (this.f8441g) {
            this.f8435a.mo1134b();
            this.f8441g = false;
        }
    }
}
