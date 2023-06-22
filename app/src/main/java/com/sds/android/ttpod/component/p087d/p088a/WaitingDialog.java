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
    private View f4002a;

    /* renamed from: b */
    private ImageView f4003b;

    /* renamed from: c */
    private TextView f4004c;

    /* renamed from: d */
    private Animation f4005d;

    public WaitingDialog(Context context) {
        super(context, R.style.Theme_Dialog_Waiting);
        m7257a(false);
        m7251b(false);
        m7264a(R.drawable.xml_background_dialog_waiting);
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sds.android.ttpod.component.d.a.r.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                WaitingDialog.this.f4004c.clearAnimation();
            }
        });
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View mo2034a(Context context) {
        this.f4002a = View.inflate(context, R.layout.dialog_body_waiting, null);
        this.f4003b = (ImageView) this.f4002a.findViewById(R.id.waiting_progress);
        this.f4004c = (TextView) this.f4002a.findViewById(R.id.waiting_message);
        this.f4005d = AnimationUtils.loadAnimation(context, R.anim.rotate);
        return this.f4002a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: b */
    public WaitingDialog mo2037a() {
        return this;
    }

    /* renamed from: a */
    public void m6775a(CharSequence charSequence) {
        this.f4004c.setText(charSequence);
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog, android.app.Dialog
    public void show() {
        super.show();
        this.f4003b.startAnimation(this.f4005d);
    }
}
