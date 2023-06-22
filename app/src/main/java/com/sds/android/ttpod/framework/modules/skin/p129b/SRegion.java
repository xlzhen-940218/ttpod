package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.v */
/* loaded from: classes.dex */
public class SRegion extends SComponent {
    public SRegion(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: b */
    View mo3771b(Context context, SkinCache skinCache) {
        return new FrameLayout(context);
    }
}
