package com.sds.android.ttpod.framework.modules.skin.serialskin;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.m */
/* loaded from: classes.dex */
public final class SEvent extends SBase implements Container<SMotion> {

    /* renamed from: c */
    String[] f6449c;

    /* renamed from: d */
    SMotion[] f6450d;

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String getId() {
        return super.getId();
    }

    public SEvent(KXmlParser kXmlParser) throws Exception {
        super(kXmlParser);
        if (this.id != null) {
            this.f6449c = this.id.split("\\|");
        }
    }

    /* renamed from: b */
    public String[] m3803b() {
        return this.f6449c;
    }

    /* renamed from: c */
    public SMotion[] m3802c() {
        return this.f6450d;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.Container
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void setSkinViews(SMotion[] sMotionArr) {
        this.f6450d = sMotionArr;
    }
}
