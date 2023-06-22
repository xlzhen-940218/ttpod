package com.sds.android.ttpod.framework.modules.p124f;

import android.util.LongSparseArray;
import com.sds.android.cloudapi.ttpod.p055a.FriendsAPI;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.IdListResult;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.f.b */
/* loaded from: classes.dex */
public final class FollowManager {

    /* renamed from: a */
    private static FollowManager f6127a = new FollowManager();

    /* renamed from: c */
    private long f6129c;

    /* renamed from: d */
    private long f6130d;

    /* renamed from: e */
    private boolean f6131e;

    /* renamed from: b */
    private LongSparseArray<Long> f6128b = new LongSparseArray<>();

    /* renamed from: f */
    private Object f6132f = new Object();

    /* renamed from: g */
    private long f6133g = 0;

    /* compiled from: FollowManager.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.f.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1901a {
        /* renamed from: a */
        void mo4041a();
    }

    private FollowManager() {
    }

    /* renamed from: a */
    public static FollowManager m4064a() {
        return f6127a;
    }

    /* renamed from: a */
    public BaseResult m4063a(long j) {
        m4062a((InterfaceC1901a) null);
        BaseResult m4056a = MusicCircleModule.m4056a(FriendsAPI.m8891a(Preferences.m2954aq().getAccessToken(), j));
        if (1 == m4056a.getCode()) {
            synchronized (this.f6132f) {
                this.f6128b.put(j, Long.valueOf(j));
            }
        }
        return m4056a;
    }

    /* renamed from: b */
    public BaseResult m4061b(long j) {
        m4062a((InterfaceC1901a) null);
        BaseResult m4056a = MusicCircleModule.m4056a(FriendsAPI.m8888b(Preferences.m2954aq().getAccessToken(), j));
        if (1 == m4056a.getCode()) {
            synchronized (this.f6132f) {
                this.f6128b.remove(j);
            }
        }
        return m4056a;
    }

    /* renamed from: c */
    public boolean m4060c(long j) {
        return this.f6128b.get(j) != null;
    }

    /* renamed from: a */
    public void m4062a(InterfaceC1901a interfaceC1901a) {
        if (this.f6130d != this.f6129c) {
            this.f6130d = this.f6129c;
            this.f6128b.clear();
            this.f6131e = false;
        }
        if (!this.f6131e && System.currentTimeMillis() - this.f6133g > 10000) {
            synchronized (this.f6132f) {
                if (!this.f6131e) {
                    this.f6133g = System.currentTimeMillis();
                    IdListResult idListResult = (IdListResult) MusicCircleModule.m4056a(FriendsAPI.m8893a(this.f6130d));
                    if (idListResult.isSuccess()) {
                        this.f6131e = true;
                        Iterator<Long> it = idListResult.getDataList().iterator();
                        while (it.hasNext()) {
                            long longValue = it.next().longValue();
                            this.f6128b.put(longValue, Long.valueOf(longValue));
                        }
                        if (interfaceC1901a != null) {
                            interfaceC1901a.mo4041a();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: d */
    public void m4059d(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("invalid loginUid");
        }
        this.f6129c = j;
    }
}
