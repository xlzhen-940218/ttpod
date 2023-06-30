package com.sds.android.ttpod.framework.modules.skin.serialskin;

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
    protected long dateCreated;

    /* renamed from: a */
    public String getAuthor() {
        return this.author;
    }

    /* renamed from: b */
    public String getVersion() {
        return this.version;
    }

    /* renamed from: c */
    public long getDateCreated() {
        return this.dateCreated;
    }

    /* renamed from: d */
    public String getName() {
        return this.name;
    }

    /* renamed from: e */
    public String getEmail() {
        return this.email;
    }

    /* renamed from: f */
    public String getWebPage() {
        return this.webpage;
    }

    public SSkinInfo(KXmlParser kXmlParser) {
        this.name = null;
        this.author = null;
        this.version = null;
        this.email = null;
        this.webpage = null;
        this.loaderVersion = 0;
        this.dateCreated = 0L;
        this.name = kXmlParser.getAttributeValue(null, "Name");
        this.author = kXmlParser.getAttributeValue(null, "Author");
        this.version = kXmlParser.getAttributeValue(null, "Ver");
        this.email = kXmlParser.getAttributeValue(null, "EMail");
        this.webpage = kXmlParser.getAttributeValue(null, "WebPage");
        this.background = kXmlParser.getAttributeValue(null, "Background");
        this.preview = kXmlParser.getAttributeValue(null, "Preview");
        this.loaderVersion = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "LoaderVer"), 0);
    }

    public SSkinInfo(SSkinInfo sSkinInfo) {
        this.name = null;
        this.author = null;
        this.version = null;
        this.email = null;
        this.webpage = null;
        this.loaderVersion = 0;
        this.dateCreated = 0L;
        this.name = sSkinInfo.name;
        this.author = sSkinInfo.author;
        this.version = sSkinInfo.version;
        this.email = sSkinInfo.email;
        this.webpage = sSkinInfo.webpage;
        this.loaderVersion = sSkinInfo.loaderVersion;
        this.background = sSkinInfo.background;
        this.dateCreated = sSkinInfo.dateCreated;
    }
}
