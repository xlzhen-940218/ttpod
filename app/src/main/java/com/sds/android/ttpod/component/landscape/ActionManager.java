package com.sds.android.ttpod.component.landscape;

import com.sds.android.ttpod.component.landscape.Scheduler;
import com.sds.android.ttpod.component.landscape.p097a.Action;
import com.sds.android.ttpod.component.landscape.p098b.Scene;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.landscape.a */
/* loaded from: classes.dex */
public final class ActionManager implements Scheduler.InterfaceC1275a {

    /* renamed from: a */
    private static ActionManager f4344a = null;

    /* renamed from: b */
    private ArrayList<C1252a> f4345b;

    /* renamed from: c */
    private ArrayList<C1252a> f4346c;

    /* renamed from: d */
    private ArrayList<C1252a> f4347d;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ActionManager.java */
    /* renamed from: com.sds.android.ttpod.component.landscape.a$a */
    /* loaded from: classes.dex */
    public class C1252a {

        /* renamed from: b */
        private Scene f4349b;

        /* renamed from: c */
        private Action f4350c;

        private C1252a() {
        }
    }

    /* renamed from: a */
    public static ActionManager m6371a() {
        if (f4344a == null) {
            f4344a = new ActionManager();
        }
        return f4344a;
    }

    private ActionManager() {
        Scheduler.m6112a().m6109a(this, 5);
        this.f4345b = new ArrayList<>(5);
        this.f4346c = new ArrayList<>(5);
        this.f4347d = new ArrayList<>(5);
    }

    /* renamed from: b */
    public void m6368b() {
        Scheduler.m6112a().m6110a(this);
        Iterator<C1252a> it = this.f4345b.iterator();
        while (it.hasNext()) {
            Scene scene = it.next().f4349b;
            if (scene != null) {
                scene.mo6188a();
            }
        }
        this.f4345b.clear();
        Iterator<C1252a> it2 = this.f4346c.iterator();
        while (it2.hasNext()) {
            Scene scene2 = it2.next().f4349b;
            if (scene2 != null) {
                scene2.mo6188a();
            }
        }
        this.f4346c.clear();
        Iterator<C1252a> it3 = this.f4347d.iterator();
        while (it3.hasNext()) {
            Scene scene3 = it3.next().f4349b;
            if (scene3 != null) {
                scene3.mo6188a();
            }
        }
        this.f4347d.clear();
        f4344a = null;
    }

    /* renamed from: a */
    private C1252a m6370a(Scene scene) {
        Iterator<C1252a> it = this.f4345b.iterator();
        while (it.hasNext()) {
            C1252a next = it.next();
            if (scene == next.f4349b) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m6369a(Scene scene, Action action) {
        C1252a m6370a = m6370a(scene);
        if (m6370a != null) {
            if (m6370a.f4350c != action) {
                this.f4347d.add(m6370a);
                C1252a c1252a = new C1252a();
                c1252a.f4349b = scene;
                c1252a.f4350c = action;
                this.f4346c.add(c1252a);
            }
        } else {
            C1252a c1252a2 = new C1252a();
            c1252a2.f4349b = scene;
            c1252a2.f4350c = action;
            this.f4346c.add(c1252a2);
        }
        action.mo6344a(scene);
    }

    @Override // com.sds.android.ttpod.component.landscape.Scheduler.InterfaceC1275a
    /* renamed from: a */
    public void mo6105a(float f) {
        if (this.f4347d.size() > 0) {
            Iterator<C1252a> it = this.f4347d.iterator();
            while (it.hasNext()) {
                this.f4345b.remove(it.next());
            }
            this.f4347d.clear();
        }
        if (this.f4346c.size() > 0) {
            Iterator<C1252a> it2 = this.f4346c.iterator();
            while (it2.hasNext()) {
                this.f4345b.add(it2.next());
            }
            this.f4346c.clear();
        }
        for (int size = this.f4345b.size() - 1; size >= 0; size--) {
            C1252a c1252a = this.f4345b.get(size);
            c1252a.f4350c.mo6357a(f);
            if (c1252a.f4350c.mo6358a()) {
                this.f4345b.remove(c1252a);
            }
        }
    }
}
