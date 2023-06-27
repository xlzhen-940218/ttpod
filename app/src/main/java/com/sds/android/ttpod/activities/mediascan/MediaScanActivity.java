package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.mediascan.setting.MediaScanSettingActivity;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.utils.EntryUtils;

/* loaded from: classes.dex */
public class MediaScanActivity extends SlidingClosableActivity {
    private static final int REQUEST_CODE_FILE_PICKER = 0;
    private static final int REQUEST_CODE_SETTING = 1;
    private static final int REQUEST_CODE_WIFI = 2;
    private static final String TAG = "MediaScanActivity";
    private View mCustomView;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.mediascan.MediaScanActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_mediascan_custom /* 2131230894 */:
                    //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_SCAN_MUSIC_CUSTOM, SPage.PAGE_NONE, SPage.PAGE_SCAN_MUSIC_CUSTOM);
                    Intent intent = new Intent(MediaScanActivity.this, FilePickerActivity.class);
                    intent.putExtra(FilePickerActivity.KEY_EXTRA_CONFIRMYPE, 2);
                    MediaScanActivity.this.startActivityForResult(intent, 0);
                    return;
                case R.id.button_mediascan_wifi /* 2131230895 */:
                    //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_SCAN_MUSCI_WIFI, SPage.PAGE_NONE, SPage.PAGE_SCAN_MUSIC_UPLOAD_WIFI);
                    EntryUtils.m8301a(MediaScanActivity.this, 2);
                    return;
                case R.id.button_mediascan_setting /* 2131230896 */:
                    //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_SCAN_MUSCI_SETTING, SPage.PAGE_NONE, SPage.PAGE_SCAN_MUSIC_SETTING);
                    MediaScanActivity.this.startActivityForResult(new Intent(MediaScanActivity.this, MediaScanSettingActivity.class), 1);
                    return;
                case R.id.button_mediascan_start /* 2131230897 */:
                    //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_SCAN_MUSCI_ONE_KEY, SPage.PAGE_NONE, SPage.PAGE_SCAN_MUSIC_ONE_KEY);
                    MediaScanActivity.this.startActivity(new Intent(MediaScanActivity.this, MediaScanAnimationActivity.class));
                    MediaScanActivity.this.finish();
                    return;
                default:
                    return;
            }
        }
    };
    private View mSettingView;
    private View mStartView;
    private View mWifiView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mediascan);
        setPage(SPage.PAGE_SCAN_MUSIC);
        setTitle(R.string.media_scan);
        getActionBarController().m7179d();
        this.mCustomView = findViewById(R.id.button_mediascan_custom);
        this.mCustomView.setOnClickListener(this.mOnClickListener);
        this.mWifiView = findViewById(R.id.button_mediascan_wifi);
        this.mWifiView.setOnClickListener(this.mOnClickListener);
        this.mSettingView = findViewById(R.id.button_mediascan_setting);
        this.mSettingView.setOnClickListener(this.mOnClickListener);
        this.mStartView = findViewById(R.id.button_mediascan_start);
        this.mStartView.setOnClickListener(this.mOnClickListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 0:
                    String[] stringArrayExtra = intent.getStringArrayExtra(FilePickerActivity.KEY_EXTRA_SELECTED_FILES);
                    if (stringArrayExtra != null && stringArrayExtra.length > 0) {
                        LogUtils.debug(TAG, "set custom scan folder: " + stringArrayExtra.toString());
                        Intent intent2 = new Intent(this, MediaScanAnimationActivity.class);
                        intent2.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, stringArrayExtra);
                        startActivity(intent2);
                        finish();
                        return;
                    }
                    return;
                case 1:
                    finish();
                    return;
                case 2:
                    if (intent.getBooleanExtra(MediaScanWifiActivity.KEY_WIFI_UPLOAD, false)) {
                        String[] strArr = {TTPodConfig.getMyMusicsPath()};
                        Intent intent3 = new Intent(this, MediaScanAnimationActivity.class);
                        intent3.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, strArr);
                        startActivity(intent3);
                        finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
