package com.sds.android.ttpod.component.landscape.p098b;

import android.graphics.PointF;
import android.opengl.GLES11;
import android.opengl.Matrix;
import com.sds.android.ttpod.component.landscape.ActionManager;
import com.sds.android.ttpod.component.landscape.Scheduler;
import com.sds.android.ttpod.component.landscape.p097a.Action;
import com.sds.android.ttpod.component.landscape.p099c.Texture;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.landscape.b.i */
/* loaded from: classes.dex */
public class Scene extends Node implements Scheduler.InterfaceC1275a {

    /* renamed from: l */
    protected Texture f4497l;

    /* renamed from: m */
    protected C1256a f4498m = new C1256a();

    /* compiled from: Scene.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.b.i$a */
    /* loaded from: classes.dex */
    public static class C1256a {

        /* renamed from: b */
        private float f4500b;

        /* renamed from: c */
        private float f4501c;

        /* renamed from: d */
        private float f4502d;

        /* renamed from: e */
        private float f4503e;

        /* renamed from: a */
        private PointF f4499a = new PointF(0.0f, 0.0f);

        /* renamed from: f */
        private float f4504f = 1.0f;

        /* renamed from: g */
        private float f4505g = 1.0f;

        /* renamed from: h */
        private float[] f4506h = new float[16];

        /* renamed from: i */
        private boolean f4507i = true;

        /* renamed from: j */
        private boolean f4508j = true;

        /* renamed from: k */
        private float f4509k = 1.0f;

        /* renamed from: l */
        private float f4510l = 1.0f;

        /* renamed from: m */
        private float f4511m = 1.0f;

        /* renamed from: n */
        private float f4512n = 1.0f;

        /* renamed from: a */
        public void m6250a(boolean z) {
            this.f4508j = z;
        }

        /* renamed from: a */
        public void m6253a(float f, float f2) {
            this.f4499a.set(f, f2);
            this.f4507i = true;
        }

        /* renamed from: a */
        public PointF m6255a() {
            return new PointF(this.f4499a.x, this.f4499a.y);
        }

        /* renamed from: a */
        public void m6254a(float f) {
            this.f4500b = f;
            this.f4507i = true;
        }

        /* renamed from: b */
        public float m6249b() {
            return this.f4500b;
        }

        /* renamed from: b */
        public void m6248b(float f) {
            this.f4501c = f;
            this.f4507i = true;
        }

        /* renamed from: c */
        public float m6246c() {
            return this.f4501c;
        }

        /* renamed from: c */
        public void m6245c(float f) {
            this.f4502d = f;
            this.f4507i = true;
        }

        /* renamed from: d */
        public float m6243d() {
            return this.f4502d;
        }

        /* renamed from: d */
        public void m6242d(float f) {
            this.f4503e = f;
            this.f4507i = true;
        }

        /* renamed from: e */
        public float m6240e() {
            return this.f4503e;
        }

        /* renamed from: e */
        public void m6239e(float f) {
            this.f4504f = f;
            this.f4507i = true;
        }

        /* renamed from: f */
        public float m6237f() {
            return this.f4504f;
        }

        /* renamed from: f */
        public void m6236f(float f) {
            this.f4505g = f;
            this.f4507i = true;
        }

        /* renamed from: g */
        public float m6234g() {
            return this.f4505g;
        }

        /* renamed from: g */
        public void m6233g(float f) {
            this.f4504f = f;
            this.f4505g = f;
            this.f4507i = true;
        }

        /* renamed from: h */
        public float m6231h() {
            return this.f4509k;
        }

        /* renamed from: i */
        public float m6228i() {
            return this.f4510l;
        }

        /* renamed from: j */
        public float m6226j() {
            return this.f4511m;
        }

        /* renamed from: h */
        public void m6230h(float f) {
            this.f4512n = f;
        }

        /* renamed from: k */
        public float m6224k() {
            return this.f4512n;
        }
    }

    /* renamed from: m */
    public C1256a m6257m() {
        return this.f4498m;
    }

    /* renamed from: d */
    private void m6263d() {
        if (0.0f != this.f4498m.f4501c) {
            Matrix.rotateM(this.f4498m.f4506h, 0, this.f4498m.f4501c, 1.0f, 0.0f, 0.0f);
        }
    }

    /* renamed from: e */
    private void m6262e() {
        if (0.0f != this.f4498m.f4502d) {
            Matrix.rotateM(this.f4498m.f4506h, 0, this.f4498m.f4502d, 0.0f, 1.0f, 0.0f);
        }
    }

    /* renamed from: f */
    private void m6260f() {
        if (0.0f != this.f4498m.f4503e) {
            Matrix.rotateM(this.f4498m.f4506h, 0, this.f4498m.f4503e, 0.0f, 0.0f, 1.0f);
        }
    }

    /* renamed from: n */
    protected void m6256n() {
        m6263d();
        m6262e();
        m6260f();
    }

    /* renamed from: g */
    private float[] m6259g() {
        if (this.f4498m.f4507i) {
            Matrix.setIdentityM(this.f4498m.f4506h, 0);
            if (!this.f4498m.f4499a.equals(0.0f, 0.0f)) {
                Matrix.translateM(this.f4498m.f4506h, 0, this.f4498m.f4499a.x, this.f4498m.f4499a.y, this.f4498m.f4500b);
            }
            m6256n();
            if (this.f4498m.f4504f != 1.0f || this.f4498m.f4505g != 1.0f) {
                Matrix.scaleM(this.f4498m.f4506h, 0, this.f4498m.f4504f, this.f4498m.f4505g, 1.0f);
            }
            this.f4498m.f4507i = false;
        }
        return this.f4498m.f4506h;
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: j */
    public final void mo6258j() {
        if (this.f4498m.f4508j) {
            GLES11.glPushMatrix();
            GLES11.glMultMatrixf(m6259g(), 0);
            if (this.f4482e.size() > 0) {
                Iterator<Node> it = this.f4482e.iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Node next = it.next();
                    if (!z && next.f4483f > 0) {
                        mo6185b();
                        z = true;
                    }
                    next.mo6258j();
                }
            } else {
                mo6185b();
            }
            GLES11.glPopMatrix();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo6185b() {
    }

    /* renamed from: b */
    public void m6264b(Action action) {
        ActionManager.m6371a().m6369a(this, action);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void m6265b(int i) {
        Scheduler.m6112a().m6109a(this, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e_ */
    public void mo6261e_() {
        Scheduler.m6112a().m6110a(this);
    }

    /* renamed from: a */
    public void mo6105a(float f) {
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
    }
}
