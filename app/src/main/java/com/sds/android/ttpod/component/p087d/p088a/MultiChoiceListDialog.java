package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.MultiChoicePopupsListAdapter;
import com.sds.android.ttpod.component.p087d.PopupsListAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.a.j */
/* loaded from: classes.dex */
public class MultiChoiceListDialog extends ListDialog<CheckableActionItem> {

    /* renamed from: a */
    private MultiChoicePopupsListAdapter f3971a;

    public MultiChoiceListDialog(Context context, List<CheckableActionItem> list, BaseDialog.InterfaceC1064a<MultiChoiceListDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<MultiChoiceListDialog> interfaceC1064a2) {
        super(context, list, interfaceC1064a, interfaceC1064a2);
    }

    public MultiChoiceListDialog(Context context, CheckableActionItem[] checkableActionItemArr, BaseDialog.InterfaceC1064a<MultiChoiceListDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<MultiChoiceListDialog> interfaceC1064a2) {
        this(context, new ArrayList(Arrays.asList(checkableActionItemArr)), interfaceC1064a, interfaceC1064a2);
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.ListDialog
    /* renamed from: a */
    protected PopupsListAdapter<CheckableActionItem> mo6782a(Context context, List<CheckableActionItem> list) {
        this.f3971a = new MultiChoicePopupsListAdapter(context, list);
        return this.f3971a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.p087d.p088a.ListDialog
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo6781a(CheckableActionItem checkableActionItem, int i) {
        checkableActionItem.setChecked(!checkableActionItem.isChecked());
        this.f3971a.notifyDataSetChanged();
    }

    /* renamed from: e */
    public List<CheckableActionItem> m6813e() {
        List<CheckableActionItem> b = this.f3971a.m6768b();
        ArrayList arrayList = new ArrayList();
        for (CheckableActionItem checkableActionItem : b) {
            if (checkableActionItem.isChecked()) {
                arrayList.add(checkableActionItem);
            }
        }
        return arrayList;
    }

    @Override
    public ListDialog mo2037a() {
        return this;
    }
}
