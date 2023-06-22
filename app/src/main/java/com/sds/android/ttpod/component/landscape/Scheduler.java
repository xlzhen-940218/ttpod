package com.sds.android.ttpod.component.landscape;

import com.sds.android.ttpod.component.landscape.p098b.Scene;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.landscape.i */
/* loaded from: classes.dex */
public final class Scheduler {

    /* renamed from: a */
    private static Scheduler f4599a = null;

    /* renamed from: b */
    private ArrayList<C1276b> f4600b = new ArrayList<>(5);

    /* renamed from: c */
    private ArrayList<C1276b> f4601c = new ArrayList<>(5);

    /* renamed from: d */
    private ArrayList<C1276b> f4602d = new ArrayList<>(5);

    /* compiled from: Scheduler.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.i$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1275a {
        /* renamed from: a */
        void mo6105a(float f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Scheduler.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.i$b */
    /* loaded from: classes.dex */
    public class C1276b {

        /* renamed from: b */
        private InterfaceC1275a f4604b;

        /* renamed from: c */
        private int f4605c;

        private C1276b() {
        }
    }

    /* renamed from: a */
    public static Scheduler m6112a() {
        if (f4599a == null) {
            f4599a = new Scheduler();
        }
        return f4599a;
    }

    private Scheduler() {
    }

    /* renamed from: b */
    public void m6107b() {
        Iterator<C1276b> it = this.f4600b.iterator();
        while (it.hasNext()) {
            InterfaceC1275a interfaceC1275a = it.next().f4604b;
            if (interfaceC1275a instanceof Scene) {
                ((Scene) interfaceC1275a).mo6188a();
            }
        }
        this.f4600b.clear();
        Iterator<C1276b> it2 = this.f4601c.iterator();
        while (it2.hasNext()) {
            InterfaceC1275a interfaceC1275a2 = it2.next().f4604b;
            if (interfaceC1275a2 instanceof Scene) {
                ((Scene) interfaceC1275a2).mo6188a();
            }
        }
        this.f4601c.clear();
        Iterator<C1276b> it3 = this.f4602d.iterator();
        while (it3.hasNext()) {
            InterfaceC1275a interfaceC1275a3 = it3.next().f4604b;
            if (interfaceC1275a3 instanceof Scene) {
                ((Scene) interfaceC1275a3).mo6188a();
            }
        }
        this.f4602d.clear();
        f4599a = null;
    }

    /* renamed from: b */
    private C1276b m6106b(InterfaceC1275a interfaceC1275a) {
        if (this.f4600b != null) {
            Iterator<C1276b> it = this.f4600b.iterator();
            while (it.hasNext()) {
                C1276b next = it.next();
                if (next.f4604b == interfaceC1275a) {
                    return next;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m6109a(InterfaceC1275a interfaceC1275a, int i) {
        C1276b m6106b = m6106b(interfaceC1275a);
        if (m6106b != null) {
            if (m6106b.f4605c != i) {
                synchronized (this.f4602d) {
                    this.f4602d.add(m6106b);
                }
                C1276b c1276b = new C1276b();
                c1276b.f4604b = interfaceC1275a;
                c1276b.f4605c = i;
                synchronized (this.f4601c) {
                    this.f4601c.add(c1276b);
                }
                return;
            }
            return;
        }
        C1276b c1276b2 = new C1276b();
        c1276b2.f4604b = interfaceC1275a;
        c1276b2.f4605c = i;
        synchronized (this.f4601c) {
            this.f4601c.add(c1276b2);
        }
    }

    /* renamed from: a */
    public void m6110a(InterfaceC1275a interfaceC1275a) {
        C1276b m6106b;
        if (interfaceC1275a != null && (m6106b = m6106b(interfaceC1275a)) != null) {
            synchronized (this.f4602d) {
                this.f4602d.add(m6106b);
            }
        }
    }

    /* renamed from: a */
    private void m6108a(C1276b c1276b) {
        boolean z = false;
        Iterator<C1276b> it = this.f4600b.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().f4605c < c1276b.f4605c) {
                this.f4600b.add(i, c1276b);
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            this.f4600b.add(c1276b);
        }
    }

    /* renamed from: a */
    public void m6111a(float f) {
        Iterator<C1276b> it = this.f4600b.iterator();
        while (it.hasNext()) {
            it.next().f4604b.mo6105a(f);
        }
        synchronized (this.f4602d) {
            if (this.f4602d.size() > 0) {
                Iterator<C1276b> it2 = this.f4602d.iterator();
                while (it2.hasNext()) {
                    this.f4600b.remove(it2.next());
                }
                this.f4602d.clear();
            }
        }
        synchronized (this.f4601c) {
            if (this.f4601c.size() > 0) {
                Iterator<C1276b> it3 = this.f4601c.iterator();
                while (it3.hasNext()) {
                    m6108a(it3.next());
                }
                this.f4601c.clear();
            }
        }
    }
}
