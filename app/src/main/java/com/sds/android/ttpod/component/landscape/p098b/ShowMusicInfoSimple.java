package com.sds.android.ttpod.component.landscape.p098b;

import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.p099c.TextureMusicInfo;
import com.sds.android.ttpod.component.landscape.p100d.SizeF;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* renamed from: com.sds.android.ttpod.component.landscape.b.j */
/* loaded from: classes.dex */
public class ShowMusicInfoSimple extends Scene {

    /* renamed from: a */
    private FloatBuffer f4513a;

    /* renamed from: b */
    private FloatBuffer f4514b;

    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
        this.f4513a.clear();
        this.f4514b.clear();
    }

    public ShowMusicInfoSimple() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f4513a = allocateDirect.asFloatBuffer();
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(32);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.f4514b = allocateDirect2.asFloatBuffer();
        this.f4497l = new TextureMusicInfo("ShowMusicInfoSimple");
    }

    /* renamed from: a */
    public void m6223a(String str, String str2) {
        ((TextureMusicInfo) this.f4497l).m6162a(str, str2);
        SizeF m6171b = this.f4497l.m6171b();
        float m6141a = m6171b.m6141a() / 2.0f;
        float m6138b = m6171b.m6138b() / 2.0f;
        this.f4513a.put(new float[]{-m6141a, -m6138b, m6141a, -m6138b, -m6141a, m6138b, m6141a, m6138b});
        this.f4513a.position(0);
        this.f4514b.put(new float[]{0.0f, this.f4497l.m6169d(), this.f4497l.m6170c(), this.f4497l.m6169d(), 0.0f, 0.0f, this.f4497l.m6170c(), 0.0f});
        this.f4514b.position(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.landscape.p098b.Scene
    /* renamed from: b */
    public void mo6185b() {
        super.mo6185b();
        GLES10.glBindTexture(3553, this.f4497l.m6175a());
        GLES10.glColor4f(this.f4498m.m6231h(), this.f4498m.m6228i(), this.f4498m.m6226j(), this.f4498m.m6224k());
        GLES10.glVertexPointer(2, 5126, 0, this.f4513a);
        GLES10.glTexCoordPointer(2, 5126, 0, this.f4514b);
        GLES10.glDrawArrays(5, 0, 4);
    }
}
