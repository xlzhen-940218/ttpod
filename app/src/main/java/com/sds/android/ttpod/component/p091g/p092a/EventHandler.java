package com.sds.android.ttpod.component.p091g.p092a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sds.android.ttpod.framework.modules.skin.p129b.SEvent;

/* renamed from: com.sds.android.ttpod.component.g.a.f */
/* loaded from: classes.dex */
public final class EventHandler extends Handler {
    public EventHandler(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        C1218c c1218c = (C1218c) message.obj;
        if (c1218c != null) {
            c1218c.m6568a(3);
            c1218c.m6567a(c1218c.m6569a(), c1218c.m6565b());
        }
    }

    /* renamed from: a */
    public EventExecutor m6562a(SEvent sEvent) {
        return new EventExecutor(sEvent, this, 0, 0);
    }
}
