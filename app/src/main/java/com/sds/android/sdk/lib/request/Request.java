package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.sds.android.sdk.lib.request.m */
/* loaded from: classes.dex */
public abstract class Request<R extends BaseResult> {

    /* renamed from: a */
    private Class<R> resultModel;

    /* renamed from: b */
    private Class<?> f2421b;

    /* renamed from: c */
    private R result;

    /* renamed from: d */
    private long requestTime;

    /* renamed from: e */
    private HashMap<String, Object> paramsMaps = new HashMap<>();

    /* renamed from: f */
    private HashMap<String, Object> f2425f = new HashMap<>();

    /* renamed from: g */
    private HashMap<String, Object> f2426g = new HashMap<>();

    /* renamed from: h */
    private ArrayList<Object> f2427h = new ArrayList<>();

    /* renamed from: i */
    private String url;

    /* renamed from: j */
    private AsyncRequestTask asyncRequestTask;

    /* renamed from: k */
    private InterfaceC0601a f2430k;

    /* renamed from: l */
    private String uri;

    /* compiled from: Request.java */
    /* renamed from: com.sds.android.sdk.lib.request.m$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0601a {
        /* renamed from: a */
        String mo8527a(String str);
    }

    /* renamed from: a */
    protected abstract HttpRequest.Response doHttpRequest(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3);

    /* renamed from: a */
    public Request<R> m8543a(Class<?> cls) {
        this.f2421b = cls;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public Class<?> m8534d() {
        return this.f2421b;
    }

    public Request(Class<R> cls, String url) {
        if (!BaseResult.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("resultClass must be subClass of BaseResult!");
        }
        this.resultModel = cls;
        this.asyncRequestTask = new AsyncRequestTask();
        this.url = url;
        clear();
    }

    /* renamed from: a */
    public void m8545a(InterfaceC0601a interfaceC0601a) {
        this.f2430k = interfaceC0601a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public String getUrl() {
        return this.url;
    }

    /* renamed from: a */
    public Request<R> m8540a(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            putParams(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /* renamed from: b */
    public Request<R> putParams(String str, Object obj) {
        if (!isNull(obj)) {
            this.paramsMaps.put(str, obj.toString());
            clear();
        }
        return this;
    }

    /* renamed from: c */
    public Request<R> m8535c(String str, Object obj) {
        this.f2425f.put(str, obj);
        return this;
    }

    /* renamed from: a */
    public Request<R> m8542a(Object obj) {
        if (!isNull(obj)) {
            if (obj.getClass().isArray() || (obj instanceof Collection)) {
                obj = StringUtils.spliceStringAndArray("_", obj);
            }
            this.f2427h.add(obj);
            clear();
        }
        return this;
    }

    /* renamed from: b */
    private boolean isNull(Object obj) {
        return obj == null || StringUtils.isEmpty(obj.toString());
    }

    /* renamed from: d */
    public Request<R> m8533d(String str, Object obj) {
        if (!isNull(obj)) {
            this.f2426g.put(str, String.valueOf(obj));
            clear();
        }
        return this;
    }

    /* renamed from: e */
    public String m8532e() {
        return this.uri;
    }

    /* renamed from: f */
    public R execute() {
        LogUtils.info("Request", "in execute lookNetProblem");
        if (checkResultAndRequestTime()) {
            return this.result;
        }
        this.uri = mo8536c();
        LogUtils.info("Request", "in execute lookNetProblem url=%s", this.uri);
        return m8539b(doHttpRequest(this.uri + (this.uri.indexOf("?") == -1 ? "?" : "&") + "utdid=" + EnvironmentUtils.UUIDConfig.m8499a(), this.f2426g, this.paramsMaps, this.f2425f));
    }

    /* renamed from: a */
    public void m8544a(RequestCallback<R> requestCallback) {
        this.asyncRequestTask.m8562a(this, requestCallback, new Object[0]);
    }

    /* renamed from: g */
    protected boolean checkResultAndRequestTime() {
        return this.result != null && System.currentTimeMillis() < this.requestTime;
    }

    /* renamed from: h */
    public void clear() {
        this.result = null;
        this.requestTime = 0L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public String mo8536c() {
        String url = getUrl();
        String m8343a = StringUtils.arrayToString("/", this.f2427h);
        if (!StringUtils.isEmpty(m8343a)) {
            url = StringUtils.spliceStringAndArray("/", url, m8343a);
        }
        LogUtils.debug("Request", url);
        return url;
    }

    /* renamed from: a */
    protected R getResult(HttpRequest.Response c0586a) {
        if (c0586a == null) {
            LogUtils.error("Request", "Http request result is null, stop parse.");
            return null;
        }
        try {
            try {
                String m8347a = StringUtils.streamToString(c0586a.getInputStream());
                LogUtils.debug("Request", "TEST: jsonString %s", m8347a);
                if (this.f2430k != null) {
                    m8347a = this.f2430k.mo8527a(m8347a);
                }
                R r = (R) JSONUtils.fromJson(m8347a,  this.resultModel);
                try {
                    c0586a.getInputStream().close();
                    return r;
                } catch (Exception e) {
                    e.printStackTrace();
                    return r;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        } finally {
            try {
                c0586a.getInputStream().close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: i */
    public R m8528i() {
        R r;
        try {
            r = this.resultModel.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            r = null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            r = null;
        }
        if (r == null) {
            LogUtils.error("Request", "return null, this should not happen.");
        }
        return r;
    }

    /* renamed from: b */
    private R m8539b(HttpRequest.Response c0586a) {
        R result = getResult(c0586a);
        if (result != null) {
            this.requestTime = (result.getTTL() - HttpRequest.Response.getAge()) + System.currentTimeMillis();
        } else {
            result = m8528i();
            if (c0586a == null) {
                result.setCode(-1);
                result.setMessage("无法连接到服务器");
            } else {
                result.setCode(-2);
                result.setMessage(String.format("无法解析数据，HTTP返回代码%d", Integer.valueOf(c0586a.getStatusCode())));
            }
        }
        this.result = result;
        return result;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String next = null;
        String next2 = null;
        String next3 = null;
        String str4 = "";
        Iterator<String> it = this.f2426g.keySet().iterator();
        while (true) {
            str = str4;
            if (!it.hasNext()) {
                break;
            }
            str4 = str + it.next() + ":" + this.f2426g.get(next3) + " ";
        }
        String str5 = "";
        Iterator<String> it2 = this.paramsMaps.keySet().iterator();
        while (true) {
            str2 = str5;
            if (!it2.hasNext()) {
                break;
            }
            str5 = str2 + it2.next() + ":" + this.paramsMaps.get(next2) + " ";
        }
        String str6 = "";
        Iterator<String> it3 = this.f2425f.keySet().iterator();
        while (true) {
            str3 = str6;
            if (!it3.hasNext()) {
                break;
            }
            str6 = str3 + it3.next() + ":" + this.f2425f.get(next) + " ";
        }
        String str7 = "url: " + mo8536c();
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
