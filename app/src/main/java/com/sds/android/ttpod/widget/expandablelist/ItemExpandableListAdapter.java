package com.sds.android.ttpod.widget.expandablelist;

import android.view.View;
import android.widget.ListAdapter;

/* renamed from: com.sds.android.ttpod.widget.expandablelist.b */
/* loaded from: classes.dex */
public class ItemExpandableListAdapter extends AbstractExpandableListAdapter {

    /* renamed from: a */
    private int f8338a;

    /* renamed from: c */
    private int f8339c;

    public ItemExpandableListAdapter(ListAdapter listAdapter, int i, int i2) {
        super(listAdapter);
        if (i < 0) {
            throw new IllegalArgumentException("toggleButtonId can NOT be negative");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("expandableViewId can NOT be negative");
        }
        this.f8338a = i;
        this.f8339c = i2;
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter
    /* renamed from: a */
    public View mo1258a(View view) {
        if (this.f8338a > 0) {
            return view.findViewById(this.f8338a);
        }
        return null;
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter
    /* renamed from: b */
    public View mo1257b(View view) {
        if (this.f8339c > 0) {
            return view.findViewById(this.f8339c);
        }
        return null;
    }
}
