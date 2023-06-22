package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.URLUtil;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;

/* renamed from: com.sds.android.ttpod.component.d.a.n */
/* loaded from: classes.dex */
public class RecommendsEffectHelpDialog extends BaseDialog {
    public RecommendsEffectHelpDialog(Context context) {
        super(context);
        m7254b(R.string.effect_know, (BaseDialog.InterfaceC1064a) null);
        m7244e(true);
        setTitle(R.string.recommends_effect_help_dialog_title);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View mo2034a(Context context) {
        View inflate = View.inflate(getContext(), R.layout.dialog_recommends_effect_help, null);
        inflate.findViewById(R.id.textview_contact).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.n.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendsEffectHelpDialog.m6795b(RecommendsEffectHelpDialog.this.getContext(), "http://bbs.dongting.com");
                RecommendsEffectHelpDialog.this.dismiss();
            }
        });
        return inflate;
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected <T> T mo2037a() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m6795b(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(m6794c(str))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private static String m6794c(String str) {
        if (!URLUtil.isNetworkUrl(str)) {
            return "http://" + str;
        }
        return str;
    }
}
