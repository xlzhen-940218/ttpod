package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class EqualizerIndicatorWaveView extends EqualizerBaseWaveView {

    /* renamed from: c */
    private final float f8122c;

    /* renamed from: d */
    private final float f8123d;

    /* renamed from: e */
    private final float f8124e;

    /* renamed from: f */
    private int f8125f;

    /* renamed from: g */
    private boolean f8126g;

    public EqualizerIndicatorWaveView(Context context) {
        this(context, null);
        setWillNotDraw(false);
    }

    public EqualizerIndicatorWaveView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        setWillNotDraw(false);
    }

    public EqualizerIndicatorWaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8126g = false;
        float f = getResources().getDisplayMetrics().density;
        this.f8122c = 0.0f * f;
        this.f8123d = 12.0f * f;
        this.f8124e = f * 8.0f;
        this.f8125f = -1;
        setWillNotDraw(false);
    }

    @Override // com.sds.android.ttpod.widget.audioeffect.EqualizerBaseWaveView
    /* renamed from: a */
    public void mo1411a(int i, float f) {
        super.mo1411a(i, f);
        this.f8125f = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.widget.audioeffect.EqualizerBaseWaveView
    public int getHeightWithoutPadding() {
        return super.getHeightWithoutPadding() - ((int) this.f8122c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.widget.audioeffect.EqualizerBaseWaveView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f8126g) {
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(this.f8124e);
            textPaint.setColor(-11607297);
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float height = 2.0f + fontMetrics.descent + ((getHeight() - getPaddingBottom()) - fontMetrics.ascent);
            for (int i = 0; i < 10; i++) {
                String valueOf = String.valueOf((int) (this.f8111a[i] / 100.0f));
                if (i == 0) {
                    textPaint.setTextAlign(Paint.Align.LEFT);
                } else if (i == 9) {
                    textPaint.setTextAlign(Paint.Align.RIGHT);
                } else {
                    textPaint.setTextAlign(Paint.Align.CENTER);
                }
                textPaint.setTextAlign(Paint.Align.CENTER);
                if (i == this.f8125f) {
                    textPaint.setTextSize(this.f8123d);
                    textPaint.setColor(-1);
                    canvas.drawText(valueOf, this.f8112b[i].x, height, textPaint);
                    textPaint.setTextSize(this.f8124e);
                    textPaint.setColor(-11607297);
                    this.f8125f = -1;
                } else {
                    canvas.drawText(valueOf, this.f8112b[i].x, height, textPaint);
                }
            }
        }
    }

    public void setCoordinateVisible(boolean z) {
        this.f8126g = z;
    }
}
