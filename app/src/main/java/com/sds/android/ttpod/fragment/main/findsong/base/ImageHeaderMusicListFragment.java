package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes.dex */
public abstract class ImageHeaderMusicListFragment extends BaseFragment {
    protected static final int HOME_PAGE = 1;
    private static final String TAG = "ImageHeaderMusicListFragment";
    private InterfaceC1588a mOnSetTitleListener;
    protected OnlineMediaListFragment mOnlineMediaListFragment;

    private String mPlayingGroupName = "";
    private long mPlayingSongId;
    protected View mRootView;
    protected ArrayList mediaItemList;

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1588a {
        /* renamed from: a */
        void mo5547a(String str);
    }


    protected abstract String onLoadTitleText();

    protected abstract void setupListHeader();

    public void setOnSetTitleListener(final ActionBarController actionBarController) {
        if (actionBarController != null) {
            this.mOnSetTitleListener = new InterfaceC1588a() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment.1
                @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment.InterfaceC1588a
                /* renamed from: a */
                public void mo5547a(String str) {
                    actionBarController.m7193a((CharSequence) str);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTitle(String str) {
        if (this.mOnSetTitleListener != null) {
            this.mOnSetTitleListener.mo5547a(str);
        }
    }

    public void setPlayingGroupName(String str) {
        this.mPlayingGroupName = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "playMediaChanged", new Class[0]));
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        MediaItem m3225N;
        if (isViewAccessAble() && (m3225N = Cache.getInstance().getCurrentPlayMediaItem()) != null) {
            Long songID = m3225N.getSongID();
            this.mPlayingSongId = songID == null ? 0L : songID.longValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isPlayingItem() {
        return StringUtils.m8344a(Preferences.m2926bc(), this.mPlayingGroupName);
    }

    public void playMediaChanged() {
        updatePlayStatus(PlayStatus.STATUS_PLAYING);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mRootView, ThemeElement.BACKGROUND_MASK);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void requestDataList(int i) {
        if (this.mOnlineMediaListFragment.isHomePage()) {
            this.mOnlineMediaListFragment.updateStateViews(null);
        }
    }

    public void updateData(ArrayList arrayList, Integer num) {
        if (isViewAccessAble()) {
            this.mediaItemList = arrayList;
            this.mOnlineMediaListFragment.updateMediaList(num, arrayList);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return onCreateContentView(layoutInflater, viewGroup, bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_imageheader_musiclist, (ViewGroup) null);
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initOnlineMediaListFragment();
        setupTitleText();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updatePlayStatus(SupportFactory.m2397a(BaseApplication.getApplication()).m2463m());
    }

    private void initOnlineMediaListFragment() {
        if (this.mOnlineMediaListFragment == null) {
            Bundle bundle = new Bundle();
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
            this.mOnlineMediaListFragment = new ImageHeaderMusicMediaListFragment();
            this.mOnlineMediaListFragment.setArguments(bundle);
            this.mOnlineMediaListFragment.setOnNextPageListener(new OnlineMediaListFragment.InterfaceC1668c() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment.2
                @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.InterfaceC1668c
                /* renamed from: a */
                public void mo5452a(int i) {
                    ImageHeaderMusicListFragment.this.requestDataList(i);
                }
            });
            this.mOnlineMediaListFragment.setOnDataRequestListener(new OnlineMediaListFragment.InterfaceC1666a() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment.3
                @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.InterfaceC1666a
                /* renamed from: a */
                public void mo5420a() {
                    ImageHeaderMusicListFragment.this.requestDataList(1);
                }
            });
        }
        getChildFragmentManager().beginTransaction().replace(R.id.content_online_media_list, this.mOnlineMediaListFragment).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDownloadButtonClick() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void togglePlayMedia(long j) {
        if (!this.mOnlineMediaListFragment.isEmpty()) {
            OnlineMediaUtils.m4678a(this.mPlayingSongId, this.mOnlineMediaListFragment.getMediaItemList(), this.mPlayingGroupName, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void replayPlayMediaRepeat(long j) {
        if (!this.mOnlineMediaListFragment.isEmpty()) {
            OnlineMediaUtils.m4673a(this.mOnlineMediaListFragment.getMediaItemList(), this.mPlayingGroupName, j);
            Preferences.m3018a(PlayMode.REPEAT);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void downloadAllMediaList() {
        new DownloadMenuHandler(getActivity()).m6926a(this.mOnlineMediaListFragment.getMediaItemList());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSongListNotLoaded() {
        return this.mOnlineMediaListFragment == null || ListUtils.m4718a(this.mOnlineMediaListFragment.getMediaItemList());
    }

    protected ActionExpandableListView getListView() {
        return this.mOnlineMediaListFragment.getListView();
    }

    protected void setupTitleText() {
    }

    /* loaded from: classes.dex */
    public class ImageHeaderMusicMediaListFragment extends OnlineMediaListFragment {
        public ImageHeaderMusicMediaListFragment() {
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, android.support.v4.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            ImageHeaderMusicListFragment.this.setupListHeader();
            //ListStatistic.m5211a(1);
            //ListStatistic.m5205a(ImageHeaderMusicListFragment.this.onLoadTitleText());
            //ListStatistic.m5202b(UUID.randomUUID().toString());
            return onCreateView;
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mListView.setFastScrollEnabled(true);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
        public void onThemeLoaded() {
            super.onThemeLoaded();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void onFavoriteChanged(MediaItem mediaItem, boolean z) {
            ImageHeaderMusicListFragment.this.doStatiticFavorite(mediaItem, z);
            //ListStatistic.m5206a(mediaItem.getSongID().longValue(), //OnlineMediaStatistic.m5029f(), z);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void onMediaItemClicked(MediaItem mediaItem, int i) {
            super.onMediaItemClicked(mediaItem, i);
            ImageHeaderMusicListFragment.this.doStatisticMediaClick(mediaItem);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
        protected boolean onListStatistic() {
            return true;
        }

        @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
        public void beforeOnMediaItemClicked(MediaItem mediaItem) {
            super.beforeOnMediaItemClicked(mediaItem);
            ImageHeaderMusicListFragment.this.beforeOnlineFragmentOnMediaItemClicked(mediaItem);
        }
    }

    protected void beforeOnlineFragmentOnMediaItemClicked(MediaItem mediaItem) {
    }

    protected void doStatiticFavorite(MediaItem mediaItem, boolean z) {
        //MusicLibraryStatistic.m5065a(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doStatisticMediaClick(MediaItem mediaItem) {
        //MusicLibraryStatistic.m5061c();
    }
}
