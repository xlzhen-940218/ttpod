package com.sds.android.ttpod.share.p136a;

import android.content.Context;
import android.os.Bundle;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.p139d.AccessTokenUtil;


/* renamed from: com.sds.android.ttpod.share.a.b */
/* loaded from: classes.dex */
public abstract class BaseApi {

    /* renamed from: a */
    private String f7316a;

    /* renamed from: b */
    private String f7317b;

    /* renamed from: c */
    private long f7318c;

    /* renamed from: b */
    protected abstract ShareResult mo2076b(ShareInfo shareInfo, ApiCallback apiCallback);

    public BaseApi() {
    }

    public BaseApi(String str) {
        this.f7316a = str;
    }

    /* renamed from: a */
    public String m2115a() {
        return this.f7316a;
    }

    /* renamed from: b */
    public String m2110b() {
        return this.f7317b;
    }

    /* renamed from: a */
    public void m2111a(String str) {
        this.f7317b = str;
    }

    /* renamed from: a */
    public void m2114a(long j) {
        this.f7318c = j;
    }

    /* renamed from: c */
    public boolean m2109c() {
        return System.currentTimeMillis() + (this.f7318c * 1000) <= System.currentTimeMillis();
    }

    /* renamed from: a */
    public void m2112a(Context context, Bundle bundle) {
        mo2100a(bundle);
        AccessTokenUtil.m1940a(context, mo2084d(), bundle);
    }

    /* renamed from: a */
    public void m2113a(Context context) {
        Bundle bundle = new Bundle();
        AccessTokenUtil.m1939b(context, mo2084d(), bundle);
        mo2100a(bundle);
    }

    /* renamed from: d */
    public String mo2084d() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2100a(Bundle bundle) {
        String string = bundle.getString("access_token");
        String string2 = bundle.getString("expires_in");
        m2111a(string);
        try {
            m2114a(Long.parseLong(string2));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo2096a(final ShareInfo shareInfo, final ApiCallback apiCallback) {
        TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask(null) { // from class: com.sds.android.ttpod.share.a.b.1
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            protected Object inBackground(Object obj) {
                return BaseApi.this.mo2076b(shareInfo, apiCallback);
            }

            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: b */
            protected void postExecute(Object obj) {
                BaseApi.this.mo2080a(apiCallback, (ShareResult) obj);
            }
        });
    }

    /* renamed from: e */
    public boolean mo2083e() {
        return false;
    }

    /* renamed from: a */
    protected void mo2080a(ApiCallback apiCallback, ShareResult shareResult) {
        if (apiCallback != null && shareResult != null) {
            apiCallback.mo1987a(shareResult);
        }
    }
}
