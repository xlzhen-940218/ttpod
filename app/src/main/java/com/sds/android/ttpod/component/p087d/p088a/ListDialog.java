package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsListAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.a.e */
/* loaded from: classes.dex */
public class ListDialog<M extends ActionItem> extends BaseDialog {

    /* renamed from: a */
    private ActionItem.InterfaceC1135b f3941a;

    /* renamed from: b */
    private PopupsListAdapter<M> f3942b;

    /* renamed from: c */
    private ListView f3943c;

    /* renamed from: d */
    private M f3944d;

    public ListDialog(Context context, List<M> list, int i, OnClickListener<? extends ListDialog> onClickListener, int i2, OnClickListener<? extends ListDialog> onClickListener2) {
        super(context);
        m7261a(i, onClickListener, i2, onClickListener2);
        this.f3942b = mo6782a(context, list);
        m6839e();
        this.f3943c.setAdapter((ListAdapter) this.f3942b);
    }

    public ListDialog(Context context, M[] mArr, int i, OnClickListener<? extends ListDialog> onClickListener, int i2, OnClickListener<? extends ListDialog> onClickListener2) {
        this(context, new ArrayList(Arrays.asList(mArr)), i, onClickListener, i2, onClickListener2);
    }

    public ListDialog(Context context, List<M> list, OnClickListener<? extends ListDialog> onClickListener, OnClickListener<? extends ListDialog> onClickListener2) {
        this(context, list, (int) R.string.ok, onClickListener, (int) R.string.cancel, onClickListener2);
    }

    public ListDialog(Context context, M[] mArr, OnClickListener<? extends ListDialog> onClickListener, OnClickListener<? extends ListDialog> onClickListener2) {
        this(context, mArr, (int) R.string.ok, onClickListener, (int) R.string.cancel, onClickListener2);
    }

    public ListDialog(Context context, M[] mArr, int i, OnClickListener<? extends ListDialog> onClickListener) {
        super(context);
        m7254b(i, onClickListener);
        this.f3942b = mo6782a(context, new ArrayList(Arrays.asList(mArr)));
        m6839e();
        this.f3943c.setAdapter((ListAdapter) this.f3942b);
    }

    /* renamed from: a */
    protected void mo6781a(M m, int i) {
    }

    /* renamed from: a */
    public void m6844a(ActionItem.InterfaceC1135b interfaceC1135b) {
        this.f3941a = interfaceC1135b;
    }

    /* renamed from: a */
    protected PopupsListAdapter<M> mo6782a(Context context, List<M> list) {
        return new PopupsListAdapter<>(context, list);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View inflate(Context context) {
        this.f3943c = (ListView) View.inflate(context, R.layout.popups_body_list, null);
        this.f3943c.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.component.d.a.e.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ListDialog.this.f3944d = ListDialog.this.f3942b.getItem(i);
                if (ListDialog.this.f3941a != null) {
                    ListDialog.this.f3941a.mo5409a(ListDialog.this.f3944d, i);
                }
                ListDialog.this.mo6781a(f3944d, i);
                ListDialog.this.f3942b.notifyDataSetChanged();
            }
        });
        return this.f3943c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: c */
    public ListDialog getDialog() {
        return this;
    }

    /* renamed from: e */
    private void m6839e() {
        View mo6702b = mo6702b();
        if (mo6702b != null) {
            this.f3943c.addFooterView(mo6702b);
        }
    }

    /* renamed from: b */
    protected View mo6702b() {
        return null;
    }
}
