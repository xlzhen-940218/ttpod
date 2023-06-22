package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.a.a.g */
/* loaded from: classes.dex */
public class LocalMediaStatistic {

    /* renamed from: a */
    private static HashMap<String, C1769a> f5573a = new HashMap<>();

    /* renamed from: b */
    private static C1769a m5197b(String str) {
        C1769a c1769a = f5573a.get(str);
        if (c1769a == null) {
            C1769a c1769a2 = new C1769a();
            c1769a2.m5191a(str);
            f5573a.put(str, c1769a2);
            return c1769a2;
        }
        return c1769a;
    }

    /* renamed from: a */
    public static void m5198a(String str, boolean z) {
        LogUtils.m8381c("statistic_LocalMediaStatistic", "setCompleted = " + z);
        m5197b(str).m5190a(z);
    }

    /* renamed from: a */
    public static void m5199a(String str, long j) {
        LogUtils.m8381c("statistic_LocalMediaStatistic", "setTimeStartPlay = " + j);
        m5197b(str).m5192a(j);
    }

    /* renamed from: b */
    public static void m5196b(String str, long j) {
        m5197b(str).m5184d(j);
    }

    /* renamed from: c */
    public static void m5195c(String str, long j) {
        m5197b(str).m5186c(j);
    }

    /* renamed from: d */
    public static void m5194d(String str, long j) {
        LogUtils.m8381c("statistic_LocalMediaStatistic", "setTimePlayed = " + j);
        m5197b(str).m5188b(j);
    }

    /* renamed from: a */
    public static void m5200a(String str) {
        C1769a c1769a = f5573a.get(str);
        if (c1769a != null && !StringUtils.m8346a(str)) {
            DebugUtils.m8426a(c1769a.m5185d(), "statisticItem SongId is null!");
            f5573a.remove(str);
            SessionStatisticEvent m4903b = StatisticUtils.m4903b("song", "listen_info", "local", str.hashCode());
            m4903b.put("song_id", str);
            m4903b.put("song_time", c1769a.m5187c());
            m4903b.put("file_size", c1769a.m5189b());
            if (c1769a.m5183e()) {
                m4903b.put("play_time", c1769a.m5187c());
            } else {
                m4903b.put("play_time", c1769a.m5193a());
            }
            m4903b.put("time", System.currentTimeMillis());
            m4903b.complete();
            StatisticUtils.m4912a(m4903b);
            LogUtils.m8382b("statistic_LocalMediaStatistic", "put local listen_info to statisticManager songId=%s play_time=%d song_time=%s file_size=%s ", str, Long.valueOf(c1769a.m5193a()), Long.valueOf(c1769a.m5187c()), Long.valueOf(c1769a.m5189b()));
        }
    }

    /* compiled from: LocalMediaStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.g$a */
    /* loaded from: classes.dex */
    public static class C1769a {

        /* renamed from: a */
        private long f5574a;

        /* renamed from: b */
        private long f5575b;

        /* renamed from: c */
        private long f5576c;

        /* renamed from: d */
        private long f5577d;

        /* renamed from: e */
        private String f5578e;

        /* renamed from: f */
        private boolean f5579f = false;

        /* renamed from: a */
        public void m5192a(long j) {
            this.f5574a = j;
        }

        /* renamed from: a */
        public long m5193a() {
            return this.f5575b;
        }

        /* renamed from: b */
        public void m5188b(long j) {
            this.f5575b = j;
        }

        /* renamed from: b */
        public long m5189b() {
            return this.f5576c;
        }

        /* renamed from: c */
        public void m5186c(long j) {
            this.f5576c = j;
        }

        /* renamed from: c */
        public long m5187c() {
            return this.f5577d;
        }

        /* renamed from: d */
        public void m5184d(long j) {
            this.f5577d = j;
        }

        /* renamed from: d */
        public String m5185d() {
            return this.f5578e;
        }

        /* renamed from: a */
        public void m5191a(String str) {
            this.f5578e = str;
        }

        /* renamed from: a */
        public void m5190a(boolean z) {
            this.f5579f = z;
        }

        /* renamed from: e */
        public boolean m5183e() {
            return this.f5579f;
        }
    }
}
