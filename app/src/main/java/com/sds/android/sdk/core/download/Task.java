package com.sds.android.sdk.core.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

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
    private int threadCount;

    /* renamed from: b */
    private Handler handler;

    /* renamed from: c */
    private TaskInfo taskInfo;

    /* renamed from: d */
    private TaskCallback taskCallback;

    /* renamed from: e */
    private byte[] tmpData;

    /* renamed from: f */
    private HttpGet httpGet;

    /* renamed from: g */
    private InputStream inputStream;

    /* renamed from: h */
    private FileOutputStream fileOutputStream;

    /* compiled from: Task.java */
    /* renamed from: com.sds.android.sdk.core.download.b$a */
    /* loaded from: classes.dex */
    public static abstract class TaskCallback {
        /* renamed from: a */
        public void mo2410a(TaskInfo taskInfo) {
        }

        /* renamed from: b */
        public void start(TaskInfo taskInfo) {
        }

        /* renamed from: c */
        public void downloaded(TaskInfo taskInfo) {
        }

        /* renamed from: a */
        public void error(TaskInfo taskInfo, ErrorCodeType enumC0579b) {
        }

        /* renamed from: b */
        public void mo2409b(TaskInfo taskInfo, ErrorCodeType enumC0579b) {
        }
    }

    public Task(TaskInfo taskInfo) {
        this.threadCount = 5;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.sds.android.sdk.core.download.b.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                synchronized (this) {
                    if (Task.this.taskInfo != null) {
                        switch (message.what) {
                            case 0:
                                Task.this.taskInfo.setState(1);
                                Task.this.taskCallback.mo2410a(Task.this.taskInfo);
                                break;
                            case 1:
                                Task.this.taskInfo.setFileLength(Integer.valueOf(message.arg1));
                                Task.this.taskInfo.setState(2);
                                Task.this.taskCallback.start(Task.this.taskInfo);
                                break;
                            case 2:
                                FileUtils.m8410c(Task.this.taskInfo.buildTmpPath(), Task.this.taskInfo.getSavePath());
                                Task.this.taskInfo.setDownloadSpend(0);
                                Task.this.taskInfo.setState(4);
                                Task.this.taskCallback.downloaded(Task.this.taskInfo);
                                break;
                            case 3:
                                Task.this.taskInfo.setDownloadSpend(0);
                                Task.this.taskInfo.setState(5);
                                Task.this.taskCallback.error(Task.this.taskInfo, (ErrorCodeType) message.obj);
                                break;
                            case 4:
                                Task.this.taskCallback.mo2409b(Task.this.taskInfo, (ErrorCodeType) message.obj);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid Message Id !!");
                        }
                    }
                }
            }
        };
        this.taskCallback = null;
        this.tmpData = new byte[8192];
        if (taskInfo == null) {
            throw new IllegalArgumentException("TaskInfo must not be null !!");
        }
        if (!taskInfo.resumeBrokenTransferSupported() && taskInfo.getDownloadLength() != 0) {
            throw new IllegalArgumentException("if resume broken transfer is not supported, downloadLength must be zero !!");
        }
        this.taskInfo = taskInfo;
        synchronized (this) {
            this.taskInfo.setState(0);
        }
    }

    public Task(TaskInfo taskInfo, TaskCallback abstractC0578a) {
        this(taskInfo);
        this.taskCallback = abstractC0578a;
    }

    /* renamed from: a */
    public TaskInfo m8737a() {
        return this.taskInfo;
    }

    /* renamed from: b */
    public void m8728b() {
        synchronized (this) {
            this.taskInfo.setState(3);
            this.taskInfo.setAttachTask(null);
            this.threadCount = 0;
            try {
                if (this.httpGet != null && !this.httpGet.isAborted()) {
                    this.httpGet.abort();
                }
                if (this.inputStream != null) {
                    this.inputStream.close();
                }
                if (this.fileOutputStream != null) {
                    this.fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.threadCount > 0 && !isRunning()) {
            execute();
        }
    }

    /* renamed from: c */
    private void execute() {
        try {
            if (!EnvironmentUtils.DeviceConfig.isConnected()) {
                m8734a(ErrorCodeType.NETWORK_UNAVAILABLE);
            }
            if (!this.taskInfo.resumeBrokenTransferSupported()) {
                FileUtils.exists(this.taskInfo.buildTmpPath());
            }
            File file = FileUtils.createFile(this.taskInfo.buildTmpPath());
            if (file == null) {
                m8734a(ErrorCodeType.FILE_CREATION);
                return;
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            int length = (int) file.length();
            if (this.taskInfo.resumeBrokenTransferSupported()) {
                if (this.taskInfo.getFileLength() != null && this.taskInfo.getFileLength().intValue() != 0) {
                    this.taskInfo.setDownloadLength(length);
                    if (length == this.taskInfo.getFileLength().intValue()) {
                        m8721d();
                        return;
                    }
                    m8735a(file.length(), hashMap);
                } else if (length != 0) {
                    FileUtils.exists(this.taskInfo.buildTmpPath());
                    file = FileUtils.createFile(this.taskInfo.buildTmpPath());
                    LogUtils.warning("com.sds.android.sdk.core.download.Task", "ResumeBrokenTransfer supported and TotalFileLen unknown, recreate File");
                }
            } else if (length != 0) {
                this.threadCount = 0;
                throw new IllegalStateException("not ResumeBrokenTransfer Supported, cached file len must be 0");
            }
            m8720e();
            if (m8730a(this.taskInfo.getSourceUrl(), file, hashMap)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            m8734a(ErrorCodeType.UNKNOWN);
        }
    }

    /* renamed from: a */
    private boolean m8730a(String str, File file, HashMap<String, Object> hashMap) throws InterruptedException {
        HttpRequest.Response m8729a = m8729a(str, hashMap);
        if (m8729a == null) {
            m8734a(EnvironmentUtils.DeviceConfig.isConnected() ? ErrorCodeType.URL_REQUEST_FAILED : ErrorCodeType.NETWORK_UNAVAILABLE);
            Thread.sleep(1000L);
            return false;
        }
        this.taskInfo.setResponseCode(m8729a.getStatusCode());
        if (m8732a(m8729a)) {
            if (m8725b(file, m8729a)) {
                return true;
            }
            m8736a((this.taskInfo.getFileLength() == null || this.taskInfo.getFileLength().intValue() == 0) ? m8729a.getContentLength() : this.taskInfo.getFileLength().intValue());
            download(file, m8729a);
        } else {
            m8734a(ErrorCodeType.URL_RESPONED_FAILED);
        }
        return false;
    }

    /* renamed from: a */
    private void download(File file, HttpRequest.Response response) {
        int i = 0;
        long j = 0;
        synchronized (this) {
            this.inputStream = response.getInputStream();
            try {
                this.fileOutputStream = new FileOutputStream(file, this.taskInfo.resumeBrokenTransferSupported());
                long currentTimeMillis = System.currentTimeMillis();
                int i2 = 0;
                int i3 = 0;
                while (!isRunning()) {
                    int read = this.inputStream.read(this.tmpData, 0, 8192);
                    if (read > 0) {
                        i = i2 + read;
                        this.fileOutputStream.write(this.tmpData, 0, read);
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (currentTimeMillis2 - currentTimeMillis >= 100) {
                            this.taskInfo.setDownloadSpend((int) ((i * 1000) / (currentTimeMillis2 - currentTimeMillis)));
                            i = 0;
                            j = currentTimeMillis2;
                        } else {
                            j = currentTimeMillis;
                        }
                        this.taskInfo.setDownloadLength(this.taskInfo.getDownloadLength() + read);
                    } else if (read >= 0) {
                        i = i2;
                        j = currentTimeMillis;
                    } else if (this.taskInfo.getFileLength() != null && this.taskInfo.getDownloadLength() >= this.taskInfo.getFileLength().intValue()) {
                        m8721d();
                        break;
                    } else {
                        int i4 = i3 + 1;
                        if (i3 <= 10) {
                            i3 = i4;
                            i = i2;
                            j = currentTimeMillis;
                        } else if (this.taskInfo.getFileLength() == null || this.taskInfo.getFileLength().intValue() <= 0) {
                            m8721d();
                        } else {
                            m8734a(ErrorCodeType.UNKNOWN);
                        }
                    }
                    currentTimeMillis = j;
                    i2 = i;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                m8734a(ErrorCodeType.UNKNOWN);
                try {
                    if (!this.httpGet.isAborted()) {
                        this.httpGet.abort();
                    }
                    this.fileOutputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: b */
    private boolean m8725b(File file, HttpRequest.Response c0586a) {
        if (EnvironmentUtils.C0605d.m8469a(file.getParentFile()) < c0586a.getContentLength()) {
            this.threadCount = 1;
            m8734a(ErrorCodeType.STORAGE);
            c0586a.close();
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m8732a(HttpRequest.Response c0586a) {
        int m8690c = c0586a.getStatusCode();
        return m8690c == 200 || m8690c == 206;
    }

    /* renamed from: a */
    private HttpRequest.Response m8729a(String str, HashMap<String, Object> hashMap) {
        if (this.taskInfo.getStatisticRequest()) {
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
    private HttpRequest.Response m8724b(String str, HashMap<String, Object> hashMap) {
        HttpRequest.Response c0586a = null;
        URI uri = null;
        String str2 = null;
        HttpRequest.Response c0586a2 = null;
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
                this.taskInfo.statisticConnectFailedIPs(hostAddress);
                this.threadCount++;
                m8734a(EnvironmentUtils.DeviceConfig.isConnected() ? ErrorCodeType.URL_REQUEST_FAILED : ErrorCodeType.NETWORK_UNAVAILABLE);
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
    private HttpRequest.Response m8722c(String str, HashMap<String, Object> hashMap) {
        synchronized (this) {
            this.httpGet = new HttpGet(str);
        }
        return HttpRequest.m8708a(this.httpGet, hashMap, (HashMap<String, Object>) null);
    }

    /* renamed from: a */
    private void m8735a(long j, HashMap<String, Object> hashMap) {
        hashMap.put("Range", "bytes=" + j + "-");
    }

    /* renamed from: a */
    private void m8734a(ErrorCodeType errorCodeType) {
        this.threadCount--;
        if (this.taskCallback != null && !isRunning() && this.threadCount == 0) {
            this.handler.sendMessage(Message.obtain(this.handler, 3, errorCodeType));
        }
        m8727b(errorCodeType);
    }

    /* renamed from: d */
    private void m8721d() {
        this.threadCount = 0;
        if (this.taskCallback != null && !isRunning()) {
            this.handler.sendMessage(Message.obtain(this.handler, 2));
        }
    }

    /* renamed from: b */
    private void m8727b(ErrorCodeType enumC0579b) {
        if (this.taskCallback != null && !isRunning()) {
            this.handler.sendMessage(Message.obtain(this.handler, 4, enumC0579b));
        }
    }

    /* renamed from: e */
    private void m8720e() {
        if (this.taskCallback != null && !isRunning() && this.taskInfo.getState().intValue() != 1 && this.taskInfo.getState().intValue() != 2) {
            this.handler.sendMessage(Message.obtain(this.handler, 0));
        }
    }

    /* renamed from: a */
    private void m8736a(int i) {
        if (this.taskCallback != null && !isRunning() && this.taskInfo.getState().intValue() != 2) {
            this.handler.sendMessage(Message.obtain(this.handler, 1, i, 0));
        }
    }

    /* renamed from: f */
    private boolean isRunning() {
        boolean z;
        synchronized (this) {
            z = this.taskInfo == null || this.taskInfo.getState().intValue() == 3;
        }
        return z;
    }

    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            if (this.taskInfo == ((Task) obj).m8737a()) {
                return true;
            }
            if (this.taskInfo != null) {
                return this.taskInfo.equals(((Task) obj).m8737a());
            }
        }
        return false;
    }

    public int hashCode() {
        return (((this.taskCallback != null ? this.taskCallback.hashCode() : 0) + (((this.taskInfo != null ? this.taskInfo.hashCode() : 0) + ((this.handler != null ? this.handler.hashCode() : 0) * 31)) * 31)) * 31) + (this.tmpData != null ? Arrays.hashCode(this.tmpData) : 0);
    }

    /* compiled from: Task.java */
    /* renamed from: com.sds.android.sdk.core.download.b$b */
    /* loaded from: classes.dex */
    public enum ErrorCodeType {
        FILE_CREATION(0),
        NETWORK_UNAVAILABLE(1),
        STORAGE(2),
        UNKNOWN(3),
        URL_REQUEST_FAILED(4),
        URL_RESPONED_FAILED(5),
        URL_DOMAIN_PARSE_FAILED(6);

        private int mErrorCode;

        ErrorCodeType(int i) {
            this.mErrorCode = i;
        }

        public int getErrorCode() {
            return this.mErrorCode;
        }
    }
}
