package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;

/* renamed from: com.sds.android.ttpod.component.d.a.r */
/* loaded from: classes.dex */
public class WaitingDialog extends BaseDialog {

    /* renamed from: a */
    private View rootView;

    /* renamed from: b */
    private ImageView progressWaitImageView;

    /* renamed from: c */
    private TextView messageWaitTextView;

    /* renamed from: d */
    private Animation rotateAnim;

    public WaitingDialog(Context context) {
        super(context, R.style.Theme_Dialog_Waiting);
        m7257a(false);
        m7251b(false);
        m7264a(R.drawable.xml_background_dialog_waiting);
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sds.android.ttpod.component.d.a.r.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                WaitingDialog.this.messageWaitTextView.clearAnimation();
            }
        });
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View inflate(Context context) {
        this.rootView = View.inflate(context, R.layout.dialog_body_waiting, null);
        this.progressWaitImageView = (ImageView) this.rootView.findViewById(R.id.waiting_progress);
        this.messageWaitTextView = (TextView) this.rootView.findViewById(R.id.waiting_message);
        this.rotateAnim = AnimationUtils.loadAnimation(context, R.anim.rotate);
        return this.rootView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: b */
    public WaitingDialog getDialog() {
        return this;
    }

    /* renamed from: a */
    public void setText(CharSequence charSequence) {
        this.messageWaitTextView.setText(charSequence);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog, android.app.Dialog
    public void show() {
        super.show();
        this.progressWaitImageView.startAnimation(this.rotateAnim);
    }
}
