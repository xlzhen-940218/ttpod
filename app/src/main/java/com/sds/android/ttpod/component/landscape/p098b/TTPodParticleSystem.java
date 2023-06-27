package com.sds.android.ttpod.component.landscape.p098b;

import android.content.Context;
import android.util.Xml;

import com.sds.android.ttpod.framework.TTPodConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: com.sds.android.ttpod.component.landscape.b.k */
/* loaded from: classes.dex */
public class TTPodParticleSystem extends ParticleSystemWithConfig {

    /* renamed from: n */
    private static final String f4515n = TTPodConfig.getLandscapePath() + File.separator + "ttpod_particle_effect_configuration.xml";

    /* renamed from: o */
    private static final String f4516o = f4484g + "ttpod_particle_effect_configuration.xml";

    /* renamed from: p */
    private ArrayList<C1259b> f4517p;

    /* renamed from: q */
    private ArrayList<ArrayList<C1259b>> f4518q;

    /* renamed from: r */
    private float f4519r;

    /* renamed from: s */
    private float f4520s;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TTPodParticleSystem.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.b.k$b */
    /* loaded from: classes.dex */
    public static class C1259b {

        /* renamed from: a */
        private boolean f4524a;

        /* renamed from: b */
        private boolean f4525b;

        /* renamed from: c */
        private boolean f4526c;

        /* renamed from: d */
        private float f4527d;

        /* renamed from: e */
        private boolean f4528e;

        /* renamed from: f */
        private float f4529f;

        /* renamed from: g */
        private float f4530g;

        /* renamed from: h */
        private boolean f4531h;

        /* renamed from: i */
        private boolean f4532i;

        /* renamed from: j */
        private boolean f4533j;

        /* renamed from: k */
        private boolean f4534k;

        private C1259b() {
        }
    }

    /* compiled from: TTPodParticleSystem.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.b.k$a */
    /* loaded from: classes.dex */
    private class C1258a extends BaseParticleSystem {

        /* renamed from: h */
        private BaseParticleSystem.C1255b f4522h;

        /* renamed from: i */
        private C1259b f4523i;

        public C1258a(BaseParticleSystem.C1255b c1255b) {
            super(c1255b);
            this.f4522h = new BaseParticleSystem.C1255b(c1255b);
        }

        @Override // com.sds.android.ttpod.component.landscape.p098b.BaseParticleSystem
        /* renamed from: h */
        protected BaseParticleSystem.C1255b mo6211h() {
            return this.f4522h;
        }

        @Override // com.sds.android.ttpod.component.landscape.p098b.BaseParticleSystem, com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.Scheduler.InterfaceC1275a
        /* renamed from: a */
        public void mo6105a(float f) {
            if (this.f4523i == null) {
                this.f4523i = TTPodParticleSystem.this.m6215c(this.f4395d.f4424a);
            }
            if (this.f4523i.f4524a) {
                this.f4522h.f4448y = this.f4395d.f4448y * TTPodParticleSystem.this.f4519r;
            }
            if (this.f4523i.f4526c) {
                this.f4522h.f4428e = this.f4395d.f4428e * TTPodParticleSystem.this.f4519r * TTPodParticleSystem.this.f4520s;
            }
            if (this.f4523i.f4527d > 1.0f) {
                float f2 = this.f4395d.f4425b - this.f4523i.f4527d;
                this.f4522h.f4425b = f2 + (this.f4523i.f4527d * TTPodParticleSystem.this.f4519r * 2.0f);
            }
            if (this.f4523i.f4528e) {
                this.f4522h.f4427d = this.f4395d.f4427d * TTPodParticleSystem.this.f4519r;
            }
            if (this.f4523i.f4529f > 1.0f) {
                float f3 = this.f4395d.f4430g.x - this.f4523i.f4529f;
                this.f4522h.f4430g.x = f3 + (this.f4523i.f4529f * TTPodParticleSystem.this.f4519r * 2.0f);
            } else if (this.f4523i.f4530g > 1.0f) {
                float f4 = this.f4395d.f4430g.y - this.f4523i.f4530g;
                this.f4522h.f4430g.y = f4 + (this.f4523i.f4530g * TTPodParticleSystem.this.f4519r * 2.0f);
            }
            if (this.f4523i.f4531h) {
                this.f4522h.f4434k = this.f4395d.f4434k * TTPodParticleSystem.this.f4519r;
            }
            if (this.f4523i.f4532i && this.f4395d.f4436m != this.f4395d.f4438o) {
                float min = Math.min(this.f4395d.f4436m, this.f4395d.f4438o);
                float max = min + ((Math.max(this.f4395d.f4436m, this.f4395d.f4438o) - min) * TTPodParticleSystem.this.f4519r);
                this.f4522h.f4436m = max;
                this.f4522h.f4438o = max;
            }
            if (this.f4523i.f4533j) {
                this.f4522h.f4442s = this.f4395d.f4442s * TTPodParticleSystem.this.f4519r;
            }
            if (this.f4523i.f4534k && this.f4395d.f4444u != this.f4395d.f4446w) {
                float min2 = Math.min(this.f4395d.f4444u, this.f4395d.f4446w);
                float max2 = min2 + ((Math.max(this.f4395d.f4444u, this.f4395d.f4446w) - min2) * TTPodParticleSystem.this.f4519r);
                this.f4522h.f4444u = max2;
                this.f4522h.f4446w = max2;
            }
            super.mo6105a(f);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.component.landscape.p098b.BaseParticleSystem
        /* renamed from: b */
        public float mo6212b(float f) {
            return this.f4523i.f4525b ? (this.f4395d.f4422F * f * TTPodParticleSystem.this.f4519r) + this.f4394c : super.mo6212b(f);
        }
    }

    public TTPodParticleSystem(Context context) {
        super(context);
        m6213o();
        this.f4517p = this.f4518q.get(this.f4487h);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.ParticleSystemWithConfig
    /* renamed from: a */
    protected BaseParticleSystem mo6221a(BaseParticleSystem.C1255b c1255b) {
        return new C1258a(c1255b);
    }

    /* renamed from: o */
    protected void m6213o() {
        try {
            InputStream open = this.f4490k.getAssets().open(f4516o);
            this.f4518q = m6218a(open);
            open.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private ArrayList<ArrayList<C1259b>> m6218a(InputStream inputStream) throws Throwable {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "UTF-8");
        ArrayList<C1259b> arrayList = null;
        ArrayList<ArrayList<C1259b>> arrayList2 = null;
        C1259b c1259b = null;
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            switch (eventType) {
                case 0:
                    arrayList2 = new ArrayList<>();
                    break;
                case 2:
                    String name = newPullParser.getName();
                    if ("ttpod_particle_effect_list".equals(name)) {
                        arrayList = new ArrayList<>();
                        break;
                    } else if (arrayList == null) {
                        break;
                    } else if ("ttpod_particle_effect".equals(name)) {
                        c1259b = new C1259b();
                        break;
                    } else if (c1259b == null) {
                        break;
                    } else if ("impact_emit_count".equals(name)) {
                        c1259b.f4525b = true;
                        break;
                    } else if ("impact_life".equals(name)) {
                        c1259b.f4524a = true;
                        break;
                    } else if ("impact_speed".equals(name)) {
                        c1259b.f4526c = true;
                        break;
                    } else if ("impact_emit_angle".equals(name)) {
                        c1259b.f4527d = m6274a(newPullParser);
                        break;
                    } else if ("impact_emit_angle_delta".equals(name)) {
                        c1259b.f4528e = true;
                        break;
                    } else if ("impact_position_x".equals(name)) {
                        c1259b.f4529f = m6274a(newPullParser);
                        break;
                    } else if ("impact_position_y".equals(name)) {
                        c1259b.f4530g = m6274a(newPullParser);
                        break;
                    } else if ("impact_revolution_angle".equals(name)) {
                        c1259b.f4531h = true;
                        break;
                    } else if ("impact_revolution_radius".equals(name)) {
                        c1259b.f4532i = true;
                        break;
                    } else if ("impact_rotate_angle".equals(name)) {
                        c1259b.f4533j = true;
                        break;
                    } else if ("impact_size".equals(name)) {
                        c1259b.f4534k = true;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ("ttpod_particle_effect".equals(newPullParser.getName())) {
                        arrayList.add(c1259b);
                        c1259b = null;
                        break;
                    } else if ("ttpod_particle_effect_list".equals(newPullParser.getName())) {
                        arrayList2.add(arrayList);
                        arrayList = null;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public C1259b m6215c(int i) {
        if (i < this.f4517p.size()) {
            return this.f4517p.get(i);
        }
        return this.f4517p.get(0);
    }

    /* renamed from: a */
    public void m6222a(int i) {
        this.f4520s = i / 800.0f;
    }

    /* renamed from: c */
    public void m6216c(float f) {
        this.f4519r = 0.0f;
        if (f > 0.0f) {
            this.f4519r = f / 80.0f;
            if (this.f4519r > 1.0f) {
                this.f4519r = 1.0f;
            }
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.ParticleSystemWithConfig, com.sds.android.ttpod.component.landscape.p098b.NextEffect
    /* renamed from: f_ */
    public void mo6214f_() {
        super.mo6214f_();
        int i = 0;
        if (this.f4518q.size() > this.f4487h) {
            i = this.f4487h;
        }
        this.f4517p = this.f4518q.get(i);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.ParticleSystemWithConfig, com.sds.android.ttpod.component.landscape.p098b.BaseParticleSystem, com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
        Iterator<ArrayList<C1259b>> it = this.f4518q.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.f4518q.clear();
        this.f4517p.clear();
    }
}
