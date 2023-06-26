package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.SingerData;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;

import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p073e.OnlinePlayStatus;
import com.sds.android.ttpod.adapter.p075f.SongListAdapter;
import com.sds.android.ttpod.adapter.p075f.TabPagerAdapter;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ListViewUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SingerAlbumDetailFragmentNew extends SingerTabFragment {
    private static final int DELAY_REQUEST_SINGER_IMAGE = 200;
    private static final int TAB_ALBUM_INTRODUCE = 1;
    private static final int TAB_ALBUM_SONGS = 0;
    private C1611a mAlbumIntroduceAdapter;
    private AlbumItem mAlbumItem;
    private SingerAlbumIntroduceView mIntroduceAlbumView;
    private OnlinePlayStatus mPlayStatus;
    private ImageView mPlayView;
    private List<MediaItem> mPlayingList = new ArrayList();
    private long mPlayingSongId;
    private SingerData mSingerData;
    private SongListAdapter mSongAdapter;

    public SingerAlbumDetailFragmentNew(AlbumItem albumItem) {
        this.mAlbumItem = albumItem;
        setGroupID(MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mSingerData = (SingerData) arguments.getSerializable("key_data");
        }
        this.mSongAdapter = new SongListAdapter(getActivity(), getGroupID());
        this.mSongAdapter.setModule(getModule());
        this.mSongAdapter.setOrigin(getOrigin());
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mAlbumItem);
        this.mAlbumIntroduceAdapter = new C1611a(arrayList);
        this.mTab = 0;
        if (this.mAlbumItem != null) {
            setPage(this.mAlbumItem.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.m8375a(cls, "updatePlayingMediaInfo", new Class[0]));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "playMediaChanged", new Class[0]));
        map.put(CommandID.UPDATE_FAVORITE_CHANGED, ReflectUtils.m8375a(cls, "updateFavoriteChanged", new Class[0]));
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

    public void updatePlayingMediaInfo() {
        notifyDataSetChanged();
    }

    public void playMediaChanged() {
        updatePlayStatus(PlayStatus.STATUS_PLAYING);
        this.mSongAdapter.setLocalGroupId(Preferences.getLocalGroupId());
        this.mSongAdapter.setMediaId(Preferences.getMediaId());
        if (StringUtils.equals(getGroupID(), this.mSongAdapter.getLocalGroupId())) {
            notifyDataSetChanged();
        }
    }

    public void updateFavoriteChanged() {
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updatePlayStatus(SupportFactory.m2397a(BaseApplication.getApplication()).m2463m());
        setCurrentItem(this.mTab);
        new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerAlbumDetailFragmentNew.1
            @Override // java.lang.Runnable
            public void run() {
                SingerAlbumDetailFragmentNew.this.requestSingerImage();
            }
        }, 200L);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7193a((CharSequence) this.mAlbumItem.getName());
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected SingerHeader initHeaderSingerView() {
        if (this.mAlbumItem != null) {
            SingerHeader singerHeader = new SingerHeader(getActivity(), this.mAlbumItem.getName(), this.mAlbumItem.getPic200());
            singerHeader.m5471a(true);
            initPlayView(singerHeader.m5469c());
            return singerHeader;
        }
        return new SingerHeader(getActivity(), "", null);
    }

    private void initPlayView(ImageView imageView) {
        this.mPlayView = imageView;
        this.mPlayView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerAlbumDetailFragmentNew.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean z = false;
                if (SingerAlbumDetailFragmentNew.this.mSingerData != null) {
                    //MusicLibraryStatistic.m5068a(SingerAlbumDetailFragmentNew.this.mSingerData.getId(), SingerAlbumDetailFragmentNew.this.mSingerData.getName());
                }
                if (SingerAlbumDetailFragmentNew.this.isPlayingItem()) {
                    SingerAlbumDetailFragmentNew.this.mPlayView.clearAnimation();
                    SingerAlbumDetailFragmentNew.this.mPlayView.setEnabled(true);
                    SingerAlbumDetailFragmentNew.this.mPlayView.setSelected(SingerAlbumDetailFragmentNew.this.mPlayStatus == OnlinePlayStatus.PLAYING);
                } else {
                    SingerAlbumDetailFragmentNew.this.mPlayView.setEnabled(false);
                    SingerAlbumDetailFragmentNew.this.mPlayView.startAnimation(AnimationUtils.loadAnimation(SingerAlbumDetailFragmentNew.this.getActivity(), R.anim.unlimited_rotate));
                    z = true;
                }
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_SINGER_ALBUM_MUSIC_PLAY_ALL.getValue(), SingerAlbumDetailFragmentNew.this.mAlbumItem.getName(), String.valueOf(SPage.PAGE_NONE)).append("song_album_id", Long.valueOf(SingerAlbumDetailFragmentNew.this.mAlbumItem.getId())).append("status", Boolean.valueOf(z)).post();
                SingerAlbumDetailFragmentNew.this.togglePlayMedia();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void togglePlayMedia() {
        if (ListUtils.m4717b(this.mPlayingList)) {
            playOnlineMediaGroup();
        } else {
            requestSongList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playOnlineMediaGroup() {
        OnlineMediaUtils.m4679a(this.mPlayingSongId, this.mPlayingList, OnlinePlayingGroupUtils.m6918a(this.mAlbumItem));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlayingItem() {
        return OnlinePlayingGroupUtils.m6911a(Preferences.m2926bc(), this.mAlbumItem);
    }

    private void requestAlbumSongList() {
        OnlineMediaUtils.m4677a(this.mAlbumItem, new RequestCallback<OnlineMediaItemsResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerAlbumDetailFragmentNew.3
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
                if (SingerAlbumDetailFragmentNew.this.isAdded()) {
                    SingerAlbumDetailFragmentNew.this.updateStateView(0, onlineMediaItemsResult.getCode(), arrayList, 1);
                    SingerAlbumDetailFragmentNew.this.mSongAdapter.setMediaItems(arrayList);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OnlineMediaItemsResult onlineMediaItemsResult) {
                SingerAlbumDetailFragmentNew.this.updateStateView(0, onlineMediaItemsResult.getCode(), null, onlineMediaItemsResult.getExtra().m8556b());
            }
        });
    }

    private void requestSongList() {
        OnlineMediaUtils.m4677a(this.mAlbumItem, new RequestCallback<OnlineMediaItemsResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerAlbumDetailFragmentNew.4
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
                if (ListUtils.m4717b(arrayList)) {
                    SingerAlbumDetailFragmentNew.this.mPlayingList = arrayList;
                    SingerAlbumDetailFragmentNew.this.playOnlineMediaGroup();
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OnlineMediaItemsResult onlineMediaItemsResult) {
                if (SingerAlbumDetailFragmentNew.this.isViewAccessAble()) {
                    PopupsUtils.m6760a((int) R.string.network_unavailable);
                }
                SingerAlbumDetailFragmentNew.this.updatePlayStatus(SupportFactory.m2397a(BaseApplication.getApplication()).m2463m());
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mIntroduceAlbumView != null) {
            this.mIntroduceAlbumView.m5474b();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected List<TabPagerAdapter.C1004a> onBuildTabBinders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TabPagerAdapter.C1004a(0L, R.string.singer_tab_album_songs, 0));
        arrayList.add(new TabPagerAdapter.C1004a(1L, R.string.singer_tab_album_introduce, 0));
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected void onRequestData(int i, int i2) {
        if (i == 0) {
            requestAlbumSongList();
        } else {
            updateStateView(1, 1, this.mAlbumIntroduceAdapter.f5321b, 0);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected BaseAdapter getAdapter(int i) {
        this.mListView.removeFooterView(this.mFooterView);
        if (i == 0) {
            this.mListView.addFooterView(this.mFooterView);
            return this.mSongAdapter;
        }
        return this.mAlbumIntroduceAdapter;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment
    protected void onItemClick(int i, AdapterView<?> adapterView, View view, int i2, long j) {
        if (i == 0) {
            onSongItemClick(adapterView, view, i2, j);
        }
    }

    private void onSongItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListViewUtils.m8264a(this.mListView);
        this.mSongAdapter.m7408a(i, false);
        Preferences.m2828t(OnlinePlayingGroupUtils.m6918a(this.mAlbumItem));
        MediaItem item = this.mSongAdapter.getItem(i);
        if (item != null) {
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), this.mAlbumItem.getName(), String.valueOf(SPage.PAGE_NONE)).append("song_id", item.getSongID()).append("song_album_id", Long.valueOf(this.mAlbumItem.getId())).post();
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
        //OnlineMediaStatistic.m5053a(i + 1);
        this.mSongAdapter.download(this.mSongAdapter.getItem(i));
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.singer.SingerAlbumDetailFragmentNew$a */
    /* loaded from: classes.dex */
    private class C1611a extends BaseAdapter {

        /* renamed from: b */
        private ArrayList<AlbumItem> f5321b;

        public C1611a(ArrayList<AlbumItem> arrayList) {
            this.f5321b = arrayList;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.f5321b == null) {
                return 0;
            }
            return this.f5321b.size();
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
                SingerAlbumDetailFragmentNew.this.mIntroduceAlbumView = new SingerAlbumIntroduceView(SingerAlbumDetailFragmentNew.this.getActivity(), this.f5321b.get(i));
                return SingerAlbumDetailFragmentNew.this.mIntroduceAlbumView.m5475a();
            }
            return view;
        }
    }
}
