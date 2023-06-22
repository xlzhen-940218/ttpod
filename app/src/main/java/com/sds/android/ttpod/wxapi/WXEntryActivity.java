package com.sds.android.ttpod.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sds.android.ttpod.share.p136a.ApiCallback;
import com.sds.android.ttpod.share.p136a.ShareResult;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/* loaded from: classes.dex */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    /* renamed from: a */
    private static ApiCallback f8446a;

    /* renamed from: b */
    private IWXAPI f8447b;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f8447b = WXAPIFactory.createWXAPI(getApplicationContext(), "wx35c4036acd33a2bc", true);
        this.f8447b.registerApp("wx35c4036acd33a2bc");
        this.f8447b.handleIntent(getIntent(), this);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case -4:
                m1131a(f8446a, new ShareResult(0, "发送被拒绝"));
                break;
            case -3:
            case -1:
            default:
                m1131a(f8446a, new ShareResult(0, "发送返回"));
                break;
            case -2:
                m1131a(f8446a, new ShareResult(0, "取消分享"));
                break;
            case 0:
                m1131a(f8446a, new ShareResult(1, "分享成功"));
                break;
        }
        finish();
    }

    /* renamed from: a */
    private void m1131a(ApiCallback apiCallback, ShareResult shareResult) {
        if (apiCallback != null) {
            apiCallback.mo1987a(shareResult);
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        this.f8447b.handleIntent(intent, this);
    }

    /* renamed from: a */
    public static void m1132a(ApiCallback apiCallback) {
        f8446a = apiCallback;
    }
}
