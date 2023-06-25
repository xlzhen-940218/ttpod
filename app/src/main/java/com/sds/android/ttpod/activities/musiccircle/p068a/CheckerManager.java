package com.sds.android.ttpod.activities.musiccircle.p068a;

import android.os.Handler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.a.c */
/* loaded from: classes.dex */
public final class CheckerManager implements Checker.InterfaceC0796a, Runnable {

    /* renamed from: a */
    private static CheckerManager f2766a;

    /* renamed from: c */
    private static final Object[] f2767c = new Object[0];

    /* renamed from: b */
    private Handler f2768b = new Handler();

    /* renamed from: d */
    private ArrayList<Checker> f2769d = new ArrayList<>();

    /* renamed from: e */
    private ArrayList<CheckObserver> f2770e = new ArrayList<>();

    /* renamed from: f */
    private int f2771f = -1;

    /* renamed from: g */
    private long f2772g;

    /* renamed from: a */
    public static CheckerManager m7949a() {
        if (f2766a == null) {
            synchronized (f2767c) {
                if (f2766a == null) {
                    f2766a = new CheckerManager();
                }
            }
        }
        return f2766a;
    }

    private CheckerManager() {
        m7937f();
        m7939d();
    }

    /* renamed from: b */
    public void m7943b() {
        m7938e();
        LogUtils.debug("CheckerManager", "pauseCheckMessage");
    }

    /* renamed from: c */
    public void m7941c() {
        m7938e();
        long max = Math.max(1000L, (300000 - TimeUnit.NANOSECONDS.toMillis(System.nanoTime())) + this.f2772g);
        LogUtils.debug("CheckerManager", "resumeCheckMessage " + max);
        m7948a(max);
    }

    /* renamed from: f */
    private void m7937f() {
        this.f2769d.add(new MessageChecker(this));
        this.f2769d.add(new MusicCircleChecker(this));
    }

    /* renamed from: d */
    public void m7939d() {
        m7938e();
        m7948a(1000L);
    }

    /* renamed from: e */
    public void m7938e() {
        this.f2768b.removeCallbacks(this);
    }

    /* renamed from: a */
    private void m7948a(long j) {
        this.f2768b.postDelayed(this, j);
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.p068a.Checker.InterfaceC0796a
    /* renamed from: a */
    public void mo7945a(Checker checker, boolean z, BaseResult baseResult) {
        if (z) {
            m7946a(checker, baseResult);
        }
        run();
    }

    /* renamed from: a */
    public void m7944a(Class cls) {
        Iterator<Checker> it = this.f2769d.iterator();
        while (it.hasNext()) {
            Checker next = it.next();
            if (next.getClass() == cls) {
                next.m7951c();
                return;
            }
        }
    }

    /* renamed from: a */
    public void m7947a(CheckObserver checkObserver) {
        if (!this.f2770e.contains(checkObserver)) {
            this.f2770e.add(checkObserver);
            m7940c(checkObserver);
        }
    }

    /* renamed from: b */
    public void m7942b(CheckObserver checkObserver) {
        this.f2770e.remove(checkObserver);
    }

    /* renamed from: a */
    private void m7946a(Checker checker, BaseResult baseResult) {
        Iterator<CheckObserver> it = this.f2770e.iterator();
        while (it.hasNext()) {
            it.next().onCheckFinished(checker, baseResult);
        }
    }

    /* renamed from: c */
    private void m7940c(CheckObserver checkObserver) {
        Iterator<Checker> it = this.f2769d.iterator();
        while (it.hasNext()) {
            Checker next = it.next();
            if (next.m7953a()) {
                checkObserver.onCheckFinished(next, next.m7952b());
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (Preferences.m2954aq() != null) {
            int i = this.f2771f + 1;
            this.f2771f = i;
            if (i < this.f2769d.size()) {
                Checker checker = this.f2769d.get(this.f2771f);
                LogUtils.debug("CheckerManager", "will check %d task %s", Integer.valueOf(this.f2771f), checker.getClass().getSimpleName());
                checker.m7950d();
                this.f2772g = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            }
        }
        this.f2771f = -1;
        LogUtils.debug("CheckerManager", "check complete, wait for next check.");
        m7948a(300000L);
        this.f2772g = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }
}
