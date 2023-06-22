package com.sds.android.ttpod.cmmusic.p081e;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/* renamed from: com.sds.android.ttpod.cmmusic.e.a */
/* loaded from: classes.dex */
public class AsyncImageLoader {

    /* renamed from: a */
    private final int f3484a = 15000;

    /* renamed from: b */
    private HashMap<String, SoftReference<Drawable>> f3485b = new HashMap<>();

    /* compiled from: AsyncImageLoader.java */
    /* renamed from: com.sds.android.ttpod.cmmusic.e.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1029a {
        /* renamed from: a */
        void mo7300a(Drawable drawable, String str);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.sds.android.ttpod.cmmusic.e.a$2] */
    /* renamed from: a */
    public Drawable m7301a(final String str, final InterfaceC1029a interfaceC1029a) {
        Drawable drawable;
        if (this.f3485b.containsKey(str) && (drawable = this.f3485b.get(str).get()) != null) {
            interfaceC1029a.mo7300a(drawable, str);
            return drawable;
        }
        final Handler handler = new Handler() { // from class: com.sds.android.ttpod.cmmusic.e.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                interfaceC1029a.mo7300a((Drawable) message.obj, str);
            }
        };
        new Thread() { // from class: com.sds.android.ttpod.cmmusic.e.a.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Drawable m7302a = AsyncImageLoader.this.m7302a(str);
                AsyncImageLoader.this.f3485b.put(str, new SoftReference(m7302a));
                handler.sendMessage(handler.obtainMessage(0, m7302a));
            }
        }.start();
        return null;
    }

    /* renamed from: a */
    public Drawable m7302a(String str) {
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            defaultHttpClient.getParams().setParameter("http.connection.timeout", 15000);
            HttpResponse execute = defaultHttpClient.execute(new HttpGet(str));
            if (execute.getStatusLine().getStatusCode() == 200) {
                return Drawable.createFromStream(execute.getEntity().getContent(), "src");
            }
            return null;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
