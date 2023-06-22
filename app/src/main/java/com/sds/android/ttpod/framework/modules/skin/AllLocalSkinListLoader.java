package com.sds.android.ttpod.framework.modules.skin;

import android.content.pm.ApplicationInfo;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p129b.SSkinInfo;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.a */
/* loaded from: classes.dex */
public class AllLocalSkinListLoader implements Runnable {

    /* renamed from: a */
    private static ArrayList<SkinItem> f6389a = m3871b();

    /* renamed from: a */
    public static SkinItem m3872a() {
        return f6389a.get(0);
    }

    @Override // java.lang.Runnable
    public void run() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(m3870c());
        arrayList.addAll(m3869d());
        arrayList.addAll(m3868e());
        CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_ALL_LOCAL_SKIN_LIST, arrayList), ModuleID.SKIN);
    }

    /* renamed from: b */
    private static ArrayList<SkinItem> m3871b() {
        ArrayList<SkinItem> arrayList = new ArrayList<>();
        try {
            String[] list = BaseApplication.getApplication().getAssets().list("skin");
            if (list.length > 0) {
                int length = list.length;
                for (int i = 0; i < length; i++) {
                    arrayList.add(new SkinItem("skin" + File.separator + list[i], 1));
                }
            }
        } catch (IOException e) {
            SkinModule.logE("SkinListLoader.readInternalSkinList read internal skin list error.");
            e.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: c */
    private ArrayList<SkinItem> m3870c() {
        ArrayList<SkinItem> arrayList = new ArrayList<>();
        Iterator<SkinItem> it = f6389a.iterator();
        while (it.hasNext()) {
            SkinItem next = it.next();
            SkinItem skinItem = new SkinItem(next.m3571b(), 1);
            skinItem.m3573a(new C1976a(next.m3571b()).m3866a());
            arrayList.add(skinItem);
        }
        return arrayList;
    }

    /* renamed from: d */
    private ArrayList<SkinItem> m3869d() {
        ArrayList<SkinItem> arrayList = new ArrayList<>();
        List<ApplicationInfo> list = null;
        try {
            list = BaseApplication.getApplication().getPackageManager().getInstalledApplications(0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            for (ApplicationInfo applicationInfo : list) {
                if (applicationInfo != null && applicationInfo.packageName.startsWith("com.sds.android.ttpod.skin.")) {
                    arrayList.add(new SkinItem(applicationInfo.packageName, 2));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    private ArrayList<SkinItem> m3868e() {
        ArrayList<SkinItem> arrayList = new ArrayList<>();
        File[] m4650a = SkinUtils.m4650a();
        if (m4650a != null && m4650a.length > 0) {
            for (File file : m4650a) {
                SkinItem skinItem = new SkinItem(file.getAbsolutePath(), 0);
                skinItem.m3573a(new SkinInfoLoader(skinItem.m3571b()).m3576a());
                arrayList.add(skinItem);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AllLocalSkinListLoader.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.a$a */
    /* loaded from: classes.dex */
    public class C1976a extends SkinReader {

        /* renamed from: c */
        private String f6391c;

        public C1976a(String str) {
            this.f6391c = str;
        }

        /* renamed from: a */
        public SSkinInfo m3866a() {
            SSkinInfo sSkinInfo = null;
            if (m3530a(m3533a(1, this.f6391c))) {
                KXmlParser kXmlParser = new KXmlParser();
                BufferedReader k = m3524k();
                if (k != null) {
                    try {
                        kXmlParser.setInput(k);
                        kXmlParser.nextTag();
                        kXmlParser.require(2, null, "Theme");
                        sSkinInfo = new SSkinInfo(kXmlParser);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e2) {
                        e2.printStackTrace();
                        sSkinInfo = null;
                    }
                    m3525j();
                    return sSkinInfo;
                }
            }
            sSkinInfo = null;
            m3525j();
            return sSkinInfo;
        }
    }
}
