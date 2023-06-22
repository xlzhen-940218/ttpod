package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.opengl.GLSurfaceView;
import com.sds.android.ttpod.component.landscape.p098b.MainScene;
import com.sds.android.ttpod.component.landscape.temporary.ShowFrame;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.sds.android.ttpod.component.landscape.e */
/* loaded from: classes.dex */
public class LandscapeRenderer implements GLSurfaceView.Renderer {

    /* renamed from: a */
    private Context f4578a;

    public LandscapeRenderer(Context context) {
        this.f4578a = context;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        ShowFrame.m6094a().m6091d();
        SceneManager.m6121a().m6117c();
        SceneManager.m6121a().m6120a(new MainScene(this.f4578a));
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        ShowFrame.m6094a().m6090e();
        SceneManager.m6121a().m6116d();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        SceneManager.m6121a().m6119a(gl10, i, i2);
    }
}
