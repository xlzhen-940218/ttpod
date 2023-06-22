package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes.dex */
public class DynamicSizeTextView extends TextView implements DynamicSize {

    /* renamed from: a */
    private float f7647a;

    /* renamed from: a */
    private void m1763a(Context context) {
        this.f7647a = getTextSize();
    }

    public DynamicSizeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m1763a(context);
    }

    public DynamicSizeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m1763a(context);
    }

    @Override // com.sds.android.ttpod.widget.DynamicSize
    /* renamed from: a */
    public void mo1365a(float f) {
        setTextSize(0, this.f7647a * f);
    }
}
