package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class FavoriteSubMediaListFragment extends SubMediaListFragment {
    public static final String TAG = "FavoriteSubMediaListFragment";

    @Override // com.sds.android.ttpod.fragment.main.list.SubMediaListFragment
    protected String selectMediaListFragmentClassName() {
        return FavoriteSongFragment.class.getName();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.SubMediaListFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected Collection<ActionItem> onCreateDropDownMenu() {
        //SUserUtils.m4956a(SAction.ACTION_OPEN_LOCAL_DROP_MENU, SPage.PAGE_NONE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_title).m7009a((Object) 7));
        arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_add_time).m7009a((Object) 10));
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.SubMediaListFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        super.onDropDownMenuClicked(i, actionItem);
        switch (i) {
            case 6:
                //LocalStatistic.m5122ak();
                return;
            default:
                return;
        }
    }

    /* loaded from: classes.dex */
    public static class FavoriteSongFragment extends MediaListFragment {
        private Animation mDownloadAnimation;
        private View mHeaderView;
        private boolean mIsRefreshing = false;
        private boolean mHasNotified = false;
        private Map<MediaItem, TaskInfo> mDownloadMediaItemTaskInfoMap = new HashMap();

        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment
        public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
            super.onLoadCommandMap(map);
            Class<?> cls = getClass();
            map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.m8375a(cls, "updateDownloadState", DownloadTaskInfo.class));
            map.put(CommandID.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, ReflectUtils.m8375a(cls, "pullFavoriteOnlineMediaListComplete", new Class[0]));
            map.put(CommandID.PULL_FAVORITE_ONLINE_MEDIA_LIST_ERROR, ReflectUtils.m8375a(cls, "refreshFavoriteOnlineFailed", new Class[0]));
            map.put(CommandID.PUSH_FAVORITE_ONLINE_MEDIA_LIST_ERROR, ReflectUtils.m8375a(cls, "refreshFavoriteOnlineFailed", new Class[0]));
            map.put(CommandID.PUSH_FAVORITE_ONLINE_MEDIA_LIST_INVALID, ReflectUtils.m8375a(cls, "refreshFavoriteOnlineFailed", new Class[0]));
            map.put(CommandID.UPDATE_FAVORITE_CHANGED, ReflectUtils.m8375a(cls, "updateFavoriteChanged", new Class[0]));
            map.put(CommandID.UPDATE_ADD_DOWNLOAD_TASK_LIST_DATABASE, ReflectUtils.m8375a(cls, "updateDownloadTaskInfoList", List.class));
            map.put(CommandID.UPDATE_ADD_DOWNLOAD_TASK_DATABASE, ReflectUtils.m8375a(cls, "updateAddDownloadTaskDatabase", DownloadTaskInfo.class));
        }

        public void pullFavoriteOnlineMediaListComplete() {
            PopupsUtils.m6760a((int) R.string.sync_favorite_songs_complete);
            this.mIsRefreshing = false;
        }

        public void refreshFavoriteOnlineFailed() {
            if (!this.mHasNotified) {
                this.mHasNotified = true;
                PopupsUtils.m6760a((int) R.string.sync_favorite_songs_failed);
            }
            this.mIsRefreshing = false;
        }

        public void updateDownloadState(DownloadTaskInfo downloadTaskInfo) {
            LogUtils.error(FavoriteSubMediaListFragment.TAG, "updateDownloadState = " + downloadTaskInfo.getState());
            if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType()) && (downloadTaskInfo.getTag() instanceof MediaItem) && Preferences.m2954aq() != null && getMediaItemList() != null && (getMediaItemList() instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) getMediaItemList()).isLoadFinished()) {
                notifyDataSetChanged();
            }
        }

        @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void updateFavoriteChanged() {
            LogUtils.error(FavoriteSubMediaListFragment.TAG, "updateFavoriteChanged");
            if (getGroupID().equals(MediaStorage.GROUP_ID_FAV)) {
                onReloadData();
            } else {
                notifyDataSetChanged();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void updateMediaList(List<MediaItem> list) {
            this.mDownloadMediaItemTaskInfoMap = (Map) CommandCenter.getInstance().m4602a(new Command(CommandID.QUERY_DOWNLOADING_INFO_BY_GROUP, getGroupID()), Map.class);
            super.updateMediaList(list);
        }

        public void updateDownloadTaskInfoList(List<DownloadTaskInfo> list) {
            this.mDownloadMediaItemTaskInfoMap = (Map) CommandCenter.getInstance().m4602a(new Command(CommandID.QUERY_DOWNLOADING_INFO_BY_GROUP, getGroupID()), Map.class);
            notifyDataSetChanged();
        }

        public void updateFavoriteOnlineDownloadState(DownloadTaskInfo downloadTaskInfo) {
            LogUtils.error(FavoriteSubMediaListFragment.TAG, "updateFavoriteOnlineDownloadState");
            if (this.mListView != null && getMediaItemList() != null && downloadTaskInfo != null && downloadTaskInfo.getGroupId().equals(getGroupID())) {
                this.mDownloadMediaItemTaskInfoMap = (Map) CommandCenter.getInstance().m4602a(new Command(CommandID.QUERY_DOWNLOADING_INFO_BY_GROUP, downloadTaskInfo.getGroupId()), Map.class);
                if (downloadTaskInfo.getState() != null && 4 == downloadTaskInfo.getState().intValue() && (getMediaItemList() instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) getMediaItemList()).isLoadFinished()) {
                    int indexOf = getMediaItemList().indexOf(downloadTaskInfo.getTag());
                    if (indexOf >= 0) {
                        getMediaItemList().get(indexOf).setLocalDataSource(downloadTaskInfo.getSavePath());
                    } else {
                        return;
                    }
                }
                MediaItem mediaItem = (MediaItem) downloadTaskInfo.getTag();
                int firstVisiblePosition = this.mListView.getFirstVisiblePosition();
                while (true) {
                    int i = firstVisiblePosition;
                    if (i <= this.mListView.getLastVisiblePosition()) {
                        int headerViewsCount = i - this.mListView.getHeaderViewsCount();
                        if (headerViewsCount < 0 || headerViewsCount >= getMediaItemList().size() || !getMediaItemList().get(headerViewsCount).equals(mediaItem)) {
                            firstVisiblePosition = i + 1;
                        } else {
                            notifyDataSetChanged();
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        public void updateAddDownloadTaskDatabase(DownloadTaskInfo downloadTaskInfo) {
            if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType()) && (downloadTaskInfo.getTag() instanceof MediaItem) && Preferences.m2954aq() != null && getMediaItemList() != null && (getMediaItemList() instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) getMediaItemList()).isLoadFinished()) {
                if (this.mDownloadMediaItemTaskInfoMap == null) {
                    this.mDownloadMediaItemTaskInfoMap = new ArrayMap();
                }
                this.mDownloadMediaItemTaskInfoMap.put((MediaItem) downloadTaskInfo.getTag(), downloadTaskInfo);
                notifyDataSetChanged();
            }
        }

        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            getListView().addHeaderView(getHeaderView());
            if (String.valueOf(SPage.PAGE_MY_FAVORITE.getValue()).equals(getPage())) {
                setNeedCountStastic();
            }
            return onCreateView;
        }

        @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
        public void onThemeLoaded() {
            super.onThemeLoaded();
            ThemeManager.m3269a(this.mHeaderView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
            ThemeManager.m3269a(this.mHeaderView.findViewById(R.id.textview_online_favorite_all_download), ThemeElement.SONG_LIST_ITEM_TEXT);
            ThemeUtils.m8173a((IconTextView) this.mHeaderView.findViewById(R.id.itv_download), ThemeElement.SONG_LIST_ITEM_TEXT);
        }

        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment
        public void updateMediaLibraryChanged(String str) {
        }

        protected View getHeaderView() {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.favorite_online_header, (ViewGroup) null);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.FavoriteSubMediaListFragment.FavoriteSongFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DownloadMenuHandler downloadMenuHandler = new DownloadMenuHandler(FavoriteSongFragment.this.getActivity());
                    String page = FavoriteSongFragment.this.getPage();
                    if (String.valueOf(SPage.PAGE_MY_FAVORITE.getValue()).equals(page)) {
                        downloadMenuHandler.m6930a(SAction.ACTION_FAVORITE_CLICK_DOWNLOAD_ALL);
                    } else if (String.valueOf(SPage.PAGE_MY_SONGLIST_ONLINE_DETAIL.getValue()).equals(page)) {
                        downloadMenuHandler.m6930a(SAction.ACTION_SONG_LIST_ONLINE_DETAIL_ALL_DOWNLOAD);
                    }
                    downloadMenuHandler.m6926a(FavoriteSongFragment.this.getMediaItemList());
                }
            });
            this.mHeaderView = inflate;
            return inflate;
        }

        public void sync() {
            if (getGroupID().equals(MediaStorage.GROUP_ID_FAV)) {
                if (!EnvironmentUtils.C0604c.m8474e()) {
                    PopupsUtils.m6760a((int) R.string.network_unavailable);
                    return;
                } else if (!this.mIsRefreshing) {
                    this.mIsRefreshing = true;
                    this.mHasNotified = false;
                    CommandCenter.getInstance().m4606a(new Command(CommandID.SYNC_FAVORITE_ONLINE_MEDIA_LIST, new Object[0]));
                    PopupsUtils.m6760a((int) R.string.sync_favorite_songs_start);
                    return;
                } else {
                    return;
                }
            }
            onReloadData();
        }

        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
            View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
            bindDownloadState(((MediaItemViewHolder) mediaItemView.getTag()).m6956i(), mediaItem);
            return mediaItemView;
        }

        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment
        protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
            if (iconTextView != null) {
                iconTextView.setText(R.string.icon_no_favorite);
            }
            if (textView != null) {
                textView.setText(R.string.no_song_favorite);
            }
            if (textView2 != null) {
                textView2.setVisibility(View.GONE);
            }
        }

        private void bindDownloadState(IconTextView iconTextView, MediaItem mediaItem) {
            int i;
            iconTextView.clearAnimation();
            iconTextView.setVisibility(View.VISIBLE);
            if (!StringUtils.isEmpty(mediaItem.getLocalDataSource())) {
                if (FileUtils.m8419a(mediaItem.getLocalDataSource())) {
                    flushDownloadStateView(iconTextView, R.string.icon_download_downloaded);
                    return;
                }
                mediaItem.setLocalDataSource("");
            }
            TaskInfo taskInfo = this.mDownloadMediaItemTaskInfoMap.get(mediaItem);
            if (taskInfo == null) {
                iconTextView.setVisibility(View.GONE);
            } else if (taskInfo.getState() == null) {
                flushDownloadStateView(iconTextView, R.string.icon_download_download);
            } else {
                switch (taskInfo.getState().intValue()) {
                    case 2:
                        this.mDownloadAnimation = this.mDownloadAnimation == null ? AnimationUtils.loadAnimation(getActivity(), R.anim.rotate) : this.mDownloadAnimation;
                        iconTextView.startAnimation(this.mDownloadAnimation);
                        i = R.string.icon_download_downloading;
                        break;
                    case 3:
                        i = R.string.icon_download_download;
                        break;
                    case 4:
                        mediaItem.setLocalDataSource(taskInfo.getSavePath());
                        this.mDownloadMediaItemTaskInfoMap.remove(mediaItem);
                        i = R.string.icon_download_downloaded;
                        break;
                    case 5:
                        i = R.string.icon_download_error;
                        break;
                    default:
                        i = R.string.icon_female;
                        break;
                }
                flushDownloadStateView(iconTextView, i);
            }
        }

        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public void onMediaItemLongClicked(MediaItem mediaItem) {
            PopupsUtils.m6757a(getActivity(), this, mediaItem);
        }
    }
}
