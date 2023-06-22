package com.sds.android.ttpod.component.landscape.p099c;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.sds.android.ttpod.component.landscape.p100d.Size;
import com.sds.android.ttpod.component.landscape.p100d.SizeF;

/* renamed from: com.sds.android.ttpod.component.landscape.c.b */
/* loaded from: classes.dex */
public class Texture2D extends Texture {
    public Texture2D(String str) {
        super(str);
        this.f4547b = 0;
        this.f4548c = new SizeF();
        this.f4549d = new Size();
        this.f4550e = 0.0f;
        this.f4551f = 0.0f;
    }

    /* renamed from: b */
    public void m6167b(Bitmap bitmap, boolean z) {
        Bitmap createBitmap;
        if (bitmap == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int a = m6173a(width);
        int a2 = m6173a(height);
        if (width != a || height != a2) {
            try {
                try {
                    createBitmap = Bitmap.createBitmap(a, a2, Bitmap.Config.ARGB_8888);
                } catch (Exception e) {
                    if (z) {
                        bitmap.recycle();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (z) {
                        bitmap.recycle();
                    }
                    throw th;
                }
            } catch (OutOfMemoryError e2) {
                try {
                    createBitmap = Bitmap.createBitmap(a, a2, Bitmap.Config.RGB_565);
                } catch (OutOfMemoryError e3) {
                    if (z) {
                        bitmap.recycle();
                        return;
                    }
                    return;
                }
            }
            createBitmap.eraseColor(0);
            createBitmap.setDensity(0);
            Canvas canvas = new Canvas(createBitmap);
            canvas.setDensity(0);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            if (z) {
                bitmap.recycle();
            }
            z = true;
            bitmap = createBitmap;
        }
        m6172a(bitmap, z);
        this.f4548c.m6140a(width);
        this.f4548c.m6137b(height);
        this.f4549d.m6145a(a);
        this.f4549d.m6142b(a2);
        this.f4550e = width / a;
        this.f4551f = height / a2;
    }
}
