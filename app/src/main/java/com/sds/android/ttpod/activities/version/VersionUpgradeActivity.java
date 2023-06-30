package com.sds.android.ttpod.activities.version;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.WaitingDialog;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.ApkUtils;

import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class VersionUpgradeActivity extends BaseActivity {
    public static final String KEY_DATA = "key_data";
    public static final String KEY_IS_SMART = "key_force_update";
    private static final String TYPE_ANZHI_UPDATE = "anzhi";
    private Button mCancelButton;
    private TextView mDescriptionView;
    private WaitingDialog mDialog;
    private boolean mIsSmart;
    private TextView mNameView;
    private Button mNormalButton;
    private Button mSmartButton;
    private TextView mSmartMessageView;
    private VersionUpdateData mVersionUpdateData;

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_version_upgrade);
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mVersionUpdateData != null) {
           // UpdateStatistic.m4799a(getStatisticType(this.mVersionUpdateData.getUpgradeType()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getStatisticType(int i) {
        switch (i) {
            case 1000:
                return "360";
            case VersionUpdateConst.UPDATE_WANDOUJIA_TYPE /* 1001 */:
                return VersionUpdateConst.TYPE_WANDOUJIA_UPDATE;
            case VersionUpdateConst.UPDATE_HIAPK_TYPE /* 1002 */:
                return VersionUpdateConst.TYPE_HIAPK_UPDATE;
            case VersionUpdateConst.UPDATE_BAIDU_TYPE /* 1003 */:
                return VersionUpdateConst.TYPE_BAIDU_UPDATE;
            case VersionUpdateConst.UPDATE_TENCENT_TYPE /* 1004 */:
                return VersionUpdateConst.TYPE_TENCENT_UPDATE;
            case VersionUpdateConst.UPDATE_ANZHI_TYPE /* 1005 */:
                return TYPE_ANZHI_UPDATE;
            default:
                return "update";
        }
    }

    private void initView() {
        Intent intent = getIntent();
        this.mVersionUpdateData = (VersionUpdateData) JSONUtils.fromJson(intent.getStringExtra("key_data"),  VersionUpdateData.class);
        if (this.mVersionUpdateData == null) {
            finish();
        }
        this.mNormalButton = (Button) findViewById(R.id.button_upgrade_normal);
        this.mCancelButton = (Button) findViewById(R.id.button_upgrade_cancel);
        this.mSmartButton = (Button) findViewById(R.id.button_upgrade_smart);
        this.mNameView = (TextView) findViewById(R.id.textview_upgrade_version);
        this.mDescriptionView = (TextView) findViewById(R.id.textview_upgrade_content);
        this.mSmartMessageView = (TextView) findViewById(R.id.textview_smart_upgrade);
        this.mIsSmart = intent.getBooleanExtra(KEY_IS_SMART, false);
        this.mSmartButton.setVisibility(this.mIsSmart ? View.VISIBLE : View.GONE);
        updateContent(this.mVersionUpdateData);
        setNormalButton(this.mVersionUpdateData);
        setSmartButton(this.mVersionUpdateData);
    }

    private void setSmartButton(final VersionUpdateData versionUpdateData) {
        if (this.mIsSmart && this.mSmartButton != null) {
            this.mSmartButton.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.version.VersionUpgradeActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    //StatisticUtils.m4910a("update", VersionUpgradeActivity.this.getStatisticType(versionUpdateData.getUpgradeType()), "smart");
                    if (VersionUpdateData.UpdateState.NEED == versionUpdateData.getUpdateState()) {
                        CommandCenter.getInstance().execute(new Command(CommandID.START_SMART_UPGRADE, versionUpdateData.getAppstoreInstalled()));
                    } else if (VersionUpdateData.UpdateState.NO_NEED == versionUpdateData.getUpdateState()) {
                        PopupsUtils.m6721a("没有发现新的版本");
                        VersionUpgradeActivity.this.finish();
                    } else {
                        PopupsUtils.m6721a("检测更新失败");
                        VersionUpgradeActivity.this.finish();
                    }
                }
            });
        }
    }

    private void setNormalButton(final VersionUpdateData versionUpdateData) {
        if (this.mNormalButton != null) {
            this.mNormalButton.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.version.VersionUpgradeActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                   // UpdateStatistic.m4798b();
                    CommandCenter.getInstance().execute(new Command(CommandID.START_COMMON_UPGRADE, versionUpdateData.getUpdateUrl()));
                    VersionUpgradeActivity.this.startActivity(new Intent(VersionUpgradeActivity.this, VersionUpgradeProgressActivity.class));
                    VersionUpgradeActivity.this.finish();
                }
            });
        }
    }

    private void showUpdateProgressDialog() {
        if (this.mDialog == null) {
            this.mDialog = new WaitingDialog(this);
            this.mDialog.m6775a((CharSequence) getString(R.string.login_wait_message));
        }
        this.mDialog.show();
    }

    private void dismiss() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }

    private void updateContent(VersionUpdateData versionUpdateData) {
        String updateDescription = versionUpdateData.getUpdateDescription();
        this.mNameView.setText(versionUpdateData.getLatestVersion());
        this.mDescriptionView.setText(StringUtils.isEmpty(updateDescription) ? "" : updateDescription.trim());
        this.mCancelButton.setText(versionUpdateData.isUpdateMandatory() ? R.string.version_upgrade_hint_next_time : R.string.cancel);
        this.mCancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.version.VersionUpgradeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
               // UpdateStatistic.m4797c();
                VersionUpgradeActivity.this.finish();
            }
        });
        updateSmartContent(versionUpdateData);
    }

    private void updateSmartContent(VersionUpdateData versionUpdateData) {
        if (this.mIsSmart && versionUpdateData.getSize() > 0.0d) {
            this.mSmartMessageView.setVisibility(View.VISIBLE);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) "更新包大小：").append((CharSequence) Double.toString(versionUpdateData.getSize())).append((CharSequence) "MB\n");
            spannableStringBuilder.append((CharSequence) Html.fromHtml("<font color=#555555>使用" + versionUpdateData.getToolName() + "更新</font> "));
            if (versionUpdateData.getPatchSize() > 0.0d) {
                spannableStringBuilder.append((CharSequence) "\n");
                spannableStringBuilder.append((CharSequence) Html.fromHtml("<font color=#555555>仅需</font><font color=#f20d0d>" + versionUpdateData.getPatchSize() + "MB</font>,<font color=#555555>省流量省钱</font>"));
            }
            if (!versionUpdateData.getAppstoreInstalled().booleanValue()) {
                spannableStringBuilder.append((CharSequence) "\n");
                spannableStringBuilder.append((CharSequence) Html.fromHtml("<font color=#f20d0d >*省流量升级时会先安装" + versionUpdateData.getToolName()));
            }
            this.mSmartMessageView.setText(spannableStringBuilder);
            return;
        }
        this.mSmartMessageView.setVisibility(View.GONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_SHOW_DOWNLOAD_PROGRESS, ReflectUtils.loadMethod(cls, "showDownloadProgress", Boolean.class));
        map.put(CommandID.INSTALL_APP, ReflectUtils.loadMethod(cls, "installApp", String.class));
    }

    public void showDownloadProgress(Boolean bool) {
        Intent intent = new Intent(this, VersionUpgradeProgressActivity.class);
        intent.putExtra(VersionUpgradeProgressActivity.KEY_THIRDPARTY_PROGRESS, bool);
        startActivity(intent);
    }

    public void installApp(String str) {
        ApkUtils.m8311a(this, str);
    }

    private void downloadApp(String str, String str2) {
        if (!ApkUtils.m8311a(BaseApplication.getApplication(), str2)) {
            DownloadUtils.m4760a(str, str2, 0L, FileUtils.getFilename(str2), DownloadTaskInfo.TYPE_APP, true, "update").setTag(str);
            CommandCenter.getInstance().execute(new Command(CommandID.START_SMART_UPGRADE, false));
            startActivity(new Intent(this, VersionUpgradeProgressActivity.class));
        }
    }
}
