package com.sds.android.ttpod.component.landscape.p099c;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.core.internal.view.SupportMenu;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.sds.android.ttpod.component.landscape.LandscapeData;
import com.sds.android.ttpod.component.landscape.p100d.Size;
import com.sds.android.ttpod.component.landscape.p100d.SizeF;

/* renamed from: com.sds.android.ttpod.component.landscape.c.e */
/* loaded from: classes.dex */
public class TextureMusicInfo extends Texture {

    /* renamed from: g */
    private float f4554g;

    /* renamed from: h */
    private float f4555h;

    /* renamed from: i */
    private TextPaint f4556i;

    /* renamed from: j */
    private TextPaint f4557j;

    public TextureMusicInfo(String str) {
        super(str);
        this.f4556i = new TextPaint();
        this.f4556i.setTextAlign(Paint.Align.LEFT);
        this.f4556i.setAntiAlias(true);
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        this.f4556i.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, LandscapeData.m6331e(), displayMetrics));
        this.f4556i.setColor(-1);
        this.f4556i.setShadowLayer(2.0f, 0.0f, 0.0f, SupportMenu.CATEGORY_MASK);
        this.f4556i.setTypeface(Typeface.DEFAULT);
        Paint.FontMetrics fontMetrics = this.f4556i.getFontMetrics();
        this.f4554g = 0.0f - fontMetrics.top;
        float f = fontMetrics.bottom - fontMetrics.top;
        float m6329g = LandscapeData.m6329g() * LandscapeData.m6337a();
        this.f4557j = new TextPaint();
        this.f4557j.setTextAlign(Paint.Align.LEFT);
        this.f4557j.setAntiAlias(true);
        this.f4557j.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, LandscapeData.m6330f(), displayMetrics));
        this.f4557j.setColor(-1);
        this.f4557j.setShadowLayer(2.0f, 0.0f, 0.0f, SupportMenu.CATEGORY_MASK);
        this.f4557j.setTypeface(Typeface.DEFAULT);
        Paint.FontMetrics fontMetrics2 = this.f4557j.getFontMetrics();
        this.f4555h = (0.0f - fontMetrics2.top) + m6329g + f;
        float f2 = fontMetrics2.bottom - fontMetrics2.top;
        this.f4548c = new SizeF();
        this.f4548c.m6137b(f2 + f + m6329g);
        this.f4549d = new Size();
        this.f4549d.m6142b(m6173a((int) this.f4548c.m6138b()));
        this.f4551f = this.f4548c.m6138b() / this.f4549d.m6143b();
        if (this.f4551f > 1.0f) {
            this.f4551f = 1.0f;
        }
        m6162a("", "");
    }

    /* renamed from: a */
    public void m6162a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        float measureText = this.f4556i.measureText(str);
        float measureText2 = this.f4557j.measureText(str2);
        this.f4548c.m6140a(Math.max(measureText, measureText2));
        if (this.f4548c.m6141a() == 0.0f) {
            this.f4548c.m6140a(1.0f);
        }
        this.f4549d.m6145a(m6173a((int) this.f4548c.m6141a()));
        this.f4550e = this.f4548c.m6141a() / this.f4549d.m6146a();
        if (this.f4550e > 1.0f) {
            this.f4550e = 1.0f;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.f4549d.m6146a(), this.f4549d.m6143b(), Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawText(str, (this.f4548c.m6141a() - measureText) / 2.0f, this.f4554g, this.f4556i);
        canvas.drawText(str2, (this.f4548c.m6141a() - measureText2) / 2.0f, this.f4555h, this.f4557j);
        m6172a(createBitmap, true);
    }
}
