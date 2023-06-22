package com.sds.android.ttpod.share.p136a;

import android.app.Activity;

/* renamed from: com.sds.android.ttpod.share.a.k */
/* loaded from: classes.dex */
public class WeChatFriendApi extends WeChatApi {
    public WeChatFriendApi(String str, Activity activity) {
        super(str, activity);
        this.f7350a = true;
    }

    @Override // com.sds.android.ttpod.share.p136a.WeChatApi
    /* renamed from: f */
    public boolean mo2073f() {
        return m2074h().getWXAppSupportAPI() >= 553779201;
    }
}
