package com.sds.android.ttpod.framework.support.download;

import android.content.Intent;
import androidx.core.os.EnvironmentCompat;

import com.sds.android.cloudapi.ttpod.data.MVOnlineData;
import com.sds.android.sdk.core.download.Manager;
import com.sds.android.sdk.core.download.Task;
import com.sds.android.sdk.core.download.TaskInfo;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.database.SqliteDb;
import com.sds.android.ttpod.framework.storage.database.SqliteDbWrapper;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.p134a.Player;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.framework.support.download.a */
/* loaded from: classes.dex */
public final class DownloadTaskFacade {

    /* renamed from: c */
    private static ManagerWrapper f7145c;

    /* renamed from: a */
    private final Map<String, C2067a> f7146a = new ConcurrentHashMap();

    /* renamed from: b */
    private SqliteDbWrapper f7147b;

    /* renamed from: d */


    public DownloadTaskFacade(SqliteDbWrapper sqliteDbWrapper, ManagerWrapper managerWrapper) {
        this.f7146a.put("download_normal", new C2067a(1, new Task.TaskCallback() { // from class: com.sds.android.ttpod.framework.support.download.a.1
            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: a */
            public void mo2410a(TaskInfo taskInfo) {
                DownloadTaskFacade.this.m2437a(taskInfo);
            }

            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: b */
            public void start(TaskInfo taskInfo) {
                DownloadTaskFacade.this.m2423b(taskInfo);
            }

            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: c */
            public void downloaded(TaskInfo taskInfo) {
                DownloadTaskFacade.this.m2416c(taskInfo);
            }

            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: a */
            public void error(TaskInfo taskInfo, Task.ErrorCodeType enumC0579b) {
                DownloadTaskFacade.this.m2436a(taskInfo, enumC0579b);
            }

            @Override // com.sds.android.sdk.core.download.Task.AbstractC0578a
            /* renamed from: b */
            public void mo2409b(TaskInfo taskInfo, Task.ErrorCodeType enumC0579b) {
                //DownloadTaskFacade.this.m2422b(taskInfo, enumC0579b);
            }
        }, sqliteDbWrapper));
        this.f7147b = sqliteDbWrapper;
        f7145c = managerWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2437a(TaskInfo taskInfo) {
        LogUtils.debug("DownloadTaskFacade", taskInfo.getSavePath() + " onConnecting");
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        C2067a c2067a = this.f7146a.get(downloadTaskInfo.getGroupId());
        if (c2067a != null && c2067a.m2401c(downloadTaskInfo)) {
            DownloadTaskInfo m2400d = c2067a.m2400d(downloadTaskInfo);
            m2400d.setState(taskInfo.getState());
            m2400d.setConnectTimeStamp(Long.valueOf(System.nanoTime()));
            m2434a(m2400d, (Task.ErrorCodeType) null);
            //m2427a("connection", m2400d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m2423b(TaskInfo taskInfo) {
        LogUtils.debug("DownloadTaskFacade", taskInfo.getSavePath() + " onStarted");
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        C2067a c2067a = this.f7146a.get(downloadTaskInfo.getGroupId());
        if (c2067a != null && c2067a.m2401c(downloadTaskInfo)) {
            DownloadTaskInfo m2400d = c2067a.m2400d(downloadTaskInfo);
            m2400d.setRespondTime(Long.valueOf(System.nanoTime() - m2400d.getConnectTimeStamp().longValue()));
            m2400d.setState(taskInfo.getState());
            m2434a(m2400d, (Task.ErrorCodeType) null);
            //m2427a("start_download", m2400d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m2416c(TaskInfo taskInfo) {
        MediaItem m4712a;
        LogUtils.debug("DownloadTaskFacade", taskInfo.getSavePath() + " onFinished");
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        C2067a c2067a = this.f7146a.get(downloadTaskInfo.getGroupId());
        if (c2067a != null && c2067a.m2401c(downloadTaskInfo)) {
            DownloadTaskInfo m2400d = c2067a.m2400d(downloadTaskInfo);
            m2400d.setState(taskInfo.getState());
            m2400d.setDownloadTime(Long.valueOf(m2400d.getDownloadTime().longValue() + (System.nanoTime() - m2400d.getConnectTimeStamp().longValue())));
            m2400d.setCompleteTime(Long.valueOf(System.currentTimeMillis()));
            if (m2400d.getStatisticRequest()) {
                m2400d.setResponseCode(m2400d.getResponseCode());
                m2400d.statisticConnectFailedIPs(m2400d.getStatisticConnectFailedIP());
                m2400d.setConnectedIP(m2400d.getConnectedIP());
            }
            if (m2400d.resumeBrokenTransferSupported()) {
                SqliteDb.m3121a(m2400d, new DownloadTaskInfo(m2400d.getSavePath()));
                if (DownloadTaskInfo.TYPE_AUDIO.equals(m2400d.getType()) && (m4712a = MediaItemUtils.m4712a(m2400d.getSavePath())) != null) {
                    m4712a.setSongID(m2400d.getFileId());
                    m2429a(m4712a);
                    if (StringUtils.equals(MediaStorage.GROUP_ID_ALL_LOCAL, Preferences.getLocalGroupId()) || StringUtils.equals(DownloadUtils.m4762a(m2400d), Preferences.getLocalGroupId())) {
                        Player.getInstance().m2624a(Preferences.getLocalGroupId(), (String) null);
                    }
                }
            }
            m2433a(m2400d, false);
            //m2427a("finish", m2400d);
            c2067a.m2406a(m2400d);
            if (c2067a.m2405a(m2400d.getGroupId()) == null) {
                this.f7146a.remove(m2400d.getGroupId());
            }
            m2434a(m2400d, (Task.ErrorCodeType) null);
        }
    }

    /* renamed from: a */
    private void m2429a(MediaItem mediaItem) {
        MediaItem queryMediaItemBySongID;
        if (!MediaStorage.isGroupExisted(BaseApplication.getApplication(), MediaStorage.GROUP_ID_DOWNLOAD)) {
            MediaStorage.insertGroup(BaseApplication.getApplication(), MediaStorage.GROUP_NAME_DOWNLOADED_SONG, MediaStorage.GROUP_ID_DOWNLOAD, GroupType.CUSTOM_LOCAL);
        }
        if (Preferences.m2954aq() != null && (queryMediaItemBySongID = MediaStorage.queryMediaItemBySongID(BaseApplication.getApplication(), MediaStorage.buildOnlineFavGroupID(), mediaItem.getSongID())) != null) {
            MediaItemUtils.m4714a(queryMediaItemBySongID, mediaItem.getLocalDataSource());
            MediaStorage.updateMediaItem(BaseApplication.getApplication(), queryMediaItemBySongID);
        }
        MediaStorage.deleteMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, mediaItem.getID());
        MediaStorage.insertMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_DOWNLOAD, mediaItem);
        MediaStorage.insertMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, mediaItem);
        MediaStorage.insertMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_RECENTLY_ADD, mediaItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2436a(TaskInfo taskInfo, Task.ErrorCodeType enumC0579b) {
        LogUtils.debug("DownloadTaskFacade", taskInfo.getSavePath() + " onError:" + enumC0579b.name());
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        downloadTaskInfo.setRespondTime(Long.valueOf(System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue()));
        downloadTaskInfo.setDownloadTime(Long.valueOf((downloadTaskInfo.getConnectTimeStamp() == null ? 0L : System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue()) + downloadTaskInfo.getDownloadTime().longValue()));
        downloadTaskInfo.setCutOffTimes(Integer.valueOf(downloadTaskInfo.getCutOffTimes().intValue() + 1));
        if (downloadTaskInfo.getStatisticRequest()) {
            downloadTaskInfo.setResponseCode(downloadTaskInfo.getResponseCode());
            downloadTaskInfo.statisticConnectFailedIPs(downloadTaskInfo.getStatisticConnectFailedIP());
            downloadTaskInfo.setConnectedIP(downloadTaskInfo.getConnectedIP());
        }
        if (enumC0579b != Task.ErrorCodeType.URL_REQUEST_FAILED || downloadTaskInfo.isUrlUpdated()) {
            m2433a(downloadTaskInfo, false);
        }
        if (downloadTaskInfo.resumeBrokenTransferSupported()) {
            SqliteDb.m3121a(downloadTaskInfo, new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
        }
        if (this.f7146a.containsKey(downloadTaskInfo.getGroupId())) {
            C2067a c2067a = this.f7146a.get(downloadTaskInfo.getGroupId());
            c2067a.m2406a(downloadTaskInfo);
            if (c2067a.m2405a(downloadTaskInfo.getGroupId()) == null) {
                this.f7146a.remove(downloadTaskInfo.getGroupId());
            }
        }
        m2434a(downloadTaskInfo, enumC0579b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
   

    /* renamed from: a */
 

    /* renamed from: a */
    public void m2439a() {
        int i = 0;
        Iterator<C2067a> it = this.f7146a.values().iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                i = it.next().m2402c() + i2;
            } else {
                this.f7147b.m3115a(BaseApplication.getApplication());
                C2067a.m2407a(i2);
                return;
            }
        }
    }

    /* renamed from: b */
    public void m2424b() {
        for (C2067a c2067a : this.f7146a.values()) {
            m2432a(c2067a);
        }
        this.f7146a.clear();
        C2067a.m2408a();
    }

    /* renamed from: a */
    private void m2432a(C2067a c2067a) {
        List<DownloadTaskInfo> m3118b;
        for (DownloadTaskInfo downloadTaskInfo : c2067a.m2404b()) {
            m2412e(downloadTaskInfo);
            if (!downloadTaskInfo.resumeBrokenTransferSupported() && ((m3118b = SqliteDb.m3118b(new DownloadTaskInfo(downloadTaskInfo.getSavePath()))) == null || m3118b.isEmpty())) {
                FileUtils.exists(downloadTaskInfo.getSavePath() + ".tmp");
            }
        }
    }

    /* renamed from: e */
    private void m2412e(DownloadTaskInfo downloadTaskInfo) {
        m2426a(downloadTaskInfo == null, "taskInfo is null!");
        C2067a c2067a = this.f7146a.get(downloadTaskInfo.getGroupId());
        if (c2067a != null && c2067a.m2401c(downloadTaskInfo)) {
            //m2427a("cancel", downloadTaskInfo);
            downloadTaskInfo.setDownloadTime(Long.valueOf((downloadTaskInfo.getConnectTimeStamp() == null ? 0L : System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue()) + downloadTaskInfo.getDownloadTime().longValue()));
            downloadTaskInfo.setState(3);
            c2067a.m2406a(downloadTaskInfo);
            if (c2067a.m2405a(downloadTaskInfo.getGroupId()) == null) {
                this.f7146a.remove(downloadTaskInfo.getGroupId());
            }
        }
    }

    /* renamed from: a */
    private static void m2434a(DownloadTaskInfo downloadTaskInfo, Task.ErrorCodeType enumC0579b) {
        Intent intent = new Intent(Action.DOWNLOAD_TASK_STATE_CHANGED);
        intent.putExtra("download_task", downloadTaskInfo);
        if (enumC0579b != null) {
            intent.putExtra("download_error", enumC0579b.getErrorCode());
        }
        BaseApplication.getApplication().sendBroadcast(intent);
    }

    /* renamed from: a */
    private static void m2426a(boolean z, String str) {
        if (EnvironmentUtils.AppConfig.getTestMode() && z) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: a */
    public void m2435a(DownloadTaskInfo downloadTaskInfo) {
        LogUtils.debug("DownloadTaskFacade", "addDownloadTask");
        C2067a c2067a = this.f7146a.get("download_normal");
        if (!c2067a.m2401c(downloadTaskInfo)) {
            if (!this.f7146a.containsKey(downloadTaskInfo.getGroupId())) {
                this.f7146a.put(downloadTaskInfo.getGroupId(), c2067a);
            }
            //m2427a("start", downloadTaskInfo);
            if (downloadTaskInfo.resumeBrokenTransferSupported()) {
                if (this.f7147b.m3112b(new DownloadTaskInfo(downloadTaskInfo.getSavePath())).isEmpty()) {
                    this.f7147b.m3114a(downloadTaskInfo);
                } else {
                    this.f7147b.m3113a(downloadTaskInfo, new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
                }
            }
            c2067a.m2403b(downloadTaskInfo);
        }
    }

    /* renamed from: b */
    public DownloadTaskInfo m2421b(DownloadTaskInfo downloadTaskInfo) {
        LogUtils.debug("DownloadTaskFacade", "cancelDownloadTask");
        DownloadTaskInfo m2411f = m2411f(downloadTaskInfo);
        if (m2411f != null) {
            m2412e(m2411f);
        }
        return m2411f;
    }

    /* renamed from: c */
    public DownloadTaskInfo m2415c(DownloadTaskInfo downloadTaskInfo) {
        LogUtils.debug("DownloadTaskFacade", "removeDownloadTask");
        m2426a(downloadTaskInfo == null, "remoteTaskInfo is null!");
        DownloadTaskInfo m2411f = m2411f(downloadTaskInfo);
        if (m2411f != null) {
            m2412e(m2411f);
        }
        List<DownloadTaskInfo> m3112b = this.f7147b.m3112b(new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
        if (downloadTaskInfo.resumeBrokenTransferSupported() && m3112b != null && !m3112b.isEmpty()) {
            DownloadTaskInfo downloadTaskInfo2 = m3112b.get(0);
            this.f7147b.m3111c(new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
            downloadTaskInfo2.setDownloadTime(Long.valueOf((downloadTaskInfo2.getConnectTimeStamp() == null ? 0L : System.nanoTime() - downloadTaskInfo2.getConnectTimeStamp().longValue()) + downloadTaskInfo2.getDownloadTime().longValue()));
            m2433a(downloadTaskInfo2, true);
            //m2427a("remove", downloadTaskInfo2);
        }
        C2067a c2067a = this.f7146a.get(downloadTaskInfo.getGroupId());
        if (m3112b == null || m3112b.isEmpty() || c2067a == null || c2067a.m2400d(downloadTaskInfo) == null) {
            FileUtils.exists(downloadTaskInfo.getSavePath() + ".tmp");
        }
        return m2411f != null ? m2411f : downloadTaskInfo;
    }

    /* renamed from: f */
    private DownloadTaskInfo m2411f(DownloadTaskInfo downloadTaskInfo) {
        C2067a c2067a = this.f7146a.get(downloadTaskInfo.getGroupId());
        if (c2067a == null || !c2067a.m2401c(downloadTaskInfo)) {
            return null;
        }
        return c2067a.m2400d(downloadTaskInfo);
    }

    /* renamed from: a */
    public void m2428a(String str) {
        Map<String, DownloadTaskInfo> m2405a;
        LogUtils.debug("DownloadTaskFacade", "removeDownloadList.....");
        if (this.f7146a.containsKey(str) && (m2405a = this.f7146a.get(str).m2405a(str)) != null) {
            Object[] array = m2405a.values().toArray();
            for (int length = array.length - 1; length >= 0; length--) {
                m2415c((DownloadTaskInfo) array[length]);
            }
        }
    }

    /* renamed from: b */
    public void m2418b(String str) {
        Map<String, DownloadTaskInfo> m2405a;
        LogUtils.debug("DownloadTaskFacade", "cancelDownloadList.....");
        if (this.f7146a.containsKey(str) && (m2405a = this.f7146a.get(str).m2405a(str)) != null) {
            Object[] array = m2405a.values().toArray();
            for (int length = array.length - 1; length >= 0; length--) {
                m2421b((DownloadTaskInfo) array[length]);
            }
        }
    }

    /* renamed from: d */
    public int m2413d(DownloadTaskInfo downloadTaskInfo) {
        DownloadTaskInfo m2400d;
        C2067a c2067a = this.f7146a.get(downloadTaskInfo.getGroupId());
        if (c2067a == null || (m2400d = c2067a.m2400d(downloadTaskInfo)) == null) {
            return 0;
        }
        return m2400d.getDownloadLength();
    }

    /* renamed from: a */
    public List<DownloadTaskInfo> m2425a(int[] iArr) {
        LogUtils.debug("DownloadTaskFacade", "getDownloadTaskListByTypes...");
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.addAll(m2438a(i));
        }
        return arrayList;
    }

    /* renamed from: a */
    private List<DownloadTaskInfo> m2438a(int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<DownloadTaskInfo> it = this.f7146a.get("download_normal").m2404b().iterator();
        while (it.hasNext()) {
            DownloadTaskInfo next = it.next();
            if (i == next.getType().intValue()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DownloadTaskFacade.java */
    /* renamed from: com.sds.android.ttpod.framework.support.download.a$a */
    /* loaded from: classes.dex */
    public static class C2067a {

        /* renamed from: a */
        private int f7150a;

        /* renamed from: c */
        private Task.TaskCallback f7152c;

        /* renamed from: d */
        private SqliteDbWrapper f7153d;

        /* renamed from: e */
        private final LinkedHashMap<String, LinkedHashMap<String, DownloadTaskInfo>> f7154e = new LinkedHashMap<>();

        /* renamed from: b */
        private int f7151b = 0;

        /* renamed from: a */
        static void m2407a(int i) {
            if (!Manager.getInstance().m8743a("download_manger")) {
                Manager.getInstance().m8742a("download_manger", i);
            }
        }

        /* renamed from: a */
        static void m2408a() {
            if (Manager.getInstance().m8743a("download_manger")) {
                Manager.getInstance().m8739b("download_manger");
            }
        }

        public C2067a(int i, Task.TaskCallback abstractC0578a, SqliteDbWrapper sqliteDbWrapper) {
            this.f7152c = abstractC0578a;
            this.f7150a = i;
            this.f7153d = sqliteDbWrapper;
        }

        /* renamed from: a */
        public synchronized void m2406a(DownloadTaskInfo downloadTaskInfo) {
            DownloadTaskInfo downloadTaskInfo2;
            String groupId = downloadTaskInfo.getGroupId();
            if (groupId != null && this.f7154e.get(groupId) != null && (downloadTaskInfo2 = this.f7154e.get(groupId).get(downloadTaskInfo.getSavePath())) != null) {
                if (downloadTaskInfo.getState().intValue() == 3) {
                    Manager.getInstance().m8741a("download_manger", downloadTaskInfo);
                }
                this.f7154e.get(groupId).remove(downloadTaskInfo.getSavePath());
                if (downloadTaskInfo2.resumeBrokenTransferSupported()) {
                    downloadTaskInfo.setDownloadLength(downloadTaskInfo2.getDownloadLength());
                    this.f7153d.m3113a(downloadTaskInfo, new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
                }
                if (this.f7154e.get(groupId).isEmpty()) {
                    this.f7154e.remove(groupId);
                }
            }
            if (this.f7151b >= 1) {
                this.f7151b--;
                ArrayList<DownloadTaskInfo> m2404b = m2404b();
                if (m2404b.size() >= this.f7150a && this.f7151b < this.f7150a) {
                    m2403b(m2404b.get(this.f7151b));
                }
            }
        }

        /* renamed from: b */
        public synchronized void m2403b(DownloadTaskInfo downloadTaskInfo) {
            String groupId = downloadTaskInfo.getGroupId();
            if (!this.f7154e.containsKey(groupId)) {
                this.f7154e.put(groupId, new LinkedHashMap<>());
            }
            this.f7154e.get(groupId).put(downloadTaskInfo.getSavePath(), downloadTaskInfo);
            if (this.f7151b < this.f7150a) {
                int intValue = downloadTaskInfo.getType().intValue();
                if (DownloadTaskInfo.TYPE_AUDIO.equals(Integer.valueOf(intValue)) || DownloadTaskInfo.TYPE_VIDEO.equals(Integer.valueOf(intValue))) {
                    downloadTaskInfo.setStatisticRequest(true);
                }
                DownloadTaskFacade.f7145c.m2399a("download_manger", downloadTaskInfo, this.f7152c);
                this.f7151b++;
            }
        }

        /* renamed from: c */
        public synchronized boolean m2401c(DownloadTaskInfo downloadTaskInfo) {
            boolean z = false;
            if (this.f7154e.containsKey(downloadTaskInfo.getGroupId())) {
                z = this.f7154e.get(downloadTaskInfo.getGroupId()).containsKey(downloadTaskInfo.getSavePath());
            }
            return z;
        }

        /* renamed from: d */
        public synchronized DownloadTaskInfo m2400d(DownloadTaskInfo downloadTaskInfo) {
            return this.f7154e.containsKey(downloadTaskInfo.getGroupId()) ? this.f7154e.get(downloadTaskInfo.getGroupId()).get(downloadTaskInfo.getSavePath()) : null;
        }

        /* renamed from: a */
        public synchronized Map<String, DownloadTaskInfo> m2405a(String str) {
            return this.f7154e.containsKey(str) ? this.f7154e.get(str) : null;
        }

        /* renamed from: b */
        public synchronized ArrayList<DownloadTaskInfo> m2404b() {
            ArrayList<DownloadTaskInfo> arrayList;
            arrayList = new ArrayList<>();
            for (LinkedHashMap<String, DownloadTaskInfo> linkedHashMap : this.f7154e.values()) {
                arrayList.addAll(linkedHashMap.values());
            }
            return arrayList;
        }

        /* renamed from: c */
        public int m2402c() {
            return this.f7150a;
        }
    }

    /* renamed from: a */
    private static void m2433a(DownloadTaskInfo downloadTaskInfo, boolean z) {
        String str;
        long j;
        String str2 = EnvironmentCompat.MEDIA_UNKNOWN;
        int intValue = downloadTaskInfo.getType().intValue();
        if (DownloadTaskInfo.TYPE_APP.equals(Integer.valueOf(intValue))) {
            str2 = "app";
        } else if (DownloadTaskInfo.TYPE_AUDIO.equals(Integer.valueOf(intValue))) {
            str2 = "song";
        } else if (DownloadTaskInfo.TYPE_VIDEO.equals(Integer.valueOf(intValue))) {
            str2 = "mv";
        } else if (DownloadTaskInfo.TYPE_SKIN.equals(Integer.valueOf(intValue))) {
            str2 = "theme";
        } else if (DownloadTaskInfo.TYPE_PLUGIN.equals(Integer.valueOf(intValue))) {
            str2 = "app";
        }
        //SessionStatisticEvent m4903b = //StatisticUtils.m4903b("download", str2, downloadTaskInfo.getOrigin(), downloadTaskInfo.hashCode());
        if (z) {
            str = "deleted";
        } else {
            str = downloadTaskInfo.getState().intValue() == 4 ? "success" : "failed";
        }
       
        LogUtils.debug("Statistic_DownloadTaskManager", "put Download info origin=%s downstatus=%s fileid=%s filename=%s filesize=%s down_file_size=%s response_time=%s download_time=%s cutoff_count=%s position=%s quality=%s", downloadTaskInfo.getOrigin(), str, downloadTaskInfo.getFileId(), downloadTaskInfo.getFileName(), downloadTaskInfo.getFileLength(), Integer.valueOf(downloadTaskInfo.getDownloadLength()), String.valueOf(TimeUnit.NANOSECONDS.toMillis(downloadTaskInfo.getRespondTime().longValue())), String.valueOf(TimeUnit.NANOSECONDS.toMillis(downloadTaskInfo.getDownloadTime().longValue())), String.valueOf(downloadTaskInfo.getCutOffTimes()), String.valueOf(String.valueOf(downloadTaskInfo.getPosition())), String.valueOf(downloadTaskInfo.getSongType()));
        //StatisticUtils.m4912a(m4903b);
        if (DownloadTaskInfo.TYPE_AUDIO.equals(Integer.valueOf(intValue)) || DownloadTaskInfo.TYPE_VIDEO.equals(Integer.valueOf(intValue))) {
            Object tag = downloadTaskInfo.getTag();
            if (tag instanceof MediaItem) {
                j = ((MediaItem) tag).getSongID().longValue();
            } else {
                j = tag instanceof MVOnlineData ? ((MVOnlineData) tag).getId() : -1L;
            }
            if (j != -1) {
               // //ListStatistic.m5208a(downloadTaskInfo.getListType(), downloadTaskInfo.getListId(), j, downloadTaskInfo.getPosition().intValue());
            }
        }
    }
}
