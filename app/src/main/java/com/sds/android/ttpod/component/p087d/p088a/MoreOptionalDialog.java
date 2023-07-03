package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ScrollView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;

/* renamed from: com.sds.android.ttpod.component.d.a.i */
/* loaded from: classes.dex */
public abstract class MoreOptionalDialog extends BaseDialog {

    /* renamed from: a */
    private ScrollView f3964a;

    /* renamed from: b */
    private CheckedTextView f3965b;

    /* renamed from: c */
    private CheckedTextView f3966c;

    /* renamed from: d */
    private CheckedTextView f3967d;

    /* renamed from: e */
    private CheckedTextView f3968e;

    /* renamed from: f */
    private View.OnClickListener f3969f;

    /* renamed from: b */
    protected abstract View mo6698b(Context context);

    public MoreOptionalDialog(Context context, int i, OnClickListener<MoreOptionalDialog> onClickListener, int i2, OnClickListener<MoreOptionalDialog> onClickListener2) {
        super(context);
        m7261a(i, onClickListener, i2, onClickListener2);
        this.f3969f = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.i.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((CheckedTextView) view).toggle();
                boolean isChecked = ((CheckedTextView) view).isChecked();
                if (MoreOptionalDialog.this.f3965b == view && isChecked) {
                    MoreOptionalDialog.this.f3967d.setChecked(true);
                    MoreOptionalDialog.this.f3968e.setChecked(true);
                }
            }
        };
        this.f3965b.setOnClickListener(this.f3969f);
        this.f3966c.setOnClickListener(this.f3969f);
        this.f3967d.setOnClickListener(this.f3969f);
        this.f3968e.setOnClickListener(this.f3969f);
        this.f3967d.setChecked(true);
        this.f3968e.setChecked(true);
    }

    public MoreOptionalDialog(Context context, OnClickListener<MoreOptionalDialog> onClickListener, OnClickListener<MoreOptionalDialog> onClickListener2) {
        this(context, R.string.ok, onClickListener, R.string.cancel, onClickListener2);
    }

    /* renamed from: b */
    public boolean m6821b() {
        return this.f3965b.isChecked();
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected final View inflate(Context context) {
        View inflate = View.inflate(context, R.layout.dialog_body_more_option, null);
        this.f3965b = (CheckedTextView) inflate.findViewById(R.id.option_original_file);
        this.f3966c = (CheckedTextView) inflate.findViewById(R.id.option_artist_pic);
        this.f3967d = (CheckedTextView) inflate.findViewById(R.id.option_audio_profile);
        this.f3968e = (CheckedTextView) inflate.findViewById(R.id.option_lyric);
        this.f3964a = (ScrollView) inflate.findViewById(R.id.scroll_content);
        View mo6698b = mo6698b(context);
        if (mo6698b != null) {
            this.f3964a.addView(mo6698b);
        }
        return inflate;
    }

    /* renamed from: c */
    public boolean m6819c() {
        return this.f3966c.isChecked();
    }

    /* renamed from: e */
    public boolean m6817e() {
        return this.f3967d.isChecked();
    }

    /* renamed from: f */
    public boolean m6816f() {
        return this.f3968e.isChecked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: g */
    public MoreOptionalDialog getDialog() {
        return this;
    }
}
