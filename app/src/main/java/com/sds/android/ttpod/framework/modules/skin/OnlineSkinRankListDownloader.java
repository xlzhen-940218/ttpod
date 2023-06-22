package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p130c.OnlineListDownloader;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.i */
/* loaded from: classes.dex */
public class OnlineSkinRankListDownloader extends OnlineListDownloader {
    public OnlineSkinRankListDownloader(Long l, String str, String str2, CommandID commandID) {
        super(l, str, str2, commandID);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.OnlineListDownloader
    /* renamed from: a */
    protected String mo3599a(Long l) {
        return "rank_" + l + ".json";
    }
}
