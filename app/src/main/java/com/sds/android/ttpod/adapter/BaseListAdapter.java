package com.sds.android.ttpod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* renamed from: com.sds.android.ttpod.adapter.a */
/* loaded from: classes.dex */
public abstract class BaseListAdapter<D> extends BaseAdapter {

    /* renamed from: a */
    private D f3155a;

    /* renamed from: b */
    protected Context f3156b;

    /* renamed from: c */
    protected LayoutInflater f3157c;

    /* renamed from: d */
    protected List<D> f3158d;

    /* renamed from: a */
    protected abstract View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* renamed from: a */
    protected abstract void mo5400a(View view, D d, int i);

    public BaseListAdapter(Context context, List<D> list) {
        if (list == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.f3156b = context == null ? BaseApplication.getApplication() : context;
        this.f3158d = list;
        this.f3157c = LayoutInflater.from(this.f3156b);
    }

    public BaseListAdapter() {
        this(BaseApplication.getApplication(), new ArrayList());
    }

    /* renamed from: a */
    public Context m7664a() {
        return this.f3156b;
    }

    /* renamed from: b */
    public List<D> m7662b() {
        return this.f3158d;
    }

    /* renamed from: a */
    public void m7663a(List<D> list) {
        if (list == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.f3158d = list;
        notifyDataSetChanged();
    }

    /* renamed from: c */
    public D m7661c() {
        return this.f3155a;
    }

    /* renamed from: a */
    public void mo7467a(D d) {
        this.f3155a = d;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3158d.size();
    }

    @Override // android.widget.Adapter
    public D getItem(int i) {
        return this.f3158d.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mo5402a(this.f3157c, viewGroup);
        }
        mo5400a(view, getItem(i), i);
        return view;
    }
}
