package com.sds.android.ttpod.activities.unicomflow;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.p088a.ScrollableDialog;

/* renamed from: com.sds.android.ttpod.activities.unicomflow.a */
/* loaded from: classes.dex */
public class UnicomDialog extends ScrollableDialog {

    /* renamed from: a */
    private TextView f3048a;

    /* renamed from: b */
    private TextView f3049b;

    /* renamed from: c */
    private TextView f3050c;

    /* renamed from: d */
    private CheckBox f3051d;

    /* renamed from: e */
    private DialogInterface.OnCancelListener f3052e;

    public UnicomDialog(Context context, String str, int i, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a, int i2, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a2, View.OnClickListener onClickListener) {
        this(context, str, (String) null, i, interfaceC1064a, i2, interfaceC1064a2, onClickListener);
        setOnCancelListener(this.f3052e);
    }

    public UnicomDialog(Context context, String str, int i, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a, int i2, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a2, boolean z, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        super(context);
        this.f3052e = new DialogInterface.OnCancelListener() { // from class: com.sds.android.ttpod.activities.unicomflow.a.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        };
        this.f3048a.setText(str);
        m7261a(i, interfaceC1064a, i2, interfaceC1064a2);
        if (z) {
            this.f3051d.setVisibility(View.VISIBLE);
            this.f3051d.setOnCheckedChangeListener(onCheckedChangeListener);
        } else {
            this.f3051d.setVisibility(View.GONE);
        }
        setOnCancelListener(this.f3052e);
    }

    public UnicomDialog(Context context, String str, String str2, int i, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a, int i2, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a2, View.OnClickListener onClickListener) {
        super(context);
        this.f3052e = new DialogInterface.OnCancelListener() { // from class: com.sds.android.ttpod.activities.unicomflow.a.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        };
        this.f3048a.setText(str);
        m7261a(i, interfaceC1064a, i2, interfaceC1064a2);
        if (!StringUtils.m8346a(str2)) {
            this.f3049b.setText(str2);
            this.f3049b.setVisibility(View.VISIBLE);
        } else {
            this.f3049b.setVisibility(View.GONE);
        }
        if (onClickListener != null) {
            this.f3050c.setVisibility(View.VISIBLE);
            this.f3050c.setOnClickListener(onClickListener);
        } else {
            this.f3050c.setVisibility(View.GONE);
        }
        setOnCancelListener(this.f3052e);
    }

    /* renamed from: b */
    public boolean m7768b() {
        return this.f3051d.isChecked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: c */
    public UnicomDialog mo2037a() {
        return this;
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.ScrollableDialog
    /* renamed from: b */
    protected View mo6793b(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.popups_unicom_flow_dialog, (ViewGroup) null);
        this.f3048a = (TextView) inflate.findViewById(R.id.textview_phone);
        this.f3049b = (TextView) inflate.findViewById(R.id.textview_title);
        this.f3050c = (TextView) inflate.findViewById(R.id.textview_more);
        this.f3051d = (CheckBox) inflate.findViewById(R.id.check_dialog);
        return inflate;
    }
}
