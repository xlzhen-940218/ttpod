package com.sds.android.ttpod.component.landscape.p099c;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.core.internal.view.SupportMenu;
import android.text.TextPaint;
import android.util.TypedValue;
import com.sds.android.ttpod.component.landscape.LandscapeData;
import com.sds.android.ttpod.component.landscape.p100d.Size;
import com.sds.android.ttpod.component.landscape.p100d.SizeF;

/* renamed from: com.sds.android.ttpod.component.landscape.c.d */
/* loaded from: classes.dex */
public class TextureLyric extends Texture {

    /* renamed from: g */
    private float f4552g;

    /* renamed from: h */
    private TextPaint f4553h;

    public TextureLyric(String str) {
        super(str);
        this.f4553h = new TextPaint();
        this.f4553h.setTextAlign(Paint.Align.LEFT);
        this.f4553h.setAntiAlias(true);
        this.f4553h.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, LandscapeData.m6332d(), Resources.getSystem().getDisplayMetrics()));
        this.f4553h.setColor(-1);
        this.f4553h.setShadowLayer(3.0f, 0.0f, 0.0f, SupportMenu.CATEGORY_MASK);
        this.f4553h.setTypeface(Typeface.DEFAULT_BOLD);
        Paint.FontMetrics fontMetrics = this.f4553h.getFontMetrics();
        float f = fontMetrics.bottom - fontMetrics.top;
        this.f4549d = new Size(1024, m6173a((int) f));
        this.f4548c = new SizeF(0.0f, f);
        this.f4551f = this.f4548c.m6138b() / this.f4549d.m6143b();
        this.f4552g = -fontMetrics.top;
    }

    /* renamed from: a */
    public void m6164a(String str) {
        Bitmap createBitmap = Bitmap.createBitmap(this.f4549d.m6146a(), this.f4549d.m6143b(), Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        this.f4548c.m6140a(this.f4553h.measureText(str));
        this.f4550e = this.f4548c.m6141a() / this.f4549d.m6146a();
        createBitmap.eraseColor(0);
        canvas.drawText(str, 0.0f, this.f4552g, this.f4553h);
        m6172a(createBitmap, true);
    }
}
