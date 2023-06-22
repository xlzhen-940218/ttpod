package com.sds.android.ttpod.framework.modules.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandle;
import com.sds.android.ttpod.framework.modules.skin.p130c.p131a.PackHandleProxy;
import com.sds.android.ttpod.framework.modules.skin.p130c.p131a.RandomAccessFileInputStream;
import com.sds.android.ttpod.framework.p106a.CodeIdentifyInputStreamReader;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.p */
/* loaded from: classes.dex */
public class SkinReader {

    /* renamed from: b */
    protected PackHandle f6692b = null;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m3530a(InputStream inputStream) {
        boolean z = this.f6692b == null;
        if (this.f6692b == null) {
            this.f6692b = new PackHandleProxy();
        }
        try {
            this.f6692b.mo3756a(inputStream, z);
        } catch (Exception e) {
            m3528b(inputStream);
        }
        return this.f6692b != null && this.f6692b.mo3757a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public boolean m3526e(String str) {
        if (this.f6692b == null) {
            this.f6692b = new PackHandleProxy();
        }
        try {
            this.f6692b.mo3755a(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.f6692b != null && this.f6692b.mo3757a();
    }

    /* renamed from: b */
    private void m3528b(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                LogUtils.m8381c(SkinModule.TAG, "BaseCreator.openSkinPackInputStream crate unPacker failed");
            }
        }
    }

    /* renamed from: j */
    public void m3525j() {
        if (this.f6692b != null) {
            try {
                this.f6692b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: k */
    public BufferedReader m3524k() {
        byte[] m3523l = m3523l();
        if (m3523l != null) {
            return new BufferedReader(new CodeIdentifyInputStreamReader(new ByteArrayInputStream(m3523l)));
        }
        return null;
    }

    /* renamed from: l */
    protected byte[] m3523l() {
        byte[] bArr;
        IOException e;
        bArr = null;
        for (String str : m3529a(File.separatorChar + "skin")) {
            try {
                bArr = this.f6692b.mo3753b(str);
                if (bArr != null) {
                    break;
                }
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return bArr;
            }
        }
        return bArr;
    }

    /* renamed from: a */
    private String[] m3529a(String str) {
        String m7221g = DisplayUtils.m7221g();
        return new String[]{str + DisplayUtils.m7225c() + "x" + DisplayUtils.m7224d() + ".xml", str + DisplayUtils.m7222f() + ".xml", str + m7221g + ".xml", File.separatorChar + m7221g + File.separatorChar + "skin.xml", File.separatorChar + "skin.xml"};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public InputStream m3533a(int i, String str) {
        BaseApplication m4635c = BaseApplication.getApplication();
        switch (i) {
            case 1:
                return m3531a(m4635c.getAssets(), str);
            case 2:
                return m3532a(m4635c, str);
            default:
                return m3527b(str);
        }
    }

    /* renamed from: a */
    private InputStream m3532a(Context context, String str) {
        Resources resourcesForApplication;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                resourcesForApplication = packageManager.getResourcesForApplication(str);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            resourcesForApplication = null;
        }
        if (resourcesForApplication != null) {
            return m3531a(resourcesForApplication.getAssets(), "skin/skin.tsk");
        }
        return null;
    }

    /* renamed from: a */
    private InputStream m3531a(AssetManager assetManager, String str) {
        try {
            return assetManager.open(str, 1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private InputStream m3527b(String str) {
        try {
            return new RandomAccessFileInputStream(str, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
