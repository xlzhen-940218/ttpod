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

/* renamed from: com.sds.android.ttpod.framework.modules.skin.f */
/* loaded from: classes.dex */
public class LocalSkinListLoader implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        m3601a();
    }

    /* renamed from: a */
    public void m3601a() {
        String m5294n = TTPodConfig.getSkinPath();
        String m3765a = CachedOnlineListReader.m3765a(m5294n, SkinUtils.m4645a(m5294n, "list_"), "skin_list");
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_RECOMMEND_SKIN_LIST, SkinUtils.m4648a(TextUtils.isEmpty(m3765a) ? null : (OnlineSkinListResult) JSONUtils.fromJson(m3765a, OnlineSkinListResult.class))), ModuleID.SKIN);
    }
}
