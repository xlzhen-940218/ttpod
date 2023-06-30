package com.sds.android.ttpod.component.p091g.p093b;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.component.p091g.p092a.EventHandler;
import com.sds.android.ttpod.component.p091g.p093b.p094a.BasePlayerViewController;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.serialskin.SComponentView;
import com.sds.android.ttpod.framework.modules.skin.serialskin.SEvent;
import com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout;

/* renamed from: com.sds.android.ttpod.component.g.b.a */
/* loaded from: classes.dex */
public class AbsolutePlayerViewController extends BasePlayerViewController {

    /* renamed from: a */
    private ViewGroup f4177a;

    public AbsolutePlayerViewController(Context context, SkinCache skinCache, SComponentView sComponentView) {
        super(context, null);
        m6561a(context, skinCache, sComponentView);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.BasePlayerViewController
    /* renamed from: a */
    public View getMultiScreenLayout() {
        return this.f4177a;
    }

    /* renamed from: a */
    private void m6561a(Context context, SkinCache skinCache, SComponentView sComponentView) {
        if (skinCache == null) {
            throw new IllegalArgumentException("illegal SkinCache");
        }
        skinCache.m3581g();
        if (sComponentView != null) {
            SkinAbsoluteLayout skinAbsoluteLayout = (SkinAbsoluteLayout) sComponentView.m3811c(context, skinCache);
            m6552c(sComponentView.getFullScreen());
            for (int childCount = skinAbsoluteLayout.getChildCount() - 1; childCount >= 0; childCount--) {
                m6529c(skinAbsoluteLayout.getChildAt(childCount));
            }
            SEvent[] m3805d = sComponentView.getSEvents();
            if (m3805d != null) {
                EventHandler eventHandler = new EventHandler(Looper.myLooper());
                for (SEvent sEvent : m3805d) {
                    m6517a(sEvent.m3803b(), eventHandler.m6562a(sEvent));
                }
            }
            m6560a((ViewGroup) skinAbsoluteLayout, false);
        }
        skinCache.handleClose();
    }

    /* renamed from: a */
    private void m6560a(ViewGroup viewGroup, boolean z) {
        if (viewGroup != null) {
            if (viewGroup != this.f4177a || z) {
                this.f4177a = viewGroup;
                m6545H();
                mo6403b_();
            }
        }
    }
}
