package com.sds.android.ttpod.component.apshare;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* renamed from: com.sds.android.ttpod.component.apshare.e */
/* loaded from: classes.dex */
public class ReceiveSongClient {

    /* renamed from: b */
    private DataTransferListener f3720b;

    /* renamed from: c */
    private Handler f3721c;

    /* renamed from: d */
    private String f3722d;

    /* renamed from: f */
    private SocketClient f3724f;

    /* renamed from: a */
    private String f3719a = TTPodConfig.getSongPath();

    /* renamed from: e */
    private ExecutorService f3723e = Executors.newFixedThreadPool(1);

    /* renamed from: g */
    private boolean f3725g = false;

    /* compiled from: ReceiveSongClient.java */
    /* renamed from: com.sds.android.ttpod.component.apshare.e$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1118a {
        /* renamed from: a */
        void mo5779a();
    }

    public ReceiveSongClient(String str, Handler handler, DataTransferListener dataTransferListener) {
        this.f3722d = str;
        this.f3720b = dataTransferListener;
        this.f3721c = handler;
        if (TextUtils.isEmpty(this.f3719a)) {
            throw new IllegalArgumentException("storeDir must be valid");
        }
        File file = new File(this.f3719a);
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IllegalArgumentException("storeDir must be valid");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7088a(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int parseInt = Integer.parseInt(bufferedReader.readLine());
            LogUtils.info("ReceiveSongClient", "count = " + parseInt);
            Message obtainMessage = this.f3721c.obtainMessage();
            obtainMessage.what = ApShareReceiveFragment.WHAT_GET_SHARED_LIST_COMPLETE;
            obtainMessage.obj = Integer.valueOf(parseInt);
            this.f3721c.sendMessage(obtainMessage);
            for (int i = 0; i < parseInt; i++) {
                final TransmittableMediaItem m7118a = ApShareUtils.m7118a(bufferedReader);
                if (m7118a != null) {
                    this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (ReceiveSongClient.this.f3720b != null) {
                                ReceiveSongClient.this.f3720b.mo7107d(m7118a);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            LogUtils.error("ReceiveSongClient", e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x023c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m7087a(InputStream inputStream, TransmittableMediaItem transmittableMediaItem) {
        long j;
        FileOutputStream fileOutputStream;
        int read;
        byte[] bArr = new byte[32768];
        FileOutputStream fileOutputStream2 = null;
         long j2 = 0;
        Throwable th;
        Exception e;
        File file = null;
        MediaItem m7119a = ApShareUtils.m7119a(transmittableMediaItem.m5772a());
        final TransmittableMediaItem transmittableMediaItem2 = new TransmittableMediaItem(m7119a);
        try {
            String str = ApShareUtils.m7115a(m7119a.getTitle().trim(), "") + "." + ApShareUtils.m7111b(m7119a.getLocalDataSource());
            String str2 = this.f3719a.endsWith(File.separator) ? this.f3719a + str : this.f3719a + File.separatorChar + str;
            m7119a.setLocalDataSource(str2);
            File file2 = new File(str2);
            try {
                m7089a(file2);
                FileOutputStream fileOutputStream3 = new FileOutputStream(str2);
                do {
                    try {
                        read = inputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream3.write(bArr, 0, read);
                        j2 += read;
                        if (this.f3720b != null) {
                            long finalJ = j2;
                            this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    ReceiveSongClient.this.f3720b.mo7108a(transmittableMediaItem2, finalJ);
                                }
                            });
                        }
                        if (j2 >= m7119a.getSize()) {
                            break;
                        }
                    } catch (Exception e1) {
                        e = e1;
                        j = j2;
                        fileOutputStream = fileOutputStream3;
                        file = file2;
                        try {
                            LogUtils.error("ReceiveSongClient", e.getMessage(), e);
                            if (this.f3720b != null && j == m7119a.getSize()) {
                                this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.3
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ReceiveSongClient.this.f3720b.mo7106e(transmittableMediaItem2);
                                    }
                                });
                                MediaItem m4712a = MediaItemUtils.m4712a(m7119a.getLocalDataSource());
                                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_RECENTLY_ADD, m4712a));
                                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, m4712a));
                            } else if (this.f3720b != null) {
                                LogUtils.info("ReceiveSongClient", "[apshare]-transfer canceled");
                                if (this.f3720b != null) {
                                    this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            ReceiveSongClient.this.f3720b.mo7104g(transmittableMediaItem2);
                                        }
                                    });
                                }
                                m7089a(file);
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                    return;
                                } catch (Exception e2) {
                                    LogUtils.error("ReceiveSongClient", e2.getMessage(), e2);
                                    return;
                                }
                            }
                            return;
                        } catch (Throwable th1) {
                            th = th1;
                            fileOutputStream2 = fileOutputStream;
                            j2 = j;
                            if (this.f3720b == null && j2 == m7119a.getSize()) {
                                this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.3
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ReceiveSongClient.this.f3720b.mo7106e(transmittableMediaItem2);
                                    }
                                });
                                MediaItem m4712a2 = MediaItemUtils.m4712a(m7119a.getLocalDataSource());
                                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_RECENTLY_ADD, m4712a2));
                                CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, m4712a2));
                            } else if (this.f3720b != null) {
                                LogUtils.info("ReceiveSongClient", "[apshare]-transfer canceled");
                                if (this.f3720b != null) {
                                    this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            ReceiveSongClient.this.f3720b.mo7104g(transmittableMediaItem2);
                                        }
                                    });
                                }
                                m7089a(file);
                            }
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (Exception e3) {
                                    LogUtils.error("ReceiveSongClient", e3.getMessage(), e3);
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream2 = fileOutputStream3;
                        file = file2;
                        if (this.f3720b == null) {
                        }
                        if (this.f3720b != null) {
                        }
                        if (fileOutputStream2 != null) {
                        }
                        throw th;
                    }
                } while (read > 0);
                fileOutputStream3.flush();
                LogUtils.info("ReceiveSongClient", "=== 下载完成..." + m7119a.getTitle() + ", transfer size = " + j2 + " === ");
                if (this.f3720b != null && j2 == m7119a.getSize()) {
                    this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.3
                        @Override // java.lang.Runnable
                        public void run() {
                            ReceiveSongClient.this.f3720b.mo7106e(transmittableMediaItem2);
                        }
                    });
                    MediaItem m4712a3 = MediaItemUtils.m4712a(m7119a.getLocalDataSource());
                    CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_RECENTLY_ADD, m4712a3));
                    CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, m4712a3));
                } else if (this.f3720b != null) {
                    LogUtils.info("ReceiveSongClient", "[apshare]-transfer canceled");
                    if (this.f3720b != null) {
                        this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.4
                            @Override // java.lang.Runnable
                            public void run() {
                                ReceiveSongClient.this.f3720b.mo7104g(transmittableMediaItem2);
                            }
                        });
                    }
                    m7089a(file2);
                }
                if (fileOutputStream3 != null) {
                    try {
                        fileOutputStream3.close();
                    } catch (Exception e4) {
                        LogUtils.error("ReceiveSongClient", e4.getMessage(), e4);
                    }
                }
            } catch (Exception e5) {
                e = e5;
                file = file2;
                j = 0;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                file = file2;
            }
        } catch (Exception e6) {
            e = e6;
            j = 0;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: a */
    private void m7089a(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ReceiveSongClient.java */
    /* renamed from: com.sds.android.ttpod.component.apshare.e$5 */
    /* loaded from: classes.dex */
    public class CallableC11055 implements Callable<Boolean> {

        /* renamed from: a */
        final /* synthetic */ String f3735a;

        /* renamed from: b */
        final /* synthetic */ ClientModel f3736b;

        /* renamed from: c */
        final /* synthetic */ InterfaceC1118a f3737c;

        CallableC11055(String str, ClientModel clientModel, InterfaceC1118a interfaceC1118a) {
            this.f3735a = str;
            this.f3736b = clientModel;
            this.f3737c = interfaceC1118a;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() throws Exception {
            ReceiveSongClient.this.m7082c();
            ReceiveSongClient.this.f3724f.m7061a(new SocketClient.InterfaceC1127b() { // from class: com.sds.android.ttpod.component.apshare.e.5.1
                @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1127b
                /* renamed from: a */
                public void mo5773a(OutputStream outputStream) {
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.print(CallableC11055.this.f3735a + "\r\n");
                    printWriter.print(JSONUtils.toJson(CallableC11055.this.f3736b) + "\r\n");
                    printWriter.flush();
                    if (CallableC11055.this.f3737c != null) {
                        ReceiveSongClient.this.f3721c.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.5.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CallableC11055.this.f3737c.mo5779a();
                            }
                        });
                    }
                }
            });
            ReceiveSongClient.this.f3724f.m7062a(new SocketClient.InterfaceC1126a() { // from class: com.sds.android.ttpod.component.apshare.e.5.2
                @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1126a
                /* renamed from: a */
                public void mo7059a(InputStream inputStream) {
                    try {
                        String readLine = new BufferedReader(new InputStreamReader(inputStream)).readLine();
                        LogUtils.info("ReceiveSongClient", "msg: " + readLine);
                        if (readLine.equals("black_list")) {
                            ReceiveSongClient.this.m7080d();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            ReceiveSongClient.this.f3724f.m7060b();
            return Boolean.valueOf(ReceiveSongClient.this.f3725g);
        }
    }

    /* renamed from: a */
    public Future<Boolean> m7085a(String str, ClientModel clientModel, InterfaceC1118a interfaceC1118a) {
        return this.f3723e.submit(new CallableC11055(str, clientModel, interfaceC1118a));
    }

    /* renamed from: a */
    public void m7096a() {
        LogUtils.info("ReceiveSongClient", "is in black list " + this.f3725g);
        if (!this.f3725g) {
            this.f3723e.execute(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.6
                @Override // java.lang.Runnable
                public void run() {
                    ReceiveSongClient.this.m7082c();
                    ReceiveSongClient.this.f3724f.m7061a(new SocketClient.InterfaceC1127b() { // from class: com.sds.android.ttpod.component.apshare.e.6.1
                        @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1127b
                        /* renamed from: a */
                        public void mo5773a(OutputStream outputStream) {
                            ReceiveSongClient.this.m7086a(outputStream, "get_share_song_list");
                        }
                    });
                    ReceiveSongClient.this.f3724f.m7062a(new SocketClient.InterfaceC1126a() { // from class: com.sds.android.ttpod.component.apshare.e.6.2
                        @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1126a
                        /* renamed from: a */
                        public void mo7059a(InputStream inputStream) {
                            ReceiveSongClient.this.m7088a(inputStream);
                        }
                    });
                    ReceiveSongClient.this.f3724f.m7060b();
                }
            });
        }
    }

    /* renamed from: a */
    public void m7090a(final TransmittableMediaItem transmittableMediaItem) {
        if (!this.f3725g) {
            this.f3723e.execute(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.7
                @Override // java.lang.Runnable
                public void run() {
                    ReceiveSongClient.this.m7082c();
                    ReceiveSongClient.this.f3724f.m7061a(new SocketClient.InterfaceC1127b() { // from class: com.sds.android.ttpod.component.apshare.e.7.1
                        @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1127b
                        /* renamed from: a */
                        public void mo5773a(OutputStream outputStream) {
                            ReceiveSongClient.this.m7086a(outputStream, "download_song");
                            ApShareUtils.m7117a(outputStream, transmittableMediaItem);
                        }
                    });
                    ReceiveSongClient.this.f3724f.m7062a(new SocketClient.InterfaceC1126a() { // from class: com.sds.android.ttpod.component.apshare.e.7.2
                        @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1126a
                        /* renamed from: a */
                        public void mo7059a(InputStream inputStream) {
                            ReceiveSongClient.this.m7087a(inputStream, transmittableMediaItem);
                        }
                    });
                    if (ReceiveSongClient.this.f3724f != null && !ReceiveSongClient.this.f3724f.m7063a()) {
                        ReceiveSongClient.this.f3724f.m7061a(new SocketClient.InterfaceC1127b() { // from class: com.sds.android.ttpod.component.apshare.e.7.3
                            @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1127b
                            /* renamed from: a */
                            public void mo5773a(OutputStream outputStream) {
                                ReceiveSongClient.this.m7086a(outputStream, "over");
                            }
                        });
                        ReceiveSongClient.this.f3724f.m7060b();
                    }
                }
            });
        }
    }

    /* renamed from: b */
    public void m7084b() {
        if (!this.f3725g) {
            new Thread(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.e.8
                @Override // java.lang.Runnable
                public void run() {
                    if (ReceiveSongClient.this.f3724f != null && !ReceiveSongClient.this.f3724f.m7063a()) {
                        ReceiveSongClient.this.f3724f.m7061a(new SocketClient.InterfaceC1127b() { // from class: com.sds.android.ttpod.component.apshare.e.8.1
                            @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1127b
                            /* renamed from: a */
                            public void mo5773a(OutputStream outputStream) {
                                ReceiveSongClient.this.m7086a(outputStream, "download_cancel");
                            }
                        });
                        ReceiveSongClient.this.f3724f.m7060b();
                        ReceiveSongClient.this.f3724f = null;
                        LogUtils.info("ReceiveSongClient", "client socket closed");
                    }
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7086a(OutputStream outputStream, String str) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print(str + "\r\n");
        printWriter.flush();
        LogUtils.info("ReceiveSongClient", "client send message: " + str + ", tid = " + Process.myTid());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m7082c() {
        if (this.f3724f == null || this.f3724f.m7063a()) {
            this.f3724f = new SocketClient(this.f3722d, 5001);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public synchronized void m7080d() {
        this.f3725g = true;
    }
}
