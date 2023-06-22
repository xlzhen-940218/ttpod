package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.PopupsListAdapter;
import com.sds.android.ttpod.component.p087d.SingleChoicePopupsListAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.a.q */
/* loaded from: classes.dex */
public class SingleChoiceListDialog extends ListDialog<CheckableActionItem> {

    /* renamed from: a */
    private SingleChoicePopupsListAdapter f4000a;

    public SingleChoiceListDialog(Context context, List<CheckableActionItem> list, BaseDialog.InterfaceC1064a<SingleChoiceListDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<SingleChoiceListDialog> interfaceC1064a2) {
        super(context, list, interfaceC1064a, interfaceC1064a2);
    }

    public SingleChoiceListDialog(Context context, CheckableActionItem[] checkableActionItemArr, BaseDialog.InterfaceC1064a<SingleChoiceListDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<SingleChoiceListDialog> interfaceC1064a2) {
        this(context, new ArrayList(Arrays.asList(checkableActionItemArr)), interfaceC1064a, interfaceC1064a2);
    }

    public SingleChoiceListDialog(Context context, CheckableActionItem[] checkableActionItemArr, BaseDialog.InterfaceC1064a<SingleChoiceListDialog> interfaceC1064a) {
        super(context, checkableActionItemArr, (int) R.string.cancel, interfaceC1064a);
    }

    /* renamed from: c */
    public void m6778c(int i) {
        this.f4000a.m6682b(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.p087d.p088a.ListDialog
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo6781a(CheckableActionItem checkableActionItem, int i) {
        m6778c(checkableActionItem.m7005e());
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.ListDialog
    /* renamed from: a */
    protected PopupsListAdapter<CheckableActionItem> mo6782a(Context context, List<CheckableActionItem> list) {
        this.f4000a = new SingleChoicePopupsListAdapter(context, list) { // from class: com.sds.android.ttpod.component.d.a.q.1
            @Override // com.sds.android.ttpod.component.p087d.PopupsListAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                return super.getView(i, view, viewGroup);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.ttpod.component.p087d.SingleChoicePopupsListAdapter, com.sds.android.ttpod.component.p087d.PopupsListAdapter
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo6684a(PopupsListAdapter.C1171a c1171a, CheckableActionItem checkableActionItem) {
                super.mo6684a(c1171a, checkableActionItem);
            }
        };
        return this.f4000a;
    }

    @Override
    public ListDialog mo2037a() {
        return this;
    }
}
