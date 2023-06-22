package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.ActionListAdapter;
import com.sds.android.ttpod.component.p087d.PopupsListAdapter;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.a.a */
/* loaded from: classes.dex */
public class ActionListDialog extends ListDialog<ActionItem> {

    /* renamed from: a */
    private InterfaceC1142a f3875a;

    /* renamed from: b */
    private ActionListAdapter f3876b;

    /* compiled from: ActionListDialog.java */
    /* renamed from: com.sds.android.ttpod.component.d.a.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1142a {
        /* renamed from: a */
        void mo5775a(ActionItem actionItem);
    }

    public ActionListDialog(Context context, List<ActionItem> list, InterfaceC1142a interfaceC1142a, BaseDialog.InterfaceC1064a<? extends ListDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<? extends ListDialog> interfaceC1064a2) {
        super(context, list, interfaceC1064a, interfaceC1064a2);
        this.f3875a = interfaceC1142a;
        this.f3876b.m6907a(this.f3875a);
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.ListDialog
    /* renamed from: a */
    protected PopupsListAdapter<ActionItem> mo6782a(Context context, List<ActionItem> list) {
        this.f3876b = new ActionListAdapter(context, list);
        return this.f3876b;
    }
}
