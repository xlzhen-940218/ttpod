package com.sds.android.ttpod.fragment.audioeffect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectCommResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.activities.user.LoginActivity;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p084a.EffectLevelItemHelper;
import com.sds.android.ttpod.component.p084a.EqualizerWaveViewHelper;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.RecommendsEffectHelpDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectCache;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.utils.EffectPickUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.audioeffect.CircularProgress;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class RecommandAudioEffectFragment extends BaseFragment {
    private static final int DEFAULT_PAGE = 1;
    private static final int DELAY_UPDATE_LIST_TIME = 800;
    private static final int EFFECT_PICK_REPEAT = 20001;
    private static final int EFFECT_STATUS_USE_LOCAL = 4;
    private static final int EFFECT_STATUS_USE_UNINIT = 0;
    private static final int PAGE_SIZE = 20;
    private static final int START_PAGE = 1;
    private BaseAdapter mAdapter;
    private List<AudioEffectItem> mDatas;
    private DataListFooterView mFooterView;
    private ListView mListView;
    private View mRecommandAudioEffectRootView;
    private StateView mStateView;
    private Pager mPager = new Pager();
    private boolean mIsLoading = true;
    private long mContinuesTime = 0;
    private boolean mIsItemClicked = false;
    private HashMap<String, Boolean> mPickedHashMap = new HashMap<>();
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.RecommandAudioEffectFragment.1
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (ListViewUtils.m8262b(i, i2, i3) && !RecommandAudioEffectFragment.this.mIsLoading) {
                if (RecommandAudioEffectFragment.this.mPager.m4669a() >= RecommandAudioEffectFragment.this.mPager.m4658g()) {
                    RecommandAudioEffectFragment.this.mFooterView.m1873c();
                    return;
                }
                RecommandAudioEffectFragment.this.mIsLoading = true;
                RecommandAudioEffectFragment.this.mFooterView.m1877a();
                RecommandAudioEffectFragment.this.requestRecommendsEffect(Integer.valueOf(RecommandAudioEffectFragment.this.mPager.m4662d()));
                RecommandAudioEffectFragment.this.mPager.m4663c(RecommandAudioEffectFragment.this.mPager.m4662d());
            }
        }
    };
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.RecommandAudioEffectFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.layout_play /* 2131231359 */:
                    RecommandAudioEffectFragment.this.performPickClick(((Integer) view.getTag()).intValue());
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.RecommandAudioEffectFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecommandAudioEffectFragment.this.hit(((Integer) view.getTag()).intValue());
        }
    };

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRecommandAudioEffectRootView == null) {
            this.mRecommandAudioEffectRootView = layoutInflater.inflate(R.layout.activity_others_effect, viewGroup, false);
            initRecommandAudioEffectViews();
        }
        return this.mRecommandAudioEffectRootView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_QUERY_EFFECT, ReflectUtils.m8375a(cls, "updateQueryEffect", AudioEffectItemResult.class));
        map.put(CommandID.UPDATE_PICK_EFFECT, ReflectUtils.m8375a(cls, "updatePickEffect", AudioEffectCommResult.class, String.class));
        map.put(CommandID.UPDATE_SAVE_EFFECT_TO_NETWORK, ReflectUtils.m8375a(cls, "updateSaveEffectToNetwork", AudioEffectAddResult.class));
        map.put(CommandID.UPDATE_MANUAL_SETTING_EFFECT, ReflectUtils.m8375a(cls, "updateManualSettingEffect", new Class[0]));
        map.put(CommandID.UPDATE_AUDIO_EFFECT_INFO, ReflectUtils.m8375a(cls, "updateAudioEffectList", new Class[0]));
    }

    public void updateAudioEffectList() {
        notifyUpdateRecommandList();
    }

    public void updateManualSettingEffect() {
        ((C1411a) this.mAdapter).m5730a(-1);
        this.mAdapter.notifyDataSetChanged();
    }

    public void notifyUpdateRecommandList() {
        this.mIsItemClicked = false;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPostViewCreated(View view, Bundle bundle) {
        super.onPostViewCreated(view, bundle);
        initData();
    }

    public void updateRecommandList() {
        if (this.mContinuesTime == 0) {
            this.mContinuesTime = System.currentTimeMillis();
            do {
            } while (System.currentTimeMillis() - this.mContinuesTime < 800);
            this.mContinuesTime = 0L;
            initData();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void updateSaveEffectToNetwork(AudioEffectAddResult audioEffectAddResult) {
        if (audioEffectAddResult.getCode() == 1) {
            initData();
        }
    }

    public void updateQueryEffect(AudioEffectItemResult audioEffectItemResult) {
        this.mIsItemClicked = false;
        if (audioEffectItemResult.getCode() == 1) {
            int allPage = audioEffectItemResult.getAllPage();
            List<AudioEffectItem> effectList = audioEffectItemResult.getEffectList();
            if (effectList.size() == 0) {
                this.mStateView.setState(StateView.EnumC2248b.NO_DATA);
                this.mListView.setVisibility(View.INVISIBLE);
            } else {
                this.mListView.setVisibility(View.VISIBLE);
                this.mPager.m4665b(allPage);
                if (this.mPager.m4669a() > 1) {
                    this.mDatas.addAll(effectList);
                    this.mFooterView.m1873c();
                } else {
                    this.mPickedHashMap.clear();
                    this.mDatas = effectList;
                }
                this.mAdapter.notifyDataSetChanged();
                this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
            }
        } else {
            PopupsUtils.m6721a(getString(R.string.string_effect_net_error));
            if (this.mDatas == null || this.mDatas.size() == 0) {
                this.mStateView.setState(StateView.EnumC2248b.FAILED);
            }
        }
        this.mIsLoading = false;
    }

    public void updatePickEffect(AudioEffectCommResult audioEffectCommResult, String str) {
        if (audioEffectCommResult.getCode() == 1) {
            EffectPickUtils.m8305a();
            PopupsUtils.m6721a(getString(R.string.string_effect_Liked));
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.mDatas.size()) {
                    break;
                }
                AudioEffectItem audioEffectItem = this.mDatas.get(i2);
                if (audioEffectItem.getID() == null || !audioEffectItem.getID().equals(str)) {
                    i = i2 + 1;
                } else {
                    audioEffectItem.setPickCount(audioEffectItem.getPickCount() + 1);
                    break;
                }
            }
            this.mAdapter.notifyDataSetChanged();
        } else if (audioEffectCommResult.getCode() == 20001) {
            PopupsUtils.m6721a("已经点过赞了.");
        } else {
            PopupsUtils.m6721a(getString(R.string.string_effect_unliked));
        }
    }

    private void initData() {
        this.mIsItemClicked = false;
        if (this.mDatas != null) {
            this.mDatas.clear();
            this.mListView.setAdapter((ListAdapter) this.mAdapter);
            this.mPager.m4663c(1);
            this.mIsLoading = true;
            ((C1411a) this.mAdapter).m5730a(-1);
            this.mAdapter.notifyDataSetChanged();
        }
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        requestRecommendsEffect(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestRecommendsEffect(Integer num) {
        requestRecommendsEffect(num, 20);
    }

    private void requestRecommendsEffect(Integer num, Integer num2) {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull()) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_EFFECT, m3225N, num, num2));
        } else {
            this.mStateView.setState(StateView.EnumC2248b.NO_DATA);
        }
    }

    private void performActionItemHelpClick() {
        new RecommendsEffectHelpDialog(getActivity()).show();
    }

    private void initRecommandAudioEffectViews() {
        this.mStateView = (StateView) this.mRecommandAudioEffectRootView.findViewById(R.id.stateview_recommends_effect);
        this.mListView = (ListView) this.mRecommandAudioEffectRootView.findViewById(R.id.listview_others_effect);
        this.mListView.setFooterDividersEnabled(false);
        this.mListView.setBackgroundColor(Color.parseColor("#1d1f1f"));
        this.mFooterView = new DataListFooterView(getActivity());
        this.mFooterView.setBackgroundResource(R.color.effect_dialog_background);
        this.mFooterView.setTextColor(Color.parseColor("#4f5051"));
        this.mListView.addFooterView(this.mFooterView);
        this.mAdapter = new C1411a();
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hit(int i) {
        int headerViewsCount = i + this.mListView.getHeaderViewsCount();
        this.mIsItemClicked = true;
        ((C1411a) this.mAdapter).m5730a(i);
        this.mAdapter.notifyDataSetChanged();
        performPlayClick(headerViewsCount, true);
        performSaveClick(i);
        CommandCenter.getInstance().m4596b(new Command(CommandID.SET_LOCAL_AUDIO_EFFECT, false));
        //AudioEffectStatistic.m5269c();
        //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_APPLY, SPage.PAGE_AUDIO_CLOUD_EFFECT, SPage.PAGE_NONE);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.audioeffect.RecommandAudioEffectFragment$a */
    /* loaded from: classes.dex */
    public class C1411a extends BaseAdapter {

        /* renamed from: b */
        private int f4987b;

        private C1411a() {
            this.f4987b = -1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (RecommandAudioEffectFragment.this.mDatas == null) {
                return 0;
            }
            return RecommandAudioEffectFragment.this.mDatas.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(RecommandAudioEffectFragment.this.getActivity(), R.layout.effect_recommend_item_new, null);
                view.setTag(new C1412b(view));
            }
            C1412b c1412b = (C1412b) view.getTag();
            AudioEffectItem audioEffectItem = (AudioEffectItem) RecommandAudioEffectFragment.this.mDatas.get(i);
            ImageCacheUtils.m4752a(c1412b.f4989b, audioEffectItem.getPic(), DisplayUtils.dp2px(40), DisplayUtils.dp2px(40), (int) R.drawable.img_avatar_default);
            c1412b.f4990c.setText(audioEffectItem.getNickName());
            c1412b.f4991d.setImageResource(EffectLevelItemHelper.m7148a(audioEffectItem.getTotal()).m7164b());
            c1412b.f4992e.setText(audioEffectItem.getPickCount() + "");
            if (!RecommandAudioEffectFragment.this.mIsItemClicked) {
                AudioEffectParam m2457s = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s();
                if (m2457s != null && StringUtils.equals(audioEffectItem.getID(), m2457s.m4419i()) && m2457s.m4420h() != 0 && m2457s.m4420h() != 4) {
                    this.f4987b = i;
                } else {
                    this.f4987b = -1;
                }
            }
            c1412b.f4995h.setValue(RadialProgressWidget.m1404b(audioEffectItem.getDataBass()));
            c1412b.f4996i.setValue(RadialProgressWidget.m1404b(audioEffectItem.getDataTreble()));
            c1412b.f4997j.setValue(RadialProgressWidget.m1404b(audioEffectItem.getDataVirtualizer()));
            c1412b.f4993f.setOnClickListener(RecommandAudioEffectFragment.this.mOnClickListener);
            c1412b.f4993f.setTag(Integer.valueOf(i));
            EqualizerWaveViewHelper.m7147a(c1412b.f4994g, audioEffectItem.getDataEqualizer());
            c1412b.f4998k.setSelected(i == this.f4987b);
            c1412b.f4999l.setTag(Integer.valueOf(i));
            c1412b.f5000m.setTag(Integer.valueOf(i));
            return view;
        }

        /* renamed from: a */
        public void m5730a(int i) {
            this.f4987b = i;
        }
    }

    private void performPlayClick(int i, boolean z) {
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (m8266a != -1) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.SET_CLOUD_AUDIO_EFFECT, this.mDatas.get(m8266a), Boolean.valueOf(z)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performPickClick(int i) {
        if (Preferences.m2954aq() == null) {
            PopupsUtils.m6721a(getString(R.string.string_effect_unlogin));
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        }
        AudioEffectItem audioEffectItem = this.mDatas.get(i);
        if (Boolean.TRUE.equals(this.mPickedHashMap.get(audioEffectItem.getID()))) {
            PopupsUtils.m6721a(getString(R.string.string_effect_already_liked));
        } else {
            EffectPickUtils.m8304a(audioEffectItem.getID());
            this.mPickedHashMap.put(audioEffectItem.getID(), true);
        }
        //AudioEffectStatistic.m5270b();
        //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_LIKE, SPage.PAGE_AUDIO_CLOUD_EFFECT, SPage.PAGE_NONE);
    }

    private Boolean isPickedEffect(String str) {
        return (Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_PICKED_EFFECT, str), Boolean.class);
    }

    private void performSaveClick(int i) {
        AudioEffectItem audioEffectItem = this.mDatas.get(i);
        saveCloudAudioEffect(audioEffectItem);
        CommandCenter.getInstance().m4596b(new Command(CommandID.BIND_EFFECT, audioEffectItem.getID()));
        if (!Preferences.m2974ad()) {
            Preferences.m3079A(true);
        }
        PopupsUtils.m6721a(getString(R.string.cloud_effect_use_sucess));
    }

    private void saveCloudAudioEffect(AudioEffectItem audioEffectItem) {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        String title = m3225N.getTitle();
        String artist = m3225N.getArtist();
        Long songID = m3225N.getSongID();
        AudioEffectCache audioEffectCache = new AudioEffectCache();
        audioEffectCache.m4411a(audioEffectItem.getID());
        audioEffectCache.m4412a(songID);
        audioEffectCache.m4405b(artist);
        audioEffectCache.m4402c(title);
        audioEffectCache.m4414a(audioEffectItem.getStyle());
        audioEffectCache.m4407b(audioEffectItem.getOutput());
        audioEffectCache.m4399d(audioEffectItem.getDevice());
        audioEffectCache.m4403c(audioEffectItem.getTotal());
        audioEffectCache.m4400d(audioEffectItem.getPickCount());
        audioEffectCache.m4393f(audioEffectItem.getNickName());
        audioEffectCache.m4396e(audioEffectItem.getPic());
        audioEffectCache.m4413a(audioEffectItem.getUserId());
        audioEffectCache.m4397e(audioEffectItem.getDataBass());
        audioEffectCache.m4394f(audioEffectItem.getDataTreble());
        audioEffectCache.m4391g(audioEffectItem.getDataVirtualizer());
        audioEffectCache.m4388h(audioEffectItem.getDataReverb());
        audioEffectCache.m4415a(audioEffectItem.getDataBalance());
        audioEffectCache.m4410a(audioEffectItem.getDataIsLimit());
        audioEffectCache.m4409a(audioEffectItem.getDataEqualizer());
        audioEffectCache.m4406b(System.currentTimeMillis());
        if (!StringUtils.isEmpty(m3225N.getLocalDataSource())) {
            audioEffectCache.m4390g(m3225N.getLocalDataSource());
        }
        CommandCenter.getInstance().m4596b(new Command(CommandID.SAVE_EFFECT_TO_LOCAL, m3225N, audioEffectCache));
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    /* renamed from: com.sds.android.ttpod.fragment.audioeffect.RecommandAudioEffectFragment$b */
    /* loaded from: classes.dex */
    private class C1412b {

        /* renamed from: b */
        private ImageView f4989b;

        /* renamed from: c */
        private TextView f4990c;

        /* renamed from: d */
        private ImageView f4991d;

        /* renamed from: e */
        private TextView f4992e;

        /* renamed from: f */
        private View f4993f;

        /* renamed from: g */
        private EqualizerAnimationWaveView f4994g;

        /* renamed from: h */
        private CircularProgress f4995h;

        /* renamed from: i */
        private CircularProgress f4996i;

        /* renamed from: j */
        private CircularProgress f4997j;

        /* renamed from: k */
        private RelativeLayout f4998k;

        /* renamed from: l */
        private RelativeLayout f4999l;

        /* renamed from: m */
        private RelativeLayout f5000m;

        public C1412b(View view) {
            this.f4989b = (ImageView) view.findViewById(R.id.imageview_avatar);
            this.f4990c = (TextView) view.findViewById(R.id.textview_nickname);
            this.f4991d = (ImageView) view.findViewById(R.id.imageview_level);
            this.f4992e = (TextView) view.findViewById(R.id.textview_pickcount);
            this.f4993f = view.findViewById(R.id.layout_play);
            this.f4994g = (EqualizerAnimationWaveView) view.findViewById(R.id.waveview_effect_equalizer_equ);
            this.f4995h = (CircularProgress) view.findViewById(R.id.rotatebutton_bass_boost);
            this.f4995h.setPaintText(VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY);
            this.f4996i = (CircularProgress) view.findViewById(R.id.rotatebutton_treble_boost);
            this.f4996i.setPaintText("T");
            this.f4997j = (CircularProgress) view.findViewById(R.id.rotatebutton_surround);
            this.f4997j.setPaintText("S");
            this.f4999l = (RelativeLayout) view.findViewById(R.id.recommand_left);
            this.f5000m = (RelativeLayout) view.findViewById(R.id.recommand_middle);
            this.f4999l.setOnClickListener(RecommandAudioEffectFragment.this.mListener);
            this.f5000m.setOnClickListener(RecommandAudioEffectFragment.this.mListener);
            this.f4998k = (RelativeLayout) view.findViewById(R.id.root_hit);
        }
    }
}
