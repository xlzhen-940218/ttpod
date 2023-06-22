package com.sds.android.ttpod.widget.expandablelist;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter;

/* loaded from: classes.dex */
public class ActionExpandableListView extends ItemExpandableListView {

    /* renamed from: a */
    private InterfaceC2282a f8327a;

    /* renamed from: b */
    private int[] f8328b;

    /* renamed from: com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2282a {
        /* renamed from: a */
        void m1262a(View view, View view2, int i);
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.ItemExpandableListView
    /* renamed from: e */
    public /* bridge */ /* synthetic */ int mo1260e() {
        return super.mo1260e();
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.ItemExpandableListView
    /* renamed from: f */
    public /* bridge */ /* synthetic */ boolean mo1259f() {
        return super.mo1259f();
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.ItemExpandableListView, android.widget.AbsListView, android.view.View
    public /* bridge */ /* synthetic */ void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.ItemExpandableListView, android.widget.AbsListView, android.view.View
    public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.ItemExpandableListView
    public /* bridge */ /* synthetic */ void setItemExpandCollapseListener(AbstractExpandableListAdapter.InterfaceC2279a interfaceC2279a) {
        super.setItemExpandCollapseListener(interfaceC2279a);
    }

    public ActionExpandableListView(Context context) {
        super(context);
        this.f8328b = null;
    }

    public ActionExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8328b = null;
    }

    public ActionExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8328b = null;
    }

    @Override // com.sds.android.ttpod.widget.DraggableListView, android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        mo1261a(listAdapter, 0, 0);
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.ItemExpandableListView
    /* renamed from: a */
    public void mo1261a(ListAdapter listAdapter, int i, int i2) {
        if (listAdapter != null) {
            super.mo1261a(new WrapperListAdapterImpl(listAdapter) { // from class: com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView.1
                @Override // com.sds.android.ttpod.widget.expandablelist.WrapperListAdapterImpl, android.widget.Adapter
                public View getView(final int i3, View view, ViewGroup viewGroup) {
                    int[] iArr;
                    final View view2 = this.f8340b.getView(i3, view, viewGroup);
                    if (ActionExpandableListView.this.f8328b != null && view2 != null) {
                        for (int i4 : ActionExpandableListView.this.f8328b) {
                            View findViewById = view2.findViewById(i4);
                            if (findViewById != null) {
                                findViewById.findViewById(i4).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView.1.1
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view3) {
                                        if (ActionExpandableListView.this.f8327a != null) {
                                            ActionExpandableListView.this.f8327a.m1262a(view2, view3, i3);
                                        }
                                    }
                                });
                            }
                        }
                    }
                    return view2;
                }
            }, i, i2);
        } else {
            super.mo1261a((ListAdapter) null, i, i2);
        }
    }
}
