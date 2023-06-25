package com.sds.android.sdk.lib.p059a;

import android.util.Base64;


import com.sds.android.sdk.lib.p064d.OriginalByteArrayOutputStream;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.UrlUtils;
import com.sds.android.sdk.lib.util.ZIPUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

/* renamed from: com.sds.android.sdk.lib.a.a */
/* loaded from: classes.dex */
public class HttpRequest {

    /* renamed from: b */
    private static ThreadSafeClientConnManager threadSafeClientConnManager;

    /* renamed from: a */
    private static HttpClient httpClient = null;

    /* renamed from: c */
    private static boolean f2327c = false;

    /* renamed from: d */
    private static String authorization = "";

    /* renamed from: e */
    private static String f2329e = "";

    /* renamed from: f */
    private static String hostname = "";

    /* renamed from: g */
    private static int port = 8080;

    /* renamed from: h */
    private static boolean f2332h = false;

    /* renamed from: i */
    private static long f2333i = 0;

    /* renamed from: d */
    private static synchronized HttpClient getHttpClient() {
        HttpClient httpClient;
        synchronized (HttpRequest.class) {
            if (HttpRequest.httpClient == null) {
                HttpRequest.httpClient = initHttpClient();
            }
            if (m8704b()) {
                HttpRequest.httpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(hostname, port));
                LogUtils.debug("HttpRequest", "set use proxy " + f2327c);
            } else {
                HttpRequest.httpClient.getParams().removeParameter("http.route.default-proxy");
                LogUtils.debug("HttpRequest", "set remove proxy" + f2327c);
            }
            httpClient = HttpRequest.httpClient;
        }
        return httpClient;
    }

    /* renamed from: a */
    public static void m8715a(String str, int i, String str2, String str3) {
        hostname = str;
        port = i;
        authorization = str2;
        f2329e = str3;
    }

    /* renamed from: a */
    public static long m8718a() {
        return f2333i;
    }

    /* renamed from: a */
    public static void m8716a(long j) {
        f2333i = j;
    }

    /* renamed from: a */
    private static void m8707a(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(new BasicHeader("Authorization", "Basic " + Base64.encodeToString((authorization + ":" + f2329e).getBytes(), 0).trim()));
    }

    /* renamed from: b */
    public static boolean m8704b() {
        return f2327c;
    }

    /* renamed from: a */
    public static void m8705a(boolean z) {
        f2327c = z;
    }

    /* renamed from: b */
    public static void m8702b(boolean z) {
        f2332h = z;
    }

    /* renamed from: c */
    public static boolean m8701c() {
        return f2332h;
    }

    /* renamed from: e */
    private static HttpClient initHttpClient() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean((int) 400));
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, "Mozilla/5.0 (Linux; U; Android 2.3.6; en-us; Nexus S Build/GRK39F) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        ConnManagerParams.setTimeout(basicHttpParams, (long) 6000);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 6000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 6000);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        if (threadSafeClientConnManager == null) {
            threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        }
        return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
    }

    /* renamed from: a */
    public static C0586a m8713a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        LogUtils.debug("HttpRequest", "doGetRequest: " + str);
        return m8708a(new HttpGet(UrlUtils.m8333a(str, hashMap2)), hashMap, hashMap2);
    }

    /* renamed from: a */
    public static C0586a m8708a(HttpGet httpGet, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        try {
            buildHeader((HttpRequestBase) httpGet, hashMap);
            C0586a m8709a = m8709a(getHttpClient().execute(httpGet), (HttpRequestBase) httpGet);
            m8698f();
            return m8709a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            LogUtils.error("HttpRequest", "doGetRequest exception:" + th.toString());
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: f */
    private static void m8698f() {
        threadSafeClientConnManager.closeIdleConnections(30000L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: a */
    public static C0586a m8712a(String uri, HashMap<String, Object> headerMaps, HttpEntity httpEntity) {
        LogUtils.debug("HttpRequest", "doPostRequest: " + uri);
        try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(httpEntity);
            String str2 = (String) headerMaps.get("Connection");
            if (str2 == null || !"close".equals(str2)) {
                headerMaps.put("Connection", "close");
            }
            buildHeader((HttpRequestBase) httpPost, headerMaps);
            C0586a m8709a = m8709a(getHttpClient().execute(httpPost), (HttpRequestBase) httpPost);
            m8698f();
            return m8709a;
        } catch (Exception e) {
            LogUtils.error("HttpRequest", "doPostRequest exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static C0586a m8703b(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        try {
            LogUtils.debug("HttpRequest", "doPostRequest Content: " + hashMap2.toString());
            return m8712a(str, hashMap, m8710a(hashMap2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static C0586a m8714a(String str, HashMap<String, Object> hashMap, String str2) {
        try {
            LogUtils.debug("HttpRequest", "doPostRequest Content: " + str2);
            return m8712a(str, hashMap, m8711a(str2, hashMap));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static HttpEntity m8710a(HashMap<String, Object> hashMap) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (String str : hashMap.keySet()) {
                Object obj = hashMap.get(str);
                if (obj != null) {
                    if (obj.getClass().isArray() || (obj instanceof Collection)) {
                        obj = JSONUtils.build(obj);
                    }
                    String obj2 = obj.toString();
                    LogUtils.debug("HttpRequest", "POST:key=%s,value=%s", str, obj2);
                    arrayList.add(new BasicNameValuePair(str, obj2));
                }
            }
        }
        return new UrlEncodedFormEntity(arrayList, "UTF-8");
    }

    /* renamed from: a */
    private static HttpEntity m8711a(String str, Map<String, Object> map) throws Exception {
        StringEntity stringEntity = new StringEntity(str, "UTF-8");
        if (!"not-gzip".equals(map.get("Accept-Gzip")) && stringEntity.getContentLength() > 500) {
            OriginalByteArrayOutputStream m8329a = ZIPUtils.m8329a(str);
            InputStreamEntity inputStreamEntity = new InputStreamEntity(new ByteArrayInputStream(m8329a.m8583a()), m8329a.size());
            map.put("Content-Encoding", "gzip");
            return inputStreamEntity;
        }
        return stringEntity;
    }

    /* renamed from: a */
    private static void buildHeader(HttpRequestBase httpRequestBase, HashMap<String, Object> hashMap) {
        httpRequestBase.addHeader(new BasicHeader("Accept-Encoding", "gzip"));
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                httpRequestBase.addHeader(new BasicHeader(str, String.valueOf(hashMap.get(str))));
            }
        }
        if (m8704b()) {
            m8707a(httpRequestBase);
        }
    }

    /* renamed from: a */
    private static C0586a m8709a(HttpResponse httpResponse, HttpRequestBase httpRequestBase) throws Exception {
        InputStream m8331a;
        LogUtils.info("HttpRequest", "in createResultFromHttpResponse lookNetProblem");
        Header[] allHeaders = httpResponse.getAllHeaders();
        HttpEntity entity = httpResponse.getEntity();
        Header contentEncoding = entity.getContentEncoding();
        String value = contentEncoding != null ? contentEncoding.getValue() : null;
        if ("gzip".equalsIgnoreCase(value) || "deflate".equalsIgnoreCase(value)) {
            LogUtils.debug("HttpRequest", "TEST: unzip GZip or Deflate stream... lookNetProblem");
            m8331a = ZIPUtils.m8331a(entity.getContent());
        } else {
            LogUtils.info("HttpRequest", "createResultFromHttpResponse not zip format lookNetProblem");
            m8331a = entity.getContent();
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        long contentLength = entity.getContentLength();
        LogUtils.info("HttpRequest", "createResultFromHttpResponse statusCode=%d lookNetProblem", Integer.valueOf(statusCode));
        C0586a c0586a = new C0586a(statusCode, allHeaders, m8331a, (int) contentLength, httpRequestBase);
        LogUtils.info("HttpRequest", "createResultFromHttpResponse lookNetProblem isUseProxy=" + f2327c + "  host=" + hostname + "  port=" + port);
        if (f2332h && contentLength > 0) {
            f2333i += contentLength;
        }
        c0586a.f2343h = "bytes".equals(c0586a.m8694a("Accept-Ranges"));
        Header contentType = entity.getContentType();
        c0586a.f2341f = contentType != null ? contentType.getValue() : null;
        c0586a.f2342g = value;
        c0586a.f2344i = entity.isChunked();
        if (statusCode < 200 || statusCode >= 400) {
            String uri = httpRequestBase.getURI().toString();
            int indexOf = uri.indexOf(63);
            if (indexOf > 0) {
                uri = uri.substring(0, indexOf);
            }
            LogUtils.error("HttpRequest", "createResultFromHttpResponse lookNetProblem status=" + statusCode + " url=" + uri);
            m8717a(statusCode, uri);
        }
        LogUtils.info("HttpRequest", "createResultFromHttpResponse statusCode=%d exit function lookNetProblem", Integer.valueOf(statusCode));
        return c0586a;
    }

    /* renamed from: a */
    private static void m8717a(int i, String str) {
        Method[] methods;
        try {
            for (Method method : Class.forName(EnvironmentUtils.m8526a() + ".HttpRequestErrorHook").getMethods()) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 2 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class) {
                    method.invoke(null, Integer.valueOf(i), str);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: HttpRequest.java */
    /* renamed from: com.sds.android.sdk.lib.a.a$a */
    /* loaded from: classes.dex */
    public static final class C0586a {

        /* renamed from: j */
        private static long f2334j = 0;

        /* renamed from: k */
        private static long f2335k = 0;

        /* renamed from: a */
        private int f2336a;

        /* renamed from: b */
        private Header[] f2337b;

        /* renamed from: c */
        private InputStream f2338c;

        /* renamed from: d */
        private int f2339d;

        /* renamed from: e */
        private HttpRequestBase f2340e;

        /* renamed from: f */
        private String f2341f;

        /* renamed from: g */
        private String f2342g;

        /* renamed from: h */
        private boolean f2343h;

        /* renamed from: i */
        private boolean f2344i;

        /* renamed from: a */
        public static long m8697a() {
            return f2334j;
        }

        /* renamed from: b */
        public static long m8693b() {
            return f2335k;
        }

        public C0586a(int i, Header[] headerArr, InputStream inputStream, int i2, HttpRequestBase httpRequestBase) {
            this.f2336a = i;
            this.f2337b = headerArr;
            this.f2338c = inputStream;
            this.f2339d = i2;
            this.f2340e = httpRequestBase;
            f2334j = m8685h();
            f2335k = m8684i();
        }

        /* renamed from: c */
        public int m8690c() {
            return this.f2336a;
        }

        /* renamed from: d */
        public Header[] m8689d() {
            return this.f2337b;
        }

        /* renamed from: a */
        public String m8694a(String str) {
            Header[] headerArr;
            if (this.f2337b != null && this.f2337b.length > 0) {
                for (Header header : this.f2337b) {
                    if (header.getName().equals(str)) {
                        return header.getValue();
                    }
                }
            }
            return null;
        }

        /* renamed from: e */
        public InputStream m8688e() {
            return this.f2338c;
        }

        /* renamed from: f */
        public int m8687f() {
            return this.f2339d;
        }

        /* renamed from: g */
        public void m8686g() {
            if (this.f2338c != null) {
                try {
                    this.f2340e.abort();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    this.f2338c = null;
                    this.f2340e = null;
                }
            }
        }

        /* renamed from: h */
        private long m8685h() {
            long j = 0;
            Header[] m8689d = m8689d();
            if (m8689d != null) {
                for (Header header : m8689d) {
                    if ("age".equals(header.getName())) {
                        try {
                            j = Long.parseLong(header.getValue());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return j;
        }

        /* renamed from: i */
        private long m8684i() {
            Header[] m8689d = m8689d();
            int length = m8689d.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Header header = m8689d[i];
                if (header == null || !"Date".equals(header.getName())) {
                    i++;
                } else {
                    try {
                        return TimeUnit.MILLISECONDS.toSeconds(DateUtils.parseDate(header.getValue()).getTime());
                    } catch (DateParseException e) {
                        e.printStackTrace();
                        return 0L;
                    }
                }
            }
            return 0;
        }
    }
}
