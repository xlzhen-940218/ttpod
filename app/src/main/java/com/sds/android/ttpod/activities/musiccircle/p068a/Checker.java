package com.sds.android.ttpod.activities.musiccircle.p068a;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.RequestCallback;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.a.b */
/* loaded from: classes.dex */
public abstract class Checker implements RequestCallback {

    /* renamed from: a */
    private InterfaceC0796a f2763a;

    /* renamed from: b */
    private boolean f2764b = false;

    /* renamed from: c */
    private BaseResult f2765c;

    /* compiled from: Checker.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.a.b$a */
    /* loaded from: classes.dex */
    protected interface InterfaceC0796a {
        /* renamed from: a */
        void mo7945a(Checker checker, boolean z, BaseResult baseResult);
    }

    /* renamed from: a */
    protected abstract boolean mo7936a(BaseResult baseResult);

    /* renamed from: e */
    protected abstract void mo7935e();

    /* JADX INFO: Access modifiers changed from: protected */
    public Checker(InterfaceC0796a interfaceC0796a) {
        this.f2763a = interfaceC0796a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m7953a() {
        return this.f2764b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public BaseResult m7952b() {
        return this.f2765c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m7951c() {
        this.f2764b = false;
        this.f2765c = null;
    }

    @Override // com.sds.android.sdk.lib.request.RequestCallback
    public void onRequestSuccess(BaseResult baseResult) {
        this.f2765c = baseResult;
        this.f2764b = mo7936a(baseResult);
        this.f2763a.mo7945a(this, this.f2764b, baseResult);
    }

    @Override // com.sds.android.sdk.lib.request.RequestCallback
    public void onRequestFailure(BaseResult baseResult) {
        this.f2765c = baseResult;
        this.f2764b = false;
        this.f2763a.mo7945a(this, this.f2764b, baseResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public void m7950d() {
        if (this.f2764b) {
            this.f2763a.mo7945a(this, this.f2764b, this.f2765c);
        } else {
            mo7935e();
        }
    }
}
