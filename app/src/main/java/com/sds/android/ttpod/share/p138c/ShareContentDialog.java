package com.sds.android.ttpod.share.p138c;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.ShareType;
import com.sds.android.ttpod.share.p136a.ApiCallback;
import com.sds.android.ttpod.share.p136a.BaseApi;
import com.sds.android.ttpod.share.p139d.ShareContentUtil;

/* renamed from: com.sds.android.ttpod.share.c.b */
/* loaded from: classes.dex */
public class ShareContentDialog extends BaseDialog {

    /* renamed from: a */
    private BaseApi f7380a;

    /* renamed from: b */
    private ShareInfo f7381b;

    /* renamed from: c */
    private ApiCallback f7382c;

    /* renamed from: d */
    private TextView f7383d;

    /* renamed from: e */
    private EditText f7384e;

    /* renamed from: f */
    private ShareType f7385f;

    /* renamed from: g */
    private BaseDialog.InterfaceC1064a f7386g;

    /* renamed from: h */
    private BaseDialog.InterfaceC1064a f7387h;

    /* renamed from: i */
    private TextWatcher f7388i;

    public ShareContentDialog(Context context, BaseApi baseApi, ApiCallback apiCallback) {
        super(context);
        this.f7385f = ShareType.NONE;
        this.f7386g = new BaseDialog.InterfaceC1064a() { // from class: com.sds.android.ttpod.share.c.b.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a */
            public void mo2038a(Object obj) {
                ShareContentDialog.this.m2042c();
            }
        };
        this.f7387h = new BaseDialog.InterfaceC1064a() { // from class: com.sds.android.ttpod.share.c.b.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a */
            public void mo2038a(Object obj) {
                ShareContentDialog.this.dismiss();
            }
        };
        this.f7388i = new TextWatcher() { // from class: com.sds.android.ttpod.share.c.b.3

            /* renamed from: b */
            private int f7392b;

            /* renamed from: c */
            private int f7393c;

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                this.f7392b = ShareContentDialog.this.f7384e.getSelectionStart();
                this.f7393c = ShareContentDialog.this.f7384e.getSelectionEnd();
                ShareContentDialog.this.f7384e.removeTextChangedListener(ShareContentDialog.this.f7388i);
                int m2045a = 140 - ShareContentDialog.this.m2045a((CharSequence) ShareContentDialog.this.f7381b.m1958e());
                while (ShareContentDialog.this.m2045a((CharSequence) editable.toString()) > m2045a && this.f7392b != 0 && this.f7393c != 0) {
                    editable.delete(this.f7392b - 1, this.f7393c);
                    this.f7392b--;
                    this.f7393c--;
                }
                ShareContentDialog.this.f7384e.setSelection(this.f7392b);
                ShareContentDialog.this.f7384e.addTextChangedListener(ShareContentDialog.this.f7388i);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        m7261a(R.string.share, this.f7386g, R.string.cancel, this.f7387h);
        this.f7380a = baseApi;
        this.f7382c = apiCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m2042c() {
        String obj = this.f7384e.getText().toString();
        if (this.f7381b.m1942p()) {
            this.f7381b.m1959d(obj + " " + this.f7381b.m1958e());
        } else {
            this.f7381b.m1959d(m2040c(obj));
        }
        this.f7380a.mo2096a(this.f7381b, this.f7382c);
        dismiss();
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View mo2034a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.share_content_dialog, (ViewGroup) null);
        this.f7383d = (TextView) inflate.findViewById(R.id.textview_share_title);
        this.f7384e = (EditText) inflate.findViewById(R.id.edittext_share_content);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: b */
    public ShareContentDialog mo2037a() {
        return this;
    }

    /* renamed from: c */
    private String m2040c(String str) {
        return ShareContentUtil.m1933a(str, getContext().getString(R.string.share_text_tail_info), this.f7381b, this.f7385f);
    }

    /* renamed from: a */
    public void m2046a(ShareType shareType, ShareInfo shareInfo) {
        this.f7385f = shareType;
        this.f7381b = shareInfo;
        this.f7384e.removeTextChangedListener(this.f7388i);
        this.f7384e.setText("");
        this.f7384e.addTextChangedListener(this.f7388i);
        if (this.f7381b.m1942p()) {
            this.f7383d.setText(this.f7381b.m1958e());
            return;
        }
        this.f7383d.setText(ShareContentUtil.m1936a(this.f7381b));
        this.f7381b.m1959d(m2040c(""));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int m2045a(CharSequence charSequence) {
        double d;
        double d2 = 0.0d;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (charAt > 0 && charAt < 127) {
                d = 0.5d;
            } else {
                d = 1.0d;
            }
            d2 += d;
        }
        return (int) Math.round(d2);
    }
}
