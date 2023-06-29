package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.a */
/* loaded from: classes.dex */
public class BitmapDrawableCreator extends DrawableCreator {

    /* renamed from: a */
    private Bitmap f6509a;

    /* renamed from: b */
    private int f6510b = -1;

    /* renamed from: c */
    private int f6511c = -1;

    public BitmapDrawableCreator(Bitmap bitmap) {
        this.f6509a = bitmap;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.DrawableCreator
    /* renamed from: a */
    public Drawable getDrawable(Resources resources) {
        Bitmap bitmap = this.f6509a;
        if (bitmap != null) {
            byte[] ninePatchChunk = bitmap.getNinePatchChunk();
            if (ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
                return new NinePatchDrawable(resources, bitmap, ninePatchChunk, NinePatchChunk.m3723a(ninePatchChunk).m3725a(), null);
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
            bitmapDrawable.setTileModeXY(m3760a(this.f6510b), m3760a(this.f6511c));
            return bitmapDrawable;
        }
        return null;
    }

    /* renamed from: a */
    public Bitmap m3761a() {
        return this.f6509a;
    }

    /* renamed from: a */
    public void m3759a(int i, int i2) {
        this.f6510b = i;
        this.f6511c = i2;
    }

    /* renamed from: a */
    private static Shader.TileMode m3760a(int i) {
        switch (i) {
            case 1:
                return Shader.TileMode.CLAMP;
            case 2:
                return Shader.TileMode.REPEAT;
            case 3:
                return Shader.TileMode.MIRROR;
            default:
                return null;
        }
    }
}
