package com.sds.android.ttpod.component.p087d;

import android.content.Context;
import android.view.View;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.PopupsListAdapter;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.b */
/* loaded from: classes.dex */
public class MultiChoicePopupsListAdapter extends PopupsListAdapter<CheckableActionItem> {
    public MultiChoicePopupsListAdapter(Context context, List<CheckableActionItem> list) {
        super(context, list);
    }

    @Override // com.sds.android.ttpod.component.p087d.PopupsListAdapter
    /* renamed from: a */
    protected int mo6685a() {
        return R.layout.popups_list_item;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.p087d.PopupsListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo6684a(PopupsListAdapter.C1171a c1171a, CheckableActionItem checkableActionItem) {
        c1171a.m6763e().setVisibility(View.VISIBLE);
        c1171a.m6763e().setImageResource(checkableActionItem.isChecked() ? R.drawable.icon_setting_menu_choice : R.drawable.icon_setting_menu_unchoice);
    }
}
