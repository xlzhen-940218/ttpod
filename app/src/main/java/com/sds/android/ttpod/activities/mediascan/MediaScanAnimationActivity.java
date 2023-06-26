package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.SlidingPagerFragment;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.view.AnimationImageView;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public class MediaScanAnimationActivity extends BaseActivity {
    public static final String KEY_GROUP_ID = "key_groupid";
    public static final String KEY_SCAN_FILES = "key_scan_files";
    private static final int MSG_REFRESH = 0;
    private static final int REFRESH_TIME = 50;
    private static final String TAG = "MediaScanAnimationActivity";
    private AnimationImageView mAnimationImageView;
    private boolean mIsScanning;
    private TextView mMediaScanCountTextView;
    private Button mMediaScanFinishedButton;
    private TextView mMediaScanPathTextView;
    private ProgressBar mProgressBar;
    private int[] mImageIds = {R.drawable.img_scan_navigation001, R.drawable.img_scan_navigation002, R.drawable.img_scan_navigation003, R.drawable.img_scan_navigation004, R.drawable.img_scan_navigation005, R.drawable.img_scan_navigation006};
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    MediaScanAnimationActivity.this.refreshProgress();
                    sendEmptyMessageDelayed(0, 50L);
                    return;
                default:
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String[] strArr;
        super.onCreate(bundle);
        setContentView(R.layout.activity_mediascan_animation);
        if (bundle == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            SlidingPagerFragment slidingPagerFragment = new SlidingPagerFragment(this.mImageIds);
            slidingPagerFragment.setAutoScroll(true);
            beginTransaction.replace(R.id.fragment_navigate, slidingPagerFragment);
            beginTransaction.commit();
        }
        this.mAnimationImageView = (AnimationImageView) findViewById(R.id.imageview_mediascan_anim);
        this.mAnimationImageView.setIgnoreFocusChanged(true);
        this.mAnimationImageView.setAnimationResource(R.drawable.xml_imageview_mediascan_animation);
        this.mMediaScanCountTextView = (TextView) findViewById(R.id.textview_mediascan_count);
        this.mMediaScanPathTextView = (TextView) findViewById(R.id.textview_mediascan_scan_path);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progressbar_mediascan_progress);
        this.mProgressBar.setProgress(0);
        this.mMediaScanFinishedButton = (Button) findViewById(R.id.button_mediascan_scan_finished);
        this.mMediaScanFinishedButton.setVisibility(View.GONE);
        this.mMediaScanFinishedButton.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaScanAnimationActivity.this.finish();
            }
        });
        Intent intent = getIntent();
        String str = MediaStorage.GROUP_ID_ALL_LOCAL;
        if (intent != null) {
            strArr = intent.getStringArrayExtra(KEY_SCAN_FILES);
            str = intent.getStringExtra(KEY_GROUP_ID);
        } else {
            strArr = null;
        }
        this.mIsScanning = true;
        if (strArr == null) {
            setPage(SPage.PAGE_SCAN_MUSIC_ONE_KEY);
        }
        CommandCenter.getInstance().execute(new Command(CommandID.STOP_SCAN, new Object[0]));
        CommandCenter m4607a = CommandCenter.getInstance();
        CommandID commandID = CommandID.START_SCAN;
        Object[] objArr = new Object[2];
        objArr[0] = strArr != null ? Arrays.asList(strArr) : null;
        if (StringUtils.isEmpty(str)) {
            str = MediaStorage.GROUP_ID_ALL_LOCAL;
        }
        objArr[1] = str;
        m4607a.execute(new Command(commandID, objArr));
        this.mAnimationImageView.m3504a();
        this.mHandler.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.SCAN_FINISHED, ReflectUtils.m8375a(getClass(), "onScanFinished", Integer.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshProgress() {
        this.mMediaScanCountTextView.setText(String.valueOf((Integer) CommandCenter.getInstance().m4602a(new Command(CommandID.SCANNED_FILE_COUNT, new Object[0]), Integer.class)));
        this.mMediaScanPathTextView.setText((CharSequence) CommandCenter.getInstance().m4602a(new Command(CommandID.SCANNING_FILE_PATH, new Object[0]), String.class));
        this.mProgressBar.setProgress(((Integer) CommandCenter.getInstance().m4602a(new Command(CommandID.SCAN_PROGRESS, new Object[0]), Integer.class)).intValue());
    }

    public void onScanFinished(Integer num) {
        this.mHandler.removeMessages(0);
        this.mIsScanning = false;
        this.mMediaScanCountTextView.setText(String.valueOf(num));
        this.mMediaScanFinishedButton.setVisibility(View.VISIBLE);
        this.mMediaScanPathTextView.setVisibility(View.GONE);
        this.mProgressBar.setVisibility(View.GONE);
        this.mAnimationImageView.m3499b();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (!this.mIsScanning) {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
