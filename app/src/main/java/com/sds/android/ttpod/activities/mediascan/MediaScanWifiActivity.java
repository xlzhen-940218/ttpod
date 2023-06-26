package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class MediaScanWifiActivity extends SlidingClosableActivity {
    public static final String KEY_WIFI_UPLOAD = "key_wifi_upload";
    private static final String TAG = "MediaScanWifiActivity";
    private boolean mHasMediaFileUploaded;
    private TextView mInputIpTextView;
    private TextView mIpAddressTextView;
    private TextView mTipsTextView;
    private TextView mTitleTextView;

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mediascan_wifi);
        setPage(SPage.PAGE_SCAN_MUSIC_UPLOAD_WIFI);
        setTitle(R.string.mediascan_wifi);
        getActionBarController().m7179d();
        this.mTitleTextView = (TextView) findViewById(R.id.textview_mediascan_wifi_title);
        this.mTipsTextView = (TextView) findViewById(R.id.textview_mediascan_wifi_tips);
        this.mInputIpTextView = (TextView) findViewById(R.id.textview_mediascan_wifi_input_ip);
        this.mIpAddressTextView = (TextView) findViewById(R.id.textview_mediascan_wifi_ipaddress);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_WIFI_TRANSMISSION_STATE, ReflectUtils.m8375a(MediaScanWifiActivity.class, "updateWifiTransmissionState", CommonResult.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        CommandCenter.getInstance().execute(new Command(CommandID.START_WIFI_TRANSMISSION, new Object[0]));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        CommandCenter.getInstance().execute(new Command(CommandID.STOP_WIFI_TRANSMISSION, new Object[0]));
    }

    public void updateWifiTransmissionState(CommonResult commonResult) {
        LogUtils.error(TAG, "updateWifiTransmissionState: " + commonResult.toString());
        ErrCode m4585a = commonResult.m4585a();
        if (ErrCode.ErrNone == m4585a) {
            this.mTitleTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.img_textview_mediascan_wifi_on), (Drawable) null, (Drawable) null);
            this.mTitleTextView.setText(getText(R.string.mediascan_wifi_title));
            this.mTipsTextView.setText(getText(R.string.mediascan_wifi_tips));
            this.mInputIpTextView.setVisibility(View.VISIBLE);
            this.mIpAddressTextView.setVisibility(View.VISIBLE);
            this.mIpAddressTextView.setText(commonResult.m4582d().toString());
        } else if (ErrCode.ErrCompletion == m4585a) {
            this.mHasMediaFileUploaded = true;
        } else {
            this.mTitleTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.img_textview_mediascan_wifi_off), (Drawable) null, (Drawable) null);
            this.mTitleTextView.setText(getText(R.string.mediascan_wifi_no_wifi));
            this.mTipsTextView.setText(getText(R.string.mediascan_wifi_check_network));
            this.mInputIpTextView.setVisibility(View.GONE);
            this.mIpAddressTextView.setVisibility(View.GONE);
        }
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.app.Activity
    public void finish() {
        setResult(-1, new Intent().putExtra(KEY_WIFI_UPLOAD, this.mHasMediaFileUploaded));
        super.finish();
    }
}
