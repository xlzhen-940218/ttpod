package com.sds.android.ttpod.share.p136a;

import android.app.Activity;
import android.graphics.Bitmap;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.share.ShareInfo;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.io.ByteArrayOutputStream;

/* renamed from: com.sds.android.ttpod.share.a.j */
/* loaded from: classes.dex */
public class WeChatApi extends BaseApi {

    /* renamed from: c */
    private IWXAPI f7352c;

    /* renamed from: d */
    private ShareInfo f7353d;

    /* renamed from: e */
    private Activity f7354e;

    /* renamed from: b */
    private BitmapUtils f7351b = new BitmapUtils();

    /* renamed from: a */
    protected boolean f7350a = false;

    public WeChatApi(String str, Activity activity) {
        this.f7354e = activity;
        m2114a(Long.MAX_VALUE);
        this.f7352c = WXAPIFactory.createWXAPI(activity.getApplicationContext(), str, true);
        this.f7352c.registerApp(str);
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: b */
    public ShareResult mo2076b(ShareInfo shareInfo, ApiCallback apiCallback) {
        this.f7353d = shareInfo;
        return null;
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: a */
    protected void mo2080a(ApiCallback apiCallback, ShareResult shareResult) {
        String m1946l = this.f7353d.m1946l();
        String m1947k = this.f7353d.m1947k();
        String m1956f = this.f7353d.m1956f();
        String m1964c = this.f7353d.m1964c();
        String m1954g = this.f7353d.m1954g();
        String m1958e = this.f7353d.m1958e();
        if (this.f7353d.m1942p()) {
            m2078a(m1958e, m1956f, m1964c, this.f7353d.m1943o());
        } else {
            m2077a(m1946l, m1947k, m1956f, m1954g, m1964c);
        }
    }

    /* renamed from: f */
    public boolean mo2073f() {
        return true;
    }

    /* renamed from: g */
    public boolean m2075g() {
        return this.f7352c.isWXAppInstalled();
    }

    /* renamed from: h */
    public IWXAPI m2074h() {
        return this.f7352c;
    }

    /* renamed from: a */
    private boolean m2078a(String str, String str2, String str3, String str4) {
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        m2079a(wXMediaMessage, str3);
        wXMediaMessage.title = str2;
        wXMediaMessage.description = str;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wXMediaMessage;
        req.scene = this.f7350a ? 1 : 0;
        return this.f7352c.sendReq(req);
    }

    /* renamed from: a */
    private void m2077a(String str, String str2, String str3, String str4, String str5) {
        WXMusicObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str;
        wXMusicObject.musicDataUrl = str2;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.title = str3;
        wXMediaMessage.description = str4;
        m2079a(wXMediaMessage, str5);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.scene = 1;
        req.message = wXMediaMessage;
        req.scene = this.f7350a ? 1 : 0;
        this.f7352c.sendReq(req);
    }

    /* renamed from: a */
    private boolean m2079a(WXMediaMessage wXMediaMessage, String str) {
        if (FileUtils.m8419a(str)) {
            wXMediaMessage.thumbData = m2081a(this.f7351b.m8444a(str, 60, 100), true);
        } else {
            wXMediaMessage.thumbData = m2081a(this.f7351b.m8455a(this.f7354e.getResources(), R.drawable.share_ttpod), true);
        }
        return true;
    }

    /* renamed from: a */
    private static byte[] m2081a(Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        if (z) {
            bitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
            return byteArray;
        }
    }
}
