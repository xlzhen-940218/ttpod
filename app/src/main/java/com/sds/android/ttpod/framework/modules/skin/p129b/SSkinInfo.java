package com.sds.android.ttpod.framework.modules.skin.p129b;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.x */
/* loaded from: classes.dex */
public class SSkinInfo implements Serializable {

    /* renamed from: a */
    protected String f6478a;

    /* renamed from: b */
    protected String f6479b;

    /* renamed from: c */
    protected String f6480c;

    /* renamed from: d */
    protected String f6481d;

    /* renamed from: e */
    protected String f6482e;

    /* renamed from: f */
    protected String f6483f;

    /* renamed from: g */
    protected String f6484g;

    /* renamed from: h */
    protected int f6485h;

    /* renamed from: i */
    protected long f6486i;

    /* renamed from: a */
    public String m3781a() {
        return this.f6479b;
    }

    /* renamed from: b */
    public String m3780b() {
        return this.f6480c;
    }

    /* renamed from: c */
    public long m3779c() {
        return this.f6486i;
    }

    /* renamed from: d */
    public String m3778d() {
        return this.f6478a;
    }

    /* renamed from: e */
    public String m3777e() {
        return this.f6481d;
    }

    /* renamed from: f */
    public String m3776f() {
        return this.f6482e;
    }

    public SSkinInfo(KXmlParser kXmlParser) {
        this.f6478a = null;
        this.f6479b = null;
        this.f6480c = null;
        this.f6481d = null;
        this.f6482e = null;
        this.f6485h = 0;
        this.f6486i = 0L;
        this.f6478a = kXmlParser.getAttributeValue(null, "Name");
        this.f6479b = kXmlParser.getAttributeValue(null, "Author");
        this.f6480c = kXmlParser.getAttributeValue(null, "Ver");
        this.f6481d = kXmlParser.getAttributeValue(null, "EMail");
        this.f6482e = kXmlParser.getAttributeValue(null, "WebPage");
        this.f6483f = kXmlParser.getAttributeValue(null, "Background");
        this.f6484g = kXmlParser.getAttributeValue(null, "Preview");
        this.f6485h = ValueParser.m3702a(kXmlParser.getAttributeValue(null, "LoaderVer"), 0);
    }

    public SSkinInfo(SSkinInfo sSkinInfo) {
        this.f6478a = null;
        this.f6479b = null;
        this.f6480c = null;
        this.f6481d = null;
        this.f6482e = null;
        this.f6485h = 0;
        this.f6486i = 0L;
        this.f6478a = sSkinInfo.f6478a;
        this.f6479b = sSkinInfo.f6479b;
        this.f6480c = sSkinInfo.f6480c;
        this.f6481d = sSkinInfo.f6481d;
        this.f6482e = sSkinInfo.f6482e;
        this.f6485h = sSkinInfo.f6485h;
        this.f6483f = sSkinInfo.f6483f;
        this.f6486i = sSkinInfo.f6486i;
    }
}
