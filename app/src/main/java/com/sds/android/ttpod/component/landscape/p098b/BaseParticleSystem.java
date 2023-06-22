package com.sds.android.ttpod.component.landscape.p098b;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.p099c.Texture2D;
import com.sds.android.ttpod.component.landscape.p100d.Color4F;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* renamed from: com.sds.android.ttpod.component.landscape.b.b */
/* loaded from: classes.dex */
public class BaseParticleSystem extends Scene {

    /* renamed from: a */
    protected C1254a[] f4392a;

    /* renamed from: b */
    protected int f4393b;

    /* renamed from: c */
    protected float f4394c;

    /* renamed from: d */
    protected C1255b f4395d;

    /* renamed from: g */
    private float f4396g;

    /* renamed from: h */
    private float f4397h;

    /* renamed from: i */
    private FloatBuffer f4398i;

    /* renamed from: j */
    private FloatBuffer f4399j;

    /* renamed from: k */
    private ShortBuffer f4400k;

    /* renamed from: n */
    private FloatBuffer f4401n;

    /* compiled from: BaseParticleSystem.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.b.b$b */
    /* loaded from: classes.dex */
    public static class C1255b {

        /* renamed from: A */
        protected Color4F f4417A;

        /* renamed from: B */
        protected Color4F f4418B;

        /* renamed from: C */
        protected Color4F f4419C;

        /* renamed from: D */
        protected Color4F f4420D;

        /* renamed from: E */
        protected int f4421E;

        /* renamed from: F */
        protected float f4422F;

        /* renamed from: G */
        protected String f4423G;

        /* renamed from: a */
        protected int f4424a;

        /* renamed from: b */
        protected float f4425b;

        /* renamed from: c */
        protected float f4426c;

        /* renamed from: d */
        protected float f4427d;

        /* renamed from: e */
        protected float f4428e;

        /* renamed from: f */
        protected float f4429f;

        /* renamed from: g */
        protected PointF f4430g;

        /* renamed from: h */
        protected PointF f4431h;

        /* renamed from: i */
        protected float f4432i;

        /* renamed from: j */
        protected float f4433j;

        /* renamed from: k */
        protected float f4434k;

        /* renamed from: l */
        protected float f4435l;

        /* renamed from: m */
        protected float f4436m;

        /* renamed from: n */
        protected float f4437n;

        /* renamed from: o */
        protected float f4438o;

        /* renamed from: p */
        protected float f4439p;

        /* renamed from: q */
        protected float f4440q;

        /* renamed from: r */
        protected float f4441r;

        /* renamed from: s */
        protected float f4442s;

        /* renamed from: t */
        protected float f4443t;

        /* renamed from: u */
        protected float f4444u;

        /* renamed from: v */
        protected float f4445v;

        /* renamed from: w */
        protected float f4446w;

        /* renamed from: x */
        protected float f4447x;

        /* renamed from: y */
        protected float f4448y;

        /* renamed from: z */
        protected float f4449z;

        /* JADX INFO: Access modifiers changed from: protected */
        public C1255b() {
            this.f4430g = new PointF();
            this.f4431h = new PointF();
            this.f4417A = new Color4F();
            this.f4418B = new Color4F();
            this.f4419C = new Color4F();
            this.f4420D = new Color4F();
        }

        public C1255b(C1255b c1255b) {
            this.f4430g = new PointF();
            this.f4431h = new PointF();
            this.f4417A = new Color4F();
            this.f4418B = new Color4F();
            this.f4419C = new Color4F();
            this.f4420D = new Color4F();
            this.f4425b = c1255b.f4425b;
            this.f4426c = c1255b.f4426c;
            this.f4427d = c1255b.f4427d;
            this.f4428e = c1255b.f4428e;
            this.f4429f = c1255b.f4429f;
            this.f4430g.x = c1255b.f4430g.x;
            this.f4430g.y = c1255b.f4430g.y;
            this.f4431h.x = c1255b.f4431h.x;
            this.f4431h.y = c1255b.f4431h.y;
            this.f4432i = c1255b.f4432i;
            this.f4433j = c1255b.f4433j;
            this.f4434k = c1255b.f4434k;
            this.f4435l = c1255b.f4435l;
            this.f4436m = c1255b.f4436m;
            this.f4437n = c1255b.f4437n;
            this.f4438o = c1255b.f4438o;
            this.f4439p = c1255b.f4439p;
            this.f4440q = c1255b.f4440q;
            this.f4441r = c1255b.f4441r;
            this.f4442s = c1255b.f4442s;
            this.f4443t = c1255b.f4443t;
            this.f4444u = c1255b.f4444u;
            this.f4445v = c1255b.f4445v;
            this.f4446w = c1255b.f4446w;
            this.f4447x = c1255b.f4447x;
            this.f4448y = c1255b.f4448y;
            this.f4449z = c1255b.f4449z;
            this.f4417A = new Color4F(c1255b.f4417A);
            this.f4418B = new Color4F(c1255b.f4418B);
            this.f4419C = new Color4F(c1255b.f4419C);
            this.f4420D = new Color4F(c1255b.f4420D);
            this.f4421E = c1255b.f4421E;
            this.f4422F = c1255b.f4422F;
            this.f4423G = c1255b.f4423G;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: BaseParticleSystem.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.b.b$a */
    /* loaded from: classes.dex */
    public static class C1254a {

        /* renamed from: b */
        private float f4403b;

        /* renamed from: e */
        private float f4406e;

        /* renamed from: f */
        private float f4407f;

        /* renamed from: g */
        private float f4408g;

        /* renamed from: h */
        private float f4409h;

        /* renamed from: i */
        private float f4410i;

        /* renamed from: j */
        private float f4411j;

        /* renamed from: k */
        private float f4412k;

        /* renamed from: l */
        private float f4413l;

        /* renamed from: o */
        private float f4416o;

        /* renamed from: c */
        private PointF f4404c = new PointF();

        /* renamed from: d */
        private PointF f4405d = new PointF();

        /* renamed from: m */
        private Color4F f4414m = new Color4F();

        /* renamed from: n */
        private Color4F f4415n = new Color4F();

        protected C1254a() {
        }

        /* renamed from: a */
        static  /* synthetic */ float m6310a(C1254a c1254a, float f) {
            float f2 = c1254a.f4416o - f;
            c1254a.f4416o = f2;
            return f2;
        }

        /* renamed from: b */
        static /* synthetic */ float m6308b(C1254a c1254a, float f) {
            float f2 = c1254a.f4406e + f;
            c1254a.f4406e = f2;
            return f2;
        }

        /* renamed from: c */
        static  /* synthetic */ float m6306c(C1254a c1254a, float f) {
            float f2 = c1254a.f4408g + f;
            c1254a.f4408g = f2;
            return f2;
        }

        /* renamed from: d */
        static   /* synthetic */ float m6304d(C1254a c1254a, float f) {
            float f2 = c1254a.f4410i + f;
            c1254a.f4410i = f2;
            return f2;
        }

        /* renamed from: e */
        static   /* synthetic */ float m6302e(C1254a c1254a, float f) {
            float f2 = c1254a.f4412k + f;
            c1254a.f4412k = f2;
            return f2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseParticleSystem() {
        m6265b(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene
    /* renamed from: e_ */
    public void mo6261e_() {
        super.mo6261e_();
        if (this.f4398i != null) {
            this.f4398i.clear();
        }
        if (this.f4399j != null) {
            this.f4399j.clear();
        }
        if (this.f4400k != null) {
            this.f4400k.clear();
        }
        if (this.f4401n != null) {
            this.f4401n.clear();
        }
    }

    public BaseParticleSystem(C1255b c1255b) {
        this();
        this.f4497l = new Texture2D("BaseParticleSystem");
        if (c1255b != null) {
            this.f4395d = c1255b;
        } else {
            this.f4395d = m6316d();
        }
        m6315e();
    }

    /* renamed from: d */
    protected C1255b m6316d() {
        C1255b c1255b = new C1255b();
        c1255b.f4448y = 1.0f;
        c1255b.f4425b = 0.0f;
        c1255b.f4426c = 2.0f;
        c1255b.f4428e = 1.0f;
        c1255b.f4444u = 2.0f;
        c1255b.f4446w = 2.0f;
        c1255b.f4417A.m6154a(1.0f, 1.0f, 1.0f, 1.0f);
        c1255b.f4419C.m6154a(0.0f, 0.0f, 0.0f, 0.0f);
        c1255b.f4421E = 16;
        c1255b.f4422F = 10.0f;
        return c1255b;
    }

    /* renamed from: e */
    protected void m6315e() {
        if (this.f4395d.f4421E <= 0) {
            this.f4395d.f4421E = 16;
        }
        if (this.f4395d.f4422F <= 0.0f) {
            this.f4395d.f4422F = 10.0f;
        }
        int i = this.f4395d.f4421E;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i * 4 * 2 * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        if (this.f4398i != null) {
            this.f4398i.clear();
        }
        this.f4398i = allocateDirect.asFloatBuffer();
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(i * 4 * 4 * 4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        if (this.f4401n != null) {
            this.f4401n.clear();
        }
        this.f4401n = allocateDirect2.asFloatBuffer();
        ByteBuffer allocateDirect3 = ByteBuffer.allocateDirect(i * 4 * 2 * 4);
        allocateDirect3.order(ByteOrder.nativeOrder());
        if (this.f4399j != null) {
            this.f4399j.clear();
        }
        this.f4399j = allocateDirect3.asFloatBuffer();
        ByteBuffer allocateDirect4 = ByteBuffer.allocateDirect(i * 2 * 3 * 2);
        allocateDirect4.order(ByteOrder.nativeOrder());
        if (this.f4400k != null) {
            this.f4400k.clear();
        }
        this.f4400k = allocateDirect4.asShortBuffer();
        this.f4392a = new C1254a[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.f4392a[i2] = new C1254a();
            int i3 = i2 * 6;
            int i4 = i2 * 4;
            this.f4400k.put(i3 + 0, (short) (i4 + 0));
            this.f4400k.put(i3 + 1, (short) (i4 + 2));
            this.f4400k.put(i3 + 2, (short) (i4 + 1));
            this.f4400k.put(i3 + 3, (short) (i4 + 1));
            this.f4400k.put(i3 + 4, (short) (i4 + 2));
            this.f4400k.put(i3 + 5, (short) (i4 + 3));
        }
    }

    /* renamed from: a */
    public void m6322a(Bitmap bitmap, boolean z) {
        ((Texture2D) this.f4497l).m6167b(bitmap, z);
        m6314f();
        m6313g();
    }

    /* renamed from: f */
    protected void m6314f() {
        this.f4396g = this.f4497l.m6171b().m6138b() / this.f4497l.m6171b().m6141a();
        this.f4397h = (float) Math.atan(this.f4396g);
    }

    /* renamed from: g */
    protected void m6313g() {
        float m6170c = this.f4497l.m6170c();
        float m6169d = this.f4497l.m6169d();
        for (int i = 0; i < this.f4395d.f4421E; i++) {
            int i2 = i * 8;
            this.f4399j.put(i2 + 0, 0.0f);
            this.f4399j.put(i2 + 1, 0.0f);
            this.f4399j.put(i2 + 2, m6170c);
            this.f4399j.put(i2 + 3, 0.0f);
            this.f4399j.put(i2 + 4, 0.0f);
            this.f4399j.put(i2 + 5, m6169d);
            this.f4399j.put(i2 + 6, m6170c);
            this.f4399j.put(i2 + 7, m6169d);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public float mo6212b(float f) {
        return (this.f4395d.f4422F * f) + this.f4394c;
    }

    /* renamed from: h */
    protected C1255b mo6211h() {
        return this.f4395d;
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.Scheduler.InterfaceC1275a
    /* renamed from: a */
    public void mo6105a(float f) {
        int i = 0;
        float mo6212b = mo6212b(f);
        this.f4393b = (int) mo6212b;
        this.f4394c = mo6212b - this.f4393b;
        C1255b mo6211h = mo6211h();
        mo6211h.f4425b += mo6211h.f4427d * f;
        for (int i2 = 0; i2 < mo6211h.f4421E; i2++) {
            C1254a c1254a = this.f4392a[i2];
            if (c1254a.f4416o > 0.0f) {
                C1254a.m6310a(c1254a, f);
                PointF pointF = new PointF();
                m6321a(c1254a.f4404c, f, pointF);
                m6320a(c1254a.f4405d, pointF, c1254a.f4405d);
                C1254a.m6308b(c1254a, c1254a.f4407f * f);
                C1254a.m6306c(c1254a, c1254a.f4409h * f);
                C1254a.m6304d(c1254a, c1254a.f4411j * f);
                C1254a.m6302e(c1254a, c1254a.f4413l * f);
                c1254a.f4414m.m6155a(c1254a.f4414m.m6156a() + (c1254a.f4415n.m6156a() * f));
                c1254a.f4414m.m6152b(c1254a.f4414m.m6153b() + (c1254a.f4415n.m6153b() * f));
                c1254a.f4414m.m6150c(c1254a.f4414m.m6151c() + (c1254a.f4415n.m6151c() * f));
                c1254a.f4414m.m6147d(c1254a.f4414m.m6149d() + (c1254a.f4415n.m6149d() * f));
                m6317b(c1254a, i2);
            } else if (i >= this.f4393b) {
                if (c1254a.f4416o != -100.0f) {
                    m6319a(c1254a, i2);
                }
            } else {
                m6318a(c1254a, mo6211h);
                m6317b(c1254a, i2);
                i++;
            }
        }
    }

    /* renamed from: a */
    private void m6318a(C1254a c1254a, C1255b c1255b) {
        c1254a.f4416o = Math.max(0.0f, c1255b.f4448y + (c1255b.f4449z * m6312i()));
        c1254a.f4403b = (float) Math.toRadians(c1255b.f4425b + (c1255b.f4426c * m6312i()));
        m6321a(new PointF((float) Math.cos(c1254a.f4403b), (float) Math.sin(c1254a.f4403b)), Math.max(0.0f, c1255b.f4428e + (c1255b.f4429f * m6312i())), c1254a.f4404c);
        c1254a.f4405d.x = (c1255b.f4431h.x * m6312i()) + c1255b.f4430g.x;
        c1254a.f4405d.y = (c1255b.f4431h.y * m6312i()) + c1255b.f4430g.y;
        c1254a.f4406e = (float) Math.toRadians((c1255b.f4433j * m6312i()) + c1255b.f4432i);
        c1254a.f4407f = (float) Math.toRadians((c1255b.f4435l * m6312i()) + c1255b.f4434k);
        c1254a.f4408g = Math.max(0.0f, (c1255b.f4437n * m6312i()) + c1255b.f4436m);
        c1254a.f4409h = (Math.max(0.0f, (c1255b.f4439p * m6312i()) + c1255b.f4438o) - c1254a.f4408g) / c1254a.f4416o;
        c1254a.f4410i = (float) Math.toRadians((c1255b.f4441r * m6312i()) + c1255b.f4440q);
        c1254a.f4411j = (float) Math.toRadians((c1255b.f4443t * m6312i()) + c1255b.f4442s);
        c1254a.f4412k = Math.max(1.0f, (c1255b.f4445v * m6312i()) + c1255b.f4444u);
        c1254a.f4413l = (Math.max(1.0f, (c1255b.f4447x * m6312i()) + c1255b.f4446w) - c1254a.f4412k) / c1254a.f4416o;
        c1254a.f4414m.m6155a(m6323a(0.0f, 1.0f, c1255b.f4417A.m6156a() + (c1255b.f4418B.m6156a() * m6312i())));
        c1254a.f4414m.m6152b(m6323a(0.0f, 1.0f, c1255b.f4417A.m6153b() + (c1255b.f4418B.m6153b() * m6312i())));
        c1254a.f4414m.m6150c(m6323a(0.0f, 1.0f, c1255b.f4417A.m6151c() + (c1255b.f4418B.m6151c() * m6312i())));
        c1254a.f4414m.m6147d(m6323a(0.0f, 1.0f, c1255b.f4417A.m6149d() + (c1255b.f4418B.m6149d() * m6312i())));
        Color4F color4F = new Color4F();
        color4F.m6155a(m6323a(0.0f, 1.0f, c1255b.f4419C.m6156a() + (c1255b.f4420D.m6156a() * m6312i())));
        color4F.m6152b(m6323a(0.0f, 1.0f, c1255b.f4419C.m6153b() + (c1255b.f4420D.m6153b() * m6312i())));
        color4F.m6150c(m6323a(0.0f, 1.0f, c1255b.f4419C.m6151c() + (c1255b.f4420D.m6151c() * m6312i())));
        color4F.m6147d(m6323a(0.0f, 1.0f, c1255b.f4419C.m6149d() + (c1255b.f4420D.m6149d() * m6312i())));
        c1254a.f4415n.m6155a((color4F.m6156a() - c1254a.f4414m.m6156a()) / c1254a.f4416o);
        c1254a.f4415n.m6152b((color4F.m6153b() - c1254a.f4414m.m6153b()) / c1254a.f4416o);
        c1254a.f4415n.m6150c((color4F.m6151c() - c1254a.f4414m.m6151c()) / c1254a.f4416o);
        c1254a.f4415n.m6147d((color4F.m6149d() - c1254a.f4414m.m6149d()) / c1254a.f4416o);
    }

    /* renamed from: a */
    private void m6319a(C1254a c1254a, int i) {
        c1254a.f4416o = -100.0f;
        this.f4401n.put((i * 16) + 0, 0.0f);
        this.f4401n.put((i * 16) + 1, 0.0f);
        this.f4401n.put((i * 16) + 2, 0.0f);
        this.f4401n.put((i * 16) + 3, 0.0f);
        this.f4401n.put((i * 16) + 4, 0.0f);
        this.f4401n.put((i * 16) + 5, 0.0f);
        this.f4401n.put((i * 16) + 6, 0.0f);
        this.f4401n.put((i * 16) + 7, 0.0f);
        this.f4401n.put((i * 16) + 8, 0.0f);
        this.f4401n.put((i * 16) + 9, 0.0f);
        this.f4401n.put((i * 16) + 10, 0.0f);
        this.f4401n.put((i * 16) + 11, 0.0f);
        this.f4401n.put((i * 16) + 12, 0.0f);
        this.f4401n.put((i * 16) + 13, 0.0f);
        this.f4401n.put((i * 16) + 14, 0.0f);
        this.f4401n.put((i * 16) + 15, 0.0f);
    }

    /* renamed from: b */
    private void m6317b(C1254a c1254a, int i) {
        this.f4401n.put((i * 16) + 0, c1254a.f4414m.m6156a());
        this.f4401n.put((i * 16) + 1, c1254a.f4414m.m6153b());
        this.f4401n.put((i * 16) + 2, c1254a.f4414m.m6151c());
        this.f4401n.put((i * 16) + 3, c1254a.f4414m.m6149d());
        this.f4401n.put((i * 16) + 4, c1254a.f4414m.m6156a());
        this.f4401n.put((i * 16) + 5, c1254a.f4414m.m6153b());
        this.f4401n.put((i * 16) + 6, c1254a.f4414m.m6151c());
        this.f4401n.put((i * 16) + 7, c1254a.f4414m.m6149d());
        this.f4401n.put((i * 16) + 8, c1254a.f4414m.m6156a());
        this.f4401n.put((i * 16) + 9, c1254a.f4414m.m6153b());
        this.f4401n.put((i * 16) + 10, c1254a.f4414m.m6151c());
        this.f4401n.put((i * 16) + 11, c1254a.f4414m.m6149d());
        this.f4401n.put((i * 16) + 12, c1254a.f4414m.m6156a());
        this.f4401n.put((i * 16) + 13, c1254a.f4414m.m6153b());
        this.f4401n.put((i * 16) + 14, c1254a.f4414m.m6151c());
        this.f4401n.put((i * 16) + 15, c1254a.f4414m.m6149d());
        float f = c1254a.f4403b + c1254a.f4406e;
        PointF pointF = new PointF();
        pointF.x = (c1254a.f4408g * ((float) Math.cos(f))) + c1254a.f4405d.x;
        pointF.y = (c1254a.f4408g * ((float) Math.sin(f))) + c1254a.f4405d.y;
        float f2 = f + c1254a.f4410i;
        float f3 = c1254a.f4412k / 2.0f;
        float f4 = this.f4396g * f3;
        float sqrt = (float) Math.sqrt((f3 * f3) + (f4 * f4));
        float cos = ((float) Math.cos(this.f4397h + f2)) * sqrt;
        float sin = ((float) Math.sin(this.f4397h + f2)) * sqrt;
        float cos2 = ((float) Math.cos(f2 - this.f4397h)) * sqrt;
        float sin2 = ((float) Math.sin(f2 - this.f4397h)) * sqrt;
        PointF pointF2 = new PointF();
        PointF pointF3 = new PointF();
        PointF pointF4 = new PointF();
        PointF pointF5 = new PointF();
        pointF2.x = pointF.x + cos;
        pointF2.y = pointF.y + sin;
        pointF3.x = pointF.x - cos2;
        pointF3.y = pointF.y - sin2;
        pointF4.x = pointF.x - cos;
        pointF4.y = pointF.y - sin;
        pointF5.x = pointF.x + cos2;
        pointF5.y = sin2 + pointF.y;
        this.f4398i.put((i * 8) + 0, pointF3.x);
        this.f4398i.put((i * 8) + 1, pointF3.y);
        this.f4398i.put((i * 8) + 2, pointF2.x);
        this.f4398i.put((i * 8) + 3, pointF2.y);
        this.f4398i.put((i * 8) + 4, pointF4.x);
        this.f4398i.put((i * 8) + 5, pointF4.y);
        this.f4398i.put((i * 8) + 6, pointF5.x);
        this.f4398i.put((i * 8) + 7, pointF5.y);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene
    /* renamed from: b */
    public void mo6185b() {
        super.mo6185b();
        GLES10.glBindTexture(3553, this.f4497l.m6175a());
        GLES10.glVertexPointer(2, 5126, 0, this.f4398i);
        GLES10.glColorPointer(4, 5126, 0, this.f4401n);
        GLES10.glTexCoordPointer(2, 5126, 0, this.f4399j);
        GLES10.glDrawElements(4, this.f4395d.f4421E * 2 * 3, 5123, this.f4400k);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
        mo6261e_();
    }

    /* renamed from: i */
    public static float m6312i() {
        return (((float) Math.random()) * 2.0f) - 1.0f;
    }

    /* renamed from: a */
    public static float m6323a(float f, float f2, float f3) {
        return Math.max(f, Math.min(f2, f3));
    }

    /* renamed from: a */
    public static void m6321a(PointF pointF, float f, PointF pointF2) {
        pointF2.x = pointF.x * f;
        pointF2.y = pointF.y * f;
    }

    /* renamed from: a */
    public static void m6320a(PointF pointF, PointF pointF2, PointF pointF3) {
        pointF3.x = pointF.x + pointF2.x;
        pointF3.y = pointF.y + pointF2.y;
    }
}
