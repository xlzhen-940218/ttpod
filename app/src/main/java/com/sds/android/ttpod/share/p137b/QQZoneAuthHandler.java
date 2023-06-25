package com.sds.android.ttpod.share.p137b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.sdk.lib.util.UrlUtils;
import com.sds.android.ttpod.R;

import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.share.b.f */
/* loaded from: classes.dex */
public class QQZoneAuthHandler extends AuthHandler {

    /* renamed from: b */
    private Tencent f7363b;

    public QQZoneAuthHandler(Activity activity) {
        super(activity);
        try {
            this.f7363b = Tencent.createInstance("100240447", this.activity.getApplicationContext());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: a */
    public String mo2066a() {
        HashMap hashMap = new HashMap();
        hashMap.put("client_id", "100240447");
        hashMap.put("response_type", "token");
        hashMap.put("redirect_uri", "http://ttus.ttpod.com/thirdlogin/qq?code=beed767ac431765bb6f66c4a9a437029");
        hashMap.put("scope", "add_share,add_pic_t");
        hashMap.put("display", "mobile");
        return UrlUtils.m8333a("https://openmobile.qq.com/oauth2.0/authorize", hashMap);
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: c */
    public void mo2059c(final AuthCallback authCallback) {
        if (this.f7363b == null) {
            Toast.makeText(this.activity, "当前系统不支持QQ授权", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            this.activity.getString(R.string.app_name);
            this.f7363b.login(this.activity, "add_share,add_pic_t", new BaseIUiListener() { // from class: com.sds.android.ttpod.share.b.f.1
                @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener
                /* renamed from: a */
                public void mo2067a(JSONObject jSONObject) {
                    String optString = jSONObject.optString("access_token");
                    String optString2 = jSONObject.optString("expires_in");
                    String optString3 = jSONObject.optString("openid");
                    Bundle bundle = new Bundle();
                    bundle.putString("access_token", optString);
                    bundle.putString("expires_in", optString2);
                    bundle.putString("openid", optString3);
                    if (StringUtils.isEmpty(optString)) {
                        QQZoneAuthHandler.this.m2070a(authCallback, "QQZone SSO授权失败");
                    } else {
                        QQZoneAuthHandler.this.m2071a(authCallback, bundle);
                    }
                }

                @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener, com.tencent.tauth.IUiListener
                public void onError(UiError uiError) {
                    QQZoneAuthHandler.this.m2070a(authCallback, uiError.errorMessage);
                }

                @Override // com.sds.android.ttpod.share.p137b.BaseIUiListener, com.tencent.tauth.IUiListener
                public void onCancel() {
                    QQZoneAuthHandler.this.m2070a(authCallback, "cancel");
                }

                @Override
                public void onWarning(int i) {

                }
            });
        } catch (Exception e) {
            authCallback.mo1975a(e.toString());
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.share.p137b.AuthHandler
    /* renamed from: a */
    public void mo2065a(int i, int i2, Intent intent) {
    }
}
