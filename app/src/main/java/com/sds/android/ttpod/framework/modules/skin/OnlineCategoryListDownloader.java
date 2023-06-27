package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.skin.p130c.OnlineListDownloader;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.h */
/* loaded from: classes.dex */
public class OnlineCategoryListDownloader extends OnlineListDownloader {
    public OnlineCategoryListDownloader(String str, String str2, CommandID commandID) {
        super(null, str, str2, commandID);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p130c.OnlineListDownloader, java.lang.Runnable
    public void run() {
        String str = this.f6539a + ".tmp";
        if (m3719a(this.f6540b, str) && !TextUtils.isEmpty(this.f6539a)) {
            FileUtils.exists(this.f6539a);
        }
        FileUtils.m8410c(str, this.f6539a);
        CommandCenter.getInstance().m4595b(new Command(this.f6541c, new Object[0]), ModuleID.SKIN);
    }
}
