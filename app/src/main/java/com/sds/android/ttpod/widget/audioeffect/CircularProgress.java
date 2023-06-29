package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class CircularProgress extends View {

    /* renamed from: a */
    private Bitmap backgroundBitmap;

    /* renamed from: b */
    private int background_resource;

    /* renamed from: c */
    private int f8094c;

    /* renamed from: d */
    private float f8095d;

    /* renamed from: e */
    private RectF f8096e;

    /* renamed from: f */
    private Paint f8097f;

    /* renamed from: g */
    private float border_width;

    /* renamed from: h */
    private float f8099h;

    /* renamed from: i */
    private float text_size;

    /* renamed from: j */
    private int border_color;

    /* renamed from: k */
    private float f8102k;

    /* renamed from: l */
    private int text_color;

    /* renamed from: m */
    private Rect f8104m;

    /* renamed from: n */
    private String paint_text;

    /* renamed from: o */
    private boolean show_text;

    /* renamed from: p */
    private Rect f8107p;

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > i2) {
            this.f8094c = i2;
            this.f8095d = (this.f8094c / 2) - (getPaddingTop() + getPaddingBottom());
        } else {
            this.f8094c = i;
            this.f8095d = (this.f8094c / 2) - (getPaddingLeft() + getPaddingRight());
        }
        if (this.backgroundBitmap != null) {
            this.f8107p = new Rect(((getWidth() / 2) - (this.backgroundBitmap.getWidth() / 2)) + getPaddingLeft(), ((getWidth() / 2) - (this.backgroundBitmap.getHeight() / 2)) + getPaddingTop(), ((getWidth() / 2) + (this.backgroundBitmap.getWidth() / 2)) - getPaddingRight(), ((getWidth() / 2) + (this.backgroundBitmap.getHeight() / 2)) - getPaddingBottom());
            this.f8096e = new RectF(this.f8107p);
        }
    }

    /* renamed from: a */
    private void m1418a(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularProgress, i, 0);
            this.background_resource = obtainStyledAttributes.getResourceId(R.styleable.CircularProgress_background_resource, R.drawable.effect_circle_green_small);
            this.paint_text = obtainStyledAttributes.getString(R.styleable.CircularProgress_paint_text);
            if (this.paint_text == null) {
                this.paint_text = "";
            }
            this.text_size = obtainStyledAttributes.getFloat(R.styleable.CircularProgress_text_size, 7.0f);
            this.text_color = obtainStyledAttributes.getColor(R.styleable.CircularProgress_text_color, -1);
            this.border_color = obtainStyledAttributes.getColor(R.styleable.CircularProgress_border_color, 0xff000000);
            this.border_width = obtainStyledAttributes.getFloat(R.styleable.CircularProgress_border_width, 5.0f);
            this.show_text = obtainStyledAttributes.getBoolean(R.styleable.CircularProgress_show_text, true);
            obtainStyledAttributes.recycle();
        }
        this.backgroundBitmap = BitmapFactory.decodeResource(getResources(), this.background_resource);
    }

    public CircularProgress(Context context) {
        this(context, null);
    }

    public CircularProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.background_resource = R.drawable.effect_circle_green_small;
        this.f8097f = new Paint(1);
        this.border_color = 0xff000000;
        this.f8102k = 0.0f;
        this.text_color = Color.parseColor("#FFFFFF");
        this.f8104m = new Rect();
        this.paint_text = "";
        this.show_text = true;
        m1418a(context, attributeSet, 0);
    }

    public CircularProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.background_resource = R.drawable.effect_circle_green_small;
        this.f8097f = new Paint(1);
        this.border_color = 0xff000000;
        this.f8102k = 0.0f;
        this.text_color = Color.parseColor("#FFFFFF");
        this.f8104m = new Rect();
        this.paint_text = "";
        this.show_text = true;
        m1418a(context, attributeSet, i);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.backgroundBitmap != null) {
            canvas.drawBitmap(this.backgroundBitmap, (getWidth() / 2) - (this.backgroundBitmap.getWidth() / 2), (getWidth() / 2) - (this.backgroundBitmap.getHeight() / 2), (Paint) null);
        }
        this.f8097f.setStrokeWidth(this.border_width * getResources().getDisplayMetrics().density);
        this.f8097f.setColor(this.border_color);
        this.f8097f.setStyle(Paint.Style.STROKE);
        canvas.drawArc(this.f8096e, 90.0f, (int) (this.f8102k - 360.0f), false, this.f8097f);
        this.f8097f.setColor(this.text_color);
        this.f8097f.setTextSize(this.text_size * getResources().getDisplayMetrics().density);
        this.f8097f.setStrokeWidth(this.f8099h * getResources().getDisplayMetrics().density);
        this.f8097f.setStyle(Paint.Style.FILL);
        String valueOf = String.valueOf((((int) this.f8102k) * 50) / 360);
        if (this.show_text) {
            if ("".equals(this.paint_text)) {
                this.paint_text = valueOf;
            }
            this.f8097f.getTextBounds(this.paint_text, 0, 1, this.f8104m);
            int width = this.f8104m.width();
            int height = this.f8104m.height();
            if (this.f8107p != null) {
                canvas.drawText(this.paint_text, (this.f8107p.left + (this.f8107p.width() / 2)) - (width / 2), (height / 2) + this.f8107p.top + (this.f8107p.height() / 2), this.f8097f);
            }
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.backgroundBitmap != null) {
            this.backgroundBitmap.recycle();
            this.backgroundBitmap = null;
        }
    }

    public void setValue(int i) {
        this.f8102k = Math.min((i * 360) / 50, 360);
        invalidate();
    }

    public void setPaintText(String str) {
        this.paint_text = str;
    }

    public void setShowText(boolean z) {
        this.show_text = z;
    }
}
