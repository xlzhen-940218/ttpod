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
    private D data;

    /* renamed from: b */
    protected Context context;

    /* renamed from: c */
    protected LayoutInflater layoutInflater;

    /* renamed from: d */
    protected List<D> dataList;

    /* renamed from: a */
    protected abstract View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* renamed from: a */
    protected abstract void buildDataUI(View view, D d, int i);

    public BaseListAdapter(Context context, List<D> list) {
        if (list == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.context = context == null ? BaseApplication.getApplication() : context;
        this.dataList = list;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    public BaseListAdapter() {
        this(BaseApplication.getApplication(), new ArrayList());
    }

    /* renamed from: a */
    public Context getContext() {
        return this.context;
    }

    /* renamed from: b */
    public List<D> getDataList() {
        return this.dataList;
    }

    /* renamed from: a */
    public void setDataList(List<D> list) {
        if (list == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.dataList = list;
        notifyDataSetChanged();
    }

    /* renamed from: c */
    public D getData() {
        return this.data;
    }

    /* renamed from: a */
    public void setData(D d) {
        this.data = d;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.dataList.size();
    }

    @Override // android.widget.Adapter
    public D getItem(int i) {
        return this.dataList.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = getConvertView(this.layoutInflater, viewGroup);
        }
        buildDataUI(view, getItem(i), i);
        return view;
    }
}
