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
    private List<T> dataList;

    /* renamed from: b */
    private final WeakReference<Context> contextWeakReference;

    /* renamed from: c */
    private final LayoutInflater layoutInflater;

    /* renamed from: a */
    protected abstract View getConvertView(T t, int i, ViewGroup viewGroup, LayoutInflater layoutInflater);

    /* renamed from: a */
    protected abstract void buildDataUI(T t, int i, View view);

    public ItemsAdapter(Context context) {
        this.contextWeakReference = new WeakReference<>(context);
        this.layoutInflater = LayoutInflater.from(context);
    }

    /* renamed from: a */
    public void setDataList(List<T> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public List<T> getDataList() {
        return this.dataList;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.dataList == null) {
            return 0;
        }
        return this.dataList.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int i) {
        if (this.dataList == null || i < 0 || i >= this.dataList.size()) {
            return null;
        }
        return this.dataList.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View view, ViewGroup viewGroup) {
        T data = this.dataList.get(position);
        if (view == null) {
            view = getConvertView(data, position, viewGroup, this.layoutInflater);
        }
        buildDataUI(data, position, view);
        return view;
    }
}
