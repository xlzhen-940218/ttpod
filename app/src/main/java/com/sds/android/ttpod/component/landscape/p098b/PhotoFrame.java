package com.sds.android.ttpod.component.landscape.p098b;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.p099c.TextureArtist;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* renamed from: com.sds.android.ttpod.component.landscape.b.h */
/* loaded from: classes.dex */
public class PhotoFrame extends Scene {

    /* renamed from: a */
    private FloatBuffer f4492a;

    /* renamed from: b */
    private FloatBuffer f4493b;

    /* renamed from: c */
    private FloatBuffer f4494c;

    /* renamed from: d */
    private FloatBuffer f4495d;

    /* renamed from: g */
    private ShortBuffer f4496g;

    public PhotoFrame() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(64);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f4492a = allocateDirect.asFloatBuffer();
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(128);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.f4493b = allocateDirect2.asFloatBuffer();
        ByteBuffer allocateDirect3 = ByteBuffer.allocateDirect(128);
        allocateDirect3.order(ByteOrder.nativeOrder());
        this.f4494c = allocateDirect3.asFloatBuffer();
        ByteBuffer allocateDirect4 = ByteBuffer.allocateDirect(64);
        allocateDirect4.order(ByteOrder.nativeOrder());
        this.f4495d = allocateDirect4.asFloatBuffer();
        ByteBuffer allocateDirect5 = ByteBuffer.allocateDirect(24);
        allocateDirect5.order(ByteOrder.nativeOrder());
        this.f4496g = allocateDirect5.asShortBuffer();
        this.f4496g.put(new short[]{0, 1, 2, 2, 1, 3, 4, 5, 6, 6, 5, 7});
        this.f4496g.position(0);
        this.f4493b.put(new float[]{this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), 0.0f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), 0.0f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k() * 0.5f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k() * 0.5f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k(), this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k(), this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k(), this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k()});
        this.f4493b.position(0);
        this.f4494c.put(new float[]{this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), 0.0f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), 0.0f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k() * 0.3f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k() * 0.3f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k() * 0.3f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k() * 0.3f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k() * 0.3f, this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k() * 0.3f});
        this.f4494c.position(0);
        this.f4497l = new TextureArtist("PhotoFrame");
    }

    /* renamed from: a */
    public void m6267a(Bitmap bitmap) {
        ((TextureArtist) this.f4497l).m6166a(bitmap);
        this.f4495d.clear();
        this.f4495d.position(0);
        this.f4495d.put(new float[]{0.0f, this.f4497l.m6169d() * 0.9f, this.f4497l.m6170c(), this.f4497l.m6169d() * 0.9f, 0.0f, this.f4497l.m6169d(), this.f4497l.m6170c(), this.f4497l.m6169d(), 0.0f, this.f4497l.m6169d(), this.f4497l.m6170c(), this.f4497l.m6169d(), 0.0f, 0.0f, this.f4497l.m6170c(), 0.0f});
        this.f4495d.position(0);
    }

    /* renamed from: b */
    public void m6266b(float f) {
        float f2 = f / 2.0f;
        this.f4492a.clear();
        this.f4492a.position(0);
        this.f4492a.put(new float[]{-f2, (-f) * 0.1f, f2, (-f) * 0.1f, -f2, 0.0f, f2, 0.0f, -f2, 0.0f, f2, 0.0f, -f2, f, f2, f});
        this.f4492a.position(0);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene
    /* renamed from: b */
    public void mo6185b() {
        super.mo6185b();
        GLES10.glEnable(2929);
        GLES10.glEnable(32925);
        GLES10.glEnableClientState(32886);
        GLES10.glBindTexture(3553, this.f4497l.m6175a());
        GLES10.glVertexPointer(2, 5126, 0, this.f4492a);
        GLES10.glColorPointer(4, 5126, 0, this.f4494c);
        GLES10.glTexCoordPointer(2, 5126, 0, this.f4495d);
        GLES10.glPushMatrix();
        GLES10.glTranslatef(0.0f, 0.0f, -100.0f);
        GLES10.glScalef(2.0f, 2.0f, 1.0f);
        GLES10.glDrawElements(4, 12, 5123, this.f4496g);
        GLES10.glPopMatrix();
        GLES10.glColorPointer(4, 5126, 0, this.f4493b);
        GLES10.glDrawElements(4, 12, 5123, this.f4496g);
        GLES10.glDisable(2929);
        GLES10.glDisable(32925);
        GLES10.glDisableClientState(32886);
    }
}
