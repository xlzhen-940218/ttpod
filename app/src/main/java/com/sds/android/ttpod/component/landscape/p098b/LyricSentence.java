package com.sds.android.ttpod.component.landscape.p098b;

import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.p099c.TextureLyric;
import com.sds.android.ttpod.component.landscape.p100d.SizeF;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* renamed from: com.sds.android.ttpod.component.landscape.b.c */
/* loaded from: classes.dex */
public class LyricSentence extends Scene {

    /* renamed from: a */
    private FloatBuffer f4450a;

    /* renamed from: b */
    private FloatBuffer f4451b;

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
        this.f4450a.clear();
        this.f4451b.clear();
    }

    public LyricSentence() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f4450a = allocateDirect.asFloatBuffer();
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(32);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.f4451b = allocateDirect2.asFloatBuffer();
        this.f4497l = new TextureLyric("LyricSentence");
        m6282a("");
    }

    /* renamed from: a */
    public void m6282a(String str) {
        ((TextureLyric) this.f4497l).m6164a(str);
        SizeF m6171b = this.f4497l.m6171b();
        float m6141a = m6171b.m6141a() / 2.0f;
        float m6138b = m6171b.m6138b() / 2.0f;
        this.f4450a.put(new float[]{-m6141a, -m6138b, m6141a, -m6138b, -m6141a, m6138b, m6141a, m6138b});
        this.f4450a.position(0);
        this.f4451b.put(new float[]{0.0f, this.f4497l.m6169d(), this.f4497l.m6170c(), this.f4497l.m6169d(), 0.0f, 0.0f, this.f4497l.m6170c(), 0.0f});
        this.f4451b.position(0);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene
    /* renamed from: b */
    public void mo6185b() {
        super.mo6185b();
        GLES10.glBindTexture(3553, this.f4497l.m6175a());
        GLES10.glVertexPointer(2, 5126, 0, this.f4450a);
        GLES10.glTexCoordPointer(2, 5126, 0, this.f4451b);
        GLES10.glColor4f(this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k());
        GLES10.glDrawArrays(5, 0, 4);
    }
}
