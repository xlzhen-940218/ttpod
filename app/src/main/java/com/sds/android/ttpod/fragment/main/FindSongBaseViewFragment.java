package com.sds.android.ttpod.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FindSongHotListData;
import com.sds.android.cloudapi.ttpod.data.ForwardAction;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.cloudapi.ttpod.result.StyleDataListResult;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.PostDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.user.LoginActivity;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.WebSlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.findsong.BestAlbumFromNewSongFragment;
import com.sds.android.ttpod.fragment.main.findsong.DailyRecommendFragment;
import com.sds.android.ttpod.fragment.main.findsong.DiscoveryFragment;
import com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment;
import com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment;
import com.sds.android.ttpod.fragment.main.findsong.PrivateCustomFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubPopularSongFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubSongCategoryDetailFragment;
import com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.FindSongConfig;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class FindSongBaseViewFragment extends BaseFragment {
    private static final int FORWARD_BEST_ALBUM = 10;
    private static final int FORWARD_CATEGORY_DETAIL = 4;
    private static final int FORWARD_DAILY_RECOMMEND = 13;
    private static final int FORWARD_DISCOVERY = 8;
    private static final int FORWARD_MUSIC_CIRCLE = 7;
    private static final int FORWARD_NEW_ALBUM = 11;
    @Deprecated
    private static final int FORWARD_NEW_SONG_PUBLISH = 9;
    private static final int FORWARD_NONE = 0;
    private static final int FORWARD_POPULAR_SONG = 6;
    private static final int FORWARD_POST_DETAIL = 5;
    private static final int FORWARD_PRIVATE_CUSTOM_PAGE = 12;
    @Deprecated
    private static final int FORWARD_SONG_LIST_SQUARE = 2;
    private static final int FORWARD_UNICOM_OPEN = 14;
    private static final int FORWARD_USER_HOME = 3;
    private static final int FORWARD_WEB = 1;
    private static final int POSITION_FORWARD_MORE = -1;
    private static final int TYPE_SONG_LIST = 5;
    protected View mTitleBarView;
    private int mUserType;
    private static final String TAG = FindSongBaseViewFragment.class.getSimpleName();
    private static final HashSet<Integer> TYPES_NEED_VALUE = new HashSet<>();
    private static final SparseIntArray TYPE_STO = new SparseIntArray();
    private static final SparseArray<Method> METHODS = new SparseArray<>();
    protected boolean mThemeReload = true;
    private SparseArray<Long> mMapPositionToId = new SparseArray<>();
    private SparseArray<Post> mMapPositionToPreLoadSongList = new SparseArray<>();

    static {
        TYPES_NEED_VALUE.add(1);
        TYPES_NEED_VALUE.add(3);
        TYPES_NEED_VALUE.add(4);
        TYPES_NEED_VALUE.add(5);
        TYPE_STO.put(1, SPage.PAGE_ONLINE_POST_DETAIL_WEB.getValue());
        TYPE_STO.put(3, SPage.PAGE_ONLINE_MUSIC_CIRCLE_USER_HOME.getValue());
        TYPE_STO.put(4, SPage.PAGE_ONLINE_SONG_CATEGORY.getValue());
        TYPE_STO.put(5, SPage.PAGE_ONLINE_POST_DETAIL.getValue());
        TYPE_STO.put(6, SPage.PAGE_ONLINE_POPULAR_SONG.getValue());
        TYPE_STO.put(7, SPage.PAGE_ONLINE_MUSIC_CIRCLE_MY_HOME.getValue());
        TYPE_STO.put(8, SPage.PAGE_ONLINE_DISCOVERY.getValue());
        TYPE_STO.put(10, SPage.PAGE_ONLINE_BEST_ALBUM_FROM_NEW_SONG.getValue());
        TYPE_STO.put(11, SPage.PAGE_ONLINE_NEW_ALBUM.getValue());
        TYPE_STO.put(12, SPage.PAGE_ONLINE_PERSONALIZED_RECOMMEND.getValue());
        TYPE_STO.put(13, SPage.PAGE_ONLINE_DAILY_RECOMMEND.getValue());
        TYPE_STO.put(14, SPage.PAGE_UNICOM_SUBSCRIBE.getValue());
        try {
            METHODS.put(0, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardAbnormal", Integer.class));
            METHODS.put(1, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardWeb", Integer.class));
            METHODS.put(3, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardMusicCircleUserHome", Integer.class));
            METHODS.put(4, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardCategoryDetail", Integer.class));
            METHODS.put(5, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardPostDetail", Integer.class));
            METHODS.put(6, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardPopularSong", Integer.class));
            METHODS.put(7, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardMusicCircle", Integer.class));
            METHODS.put(8, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardDiscovery", Integer.class));
            METHODS.put(10, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardBestAlbum", Integer.class));
            METHODS.put(11, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardNewAlbum", Integer.class));
            METHODS.put(12, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardPrivateCustom", Integer.class));
            METHODS.put(13, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardDailyRecommend", Integer.class));
            METHODS.put(14, ReflectUtils.m8375a(FindSongBaseViewFragment.class, "forwardUnicomOpen", Integer.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        preLoadSongListDataInWIFI();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_POST_INFO_LIST_BY_ID, ReflectUtils.m8375a(getClass(), "updatePreLoadSongList", PostResult.class, String.class));
    }

    private void preLoadSongListDataInWIFI() {
        if (EnvironmentUtils.DeviceConfig.hasNetwork() == 2) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.REQUEST_POST_INFOS_BY_ID, getNeedPreLoadSongListIds(), getRequestIdForCommandParallel()));
        }
    }

    private ArrayList<Long> getNeedPreLoadSongListIds() {
        ArrayList<Long> arrayList = new ArrayList<>();
        int size = getModuleData().getDataList().size();
        for (int i = 0; i < size; i++) {
            Long songListIdInPositionSafely = getSongListIdInPositionSafely(i);
            if (getForwardType(i) == 5 && songListIdInPositionSafely.longValue() != 0) {
                arrayList.add(songListIdInPositionSafely);
                this.mMapPositionToId.put(i, songListIdInPositionSafely);
            }
        }
        return arrayList;
    }

    private Long getSongListIdInPositionSafely(int i) {
        try {
            return Long.valueOf(getForwardValue(i));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public void updatePreLoadSongList(PostResult postResult, String str) {
        if (StringUtils.equals(str, getRequestIdForCommandParallel()) && !ListUtils.m4718a(postResult.getDataList())) {
            Iterator<Post> it = postResult.getDataList().iterator();
            while (it.hasNext()) {
                Post next = it.next();
                Long valueOf = Long.valueOf(next.getId());
                int size = this.mMapPositionToId.size();
                for (int i = 0; i < size; i++) {
                    if (this.mMapPositionToId.valueAt(i).equals(valueOf)) {
                        this.mMapPositionToPreLoadSongList.put(this.mMapPositionToId.keyAt(i), next);
                    }
                }
            }
        }
    }

    private String getRequestIdForCommandParallel() {
        return String.valueOf(getModuleId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forward(Integer num) {
       // FindSongNewStatistic.m5230a(348, getModuleId());
        if (TYPES_NEED_VALUE.contains(Integer.valueOf(getForwardType(num.intValue()))) && StringUtils.isEmpty(getForwardValue(num.intValue()))) {
            showSorry();
            return;
        }
        try {
            Method method = METHODS.get(getForwardType(num.intValue()));
            if (method != null) {
                method.invoke(this, num);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        forwardAbnormal(num);
    }

    private void forwardAbnormal(Integer num) {
        LogUtils.error(TAG, "forwardAbnormal");
        showSorry();
    }

    private void forwardMusicCircleUserHome(Integer num) {
        try {
            Long valueOf = Long.valueOf(Long.parseLong(getForwardValue(num.intValue())));
            if (Preferences.m2954aq() != null) {
                launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUserId(valueOf.longValue(), ""));
            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        } catch (NumberFormatException e) {
            showSorry();
            e.printStackTrace();
        }
    }

    private void forwardCategoryDetail(Integer num) {
        launchFragment(new SubSongCategoryDetailFragment(getForwardValue(num.intValue())));
    }

    private void forwardDailyRecommend(Integer num) {
        launchFragment(new DailyRecommendFragment(getItemData(num.intValue()).getId()));
    }

    private void forwardPostDetail(Integer num) {
        PostDetailFragment.Recomment personalizedRecommendationInfo = getPersonalizedRecommendationInfo(getItemData(num.intValue()));
        Post post = this.mMapPositionToPreLoadSongList.get(num.intValue());
        if (post != null) {
            SubPostDetailFragment createByPost = SubPostDetailFragment.createByPost(post, getModuleName());
            createByPost.setPersonalizedRecommendInfo(personalizedRecommendationInfo);
            launchFragment(createByPost);
            return;
        }
        try {
            SubPostDetailFragment createById = SubPostDetailFragment.createById(Long.valueOf(getForwardValue(num.intValue())).longValue(), getModuleName());
            createById.setPersonalizedRecommendInfo(personalizedRecommendationInfo);
            launchFragment(createById);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showSorry();
        }
    }

    private PostDetailFragment.Recomment getPersonalizedRecommendationInfo(RecommendData recommendData) {
        int recommentType;
        int recommentAlgorithm = 0;
        if (recommendData instanceof FindSongHotListData) {
            recommentType = ((FindSongHotListData) recommendData).getRecommentType();
            recommentAlgorithm = ((FindSongHotListData) recommendData).getRecommentAlgorithm();
        } else {
            recommentType = 0;
        }
        return new PostDetailFragment.Recomment(this.mUserType, recommentType, recommentAlgorithm);
    }

    private void forwardMusicCircle(Integer num) {
        if (Preferences.m2954aq() == null) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else if (Preferences.m2998aI()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("new_flag", false);
            bundle.putSerializable("user", Preferences.m2954aq());
            MusicCircleEntryFragment musicCircleEntryFragment = new MusicCircleEntryFragment();
            musicCircleEntryFragment.setArguments(bundle);
            launchFragment(musicCircleEntryFragment);
        } else {
            launchFragment(new IPUnSupportedFragment());
        }
    }

    private void forwardWeb(Integer num) {
        launchFragment(WebSlidingClosableFragment.instantiate(getForwardValue(num.intValue()), getItemName(num.intValue()), getItemPicUrl(num.intValue()), true, false));
    }

    private void forwardNewSongPublish(Integer num) {
        launchFragment(new NewSongPublishFragment());
    }

    private void forwardUnicomOpen(Integer num) {
        EntryUtils.m8293c((Activity) getActivity());
    }

    private void forwardPopularSong(Integer num) {
        launchFragment(new SubPopularSongFragment(getItemData(num.intValue()).getId()));
    }

    private void forwardDiscovery(Integer num) {
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", getItemName(num.intValue()));
        discoveryFragment.setArguments(bundle);
        launchFragment(discoveryFragment);
    }

    private void forwardBestAlbum(Integer num) {
        launchFragment(new BestAlbumFromNewSongFragment());
    }

    private void forwardNewAlbum(Integer num) {
        launchFragment(new NewAlbumFragment());
    }

    private void forwardPrivateCustom(Integer num) {
        String moduleName = StringUtils.isEmpty(getItemName(num.intValue())) ? getModuleName() : getItemName(num.intValue());
        PrivateCustomFragment privateCustomFragment = new PrivateCustomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", moduleName);
        privateCustomFragment.setArguments(bundle);
        launchFragment(privateCustomFragment);
    }

    private String getModuleName() {
        return getModuleData().getName();
    }

    private void showSorry() {
        PopupsUtils.m6760a((int) R.string.sorry);
    }

    public void setUserType(int i) {
        this.mUserType = i;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onThemeChanged() {
        this.mThemeReload = true;
        super.onThemeChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTitleBarTheme() {
        ThemeManager.m3269a(this.mTitleBarView.findViewById(R.id.id_text_title), ThemeElement.TILE_TEXT);
        ThemeManager.m3269a(this.mTitleBarView.findViewById(R.id.id_text_more), ThemeElement.TILE_SUB_TEXT);
        ThemeManager.m3269a(this.mTitleBarView.findViewById(R.id.id_title_bar_layout), ThemeElement.TILE_BACKGROUND);
        ThemeManager.m3269a(this.mTitleBarView.findViewById(R.id.id_title_bar_left_line), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        ThemeUtils.m8173a((IconTextView) this.mTitleBarView.findViewById(R.id.img_arrow_more), ThemeElement.TILE_SUB_TEXT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View createTitleBarView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.find_song_title_bar, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.id_text_title)).setText(getModuleName());
        View findViewById = inflate.findViewById(R.id.layout_for_more);
        findViewById.setVisibility(getModuleData().getForwardAction().getType() <= 0 ? View.GONE : View.VISIBLE);
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FindSongBaseViewFragment.this.doFindSongStatistic(-1, SAction.ACTION_CLICK_ONLINE_FIND_SONG_MODULE_MORE);
               // FindSongNewStatistic.m5230a(322, FindSongBaseViewFragment.this.getModuleData().getId());
                FindSongBaseViewFragment.this.forward(-1);
            }
        });
        this.mTitleBarView = inflate;
        setTitleBarTheme();
        return this.mTitleBarView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View.OnClickListener createItemOnClickListener(final int i) {
        return new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FindSongBaseViewFragment.this.doFindSongStatistic(i, SAction.ACTION_CLICK_ONLINE_FIND_SONG_ITEM);
               // FindSongNewStatistic.m5230a(323, FindSongBaseViewFragment.this.getItemData(i).getId());
                FindSongBaseViewFragment.this.forward(Integer.valueOf(i));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getModuleDataType() {
        return FindSongConfig.C0629d.m8271a(getModuleData());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StyleDataListResult getModuleData() {
        return (StyleDataListResult) getArguments().getParcelableArrayList("result").get(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RecommendData getItemData(int i) {
        if (i == -1) {
            return new C1449a();
        }
        RecommendData recommendData = (RecommendData) getModuleData().getDataList().get(i);
        return recommendData == null ? new C1449a() : recommendData;
    }

    private long getModuleId() {
        return getModuleData().getId();
    }

    private String getItemName(int i) {
        String name = getItemData(i).getName();
        return name == null ? "" : name;
    }

    private String getItemPicUrl(int i) {
        return getItemData(i).getPicUrl();
    }

    private ForwardAction getForwardAction(int i) {
        return i == -1 ? getModuleData().getForwardAction() : getItemData(i).getForwardAction();
    }

    private int getForwardType(int i) {
        return getForwardAction(i).getType();
    }

    private String getForwardValue(int i) {
        return getForwardAction(i).getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSongListItemInSongListModule(int i) {
        return isInSongListModule() && getItemData(i).getForwardAction().getType() == 5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isInSongListModule() {
        return getModuleDataType().equals("song_list");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doFindSongStatistic(int i, SAction sAction) {
        //new SUserEvent("PAGE_CLICK", sAction.getValue(), SPage.PAGE_ONLINE_FIND_SONG.getValue(), TYPE_STO.get(getForwardType(i))).append("forward_type", Integer.valueOf(getForwardType(i))).append("forward_value", getForwardValue(i)).append("module_id", Long.valueOf(getModuleId())).append("module_name", getModuleName()).append("item_id", Long.valueOf(getItemData(i).getId())).append("item_name", getItemName(i)).append("position", Integer.valueOf(i + 1)).post();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment$a */
    /* loaded from: classes.dex */
    public class C1449a extends RecommendData {
        private C1449a() {
        }

        @Override // com.sds.android.cloudapi.ttpod.data.RecommendData
        public long getId() {
            return 0L;
        }

        @Override // com.sds.android.cloudapi.ttpod.data.RecommendData
        public int getTag() {
            return -1;
        }

        @Override // com.sds.android.cloudapi.ttpod.data.RecommendData
        public String getName() {
            return "";
        }

        @Override // com.sds.android.cloudapi.ttpod.data.RecommendData
        public String getPicUrl() {
            return "";
        }

        @Override // com.sds.android.cloudapi.ttpod.data.RecommendData
        public String getDesc() {
            return "";
        }

        @Override // com.sds.android.cloudapi.ttpod.data.RecommendData
        public ForwardAction getForwardAction() {
            return new ForwardAction();
        }
    }
}
