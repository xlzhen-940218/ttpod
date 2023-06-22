package com.sds.android.ttpod.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class FreezableViewPager extends ViewPager {

    /* renamed from: a */
    private boolean f7682a;

    public FreezableViewPager(Context context) {
        super(context);
        this.f7682a = true;
    }

    public FreezableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7682a = true;
    }

    public void setCanScroll(boolean z) {
        this.f7682a = z;
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        if (this.f7682a) {
            super.scrollTo(i, i2);
        }
    }
}
