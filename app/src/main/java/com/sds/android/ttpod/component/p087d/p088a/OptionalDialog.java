package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ScrollView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;

/* renamed from: com.sds.android.ttpod.component.d.a.l */
/* loaded from: classes.dex */
public abstract class OptionalDialog extends BaseDialog {

    /* renamed from: a */
    private ScrollView f3975a;

    /* renamed from: b */
    private CheckedTextView f3976b;

    /* renamed from: c */
    private View.OnClickListener f3977c;

    /* renamed from: b */
    protected abstract View mo6699b(Context context);

    public OptionalDialog(Context context, int i, int i2, OnClickListener<OptionalDialog> onClickListener, int i3, OnClickListener<OptionalDialog> onClickListener2) {
        super(context);
        m7261a(i2, onClickListener, i3, onClickListener2);
        this.f3976b.setText(i);
        this.f3976b.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.l.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OptionalDialog.this.f3976b.setChecked(!OptionalDialog.this.f3976b.isChecked());
                if (OptionalDialog.this.f3977c != null) {
                    OptionalDialog.this.f3977c.onClick(view);
                }
            }
        });
    }

    public OptionalDialog(Context context, int i, OnClickListener<OptionalDialog> onClickListener, OnClickListener<OptionalDialog> onClickListener2) {
        this(context, i, R.string.ok, onClickListener, R.string.cancel, onClickListener2);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected final View inflate(Context context) {
        View inflate = View.inflate(context, R.layout.dialog_body_option, null);
        this.f3976b = (CheckedTextView) inflate.findViewById(R.id.option);
        this.f3975a = (ScrollView) inflate.findViewById(R.id.scroll_content);
        View mo6699b = mo6699b(context);
        if (mo6699b != null) {
            this.f3975a.addView(mo6699b);
        }
        return inflate;
    }

    /* renamed from: b */
    public boolean m6808b() {
        return this.f3976b.isChecked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: c */
    public OptionalDialog getDialog() {
        return this;
    }
}
