package com.sds.android.ttpod.component.landscape.p098b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.landscape.LandscapeData;
import com.sds.android.ttpod.component.landscape.LandscapeGestureHelper;
import com.sds.android.ttpod.component.landscape.LandscapeMediaHelper;
import com.sds.android.ttpod.component.landscape.LyricProvider;
import com.sds.android.ttpod.component.landscape.SizeChange;
import com.sds.android.ttpod.component.landscape.p097a.Action;
import com.sds.android.ttpod.component.landscape.p097a.ActionFadeIn;
import com.sds.android.ttpod.component.landscape.p097a.ActionFadeOut;
import com.sds.android.ttpod.component.landscape.p097a.ActionInstant;
import com.sds.android.ttpod.component.landscape.p097a.ActionMoveTo;
import com.sds.android.ttpod.component.landscape.p097a.ActionScaleTo;
import com.sds.android.ttpod.component.landscape.p097a.ActionSequence;
import com.sds.android.ttpod.component.landscape.p097a.ActionSpawn;
import com.sds.android.ttpod.component.landscape.p097a.ActionYRotateTo;
import com.sds.android.ttpod.component.landscape.p097a.ActionZRotateTo;
import com.sds.android.ttpod.framework.p106a.BitmapUtils;

/* renamed from: com.sds.android.ttpod.component.landscape.b.d */
/* loaded from: classes.dex */
public class MainScene extends Scene implements ActionInstant.InterfaceC1253a, NextEffect, LandscapeGestureHelper.InterfaceC1262c, LandscapeGestureHelper.InterfaceC1263d, LandscapeGestureHelper.InterfaceC1264e, LandscapeMediaHelper.InterfaceC1265a, LandscapeMediaHelper.InterfaceC1266b, LandscapeMediaHelper.InterfaceC1267c, LandscapeMediaHelper.InterfaceC1268d, LandscapeMediaHelper.InterfaceC1269e, LyricProvider.InterfaceC1273a, SizeChange {

    /* renamed from: A */
    private ActionSequence f4452A;

    /* renamed from: B */
    private ActionInstant f4453B;

    /* renamed from: C */
    private Bitmap f4454C;

    /* renamed from: D */
    private Bitmap f4455D;

    /* renamed from: E */
    private Bitmap f4456E;

    /* renamed from: F */
    private boolean f4457F;

    /* renamed from: G */
    private String f4458G;

    /* renamed from: b */
    private Context f4461b;

    /* renamed from: c */
    private int f4462c;

    /* renamed from: d */
    private int f4463d;

    /* renamed from: g */
    private Background f4464g;

    /* renamed from: h */
    private BaseParticleSystem f4465h;

    /* renamed from: i */
    private Torus f4466i;

    /* renamed from: j */
    private Scene f4467j;

    /* renamed from: k */
    private PhotoFrame f4468k;

    /* renamed from: n */
    private Scene f4469n;

    /* renamed from: o */
    private Scene f4470o;

    /* renamed from: p */
    private Scene f4471p;

    /* renamed from: q */
    private LyricSentence f4472q;

    /* renamed from: r */
    private ShowMusicInfoSimple f4473r;

    /* renamed from: s */
    private ActionSequence f4474s;

    /* renamed from: t */
    private ActionInstant f4475t;

    /* renamed from: u */
    private ActionSequence f4476u;

    /* renamed from: v */
    private ActionSequence f4477v;

    /* renamed from: w */
    private ActionInstant f4478w;

    /* renamed from: x */
    private ActionZRotateTo f4479x;

    /* renamed from: y */
    private ActionSequence f4480y;

    /* renamed from: z */
    private ActionInstant f4481z;

    /* renamed from: a */
    private int f4460a = 3;

    /* renamed from: H */
    private long f4459H = 0;

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
        this.f4464g.mo6188a();
        this.f4465h.mo6188a();
        this.f4466i.mo6188a();
        this.f4467j.mo6188a();
        this.f4468k.mo6188a();
        this.f4469n.mo6188a();
        this.f4470o.mo6188a();
        this.f4471p.mo6188a();
        this.f4472q.mo6188a();
        this.f4473r.mo6188a();
        this.f4454C.recycle();
    }

    public MainScene(Context context) {
        this.f4461b = context;
        m6280f();
        m6279g();
        this.f4457F = true;
    }

    /* renamed from: f */
    private void m6280f() {
        BitmapUtils c1780b = new BitmapUtils();
        c1780b.getNewOptions().inScaled = false;
        int m7225c = DisplayUtils.getWidth();
        int m7224d = DisplayUtils.getHeight();
        this.f4464g = new Background();
        Bitmap m4789a = c1780b.m4789a(this.f4461b.getResources(), R.raw.background, m7225c, m7224d);
        if (m4789a != null) {
            LogUtils.debug("MainScene", "initChildren background bmpWidth=" + m4789a.getWidth() + " bmpHeight=" + m4789a.getHeight());
        }
        this.f4464g.m6324a(m4789a);
        m6276a(this.f4464g, 0);
        if (this.f4460a == 1) {
            this.f4465h = new BaseParticleSystem(null);
            m7224d = m7225c <= 320 ? 32 : 64;
            this.f4465h.m6322a(c1780b.m4789a(this.f4461b.getResources(), R.raw.torus, m7224d, m7224d), true);
        } else if (this.f4460a == 2) {
            this.f4465h = new ParticleSystemWithConfig(this.f4461b);
        } else {
            this.f4465h = new TTPodParticleSystem(this.f4461b);
        }
        this.f4466i = new Torus();
        this.f4466i.m6186a(c1780b.m4789a(this.f4461b.getResources(), R.raw.torus, m7224d, m7224d));
        this.f4467j = new Scene();
        if (this.f4465h != null) {
            this.f4467j.m6276a(this.f4465h, 0);
        }
        if (this.f4466i != null) {
            this.f4467j.m6276a(this.f4466i, 10);
        }
        this.f4467j.m6257m().m6248b(-90.0f);
        this.f4468k = new PhotoFrame();
        int i = m7225c <= 320 ? 128 : 256;
        this.f4454C = c1780b.m4789a(this.f4461b.getResources(), R.raw.landscape_default, i, i);
        this.f4469n = new Scene();
        this.f4469n.m6276a(this.f4467j, 0);
        if (this.f4468k != null) {
            this.f4469n.m6276a(this.f4468k, 10);
        }
        this.f4470o = new Scene();
        this.f4470o.m6276a(this.f4469n, 0);
        this.f4470o.m6257m().m6248b(10.0f);
        this.f4471p = new Scene();
        this.f4471p.m6276a(this.f4470o, 0);
        m6276a(this.f4471p, 10);
        this.f4472q = new LyricSentence();
        m6276a(this.f4472q, 20);
        this.f4473r = new ShowMusicInfoSimple();
        m6276a(this.f4473r, 30);
    }

    /* renamed from: g */
    private void m6279g() {
        if (this.f4473r != null) {
            this.f4475t = new ActionInstant();
            this.f4475t.m6360a((ActionInstant.InterfaceC1253a) this);
            this.f4474s = ActionSequence.m6353a(new ActionSpawn(new ActionFadeIn(0.001f), new ActionScaleTo(0.001f, 1.0f)), new ActionSpawn(new ActionFadeOut(2.0f), new ActionScaleTo(2.0f, 1.6f)), this.f4475t);
        }
        this.f4478w = new ActionInstant();
        this.f4478w.m6360a((ActionInstant.InterfaceC1253a) this);
        this.f4479x = new ActionZRotateTo(0.001f, 0.0f);
        if (this.f4468k != null) {
            this.f4481z = new ActionInstant();
            this.f4481z.m6360a((ActionInstant.InterfaceC1253a) this);
            this.f4480y = ActionSequence.m6353a(new ActionYRotateTo(0.001f, 0.0f), new ActionYRotateTo(0.5f, -90.0f), new ActionYRotateTo(0.001f, 90.0f), this.f4481z, new ActionYRotateTo(0.5f, 0.0f));
        }
        if (this.f4472q != null) {
            this.f4453B = new ActionInstant();
            this.f4453B.m6360a((ActionInstant.InterfaceC1253a) this);
            this.f4452A = ActionSequence.m6353a(new ActionSpawn(new ActionFadeOut(0.25f), new ActionScaleTo(0.25f, 0.7f)), this.f4453B, new ActionSpawn(new ActionFadeIn(0.25f), new ActionScaleTo(0.25f, 1.0f)));
        }
    }

    /* renamed from: h */
    private void m6278h() {
        PointF m6255a = this.f4469n.m6257m().m6255a();
        PointF pointF = new PointF(m6255a.x - 1000.0f, m6255a.y + 200.0f);
        PointF pointF2 = new PointF(m6255a.x + 1000.0f, m6255a.y + 200.0f);
        this.f4476u = ActionSequence.m6353a(new ActionSpawn(new ActionMoveTo(0.001f, m6255a), new ActionScaleTo(0.001f, 1.0f)), new ActionSpawn(new ActionMoveTo(0.5f, pointF), new ActionScaleTo(0.5f, 0.5f)), this.f4478w, new ActionMoveTo(0.001f, pointF2), this.f4479x, new ActionSpawn(new ActionMoveTo(0.5f, m6255a), new ActionScaleTo(0.5f, 1.0f)));
        this.f4477v = ActionSequence.m6353a(new ActionSpawn(new ActionMoveTo(0.001f, m6255a), new ActionScaleTo(0.001f, 1.0f)), new ActionSpawn(new ActionMoveTo(0.5f, pointF2), new ActionScaleTo(0.5f, 0.5f)), this.f4478w, new ActionMoveTo(0.001f, pointF), this.f4479x, new ActionSpawn(new ActionMoveTo(0.5f, m6255a), new ActionScaleTo(0.5f, 1.0f)));
    }

    @Override // com.sds.android.ttpod.component.landscape.SizeChange
    /* renamed from: a */
    public void mo6100a(int i, int i2) {
        if (i != this.f4462c) {
            if (this.f4465h != null && this.f4460a == 3) {
                ((TTPodParticleSystem) this.f4465h).m6222a(i);
            }
            if (this.f4466i != null) {
                this.f4466i.m6187a(i);
            }
        }
        if (i2 != this.f4463d && this.f4468k != null) {
            this.f4468k.m6266b(i2 * 0.4f);
            this.f4455D = this.f4454C;
            this.f4468k.m6267a(this.f4455D);
            this.f4456E = this.f4455D;
        }
        if (i != this.f4462c || i2 != this.f4463d) {
            if (this.f4464g != null) {
                this.f4464g.m6325a(i, i2);
            }
            this.f4471p.m6257m().m6253a(i * 0.5f, i2 * 0.36f);
            if (this.f4472q != null) {
                this.f4472q.m6257m().m6253a(i * 0.5f, i2 * LandscapeData.m6328h());
            }
            if (this.f4473r != null) {
                this.f4473r.m6257m().m6253a(i * 0.5f, i2 * LandscapeData.m6327i());
            }
            m6278h();
        }
        this.f4462c = i;
        this.f4463d = i2;
    }

    /* renamed from: i */
    private void m6277i() {
        if (this.f4456E != this.f4455D && this.f4468k != null && !this.f4455D.isRecycled()) {
            this.f4468k.m6267a(this.f4455D);
            this.f4456E = this.f4455D;
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.p097a.ActionInstant.InterfaceC1253a
    /* renamed from: a */
    public void mo6281a(Action action) {
        if (action == this.f4478w) {
            m6277i();
            this.f4457F = true;
        } else if (action == this.f4481z) {
            m6277i();
        } else if (action == this.f4453B) {
            this.f4472q.m6282a(this.f4458G == null ? "" : this.f4458G);
        } else if (action == this.f4475t) {
            this.f4473r.f4498m.m6250a(false);
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.LandscapeMediaHelper.InterfaceC1265a
    /* renamed from: d */
    public void mo6148d() {
        this.f4455D = this.f4454C;
    }

    @Override // com.sds.android.ttpod.component.landscape.LandscapeMediaHelper.InterfaceC1266b
    /* renamed from: a */
    public void mo6144a(String str, String str2) {
        if (this.f4473r != null) {
            this.f4473r.m6223a(str, str2);
            this.f4473r.f4498m.m6250a(true);
            this.f4473r.m6264b(this.f4474s);
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.LandscapeMediaHelper.InterfaceC1267c
    /* renamed from: a */
    public void mo6139a(int i) {
        if (i != 12) {
            this.f4457F = false;
        }
        if (i == 10) {
            this.f4479x.m6355c((float) (((Math.random() * 2.0d) - 1.0d) * 10.0d));
            this.f4469n.m6264b(this.f4477v);
        } else if (i == 11) {
            this.f4479x.m6355c((float) (((Math.random() * 2.0d) - 1.0d) * 10.0d));
            this.f4469n.m6264b(this.f4476u);
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.LandscapeMediaHelper.InterfaceC1268d
    /* renamed from: a */
    public void mo6136a(Bitmap bitmap) {
        if (bitmap == null) {
            bitmap = this.f4454C;
        }
        this.f4455D = bitmap;
        if (this.f4468k != null && this.f4455D != this.f4456E && this.f4457F) {
            this.f4468k.m6264b(this.f4480y);
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.LyricProvider.InterfaceC1273a
    /* renamed from: a */
    public void mo6122a(String str, String str2, boolean z) {
        this.f4458G = str;
        if (this.f4472q != null) {
            this.f4472q.m6264b(this.f4452A);
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.LandscapeMediaHelper.InterfaceC1269e
    /* renamed from: a */
    public void mo6135a(int[] iArr) {
        float f;
        float f2;
        float f3 = 0.0f;
        if (iArr == null || iArr.length < 10) {
            f = 0.0f;
            f2 = 0.0f;
        } else {
            float f4 = 0.0f;
            for (int i = 0; i < 32; i++) {
                f4 += iArr[i];
                if (i < 4) {
                    f3 += iArr[i];
                }
            }
            f2 = f4 / 32.0f;
            f = f3 / 4.0f;
        }
        if (this.f4465h != null && this.f4460a == 3) {
            ((TTPodParticleSystem) this.f4465h).m6216c(f);
        }
        if (this.f4466i != null) {
            this.f4466i.m6184b(f2);
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.LandscapeGestureHelper.InterfaceC1263d
    /* renamed from: c_ */
    public Scene[] mo6163c_() {
        return new Scene[]{this.f4470o};
    }

    @Override // com.sds.android.ttpod.component.landscape.LandscapeGestureHelper.InterfaceC1264e
    /* renamed from: c */
    public Scene[] mo6161c() {
        return new Scene[]{this.f4471p};
    }

    @Override // com.sds.android.ttpod.component.landscape.LandscapeGestureHelper.InterfaceC1262c
    /* renamed from: d_ */
    public void mo6165d_() {
        mo6214f_();
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.NextEffect
    /* renamed from: f_ */
    public void mo6214f_() {
        if (System.currentTimeMillis() - this.f4459H >= 1000) {
            this.f4459H = System.currentTimeMillis();
            if (this.f4465h != null && (this.f4465h instanceof ParticleSystemWithConfig)) {
                ((ParticleSystemWithConfig) this.f4465h).mo6214f_();
            }
        }
    }
}
