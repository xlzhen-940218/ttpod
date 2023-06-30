package com.sds.android.ttpod.framework.modules.skin.serialskin;

import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.s */
/* loaded from: classes.dex */
public class SPanel extends SBase implements Container<SComponent>, HasEvent {

    /* renamed from: c */
    private SComponent[] f6468c;

    /* renamed from: d */
    private SEvent[] f6469d;

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String getId() {
        return super.getId();
    }

    public SPanel(KXmlParser kXmlParser) {
        super(kXmlParser);
    }

    /* renamed from: b */
    public SComponent[] m3790b() {
        return this.f6468c;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.Container
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void setSkinViews(SComponent[] sComponentArr) {
        this.f6468c = sComponentArr;
    }

    /* renamed from: c */
    public SEvent[] m3789c() {
        return this.f6469d;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.HasEvent
    /* renamed from: a */
    public void setSEvents(SEvent[] sEventArr) {
        this.f6469d = sEventArr;
    }
}
