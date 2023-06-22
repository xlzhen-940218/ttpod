package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class RoundedImageView extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    static final /* synthetic */ boolean desiredAssertionStatus;

    /* renamed from: b */
    private static final ImageView.ScaleType[] SCALE_TYPES;

    /* renamed from: c */
    private float corner_radius;

    /* renamed from: d */
    private float Image_border_width;

    /* renamed from: e */
    private ColorStateList Image_border_color;

    /* renamed from: f */
    private boolean oval;

    /* renamed from: g */
    private boolean mutate_background;

    /* renamed from: h */
    private int imageResourceId;

    /* renamed from: i */
    private Drawable imageDrawable;

    /* renamed from: j */
    private Drawable f7886j;

    /* renamed from: k */
    private ImageView.ScaleType scaleType;

    /* renamed from: l */
    private float image_ratio;

    static {
        desiredAssertionStatus = !RoundedImageView.class.desiredAssertionStatus();
        SCALE_TYPES = new ImageView.ScaleType[]{ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    }

    public RoundedImageView(Context context) {
        super(context);
        this.corner_radius = 0.0f;
        this.Image_border_width = 0.0f;
        this.Image_border_color = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        this.oval = false;
        this.mutate_background = false;
        this.scaleType = ImageView.ScaleType.FIT_CENTER;
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.corner_radius = 0.0f;
        this.Image_border_width = 0.0f;
        this.Image_border_color = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        this.oval = false;
        this.mutate_background = false;
        this.scaleType = ImageView.ScaleType.FIT_CENTER;
        m1567a(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.corner_radius = 0.0f;
        this.Image_border_width = 0.0f;
        this.Image_border_color = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        this.oval = false;
        this.mutate_background = false;
        this.scaleType = ImageView.ScaleType.FIT_CENTER;
        m1567a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m1567a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, i, 0);
        int scaleType = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_android_scaleType, -1);
        if (scaleType >= 0) {
            setScaleType(SCALE_TYPES[scaleType]);
        } else {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        this.corner_radius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_corner_radius, -1);
        this.Image_border_width = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_Image_border_width, -1);
        if (this.corner_radius < 0.0f) {
            this.corner_radius = 0.0f;
        }
        if (this.Image_border_width < 0.0f) {
            this.Image_border_width = 0.0f;
        }
        this.Image_border_color = obtainStyledAttributes.getColorStateList(R.styleable.RoundedImageView_Image_border_color);
        if (this.Image_border_color == null) {
            this.Image_border_color = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        }
        this.mutate_background = obtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_mutate_background, false);
        this.oval = obtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_oval, false);
        this.image_ratio = obtainStyledAttributes.getFloat(R.styleable.RoundedImageView_image_ratio, 0.0f);
        m1564b();
        m1565a(true);
        obtainStyledAttributes.recycle();
    }

    public RoundedImageView(Context context, float f) {
        super(context);
        this.corner_radius = 0.0f;
        this.Image_border_width = 0.0f;
        this.Image_border_color = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        this.oval = false;
        this.mutate_background = false;
        this.scaleType = ImageView.ScaleType.FIT_CENTER;
        this.corner_radius = f;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (!desiredAssertionStatus && scaleType == null) {
            throw new AssertionError();
        }
        if (this.scaleType != scaleType) {
            this.scaleType = scaleType;
            switch (C22231.f7889a[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    super.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            m1564b();
            m1565a(false);
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.widget.RoundedImageView$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C22231 {

        /* renamed from: a */
        static final /* synthetic */ int[] f7889a = new int[ImageView.ScaleType.values().length];

        static {
            try {
                f7889a[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7889a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7889a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7889a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f7889a[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f7889a[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f7889a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.imageResourceId = 0;
        this.imageDrawable = RoundedDrawable.m1242a(drawable);
        m1564b();
        super.setImageDrawable(this.imageDrawable);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.imageResourceId = 0;
        this.imageDrawable = RoundedDrawable.m1243a(bitmap);
        m1564b();
        super.setImageDrawable(this.imageDrawable);
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        if (this.imageResourceId != i) {
            this.imageResourceId = i;
            this.imageDrawable = m1568a();
            m1564b();
            super.setImageDrawable(this.imageDrawable);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    /* renamed from: a */
    private Drawable m1568a() {
        Drawable drawable = null;
        Resources resources = getResources();
        if (resources == null) {
            return null;
        }
        if (this.imageResourceId != 0) {
            try {
                drawable = resources.getDrawable(this.imageResourceId);
            } catch (Exception e) {
                LogUtils.m8387a("RoundedImageView", "Unable to find resource: " + this.imageResourceId, e);
                this.imageResourceId = 0;
            }
        }
        return RoundedDrawable.m1242a(drawable);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    /* renamed from: b */
    private void m1564b() {
        m1566a(this.imageDrawable);
    }

    /* renamed from: a */
    private void m1565a(boolean z) {
        if (this.mutate_background) {
            if (z) {
                this.f7886j = RoundedDrawable.m1242a(this.f7886j);
            }
            m1566a(this.f7886j);
        }
    }

    /* renamed from: a */
    private void m1566a(Drawable drawable) {
        if (drawable != null) {
            if (!(drawable instanceof RoundedDrawable)) {
                if (drawable instanceof LayerDrawable) {
                    LayerDrawable layerDrawable = (LayerDrawable) drawable;
                    int numberOfLayers = layerDrawable.getNumberOfLayers();
                    for (int i = 0; i < numberOfLayers; i++) {
                        m1566a(layerDrawable.getDrawable(i));
                    }
                    return;
                }
                return;
            }
            ((RoundedDrawable) drawable).m1241a(this.scaleType).m1245a(this.corner_radius == 0.0f ? getHeight() / 2 : this.corner_radius).m1238b(this.Image_border_width).m1244a(this.Image_border_color).m1240a(this.oval);
        }
    }

    @Override // android.view.View
    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        this.f7886j = drawable;
        m1565a(true);
        super.setBackgroundDrawable(this.f7886j);
    }

    public float getmCornerRadius() {
        return this.corner_radius;
    }

    public void setmCornerRadius(int i) {
        setCornerRadius(getResources().getDimension(i));
    }

    public void setCornerRadius(float f) {
        if (this.corner_radius != f) {
            this.corner_radius = f;
            m1564b();
            m1565a(false);
        }
    }

    public float getmBorderWidth() {
        return this.Image_border_width;
    }

    public void setmBorderWidth(int i) {
        setBorderWidth(getResources().getDimension(i));
    }

    public void setBorderWidth(float f) {
        if (this.Image_border_width != f) {
            this.Image_border_width = f;
            m1564b();
            m1565a(false);
            invalidate();
        }
    }

    public int getmBorderColor() {
        return this.Image_border_color.getDefaultColor();
    }

    public void setmBorderColor(int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.Image_border_color;
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (!this.Image_border_color.equals(colorStateList)) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
            }
            this.Image_border_color = colorStateList;
            m1564b();
            m1565a(false);
            if (this.Image_border_width > 0.0f) {
                invalidate();
            }
        }
    }

    public void setOval(boolean z) {
        this.oval = z;
        m1564b();
        m1565a(false);
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.image_ratio > 0.0f) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode == 0) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT mode=" + mode + " width=" + size);
            }
            i2 = View.MeasureSpec.makeMeasureSpec((int) (size / this.image_ratio), MeasureSpec.EXACTLY);
        }
        super.onMeasure(i, i2);
    }

    public void setMutateBackground(boolean z) {
        if (this.mutate_background != z) {
            this.mutate_background = z;
            m1565a(true);
            invalidate();
        }
    }
}
