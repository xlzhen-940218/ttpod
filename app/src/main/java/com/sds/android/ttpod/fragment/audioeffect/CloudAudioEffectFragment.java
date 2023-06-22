package com.sds.android.ttpod.fragment.audioeffect;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sds.android.cloudapi.ttpod.data.AudioEffectUser;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectCache;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.p106a.p107a.AudioEffectStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.widget.audioeffect.CircularProgress;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class CloudAudioEffectFragment extends BaseFragment {
    private static final int DELAYTIME = 200;
    private static final int DELAY_LOAD_TIME = 1200;
    private static final int EFFECT_STATUS_USE_AUTO = 2;
    private static final int EFFECT_STATUS_USE_CLOUD = 3;
    private static final int EFFECT_STATUS_USE_LOCAL = 4;
    private static final int EFFECT_STATUS_USE_PRIVATE = 1;
    private static final int EFFECT_STATUS_USE_UNINIT = 0;
    private TextView mAauthor;
    private boolean mAttach;
    private AudioEffectUser mAudioEffectUser;
    private RelativeLayout mAudioStopLayout;
    private LinearLayout mAudioWifiTipLayout;
    private TextView mAutorTextView;
    private MediaItem mCurrentMediaItem;
    private View mDefaultEffectLayout;
    private View mDefaultRootHit;
    private FrameLayout mFrameLayout;
    private ImageView mHeadPlaystatusView;
    private boolean mInit;
    private RelativeLayout mLayoutRoot;
    private TextView mMediaTextView;
    private Button mOfflineContinueBt;
    private ToggleButton mOpenCloudEffectToggleButton;
    private RecommandAudioEffectFragment mRecommandAudioEffectFragment;
    private RelativeLayout mRecommandAudioFragment;
    private View mRootView;
    private RelativeLayout mTitleLayer;
    private Handler mHandler = new Handler();
    private boolean mChangedMedia = false;
    private RunnableC1393a mRunnable = new RunnableC1393a();
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.CloudAudioEffectFragment.4
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            CloudAudioEffectFragment.this.performCloudMatchChanged(z);
            if (z) {
                CloudAudioEffectFragment.this.startRecommandAudioEffectFragment();
            } else {
                CloudAudioEffectFragment.this.mAutorTextView.setText(CloudAudioEffectFragment.this.getResources().getString(R.string.effect_match_no));
            }
            CloudAudioEffectFragment.this.mDefaultEffectLayout.setVisibility(z ? View.VISIBLE : View.GONE);
            CloudAudioEffectFragment.this.updatePlayView();
            new SUserEvent("PAGE_CLICK", SAction.ACTION_EFFECT_MATCH_CLOUD_AUDIO.getValue(), SPage.PAGE_AUDIO_CLOUD_EFFECT.getValue(), SPage.PAGE_NONE.getValue()).append("status", Boolean.valueOf(z)).post();
        }
    };
    private Runnable mRequestRunnable = new Runnable() { // from class: com.sds.android.ttpod.fragment.audioeffect.CloudAudioEffectFragment.5
        @Override // java.lang.Runnable
        public void run() {
            if (CloudAudioEffectFragment.this.mRecommandAudioEffectFragment != null) {
                CloudAudioEffectFragment.this.mRecommandAudioEffectFragment.updateRecommandList();
            }
        }
    };

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_cloud_audio_effect, viewGroup, false);
            initViews();
        }
        return this.mRootView;
    }

    private void initViews() {
        boolean m2974ad = Preferences.m2974ad();
        this.mAutorTextView = (TextView) this.mRootView.findViewById(R.id.textview_effect_equalizer_content);
        this.mAutorTextView.setText(getResources().getString(R.string.effect_match_successex));
        this.mAutorTextView.setSelected(true);
        if (!m2974ad) {
            this.mAutorTextView.setText(getResources().getString(R.string.effect_match_no));
        }
        this.mOpenCloudEffectToggleButton = (ToggleButton) this.mRootView.findViewById(R.id.togglebutton_effect_equalizer_cloud);
        this.mOpenCloudEffectToggleButton.setChecked(m2974ad);
        this.mOpenCloudEffectToggleButton.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        initDefaultAudioEffectViews(m2974ad);
        this.mMediaTextView = (TextView) this.mRootView.findViewById(R.id.textview_effect_equalizer_media);
        this.mAudioStopLayout = (RelativeLayout) this.mRootView.findViewById(R.id.audio_stop);
        this.mAudioWifiTipLayout = (LinearLayout) this.mRootView.findViewById(R.id.audio_wifi_tip);
        this.mOfflineContinueBt = (Button) this.mRootView.findViewById(R.id.button_offline_continue);
        this.mOfflineContinueBt.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.CloudAudioEffectFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Preferences.m2814x(false);
                Preferences.m3077B(true);
                CommandCenter.m4607a().m4606a(new Command(CommandID.SET_LOCAL_AUDIO_EFFECT, false));
                CloudAudioEffectFragment.this.requestAudioEffect();
            }
        });
        this.mFrameLayout = (FrameLayout) this.mRootView.findViewById(R.id.recomand_audio_effect);
        this.mAauthor = (TextView) this.mRootView.findViewById(R.id.music_author);
        this.mLayoutRoot = (RelativeLayout) this.mRootView.findViewById(R.id.layout_root);
        this.mLayoutRoot.setSelected(true);
        this.mHeadPlaystatusView = (ImageView) this.mRootView.findViewById(R.id.head_playstatus);
        updatePlayView();
        CommandCenter.m4607a().m4596b(new Command(CommandID.QUERY_EFFECT_USERINFO, new Object[0]));
        this.mTitleLayer = (RelativeLayout) this.mRootView.findViewById(R.id.new_title);
        this.mTitleLayer.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.CloudAudioEffectFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayStatus m2463m = SupportFactory.m2397a(BaseApplication.getApplication()).m2463m();
                if (m2463m == PlayStatus.STATUS_PAUSED) {
                    CommandCenter.m4607a().m4606a(new Command(CommandID.RESUME, new Object[0]));
                } else if (m2463m == PlayStatus.STATUS_PLAYING) {
                    CommandCenter.m4607a().m4606a(new Command(CommandID.PAUSE, new Object[0]));
                } else {
                    CommandCenter.m4607a().m4606a(new Command(CommandID.START, new Object[0]));
                }
                CloudAudioEffectFragment.this.updateAudioEffectInfoByRunnable();
            }
        });
        this.mInit = true;
    }

    private void initDefaultAudioEffectViews(boolean z) {
        this.mCurrentMediaItem = Cache.m3218a().m3225N();
        this.mDefaultEffectLayout = this.mRootView.findViewById(R.id.recommends_effect_default);
        ((CircularProgress) this.mDefaultEffectLayout.findViewById(R.id.rotatebutton_bass_boost)).setPaintText(VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY);
        ((CircularProgress) this.mDefaultEffectLayout.findViewById(R.id.rotatebutton_treble_boost)).setPaintText("T");
        ((CircularProgress) this.mDefaultEffectLayout.findViewById(R.id.rotatebutton_surround)).setPaintText("S");
        updateWaveView((EqualizerAnimationWaveView) this.mDefaultEffectLayout.findViewById(R.id.waveview_effect_equalizer_equ));
        if (!z) {
            this.mDefaultEffectLayout.setVisibility(View.GONE);
        }
        this.mDefaultRootHit = this.mDefaultEffectLayout.findViewById(R.id.root_hit);
        this.mDefaultRootHit.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.CloudAudioEffectFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CloudAudioEffectFragment.this.handleClickDefaultAudioEffect(view);
            }
        });
    }

    private void updateWaveView(EqualizerAnimationWaveView equalizerAnimationWaveView) {
        equalizerAnimationWaveView.setWaveShadowColor(-66);
        equalizerAnimationWaveView.setWaveShadowRadius(10.0f);
        equalizerAnimationWaveView.setWaveColor(-1);
        equalizerAnimationWaveView.setWaveStrokeWidth(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleClickDefaultAudioEffect(View view) {
        AudioEffectStatistic.m5252t();
        SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_AJUST_RESET, SPage.PAGE_NONE, SPage.PAGE_NONE);
        if (!this.mCurrentMediaItem.isNull() && !view.isSelected()) {
            view.setSelected(true);
            restoreCurrentEffect();
        }
    }

    private void restoreCurrentEffect() {
        CommandCenter.m4607a().m4596b(new Command(CommandID.SET_AUDIO_EFFECT_RESET, new Object[0]));
        String title = this.mCurrentMediaItem.getTitle();
        String artist = this.mCurrentMediaItem.getArtist();
        TTPodUser m2954aq = Preferences.m2954aq();
        short[] sArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        String m4379a = new AudioEffectID(m2954aq == null ? 0L : m2954aq.getUserId(), title, artist, sArr, 0, 0, 0, 0, 0.0f, true).m4379a();
        AudioEffectCache audioEffectCache = new AudioEffectCache();
        audioEffectCache.m4411a(m4379a);
        audioEffectCache.m4412a(this.mCurrentMediaItem.getSongID());
        audioEffectCache.m4405b(artist);
        audioEffectCache.m4402c(title);
        audioEffectCache.m4414a(0);
        audioEffectCache.m4407b(0);
        audioEffectCache.m4399d(Build.MODEL);
        audioEffectCache.m4393f(getResources().getString(R.string.me));
        audioEffectCache.m4397e(0);
        audioEffectCache.m4394f(0);
        audioEffectCache.m4391g(0);
        audioEffectCache.m4388h(0);
        audioEffectCache.m4415a(0.0f);
        audioEffectCache.m4410a(true);
        audioEffectCache.m4409a(sArr);
        audioEffectCache.m4406b(System.currentTimeMillis());
        audioEffectCache.m4390g(this.mCurrentMediaItem.getLocalDataSource());
        LogUtils.m8388a("CloudAudioEffectFragment", "saveToLocal " + audioEffectCache);
        CommandCenter.m4607a().m4596b(new Command(CommandID.SAVE_EFFECT, this.mCurrentMediaItem, audioEffectCache, false));
    }

    public RecommandAudioEffectFragment getRecommandEffectFragment() {
        return this.mRecommandAudioEffectFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayView() {
        String title;
        String artist;
        if (this.mCurrentMediaItem.isNull()) {
            title = getString(R.string.effect_no_play);
            artist = "";
        } else {
            title = this.mCurrentMediaItem.getTitle();
            artist = this.mCurrentMediaItem.getArtist();
        }
        this.mMediaTextView.setText(title);
        this.mAauthor.setText(artist);
        if (Preferences.m2974ad()) {
            this.mAudioStopLayout.setVisibility(View.GONE);
            this.mFrameLayout.setVisibility(View.VISIBLE);
            startRecommandAudioEffectFragment();
            return;
        }
        this.mAudioStopLayout.setVisibility(View.VISIBLE);
        this.mFrameLayout.setVisibility(View.GONE);
    }

    private String getMediaText(MediaItem mediaItem, AudioEffectParam audioEffectParam) {
        String title;
        String str;
        if (mediaItem.isNull()) {
            title = getString(R.string.effect_no_play);
            str = "";
        } else {
            title = mediaItem.getTitle();
            str = "-" + mediaItem.getArtist();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.m8375a(cls, "updatePlayMediaInfo", new Class[0]));
        map.put(CommandID.UPDATE_QUERY_EFFECT_USERINFO, ReflectUtils.m8375a(cls, "updateQueryEffectUserInfo", AudioEffectUserResult.class));
        map.put(CommandID.UPDATE_AUDIO_EFFECT_INFO, ReflectUtils.m8375a(cls, "updateAudioEffectInfo", new Class[0]));
        map.put(CommandID.UPDATE_MANUAL_SETTING_EFFECT, ReflectUtils.m8375a(cls, "updateManualSettingEffect", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (this.mInit) {
            if (playStatus == PlayStatus.STATUS_PLAYING) {
                this.mHeadPlaystatusView.setBackgroundResource(R.drawable.myeffect_item_hit);
            } else {
                this.mHeadPlaystatusView.setBackgroundResource(R.drawable.myeffect_pause);
            }
        }
    }

    public void updatePlayMediaInfo() {
        this.mChangedMedia = true;
        this.mCurrentMediaItem = Cache.m3218a().m3225N();
        updatePlayView();
        updateAudioEffectInfoByRunnable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.audioeffect.CloudAudioEffectFragment$a */
    /* loaded from: classes.dex */
    public class RunnableC1393a implements Runnable {
        private RunnableC1393a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CloudAudioEffectFragment.this.updateAudioEffectInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAudioEffectInfoByRunnable() {
        if (isAdded()) {
            this.mHandler.removeCallbacks(this.mRunnable);
            this.mHandler.postDelayed(this.mRunnable, 200L);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        updateAudioEffectInfo();
        this.mOpenCloudEffectToggleButton.setChecked(Preferences.m2974ad());
    }

    private void updateAuthorText() {
        this.mAutorTextView.setText(R.string.effect_match_local);
        this.mAutorTextView.setSelected(false);
    }

    public void updateQueryEffectUserInfo(AudioEffectUserResult audioEffectUserResult) {
        this.mAudioEffectUser = audioEffectUserResult.getData();
    }

    public void updateManualSettingEffect() {
        updateAudioEffectInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecommandAudioEffectFragment() {
        if (OfflineModeUtils.m8256a()) {
            this.mAudioWifiTipLayout.setVisibility(View.VISIBLE);
        } else {
            requestAudioEffect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestAudioEffect() {
        this.mAudioWifiTipLayout.setVisibility(View.GONE);
        if (this.mRecommandAudioEffectFragment == null) {
            this.mRecommandAudioEffectFragment = new RecommandAudioEffectFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.recomand_audio_effect, this.mRecommandAudioEffectFragment).commit();
        } else if (this.mChangedMedia) {
            this.mHandler.removeCallbacks(this.mRequestRunnable);
            this.mHandler.postDelayed(this.mRequestRunnable, 1200L);
        } else {
            this.mRecommandAudioEffectFragment.notifyUpdateRecommandList();
        }
        this.mChangedMedia = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performCloudMatchChanged(boolean z) {
        PopupsUtils.m6721a(getString(z ? R.string.effect_equalizer_cloud_match_enabled : R.string.effect_equalizer_cloud_match_disabled));
        CommandCenter.m4607a().m4606a(new Command(CommandID.SET_CLOUD_AUDIO_EFFECT_ENABLED, Boolean.valueOf(z)));
        CommandCenter m4607a = CommandCenter.m4607a();
        CommandID commandID = CommandID.SET_LOCAL_AUDIO_EFFECT;
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(!z);
        m4607a.m4606a(new Command(commandID, objArr));
        updateAudioEffectInfo();
    }

    private boolean isDefaultAudioEffect(AudioEffectParam audioEffectParam) {
        short[] bandLevels = new TTEqualizer.Settings(audioEffectParam.m4421g()).getBandLevels();
        float m4426e = audioEffectParam.m4426e();
        int m4448a = audioEffectParam.m4448a();
        int m4439b = audioEffectParam.m4439b();
        int m4433c = audioEffectParam.m4433c();
        int m4429d = audioEffectParam.m4429d();
        for (int length = bandLevels.length - 1; length >= 0; length--) {
            if (bandLevels[length] != 0) {
                return false;
            }
        }
        return m4448a == 0 && m4439b == 0 && m4426e == 0.0f && m4433c == 0 && m4429d == 0;
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mAttach = true;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacks(this.mRunnable);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mAttach = false;
    }

    public void updateAudioEffectInfo() {
        Boolean bool;
        if (this.mAttach && getActivity() != null) {
            if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING) {
                this.mHeadPlaystatusView.setBackgroundResource(R.drawable.myeffect_item_hit);
            } else {
                this.mHeadPlaystatusView.setBackgroundResource(R.drawable.myeffect_pause);
            }
            boolean m2974ad = Preferences.m2974ad();
            if (this.mCurrentMediaItem.isNull()) {
                showNoMatch(false);
                return;
            }
            AudioEffectParam m2457s = SupportFactory.m2397a(BaseApplication.getApplication()).m2457s();
            if (m2457s != null) {
                Boolean.valueOf(false);
                switch (m2457s.m4420h()) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        bool = true;
                        break;
                    default:
                        bool = false;
                        break;
                }
                if (bool.booleanValue() && m2974ad && (m2457s.m4420h() != 3 || !StringUtils.m8346a(m2457s.m4419i()))) {
                    if (isDefaultAudioEffect(m2457s)) {
                        showNoMatch(m2974ad);
                        return;
                    } else {
                        showMatch();
                        return;
                    }
                }
            }
            showNoMatch(m2974ad);
        }
    }

    private void showMatch() {
        this.mAutorTextView.setSelected(true);
        this.mLayoutRoot.setSelected(true);
        this.mAutorTextView.setText(getResources().getText(R.string.effect_match_successex));
        this.mDefaultRootHit.setSelected(false);
    }

    private void showNoMatch(boolean z) {
        if (z) {
            showNoMatch();
            return;
        }
        this.mAutorTextView.setSelected(false);
        this.mLayoutRoot.setSelected(false);
        this.mAutorTextView.setText(getResources().getText(R.string.effect_match_no));
        this.mDefaultRootHit.setSelected(false);
    }

    private void showNoMatch() {
        this.mAutorTextView.setSelected(true);
        this.mLayoutRoot.setSelected(true);
        this.mAutorTextView.setText(getResources().getText(R.string.recommand_effect_default));
        this.mDefaultRootHit.setSelected(true);
    }
}
