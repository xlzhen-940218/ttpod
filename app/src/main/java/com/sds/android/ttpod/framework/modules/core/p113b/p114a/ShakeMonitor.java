package com.sds.android.ttpod.framework.modules.core.p113b.p114a;

import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.player.PlayStatus;

/* renamed from: com.sds.android.ttpod.framework.modules.core.b.a.b */
/* loaded from: classes.dex */
public class ShakeMonitor implements ShakeManager.InterfaceC1857a {

    /* renamed from: a */
    private ShakeManager f5924a;

    /* renamed from: a */
    public void m4304a() {
        this.f5924a = new ShakeManager(BaseApplication.getApplication(), false, -2);
        this.f5924a.m4314a(this);
        int value = 600 - Preferences.m2815x().value();
        this.f5924a.m4315a(-value, value);
    }

    /* renamed from: b */
    public void m4301b() {
        this.f5924a.m4319a();
    }

    /* renamed from: a */
    public void m4302a(ShakeSensitivityType shakeSensitivityType) {
        int value = 600 - shakeSensitivityType.value();
        this.f5924a.m4315a(-value, value);
    }

    @Override // com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeManager.InterfaceC1857a
    /* renamed from: a */
    public void mo4303a(int i) {
        if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING) {
            CommandCenter.getInstance().execute(new Command(CommandID.NEXT, new Object[0]));
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeManager.InterfaceC1857a
    /* renamed from: b */
    public void mo4300b(int i) {
    }

    @Override // com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeManager.InterfaceC1857a
    /* renamed from: c */
    public void mo4299c(int i) {
    }
}
