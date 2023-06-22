package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.utils.ClipboardUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.a.b */
/* loaded from: classes.dex */
public class EditTextDialog extends ScrollableDialog {

    /* renamed from: a */
    private ViewGroup f3877a;

    /* renamed from: b */
    private int f3878b;

    /* renamed from: c */
    private List<EditText> f3879c;

    public EditTextDialog(Context context, C1144a[] c1144aArr, int i, BaseDialog.InterfaceC1064a<EditTextDialog> interfaceC1064a, int i2, BaseDialog.InterfaceC1064a<EditTextDialog> interfaceC1064a2) {
        super(context);
        this.f3879c = new ArrayList();
        if (c1144aArr != null) {
            this.f3878b = c1144aArr.length;
            if (this.f3878b > 0) {
                for (C1144a c1144a : c1144aArr) {
                    m6904a(c1144a);
                }
            }
        }
        m7261a(i, interfaceC1064a, i2, interfaceC1064a2);
    }

    public EditTextDialog(Context context, C1144a[] c1144aArr, int i, BaseDialog.InterfaceC1064a<EditTextDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<EditTextDialog> interfaceC1064a2) {
        this(context, c1144aArr, i, interfaceC1064a, R.string.cancel, interfaceC1064a2);
    }

    @Override // com.sds.android.ttpod.component.p087d.p088a.ScrollableDialog
    /* renamed from: b */
    protected View mo6793b(Context context) {
        this.f3877a = (ViewGroup) View.inflate(context, R.layout.dialog_body_edittext, null);
        return this.f3877a;
    }

    /* renamed from: a */
    private void m6904a(C1144a c1144a) {
        if (c1144a != null) {
            View inflate = View.inflate(getContext(), R.layout.dialog_body_edittext_item, null);
            EditText editText = (EditText) inflate.findViewById(R.id.input_text);
            editText.setTag(c1144a);
            this.f3879c.add(editText);
            m6905a((TextView) inflate.findViewById(R.id.input_label), editText, c1144a);
            if (c1144a.f3889h) {
                editText.setVisibility(View.GONE);
                final TextView textView = (TextView) inflate.findViewById(R.id.tv_text);
                textView.setText(c1144a.m6896d());
                textView.setVisibility(View.VISIBLE);
                TextView textView2 = (TextView) inflate.findViewById(R.id.tv_copy);
                textView2.setVisibility(View.VISIBLE);
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.b.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ClipboardUtils.m8306a(BaseApplication.getApplication(), textView.getText());
                        PopupsUtils.m6721a("已复制到剪贴板");
                    }
                };
                textView.setOnClickListener(onClickListener);
                textView2.setOnClickListener(onClickListener);
            }
            this.f3877a.addView(inflate);
        }
    }

    /* renamed from: a */
    private void m6905a(TextView textView, EditText editText, C1144a c1144a) {
        editText.setImeOptions(this.f3878b == this.f3879c.size() ? 6 : 5);
        editText.setText(c1144a.m6896d());
        editText.setSelection(c1144a.m6896d().length());
        editText.setHint(c1144a.m6895e());
        editText.setInputType(c1144a.m6894f());
        editText.setGravity(c1144a.m6893g());
        if (c1144a.m6892h() > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(c1144a.m6892h())});
        }
        CharSequence m6897c = c1144a.m6897c();
        if (TextUtils.isEmpty(m6897c)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(m6897c);
        }
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.f3879c.clear();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: b */
    public EditTextDialog mo2037a() {
        return this;
    }

    /* renamed from: c */
    public C1144a m6902c(int i) {
        for (EditText editText : this.f3879c) {
            C1144a c1144a = (C1144a) editText.getTag();
            if (c1144a.m6898b() == i) {
                c1144a.m6899a(editText.getText());
                return c1144a;
            }
        }
        return null;
    }

    /* compiled from: EditTextDialog.java */
    /* renamed from: com.sds.android.ttpod.component.d.a.b$a */
    /* loaded from: classes.dex */
    public static class C1144a {

        /* renamed from: a */
        private int f3882a;

        /* renamed from: b */
        private CharSequence f3883b;

        /* renamed from: c */
        private CharSequence f3884c;

        /* renamed from: d */
        private CharSequence f3885d;

        /* renamed from: e */
        private int f3886e;

        /* renamed from: f */
        private int f3887f;

        /* renamed from: g */
        private int f3888g;

        /* renamed from: h */
        private boolean f3889h;

        /* renamed from: a */
        public C1144a m6901a() {
            this.f3889h = true;
            return this;
        }

        public C1144a(int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
            this(i, charSequence, charSequence2, charSequence3, 1, 19);
        }

        public C1144a(int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, int i3) {
            this(i, charSequence, charSequence2, charSequence3, i2, i3, -1);
        }

        public C1144a(int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, int i3, int i4) {
            this.f3886e = 1;
            this.f3887f = 19;
            this.f3882a = i;
            this.f3883b = charSequence;
            this.f3884c = charSequence2;
            this.f3885d = charSequence3;
            this.f3886e = i2;
            this.f3887f = i3;
            this.f3888g = i4;
        }

        /* renamed from: b */
        public int m6898b() {
            return this.f3882a;
        }

        /* renamed from: c */
        public CharSequence m6897c() {
            return this.f3883b;
        }

        /* renamed from: d */
        public CharSequence m6896d() {
            return this.f3884c;
        }

        /* renamed from: a */
        public void m6899a(CharSequence charSequence) {
            this.f3884c = charSequence;
        }

        /* renamed from: e */
        public CharSequence m6895e() {
            return this.f3885d;
        }

        /* renamed from: f */
        public int m6894f() {
            return this.f3886e;
        }

        /* renamed from: g */
        public int m6893g() {
            return this.f3887f;
        }

        /* renamed from: h */
        public int m6892h() {
            return this.f3888g;
        }
    }
}
