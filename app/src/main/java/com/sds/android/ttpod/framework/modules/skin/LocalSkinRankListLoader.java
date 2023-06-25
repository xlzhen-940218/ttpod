package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.g */
/* loaded from: classes.dex */
public class LocalSkinRankListLoader implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        m3600a();
    }

    /* renamed from: a */
    public void m3600a() {
        String m3767a = CachedOnlineListReader.m3767a(SkinUtils.m4645a(TTPodConfig.m5294n(), "rank_"));
        OnlineSkinListResult onlineSkinListResult = TextUtils.isEmpty(m3767a) ? null : (OnlineSkinListResult) JSONUtils.fromJson(m3767a, OnlineSkinListResult.class);
        new ArrayList();
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SKIN_RANK_LIST, SkinUtils.m4648a(onlineSkinListResult)), ModuleID.SKIN);
    }
}
