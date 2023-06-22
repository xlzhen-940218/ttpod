package com.sds.android.ttpod.framework.modules.skin.p129b;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.f */
/* loaded from: classes.dex */
abstract class SBase implements Serializable {

    /* renamed from: a */
    protected String f6430a;

    /* renamed from: b */
    protected String f6431b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBase() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBase(KXmlParser kXmlParser) {
        this.f6430a = kXmlParser.getAttributeValue(null, "ID");
        this.f6431b = kXmlParser.getAttributeValue(null, "Name");
    }

    /* renamed from: a */
    public String mo3794a() {
        return this.f6430a;
    }
}
