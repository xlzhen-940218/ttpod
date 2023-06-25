package com.sds.android.ttpod.framework.modules.p123e;

import android.app.KeyguardManager;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.p106a.RomRecognizer;

/* renamed from: com.sds.android.ttpod.framework.modules.e.b */
/* loaded from: classes.dex */
public class SystemLockManager {

    /* renamed from: a */
    private static final String f6116a = SystemLockManager.class.getSimpleName();

    /* renamed from: b */
    private static KeyguardManager f6117b = null;

    /* renamed from: c */
    private static KeyguardManager.KeyguardLock f6118c = null;

    /* compiled from: SystemLockManager.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.e.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1899a {
        /* renamed from: a */
        void mo4071a();
    }

    /* renamed from: f */
    private static synchronized void m4073f() {
        synchronized (SystemLockManager.class) {
            if (f6117b == null) {
                f6117b = (KeyguardManager) BaseApplication.getApplication().getSystemService("keyguard");
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m4079a() {
        synchronized (SystemLockManager.class) {
            m4073f();
            if (f6117b.inKeyguardRestrictedInputMode()) {
                f6118c = f6117b.newKeyguardLock(f6116a);
                f6118c.disableKeyguard();
                LogUtils.debug(f6116a, "--Keyguard disabled");
            } else {
                f6118c = null;
            }
        }
    }

    /* renamed from: b */
    public static synchronized void m4077b() {
        synchronized (SystemLockManager.class) {
            if (f6117b != null && f6118c != null) {
                f6118c.reenableKeyguard();
                f6118c = null;
            }
        }
    }

    /* renamed from: c */
    public static synchronized boolean m4076c() {
        boolean inKeyguardRestrictedInputMode;
        synchronized (SystemLockManager.class) {
            inKeyguardRestrictedInputMode = f6117b != null ? f6117b.inKeyguardRestrictedInputMode() : false;
        }
        return inKeyguardRestrictedInputMode;
    }

    /* renamed from: a */
    public static synchronized void m4078a(final InterfaceC1899a interfaceC1899a) {
        synchronized (SystemLockManager.class) {
            if (m4076c()) {
                f6117b.exitKeyguardSecurely(new KeyguardManager.OnKeyguardExitResult() { // from class: com.sds.android.ttpod.framework.modules.e.b.1
                    @Override // android.app.KeyguardManager.OnKeyguardExitResult
                    public void onKeyguardExitResult(boolean z) {
                        SystemLockManager.m4072g();
                        if (z) {
                            LogUtils.debug(SystemLockManager.f6116a, "--Keyguard exited success");
                            interfaceC1899a.mo4071a();
                            return;
                        }
                        LogUtils.debug(SystemLockManager.f6116a, "--Keyguard exited failed");
                    }
                });
            } else {
                interfaceC1899a.mo4071a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public static void m4072g() {
        if (!RomRecognizer.m4655a()) {
            LogUtils.debug(f6116a, "reenable keyguard... ");
            m4077b();
        }
    }
}
