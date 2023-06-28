package com.sds.android.ttpod.widget;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.view.ViewCompat;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.LogUtils;

/* renamed from: com.sds.android.ttpod.widget.g */
/* loaded from: classes.dex */
public class RoundedDrawable extends Drawable {

    /* renamed from: d */
    private final BitmapShader f8357d;

    /* renamed from: e */
    private final Paint f8358e;

    /* renamed from: f */
    private final int f8359f;

    /* renamed from: g */
    private final int f8360g;

    /* renamed from: i */
    private final Paint strokePaint;

    /* renamed from: a */
    private final RectF f8354a = new RectF();

    /* renamed from: b */
    private final RectF f8355b = new RectF();

    /* renamed from: c */
    private final RectF f8356c = new RectF();

    /* renamed from: h */
    private final RectF f8361h = new RectF();

    /* renamed from: j */
    private final Matrix f8363j = new Matrix();

    /* renamed from: k */
    private float cornerRadius = 0.0f;

    /* renamed from: l */
    private boolean oval = false;

    /* renamed from: m */
    private float imageBorderWidth = 0.0f;

    /* renamed from: n */
    private ColorStateList imageBorderColor = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);

    /* renamed from: o */
    private ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;

    public RoundedDrawable(Bitmap bitmap) {
        this.f8359f = bitmap.getWidth();
        this.f8360g = bitmap.getHeight();
        this.f8356c.set(0.0f, 0.0f, this.f8359f, this.f8360g);
        this.f8357d = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.f8357d.setLocalMatrix(this.f8363j);
        this.f8358e = new Paint();
        this.f8358e.setStyle(Paint.Style.FILL);
        this.f8358e.setAntiAlias(true);
        this.f8358e.setFilterBitmap(true);
        this.f8358e.setShader(this.f8357d);
        this.strokePaint = new Paint();
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setAntiAlias(true);
        this.strokePaint.setColor(this.imageBorderColor.getColorForState(getState(), ViewCompat.MEASURED_STATE_MASK));
        this.strokePaint.setStrokeWidth(this.imageBorderWidth);
    }

    /* renamed from: a */
    public static RoundedDrawable m1243a(Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        }
        return null;
    }

    /* renamed from: a */
    public static Drawable m1242a(Drawable drawable) {
        if (drawable != null && !(drawable instanceof RoundedDrawable)) {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), m1242a(layerDrawable.getDrawable(i)));
                }
                return layerDrawable;
            }
            Bitmap m1237b = m1237b(drawable);
            if (m1237b != null) {
                return new RoundedDrawable(m1237b);
            }
            LogUtils.warning("RoundedDrawable", "Failed to create bitmap from drawable!");
            return drawable;
        }
        return drawable;
    }

    /* renamed from: b */
    public static Bitmap m1237b(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 1), Math.max(drawable.getIntrinsicHeight(), 1), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.imageBorderColor.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int colorForState = this.imageBorderColor.getColorForState(iArr, 0);
        if (this.strokePaint.getColor() != colorForState) {
            this.strokePaint.setColor(colorForState);
            return true;
        }
        return super.onStateChange(iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RoundedDrawable.java */
    /* renamed from: com.sds.android.ttpod.widget.g$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C22861 {

        /* renamed from: a */
        static final /* synthetic */ int[] f8369a = new int[ImageView.ScaleType.values().length];

        static {
            try {
                f8369a[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8369a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8369a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8369a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f8369a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f8369a[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f8369a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* renamed from: b */
    private void m1239b() {
        float min;
        float width;
        float f;
        float f2 = 0.0f;
        switch (C22861.f8369a[this.scaleType.ordinal()]) {
            case 1:
                this.f8361h.set(this.f8354a);
                this.f8361h.inset(this.imageBorderWidth / 2.0f, this.imageBorderWidth / 2.0f);
                this.f8363j.reset();
                this.f8363j.setTranslate((int) (((this.f8361h.width() - this.f8359f) * 0.5f) + 0.5f), (int) (((this.f8361h.height() - this.f8360g) * 0.5f) + 0.5f));
                break;
            case 2:
                this.f8361h.set(this.f8354a);
                this.f8361h.inset(this.imageBorderWidth / 2.0f, this.imageBorderWidth / 2.0f);
                this.f8363j.reset();
                if (this.f8359f * this.f8361h.height() > this.f8361h.width() * this.f8360g) {
                    width = this.f8361h.height() / this.f8360g;
                    f = (this.f8361h.width() - (this.f8359f * width)) * 0.5f;
                } else {
                    width = this.f8361h.width() / this.f8359f;
                    f = 0.0f;
                    f2 = (this.f8361h.height() - (this.f8360g * width)) * 0.5f;
                }
                this.f8363j.setScale(width, width);
                this.f8363j.postTranslate(((int) (f + 0.5f)) + this.imageBorderWidth, ((int) (f2 + 0.5f)) + this.imageBorderWidth);
                break;
            case 3:
                this.f8363j.reset();
                if (this.f8359f <= this.f8354a.width() && this.f8360g <= this.f8354a.height()) {
                    min = 1.0f;
                } else {
                    min = Math.min(this.f8354a.width() / this.f8359f, this.f8354a.height() / this.f8360g);
                }
                this.f8363j.setScale(min, min);
                this.f8363j.postTranslate((int) (((this.f8354a.width() - (this.f8359f * min)) * 0.5f) + 0.5f), (int) (((this.f8354a.height() - (this.f8360g * min)) * 0.5f) + 0.5f));
                this.f8361h.set(this.f8356c);
                this.f8363j.mapRect(this.f8361h);
                this.f8361h.inset(this.imageBorderWidth / 2.0f, this.imageBorderWidth / 2.0f);
                this.f8363j.setRectToRect(this.f8356c, this.f8361h, Matrix.ScaleToFit.FILL);
                break;
            case 4:
            default:
                this.f8361h.set(this.f8356c);
                this.f8363j.setRectToRect(this.f8356c, this.f8354a, Matrix.ScaleToFit.CENTER);
                this.f8363j.mapRect(this.f8361h);
                this.f8361h.inset(this.imageBorderWidth / 2.0f, this.imageBorderWidth / 2.0f);
                this.f8363j.setRectToRect(this.f8356c, this.f8361h, Matrix.ScaleToFit.FILL);
                break;
            case 5:
                this.f8361h.set(this.f8356c);
                this.f8363j.setRectToRect(this.f8356c, this.f8354a, Matrix.ScaleToFit.END);
                this.f8363j.mapRect(this.f8361h);
                this.f8361h.inset(this.imageBorderWidth / 2.0f, this.imageBorderWidth / 2.0f);
                this.f8363j.setRectToRect(this.f8356c, this.f8361h, Matrix.ScaleToFit.FILL);
                break;
            case 6:
                this.f8361h.set(this.f8356c);
                this.f8363j.setRectToRect(this.f8356c, this.f8354a, Matrix.ScaleToFit.START);
                this.f8363j.mapRect(this.f8361h);
                this.f8361h.inset(this.imageBorderWidth / 2.0f, this.imageBorderWidth / 2.0f);
                this.f8363j.setRectToRect(this.f8356c, this.f8361h, Matrix.ScaleToFit.FILL);
                break;
            case 7:
                this.f8361h.set(this.f8354a);
                this.f8361h.inset(this.imageBorderWidth / 2.0f, this.imageBorderWidth / 2.0f);
                this.f8363j.reset();
                this.f8363j.setRectToRect(this.f8356c, this.f8361h, Matrix.ScaleToFit.FILL);
                break;
        }
        this.f8355b.set(this.f8361h);
        this.f8357d.setLocalMatrix(this.f8363j);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f8354a.set(rect);
        m1239b();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.oval) {
            if (this.imageBorderWidth > 0.0f) {
                canvas.drawOval(this.f8355b, this.f8358e);
                canvas.drawOval(this.f8361h, this.strokePaint);
                return;
            }
            canvas.drawOval(this.f8355b, this.f8358e);
        } else if (this.imageBorderWidth > 0.0f) {
            canvas.drawRoundRect(this.f8355b, Math.max(this.cornerRadius, 0.0f), Math.max(this.cornerRadius, 0.0f), this.f8358e);
            canvas.drawRoundRect(this.f8361h, this.cornerRadius, this.cornerRadius, this.strokePaint);
        } else {
            canvas.drawRoundRect(this.f8355b, this.cornerRadius, this.cornerRadius, this.f8358e);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f8358e.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f8358e.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.f8358e.setDither(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f8358e.setFilterBitmap(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f8359f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f8360g;
    }

    /* renamed from: a */
    public RoundedDrawable setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        return this;
    }

    /* renamed from: b */
    public RoundedDrawable setImageBorderWidth(float f) {
        this.imageBorderWidth = f;
        this.strokePaint.setStrokeWidth(this.imageBorderWidth);
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable setImageBorderColor(ColorStateList imageBorderColor) {
        if (imageBorderColor == null) {
            imageBorderColor = ColorStateList.valueOf(0);
        }
        this.imageBorderColor = imageBorderColor;
        this.strokePaint.setColor(this.imageBorderColor.getColorForState(getState(), ViewCompat.MEASURED_STATE_MASK));
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable setOval(boolean oval) {
        this.oval = oval;
        return this;
    }

    /* renamed from: a */
    public RoundedDrawable setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        if (this.scaleType != scaleType) {
            this.scaleType = scaleType;
            m1239b();
        }
        return this;
    }

    /* renamed from: a */
    public Paint m1246a() {
        return this.f8358e;
    }
}
