package com.sds.android.ttpod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.adapter.e */
/* loaded from: classes.dex */
public abstract class SectionListAdapter extends BaseAdapter {

    /* renamed from: b */
    private ArrayList<Integer> f3242b = new ArrayList<>();

    /* renamed from: c */
    private int f3243c = 0;

    /* renamed from: a */
    protected LayoutInflater f3241a = LayoutInflater.from(BaseApplication.getApplication());

    /* renamed from: a */
    protected abstract int mo5528a();

    /* renamed from: a */
    protected abstract int mo5527a(int i);

    /* renamed from: a */
    protected abstract View mo5523a(ViewGroup viewGroup);

    /* renamed from: a */
    protected abstract Object mo5526a(int i, int i2);

    /* renamed from: a */
    protected abstract void mo5525a(int i, int i2, View view);

    /* renamed from: a */
    protected abstract void mo5524a(int i, View view);

    /* renamed from: b */
    protected abstract View mo5519b(ViewGroup viewGroup);

    /* renamed from: c */
    protected abstract Object mo5517c(int i);

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f3243c;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        if (m7569d(i)) {
            return mo5517c(m7568e(i));
        }
        int m7568e = m7568e(i);
        return mo5526a(m7568e, m7571b(i, m7568e));
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        return m7569d(i) ? m7573a(i, view, viewGroup) : m7570b(i, view, viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void m7572b() {
        int mo5528a = mo5528a();
        this.f3242b.clear();
        int i = 0;
        for (int i2 = 0; i2 < mo5528a; i2++) {
            this.f3242b.add(Integer.valueOf(i));
            i = i + 1 + mo5527a(i2);
        }
        this.f3243c = i;
    }

    /* renamed from: a */
    protected final View m7573a(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mo5523a(viewGroup);
            view.setTag(R.id.tag_view_key, "section");
        } else if ("section" != view.getTag(R.id.tag_view_key)) {
            view = mo5523a(viewGroup);
            view.setTag(R.id.tag_view_key, "section");
        }
        mo5524a(m7568e(i), view);
        return view;
    }

    /* renamed from: b */
    protected final View m7570b(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mo5519b(viewGroup);
            view.setTag(R.id.tag_view_key, "subItem");
        } else if ("subItem" != view.getTag(R.id.tag_view_key)) {
            view = mo5519b(viewGroup);
            view.setTag(R.id.tag_view_key, "subItem");
        }
        int m7568e = m7568e(i);
        mo5525a(m7568e, m7571b(i, m7568e), view);
        return view;
    }

    /* renamed from: d */
    protected boolean m7569d(int i) {
        return this.f3242b.contains(Integer.valueOf(i));
    }

    /* renamed from: e */
    protected int m7568e(int i) {
        int size = this.f3242b.size();
        int i2 = 0;
        while (i2 < size - 1) {
            if (i >= this.f3242b.get(i2).intValue() && i < this.f3242b.get(i2 + 1).intValue()) {
                return i2;
            }
            i2++;
        }
        return i2;
    }

    /* renamed from: b */
    protected int m7571b(int i, int i2) {
        return (i - this.f3242b.get(i2).intValue()) - 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public int m7567f(int i) {
        if (i < this.f3242b.size()) {
            return this.f3242b.get(i).intValue();
        }
        return -1;
    }
}
