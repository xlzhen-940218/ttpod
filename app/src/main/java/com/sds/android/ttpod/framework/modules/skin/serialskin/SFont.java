package com.sds.android.ttpod.framework.modules.skin.serialskin;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.n */
/* loaded from: classes.dex */
public class SFont extends SBase {

    /* renamed from: c */
    int size;

    /* renamed from: d */
    int style;

    /* renamed from: e */
    String familyName;

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String getId() {
        return super.getId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SFont(KXmlParser kXmlParser) {
        super(kXmlParser);
        this.size = 15;
        this.style = 0;
        this.size = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "Size"), this.size);
        this.style = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "Style"), this.style);
        this.familyName = kXmlParser.getAttributeValue(null, "FamilyName");
    }

    public SFont(String str) {
        this.size = 15;
        this.style = 0;
        this.familyName = str;
    }

    public SFont(SFont sFont) {
        this.size = 15;
        this.style = 0;
        this.familyName = sFont.familyName;
        this.style = sFont.style;
        this.size = sFont.size;
    }

    /* renamed from: b */
    public String getFamilyName() {
        return this.familyName;
    }

    /* renamed from: c */
    public int getStyle() {
        return this.style;
    }
}
