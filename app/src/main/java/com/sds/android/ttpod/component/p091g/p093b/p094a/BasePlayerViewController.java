package com.sds.android.ttpod.component.p091g.p093b.p094a;

import android.content.Context;
import android.os.Process;
import android.view.View;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.view.LineVisualization;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.player.PlayStatus;

/* renamed from: com.sds.android.ttpod.component.g.b.a.a */
/* loaded from: classes.dex */
public abstract class BasePlayerViewController extends ViewEventController {

    /* renamed from: ab */
    private static final int[] f4178ab = new int[0];

    /* renamed from: Y */
    private boolean f4179Y;

    /* renamed from: Z */
    private boolean f4180Z;

    /* renamed from: a */
    private final Object f4181a;

    /* renamed from: aa */
    private boolean f4182aa;

    /* renamed from: ac */
    private int[] f4183ac;

    /* renamed from: b */
    private C1220a f4184b;

    /* renamed from: c */
    protected LineVisualization f4185c;

    /* renamed from: a */
    public abstract View mo6461a();

    public BasePlayerViewController(Context context, String str) {
        super(context, str);
        this.f4181a = new Object();
        this.f4179Y = false;
        this.f4180Z = false;
        this.f4182aa = false;
    }

    /* renamed from: x */
    public void m6551x() {
        this.f4182aa = true;
    }

    /* renamed from: y */
    public void m6550y() {
        this.f4182aa = false;
        m6558B();
    }

    /* renamed from: z */
    public boolean m6549z() {
        return this.f4180Z;
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b */
    public void mo6441b() {
        synchronized (this.f4181a) {
            if (this.f4184b != null) {
                this.f4184b.interrupt();
                this.f4184b = null;
            }
        }
        this.f4229l = null;
        this.f4230m = null;
        this.f4234q = null;
        this.f4199K = null;
        super.mo6441b();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: r */
    public void mo6404r() {
        if (!m6549z()) {
            this.f4180Z = true;
            super.mo6404r();
            m6559A();
        }
    }

    /* renamed from: A */
    protected void m6559A() {
        if (this.f4185c == null) {
            this.f4185c = this.f4199K;
        }
        if (this.f4185c != null) {
            if (this.f4184b == null || this.f4184b.isInterrupted()) {
                this.f4185c.setOnActiveListener(new LineVisualization.InterfaceC2008a() { // from class: com.sds.android.ttpod.component.g.b.a.a.1
                    @Override // com.sds.android.ttpod.framework.modules.skin.view.LineVisualization.InterfaceC2008a
                    /* renamed from: a */
                    public void mo3350a() {
                        BasePlayerViewController.this.m6558B();
                    }

                    @Override // com.sds.android.ttpod.framework.modules.skin.view.LineVisualization.InterfaceC2008a
                    /* renamed from: b */
                    public void mo3349b() {
                    }
                });
                this.f4184b = new C1220a();
                this.f4184b.start();
                return;
            }
            m6558B();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: B */
    public final void m6558B() {
        if (m6549z() && m6557C()) {
            synchronized (this.f4181a) {
                this.f4181a.notifyAll();
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: q */
    public void mo6410q() {
        if (m6549z()) {
            this.f4180Z = false;
            super.mo6410q();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: C */
    public boolean m6557C() {
        return !this.f4182aa && m6526d(this.f4185c) && this.f4185c.isEnabled();
    }

    /* renamed from: c */
    public void m6552c(boolean z) {
        this.f4179Y = z;
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController
    /* renamed from: e */
    public int mo6467e() {
        View mo6461a = mo6461a();
        if (mo6461a == null) {
            return 0;
        }
        return mo6461a.getWidth();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController
    /* renamed from: f */
    public int mo6466f() {
        View mo6461a = mo6461a();
        if (mo6461a == null) {
            return 0;
        }
        return mo6461a.getHeight();
    }

    /* renamed from: c */
    public void mo6432c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BasePlayerViewController.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.a.a$a */
    /* loaded from: classes.dex */
    public final class C1220a extends Thread {

        /* renamed from: b */
        private boolean f4188b;

        public C1220a() {
            super("visualization thread");
            this.f4188b = false;
            Process.setThreadPriority(0);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!this.f4188b) {
                long currentTimeMillis = System.currentTimeMillis();
                if (BasePlayerViewController.this.m6549z() && BasePlayerViewController.this.m6557C()) {
                    int numberOfLine = BasePlayerViewController.this.f4185c.getNumberOfLine();
                    if (BasePlayerViewController.this.f4183ac == null || BasePlayerViewController.this.f4183ac.length != numberOfLine) {
                        BasePlayerViewController.this.f4183ac = new int[numberOfLine];
                    }
                    if (BasePlayerViewController.this.f4209U != PlayStatus.STATUS_PLAYING || !SupportFactory.m2397a(BaseApplication.getApplication()).m2486a(BasePlayerViewController.this.f4183ac, numberOfLine)) {
                        BasePlayerViewController.this.f4185c.m3354a(BasePlayerViewController.f4178ab);
                    } else {
                        BasePlayerViewController.this.f4185c.m3354a(BasePlayerViewController.this.f4183ac);
                    }
                    try {
                        Thread.sleep(Math.max(50 - (System.currentTimeMillis() - currentTimeMillis), 20L));
                    } catch (InterruptedException e) {
                        return;
                    }
                } else {
                    try {
                        Thread.sleep(50L);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    synchronized (BasePlayerViewController.this.f4181a) {
                        if (!BasePlayerViewController.this.m6557C()) {
                            try {
                                BasePlayerViewController.this.f4181a.wait();
                            } catch (InterruptedException e3) {
                                return;
                            }
                        }
                    }
                }
            }
        }

        @Override // java.lang.Thread
        public void interrupt() {
            this.f4188b = true;
            super.interrupt();
        }

        @Override // java.lang.Thread
        public boolean isInterrupted() {
            return this.f4188b || super.isInterrupted();
        }
    }
}
