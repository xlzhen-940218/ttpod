package com.sds.android.ttpod.activities.musiccircle.p068a;

import com.sds.android.cloudapi.ttpod.data.NewNoticeCount;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.NoticeAPI;
import com.sds.android.cloudapi.ttpod.result.NewNoticeCountResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.ttpod.activities.musiccircle.p068a.Checker;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.a.d */
/* loaded from: classes.dex */
public final class MessageChecker extends Checker {
    /* JADX INFO: Access modifiers changed from: protected */
    public MessageChecker(Checker.InterfaceC0796a interfaceC0796a) {
        super(interfaceC0796a);
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.p068a.Checker
    /* renamed from: e */
    protected void mo7935e() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            NoticeAPI.m8874a(m2954aq.getAccessToken()).m8544a((RequestCallback<NewNoticeCountResult>) this);
        }
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.p068a.Checker
    /* renamed from: a */
    protected boolean mo7936a(BaseResult baseResult) {
        NewNoticeCount data = ((NewNoticeCountResult) baseResult).getData();
        return data != null && data.getNewMessageTotalCount() > 0;
    }
}
