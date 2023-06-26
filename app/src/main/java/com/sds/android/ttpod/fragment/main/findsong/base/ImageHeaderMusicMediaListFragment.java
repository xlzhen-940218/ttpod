package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.media.mediastore.MediaItem;

public class ImageHeaderMusicMediaListFragment extends OnlineMediaListFragment {
    ImageHeaderMusicListFragment fragment;

    public ImageHeaderMusicMediaListFragment(ImageHeaderMusicListFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        fragment.setupListHeader();
        return onCreateView;
    }

    @Override
    // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mListView.setFastScrollEnabled(true);
    }

    @Override
    // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onFavoriteChanged(MediaItem mediaItem, boolean z) {
        fragment.doStatiticFavorite(mediaItem, z);
        //ListStatistic.m5206a(mediaItem.getSongID().longValue(), //OnlineMediaStatistic.m5029f(), z);
    }

    @Override
    // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onMediaItemClicked(MediaItem mediaItem, int i) {
        super.onMediaItemClicked(mediaItem, i);
        fragment.doStatisticMediaClick(mediaItem);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
    protected boolean onListStatistic() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
    public void beforeOnMediaItemClicked(MediaItem mediaItem) {
        super.beforeOnMediaItemClicked(mediaItem);
        fragment.beforeOnlineFragmentOnMediaItemClicked(mediaItem);
    }
}