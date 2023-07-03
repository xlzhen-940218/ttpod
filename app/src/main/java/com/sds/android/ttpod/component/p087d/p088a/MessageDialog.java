package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* renamed from: com.sds.android.ttpod.component.d.a.h */
/* loaded from: classes.dex */
public class MessageDialog extends ScrollableDialog {

    /* renamed from: a */
    private TextView f3963a;

    public MessageDialog(Context context, int i, int i2, OnClickListener<MessageDialog> onClickListener) {
        super(context);
        this.f3963a.setText(i);
        m7254b(i2, onClickListener);
    }

    public MessageDialog(Context context, String str, int i, OnClickListener<MessageDialog> onClickListener) {
        super(context);
        this.f3963a.setText(str);
        m7254b(i, onClickListener);
        m7244e(true);
    }

    public MessageDialog(Context context, int i, int i2, OnClickListener<MessageDialog> onClickListener, int i3, OnClickListener<MessageDialog> onClickListener2) {
        super(context);
        this.f3963a.setText(i);
        m7261a(i2, onClickListener, i3, onClickListener2);
    }

    public MessageDialog(Context context, CharSequence charSequence, int i, OnClickListener<MessageDialog> onClickListener, int i2, OnClickListener<MessageDialog> onClickListener2) {
        super(context);
        this.f3963a.setText(charSequence);
        m7261a(i, onClickListener, i2, onClickListener2);
    }

    public MessageDialog(Context context, int i, OnClickListener<MessageDialog> onClickListener, OnClickListener<MessageDialog> onClickListener2) {
        this(context, context.getString(i), onClickListener, onClickListener2);
    }

    public MessageDialog(Context context, String str, OnClickListener<MessageDialog> onClickListener, OnClickListener<MessageDialog> onClickListener2) {
        super(context);
        this.f3963a.setText(str);
        m7261a(R.string.ok, onClickListener, R.string.cancel, onClickListener2);
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
    public MessageDialog getDialog() {
        return this;
    }
}
