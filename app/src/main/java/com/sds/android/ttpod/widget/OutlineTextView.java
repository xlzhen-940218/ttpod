package com.sds.android.ttpod.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

@SuppressLint({"DrawAllocation"})
/* loaded from: classes.dex */
public class OutlineTextView extends TextView {

    /* renamed from: a */
    private TextPaint f7840a;

    /* renamed from: b */
    private TextPaint f7841b;

    /* renamed from: c */
    private String f7842c;

    /* renamed from: d */
    private int f7843d;

    /* renamed from: e */
    private float f7844e;

    /* renamed from: f */
    private int f7845f;

    /* renamed from: g */
    private int f7846g;

    /* renamed from: h */
    private float f7847h;

    /* renamed from: i */
    private float f7848i;

    /* renamed from: j */
    private boolean f7849j;

    public OutlineTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7842c = "";
        this.f7843d = 0;
        this.f7847h = 1.0f;
        this.f7848i = 0.0f;
        this.f7849j = true;
        m1592a();
    }

    public OutlineTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7842c = "";
        this.f7843d = 0;
        this.f7847h = 1.0f;
        this.f7848i = 0.0f;
        this.f7849j = true;
        m1592a();
    }

    /* renamed from: a */
    private void m1592a() {
        this.f7840a = new TextPaint();
        this.f7840a.setAntiAlias(true);
        this.f7840a.setTextSize(getTextSize());
        this.f7840a.setColor(this.f7846g);
        this.f7840a.setStyle(Paint.Style.FILL);
        this.f7840a.setTypeface(getTypeface());
        this.f7841b = new TextPaint();
        this.f7841b.setAntiAlias(true);
        this.f7841b.setTextSize(getTextSize());
        this.f7841b.setColor(this.f7845f);
        this.f7841b.setStyle(Paint.Style.STROKE);
        this.f7841b.setTypeface(getTypeface());
        this.f7841b.setStrokeWidth(this.f7844e);
    }

    public void setText(String str) {
        super.setText((CharSequence) str);
        this.f7842c = str.toString();
        requestLayout();
        invalidate();
    }

    @Override // android.widget.TextView
    public void setTextSize(float f) {
        super.setTextSize(f);
        requestLayout();
        invalidate();
        m1592a();
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
        this.f7846g = i;
        invalidate();
        m1592a();
    }

    @Override // android.widget.TextView
    public void setShadowLayer(float f, float f2, float f3, int i) {
        super.setShadowLayer(f, f2, f3, i);
        this.f7844e = f;
        this.f7845f = i;
        requestLayout();
        invalidate();
        m1592a();
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i) {
        super.setTypeface(typeface, i);
        requestLayout();
        invalidate();
        m1592a();
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface) {
        super.setTypeface(typeface);
        requestLayout();
        invalidate();
        m1592a();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        new StaticLayout(getText(), this.f7841b, getWidth(), Layout.Alignment.ALIGN_CENTER, this.f7847h, this.f7848i, this.f7849j).draw(canvas);
        new StaticLayout(getText(), this.f7840a, getWidth(), Layout.Alignment.ALIGN_CENTER, this.f7847h, this.f7848i, this.f7849j).draw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        StaticLayout staticLayout = new StaticLayout(getText(), this.f7841b, m1591a(i), Layout.Alignment.ALIGN_CENTER, this.f7847h, this.f7848i, this.f7849j);
        int i3 = (int) ((this.f7844e * 2.0f) + 1.0f);
        setMeasuredDimension(m1591a(i) + i3, (staticLayout.getLineCount() * m1590b(i2)) + i3);
    }

    /* renamed from: a */
    private int m1591a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != 1073741824) {
            int measureText = ((int) this.f7841b.measureText(this.f7842c)) + getPaddingLeft() + getPaddingRight();
            return mode == Integer.MIN_VALUE ? Math.min(measureText, size) : measureText;
        }
        return size;
    }

    /* renamed from: b */
    private int m1590b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        this.f7843d = (int) this.f7841b.ascent();
        if (mode != 1073741824) {
            int descent = ((int) ((-this.f7843d) + this.f7841b.descent())) + getPaddingTop() + getPaddingBottom();
            return mode == Integer.MIN_VALUE ? Math.min(descent, size) : descent;
        }
        return size;
    }
}
