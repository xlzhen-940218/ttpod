package com.sds.android.ttpod.widget.expandablelist;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import com.sds.android.ttpod.widget.DraggableListView;
import com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter;

/* loaded from: classes.dex */
class ItemExpandableListView extends DraggableListView {

    /* renamed from: a */
    private ItemExpandableListAdapter f8333a;

    public ItemExpandableListView(Context context) {
        super(context, null);
    }

    public ItemExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ItemExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
    }

    /* renamed from: f */
    public boolean mo1259f() {
        return this.f8333a != null && this.f8333a.m1280a(false);
    }

    /* renamed from: e */
    public int mo1260e() {
        if (this.f8333a != null) {
            return this.f8333a.m1292a();
        }
        return -1;
    }

    /* renamed from: a */
    public void mo1261a(ListAdapter listAdapter, int i, int i2) {
        this.f8333a = listAdapter != null ? new ItemExpandableListAdapter(listAdapter, i, i2) : null;
        super.setAdapter((ListAdapter) this.f8333a);
    }

    public void setItemExpandCollapseListener(AbstractExpandableListAdapter.InterfaceC2279a interfaceC2279a) {
        this.f8333a.m1283a(interfaceC2279a);
    }

    @Override // android.widget.AbsListView, android.view.View
    public Parcelable onSaveInstanceState() {
        return this.f8333a.m1288a(super.onSaveInstanceState());
    }

    @Override // android.widget.AbsListView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof AbstractExpandableListAdapter.SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        AbstractExpandableListAdapter.SavedState savedState = (AbstractExpandableListAdapter.SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f8333a.m1284a(savedState);
    }
}
