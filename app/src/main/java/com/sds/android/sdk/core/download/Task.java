package com.sds.android.sdk.core.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.http.client.methods.HttpGet;

/* renamed from: com.sds.android.sdk.core.download.b */
/* loaded from: classes.dex */
public final class Task implements Runnable {

    /* renamed from: a */
    private int f2315a;

    /* renamed from: b */
    private Handler f2316b;

    /* renamed from: c */
    private TaskInfo f2317c;

    /* renamed from: d */
    private AbstractC0578a f2318d;

    /* renamed from: e */
    private byte[] f2319e;

    /* renamed from: f */
    private HttpGet f2320f;

    /* renamed from: g */
    private InputStream f2321g;

    /* renamed from: h */
    private FileOutputStream f2322h;

    /* compiled from: Task.java */
    /* renamed from: com.sds.android.sdk.core.download.b$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0578a {
        /* renamed from: a */
        public void mo2410a(TaskInfo taskInfo) {
        }

        /* renamed from: b */
        public void mo2149b(TaskInfo taskInfo) {
        }

        /* renamed from: c */
        public void mo2148c(TaskInfo taskInfo) {
        }

        /* renamed from: a */
        public void mo2150a(TaskInfo taskInfo, EnumC0579b enumC0579b) {
        }

        /* renamed from: b */
        public void mo2409b(TaskInfo taskInfo, EnumC0579b enumC0579b) {
        }
    }

    public Task(TaskInfo taskInfo) {
        this.f2315a = 5;
        this.f2316b = new Handler(Looper.getMainLooper()) { // from class: com.sds.android.sdk.core.download.b.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                synchronized (this) {
                    if (Task.this.f2317c != null) {
                        switch (message.what) {
                            case 0:
                                Task.this.f2317c.setState(1);
                                Task.this.f2318d.mo2410a(Task.this.f2317c);
                                break;
                            case 1:
                                Task.this.f2317c.setFileLength(Integer.valueOf(message.arg1));
                                Task.this.f2317c.setState(2);
                                Task.this.f2318d.mo2149b(Task.this.f2317c);
                                break;
                            case 2:
                                FileUtils.m8410c(Task.this.f2317c.buildTmpPath(), Task.this.f2317c.getSavePath());
                                Task.this.f2317c.setDownloadSpend(0);
                                Task.this.f2317c.setState(4);
                                Task.this.f2318d.mo2148c(Task.this.f2317c);
                                break;
                            case 3:
                                Task.this.f2317c.setDownloadSpend(0);
                                Task.this.f2317c.setState(5);
                                Task.this.f2318d.mo2150a(Task.this.f2317c, (EnumC0579b) message.obj);
                                break;
                            case 4:
                                Task.this.f2318d.mo2409b(Task.this.f2317c, (EnumC0579b) message.obj);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid Message Id !!");
                        }
                    }
                }
            }
        };
        this.f2318d = null;
        this.f2319e = new byte[2048];
        if (taskInfo == null) {
            throw new IllegalArgumentException("TaskInfo must not be null !!");
        }
        if (!taskInfo.resumeBrokenTransferSupported() && taskInfo.getDownloadLength() != 0) {
            throw new IllegalArgumentException("if resume broken transfer is not supported, downloadLength must be zero !!");
        }
        this.f2317c = taskInfo;
        synchronized (this) {
            this.f2317c.setState(0);
        }
    }

    public Task(TaskInfo taskInfo, AbstractC0578a abstractC0578a) {
        this(taskInfo);
        this.f2318d = abstractC0578a;
    }

    /* renamed from: a */
    public TaskInfo m8737a() {
        return this.f2317c;
    }

    /* renamed from: b */
    public void m8728b() {
        synchronized (this) {
            this.f2317c.setState(3);
            this.f2317c.setAttachTask(null);
            this.f2315a = 0;
            try {
                if (this.f2320f != null && !this.f2320f.isAborted()) {
                    this.f2320f.abort();
                }
                if (this.f2321g != null) {
                    this.f2321g.close();
                }
                if (this.f2322h != null) {
                    this.f2322h.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.f2315a > 0 && !m8719f()) {
            m8723c();
        }
    }

    /* renamed from: c */
    private void m8723c() {
        try {
            if (!EnvironmentUtils.C0604c.m8474e()) {
                m8734a(EnumC0579b.NETWORK_UNAVAILABLE);
            }
            if (!this.f2317c.resumeBrokenTransferSupported()) {
                FileUtils.m8404h(this.f2317c.buildTmpPath());
            }
            File m8407e = FileUtils.m8407e(this.f2317c.buildTmpPath());
            if (m8407e == null) {
                m8734a(EnumC0579b.FILE_CREATION);
                return;
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            int length = (int) m8407e.length();
            if (this.f2317c.resumeBrokenTransferSupported()) {
                if (this.f2317c.getFileLength() != null && this.f2317c.getFileLength().intValue() != 0) {
                    this.f2317c.setDownloadLength(length);
                    if (length == this.f2317c.getFileLength().intValue()) {
                        m8721d();
                        return;
                    }
                    m8735a(m8407e.length(), hashMap);
                } else if (length != 0) {
                    FileUtils.m8404h(this.f2317c.buildTmpPath());
                    m8407e = FileUtils.m8407e(this.f2317c.buildTmpPath());
                    LogUtils.m8384b("com.sds.android.sdk.core.download.Task", "ResumeBrokenTransfer supported and TotalFileLen unknown, recreate File");
                }
            } else if (length != 0) {
                this.f2315a = 0;
                throw new IllegalStateException("not ResumeBrokenTransfer Supported, cached file len must be 0");
            }
            m8720e();
            if (m8730a(this.f2317c.getSourceUrl(), m8407e, hashMap)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            m8734a(EnumC0579b.UNKNOWN);
        }
    }

    /* renamed from: a */
    private boolean m8730a(String str, File file, HashMap<String, Object> hashMap) throws InterruptedException {
        HttpRequest.C0586a m8729a = m8729a(str, hashMap);
        if (m8729a == null) {
            m8734a(EnvironmentUtils.C0604c.m8474e() ? EnumC0579b.URL_REQUEST_FAILED : EnumC0579b.NETWORK_UNAVAILABLE);
            Thread.sleep(1000L);
            return false;
        }
        this.f2317c.setResponseCode(m8729a.m8690c());
        if (m8732a(m8729a)) {
            if (m8725b(file, m8729a)) {
                return true;
            }
            m8736a((this.f2317c.getFileLength() == null || this.f2317c.getFileLength().intValue() == 0) ? m8729a.m8687f() : this.f2317c.getFileLength().intValue());
            m8731a(file, m8729a);
        } else {
            m8734a(EnumC0579b.URL_RESPONED_FAILED);
        }
        return false;
    }

    /* renamed from: a */
    private void m8731a(File file, HttpRequest.C0586a c0586a) {
        int i = 0;
        long j = 0;
        synchronized (this) {
            this.f2321g = c0586a.m8688e();
            try {
            } finally {
                try {
                    if (!this.f2320f.isAborted()) {
                        this.f2320f.abort();
                    }
                    this.f2322h.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            this.f2322h = new FileOutputStream(file, this.f2317c.resumeBrokenTransferSupported());
            long currentTimeMillis = System.currentTimeMillis();
            int i2 = 0;
            int i3 = 0;
            while (!m8719f()) {
                int read = this.f2321g.read(this.f2319e, 0, 2048);
                if (read > 0) {
                    i = i2 + read;
                    this.f2322h.write(this.f2319e, 0, read);
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 - currentTimeMillis >= 100) {
                        this.f2317c.setDownloadSpend((int) ((i * 1000) / (currentTimeMillis2 - currentTimeMillis)));
                        i = 0;
                        j = currentTimeMillis2;
                    } else {
                        j = currentTimeMillis;
                    }
                    this.f2317c.setDownloadLength(this.f2317c.getDownloadLength() + read);
                } else if (read >= 0) {
                    i = i2;
                    j = currentTimeMillis;
                } else if (this.f2317c.getFileLength() != null && this.f2317c.getDownloadLength() >= this.f2317c.getFileLength().intValue()) {
                    m8721d();
                    break;
                } else {
                    int i4 = i3 + 1;
                    if (i3 <= 10) {
                        i3 = i4;
                        i = i2;
                        j = currentTimeMillis;
                    } else if (this.f2317c.getFileLength() == null || this.f2317c.getFileLength().intValue() <= 0) {
                        m8721d();
                    } else {
                        m8734a(EnumC0579b.UNKNOWN);
                    }
                }
                currentTimeMillis = j;
                i2 = i;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            m8734a(EnumC0579b.UNKNOWN);
            try {
                if (!this.f2320f.isAborted()) {
                    this.f2320f.abort();
                }
                this.f2322h.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    private boolean m8725b(File file, HttpRequest.C0586a c0586a) {
        if (EnvironmentUtils.C0605d.m8469a(file.getParentFile()) < c0586a.m8687f()) {
            this.f2315a = 1;
            m8734a(EnumC0579b.STORAGE);
            c0586a.m8686g();
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m8732a(HttpRequest.C0586a c0586a) {
        int m8690c = c0586a.m8690c();
        return m8690c == 200 || m8690c == 206;
    }

    /* renamed from: a */
    private HttpRequest.C0586a m8729a(String str, HashMap<String, Object> hashMap) {
        if (this.f2317c.getStatisticRequest()) {
            return m8724b(str, hashMap);
        }
        return m8722c(str, hashMap);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0061, code lost:
        r10.f2317c.setConnectedIP(r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0068  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private HttpRequest.C0586a m8724b(String str, HashMap<String, Object> hashMap) {
        HttpRequest.C0586a c0586a = null;
        URI uri = null;
        String str2 = null;
        HttpRequest.C0586a c0586a2 = null;
        Exception e;
        try {
            InetAddress[] allByName = InetAddress.getAllByName(new URI(str).getHost());
            int length = allByName.length;
            c0586a = null;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String hostAddress = allByName[i].getHostAddress();
                c0586a = m8722c((uri.getScheme() + "://") + hostAddress + "/" + str.substring(str2.length()), hashMap);
                if (c0586a != null && m8732a(c0586a)) {
                    break;
                }
                this.f2317c.statisticConnectFailedIPs(hostAddress);
                this.f2315a++;
                m8734a(EnvironmentUtils.C0604c.m8474e() ? EnumC0579b.URL_REQUEST_FAILED : EnumC0579b.NETWORK_UNAVAILABLE);
                i++;
            }
        } catch (URISyntaxException e3) {
            e = e3;
        } catch (UnknownHostException e4) {
            e = e4;
        }
        if (c0586a != null || !m8732a(c0586a)) {
            return m8722c(str, hashMap);
        }
        return c0586a;
    }

    /* renamed from: c */
    private HttpRequest.C0586a m8722c(String str, HashMap<String, Object> hashMap) {
        synchronized (this) {
            this.f2320f = new HttpGet(str);
        }
        return HttpRequest.m8708a(this.f2320f, hashMap, (HashMap<String, Object>) null);
    }

    /* renamed from: a */
    private void m8735a(long j, HashMap<String, Object> hashMap) {
        hashMap.put(HttpClientProxy.HEADER_RANGE, "bytes=" + j + "-");
    }

    /* renamed from: a */
    private void m8734a(EnumC0579b enumC0579b) {
        this.f2315a--;
        if (this.f2318d != null && !m8719f() && this.f2315a == 0) {
            this.f2316b.sendMessage(Message.obtain(this.f2316b, 3, enumC0579b));
        }
        m8727b(enumC0579b);
    }

    /* renamed from: d */
    private void m8721d() {
        this.f2315a = 0;
        if (this.f2318d != null && !m8719f()) {
            this.f2316b.sendMessage(Message.obtain(this.f2316b, 2));
        }
    }

    /* renamed from: b */
    private void m8727b(EnumC0579b enumC0579b) {
        if (this.f2318d != null && !m8719f()) {
            this.f2316b.sendMessage(Message.obtain(this.f2316b, 4, enumC0579b));
        }
    }

    /* renamed from: e */
    private void m8720e() {
        if (this.f2318d != null && !m8719f() && this.f2317c.getState().intValue() != 1 && this.f2317c.getState().intValue() != 2) {
            this.f2316b.sendMessage(Message.obtain(this.f2316b, 0));
        }
    }

    /* renamed from: a */
    private void m8736a(int i) {
        if (this.f2318d != null && !m8719f() && this.f2317c.getState().intValue() != 2) {
            this.f2316b.sendMessage(Message.obtain(this.f2316b, 1, i, 0));
        }
    }

    /* renamed from: f */
    private boolean m8719f() {
        boolean z;
        synchronized (this) {
            z = this.f2317c == null || this.f2317c.getState().intValue() == 3;
        }
        return z;
    }

    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            if (this.f2317c == ((Task) obj).m8737a()) {
                return true;
            }
            if (this.f2317c != null) {
                return this.f2317c.equals(((Task) obj).m8737a());
            }
        }
        return false;
    }

    public int hashCode() {
        return (((this.f2318d != null ? this.f2318d.hashCode() : 0) + (((this.f2317c != null ? this.f2317c.hashCode() : 0) + ((this.f2316b != null ? this.f2316b.hashCode() : 0) * 31)) * 31)) * 31) + (this.f2319e != null ? Arrays.hashCode(this.f2319e) : 0);
    }

    /* compiled from: Task.java */
    /* renamed from: com.sds.android.sdk.core.download.b$b */
    /* loaded from: classes.dex */
    public enum EnumC0579b {
        FILE_CREATION(0),
        NETWORK_UNAVAILABLE(1),
        STORAGE(2),
        UNKNOWN(3),
        URL_REQUEST_FAILED(4),
        URL_RESPONED_FAILED(5),
        URL_DOMAIN_PARSE_FAILED(6);
        
        private int mErrorCode;

        EnumC0579b(int i) {
            this.mErrorCode = i;
        }

        public int getErrorCode() {
            return this.mErrorCode;
        }
    }
}
