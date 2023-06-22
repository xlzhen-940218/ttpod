package com.sds.android.ttpod.component.p091g.p093b;

import android.content.Context;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;

/* renamed from: com.sds.android.ttpod.component.g.b.f */
/* loaded from: classes.dex */
public class PlayingPanelViewController extends PanelViewController {
    public PlayingPanelViewController(Context context, String str) {
        super(context, str);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b_ */
    public void mo6403b_() {
        super.mo6403b_();
        m6468d();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: r */
    public void mo6404r() {
        super.mo6404r();
        CommandCenter.m4607a().m4606a(new Command(CommandID.NOTIFY_PLAYING_PANEL_ON_SHOW, new Object[0]));
    }
}
