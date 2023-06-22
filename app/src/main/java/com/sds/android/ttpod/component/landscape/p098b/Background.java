package com.sds.android.ttpod.component.landscape.p098b;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.p099c.Texture2D;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* renamed from: com.sds.android.ttpod.component.landscape.b.a */
/* loaded from: classes.dex */
public class Background extends Scene {

    /* renamed from: a */
    private FloatBuffer f4390a;

    /* renamed from: b */
    private FloatBuffer f4391b;

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
        this.f4390a.clear();
        this.f4391b.clear();
    }

    public Background() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f4390a = allocateDirect.asFloatBuffer();
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(32);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.f4391b = allocateDirect2.asFloatBuffer();
        this.f4497l = new Texture2D("Background");
    }

    /* renamed from: a */
    public void m6324a(Bitmap bitmap) {
        ((Texture2D) this.f4497l).m6167b(bitmap, true);
        this.f4391b.clear();
        this.f4391b.position(0);
        this.f4391b.put(new float[]{0.0f, this.f4497l.m6169d(), this.f4497l.m6170c(), this.f4497l.m6169d(), 0.0f, 0.0f, this.f4497l.m6170c(), 0.0f});
        this.f4391b.position(0);
    }

    /* renamed from: a */
    public void m6325a(int i, int i2) {
        this.f4390a.clear();
        this.f4390a.position(0);
        this.f4390a.put(new float[]{0.0f, 0.0f, i, 0.0f, 0.0f, i2, i, i2});
        this.f4390a.position(0);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene
    /* renamed from: b */
    public void mo6185b() {
        super.mo6185b();
        GLES10.glBindTexture(3553, this.f4497l.m6175a());
        GLES10.glColor4f(this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k());
        GLES10.glVertexPointer(2, 5126, 0, this.f4390a);
        GLES10.glTexCoordPointer(2, 5126, 0, this.f4391b);
        GLES10.glDrawArrays(5, 0, 4);
        GLES10.glBlendFunc(770, 1);
        GLES10.glEnableClientState(32886);
    }
}
