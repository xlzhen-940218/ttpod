package com.sds.android.ttpod.component.landscape.p099c;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.sds.android.ttpod.component.landscape.p100d.Size;
import com.sds.android.ttpod.component.landscape.p100d.SizeF;

/* renamed from: com.sds.android.ttpod.component.landscape.c.c */
/* loaded from: classes.dex */
public class TextureArtist extends Texture {
    public TextureArtist(String str) {
        super(str);
        this.f4548c = new SizeF();
        this.f4549d = new Size();
    }

    /* renamed from: a */
    public void m6166a(Bitmap bitmap) {
        Rect rect;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            int i = (width - height) / 2;
            rect = new Rect(i, 0, width - i, height);
        } else if (width < height) {
            rect = new Rect(0, 0, width, width);
        } else {
            rect = new Rect(0, 0, height, height);
        }
        int i2 = rect.right - rect.left;
        int a = m6173a(i2);
        this.f4548c.m6140a(i2);
        this.f4548c.m6137b(i2);
        this.f4549d.m6145a(a);
        this.f4549d.m6142b(a);
        this.f4550e = i2 / a;
        this.f4551f = this.f4550e;
        Bitmap createBitmap = Bitmap.createBitmap(a, a, Bitmap.Config.ARGB_8888);
        createBitmap.setDensity(0);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setDensity(0);
        canvas.drawBitmap(bitmap, rect, new Rect(0, 0, i2, i2), (Paint) null);
        m6172a(createBitmap, true);
    }
}
