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
    private ArrayList<Integer> sectionIndexList = new ArrayList<>();

    /* renamed from: c */
    private int f3243c = 0;

    /* renamed from: a */
    protected LayoutInflater layoutInflater = LayoutInflater.from(BaseApplication.getApplication());

    /* renamed from: a */
    protected abstract int mo5528a();

    /* renamed from: a */
    protected abstract int mo5527a(int i);

    /* renamed from: a */
    protected abstract View getSectionConvertView(ViewGroup viewGroup);

    /* renamed from: a */
    protected abstract Object mo5526a(int i, int i2);

    /* renamed from: a */
    protected abstract void mo5525a(int i, int i2, View view);

    /* renamed from: a */
    protected abstract void mo5524a(int i, View view);

    /* renamed from: b */
    protected abstract View getSubConvertView(ViewGroup viewGroup);

    /* renamed from: c */
    protected abstract Object mo5517c(int i);

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f3243c;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        if (isSectionIndex(i)) {
            return mo5517c(findSectionFormSubIndex(i));
        }
        int m7568e = findSectionFormSubIndex(i);
        return mo5526a(m7568e, m7571b(i, m7568e));
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int position, View view, ViewGroup viewGroup) {
        return isSectionIndex(position) ? getSectionView(position, view, viewGroup) : m7570b(position, view, viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void m7572b() {
        int mo5528a = mo5528a();
        this.sectionIndexList.clear();
        int i = 0;
        for (int i2 = 0; i2 < mo5528a; i2++) {
            this.sectionIndexList.add(Integer.valueOf(i));
            i = i + 1 + mo5527a(i2);
        }
        this.f3243c = i;
    }

    /* renamed from: a */
    protected final View getSectionView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = getSectionConvertView(viewGroup);
            view.setTag(R.id.tag_view_key, "section");
        } else if ("section" != view.getTag(R.id.tag_view_key)) {
            view = getSectionConvertView(viewGroup);
            view.setTag(R.id.tag_view_key, "section");
        }
        mo5524a(findSectionFormSubIndex(position), view);
        return view;
    }

    /* renamed from: b */
    protected final View m7570b(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = getSubConvertView(viewGroup);
            view.setTag(R.id.tag_view_key, "subItem");
        } else if ("subItem" != view.getTag(R.id.tag_view_key)) {
            view = getSubConvertView(viewGroup);
            view.setTag(R.id.tag_view_key, "subItem");
        }
        int sectionIndex = findSectionFormSubIndex(i);
        mo5525a(sectionIndex, m7571b(i, sectionIndex), view);
        return view;
    }

    /* renamed from: d */
    protected boolean isSectionIndex(int i) {
        return this.sectionIndexList.contains(Integer.valueOf(i));
    }

    /* renamed from: e */
    protected int findSectionFormSubIndex(int i) {
        int size = this.sectionIndexList.size();
        int i2 = 0;
        while (i2 < size - 1) {
            if (i >= this.sectionIndexList.get(i2).intValue() && i < this.sectionIndexList.get(i2 + 1).intValue()) {
                return i2;
            }
            i2++;
        }
        return i2;
    }

    /* renamed from: b */
    protected int m7571b(int i, int i2) {
        return (i - this.sectionIndexList.get(i2).intValue()) - 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public int m7567f(int i) {
        if (i < this.sectionIndexList.size()) {
            return this.sectionIndexList.get(i).intValue();
        }
        return -1;
    }
}
