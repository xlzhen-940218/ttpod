package com.sds.android.ttpod.framework.modules.skin.p129b;

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
    public /* bridge */ /* synthetic */ String mo3794a() {
        return super.mo3794a();
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
    public void mo3791a(SComponent[] sComponentArr) {
        this.f6468c = sComponentArr;
    }

    /* renamed from: c */
    public SEvent[] m3789c() {
        return this.f6469d;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.HasEvent
    /* renamed from: a */
    public void mo3792a(SEvent[] sEventArr) {
        this.f6469d = sEventArr;
    }
}
