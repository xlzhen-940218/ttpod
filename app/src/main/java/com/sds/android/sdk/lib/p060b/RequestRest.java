package com.sds.android.sdk.lib.p060b;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.sds.android.sdk.lib.b.f */
/* loaded from: classes.dex */
public abstract class RequestRest {

    /* renamed from: a */
    private BaseResultRest f2353a;

    /* renamed from: b */
    private long age;

    /* renamed from: g */
    private String f2359g;

    /* renamed from: i */
    private InterfaceC0588a f2361i;

    /* renamed from: j */
    private String f2362j;

    /* renamed from: c */
    private HashMap<String, Object> f2355c = new HashMap<>();

    /* renamed from: d */
    private HashMap<String, Object> f2356d = new HashMap<>();

    /* renamed from: e */
    private HashMap<String, Object> f2357e = new HashMap<>();

    /* renamed from: f */
    private ArrayList<Object> f2358f = new ArrayList<>();

    /* renamed from: h */
    private AsyncRequestTaskRest f2360h = new AsyncRequestTaskRest();

    /* compiled from: RequestRest.java */
    /* renamed from: com.sds.android.sdk.lib.b.f$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0588a {
        /* renamed from: a */
        String m8662a(String str);
    }

    /* renamed from: a */
    protected abstract HttpRequest.Response mo8669a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3);

    public RequestRest(String str) {
        this.f2359g = str;
        m8664d();
    }

    /* renamed from: a */
    protected String m8673a() {
        return this.f2359g;
    }

    /* renamed from: a */
    public RequestRest m8670a(String str, Object obj) {
        if (!m8671a(obj)) {
            this.f2355c.put(str, obj.toString());
            m8664d();
        }
        return this;
    }

    /* renamed from: a */
    private boolean m8671a(Object obj) {
        return obj == null || StringUtils.isEmpty(obj.toString());
    }

    /* renamed from: b */
    public RequestRest m8666b(String str, Object obj) {
        if (!m8671a(obj)) {
            this.f2357e.put(str, String.valueOf(obj));
            m8664d();
        }
        return this;
    }

    /* renamed from: b */
    public BaseResultRest m8668b() {
        LogUtils.info("Request", "in execute lookNetProblem");
        if (m8665c()) {
            return this.f2353a;
        }
        this.f2362j = m8663e();
        LogUtils.info("Request", "in execute lookNetProblem url=%s", this.f2362j);
        return m8667b(mo8669a(this.f2362j, this.f2357e, this.f2355c, this.f2356d));
    }

    /* renamed from: c */
    protected boolean m8665c() {
        return this.f2353a != null && System.currentTimeMillis() < this.age;
    }

    /* renamed from: d */
    public void m8664d() {
        this.f2353a = null;
        this.age = 0L;
    }

    /* renamed from: e */
    protected String m8663e() {
        String m8673a = m8673a();
        String m8343a = StringUtils.arrayToString("/", this.f2358f);
        if (!StringUtils.isEmpty(m8343a)) {
            m8673a = StringUtils.spliceStringAndArray("/", m8673a, m8343a);
        }
        LogUtils.debug("Request", m8673a);
        return m8673a;
    }

    /* renamed from: a */
    protected BaseResultRest m8672a(HttpRequest.Response c0586a) {
        if (c0586a == null) {
            LogUtils.error("Request", "Http request result is null, stop parse.");
            return null;
        }
        BaseResultRest baseResultRest = new BaseResultRest();
        baseResultRest.m8680b(c0586a.getStatusCode());
        baseResultRest.m8682a(c0586a.getHeader("Location"));
        try {
            try {
                String m8347a = StringUtils.streamToString(c0586a.getInputStream());
                LogUtils.debug("Request", "TEST: jsonString %s", m8347a);
                if (this.f2361i != null) {
                    m8347a = this.f2361i.m8662a(m8347a);
                }
                baseResultRest.m8679b(m8347a);
                try {
                    c0586a.getInputStream().close();
                    return baseResultRest;
                } catch (Exception e) {
                    e.printStackTrace();
                    return baseResultRest;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    c0586a.getInputStream().close();
                    return baseResultRest;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return baseResultRest;
                }
            }
        } catch (Throwable th) {
            try {
                c0586a.getInputStream().close();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    /* renamed from: b */
    private BaseResultRest m8667b(HttpRequest.Response c0586a) {
        BaseResultRest m8672a = m8672a(c0586a);
        if (m8672a != null) {
            this.age = (m8672a.m8675f() - HttpRequest.Response.getAge()) + System.currentTimeMillis();
        } else {
            m8672a = new BaseResultRest(-1, "无法连接到服务器");
        }
        this.f2353a = m8672a;
        return m8672a;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String next= null;
        String next2= null;
        String next3 = null;
        String str4 = "";
        Iterator<String> it = this.f2357e.keySet().iterator();
        while (true) {
            str = str4;
            if (!it.hasNext()) {
                break;
            }
            str4 = str + it.next() + ":" + this.f2357e.get(next3) + " ";
        }
        String str5 = "";
        Iterator<String> it2 = this.f2355c.keySet().iterator();
        while (true) {
            str2 = str5;
            if (!it2.hasNext()) {
                break;
            }
            str5 = str2 + it2.next() + ":" + this.f2355c.get(next2) + " ";
        }
        String str6 = "";
        Iterator<String> it3 = this.f2356d.keySet().iterator();
        while (true) {
            str3 = str6;
            if (!it3.hasNext()) {
                break;
            }
            str6 = str3 + it3.next() + ":" + this.f2356d.get(next) + " ";
        }
        String str7 = "url: " + m8663e();
        if (!StringUtils.isEmpty(str)) {
            str7 = str7 + " " + str;
        }
        if (!StringUtils.isEmpty(str2)) {
            str7 = str7 + " " + str2;
        }
        if (!StringUtils.isEmpty(str3)) {
            return str7 + " " + str3;
        }
        return str7;
    }
}
