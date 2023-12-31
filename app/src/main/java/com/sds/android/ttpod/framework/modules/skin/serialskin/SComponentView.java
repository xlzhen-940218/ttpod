package com.sds.android.ttpod.framework.modules.skin.serialskin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.l */
/* loaded from: classes.dex */
public abstract class SComponentView extends SBaseView<ViewGroup, SComponent> implements HasEvent {

    /* renamed from: j */
    private SEvent[] sEvents;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public abstract ViewGroup getIcon(Context context, SkinCache skinCache);

    public SComponentView(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint("ResourceType")
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBaseView, com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void setBackground(Context context, ViewGroup viewGroup, SkinCache skinCache) {
        super.setBackground(context, viewGroup, skinCache);
        if (this.skinViews != null) {
            for (SComponent sComponent : (SComponent[]) this.skinViews) {
                View m3811c = sComponent.m3811c(context, skinCache);
                if (m3811c != null) {
                    int id = m3811c.getId();
                    int childCount = viewGroup.getChildCount() - 1;
                    while (childCount >= 0) {
                        viewGroup.getChildAt(childCount);
                        if (id > viewGroup.getChildAt(childCount).getId()) {
                            break;
                        }
                        childCount--;
                    }
                    viewGroup.addView(m3811c, childCount + 1);
                }
            }
        }
    }

    /* renamed from: d */
    public SEvent[] getSEvents() {
        return this.sEvents;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.HasEvent
    /* renamed from: a */
    public void setSEvents(SEvent[] sEvents) {
        this.sEvents = sEvents;
    }
}
