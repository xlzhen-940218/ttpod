package com.sds.android.ttpod.fragment.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult;


import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.cmmusic.ListenContentActivity;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter;
import com.sds.android.ttpod.cmmusic.p081e.CMMusicUtils;
import com.sds.android.ttpod.fragment.OnPageSelectedListener;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.fragment.WebSlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.findsong.OnlineMVFragment;
import com.sds.android.ttpod.fragment.main.findsong.RadioCategoryFragment;
import com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment;
import com.sds.android.ttpod.fragment.main.findsong.SingerCategoryFragment;
import com.sds.android.ttpod.fragment.main.findsong.SongCategoryChannelFragment;
import com.sds.android.ttpod.fragment.main.findsong.hot.SongCategorySectionView;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.SimpleSongView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class MusicLibraryFragment extends BaseFragment implements OnPageSelectedListener {
    public static final String CATEGORY_CMMUSIC = "彩铃";
    private static final String CATEGORY_FEEL = "心情";
    private static final String CATEGORY_HOT = "热门";
    private static final String CATEGORY_LANGUAGE = "语言";
    private static final String CATEGORY_LOSSLESS = "无损";
    public static final String CATEGORY_MV = "MV";
    public static final String CATEGORY_RADIO = "电台";
    private static final String CATEGORY_SCENE = "场景";
    public static final String CATEGORY_SINGER = "歌手";
    private static final String CATEGORY_SPECIAL = "特色";
    private static final String CATEGORY_STYLE = "风格";
    private static final String CATEGORY_TTPOD_FM = "天天FM";
    private static final String CATEGORY_YEARS = "年代";
    private static final int ID_MV = 366;
    private static final int ID_SINGER = 46;
    public static final String KEY_DATA = "data";
    private static final int MUSIC_CATEGORY_PAGE = 1;
    private static final int MUSIC_CATEGORY_PAGE_SIZE = 15;
    private static final String TAG = "MusicLibraryFragment";
    public static final String TITLE_MUSIC_CHARTS = "排行榜";
    private NetworkLoadView mNetworkLoadView;
    private OnlineMusicCategoryResult mResult;
    private View mRootView;
    private SongCategorySectionView mSongCategorySectionView;
    private ArrayMap<String, Integer> mStatisticMap;
    private boolean mReloadTheme = true;
    private NetworkLoadView.InterfaceC2206b mSongCategoryStartLoadingListener = new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.MusicLibraryFragment.1
        @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
        /* renamed from: a */
        public void mo1678a() {
            CommandCenter.getInstance().execute(new Command(CommandID.GET_MUSIC_CATEGORY, 1, 15));
        }
    };
    private SimpleSongView.InterfaceC2231b mSongCategoryClickListener = new SimpleSongView.InterfaceC2231b() { // from class: com.sds.android.ttpod.fragment.main.MusicLibraryFragment.2
        @Override // com.sds.android.ttpod.widget.SimpleSongView.InterfaceC2231b
        /* renamed from: a */
        public void mo1537a(Object obj) {
            if (obj != null && (obj instanceof FindSongGridSectionListAdapter.C0963a)) {
                MusicLibraryFragment.this.processFindSongCategoryClick((FindSongGridSectionListAdapter.C0963a) obj);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_MUSIC_CATEGORY, ReflectUtils.m8375a(getClass(), "updateResult", OnlineMusicCategoryResult.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_MUSIC_LIBRARY);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_music_library, (ViewGroup) null);
            this.mNetworkLoadView = (NetworkLoadView) this.mRootView.findViewById(R.id.loading_view);
            this.mNetworkLoadView.setStartAnimationUntilVisibleToUser(true);
            ScrollView scrollView = (ScrollView) this.mRootView.findViewById(R.id.music_library_container);
            scrollView.addView(initContainer(layoutInflater));
            scrollView.setVerticalScrollBarEnabled(true);
            this.mNetworkLoadView.setOnStartLoadingListener(this.mSongCategoryStartLoadingListener);
            this.mNetworkLoadView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
        }
        initStatisticMap();
        return this.mRootView;
    }

    private View initContainer(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.music_library_header, (ViewGroup) null);
        ThemeUtils.m8178a(inflate.findViewById(R.id.item_picture), ThemeElement.TILE_BACKGROUND, ThemeElement.HOME_BACKGROUND);
        this.mSongCategorySectionView = (SongCategorySectionView) inflate.findViewById(R.id.song_category_section);
        this.mSongCategorySectionView.m1542a(false);
        this.mSongCategorySectionView.setOnSectionViewItemClickListener(this.mSongCategoryClickListener);
        return inflate;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        if (isViewAccessAble() && this.mReloadTheme) {
            super.onThemeLoaded();
            this.mReloadTheme = false;
            ThemeManager.m3269a(this.mRootView, ThemeElement.BACKGROUND_MASK);
            if (this.mSongCategorySectionView != null) {
                this.mSongCategorySectionView.onThemeLoaded();
                updateView(this.mResult);
            }
            if (this.mNetworkLoadView != null) {
                this.mNetworkLoadView.onThemeLoaded();
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onThemeChanged() {
        this.mReloadTheme = true;
        super.onThemeChanged();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updateView(this.mResult);
    }

    public void updateResult(OnlineMusicCategoryResult onlineMusicCategoryResult) {
        this.mResult = onlineMusicCategoryResult;
        ResultHelper.m5665a(this, onlineMusicCategoryResult, new ResultHelper.InterfaceC1510a<OnlineMusicCategoryResult>() { // from class: com.sds.android.ttpod.fragment.main.MusicLibraryFragment.3
            @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo5564a(OnlineMusicCategoryResult onlineMusicCategoryResult2) {
                MusicLibraryFragment.this.updateView(onlineMusicCategoryResult2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateView(OnlineMusicCategoryResult onlineMusicCategoryResult) {
        if (onlineMusicCategoryResult != null) {
            if (!onlineMusicCategoryResult.isSuccess()) {
                this.mNetworkLoadView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                return;
            }
            this.mNetworkLoadView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
            ArrayList<OnlineMusicCategoryResult.CategoryData> categoryList = onlineMusicCategoryResult.getCategoryList();
            if (categoryList != null) {
                ArrayList<FindSongGridSectionListAdapter.C0963a<OnlineMusicCategoryResult.CategoryData>> convertCategoryDataSectionList = convertCategoryDataSectionList(categoryList);
                this.mSongCategorySectionView.mo1543a(convertCategoryDataSectionList, convertCategoryDataSectionList.size());
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public boolean isSupportOfflineMode() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processFindSongCategoryClick(FindSongGridSectionListAdapter.C0963a<OnlineMusicCategoryResult.CategoryData> c0963a) {
        SAction sAction;
        int i;
        //MusicLibraryStatistic.m5059d();
        String m7617c = c0963a.m7617c();
        SAction sAction2 = SAction.ACTION_LIBRARY_OTHERS;
        if (StringUtils.equals(m7617c, CATEGORY_SINGER)) {
            launchFragment(new SingerCategoryFragment(m7617c, ID_SINGER));
            i = (int) c0963a.m7618b();
            sAction = SAction.ACTION_SINGERS;
        } else if (StringUtils.equals(m7617c, CATEGORY_RADIO)) {
            launchFragment(new RadioCategoryFragment(m7617c));
            i = (int) c0963a.m7618b();
            sAction = SAction.ACTION_RADIO;
        } else if (StringUtils.equals(m7617c, CATEGORY_MV)) {
            launchFragment(new OnlineMVFragment(m7617c, ID_MV));
            i = (int) c0963a.m7618b();
            sAction = SAction.ACTION_MV;
        } else if (StringUtils.equals(m7617c, CATEGORY_TTPOD_FM)) {
            gotoTTPodFMPage();
            i = (int) c0963a.m7618b();
            sAction = sAction2;
        } else {
            if (StringUtils.equals(m7617c, CATEGORY_CMMUSIC)) {
                if (CMMusicUtils.m7276a()) {
                    //StatisticUtils.m4917a(346, (int) 65537, 1L);
                    getActivity().startActivity(new Intent(getActivity(), ListenContentActivity.class));
                    sAction = sAction2;
                    i = 0;
                }
            } else {
                launchFragment(new SongCategoryChannelFragment(c0963a));
            }
            sAction = sAction2;
            i = 0;
        }
        //MusicLibraryStatistic.m5063b(i, m7617c);
        Integer num = this.mStatisticMap.get(m7617c);
        int intValue = num != null ? num.intValue() : 0;
        if (intValue > 0) {
            //StatisticUtils.m4917a(intValue, (int) 65537, 1L);
            //new SUserEvent("PAGE_CLICK", sAction.getValue(), String.valueOf(SPage.PAGE_MUSIC_LIBRARY.getValue()), m7617c).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(i)).post();
        }
    }

    private void gotoTTPodFMPage() {
        launchFragment(WebSlidingClosableFragment.instantiate("http://fm.ttpod.com/mindex.html", getString(R.string.ttpod_fm), "http://fm.ttpod.com/logo.png", true, false));
    }

    private void gotoMusicPage(String str, String str2) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.setData(Uri.parse(str));
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra(WebFragment.EXTRA_TITLE, str2);
        intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, false);
        startActivity(intent);
    }

    private void initStatisticMap() {
        this.mStatisticMap = new ArrayMap<>();
        this.mStatisticMap.put(CATEGORY_SINGER, 277);
        this.mStatisticMap.put(CATEGORY_RADIO, 278);
        this.mStatisticMap.put(CATEGORY_MV, 279);
        this.mStatisticMap.put(CATEGORY_HOT, 280);
        this.mStatisticMap.put(CATEGORY_SCENE, Integer.valueOf((int) RankCategoryFragment.ID_RANK_CATEGORY));
        this.mStatisticMap.put(CATEGORY_LANGUAGE, 282);
        this.mStatisticMap.put(CATEGORY_FEEL, 283);
        this.mStatisticMap.put(CATEGORY_STYLE, 284);
        this.mStatisticMap.put(CATEGORY_SPECIAL, 285);
        this.mStatisticMap.put(CATEGORY_TTPOD_FM, 345);
        this.mStatisticMap.put(CATEGORY_LOSSLESS, 431);
        this.mStatisticMap.put(CATEGORY_YEARS, 432);
    }

    private ArrayList<FindSongGridSectionListAdapter.C0963a<OnlineMusicCategoryResult.CategoryData>> convertCategoryDataSectionList(ArrayList<OnlineMusicCategoryResult.CategoryData> arrayList) {
        ArrayList<FindSongGridSectionListAdapter.C0963a<OnlineMusicCategoryResult.CategoryData>> arrayList2 = new ArrayList<>();
        Iterator<OnlineMusicCategoryResult.CategoryData> it = arrayList.iterator();
        while (it.hasNext()) {
            OnlineMusicCategoryResult.CategoryData next = it.next();
            if (next != null) {
                arrayList2.add(new FindSongGridSectionListAdapter.C0963a<>(next.getName(), next.getCount(), next.getId(), null));
            }
        }
        if (CMMusicUtils.m7276a()) {
            arrayList2.add(new FindSongGridSectionListAdapter.C0963a<>(CATEGORY_CMMUSIC, 1, 0L, null));
        }
        arrayList2.add(new FindSongGridSectionListAdapter.C0963a<>(CATEGORY_TTPOD_FM, 1, 0L, null));
        return arrayList2;
    }

    @Override // com.sds.android.ttpod.fragment.OnPageSelectedListener
    public void onPageSelected() {
        if (this.mNetworkLoadView != null) {
            this.mNetworkLoadView.setIsVisibleToUser(true);
        }
    }
}
