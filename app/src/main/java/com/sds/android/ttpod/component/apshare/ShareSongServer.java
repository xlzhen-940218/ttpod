package com.sds.android.ttpod.component.apshare;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import com.sds.android.ttpod.media.mediastore.MediaItem;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.sds.android.ttpod.component.apshare.g */
/* loaded from: classes.dex */
public class ShareSongServer implements SocketServer.InterfaceC1130b {

    /* renamed from: c */
    private DataTransferListener f3764c;

    /* renamed from: d */
    private ClientConnectListener f3765d;

    /* renamed from: e */
    private List<MediaItem> f3766e;

    /* renamed from: f */
    private SocketServer f3767f;

    /* renamed from: h */
    private List<String> f3769h;

    /* renamed from: a */
    private ExecutorService f3762a = Executors.newFixedThreadPool(3);

    /* renamed from: b */
    private ExecutorService f3763b = Executors.newFixedThreadPool(3);

    /* renamed from: g */
    private Handler f3768g = new Handler(Looper.getMainLooper());

    public ShareSongServer(List<MediaItem> list, DataTransferListener dataTransferListener, ClientConnectListener clientConnectListener, int i) {
        this.f3766e = list;
        this.f3764c = dataTransferListener;
        this.f3765d = clientConnectListener;
        this.f3767f = new SocketServer(this, i);
    }

    @Override // com.sds.android.ttpod.component.apshare.SocketServer.InterfaceC1130b
    /* renamed from: a */
    public void mo5777a(final Socket socket) {
        this.f3762a.execute(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.g.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        ShareSongServer.this.m7065b(socket);
                        try {
                            if (socket != null && socket.isClosed()) {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Throwable th) {
                        try {
                            if (socket != null && socket.isClosed()) {
                                socket.close();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    LogUtils.m8383b("ShareSongServer", e3.getMessage(), e3);
                    try {
                        if (socket != null && socket.isClosed()) {
                            socket.close();
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m7065b(Socket socket) throws Exception {
         SendSongHandler sendSongHandler;
        Exception e;
        String readLine;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String readLine2 = bufferedReader.readLine();
        LogUtils.m8379d("ShareSongServer", "server receive message: " + readLine2 + ", tid = " + Process.myTid());
        if (readLine2.equals("get_share_song_list")) {
            m7068a(socket.getOutputStream());
        } else if (readLine2.equals("download_song")) {
            try {
                sendSongHandler = new SendSongHandler(socket.getOutputStream(), this.f3764c, ApShareUtils.m7118a(bufferedReader));
            } catch (Exception e2) {
                sendSongHandler = null;
                e = e2;
            }
            try {
                SendSongHandler finalSendSongHandler = sendSongHandler;
                this.f3763b.submit(new Callable<Boolean>() { // from class: com.sds.android.ttpod.component.apshare.g.2
                    @Override // java.util.concurrent.Callable
                    /* renamed from: a */
                    public Boolean call() throws Exception {
                        finalSendSongHandler.run();
                        return true;
                    }
                });
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                readLine = bufferedReader.readLine();
                LogUtils.m8379d("ShareSongServer", "msg: " + readLine);
                if (!readLine.equals("download_cancel")) {
                }
            }
            readLine = bufferedReader.readLine();
            LogUtils.m8379d("ShareSongServer", "msg: " + readLine);
            if (!readLine.equals("download_cancel")) {
                sendSongHandler.m7075a();
            }
        } else if (readLine2.equals("who_am_i") || readLine2.equals("disconnect")) {
            if (this.f3769h != null && this.f3769h.contains(socket.getInetAddress().toString().replace("/", ""))) {
                m7067a(socket.getOutputStream(), "black_list");
                return;
            }
            m7069a(bufferedReader, readLine2);
            m7067a(socket.getOutputStream(), "end");
        }
    }

    /* renamed from: a */
    private void m7068a(OutputStream outputStream) {
        try {
            if (this.f3766e != null) {
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.print(this.f3766e.size() + "\r\n");
                printWriter.flush();
                for (MediaItem mediaItem : this.f3766e) {
                    ApShareUtils.m7117a(outputStream, new TransmittableMediaItem(mediaItem));
                }
            }
        } catch (Exception e) {
            LogUtils.m8383b("ShareSongServer", e.getMessage(), e);
        }
    }

    /* renamed from: a */
    private void m7069a(BufferedReader bufferedReader, final String str) {
        try {
            final ClientModel clientModel = (ClientModel) JSONUtils.fromJson(bufferedReader.readLine(), ClientModel.class);
            if (clientModel != null && this.f3765d != null && this.f3768g != null) {
                this.f3768g.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.g.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (str.equals("who_am_i")) {
                            ShareSongServer.this.f3765d.onConnected(clientModel);
                        } else if (str.equals("disconnect")) {
                            ShareSongServer.this.f3765d.onDisconnected(clientModel);
                        }
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.m8383b("ShareSongServer", e.getMessage(), e);
        }
    }

    /* renamed from: a */
    private void m7067a(OutputStream outputStream, String str) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print(str + "\r\n");
        printWriter.flush();
    }

    /* renamed from: a */
    public void m7072a() {
        this.f3767f.m7058a();
    }

    /* renamed from: a */
    public void m7066a(List<String> list) {
        this.f3769h = list;
    }
}
