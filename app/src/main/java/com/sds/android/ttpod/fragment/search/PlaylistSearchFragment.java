package com.sds.android.ttpod.fragment.search;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.PlaylistResult;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class PlaylistSearchFragment extends BaseSearchFragment {
    private static final String TAG = "PlaylistSearchFragment";
    protected List<PlaylistResult.PlaylistItem> mPlaylist = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SEARCH_PLAY_LIST_RESULT, ReflectUtils.m8375a(getClass(), "updatePlaylistResult", PlaylistResult.class));
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected void showLastPageFooterText() {
        this.mFooterView.m1875a(getString(R.string.count_of_song_list, Integer.valueOf(this.mPlaylist.size())));
        this.mFooterView.setOnClickListener(null);
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    public View createEmptyView(LayoutInflater layoutInflater) {
        View createEmptyView = super.createEmptyView(layoutInflater);
        ((TextView) createEmptyView.findViewById(R.id.textview_load_failed)).setText(R.string.song_list_search_nodata);
        ((IconTextView) createEmptyView.findViewById(R.id.icon_no_data)).setText(R.string.icon_search_result_no_song_list);
        return createEmptyView;
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected int getSize() {
        return this.mPlaylist.size();
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected BaseAdapter getAdapter() {
        return new C1721a();
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected void search(String str, int i, int i2) {
        this.mWord = str;
        LogUtils.debug(TAG, "search playlist, word: " + str + ",page: " + i + ",pageSize: " + i2 + ",mUserInput: " + this.mUserInput);
        CommandCenter.getInstance().execute(new Command(CommandID.START_SEARCH_PLAY_LIST, str, Integer.valueOf(i), Integer.valueOf(i2), this.mUserInput));
    }

    public void updatePlaylistResult(PlaylistResult playlistResult) {
        if (isAdded()) {
            int code = playlistResult.getCode();
            if (code != 1) {
                onLoadNextPageError();
            } else {
                if (playlistResult.getDataList().size() == 0) {
                    this.mStateView.setState(StateView.EnumC2248b.NO_DATA);
                } else {
                    int pages = playlistResult.getPages();
                    this.mPager.m4665b(pages);
                    if (this.mPager.m4669a() > 1) {
                        this.mPlaylist.addAll(playlistResult.getDataList());
                        this.mAdapter.notifyDataSetChanged();
                        this.mFooterView.m1873c();
                    } else {
                        this.mPlaylist = playlistResult.getDataList();
                        if (pages == 1) {
                            showLastPageFooterText();
                        }
                        this.mAdapter.notifyDataSetChanged();
                    }
                    this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            }
            this.mIsLoading = false;
            statisticPlaylist(code);
            if (this.mOnDataCountChangeListener != null && !this.mIsErrorNotFirstPage) {
                this.mOnDataCountChangeListener.m5717a(playlistResult.getCount());
            }
        }
    }

    protected void statisticPlaylist(int i) {
        //SearchStatistic.m4947a(Integer.valueOf(i));
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected void performOnItemClick(int i) {
        PlaylistResult.PlaylistItem playlistItem = this.mPlaylist.get(i);
        if (playlistItem.getQuanId() != 0) {
            launchFragment(SubPostDetailFragment.createById(playlistItem.getQuanId(), "search-playlist"));
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SEARCH_SONG_LIST_ITEM.getValue(), SPage.PAGE_SEARCH_SONG_LIST.getValue(), SPage.PAGE_SEARCH_SONG_LIST_DETAIL.getValue()).append("title", playlistItem.getTitle()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(playlistItem.getQuanId())).append(OnlineSearchEntryActivity.KEY_THIRD_ONLINE_SEARCH_KEYWORD, this.mWord).append("position", Integer.valueOf(i + 1)).post();
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.search.PlaylistSearchFragment$a */
    /* loaded from: classes.dex */
    private class C1721a extends BaseAdapter {
        private C1721a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (PlaylistSearchFragment.this.mPlaylist == null) {
                return 0;
            }
            return PlaylistSearchFragment.this.mPlaylist.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return PlaylistSearchFragment.this.mPlaylist.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(PlaylistSearchFragment.this.getActivity(), R.layout.album_list_item, null);
                C1722b c1722b = new C1722b(view);
                ThemeManager.m3269a(c1722b.f5479c, ThemeElement.SONG_LIST_ITEM_TEXT);
                ThemeManager.m3269a(c1722b.f5480d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
                ThemeManager.m3269a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
                view.setTag(c1722b);
            }
            C1722b c1722b2 = (C1722b) view.getTag();
            PlaylistResult.PlaylistItem playlistItem = PlaylistSearchFragment.this.mPlaylist.get(i);
            ImageCacheUtils.m4752a(c1722b2.f5478b, playlistItem.getPicUrl(), DisplayUtils.m7229a(50), DisplayUtils.m7229a(50), (int) R.drawable.img_album_list_item_cover_default);
            c1722b2.f5479c.setText(Html.fromHtml(playlistItem.getTitle()));
            c1722b2.f5480d.setText(PlaylistSearchFragment.this.getString(R.string.search_song_unit, Integer.valueOf(playlistItem.getSongListSize())));
            c1722b2.f5481e.setVisibility(View.VISIBLE);
            return view;
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.search.PlaylistSearchFragment$b */
    /* loaded from: classes.dex */
    private class C1722b {

        /* renamed from: b */
        private ImageView f5478b;

        /* renamed from: c */
        private TextView f5479c;

        /* renamed from: d */
        private TextView f5480d;

        /* renamed from: e */
        private ImageView f5481e;

        public C1722b(View view) {
            this.f5478b = (ImageView) view.findViewById(R.id.image_album_cover);
            this.f5479c = (TextView) view.findViewById(R.id.title_view);
            this.f5480d = (TextView) view.findViewById(R.id.subtitle_view);
            this.f5481e = (ImageView) view.findViewById(R.id.iv_right_arrow);
        }
    }
}
