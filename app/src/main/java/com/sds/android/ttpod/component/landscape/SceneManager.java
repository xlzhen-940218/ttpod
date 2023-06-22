package com.sds.android.ttpod.component.landscape;

import android.opengl.GLES10;
import android.opengl.GLU;
import com.sds.android.ttpod.component.landscape.p098b.Scene;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.sds.android.ttpod.component.landscape.h */
/* loaded from: classes.dex */
public final class SceneManager {

    /* renamed from: a */
    private static SceneManager f4595a = null;

    /* renamed from: b */
    private Scene f4596b;

    /* renamed from: c */
    private long f4597c;

    /* renamed from: d */
    private boolean f4598d = true;

    /* renamed from: a */
    public static SceneManager m6121a() {
        if (f4595a == null) {
            f4595a = new SceneManager();
        }
        return f4595a;
    }

    private SceneManager() {
    }

    /* renamed from: b */
    public Scene m6118b() {
        return this.f4596b;
    }

    /* renamed from: c */
    public void m6117c() {
        GLES10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES10.glEnable(3553);
        GLES10.glEnable(3042);
        GLES10.glBlendFunc(770, 771);
        GLES10.glEnableClientState(32884);
        GLES10.glEnableClientState(32888);
    }

    /* renamed from: a */
    public void m6119a(GL10 gl10, int i, int i2) {
        GLES10.glViewport(0, 0, i, i2);
        GLES10.glMatrixMode(5889);
        GLES10.glLoadIdentity();
        GLU.gluPerspective(gl10, 60.0f, i / i2, 100.0f, 5000.0f);
        GLES10.glMatrixMode(5888);
        GLES10.glLoadIdentity();
        float f = i * 0.5f;
        float f2 = i2 * 0.5f;
        float tan = (float) ((i2 * 0.5f) / Math.tan(Math.toRadians(30.0d)));
        LandscapeData.m6336a(tan);
        GLU.gluLookAt(gl10, f, f2, tan, f, f2, 0.0f, 0.0f, 1.0f, 0.0f);
        if (this.f4596b instanceof SizeChange) {
            ((SizeChange) this.f4596b).mo6100a(i, i2);
        }
    }

    /* renamed from: d */
    public void m6116d() {
        Scheduler.m6112a().m6111a(m6113g());
        GLES10.glClear(16640);
        GLES10.glPushMatrix();
        if (this.f4596b != null) {
            this.f4596b.mo6258j();
        }
        GLES10.glPopMatrix();
    }

    /* renamed from: g */
    private float m6113g() {
        float f = 0.0f;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f4598d) {
            this.f4598d = false;
        } else {
            f = Math.max(0.0f, ((float) (currentTimeMillis - this.f4597c)) * 0.001f);
        }
        this.f4597c = currentTimeMillis;
        return f;
    }

    /* renamed from: e */
    public void m6115e() {
        this.f4598d = true;
    }

    /* renamed from: f */
    public void m6114f() {
        if (this.f4596b != null) {
            this.f4596b.mo6188a();
        }
        ActionManager.m6371a().m6368b();
        Scheduler.m6112a().m6107b();
        f4595a = null;
    }

    /* renamed from: a */
    public void m6120a(Scene scene) {
        this.f4596b = scene;
    }
}
