package com.sds.android.ttpod.framework.modules.core.p116d.p118b;

import com.sds.android.sdk.lib.util.LogUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

/* renamed from: com.sds.android.ttpod.framework.modules.core.d.b.b */
/* loaded from: classes.dex */
class HttpServer {

    /* renamed from: h */
    private static Hashtable<String, String> f6008h = new Hashtable<>();

    /* renamed from: a */
    private String f6009a;

    /* renamed from: b */
    private int f6010b;

    /* renamed from: c */
    private String f6011c;

    /* renamed from: d */
    private final ServerSocket f6012d;

    /* renamed from: e */
    private Thread f6013e;

    /* renamed from: f */
    private File f6014f;

    /* renamed from: g */
    private final InterfaceC1874a f6015g;

    /* compiled from: HttpServer.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.core.d.b.b$a */
    /* loaded from: classes.dex */
    interface InterfaceC1874a {
        /* renamed from: a */
        void mo4180a();
    }

    static {
        StringTokenizer stringTokenizer = new StringTokenizer("css\t\ttext/css htm\t\ttext/html html\t\ttext/html gif\t\timage/gif jpg\t\timage/jpeg jpeg\t\timage/jpeg png\t\timage/png ico\t\timage/x-icon swf\t\tapplication/x-shockwave-flash js\t\t\tapplication/javascript ");
        while (stringTokenizer.hasMoreTokens()) {
            f6008h.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
        }
    }

    public HttpServer(String str, int i, String str2, String str3, InterfaceC1874a interfaceC1874a) throws IOException {
        if (interfaceC1874a == null) {
            throw new IllegalArgumentException("callback must not be null!!");
        }
        this.f6015g = interfaceC1874a;
        this.f6009a = str;
        this.f6010b = i;
        this.f6014f = new File(str2);
        this.f6011c = str3;
        this.f6012d = new ServerSocket(this.f6010b, 0, InetAddress.getByName(this.f6009a));
        this.f6013e = new Thread(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.d.b.b.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        new HttpSession(HttpServer.this.f6012d.accept(), HttpServer.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        });
    }

    /* renamed from: a */
    public void m4218a() {
        if (this.f6013e.isAlive()) {
            throw new IllegalStateException("already started!");
        }
        this.f6013e.setDaemon(true);
        this.f6013e.start();
    }

    /* renamed from: b */
    public void m4205b() {
        try {
            this.f6012d.close();
            this.f6013e.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public String m4201c() {
        return this.f6011c;
    }

    /* renamed from: a */
    public HttpResponse m4212a(String str, String str2, Properties properties, Properties properties2) {
        LogUtils.m8388a("HttpServer", str2 + " '" + str + "' ");
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str3 = (String) propertyNames.nextElement();
            LogUtils.m8388a("HttpServer", "Header: '" + str3 + "' = '" + properties.getProperty(str3) + "'");
        }
        Enumeration<?> propertyNames2 = properties2.propertyNames();
        while (propertyNames2.hasMoreElements()) {
            String str4 = (String) propertyNames2.nextElement();
            LogUtils.m8388a("HttpServer", "Parameters: '" + str4 + "' = '" + properties2.getProperty(str4) + "'");
        }
        HttpResponse m4210a = m4210a(str, properties, this.f6014f);
        if (m4210a.m4224a() == "200 OK") {
            this.f6015g.mo4180a();
        }
        return m4210a;
    }

    /* renamed from: a */
    HttpResponse m4210a(String str, Properties properties, File file) {
        HttpResponse httpResponse;
        String replace = str.trim().replace(File.separatorChar, '/');
        if (replace.indexOf(63) >= 0) {
            replace = replace.substring(0, replace.indexOf(63));
        }
        HttpResponse httpResponse2 = (replace.startsWith("..") || replace.endsWith("..") || replace.indexOf("../") >= 0) ? new HttpResponse("403 Forbidden", "text/plain", "Forbidden: Can't access for security reasons.") : null;
        File file2 = new File(file, replace);
        HttpResponse httpResponse3 = (httpResponse2 != null || file2.exists()) ? httpResponse2 : new HttpResponse("404 Not Found", "text/plain", "Error 404, file not found.");
        if (httpResponse3 != null || !file2.isDirectory()) {
            httpResponse = httpResponse3;
        } else if (new File(file2, "index.html").exists()) {
            file2 = new File(file + replace + "/index.html");
            httpResponse = httpResponse3;
        } else if (new File(file2, "index.htm").exists()) {
            file2 = new File(file + replace + "/index.htm");
            httpResponse = httpResponse3;
        } else {
            httpResponse = new HttpResponse("403 Forbidden", "text/plain", "Forbidden: Can't access for security reasons.");
        }
        if (httpResponse == null) {
            try {
                int lastIndexOf = file2.getCanonicalPath().lastIndexOf(46);
                String str2 = lastIndexOf >= 0 ? f6008h.get(file2.getCanonicalPath().substring(lastIndexOf + 1).toLowerCase()) : null;
                HttpResponse httpResponse4 = new HttpResponse("200 OK", str2 == null ? "application/octet-stream" : str2, new FileInputStream(file2));
                httpResponse4.m4223a("Content-Length", "" + file2.length());
                return httpResponse4;
            } catch (IOException e) {
                return new HttpResponse("403 Forbidden", "text/plain", "Forbidden: Access file failed.");
            }
        }
        return httpResponse;
    }
}
