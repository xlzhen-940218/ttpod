package com.sds.android.ttpod.activities.musiccircle.p068a;

import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.api.PostAPI;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.a.e */
/* loaded from: classes.dex */
public final class MusicCircleChecker extends Checker {
    /* JADX INFO: Access modifiers changed from: protected */
    public MusicCircleChecker(Checker.InterfaceC0796a interfaceC0796a) {
        super(interfaceC0796a);
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.p068a.Checker
    /* renamed from: e */
    protected void mo7935e() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            PostAPI.m8853a(m2954aq.getAccessToken()).m8544a((RequestCallback<BaseResult>) this);
        }
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.p068a.Checker
    /* renamed from: a */
    protected boolean mo7936a(BaseResult baseResult) {
        return true;
    }
}
