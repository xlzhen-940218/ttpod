package com.sds.android.ttpod.framework.modules.p123e;

import android.content.Intent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.e.a */
/* loaded from: classes.dex */
public class LockScreenModule extends BaseModule {

    /* renamed from: a */
    private static final String f6113a = LockScreenModule.class.getSimpleName();

    /* renamed from: b */
    private boolean f6114b = false;

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID mo3239id() {
        return ModuleID.LOCK_SCREEN;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.START_LOCK_SCREEN, ReflectUtils.m8375a(cls, "startLockScreen", new Class[0]));
        map.put(CommandID.STOP_LOCK_SCREEN, ReflectUtils.m8375a(cls, "stopLockScreen", new Class[0]));
        map.put(CommandID.RECEIVED_LOCK_SCREEN_ACTION, ReflectUtils.m8375a(cls, "onReceive", Intent.class));
    }

    public void startLockScreen() {
        if (Preferences.m2871i(isAllowDefaultLockScreen())) {
            if (PlayStatus.STATUS_PLAYING == SupportFactory.m2397a(BaseApplication.getApplication()).m2463m()) {
                sContext.startActivity(new Intent(Action.LOCK_SCREEN_ACTIVITY_ENTRY).setFlags(268697600));
                this.f6114b = true;
            }
        }
    }

    public void stopLockScreen() {
        if (this.f6114b) {
            stopSystemLock();
            this.f6114b = false;
            LogUtils.m8379d(f6113a, "stopLockScreen looklockscreen statistic");
            LocalStatistic.m5106b();
        }
    }

    public void stopSystemLock() {
        try {
            if (!SDKVersionUtils.m8369e()) {
                SystemLockManager.m4079a();
                SystemLockManager.m4078a(new SystemLockManager.InterfaceC1899a() { // from class: com.sds.android.ttpod.framework.modules.e.a.1
                    @Override // com.sds.android.ttpod.framework.modules.p123e.SystemLockManager.InterfaceC1899a
                    /* renamed from: a */
                    public void mo4071a() {
                        LogUtils.m8381c(LockScreenModule.f6113a, "onReceive unlock success!");
                    }
                });
            }
        } catch (Throwable th) {
            LogUtils.m8381c(f6113a, "onReceive Throwable = " + th.toString());
        }
    }

    public static boolean isAllowDefaultLockScreen() {
        String m8501j = EnvironmentUtils.C0602a.m8501j();
        if (!StringUtils.m8346a(m8501j)) {
            String[] split = m8501j.split("_");
            String m8512b = EnvironmentUtils.C0602a.m8512b();
            if (split != null && !StringUtils.m8346a(m8512b)) {
                for (String str : split) {
                    if (str.equals(m8512b)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void onReceive(Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.SCREEN_OFF".equals(action)) {
            startLockScreen();
        } else if ("android.intent.action.SCREEN_ON".equals(action) && this.f6114b) {
            stopLockScreen();
            this.f6114b = false;
        } else if (Action.START_ENTRY.equals(action) || Action.CALL_STATE_RINGING.equals(action) || Action.STOP_LOCK_SCREEN.equals(action)) {
            stopLockScreen();
        }
    }
}
