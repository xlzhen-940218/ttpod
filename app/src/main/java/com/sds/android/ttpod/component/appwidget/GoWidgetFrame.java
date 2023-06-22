package com.sds.android.ttpod.component.appwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public abstract class GoWidgetFrame extends FrameLayout implements GoWidgetLife {
    public GoWidgetFrame(Context context) {
        super(context);
    }

    public GoWidgetFrame(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GoWidgetFrame(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
