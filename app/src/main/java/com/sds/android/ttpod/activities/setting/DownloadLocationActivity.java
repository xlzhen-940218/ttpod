package com.sds.android.ttpod.activities.setting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.mediascan.FilePickerActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.component.p087d.p088a.OptionalDialog;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.widget.CheckImageView;
import java.io.File;

/* loaded from: classes.dex */
public class DownloadLocationActivity extends SlidingClosableActivity {
    private static final int REQUEST_CODE_AUTO_DOWNLOAD_DIR = 0;
    private TextView mDownloadPathInfoTextView;
    private String mExtensionCardPath;
    private CheckImageView mExtensionSDCardCheckView;
    private String mStandardCardPath;
    private CheckImageView mStandardSDCardCheckView;
    private View mStoreOtherDirView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_DOWNLOAD_LOCATION);
        setContentView(R.layout.activity_download_location_layout);
        this.mStoreOtherDirView = findViewById(R.id.store_other_dir);
        this.mStoreOtherDirView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.setting.DownloadLocationActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String m3054N = Preferences.m3054N();
                if (SDKVersionUtils.m8365i()) {
                    m3054N = TTPodConfig.m5291q();
                }
                DownloadLocationActivity.this.startActivityForResult(new Intent(DownloadLocationActivity.this, FilePickerActivity.class).putExtra(FilePickerActivity.KEY_EXTRA_PATH, m3054N).putExtra(FilePickerActivity.KEY_EXTRA_CHOICE_MODE, 1).putExtra(FilePickerActivity.KEY_EXTRA_NEW_FOLDER, true).putExtra(FilePickerActivity.KEY_EXTRA_SHOW_FILE_TYPE, 2), 0);
            }
        });
        SettingUtils.m7778a(this);
        initSDCardPath();
        if (!StringUtils.isEmpty(this.mExtensionCardPath)) {
            findViewById(R.id.extension_sdcard).setVisibility(View.VISIBLE);
            initStandardSDCard(this.mStandardCardPath);
            initExtensionSDCard(this.mExtensionCardPath);
        } else {
            initStandardSDCard(this.mStandardCardPath);
        }
        this.mDownloadPathInfoTextView = (TextView) findViewById(R.id.download_path_info);
        this.mDownloadPathInfoTextView.setText(String.format(getResources().getString(R.string.download_path_info), Preferences.m3054N()));
    }

    private void initSDCardPath() {
        String m8467b = EnvironmentUtils.C0605d.getSdcardPath();
        String m8460d = EnvironmentUtils.C0605d.m8460d(this);
        this.mStandardCardPath = m8467b;
        this.mExtensionCardPath = m8460d;
        try {
            if (StringUtils.isEmpty(m8460d) || m8467b.equals(m8460d) || !checkSDCardPath(m8467b) || !checkSDCardPath(m8460d)) {
                this.mExtensionCardPath = "";
            } else if (SDKVersionUtils.m8372b() && Environment.isExternalStorageRemovable() && FileUtils.m8408d(EnvironmentUtils.C0605d.getSdcardPath(), Environment.getExternalStorageDirectory().getCanonicalPath())) {
                this.mStandardCardPath = EnvironmentUtils.C0605d.m8460d(this);
                this.mExtensionCardPath = EnvironmentUtils.C0605d.getSdcardPath();
            }
        } catch (Exception e) {
            this.mStandardCardPath = EnvironmentUtils.C0605d.getSdcardPath();
            this.mExtensionCardPath = "";
            e.printStackTrace();
        }
    }

    private void initStandardSDCard(String str) {
        TextView textView = (TextView) findViewById(R.id.standard_sdcard_storage_status);
        float totalSizeInGB = getTotalSizeInGB(str);
        float availableSizeInGB = getAvailableSizeInGB(str);
        textView.setText(String.format(textView.getText().toString(), Float.valueOf(totalSizeInGB), Float.valueOf(availableSizeInGB)));
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.standard_sdcard_progressbar);
        progressBar.setMax((int) totalSizeInGB);
        progressBar.setProgress((int) (totalSizeInGB - availableSizeInGB));
        final String str2 = getWritableBasePath(str) + File.separator + "song";
        if (!FileUtils.isDir(str2)) {
            FileUtils.m8406f(str2);
        }
        this.mStandardSDCardCheckView = (CheckImageView) findViewById(R.id.standard_sdcard_checkView);
        this.mStandardSDCardCheckView.m1899a(R.drawable.img_checkbox_multiselect_unchecked, R.drawable.img_checkbox_multiselect_checked);
        this.mStandardSDCardCheckView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.setting.DownloadLocationActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!DownloadLocationActivity.this.mStandardSDCardCheckView.m1900a()) {
                    DownloadLocationActivity.this.setCheckViewById(R.id.standard_sdcard_checkView);
                    Preferences.m2880g(str2);
                    DownloadLocationActivity.this.mDownloadPathInfoTextView.setText(String.format(DownloadLocationActivity.this.getResources().getString(R.string.download_path_info), str2));
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_MUSIC_SAVE_STANDARD_SD, SPage.PAGE_NONE);
                }
            }
        });
        if (Preferences.m3054N().equals(str2)) {
            setCheckViewById(R.id.standard_sdcard_checkView);
        }
    }

    private void initExtensionSDCard(String str) {
        TextView textView = (TextView) findViewById(R.id.extension_sdcard_storage_status);
        float totalSizeInGB = getTotalSizeInGB(str);
        float availableSizeInGB = getAvailableSizeInGB(str);
        textView.setText(String.format(textView.getText().toString(), Float.valueOf(totalSizeInGB), Float.valueOf(availableSizeInGB)));
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.extension_sdcard_progressbar);
        progressBar.setMax((int) totalSizeInGB);
        progressBar.setProgress((int) (totalSizeInGB - availableSizeInGB));
        this.mExtensionSDCardCheckView = (CheckImageView) findViewById(R.id.extension_sdcard_checkView);
        this.mExtensionSDCardCheckView.m1899a(R.drawable.img_checkbox_multiselect_unchecked, R.drawable.img_checkbox_multiselect_checked);
        final String str2 = getWritableBasePath(str) + File.separator + "song";
        if (!FileUtils.isDir(str2)) {
            FileUtils.m8406f(str2);
        }
        this.mExtensionSDCardCheckView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.setting.DownloadLocationActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!DownloadLocationActivity.this.mExtensionSDCardCheckView.m1900a()) {
                    DownloadLocationActivity.this.setCheckViewById(R.id.extension_sdcard_checkView);
                    try {
                        Preferences.m2880g(str2);
                        DownloadLocationActivity.this.mDownloadPathInfoTextView.setText(String.format(DownloadLocationActivity.this.getResources().getString(R.string.download_path_info), str2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (SDKVersionUtils.m8365i() && Preferences.m2911br()) {
                        PopupsUtils.m6747a(DownloadLocationActivity.this, (int) R.string.never_show_again, "通知", DownloadLocationActivity.this.getString(R.string.download_chose_tip_info), (BaseDialog.InterfaceC1064a<OptionalDialog>) null).m7254b(R.string.i_known, new BaseDialog.InterfaceC1064a<OptionalDialog>() { // from class: com.sds.android.ttpod.activities.setting.DownloadLocationActivity.3.1
                            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                            /* renamed from: a  reason: avoid collision after fix types in other method */
                            public void mo2038a(OptionalDialog optionalDialog) {
                                Preferences.m2963ai(!optionalDialog.m6808b());
                            }
                        });
                    }
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_MUSIC_SAVE_EXTERN_SD, SPage.PAGE_NONE);
                }
            }
        });
        if (Preferences.m3054N().equals(str2)) {
            setCheckViewById(R.id.extension_sdcard_checkView);
        }
    }

    private String getWritableBasePath(String str) {
        String str2 = str + File.separator + "ttpod";
        if (SDKVersionUtils.m8365i() && this.mExtensionCardPath.equals(str) && !this.mExtensionCardPath.equals(EnvironmentUtils.C0605d.getSdcardPath())) {
            str2 = EnvironmentUtils.C0605d.m8470a(this, EnvironmentUtils.C0605d.EnumC0607a.SECOND_SD_CARD);
        }
        if (!FileUtils.isDir(str2)) {
            FileUtils.m8406f(str2);
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckViewById(int i) {
        if (i == R.id.standard_sdcard_checkView) {
            trySetCheckView(this.mStandardSDCardCheckView, true);
            trySetCheckView(this.mExtensionSDCardCheckView, false);
        } else if (i == R.id.extension_sdcard_checkView) {
            trySetCheckView(this.mStandardSDCardCheckView, false);
            trySetCheckView(this.mExtensionSDCardCheckView, true);
        } else {
            trySetCheckView(this.mStandardSDCardCheckView, false);
            trySetCheckView(this.mExtensionSDCardCheckView, false);
        }
    }

    void trySetCheckView(CheckImageView checkImageView, boolean z) {
        if (checkImageView != null) {
            checkImageView.setChecked(z);
        }
    }

    private float getTotalSizeInGB(String str) {
        StatFs statFs = new StatFs(new File(str).getPath());
        return (statFs.getBlockCount() * statFs.getBlockSize()) / 1.0737418E9f;
    }

    private float getAvailableSizeInGB(String str) {
        StatFs statFs = new StatFs(new File(str).getPath());
        return (statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1.0737418E9f;
    }

    private boolean checkSDCardPath(String str) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        String str2 = getWritableBasePath(str) + File.separator + valueOf.toString();
        FileUtils.m8407e(str2);
        if (FileUtils.m8419a(str2)) {
            FileUtils.m8404h(str2);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            String stringExtra = intent.getStringExtra(FilePickerActivity.KEY_EXTRA_SELECTED_FILES);
            if (!EnvironmentUtils.C0605d.m8468a(stringExtra)) {
                final File file = new File(EnvironmentUtils.C0605d.m8462c(this), "song");
                MessageDialog messageDialog = new MessageDialog(this, getString(R.string.change_to_valid_path, new Object[]{file.getAbsolutePath()}), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.activities.setting.DownloadLocationActivity.4
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(MessageDialog messageDialog2) {
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        DownloadLocationActivity.this.updateDownloadPath(file.getAbsolutePath());
                    }
                }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
                messageDialog.setTitle(R.string.invalid_download_path);
                messageDialog.show();
                return;
            }
            updateDownloadPath(stringExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadPath(String str) {
        String str2 = getWritableBasePath(this.mStandardCardPath) + File.separator + "song";
        String str3 = getWritableBasePath(this.mExtensionCardPath) + File.separator + "song";
        FileUtils.m8406f(str);
        if (!StringUtils.isEmpty(str) && str.equals(str2)) {
            setCheckViewById(R.id.standard_sdcard_checkView);
        } else if (!StringUtils.isEmpty(str) && str.equals(str3)) {
            setCheckViewById(R.id.extension_sdcard_checkView);
        } else {
            setCheckViewById(-1);
        }
        Preferences.m2880g(str);
        this.mDownloadPathInfoTextView.setText(String.format(getResources().getString(R.string.download_path_info), str));
    }
}
