package com.sds.android.ttpod.framework.modules.skin.p129b;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.x */
/* loaded from: classes.dex */
public class SSkinInfo implements Serializable {

    /* renamed from: a */
    protected String name;

    /* renamed from: b */
    protected String author;

    /* renamed from: c */
    protected String version;

    /* renamed from: d */
    protected String email;

    /* renamed from: e */
    protected String webpage;

    /* renamed from: f */
    protected String background;

    /* renamed from: g */
    protected String preview;

    /* renamed from: h */
    protected int loaderVersion;

    /* renamed from: i */
    protected long f6486i;

    /* renamed from: a */
    public String m3781a() {
        return this.author;
    }

    /* renamed from: b */
    public String m3780b() {
        return this.version;
    }

    /* renamed from: c */
    public long m3779c() {
        return this.f6486i;
    }

    /* renamed from: d */
    public String m3778d() {
        return this.name;
    }

    /* renamed from: e */
    public String m3777e() {
        return this.email;
    }

    /* renamed from: f */
    public String m3776f() {
        return this.webpage;
    }

    public SSkinInfo(KXmlParser kXmlParser) {
        this.name = null;
        this.author = null;
        this.version = null;
        this.email = null;
        this.webpage = null;
        this.loaderVersion = 0;
        this.f6486i = 0L;
        this.name = kXmlParser.getAttributeValue(null, "Name");
        this.author = kXmlParser.getAttributeValue(null, "Author");
        this.version = kXmlParser.getAttributeValue(null, "Ver");
        this.email = kXmlParser.getAttributeValue(null, "EMail");
        this.webpage = kXmlParser.getAttributeValue(null, "WebPage");
        this.background = kXmlParser.getAttributeValue(null, "Background");
        this.preview = kXmlParser.getAttributeValue(null, "Preview");
        this.loaderVersion = ValueParser.m3702a(kXmlParser.getAttributeValue(null, "LoaderVer"), 0);
    }

    public SSkinInfo(SSkinInfo sSkinInfo) {
        this.name = null;
        this.author = null;
        this.version = null;
        this.email = null;
        this.webpage = null;
        this.loaderVersion = 0;
        this.f6486i = 0L;
        this.name = sSkinInfo.name;
        this.author = sSkinInfo.author;
        this.version = sSkinInfo.version;
        this.email = sSkinInfo.email;
        this.webpage = sSkinInfo.webpage;
        this.loaderVersion = sSkinInfo.loaderVersion;
        this.background = sSkinInfo.background;
        this.f6486i = sSkinInfo.f6486i;
    }
}
