package com.sds.android.ttpod.component.apshare;

import com.sds.android.sdk.lib.util.LogUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* renamed from: com.sds.android.ttpod.component.apshare.h */
/* loaded from: classes.dex */
public class SocketClient {

    /* renamed from: a */
    private Socket f3777a;

    /* compiled from: SocketClient.java */
    /* renamed from: com.sds.android.ttpod.component.apshare.h$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1126a {
        /* renamed from: a */
        void mo7059a(InputStream inputStream);
    }

    /* compiled from: SocketClient.java */
    /* renamed from: com.sds.android.ttpod.component.apshare.h$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1127b {
        /* renamed from: a */
        void mo5773a(OutputStream outputStream);
    }

    public SocketClient(String str, int i) {
        try {
            this.f3777a = new Socket(str, i);
            this.f3777a.setSoTimeout(360000);
            this.f3777a.setSendBufferSize(32768);
            this.f3777a.setReceiveBufferSize(32768);
            LogUtils.m8388a("SocketClient", "connected to server: " + str);
        } catch (IOException e) {
            LogUtils.m8383b("SocketClient", e.getMessage(), e);
        }
    }

    /* renamed from: a */
    public boolean m7063a() {
        if (this.f3777a != null) {
            return this.f3777a.isClosed();
        }
        return true;
    }

    /* renamed from: b */
    public void m7060b() {
        try {
            if (this.f3777a != null && !this.f3777a.isClosed()) {
                this.f3777a.close();
                LogUtils.m8388a("SocketClient", "client socket is closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m7061a(InterfaceC1127b interfaceC1127b) {
        if (this.f3777a != null && this.f3777a.isConnected() && interfaceC1127b != null) {
            try {
                interfaceC1127b.mo5773a(this.f3777a.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void m7062a(InterfaceC1126a interfaceC1126a) {
        if (this.f3777a != null && this.f3777a.isConnected() && interfaceC1126a != null) {
            try {
                interfaceC1126a.mo7059a(this.f3777a.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
