package com.sds.android.ttpod.framework.p106a;

import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.api.OnlineMediaItemAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.a.m */
/* loaded from: classes.dex */
public class OnlineMediaUtils {

    /* compiled from: OnlineMediaUtils.java */
    /* renamed from: com.sds.android.ttpod.framework.a.m$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1790a<D> {
        /* renamed from: a */
        void mo4039a(D d);
    }

    /* renamed from: a */
    public static void m4675a(List<Long> list, InterfaceC1790a<List<MediaItem>> interfaceC1790a) {
        m4674a(list, interfaceC1790a, false);
    }

    /* renamed from: b */
    public static void m4672b(List<Long> list, InterfaceC1790a<List<MediaItem>> interfaceC1790a) {
        m4674a(list, interfaceC1790a, Preferences.m2997aJ());
    }

    /* renamed from: a */
    private static void m4674a(List<Long> list, final InterfaceC1790a<List<MediaItem>> interfaceC1790a, final boolean z) {
        if (list == null) {
            throw new IllegalArgumentException("ids should not be null");
        }
        TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask<List<Long>, OnlineMediaItemsResult>(list) { // from class: com.sds.android.ttpod.framework.a.m.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public OnlineMediaItemsResult inBackground(List<Long> list2) {
                return z ? OnlineMediaItemAPI.m8864b(list2).execute() : OnlineMediaItemAPI.m8867a(list2).execute();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            public void postExecute(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                ArrayList arrayList = new ArrayList();
                for (OnlineMediaItem onlineMediaItem : dataList) {
                    if (onlineMediaItem != null) {
                        arrayList.add(MediaItemUtils.m4716a(onlineMediaItem));
                    }
                }
                if (interfaceC1790a != null) {
                    interfaceC1790a.mo4039a(arrayList);
                }
            }
        });
    }

    /* renamed from: a */
    public static void m4679a(long j, List<MediaItem> list, String str) {
        m4678a(j, list, str, 0L);
    }

    /* renamed from: a */
    public static void m4678a(long j, List<MediaItem> list, String str, long j2) {
        MediaItem mediaItem;
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("mediaItems should not be null and not be empty");
        }
        MediaItem mediaItem2 = list.get(0);
        Iterator<MediaItem> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                mediaItem = mediaItem2;
                break;
            }
            mediaItem = it.next();
            Long songID = mediaItem.getSongID();
            if (songID != null && j == songID.longValue()) {
                break;
            }
        }
        if (StringUtils.equals(mediaItem.getID(), Preferences.getMediaId()) && PlayStatus.STATUS_STOPPED != SupportFactory.getInstance(BaseApplication.getApplication()).m2463m()) {
            if (PlayStatus.STATUS_PLAYING == SupportFactory.getInstance(BaseApplication.getApplication()).m2463m()) {
                CommandCenter.getInstance().execute(new Command(CommandID.PAUSE, new Object[0]));
                return;
            }
            CommandCenter.getInstance().execute(new Command(CommandID.RESUME, new Object[0]));
            CommandCenter.getInstance().execute(new Command(CommandID.ADD_LISTENER_COUNT, Long.valueOf(j2)));
            return;
        }
        CommandCenter.getInstance().execute(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, list, str));
        CommandCenter.getInstance().execute(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, mediaItem));
        CommandCenter.getInstance().execute(new Command(CommandID.ADD_LISTENER_COUNT, Long.valueOf(j2)));
    }

    /* renamed from: a */
    public static void m4673a(List<MediaItem> list, String str, long j) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("mediaItems should not be null and not be empty");
        }
        CommandCenter.getInstance().execute(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, list, str));
        CommandCenter.getInstance().execute(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list.get(0)));
        CommandCenter.getInstance().execute(new Command(CommandID.ADD_LISTENER_COUNT, Long.valueOf(j)));
    }

    /* renamed from: a */
    public static List<Long> m4676a(List<MediaItem> list) {
        if (list == null) {
            throw new IllegalArgumentException("mediaItems should not be null");
        }
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : list) {
            arrayList.add(mediaItem.getSongID());
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m4677a(AlbumItem albumItem, RequestCallback<OnlineMediaItemsResult> requestCallback) {
        if (requestCallback != null) {
            if (albumItem == null) {
                requestCallback.onRequestFailure(new OnlineMediaItemsResult());
                return;
            }
            List<Long> songIds = albumItem.getSongIds();
            if (songIds.size() == 0) {
                requestCallback.onRequestFailure(new OnlineMediaItemsResult());
            } else if (Preferences.m2997aJ()) {
                OnlineMediaItemAPI.m8864b(songIds).m8544a(requestCallback);
            } else {
                OnlineMediaItemAPI.m8867a(songIds).m8544a(requestCallback);
            }
        }
    }
}
