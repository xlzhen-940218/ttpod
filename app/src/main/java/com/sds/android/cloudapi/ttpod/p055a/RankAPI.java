package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment;

/* renamed from: com.sds.android.cloudapi.ttpod.a.v */
/* loaded from: classes.dex */
public class RankAPI {
    /* renamed from: a */
    public static Request<MusicRanksResult> getMusicRanksRequest() {
        return new GetMethodRequest(MusicRanksResult.class, "http://127.0.0.1:18098/ttpod?a=getnewttpod")
                .putParams("id", Integer.valueOf((int) RankCategoryFragment.ID_RANK_CATEGORY))
                .putParams("page", "1")
                .putParams("size", 50);
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8832a(int i, int i2) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod")
                .putParams("id", Integer.valueOf(i))
                .putParams("page", Integer.valueOf(i2))
                .putParams("size", 50);
    }
}
