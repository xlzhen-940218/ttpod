package com.sds.android.ttpod.framework.base;

import android.app.Activity;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.base.a */
/* loaded from: classes.dex */
public class ActivityManager {

    /* renamed from: e */
    private static ActivityManager f5704e;

    /* renamed from: a */
    private List<Activity> f5705a = new ArrayList();

    /* renamed from: b */
    private Activity f5706b = null;

    /* renamed from: c */
    private Activity f5707c = null;

    /* renamed from: d */
    private Activity f5708d = null;

    /* renamed from: a */
    public static ActivityManager m4618a() {
        if (f5704e == null) {
            f5704e = new ActivityManager();
        }
        return f5704e;
    }

    /* renamed from: a */
    public void m4617a(Activity activity) {
        this.f5705a.add(activity);
    }

    /* renamed from: b */
    public void m4615b(Activity activity) {
        if (this.f5707c == activity) {
            this.f5707c = null;
        }
        if (this.f5708d == activity) {
            this.f5708d = null;
        }
        this.f5705a.remove(activity);
    }

    /* renamed from: b */
    public void m4616b() {
        for (Activity activity : this.f5705a) {
            if (SDKVersionUtils.m8367g()) {
                activity.finishAffinity();
            }
            activity.finish();
        }
        this.f5705a.clear();
    }

    /* renamed from: c */
    public void m4613c(Activity activity) {
        this.f5706b = activity;
        if (this.f5707c == activity) {
            this.f5707c = this.f5708d;
        }
    }

    /* renamed from: d */
    public void m4612d(Activity activity) {
        this.f5708d = this.f5707c;
        this.f5707c = activity;
    }

    /* renamed from: c */
    public Activity m4614c() {
        return this.f5706b;
    }

    /* renamed from: e */
    public boolean m4611e(Activity activity) {
        return activity != null && activity == m4614c();
    }
}
