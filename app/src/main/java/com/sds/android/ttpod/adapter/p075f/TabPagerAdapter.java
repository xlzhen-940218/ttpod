package com.sds.android.ttpod.adapter.p075f;

import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.f.d */
/* loaded from: classes.dex */
public class TabPagerAdapter extends PagerAdapter implements SlidingTabHost.InterfaceC2244a {

    /* renamed from: a */
    private List<C1004a> f3410a;

    public TabPagerAdapter(List<C1004a> list) {
        this.f3410a = list;
    }

    @Override // com.sds.android.ttpod.widget.SlidingTabHost.InterfaceC2244a
    /* renamed from: b */
    public int mo1459b(int i) {
        return this.f3410a.get(i).m7392b();
    }

    @Override // com.sds.android.ttpod.widget.SlidingTabHost.InterfaceC2244a
    /* renamed from: c */
    public int mo1458c(int i) {
        return this.f3410a.get(i).m7393a();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.f3410a.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return false;
    }

    /* compiled from: TabPagerAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.f.d$a */
    /* loaded from: classes.dex */
    public static class C1004a {

        /* renamed from: a */
        private long f3411a;

        /* renamed from: b */
        private int f3412b;

        /* renamed from: c */
        private int f3413c;

        public C1004a(long j, int i, int i2) {
            this.f3411a = j;
            this.f3412b = i;
            this.f3413c = i2;
        }

        /* renamed from: a */
        public int m7393a() {
            return this.f3412b;
        }

        /* renamed from: b */
        public int m7392b() {
            return this.f3413c;
        }
    }
}
