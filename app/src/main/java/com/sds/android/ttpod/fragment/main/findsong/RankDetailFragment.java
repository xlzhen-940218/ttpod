package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p073e.OnlinePlayStatus;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.fragment.main.ResultHelper;
import com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.MediaItemListResult;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.ViewUtils;
import com.sds.android.ttpod.framework.p106a.p107a.RankStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.StatisticUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.RectangleImageView;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class RankDetailFragment extends ImageHeaderMusicListFragment {
    private MusicRank mMusicRank;
    private OnlinePlayStatus mOnlinePlayStatus;
    private C1561b mRankDetailHeader = new C1560a();
    private MediaItemListResult mResult;

    public RankDetailFragment(MusicRank musicRank) {
        this.mMusicRank = musicRank;
        setPlayingGroupName(OnlinePlayingGroupUtils.m6917a(musicRank));
    }

    public RankDetailFragment(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("rank id can NOT be negative: Rank id = " + i);
        }
        this.mMusicRank = new MusicRank();
        this.mMusicRank.setId(i);
        CommandCenter.m4607a().m4606a(new Command(CommandID.GET_MUSIC_RANKS, getRequestId()));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_RANK_MUSIC_LIST, ReflectUtils.m8375a(getClass(), "updateRankDetailResult", MediaItemListResult.class, String.class));
        map.put(CommandID.UPDATE_MUSIC_RANKS, ReflectUtils.m8375a(getClass(), "updateMusicRanks", MusicRanksResult.class, String.class));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void updatePlayStatus(PlayStatus playStatus) {
        if (playStatus != null) {
            this.mOnlinePlayStatus = OnlinePlayStatus.from(playStatus);
            this.mRankDetailHeader.mo5585a(this.mOnlinePlayStatus == OnlinePlayStatus.PLAYING && isPlayingItem());
        }
    }

    public void updateMusicRanks(MusicRanksResult musicRanksResult, String str) {
        if (musicRanksResult.isSuccess() && str != null && str.equals(getRequestId())) {
            Iterator<MusicRank> it = musicRanksResult.getDataList().iterator();
            while (it.hasNext()) {
                MusicRank next = it.next();
                if (next.getId() == this.mMusicRank.getId()) {
                    this.mMusicRank = next;
                    setupListHeader();
                    setPlayingGroupName(OnlinePlayingGroupUtils.m6917a(this.mMusicRank));
                    return;
                }
            }
        }
    }

    public void updateRankDetailResult(MediaItemListResult mediaItemListResult, String str) {
        if (str != null && str.equals(getRequestId())) {
            this.mResult = mediaItemListResult;
            ResultHelper.m5665a(this, mediaItemListResult, new ResultHelper.InterfaceC1510a<MediaItemListResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment.1
                @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo5564a(MediaItemListResult mediaItemListResult2) {
                    RankDetailFragment.this.updateRankDetailData(mediaItemListResult2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRankDetailData(MediaItemListResult mediaItemListResult) {
        if (mediaItemListResult != null) {
            updateData(mediaItemListResult.m4517a(), Integer.valueOf(mediaItemListResult.m4514b().m8556b()));
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updateRankDetailData(this.mResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void doStatisticMediaClick(MediaItem mediaItem) {
        super.doStatisticMediaClick(mediaItem);
        Preferences.m2828t(OnlinePlayingGroupUtils.m6917a(this.mMusicRank));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected void doStatiticFavorite(MediaItem mediaItem, boolean z) {
        int indexOf;
        if (this.mOnlineMediaListFragment != null) {
            List<MediaItem> mediaItemList = this.mOnlineMediaListFragment.getMediaItemList();
            if (ListUtils.m4717b(mediaItemList) && (indexOf = mediaItemList.indexOf(mediaItem)) > -1) {
                RankStatistic.m4963a(this.mMusicRank.getTitle(), z, indexOf + 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void onDownloadButtonClick() {
        super.onDownloadButtonClick();
        if (getActivity() != null) {
            new DownloadMenuHandler(getActivity()).m6926a(this.mediaItemList);
        }
        StatisticUtils.m4917a(295, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mRankDetailHeader.mo5588a();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mRankDetailHeader.mo5584b();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected void setupListHeader() {
        if (this.mRankDetailHeader instanceof C1560a) {
            this.mRankDetailHeader = new C1561b(getActivity());
            this.mRankDetailHeader.mo5587a(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (RankDetailFragment.this.isPlayingItem()) {
                        RankDetailFragment.this.mRankDetailHeader.mo5583b(RankDetailFragment.this.mOnlinePlayStatus == OnlinePlayStatus.PLAYING);
                    }
                    RankDetailFragment.this.togglePlayMedia(0L);
                    RankDetailFragment.this.onPlayButtonClick();
                }
            });
            this.mOnlineMediaListFragment.getListView().addHeaderView(this.mRankDetailHeader.m5582c());
        }
        setTitle(this.mMusicRank.getTitle());
        this.mRankDetailHeader.mo5586a(this.mMusicRank);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void requestDataList(int i) {
        super.requestDataList(i);
        CommandCenter.m4607a().m4606a(new Command(CommandID.GET_RANK_MUSIC_LIST, Integer.valueOf(this.mMusicRank.getId()), Integer.valueOf(i), getRequestId()));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected String onLoadTitleText() {
        return this.mMusicRank.getTitle();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.InterfaceC1667b
    public void doStatistic(MediaItem mediaItem, int i) {
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), SPage.PAGE_RANK_DETAIL.getValue(), 0).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mMusicRank.getId())).append("song_list_name", this.mMusicRank.getTitle()).append("position", Integer.valueOf(i + 1)).append("song_id", mediaItem.getSongID()).post();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPlayButtonClick() {
        Preferences.m2828t(OnlinePlayingGroupUtils.m6917a(this.mMusicRank));
        RankStatistic.m4967a(this.mMusicRank.getId(), this.mMusicRank.getTitle());
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_RANK_DETAIL_PLAY_ALL.getValue(), SPage.PAGE_RANK_DETAIL.getValue(), 0).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mMusicRank.getId())).append("title", this.mMusicRank.getTitle()).post();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected String onLoadStatisticModule() {
        return RankStatistic.m4962b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment$b */
    /* loaded from: classes.dex */
    public static class C1561b {

        /* renamed from: a */
        protected ImageView f5227a;

        /* renamed from: b */
        protected TextView f5228b;

        /* renamed from: c */
        protected TextView f5229c;

        /* renamed from: d */
        private ImageView f5230d;

        /* renamed from: e */
        private View f5231e;

        public C1561b() {
        }

        public C1561b(Context context) {
            this.f5231e = View.inflate(context, R.layout.find_song_list_image_header, null);
            this.f5228b = (TextView) this.f5231e.findViewById(R.id.find_song_list_image_header_date);
            this.f5227a = (ImageView) this.f5231e.findViewById(R.id.find_song_list_image_header_pic);
            try {
                this.f5227a.setImageResource(R.drawable.img_background_ttpod_music_large_logo);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
            }
            ((RectangleImageView) this.f5227a).setAspectRatio(1.88f);
            this.f5230d = (ImageView) this.f5231e.findViewById(R.id.find_song_list_image_header_play);
            this.f5229c = (TextView) this.f5231e.findViewById(R.id.find_song_list_image_header_summary);
        }

        /* renamed from: c */
        public View m5582c() {
            return this.f5231e;
        }

        /* renamed from: a */
        public void mo5586a(MusicRank musicRank) {
            this.f5229c.setText(musicRank.getDetail());
            this.f5228b.setText(musicRank.getTime());
            ImageCacheUtils.m4752a(this.f5227a, StringUtils.m8346a(musicRank.getBigPicUrl()) ? musicRank.getPicUrl() : musicRank.getBigPicUrl(), DisplayUtils.m7225c(), DisplayUtils.m7224d() / 2, (int) R.drawable.img_background_ttpod_music_large_logo);
        }

        /* renamed from: a */
        public void mo5585a(boolean z) {
            this.f5230d.clearAnimation();
            this.f5230d.setEnabled(true);
            this.f5230d.setSelected(z);
        }

        /* renamed from: a */
        public void mo5587a(View.OnClickListener onClickListener) {
            this.f5230d.setOnClickListener(onClickListener);
        }

        /* renamed from: b */
        public void mo5583b(boolean z) {
            this.f5230d.clearAnimation();
            this.f5230d.setSelected(z);
        }

        /* renamed from: a */
        public void mo5588a() {
            ThemeManager.m3269a(this.f5231e, ThemeElement.TILE_BACKGROUND);
            ThemeManager.m3269a(this.f5229c, ThemeElement.SONG_LIST_ITEM_TEXT);
        }

        /* renamed from: b */
        public void mo5584b() {
            ViewUtils.m4639a(this.f5231e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment$a */
    /* loaded from: classes.dex */
    public static final class C1560a extends C1561b {
        private C1560a() {
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment.C1561b
        /* renamed from: a */
        public void mo5586a(MusicRank musicRank) {
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment.C1561b
        /* renamed from: a */
        public void mo5585a(boolean z) {
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment.C1561b
        /* renamed from: a */
        public void mo5587a(View.OnClickListener onClickListener) {
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment.C1561b
        /* renamed from: b */
        public void mo5583b(boolean z) {
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment.C1561b
        /* renamed from: a */
        public void mo5588a() {
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.RankDetailFragment.C1561b
        /* renamed from: b */
        public void mo5584b() {
        }
    }

    private String getRequestId() {
        return toString() + this.mMusicRank.getId();
    }
}
