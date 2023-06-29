package com.sds.android.ttpod.component.p090f;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import com.sds.android.ttpod.framework.p106a.BitmapUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.sds.android.ttpod.component.f.b */
/* loaded from: classes.dex */
public class SnowSceneRenderer implements GLSurfaceView.Renderer {

    /* renamed from: b */
    private Bitmap f4132b;

    /* renamed from: g */
    private FloatBuffer f4137g;

    /* renamed from: h */
    private FloatBuffer f4138h;

    /* renamed from: i */
    private float f4139i;

    /* renamed from: p */
    private Context f4146p;

    /* renamed from: a */
    private final Random f4131a = new Random(System.currentTimeMillis());

    /* renamed from: c */
    private float f4133c = 0.0f;

    /* renamed from: d */
    private float f4134d = 2.0E-4f;

    /* renamed from: e */
    private int f4135e = 100;

    /* renamed from: f */
    private C1215a[] f4136f = new C1215a[0];

    /* renamed from: j */
    private float f4140j = -0.005f;

    /* renamed from: k */
    private float f4141k = 0.005f;

    /* renamed from: l */
    private float f4142l = -0.005f;

    /* renamed from: m */
    private float f4143m = 0.0f;

    /* renamed from: n */
    private float f4144n = 10.0f;

    /* renamed from: o */
    private float f4145o = 40.0f;

    public SnowSceneRenderer(Context context) {
        this.f4146p = context;
    }

    /* renamed from: a */
    public void m6625a(Bitmap bitmap) {
        if (bitmap != this.f4132b && bitmap != null) {
            this.f4132b = bitmap;
        }
    }

    /* renamed from: j */
    private void m6611j() {
        try {
            InputStream open = this.f4146p.getAssets().open("scene/snow_flake.png");
            this.f4132b = new BitmapUtils().m4780a(open);
            try {
                open.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: k */
    private void m6610k() {
        if (this.f4132b == null) {
            m6611j();
        }
        float[] fArr = new float[12];
        float[] fArr2 = new float[8];
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f4137g = allocateDirect.asFloatBuffer();
        this.f4137g.put(fArr);
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(fArr2.length * 4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.f4138h = allocateDirect2.asFloatBuffer();
        this.f4138h.put(fArr2);
    }

    /* renamed from: l */
    private void m6609l() {
        this.f4137g.clear();
        this.f4138h.clear();
    }

    /* renamed from: a */
    private void m6627a(float f, float f2, float f3, float f4, float f5) {
        this.f4138h.put(f);
        this.f4138h.put(f2);
        this.f4137g.put(f3);
        this.f4137g.put(f4);
        this.f4137g.put(f5);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        C1215a[] c1215aArr;
        gl10.glClear(16640);
        gl10.glLoadIdentity();
        gl10.glEnableClientState(32884);
        gl10.glEnableClientState(32888);
        m6609l();
        gl10.glVertexPointer(3, 5126, 0, this.f4137g);
        gl10.glTexCoordPointer(2, 5126, 0, this.f4138h);
        for (C1215a c1215a : this.f4136f) {
            float f = c1215a.f4147a;
            float f2 = c1215a.f4148b;
            float f3 = -c1215a.f4149c;
            m6609l();
            m6627a(1.0f, 1.0f, f + 0.5f, f2 + 0.5f, f3);
            m6627a(1.0f, 0.0f, f + 0.5f, f2, f3);
            m6627a(0.0f, 1.0f, f, f2 + 0.5f, f3);
            m6627a(0.0f, 0.0f, f, f2, f3);
            gl10.glDrawArrays(5, 0, 4);
            c1215a.m6605b();
            C1215a.m6606a(c1215a, -this.f4134d);
            C1215a.m6603b(c1215a, this.f4133c);
            if (Math.abs(c1215a.f4148b) > 1.5f * c1215a.f4149c || Math.abs(c1215a.f4147a) > (this.f4139i + 0.5f) * c1215a.f4149c) {
                m6624a(c1215a);
            }
        }
        gl10.glDisableClientState(32884);
        gl10.glDisableClientState(32888);
        gl10.glFinish();
    }

    /* renamed from: d */
    private float m6617d(float f, float f2) {
        return (this.f4131a.nextFloat() * (f2 - f)) + f;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        gl10.glViewport(0, 0, i, i2);
        gl10.glMatrixMode(5889);
        gl10.glLoadIdentity();
        this.f4139i = i / (i2 == 0 ? 1.0f : i2);
        gl10.glFrustumf(-this.f4139i, this.f4139i, -1.0f, 1.0f, 1.0f, 100.0f);
        gl10.glMatrixMode(5888);
        gl10.glLoadIdentity();
    }

    /* renamed from: a */
    public void m6626a(int i) {
        if (i != this.f4136f.length) {
            this.f4135e = i;
            C1215a[] c1215aArr = new C1215a[this.f4135e];
            for (int i2 = 0; i2 < c1215aArr.length; i2++) {
                c1215aArr[i2] = new C1215a();
                m6624a(c1215aArr[i2]);
            }
            this.f4136f = c1215aArr;
        }
    }

    /* renamed from: a */
    private void m6624a(C1215a c1215a) {
        float f = 1.0f;
        c1215a.m6608a();
        c1215a.f4149c = m6617d(this.f4144n, this.f4145o);
        float nextFloat = (this.f4139i + 1.0f) * ((this.f4131a.nextFloat() * 2.0f) - 1.0f);
        c1215a.f4147a = Math.abs(c1215a.f4149c) * nextFloat;
        if (this.f4133c != 0.0f && (nextFloat >= this.f4139i || nextFloat < (-this.f4139i))) {
            f = (this.f4131a.nextFloat() * 2.0f) - 1.0f;
        }
        c1215a.f4148b = f * c1215a.f4149c;
        c1215a.f4150d = m6617d(this.f4140j, this.f4141k);
        c1215a.f4151e = m6617d(this.f4142l, this.f4143m);
    }

    /* renamed from: a */
    public int m6630a() {
        return this.f4135e;
    }

    /* renamed from: a */
    public void m6628a(float f, float f2) {
        this.f4144n = f;
        this.f4145o = f2;
    }

    /* renamed from: b */
    public void m6621b(float f, float f2) {
        this.f4142l = f;
        this.f4143m = f2;
    }

    /* renamed from: c */
    public void m6619c(float f, float f2) {
        this.f4140j = f;
        this.f4141k = f2;
    }

    /* renamed from: b */
    public float m6623b() {
        return this.f4140j;
    }

    /* renamed from: c */
    public float m6620c() {
        return this.f4141k;
    }

    /* renamed from: d */
    public float m6618d() {
        return this.f4142l;
    }

    /* renamed from: e */
    public float m6616e() {
        return this.f4143m;
    }

    /* renamed from: f */
    public float m6615f() {
        return this.f4144n;
    }

    /* renamed from: g */
    public float m6614g() {
        return this.f4145o;
    }

    /* renamed from: a */
    public void m6629a(float f) {
        this.f4133c = f;
    }

    /* renamed from: h */
    public float m6613h() {
        return this.f4133c;
    }

    /* renamed from: b */
    public void m6622b(float f) {
        this.f4134d = f;
    }

    /* renamed from: i */
    public float m6612i() {
        return this.f4134d;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        m6610k();
        gl10.glHint(3152, 4354);
        gl10.glClearColorx(0, 0, 0, 0);
        gl10.glShadeModel(7425);
        gl10.glDisable(2929);
        gl10.glEnable(3042);
        gl10.glBlendFunc(770, 1);
        gl10.glEnable(3553);
        int[] iArr = new int[1];
        gl10.glGenTextures(1, iArr, 0);
        gl10.glBindTexture(3553, iArr[0]);
        GLUtils.texImage2D(3553, 0, this.f4132b, 0);
        gl10.glTexParameterf(3553, 10240, 9729.0f);
        gl10.glTexParameterf(3553, 10241, 9729.0f);
        m6626a(this.f4135e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SnowSceneRenderer.java */
    /* renamed from: com.sds.android.ttpod.component.f.b$a */
    /* loaded from: classes.dex */
    public static class C1215a {

        /* renamed from: a */
        private float f4147a;

        /* renamed from: b */
        private float f4148b;

        /* renamed from: c */
        private float f4149c;

        /* renamed from: d */
        private float f4150d;

        /* renamed from: e */
        private float f4151e;

        /* renamed from: f */
        private float f4152f;

        private C1215a() {
        }

        /* renamed from: a */
        static /* synthetic */ float m6606a(C1215a c1215a, float f) {
            float f2 = c1215a.f4151e + f;
            c1215a.f4151e = f2;
            return f2;
        }

        /* renamed from: b */
        static /* synthetic */ float m6603b(C1215a c1215a, float f) {
            float f2 = c1215a.f4150d + f;
            c1215a.f4150d = f2;
            return f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m6608a() {
            this.f4147a = 0.0f;
            this.f4148b = 0.0f;
            this.f4149c = 0.0f;
            this.f4150d = 0.0f;
            this.f4151e = 0.0f;
            this.f4152f = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m6605b() {
            this.f4147a += this.f4150d;
            this.f4148b += this.f4151e;
            this.f4149c += this.f4152f;
        }
    }
}
