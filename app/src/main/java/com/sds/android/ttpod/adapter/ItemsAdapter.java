package com.sds.android.ttpod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.d */
/* loaded from: classes.dex */
public abstract class ItemsAdapter<T> extends BaseAdapter {

    /* renamed from: a */
    private List<T> f3222a;

    /* renamed from: b */
    private final WeakReference<Context> f3223b;

    /* renamed from: c */
    private final LayoutInflater f3224c;

    /* renamed from: a */
    protected abstract View mo5653a(T t, int i, ViewGroup viewGroup, LayoutInflater layoutInflater);

    /* renamed from: a */
    protected abstract void mo5654a(T t, int i, View view);

    public ItemsAdapter(Context context) {
        this.f3223b = new WeakReference<>(context);
        this.f3224c = LayoutInflater.from(context);
    }

    /* renamed from: a */
    public void m7592a(List<T> list) {
        this.f3222a = list;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public List<T> m7593a() {
        return this.f3222a;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3222a == null) {
            return 0;
        }
        return this.f3222a.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int i) {
        if (this.f3222a == null || i < 0 || i >= this.f3222a.size()) {
            return null;
        }
        return this.f3222a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        T t = this.f3222a.get(i);
        if (view == null) {
            view = mo5653a(t, i, viewGroup, this.f3224c);
        }
        mo5654a(t, i, view);
        return view;
    }
}
