package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment;

/* renamed from: com.sds.android.cloudapi.ttpod.a.v */
/* loaded from: classes.dex */
public class RankAPI {
    /* renamed from: a */
    public static Request<MusicRanksResult> m8833a() {
        return new GetMethodRequest(MusicRanksResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").m8537b("id", Integer.valueOf((int) RankCategoryFragment.ID_RANK_CATEGORY)).m8537b(SEvent.FIELD_PAGE, "1").m8537b("size", 50);
    }

    /* renamed from: a */
    public static Request<OnlineMediaItemsResult> m8832a(int i, int i2) {
        return new GetMethodRequest(OnlineMediaItemsResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").m8537b("id", Integer.valueOf(i)).m8537b(SEvent.FIELD_PAGE, Integer.valueOf(i2)).m8537b("size", 50);
    }
}
