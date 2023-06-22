package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.p106a.C1780b;
import com.sds.android.ttpod.utils.ThemeUtils;

/* loaded from: classes.dex */
public class GlobalMenuThumbImageView extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    private Bitmap f7683a;

    /* renamed from: b */
    private float f7684b;

    /* renamed from: c */
    private PorterDuffXfermode f7685c;

    /* renamed from: d */
    private Paint f7686d;

    /* renamed from: e */
    private Drawable f7687e;

    public GlobalMenuThumbImageView(Context context) {
        super(context);
        this.f7685c = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.f7686d = new Paint(1);
    }

    public GlobalMenuThumbImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7685c = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.f7686d = new Paint(1);
    }

    public GlobalMenuThumbImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7685c = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.f7686d = new Paint(1);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f7683a = null;
    }

    public void setThumbDrawable(Drawable drawable) {
        this.f7687e = drawable;
        this.f7683a = null;
        invalidate();
    }

    /* renamed from: b */
    private void m1741b() {
        int intrinsicHeight = this.f7687e.getIntrinsicHeight();
        int height = getHeight();
        if (intrinsicHeight == height && (this.f7687e instanceof BitmapDrawable)) {
            this.f7683a = ((BitmapDrawable) this.f7687e).getBitmap();
        } else {
            float f = (height * 1.0f) / intrinsicHeight;
            this.f7683a = C1780b.m4781a(this.f7687e, (int) (this.f7687e.getIntrinsicWidth() * f), (int) (intrinsicHeight * f));
        }
        this.f7686d.setShader(new BitmapShader(this.f7683a, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.f7687e == null) {
            super.onDraw(canvas);
            return;
        }
        if (this.f7683a == null) {
            m1741b();
        }
        int height = getHeight();
        int saveLayer = canvas.saveLayer(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), height - getPaddingBottom(), null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        int width = this.f7683a.getWidth();
        int width2 = getWidth() >> 1;
        int i = (((int) (width2 * (1.0f - this.f7684b))) + (width2 >> 1)) - (width >> 1);
        this.f7686d.setXfermode(this.f7685c);
        canvas.translate(i, 0.0f);
        canvas.drawRect(0.0f, 0.0f, width, height, this.f7686d);
        this.f7686d.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    public void setThumbOffset(float f) {
        this.f7684b = f;
        invalidate();
    }

    /* renamed from: a */
    public void m1742a() {
        setImageDrawable(ThemeUtils.m8165a(ThemeElement.SETTING_MENU_INDICATOR_BACKGROUND_IMAGE, (int) R.drawable.img_menu_indicator_background));
        setThumbDrawable(getResources().getDrawable(R.drawable.img_menu_indicator_thumb));
    }
}
