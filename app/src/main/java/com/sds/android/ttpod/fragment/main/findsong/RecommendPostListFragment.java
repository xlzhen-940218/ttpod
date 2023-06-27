package com.sds.android.ttpod.fragment.main.findsong;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.RecommendPost;
import com.sds.android.cloudapi.ttpod.api.OnlineMediaItemAPI;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.Extra;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.MusiccircleFooter;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.NoHeaderPostListProxy;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class RecommendPostListFragment extends SlidingClosableFragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    public static final int PAGE_SIZE = 10;
    private RecommendPostListAdapter mAdapter;
    private MusiccircleFooter mFooter;
    private EnumC1567a mLoadStatus = EnumC1567a.IDLE;
    private Pager mPager = new Pager();
    private NoHeaderPostListProxy mProxy;

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment$a */
    /* loaded from: classes.dex */
    public enum EnumC1567a {
        IDLE,
        FIRST_LOAD,
        RELOAD,
        NEXT_PAGE
    }

    protected abstract void onLoadData();

    public abstract void playMediaItemStatistic(long j, int i);

    public abstract void startPostDetailStatistic(long j, int i);

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mProxy = new NoHeaderPostListProxy(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendPostListFragment.this.reloadData();
            }
        }, new DragUpdateHelper.InterfaceC2273c() { // from class: com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment.2
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
            public void onStartRefreshEvent() {
                RecommendPostListFragment.this.reloadData();
            }
        });
        View m5426a = this.mProxy.m5426a(layoutInflater, viewGroup);
        initView(layoutInflater, this.mProxy.m5427a());
        return m5426a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reloadData() {
        this.mLoadStatus = EnumC1567a.RELOAD;
        onLoadData();
    }

    private void initView(LayoutInflater layoutInflater, ListView listView) {
        this.mAdapter = onCreateAdapter();
        listView.setAdapter((ListAdapter) this.mAdapter);
        initFooterView(layoutInflater, listView);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (ListViewUtils.m8262b(i, i2, i3) && this.mLoadStatus == EnumC1567a.IDLE && getDataCount() > 0) {
            onRequestNextPage();
        }
    }

    private void initFooterView(LayoutInflater layoutInflater, ListView listView) {
        this.mFooter = new MusiccircleFooter(layoutInflater, new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendPostListFragment.this.onRequestNextPage();
            }
        });
        listView.addFooterView(this.mFooter.m7934a());
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mFooter != null) {
            this.mFooter.onThemeLoaded();
        }
        ThemeManager.m3269a(this.mProxy.m5427a(), ThemeElement.BACKGROUND_MASK);
        StateView m5423b = this.mProxy.m5423b();
        if (m5423b != null) {
            m5423b.onThemeLoaded();
        }
        DragUpdateListView dragUpdateListView = (DragUpdateListView) this.mProxy.m5427a();
        if (dragUpdateListView != null) {
            ColorStateList m3254c = ThemeManager.m3254c(ThemeElement.COMMON_CONTENT_TEXT);
            if (m3254c != null) {
                dragUpdateListView.setLoadingTitleColor(m3254c);
            }
            refreshListViewTheme();
        }
    }

    protected void refreshListViewTheme() {
        if (this.mProxy.m5427a() != null) {
            int childCount = this.mProxy.m5427a().getChildCount();
            for (int i = 0; i < childCount; i++) {
                Object tag = this.mProxy.m5427a().getChildAt(i).getTag();
                if (tag instanceof RecommendPostViewHolder) {
                    this.mAdapter.m5503a((RecommendPostViewHolder) tag);
                }
            }
        }
    }

    protected RecommendPostListAdapter onCreateAdapter() {
        return new RecommendPostListAdapter(getActivity()) { // from class: com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment.4
            @Override // com.sds.android.ttpod.fragment.main.findsong.RecommendPostListAdapter
            /* renamed from: a */
            protected void mo5504a(RecommendPost recommendPost, RecommendPostViewHolder recommendPostViewHolder, int i) {
                RecommendPostListFragment.this.playMediaItemStatistic(recommendPost.getId(), i);
                setData(recommendPost);
                RecommendPostListFragment.this.requestPlayMediaView(recommendPost);
            }
        };
    }

    public List<Long> getPlaySongId(RecommendPost recommendPost) {
        ArrayList arrayList = new ArrayList();
        Iterator<OnlineMediaItem> it = recommendPost.getSongList().iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(it.next().getSongId()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MediaItem> convertMediaList(List<OnlineMediaItem> list) {
        ArrayList arrayList = new ArrayList();
        for (OnlineMediaItem onlineMediaItem : list) {
            arrayList.add(MediaItemUtils.m4716a(onlineMediaItem));
        }
        return arrayList;
    }

    public void requestPlayMediaView(final RecommendPost recommendPost) {
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<List<Long>, List<MediaItem>>(getPlaySongId(recommendPost)) { // from class: com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public List<MediaItem> mo1981a(List<Long> list) {
                return RecommendPostListFragment.this.convertMediaList(OnlineMediaItemAPI.m8867a(list).execute().getDataList());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: b  reason: avoid collision after fix types in other method */
            public void postExecute(List<MediaItem> list) {
                RecommendPostListFragment.this.playMediaList(list, recommendPost.getId());
            }
        });
    }

    public void playMediaList(List<MediaItem> list, long j) {
        if (!list.isEmpty()) {
            OnlineMediaUtils.m4673a(list, OnlinePlayingGroupUtils.m6908c(String.valueOf(j)), j);
            Preferences.m3018a(PlayMode.REPEAT);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        this.mLoadStatus = EnumC1567a.FIRST_LOAD;
        setLoadingState(StateView.EnumC2248b.LOADING);
        onLoadData();
    }

    public void setLoadingState(StateView.EnumC2248b enumC2248b) {
        this.mProxy.m5424a(enumC2248b);
    }

    public void updateDataListView(List<RecommendPost> list) {
        this.mProxy.m5421c();
        if (this.mLoadStatus == EnumC1567a.NEXT_PAGE) {
            this.mAdapter.getDataList().addAll(list);
        } else {
            this.mAdapter.setDataList((List) list);
        }
        this.mAdapter.notifyDataSetChanged();
        this.mFooter.m7932a(list.isEmpty(), 8, list.isEmpty() ? getString(R.string.loading_failed) : getString(R.string.num_loaded_data, Integer.valueOf(this.mAdapter.getCount())));
        this.mLoadStatus = EnumC1567a.IDLE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleResult(List<RecommendPost> list, Extra extra, int i) {
        if (ListUtils.m4718a(list) && EnumC1567a.NEXT_PAGE == getLoadStatus()) {
            updateFooterView(i == -1 ? StateView.EnumC2248b.FAILED : StateView.EnumC2248b.NO_DATA);
        } else if (ListUtils.m4718a(list)) {
            setLoadingState(StateView.EnumC2248b.FAILED);
        } else {
            setLoadingState(StateView.EnumC2248b.SUCCESS);
            handleExtra(extra);
            updateDataListView(list);
        }
    }

    private void updateFooterView(StateView.EnumC2248b enumC2248b) {
        if (StateView.EnumC2248b.LOADING == enumC2248b) {
            this.mFooter.m7932a(false, 0, getString(R.string.loading));
        } else if (StateView.EnumC2248b.FAILED == enumC2248b) {
            this.mFooter.m7932a(true, 8, getString(R.string.network_error));
        } else {
            this.mFooter.m7932a(false, 8, getString(R.string.num_loaded_data, Integer.valueOf(getDataCount())));
            this.mProxy.m5427a().setOnScrollListener(null);
        }
    }

    public Pager getPager() {
        return this.mPager;
    }

    private void handleExtra(Extra extra) {
        if (extra != null) {
            this.mPager.m4665b(extra.m8556b());
            this.mPager.m4660e();
            if (!this.mPager.m4657h()) {
                updateFooterView(StateView.EnumC2248b.NO_DATA);
            }
        }
    }

    public List<RecommendPost> getDataList() {
        return this.mAdapter.getDataList();
    }

    public int getDataCount() {
        return this.mAdapter.getCount();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mAdapter != null) {
            int m8266a = ListViewUtils.m8266a(((ListView) adapterView).getHeaderViewsCount(), i, getDataCount());
            RecommendPost item = this.mAdapter.getItem(m8266a);
            startPostDetailStatistic(item.getId(), m8266a);
            this.mAdapter.setData(item);
            launchFragment(SubPostDetailFragment.createById(item.getId(), getClass().getSimpleName()));
        }
    }

    protected void onRequestNextPage() {
        updateFooterView(StateView.EnumC2248b.LOADING);
        this.mLoadStatus = EnumC1567a.NEXT_PAGE;
        onLoadData();
    }

    public EnumC1567a getLoadStatus() {
        return this.mLoadStatus;
    }
}
