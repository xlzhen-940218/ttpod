package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.u */
/* loaded from: classes.dex */
public class SPlaylistView extends SComponentView {

    /* renamed from: j */
    private int f6474j;

    /* renamed from: k */
    private int f6475k;

    public SPlaylistView(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.f6474j = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "AnimationInStyle"), 0);
        this.f6475k = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "AnimationOutStyle"), 0);
    }

    /* renamed from: e */
    public int m3785e() {
        return this.f6475k;
    }

    /* renamed from: f */
    public int m3784f() {
        return this.f6474j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponentView, com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public ViewGroup mo3771b(Context context, SkinCache skinCache) {
        return new SkinAbsoluteLayout(context);
    }
}
