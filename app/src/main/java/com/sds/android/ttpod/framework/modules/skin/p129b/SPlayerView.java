package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.t */
/* loaded from: classes.dex */
public class SPlayerView extends SBaseView<MultiScreenLayout, SPanel> {

    /* renamed from: j */
    private boolean f6470j;

    /* renamed from: k */
    private boolean f6471k;

    /* renamed from: l */
    private boolean f6472l;

    /* renamed from: m */
    private int f6473m;

    public SPlayerView(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.f6470j = true;
        this.f6471k = false;
        this.f6472l = false;
        this.f6473m = 0;
        this.f6470j = ValueParser.m3698a(kXmlParser.getAttributeValue(null, "EnableBounce"), this.f6470j);
        this.f6471k = ValueParser.m3698a(kXmlParser.getAttributeValue(null, "EnableArtistBackground"), this.f6471k);
        this.f6472l = ValueParser.m3698a(kXmlParser.getAttributeValue(null, "EnableBackgroundOffset"), this.f6472l);
        this.f6473m = ValueParser.m3702a(kXmlParser.getAttributeValue(null, "ArtistBackgroundRadius"), this.f6473m);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public MultiScreenLayout mo3771b(Context context, SkinCache skinCache) {
        return new MultiScreenLayout(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBaseView, com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void mo3775a(Context context, MultiScreenLayout multiScreenLayout, SkinCache skinCache) {
        multiScreenLayout.setTag(this.id);
        multiScreenLayout.setEnableBackgroundOffset(this.f6472l);
        multiScreenLayout.setEnableBoundaryBounce(this.f6470j);
        multiScreenLayout.setEnableSecondBackground(this.f6471k);
        multiScreenLayout.setSecondBackgroundBlurRadius(this.f6473m);
        Drawable d = m3810d(context, skinCache);
        multiScreenLayout.setBackgroundDrawable(d);
        if (d != null) {
            if (!(d instanceof ColorDrawable)) {
                multiScreenLayout.setDrawingCacheBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                return;
            }
            return;
        }
        multiScreenLayout.setWillNotDraw(true);
    }
}
