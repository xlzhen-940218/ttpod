package com.sds.android.ttpod.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.f */
/* loaded from: classes.dex */
public class SlidingTabFragmentPagerAdapter extends FragmentPagerAdapter implements SlidingTabHost.InterfaceC2244a {

    /* renamed from: a */
    private List<C0998a> f3379a;

    /* renamed from: b */
    private Context f3380b;

    public SlidingTabFragmentPagerAdapter(Context context, FragmentManager fragmentManager, List<C0998a> list) {
        super(fragmentManager);
        this.f3380b = context;
        this.f3379a = list;
        if (this.f3379a == null) {
            throw new IllegalArgumentException("FragmentBinders must not be null!");
        }
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.f3379a.get(i).m7419e();
    }

    /* renamed from: a */
    public void m7428a() {
        this.f3380b = null;
    }

    /* renamed from: a */
    public void m7427a(int i) {
        if (this.f3380b != null) {
            BaseFragment topFragment = ((BaseActivity) this.f3380b).getTopFragment();
            BaseFragment baseFragment = (BaseFragment) getItem(i);
            if (topFragment != null) {
                topFragment.setPage(baseFragment.getPage());
                topFragment.setPageProperties(baseFragment.getPageProperties());
                topFragment.updatePageProperties();
                return;
            }
            ((BaseActivity) this.f3380b).updatePage(baseFragment.getPage());
        }
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.f3379a.size();
    }

    @Override // com.sds.android.ttpod.widget.SlidingTabHost.InterfaceC2244a
    /* renamed from: b */
    public int mo1459b(int i) {
        return this.f3379a.get(i).m7421c();
    }

    @Override // com.sds.android.ttpod.widget.SlidingTabHost.InterfaceC2244a
    /* renamed from: c */
    public int mo1458c(int i) {
        return this.f3379a.get(i).m7422b();
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        CharSequence m7426a = this.f3379a.get(i).m7426a();
        return m7426a == null ? super.getPageTitle(i) : m7426a.toString();
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public long getItemId(int i) {
        return this.f3379a.get(i).m7420d();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        for (C0998a c0998a : this.f3379a) {
            if (c0998a != null && obj.equals(c0998a.m7419e())) {
                return super.getItemPosition(obj);
            }
        }
        return -2;
    }

    /* compiled from: SlidingTabFragmentPagerAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.f$a */
    /* loaded from: classes.dex */
    public static class C0998a {

        /* renamed from: a */
        private long f3381a;

        /* renamed from: b */
        private int f3382b;

        /* renamed from: c */
        private CharSequence f3383c;

        /* renamed from: d */
        private int f3384d;

        /* renamed from: e */
        private Fragment f3385e;

        public C0998a(long j, int i, int i2, Fragment fragment) {
            this.f3381a = j;
            this.f3382b = i;
            this.f3384d = i2;
            this.f3385e = fragment;
        }

        public C0998a(long j, CharSequence charSequence, int i, Fragment fragment) {
            this.f3381a = j;
            this.f3383c = charSequence;
            this.f3384d = i;
            this.f3385e = fragment;
        }

        /* renamed from: a */
        public CharSequence m7426a() {
            return this.f3383c;
        }

        /* renamed from: b */
        public int m7422b() {
            return this.f3382b;
        }

        /* renamed from: c */
        public int m7421c() {
            return this.f3384d;
        }

        /* renamed from: d */
        public long m7420d() {
            return this.f3381a;
        }

        /* renamed from: e */
        public Fragment m7419e() {
            return this.f3385e;
        }
    }
}
