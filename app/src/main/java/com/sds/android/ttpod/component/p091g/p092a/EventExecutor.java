package com.sds.android.ttpod.component.p091g.p092a;

import com.sds.android.ttpod.framework.modules.skin.p129b.SEvent;
import com.sds.android.ttpod.framework.modules.skin.p129b.SMotion;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.g.a.e */
/* loaded from: classes.dex */
public class EventExecutor {

    /* renamed from: a */
    private ArrayList<C1218c> f4173a = new ArrayList<>();

    /* renamed from: b */
    private EventCallback f4174b;

    /* renamed from: c */
    private int f4175c;

    /* renamed from: d */
    private int f4176d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventExecutor(SEvent sEvent, EventHandler eventHandler, int i, int i2) {
        SMotion[] m3802c = sEvent.m3802c();
        this.f4175c = i;
        this.f4176d = i2;
        if (m3802c != null) {
            for (SMotion sMotion : m3802c) {
                this.f4173a.add(new C1218c(sMotion, eventHandler));
            }
        }
    }

    /* renamed from: a */
    public void m6564a(int i, int i2) {
        Iterator<C1218c> it = this.f4173a.iterator();
        while (it.hasNext()) {
            it.next().m6567a(i, i2);
        }
    }

    /* renamed from: a */
    public void m6563a(EventCallback eventCallback) {
        this.f4174b = eventCallback;
        Iterator<C1218c> it = this.f4173a.iterator();
        while (it.hasNext()) {
            it.next().m6566a(eventCallback);
        }
    }
}
