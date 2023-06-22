package com.sds.android.ttpod.activities.ktv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;

/* renamed from: com.sds.android.ttpod.activities.ktv.a */
/* loaded from: classes.dex */
public class DownloadProgressDialog extends BaseDialog {

    /* renamed from: a */
    private TextView f2633a;

    /* renamed from: b */
    private ProgressBar f2634b;

    public DownloadProgressDialog(Context context, BaseDialog.InterfaceC1064a interfaceC1064a, BaseDialog.InterfaceC1064a interfaceC1064a2) {
        super(context, R.style.Theme_NoTitle_Dialog);
        m7261a(R.string.version_upgrade_hide_dialog, interfaceC1064a2, R.string.cancel, interfaceC1064a);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View mo2034a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ktv_dialog_download_progress, (ViewGroup) null);
        this.f2633a = (TextView) inflate.findViewById(R.id.text_downloading_progress);
        this.f2634b = (ProgressBar) inflate.findViewById(R.id.downloading_progress_bar);
        setTitle("正在下载");
        return inflate;
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected <T> T mo2037a() {
        return null;
    }

    /* renamed from: a */
    public void m8125a(int i, int i2) {
        this.f2633a.setText("已下载" + (i / 1024) + "kb,总共" + (i2 > 0 ? (i2 / 1024) + "kb" : ""));
        this.f2634b.setProgress(i2 > 0 ? (i * 100) / i2 : 0);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog, android.app.Dialog
    public void show() {
        super.show();
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }
}
