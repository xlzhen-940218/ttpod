package com.sds.android.ttpod.common.p082a;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* renamed from: com.sds.android.ttpod.common.a.b */
/* loaded from: classes.dex */
public class ShareWaitingDialog extends BaseDialog {

    /* renamed from: a */
    private View f3572a;

    /* renamed from: b */
    private ImageView f3573b;

    /* renamed from: c */
    private TextView f3574c;

    /* renamed from: d */
    private Animation f3575d;

    public ShareWaitingDialog(Context context) {
        super(context, R.style.Share_Theme_Dialog_Waiting);
        m7257a(false);
        m7251b(false);
        m7264a(R.drawable.xml_share_background_dialog_waiting);
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sds.android.ttpod.common.a.b.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ShareWaitingDialog.this.f3574c.clearAnimation();
            }
        });
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View inflate(Context context) {
        this.f3572a = View.inflate(context, R.layout.share_dialog_body_waiting, null);
        this.f3573b = (ImageView) this.f3572a.findViewById(R.id.waiting_progress);
        this.f3574c = (TextView) this.f3572a.findViewById(R.id.waiting_message);
        this.f3575d = AnimationUtils.loadAnimation(context, R.anim.rotate);
        return this.f3572a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: b */
    public ShareWaitingDialog getDialog() {
        return this;
    }

    /* renamed from: a */
    public void m7236a(CharSequence charSequence) {
        this.f3574c.setText(charSequence);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog, android.app.Dialog
    public void show() {
        super.show();
        this.f3573b.startAnimation(this.f3575d);
    }
}
