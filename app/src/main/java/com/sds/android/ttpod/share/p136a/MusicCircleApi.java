package com.sds.android.ttpod.share.p136a;

import com.sds.android.cloudapi.ttpod.p055a.PostAPI;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.ttpod.share.ShareInfo;


/* renamed from: com.sds.android.ttpod.share.a.c */
/* loaded from: classes.dex */
public class MusicCircleApi extends BaseApi {
    public MusicCircleApi() {
        m2114a(Long.MAX_VALUE);
    }

    @Override // com.sds.android.ttpod.share.p136a.BaseApi
    /* renamed from: b */
    public ShareResult mo2076b(ShareInfo shareInfo, ApiCallback apiCallback) {
        BaseResult m8531f = PostAPI.m8850a(m2110b(), shareInfo.m1944n(), shareInfo.m1945m(), shareInfo.m1958e()).execute();
        ShareResult shareResult = new ShareResult();
        if (m8531f == null) {
            shareResult.m2090a("error");
        } else {
            shareResult.m2091a(m8531f.getCode());
            shareResult.m2090a(m8531f.getMessage());
        }
        return shareResult;
    }
}
