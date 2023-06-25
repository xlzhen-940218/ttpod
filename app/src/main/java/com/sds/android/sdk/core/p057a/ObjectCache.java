package com.sds.android.sdk.core.p057a;

import android.util.LruCache;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.sds.android.sdk.core.a.f */
/* loaded from: classes.dex */
public final class ObjectCache {

    /* renamed from: a */
    private static ObjectCache f2296a;

    /* renamed from: b */
    private static final ReentrantLock f2297b = new ReentrantLock();

    /* renamed from: c */
    private File f2298c;

    /* renamed from: d */
    private boolean f2299d = false;

    /* renamed from: e */
    private C0572b f2300e = new C0572b();

    /* renamed from: f */
    private LruCache<String, C0571a> f2301f;

    /* renamed from: g */
    private HashMap<String, C0571a> f2302g;

    /* renamed from: a */
    public static synchronized ObjectCache m8776a(float f, String str) {
        ObjectCache objectCache;
        synchronized (ObjectCache.class) {
            if (f2296a == null) {
                f2296a = new ObjectCache(f, str);
                objectCache = f2296a;
            } else {
                throw new IllegalStateException("ObjectCache already existed!");
            }
        }
        return objectCache;
    }

    private ObjectCache(float f, String str) {
        this.f2298c = FileUtils.m8406f(str);
        if (this.f2298c == null) {
            this.f2298c = new File("");
        }
        if (f < 0.005f || f > 0.5f) {
            throw new IllegalArgumentException("memCacheSizePercent - percent must be between0.0050and0.5 (inclusive)");
        }
        LogUtils.info("ObjectCache", "MaxSize:" + (Math.round(((float) Runtime.getRuntime().maxMemory()) * f) / 1024));
        this.f2301f = new LruCache<String, C0571a>(Math.round(((float) Runtime.getRuntime().maxMemory()) * f) / 1024) { // from class: com.sds.android.sdk.core.a.f.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.util.LruCache
            /* renamed from: a */
            public int sizeOf(String str2, C0571a c0571a) {
                int i = 0;
                if (c0571a.m8758a() instanceof ISizeOfObject) {
                    i = ((ISizeOfObject) c0571a.m8758a()).m8816a() / 1024;
                }
                if (i == 0) {
                    return 1;
                }
                return i;
            }
        };
        this.f2302g = new HashMap<>();
        this.f2300e.setPriority(10);
        this.f2300e.setDaemon(true);
        this.f2300e.start();
    }

    /* renamed from: a */
    public synchronized void m8771a(String str, Object obj) {
        m8770a(str, obj, 315360000000L);
    }

    /* renamed from: a */
    public synchronized void m8768a(String str, Object obj, boolean z) {
        m8769a(str, obj, 315360000000L, z);
    }

    /* renamed from: a */
    public synchronized void m8770a(String str, Object obj, long j) {
        m8769a(str, obj, j, true);
    }

    /* renamed from: a */
    public synchronized void m8769a(String str, Object obj, long j, boolean z) {
        if (this.f2299d) {
            throw new IllegalStateException("Cache has been closed!");
        }
        long currentTimeMillis = System.currentTimeMillis() + j;
        if (z && (obj instanceof Serializable)) {
            C0571a c0571a = new C0571a(obj, currentTimeMillis);
            this.f2301f.put(str, c0571a);
            synchronized (this.f2300e) {
                this.f2300e.m8755a(str, c0571a);
                this.f2300e.notify();
            }
        } else {
            if (z) {
                LogUtils.error("ObjectCache", "Object must be implement Serializable if can be serialized");
            }
            this.f2302g.put(str, new C0571a(obj, currentTimeMillis));
        }
    }

    /* renamed from: a */
    public synchronized void m8777a() {
        this.f2301f.evictAll();
    }

    /* renamed from: a */
    public synchronized boolean m8773a(String str) {
        boolean z = false;
        if (getValue(str) == null) {
            z = m8762d(str) != null;
        }
        return z;
    }

    /* renamed from: b */
    public synchronized <T> T m8765b(String str, T t) throws Exception {
        return (T) m8764b(str, t, true);
    }

    /* renamed from: b */
    public synchronized <T> T m8764b(String str, T t, boolean z) throws Exception {
        Object value = getValue(str);
        if (z && value == null) {
            value = m8762d(str);
        }
        if (value != null) {
            t = (T) value;
        }
        return t;
    }

    /* renamed from: c */
    private synchronized Object getValue(String key) {
        Object obj = null;
        if (this.f2302g.containsKey(key)) {
            if (this.f2302g.get(key).m8757b() >= System.currentTimeMillis()) {
                obj = this.f2302g.get(key).m8758a();
            } else {
                this.f2302g.remove(key);
            }
        }
        return obj;
    }

    /* renamed from: d */
    private synchronized Object m8762d(String str) {
        Object obj = null;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        Object obj2 = null;
        Exception e;
        Throwable th;
        synchronized (this) {
            if (this.f2301f.get(str) != null) {
                if (this.f2301f.get(str).m8757b() >= System.currentTimeMillis()) {
                    obj2 = this.f2301f.get(str).m8758a();
                }
            } else {
                f2297b.lock();
                File file = new File(m8760f(str));
                if (file.isFile()) {
                    boolean z = false;
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Exception e1) {
                        e = e1;
                        fileInputStream = null;
                        objectInputStream = null;
                    } catch (Throwable th1) {
                        th = th1;
                        fileInputStream = null;
                        objectInputStream = null;
                    }
                    try {
                        objectInputStream = new ObjectInputStream(fileInputStream);
                    } catch (Exception e2) {
                        e = e2;
                        objectInputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        objectInputStream = null;
                        try {
                            fileInputStream.close();
                            objectInputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        f2297b.unlock();
                        //throw th;
                    }
                    try {
                        try {
                            C0571a c0571a = (C0571a) objectInputStream.readObject();
                            if (c0571a.m8757b() > System.currentTimeMillis()) {
                                this.f2301f.put(str, c0571a);
                                obj = c0571a.m8758a();
                            } else {
                                z = true;
                                obj = null;
                            }
                            try {
                                fileInputStream.close();
                                objectInputStream.close();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                            if (z) {
                                file.delete();
                            }
                            f2297b.unlock();
                        } catch (Throwable th3) {
                            th = th3;
                            fileInputStream.close();
                            objectInputStream.close();
                            f2297b.unlock();
                            //throw th;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        e.printStackTrace();
                        try {
                            fileInputStream.close();
                            objectInputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                        f2297b.unlock();
                        obj = null;
                        obj2 = obj;
                        return obj2;
                    }
                } else {
                    f2297b.unlock();
                    obj = null;
                }
                obj2 = obj;
            }
        }
        return obj2;
    }

    /* renamed from: b */
    public synchronized void m8766b(String str) {
        if (this.f2302g.remove(str) == null) {
            this.f2301f.remove(str);
            m8761e(str);
        }
    }

    /* renamed from: e */
    private void m8761e(String str) {
        f2297b.lock();
        FileUtils.m8404h(m8760f(str));
        f2297b.unlock();
    }

    /* renamed from: f */
    private String m8760f(String str) {
        return this.f2298c.getAbsolutePath() + File.separator + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m8772a(String str, C0571a c0571a) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream = null;
        f2297b.lock();
        Exception e;
        Throwable th;
        try {
            try {
                File file = new File(m8760f(str));
                fileOutputStream = new FileOutputStream(file);
                try {
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream);
                    try {
                        objectOutputStream2.writeObject(c0571a);
                        file.setLastModified(c0571a.m8757b());
                        try {
                            fileOutputStream.close();
                            objectOutputStream2.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        objectOutputStream = objectOutputStream2;
                        e.printStackTrace();
                        try {
                            fileOutputStream.close();
                            objectOutputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        f2297b.unlock();
                    } catch (Throwable th1) {
                        th = th1;
                        objectOutputStream = objectOutputStream2;
                        try {
                            fileOutputStream.close();
                            objectOutputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                }
            } catch (Exception e6) {
                e = e6;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
            f2297b.unlock();
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ObjectCache.java */
    /* renamed from: com.sds.android.sdk.core.a.f$a */
    /* loaded from: classes.dex */
    public static final class C0571a implements Serializable {

        /* renamed from: a */
        private Object f2304a;

        /* renamed from: b */
        private long f2305b;

        private C0571a(Object obj, long j) {
            this.f2304a = obj;
            this.f2305b = j;
        }

        /* renamed from: a */
        public Object m8758a() {
            return this.f2304a;
        }

        /* renamed from: b */
        public long m8757b() {
            return this.f2305b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ObjectCache.java */
    /* renamed from: com.sds.android.sdk.core.a.f$b */
    /* loaded from: classes.dex */
    public final class C0572b extends Thread {

        /* renamed from: b */
        private LinkedHashMap<String, C0571a> f2307b;

        /* renamed from: c */
        private long f2308c;

        /* renamed from: d */
        private ReentrantLock f2309d;

        private C0572b() {
            this.f2307b = new LinkedHashMap<>();
            this.f2308c = 0L;
            this.f2309d = new ReentrantLock();
        }

        /* renamed from: a */
        public void m8755a(String str, C0571a c0571a) {
            this.f2309d.lock();
            this.f2307b.put(str, c0571a);
            this.f2309d.unlock();
        }

        /* renamed from: a */
        private void m8756a() {
            this.f2309d.lock();
            LinkedHashMap<String,C0571a> linkedHashMap = new LinkedHashMap<String, C0571a>(this.f2307b);
            this.f2309d.unlock();
            for (String str : linkedHashMap.keySet()) {
                ObjectCache.this.m8772a(str, (C0571a) linkedHashMap.get(str));
                this.f2309d.lock();
                this.f2307b.remove(str);
                this.f2309d.unlock();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void m8754b() {
            FileInputStream fileInputStream = null;
            ObjectInputStream objectInputStream = null;
            FileInputStream fileInputStream2;
            ObjectInputStream objectInputStream2;
            boolean z;
            ObjectCache.f2297b.lock();
            File[] listFiles = ObjectCache.this.f2298c.listFiles();
            ObjectCache.f2297b.unlock();
            Exception e;
            Throwable th;
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.lastModified() <= System.currentTimeMillis()) {
                        ObjectCache.f2297b.lock();
                        try {
                            fileInputStream = new FileInputStream(file);
                            try {
                                objectInputStream = new ObjectInputStream(fileInputStream);
                            } catch (ClassCastException e1) {
                                fileInputStream2 = fileInputStream;
                                objectInputStream2 = null;
                            } catch (Exception e2) {
                                e = e2;
                                objectInputStream = null;
                            } catch (Throwable th1) {
                                th = th1;
                                objectInputStream = null;
                            }
                        } catch (ClassCastException e3) {
                            fileInputStream2 = null;
                            objectInputStream2 = null;
                        } catch (Exception e4) {
                            e = e4;
                            fileInputStream = null;
                            objectInputStream = null;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream = null;
                            objectInputStream = null;
                        }
                        try {
                            try {
                                z = ((C0571a) objectInputStream.readObject()).m8757b() <= System.currentTimeMillis();
                                try {
                                    fileInputStream.close();
                                    objectInputStream.close();
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            } catch (ClassCastException e6) {
                                fileInputStream2 = fileInputStream;
                                objectInputStream2 = objectInputStream;
                                try {
                                    fileInputStream2.close();
                                    objectInputStream2.close();
                                    z = true;
                                } catch (Exception e7) {
                                    e7.printStackTrace();
                                    z = true;
                                }
                                if (z) {
                                }
                                ObjectCache.f2297b.unlock();
                            } catch (Exception e8) {
                                e = e8;
                                e.printStackTrace();
                                try {
                                    fileInputStream.close();
                                    objectInputStream.close();
                                    z = false;
                                } catch (Exception e9) {
                                    e9.printStackTrace();
                                    z = false;
                                }
                                if (z) {
                                }
                                ObjectCache.f2297b.unlock();
                            }
                            if (z) {
                                FileUtils.m8412c(file);
                            }
                            ObjectCache.f2297b.unlock();
                        } catch (Throwable th3) {
                            th = th3;
                            try {
                                fileInputStream.close();
                                objectInputStream.close();
                            } catch (Exception e10) {
                                e10.printStackTrace();
                            }
                            //throw th;
                        }
                    }
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!isInterrupted()) {
                try {
                    if (System.currentTimeMillis() > this.f2308c + 1800000) {
                        if (this.f2308c != 0) {
                            m8754b();
                        }
                        this.f2308c = System.currentTimeMillis();
                    }
                    m8756a();
                    synchronized (this) {
                        if (this.f2307b.size() <= 0) {
                            wait();
                        }
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}
