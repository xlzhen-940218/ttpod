package com.sds.android.ttpod.share.p137b;

import android.app.Activity;
import android.content.Intent;

/* renamed from: com.sds.android.ttpod.share.b.d */
/* loaded from: classes.dex */
public class NoneAuthHandler extends AuthHandler {
    public NoneAuthHandler(Activity activity) {
        super(activity);
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: a */
    public String mo2066a() {
        return null;
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: c */
    public void mo2059c(AuthCallback authCallback) {
        m2070a(authCallback, "ssoAuth() not support");
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: a */
    public void mo2065a(int i, int i2, Intent intent) {
    }
}
