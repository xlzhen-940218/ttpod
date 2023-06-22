package com.sds.android.ttpod.common.widget;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class CustomClickIconTextView extends IconTextView {

    /* renamed from: a */
    private boolean f3582a;

    public CustomClickIconTextView(Context context) {
        super(context);
        this.f3582a = false;
    }

    public CustomClickIconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3582a = false;
    }

    public CustomClickIconTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3582a = false;
    }

    /* renamed from: a */
    public boolean m7219a() {
        return this.f3582a;
    }

    public void setOnClickEventCustomized(boolean z) {
        this.f3582a = z;
    }
}
