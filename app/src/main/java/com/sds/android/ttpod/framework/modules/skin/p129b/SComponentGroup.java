package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.k */
/* loaded from: classes.dex */
public class SComponentGroup extends SComponent<ViewGroup> implements Container<SComponent> {

    /* renamed from: c */
    private SComponent[] f6447c;

    public SComponentGroup(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public ViewGroup mo3771b(Context context, SkinCache skinCache) {
        return new SkinAbsoluteLayout(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint("ResourceType")
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo3775a(Context context, ViewGroup viewGroup, SkinCache skinCache) {
        super.mo3775a(context, viewGroup, skinCache);
        SkinAbsoluteLayout skinAbsoluteLayout = (SkinAbsoluteLayout) viewGroup;
        if (this.f6447c != null) {
            for (SComponent sComponent : this.f6447c) {
                View m3811c = sComponent.m3811c(context, skinCache);
                if (m3811c != null) {
                    int id = m3811c.getId();
                    int childCount = skinAbsoluteLayout.getChildCount() - 1;
                    while (childCount >= 0) {
                        skinAbsoluteLayout.getChildAt(childCount);
                        if (id > skinAbsoluteLayout.getChildAt(childCount).getId()) {
                            break;
                        }
                        childCount--;
                    }
                    skinAbsoluteLayout.addView(m3811c, childCount + 1);
                }
            }
        }
        if (skinAbsoluteLayout.getBackground() == null) {
            skinAbsoluteLayout.setWillNotDraw(true);
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.Container
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo3791a(SComponent[] sComponentArr) {
        this.f6447c = sComponentArr;
    }
}
