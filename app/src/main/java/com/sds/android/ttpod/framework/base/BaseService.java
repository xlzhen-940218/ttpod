package com.sds.android.ttpod.framework.base;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.pm.ServiceInfo;
import android.os.Build;

/* loaded from: classes.dex */
public abstract class BaseService extends Service {

    private NotificationManager f5700a;

    private void m4621a() {
        this.f5700a = (NotificationManager) getSystemService("notification");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m4619a(int i, Notification notification) {
        if (notification == null) {
            return;
        }
        notification.flags |= 2;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startForeground(i, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK);
            } else {
                startForeground(i, notification);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            try {
                if (this.f5700a != null) {
                    this.f5700a.notify(i, notification);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m4620a(int i) {
        try {
            stopForeground(true);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        m4621a();
    }
}
