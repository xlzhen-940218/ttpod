package com.sds.android.ttpod.framework.modules.skin.p129b;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.EventCompiler;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.r */
/* loaded from: classes.dex */
public final class SMotion extends SBase {

    /* renamed from: c */
    String[] f6466c;

    /* renamed from: d */
    int[] f6467d;

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String getId() {
        return super.getId();
    }

    public SMotion(KXmlParser kXmlParser) throws Exception {
        super(kXmlParser);
        String attributeValue = kXmlParser.getAttributeValue(null, "Component");
        if (attributeValue != null) {
            this.f6466c = attributeValue.split("\\|");
            this.f6467d = EventCompiler.m3742a(kXmlParser.getAttributeValue(null, "Motion"));
        }
    }

    /* renamed from: b */
    public String[] m3796b() {
        return this.f6466c;
    }

    /* renamed from: c */
    public int[] m3795c() {
        return this.f6467d;
    }
}
