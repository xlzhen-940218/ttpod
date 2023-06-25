package com.sds.android.ttpod.share.p136a;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.p139d.ShareHttpUtil;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.share.a.f */
/* loaded from: classes.dex */
public class QQWeiboApi extends BaseApi {

    /* renamed from: a */
    private String f7332a;

    /* renamed from: b */
    private int f7333b;

    public QQWeiboApi(String str) {
        super(str);
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: b */
    public ShareResult mo2076b(ShareInfo shareInfo, ApiCallback apiCallback) {
        String m1928a;
        List<NameValuePair> m2104a = m2104a(shareInfo);
        if (StringUtils.isEmpty(shareInfo.m1964c())) {
            m1928a = ShareHttpUtil.m1929a("https://open.t.qq.com/api/t/add_pic", m2104a);
        } else {
            m1928a = ShareHttpUtil.m1928a("https://open.t.qq.com/api/t/add_pic", m2104a, (NameValuePair[]) m2103b(shareInfo).toArray(new NameValuePair[0]));
        }
        ShareResult shareResult = new ShareResult();
        if (StringUtils.isEmpty(m1928a)) {
            shareResult.m2090a("result is empty");
        } else {
            try {
                this.f7333b = new JSONObject(m1928a).optInt("ret");
                if (this.f7333b == 0) {
                    shareResult.m2091a(1);
                    shareResult.m2090a(m1928a);
                } else {
                    shareResult.m2090a(m1928a);
                }
            } catch (JSONException e) {
                shareResult.m2090a(e.getMessage());
            }
        }
        return shareResult;
    }

    /* renamed from: a */
    private List<NameValuePair> m2104a(ShareInfo shareInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("oauth_version", "2.a"));
        arrayList.add(new BasicNameValuePair("scope", "all"));
        arrayList.add(new BasicNameValuePair("access_token", m2110b()));
        arrayList.add(new BasicNameValuePair("oauth_consumer_key", m2115a()));
        arrayList.add(new BasicNameValuePair("openid", m2101f()));
        arrayList.add(new BasicNameValuePair("format", "json"));
        arrayList.add(new BasicNameValuePair("syncflag", FeedbackItem.STATUS_WAITING));
        arrayList.add(new BasicNameValuePair("content", shareInfo.m1958e()));
        arrayList.add(new BasicNameValuePair("compatibleflag", FeedbackItem.STATUS_WAITING));
        return arrayList;
    }

    /* renamed from: b */
    private List<NameValuePair> m2103b(ShareInfo shareInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(User.KEY_AVATAR, shareInfo.m1964c()));
        return arrayList;
    }

    /* renamed from: f */
    public String m2101f() {
        return this.f7332a;
    }

    /* renamed from: b */
    public void m2102b(String str) {
        this.f7332a = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: a */
    public void mo2100a(Bundle bundle) {
        super.mo2100a(bundle);
        m2102b(bundle.getString("openid"));
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: e */
    public boolean mo2083e() {
        if (this.f7333b < 1 || this.f7333b > 4) {
            return super.mo2083e();
        }
        return true;
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: d */
    public String mo2084d() {
        return "TENTCANT_WEIBO_TTPOD_TOKEN";
    }
}
