package com.sds.android.ttpod.component.landscape;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import android.os.Handler;
import com.sds.android.ttpod.component.landscape.Scheduler;
import java.nio.IntBuffer;

/* renamed from: com.sds.android.ttpod.component.landscape.f */
/* loaded from: classes.dex */
public class LandscapeScreenCaptureHelper implements Scheduler.InterfaceC1275a {

    /* renamed from: a */
    private InterfaceC1271a f4579a;

    /* renamed from: b */
    private Handler f4580b;

    /* renamed from: c */
    private boolean f4581c;

    /* renamed from: d */
    private int f4582d;

    /* renamed from: e */
    private int f4583e;

    /* compiled from: LandscapeScreenCaptureHelper.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.f$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1271a {
        /* renamed from: a */
        void mo6131a(Bitmap bitmap);
    }

    public LandscapeScreenCaptureHelper(InterfaceC1271a interfaceC1271a) {
        if (interfaceC1271a != null) {
            this.f4579a = interfaceC1271a;
            this.f4580b = new Handler();
            Scheduler.m6112a().m6109a(this, 20);
            this.f4582d = LandscapeData.m6334b();
            this.f4583e = LandscapeData.m6333c();
        }
    }

    /* renamed from: a */
    public void m6134a() {
        this.f4581c = true;
    }

    /* renamed from: b */
    private void m6132b() {
        int[] iArr = new int[this.f4582d * this.f4583e];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        GLES10.glReadPixels(0, 0, this.f4582d, this.f4583e, 6408, 5121, wrap);
        int[] iArr2 = new int[this.f4582d * this.f4583e];
        for (int i = 0; i < this.f4583e; i++) {
            for (int i2 = 0; i2 < this.f4582d; i2++) {
                int i3 = iArr[(this.f4582d * i) + i2];
                iArr2[(((this.f4583e - 1) - i) * this.f4582d) + i2] = (i3 & (-16711936)) | ((i3 << 16) & 16711680) | ((i3 >> 16) & 255);
            }
        }
        final Bitmap createBitmap = Bitmap.createBitmap(iArr2, this.f4582d, this.f4583e, Bitmap.Config.ARGB_8888);
        this.f4580b.post(new Runnable() { // from class: com.sds.android.ttpod.component.landscape.f.1
            @Override // java.lang.Runnable
            public void run() {
                LandscapeScreenCaptureHelper.this.f4579a.mo6131a(createBitmap);
            }
        });
    }

    @Override // com.sds.android.ttpod.component.landscape.Scheduler.InterfaceC1275a
    /* renamed from: a */
    public void mo6105a(float f) {
        if (this.f4581c) {
            m6132b();
            this.f4581c = false;
        }
    }
}
