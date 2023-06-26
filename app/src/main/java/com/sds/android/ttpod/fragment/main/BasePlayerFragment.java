package com.sds.android.ttpod.fragment.main;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.p088a.ListDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class BasePlayerFragment extends BaseFragment {
    private static final float BUFFER_COMPLETE_PERCENT = 0.99f;
    private static final String LOG_TAG = "BasePlayerFragment";
    private static final int UPDATE_PLAY_POSITION_INTERVAL = 50;
    private static final int UPDATE_PLAY_POSITION_MSG = 1;
    private Bitmap mArtistBitmap;
    private String mArtistPath;
    private Lyric mLyric;
    private String mMediaID;
    private Handler mPlayPositionRefreshHandler = new Handler() { // from class: com.sds.android.ttpod.fragment.main.BasePlayerFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (BasePlayerFragment.this.mPlayPositionRefreshHandler != null) {
                BasePlayerFragment.this.updatePlayPosition();
                BasePlayerFragment.this.mPlayPositionRefreshHandler.sendEmptyMessageDelayed(1, 50L);
            }
        }
    };

    public abstract void updatePlayMediaInfo();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "playMediaChanged", new Class[0]));
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.m8375a(cls, "updatePlayMediaInfo", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_SEARCH_PICTURE_STATE, ReflectUtils.m8375a(cls, "updateSearchPictureState", SearchStatus.class, List.class, String.class, Bitmap.class));
        map.put(CommandID.SWITCH_ARTIST_PICTURE, ReflectUtils.m8375a(cls, "switchArtistPicture", String.class, String.class, Bitmap.class));
        map.put(CommandID.UPDATE_SEARCH_LYRIC_STATE, ReflectUtils.m8375a(cls, "updateSearchLyricState", SearchStatus.class, List.class, String.class, Lyric.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPostViewCreated(View view, Bundle bundle) {
        super.onPostViewCreated(view, bundle);
        String m3164g = Cache.getInstance().m3164g();
        flushArtistBitmap(ImageCacheUtils.m4745a(m3164g, DisplayUtils.m7225c(), DisplayUtils.m7224d(), true), m3164g);
    }

    public void playMediaChanged() {
        String id = Cache.getInstance().getCurrentPlayMediaItem().getID();
        boolean m8344a = StringUtils.equals(id, this.mMediaID);
        LogUtils.debug(LOG_TAG, "playMediaChanged lookLyricPic fragment=%s will clear lyric pic info equal=%b %s %s", getClass().getSimpleName(), Boolean.valueOf(m8344a), this.mMediaID, id);
        if (!m8344a) {
            this.mMediaID = id;
            this.mLyric = null;
            this.mArtistBitmap = null;
            this.mArtistPath = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLyric(Lyric lyric) {
        this.mLyric = lyric;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setArtistBitmap(Bitmap bitmap, String str) {
        this.mArtistBitmap = bitmap;
        this.mArtistPath = str;
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (playStatus == PlayStatus.STATUS_PLAYING) {
            startUpdatePlayPosition();
        } else if (Cache.getInstance().getCurrentPlayMediaItem().isOnline()) {
            if (SupportFactory.m2397a(BaseApplication.getApplication()).m2464l() > BUFFER_COMPLETE_PERCENT) {
                stopUpdatePlayPosition();
            }
        } else {
            stopUpdatePlayPosition();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void artistBitmapLoadFinished() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void artistBitmapSearchStarted() {
    }

    protected void artistBitmapSearchFailed() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void artistBitmapDownloadStarted() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void artistBitmapDownloadError() {
    }

    protected void artistBitmapNetError() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void lyricLoadFinished() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void lyricSearchStarted() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void lyricSearchFailed() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void lyricDownloadStarted() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void lyricDownloadError() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void lyricNetError() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void lyricSearchStop() {
    }

    private void flushArtistBitmap(Bitmap bitmap, String str) {
        if (StringUtils.equals(str, this.mArtistPath)) {
            LogUtils.debug(LOG_TAG, "flushArtistBitmap lookLyricPic fragment=%s equal Path=%s", getClass().getSimpleName(), str);
            return;
        }
        setArtistBitmap(bitmap, str);
        artistBitmapLoadFinished();
    }

    public void updateSearchPictureState(SearchStatus searchStatus, List<ResultData> list, String str, Bitmap bitmap) {
        String m2184a = (list == null || list.isEmpty()) ? "noResult" : list.get(0).m2184a();
        String id = Cache.getInstance().getCurrentPlayMediaItem().getID();
        if (searchStatus == SearchStatus.SEARCH_LOCAL_FINISHED || searchStatus == SearchStatus.SEARCH_DOWNLOAD_FINISHED) {
            m2184a = Cache.getInstance().m3164g();
        }
        if (StringUtils.equals(id, str)) {
            this.mMediaID = str;
            switch (searchStatus) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    flushArtistBitmap(bitmap, m2184a);
                    return;
                case SEARCH_LOCAL_FAILURE:
                    flushArtistBitmap(null, null);
                    return;
                case SEARCH_ONLINE_STARTED:
                    artistBitmapSearchStarted();
                    return;
                case SEARCH_ONLINE_FAILURE:
                    artistBitmapSearchFailed();
                    return;
                case SEARCH_ONLINE_NET_EXCEPTION:
                    artistBitmapNetError();
                    return;
                case SEARCH_ONLINE_SETTING_EXCEPTION:
                case SEARCH_ONLINE_FINISHED:
                default:
                    return;
                case SEARCH_DOWNLOAD_FAILURE:
                    artistBitmapDownloadError();
                    return;
                case SEARCH_DOWNLOAD_STARTED:
                    artistBitmapDownloadStarted();
                    return;
            }
        }
    }

    public void switchArtistPicture(String str, String str2, Bitmap bitmap) {
        if (StringUtils.equals(Cache.getInstance().getCurrentPlayMediaItem().getID(), str)) {
            switchArtistPicture(bitmap, str2);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CommandCenter.getInstance().execute(new Command(CommandID.RESUME_IMAGE_SWITCHER, new Object[0]));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CommandCenter.getInstance().execute(new Command(CommandID.PAUSE_IMAGE_SWITCHER, new Object[0]));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void switchArtistPicture(Bitmap bitmap, String str) {
        setArtistBitmap(bitmap, str);
    }

    public void updateSearchLyricState(SearchStatus searchStatus, final List<ResultData> list, String str, Lyric lyric) {
        LogUtils.debug(LOG_TAG, "lookLyricPic fragment updateSearchLyricState state=%s title=%s", searchStatus.name(), (list == null || list.isEmpty()) ? "noResult" : list.get(0).m2184a());
        final MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (StringUtils.equals(m3225N.getID(), str)) {
            switch (searchStatus) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    setLyric(lyric);
                    lyricLoadFinished();
                    return;
                case SEARCH_LOCAL_FAILURE:
                default:
                    return;
                case SEARCH_ONLINE_STARTED:
                    lyricSearchStarted();
                    return;
                case SEARCH_ONLINE_FAILURE:
                    lyricSearchFailed();
                    return;
                case SEARCH_ONLINE_NET_EXCEPTION:
                    lyricNetError();
                    return;
                case SEARCH_ONLINE_SETTING_EXCEPTION:
                    lyricSearchStop();
                    return;
                case SEARCH_DOWNLOAD_FAILURE:
                    lyricDownloadError();
                    return;
                case SEARCH_DOWNLOAD_STARTED:
                    lyricDownloadStarted();
                    return;
                case SEARCH_ONLINE_FINISHED:
                    Object[] objArr = new Object[3];
                    objArr[0] = Boolean.valueOf(list != null);
                    objArr[1] = Boolean.valueOf(this instanceof PortraitPlayerFragment);
                    objArr[2] = Boolean.valueOf(getUserVisibleHint());
                    LogUtils.debug(LOG_TAG, "updateSearchLyricState SEARCH_ONLINE_FINISHED lookLyricPic searchList!=null_%b isPortPlayFrag=%b userVisible=%b", objArr);
                    if (list != null && (this instanceof PortraitPlayerFragment) && getUserVisibleHint()) {
                        DebugUtils.m8424a((Collection) list, "searchList");
                        showLyricPictureDownloadSelectDialog(R.string.search_lyric, list, new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.BasePlayerFragment.2
                            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                            /* renamed from: a */
                            public void mo5409a(ActionItem actionItem, int i) {
                                DebugUtils.m8423a((Object[]) ((ResultData) list.get(i)).m2179c(), "items");
                                CommandCenter.getInstance().m4596b(new Command(CommandID.START_DOWNLOAD_SEARCH_LYRIC, ((ResultData) list.get(i)).m2179c()[0], m3225N));
                            }
                        }, new DialogInterface.OnCancelListener() { // from class: com.sds.android.ttpod.fragment.main.BasePlayerFragment.3
                            @Override // android.content.DialogInterface.OnCancelListener
                            public void onCancel(DialogInterface dialogInterface) {
                                BasePlayerFragment.this.lyricLoadFinished();
                            }
                        });
                        return;
                    }
                    return;
            }
        }
    }

    public Lyric getCurrentLyric() {
        return this.mLyric;
    }

    public Bitmap getCurrentArtistBitmap() {
        return this.mArtistBitmap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updatePlayPosition() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startUpdatePlayPosition() {
        if (!this.mPlayPositionRefreshHandler.hasMessages(1)) {
            this.mPlayPositionRefreshHandler.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stopUpdatePlayPosition() {
        this.mPlayPositionRefreshHandler.removeMessages(1);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mPlayPositionRefreshHandler.removeCallbacksAndMessages(null);
    }

    private void showLyricPictureDownloadSelectDialog(int i, List<ResultData> list, final ActionItem.InterfaceC1135b interfaceC1135b, final DialogInterface.OnCancelListener onCancelListener) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(new ActionItem(i2, 0, list.get(i2).toString()));
        }
        final ListDialog listDialog = new ListDialog(getActivity(), arrayList, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
        listDialog.m7254b(R.string.cancel, new BaseDialog.InterfaceC1064a() { // from class: com.sds.android.ttpod.fragment.main.BasePlayerFragment.4
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a */
            public void mo2038a(Object obj) {
                onCancelListener.onCancel(listDialog);
            }
        });
        listDialog.setTitle(i);
        listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.BasePlayerFragment.5
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i3) {
                interfaceC1135b.mo5409a(actionItem, i3);
                listDialog.dismiss();
            }
        });
        listDialog.show();
        LogUtils.debug(LOG_TAG, "showLyricPictureDownloadSelectDialog lookLyricPic choose_size=%d", Integer.valueOf(arrayList.size()));
        listDialog.setOnCancelListener(onCancelListener);
    }

    public String getCurrentArtistBitmapPath() {
        return this.mArtistPath;
    }
}
