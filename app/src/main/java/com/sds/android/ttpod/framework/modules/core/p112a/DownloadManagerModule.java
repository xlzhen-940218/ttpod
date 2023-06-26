package com.sds.android.ttpod.framework.modules.core.p112a;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.p055a.OnlineMediaItemAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.core.download.Task;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.NotificationUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaItemUtils;
import com.sds.android.ttpod.framework.storage.database.SqliteDb;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportCallback;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@ObserverCommandID(m4563a = {CommandID.DOWNLOAD_STATE_CHANGED, CommandID.NET_WORK_TYPE_CHANGED})
/* renamed from: com.sds.android.ttpod.framework.modules.core.a.a */
/* loaded from: classes.dex */
public final class DownloadManagerModule extends BaseModule {
    public static final int ONLY_COMPLETED_TASKS = 0;
    public static final int ONLY_UNCOMPLETED_TASKS = 1;

    /* renamed from: d */
    private static final ReentrantLock f5790d = new ReentrantLock();

    /* renamed from: f */
    private static int f5791f = -1;

    /* renamed from: c */
    private int f5794c;

    /* renamed from: a */
    private final List<DownloadTaskInfo> f5792a = new ArrayList();

    /* renamed from: b */
    private ConcurrentHashMap<String, ConcurrentHashMap<MediaItem, DownloadTaskInfo>> f5793b = new ConcurrentHashMap<>();

    /* renamed from: e */
    private SupportCallback f5795e = new SupportCallback() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.1
        @Override // com.sds.android.ttpod.framework.support.SupportCallback
        /* renamed from: a */
        public void mo2448a() {
            DownloadManagerModule.f5790d.lock();
            DownloadManagerModule.this.f5792a.clear();
            DownloadManagerModule.this.f5792a.addAll(SupportFactory.m2397a(DownloadManagerModule.sContext).m2487a(new int[]{DownloadTaskInfo.TYPE_AUDIO.intValue()}));
            for (DownloadTaskInfo downloadTaskInfo : DownloadManagerModule.this.f5792a) {
                if (!DownloadManagerModule.this.f5793b.containsKey(downloadTaskInfo.getGroupId())) {
                    DownloadManagerModule.this.f5793b.put(downloadTaskInfo.getGroupId(), new ConcurrentHashMap());
                }
                if (downloadTaskInfo.getTag() != null) {
                    ((ConcurrentHashMap) DownloadManagerModule.this.f5793b.get(downloadTaskInfo.getGroupId())).put((MediaItem) downloadTaskInfo.getTag(), downloadTaskInfo);
                }
            }
            DownloadManagerModule.f5790d.unlock();
            DownloadManagerModule.this.m4458d();
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.DOWNLOAD_MANAGER;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        SupportFactory.m2397a(sContext).mo2497a(this.f5795e);
        m4458d();
        this.f5794c = 0;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.ADD_DOWNLOAD_TASK, ReflectUtils.m8375a(cls, "addDownloadTask", DownloadTaskInfo.class));
        map.put(CommandID.ASYN_ADD_DOWNLOAD_TASK_LIST, ReflectUtils.m8375a(cls, "asynAddDownloadTaskList", List.class, Boolean.class));
        map.put(CommandID.CANCEL_DOWNLOAD_TASK, ReflectUtils.m8375a(cls, "cancelDownloadTask", DownloadTaskInfo.class));
        map.put(CommandID.ADD_DOWNLOAD_TASK_BY_GROUP, ReflectUtils.m8375a(cls, "addDownloadTaskByGroup", String.class, Boolean.class));
        map.put(CommandID.QUERY_DOWNLOADING_INFO_BY_GROUP, ReflectUtils.m8375a(cls, "queryDownloadTaskInfoByGroup", String.class));
        map.put(CommandID.DELETE_DOWNLOAD_TASK, ReflectUtils.m8375a(cls, "deleteDownloadTask", DownloadTaskInfo.class, Boolean.class));
        map.put(CommandID.GET_TASK_LIST_WITH_STATE, ReflectUtils.m8375a(cls, "getTaskListWithState", Integer.class));
        map.put(CommandID.GET_TASK_LIST_WITH_TYPE, ReflectUtils.m8375a(cls, "getTaskList", Integer.class));
        map.put(CommandID.DELETE_ALL_FINISHED_DOWNLOAD_TASK, ReflectUtils.m8375a(cls, "deleteAllFinishedTask", Integer.class, Boolean.class));
        map.put(CommandID.CLEAR_COMPLETE_TASK_COUNT, ReflectUtils.m8375a(cls, "clearCompleteCount", new Class[0]));
        map.put(CommandID.GET_TASK_DOWNLOADED_LENGTH, ReflectUtils.m8375a(cls, "getTaskDownloadedLength", DownloadTaskInfo.class));
        map.put(CommandID.GET_TOTAL_DOWNLOAD_FILE_SIZE, ReflectUtils.m8375a(cls, "getTotalEvaluatedDownloadFileSizeInByte", List.class, AudioQuality.class));
        map.put(CommandID.DOWNLOAD_STATE_CHANGED, ReflectUtils.m8375a(cls, "downloadStateChanged", DownloadTaskInfo.class, Task.EnumC0579b.class));
        map.put(CommandID.NET_WORK_TYPE_CHANGED, ReflectUtils.m8375a(cls, "netWorkTypeChanged", new Class[0]));
    }

    public void netWorkTypeChanged() {
        LogUtils.error("DownloadManagerModule", "netWorkTypeChanged");
        m4458d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m4458d() {
        final int m8476d = EnvironmentUtils.DeviceConfig.m8476d();
        LogUtils.error("DownloadManagerModule", "handleNetWorkState = " + m8476d);
        if (f5791f != m8476d) {
            f5791f = m8476d;
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.3
                @Override // java.lang.Runnable
                public void run() {
                    if (m8476d != 2) {
                        DownloadManagerModule.f5790d.lock();
                        for (int size = DownloadManagerModule.this.f5792a.size() - 1; size >= 0; size--) {
                            DownloadManagerModule.this.cancelDownloadTask((DownloadTaskInfo) DownloadManagerModule.this.f5792a.get(size));
                        }
                        DownloadManagerModule.f5790d.unlock();
                        return;
                    }
                    List<DownloadTaskInfo> taskListWithState = DownloadManagerModule.this.getTaskListWithState(1);
                    Collections.reverse(taskListWithState);
                    DownloadManagerModule.this.m4466a(taskListWithState, (Boolean) false);
                }
            }, new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.4
                @Override // java.lang.Runnable
                public void run() {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_DOWNLOAD_TASK_LIST_RELOADED, new Object[0]), ModuleID.DOWNLOAD_MANAGER);
                }
            });
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onPreDestroy() {
        super.onPreDestroy();
        SupportFactory.m2397a(sContext).m2482b(this.f5795e);
        this.f5792a.clear();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onDestroy() {
        super.onDestroy();
        this.f5794c = 0;
    }

    public Long getTotalEvaluatedDownloadFileSizeInByte(List<MediaItem> list, AudioQuality audioQuality) {
        int size = list.size();
        return 1 == size ? Long.valueOf(OnlineMediaItemUtils.m4680c((OnlineMediaItem) JSONUtils.fromJson(list.get(0).getExtra(), OnlineMediaItem.class), audioQuality).intValue() - m4467a(list, audioQuality)) : Long.valueOf((m4473a(audioQuality) * size) - m4467a(list, audioQuality));
    }

    /* renamed from: a */
    private long m4473a(AudioQuality audioQuality) {
        if (AudioQuality.COMPRESSED == audioQuality) {
            return 1572864L;
        }
        if (AudioQuality.STANDARD == audioQuality) {
            return 5242880L;
        }
        if (AudioQuality.SUPER == audioQuality) {
            return 12582912L;
        }
        if (AudioQuality.LOSSLESS == audioQuality) {
            return 31457280L;
        }
        LogUtils.debug("DownloadManagerModule", "audioQuality is not available!");
        return 0L;
    }

    /* renamed from: a */
    private long m4467a(List<MediaItem> list, AudioQuality audioQuality) {
        long j = 0;
        if (1 == list.size()) {
            MediaItem mediaItem = list.get(0);
            if (!StringUtils.isEmpty(mediaItem.getLocalDataSource())) {
                return mediaItem.getSize();
            }
        } else {
            for (MediaItem mediaItem2 : list) {
                if (!StringUtils.isEmpty(mediaItem2.getLocalDataSource())) {
                    j += m4473a(audioQuality);
                }
            }
        }
        return j;
    }

    public void addDownloadTask(DownloadTaskInfo downloadTaskInfo) {
        m4472a(downloadTaskInfo.getType());
        if (FileUtils.m8414b(downloadTaskInfo.getSavePath())) {
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_ADD_DOWNLOAD_TASK_ERROR, downloadTaskInfo), ModuleID.DOWNLOAD_MANAGER);
            return;
        }
        m4463b(downloadTaskInfo);
        if (m4475a(downloadTaskInfo)) {
            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_ADD_DOWNLOAD_TASK_DATABASE, downloadTaskInfo), ModuleID.DOWNLOAD_MANAGER);
        }
    }

    /* renamed from: a */
    private boolean m4475a(DownloadTaskInfo downloadTaskInfo) {
        List<DownloadTaskInfo> m3124a = SqliteDb.m3124a(sContext, downloadTaskInfo.getSavePath());
        return (m3124a == null || m3124a.isEmpty()) ? false : true;
    }

    /* renamed from: b */
    private void m4463b(DownloadTaskInfo downloadTaskInfo) {
        f5790d.lock();
        if (!this.f5792a.contains(downloadTaskInfo)) {
            downloadTaskInfo.setGroupId(downloadTaskInfo.getGroupId() != null ? downloadTaskInfo.getGroupId() : MediaStorage.GROUP_ID_DOWNLOAD);
            downloadTaskInfo.setState(0);
            this.f5792a.add(downloadTaskInfo);
            if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
                if (this.f5793b.get(downloadTaskInfo.getGroupId()) == null) {
                    this.f5793b.put(downloadTaskInfo.getGroupId(), new ConcurrentHashMap<>());
                }
                if (downloadTaskInfo.getTag() != null) {
                    this.f5793b.get(downloadTaskInfo.getGroupId()).put((MediaItem) downloadTaskInfo.getTag(), downloadTaskInfo);
                }
            }
            SupportFactory.m2397a(sContext).m2496a(downloadTaskInfo);
            LogUtils.error("DownloadManagerModule", "addTask");
            m4455e(downloadTaskInfo);
        }
        f5790d.unlock();
    }

    public Map queryDownloadTaskInfoByGroup(String str) {
        return this.f5793b.containsKey(str) ? new ConcurrentHashMap(this.f5793b.get(str)) : new ConcurrentHashMap();
    }

    public void asynAddDownloadTaskList(final List<DownloadTaskInfo> list, final Boolean bool) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.5
            @Override // java.lang.Runnable
            public void run() {
                DownloadManagerModule.this.m4466a(list, bool);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4466a(List<DownloadTaskInfo> list, Boolean bool) {
        boolean z;
        if (list != null && !list.isEmpty()) {
            boolean z2 = false;
            for (DownloadTaskInfo downloadTaskInfo : list) {
                if (FileUtils.m8414b(downloadTaskInfo.getSavePath())) {
                    if (bool.booleanValue()) {
                        FileUtils.m8404h(downloadTaskInfo.getSavePath());
                    }
                    z = true;
                } else {
                    z = z2;
                }
                z2 = z;
            }
            if (!bool.booleanValue() && z2) {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_ADD_DOWNLOAD_TASK_LIST_ERROR, list), ModuleID.DOWNLOAD_MANAGER);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (DownloadTaskInfo downloadTaskInfo2 : list) {
                boolean m4475a = m4475a(downloadTaskInfo2);
                m4463b(downloadTaskInfo2);
                if (!m4475a) {
                    arrayList.add(downloadTaskInfo2);
                }
            }
            if (!arrayList.isEmpty()) {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_ADD_DOWNLOAD_TASK_LIST_DATABASE, arrayList), ModuleID.DOWNLOAD_MANAGER);
            }
        }
    }

    public void addDownloadTaskByGroup(final String str, final Boolean bool) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.6
            @Override // java.lang.Runnable
            public void run() {
                List<MediaItem> queryMediaItemList = MediaStorage.queryMediaItemList(BaseApplication.getApplication(), str, Preferences.m2860l(str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) ? MediaStorage.GROUP_ID_FAV : str));
                ArrayList arrayList = new ArrayList(queryMediaItemList.size());
                for (MediaItem mediaItem : queryMediaItemList) {
                    DownloadTaskInfo m4761a = DownloadUtils.m4761a(mediaItem, Preferences.m3058L());
                    m4761a.setGroupId(str);
                    arrayList.add(m4761a);
                }
                DownloadManagerModule.this.asynAddDownloadTaskList(arrayList, bool);
            }
        });
    }

    public void cancelDownloadTask(DownloadTaskInfo downloadTaskInfo) {
        MediaItem mediaItem;
        f5790d.lock();
        LogUtils.error("DownloadManagerModule", "cancelDownloadTask");
        this.f5792a.remove(downloadTaskInfo);
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType()) && (mediaItem = (MediaItem) downloadTaskInfo.getTag()) != null && this.f5793b.containsKey(mediaItem.getGroupID())) {
            this.f5793b.get(mediaItem.getGroupID()).remove(mediaItem);
        }
        f5790d.unlock();
        DownloadTaskInfo m2481b = SupportFactory.m2397a(sContext).m2481b(downloadTaskInfo);
        if (m2481b != null) {
            m4474a(m2481b, downloadTaskInfo);
            m4455e(downloadTaskInfo);
        }
    }

    public void deleteDownloadTask(final DownloadTaskInfo downloadTaskInfo, Boolean bool) {
        MediaItem mediaItem;
        if (downloadTaskInfo.getState() != null && 4 != downloadTaskInfo.getState().intValue()) {
            SupportFactory.m2397a(sContext).m2475c(downloadTaskInfo);
        }
        f5790d.lock();
        LogUtils.error("DownloadManagerModule", "cancelDownloadTask");
        this.f5792a.remove(downloadTaskInfo);
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType()) && (mediaItem = (MediaItem) downloadTaskInfo.getTag()) != null && this.f5793b.containsKey(mediaItem.getGroupID())) {
            this.f5793b.get(mediaItem.getGroupID()).remove(mediaItem);
        }
        f5790d.unlock();
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
            m4482a((TaskInfo) downloadTaskInfo);
        }
        if (bool.booleanValue()) {
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.7
                @Override // java.lang.Runnable
                public void run() {
                    if (FileUtils.m8414b(downloadTaskInfo.getSavePath())) {
                        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
                            DownloadManagerModule.this.m4457d(downloadTaskInfo);
                        }
                        FileUtils.m8404h(downloadTaskInfo.getSavePath());
                    }
                }
            });
        }
    }

    public Integer getTaskDownloadedLength(DownloadTaskInfo downloadTaskInfo) {
        int m2472d = SupportFactory.m2397a(sContext).m2472d(downloadTaskInfo);
        if (m2472d != 0) {
            downloadTaskInfo.setDownloadLength(m2472d);
        }
        return Integer.valueOf(downloadTaskInfo.getDownloadLength());
    }

    public List<DownloadTaskInfo> getTaskListWithState(Integer num) {
        if (num == null) {
            num = 0;
        }
        return m4471a((Integer) null, num.intValue());
    }

    public List<DownloadTaskInfo> getTaskList(Integer num) {
        m4472a(num);
        return m4471a(num, 1);
    }

    public void deleteAllFinishedTask(final Integer num, final Boolean bool) {
        if (DownloadTaskInfo.TYPE_AUDIO.equals(num)) {
            CommandCenter.getInstance().execute(new Command(CommandID.DELETE_GROUP, MediaStorage.GROUP_ID_DOWNLOAD));
        }
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.8
            @Override // java.lang.Runnable
            public void run() {
                if (DownloadTaskInfo.TYPE_AUDIO.equals(num)) {
                    CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_ASYNCLOAD_MEDIA_ITEM_LIST, MediaStorage.GROUP_ID_DOWNLOAD, Preferences.m2860l(MediaStorage.GROUP_ID_DOWNLOAD)));
                }
                for (DownloadTaskInfo downloadTaskInfo : DownloadManagerModule.this.m4471a(num, 0)) {
                    SqliteDb.m3119b(DownloadManagerModule.sContext, downloadTaskInfo.getSavePath());
                    if (bool.booleanValue()) {
                        if (DownloadTaskInfo.TYPE_AUDIO.equals(num)) {
                            DownloadManagerModule.this.m4457d(downloadTaskInfo);
                        }
                        FileUtils.m8404h(downloadTaskInfo.getSavePath());
                    }
                }
                NotificationUtils.m4696a(15121730);
                DownloadManagerModule.this.f5794c = 0;
            }
        });
    }

    public void clearCompleteCount() {
        this.f5794c = 0;
    }

    public void downloadStateChanged(DownloadTaskInfo downloadTaskInfo, Task.EnumC0579b enumC0579b) {
        DownloadTaskInfo m4470a = m4470a(downloadTaskInfo.getSavePath());
        if (m4470a != null) {
            m4474a(downloadTaskInfo, m4470a);
            f5790d.lock();
            if (4 == m4470a.getState().intValue()) {
                this.f5792a.remove(m4470a);
                if (m4470a.getTag() != null && DownloadTaskInfo.TYPE_AUDIO == m4470a.getType()) {
                    this.f5793b.get(m4470a.getGroupId()).remove(m4470a.getTag());
                }
            } else if (m4470a.getState().intValue() == 5 && enumC0579b != null) {
                CommandCenter.getInstance().m4595b(new Command(CommandID.DOWNLOAD_TASK_FAILED, m4470a, enumC0579b), ModuleID.DOWNLOAD_MANAGER);
                this.f5792a.remove(m4470a);
                if (m4470a.getTag() != null && DownloadTaskInfo.TYPE_AUDIO == m4470a.getType()) {
                    this.f5793b.get(m4470a.getGroupId()).remove(m4470a.getTag());
                }
                if (DownloadTaskInfo.TYPE_AUDIO.equals(m4470a.getType()) && ((enumC0579b == Task.EnumC0579b.URL_REQUEST_FAILED || enumC0579b == Task.EnumC0579b.URL_RESPONED_FAILED) && !m4470a.isUrlUpdated())) {
                    if (enumC0579b == Task.EnumC0579b.URL_RESPONED_FAILED) {
                        SupportFactory.m2397a(sContext).m2475c(m4470a);
                        m4470a.setFileLength(0);
                        m4470a.setDownloadLength(0);
                    }
                    m4470a.setUrlUpdated(true);
                    m4470a.setState(0);
                    m4459c(m4470a);
                }
            }
            f5790d.unlock();
            m4455e(m4470a);
        }
        if (4 == downloadTaskInfo.getState().intValue()) {
            if (StringUtils.equals(MediaStorage.GROUP_ID_ALL_LOCAL, Preferences.getLocalGroupId()) || StringUtils.equals(DownloadUtils.m4762a(downloadTaskInfo), Preferences.getLocalGroupId())) {
                SupportFactory.m2397a(sContext).mo2474c(Preferences.getLocalGroupId(), null);
            }
        }
    }

    /* renamed from: a */
    private void m4474a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
        downloadTaskInfo2.setState(downloadTaskInfo.getState());
        downloadTaskInfo2.setFileLength(downloadTaskInfo.getFileLength());
        downloadTaskInfo2.setDownloadLength(downloadTaskInfo.getDownloadLength());
        downloadTaskInfo2.setUrlUpdated(downloadTaskInfo.isUrlUpdated());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public List<DownloadTaskInfo> m4471a(Integer num, int i) {
        Comparator<DownloadTaskInfo> comparator;
        List<DownloadTaskInfo> m3125a = SqliteDb.m3125a(sContext, num);
        Iterator<DownloadTaskInfo> it = m3125a.iterator();
        f5790d.lock();
        while (it.hasNext()) {
            DownloadTaskInfo next = it.next();
            if (next.getState().intValue() == 4) {
                if (!FileUtils.m8414b(next.getSavePath()) || i == 1) {
                    it.remove();
                } else if (next.getCompleteTime() == null) {
                    next.setCompleteTime(Long.valueOf(next.getAddTime().longValue()));
                }
            } else if (i == 0 || ((this.f5792a.contains(next) && num == null) || next.getType().equals(num))) {
                it.remove();
            } else if (next.getState().intValue() == 0 && i == 1) {
                next.setState(3);
                next.setDownloadTime(Long.valueOf(next.getDownloadTime().longValue() + (System.nanoTime() - next.getConnectTimeStamp().longValue())));
            }
        }
        if (i == 1) {
            if (num == null) {
                m3125a.addAll(this.f5792a);
            } else {
                for (DownloadTaskInfo downloadTaskInfo : this.f5792a) {
                    if (downloadTaskInfo.getType().equals(num)) {
                        m3125a.add(downloadTaskInfo);
                    }
                }
            }
            comparator = new Comparator<DownloadTaskInfo>() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.9
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(DownloadTaskInfo downloadTaskInfo2, DownloadTaskInfo downloadTaskInfo3) {
                    return DownloadManagerModule.this.m4483a(downloadTaskInfo2.getAddTime().longValue(), downloadTaskInfo3.getAddTime().longValue());
                }
            };
        } else {
            comparator = new Comparator<DownloadTaskInfo>() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.10
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(DownloadTaskInfo downloadTaskInfo2, DownloadTaskInfo downloadTaskInfo3) {
                    return DownloadManagerModule.this.m4483a(downloadTaskInfo3.getCompleteTime().longValue(), downloadTaskInfo2.getCompleteTime().longValue());
                }
            };
        }
        f5790d.unlock();
        Collections.sort(m3125a, comparator);
        return m3125a;
    }

    /* renamed from: c */
    private void m4459c(final DownloadTaskInfo downloadTaskInfo) {
        OnlineMediaItemAPI.m8868a(downloadTaskInfo.getFileId()).m8544a(new RequestCallback<OnlineMediaItemsResult>() { // from class: com.sds.android.ttpod.framework.modules.core.a.a.2
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(OnlineMediaItemsResult onlineMediaItemsResult) {
                if (!StringUtils.isEmpty(downloadTaskInfo.getAudioQuality()) && onlineMediaItemsResult.getDataList().size() > 0) {
                    OnlineMediaItem.Url m4682b = OnlineMediaItemUtils.m4682b(onlineMediaItemsResult.getDataList().get(0), AudioQuality.valueOf(downloadTaskInfo.getAudioQuality()));
                    if (m4682b != null && !m4682b.getUrl().equals(downloadTaskInfo.getSourceUrl())) {
                        downloadTaskInfo.setSourceUrl(m4682b.getUrl());
                    }
                } else {
                    LogUtils.error("DownloadManagerModule", "DownloadTaskInfo audioQuality is null");
                }
                DownloadManagerModule.this.addDownloadTask(downloadTaskInfo);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OnlineMediaItemsResult onlineMediaItemsResult) {
                DownloadManagerModule.this.addDownloadTask(downloadTaskInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m4457d(DownloadTaskInfo downloadTaskInfo) {
        MediaItem queryMediaItem;
        MediaItem m4712a = MediaItemUtils.m4712a(downloadTaskInfo.getSavePath());
        if (m4712a != null && (queryMediaItem = MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, m4712a.getID())) != null) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, queryMediaItem, Boolean.FALSE));
        }
    }

    /* renamed from: e */
    private void m4455e(DownloadTaskInfo downloadTaskInfo) {
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_DOWNLOAD_TASK_STATE, downloadTaskInfo), ModuleID.DOWNLOAD_MANAGER);
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
            m4482a((TaskInfo) downloadTaskInfo);
        }
    }

    /* renamed from: a */
    private DownloadTaskInfo m4470a(String str) {
        for (DownloadTaskInfo downloadTaskInfo : this.f5792a) {
            if (str.equals(downloadTaskInfo.getSavePath())) {
                return downloadTaskInfo;
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m4472a(Integer num) {
        if (num == null) {
            throw new IllegalArgumentException("type not be null!");
        }
        if (!DownloadTaskInfo.TYPE_AUDIO.equals(num) && !DownloadTaskInfo.TYPE_APP.equals(num) && !DownloadTaskInfo.TYPE_SKIN.equals(num) && !DownloadTaskInfo.TYPE_VIDEO.equals(num) && !DownloadTaskInfo.TYPE_PLUGIN.equals(num) && !DownloadTaskInfo.TYPE_BACKGROUND.equals(num)) {
            throw new IllegalArgumentException("type must be DownloadTaskInfo.TYPE_AUDIO, DownloadTaskInfo.TYPE_APP, DownloadTaskInfo.TYPE_VIDEO, DownloadTaskInfo.TYPE_SKIN, DownloadTaskInfo.TYPE_BACKGROUND!");
        }
    }

    /* renamed from: a */
    private void m4482a(TaskInfo taskInfo) {
        Integer state = taskInfo.getState();
        if (state.intValue() == 3 || state.intValue() == 2 || state.intValue() == 0) {
            m4456e();
        } else if (state.intValue() == 4) {
            m4454f();
            m4456e();
        } else if (state.intValue() == 5) {
            m4462b(FileUtils.m8401k(taskInfo.getSavePath()));
            m4456e();
        }
    }

    /* renamed from: e */
    private void m4456e() {
        int m4453g = m4453g();
        if (m4453g == 0) {
            NotificationUtils.m4696a(15121750);
            CommandCenter.getInstance().m4595b(new Command(CommandID.DOWNLOADING_TASK_CLEAR, new Object[0]), ModuleID.DOWNLOAD_MANAGER);
            return;
        }
        Notification m4469a = m4469a(sContext.getString(R.string.notification_task_downloading, Integer.valueOf(m4453g)), sContext.getString(R.string.notification_download_click_hint), 1);
        m4469a.flags |= 16;
        NotificationUtils.m4695a(15121750, m4469a);
    }

    /* renamed from: f */
    private void m4454f() {
        Context context = sContext;
        int i = R.string.notification_task_completed;
        int i2 = this.f5794c + 1;
        this.f5794c = i2;
        Notification m4468a = m4468a(context.getString(i, Integer.valueOf(i2)), sContext.getString(R.string.notification_download_click_hint), System.currentTimeMillis(), 0);
        m4468a.flags |= 16;
        NotificationUtils.m4695a(15121730, m4468a);
    }

    /* renamed from: b */
    private void m4462b(String str) {
        Notification m4469a = m4469a(str, sContext.getString(R.string.notification_task_failed), 1);
        m4469a.flags |= 16;
        NotificationUtils.m4695a(15121740, m4469a);
    }

    /* renamed from: a */
    private Notification m4469a(String str, String str2, int i) {
        return m4468a(str, str2, 0L, i);
    }

    /* renamed from: a */
    private Notification m4468a(String str, String str2, long j, int i) {
        PendingIntent activity = PendingIntent.getActivity(sContext, 0, new Intent(Action.NOTIFICATION_START_DOWNLOAD_MANAGER).putExtra("fragment_page_index", i), PendingIntent.FLAG_IMMUTABLE);
        Bitmap bitmap = null;
        try {
            bitmap = ((BitmapDrawable) sContext.getResources().getDrawable(R.drawable.img_notification_artist_default)).getBitmap();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (bitmap == null) {
            bitmap = ((BitmapDrawable) sContext.getResources().getDrawable(R.drawable.img_notification_tickericon)).getBitmap();
        }
        return NotificationUtils.m4694a(sContext, R.drawable.img_notification_tickericon, str, str2, bitmap, j, activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int m4483a(long j, long j2) {
        if (j == j2) {
            return 0;
        }
        if (j > j2) {
            return 1;
        }
        return -1;
    }

    /* renamed from: g */
    private int m4453g() {
        f5790d.lock();
        int size = this.f5792a.size();
        Iterator<DownloadTaskInfo> it = this.f5792a.iterator();
        while (true) {
            int i = size;
            if (it.hasNext()) {
                DownloadTaskInfo next = it.next();
                size = i - ((DownloadTaskInfo.TYPE_SKIN == next.getType() || DownloadTaskInfo.TYPE_PLUGIN == next.getType()) ? 1 : 0);
            } else {
                f5790d.unlock();
                return i;
            }
        }
    }
}
