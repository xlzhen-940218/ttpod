package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;

/* renamed from: com.sds.android.ttpod.component.d.a.h */
/* loaded from: classes.dex */
public class MessageDialog extends ScrollableDialog {

    /* renamed from: a */
    private TextView f3963a;

    public MessageDialog(Context context, int i, int i2, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a) {
        super(context);
        this.f3963a.setText(i);
        m7254b(i2, interfaceC1064a);
    }

    public MessageDialog(Context context, String str, int i, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a) {
        super(context);
        this.f3963a.setText(str);
        m7254b(i, interfaceC1064a);
        m7244e(true);
    }

    public MessageDialog(Context context, int i, int i2, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a, int i3, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a2) {
        super(context);
        this.f3963a.setText(i);
        m7261a(i2, interfaceC1064a, i3, interfaceC1064a2);
    }

    public MessageDialog(Context context, CharSequence charSequence, int i, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a, int i2, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a2) {
        super(context);
        this.f3963a.setText(charSequence);
        m7261a(i, interfaceC1064a, i2, interfaceC1064a2);
    }

    public MessageDialog(Context context, int i, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a2) {
        this(context, context.getString(i), interfaceC1064a, interfaceC1064a2);
    }

    public MessageDialog(Context context, String str, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a2) {
        super(context);
        this.f3963a.setText(str);
        m7261a(R.string.ok, interfaceC1064a, R.string.cancel, interfaceC1064a2);
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.ScrollableDialog
    /* renamed from: b */
    protected View mo6793b(Context context) {
        this.f3963a = (TextView) View.inflate(context, R.layout.dialog_body_message, null);
        return this.f3963a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: b */
    public MessageDialog mo2037a() {
        return this;
    }
}
