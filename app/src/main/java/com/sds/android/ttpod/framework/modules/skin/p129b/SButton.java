package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.view.TTPodButton;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.i */
/* loaded from: classes.dex */
public class SButton extends SImage<TTPodButton> {
    public SButton(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public TTPodButton mo3771b(Context context, SkinCache skinCache) {
        return new TTPodButton(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void setBackground(Context context, TTPodButton tTPodButton, SkinCache skinCache) {
        super.setBackground(context,tTPodButton, skinCache);
        tTPodButton.setImageDrawable(skinCache.m3596a(context.getResources(), this.icon));
        tTPodButton.setScaleType(getScaleType(this.scaleType));
    }
}
