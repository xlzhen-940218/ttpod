package com.sds.android.ttpod.framework.base;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.base.f */
/* loaded from: classes.dex */
public class FragmentBackStackManager implements IFragmentHandler {

    /* renamed from: a */
    private int f5726a;

    /* renamed from: b */
    private int f5727b;

    /* renamed from: c */
    private int f5728c;

    /* renamed from: d */
    private FragmentManager f5729d;

    /* renamed from: e */
    private BaseFragment f5730e;

    /* renamed from: f */
    private List<BaseFragment> f5731f = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentBackStackManager(FragmentManager fragmentManager) {
        this.f5729d = fragmentManager;
    }

    /* renamed from: f */
    private void m4572f(BaseFragment baseFragment) {
        this.f5731f.add(baseFragment);
    }

    /* renamed from: i */
    private BaseFragment m4570i() {
        BaseFragment baseFragment = this.f5731f.get(this.f5731f.size() - 1);
        this.f5731f.remove(baseFragment);
        return baseFragment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m4581a() {
        return this.f5731f.isEmpty();
    }

    /* renamed from: g */
    private boolean m4571g(BaseFragment baseFragment) {
        return this.f5730e == baseFragment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m4578b() {
        this.f5730e = null;
        this.f5731f.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public BaseFragment m4576c() {
        return m4581a() ? this.f5730e : this.f5731f.get(this.f5731f.size() - 1);
    }

    /* renamed from: d */
    public List<BaseFragment> m4575d() {
        return this.f5731f;
    }

    /* renamed from: j */
    private BaseFragment m4569j() {
        int size = this.f5731f.size();
        if (size > 1) {
            return this.f5731f.get(size - 2);
        }
        if (size == 1) {
            return this.f5730e;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m4580a(int i, int i2, int i3) {
        this.f5726a = i;
        this.f5727b = i2;
        this.f5728c = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m4579a(BaseFragment baseFragment) {
        if (this.f5726a == 0) {
            throw new IllegalArgumentException("you must call setLaunchFragmentAttr(int containerViewRes, int enterAnimRes, int popExitAnimRes) first");
        }
        baseFragment.markAsPage();
        this.f5730e = baseFragment;
        this.f5729d.beginTransaction().add(this.f5726a, baseFragment).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final BaseFragment m4574e() {
        return this.f5730e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m4577b(BaseFragment baseFragment) {
        this.f5730e = baseFragment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public FragmentManager m4573f() {
        return this.f5729d;
    }

    @Override // com.sds.android.ttpod.framework.base.IFragmentHandler
    /* renamed from: c */
    public final void mo4568c(BaseFragment baseFragment) {
        baseFragment.markAsPage();
        m4572f(baseFragment);
        this.f5729d.beginTransaction().setCustomAnimations(this.f5727b, 0, 0, this.f5728c).add(this.f5726a, baseFragment).addToBackStack(null).commitAllowingStateLoss();
    }

    @Override // com.sds.android.ttpod.framework.base.IFragmentHandler
    /* renamed from: d */
    public final void mo4567d(BaseFragment baseFragment) {
        BaseFragment m4569j;
        if (m4576c() == baseFragment && (m4569j = m4569j()) != null && m4569j.getView() != null) {
            m4569j.getView().setVisibility(View.GONE);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.IFragmentHandler
    /* renamed from: e */
    public void mo4566e(BaseFragment baseFragment) {
        if (m4571g(baseFragment)) {
            throw new IllegalArgumentException("Primary fragment must not be finish");
        }
        try {
            if (this.f5731f.size() > 0 && this.f5731f.get(this.f5731f.size() - 1) == baseFragment) {
                this.f5729d.popBackStack();
                m4570i();
                BaseFragment m4576c = m4576c();
                if (m4576c != null) {
                    m4576c.pageBack();
                    m4576c.getView().setVisibility(View.VISIBLE);
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.IFragmentHandler
    /* renamed from: g */
    public final void mo4565g() {
        BaseFragment m4569j = m4569j();
        if (m4569j != null && m4569j.getView() != null) {
            m4569j.getView().setVisibility(View.VISIBLE);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.IFragmentHandler
    /* renamed from: h */
    public final void mo4564h() {
        BaseFragment m4569j = m4569j();
        if (m4569j != null && m4569j.getView() != null) {
            m4569j.getView().setVisibility(View.GONE);
        }
    }
}
