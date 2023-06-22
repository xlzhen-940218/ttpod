package com.sds.android.ttpod.cmmusic.p077a;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;

/* compiled from: TabPagerAdapter.java */
/* renamed from: com.sds.android.ttpod.cmmusic.a.e */
/* loaded from: classes.dex */
public class C1025e extends FragmentStatePagerAdapter {

    /* renamed from: a */
    private List<Fragment> f3476a;

    public C1025e(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.f3476a = list;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        return this.f3476a.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.f3476a.size();
    }
}
