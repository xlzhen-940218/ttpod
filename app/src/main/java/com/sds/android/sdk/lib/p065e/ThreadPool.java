package com.sds.android.sdk.lib.p065e;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.sds.android.sdk.lib.e.b */
/* loaded from: classes.dex */
public final class ThreadPool {

    /* renamed from: a */
    private ThreadPoolExecutor f2391a;

    /* renamed from: b */
    private String f2392b;

    /* compiled from: ThreadPool.java */
    /* renamed from: com.sds.android.sdk.lib.e.b$a */
    /* loaded from: classes.dex */
    static class ThreadFactoryC0596a implements ThreadFactory {

        /* renamed from: a */
        private static final AtomicInteger f2393a = new AtomicInteger(1);

        /* renamed from: b */
        private final ThreadGroup f2394b;

        /* renamed from: c */
        private final AtomicInteger f2395c = new AtomicInteger(1);

        /* renamed from: d */
        private final String f2396d;

        ThreadFactoryC0596a() {
            SecurityManager securityManager = System.getSecurityManager();
            this.f2394b = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.f2396d = "pool-" + f2393a.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f2394b, runnable, this.f2396d + this.f2395c.getAndIncrement(), 0L);
            thread.setDaemon(true);
            thread.setPriority(10);
            return thread;
        }
    }

    public ThreadPool(String str, int i, long j) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxThreadCount must be > 0 !");
        }
        this.f2392b = str;
        this.f2391a = new ThreadPoolExecutor(i, i, j, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC0596a());
    }

    /* renamed from: a */
    public String m8578a() {
        return this.f2392b;
    }

    /* renamed from: a */
    public synchronized void m8576a(Runnable runnable) {
        m8573c();
        m8577a((Object) runnable);
        this.f2391a.execute(runnable);
    }

    /* renamed from: c */
    private void m8573c() {
        if (this.f2391a.isShutdown()) {
            throw new IllegalStateException("this ThreadPool has been shutdown!!");
        }
    }

    /* renamed from: a */
    private void m8577a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("task must not be null !!");
        }
    }

    /* renamed from: b */
    public synchronized boolean m8574b(Runnable runnable) {
        this.f2391a.purge();
        return this.f2391a.remove(runnable);
    }

    /* renamed from: b */
    public synchronized List<Runnable> m8575b() throws InterruptedException {
        List<Runnable> shutdownNow;
        shutdownNow = this.f2391a.shutdownNow();
        this.f2391a.awaitTermination(10000L, TimeUnit.MILLISECONDS);
        return shutdownNow;
    }
}
