package com.sds.android.ttpod.share.p136a;

import android.text.TextUtils;

import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.SinaWeiboFriendShipShowData;
import com.sds.android.ttpod.share.p139d.ShareHttpUtil;


import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.share.a.i */
/* loaded from: classes.dex */
public class SinaWeiboApi extends BaseApi {

    /* renamed from: a */
    private String f7348a;

    public SinaWeiboApi(String str) {
        super(str);
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: b */
    public ShareResult mo2076b(ShareInfo shareInfo, ApiCallback apiCallback) {
        String m1928a;
        List<NameValuePair> m2086a = m2086a(shareInfo);
        if (StringUtils.m8346a(shareInfo.m1964c())) {
            m1928a = ShareHttpUtil.m1929a("https://upload.api.weibo.com/2/statuses/update.json", m2086a);
        } else {
            m1928a = ShareHttpUtil.m1928a("https://upload.api.weibo.com/2/statuses/upload.json", m2086a, (NameValuePair[]) m2085b(shareInfo).toArray(new NameValuePair[0]));
        }
        ShareResult shareResult = new ShareResult();
        if (StringUtils.m8346a(m1928a)) {
            shareResult.m2090a("result is empty");
        } else {
            try {
                this.f7348a = new JSONObject(m1928a).optString("error_code");
                if (!TextUtils.isEmpty(this.f7348a)) {
                    shareResult.m2090a(m1928a);
                } else {
                    shareResult.m2091a(1);
                    shareResult.m2090a(m1928a);
                }
            } catch (JSONException e) {
                shareResult.m2090a(e.getMessage());
            }
        }
        return shareResult;
    }

    /* renamed from: a */
    private List<NameValuePair> m2086a(ShareInfo shareInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("status", shareInfo.m1958e()));
        arrayList.add(new BasicNameValuePair("access_token", m2110b()));
        return arrayList;
    }

    /* renamed from: b */
    private List<NameValuePair> m2085b(ShareInfo shareInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(User.KEY_AVATAR, shareInfo.m1964c()));
        return arrayList;
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: d */
    public String mo2084d() {
        return "SINA_TTPOD_TOKEN";
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: e */
    public boolean mo2083e() {
        if (!StringUtils.m8346a(this.f7348a)) {
            try {
                int parseInt = Integer.parseInt(this.f7348a);
                if (parseInt >= 21314 && parseInt <= 21327) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* renamed from: f */
    public void m2082f() {
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a(null) { // from class: com.sds.android.ttpod.share.a.i.1
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            protected Object mo1981a(Object obj) {
                SinaWeiboFriendShipShowData sinaWeiboFriendShipShowData = (SinaWeiboFriendShipShowData) JSONUtils.fromJson(SinaWeiboApi.this.m2087a(0L, null, 1766187712L, "天天动听"),  SinaWeiboFriendShipShowData.class);
                if (sinaWeiboFriendShipShowData == null || sinaWeiboFriendShipShowData.m1923a().m1922a()) {
                    return null;
                }
                return SinaWeiboApi.this.m2088a(1766187712L, "天天动听");
            }

            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: b */
            protected void postExecute(Object obj) {
            }
        });
    }

    /* renamed from: a */
    public String m2088a(long j, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("source", m2115a()));
        arrayList.add(new BasicNameValuePair("access_token", m2110b()));
        if (j != 0) {
            arrayList.add(new BasicNameValuePair("uid", String.valueOf(j)));
        }
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("screen_name", str));
        }
        return ShareHttpUtil.m1929a("https://api.weibo.com/2/friendships/create.json", arrayList);
    }

    /* renamed from: a */
    public String m2087a(long j, String str, long j2, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("source", m2115a()));
        arrayList.add(new BasicNameValuePair("access_token", m2110b()));
        if (j != 0) {
            arrayList.add(new BasicNameValuePair("source_id", String.valueOf(j)));
        }
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("source_screen_name", str));
        }
        if (j2 != 0) {
            arrayList.add(new BasicNameValuePair("target_id", String.valueOf(j2)));
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("target_screen_name", str2));
        }
        return ShareHttpUtil.m1927b("https://api.weibo.com/2/friendships/show.json", arrayList);
    }
}
