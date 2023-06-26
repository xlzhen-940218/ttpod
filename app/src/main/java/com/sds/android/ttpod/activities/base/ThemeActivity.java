package com.sds.android.ttpod.activities.base;

import android.content.Intent;
import com.sds.android.sdk.core.download.Task;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.ThirdPartyApp;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;
import com.sds.android.ttpod.activities.setting.AboutActivity;

import com.sds.android.ttpod.activities.version.VersionUpgradeActivity;
import com.sds.android.ttpod.activities.version.VersionUpgradeProgressActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.framework.base.ActivityManager;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowDialogType;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.ApkUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ThemeActivity extends BaseActivity {
    private static final long FIVE_SECOND_IN_MILLIS = 5000;
    private static final int PLAY_ERROR_CODE_1 = -1;
    private static final int PLAY_ERROR_CODE_100 = -100;
    private static final int PLAY_ERROR_CODE_12 = -12;
    private static final int PLAY_ERROR_CODE_23 = -23;
    private static final int PLAY_ERROR_CODE_34 = -34;
    private static final int PLAY_ERROR_CODE_35 = -35;
    private static final int PLAY_ERROR_CODE_36 = -36;
    private static final int PLAY_ERROR_CODE_5 = -5;
    private static final int PLAY_ERROR_CODE_5000 = -5000;
    private static final int PLAY_ERROR_CODE_99 = -99;
    private static long mLastToastShowedTime = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_HEADSET_PLUGGED, ReflectUtils.m8375a(cls, "headSetPlugged", new Class[0]));
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.m8375a(cls, "updateTaskState", DownloadTaskInfo.class));
        map.put(CommandID.UPDATE_ADD_DOWNLOAD_TASK_ERROR, ReflectUtils.m8375a(cls, "updateAddDownloadTaskError", DownloadTaskInfo.class));
        map.put(CommandID.UPDATE_ADD_DOWNLOAD_TASK_DATABASE, ReflectUtils.m8375a(cls, "updateAddDownloadTaskDatabase", DownloadTaskInfo.class));
        map.put(CommandID.DOWNLOAD_TASK_FAILED, ReflectUtils.m8375a(cls, "onDownloadTaskFailed", DownloadTaskInfo.class, Task.EnumC0579b.class));
        map.put(CommandID.UPDATE_COMMON_UPGRADE_INFO, ReflectUtils.m8375a(cls, "updateCommonUpgradeInfo", CommonResult.class));
        map.put(CommandID.UPDATE_SMART_UPDATE_INFO, ReflectUtils.m8375a(cls, "updateSmartUpgradeInfo", VersionUpdateData.class));
        map.put(CommandID.UPDATE_ADD_DOWNLOAD_TASK_LIST_ERROR, ReflectUtils.m8375a(cls, "updateAddDownloadTaskListError", List.class));
        map.put(CommandID.APP_THEME_CHANGED, ReflectUtils.m8375a(getClass(), "onThemeChanged", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_ERROR, ReflectUtils.m8375a(getClass(), "updatePlayError", Integer.class));
        map.put(CommandID.UNICOM_FLOW_POPUP_DIALOG, ReflectUtils.m8375a(getClass(), "unicomFlowPopupDialog", UnicomFlowDialogType.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        loadTheme();
    }

    public void updateAddDownloadTaskDatabase(DownloadTaskInfo downloadTaskInfo) {
        if (ActivityManager.m4618a().m4611e(this) && isShowToast()) {
            PopupsUtils.m6721a(buildDownloadToastText(downloadTaskInfo, getString(R.string.start_download), null));
        }
    }

    private String buildDownloadToastText(DownloadTaskInfo downloadTaskInfo, String str, String str2) {
        String m8402j = FileUtils.getFilename(downloadTaskInfo.getSavePath());
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
            m8402j = FileUtils.m8401k(downloadTaskInfo.getSavePath());
            List<String> m8335c = StringUtils.stringToArray(m8402j, "-");
            if (m8335c.size() > 1) {
                m8402j = m8335c.get(1).trim();
            } else if (m8335c.size() == 1) {
                m8402j = m8335c.get(0).trim();
            }
        }
        if (!StringUtils.isEmpty(str)) {
            m8402j = str + "-" + m8402j;
        }
        if (!StringUtils.isEmpty(str2)) {
            return m8402j + "\n" + str2;
        }
        return m8402j;
    }

    public void updateAddDownloadTaskError(final DownloadTaskInfo downloadTaskInfo) {
        if (ActivityManager.m4618a().m4611e(this)) {
            if (DownloadTaskInfo.TYPE_AUDIO == downloadTaskInfo.getType()) {
                PopupsUtils.m6721a("已经下载本首歌曲了");
                return;
            }
            MessageDialog messageDialog = new MessageDialog(this, (int) R.string.download_file_already_existed, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.activities.base.ThemeActivity.1
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MessageDialog messageDialog2) {
                    FileUtils.m8404h(downloadTaskInfo.getSavePath());
                    CommandCenter.getInstance().m4596b(new Command(CommandID.ADD_DOWNLOAD_TASK, downloadTaskInfo));
                }
            }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
            messageDialog.setTitle(R.string.download);
            messageDialog.show();
        }
    }

    public void updateAddDownloadTaskListError(final List<DownloadTaskInfo> list) {
        if (ActivityManager.m4618a().m4611e(this)) {
            MessageDialog messageDialog = new MessageDialog(this, (int) R.string.download_file_already_existed, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.activities.base.ThemeActivity.2
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MessageDialog messageDialog2) {
                    CommandCenter.getInstance().m4596b(new Command(CommandID.ASYN_ADD_DOWNLOAD_TASK_LIST, list, Boolean.TRUE));
                }
            }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
            messageDialog.setTitle(R.string.download);
            messageDialog.show();
        }
    }

    public void unicomFlowPopupDialog(UnicomFlowDialogType unicomFlowDialogType) {
        if (ActivityManager.m4618a().m4611e(this)) {
            //UnicomFlowManager.m7751d(this, unicomFlowDialogType);
        }
    }

    public void onDownloadTaskFailed(DownloadTaskInfo downloadTaskInfo, Task.EnumC0579b enumC0579b) {
        int i;
        switch (enumC0579b) {
            case FILE_CREATION:
                i = R.string.file_create_failed;
                break;
            case NETWORK_UNAVAILABLE:
                i = R.string.network_unavailable;
                break;
            case STORAGE:
                i = R.string.insufficient_storage;
                break;
            case URL_REQUEST_FAILED:
                i = R.string.download_url_invalid;
                break;
            default:
                i = R.string.unknown_error;
                break;
        }
        if (isShowToast()) {
            PopupsUtils.m6721a(buildDownloadToastText(downloadTaskInfo, getString(R.string.download_error_occurred), getString(i)));
        }
    }

    private boolean isShowToast() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - mLastToastShowedTime > FIVE_SECOND_IN_MILLIS;
        if (z) {
            mLastToastShowedTime = currentTimeMillis;
        }
        return z;
    }

    public void headSetPlugged() {
        if (ActivityManager.m4618a().m4611e(this) && !Preferences.m2951at()) {
            Preferences.m3069F(true);
            MessageDialog messageDialog = new MessageDialog(this, (int) R.string.headset_plug_tip, (int) R.string.iknown, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
            messageDialog.setTitle(R.string.headset_plugged);
            messageDialog.m7244e(true);
            messageDialog.show();
        }
    }

    public void updateTaskState(DownloadTaskInfo downloadTaskInfo) {
        if (ActivityManager.m4618a().m4611e(this) && downloadTaskInfo.getState().intValue() == 4 && downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_APP) {
            Object tag = downloadTaskInfo.getTag();
            if (tag instanceof ThirdPartyApp) {
                ThirdPartyApp thirdPartyApp = (ThirdPartyApp) tag;
                if (thirdPartyApp.m8320g()) {
                    //StatisticUtils.m4908a(thirdPartyApp.m8325b(), thirdPartyApp.m8324c(), thirdPartyApp.m8323d(), 0L, thirdPartyApp.m8319h());
                    thirdPartyApp.m8322e();
                    Preferences.m2840q(JSONUtils.toJson(thirdPartyApp));
                }
            }
            ApkUtils.m8311a(this, downloadTaskInfo.getSavePath());
        }
    }

    public void updateCommonUpgradeInfo(CommonResult commonResult) {
        if (ActivityManager.m4618a().m4611e(this)) {
            Integer num = (Integer) commonResult.m4583c();
            boolean z = this instanceof AboutActivity;
            VersionUpdateData versionUpdateData = (VersionUpdateData) commonResult.m4582d();
            if (num.intValue() == 0) {
                CommandCenter.getInstance().execute(new Command(CommandID.START_COMMON_UPGRADE, versionUpdateData.getUpdateUrl()));
                startActivity(new Intent(this, VersionUpgradeProgressActivity.class));
            } else if (num.intValue() == 1) {
                startVersionUpdateActivity(versionUpdateData, false);
            } else if (z) {
                if (num.intValue() == 6) {
                    PopupsUtils.m6760a((int) R.string.version_update_no_update);
                } else if (num.intValue() == 2) {
                    PopupsUtils.m6760a((int) R.string.version_update_network_bad);
                }
            }
        }
    }

    public void updatePlayError(Integer num) {
        if (ActivityManager.m4618a().m4611e(this) && checkExternalStorageExisted()) {
            int i = R.string.unknown_error;
            switch (num.intValue()) {
                case PLAY_ERROR_CODE_5000 /* -5000 */:
                    PopupsUtils.m6760a((int) R.string.play_error_code_5000);
                    return;
                case PLAY_ERROR_CODE_100 /* -100 */:
                    i = R.string.play_error_code_100;
                    break;
                case PLAY_ERROR_CODE_99 /* -99 */:
                    i = R.string.play_error_code_99;
                    break;
                case PLAY_ERROR_CODE_36 /* -36 */:
                    i = R.string.play_error_code_36;
                    break;
                case PLAY_ERROR_CODE_35 /* -35 */:
                    i = R.string.play_error_code_35;
                    break;
                case PLAY_ERROR_CODE_34 /* -34 */:
                    i = R.string.play_error_code_34;
                    break;
                case PLAY_ERROR_CODE_23 /* -23 */:
                    i = R.string.play_error_code_23;
                    break;
                case -12:
                    if (!EnvironmentUtils.C0605d.m8472a()) {
                        i = R.string.play_error_code_12;
                        break;
                    } else {
                        i = R.string.play_error_code_12_a;
                        break;
                    }
                case -5:
                    i = R.string.play_error_code_5;
                    break;
                case -1:
                    i = R.string.play_error_code_1;
                    break;
            }
            PopupsUtils.m6721a(getString(R.string.play_error, new Object[]{num}) + getString(i));
        }
    }

    public void updateSmartUpgradeInfo(VersionUpdateData versionUpdateData) {
        if (ActivityManager.m4618a().m4611e(this)) {
            startVersionUpdateActivity(versionUpdateData, true);
        }
    }

    private void startVersionUpdateActivity(VersionUpdateData versionUpdateData, boolean z) {
        Intent intent = new Intent(this, VersionUpgradeActivity.class);
        intent.putExtra("key_data", JSONUtils.toJson(versionUpdateData));
        intent.putExtra(VersionUpgradeActivity.KEY_IS_SMART, z);
        startActivity(intent);
    }

    public void toggleMenu() {
        BaseFragment topFragment = getTopFragment();
        if (topFragment instanceof ActionBarFragment) {
            ((ActionBarFragment) topFragment).onToggleMenuView(false);
        }
    }

    public void onThemeChanged() {
        loadTheme();
    }

    private void loadTheme() {
        if (this instanceof ThemeManager.InterfaceC2019b) {
            ((ThemeManager.InterfaceC2019b) this).onThemeLoaded();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean checkExternalStorageExisted() {
        boolean m8472a = EnvironmentUtils.C0605d.m8472a();
        if (!m8472a) {
            PopupsUtils.m6760a((int) R.string.sdcard_not_existed);
        }
        return m8472a;
    }
}
