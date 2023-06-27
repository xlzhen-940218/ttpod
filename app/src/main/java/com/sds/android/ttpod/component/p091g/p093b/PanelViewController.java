package com.sds.android.ttpod.component.p091g.p093b;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;

/* renamed from: com.sds.android.ttpod.component.g.b.d */
/* loaded from: classes.dex */
public class PanelViewController extends ViewEventController {

    /* renamed from: a */
    private PlayerPortraitViewController f4290a;

    public PanelViewController(Context context, String str) {
        super(context, str);
    }

    /* renamed from: a */
    public void m6470a(PlayerPortraitViewController playerPortraitViewController) {
        this.f4290a = playerPortraitViewController;
    }

    /* renamed from: c */
    public PlayerPortraitViewController m6469c() {
        return this.f4290a;
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public View mo6445a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.lastIndexOf(46) > 0) {
            if (this.f4290a == null) {
                return null;
            }
            return this.f4290a.mo6445a(str);
        }
        return super.mo6445a(str);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController
    /* renamed from: b */
    public void mo6434b(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.lastIndexOf(46) > 0) {
                if (this.f4290a != null) {
                    this.f4290a.mo6434b(str);
                    return;
                }
                return;
            }
            super.mo6434b(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public void m6468d() {
        if (this.lyricView != null && !this.lyricView.m3462b()) {
            this.lyricView.setDisplayMode(LyricView.LyricDisplayEnum.Single);
            this.lyricView.setSlowScroll(false);
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController
    /* renamed from: e */
    public int mo6467e() {
        if (this.f4290a == null) {
            return 0;
        }
        return this.f4290a.mo6467e();
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController
    /* renamed from: f */
    public int mo6466f() {
        if (this.f4290a == null) {
            return 0;
        }
        return this.f4290a.mo6466f();
    }
}
