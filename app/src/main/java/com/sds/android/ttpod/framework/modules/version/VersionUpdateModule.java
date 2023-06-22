package com.sds.android.ttpod.framework.modules.version;

import com.sds.android.cloudapi.ttpod.p055a.AppVersionAPI;
import com.sds.android.cloudapi.ttpod.result.AppVersionResult;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.ThirdParty.ThirdPartyManager;
import com.sds.android.ttpod.ThirdParty.update.DownloadProgressListener;
import com.sds.android.ttpod.ThirdParty.update.DownloadState;
import com.sds.android.ttpod.ThirdParty.update.UpdateCallback;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.p107a.ErrorStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.StatisticUtils;
import com.sds.android.ttpod.framework.p106a.p107a.UpdateStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/* loaded from: classes.dex */
public final class VersionUpdateModule extends BaseModule implements DownloadProgressListener {
    private static final String TAG = "VersionUpdateModule";
    private static final String TTPOD_UPDATE_NAME = "ttpod";
    private DownloadTaskInfo mDownloadTaskInfo = null;
    private volatile boolean mIsStop = true;
    private UpdateCallback mUpdateCallback = new UpdateCallback() { // from class: com.sds.android.ttpod.framework.modules.version.VersionUpdateModule.2
        @Override // com.sds.android.ttpod.ThirdParty.update.UpdateCallback
        public void updateInfo(VersionUpdateData versionUpdateData) {
            CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_SMART_UPDATE_INFO, versionUpdateData), ModuleID.VERSION);
        }

        @Override // com.sds.android.ttpod.ThirdParty.update.UpdateCallback
        public boolean needInstallApp(String str, String str2) {
            return FileUtils.m8419a(VersionUpdateModule.this.getSavePath(str, str2));
        }

        @Override // com.sds.android.ttpod.ThirdParty.update.UpdateCallback
        public void installApp(String str, String str2) {
            CommandCenter.m4607a().m4604a(new Command(CommandID.INSTALL_APP, VersionUpdateModule.this.getSavePath(str, str2)), ModuleID.VERSION);
        }

        @Override // com.sds.android.ttpod.ThirdParty.update.UpdateCallback
        public void downloadApp(String str, String str2) {
            VersionUpdateModule.this.doDownload(str, str2);
        }

        @Override // com.sds.android.ttpod.ThirdParty.update.UpdateCallback
        public void statisticAppInstall(boolean z, String str) {
            StatisticUtils.m4909a("update", str, "install", z ? 0L : 1L);
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        ThirdPartyManager.m8315a(this);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID mo3239id() {
        return ModuleID.VERSION;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.START_COMMON_UPGRADE, ReflectUtils.m8375a(cls, "startCommonUpgrade", String.class));
        map.put(CommandID.START_SMART_UPGRADE, ReflectUtils.m8375a(cls, "startSmartUpgrade", Boolean.class));
        map.put(CommandID.CHECK_UPGRADE, ReflectUtils.m8375a(cls, "checkUpgrade", Boolean.class));
        map.put(CommandID.CANCEL_UPGRADE, ReflectUtils.m8375a(cls, "cancelUpgrade", new Class[0]));
    }

    public void cancelUpgrade() {
        if (this.mDownloadTaskInfo != null) {
            CommandCenter.m4607a().m4606a(new Command(CommandID.DELETE_DOWNLOAD_TASK, this.mDownloadTaskInfo, Boolean.TRUE));
            cancelUpdateProgress();
            return;
        }
        ThirdPartyManager.m8318a();
    }

    private void cancelUpdateProgress() {
        if (this.mDownloadTaskInfo != null) {
            this.mDownloadTaskInfo.setState(3);
            this.mIsStop = true;
            this.mDownloadTaskInfo = null;
        }
    }

    public void checkUpgrade(final Boolean bool) {
        if (bool.booleanValue()) {
            UpdateStatistic.m4800a();
        }
        new AppVersionAPI().m8964a(EnvironmentUtils.C0603b.m8491c(), EnvironmentUtils.C0603b.m8494b(), EnvironmentUtils.C0603b.m8489d(), false).m8544a(new RequestCallback<AppVersionResult>() { // from class: com.sds.android.ttpod.framework.modules.version.VersionUpdateModule.1
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestSuccess(AppVersionResult appVersionResult) {
                Preferences.m3013a(Long.valueOf(new Date().getTime()));
                Preferences.m2848o(appVersionResult.getLatestVersion());
                VersionUpdateData versionUpdateData = new VersionUpdateData(appVersionResult);
                if (VersionUpdateModule.compare(appVersionResult.getLatestVersion(), EnvironmentUtils.C0603b.m8491c()) <= 0) {
                    ignoreUpdate(versionUpdateData);
                } else if (versionUpdateData.isUpdateMandatory()) {
                    mandatoryUpdate(versionUpdateData);
                } else if (bool.booleanValue() && Preferences.m2952as().equals(appVersionResult.getLatestVersion())) {
                    ignoreUpdate(versionUpdateData);
                } else {
                    thirdUpdate(versionUpdateData);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestFailure(AppVersionResult appVersionResult) {
                notify("检查版本更新出错", new VersionUpdateData(appVersionResult), 2);
                statisticUpdateError();
            }

            private void statisticUpdateError() {
                String m8532e = new AppVersionAPI().m8964a(EnvironmentUtils.C0603b.m8491c(), EnvironmentUtils.C0603b.m8494b(), EnvironmentUtils.C0603b.m8489d(), false).m8532e();
                ErrorStatistic.m5233f(m8532e);
                ErrorStatistic.m5239a("update", m8532e);
            }

            private void thirdUpdate(VersionUpdateData versionUpdateData) {
                if (!ThirdPartyManager.m8316a(VersionUpdateModule.sContext, versionUpdateData, VersionUpdateModule.this.mUpdateCallback)) {
                    notify(VersionUpdateConst.MESSAGE_COMMON_UPGRADE, versionUpdateData, 1);
                }
            }

            private void mandatoryUpdate(VersionUpdateData versionUpdateData) {
                VersionUpdateModule.this.doDownload(versionUpdateData.getUpdateUrl(), "ttpod");
                notify(VersionUpdateConst.MESSAGE_COMMON_UPGRADE, versionUpdateData, 0);
            }

            private void ignoreUpdate(VersionUpdateData versionUpdateData) {
                notify(VersionUpdateConst.MESSAGE_COMMON_UPGRADE, versionUpdateData, 6);
            }

            private void notify(String str, VersionUpdateData versionUpdateData, int i) {
                CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_COMMON_UPGRADE_INFO, new CommonResult(ErrCode.ErrNone, str, versionUpdateData, Integer.valueOf(i))), ModuleID.VERSION);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSavePath(String str, String str2) {
        return TTPodConfig.m5285w() + File.separator + str2 + str.hashCode() + ".apk";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDownload(String str, String str2) {
        String savePath = getSavePath(str, str2);
        final DownloadTaskInfo m4760a = DownloadUtils.m4760a(str, savePath, 0L, FileUtils.m8402j(savePath), DownloadTaskInfo.TYPE_APP, true, "ttpod".equals(str2) ? "update" : "update-" + str2);
        m4760a.setTag(str);
        this.mDownloadTaskInfo = m4760a;
        CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_SHOW_DOWNLOAD_PROGRESS, Boolean.FALSE), ModuleID.VERSION);
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.version.VersionUpdateModule.3
            /* JADX WARN: Can't wrap try/catch for region: R(10:9|(5:32|33|34|36|27)(1:15)|16|(1:31)|22|23|24|26|27|5) */
            /* JADX WARN: Code restructure failed: missing block: B:31:0x00da, code lost:
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:32:0x00db, code lost:
                r0.printStackTrace();
                r7.this$0.mIsStop = true;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                VersionUpdateModule.this.mIsStop = false;
                if (FileUtils.m8419a(m4760a.getSavePath())) {
                    FileUtils.m8404h(m4760a.getSavePath());
                }
                CommandCenter.m4607a().m4596b(new Command(CommandID.ADD_DOWNLOAD_TASK, m4760a));
                while (!VersionUpdateModule.this.mIsStop && VersionUpdateModule.this.mDownloadTaskInfo != null) {
                    if (VersionUpdateModule.this.mDownloadTaskInfo.getState() == null || VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue() == 0 || 1 == VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue()) {
                        try {
                            Thread.sleep(1000L);
                        } catch (Exception e) {
                            e.printStackTrace();
                            VersionUpdateModule.this.mIsStop = true;
                        }
                    }
                    CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_ALL_UPGRADE_PROGRESS_INFO, VersionUpdateModule.this.mDownloadTaskInfo), ModuleID.VERSION);
                    if (5 == VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue() || 3 == VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue() || 4 == VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue()) {
                        VersionUpdateModule.this.mIsStop = true;
                        VersionUpdateModule.this.mDownloadTaskInfo = null;
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void startSmartUpgrade(Boolean bool) {
        ThirdPartyManager.m8314a(bool.booleanValue(), this.mUpdateCallback);
    }

    public void startCommonUpgrade(String str) {
        if (this.mIsStop) {
            doDownload(str, "ttpod");
        }
    }

    public static boolean hasNewVersion() {
        return compare(Preferences.m3002aE(), EnvironmentUtils.C0603b.m8491c()) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compare(String str, String str2) {
        if (StringUtils.m8346a(str) || StringUtils.m8346a(str2)) {
            return 0;
        }
        return str.substring(str.lastIndexOf(46) + 1).compareTo(str2.substring(str2.lastIndexOf(46) + 1));
    }

    @Override // com.sds.android.ttpod.ThirdParty.update.DownloadProgressListener
    public void onDownloadStateChanged(DownloadState downloadState, int i, String str) {
        if (DownloadState.DOWNLOADING.equals(downloadState)) {
            CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_SHOW_DOWNLOAD_PROGRESS, Boolean.TRUE), ModuleID.VERSION);
        }
    }

    @Override // com.sds.android.ttpod.ThirdParty.update.DownloadProgressListener
    public void onDownloadProgressChanged(long j, long j2) {
        CommandCenter.m4607a().m4595b(new Command(CommandID.THIRDPARTY_DOWNLOAD_PROGRESS, Long.valueOf(j), Long.valueOf(j2)), ModuleID.VERSION);
    }
}
