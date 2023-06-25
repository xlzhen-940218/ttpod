package com.sds.android.ttpod.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.fragment.main.SearchResultFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AlbumSearchFragment extends BaseSearchFragment {
    private static final String TAG = "AlbumSearchFragment";
    protected List<AlbumItem> mAlbumItemList = new ArrayList();

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected void search(String str, int i, int i2) {
        this.mWord = str;
        LogUtils.debug(TAG, "search album, word: " + str + ",page: " + i + ",pageSize: " + i2 + ",mUserInput: " + this.mUserInput);
        CommandCenter.getInstance().m4606a(new Command(CommandID.START_SEARCH_ALBUM, str, Integer.valueOf(i), Integer.valueOf(i2), this.mUserInput));
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    public View createEmptyView(LayoutInflater layoutInflater) {
        View createEmptyView = super.createEmptyView(layoutInflater);
        ((TextView) createEmptyView.findViewById(R.id.textview_load_failed)).setText(R.string.album_search_nodata);
        ((IconTextView) createEmptyView.findViewById(R.id.icon_no_data)).setText(R.string.icon_search_result_no_album);
        return createEmptyView;
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected BaseAdapter getAdapter() {
        return new C1714a();
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected int getSize() {
        return this.mAlbumItemList.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SEARCH_ALBUM_FINISHED, ReflectUtils.m8375a(getClass(), "updateSearchAlbum", AlbumItemsResult.class));
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected void showLastPageFooterText() {
        this.mFooterView.m1875a(getString(R.string.count_of_album, Integer.valueOf(this.mAlbumItemList.size())));
        this.mFooterView.setOnClickListener(null);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mStateView = null;
    }

    public void updateSearchAlbum(AlbumItemsResult albumItemsResult) {
        if (isAdded()) {
            int code = albumItemsResult.getCode();
            if (code == 1) {
                if (albumItemsResult.getDataList().size() == 0) {
                    this.mStateView.setState(StateView.EnumC2248b.NO_DATA);
                } else {
                    int allPage = albumItemsResult.getAllPage();
                    this.mPager.m4665b(allPage);
                    if (this.mPager.m4669a() > 1) {
                        this.mAlbumItemList.addAll(albumItemsResult.getDataList());
                        this.mAdapter.notifyDataSetChanged();
                        this.mFooterView.m1873c();
                    } else {
                        this.mAlbumItemList = albumItemsResult.getDataList();
                        if (allPage == 1) {
                            showLastPageFooterText();
                        }
                        this.mListView.setAdapter((ListAdapter) this.mAdapter);
                    }
                    this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            } else {
                onLoadNextPageError();
            }
            this.mIsLoading = false;
            statisticAlbum(code);
            if (this.mOnDataCountChangeListener != null && !this.mIsErrorNotFirstPage) {
                this.mOnDataCountChangeListener.m5717a(albumItemsResult.getCount());
            }
        }
    }

    protected void statisticAlbum(int i) {
        //SearchStatistic.m4947a(Integer.valueOf(i));
    }

    public void searchAlbum(String str) {
        if (!StringUtils.isEmpty(str)) {
            this.mPager = new Pager();
            this.mPager.m4665b(VIPPolicy.Entry.MAX_LIMIT);
            this.mStateView.setState(StateView.EnumC2248b.LOADING);
            search(str, 1, 10);
        }
    }

    @Override // com.sds.android.ttpod.fragment.search.BaseSearchFragment
    protected void performOnItemClick(int i) {
        AlbumDetailFragment albumDetailFragment = new AlbumDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SearchResultFragment.KEY_SEARCH_WORD, this.mAlbumItemList.get(i));
        albumDetailFragment.setArguments(bundle);
        launchFragment(albumDetailFragment);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SEARCH_ALBUM_ITEM.getValue(), SPage.PAGE_SEARCH_ALBUM.getValue(), SPage.PAGE_SEARCH_ALBUM_DETAIL.getValue()).append("title", this.mAlbumItemList.get(i).getName()).append("song_album_id", Long.valueOf(this.mAlbumItemList.get(i).getId())).append(OnlineSearchEntryActivity.KEY_THIRD_ONLINE_SEARCH_KEYWORD, this.mWord).append("position", Integer.valueOf(i + 1)).post();
    }

    /* renamed from: com.sds.android.ttpod.fragment.search.AlbumSearchFragment$a */
    /* loaded from: classes.dex */
    private class C1714a extends BaseAdapter {
        private C1714a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (AlbumSearchFragment.this.mAlbumItemList == null) {
                return 0;
            }
            return AlbumSearchFragment.this.mAlbumItemList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(AlbumSearchFragment.this.getActivity(), R.layout.album_list_item, null);
                view.setTag(new C1715b(view));
            }
            C1715b c1715b = (C1715b) view.getTag();
            AlbumItem albumItem = AlbumSearchFragment.this.mAlbumItemList.get(i);
            ImageCacheUtils.m4752a(c1715b.f5469b, albumItem.getPic200(), DisplayUtils.m7229a(50), DisplayUtils.m7229a(50), (int) R.drawable.img_album_list_item_cover_default);
            c1715b.f5470c.setText(albumItem.getName());
            c1715b.f5471d.setText(albumItem.getSingerName() + " " + albumItem.getPublishTime());
            ThemeManager.m3269a(c1715b.f5470c, ThemeElement.SONG_LIST_ITEM_TEXT);
            ThemeManager.m3269a(c1715b.f5471d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            ThemeManager.m3269a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
            return view;
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.search.AlbumSearchFragment$b */
    /* loaded from: classes.dex */
    private class C1715b {

        /* renamed from: b */
        private ImageView f5469b;

        /* renamed from: c */
        private TextView f5470c;

        /* renamed from: d */
        private TextView f5471d;

        public C1715b(View view) {
            this.f5469b = (ImageView) view.findViewById(R.id.image_album_cover);
            this.f5470c = (TextView) view.findViewById(R.id.title_view);
            this.f5471d = (TextView) view.findViewById(R.id.subtitle_view);
        }
    }
}
