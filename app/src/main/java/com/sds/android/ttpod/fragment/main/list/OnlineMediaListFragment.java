package com.sds.android.ttpod.fragment.main.list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.MediaItemMenuHolder;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.component.p086c.CensorHandler;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.mediamenu.MediaMenuLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class OnlineMediaListFragment extends AbsMediaListFragment {
    private static final int CODE_LOADING = -1;
    private static final int DELAY_MILLS = 200;
    private static final String TAG = "OnlineMediaListFragment";
    protected DataListFooterView mFooterView;
    private String mModule;
    protected View mNodataView;
    private InterfaceC1666a mOnDataRequestListener;
    private InterfaceC1668c mOnNextPageListener;
    private String mOrigin;
    private String mPlayingGroupID;
    private String mPlayingMediaID;
    private Pager mPager = new Pager();
    private boolean mIsLoading = true;
    protected boolean mIsErrorNotFirstPage = false;
    private boolean mNeedShowFootAnimation = true;
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.7
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (OnlineMediaListFragment.this.mAZSideBar != null) {
                OnlineMediaListFragment.this.mAZSideBar.onScrollStateChanged(absListView, i);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            OnlineMediaListFragment.this.performOnScroll(absListView, i, i2, i3);
            if (OnlineMediaListFragment.this.mAZSideBar != null) {
                OnlineMediaListFragment.this.mAZSideBar.onScroll(absListView, i, i2, i3);
            }
        }
    };

    /* renamed from: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1666a {
        /* renamed from: a */
        void mo5420a();
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment$b */
    /* loaded from: classes.dex */

    /* renamed from: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment$c */
    /* loaded from: classes.dex */
    public interface InterfaceC1668c {
        /* renamed from: a */
        void mo5452a(int i);
    }

    public String getOrigin() {
        return this.mOrigin;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    public String getModule() {
        return this.mModule;
    }

    public void setModule(String str) {
        this.mModule = str;
    }

    protected String getGroupID() {
        return this.mGroupID;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        getStateView().onThemeLoaded();
        ThemeManager.m3269a(this.mNodataView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mFooterView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(this.mFooterView, ThemeElement.SUB_BAR_TEXT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.loadingview_failed, (ViewGroup) null);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        this.mFooterView = new DataListFooterView(getActivity());
        return this.mFooterView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View onCreateNodataView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.loadingview_nodata, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressLint("RestrictedApi")
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
        boolean z = true;
        if (view == null) {
            view = getLayoutInflater(null).inflate(R.layout.online_media_list_item, (ViewGroup) null);
            setViewTagHolder(view);
        }
        MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) view.getTag();
        mediaItemViewHolder.m6970a(this.mListView, mediaItem, i, (this.mMediaItemList instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) this.mMediaItemList).isLoadFinished());
        if (StringUtils.isEmpty(this.mPlayingGroupID)) {
            this.mPlayingGroupID = Preferences.getLocalGroupId();
        }
        if (StringUtils.isEmpty(this.mPlayingMediaID)) {
            this.mPlayingMediaID = Preferences.getMediaId();
        }
        if (!StringUtils.equals(this.mGroupID, this.mPlayingGroupID) || !StringUtils.equals(this.mPlayingMediaID, mediaItem.getID())) {
            z = false;
        }
        mediaItemViewHolder.m6971a(mediaItem, this.mPlayStatus, z);
        mediaItemViewHolder.m6962c(mediaItem);
        setMenuClickEvent(mediaItem, mediaItemViewHolder);
        view.setSelected(z);
        return view;
    }

    private void setMenuClickEvent(final MediaItem mediaItem, MediaItemViewHolder mediaItemViewHolder) {
        boolean z = true;
        Iterator<OnlineMediaItem.OutListItem> it = mediaItem.getOutList().iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            z2 = !StringUtils.isEmpty(it.next().getUrl()) ? true : z2;
        }
        if (!mediaItem.hasOutList() || z2) {
            z = false;
        }
        if (z) {
            mediaItemViewHolder.getMenuView().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OnlineMediaListFragment.this.popupCopyrightDialog(mediaItem);
                }
            });
        }
        mediaItemViewHolder.getMenuView().setOnClickEventCustomized(z);
    }

    private void setMediaMenu(final MediaItem mediaItem, MediaItemMenuHolder mediaItemMenuHolder, final int i) {
        boolean hasOutList = mediaItem.hasOutList();
        mediaItemMenuHolder.m6977a(mediaItem);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                //SUserUtils.m4958a(i);
                ListViewUtils.m8264a(OnlineMediaListFragment.this.mListView);
                OnlineMediaListFragment.this.popupCopyrightDialog(mediaItem);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                //SUserUtils.m4958a(i);
                ListViewUtils.m8264a(OnlineMediaListFragment.this.mListView);
                OnlineMediaListFragment.this.showRightContextMenu(mediaItem);
            }
        };
        if (mediaItemMenuHolder.m6981a() != null) {
            MediaMenuLayout m6981a = mediaItemMenuHolder.m6981a();
            if (!hasOutList) {
                onClickListener = onClickListener2;
            }
            m6981a.m1231a(R.id.media_menu_download, onClickListener);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
    public void onExpand(View view, int i) {
        ((MediaItemViewHolder) ((View) view.getParent()).getTag()).getMenuView().setText(R.string.icon_arrow_top);
        if (i < getMediaItemList().size() && i >= 0) {
            MediaItemMenuHolder mediaItemMenuHolder = (MediaItemMenuHolder) view.getTag();
            MediaItem mediaItem = getMediaItemList().get(i);
            initMediaItemMenuClickEvent(mediaItemMenuHolder, mediaItem, i);
            setMediaMenu(mediaItem, mediaItemMenuHolder, i);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected boolean isShowFavoriteCount() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mNodataView = onCreateNodataView(layoutInflater);
        this.mStateView.setNodataView(this.mNodataView);
        return this.mStateView;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mPager.m4665b(VIPPolicy.Entry.MAX_LIMIT);
        getListView().setOnScrollListener(this.mOnScrollListener);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mOnScrollListener = null;
        this.mOnDataRequestListener = null;
        this.mOnNextPageListener = null;
        if (getListView() != null) {
            getListView().setOnScrollListener(null);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected void configFailedView(View view) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OnlineMediaListFragment.this.onReloadData();
                }
            });
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected void configListFooterView(View view) {
    }

    protected void configNodataView(View view) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OnlineMediaListFragment.this.onReloadData();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onReloadData() {
        if (this.mOnDataRequestListener != null) {
            this.mOnDataRequestListener.mo5420a();
        }
    }

    protected boolean onListStatistic() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void beforeOnMediaItemClicked(MediaItem mediaItem) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onMediaItemClicked(MediaItem mediaItem, int i) {
        ListViewUtils.m8264a(this.mListView);
        beforeOnMediaItemClicked(mediaItem);
        LogUtils.debug("ListStatistic", "onMediaItemClicked=" + mediaItem.getSongID() + "," + mediaItem.getTitle());
        if (onListStatistic()) {
            //OnlineMediaStatistic.m5047a(Integer.valueOf(//ListStatistic.m5212a()));
            //OnlineMediaStatistic.m5038b(//ListStatistic.m5203b());
            //OnlineMediaStatistic.m5046a(mediaItem.getSongID());
            //OnlineMediaStatistic.m5034c(//ListStatistic.m5201c());
        } else {
            //OnlineMediaStatistic.m5047a((Integer) (-1));
            //OnlineMediaStatistic.m5038b((String) null);
            //OnlineMediaStatistic.m5046a((Long) (-1L));
            //OnlineMediaStatistic.m5034c(null);
        }
        //OnlineMediaStatistic.m5045a(getListenOrigin());
        //OnlineMediaStatistic.m5054a();
        if (StringUtils.equals(this.mGroupID, Preferences.getLocalGroupId()) && StringUtils.equals(mediaItem.getID(), Preferences.getMediaId())) {
            PlayStatus m2463m = SupportFactory.getInstance(BaseApplication.getApplication()).m2463m();
            if (m2463m == PlayStatus.STATUS_PAUSED) {
                CommandCenter.getInstance().execute(new Command(CommandID.RESUME, new Object[0]));
                return;
            } else if (m2463m == PlayStatus.STATUS_PLAYING) {
                CommandCenter.getInstance().execute(new Command(CommandID.PAUSE, new Object[0]));
                return;
            } else {
                CommandCenter.getInstance().execute(new Command(CommandID.START, new Object[0]));
                return;
            }
        }
        LogUtils.debug(TAG, "onMediaItemClicked SYNC_NET_TEMPORARY_GROUP " + getMediaItemList());
        CommandCenter.getInstance().execute(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, filterThirdParty()));
        if (mediaItem.hasOutList()) {
            popupCopyrightDialog(mediaItem);
        } else {
            CommandCenter.getInstance().execute(new Command(CommandID.PLAY_GROUP, this.mGroupID, mediaItem));
            this.mPlayingGroupID = this.mGroupID;
            this.mPlayingMediaID = mediaItem.getID();
        }
    }

    private List<MediaItem> filterThirdParty() {
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : getMediaItemList()) {
            if (!mediaItem.hasOutList()) {
                arrayList.add(mediaItem);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void popupCopyrightDialog(MediaItem mediaItem) {
        //SearchStatistic.m4948a(mediaItem);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SHOW_COPYRIGHT_DIALOG.getValue(), SPage.PAGE_DIALOG_COPYRIGHT.getValue()).post();
        new CensorHandler(getActivity(), mediaItem).m6949a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onMediaItemLongClicked(MediaItem mediaItem) {
        super.onMediaItemLongClicked(mediaItem);
        if (mediaItem.hasOutList()) {
            popupCopyrightDialog(mediaItem);
        } else {
            showRightContextMenu(mediaItem);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        this.mPlayingGroupID = Preferences.getLocalGroupId();
        if (StringUtils.equals(this.mGroupID, this.mPlayingGroupID)) {
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void playMediaChanged() {
        this.mPlayingMediaID = Cache.getInstance().getCurrentPlayMediaItem().getID();
        if (StringUtils.equals(this.mGroupID, Preferences.getLocalGroupId())) {
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void updatePlayingMediaInfo() {
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected void showRightContextMenu(MediaItem mediaItem) {
        if (this.mOrigin != null && this.mModule != null) {
            //StatisticUtils.m4907a(this.mModule, "menu", this.mOrigin, 0L, //OnlineMediaStatistic.m5029f(), mediaItem.getTitle(), UUID.randomUUID().toString());
        }
        new DownloadMenuHandler(getActivity()).m6927a(mediaItem, getDownloadOrigin());
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void updateMediaList(List<MediaItem> list) {
        updateMediaList(0, Integer.valueOf((int) VIPPolicy.Entry.MAX_LIMIT), list);
    }

    public void updateMediaList(Integer num, List<MediaItem> list) {
        updateMediaList(0, num, list);
    }

    public void clearMediaList() {
        List<MediaItem> mediaItemList = getMediaItemList();
        if (ListUtils.m4717b(mediaItemList)) {
            mediaItemList.clear();
            notifyDataSetChanged();
        }
    }

    public void updateMediaList(Integer num, Integer num2, List<MediaItem> list) {
        this.mIsLoading = false;
        if (isViewAccessAble() && list != null && list.size() > 0) {
            LogUtils.debug(TAG, getClass().getSimpleName() + ".updateMediaList ");
            this.mPager.m4665b(num2.intValue());
            if (this.mPager.m4669a() > 1) {
                getMediaItemList().addAll(list);
                if (this.mFooterView != null) {
                    this.mFooterView.m1873c();
                }
            } else {
                setMediaList(list);
            }
            this.mIsErrorNotFirstPage = false;
        } else if (this.mPager.m4669a() > 1) {
            this.mIsErrorNotFirstPage = true;
            this.mPager.m4663c(this.mPager.m4669a() - 1);
            showNotFirstPageError();
            return;
        } else {
            this.mIsErrorNotFirstPage = false;
            setMediaList(list);
        }
        notifyDataSetChanged();
        if (isViewAccessAble()) {
            updateStateViews(num, getMediaItemList());
        }
    }

    public void updateStateViews(List<MediaItem> list) {
        if (isViewAccessAble()) {
            updateStateViews(-1, list);
        }
    }

    private void updateStateViews(Integer num, List<MediaItem> list) {
        final StateView stateView = getStateView();
        if (list == null) {
            if (num.intValue() == -1) {
                stateView.setState(StateView.EnumC2248b.LOADING);
                return;
            }
            stateView.setState(StateView.EnumC2248b.FAILED);
            configFailedView(getFailedView());
        } else if (list.size() == 0) {
            if (num.intValue() == 1) {
                stateView.setState(StateView.EnumC2248b.NO_DATA);
                configNodataView(this.mNodataView);
                return;
            }
            stateView.setState(StateView.EnumC2248b.LOADING);
            stateView.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.6
                @Override // java.lang.Runnable
                public void run() {
                    stateView.setState(StateView.EnumC2248b.FAILED);
                    OnlineMediaListFragment.this.configFailedView(OnlineMediaListFragment.this.getFailedView());
                }
            }, 200L);
        } else if (list.size() > 0) {
            stateView.setState(StateView.EnumC2248b.SUCCESS);
            configListFooterView(getListFooterView());
        }
    }

    protected void performOnScroll(AbsListView absListView, int i, int i2, int i3) {
        LogUtils.debug(TAG, "performOnScroll firstVisibleItem=" + i + ",visibleItemCount=" + i2 + ",totalItemCount=" + i3);
        if (ListViewUtils.m8262b(i, i2, i3) && !this.mIsLoading) {
            LogUtils.debug(TAG, "mPager.getCurrent()=" + this.mPager.m4669a() + ",mPager.end()=" + this.mPager.m4658g());
            if (this.mPager.m4669a() >= this.mPager.m4658g()) {
                showLastPageFooterText();
            } else {
                requestData(this.mPager.m4662d());
            }
        }
    }

    public boolean isHomePage() {
        return this.mPager.m4659f();
    }

    public void repeatPageRequestData() {
        int m4662d = this.mPager.m4662d();
        if (m4662d > this.mPager.m4664c()) {
            m4662d = this.mPager.m4666b();
        }
        requestData(m4662d);
    }

    public void requestHomePage() {
        requestData(this.mPager.m4666b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestData(int i) {
        if (!this.mIsLoading) {
            LogUtils.debug(TAG, "requestData page = " + i);
            if (i >= 1 && i >= this.mPager.m4666b() && i <= this.mPager.m4658g()) {
                this.mIsLoading = true;
                if (this.mFooterView != null && this.mNeedShowFootAnimation) {
                    this.mFooterView.m1877a();
                    this.mFooterView.setOnClickListener(null);
                }
                onRequestPage(i);
                this.mPager.m4663c(i);
                if (this.mOnNextPageListener != null) {
                    this.mOnNextPageListener.mo5452a(i);
                }
            }
        }
    }

    public void setNeedShowFootAnimation(boolean z) {
        this.mNeedShowFootAnimation = z;
    }

    public void showLastPageFooterText() {
        if (this.mFooterView != null && getMediaItemList() != null) {
            this.mFooterView.m1875a(getString(R.string.count_of_media, Integer.valueOf(getMediaItemList().size())));
            this.mFooterView.setOnClickListener(null);
        }
    }

    private void showNotFirstPageError() {
        if (this.mFooterView != null) {
            this.mFooterView.m1874b();
            this.mFooterView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OnlineMediaListFragment.this.requestData(OnlineMediaListFragment.this.mPager.m4662d());
                }
            });
        }
    }

    protected void onRequestPage(int i) {
    }

    public void setTotal(int i) {
        this.mPager.m4665b(i);
    }

    public void clearPage() {
        this.mPager = new Pager();
        this.mPager.m4665b(VIPPolicy.Entry.MAX_LIMIT);
    }

    public void setOnNextPageListener(InterfaceC1668c interfaceC1668c) {
        this.mOnNextPageListener = interfaceC1668c;
    }

    public void hide() {
        if (isViewAccessAble()) {
            getStateView().setVisibility(View.GONE);
        }
    }

    public void show() {
        if (isViewAccessAble()) {
            getStateView().setVisibility(View.VISIBLE);
        }
    }

    public void setOnDataRequestListener(InterfaceC1666a interfaceC1666a) {
        this.mOnDataRequestListener = interfaceC1666a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getListenOrigin() {
        return this.mOrigin;
    }

    protected String getDownloadOrigin() {
        return getListenOrigin();
    }
}
