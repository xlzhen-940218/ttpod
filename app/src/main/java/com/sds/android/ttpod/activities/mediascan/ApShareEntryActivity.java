package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.apshare.WifiAPManager;
import com.sds.android.ttpod.fragment.apshare.ApShareChooseFragment;
import com.sds.android.ttpod.fragment.apshare.ApShareEntryFragment;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;

/* loaded from: classes.dex */
public class ApShareEntryActivity extends BaseActivity {
    public static final int REQUEST_CODE_WIFI = 2;
    private static final String TAG = "ApShareEntryActivity";
    private String mMediaId;
    private WifiAPManager mWifiApManager;

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        BaseFragment baseFragment;
        super.onCreate(bundle);
        setContentView(R.layout.activity_lightweight_entry);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: com.sds.android.ttpod.activities.mediascan.ApShareEntryActivity.1
            @Override // android.support.v4.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChanged() {
                if (ApShareEntryActivity.this.getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    if (!StringUtils.m8346a(ApShareEntryActivity.this.mMediaId)) {
                        ApShareEntryActivity.this.mMediaId = null;
                        return;
                    }
                    ApShareEntryActivity.this.finish();
                    LogUtils.m8379d(ApShareEntryActivity.TAG, "main activity finish");
                }
            }
        });
        setLaunchFragmentAttr(R.id.layout_entry, R.anim.slide_in_right, R.anim.slide_out_right);
        this.mWifiApManager = WifiAPManager.m7053a(getApplicationContext());
        Intent intent = getIntent();
        if (intent != null) {
            this.mMediaId = intent.getStringExtra("key_media_id");
        }
        if (StringUtils.m8346a(this.mMediaId)) {
            baseFragment = new ApShareEntryFragment();
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putString("key_media_id", this.mMediaId);
            baseFragment = (BaseFragment) Fragment.instantiate(this, ApShareChooseFragment.class.getName(), bundle2);
        }
        launchFragment(baseFragment);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtils.m8379d(TAG, "onResume");
        this.mWifiApManager.m7033f();
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtils.m8379d(TAG, "onDestroy");
        this.mWifiApManager.m7040b("TTPODShare-");
        this.mWifiApManager.m7033f();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 2:
                    if (intent.getBooleanExtra(MediaScanWifiActivity.KEY_WIFI_UPLOAD, false)) {
                        String[] strArr = {TTPodConfig.m5296l()};
                        Intent intent2 = new Intent(this, MediaScanAnimationActivity.class);
                        intent2.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, strArr);
                        startActivity(intent2);
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
