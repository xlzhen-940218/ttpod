package com.sds.android.ttpod.component.emoticons;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.emoticons.d */
/* loaded from: classes.dex */
public class EmoticonsPagerAdapter extends PagerAdapter {

    /* renamed from: a */
    private List<View> f4126a;

    public EmoticonsPagerAdapter(List<View> list) {
        this.f4126a = list;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.f4126a.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(View view, int i, Object obj) {
        ((ViewPager) view).removeView(this.f4126a.get(i));
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(View view, int i) {
        ((ViewPager) view).addView(this.f4126a.get(i));
        return this.f4126a.get(i);
    }
}
