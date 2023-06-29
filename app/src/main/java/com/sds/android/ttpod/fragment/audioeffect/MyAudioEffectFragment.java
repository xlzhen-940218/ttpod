package com.sds.android.ttpod.fragment.audioeffect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AudioEffectUserExp;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.activities.audioeffect.CloudAudioEffectActivity;
import com.sds.android.ttpod.activities.audioeffect.EffectLevelActivity;
import com.sds.android.ttpod.activities.audioeffect.SelectDeleteEffectActivity;
import com.sds.android.ttpod.activities.user.UserInfoActivity;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p084a.EffectLevelItem;
import com.sds.android.ttpod.component.p084a.EffectLevelItemHelper;
import com.sds.android.ttpod.component.p084a.EqualizerWaveViewHelper;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.PrivateEffectItem;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.audioeffect.CircularProgress;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MyAudioEffectFragment extends BaseFragment {
    private static final int DELAY_TIME = 2100;
    private static final int EFFECT_STATUS_USE_CLOUD = 3;
    private static final int EFFECT_STATUS_USE_PRIVATE = 1;
    private static final int HUNRED = 100;
    private static final int LIST_DELIVER_HEIGHT = 12;
    private static final String TAG = "MyEffectActivity";
    private ImageView mAvatar;
    private MediaItem mCurrentMediaItem;
    private View mDetailView;
    private ProgressBar mEffectLevelProgressbar;
    private ImageView mImgLevel;
    private View mLayoutEffectDetail;
    private TextView mLevel;
    private TextView mListTitle;
    private TextView mListTitleTip;
    private StateView mLoadingLayer;
    private List<MediaItem> mMediaItemList;
    private View mMyAudioEffectRootView;
    private MyEffectListFragment mMyEffectListFragment;
    private PlayStatusMonitor mPlayStatusMonitor;
    private RelativeLayout mScoreLayer;
    private MediaItem mSelectedMediaItem;
    private TextView mTotalScore;
    private TextView mUpgradeLeftScore;
    private int mUsedEffect;
    private TextView mUserName;
    private TextView mWifiTip;
    private boolean mNetTemporaryGroupSynced = false;
    private boolean mIsCloudAudioOpen = false;
    private boolean mIsItemClicked = false;
    private Handler mHandler = new Handler();
    private long mLastTime = 0;
    private boolean mIsStatisticed = false;
    private boolean mNeedUpdate = true;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.MyAudioEffectFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.layout_effect_detail) {
                MyAudioEffectFragment.this.performEffectLevelClick();
            }
        }
    };

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mMyAudioEffectRootView == null) {
            this.mMyAudioEffectRootView = layoutInflater.inflate(R.layout.activity_myeffect, viewGroup, false);
            this.mLayoutEffectDetail = this.mMyAudioEffectRootView.findViewById(R.id.layout_effect_detail);
            initMyEffectFragment();
            initMyAudioEffectViews();
            getCurrentMediaItemAndEffect();
        }
        return this.mMyAudioEffectRootView;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPostViewCreated(View view, Bundle bundle) {
        super.onPostViewCreated(view, bundle);
        setUserInfoView();
        loadMonitor();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_EFFECT_USERINFO, new Object[0]));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_QUERY_EFFECT_USERINFO, ReflectUtils.m8375a(cls, "updateQueryEffectUserInfo", AudioEffectUserResult.class));
        map.put(CommandID.UPDATE_QUERY_PRIVATE_EFFECT, ReflectUtils.m8375a(cls, "updateQueryPrivateEffect", List.class, List.class));
        map.put(CommandID.UPDATE_DELETE_PRIVATE_EFFECT_LIST, ReflectUtils.m8375a(cls, "updateDeletePrivateEffectList", new Class[0]));
        map.put(CommandID.UPDATE_SAVE_EFFECT_TO_LOCAL, ReflectUtils.m8375a(cls, "updateSaveEffectToLocal", Boolean.class));
        map.put(CommandID.UPDATE_SAVE_EFFECT_TO_NETWORK, ReflectUtils.m8375a(cls, "updateSaveEffectToNetwork", AudioEffectAddResult.class));
        map.put(CommandID.UPDATE_MANUAL_SETTING_EFFECT, ReflectUtils.m8375a(cls, "updateManualSettingEffect", new Class[0]));
        map.put(CommandID.UPDATE_AUDIO_EFFECT_INFO, ReflectUtils.m8375a(cls, "updateAudioEffectList", new Class[0]));
    }

    public void updateManualSettingEffect() {
        this.mMyEffectListFragment.setSelectItem(-1);
        getCurrentMediaItemAndEffect();
    }

    public void updateAudioEffectList() {
        updateMyEffectList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCurrentMediaItemAndEffect() {
        this.mCurrentMediaItem = Cache.getInstance().getCurrentPlayMediaItem();
        if (SupportFactory.getInstance(BaseApplication.getApplication()).m2457s() != null) {
            this.mUsedEffect = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s().m4420h();
        }
    }

    /* loaded from: classes.dex */
    public class PlayStatusMonitor extends BroadcastReceiver {
        public PlayStatusMonitor() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (Action.PLAY_STATUS_CHANGED.equals(intent.getAction())) {
                MyAudioEffectFragment.this.mIsItemClicked = false;
                MyAudioEffectFragment.this.getCurrentMediaItemAndEffect();
                MyAudioEffectFragment.this.mMyEffectListFragment.notifyDataSetChanged();
            }
        }

        /* renamed from: a */
        public IntentFilter m5739a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.PLAY_STATUS_CHANGED);
            return intentFilter;
        }
    }

    private void unLoadMonitor() {
        BaseApplication.getApplication().unregisterReceiver(this.mPlayStatusMonitor);
        this.mPlayStatusMonitor = null;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        unLoadMonitor();
    }

    private void loadMonitor() {
        LogUtils.debug(TAG, "loadMonitor");
        this.mPlayStatusMonitor = new PlayStatusMonitor();
        BaseApplication.getApplication().registerReceiver(this.mPlayStatusMonitor, this.mPlayStatusMonitor.m5739a());
    }

    public void updateSaveEffectToNetwork(AudioEffectAddResult audioEffectAddResult) {
        setUserInfoView();
    }

    public void updateSaveEffectToLocal(Boolean bool) {
        this.mNeedUpdate = bool.booleanValue();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        updateCloudAudioEffectState();
        updateMyEffectList();
    }

    private void updateCloudAudioEffectState() {
        if (Preferences.m2974ad()) {
            this.mIsCloudAudioOpen = true;
        } else {
            this.mIsCloudAudioOpen = false;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.mNeedUpdate) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_PRIVATE_EFFECT, new Object[0]));
            this.mNeedUpdate = false;
        }
    }

    public void updateMyEffectList() {
        this.mIsItemClicked = false;
        updateCloudAudioEffectState();
        getCurrentMediaItemAndEffect();
        this.mMyEffectListFragment.notifyDataSetChanged();
    }

    public void updateQueryEffectUserInfo(AudioEffectUserResult audioEffectUserResult) {
        if (audioEffectUserResult.getCode() == 1) {
            AudioEffectUserExp exp = audioEffectUserResult.getData().getExp();
            if (exp != null) {
                int total = exp.getTotal();
                EffectLevelItem m7148a = EffectLevelItemHelper.m7148a(total);
                this.mImgLevel.setImageResource(m7148a.m7164b());
                this.mLevel.setText(m7148a.m7173a());
                int m7158c = m7148a.m7158c() + 1;
                if (total < 10000) {
                    this.mUpgradeLeftScore.setText(getString(R.string.effect_user_detail_update_left_score, Integer.valueOf(m7158c - total)));
                    this.mTotalScore.setText(getString(R.string.effect_user_detail_total_score, Integer.valueOf(m7158c)));
                    this.mEffectLevelProgressbar.setProgress((total * 100) / m7158c);
                    return;
                }
                this.mUpgradeLeftScore.setText(R.string.effect_user_detail_score_max);
                this.mTotalScore.setText("");
                this.mEffectLevelProgressbar.setProgress(100);
            }
        } else if (getUserVisibleHint()) {
            PopupsUtils.m6760a((int) R.string.network_error);
        }
    }

    public void updateQueryPrivateEffect(List<PrivateEffectItem> list, List<MediaItem> list2) {
        this.mLoadingLayer.setState(StateView.EnumC2248b.SUCCESS);
        this.mMyEffectListFragment.updateData(list);
        this.mMediaItemList = list2;
        boolean z = list.size() > 0;
        CloudAudioEffectActivity cloudAudioEffectActivity = (CloudAudioEffectActivity) getActivity();
        cloudAudioEffectActivity.setHasPrivateAudioEffect(z);
        cloudAudioEffectActivity.getDeleteAction().m7155c(cloudAudioEffectActivity.isMyAudioEffectFragmentPage() ? z : false);
        this.mNetTemporaryGroupSynced = false;
        if (z) {
            this.mListTitle.setVisibility(View.VISIBLE);
            this.mListTitleTip.setVisibility(View.INVISIBLE);
            return;
        }
        this.mListTitle.setVisibility(View.INVISIBLE);
        this.mListTitleTip.setVisibility(View.VISIBLE);
    }

    public void updateDeletePrivateEffectList() {
        CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_PRIVATE_EFFECT, new Object[0]));
    }

    private void initMyAudioEffectViews() {
        this.mDetailView = this.mMyAudioEffectRootView.findViewById(R.id.layout_effect_detail);
        this.mDetailView.setOnClickListener(this.mOnClickListener);
        this.mAvatar = (ImageView) this.mMyAudioEffectRootView.findViewById(R.id.image_avatar);
        this.mUserName = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.textview_username);
        this.mLevel = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.textview_level);
        this.mImgLevel = (ImageView) this.mMyAudioEffectRootView.findViewById(R.id.imageview_level);
        this.mUpgradeLeftScore = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.textview_upgrade_left_score);
        this.mTotalScore = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.textview_score);
        this.mEffectLevelProgressbar = (ProgressBar) this.mMyAudioEffectRootView.findViewById(R.id.progressbar_effect_level);
        this.mWifiTip = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.wifi_tip);
        this.mScoreLayer = (RelativeLayout) this.mMyAudioEffectRootView.findViewById(R.id.textview_upgrade_left_score_layer);
        this.mListTitle = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.myeffect_title);
        this.mListTitleTip = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.myeffect_title_tip);
        this.mLoadingLayer = (StateView) this.mMyAudioEffectRootView.findViewById(R.id.loading_my_effect);
        this.mLoadingLayer.setState(StateView.EnumC2248b.LOADING);
    }

    public void onDeleteActionItemClick() {
        Intent intent = new Intent(getActivity(), SelectDeleteEffectActivity.class);
        SelectDeleteEffectActivity.setEffectList(this.mMyEffectListFragment.getData());
        startActivity(intent);
    }

    private boolean visitNetWifi() {
        return !OfflineModeUtils.m8256a();
    }

    private void setUserinfo(User user, String str) {
        ImageCacheUtils.m4750a(this.mAvatar, str, (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height), String.format(UserInfoActivity.LOCAL_AVATAR_IMAGE_PATH_FORMAT, Long.valueOf(user.getUserId() + (user.getAvatarUrl() == null ? 0 : user.getAvatarUrl().hashCode()))));
        this.mUserName.setText(user.getNickName());
    }

    private void setUserInfoView() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null && visitNetWifi()) {
            this.mLayoutEffectDetail.setVisibility(View.VISIBLE);
            if (getUserVisibleHint()) {
                CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_EFFECT_USERINFO, new Object[0]));
            }
            setUserinfo(m2954aq, m2954aq.getAvatarUrl());
            setControlsVisible(0, 4);
        } else if (m2954aq == null) {
            this.mLayoutEffectDetail.setVisibility(View.GONE);
        } else {
            this.mLayoutEffectDetail.setVisibility(View.VISIBLE);
            setUserinfo(m2954aq, null);
            setControlsVisible(4, 0);
        }
    }

    private void setControlsVisible(int i, int i2) {
        this.mScoreLayer.setVisibility(i);
        this.mEffectLevelProgressbar.setVisibility(i);
        this.mLevel.setVisibility(i);
        this.mImgLevel.setVisibility(i);
        this.mWifiTip.setVisibility(i2);
    }

    private void initMyEffectFragment() {
        if (this.mMyEffectListFragment == null) {
            this.mMyEffectListFragment = new MyEffectListFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_myeffect, this.mMyEffectListFragment).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performEffectLevelClick() {
        startActivity(new Intent(getActivity(), EffectLevelActivity.class));
    }

    /* loaded from: classes.dex */
    public class MyEffectListFragment extends BaseEffectListFragment {
        private RunnableC1406a mUpdateRunnable = new RunnableC1406a();
        private int mSelectItem = -1;

        public MyEffectListFragment() {
        }

        @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mListView.setBackground(getResources().getDrawable(R.color.effect_section_background));
            this.mListView.setDivider(getResources().getDrawable(R.color.effect_section_background));
            this.mListView.setDividerHeight(DisplayUtils.dp2px(12));
        }

        @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment
        protected View getEffectItem(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater(null).inflate(R.layout.myeffect_list_item_new, (ViewGroup) null);
                view.setTag(new C1407a(view));
            }
            PrivateEffectItem privateEffectItem = this.mMyEffectItemList.get(i);
            C1407a c1407a = (C1407a) view.getTag();
            String m4332a = privateEffectItem.m4332a();
            int lastIndexOf = m4332a.lastIndexOf("-");
            String str = m4332a.substring(0, lastIndexOf) + "   " + getString(R.string.effect_provide_by, privateEffectItem.m4329b());
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#80ffffff")), lastIndexOf, str.length(), 33);
            c1407a.f4975b.setText(spannableStringBuilder);
            c1407a.f4976c.setText(m4332a.substring(lastIndexOf + 1));
            EqualizerWaveViewHelper.m7147a(c1407a.f4977d, privateEffectItem.m4323h());
            if (!MyAudioEffectFragment.this.mIsItemClicked) {
                if (MyAudioEffectFragment.this.mIsCloudAudioOpen && MyAudioEffectFragment.this.mCurrentMediaItem != null && StringUtils.equals(privateEffectItem.m4325f().getID(), MyAudioEffectFragment.this.mCurrentMediaItem.getID())) {
                    this.mSelectItem = i;
                } else {
                    this.mSelectItem = -1;
                }
            }
            if (i != this.mSelectItem || SupportFactory.getInstance(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING) {
                c1407a.f4982i.setBackgroundResource(R.drawable.recommand_list_hit_xml);
            } else {
                c1407a.f4982i.setBackgroundResource(R.drawable.myeffect_pause);
            }
            c1407a.f4978e.setSelected(i == this.mSelectItem);
            c1407a.f4982i.setSelected(i == this.mSelectItem);
            c1407a.f4979f.setValue(RadialProgressWidget.m1404b(privateEffectItem.m4322i()));
            c1407a.f4980g.setValue(RadialProgressWidget.m1404b(privateEffectItem.m4321j()));
            c1407a.f4981h.setValue(RadialProgressWidget.m1404b(privateEffectItem.m4320k()));
            return view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectItem(int i) {
            this.mSelectItem = i;
            this.mAdapter.notifyDataSetChanged();
        }

        private void play(int i, boolean z) {
            if (!MyAudioEffectFragment.this.mIsStatisticed) {
                MyAudioEffectFragment.this.mIsStatisticed = true;
                //AudioEffectStatistic.m5248x();
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_MY_EFFECT_PLAY, SPage.PAGE_NONE, SPage.PAGE_AUDIO_MY_CLOUD_EFFECT);
            }
            if (!MyAudioEffectFragment.this.mNetTemporaryGroupSynced) {
                CommandCenter.getInstance().execute(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, MyAudioEffectFragment.this.mMediaItemList));
                MyAudioEffectFragment.this.mNetTemporaryGroupSynced = true;
            }
            MediaItem m4325f = this.mMyEffectItemList.get(i).m4325f();
            MyAudioEffectFragment.this.mSelectedMediaItem = m4325f;
            if (!StringUtils.equals(MediaStorage.GROUP_ID_ONLINE_TEMPORARY, Preferences.getLocalGroupId()) || !StringUtils.equals(m4325f.getID(), Preferences.getMediaId())) {
                MyAudioEffectFragment.this.mHandler.postDelayed(this.mUpdateRunnable, z ? 2100L : 0L);
                return;
            }
            PlayStatus m2463m = SupportFactory.getInstance(BaseApplication.getApplication()).m2463m();
            if (m2463m == PlayStatus.STATUS_PAUSED) {
                CommandCenter.getInstance().execute(new Command(CommandID.RESUME, new Object[0]));
            } else if (m2463m == PlayStatus.STATUS_PLAYING) {
                CommandCenter.getInstance().execute(new Command(CommandID.PAUSE, new Object[0]));
            } else {
                CommandCenter.getInstance().execute(new Command(CommandID.START, new Object[0]));
            }
        }

        @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment, android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            super.onItemClick(adapterView, view, i, j);
            MyAudioEffectFragment.this.mIsItemClicked = true;
            MyAudioEffectFragment.this.mHandler.removeCallbacks(this.mUpdateRunnable);
            long currentTimeMillis = System.currentTimeMillis();
            setSelectItem(i);
            play(i, currentTimeMillis - MyAudioEffectFragment.this.mLastTime < 2100);
            MyAudioEffectFragment.this.mLastTime = currentTimeMillis;
        }

        /* renamed from: com.sds.android.ttpod.fragment.audioeffect.MyAudioEffectFragment$MyEffectListFragment$a */
        /* loaded from: classes.dex */
        public class RunnableC1406a implements Runnable {
            public RunnableC1406a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4596b(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, MyAudioEffectFragment.this.mSelectedMediaItem));
            }
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.audioeffect.MyAudioEffectFragment$a */
    /* loaded from: classes.dex */
    private class C1407a {

        /* renamed from: b */
        private TextView f4975b;

        /* renamed from: c */
        private TextView f4976c;

        /* renamed from: d */
        private EqualizerAnimationWaveView f4977d;

        /* renamed from: e */
        private RelativeLayout f4978e;

        /* renamed from: f */
        private CircularProgress f4979f;

        /* renamed from: g */
        private CircularProgress f4980g;

        /* renamed from: h */
        private CircularProgress f4981h;

        /* renamed from: i */
        private ImageView f4982i;

        public C1407a(View view) {
            this.f4975b = (TextView) view.findViewById(R.id.textview_title);
            this.f4976c = (TextView) view.findViewById(R.id.textview_title_2);
            this.f4977d = (EqualizerAnimationWaveView) view.findViewById(R.id.waveview_effect_equalizer_equ);
            this.f4978e = (RelativeLayout) view.findViewById(R.id.layout_root);
            this.f4979f = (CircularProgress) view.findViewById(R.id.rotatebutton_bass_boost);
            this.f4979f.setPaintText(VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY);
            this.f4980g = (CircularProgress) view.findViewById(R.id.rotatebutton_treble_boost);
            this.f4980g.setPaintText("T");
            this.f4981h = (CircularProgress) view.findViewById(R.id.rotatebutton_surround);
            this.f4981h.setPaintText("S");
            this.f4982i = (ImageView) view.findViewById(R.id.image_play);
        }
    }
}
