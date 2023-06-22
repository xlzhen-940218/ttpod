package com.sds.android.ttpod.fragment.main.findsong;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sds.android.cloudapi.ttpod.data.RadioCategory;
import com.sds.android.cloudapi.ttpod.data.RadioCategoryChannel;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter;
import com.sds.android.ttpod.adapter.GridListAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.main.findsong.base.DoubleItemGridSectionListFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.MusicLibraryStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.OnlineMediaStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.online.MovementImage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class RadioCategoryFragment extends DoubleItemGridSectionListFragment<RadioCategoryChannel> {
    private static final int ALPHA = 180;
    private static final int CHANNEL_CHANGE = 4;
    private static final int CHANNEL_IDLE = 1;
    private static final int CHANNEL_PAUSE = 3;
    private static final int CHANNEL_PLAY = 2;
    private static final int NONE = -1;
    private static final int SPEED = 1;
    private static final String TAG = "RadioCategoryFragment";
    private int mActiveChannelId;
    private String mActiveChannelTitle;
    private Bitmap mArtistBitmap;
    private int mCurrentChannelState;
    private boolean mNetMusicListNeedSynced;
    private MediaItem mPlayingListLastMediaItem;
    private RadioCategoryChannel mRadioCategoryChannel;
    private String mTitle;

    public RadioCategoryFragment(String str) {
        super(CommandID.GET_RADIO_CATEGORY_LIST, null, null);
        this.mNetMusicListNeedSynced = true;
        this.mActiveChannelId = -1;
        this.mActiveChannelTitle = "";
        this.mCurrentChannelState = 1;
        setAdapter(new C1550a());
        this.mTitle = str;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(this.mTitle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mRadioCategoryChannel = (RadioCategoryChannel) arguments.getSerializable("data");
        }
        this.mActiveChannelTitle = Preferences.m2926bc();
        if (!StringUtils.m8346a(this.mActiveChannelTitle) && OnlinePlayingGroupUtils.m6909b(this.mActiveChannelTitle) && SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING) {
            this.mCurrentChannelState = 2;
        }
        MediaItem m3225N = Cache.m3218a().m3225N();
        if (m3225N != null && !m3225N.isNull()) {
            String m3014a = Preferences.m3014a(m3225N);
            LogUtils.m8388a(TAG, "getCurrentArtistBitmapPath = " + m3014a);
            if (!StringUtils.m8346a(m3014a)) {
                this.mArtistBitmap = new BitmapUtils().m8446a(m3014a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.DoubleItemGridSectionListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_RADIO_CHANNEL_MUSIC_LIST, ReflectUtils.m8375a(cls, "updateRadioChannelMusicList", ArrayList.class));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.m8375a(cls, "updatePlayMediaItemInfo", new Class[0]));
        map.put(CommandID.UPDATE_SEARCH_PICTURE_STATE, ReflectUtils.m8375a(cls, "updateSearchPictureState", SearchStatus.class, List.class, String.class, Bitmap.class));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.DoubleItemGridSectionListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.DoubleItemGridSectionListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mRadioCategoryChannel != null) {
            performItemClick(this.mRadioCategoryChannel);
        }
    }

    public void updateSearchPictureState(SearchStatus searchStatus, List<ResultData> list, String str, Bitmap bitmap) {
        if (StringUtils.m8344a(Cache.m3218a().m3225N().getID(), str)) {
            switch (searchStatus) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    setArtistBitmap(bitmap);
                    return;
                default:
                    setArtistBitmap(null);
                    return;
            }
        }
    }

    private void setArtistBitmap(Bitmap bitmap) {
        if (isViewAccessAble() && this.mAdapter != null) {
            this.mArtistBitmap = bitmap;
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.DoubleItemGridSectionListFragment
    protected String onLoadTitleText() {
        return this.mTitle;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.DoubleItemGridSectionListFragment
    protected ArrayList<FindSongGridSectionListAdapter.C0963a<RadioCategoryChannel>> convertDataList(ArrayList arrayList) {
        int size = arrayList.size();
        ArrayList<FindSongGridSectionListAdapter.C0963a<RadioCategoryChannel>> arrayList2 = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            RadioCategory radioCategory = (RadioCategory) arrayList.get(i);
            arrayList2.add(new FindSongGridSectionListAdapter.C0963a<>(radioCategory.getRadioCategoryName(), 0, 0L, radioCategory.getRadioCategoryChannels()));
        }
        return arrayList2;
    }

    protected void requestRadioMusicList(int i) {
        LogUtils.m8388a(TAG, "RadioCategoryFragment.requestRadioMusicList---id: " + i);
        CommandCenter.m4607a().m4596b(new Command(CommandID.GET_RADIO_CHANNEL_MUSIC_LIST, "111", Integer.valueOf(i)));
    }

    public void updateRadioChannelMusicList(ArrayList<MediaItem> arrayList) {
        if (isViewAccessAble()) {
            if (arrayList == null || arrayList.size() == 0) {
                PopupsUtils.m6760a((int) R.string.network_unavailable);
                return;
            }
            LogUtils.m8388a(TAG, "RadioCategoryFragment.updateRadioChannelMusicList---musicList.size: " + arrayList.size() + " mNetMusicListNeedSynced: " + this.mNetMusicListNeedSynced);
            this.mPlayingListLastMediaItem = arrayList.get(arrayList.size() - 1);
            if (this.mNetMusicListNeedSynced) {
                CommandCenter.m4607a().m4596b(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, arrayList, this.mActiveChannelTitle));
                CommandCenter.m4607a().m4596b(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, arrayList.get(0)));
                this.mNetMusicListNeedSynced = false;
                return;
            }
            CommandCenter.m4607a().m4596b(new Command(CommandID.APPEND_NET_TEMPORARY_MEDIA_ITEMS, arrayList));
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        LogUtils.m8388a(TAG, "RadioCategoryFragment.updatePlayStatus---status: " + playStatus);
        if (isViewAccessAble()) {
            switch (playStatus) {
                case STATUS_PLAYING:
                    this.mCurrentChannelState = 2;
                    this.mActiveChannelTitle = Preferences.m2926bc();
                    this.mAdapter.notifyDataSetChanged();
                    return;
                case STATUS_PAUSED:
                    this.mCurrentChannelState = 3;
                    this.mActiveChannelTitle = "";
                    this.mAdapter.notifyDataSetChanged();
                    return;
                case STATUS_STOPPED:
                case STATUS_ERROR:
                    this.mActiveChannelTitle = "";
                    this.mAdapter.notifyDataSetChanged();
                    this.mCurrentChannelState = 1;
                    return;
                default:
                    return;
            }
        }
    }

    public void updatePlayMediaItemInfo() {
        if (isViewAccessAble() && Cache.m3218a().m3225N().equals(this.mPlayingListLastMediaItem)) {
            requestRadioMusicList(this.mActiveChannelId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performItemClick(RadioCategoryChannel radioCategoryChannel) {
        if (radioCategoryChannel != null) {
            isPlayingItem(radioCategoryChannel);
            int categoryChannelId = radioCategoryChannel.getCategoryChannelId();
            String categoryChannelName = radioCategoryChannel.getCategoryChannelName();
            String groupName = getGroupName(radioCategoryChannel);
            if (!StringUtils.m8344a(this.mActiveChannelTitle, groupName)) {
                this.mActiveChannelId = categoryChannelId;
                this.mActiveChannelTitle = groupName;
                this.mCurrentChannelState = 4;
                this.mArtistBitmap = null;
                this.mAdapter.notifyDataSetChanged();
            }
            transferToState(this.mCurrentChannelState);
            MusicLibraryStatistic.m5058d(categoryChannelId, categoryChannelName);
            new SUserEvent("PAGE_CLICK", SAction.ACTION_NONE.getValue(), this.mTitle, String.valueOf(SPage.PAGE_NONE.getValue())).append("channel_id", Integer.valueOf(categoryChannelId)).append("channel_name", categoryChannelName).append(SubMediaListFragment.KEY_GROUP_NAME, groupName).post();
            OnlineMediaStatistic.m5045a("library-music-radio_" + categoryChannelName + "_" + MusicLibraryStatistic.m5069a());
            OnlineMediaStatistic.m5054a();
        }
    }

    private String getGroupName(RadioCategoryChannel radioCategoryChannel) {
        return "电台_" + radioCategoryChannel.getCategoryChannelName();
    }

    public void updateDataList(ArrayList arrayList) {
        LogUtils.m8388a(TAG, "RadioCategoryFragment.updateDataList---> ");
        if (arrayList != null) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.addAll(((RadioCategory) it.next()).getRadioCategoryChannels());
            }
            super.updateData(arrayList2);
            return;
        }
        super.updateData(null);
    }

    private void transferToState(int i) {
        LogUtils.m8388a(TAG, "RadioCategoryFragment.transferToState--->state: " + i);
        switch (i) {
            case 1:
            case 4:
                requestRadioMusicList(this.mActiveChannelId);
                this.mNetMusicListNeedSynced = true;
                return;
            case 2:
                CommandCenter.m4607a().m4596b(new Command(CommandID.PAUSE, new Object[0]));
                return;
            case 3:
                CommandCenter.m4607a().m4596b(new Command(SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PAUSED ? CommandID.RESUME : CommandID.START, new Object[0]));
                return;
            default:
                return;
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.RadioCategoryFragment$a */
    /* loaded from: classes.dex */
    private class C1550a extends FindSongGridSectionListAdapter<RadioCategoryChannel> {
        private C1550a() {
        }

        @Override // com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter, com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: b */
        protected View mo5519b(ViewGroup viewGroup) {
            View inflate = this.f3241a.inflate(R.layout.radio_category_section_subitem_view, viewGroup, false);
            inflate.setTag(new GridListAdapter.C0966a[]{new GridListAdapter.C0966a(inflate.findViewById(R.id.song_item1)), new GridListAdapter.C0966a(inflate.findViewById(R.id.song_item2))});
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public String mo5603a(RadioCategoryChannel radioCategoryChannel) {
            return RadioCategoryFragment.this.getString(R.string.radio_mhz, radioCategoryChannel.getCategoryChannelName());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter
        /* renamed from: b  reason: avoid collision after fix types in other method */
        public String mo5600b(RadioCategoryChannel radioCategoryChannel) {
            return RadioCategoryFragment.this.getString(R.string.count_of_media, Integer.valueOf(radioCategoryChannel.getCount()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter
        /* renamed from: c  reason: avoid collision after fix types in other method */
        public void mo5598c(RadioCategoryChannel radioCategoryChannel) {
            RadioCategoryFragment.this.performItemClick(radioCategoryChannel);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter
        /* renamed from: a */
        public void mo5601b(GridListAdapter.C0966a c0966a, RadioCategoryChannel radioCategoryChannel) {
            Animation m7596e = c0966a.m7596e();
            MovementImage movementImage = (MovementImage) c0966a.m7598d();
            ImageView m7595f = c0966a.m7595f();
            if ((RadioCategoryFragment.this.mCurrentChannelState == 2 || RadioCategoryFragment.this.mCurrentChannelState == 4) && RadioCategoryFragment.this.isPlayingItem(radioCategoryChannel)) {
                m7595f.setBackgroundResource(R.drawable.img_online_radio_play);
                m7595f.setVisibility(View.VISIBLE);
                m7596e.setVisibility(View.VISIBLE);
                m7596e.setAnimationResource(R.drawable.xml_imageview_radio_play_animation);
                m7596e.m3504a();
                if (!ThemeManager.m3269a(c0966a.m7594g(), ThemeElement.TILE_MASK)) {
                    c0966a.m7594g().setBackgroundResource(R.drawable.color_background_radio_playing);
                }
                c0966a.m7594g().setVisibility(View.VISIBLE);
                movementImage.setVisibility(View.VISIBLE);
                if (RadioCategoryFragment.this.mArtistBitmap != null) {
                    movementImage.setMoveMentBitmap(RadioCategoryFragment.this.mArtistBitmap);
                    movementImage.setMoveSpeed(DisplayUtils.m7229a(1));
                    movementImage.setAlpha(RadioCategoryFragment.ALPHA);
                    movementImage.m1216a(movementImage.getWidth(), movementImage.getHeight());
                    movementImage.m1217a();
                    return;
                }
                return;
            }
            m7595f.setVisibility(View.INVISIBLE);
            m7596e.setVisibility(View.INVISIBLE);
            m7596e.m3499b();
            ThemeManager.m3269a(c0966a.m7594g(), ThemeElement.TILE_BACKGROUND);
            movementImage.setMoveMentBitmap(null);
            movementImage.m1211b();
            movementImage.setVisibility(View.INVISIBLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlayingItem(RadioCategoryChannel radioCategoryChannel) {
        return StringUtils.m8344a(this.mActiveChannelTitle, OnlinePlayingGroupUtils.m6915a(radioCategoryChannel));
    }
}
