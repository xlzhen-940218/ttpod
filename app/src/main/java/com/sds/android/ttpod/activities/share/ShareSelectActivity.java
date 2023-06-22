package com.sds.android.ttpod.activities.share;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SharePostSelectDialog;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.p107a.StatisticUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.share.IShareAction;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.ShareType;
import com.sds.android.ttpod.share.p136a.SinaWeiboApi;
import com.sds.android.ttpod.share.p138c.ShareSelectDialog;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ShareSelectActivity extends BaseActivity {
    public static final String KEY_EXTRA_DATA = "key_extra_data";
    private static IShareAction mShareAction;
    private ShareSelectDialog mShareSelectDialog;

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Serializable serializableExtra = getIntent().getSerializableExtra(KEY_EXTRA_DATA);
        if (serializableExtra instanceof ShareInfo) {
            ShareInfo shareInfo = (ShareInfo) serializableExtra;
            showShareDialogStatistic(shareInfo);
            this.mShareSelectDialog = new ShareSelectDialog(this, shareInfo);
            UnicomFlowUtil.m3950b(this);
        } else if (serializableExtra instanceof Post) {
            this.mShareSelectDialog = new SharePostSelectDialog(this, (Post) serializableExtra);
        } else {
            finish();
            return;
        }
        if (this.mShareSelectDialog != null) {
            this.mShareSelectDialog.m2032a(mShareAction);
        }
        this.mShareSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sds.android.ttpod.activities.share.ShareSelectActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (ShareSelectActivity.this.mShareSelectDialog != null && ShareSelectActivity.this.mShareSelectDialog.m2006g() == ShareType.SINA_WEIBO && !Preferences.m2948aw()) {
                    ShareSelectActivity.this.showFollowTTPodOfficialSinaWeiboDialog((SinaWeiboApi) ShareSelectActivity.this.mShareSelectDialog.m2004h());
                    Preferences.m3065H(true);
                    return;
                }
                ShareSelectActivity.this.finish();
            }
        });
        this.mShareSelectDialog.show();
    }

    private void showShareDialogStatistic(ShareInfo shareInfo) {
        StatisticUtils.m4907a("share", "share", "showDialog", 0L, shareInfo.m1942p() ? 1 : 0, shareInfo.m1956f(), shareInfo.m1942p() ? shareInfo.m1958e() : shareInfo.m1954g());
    }

    public static void setShareAction(IShareAction iShareAction) {
        mShareAction = iShareAction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowTTPodOfficialSinaWeiboDialog(final SinaWeiboApi sinaWeiboApi) {
        MessageDialog messageDialog = new MessageDialog(this, (int) R.string.share_follow_official_weibo_content, (int) R.string.share_follow_official_weibo_positive, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.activities.share.ShareSelectActivity.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                sinaWeiboApi.m2082f();
            }
        }, (int) R.string.cancel, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.share_follow_official_weibo_title);
        messageDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sds.android.ttpod.activities.share.ShareSelectActivity.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ShareSelectActivity.this.finish();
            }
        });
        messageDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mShareSelectDialog != null) {
            this.mShareSelectDialog.m2036a(i, i2, intent);
        }
    }
}
