package com.sds.android.ttpod.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.widget.DefaultVideoView;
import com.sds.android.ttpod.widget.MediaController;
import com.sds.android.ttpod.widget.MediaTitleBanner;

/* loaded from: classes.dex */
public class DefaultVideoActivity extends BaseActivity implements MediaController.InterfaceC2199a {
    private static final String LOG_TAG = "DefaultVideoActivity";
    private ImageView mBufferingAnimation;
    private TextView mBufferingPercentage;
    private MediaController mMediaController;
    private String mVideoTitle;
    private String mVideoUrl;
    private DefaultVideoView mVideoView;
    private long mPositionWhenPaused = 0;
    private MessageDialog mInstallStormDialog = null;
    private MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.sds.android.ttpod.activities.DefaultVideoActivity.1
        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            LogUtils.info(DefaultVideoActivity.LOG_TAG, "onPrepared");
        }
    };
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.sds.android.ttpod.activities.DefaultVideoActivity.2
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            DefaultVideoActivity.this.finish();
        }
    };
    private MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.sds.android.ttpod.activities.DefaultVideoActivity.3
        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            int i3;
            LogUtils.info(DefaultVideoActivity.LOG_TAG, "position: " + mediaPlayer.getCurrentPosition());
            LogUtils.info(DefaultVideoActivity.LOG_TAG, "what:" + i + " :extra:" + i2);
            int i4 = EnvironmentUtils.DeviceConfig.m8474e() ? i2 : -1004;
            if (1 == i) {
                i4 = -1010;
            }
            switch (i4) {
                case -1010:
                case -1007:
                    DefaultVideoActivity.this.mInstallStormDialog = VideoPlayManager.m5818a(DefaultVideoActivity.this, R.string.system_codec_not_support, null, null, false, new DialogInterface.OnClickListener() { // from class: com.sds.android.ttpod.activities.DefaultVideoActivity.3.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i5) {
                            DefaultVideoActivity.this.finish();
                        }
                    });
                    i3 = R.string.play_error;
                    break;
                case -1004:
                case -110:
                    i3 = R.string.version_update_network_bad;
                    break;
                default:
                    i3 = R.string.play_error;
                    break;
            }
            if (DefaultVideoActivity.this.mInstallStormDialog == null || !DefaultVideoActivity.this.mInstallStormDialog.isShowing()) {
                DefaultVideoActivity.this.finish();
                PopupsUtils.m6760a(i3);
                DefaultVideoActivity.this.mInstallStormDialog = null;
            }
            return true;
        }
    };
    private MediaPlayer.OnInfoListener mOnInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.sds.android.ttpod.activities.DefaultVideoActivity.4
        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            LogUtils.info(DefaultVideoActivity.LOG_TAG, "info:" + i + ":" + i2);
            return true;
        }
    };
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.sds.android.ttpod.activities.DefaultVideoActivity.5
        @Override // android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            DefaultVideoActivity.this.mBufferingPercent = i;
            DefaultVideoActivity.this.mBufferingPercentage.setText(String.valueOf(i) + "%");
        }
    };
    private View.OnClickListener mOnBackEventListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.DefaultVideoActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtils.error(DefaultVideoActivity.LOG_TAG, "OnBackEventListener listener");
            DefaultVideoActivity.this.hideBufferingAnimation();
            DefaultVideoActivity.this.finish();
        }
    };
    private int mCurrentPosition = 0;
    private int mBufferingPercent = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(0);
        setContentView(R.layout.activity_default_video_layout);
        this.mVideoView = (DefaultVideoView) findViewById(R.id.video_view);
        this.mBufferingAnimation = (ImageView) findViewById(R.id.mv_load_animation);
        this.mBufferingPercentage = (TextView) findViewById(R.id.mv_percentage);
        this.mVideoView.setMediaTitleBanner(new MediaTitleBanner(this));
        Intent intent = getIntent();
        this.mVideoUrl = intent.getStringExtra("param_video_url");
        this.mVideoTitle = intent.getStringExtra("param_video_title");
        LogUtils.info(LOG_TAG, "url:" + this.mVideoUrl);
        this.mVideoView.setVideoURI(Uri.parse(this.mVideoUrl));
        this.mVideoView.setVideoTitle(this.mVideoTitle);
        this.mVideoView.mo1689b();
        this.mVideoView.setLapseChangedListener(this);
        this.mMediaController = new MediaController(this);
        this.mVideoView.setMediaController(this.mMediaController);
        this.mVideoView.setOnBackEventListener(this.mOnBackEventListener);
        this.mVideoView.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
        this.mVideoView.setOnErrorListener(this.mOnErrorListener);
        this.mVideoView.setOnCompletionListener(this.mOnCompletionListener);
        this.mVideoView.setOnPreparedListener(this.mOnPreparedListener);
        this.mVideoView.setOnInfoListener(this.mOnInfoListener);
        showBufferingAniamtion();
    }

    private void showBufferingAniamtion() {
        if (this.mBufferingAnimation.getVisibility() != View.VISIBLE) {
            this.mBufferingAnimation.setVisibility(View.VISIBLE);
            this.mBufferingPercentage.setVisibility(View.VISIBLE);
            this.mBufferingAnimation.startAnimation(AnimationUtils.loadAnimation(this, R.anim.unlimited_rotate));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideBufferingAnimation() {
        this.mBufferingAnimation.clearAnimation();
        this.mBufferingAnimation.setVisibility(View.GONE);
        this.mBufferingPercentage.setVisibility(View.GONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtils.info(LOG_TAG, "onResume");
        getWindow().getDecorView().setKeepScreenOn(true);
        this.mBufferingAnimation.setVisibility(View.VISIBLE);
        this.mBufferingAnimation.startAnimation(AnimationUtils.loadAnimation(this, R.anim.unlimited_rotate));
        this.mBufferingPercentage.setVisibility(View.VISIBLE);
        this.mVideoView.mo1690a(this.mPositionWhenPaused);
        if (SDKVersionUtils.m8369e()) {
            this.mVideoView.m1858d();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtils.info(LOG_TAG, "onPause");
        getWindow().getDecorView().setKeepScreenOn(false);
        this.mPositionWhenPaused = this.mVideoView.getCurrentPosition();
        this.mVideoView.mo1688c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtils.info(LOG_TAG, "onStop");
        this.mVideoView.m1871a();
        this.mVideoView.m1850g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        sendBroadcast(new Intent("com.sds.android.ttpod.video_finished"));
        LogUtils.info(LOG_TAG, "onDestroy");
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mVideoView.onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private boolean showBufferingAnimationEnable(MediaPlayer mediaPlayer, int i) {
        return this.mCurrentPosition == 0 || (mediaPlayer.isPlaying() && mediaPlayer.getCurrentPosition() == this.mCurrentPosition && mediaPlayer.getCurrentPosition() != mediaPlayer.getDuration() && i != 100);
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2199a
    public void onLapseChanged(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            if (showBufferingAnimationEnable(mediaPlayer, this.mBufferingPercent)) {
                showBufferingAniamtion();
            } else {
                hideBufferingAnimation();
            }
            if (mediaPlayer.isPlaying()) {
                this.mCurrentPosition = mediaPlayer.getCurrentPosition();
            }
        }
    }
}
