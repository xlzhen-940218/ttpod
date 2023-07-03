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
    private ThreadPoolExecutor threadPoolExecutor;

    /* renamed from: b */
    private String threadPoolName;

    /* compiled from: ThreadPool.java */
    /* renamed from: com.sds.android.sdk.lib.e.b$a */
    /* loaded from: classes.dex */
    static class ThreadPoolFactory implements ThreadFactory {

        /* renamed from: a */
        private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

        /* renamed from: b */
        private final ThreadGroup threadGroup;

        /* renamed from: c */
        private final AtomicInteger f2395c = new AtomicInteger(1);

        /* renamed from: d */
        private final String name;

        ThreadPoolFactory() {
            SecurityManager securityManager = System.getSecurityManager();
            this.threadGroup = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.name = "pool-" + ATOMIC_INTEGER.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.threadGroup, runnable, this.name + this.f2395c.getAndIncrement(), 0L);
            thread.setDaemon(true);
            thread.setPriority(10);
            return thread;
        }
    }

    public ThreadPool(String threadPoolName, int maxThreadCount, long j) {
        if (maxThreadCount <= 0) {
            throw new IllegalArgumentException("maxThreadCount must be > 0 !");
        }
        this.threadPoolName = threadPoolName;
        this.threadPoolExecutor = new ThreadPoolExecutor(maxThreadCount, maxThreadCount, j, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(), new ThreadPoolFactory());
    }

    /* renamed from: a */
    public String getThreadPoolName() {
        return this.threadPoolName;
    }

    /* renamed from: a */
    public synchronized void execute(Runnable runnable) {
        checkShutdown();
        checkTaskNotNull((Object) runnable);
        this.threadPoolExecutor.execute(runnable);
    }

    /* renamed from: c */
    private void checkShutdown() {
        if (this.threadPoolExecutor.isShutdown()) {
            throw new IllegalStateException("this ThreadPool has been shutdown!!");
        }
    }

    /* renamed from: a */
    private void checkTaskNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("task must not be null !!");
        }
    }

    /* renamed from: b */
    public synchronized boolean remove(Runnable runnable) {
        this.threadPoolExecutor.purge();
        return this.threadPoolExecutor.remove(runnable);
    }

    /* renamed from: b */
    public synchronized List<Runnable> getThreadPoolList() throws InterruptedException {
        List<Runnable> shutdownNow;
        shutdownNow = this.threadPoolExecutor.shutdownNow();
        this.threadPoolExecutor.awaitTermination(10000L, TimeUnit.MILLISECONDS);
        return shutdownNow;
    }
}
