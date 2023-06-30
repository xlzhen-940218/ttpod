package com.sds.android.ttpod.fragment.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;

import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity;
import com.sds.android.ttpod.adapter.p073e.OnlinePlayStatus;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.SearchResultFragment;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.AZSideBar;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AlbumDetailFragment extends SlidingClosableFragment {
    private static final int MAX_HEADER_HEIGHT = 250;
    private View mAlbumHeader;
    private AlbumItem mAlbumItem;
    private C1712a mAlbumListFragment;
    private OnlinePlayStatus mPlayStatus;
    private ImageView mPlayView;
    private long mPlayingSongId;
    private View mRootView;
    private TextView mSongCount;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.loadMethod(getClass(), "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.loadMethod(getClass(), "playMediaChanged", new Class[0]));
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        this.mPlayStatus = OnlinePlayStatus.from(playStatus);
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (m3225N != null) {
            Long songID = m3225N.getSongID();
            this.mPlayingSongId = songID == null ? 0L : songID.longValue();
        }
        if (this.mPlayView != null) {
            boolean z = this.mPlayStatus == OnlinePlayStatus.PLAYING && isPlayingItem();
            this.mPlayView.clearAnimation();
            this.mPlayView.setEnabled(true);
            this.mPlayView.setSelected(z);
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        //ListStatistic.m5211a(0);
    }

    public void playMediaChanged() {
        updatePlayStatus(PlayStatus.STATUS_PLAYING);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_album_detail, (ViewGroup) null);
            initAlbumListFragment();
            initActionBar();
        }
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mRootView != null) {
            ThemeManager.m3269a(this.mRootView, "BackgroundMaskColor");
            ThemeManager.m3269a(this.mSongCount, ThemeElement.SUB_BAR_BACKGROUND);
            ThemeManager.m3269a(this.mSongCount, ThemeElement.SUB_BAR_TEXT);
            if (this.mAlbumHeader != null) {
                loadHeaderTheme();
            } else {
                this.mRootView.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.search.AlbumDetailFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (AlbumDetailFragment.this.mAlbumHeader != null) {
                            AlbumDetailFragment.this.loadHeaderTheme();
                        }
                    }
                }, 300L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadHeaderTheme() {
        ThemeManager.m3269a(this.mAlbumHeader.findViewById(R.id.layout_album_introduce), ThemeElement.CARD_BACKGROUND);
        ThemeManager.m3269a(this.mAlbumHeader.findViewById(R.id.album_name), ThemeElement.CARD_TEXT);
        ThemeManager.m3269a(this.mAlbumHeader.findViewById(R.id.album_artist), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(this.mAlbumHeader.findViewById(R.id.album_lang), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(this.mAlbumHeader.findViewById(R.id.album_publish_time), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(this.mAlbumHeader.findViewById(R.id.album_introduce), ThemeElement.CARD_TEXT);
        ThemeManager.m3269a(this.mAlbumHeader.findViewById(R.id.album_song_count), ThemeElement.TILE_TEXT);
        ThemeManager.m3269a(this.mAlbumHeader.findViewById(R.id.album_song_count), ThemeElement.TILE_MASK);
    }

    private void initActionBar() {
        getActionBarController().m7189b(R.string.album_detail);
    }

    private void performOnDownloadActionClick() {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), AlbumDownloadSelectActivity.class);
            intent.putExtra(AlbumDownloadSelectActivity.KEY_MEDIA_LIST, (Serializable) this.mAlbumListFragment.getList());
            startActivity(intent);
            //SearchStatistic.m4933f();
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SEARCH_ALBUM_MENU_DOWNLOAD.getValue(), SPage.PAGE_SEARCH_ALBUM_DETAIL.getValue(), SPage.PAGE_SEARCH_ALBUM_DOWNLOAD.getValue()).append("song_album_id", Long.valueOf(this.mAlbumItem.getId())).post();
        }
    }

    private void initAlbumListFragment() {
        if (this.mAlbumListFragment == null) {
            this.mAlbumListFragment = new C1712a();
            Bundle bundle = new Bundle();
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
            this.mAlbumListFragment.setArguments(bundle);
            this.mAlbumListFragment.setModule("search");
            this.mAlbumListFragment.setOrigin("-album");
            this.mAlbumListFragment.setOnDataRequestListener(() -> AlbumDetailFragment.this.requestSongList());
        }
        getChildFragmentManager().beginTransaction().replace(R.id.album_list, this.mAlbumListFragment).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupListHeader() {
        this.mAlbumHeader = View.inflate(getActivity(), R.layout.album_list_header, null);
        this.mAlbumItem = (AlbumItem) getArguments().getSerializable(SearchResultFragment.KEY_SEARCH_WORD);
        bindHeader(this.mAlbumHeader, this.mAlbumItem);
        this.mPlayView = (ImageView) this.mAlbumHeader.findViewById(R.id.album_play_button);
        this.mPlayView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.search.AlbumDetailFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AlbumDetailFragment.this.isPlayingItem()) {
                    AlbumDetailFragment.this.mPlayView.clearAnimation();
                    AlbumDetailFragment.this.mPlayView.setEnabled(true);
                    AlbumDetailFragment.this.mPlayView.setSelected(AlbumDetailFragment.this.mPlayStatus == OnlinePlayStatus.PLAYING);
                } else {
                    AlbumDetailFragment.this.mPlayView.setEnabled(false);
                    AlbumDetailFragment.this.mPlayView.startAnimation(AnimationUtils.loadAnimation(AlbumDetailFragment.this.getActivity(), R.anim.unlimited_rotate));
                }
                AlbumDetailFragment.this.togglePlayMedia();
                //SearchStatistic.m4936d();
                //new SUserEvent("PAGE_CLICK", SAction.ACTOIN_CLICK_SEARCH_ALBUM_PLAY_ALL.getValue(), SPage.PAGE_SEARCH_ALBUM_DETAIL.getValue(), 0).append("song_album_id", Long.valueOf(AlbumDetailFragment.this.mAlbumItem.getId())).post();
            }
        });
        this.mAlbumListFragment.getListView().addHeaderView(this.mAlbumHeader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void togglePlayMedia() {
        if (!this.mAlbumListFragment.isEmpty()) {
            OnlineMediaUtils.m4679a(this.mPlayingSongId, this.mAlbumListFragment.getList(), OnlinePlayingGroupUtils.m6918a(this.mAlbumItem));
        }
    }

    private void bindHeader(View view, AlbumItem albumItem) {
        TextView textView = (TextView) view.findViewById(R.id.album_introduce);
        this.mSongCount = (TextView) view.findViewById(R.id.album_song_count);
        ImageCacheUtils.displayImage((ImageView) view.findViewById(R.id.album_pic), albumItem.getPic500(), DisplayUtils.getWidth(), DisplayUtils.getWidth(), (int) R.drawable.img_album_detail_defaul);
        ((TextView) view.findViewById(R.id.album_name)).setText(albumItem.getName());
        ((TextView) view.findViewById(R.id.album_artist)).setText(albumItem.getSingerName());
        ((TextView) view.findViewById(R.id.album_lang)).setText(getString(R.string.album_lang, albumItem.getLang()));
        ((TextView) view.findViewById(R.id.album_publish_time)).setText(getString(R.string.album_publish_time, albumItem.getPublishTime()));
        if (albumItem.getDesc() != null) {
            textView.setText(Html.fromHtml(validateVisualString(albumItem.getDesc())));
        }
        view.findViewById(R.id.layout_album_introduce).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.search.AlbumDetailFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AlbumDetailFragment.this.launchAlbumIntroduceFragment();
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SEARCH_ALBUM_DETAIL_INTRODUCTION.getValue(), SPage.PAGE_SEARCH_ALBUM_DETAIL.getValue(), SPage.PAGE_SEARCH_ALBUM_INTRODUCTION.getValue()).append("song_album_id", Long.valueOf(AlbumDetailFragment.this.mAlbumItem.getId())).post();
            }
        });
    }

    public String validateVisualString(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("([\\u0000-\\u001f\\uD7B0-\\uFEFF\\uFFF0-\\uFFFF]+)", " ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchAlbumIntroduceFragment() {
        AlbumIntroduceFragment albumIntroduceFragment = new AlbumIntroduceFragment();
        albumIntroduceFragment.setArguments(getArguments());
        launchFragment(albumIntroduceFragment);
        //SearchStatistic.m4934e();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updatePlayStatus(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSongList() {
        this.mAlbumListFragment.updateStateViews(null);
        OnlineMediaUtils.m4677a(this.mAlbumItem, new RequestCallback<OnlineMediaItemsResult>() { // from class: com.sds.android.ttpod.fragment.search.AlbumDetailFragment.5
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                ArrayList arrayList = new ArrayList();
                for (OnlineMediaItem onlineMediaItem : dataList) {
                    if (onlineMediaItem != null) {
                        arrayList.add(MediaItemUtils.m4716a(onlineMediaItem));
                    }
                }
                if (AlbumDetailFragment.this.isAdded()) {
                    AlbumDetailFragment.this.mSongCount.setText(AlbumDetailFragment.this.getString(R.string.album_song_count, Integer.valueOf(arrayList.size())));
                    AlbumDetailFragment.this.mAlbumListFragment.updateMediaList(arrayList);
                }
                //SearchStatistic.m4940b(Integer.valueOf(onlineMediaItemsResult.getCode()));
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OnlineMediaItemsResult onlineMediaItemsResult) {
                if (AlbumDetailFragment.this.isAdded()) {
                    AlbumDetailFragment.this.mAlbumListFragment.updateMediaList(null);
                }
                //SearchStatistic.m4940b(Integer.valueOf(onlineMediaItemsResult.getCode()));
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needMenuAction() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected Collection<ActionItem> onCreateDropDownMenu() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ActionItem(20, 0, (int) R.string.album_download_select_activity_title));
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        super.onDropDownMenuClicked(i, actionItem);
        if (i == 20) {
            performOnDownloadActionClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.search.AlbumDetailFragment$a */
    /* loaded from: classes.dex */
    public class C1712a extends OnlineMediaListFragment {
        private C1712a() {
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, android.support.v4.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            this.mStateView = (StateView) layoutInflater.inflate(R.layout.fragment_album_list, viewGroup, false);
            this.mListView = (ActionExpandableListView) this.mStateView.findViewById(R.id.media_listview);
            this.mAZSideBar = (AZSideBar) this.mStateView.findViewById(R.id.azsidebar);
            this.mFailedView = onCreateFailedView(layoutInflater);
            this.mStateView.setFailedView(this.mFailedView);
            this.mNodataView = onCreateNodataView(layoutInflater);
            this.mStateView.setNodataView(this.mNodataView);
            AlbumDetailFragment.this.setupListHeader();
            this.mListView.setFooterDividersEnabled(false);
            return this.mStateView;
        }

        public List<MediaItem> getList() {
            return getMediaItemList();
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void onMediaItemClicked(MediaItem mediaItem, int i) {
            super.onMediaItemClicked(mediaItem, i);
            //StatisticUtils.m4907a("search", "listen", getOrigin(), 0L, //OnlineMediaStatistic.m5029f(), //SearchStatistic.m4938c(), //SearchStatistic.m4950a());
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SEARCH_ALBUM_SONG_ITEM.getValue(), SPage.PAGE_SEARCH_ALBUM_DETAIL.getValue(), 0).append("song_album_id", Long.valueOf(AlbumDetailFragment.this.mAlbumItem.getId())).append("song_id", mediaItem.getSongID()).append("position", Integer.valueOf(//OnlineMediaStatistic.m5029f())).post();
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        protected void showRightContextMenu(MediaItem mediaItem) {
            if (getOrigin() != null) {
                //StatisticUtils.m4907a(getModule(), "menu", getOrigin(), 0L, //OnlineMediaStatistic.m5029f(), //SearchStatistic.m4938c(), //SearchStatistic.m4950a());
            }
            new DownloadMenuHandler(getActivity()).m6927a(mediaItem, getOrigin());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void onFavoriteChanged(MediaItem mediaItem, boolean z) {
            super.onFavoriteChanged(mediaItem, z);
            //SearchStatistic.m4943a(z);
            //ListStatistic.m5206a(mediaItem.getSongID().longValue(), //OnlineMediaStatistic.m5029f(), z);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
        protected boolean onListStatistic() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlayingItem() {
        return OnlinePlayingGroupUtils.m6911a(Preferences.getOnlineMediaListGroupName(), this.mAlbumItem);
    }
}
