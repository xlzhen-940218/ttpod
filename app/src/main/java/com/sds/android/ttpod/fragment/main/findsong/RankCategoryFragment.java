package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.OnPageSelectedListener;
import com.sds.android.ttpod.fragment.main.ResultHelper;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.MediaItemListResult;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class RankCategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener, OnPageSelectedListener {
    private static final int CHANNEL_CHANGE = 4;
    private static final int CHANNEL_IDLE = 1;
    private static final int CHANNEL_PAUSE = 3;
    private static final int CHANNEL_PLAY = 2;
    private static final int DIVIDER_HEIGHT = 8;
    private static final int HOME_PAGE = 1;
    public static final int ID_RANK_CATEGORY = 281;
    private static final String TAG = "RankCategoryFragment";
    private static final int WIDTH = 94;
    private DataListFooterView mFooterView;
    private ListView mListView;
    private NetworkLoadView mLoadingView;
    private MediaItem mPlayingListLastMediaItem;
    private MusicRanksResult mResult;
    private View mRootView;
    private int mTotalPages;
    private boolean mNetMusicListNeedSynced = true;
    private int mActiveChannelId = -1;
    private String mActiveChannelTitle = "";
    private int mCurrentChannelState = 1;
    private boolean mReloadTheme = true;
    private ArrayList<MusicRank> mMusicRankList = null;
    private int mCurrentPage = 1;
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment.3
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (RankCategoryFragment.this.mCurrentPage == RankCategoryFragment.this.mTotalPages) {
                RankCategoryFragment.this.showLastPageFooterText();
            } else if (i2 > 0 && i3 >= i2 && i + i2 >= i3 && RankCategoryFragment.this.mCurrentPage < RankCategoryFragment.this.mTotalPages) {
                if (RankCategoryFragment.this.mFooterView != null) {
                    RankCategoryFragment.this.mFooterView.m1877a();
                }
                RankCategoryFragment.this.requestMusicRankList();
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    };
    private RankCategoryAdapter mAdapter = new RankCategoryAdapter();

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_ONLINE_RANK);
        this.mActiveChannelTitle = Preferences.getOnlineMediaListGroupName();
        if (!StringUtils.isEmpty(this.mActiveChannelTitle) && OnlinePlayingGroupUtils.m6912a(this.mActiveChannelTitle) && SupportFactory.getInstance(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING) {
            this.mCurrentChannelState = 2;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_base_list_no_actionbar, viewGroup, false);
            initViews();
        }
        return this.mRootView;
    }

    private void initViews() {
        this.mLoadingView = (NetworkLoadView) this.mRootView.findViewById(R.id.loading_view);
        this.mLoadingView.setStartAnimationUntilVisibleToUser(true);
        this.mListView = (ListView) this.mRootView.findViewById(R.id.market_app_list);
        this.mListView.addHeaderView(initHeadView());
        this.mListView.addFooterView(initHeadView());
        this.mLoadingView.setOnStartLoadingListener(new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment.1
            @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
            /* renamed from: a */
            public void mo1678a() {
                RankCategoryFragment.this.requestMusicRankList();
            }
        });
        this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
        this.mListView.setEmptyView(this.mLoadingView);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setDivider(null);
        this.mListView.setDividerHeight(DisplayUtils.dp2px(8));
        this.mListView.setFooterDividersEnabled(true);
        this.mListView.setVerticalScrollBarEnabled(false);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
    }

    private View initHeadView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.rank_category_header, (ViewGroup) null);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        if (isViewAccessAble() && this.mReloadTheme) {
            if (this.mFooterView != null) {
                ThemeManager.m3269a(this.mFooterView, "BackgroundMaskColor");
                ThemeManager.m3269a(this.mFooterView, ThemeElement.COMMON_SUB_TITLE_TEXT);
            }
            this.mLoadingView.onThemeLoaded();
            ThemeManager.m3269a(this.mListView, "BackgroundMaskColor");
            ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
            this.mReloadTheme = false;
            if (this.mListView != null) {
                this.mListView.setDivider(null);
                this.mListView.setDividerHeight(DisplayUtils.dp2px(8));
                this.mListView.setFooterDividersEnabled(true);
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        updateView(this.mResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestMusicRankList() {
        CommandCenter.getInstance().execute(new Command(CommandID.GET_MUSIC_RANKS, toString()));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onThemeChanged() {
        this.mReloadTheme = true;
        this.mAdapter.notifyDataSetChanged();
        super.onThemeChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_MUSIC_RANKS, ReflectUtils.loadMethod(cls, "updateMusicRanks", MusicRanksResult.class, String.class));
        map.put(CommandID.UPDATE_RANK_MUSIC_LIST, ReflectUtils.loadMethod(cls, "updateRankMusicData", MediaItemListResult.class, String.class));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.loadMethod(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.loadMethod(cls, "updatePlayMediaItemInfo", new Class[0]));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.loadMethod(cls, "playMediaChanged", new Class[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPlayImageClick(View view) {
        MusicRank musicRank = (MusicRank) view.getTag(R.id.view_bind_data);
        if (musicRank != null) {
            isPlayingItem(musicRank);
            String m6917a = OnlinePlayingGroupUtils.m6917a(musicRank);
            if (!StringUtils.equals(this.mActiveChannelTitle, m6917a) || this.mActiveChannelId != musicRank.getId()) {
                this.mActiveChannelId = musicRank.getId();
                this.mActiveChannelTitle = m6917a;
                this.mCurrentChannelState = 4;
                this.mAdapter.notifyDataSetChanged();
            }
            //OnlineMediaStatistic.m5045a("song-rank_" + musicRank.getTitle() + "_" +// RankStatistic.m4968a());
            //OnlineMediaStatistic.m5054a();
            updateState(this.mCurrentChannelState);
            int indexOf = this.mMusicRankList.indexOf(musicRank);
            if (indexOf > -1) {
               // RankStatistic.m4966a(musicRank.getId(), musicRank.getTitle(), indexOf + 1);
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_RANK_PLAY_ALL.getValue(), SPage.PAGE_RANK_CATEGORY.getValue(), 0).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(musicRank.getId())).append("title", musicRank.getTitle()).append("position", Integer.valueOf(indexOf + 1)).post();
            }
        }
    }

    private void updateState(int i) {
        LogUtils.debug(TAG, "RankCategoryFragment.transferToState--->state: " + i);
        switch (i) {
            case 1:
            case 4:
                requestMusicList(this.mActiveChannelId);
                this.mNetMusicListNeedSynced = true;
                return;
            case 2:
                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.PAUSE, new Object[0]));
                return;
            case 3:
                CommandCenter.getInstance().execute(new Command(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PAUSED ? CommandID.RESUME : CommandID.START, new Object[0]));
                return;
            default:
                return;
        }
    }

    public void updateRankMusicData(MediaItemListResult mediaItemListResult, String str) {
        if (mediaItemListResult == null) {
            PopupsUtils.m6760a((int) R.string.network_unavailable);
        } else if (isVisible()) {
            ArrayList<MediaItem> m4517a = mediaItemListResult.m4517a();
            LogUtils.debug(TAG, "RankCategoryFragment.updateRankMusicList---musicList.size: " + m4517a.size() + " mNetMusicListNeedSynced: " + this.mNetMusicListNeedSynced);
            if (m4517a.size() > 0 && this.mCurrentChannelState == 4) {
                if (this.mNetMusicListNeedSynced) {
                    CommandCenter.getInstance().postInvokeResult(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, m4517a, this.mActiveChannelTitle));
                    CommandCenter.getInstance().postInvokeResult(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, m4517a.get(0)));
                    this.mNetMusicListNeedSynced = false;
                } else {
                    CommandCenter.getInstance().postInvokeResult(new Command(CommandID.APPEND_NET_TEMPORARY_MEDIA_ITEMS, m4517a));
                }
                this.mPlayingListLastMediaItem = m4517a.get(m4517a.size() - 1);
            }
        }
    }

    public void playMediaChanged() {
        if (!StringUtils.equals(Preferences.getOnlineMediaListGroupName(), this.mActiveChannelTitle)) {
            this.mActiveChannelTitle = "";
        }
        if (isAdded()) {
            updatePlayStatus(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (isViewAccessAble() && this.mAdapter != null) {
            switch (playStatus) {
                case STATUS_PLAYING:
                    this.mCurrentChannelState = 2;
                    this.mActiveChannelTitle = Preferences.getOnlineMediaListGroupName();
                    break;
                case STATUS_PAUSED:
                    this.mCurrentChannelState = 3;
                    this.mActiveChannelTitle = "";
                    break;
                case STATUS_STOPPED:
                case STATUS_ERROR:
                    this.mActiveChannelTitle = "";
                    this.mCurrentChannelState = 1;
                    break;
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void updatePlayMediaItemInfo() {
        if (isViewAccessAble() && Cache.getInstance().getCurrentPlayMediaItem().equals(this.mPlayingListLastMediaItem)) {
            requestMusicList(this.mActiveChannelId);
        }
    }

    private void requestMusicList(int i) {
        LogUtils.debug(TAG, "RankCategoryFragment.requestMusicList---id: " + i);
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.GET_RANK_MUSIC_LIST, Integer.valueOf(i), 1, toString()));
    }

    public void updateMusicRanks(MusicRanksResult musicRanksResult, String str) {
        this.mResult = musicRanksResult;
        ResultHelper.m5665a(this, musicRanksResult, new ResultHelper.InterfaceC1510a<MusicRanksResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment.2
            @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo5564a(MusicRanksResult musicRanksResult2) {
                RankCategoryFragment.this.updateView(musicRanksResult2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateView(MusicRanksResult musicRanksResult) {
        if (musicRanksResult != null) {
            if (!musicRanksResult.isSuccess()) {
                this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                return;
            }
            ArrayList<MusicRank> dataList = musicRanksResult.getDataList();
            if (ListUtils.m4717b(dataList)) {
                updateMusicRanks(dataList, (Integer) 1);
            } else if (ListUtils.m4718a(this.mMusicRankList)) {
                this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
            }
        }
    }

    private void updateMusicRanks(ArrayList<MusicRank> arrayList, Integer num) {
        this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
        hideFooter();
        if (1 == this.mCurrentPage) {
            if (this.mMusicRankList != null) {
                this.mMusicRankList.clear();
            }
            this.mMusicRankList = arrayList;
            this.mTotalPages = num.intValue();
        } else {
            this.mMusicRankList.addAll(arrayList);
        }
        this.mAdapter.setDataList((List) this.mMusicRankList);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MusicRank musicRank = (MusicRank) view.getTag(R.id.view_bind_data);
        if (musicRank != null) {
            //OnlineMediaStatistic.m5045a("song-rank_" + musicRank.getTitle() + "_" +// RankStatistic.m4968a());
            //OnlineMediaStatistic.m5054a();
            launchFragment(new SubRankDetailFragment(musicRank));
            this.mActiveChannelId = musicRank.getId();
           // RankStatistic.m4961b(musicRank.getId(), musicRank.getTitle(), i);
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_RANK_TO_DETAIL.getValue(), SPage.PAGE_RANK_CATEGORY.getValue(), SPage.PAGE_RANK_DETAIL.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(musicRank.getId())).append("title", musicRank.getTitle()).append("position", Integer.valueOf(i)).post();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public boolean isSupportOfflineMode() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlayingItem(MusicRank musicRank) {
        return StringUtils.equals(this.mActiveChannelTitle, OnlinePlayingGroupUtils.m6917a(musicRank));
    }

    @Override // com.sds.android.ttpod.fragment.OnPageSelectedListener
    public void onPageSelected() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setIsVisibleToUser(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment$a */
    /* loaded from: classes.dex */
    public class RankCategoryAdapter extends BaseListAdapter<MusicRank> {
        private RankCategoryAdapter() {
        }

        /* renamed from: a */
        protected void setImageViewData(ImageView imageView, MusicRank musicRank) {
            imageView.setImageResource(((RankCategoryFragment.this.mCurrentChannelState == 2 || RankCategoryFragment.this.mCurrentChannelState == 4) && RankCategoryFragment.this.isPlayingItem(musicRank)) ? R.drawable.img_rank_play_paused : R.drawable.img_rank_play_normal);
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = this.layoutInflater.inflate(R.layout.rank_category_item, viewGroup, false);
            if (inflate != null) {
                inflate.setTag(new RankCategoryViewHolder(inflate));
            }
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void buildDataUI(View view, MusicRank musicRank, int i) {
            long uptimeMillis = SystemClock.uptimeMillis();
            if (RankCategoryFragment.this.mMusicRankList != null) {
                MusicRank musicRank2 = (MusicRank) RankCategoryFragment.this.mMusicRankList.get(i);
                view.setTag(R.id.view_bind_data, musicRank2);
                RankCategoryViewHolder rankCategoryViewHolder = (RankCategoryViewHolder) view.getTag();
                rankCategoryViewHolder.title.setText(musicRank2.getTitle());
                setImageViewData(rankCategoryViewHolder.imagePlayState, musicRank2);
                setSongListData(musicRank2, rankCategoryViewHolder);
                rankCategoryViewHolder.image.setTag(R.id.view_bind_data, musicRank2);
                rankCategoryViewHolder.image.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment.a.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        RankCategoryFragment.this.onPlayImageClick(view2);
                    }
                });
                int m7229a = DisplayUtils.dp2px((int) RankCategoryFragment.WIDTH);
                ImageCacheUtils.displayImage(rankCategoryViewHolder.image, musicRank2.getPicUrl(), m7229a, m7229a, (int) R.drawable.img_music_default_icon);
                rankCategoryViewHolder.m3250a(ThemeUtils.m8163b());
                LogUtils.info(RankCategoryFragment.TAG, "time: " + (SystemClock.uptimeMillis() - uptimeMillis));
            }
        }

        /* renamed from: a */
        private void setSongListData(MusicRank musicRank, RankCategoryViewHolder c1557b) {
            int i = 0;
            ArrayList<MusicRank.SimpleSongInfo> songList = musicRank.getSongList();
            if (songList != null) {
                int min = Math.min(songList.size(), 4);
                for (int i2 = 0; i2 < min; i2++) {
                    MusicRank.SimpleSongInfo simpleSongInfo = songList.get(i2);
                    ((TextView) c1557b.songList.get(i2)).setText((i2 + 1) + ". " + simpleSongInfo.getSongName() + " - " + simpleSongInfo.getSingerName());
                }
                i = min;
            }
            while (i < 4) {
                ((TextView) c1557b.songList.get(i)).setText("");
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment$b */
    /* loaded from: classes.dex */
    public static class RankCategoryViewHolder extends ThemeManager.AbstractC2018a {

        /* renamed from: a */
        private View convertView;

        /* renamed from: b */
        private View titleBarLeftLine;

        /* renamed from: c */
        private TextView title;

        /* renamed from: d */
        private ImageView image;

        /* renamed from: e */
        private ImageView imagePlayState;

        /* renamed from: f */
        private List<TextView> songList = new ArrayList(4);

        /* renamed from: g */
        private View layoutContent;

        public RankCategoryViewHolder(View view) {
            this.convertView = view;
            this.titleBarLeftLine = view.findViewById(R.id.id_title_bar_left_line);
            this.title = (TextView) view.findViewById(R.id.title);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.imagePlayState = (ImageView) view.findViewById(R.id.image_play_state);
            this.songList.add((TextView) view.findViewById(R.id.song0));
            this.songList.add((TextView) view.findViewById(R.id.song1));
            this.songList.add((TextView) view.findViewById(R.id.song2));
            this.songList.add((TextView) view.findViewById(R.id.song3));
            this.layoutContent = view.findViewById(R.id.layout_content);
        }

        @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.AbstractC2018a
        /* renamed from: a */
        protected void mo3251a() {
            ThemeManager.m3269a(this.layoutContent, ThemeElement.TILE_BACKGROUND);
            ThemeManager.m3269a(this.titleBarLeftLine, ThemeElement.SONG_LIST_ITEM_INDICATOR);
            ThemeManager.m3269a(this.title, ThemeElement.TILE_TEXT);
            ThemeManager.m3269a(this.title, ThemeElement.TILE_BACKGROUND);
            ThemeUtils.m8173a((IconTextView) this.convertView.findViewById(R.id.item_click_arrow), ThemeElement.TILE_SUB_TEXT);
            int size = this.songList.size();
            for (int i = 0; i < size; i++) {
                ThemeManager.m3269a(this.songList.get(i), ThemeElement.SONG_LIST_ITEM_TEXT);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLastPageFooterText() {
        if (this.mFooterView != null) {
            this.mFooterView.m1875a(BaseApplication.getApplication().getString(R.string.last_page_prompt));
        }
    }

    private void hideFooter() {
        if (this.mFooterView != null) {
            this.mFooterView.m1873c();
        }
    }
}
