package com.sds.android.ttpod.component.apshare;

import com.sds.android.sdk.lib.util.LogUtils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* renamed from: com.sds.android.ttpod.component.apshare.i */
/* loaded from: classes.dex */
public class SocketServer {

    /* renamed from: a */
    private ServerSocket f3778a;

    /* renamed from: b */
    private InterfaceC1130b f3779b;

    /* renamed from: c */
    private C1129a f3780c;

    /* compiled from: SocketServer.java */
    /* renamed from: com.sds.android.ttpod.component.apshare.i$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1130b {
        /* renamed from: a */
        void mo5777a(Socket socket);
    }

    public SocketServer(InterfaceC1130b interfaceC1130b, int i) {
        this.f3779b = interfaceC1130b;
        try {
            this.f3778a = new ServerSocket(i, 100);
            this.f3778a.setSoTimeout(360000);
            LogUtils.m8388a("SocketServer", "start server at port: " + i);
        } catch (IOException e) {
            LogUtils.m8383b("SocketServer", e.getMessage(), e);
            if (this.f3778a != null && !this.f3778a.isClosed()) {
                try {
                    this.f3778a.close();
                } catch (Exception e2) {
                    LogUtils.m8383b("SocketServer", e2.getMessage(), e);
                }
            }
        }
        this.f3780c = new C1129a();
        this.f3780c.setDaemon(true);
        this.f3780c.setPriority(10);
        this.f3780c.start();
    }

    /* renamed from: a */
    public void m7058a() {
        try {
            this.f3780c.m7055a();
            if (this.f3778a != null) {
                this.f3778a.close();
                LogUtils.m8388a("SocketServer", "server socket is closed");
            }
        } catch (IOException e) {
            LogUtils.m8383b("SocketServer", e.getMessage(), e);
        }
    }

    /* compiled from: SocketServer.java */
    /* renamed from: com.sds.android.ttpod.component.apshare.i$a */
    /* loaded from: classes.dex */
    private class C1129a extends Thread {

        /* renamed from: b */
        private boolean f3782b;

        private C1129a() {
            this.f3782b = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName(getClass().getSimpleName());
            while (!this.f3782b) {
                Socket socket = null;
                try {
                    if (SocketServer.this.f3778a != null && !SocketServer.this.f3778a.isClosed()) {
                        LogUtils.m8379d("SocketServer", "wait another client to connect...");
                        socket = SocketServer.this.f3778a.accept();
                        LogUtils.m8379d("SocketServer", "accept a new connection, addr:" + socket.getInetAddress());
                    }
                    if (socket != null && SocketServer.this.f3779b != null) {
                        socket.setKeepAlive(true);
                        SocketServer.this.f3779b.mo5777a(socket);
                    }
                } catch (IOException e) {
                    LogUtils.m8383b("SocketServer", e.getMessage(), e);
                }
            }
            LogUtils.m8388a("SocketServer", getClass().getSimpleName() + " is shutdown");
        }

        /* renamed from: a */
        public void m7055a() {
            this.f3782b = true;
            super.interrupt();
        }
    }
}
