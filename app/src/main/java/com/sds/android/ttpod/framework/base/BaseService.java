package com.sds.android.ttpod.framework.base;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import com.sds.android.sdk.lib.util.ReflectUtils;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class BaseService extends Service {

    /* renamed from: a */
    private NotificationManager f5700a;

    /* renamed from: b */
    private Method f5701b;

    /* renamed from: c */
    private Method f5702c;

    /* renamed from: d */
    private Method f5703d;

    /* renamed from: a */
    private void m4621a() {
        this.f5700a = (NotificationManager) getSystemService("notification");
        try {
            this.f5702c = ReflectUtils.m8375a(getClass(), "startForeground", Integer.TYPE, Notification.class);
            this.f5703d = ReflectUtils.m8375a(getClass(), "stopForeground", Boolean.TYPE);
        } catch (NoSuchMethodException e) {
            this.f5702c = null;
            this.f5703d = null;
            try {
                this.f5701b = ReflectUtils.m8375a(getClass(), "setForeground", Boolean.TYPE);
            } catch (NoSuchMethodException e2) {
                throw new IllegalStateException("OS doesn't have Service.startForeground OR Service.setForeground!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m4619a(int i, Notification notification) {
        notification.flags = 2;
        try {
            if (this.f5702c != null) {
                this.f5702c.invoke(this, Integer.valueOf(i), notification);
            } else {
                this.f5701b.invoke(this, Boolean.TRUE);
                this.f5700a.notify(i, notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m4620a(int i) {
        try {
            if (this.f5703d != null) {
                this.f5703d.invoke(this, Boolean.TRUE);
            } else {
                this.f5700a.cancel(i);
                this.f5701b.invoke(this, Boolean.FALSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        m4621a();
    }
}
