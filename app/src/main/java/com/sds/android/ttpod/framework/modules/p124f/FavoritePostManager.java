package com.sds.android.ttpod.framework.modules.p124f;

import android.util.LongSparseArray;
import com.sds.android.cloudapi.ttpod.data.MessageCollectItem;
import com.sds.android.cloudapi.ttpod.api.MessageCollectAPI;
import com.sds.android.cloudapi.ttpod.result.MessageCollectListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.f.a */
/* loaded from: classes.dex */
public final class FavoritePostManager {

    /* renamed from: a */
    private static FavoritePostManager f6120a = new FavoritePostManager();

    /* renamed from: c */
    private long f6122c;

    /* renamed from: d */
    private long f6123d;

    /* renamed from: e */
    private boolean f6124e;

    /* renamed from: b */
    private LongSparseArray<Long> f6121b = new LongSparseArray<>();

    /* renamed from: f */
    private Object f6125f = new Object();

    /* renamed from: g */
    private long f6126g = 0;

    /* compiled from: FavoritePostManager.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.f.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1900a {
        /* renamed from: a */
        void mo4040a();
    }

    private FavoritePostManager() {
    }

    /* renamed from: a */
    public static FavoritePostManager m4070a() {
        return f6120a;
    }

    /* renamed from: a */
    public BaseResult m4067a(List<Long> list, String str) {
        if (str == null) {
            throw new IllegalArgumentException("accesstoken should not be null");
        }
        m4068a((InterfaceC1900a) null, str);
        BaseResult m4056a = MusicCircleModule.m4056a(MessageCollectAPI.m8882a(str, list));
        if (1 == m4056a.getCode()) {
            synchronized (this.f6125f) {
                for (Long l : list) {
                    this.f6121b.put(l.longValue(), l);
                }
            }
        }
        return m4056a;
    }

    /* renamed from: b */
    public BaseResult m4065b(List<Long> list, String str) {
        if (str == null) {
            throw new IllegalArgumentException("accesstoken should not be null");
        }
        m4068a((InterfaceC1900a) null, str);
        BaseResult m4056a = MusicCircleModule.m4056a(MessageCollectAPI.m8881b(str, list));
        if (1 == m4056a.getCode()) {
            synchronized (this.f6125f) {
                for (Long l : list) {
                    this.f6121b.remove(l.longValue());
                }
            }
        }
        return m4056a;
    }

    /* renamed from: a */
    public void m4069a(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("loginUid should be > 0");
        }
        this.f6122c = j;
    }

    /* renamed from: b */
    public boolean m4066b(long j) {
        return this.f6121b.get(j) != null;
    }

    /* renamed from: a */
    public void m4068a(InterfaceC1900a interfaceC1900a, String str) {
        if (str == null) {
            throw new IllegalArgumentException("accesstoken should not be null");
        }
        if (this.f6123d != this.f6122c) {
            this.f6123d = this.f6122c;
            this.f6121b.clear();
            this.f6124e = false;
        }
        if (!this.f6124e && System.currentTimeMillis() - this.f6126g > 10000) {
            synchronized (this.f6125f) {
                if (!this.f6124e) {
                    this.f6126g = System.currentTimeMillis();
                    MessageCollectListResult messageCollectListResult = (MessageCollectListResult) MusicCircleModule.m4056a(MessageCollectAPI.m8883a(str));
                    if (messageCollectListResult.isSuccess()) {
                        this.f6124e = true;
                        Iterator<MessageCollectItem> it = messageCollectListResult.getDataList().iterator();
                        while (it.hasNext()) {
                            MessageCollectItem next = it.next();
                            this.f6121b.put(next.getId(), Long.valueOf(next.getId()));
                        }
                        if (interfaceC1900a != null) {
                            interfaceC1900a.mo4040a();
                        }
                    }
                }
            }
        }
    }
}
