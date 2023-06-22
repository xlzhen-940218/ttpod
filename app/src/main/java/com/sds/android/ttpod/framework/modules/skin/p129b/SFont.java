package com.sds.android.ttpod.framework.modules.skin.p129b;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.n */
/* loaded from: classes.dex */
public class SFont extends SBase {

    /* renamed from: c */
    int f6451c;

    /* renamed from: d */
    int f6452d;

    /* renamed from: e */
    String f6453e;

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String mo3794a() {
        return super.mo3794a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SFont(KXmlParser kXmlParser) {
        super(kXmlParser);
        this.f6451c = 15;
        this.f6452d = 0;
        this.f6451c = ValueParser.m3702a(kXmlParser.getAttributeValue(null, "Size"), this.f6451c);
        this.f6452d = ValueParser.m3702a(kXmlParser.getAttributeValue(null, "Style"), this.f6452d);
        this.f6453e = kXmlParser.getAttributeValue(null, "FamilyName");
    }

    public SFont(String str) {
        this.f6451c = 15;
        this.f6452d = 0;
        this.f6453e = str;
    }

    public SFont(SFont sFont) {
        this.f6451c = 15;
        this.f6452d = 0;
        this.f6453e = sFont.f6453e;
        this.f6452d = sFont.f6452d;
        this.f6451c = sFont.f6451c;
    }

    /* renamed from: b */
    public String m3801b() {
        return this.f6453e;
    }

    /* renamed from: c */
    public int m3800c() {
        return this.f6452d;
    }
}
