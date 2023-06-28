package com.sds.android.ttpod.fragment.main.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.local.MediaItemSearchActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.p087d.p088a.MoreOptionalDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.BlueToothUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MediaListFragment extends AbsMediaListFragment implements IEditAble, IOrderAble, ISearchAble {
    private static final int COLOR_DOWNLOAD = -14304298;
    private static final int COLOR_DOWNLOADED = -9707185;
    private static final int COLOR_DOWNLOADING = -2327585;
    private static final int COLOR_DOWNLOAD_ERROR = -383198;
    private static final ArrayList<C1657a> GROUP_PAGE_LIST = new ArrayList<>();
    private static final String TAG = "MediaListFragment";
    private EditRequestListener mEditRequestListener;
    private boolean mNeedCountStastic;
    private String mOrderBy;
    private String mPlayingGroupID;
    private String mPlayingMediaID;
    private boolean mIsEditing = false;
    private boolean mAutoSelectPlayingMedia = true;
    private Map<Integer, MediaItem> mSelectMediaItemHashMap = new LinkedHashMap();
    private int mNoDataIconResId = R.string.icon_no_song;
    private int mNoDataMessageResId = R.string.no_song_local_music;
    private boolean mDisplayMenu = true;
    private AsyncLoadMediaItemList.OnLoadFinishedListener mOnBuildAZKeysLoadFinishedListener = new AsyncLoadMediaItemList.OnLoadFinishedListener() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.3
        @Override // com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList.OnLoadFinishedListener
        public void onLoadFinished() {
            if (MediaListFragment.this.isAZSideBarEnable()) {
                MediaListFragment.this.updateAZKeys(MediaListFragment.this.buildAZKeys(MediaListFragment.this.getMediaItemList()));
            }
        }
    };
    private AsyncLoadMediaItemList.OnLoadFinishedListener mAttachFavOnLoadFinishedListener = new AsyncLoadMediaItemList.OnLoadFinishedListener() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.4
        @Override // com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList.OnLoadFinishedListener
        public void onLoadFinished() {
            MediaListFragment.this.attachFavoriteState();
        }
    };

    static {
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_ALL_LOCAL, SPage.PAGE_LOCAL_SONG));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_ARTIST_PREFIX, SPage.PAGE_LOCAL_ARTIST_DETAIL));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_ALBUM_PREFIX, SPage.PAGE_LOCAL_ALBUM_DETAIL));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_FOLDER_PREFIX, SPage.PAGE_LOCAL_FOLDER_DETAIL));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_RECENTLY_ADD, SPage.PAGE_RECENT_ADDED));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_RECENTLY_PLAY, SPage.PAGE_RECENT_PLAY));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_FAV, SPage.PAGE_MY_FAVORITE));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_DOWNLOAD, SPage.PAGE_MY_DOWNLOAD_DOWNLOADED));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX, SPage.PAGE_MY_SONGLIST_ONLINE_DETAIL));
        GROUP_PAGE_LIST.add(new C1657a(MediaStorage.GROUP_ID_CUSTOM_PREFIX, SPage.PAGE_MY_SONGLIST_LOCAL_DETAIL));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.list.MediaListFragment$a */
    /* loaded from: classes.dex */
    public static class C1657a {

        /* renamed from: a */
        private String f5391a;

        /* renamed from: b */
        private SPage f5392b;

        public C1657a(String str, SPage sPage) {
            this.f5391a = str;
            this.f5392b = sPage;
        }
    }

    public void setEditRequestListener(EditRequestListener interfaceC1677a) {
        this.mEditRequestListener = interfaceC1677a;
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAutoSelectPlayingMedia(boolean z) {
        this.mAutoSelectPlayingMedia = z;
    }

    public void setNeedCountStastic() {
        this.mNeedCountStastic = true;
    }

    public static SPage pageFromGroupId(String str) {
        SPage sPage = SPage.PAGE_NONE;
        Iterator<C1657a> it = GROUP_PAGE_LIST.iterator();
        while (it.hasNext()) {
            C1657a next = it.next();
            if (str.startsWith(next.f5391a)) {
                return next.f5392b;
            }
        }
        return sPage;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        assertGroupID();
        loadCacheInfo();
        setPage(pageFromGroupId(this.mGroupID));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mAutoSelectPlayingMedia && StringUtils.equals(Preferences.getLocalGroupId(), this.mGroupID)) {
            int m2458r = SupportFactory.getInstance(BaseApplication.getApplication()).m2458r();
            selectRow(m2458r);
            if (this.mAZSideBar != null) {
                this.mAZSideBar.m1920a(m2458r);
            }
        }
        if (this.mGroupID.equals(MediaStorage.GROUP_ID_DOWNLOAD)) {
            //LocalStatistic.m5153aA();
        }
    }

    private void assertGroupID() {
        DebugUtils.m8426a(this.mGroupID, "mGroupID");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_ASYNCLOAD_MEDIA_ITEM_LIST, ReflectUtils.m8375a(cls, "updateAsyncloadMediaItemList", String.class, AsyncLoadMediaItemList.class));
        map.put(CommandID.DELETE_MEDIA_ITEMS_FINISHED, ReflectUtils.m8375a(cls, "onDeleteMediaItemsFinished", String.class));
        map.put(CommandID.SCAN_FINISHED, ReflectUtils.m8375a(cls, "onScanFinished", Integer.class));
        map.put(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, ReflectUtils.m8375a(cls, "updateMediaLibraryChanged", String.class));
        map.put(CommandID.UPDATE_MEDIA_ITEM_FINISHED, ReflectUtils.m8375a(cls, "updateMediaItemFinished", MediaItem.class));
        map.put(CommandID.UPDATE_SAVE_EFFECT_TO_LOCAL, ReflectUtils.m8375a(cls, "updateSaveEffectToLocal", Boolean.class));
        map.put(CommandID.UPDATE_DELETE_PRIVATE_EFFECT_LIST, ReflectUtils.m8375a(cls, "updateDeletePrivateEffectList", new Class[0]));
    }

    public void updateSaveEffectToLocal(Boolean bool) {
        if (bool.booleanValue()) {
            notifyDataSetChanged();
        }
    }

    public void updateDeletePrivateEffectList() {
        notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        loadAZKeys(getMediaItemList());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.stateview_fail_local_media, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void configFailedView(View view) {
        configNoDataView((IconTextView) view.findViewById(R.id.no_media_icon), (TextView) view.findViewById(R.id.textview_load_failed), (TextView) view.findViewById(R.id.no_data_action_view));
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        iconTextView.setText(this.mNoDataIconResId);
        textView.setText(this.mNoDataMessageResId);
        textView2.setVisibility(this.mDisplayMenu ? View.VISIBLE : View.INVISIBLE);
        if (StringUtils.equals(this.mGroupID, MediaStorage.GROUP_ID_ALL_LOCAL)) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    //SUserUtils.m4956a(SAction.ACTION_LOCAL_SCAN_MUSIC_WHEN_NO_SONG, SPage.PAGE_SCAN_MUSIC);
                    MediaListFragment.this.startActivity(new Intent(MediaListFragment.this.getActivity(), MediaScanAnimationActivity.class));
                }
            });
        }
    }

    public void setNoDataViewMessage(int i, int i2) {
        this.mNoDataIconResId = i;
        this.mNoDataMessageResId = i2;
        this.mDisplayMenu = false;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.listview_footer_local_media, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void configListFooterView(View view) {
        if (view instanceof TextView) {
            List<MediaItem> mediaItemList = getMediaItemList();
            TextView textView = (TextView) view;
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(mediaItemList != null ? mediaItemList.size() : 0);
            textView.setText(getString(R.string.count_of_media, objArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onDataSetChanged() {
        super.onDataSetChanged();
        loadCacheInfo();
    }

    private void loadCacheInfo() {
        if (this.mGroupID != null) {
            this.mPlayingGroupID = Preferences.getLocalGroupId();
            this.mPlayingMediaID = Preferences.getMediaId();
            this.mOrderBy = Preferences.m2860l(this.mGroupID);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onReloadData() {
        CommandCenter.getInstance().execute(new Command(CommandID.QUERY_ASYNCLOAD_MEDIA_ITEM_LIST, this.mGroupID, Preferences.m2860l(this.mGroupID)));
    }

    public void putSelectedMediaItem(Collection<MediaItem> collection) {
        if (collection != null && collection.size() > 0) {
            for (MediaItem mediaItem : collection) {
                this.mSelectMediaItemHashMap.put(Integer.valueOf(mediaItem.hashCode()), mediaItem);
            }
            tryNotifySelectedCountChanged();
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected void onMediaItemClicked(MediaItem mediaItem, int i) {
        //OnlineMediaStatistic.m5054a();
        ListViewUtils.m8264a(this.mListView);
        if (this.mIsEditing) {
            int hashCode = mediaItem.hashCode();
            if (this.mSelectMediaItemHashMap.containsKey(Integer.valueOf(hashCode))) {
                this.mSelectMediaItemHashMap.remove(Integer.valueOf(hashCode));
            } else {
                this.mSelectMediaItemHashMap.put(Integer.valueOf(hashCode), mediaItem);
            }
            tryNotifySelectedCountChanged();
            notifyDataSetChanged();
        } else if (StringUtils.equals(this.mGroupID, Preferences.getLocalGroupId()) && StringUtils.equals(mediaItem.getID(), Preferences.getMediaId())) {
            PlayStatus m2463m = SupportFactory.getInstance(BaseApplication.getApplication()).m2463m();
            if (m2463m == PlayStatus.STATUS_PAUSED) {
                CommandCenter.getInstance().execute(new Command(CommandID.RESUME, new Object[0]));
            } else if (m2463m == PlayStatus.STATUS_PLAYING) {
                CommandCenter.getInstance().execute(new Command(CommandID.PAUSE, new Object[0]));
            } else {
                CommandCenter.getInstance().execute(new Command(CommandID.START, new Object[0]));
            }
        } else {

            CommandCenter.getInstance().m4596b(new Command(CommandID.PLAY_GROUP, this.mGroupID, mediaItem));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onMediaItemLongClicked(MediaItem mediaItem) {
        super.onMediaItemLongClicked(mediaItem);
        showRightContextMenu(mediaItem);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected void showRightContextMenu(final MediaItem mediaItem) {
        if (mediaItem.isOnline() || FileUtils.m8419a(mediaItem.getLocalDataSource())) {
            showMediaRightContextMenu(getActivity(), mediaItem, this.mGroupID, new BaseDialog.InterfaceC1064a<MoreOptionalDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.2
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MoreOptionalDialog moreOptionalDialog) {
                    List<MediaItem> mediaItemList = MediaListFragment.this.getMediaItemList();
                    if (!(mediaItemList instanceof AsyncLoadMediaItemList) || ((AsyncLoadMediaItemList) mediaItemList).isLoadFinished()) {
                        mediaItemList.remove(mediaItem);
                        MediaListFragment.this.updateMediaList(mediaItemList);
                    }
                }
            });
        }
    }

    public static void showMediaRightContextMenu(Activity activity, MediaItem mediaItem, String str, BaseDialog.InterfaceC1064a<MoreOptionalDialog> interfaceC1064a) {
        boolean m8419a = FileUtils.m8419a(mediaItem.getLocalDataSource());
        if (mediaItem.isOnline() && !m8419a) {
            new DownloadMenuHandler(activity).m6927a(mediaItem, null);
        } else if (m8419a) {
            PopupsUtils.m6705c(activity, mediaItem);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected boolean isAZSideBarEnable() {
        if (this.mIsEditing || getMediaItemList() == null || getMediaItemList().size() <= 20) {
            return false;
        }
        if (this.mGroupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) || this.mGroupID.equals(MediaStorage.GROUP_ID_FAV) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
            String m2860l = Preferences.m2860l(this.mGroupID);
            return m2860l.equals("title_key") || m2860l.equals("artist_key");
        }
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected boolean needFailedState() {
        return this.mGroupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_RECENTLY_PLAY) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_RECENTLY_ADD) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_FAV) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_DOWNLOAD) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX);
    }

    public void updateAsyncloadMediaItemList(String str, AsyncLoadMediaItemList asyncLoadMediaItemList) {
        if (StringUtils.equals(str, this.mGroupID) && this.mMediaItemList != asyncLoadMediaItemList) {
            asyncLoadMediaItemList.addRef();
            updateMediaList(asyncLoadMediaItemList);
            loadAZKeys(asyncLoadMediaItemList);
            if (this.mNeedCountStastic) {

            }
            if (!asyncLoadMediaItemList.isLoadFinished()) {
                asyncLoadMediaItemList.addLoadFinishedListener(this.mAttachFavOnLoadFinishedListener);
            }
        }
    }

    private SAction countAction() {
        SAction sAction = SAction.ACTION_NONE;
        String page = getPage();
        if (String.valueOf(SPage.PAGE_LOCAL_SONG.getValue()).equals(page)) {
            return SAction.ACTION_AMOUNT_LOCAL_SONG;
        }
        if (String.valueOf(SPage.PAGE_MY_FAVORITE.getValue()).equals(page)) {
            return SAction.ACTION_AMOUNT_FAVORITE;
        }
        return sAction;
    }

    public void updateMediaItemFinished(MediaItem mediaItem) {
        if (getMediaItemList() != null && getMediaItemList().contains(mediaItem)) {
            notifyDataSetChanged();
        }
    }

    private void loadAZKeys(List<MediaItem> list) {
        if (list != null && isViewAccessAble() && (list instanceof AsyncLoadMediaItemList)) {
            AsyncLoadMediaItemList asyncLoadMediaItemList = (AsyncLoadMediaItemList) list;
            if (isAZSideBarEnable()) {
                if (asyncLoadMediaItemList.isLoadFinished()) {
                    updateAZKeys(buildAZKeys(asyncLoadMediaItemList));
                } else {
                    updateAZKeys(buildAZKeys(asyncLoadMediaItemList));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> buildAZKeys(List<MediaItem> list) {
        ArrayList arrayList = new ArrayList(list.size());
        if (this.mGroupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) && "artist_key".equals(Preferences.m2860l(this.mGroupID))) {
            for (MediaItem mediaItem : list) {
                arrayList.add(mediaItem.getArtist());
            }
        } else {
            for (MediaItem mediaItem2 : list) {
                arrayList.add(mediaItem2.getTitle());
            }
        }
        return arrayList;
    }

    public void onDeleteMediaItemsFinished(String str) {
    }

    public void updateMediaLibraryChanged(String str) {
        if (StringUtils.equals(str, this.mGroupID) || str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
            onReloadData();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void playMediaChanged() {
        if (MediaStorage.GROUP_ID_RECENTLY_PLAY.equals(this.mGroupID)) {
            onReloadData();
        }
        if (StringUtils.equals(this.mGroupID, Preferences.getLocalGroupId())) {
            notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        if (StringUtils.equals(this.mGroupID, Preferences.getLocalGroupId())) {
            notifyDataSetChanged();
        }
    }

    public void onScanFinished(Integer num) {
        onReloadData();
    }

    public Collection<MediaItem> getSelectedMediaItems() {
        return this.mSelectMediaItemHashMap.values();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public View getMediaItemView(final MediaItem mediaItem, View view, ViewGroup viewGroup, final int i) {
        View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
        MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) mediaItemView.getTag();
        mediaItemViewHolder.m6969a((StringUtils.equals(this.mOrderBy, MediaStorage.MEDIA_ORDER_BY_FILE_NAME) || StringUtils.equals(this.mOrderBy, MediaStorage.MEDIA_ORDER_BY_FILE_NAME_DESC)) ? FileUtils.getFilename(mediaItem.getLocalDataSource()) : TTTextUtils.validateString(mediaItemView.getContext(), mediaItem.getArtist()), 0, false);
        bindView(mediaItemViewHolder, mediaItem, this.mIsEditing);
        mediaItemViewHolder.m6953l().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                MediaListFragment.this.onMediaItemClicked(mediaItem, i);
            }
        });
        if (MediaStorage.GROUP_ID_RECENTLY_PLAY.equals(this.mGroupID)) {
            bindRecentlyPlayDownloadState(mediaItemViewHolder.m6956i(), getMediaItemList().get(i));
        }
        return mediaItemView;
    }

    private void bindRecentlyPlayDownloadState(IconTextView iconTextView, MediaItem mediaItem) {
        iconTextView.setVisibility(!StringUtils.isEmpty(mediaItem.getLocalDataSource()) ? View.VISIBLE : View.GONE);
        flushDownloadStateView(iconTextView, R.string.icon_download_downloaded);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void flushDownloadStateView(IconTextView iconTextView, int i) {
        iconTextView.setText(i);
        int i2 = COLOR_DOWNLOAD;
        switch (i) {
            case R.string.icon_download_downloaded /* 2131558818 */:
                i2 = COLOR_DOWNLOADED;
                break;
            case R.string.icon_download_downloading /* 2131558819 */:
                i2 = COLOR_DOWNLOADING;
                break;
            case R.string.icon_download_error /* 2131558820 */:
                i2 = COLOR_DOWNLOAD_ERROR;
                break;
        }
        iconTextView.setTextColor(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bindView(MediaItemViewHolder mediaItemViewHolder, MediaItem mediaItem, boolean z) {
        mediaItemViewHolder.m6968a(mediaItem.getErrorStatus());
        boolean r0 = false;
        if (z) {
            mediaItemViewHolder.getConvertView().setSelected(false);
            mediaItemViewHolder.getFlagQualityView().setVisibility(View.GONE);
            mediaItemViewHolder.getMenuView().setVisibility(View.GONE);
            mediaItemViewHolder.getViewPlayState().setVisibility(View.INVISIBLE);
            mediaItemViewHolder.m6953l().setVisibility(View.VISIBLE);
            r0 = this.mSelectMediaItemHashMap.get(Integer.valueOf(mediaItem.hashCode())) != null;
            mediaItemViewHolder.m6953l().setChecked(r0);
            mediaItemViewHolder.getConvertView().setSelected(r0);
            return;
        }
        mediaItemViewHolder.m6953l().setVisibility(View.GONE);
        mediaItemViewHolder.getFlagQualityView().setVisibility(View.VISIBLE);
        mediaItemViewHolder.getMenuView().setVisibility(View.VISIBLE);
        mediaItemViewHolder.getViewPlayState().setVisibility(View.VISIBLE);
        mediaItemViewHolder.updateFlagQuality(mediaItem);
        if (!StringUtils.equals(this.mGroupID, this.mPlayingGroupID) || !StringUtils.equals(this.mPlayingMediaID, mediaItem.getID())) {
            r0 = false;
        }
        mediaItemViewHolder.m6955j().setSelected(r0);
        mediaItemViewHolder.m6954k().setSelected(r0);
        mediaItemViewHolder.getConvertView().setSelected(r0);
        mediaItemViewHolder.m6967a(r0, this.mPlayStatus);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble
    public void selectAll() {
        List<MediaItem> mediaItemList = getMediaItemList();
        this.mSelectMediaItemHashMap.clear();
        for (MediaItem mediaItem : mediaItemList) {
            this.mSelectMediaItemHashMap.put(Integer.valueOf(mediaItem.hashCode()), mediaItem);
        }
        tryNotifySelectedCountChanged();
        notifyDataSetChanged();
        //SUserUtils.m4956a(SAction.ACTION_BATCH_OPERATE_CHOOSE_ALL, SPage.PAGE_NONE);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble
    public void selectNone() {
        this.mSelectMediaItemHashMap.clear();
        tryNotifySelectedCountChanged();
        notifyDataSetChanged();
    }

    public void startEdit() {
        this.mIsEditing = true;
        notifyDataSetChanged();
        updateAZSideBar();
    }

    public void stopEdit() {
        this.mIsEditing = false;
        this.mSelectMediaItemHashMap.clear();
        notifyDataSetChanged();
        updateAZSideBar();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble
    public boolean isEditing() {
        return this.mIsEditing;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble
    public int selectedCount() {
        return this.mSelectMediaItemHashMap.size();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble
    public int totalCount() {
        if (isEmpty()) {
            return 0;
        }
        return getMediaItemList().size();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble
    public void addTo() {
        PopupsUtils.m6726a(getActivity(), Cache.getInstance().m3155k(), this.mSelectMediaItemHashMap.values(), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.6
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                MediaListFragment.this.tryNotifyStopEditRequested();
            }
        }, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.7
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(EditTextDialog editTextDialog) {
                MediaListFragment.this.tryNotifyStopEditRequested();
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble
    public void sendTo() {
        int size = this.mSelectMediaItemHashMap.size();
        if (size > 0) {
            File[] fileArr = new File[size];
            int i = 0;
            Iterator<MediaItem> it = this.mSelectMediaItemHashMap.values().iterator();
            while (true) {
                int i2 = i;
                if (it.hasNext()) {
                    MediaItem next = it.next();
                    if (next.getLocalDataSource() != null) {
                        fileArr[i2] = new File(next.getLocalDataSource());
                        i = i2 + 1;
                    } else {
                        i = i2;
                    }
                } else {
                    BlueToothUtils.m8308a(getActivity(), fileArr);
                    tryNotifyStopEditRequested();
                    return;
                }
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble
    public void remove() {
        PopupsUtils.m6730a(getActivity(), this.mSelectMediaItemHashMap.values(), this.mGroupID, new BaseDialog.InterfaceC1064a<MoreOptionalDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.8
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MoreOptionalDialog moreOptionalDialog) {
                //SUserUtils.m4956a(SAction.ACTION_BATCH_OPERATE_REMOVE_SURE, SPage.PAGE_NONE);
                MediaListFragment.this.getMediaItemList().removeAll(MediaListFragment.this.mSelectMediaItemHashMap.values());
                MediaListFragment.this.updateMediaList(MediaListFragment.this.getMediaItemList());
                MediaListFragment.this.tryNotifyStopEditRequested();
            }
        });
    }

    public void removeAll() {
        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_MEDIA_ITEM_LIST, this.mGroupID, getMediaItemList(), false));
    }

    @Override // com.sds.android.ttpod.fragment.main.list.ISearchAble
    public void search() {
        //SUserUtils.m4956a(SAction.ACTION_LOCAL_SEARCH, SPage.PAGE_NONE);
        startActivity(new Intent(getActivity(), MediaItemSearchActivity.class).putExtra(AbsMediaListFragment.KEY_GROUP_ID, this.mGroupID).putExtra("origin", getString(R.string.music)));
    }

    public void order() {
        PopupsUtils.m6750a(getActivity(), this.mGroupID, new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.list.MediaListFragment.9
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                MediaListFragment.this.onReloadData();
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IOrderAble
    public void order(int i) {
        PopupsUtils.m6720a(this.mGroupID, i);
        SAction orderAction = GroupListFragment.orderAction(i);
        if (orderAction != null) {
            //SUserUtils.m4956a(orderAction, SPage.PAGE_NONE);
        }
        onReloadData();
    }

    private void tryNotifySelectedCountChanged() {
        if (this.mEditRequestListener != null) {
            this.mEditRequestListener.onSelectedCountChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryNotifyStopEditRequested() {
        if (this.mEditRequestListener != null) {
            this.mEditRequestListener.onStopEditRequested();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onKeyPressed(int i, KeyEvent keyEvent) {
        super.onKeyPressed(i, keyEvent);
        if (getUserVisibleHint() && i == 84) {
            search();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        super.onBackPressed();
        List<MediaItem> mediaItemList = getMediaItemList();
        if (mediaItemList instanceof AsyncLoadMediaItemList) {
            ((AsyncLoadMediaItemList) mediaItemList).removeLoadFinishedListener(this.mAttachFavOnLoadFinishedListener);
            ((AsyncLoadMediaItemList) mediaItemList).removeLoadFinishedListener(this.mOnBuildAZKeysLoadFinishedListener);
        }
    }
}
