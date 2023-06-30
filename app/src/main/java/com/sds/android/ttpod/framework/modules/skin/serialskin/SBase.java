package com.sds.android.ttpod.framework.modules.skin.serialskin;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.f */
/* loaded from: classes.dex */
abstract class SBase implements Serializable {

    /* renamed from: a */
    protected String id;

    /* renamed from: b */
    protected String name;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBase() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBase(KXmlParser kXmlParser) {
        this.id = kXmlParser.getAttributeValue(null, "ID");
        this.name = kXmlParser.getAttributeValue(null, "Name");
    }

    /* renamed from: a */
    public String getId() {
        return this.id;
    }
}
