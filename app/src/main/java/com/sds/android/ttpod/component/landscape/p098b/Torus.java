package com.sds.android.ttpod.component.landscape.p098b;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.p099c.Texture2D;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* renamed from: com.sds.android.ttpod.component.landscape.b.l */
/* loaded from: classes.dex */
public class Torus extends Scene {

    /* renamed from: a */
    private FloatBuffer f4535a;

    /* renamed from: b */
    private FloatBuffer f4536b;

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
        this.f4535a.clear();
        this.f4536b.clear();
    }

    public Torus() {
        this.f4497l = new Texture2D("Torus");
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(5776);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f4535a = allocateDirect.asFloatBuffer();
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(5776);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.f4536b = allocateDirect2.asFloatBuffer();
    }

    /* renamed from: a */
    public void m6187a(int i) {
        float f = 0.03f * i;
        float f2 = f * 1.5f;
        this.f4535a.clear();
        this.f4535a.position(0);
        for (int i2 = 0; i2 <= 360; i2++) {
            float cos = (float) Math.cos(i2 * 0.01745329f);
            float sin = (float) Math.sin(i2 * 0.01745329f);
            this.f4535a.put(f * cos);
            this.f4535a.put(f * sin);
            this.f4535a.put(cos * f2);
            this.f4535a.put(sin * f2);
        }
        this.f4535a.position(0);
    }

    /* renamed from: a */
    public void m6186a(Bitmap bitmap) {
        ((Texture2D) this.f4497l).m6167b(bitmap, true);
        float m6170c = this.f4497l.m6170c();
        float m6169d = this.f4497l.m6169d();
        float f = m6169d / 360.0f;
        this.f4536b.clear();
        this.f4536b.position(0);
        float f2 = m6169d;
        for (int i = 0; i <= 360; i++) {
            this.f4536b.put(0.0f);
            this.f4536b.put(f2);
            this.f4536b.put(m6170c);
            this.f4536b.put(f2);
            f2 = Math.max(0.0f, f2 - f);
        }
        this.f4536b.position(0);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene
    /* renamed from: b */
    public void mo6185b() {
        super.mo6185b();
        GLES10.glDisableClientState(32886);
        GLES10.glBlendFunc(770, 771);
        GLES10.glBindTexture(3553, this.f4497l.m6175a());
        GLES10.glVertexPointer(2, 5126, 0, this.f4535a);
        GLES10.glTexCoordPointer(2, 5126, 0, this.f4536b);
        GLES10.glColor4f(this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k());
        GLES10.glDrawArrays(5, 0, 722);
    }

    /* renamed from: b */
    public void m6184b(float f) {
        if (f <= 0.0f) {
            f = 0.0f;
        }
        this.f4498m.m6233g((0.2f * f) + 2.0f);
    }
}
