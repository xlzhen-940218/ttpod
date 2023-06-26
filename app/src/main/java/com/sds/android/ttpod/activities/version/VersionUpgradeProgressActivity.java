package com.sds.android.ttpod.activities.version;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.ApkUtils;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class VersionUpgradeProgressActivity extends BaseActivity {
    public static final String KEY_THIRDPARTY_PROGRESS = "key_thirdparty_progress";
    private static final String TAG = "VersionUpgradeProgressActivity";
    private static final double TOTAL_PROGRESS_SIZE = 100.0d;
    private String mDownloadInfo;
    private TextView mDownloadingProgress;
    private ProgressBar mProgressBar;
    private long mTotalFileSize = 0;
    private long mDownloadedSize = 0;
    private boolean mIsShowThirdpartyProgress = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.dialog_upgrade_progress);
        initView();
        if (getIntent() != null && getIntent().getExtras() != null) {
            this.mIsShowThirdpartyProgress = getIntent().getExtras().getBoolean(KEY_THIRDPARTY_PROGRESS, false);
        }
        this.mDownloadInfo = getString(R.string.downloading_text);
        this.mDownloadingProgress.setText(String.format(this.mDownloadInfo, TTTextUtils.readableByte(this.mDownloadedSize), TTTextUtils.readableByte(this.mTotalFileSize)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_ALL_UPGRADE_PROGRESS_INFO, ReflectUtils.m8375a(cls, "updateProgressInfo", DownloadTaskInfo.class));
        map.put(CommandID.THIRDPARTY_DOWNLOAD_PROGRESS, ReflectUtils.m8375a(cls, "updateProgressChange", Long.class, Long.class));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void updateProgressInfo(DownloadTaskInfo downloadTaskInfo) {
        if (!this.mIsShowThirdpartyProgress) {
            LogUtils.error(TAG, "updateProgressInfo state = " + downloadTaskInfo.getState());
            switch (downloadTaskInfo.getState().intValue()) {
                case 0:
                case 1:
                case 2:
                    this.mTotalFileSize = downloadTaskInfo.getFileLength().intValue();
                    downloadTaskInfo.setDownloadLength(((Integer) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_TASK_DOWNLOADED_LENGTH, downloadTaskInfo), Integer.class)).intValue());
                    this.mDownloadedSize = downloadTaskInfo.getDownloadLength();
                    this.mDownloadingProgress.setText(String.format(this.mDownloadInfo, TTTextUtils.readableByte(this.mDownloadedSize), TTTextUtils.readableByte(this.mTotalFileSize)));
                    this.mProgressBar.setProgress(downloadTaskInfo.getDownloadProgress().intValue());
                    return;
                case 4:
                    ApkUtils.m8311a(BaseApplication.getApplication(), downloadTaskInfo.getSavePath());
                    break;
            }
            finish();
        }
    }

    public void updateProgressChange(Long l, Long l2) {
        if (this.mIsShowThirdpartyProgress) {
            LogUtils.error(TAG, "updateProgressInfo thiraparty state receiveDataLen:" + l + "  totalDataLen:" + l2);
            this.mTotalFileSize = l2.longValue();
            this.mDownloadedSize = l.longValue();
            this.mDownloadingProgress.setText(String.format(this.mDownloadInfo, TTTextUtils.readableByte(this.mDownloadedSize), TTTextUtils.readableByte(this.mTotalFileSize)));
            this.mProgressBar.setProgress((int) (l.longValue() / (l2.longValue() / TOTAL_PROGRESS_SIZE)));
        }
    }

    private void initView() {
        Button button;
        Button button2;
        if (!SDKVersionUtils.m8368f()) {
            button = (Button) findViewById(R.id.pdialog_standard_right);
            button2 = (Button) findViewById(R.id.pdialog_standard_left);
        } else {
            button = (Button) findViewById(R.id.pdialog_standard_left);
            button2 = (Button) findViewById(R.id.pdialog_standard_right);
        }
        this.mDownloadingProgress = (TextView) findViewById(R.id.version_downloading_progress);
        this.mProgressBar = (ProgressBar) findViewById(R.id.version_downloading_progress_bar);
        ((TextView) findViewById(R.id.pdialog_upgrade_progress_title)).setText(R.string.version_updating_head);
        button2.setText(R.string.version_upgrade_hide_dialog);
        button.setText(17039360);
        button2.setVisibility(View.VISIBLE);
        ((Button) findViewById(R.id.pdialog_standard_mid)).setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.version.VersionUpgradeProgressActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VersionUpgradeProgressActivity.this.finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.version.VersionUpgradeProgressActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommandCenter.getInstance().execute(new Command(CommandID.CANCEL_UPGRADE, new Object[0]));
                VersionUpgradeProgressActivity.this.finish();
            }
        });
    }
}
