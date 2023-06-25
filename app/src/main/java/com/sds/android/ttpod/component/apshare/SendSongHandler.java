package com.sds.android.ttpod.component.apshare;

import android.os.Handler;
import android.os.Looper;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.FileInputStream;
import java.io.OutputStream;

/* renamed from: com.sds.android.ttpod.component.apshare.f */
/* loaded from: classes.dex */
public class SendSongHandler implements Runnable {

    /* renamed from: a */
    private TransmittableMediaItem f3752a;

    /* renamed from: b */
    private OutputStream f3753b;

    /* renamed from: c */
    private DataTransferListener f3754c;

    /* renamed from: d */
    private Handler f3755d = new Handler(Looper.getMainLooper());

    /* renamed from: e */
    private boolean f3756e = false;

    public SendSongHandler(OutputStream outputStream, DataTransferListener dataTransferListener, TransmittableMediaItem transmittableMediaItem) {
        this.f3752a = transmittableMediaItem;
        this.f3753b = outputStream;
        this.f3754c = dataTransferListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x010c  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        MediaItem m5772a = null;
        Throwable th;
        FileInputStream fileInputStream;
        Exception e;
        int i = 0;
        byte[] bArr = new byte[32768];
        try {
            fileInputStream = new FileInputStream(this.f3752a.m5772a().getLocalDataSource());
        } catch (Exception e1) {
            e = e1;
            fileInputStream = null;
        } catch (Throwable th1) {
            th = th1;
            fileInputStream = null;
            if (this.f3754c == null) {
            }
            if (this.f3754c != null) {
            }
            try {
                fileInputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            //throw th;
        }
        try {
            try {
                if (this.f3754c != null) {
                    this.f3755d.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.f.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SendSongHandler.this.f3754c.mo7107d(SendSongHandler.this.f3752a);
                        }
                    });
                }
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0 || this.f3756e) {
                        break;
                    }
                    this.f3753b.write(bArr, 0, read);
                    i += read;
                    if (this.f3754c != null) {
                        final long j = i;
                        this.f3755d.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.f.2
                            @Override // java.lang.Runnable
                            public void run() {
                                SendSongHandler.this.f3754c.mo7108a(SendSongHandler.this.f3752a, j);
                            }
                        });
                    }
                }
                this.f3753b.flush();
                LogUtils.info("SendSongHandler", "结束传送歌曲: " + m5772a.getTitle() + ", transfer ratio = " + (i / m5772a.getSize()));
                if (this.f3754c != null && i == this.f3752a.m5772a().getSize()) {
                    this.f3755d.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.f.3
                        @Override // java.lang.Runnable
                        public void run() {
                            SendSongHandler.this.f3754c.mo7106e(SendSongHandler.this.f3752a);
                        }
                    });
                } else if (this.f3754c != null) {
                    this.f3755d.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.f.4
                        @Override // java.lang.Runnable
                        public void run() {
                            SendSongHandler.this.f3754c.mo7105f(SendSongHandler.this.f3752a);
                        }
                    });
                }
                try {
                    fileInputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                if (this.f3754c == null && i == this.f3752a.m5772a().getSize()) {
                    this.f3755d.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.f.3
                        @Override // java.lang.Runnable
                        public void run() {
                            SendSongHandler.this.f3754c.mo7106e(SendSongHandler.this.f3752a);
                        }
                    });
                } else if (this.f3754c != null) {
                    this.f3755d.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.f.4
                        @Override // java.lang.Runnable
                        public void run() {
                            SendSongHandler.this.f3754c.mo7105f(SendSongHandler.this.f3752a);
                        }
                    });
                }
                fileInputStream.close();
                //throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (this.f3754c != null && i == this.f3752a.m5772a().getSize()) {
                this.f3755d.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.f.3
                    @Override // java.lang.Runnable
                    public void run() {
                        SendSongHandler.this.f3754c.mo7106e(SendSongHandler.this.f3752a);
                    }
                });
            } else if (this.f3754c != null) {
                this.f3755d.post(new Runnable() { // from class: com.sds.android.ttpod.component.apshare.f.4
                    @Override // java.lang.Runnable
                    public void run() {
                        SendSongHandler.this.f3754c.mo7105f(SendSongHandler.this.f3752a);
                    }
                });
            }
            try {
                fileInputStream.close();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public synchronized void m7075a() {
        this.f3756e = true;
    }
}
