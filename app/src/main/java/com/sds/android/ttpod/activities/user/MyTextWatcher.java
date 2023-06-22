package com.sds.android.ttpod.activities.user;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.AutoCompleteView;

/* renamed from: com.sds.android.ttpod.activities.user.b */
/* loaded from: classes.dex */
public class MyTextWatcher implements TextWatcher {

    /* renamed from: a */
    private AutoCompleteView f3096a;

    /* renamed from: b */
    private EditText f3097b;

    /* renamed from: c */
    private String[] f3098c;

    public MyTextWatcher(Context context, EditText editText, AutoCompleteView autoCompleteView) {
        this.f3098c = context.getResources().getStringArray(R.array.mailDomain);
        this.f3097b = editText;
        this.f3096a = autoCompleteView;
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* renamed from: a */
    private void m7720a() {
        if (this.f3096a != null) {
            this.f3096a.m1431c();
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        String[] strArr = this.f3098c;
        String obj = editable.toString();
        if (obj.contains("@")) {
            String substring = obj.substring(0, obj.indexOf("@"));
            String substring2 = obj.substring(obj.indexOf("@"));
            this.f3096a.m1442a();
            int length = this.f3098c.length;
            for (int i = 0; i < length; i++) {
                String str = "@" + strArr[i];
                if (str.equals(substring2)) {
                    m7720a();
                    return;
                }
                if (str.startsWith(substring2)) {
                    this.f3096a.m1435a(substring + str);
                }
            }
            this.f3096a.m1433b();
            this.f3096a.m1438a(this.f3097b);
            return;
        }
        m7720a();
    }
}
