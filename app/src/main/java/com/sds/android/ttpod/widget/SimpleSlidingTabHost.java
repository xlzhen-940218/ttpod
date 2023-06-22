package com.sds.android.ttpod.widget;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.util.AttributeSet;
import com.sds.android.sdk.lib.util.LogUtils;

/* loaded from: classes.dex */
public class SimpleSlidingTabHost extends SlidingTabHost {

    /* renamed from: c */
    private PagerAdapter f7924c;

    /* renamed from: d */
    private int f7925d;

    public SimpleSlidingTabHost(Context context) {
        super(context);
    }

    public SimpleSlidingTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SimpleSlidingTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.sds.android.ttpod.widget.SlidingTabHost
    /* renamed from: a */
    public void mo1479a() {
        m1472a(this.f7924c);
    }

    @Override // com.sds.android.ttpod.widget.SlidingTabHost
    /* renamed from: a */
    public void mo1478a(int i) {
        LogUtils.m8388a("SimpleSlidingTabHost", "onTabClick tab=" + i);
        this.f7999b = i;
        m1477a(i, 0);
        invalidate();
        if (this.onPageChangeListener != null) {
            this.onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // com.sds.android.ttpod.widget.SlidingTabHost
    protected int getCurrentItem() {
        return this.f7925d;
    }

    @Override // com.sds.android.ttpod.widget.SlidingTabHost
    protected void setCurrentItem(int i) {
        this.f7925d = i;
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        this.f7924c = pagerAdapter;
        mo1479a();
    }
}
