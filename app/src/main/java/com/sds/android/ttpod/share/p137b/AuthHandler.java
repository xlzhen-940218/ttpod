package com.sds.android.ttpod.share.p137b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sds.android.ttpod.share.p138c.AuthDialog;

/* renamed from: com.sds.android.ttpod.share.b.b */
/* loaded from: classes.dex */
public abstract class AuthHandler {

    /* renamed from: a */
    protected Activity activity;

    /* renamed from: a */
    public abstract String mo2066a();

    /* renamed from: a */
    public abstract void mo2065a(int i, int i2, Intent intent);

    /* renamed from: c */
    public abstract void mo2059c(AuthCallback authCallback);

    public AuthHandler(Activity activity) {
        this.activity = activity;
    }

    /* renamed from: a */
    public final void m2072a(final AuthCallback authCallback) {
        mo2059c(new AuthCallback() { // from class: com.sds.android.ttpod.share.b.b.1
            @Override // com.sds.android.ttpod.share.p137b.AuthCallback
            /* renamed from: a */
            public void mo1976a(Bundle bundle) {
                AuthHandler.this.m2071a(authCallback, bundle);
            }

            @Override // com.sds.android.ttpod.share.p137b.AuthCallback
            /* renamed from: a */
            public void mo1975a(String str) {
                AuthHandler.this.m2069b(authCallback);
            }
        });
    }

    /* renamed from: b */
    public void m2069b(AuthCallback authCallback) {
        if (!this.activity.isFinishing()) {
            new AuthDialog(this.activity, mo2066a(), authCallback).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m2070a(AuthCallback authCallback, String str) {
        if (authCallback != null) {
            authCallback.mo1975a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m2071a(AuthCallback authCallback, Bundle bundle) {
        if (authCallback != null) {
            authCallback.mo1976a(bundle);
        }
    }
}
