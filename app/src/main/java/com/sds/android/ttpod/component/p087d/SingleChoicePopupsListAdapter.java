package com.sds.android.ttpod.component.p087d;

import android.content.Context;
import android.view.View;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.PopupsListAdapter;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.e */
/* loaded from: classes.dex */
public class SingleChoicePopupsListAdapter extends PopupsListAdapter<CheckableActionItem> {

    /* renamed from: a */
    private int f4077a;

    public SingleChoicePopupsListAdapter(Context context, List<CheckableActionItem> list) {
        super(context, list);
    }

    /* renamed from: b */
    public void m6682b(int i) {
        this.f4077a = i;
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.component.p087d.PopupsListAdapter
    /* renamed from: a */
    protected int mo6685a() {
        return R.layout.popups_list_item;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sds.android.ttpod.component.p087d.PopupsListAdapter
    /* renamed from: a */
    public void mo6684a(PopupsListAdapter.C1171a c1171a, CheckableActionItem checkableActionItem) {
        if (this.f4077a == checkableActionItem.m7005e()) {
            c1171a.m6763e().setImageResource(R.drawable.img_setting_single_choice_checked);
            c1171a.m6763e().setVisibility(View.VISIBLE);
            return;
        }
        c1171a.m6763e().setVisibility(View.INVISIBLE);
    }
}
