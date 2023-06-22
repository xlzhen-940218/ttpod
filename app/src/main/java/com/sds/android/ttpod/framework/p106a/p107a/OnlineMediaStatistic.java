package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.framework.a.a.l */
/* loaded from: classes.dex */
public class OnlineMediaStatistic {

    /* renamed from: b */
    private static int f5584b;

    /* renamed from: a */
    private static Map<String, String> f5583a = new HashMap();

    /* renamed from: c */
    private static HashMap<Long, C1772a> f5585c = new HashMap<>();

    /* renamed from: a */
    public static void m5044a(Map<String, String> map) {
        f5583a = map;
    }

    /* renamed from: a */
    public static void m5054a() {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "transOrigin origin " + f5583a.toString());
        StatisticUtils.m4905a(f5583a);
    }

    /* renamed from: a */
    public static void m5045a(String str) {
        f5583a.put("origin", str);
    }

    /* renamed from: b */
    public static String m5043b() {
        return f5583a.get("origin");
    }

    /* renamed from: a */
    public static void m5047a(Integer num) {
        f5583a.put("list_type", String.valueOf(num));
    }

    /* renamed from: c */
    public static Integer m5037c() {
        try {
            String str = f5583a.get("list_type");
            if (!StringUtils.m8346a(str)) {
                return Integer.valueOf(Integer.parseInt(str));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /* renamed from: b */
    public static void m5038b(String str) {
        f5583a.put("list_id", str);
    }

    /* renamed from: d */
    public static String m5033d() {
        return f5583a.get("list_id");
    }

    /* renamed from: a */
    public static void m5046a(Long l) {
        f5583a.put("start_play_song_id", String.valueOf(l));
    }

    /* renamed from: e */
    public static Long m5031e() {
        try {
            String str = f5583a.get("start_play_song_id");
            if (!StringUtils.m8346a(str)) {
                return Long.valueOf(Long.parseLong(str));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    /* renamed from: a */
    public static void m5053a(int i) {
        f5584b = i;
    }

    /* renamed from: f */
    public static int m5029f() {
        return f5584b;
    }

    /* renamed from: a */
    public static void m5048a(long j, boolean z) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setCompleted = " + z);
        m5042b(j).m5013a(z);
    }

    /* renamed from: a */
    public static void m5050a(long j, long j2) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setTimeStartPlay = " + j2);
        m5042b(j).m4978n(j2);
    }

    /* renamed from: b */
    public static void m5040b(long j, long j2) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setTimePlayed = " + j2);
        m5042b(j).m4988i(j2);
    }

    /* renamed from: c */
    public static void m5035c(long j, long j2) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setTimeLoadComplete = " + j2);
        m5042b(j).m4982l(j2);
    }

    /* renamed from: d */
    public static void m5032d(long j, long j2) {
        m5042b(j).m4980m(j2);
    }

    /* renamed from: e */
    public static void m5030e(long j, long j2) {
        m5042b(j).m4984k(j2);
    }

    /* renamed from: f */
    public static void m5028f(long j, long j2) {
        m5042b(j).m4990h(j2);
    }

    /* renamed from: g */
    public static void m5026g(long j, long j2) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setTimeHttpHeaderReceived = " + j2);
        C1772a m5042b = m5042b(j);
        if (m5042b.m4993g() == 0) {
            m5042b.m5003c(j2);
        } else {
            m5042b.m4994f(j2);
        }
    }

    /* renamed from: h */
    public static void m5025h(long j, long j2) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setTimeDNSDone = " + j2);
        C1772a m5042b = m5042b(j);
        if (m5042b.m4998e() == 0) {
            m5042b.m5019a(j2);
        } else {
            m5042b.m5000d(j2);
        }
    }

    /* renamed from: i */
    public static void m5024i(long j, long j2) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setTimeConnectDone = " + j2);
        C1772a m5042b = m5042b(j);
        if (m5042b.m4995f() == 0) {
            m5042b.m5010b(j2);
        } else {
            m5042b.m4997e(j2);
        }
    }

    /* renamed from: a */
    public static void m5051a(long j, int i) {
        m5042b(j).m5011b(i);
    }

    /* renamed from: b */
    public static void m5041b(long j, int i) {
        m5042b(j).m5020a(i);
    }

    /* renamed from: j */
    public static void m5023j(long j, long j2) {
        m5042b(j).m4992g(j2);
    }

    /* renamed from: a */
    public static void m5049a(long j, String str) {
        m5042b(j).m5014a(str);
    }

    /* renamed from: b */
    public static void m5039b(long j, boolean z) {
        m5042b(j).m5006b(z);
    }

    /* renamed from: k */
    public static void m5022k(long j, long j2) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setTimeGetData = " + j2);
        m5042b(j).m4986j(j2);
    }

    /* renamed from: c */
    public static void m5036c(long j, int i) {
        LogUtils.m8381c("statistic_OnlineStatisticHolder", "setListenPostion = " + i);
        m5042b(j).m5004c(i);
    }

    /* renamed from: a */
    public static void m5052a(long j) {
        C1772a c1772a = f5585c.get(Long.valueOf(j));
        if (c1772a != null && j != 0) {
            f5585c.remove(Long.valueOf(j));
            DebugUtils.m8426a(c1772a.m4985k(), "statisticItem Origin is null!");
            SessionStatisticEvent m4903b = StatisticUtils.m4903b("song", "listen_info", c1772a.m4985k(), j);
            m4903b.put("song_id", j);
            m4903b.put("song_time", c1772a.m4983l());
            m4903b.put("file_size", c1772a.m4979n());
            if (c1772a.m5021a()) {
                m4903b.put("play_time", c1772a.m4983l());
            } else {
                m4903b.put("play_time", c1772a.m4977o());
            }
            m4903b.put("play_control", c1772a.m4976p());
            m4903b.put("time", System.currentTimeMillis());
            m4903b.put("is_buffer", c1772a.m4975q() ? 1 : 0);
            if (!c1772a.m4975q()) {
                m4903b.put("response_time", TimeUnit.NANOSECONDS.toMillis(c1772a.m4974r()));
                m4903b.put("loading_time", TimeUnit.NANOSECONDS.toMillis(c1772a.m4973s()));
                m4903b.put("buffer_time", TimeUnit.NANOSECONDS.toMillis(c1772a.m4972t()));
                m4903b.put("buffer_size", c1772a.m4970v());
                int m4971u = c1772a.m4971u();
                m4903b.put("buffer_count", m4971u);
                if (m4971u > 0) {
                    m4903b.put("cutoff_times", StringUtils.m8343a(" ", c1772a.f5606u));
                }
                m4903b.put("url", c1772a.m4981m());
                m4903b.put("dnsdone_time", TimeUnit.NANOSECONDS.toMillis(c1772a.m5012b()));
                m4903b.put("connetdone_time", TimeUnit.NANOSECONDS.toMillis(c1772a.m5005c()));
                m4903b.put("httpheader_received_time", TimeUnit.NANOSECONDS.toMillis(c1772a.m5001d()));
                long m4991h = c1772a.m4991h();
                long m4989i = c1772a.m4989i();
                long m4987j = c1772a.m4987j();
                if (m4991h > 0 || m4989i > 0 || m4987j > 0) {
                    m4903b.put("dnsdone_time2", TimeUnit.NANOSECONDS.toMillis(m4991h));
                    m4903b.put("connetdone_time2", TimeUnit.NANOSECONDS.toMillis(m4989i));
                    m4903b.put("httpheader_received_time2", TimeUnit.NANOSECONDS.toMillis(m4987j));
                }
                m4903b.put("postion", c1772a.m4969w());
            }
            m4903b.complete();
            StatisticUtils.m4912a(m4903b);
            LogUtils.m8382b("statistic_OnlineStatisticHolder", "put online listen_info to statisticManager songId=%d play_time=%d origin=%s song_time=%s file_size=%s play_control=%s is_buffer=%s postion=%d", Long.valueOf(j), Long.valueOf(c1772a.m4977o()), c1772a.m4985k(), Long.valueOf(c1772a.m4983l()), Long.valueOf(c1772a.m4979n()), Integer.valueOf(c1772a.m4976p()), Boolean.valueOf(c1772a.m4975q()), Integer.valueOf(c1772a.m4969w()));
            ListStatistic.m5207a(c1772a.f5608w, c1772a.f5609x, c1772a.f5610y, j, c1772a.m4969w(), c1772a.f5611z);
        }
    }

    /* renamed from: b */
    private static C1772a m5042b(long j) {
        C1772a c1772a = f5585c.get(Long.valueOf(j));
        if (c1772a == null) {
            C1772a c1772a2 = new C1772a(m5043b());
            c1772a2.f5609x = m5033d();
            c1772a2.f5608w = m5037c().intValue();
            c1772a2.f5610y = m5031e().longValue();
            c1772a2.f5611z = m5027g();
            f5585c.put(Long.valueOf(j), c1772a2);
            return c1772a2;
        }
        return c1772a;
    }

    /* renamed from: c */
    public static void m5034c(String str) {
        f5583a.put("uuid", str);
    }

    /* renamed from: g */
    public static String m5027g() {
        return f5583a.get("uuid");
    }

    /* compiled from: OnlineMediaStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.l$a */
    /* loaded from: classes.dex */
    public static class C1772a {

        /* renamed from: a */
        private long f5586a;

        /* renamed from: b */
        private long f5587b;

        /* renamed from: c */
        private long f5588c;

        /* renamed from: d */
        private long f5589d;

        /* renamed from: e */
        private long f5590e;

        /* renamed from: f */
        private long f5591f;

        /* renamed from: g */
        private long f5592g;

        /* renamed from: h */
        private long f5593h;

        /* renamed from: i */
        private long f5594i;

        /* renamed from: j */
        private long f5595j;

        /* renamed from: k */
        private long f5596k;

        /* renamed from: l */
        private int f5597l;

        /* renamed from: m */
        private boolean f5598m;

        /* renamed from: o */
        private long f5600o;

        /* renamed from: p */
        private long f5601p;

        /* renamed from: q */
        private long f5602q;

        /* renamed from: r */
        private String f5603r;

        /* renamed from: s */
        private String f5604s;

        /* renamed from: v */
        private int f5607v;

        /* renamed from: w */
        private int f5608w;

        /* renamed from: x */
        private String f5609x;

        /* renamed from: y */
        private long f5610y;

        /* renamed from: z */
        private String f5611z;

        /* renamed from: n */
        private int f5599n = 1;

        /* renamed from: t */
        private boolean f5605t = false;

        /* renamed from: u */
        private ArrayList<Integer> f5606u = new ArrayList<>();

        /* renamed from: a */
        public void m5013a(boolean z) {
            this.f5605t = z;
        }

        /* renamed from: a */
        public boolean m5021a() {
            return this.f5605t;
        }

        public C1772a(String str) {
            this.f5604s = str;
        }

        /* renamed from: b */
        public long m5012b() {
            if (this.f5587b == 0) {
                return 0L;
            }
            return this.f5587b - this.f5586a;
        }

        /* renamed from: c */
        public long m5005c() {
            if (this.f5588c == 0) {
                return 0L;
            }
            return this.f5588c - this.f5587b;
        }

        /* renamed from: d */
        public long m5001d() {
            if (this.f5589d == 0) {
                return 0L;
            }
            return this.f5589d - this.f5588c;
        }

        /* renamed from: e */
        public long m4998e() {
            return this.f5587b;
        }

        /* renamed from: a */
        public void m5019a(long j) {
            this.f5587b = j;
        }

        /* renamed from: f */
        public long m4995f() {
            return this.f5588c;
        }

        /* renamed from: b */
        public void m5010b(long j) {
            this.f5588c = j;
        }

        /* renamed from: g */
        public long m4993g() {
            return this.f5589d;
        }

        /* renamed from: c */
        public void m5003c(long j) {
            this.f5589d = j;
        }

        /* renamed from: d */
        public void m5000d(long j) {
            this.f5590e = j;
        }

        /* renamed from: e */
        public void m4997e(long j) {
            this.f5591f = j;
        }

        /* renamed from: f */
        public void m4994f(long j) {
            this.f5592g = j;
        }

        /* renamed from: h */
        public long m4991h() {
            if (this.f5590e == 0) {
                return 0L;
            }
            return this.f5590e - this.f5589d;
        }

        /* renamed from: i */
        public long m4989i() {
            if (this.f5591f == 0) {
                return 0L;
            }
            return this.f5591f - this.f5590e;
        }

        /* renamed from: j */
        public long m4987j() {
            if (this.f5592g == 0) {
                return 0L;
            }
            return this.f5592g - this.f5591f;
        }

        /* renamed from: k */
        public String m4985k() {
            return this.f5604s;
        }

        /* renamed from: l */
        public long m4983l() {
            return this.f5602q;
        }

        /* renamed from: g */
        public void m4992g(long j) {
            this.f5602q = j;
        }

        /* renamed from: a */
        public void m5014a(String str) {
            this.f5603r = str;
        }

        /* renamed from: m */
        public String m4981m() {
            return this.f5603r;
        }

        /* renamed from: n */
        public long m4979n() {
            return this.f5601p;
        }

        /* renamed from: h */
        public void m4990h(long j) {
            this.f5601p = j;
        }

        /* renamed from: i */
        public void m4988i(long j) {
            this.f5600o = j;
        }

        /* renamed from: o */
        public long m4977o() {
            return this.f5600o;
        }

        /* renamed from: p */
        public int m4976p() {
            return this.f5599n;
        }

        /* renamed from: a */
        public void m5020a(int i) {
            this.f5599n = i;
        }

        /* renamed from: q */
        public boolean m4975q() {
            return this.f5598m;
        }

        /* renamed from: b */
        public void m5006b(boolean z) {
            this.f5598m = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: n */
        public void m4978n(long j) {
            this.f5586a = j;
        }

        /* renamed from: j */
        public void m4986j(long j) {
            this.f5593h = j;
        }

        /* renamed from: k */
        public void m4984k(long j) {
            this.f5594i = j;
        }

        /* renamed from: l */
        public void m4982l(long j) {
            if (this.f5595j == 0) {
                this.f5595j = j;
            }
        }

        /* renamed from: m */
        public void m4980m(long j) {
            this.f5596k = j;
        }

        /* renamed from: b */
        public void m5011b(int i) {
            if (!this.f5606u.contains(Integer.valueOf(i))) {
                this.f5597l++;
                this.f5606u.add(Integer.valueOf(i));
            }
        }

        /* renamed from: r */
        public long m4974r() {
            if (this.f5593h == 0) {
                return 0L;
            }
            return this.f5593h - this.f5586a;
        }

        /* renamed from: s */
        public long m4973s() {
            if (this.f5594i == 0) {
                return 0L;
            }
            return this.f5594i - this.f5593h;
        }

        /* renamed from: t */
        public long m4972t() {
            if (this.f5595j == 0) {
                return 0L;
            }
            return this.f5595j - this.f5593h;
        }

        /* renamed from: u */
        public int m4971u() {
            return this.f5597l;
        }

        /* renamed from: v */
        public long m4970v() {
            return this.f5596k;
        }

        /* renamed from: w */
        public int m4969w() {
            return this.f5607v;
        }

        /* renamed from: c */
        public void m5004c(int i) {
            this.f5607v = i;
        }
    }
}
