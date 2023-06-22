package com.sds.android.ttpod.component.p087d;

import android.content.Context;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsListAdapter;
import com.sds.android.ttpod.component.p087d.p088a.ActionListDialog;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.a */
/* loaded from: classes.dex */
public class ActionListAdapter extends PopupsListAdapter<ActionItem> {

    /* renamed from: a */
    private ActionListDialog.InterfaceC1142a f3872a;

    /* renamed from: b */
    private View.OnClickListener f3873b;

    public ActionListAdapter(Context context, List<ActionItem> list) {
        super(context, list);
        this.f3873b = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActionItem actionItem = (ActionItem) view.getTag();
                List<ActionItem> b = ActionListAdapter.this.m6768b();
                b.remove(actionItem);
                ActionListAdapter.this.m6769a(b);
                if (ActionListAdapter.this.f3872a != null) {
                    ActionListAdapter.this.f3872a.mo5775a(actionItem);
                }
            }
        };
    }

    /* renamed from: a */
    public void m6907a(ActionListDialog.InterfaceC1142a interfaceC1142a) {
        this.f3872a = interfaceC1142a;
    }

    @Override // com.sds.android.ttpod.component.p087d.PopupsListAdapter
    /* renamed from: a */
    protected int mo6685a() {
        return R.layout.popups_action_list_item;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.p087d.PopupsListAdapter
    /* renamed from: a */
    public void mo6770a(PopupsListAdapter.C1171a c1171a) {
        super.mo6770a(c1171a);
        c1171a.m6767a().setOnClickListener(this.f3873b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.component.p087d.PopupsListAdapter
    /* renamed from: a */
    public void mo6684a(PopupsListAdapter.C1171a c1171a, ActionItem actionItem) {
        super.mo6684a(c1171a,  actionItem);
        c1171a.m6767a().setTag(actionItem);
    }
}
