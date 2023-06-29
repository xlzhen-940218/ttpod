package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class EqualizerBaseWaveView extends RelativeLayout {

    /* renamed from: a */
    protected final float[] f8111a;

    /* renamed from: b */
    protected final PointF[] f8112b;

    /* renamed from: c */
    private int waveShadowColor;

    /* renamed from: d */
    private float waveShadowRadius;

    /* renamed from: e */
    private int waveColor;

    /* renamed from: f */
    private int waveStrokeWidth;

    /* renamed from: g */
    private final Path f8117g;

    /* renamed from: h */
    private float PathEffectRadius;

    /* renamed from: i */
    private CornerPathEffect f8119i;

    /* renamed from: j */
    private boolean f8120j;

    /* renamed from: k */
    private Paint f8121k;

    public EqualizerBaseWaveView(Context context) {
        this(context, null);
    }

    public EqualizerBaseWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.waveShadowColor = -16711936;
        this.waveShadowRadius = 5.0f;
        this.waveColor = 0xff000000;
        this.waveStrokeWidth = 3;
        this.f8111a = new float[10];
        this.f8112b = new PointF[10];
        this.f8117g = new Path();
        this.PathEffectRadius = 50.0f;
        this.f8120j = true;
        this.f8121k = new Paint();
        this.f8121k.setAntiAlias(true);
        this.f8121k.setStyle(Paint.Style.STROKE);
        m1413a(context, attributeSet, 0);
    }

    public EqualizerBaseWaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.waveShadowColor = -16711936;
        this.waveShadowRadius = 5.0f;
        this.waveColor = 0xff000000;
        this.waveStrokeWidth = 3;
        this.f8111a = new float[10];
        this.f8112b = new PointF[10];
        this.f8117g = new Path();
        this.PathEffectRadius = 50.0f;
        this.f8120j = true;
        this.f8121k = new Paint();
        this.f8121k.setAntiAlias(true);
        this.f8121k.setStyle(Paint.Style.STROKE);
        m1413a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m1413a(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WaveView, i, 0);
            this.waveShadowColor = obtainStyledAttributes.getColor(R.styleable.WaveView_waveShadowColor, -16711936);
            this.waveShadowRadius = obtainStyledAttributes.getFloat(R.styleable.WaveView_waveShadowRadius, 5.0f);
            this.waveColor = obtainStyledAttributes.getColor(R.styleable.WaveView_waveColor, -16776961);
            this.waveStrokeWidth = obtainStyledAttributes.getInteger(R.styleable.WaveView_waveStrokeWidth, 1);
            this.PathEffectRadius = obtainStyledAttributes.getFloat(R.styleable.WaveView_PathEffectRadius, 50.0f);
            obtainStyledAttributes.recycle();
        }
        for (int i2 = 0; i2 < 10; i2++) {
            this.f8112b[i2] = new PointF();
        }
        this.f8119i = new CornerPathEffect(this.PathEffectRadius);
    }

    /* renamed from: a */
    private void m1414a() {
        synchronized (this) {
            this.f8120j = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getHeightWithoutPadding() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    /* renamed from: b */
    private void m1412b() {
        synchronized (this) {
            int heightWithoutPadding = getHeightWithoutPadding();
            if (heightWithoutPadding > 0) {
                int paddingTop = getPaddingTop();
                int i = heightWithoutPadding >> 1;
                for (int i2 = 0; i2 < 10; i2++) {
                    this.f8112b[i2].y = ((1.0f - (this.f8111a[i2] / 1500)) * i) + paddingTop;
                }
                this.f8117g.reset();
                this.f8117g.moveTo(this.f8112b[0].x, this.f8112b[0].y);
                float f = 10.0f * getContext().getResources().getDisplayMetrics().density;
                for (int i3 = 1; i3 < 10; i3++) {
                    if (i3 == 9) {
                        this.f8117g.lineTo(this.f8112b[i3].x - 1.0f, this.f8112b[i3].y);
                        this.f8117g.lineTo(this.f8112b[i3].x + 1.0f, this.f8112b[i3].y);
                    } else {
                        this.f8117g.lineTo(this.f8112b[i3].x - f, this.f8112b[i3].y);
                    }
                }
                this.f8120j = false;
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float width = ((getWidth() - getPaddingLeft()) - getPaddingRight()) / 9.0f;
        int paddingLeft = getPaddingLeft();
        for (int i5 = 0; i5 < 10; i5++) {
            this.f8112b[i5].x = (i5 * width) + paddingLeft;
        }
        m1414a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f8120j) {
            m1412b();
        }
        Paint paint = this.f8121k;
        paint.setShadowLayer(this.waveShadowRadius, 0.0f, 0.0f, this.waveShadowColor);
        paint.setPathEffect(this.f8119i);
        paint.setColor(this.waveColor);
        paint.setStrokeWidth(this.waveStrokeWidth);
        canvas.drawPath(this.f8117g, paint);
    }

    public void setWaveShadowColor(int i) {
        this.waveShadowColor = i;
    }

    public void setWaveShadowRadius(float f) {
        this.waveShadowRadius = f;
    }

    public void setWaveColor(int i) {
        this.waveColor = i;
    }

    public void setWaveStrokeWidth(int i) {
        this.waveStrokeWidth = i;
    }

    public void setPathEffectRadius(float f) {
        this.PathEffectRadius = f;
        this.f8119i = new CornerPathEffect(this.PathEffectRadius);
    }

    /* renamed from: a */
    public void mo1411a(int i, float f) {
        this.f8111a[i] = f;
        m1414a();
        invalidate();
    }

    public void setWaveValue(float[] fArr) {
        System.arraycopy(fArr, 0, this.f8111a, 0, 10);
        m1414a();
        invalidate();
    }

    public void setWaveValue(short[] sArr) {
        boolean z = false;
        for (int i = 0; i < 10; i++) {
            float f = sArr[i];
            if (this.f8111a[i] != f) {
                this.f8111a[i] = f;
                z = true;
            }
        }
        if (z) {
            m1414a();
            invalidate();
        }
    }
}
