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
    private static ObjectCache instance;

    /* renamed from: b */
    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    /* renamed from: c */
    private File cacheFolderPath;

    /* renamed from: d */
    private boolean f2299d = false;

    /* renamed from: e */
    private C0572b f2300e = new C0572b();

    /* renamed from: f */
    private LruCache<String, SerialCache> serialLruCaches;

    /* renamed from: g */
    private HashMap<String, SerialCache> serialMapCaches;

    /* renamed from: a */
    public static synchronized ObjectCache getInstance(float f, String str) {
        ObjectCache objectCache;
        synchronized (ObjectCache.class) {
            if (instance == null) {
                instance = new ObjectCache(f, str);
                objectCache = instance;
            } else {
                throw new IllegalStateException("ObjectCache already existed!");
            }
        }
        return objectCache;
    }

    private ObjectCache(float f, String str) {
        this.cacheFolderPath = FileUtils.createFolder(str);
        if (this.cacheFolderPath == null) {
            this.cacheFolderPath = new File("");
        }
        if (f < 0.005f || f > 0.5f) {
            throw new IllegalArgumentException("memCacheSizePercent - percent must be between0.0050and0.5 (inclusive)");
        }
        LogUtils.info("ObjectCache", "MaxSize:" + (Math.round(((float) Runtime.getRuntime().maxMemory()) * f) / 1024));
        this.serialLruCaches = new LruCache<String, SerialCache>(Math.round(((float) Runtime.getRuntime().maxMemory()) * f) / 1024) { // from class: com.sds.android.sdk.core.a.f.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.util.LruCache
            /* renamed from: a */
            public int sizeOf(String str2, SerialCache serialCache) {
                int i = 0;
                if (serialCache.getObject() instanceof ISizeOfObject) {
                    i = ((ISizeOfObject) serialCache.getObject()).m8816a() / 1024;
                }
                if (i == 0) {
                    return 1;
                }
                return i;
            }
        };
        this.serialMapCaches = new HashMap<>();
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
            SerialCache serialCache = new SerialCache(obj, currentTimeMillis);
            this.serialLruCaches.put(str, serialCache);
            synchronized (this.f2300e) {
                this.f2300e.m8755a(str, serialCache);
                this.f2300e.notify();
            }
        } else {
            if (z) {
                LogUtils.error("ObjectCache", "Object must be implement Serializable if can be serialized");
            }
            this.serialMapCaches.put(str, new SerialCache(obj, currentTimeMillis));
        }
    }

    /* renamed from: a */
    public synchronized void m8777a() {
        this.serialLruCaches.evictAll();
    }

    /* renamed from: a */
    public synchronized boolean m8773a(String str) {
        boolean z = false;
        if (getValue(str) == null) {
            z = getCacheByKey(str) != null;
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
            value = getCacheByKey(str);
        }
        if (value != null) {
            t = (T) value;
        }
        return t;
    }

    /* renamed from: c */
    private synchronized Object getValue(String key) {
        Object obj = null;
        if (this.serialMapCaches.containsKey(key)) {
            if (this.serialMapCaches.get(key).getCurrentTimeMillis() >= System.currentTimeMillis()) {
                obj = this.serialMapCaches.get(key).getObject();
            } else {
                this.serialMapCaches.remove(key);
            }
        }
        return obj;
    }

    /* renamed from: d */
    private synchronized Object getCacheByKey(String cacheKey) {
        Object obj = null;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        Object obj2 = null;
        Exception e;
        Throwable th;
        synchronized (this) {
            if (this.serialLruCaches.get(cacheKey) != null) {
                if (this.serialLruCaches.get(cacheKey).getCurrentTimeMillis() >= System.currentTimeMillis()) {
                    obj2 = this.serialLruCaches.get(cacheKey).getObject();
                }
            } else {
                REENTRANT_LOCK.lock();
                File file = new File(getCachePathByKey(cacheKey));
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
                        REENTRANT_LOCK.unlock();
                        //throw th;
                    }
                    try {
                        try {
                            SerialCache serialCache = (SerialCache) objectInputStream.readObject();
                            if (serialCache.getCurrentTimeMillis() > System.currentTimeMillis()) {
                                this.serialLruCaches.put(cacheKey, serialCache);
                                obj = serialCache.getObject();
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
                            REENTRANT_LOCK.unlock();
                        } catch (Throwable th3) {
                            th = th3;
                            fileInputStream.close();
                            objectInputStream.close();
                            REENTRANT_LOCK.unlock();
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
                        REENTRANT_LOCK.unlock();
                        obj = null;
                        obj2 = obj;
                        return obj2;
                    }
                } else {
                    REENTRANT_LOCK.unlock();
                    obj = null;
                }
                obj2 = obj;
            }
        }
        return obj2;
    }

    /* renamed from: b */
    public synchronized void syncMapLruCache(String str) {
        if (this.serialMapCaches.remove(str) == null) {
            this.serialLruCaches.remove(str);
            exists(str);
        }
    }

    /* renamed from: e */
    private void exists(String cacheKey) {
        REENTRANT_LOCK.lock();
        FileUtils.exists(getCachePathByKey(cacheKey));
        REENTRANT_LOCK.unlock();
    }

    /* renamed from: f */
    private String getCachePathByKey(String str) {
        return this.cacheFolderPath.getAbsolutePath() + File.separator + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void saveToLocal(String cacheKey, SerialCache serialCache) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream = null;
        REENTRANT_LOCK.lock();
        Exception e;
        Throwable th;
        try {
            try {
                File file = new File(getCachePathByKey(cacheKey));
                fileOutputStream = new FileOutputStream(file);
                try {
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream);
                    try {
                        objectOutputStream2.writeObject(serialCache);
                        file.setLastModified(serialCache.getCurrentTimeMillis());
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
                        REENTRANT_LOCK.unlock();
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
            REENTRANT_LOCK.unlock();
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ObjectCache.java */
    /* renamed from: com.sds.android.sdk.core.a.f$a */
    /* loaded from: classes.dex */
    public static final class SerialCache implements Serializable {

        /* renamed from: a */
        private Object object;

        /* renamed from: b */
        private final long currentTimeMillis;

        private SerialCache(Object obj, long currentTimeMillis) {
            this.object = obj;
            this.currentTimeMillis = currentTimeMillis;
        }

        /* renamed from: a */
        public Object getObject() {
            return this.object;
        }

        /* renamed from: b */
        public long getCurrentTimeMillis() {
            return this.currentTimeMillis;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ObjectCache.java */
    /* renamed from: com.sds.android.sdk.core.a.f$b */
    /* loaded from: classes.dex */
    public final class C0572b extends Thread {

        /* renamed from: b */
        private LinkedHashMap<String, SerialCache> serialMapCaches;

        /* renamed from: c */
        private long timeMillis;

        /* renamed from: d */
        private ReentrantLock reentrantLock;

        private C0572b() {
            this.serialMapCaches = new LinkedHashMap<>();
            this.timeMillis = 0L;
            this.reentrantLock = new ReentrantLock();
        }

        /* renamed from: a */
        public void m8755a(String str, SerialCache serialCache) {
            this.reentrantLock.lock();
            this.serialMapCaches.put(str, serialCache);
            this.reentrantLock.unlock();
        }

        /* renamed from: a */
        private void m8756a() {
            this.reentrantLock.lock();
            LinkedHashMap<String, SerialCache> linkedHashMap = new LinkedHashMap<String, SerialCache>(this.serialMapCaches);
            this.reentrantLock.unlock();
            for (String cacheKey : linkedHashMap.keySet()) {
                ObjectCache.this.saveToLocal(cacheKey, (SerialCache) linkedHashMap.get(cacheKey));
                this.reentrantLock.lock();
                this.serialMapCaches.remove(cacheKey);
                this.reentrantLock.unlock();
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
            SerialCache serialCache;
            ObjectCache.REENTRANT_LOCK.lock();
            File[] listFiles = ObjectCache.this.cacheFolderPath.listFiles();
            ObjectCache.REENTRANT_LOCK.unlock();
            Exception e;
            Throwable th;
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.lastModified() <= System.currentTimeMillis()) {
                        ObjectCache.REENTRANT_LOCK.lock();
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
                                serialCache   = (SerialCache) objectInputStream.readObject();
                                z = serialCache.getCurrentTimeMillis() <= System.currentTimeMillis();
                                if(z){
                                    serialMapCaches.put(file.getName(),serialCache);
                                }
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
                                ObjectCache.REENTRANT_LOCK.unlock();
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
                                ObjectCache.REENTRANT_LOCK.unlock();
                            }
                            if (z) {
                                FileUtils.exists(file);
                            }
                            ObjectCache.REENTRANT_LOCK.unlock();
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
                    if (System.currentTimeMillis() > this.timeMillis + 1800000) {
                        if (this.timeMillis != 0) {
                            m8754b();
                        }
                        this.timeMillis = System.currentTimeMillis();
                    }
                    m8756a();
                    synchronized (this) {
                        if (this.serialMapCaches.size() <= 0) {
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
