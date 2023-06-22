package com.sds.android.ttpod.widget.expandablelist;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

/* renamed from: com.sds.android.ttpod.widget.expandablelist.c */
/* loaded from: classes.dex */
public abstract class WrapperListAdapterImpl extends BaseAdapter implements WrapperListAdapter {

    /* renamed from: b */
    protected ListAdapter f8340b;

    public WrapperListAdapterImpl(ListAdapter listAdapter) {
        this.f8340b = listAdapter;
    }

    @Override // android.widget.WrapperListAdapter
    public ListAdapter getWrappedAdapter() {
        return this.f8340b;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return this.f8340b.areAllItemsEnabled();
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return this.f8340b.isEnabled(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.f8340b.registerDataSetObserver(dataSetObserver);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.f8340b.unregisterDataSetObserver(dataSetObserver);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f8340b.getCount();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f8340b.getItem(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return this.f8340b.getItemId(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return this.f8340b.hasStableIds();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.f8340b.getView(i, view, viewGroup);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return this.f8340b.getItemViewType(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return this.f8340b.getViewTypeCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean isEmpty() {
        return this.f8340b.isEmpty();
    }
}
