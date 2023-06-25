package com.sds.android.ttpod.framework.modules.core.p116d.p118b;

import com.sds.android.sdk.lib.util.NetUtils;
import com.sds.android.sdk.lib.util.ZIPUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;

import java.io.File;
import java.io.IOException;

/* renamed from: com.sds.android.ttpod.framework.modules.core.d.b.e */
/* loaded from: classes.dex */
public final class WifiTransmission implements HttpServer.InterfaceC1874a {

    /* renamed from: a */
    private static WifiTransmission f6022a;

    /* renamed from: b */
    private HttpServer f6023b;

    /* renamed from: b */
    public void m4179b() {
        if (this.f6023b == null) {
            String m8376a = NetUtils.m8376a(BaseApplication.getApplication());
            if (m8376a != null) {
                String m4177d = m4177d();
                if (m4177d != null) {
                    try {
                        this.f6023b = new HttpServer(m8376a, 8888, m4177d, TTPodConfig.m5296l(), this);
                        this.f6023b.m4218a();
                        CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_WIFI_TRANSMISSION_STATE, new CommonResult(ErrCode.ErrNone, "ok", "http://" + m8376a + ":8888")), ModuleID.MEDIA_SCAN);
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_WIFI_TRANSMISSION_STATE, new CommonResult(ErrCode.ErrPathNotFound, e.getMessage())), ModuleID.MEDIA_SCAN);
                        return;
                    }
                }
                return;
            }
            CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_WIFI_TRANSMISSION_STATE, new CommonResult(ErrCode.ErrNotReady, "getLocalWiFiIPAddress Error")), ModuleID.MEDIA_SCAN);
        }
    }

    /* renamed from: c */
    public void m4178c() {
        if (this.f6023b != null) {
            this.f6023b.m4205b();
            this.f6023b = null;
        }
        f6022a = null;
    }

    /* renamed from: d */
    private String m4177d() {
        File file = new File(BaseApplication.getApplication().getFilesDir() + "/www_700/");
        if (!file.exists()) {
            try {
                file.mkdirs();
                ZIPUtils.m8330a(BaseApplication.getApplication().getAssets().open("www.zip"), file.getPath());
            } catch (Exception e) {
                e.printStackTrace();
                file.delete();
                return null;
            }
        }
        return file.getPath();
    }

    @Override // com.sds.android.ttpod.framework.modules.core.p116d.p118b.HttpServer.InterfaceC1874a
    /* renamed from: a */
    public void mo4180a() {
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_WIFI_TRANSMISSION_STATE, new CommonResult(ErrCode.ErrCompletion, "transmission completion")), ModuleID.MEDIA_SCAN);
    }
}
