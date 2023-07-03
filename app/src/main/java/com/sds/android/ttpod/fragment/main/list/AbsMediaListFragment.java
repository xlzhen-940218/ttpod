package com.sds.android.ttpod.fragment.main.list;

import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.MediaItemMenuHolder;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MoreOptionalDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.AZSideBar;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class AbsMediaListFragment extends BaseFragment implements AbstractExpandableListAdapter.InterfaceC2279a {
    public static final String KEY_GROUP_ID = "group_id";
    private static final String TAG = "AbsMediaListFragment";
    protected AZSideBar mAZSideBar;
    private DataSetObserver mDataSetObserver = new DataSetObserver() { // from class: com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment.1
        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            AbsMediaListFragment.this.onDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            AbsMediaListFragment.this.onDataSetChanged();
        }
    };
    protected View mFailedView;
    protected String mGroupID;
    protected View mListFooterView;
    protected View mListHeaderView;
    protected ActionExpandableListView mListView;
    protected List<MediaItem> mMediaItemList;
    protected MediaListAdapter mMediaListAdapter;
    protected PlayStatus mPlayStatus;
    protected StateView mStateView;

    protected abstract void configFailedView(View view);

    protected abstract void configListFooterView(View view);

    protected abstract View onCreateFailedView(LayoutInflater layoutInflater);

    protected abstract View onCreateListFooterView(LayoutInflater layoutInflater);

    protected abstract void onMediaItemClicked(MediaItem mediaItem, int i);

    protected abstract void onReloadData();

    abstract void playMediaChanged();

    abstract void showRightContextMenu(MediaItem mediaItem);

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mGroupID = arguments.getString(KEY_GROUP_ID);
            LogUtils.debug(TAG, "onCreate lookStatisticId groupid=%s", this.mGroupID);
        }
        this.mMediaListAdapter = new MediaListAdapter();
        this.mPlayStatus = SupportFactory.getInstance(BaseApplication.getApplication()).m2463m();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.loadMethod(cls, "updatePlayingMediaInfo", new Class[0]));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.loadMethod(cls, "playMediaChanged", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.loadMethod(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_FAVORITE_CHANGED, ReflectUtils.loadMethod(cls, "updateFavoriteChanged", new Class[0]));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mStateView = (StateView) layoutInflater.inflate(R.layout.fragment_media_list, viewGroup, false);
        this.mListView = (ActionExpandableListView) this.mStateView.findViewById(R.id.media_listview);
        this.mListHeaderView = onCreateListHeaderView(layoutInflater);
        if (this.mListHeaderView != null) {
            this.mListView.addHeaderView(this.mListHeaderView, null, false);
        }
        this.mListFooterView = onCreateListFooterView(layoutInflater);
        if (this.mListFooterView != null) {
            this.mListView.addFooterView(this.mListFooterView);
        }
        this.mListView.setFooterDividersEnabled(true);
        this.mListView.setHeaderDividersEnabled(true);
        this.mAZSideBar = (AZSideBar) this.mStateView.findViewById(R.id.azsidebar);
        this.mFailedView = onCreateFailedView(layoutInflater);
        this.mStateView.setFailedView(this.mFailedView);
        return this.mStateView;
    }

    protected View onCreateListHeaderView(LayoutInflater layoutInflater) {
        return null;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mStateView = null;
        this.mListView = null;
        this.mListFooterView = null;
        this.mFailedView = null;
        this.mAZSideBar = null;
        this.mMediaListAdapter.unregisterDataSetObserver(this.mDataSetObserver);
    }

    protected boolean isAZSideBarEnable() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDataSetChanged() {
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mMediaListAdapter.registerDataSetObserver(this.mDataSetObserver);
        this.mListView.mo1261a(this.mMediaListAdapter, R.id.menu_view, R.id.expandable);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                int m8266a;
                if (AbsMediaListFragment.this.mMediaListAdapter != null && AbsMediaListFragment.this.mListView != null && (m8266a = ListViewUtils.m8266a(AbsMediaListFragment.this.mListView.getHeaderViewsCount(), i, AbsMediaListFragment.this.mMediaListAdapter.getCount())) > -1) {
                    //OnlineMediaStatistic.m5053a(m8266a + 1);
                    AbsMediaListFragment.this.onMediaItemClicked(AbsMediaListFragment.this.mMediaItemList.get(m8266a), m8266a);
                }
            }
        });
        this.mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment.3
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view2, int i, long j) {
                int m8266a = ListViewUtils.m8266a(AbsMediaListFragment.this.mListView.getHeaderViewsCount(), i, AbsMediaListFragment.this.mMediaListAdapter.getCount());
                if (m8266a > -1) {
                    //OnlineMediaStatistic.m5053a(m8266a + 1);
                    AbsMediaListFragment.this.onMediaItemLongClicked(AbsMediaListFragment.this.mMediaItemList.get(m8266a));
                    return true;
                }
                return true;
            }
        });
        this.mListView.setOnScrollListener(this.mAZSideBar);
        this.mAZSideBar.setOnMatchedPositionChangeListener(new AZSideBar.OnMatchedPositionChangeListener() { // from class: com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment.4
            @Override // com.sds.android.ttpod.widget.AZSideBar.InterfaceC2161a
            /* renamed from: a */
            public void positionChanged(int position, String str) {
                AbsMediaListFragment.this.selectRow(position);
            }
        });
        updateStateViews();
        updateAZSideBar();
        this.mListView.setItemExpandCollapseListener(this);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mMediaItemList == null) {
            onReloadData();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onMediaItemLongClicked(MediaItem mediaItem) {
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        this.mStateView.onThemeLoaded();
        this.mAZSideBar.onThemeLoaded();
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(this.mListFooterView, ThemeElement.SUB_BAR_TEXT);
        ThemeManager.m3269a(this.mListFooterView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        refreshListViewTheme();
    }

    private Drawable makeDrawable(Drawable drawable, int i, int i2) {
        if (drawable != null) {
            return new InsetDrawable(drawable, i, 0, i2, 0);
        }
        return null;
    }

    private void refreshListViewTheme() {
        if (this.mListView != null) {
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mMediaItemList != null) {
            this.mMediaItemList.clear();
            notifyDataSetChanged();
            this.mMediaListAdapter = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateMediaList(List<MediaItem> list) {
        if (list != this.mMediaItemList) {
            if (this.mMediaItemList != null) {
                this.mMediaItemList.clear();
            }
            this.mMediaItemList = list;
            attachFavoriteState();
            notifyDataSetChanged();
        }
        if (isViewAccessAble()) {
            updateStateViews();
            updateAZSideBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setMediaList(List<MediaItem> list) {
        if (list != null) {
            this.mMediaItemList = new ArrayList(list);
        }
        attachFavoriteState();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyDataSetChanged() {
        if (this.mMediaListAdapter != null) {
            this.mMediaListAdapter.notifyDataSetChanged();
        }
    }

    public void updateFavoriteChanged() {
        attachFavoriteState();
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void attachFavoriteState() {
        if (this.mMediaItemList != null) {
            if (!(this.mMediaItemList instanceof AsyncLoadMediaItemList) || ((AsyncLoadMediaItemList) this.mMediaItemList).isLoadFinished()) {
                for (MediaItem mediaItem : this.mMediaItemList) {
                    mediaItem.setFav(MediaItemUtils.m4715a(mediaItem));
                }
            }
        }
    }

    protected boolean needFailedState() {
        return true;
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        this.mPlayStatus = playStatus;
    }

    public void updatePlayingMediaInfo() {
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setViewTagHolder(View view) {
        view.setTag(new MediaItemViewHolder(view));
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
    public void onExpand(View view, int i) {
        updateMediaItemView((MediaItemViewHolder) ((View) view.getParent()).getTag(), i, true);
        if (i < getMediaItemList().size() && i >= 0) {
            initMediaItemMenuClickEvent((MediaItemMenuHolder) view.getTag(), getMediaItemList().get(i), i);
        }
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
    public void onCollapse(View view, int i) {
        View view2;
        if (view != null && (view2 = (View) view.getParent()) != null) {
            updateMediaItemView((MediaItemViewHolder) view2.getTag(), i, false);
        }
    }

    private void updateMediaItemView(MediaItemViewHolder mediaItemViewHolder, int i, boolean z) {
        if (z && i < this.mMediaItemList.size()) {
            mediaItemViewHolder.updateExpandable(this.mMediaItemList.get(i));
        }
        mediaItemViewHolder.getMenuView().setText(z ? R.string.icon_arrow_top : R.string.icon_arrow_down);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
        if (view == null) {
            view = getLayoutInflater(null).inflate(R.layout.media_list_item, (ViewGroup) null);
            setViewTagHolder(view);
        }
        ((MediaItemViewHolder) view.getTag()).m6970a(this.mListView, mediaItem, i, (this.mMediaItemList instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) this.mMediaItemList).isLoadFinished());
        return view;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initMediaItemMenuClickEvent(final MediaItemMenuHolder mediaItemMenuHolder, final MediaItem mediaItem, final int i) {
        mediaItemMenuHolder.m6980a(new MediaItemMenuClickStub(getActivity(), this.mListView, mediaItem, i) { // from class: com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment.5
            @Override // com.sds.android.ttpod.fragment.main.list.MediaItemMenuClickStub
            /* renamed from: a */
            protected void mo5441a(MediaItem mediaItem2) {
                AbsMediaListFragment.this.showRightContextMenu(mediaItem2);
            }

            @Override // com.sds.android.ttpod.fragment.main.list.MediaItemMenuClickStub
            /* renamed from: b */
            protected void mo5440b(MediaItem mediaItem2) {
                AbsMediaListFragment.this.onDeleteMediaItem(mediaItem2);
            }

            @Override // com.sds.android.ttpod.fragment.main.list.MediaItemMenuClickStub
            /* renamed from: a */
            protected void mo5444a() {
                mediaItemMenuHolder.favority(mediaItem, i);
            }
        });
    }

    public void onDeleteMediaItem(final MediaItem mediaItem) {
        PopupsUtils.m6738a(getActivity(), mediaItem, this.mGroupID, new BaseDialog.OnClickListener<MoreOptionalDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment.6
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void onClick(MoreOptionalDialog moreOptionalDialog) {
                List<MediaItem> mediaItemList = AbsMediaListFragment.this.getMediaItemList();
                if (!(mediaItemList instanceof AsyncLoadMediaItemList) || ((AsyncLoadMediaItemList) mediaItemList).isLoadFinished()) {
                    mediaItemList.remove(mediaItem);
                    AbsMediaListFragment.this.updateMediaList(mediaItemList);
                }
            }
        });
        //LocalStatistic.m5145aI();
    }

    protected boolean isShowFavoriteCount() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onFavoriteChanged(MediaItem mediaItem, boolean z) {
    }

    public final List<MediaItem> getMediaItemList() {
        return this.mMediaItemList;
    }

    public int getUnDownloadedMediaSize() {
        int i = 0;
        if (this.mMediaItemList == null || this.mMediaItemList.isEmpty()) {
            return 0;
        }
        Iterator<MediaItem> it = this.mMediaItemList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                i = StringUtils.isEmpty(it.next().getLocalDataSource()) ? i2 + 1 : i2;
            } else {
                return i2;
            }
        }
    }

    public boolean isEmpty() {
        return this.mMediaItemList == null || this.mMediaItemList.size() == 0;
    }

    public final ActionExpandableListView getListView() {
        return this.mListView;
    }

    public final StateView getStateView() {
        return this.mStateView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final View getFailedView() {
        return this.mFailedView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final View getListFooterView() {
        return this.mListFooterView;
    }

    private void updateStateViews() {
        if (needFailedState()) {
            if (this.mMediaItemList == null) {
                this.mStateView.setState(StateView.EnumC2248b.LOADING);
                return;
            } else if (this.mMediaItemList.size() == 0) {
                this.mStateView.setState(StateView.EnumC2248b.FAILED);
                configFailedView(this.mFailedView);
                return;
            } else if (this.mMediaItemList.size() > 0) {
                this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                configListFooterView(this.mListFooterView);
                return;
            } else {
                return;
            }
        }
        this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
        configListFooterView(this.mListFooterView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void updateAZSideBar() {
        if (isViewAccessAble()) {
            boolean isAZSideBarEnable = isAZSideBarEnable();
            this.mAZSideBar.setVisibility(isAZSideBarEnable ? View.VISIBLE : View.GONE);
            this.mListView.setVerticalScrollBarEnabled(!isAZSideBarEnable);
            this.mListView.setFastScrollEnabled(isAZSideBarEnable ? false : true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateAZKeys(List<String> list) {
        DebugUtils.m8426a((Object) list, "AZKeys");
        if (isViewAccessAble()) {
            LogUtils.debug(TAG, "lookLoadData updateAZKeys groupId=%s", this.mGroupID);
            this.mAZSideBar.setAZKeys(list);
        }
        updateAZSideBar();
    }

    public void selectRow(int i) {
        if (this.mListView != null) {
            this.mListView.requestFocus();
            this.mListView.setSelection(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment$a */
    /* loaded from: classes.dex */
    public class MediaListAdapter extends BaseAdapter {
        private MediaListAdapter() {
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (AbsMediaListFragment.this.mMediaItemList == null) {
                return 0;
            }
            return AbsMediaListFragment.this.mMediaItemList.size();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return AbsMediaListFragment.this.getMediaItemView(AbsMediaListFragment.this.mMediaItemList.get(i), view, viewGroup, i);
        }
    }
}
