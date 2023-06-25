package com.sds.android.ttpod.component.exception;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import com.sds.android.sdk.core.p058b.ExceptionReporter;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.storage.environment.Preferences;


/* loaded from: classes.dex */
public class ExceptionSendActivity extends BaseActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        String obj = getIntent().getCharSequenceExtra("android.intent.extra.SUBJECT").toString();
        String obj2 = getIntent().getCharSequenceExtra("android.intent.extra.TEXT").toString();
        String m8478c = StringUtils.isEmpty(EnvironmentUtils.C0604c.getDeviceId()) ? EnvironmentUtils.C0604c.getMacAddress() : EnvironmentUtils.C0604c.getDeviceId();
        StringBuilder sb = new StringBuilder();
        sb.append("clientId:").append(Preferences.m2945az()).append("\r\n").append("imei:")
                .append(m8478c).append("\r\n").append(obj2);
        ExceptionReporter.m8748a(obj, sb.toString(), new ExceptionReporter.InterfaceC0575a() { // from class: com.sds.android.ttpod.component.exception.ExceptionSendActivity.1
            @Override // com.sds.android.sdk.core.p058b.ExceptionReporter.InterfaceC0575a
            /* renamed from: a */
            public void mo6637a(boolean z) {
            }
        });
        try {
            MessageDialog messageDialog = new MessageDialog(this, (int) R.string.exception_content, (BaseDialog.InterfaceC1064a<MessageDialog>) null, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
            messageDialog.setTitle(R.string.exception);
            messageDialog.m7261a(R.string.send, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.component.exception.ExceptionSendActivity.2
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MessageDialog messageDialog2) {
                    ExceptionSendActivity.this.finish();
                }
            }, R.string.cancel, new BaseDialog.InterfaceC1064a() { // from class: com.sds.android.ttpod.component.exception.ExceptionSendActivity.3
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a */
                public void mo2038a(Object obj3) {
                    ExceptionSendActivity.this.finish();
                }
            });
            messageDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sds.android.ttpod.component.exception.ExceptionSendActivity.4
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4) {
                        ExceptionSendActivity.this.finish();
                        return false;
                    }
                    return false;
                }
            });
            messageDialog.show();
            //ErrorStatistic.m5245a();
            //new //SSystemEvent("SYS_EXCEPTION", "crash").append("origin", "error").post();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }
}
