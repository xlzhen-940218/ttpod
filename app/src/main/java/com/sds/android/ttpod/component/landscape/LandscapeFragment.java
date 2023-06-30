package com.sds.android.ttpod.component.landscape;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.landscape.p098b.NextEffect;
import com.sds.android.ttpod.component.landscape.p098b.Scene;
import com.sds.android.ttpod.component.landscape.temporary.ShowFrame;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.main.BasePlayerFragment;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.lyric.Lyric;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.player.PlayStatus;

/* loaded from: classes.dex */
public class LandscapeFragment extends BasePlayerFragment {
    private static final int ANIM_DURATION = 300;
    private static final int MENU_SHOW_DURATION = 5000;
    private static final int MSG_ID_HIDE_MENU = 1;
    private static final String TAG = "LandscapeFragment";
    private AudioManager mAudioManager;
    private View mBottomMenu;
    private Dialog mDialog;
    private GLSurfaceView mGLSurfaceView;
    private AnimationSet mHideBottomMenuAnimal;
    private AnimationSet mHideTopMenuAnimal;
    private LandscapeAnimTransView mLandscapeAnimTransView;
    private ViewGroup mLandscapeContent;
    private ViewGroup mLandscapeView;
    private LyricView mLyricView;
    private LandscapeMediaHelper mMediaHelper;
    private View mNewLandScapeView;
    private View mNextView;
    private View mOldLandScapeView;
    private View mPauseView;
    private View mPlayView;
    private View mPreviousView;
    private LandscapeScreenCaptureHelper mScreenCapture;
    private View mShareView;
    private AnimationSet mShowBottomMenuAnimal;
    private AnimationSet mShowTopMenuAnimal;
    private ImageView mSwitchEffectIcon;
    private ImageView mSwitchLandscapeIcon;
    private View mTopMenu;
    private SeekBar mVolumeSeekBar;
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.component.landscape.LandscapeFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (LandscapeFragment.this.getActivity() != null && message.what == 1) {
                LandscapeFragment.this.hideMenu();
            }
        }
    };
    private View.OnClickListener mClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.landscape.LandscapeFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (LandscapeFragment.this.getActivity() != null) {
                switch (view.getId()) {
                    case R.id.button_share /* 2131231006 */:
                        if (!Cache.getInstance().getCurrentPlayMediaItem().isNull()) {
                            if (LandscapeFragment.this.mScreenCapture != null) {
                                LandscapeFragment.this.mScreenCapture.m6134a();
                                return;
                            }
                            Bitmap createBitmap = Bitmap.createBitmap(LandscapeFragment.this.mLandscapeView.getWidth(), LandscapeFragment.this.mLandscapeView.getHeight(), Bitmap.Config.RGB_565);
                            LandscapeFragment.this.mLandscapeView.draw(new Canvas(createBitmap));
                            PopupsUtils.m6754a(LandscapeFragment.this.getActivity(), Cache.getInstance().getCurrentPlayMediaItem(), createBitmap);
                            return;
                        }
                        return;
                    case R.id.imagebutton_previous_landscape /* 2131231591 */:
                        ((BaseActivity) LandscapeFragment.this.getActivity()).acquireFastClickSupport();
                        CommandCenter.getInstance().execute(new Command(CommandID.PREVIOUS, new Object[0]));
                        return;
                    case R.id.imagebutton_play_landscape /* 2131231592 */:
                        ((BaseActivity) LandscapeFragment.this.getActivity()).acquireFastClickSupport();
                        CommandCenter.getInstance().execute(new Command(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PAUSED ? CommandID.RESUME : CommandID.START, new Object[0]));
                        return;
                    case R.id.imagebutton_pause_landscape /* 2131231593 */:
                        ((BaseActivity) LandscapeFragment.this.getActivity()).acquireFastClickSupport();
                        CommandCenter.getInstance().execute(new Command(CommandID.PAUSE, new Object[0]));
                        return;
                    case R.id.imagebutton_next_landscape /* 2131231594 */:
                        ((BaseActivity) LandscapeFragment.this.getActivity()).acquireFastClickSupport();
                        CommandCenter.getInstance().execute(new Command(CommandID.NEXT, new Object[0]));
                        return;
                    case R.id.gesture /* 2131231596 */:
                    case R.id.animtransview_landscape_old /* 2131231598 */:
                    case R.id.lyricview_landscape_old /* 2131231599 */:
                        LandscapeFragment.this.toggleMenu();
                        return;
                    case R.id.switch_effect /* 2131231602 */:
                        final Scene m6118b = SceneManager.m6121a().m6118b();
                        if (m6118b != null && (m6118b instanceof NextEffect)) {
                            LandscapeFragment.this.mGLSurfaceView.queueEvent(new Runnable() { // from class: com.sds.android.ttpod.component.landscape.LandscapeFragment.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ((NextEffect) m6118b).mo6214f_();
                                }
                            });
                            return;
                        }
                        return;
                    case R.id.select_landscape /* 2131231603 */:
                        Preferences.m3067G(!Preferences.m2949av());
                        LandscapeFragment.this.switchLandscape();
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private LyricView.TouchListener mLyricTouchListener = new LyricView.TouchListener() { // from class: com.sds.android.ttpod.component.landscape.LandscapeFragment.3
        @Override // com.sds.android.ttpod.framework.modules.skin.view.LyricView.InterfaceC1998c
        /* renamed from: a */
        public void mo3408a(long j) {
            CommandCenter.getInstance().execute(new Command(CommandID.SET_POSITION, Integer.valueOf((int) j)));
        }

        @Override // com.sds.android.ttpod.framework.modules.skin.view.LyricView.InterfaceC1998c
        /* renamed from: a */
        public void mo3409a(int i) {
        }
    };

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void playMediaChanged() {
        super.playMediaChanged();
        Bitmap currentArtistBitmap = getCurrentArtistBitmap();
        if (this.mMediaHelper != null) {
            this.mMediaHelper.m6159a(currentArtistBitmap);
        }
        if (this.mLandscapeAnimTransView != null) {
            this.mLandscapeAnimTransView.setImageBitmap(currentArtistBitmap);
        }
        invalidLyric();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.debug(TAG, "onCreate looklyric");
        LandscapeData.m6335a(getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AudioManager getAudioManager() {
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        }
        return this.mAudioManager;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mLandscapeView = (ViewGroup) layoutInflater.inflate(R.layout.landscape_player, (ViewGroup) null);
        this.mLandscapeContent = (ViewGroup) this.mLandscapeView.findViewById(R.id.layout_landscape_content);
        this.mTopMenu = this.mLandscapeView.findViewById(R.id.volume_panel);
        SeekBar seekBar = (SeekBar) this.mTopMenu.findViewById(R.id.seekbar_volume_landscape);
        seekBar.setMax(getAudioManager().getStreamMaxVolume(3));
        seekBar.setProgress(getAudioManager().getStreamVolume(3));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.component.landscape.LandscapeFragment.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                LandscapeFragment.this.hideMenuDelay();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
                LandscapeFragment.this.mHandler.removeMessages(1);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i, boolean z) {
                LandscapeFragment.this.getAudioManager().setStreamVolume(3, i, 0);
            }
        });
        LandscapeLockerIcon landscapeLockerIcon = (LandscapeLockerIcon) this.mLandscapeView.findViewById(R.id.icon_locker);
        landscapeLockerIcon.setOnOffDrawable(getResources().getDrawable(R.drawable.xml_button_lock_landscape)
                , getResources().getDrawable(R.drawable.xml_button_unlock_landscape));
        landscapeLockerIcon.m6375a();
        landscapeLockerIcon.setOnLockerStateChangeListener(new LandscapeLockerIcon.OnLockerStateChangeListener() { // from class: com.sds.android.ttpod.component.landscape.LandscapeFragment.5
            @Override // com.sds.android.ttpod.component.landscape.LandscapeLockerIcon.InterfaceC1250a
            /* renamed from: a */
            public void changed(int change) {
                LandscapeFragment.this.getActivity().setRequestedOrientation(change == 0 ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                LandscapeFragment.this.hideMenuDelay();
            }
        });
        this.mVolumeSeekBar = seekBar;
        this.mBottomMenu = this.mLandscapeView.findViewById(R.id.control_panel);
        this.mPreviousView = this.mBottomMenu.findViewById(R.id.imagebutton_previous_landscape);
        this.mPreviousView.setOnClickListener(this.mClickListener);
        this.mPauseView = this.mBottomMenu.findViewById(R.id.imagebutton_pause_landscape);
        this.mPauseView.setOnClickListener(this.mClickListener);
        this.mPlayView = this.mBottomMenu.findViewById(R.id.imagebutton_play_landscape);
        this.mPlayView.setOnClickListener(this.mClickListener);
        this.mNextView = this.mBottomMenu.findViewById(R.id.imagebutton_next_landscape);
        this.mNextView.setOnClickListener(this.mClickListener);
        this.mSwitchLandscapeIcon = (ImageView) this.mLandscapeView.findViewById(R.id.select_landscape);
        this.mSwitchLandscapeIcon.setOnClickListener(this.mClickListener);
        this.mSwitchEffectIcon = (ImageView) this.mLandscapeView.findViewById(R.id.switch_effect);
        this.mSwitchEffectIcon.setOnClickListener(this.mClickListener);
        this.mShareView = this.mLandscapeView.findViewById(R.id.button_share);
        this.mShareView.setOnClickListener(this.mClickListener);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300L);
        this.mShowTopMenuAnimal = new AnimationSet(false);
        this.mShowTopMenuAnimal.addAnimation(alphaAnimation);
        this.mShowBottomMenuAnimal = new AnimationSet(false);
        this.mShowBottomMenuAnimal.addAnimation(alphaAnimation);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation2.setDuration(300L);
        this.mHideTopMenuAnimal = new AnimationSet(false);
        this.mHideTopMenuAnimal.addAnimation(alphaAnimation2);
        this.mHideBottomMenuAnimal = new AnimationSet(false);
        this.mHideBottomMenuAnimal.addAnimation(alphaAnimation2);
        this.mDialog = new DialogC1248a(getActivity(), R.style.Dialog_FullScreen);
        this.mDialog.requestWindowFeature(1);
        this.mLandscapeView.setKeepScreenOn(Preferences.m3078B());
        return this.mLandscapeView;
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        View view = getView();
        if (view != null) {
            if (view.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            this.mDialog.setContentView(view);
        }
        this.mDialog.setOwnerActivity(getActivity());
        this.mDialog.setCancelable(false);
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        this.mDialog.show();
    }

    public void show(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().add(this, "").commitAllowingStateLoss();
    }

    public void dismiss() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
        getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    @Override // android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            this.mDialog.dismiss();
        } else {
            this.mDialog.show();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        switchLandscape();
        startUpdatePlayPosition();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeMessages(1);
        stopUpdatePlayPosition();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayPosition() {
        if (this.mLyricView != null) {
            this.mLyricView.setPlayingTime(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k().intValue());
        }
        if (this.mMediaHelper != null) {
            this.mMediaHelper.m6160a(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k().intValue());
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void setArtistBitmap(Bitmap bitmap, String str) {
        super.setArtistBitmap(bitmap, str);
        if (this.mMediaHelper != null) {
            this.mMediaHelper.m6159a(bitmap);
        }
        if (this.mLandscapeAnimTransView != null) {
            this.mLandscapeAnimTransView.setImageBitmap(bitmap);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void artistBitmapSearchStarted() {
        super.artistBitmapSearchStarted();
        if (this.mMediaHelper != null) {
            this.mMediaHelper.m6159a((Bitmap) null);
        }
        if (this.mLandscapeAnimTransView != null) {
            this.mLandscapeAnimTransView.setImageBitmap(null);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricLoadFinished() {
        super.lyricLoadFinished();
        LogUtils.debug(TAG, "lyricLoadFinished looklyric");
        if (this.mLyricView != null) {
            this.mLyricView.setLyric(getCurrentLyric());
        }
        if (this.mMediaHelper != null) {
            this.mMediaHelper.m6158a(getCurrentLyric());
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricSearchStarted() {
        super.lyricSearchStarted();
        LogUtils.debug(TAG, "lyricSearchStarted looklyric");
        if (this.mLyricView != null) {
            this.mLyricView.setState(2);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricDownloadStarted() {
        super.lyricDownloadStarted();
        LogUtils.debug(TAG, "lyricDownloadStarted looklyric");
        if (this.mLyricView != null) {
            this.mLyricView.setState(4);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricDownloadError() {
        super.lyricDownloadError();
        LogUtils.debug(TAG, "lyricDownloadError looklyric");
        invalidLyric();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricSearchStop() {
        super.lyricSearchStop();
        LogUtils.debug(TAG, "lyricSearchStop looklyric");
        invalidLyric();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayMediaInfo() {
        if (this.mMediaHelper != null) {
            this.mMediaHelper.m6157a(Cache.getInstance().getCurrentPlayMediaItem(), 0);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricSearchFailed() {
        super.lyricSearchFailed();
        LogUtils.debug(TAG, "lyricSearchFailed looklyric");
        invalidLyric();
    }

    private void invalidLyric() {
        if (this.mLyricView != null) {
            this.mLyricView.setState(1);
        }
        if (this.mMediaHelper != null) {
            this.mMediaHelper.m6158a((Lyric) null);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void lyricNetError() {
        super.lyricNetError();
        LogUtils.debug(TAG, "lyricNetError looklyric");
        invalidLyric();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        if (isViewAccessAble()) {
            boolean z = PlayStatus.STATUS_PLAYING == SupportFactory.getInstance(BaseApplication.getApplication()).m2463m();
            this.mBottomMenu.findViewById(R.id.imagebutton_play_landscape).setVisibility(z ? View.GONE : View.VISIBLE);
            this.mBottomMenu.findViewById(R.id.imagebutton_pause_landscape).setVisibility(z ? View.VISIBLE : View.GONE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleMenu() {
        if (this.mTopMenu.getVisibility() == View.GONE) {
            showMenu();
        } else {
            hideMenu();
        }
    }

    private void showMenu() {
        if (this.mTopMenu.getVisibility() != View.VISIBLE) {
            this.mTopMenu.setVisibility(View.VISIBLE);
            this.mBottomMenu.setVisibility(View.VISIBLE);
            this.mTopMenu.clearAnimation();
            this.mTopMenu.startAnimation(this.mShowTopMenuAnimal);
            this.mBottomMenu.clearAnimation();
            this.mBottomMenu.startAnimation(this.mShowBottomMenuAnimal);
        }
        hideMenuDelay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideMenu() {
        this.mTopMenu.setVisibility(View.GONE);
        this.mBottomMenu.setVisibility(View.GONE);
        this.mTopMenu.clearAnimation();
        this.mTopMenu.startAnimation(this.mHideTopMenuAnimal);
        this.mBottomMenu.clearAnimation();
        this.mBottomMenu.startAnimation(this.mHideBottomMenuAnimal);
        this.mHandler.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideMenuDelay() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchLandscape() {
        if (Preferences.m2949av()) {
            if (getActivity() != null) {
                loadNewLandscape();
                return;
            }
            return;
        }
        loadOldLandscape();
    }

    private void removeNewLandscape() {
        this.mSwitchEffectIcon.setVisibility(View.GONE);
        if (this.mNewLandScapeView != null) {
            this.mGLSurfaceView.onPause();
            this.mGLSurfaceView = null;
            this.mNewLandScapeView = null;
            this.mLandscapeContent.removeAllViews();
            this.mMediaHelper = null;
            this.mScreenCapture = null;
            ShowFrame.m6094a().m6092c();
            SceneManager.m6121a().m6114f();
        }
    }

    private void loadNewLandscape() {
        if (this.mNewLandScapeView == null) {
            LogUtils.debug(TAG, "loadNewLandscape looklyric");
            removeOldLandscape();
            this.mSwitchEffectIcon.setVisibility(View.VISIBLE);
            this.mSwitchLandscapeIcon.setImageResource(R.drawable.xml_landscape_switch_old);
            this.mSwitchLandscapeIcon.setVisibility(View.VISIBLE);
            this.mNewLandScapeView = View.inflate(getActivity(), R.layout.landscape_new_layout, this.mLandscapeContent);
            this.mGLSurfaceView = (GLSurfaceView) this.mNewLandScapeView.findViewById(R.id.main_scene);
            this.mGLSurfaceView.setRenderer(new LandscapeRenderer(getActivity()));
            LandscapeGestureHelper landscapeGestureHelper = new LandscapeGestureHelper();
            GestureView gestureView = (GestureView) this.mNewLandScapeView.findViewById(R.id.gesture);
            gestureView.setTTPodClickListener(this.mClickListener);
            gestureView.setTTPodLongClickListener(landscapeGestureHelper);
            gestureView.setGestureTranslation(landscapeGestureHelper);
            gestureView.setGestureRotate(landscapeGestureHelper);
            gestureView.setGestureScale(landscapeGestureHelper);
            gestureView.setGestureState(landscapeGestureHelper);
            this.mMediaHelper = new LandscapeMediaHelper(getActivity());
            this.mMediaHelper.m6160a(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k().intValue());
            this.mMediaHelper.m6157a(Cache.getInstance().getCurrentPlayMediaItem(), 0);
            updatePlayStatus(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
            this.mMediaHelper.m6159a(getCurrentArtistBitmap());
            this.mMediaHelper.m6158a(getCurrentLyric());
            this.mScreenCapture = new LandscapeScreenCaptureHelper(new LandscapeScreenCaptureHelper.InterfaceC1271a() { // from class: com.sds.android.ttpod.component.landscape.LandscapeFragment.6
                @Override // com.sds.android.ttpod.component.landscape.LandscapeScreenCaptureHelper.InterfaceC1271a
                /* renamed from: a */
                public void mo6131a(Bitmap bitmap) {
                    PopupsUtils.m6754a(LandscapeFragment.this.getActivity(), Cache.getInstance().getCurrentPlayMediaItem(), bitmap);
                }
            });
            SceneManager.m6121a().m6115e();
            this.mGLSurfaceView.onResume();
        }
    }

    private void removeOldLandscape() {
        if (this.mOldLandScapeView != null) {
            this.mLyricView = null;
            this.mLandscapeAnimTransView = null;
            this.mOldLandScapeView = null;
            this.mLandscapeContent.removeAllViews();
        }
    }

    private void loadOldLandscape() {
        if (this.mOldLandScapeView == null) {
            LogUtils.debug(TAG, "loadOldLandscape looklyric");
            removeNewLandscape();
            this.mSwitchLandscapeIcon.setImageResource(R.drawable.xml_landscape_switch_new);
            this.mSwitchLandscapeIcon.setVisibility(View.VISIBLE);
            this.mOldLandScapeView = View.inflate(getActivity(), R.layout.landscape_old_layout, this.mLandscapeContent);
            this.mOldLandScapeView.setOnClickListener(this.mClickListener);
            updatePlayStatus(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
            this.mLyricView = (LyricView) this.mOldLandScapeView.findViewById(R.id.lyricview_landscape_old);
            this.mLyricView.setTouchListener(this.mLyricTouchListener);
            this.mLyricView.setKalaOK(Preferences.kalaOkEnabled());
            this.mLyricView.setColorHighlight(Preferences.getLyricHighlightColor());
            this.mLyricView.setLyric(getCurrentLyric());
            this.mLyricView.setPlayingTime(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k().intValue());
            this.mLyricView.setOnClickListener(this.mClickListener);
            this.mLandscapeAnimTransView = (LandscapeAnimTransView) this.mOldLandScapeView.findViewById(R.id.animtransview_landscape_old);
            this.mLandscapeAnimTransView.setOnClickListener(this.mClickListener);
            this.mLandscapeAnimTransView.setImageBitmap(getCurrentArtistBitmap());
        }
    }

    /* renamed from: com.sds.android.ttpod.component.landscape.LandscapeFragment$a */
    /* loaded from: classes.dex */
    private class DialogC1248a extends Dialog {

        /* renamed from: b */
        private boolean f4341b;

        public DialogC1248a(Context context, int i) {
            super(context, i);
            this.f4341b = false;
        }

        @Override // android.app.Dialog, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            boolean z = false;
            if (keyEvent.getRepeatCount() > 1) {
                if (i == 24) {
                    LandscapeFragment.this.increaseVolume();
                } else if (i == 25) {
                    LandscapeFragment.this.decreaseVolume();
                } else {
                    return super.onKeyDown(i, keyEvent);
                }
                this.f4341b = false;
                return true;
            }
            this.f4341b = i == 25 || i == 24;
            if (this.f4341b || super.onKeyDown(i, keyEvent)) {
                z = true;
            }
            return z;
        }

        @Override // android.app.Dialog, android.view.KeyEvent.Callback
        public boolean onKeyUp(int i, KeyEvent keyEvent) {
            boolean z = this.f4341b;
            this.f4341b = false;
            switch (i) {
                case 24:
                    if (z) {
                        LandscapeFragment.this.increaseVolume();
                        break;
                    }
                    break;
                case 25:
                    if (z) {
                        LandscapeFragment.this.decreaseVolume();
                        break;
                    }
                    break;
                case 82:
                    LandscapeFragment.this.toggleMenu();
                    break;
                default:
                    return super.onKeyUp(i, keyEvent);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void increaseVolume() {
        if (this.mVolumeSeekBar != null) {
            showMenu();
            this.mVolumeSeekBar.setProgress(this.mVolumeSeekBar.getProgress() + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decreaseVolume() {
        if (this.mVolumeSeekBar != null) {
            showMenu();
            this.mVolumeSeekBar.setProgress(this.mVolumeSeekBar.getProgress() - 1);
        }
    }

    public void initLyricArtistBitmap(Lyric lyric, Bitmap bitmap, String str) {
        setLyric(lyric);
        setArtistBitmap(bitmap, str);
    }
}
