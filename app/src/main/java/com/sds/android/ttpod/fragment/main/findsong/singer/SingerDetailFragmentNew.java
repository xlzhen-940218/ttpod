package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.cloudapi.ttpod.data.SingerData;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p075f.AlbumListAdapter;
import com.sds.android.ttpod.adapter.p075f.SongListAdapter;
import com.sds.android.ttpod.adapter.p075f.TabPagerAdapter;
import com.sds.android.ttpod.component.p085b.MediaItemMenuHolder;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.fragment.main.findsong.SingerCategoryHotDetailFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.MediaItemListResult;
import com.sds.android.ttpod.framework.p106a.p107a.MusicLibraryStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.OnlineMediaStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.ArtistUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SingerDetailFragmentNew extends SingerTabFragment implements AbstractExpandableListAdapter.InterfaceC2279a {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DELAY_REQUEST_SINGER_IMAGE = 200;
    private static final int TAB_ALBUM = 1;
    public static final int TAB_SONG = 0;
    private static final String TAG = "SingerDetailFragmentNew";
    private AlbumListAdapter mAlbumAdapter;
    private String mChannel;
    private SingerData mSingerData;
    private SingerHeader mSingerHeader;
    private String mSingerName;
    private SongListAdapter mSongAdapter;

    public SingerDetailFragmentNew(String str) {
        this.mSingerName = str;
        setGroupID(MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        setModule(MusicLibraryStatistic.m5057e());
        setOrigin(OnlineMediaStatistic.m5043b());
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mChannel = arguments.getString(SingerCategoryHotDetailFragment.KEY_CHANNEL);
            this.mSingerData = (SingerData) arguments.getSerializable("key_data");
        }
        this.mSongAdapter = new SongListAdapter(getActivity(), getGroupID());
        this.mSongAdapter.m7394d(getModule());
        this.mSongAdapter.m7399a(getOrigin());
        this.mAlbumAdapter = new AlbumListAdapter(getActivity());
        if (this.mSingerData != null) {
            setPage(this.mSingerData.getName());
            setPageProperties(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mSingerData.getId()));
        }
        this.mSongAdapter.m7407a(new SongListAdapter.InterfaceC1003a() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragmentNew.1
            @Override // com.sds.android.ttpod.adapter.p075f.SongListAdapter.InterfaceC1003a
            /* renamed from: a */
            public void mo5476a(MediaItem mediaItem, View view, MediaItemMenuHolder mediaItemMenuHolder, int i) {
                ListViewUtils.m8264a(SingerDetailFragmentNew.this.mListView);
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragmentNew.2
            @Override // java.lang.Runnable
            public void run() {
                SingerDetailFragmentNew.this.requestSingerImage();
            }
        }, 200L);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7193a((CharSequence) this.mSingerName);
        this.mListView.setItemExpandCollapseListener(this);
    }

    private void updateMediaItemView(MediaItemViewHolder mediaItemViewHolder, int i, boolean z) {
        if (z && i < this.mSongAdapter.getCount()) {
            ((MediaItemMenuHolder) mediaItemViewHolder.m6957h().getTag()).m6977a(this.mSongAdapter.getItem(i));
        }
        mediaItemViewHolder.m6961d().setText(z ? R.string.icon_arrow_top : R.string.icon_arrow_down);
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
    public void onExpand(View view, int i) {
        updateMediaItemView((MediaItemViewHolder) ((View) view.getParent()).getTag(), i, true);
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
    public void onCollapse(View view, int i) {
        View view2;
        if (view != null && (view2 = (View) view.getParent()) != null) {
            updateMediaItemView((MediaItemViewHolder) view2.getTag(), i, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_SINGER_SONG_LIST, ReflectUtils.m8375a(cls, "updateSingerSongList", MediaItemListResult.class));
        map.put(CommandID.UPDATE_SEARCH_ALBUM_FINISHED, ReflectUtils.m8375a(cls, "updateSearchAlbum", AlbumItemsResult.class));
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.m8375a(cls, "updatePlayingMediaInfo", new Class[0]));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "playMediaChanged", new Class[0]));
        map.put(CommandID.UPDATE_FAVORITE_CHANGED, ReflectUtils.m8375a(cls, "updateFavoriteChanged", new Class[0]));
    }

    public void updateSingerSongList(MediaItemListResult mediaItemListResult) {
        if (isViewAccessAble()) {
            ArrayList<MediaItem> m4517a = mediaItemListResult.m4517a();
            updateStateView(0, mediaItemListResult.getCode(), m4517a, mediaItemListResult.m4514b().m8556b());
            if (getState(mediaItemListResult.getCode(), m4517a) == StateView.EnumC2248b.SUCCESS) {
                if (getPager(0).m4669a() > 1) {
                    this.mSongAdapter.m7397b().addAll(m4517a);
                    LogUtils.m8388a(TAG, "updateMediaList SYNC_NET_TEMPORARY_GROUP " + this.mSongAdapter.m7397b());
                    CommandCenter.m4607a().m4606a(new Command(CommandID.APPEND_NET_TEMPORARY_MEDIA_ITEMS, m4517a));
                } else {
                    this.mSongAdapter.m7398a(m4517a);
                }
                if (this.mTab == 0) {
                    this.mSongAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void updatePlayingMediaInfo() {
        notifyDataSetChanged();
    }

    public void updateSearchAlbum(AlbumItemsResult albumItemsResult) {
        if (isViewAccessAble()) {
            updateStateView(1, albumItemsResult.getCode(), albumItemsResult.getDataList(), albumItemsResult.getAllPage());
            if (getState(albumItemsResult.getCode(), albumItemsResult.getDataList()) == StateView.EnumC2248b.SUCCESS) {
                if (getPager(1).m4669a() > 1) {
                    this.mAlbumAdapter.m7425a().addAll(albumItemsResult.getDataList());
                } else {
                    this.mAlbumAdapter.m7423a(albumItemsResult.getDataList());
                }
                if (1 == this.mTab) {
                    this.mAlbumAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void playMediaChanged() {
        this.mSongAdapter.m7395c(Preferences.m2858m());
        this.mSongAdapter.m7396b(Preferences.m2854n());
        if (StringUtils.m8344a(getGroupID(), this.mSongAdapter.m7410a())) {
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected SingerHeader initHeaderSingerView() {
        this.mSingerHeader = new SingerHeader(getActivity(), this.mSingerName, this.mSingerData == null ? "" : ArtistUtils.m8310a(this.mSingerData.getId()));
        return this.mSingerHeader;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    public void updateFavoriteChanged() {
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mSingerHeader != null) {
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected List<TabPagerAdapter.C1004a> onBuildTabBinders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TabPagerAdapter.C1004a(0L, R.string.singer_tab_songs, 0));
        arrayList.add(new TabPagerAdapter.C1004a(1L, R.string.singer_tab_albums, 0));
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected BaseAdapter getAdapter(int i) {
        if (i == 0) {
            return this.mSongAdapter;
        }
        return this.mAlbumAdapter;
    }

    private void requestSongs(int i) {
        LogUtils.m8388a(TAG, "requestDataList mSingerName=" + this.mSingerName + ", page=" + i);
        CommandCenter.m4607a().m4606a(new Command(CommandID.GET_SINGER_SONG_LIST, this.mSingerName, Integer.valueOf(i)));
    }

    private void requestAlbums(String str, int i, int i2) {
        LogUtils.m8388a(TAG, "search album, word: " + str + ",page: " + i + ",pageSize: " + i2);
        CommandCenter.m4607a().m4606a(new Command(CommandID.START_SEARCH_ALBUM, str, Integer.valueOf(i), Integer.valueOf(i2), ""));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected void onItemClick(int i, AdapterView<?> adapterView, View view, int i2, long j) {
        if (i == 0) {
            onSongItemClick(adapterView, view, i2, j);
        } else {
            onAlbumItemClick(adapterView, view, i2, j);
        }
    }

    private void onSongItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListViewUtils.m8264a(this.mListView);
        this.mSongAdapter.m7408a(i, onListStatistic());
        Preferences.m2828t(OnlinePlayingGroupUtils.m6914a(this.mSingerData));
        MediaItem item = this.mSongAdapter.getItem(i);
        if (this.mSingerData != null && item != null) {
            MusicLibraryStatistic.m5068a(this.mSingerData.getId(), this.mSingerData.getName());
            new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), this.mSingerData.getName(), String.valueOf(SPage.PAGE_NONE.getValue())).append("song_id", item.getSongID()).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mSingerData.getId())).append("position", Integer.valueOf(i + 1)).post();
        }
    }

    protected boolean onListStatistic() {
        return false;
    }

    private void onAlbumItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AlbumItem item = this.mAlbumAdapter.getItem(i);
        if (item != null) {
            SingerAlbumDetailFragmentNew singerAlbumDetailFragmentNew = new SingerAlbumDetailFragmentNew(item);
            singerAlbumDetailFragmentNew.setArguments(getArguments());
            launchFragment(singerAlbumDetailFragmentNew);
            if (this.mSingerData != null) {
                new SUserEvent("PAGE_CLICK", SAction.ACTION_SINGER_NAME_ALBUM.getValue(), this.mSingerData.getName(), item.getName()).append("song_album_id", Long.valueOf(item.getId())).append("position", Integer.valueOf(i + 1)).post();
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected boolean onItemLongClick(int i, AdapterView<?> adapterView, View view, int i2, long j) {
        if (i == 0) {
            onSongItemLongClick(adapterView, view, i2, j);
            return true;
        }
        return true;
    }

    private void onSongItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        OnlineMediaStatistic.m5053a(i + 1);
        this.mSongAdapter.m7401a(this.mSongAdapter.getItem(i));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected void onRequestData(int i, int i2) {
        if (i == 0) {
            requestSongs(i2);
        } else {
            requestAlbums(this.mSingerName, i2, 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    public void showLastPageFooterText() {
        super.showLastPageFooterText();
        if (this.mTab == 1 && this.mFooterView != null) {
            this.mFooterView.m1875a(getString(R.string.count_of_album, Integer.valueOf(this.mAlbumAdapter.getCount())));
        }
    }
}
