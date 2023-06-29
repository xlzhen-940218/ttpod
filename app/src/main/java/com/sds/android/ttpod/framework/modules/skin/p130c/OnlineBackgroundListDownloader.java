package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.CommandID;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.i */
/* loaded from: classes.dex */
public class OnlineBackgroundListDownloader extends OnlineListDownloader {
    public OnlineBackgroundListDownloader(Long l, String str, String str2, CommandID commandID) {
        super(l, str, str2, commandID);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.OnlineListDownloader
    /* renamed from: a */
    protected String mo3720a(String str) {
        return !TextUtils.isEmpty(str) ? FileUtils.m8400l(str) : TTPodConfig.getBkgs();
    }
}
