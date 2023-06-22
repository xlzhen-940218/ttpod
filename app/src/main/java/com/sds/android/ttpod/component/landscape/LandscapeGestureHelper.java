package com.sds.android.ttpod.component.landscape;

import android.view.View;
import com.sds.android.ttpod.component.landscape.GestureView;
import com.sds.android.ttpod.component.landscape.Scheduler;
import com.sds.android.ttpod.component.landscape.p097a.ActionXDecelerateRotateTo;
import com.sds.android.ttpod.component.landscape.p097a.ActionZDecelerateMoveTo;
import com.sds.android.ttpod.component.landscape.p098b.Scene;

/* renamed from: com.sds.android.ttpod.component.landscape.c */
/* loaded from: classes.dex */
public class LandscapeGestureHelper implements View.OnClickListener, View.OnLongClickListener, GestureView.InterfaceC1235a, GestureView.InterfaceC1236b, GestureView.InterfaceC1237c, GestureView.InterfaceC1238d, Scheduler.InterfaceC1275a {

    /* renamed from: a */
    private float f4537a = 0.0f;

    /* renamed from: b */
    private float f4538b = 0.0f;

    /* renamed from: c */
    private boolean f4539c = false;

    /* renamed from: d */
    private float f4540d = 0.0f;

    /* renamed from: e */
    private float f4541e = 0.0f;

    /* renamed from: f */
    private boolean f4542f = false;

    /* renamed from: g */
    private boolean f4543g;

    /* renamed from: h */
    private boolean f4544h;

    /* renamed from: i */
    private boolean f4545i;

    /* compiled from: LandscapeGestureHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1260a {
        /* renamed from: a */
        void m6174a();
    }

    /* compiled from: LandscapeGestureHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.c$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1261b {
        /* renamed from: a */
        void m6168a();
    }

    /* compiled from: LandscapeGestureHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.c$c */
    /* loaded from: classes.dex */
    public interface InterfaceC1262c {
        /* renamed from: d_ */
        void mo6165d_();
    }

    /* compiled from: LandscapeGestureHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.c$d */
    /* loaded from: classes.dex */
    public interface InterfaceC1263d {
        /* renamed from: c_ */
        Scene[] mo6163c_();
    }

    /* compiled from: LandscapeGestureHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.c$e */
    /* loaded from: classes.dex */
    public interface InterfaceC1264e {
        /* renamed from: c */
        Scene[] mo6161c();
    }

    public LandscapeGestureHelper() {
        Scheduler.m6112a().m6109a(this, 15);
    }

    /* renamed from: a */
    private void m6180a(Scene[] sceneArr) {
        float m6326j;
        for (int i = 0; i < sceneArr.length; i++) {
            Scene scene = sceneArr[i];
            float m6249b = scene.m6257m().m6249b();
            switch (i) {
                case 0:
                    float m6326j2 = LandscapeData.m6326j() * 0.4f;
                    m6326j = LandscapeData.m6326j() * (-0.5f);
                    if (m6249b <= m6326j2) {
                        m6326j2 = 10000.0f;
                    }
                    if (m6249b >= m6326j) {
                        m6326j = m6326j2;
                        break;
                    }
                    break;
                default:
                    m6326j = 10000.0f;
                    break;
            }
            if (m6326j != 10000.0f) {
                scene.m6264b(new ActionZDecelerateMoveTo(0.4f, m6326j));
            }
        }
    }

    /* renamed from: a */
    private void m6179a(Scene[] sceneArr, float f) {
        for (int i = 0; i < sceneArr.length; i++) {
            Scene scene = sceneArr[i];
            float m6249b = scene.m6257m().m6249b();
            switch (i) {
                case 0:
                    float f2 = m6249b + f;
                    float m6326j = 0.5f * LandscapeData.m6326j();
                    m6249b = LandscapeData.m6326j() * (-1.0f);
                    if (f2 <= m6326j) {
                        m6326j = f2;
                    }
                    if (m6326j >= m6249b) {
                        m6249b = m6326j;
                        break;
                    } else {
                        break;
                    }
            }
            scene.m6257m().m6254a(m6249b);
        }
    }

    /* renamed from: b */
    private void m6177b(Scene[] sceneArr) {
        float f;
        for (int i = 0; i < sceneArr.length; i++) {
            Scene scene = sceneArr[i];
            float m6246c = scene.m6257m().m6246c();
            switch (i) {
                case 0:
                    if (m6246c < 10.0f) {
                        f = 10.0f;
                        break;
                    } else if (m6246c > 20.0f) {
                        f = 20.0f;
                        break;
                    }
                default:
                    f = 10000.0f;
                    break;
            }
            if (f != 10000.0f) {
                scene.m6264b(new ActionXDecelerateRotateTo(0.25f, f));
            }
        }
    }

    /* renamed from: b */
    private void m6176b(Scene[] sceneArr, float f) {
        for (int i = 0; i < sceneArr.length; i++) {
            Scene scene = sceneArr[i];
            float m6246c = scene.m6257m().m6246c();
            switch (i) {
                case 0:
                    m6246c += 0.2f * f;
                    if (m6246c < 0.0f) {
                        m6246c = 0.0f;
                    }
                    if (m6246c > 30.0f) {
                        m6246c = 30.0f;
                        break;
                    } else {
                        break;
                    }
            }
            scene.m6257m().m6248b(m6246c);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        synchronized (this) {
            this.f4544h = true;
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        synchronized (this) {
            this.f4545i = true;
        }
        return true;
    }

    @Override // com.sds.android.ttpod.component.landscape.GestureView.InterfaceC1238d
    /* renamed from: a */
    public void mo6182a(float f, float f2, float f3, float f4) {
        synchronized (this) {
            this.f4537a = (f2 + f4) / 2.0f;
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.GestureView.InterfaceC1235a
    /* renamed from: a */
    public void mo6183a(double d) {
    }

    @Override // com.sds.android.ttpod.component.landscape.GestureView.InterfaceC1236b
    /* renamed from: b */
    public void mo6178b(double d) {
        synchronized (this) {
            this.f4540d = (float) d;
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.GestureView.InterfaceC1237c
    /* renamed from: a */
    public void mo6181a(int i) {
        synchronized (this) {
            switch (i) {
                case 30:
                    this.f4537a = 0.0f;
                    this.f4538b = 0.0f;
                    this.f4540d = 0.0f;
                    this.f4541e = 0.0f;
                    break;
                case 32:
                    this.f4539c = true;
                    this.f4542f = true;
                    this.f4543g = true;
                    break;
                case 33:
                    this.f4539c = false;
                    this.f4542f = false;
                    break;
            }
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.Scheduler.InterfaceC1275a
    /* renamed from: a */
    public void mo6105a(float f) {
        synchronized (this) {
            Scene m6118b = SceneManager.m6121a().m6118b();
            if (m6118b != null && (m6118b instanceof InterfaceC1263d)) {
                float f2 = this.f4537a;
                if (f2 != this.f4538b) {
                    m6176b(((InterfaceC1263d) m6118b).mo6163c_(), f2 - this.f4538b);
                    this.f4538b = f2;
                } else if (this.f4539c) {
                    m6177b(((InterfaceC1263d) m6118b).mo6163c_());
                    this.f4539c = false;
                }
            }
            if (m6118b != null && (m6118b instanceof InterfaceC1264e)) {
                float f3 = this.f4540d;
                if (f3 != this.f4541e) {
                    m6179a(((InterfaceC1264e) m6118b).mo6161c(), f3 - this.f4541e);
                    this.f4541e = f3;
                } else if (this.f4542f) {
                    m6180a(((InterfaceC1264e) m6118b).mo6161c());
                    this.f4542f = false;
                }
            }
            if (m6118b != null && (m6118b instanceof InterfaceC1262c) && this.f4545i) {
                ((InterfaceC1262c) m6118b).mo6165d_();
                this.f4545i = false;
            }
            if (m6118b != null && (m6118b instanceof InterfaceC1261b) && this.f4544h) {
                ((InterfaceC1261b) m6118b).m6168a();
                this.f4544h = false;
            }
            if (m6118b != null && (m6118b instanceof InterfaceC1260a) && this.f4543g) {
                ((InterfaceC1260a) m6118b).m6174a();
                this.f4543g = false;
            }
        }
    }
}
