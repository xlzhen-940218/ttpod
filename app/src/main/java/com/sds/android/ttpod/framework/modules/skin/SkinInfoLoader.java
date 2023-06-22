package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p129b.SSkinInfo;
import java.io.BufferedReader;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.l */
/* loaded from: classes.dex */
public class SkinInfoLoader extends SkinReader implements Runnable {

    /* renamed from: a */
    private String f6657a;

    public SkinInfoLoader(String str) {
        this.f6657a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        CommandCenter.m4607a().m4595b(new Command(CommandID.UPDATE_SKIN_INFO, this.f6657a, m3576a()), ModuleID.SKIN);
    }

    /* renamed from: a */
    public SSkinInfo m3576a() {
        SSkinInfo sSkinInfo = null;
        if (m3526e(this.f6657a)) {
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
