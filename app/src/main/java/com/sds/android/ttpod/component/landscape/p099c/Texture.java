package com.sds.android.ttpod.component.landscape.p099c;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import android.opengl.GLUtils;
import com.sds.android.ttpod.component.landscape.p100d.Size;
import com.sds.android.ttpod.component.landscape.p100d.SizeF;

/* renamed from: com.sds.android.ttpod.component.landscape.c.a */
/* loaded from: classes.dex */
public abstract class Texture {

    /* renamed from: a */
    protected String f4546a;

    /* renamed from: b */
    protected int f4547b;

    /* renamed from: c */
    protected SizeF f4548c;

    /* renamed from: d */
    protected Size f4549d;

    /* renamed from: e */
    protected float f4550e;

    /* renamed from: f */
    protected float f4551f;

    public Texture(String str) {
        this.f4546a = str;
    }

    /* renamed from: a */
    public int m6175a() {
        return this.f4547b;
    }

    /* renamed from: b */
    public SizeF m6171b() {
        return this.f4548c;
    }

    /* renamed from: c */
    public float m6170c() {
        return this.f4550e;
    }

    /* renamed from: d */
    public float m6169d() {
        return this.f4551f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m6172a(Bitmap bitmap, boolean z) {
        int[] iArr = new int[1];
        if (this.f4547b != 0) {
            iArr[0] = this.f4547b;
            GLES10.glDeleteTextures(1, iArr, 0);
        }
        GLES10.glGenTextures(1, iArr, 0);
        this.f4547b = iArr[0];
        GLES10.glBindTexture(3553, this.f4547b);
        GLES10.glTexParameterf(3553, 10241, 9729.0f);
        GLES10.glTexParameterf(3553, 10240, 9729.0f);
        GLES10.glTexParameterf(3553, 10242, 10497.0f);
        GLES10.glTexParameterf(3553, 10243, 10497.0f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        if (z) {
            bitmap.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public int m6173a(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        int i7 = (i6 | (i6 >> 16)) + 1;
        if (i7 < 1) {
            return 1;
        }
        return i7;
    }
}
