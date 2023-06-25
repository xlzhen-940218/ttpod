package com.sds.android.ttpod.share.p136a;

import android.app.Activity;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.ShareType;
import com.sds.android.ttpod.share.p137b.BaseIUiListener;
import com.sds.android.ttpod.share.p139d.ShareContentUtil;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.ArrayList;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.share.a.g */
/* loaded from: classes.dex */
public class QQZoneApi extends BaseApi {

    /* renamed from: a */
    private String f7334a;

    /* renamed from: b */
    private int f7335b;

    /* renamed from: c */
    private Activity f7336c;

    /* renamed from: d */
    private Tencent f7337d;

    public QQZoneApi(Activity activity, String str) {
        super(str);
        this.f7336c = activity;
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: a */
    public void mo2096a(ShareInfo shareInfo, final ApiCallback apiCallback) {
        final ShareResult shareResult = new ShareResult();
        this.f7337d = Tencent.createInstance("100240447", this.f7336c);
        final Bundle m2097a = m2097a(shareInfo);
        new Thread(new Runnable() { // from class: com.sds.android.ttpod.share.a.g.1
            @Override // java.lang.Runnable
            public void run() {
                QQZoneApi.this.f7337d.shareToQzone(QQZoneApi.this.f7336c, m2097a, new BaseIUiListener() { // from class: com.sds.android.ttpod.share.a.g.1.1
                    @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener
                    /* renamed from: a */
                    public void mo2067a(JSONObject jSONObject) {
                        QQZoneApi.this.f7335b = jSONObject.optInt("ret");
                        if (QQZoneApi.this.f7335b == 0) {
                            shareResult.m2091a(1);
                            shareResult.m2090a(jSONObject.toString());
                        } else {
                            shareResult.m2090a(jSONObject.toString());
                        }
                        QQZoneApi.this.mo2080a(apiCallback, shareResult);
                    }

                    @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener, com.tencent.tauth.IUiListener
                    public void onError(UiError uiError) {
                        shareResult.m2090a(uiError.errorMessage);
                        QQZoneApi.this.mo2080a(apiCallback, shareResult);
                    }

                    @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener, com.tencent.tauth.IUiListener
                    public void onCancel() {
                    }

                    @Override
                    public void onWarning(int i) {

                    }
                });
            }
        }).start();
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: a */
    protected void mo2080a(final ApiCallback apiCallback, final ShareResult shareResult) {
        this.f7336c.runOnUiThread(new Runnable() { // from class: com.sds.android.ttpod.share.a.g.2
            @Override // java.lang.Runnable
            public void run() {
                if (apiCallback != null && shareResult != null) {
                    apiCallback.mo1987a(shareResult);
                }
            }
        });
    }

    /* renamed from: b */
    public void m2094b(String str) {
        this.f7334a = str;
    }

    /* renamed from: a */
    private Bundle m2097a(ShareInfo shareInfo) {
        Bundle bundle = new Bundle();
        if (shareInfo.m1942p()) {
            bundle.putString("targetUrl", shareInfo.m1943o());
        } else {
            shareInfo.m1959d(ShareContentUtil.m1933a("", this.f7336c.getString(R.string.share_text_tail_info), shareInfo, ShareType.QZONE));
            shareInfo.m1957e(ShareContentUtil.m1936a(shareInfo));
            bundle.putString("targetUrl", shareInfo.m1946l());
        }
        bundle.putInt("req_type", 1);
        bundle.putString("title", shareInfo.m1956f());
        bundle.putString("summary", shareInfo.m1958e());
        bundle.putString("site", "天天动听");
        if (!StringUtils.isEmpty(shareInfo.m1960d())) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(shareInfo.m1960d());
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: a */
    public void mo2100a(Bundle bundle) {
        super.mo2100a(bundle);
        m2094b(bundle.getString("openid"));
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: d */
    public String mo2084d() {
        return "TENTCANT_TTPOD_TOKEN";
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: e */
    public boolean mo2083e() {
        if (this.f7335b < 100013 || this.f7335b > 100016) {
            return super.mo2083e();
        }
        return true;
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: b */
    protected ShareResult mo2076b(ShareInfo shareInfo, ApiCallback apiCallback) {
        return null;
    }
}
