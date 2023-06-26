package com.sds.android.sdk.core.p057a;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.FileUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.apache.http.client.methods.HttpGet;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.sdk.core.a.c */
/* loaded from: classes.dex */
public final class ImageLoadTask {

    /* renamed from: b */
    private static final byte[] f2277b = new byte[8192];

    /* renamed from: a */
    private Handler f2278a = new Handler() { // from class: com.sds.android.sdk.core.a.c.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 2) {
                ImageLoadTask.this.f2280d.m8785a((Bitmap) message.obj);
                ImageLoadTask.this.f2279c.mo8792a(ImageLoadTask.this.f2280d);
            }
        }
    };

    /* renamed from: c */
    private InterfaceC0567a f2279c;

    /* renamed from: d */
    private ImageRequestInfo f2280d;

    /* compiled from: ImageLoadTask.java */
    /* renamed from: com.sds.android.sdk.core.a.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0567a {
        /* renamed from: a */
        Bitmap mo8791a(String str, String str2, int i, int i2, ImageView.ScaleType scaleType);

        /* renamed from: a */
        void mo8792a(ImageRequestInfo imageRequestInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImageLoadTask(ImageRequestInfo imageRequestInfo, InterfaceC0567a interfaceC0567a) {
        if (imageRequestInfo == null || interfaceC0567a == null) {
            throw new IllegalArgumentException("imageRequestInfo and listener must be not null!");
        }
        this.f2280d = imageRequestInfo;
        this.f2279c = interfaceC0567a;
    }

    /* renamed from: a */
    public boolean m8797a() {
        Bitmap mo8791a = this.f2279c.mo8791a(ImageCache.m8800c(this.f2280d.m8784b(), this.f2280d.m8783c(), this.f2280d.m8781e(), this.f2280d.m8780f()), this.f2280d.m8782d(), this.f2280d.m8781e(), this.f2280d.m8780f(), this.f2280d.m8778h());
        if (mo8791a != null) {
            Message.obtain(this.f2278a, 2, mo8791a).sendToTarget();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m8794b() {
        String str = this.f2280d.m8782d() + ".tmp";
        try {
            if (m8795a(this.f2280d.m8784b(), str) && FileUtils.m8410c(str, this.f2280d.m8782d())) {
                m8797a();
            } else {
                FileUtils.m8404h(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x008e A[Catch: Exception -> 0x009c, TryCatch #4 {Exception -> 0x009c, blocks: (B:43:0x0085, B:45:0x008e, B:47:0x0093, B:49:0x0098), top: B:69:0x0085 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0093 A[Catch: Exception -> 0x009c, TryCatch #4 {Exception -> 0x009c, blocks: (B:43:0x0085, B:45:0x008e, B:47:0x0093, B:49:0x0098), top: B:69:0x0085 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0098 A[Catch: Exception -> 0x009c, TRY_LEAVE, TryCatch #4 {Exception -> 0x009c, blocks: (B:43:0x0085, B:45:0x008e, B:47:0x0093, B:49:0x0098), top: B:69:0x0085 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean m8795a(String str, String str2) {
        FileOutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        boolean z = false;
        BufferedOutputStream bufferedOutputStream2 = null;
        bufferedOutputStream2 = null;
        //r3 = null;
        bufferedOutputStream2 = null;
        Throwable th;
        Exception e;
        BufferedInputStream bufferedInputStream2 = null;
        HttpRequest.Response m8708a = HttpRequest.m8708a(new HttpGet(str), (HashMap<String, Object>) null, (HashMap<String, Object>) null);
        if (m8708a != null && m8708a.getStatusCode() == 200) {
            try {
                fileOutputStream = new FileOutputStream(str2);
                try {
                    bufferedInputStream = new BufferedInputStream(m8708a.getInputStream(), 8192);
                    try {
                        BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(fileOutputStream, 8192);
                        int i = 0;
                        while (true) {
                            try {
                                int read = bufferedInputStream.read(f2277b);
                                if (read < 0) {
                                    if (i >= 10) {
                                        break;
                                    }
                                    i++;
                                } else {
                                    bufferedOutputStream3.write(f2277b, 0, read);
                                }
                            } catch (Exception e1) {
                                e = e1;
                                bufferedInputStream2 = bufferedInputStream;
                                bufferedOutputStream = bufferedOutputStream3;
                                try {
                                    e.printStackTrace();
                                    try {
                                        m8708a.getInputStream().close();
                                        if (bufferedInputStream2 != null) {
                                            bufferedInputStream2.close();
                                        }
                                        if (bufferedOutputStream != null) {
                                            bufferedOutputStream.close();
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                    return z;
                                } catch (Throwable th1) {
                                    th = th1;
                                    BufferedInputStream bufferedInputStream3 = bufferedInputStream2;
                                    bufferedOutputStream2 = bufferedOutputStream;
                                    bufferedInputStream = bufferedInputStream3;
                                    try {
                                        m8708a.getInputStream().close();
                                        if (bufferedInputStream != null) {
                                            bufferedInputStream.close();
                                        }
                                        if (bufferedOutputStream2 != null) {
                                            bufferedOutputStream2.close();
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedOutputStream2 = bufferedOutputStream3;
                                m8708a.getInputStream().close();
                                if (bufferedInputStream != null) {
                                }
                                if (bufferedOutputStream2 != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                throw th;
                            }
                        }
                        z = true;
                        try {
                            m8708a.getInputStream().close();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (bufferedOutputStream3 != null) {
                                bufferedOutputStream3.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    } catch (Exception e5) {
                        e = e5;
                        bufferedOutputStream = null;
                        bufferedInputStream2 = bufferedInputStream;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Exception e6) {
                    e = e6;
                    bufferedOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    bufferedInputStream = null;
                }
            } catch (Exception e7) {
                e = e7;
                fileOutputStream = null;
                bufferedOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                bufferedInputStream = null;
            }
        }
        return z;
    }
}
