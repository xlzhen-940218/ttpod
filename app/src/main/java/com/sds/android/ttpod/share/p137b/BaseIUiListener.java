package com.sds.android.ttpod.share.p137b;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.share.b.c */
/* loaded from: classes.dex */
public abstract class BaseIUiListener implements IUiListener {
    /* renamed from: a */
    public abstract void mo2067a(JSONObject jSONObject);

    @Override // com.tencent.tauth.IUiListener
    public void onComplete(Object obj) {
        if (obj != null) {
            try {
                mo2067a(new JSONObject(obj.toString()));
                return;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        onError(new UiError(111111, "接口调用失败", ""));
    }

    @Override // com.tencent.tauth.IUiListener
    public void onError(UiError uiError) {
    }

    @Override // com.tencent.tauth.IUiListener
    public void onCancel() {
    }
}
