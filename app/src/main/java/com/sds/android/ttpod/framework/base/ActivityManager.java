package com.sds.android.ttpod.framework.base;

import android.app.Activity;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.base.a */
/* loaded from: classes.dex */
public class ActivityManager {

    /* renamed from: e */
    private static ActivityManager instance;

    /* renamed from: a */
    private List<Activity> activities = new ArrayList();

    /* renamed from: b */
    private Activity activeActivity = null;

    /* renamed from: c */
    private Activity pauseActivity = null;

    /* renamed from: d */
    private Activity f5708d = null;

    /* renamed from: a */
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /* renamed from: a */
    public void m4617a(Activity activity) {
        this.activities.add(activity);
    }

    /* renamed from: b */
    public void removeActivity(Activity activity) {
        if (this.pauseActivity == activity) {
            this.pauseActivity = null;
        }
        if (this.f5708d == activity) {
            this.f5708d = null;
        }
        this.activities.remove(activity);
    }

    /* renamed from: b */
    public void stopAllActivity() {
        for (Activity activity : this.activities) {
            if (SDKVersionUtils.sdkThan16()) {
                activity.finishAffinity();
            }
            activity.finish();
        }
        this.activities.clear();
    }

    /* renamed from: c */
    public void resumeActivity(Activity activity) {
        this.activeActivity = activity;
        if (this.pauseActivity == activity) {
            this.pauseActivity = this.f5708d;
        }
    }

    /* renamed from: d */
    public void pauseActivity(Activity activity) {
        this.f5708d = this.pauseActivity;
        this.pauseActivity = activity;
    }

    /* renamed from: c */
    public Activity getActiveActivity() {
        return this.activeActivity;
    }

    /* renamed from: e */
    public boolean isActiveActivity(Activity activity) {
        return activity != null && activity == getActiveActivity();
    }
}
