package com.sds.android.ttpod.framework.modules.p109a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.p055a.FavoritesAPI;
import com.sds.android.cloudapi.ttpod.p055a.OnlineMediaItemAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemIdListResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.a.b */
/* loaded from: classes.dex */
class FavoriteServerManager {

    /* renamed from: a */
    private static FavoriteServerManager f5747a = null;

    /* renamed from: b */
    private Handler f5748b = null;

    /* renamed from: c */
    private Handler f5749c = null;

    /* renamed from: d */
    private final Object f5750d = new Object();

    /* renamed from: e */
    private LinkedList<InterfaceC1815a> f5751e = new LinkedList<>();

    /* renamed from: f */
    private LinkedList<InterfaceC1815a> f5752f = new LinkedList<>();

    /* renamed from: g */
    private LinkedList<Long> f5753g = null;

    /* renamed from: h */
    private LinkedList<Long> f5754h = null;

    /* renamed from: i */
    private LinkedList<Long> f5755i;

    /* compiled from: FavoriteServerManager.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.a.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1815a {
        /* renamed from: a */
        void mo4520a();

        /* renamed from: b */
        void mo4519b();

        /* renamed from: c */
        void mo4518c();
    }

    FavoriteServerManager() {
    }

    /* renamed from: a */
    public static FavoriteServerManager m4544a() {
        synchronized (FavoriteServerManager.class) {
            if (f5747a == null) {
                f5747a = new FavoriteServerManager();
            }
        }
        return f5747a;
    }

    /* renamed from: a */
    public void m4542a(long j) {
        synchronized (this.f5750d) {
            if (this.f5754h.contains(Long.valueOf(j))) {
                this.f5754h.remove(Long.valueOf(j));
            } else {
                this.f5753g.add(Long.valueOf(j));
            }
        }
        if (this.f5748b != null) {
            this.f5748b.removeMessages(1);
            this.f5748b.sendEmptyMessageDelayed(1, 10000L);
        }
    }

    /* renamed from: b */
    public void m4535b(long j) {
        synchronized (this.f5750d) {
            if (this.f5753g.contains(Long.valueOf(j))) {
                this.f5753g.remove(Long.valueOf(j));
            } else {
                this.f5754h.add(Long.valueOf(j));
            }
        }
        if (this.f5748b != null) {
            this.f5748b.removeMessages(1);
            this.f5748b.sendEmptyMessageDelayed(1, 10000L);
        }
    }

    /* renamed from: b */
    public void m4537b() {
        if (this.f5749c != null) {
            this.f5749c.removeMessages(1);
            this.f5749c.sendEmptyMessageDelayed(1, 2000L);
        }
    }

    /* renamed from: c */
    public void m4531c() {
        if (this.f5748b != null) {
            this.f5748b.removeMessages(1);
            this.f5748b.sendEmptyMessage(1);
        }
    }

    /* renamed from: d */
    public void m4529d() {
        m4525g();
        HandlerThread handlerThread = new HandlerThread("favorites_push_work_thread");
        HandlerThread handlerThread2 = new HandlerThread("favorites_pull_work_thread");
        handlerThread.start();
        handlerThread2.start();
        this.f5748b = new Handler(handlerThread.getLooper()) { // from class: com.sds.android.ttpod.framework.modules.a.b.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    FavoriteServerManager.this.m4523i();
                }
            }
        };
        this.f5749c = new Handler(handlerThread2.getLooper()) { // from class: com.sds.android.ttpod.framework.modules.a.b.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    FavoriteServerManager.this.m4521k();
                }
            }
        };
    }

    /* renamed from: e */
    public void m4527e() {
        if (this.f5748b != null) {
            this.f5748b.getLooper().quit();
        }
        m4526f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m4523i() {
        if (!m4524h()) {
            if (this.f5755i != null) {
                this.f5755i.clear();
            }
            Iterator<InterfaceC1815a> it = this.f5751e.iterator();
            while (it.hasNext()) {
                it.next().mo4520a();
            }
            return;
        }
        if (this.f5753g.size() > 0) {
            m4539a(this.f5753g);
            m4543a(1);
        }
        if (this.f5754h.size() > 0) {
            m4539a(this.f5754h);
            m4543a(0);
        }
    }

    /* renamed from: a */
    private void m4539a(LinkedList<Long> linkedList) {
        synchronized (this.f5750d) {
            this.f5755i = new LinkedList<>();
            this.f5755i.addAll(linkedList);
        }
        linkedList.clear();
    }

    /* renamed from: a */
    private void m4543a(int i) {
        LogUtils.error("FavoriteServerManager", "doPush");
        String accessToken = Preferences.m2954aq().getAccessToken();
        BaseResult m8531f = i == 1 ? FavoritesAPI.m8922a(accessToken, this.f5755i).execute() : FavoritesAPI.m8921b(accessToken, this.f5755i).execute();
        if (m8531f.isSuccess()) {
            Iterator<InterfaceC1815a> it = this.f5751e.iterator();
            while (it.hasNext()) {
                it.next().mo4520a();
            }
        } else if (m8531f.getCode() == 30305) {
            m4536b(i);
            Iterator<InterfaceC1815a> it2 = this.f5751e.iterator();
            while (it2.hasNext()) {
                it2.next().mo4518c();
            }
        } else {
            m4536b(i);
            Iterator<InterfaceC1815a> it3 = this.f5751e.iterator();
            while (it3.hasNext()) {
                it3.next().mo4519b();
            }
        }
    }

    /* renamed from: f */
    public void m4526f() {
        if (m4524h()) {
            Cache.getInstance().m3200a(this.f5753g);
            Cache.getInstance().m3188b(this.f5754h);
        }
    }

    /* renamed from: g */
    public void m4525g() {
        this.f5753g = Cache.getInstance().m3176d();
        this.f5754h = Cache.getInstance().m3171e();
    }

    /* renamed from: b */
    private void m4536b(int i) {
        LinkedList<Long> linkedList = null;
        if (i == 1) {
            linkedList = this.f5753g;
        }
        if (i == 0) {
            linkedList = this.f5754h;
        }
        if (linkedList != null) {
            synchronized (this.f5750d) {
                linkedList.addAll(this.f5755i);
                this.f5755i.clear();
                m4522j();
            }
        }
    }

    /* renamed from: j */
    private void m4522j() {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(this.f5753g);
        if (linkedList.retainAll(this.f5754h)) {
            this.f5753g.removeAll(linkedList);
            this.f5754h.removeAll(linkedList);
        }
    }

    /* renamed from: h */
    public boolean m4524h() {
        return this.f5753g.size() > 0 || this.f5754h.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public synchronized void m4521k() {
        synchronized (this) {
            if (Preferences.m2954aq() != null) {
                OnlineMediaItemIdListResult m8531f = FavoritesAPI.m8923a(Preferences.m2954aq().getUserId()).execute();
                if (m8531f != null && 1 == m8531f.getCode() && m8531f.getDataList() != null && m8531f.getDataList().size() > 0) {
                    List<Long> m4532b = m4532b(m8531f.getDataList().get(0).getOnlineMediaItemIdList());
                    int size = m4532b.size();
                    if (size > 0) {
                        try {
                            String buildOnlineFavGroupID = MediaStorage.buildOnlineFavGroupID();
                            List<String> queryMediaIDs = MediaStorage.queryMediaIDs(BaseApplication.getApplication(), buildOnlineFavGroupID, Preferences.m2860l(buildOnlineFavGroupID));
                            for (int i = 0; i < size; i += 100) {
                                List<MediaItem> m4538a = m4538a(m4532b.subList(i, Math.min(i + 100, size)));
                                if (!queryMediaIDs.isEmpty()) {
                                    Iterator<MediaItem> it = m4538a.iterator();
                                    while (it.hasNext()) {
                                        String id = it.next().getID();
                                        if (queryMediaIDs.contains(id)) {
                                            queryMediaIDs.remove(id);
                                            it.remove();
                                        }
                                    }
                                }
                                if (m4538a.size() > 0) {
                                    MediaStorage.insertMediaItems(BaseApplication.getApplication(), buildOnlineFavGroupID, m4538a);
                                }
                            }
                            for (String str : queryMediaIDs) {
                                MediaStorage.deleteMediaItem(BaseApplication.getApplication(), buildOnlineFavGroupID, str);
                            }
                            Iterator<InterfaceC1815a> it2 = this.f5752f.iterator();
                            while (it2.hasNext()) {
                                it2.next().mo4520a();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Iterator<InterfaceC1815a> it3 = this.f5752f.iterator();
                            while (it3.hasNext()) {
                                it3.next().mo4519b();
                            }
                        }
                    } else {
                        Iterator<InterfaceC1815a> it4 = this.f5752f.iterator();
                        while (it4.hasNext()) {
                            it4.next().mo4520a();
                        }
                    }
                } else {
                    Iterator<InterfaceC1815a> it5 = this.f5752f.iterator();
                    while (it5.hasNext()) {
                        it5.next().mo4519b();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private List<MediaItem> m4538a(List<Long> list) throws Exception {
        OnlineMediaItemsResult m8531f = OnlineMediaItemAPI.m8867a(list).execute();
        if (m8531f == null || 1 != m8531f.getCode() || m8531f.getDataList() == null || m8531f.getDataList().size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(m8531f.getDataList().size());
        Iterator<OnlineMediaItem> it = m8531f.getDataList().iterator();
        while (it.hasNext()) {
            OnlineMediaItem next = it.next();
            MediaItem m4716a = MediaItemUtils.m4716a(next);
            MediaItemUtils.m4714a(m4716a, OnlineMediaItemUtils.m4690a(next));
            arrayList.add(m4716a);
            String localDataSource = m4716a.getLocalDataSource();
            if (!StringUtils.isEmpty(localDataSource)) {
                MediaItem m4712a = MediaItemUtils.m4712a(localDataSource);
                m4712a.setGroupID(MediaStorage.GROUP_ID_ALL_LOCAL);
                m4712a.setSongID(Long.valueOf(next.getSongId()));
                MediaStorage.updateMediaItem(BaseApplication.getApplication(), m4712a);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private List<Long> m4532b(List<Long> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 1; i < size; i++) {
                list.add(0, Long.valueOf(list.remove(i).longValue()));
            }
        }
        return list;
    }

    /* renamed from: a */
    public void m4541a(InterfaceC1815a interfaceC1815a) {
        if (!this.f5751e.contains(interfaceC1815a)) {
            this.f5751e.add(interfaceC1815a);
        }
    }

    /* renamed from: b */
    public void m4534b(InterfaceC1815a interfaceC1815a) {
        this.f5751e.remove(interfaceC1815a);
    }

    /* renamed from: c */
    public void m4530c(InterfaceC1815a interfaceC1815a) {
        if (!this.f5752f.contains(interfaceC1815a)) {
            this.f5752f.add(interfaceC1815a);
        }
    }

    /* renamed from: d */
    public void m4528d(InterfaceC1815a interfaceC1815a) {
        this.f5752f.remove(interfaceC1815a);
    }
}
