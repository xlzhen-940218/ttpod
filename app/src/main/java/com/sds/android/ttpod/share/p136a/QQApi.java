package com.sds.android.ttpod.share.p136a;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.p137b.BaseIUiListener;
import com.sds.android.ttpod.share.p139d.ShareContentUtil;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.share.a.e */
/* loaded from: classes.dex */
public class QQApi extends BaseApi {

    /* renamed from: a */
    private Tencent f7323a;

    /* renamed from: b */
    private Activity f7324b;

    /* renamed from: c */
    private ShareInfo f7325c;

    /* renamed from: d */
    private int f7326d;

    public QQApi(String str, Activity activity) {
        super(str);
        this.f7326d = 0;
        this.f7324b = activity;
        try {
            this.f7323a = Tencent.createInstance("100240447", this.f7324b.getApplicationContext());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: b */
    public ShareResult mo2076b(ShareInfo shareInfo, ApiCallback apiCallback) {
        this.f7325c = shareInfo;
        return null;
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: a */
    protected void mo2080a(final ApiCallback apiCallback, ShareResult shareResult) {
        if (this.f7323a == null) {
            Toast.makeText(this.f7324b, "当前系统不支持QQ分享", Toast.LENGTH_SHORT).show();
        } else {
            this.f7323a.shareToQQ(this.f7324b, m2106a(this.f7325c), new BaseIUiListener() { // from class: com.sds.android.ttpod.share.a.e.1
                @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener
                /* renamed from: a */
                public void complete(JSONObject jSONObject) {
                    QQApi.this.m2105b(apiCallback, new ShareResult(1, jSONObject.toString()));
                }

                @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener, com.tencent.tauth.IUiListener
                public void onError(UiError uiError) {
                    QQApi.this.m2105b(apiCallback, new ShareResult(0, uiError.errorMessage));
                }

                @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener, com.tencent.tauth.IUiListener
                public void onCancel() {
                }

                @Override
                public void onWarning(int i) {

                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m2105b(final ApiCallback apiCallback, final ShareResult shareResult) {
        if (apiCallback != null) {
            this.f7324b.runOnUiThread(new Runnable() { // from class: com.sds.android.ttpod.share.a.e.2
                @Override // java.lang.Runnable
                public void run() {
                    apiCallback.mo1987a(shareResult);
                }
            });
        }
    }

    /* renamed from: a */
    private Bundle m2106a(ShareInfo shareInfo) {
        Bundle bundle = new Bundle();
        String string = this.f7324b.getString(R.string.ttpod);
        bundle.putString("title", shareInfo.m1956f());
        bundle.putString("imageUrl", shareInfo.m1960d());
        bundle.putString("site", string);
        if (shareInfo.m1942p()) {
            bundle.putInt("req_type", 1);
            bundle.putString("summary", shareInfo.m1958e());
            bundle.putString("targetUrl", shareInfo.m1943o());
        } else {
            String m1954g = shareInfo.m1954g();
            if (!ShareContentUtil.m1934a(m1954g)) {
                m1954g = "未知";
            }
            bundle.putInt("req_type", 2);
            bundle.putString("summary", m1954g);
            bundle.putString("targetUrl", shareInfo.m1946l());
            bundle.putString("audio_url", shareInfo.m1947k());
            bundle.putInt("cflag", this.f7326d);
            bundle.putString("appName", string);
        }
        return bundle;
    }
}
