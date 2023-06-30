package com.sds.android.ttpod.framework.modules.skin.serialskin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.SkinLayoutParams;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.SeekBarExpansion;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.y */
/* loaded from: classes.dex */
public class SSlide extends SComponent<SeekBarExpansion> {

    /* renamed from: c */
    private SBitmap knobIcon;

    /* renamed from: d */
    private SBitmap slideIcon;

    /* renamed from: e */
    private SBitmap slideBackground;

    /* renamed from: j */
    private SBitmap slideSecondaryIcon;

    /* renamed from: k */
    private int orientation;

    public SSlide(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.knobIcon = getSBitmap(hashMap, kXmlParser, "KnobIcon");
        this.slideIcon = getSBitmap(hashMap, kXmlParser, "SlideIcon");
        this.slideBackground = getSBitmap(hashMap, kXmlParser, "SlideBackground");
        if (this.slideBackground == null) {
            this.slideBackground = getSBitmap(hashMap, kXmlParser, "SlideBg");
        }
        this.slideSecondaryIcon = getSBitmap(hashMap, kXmlParser, "SlideSecondaryIcon");
        String style = kXmlParser.getAttributeValue(null, "Style");
        if ("Vertical".equals(style)) {
            this.orientation = 1;
        } else if ("Horizontal".equals(style)) {
            this.orientation = 0;
        } else {
            this.orientation = ValueParser.parseInt(style, 0);
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    protected SkinLayoutParams mo3772a(KXmlParser kXmlParser, int i) {
        return new C1981a(kXmlParser, i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public SeekBarExpansion getIcon(Context context, SkinCache skinCache) {
        return new SeekBarExpansion(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void setBackground(Context context, SeekBarExpansion seekBarExpansion, SkinCache skinCache) {
        Resources resources = context.getResources();
        Drawable colorDrawable = new ColorDrawable(0);
        Drawable m3596a = skinCache.getDrawable(resources, this.knobIcon);
        if (m3596a == null) {
            m3596a = colorDrawable;
        }
        seekBarExpansion.setThumb(m3596a);
        Drawable m3596a2 = skinCache.getDrawable(resources, this.slideBackground);
        if (m3596a2 == null) {
            m3596a2 = colorDrawable;
        }
        Drawable m3596a3 = skinCache.getDrawable(resources, this.slideIcon);
        Drawable clipDrawable = m3596a3 != null ? new ClipDrawable(m3596a3, 19, 1) : colorDrawable;
        Drawable m3596a4 = skinCache.getDrawable(resources, this.slideSecondaryIcon);
        if (m3596a4 != null) {
            colorDrawable = new ClipDrawable(m3596a4, 19, 1);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{m3596a2, clipDrawable, colorDrawable});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908301);
        layerDrawable.setId(2, 16908303);
        seekBarExpansion.setProgressDrawable(layerDrawable);
        seekBarExpansion.setOrientation(this.orientation);
        super.setBackground(context, seekBarExpansion, skinCache);
    }

    /* compiled from: SSlide.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.b.y$a */
    /* loaded from: classes.dex */
    private class C1981a extends SkinLayoutParams {

        /* renamed from: b */
        private int f6493b;

        /* renamed from: c */
        private int f6494c;

        /* renamed from: d */
        private int f6495d;

        /* renamed from: e */
        private int f6496e;

        public C1981a(KXmlParser kXmlParser, int i) {
            super(kXmlParser, i);
            Rect m3701a = ValueParser.xmlToRect(kXmlParser.getAttributeValue(null, "SlidePadding"), (Rect) null);
            if (m3701a != null) {
                this.f6493b = m3701a.left;
                this.f6496e = m3701a.bottom;
                this.f6495d = m3701a.right;
                this.f6494c = m3701a.top;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.framework.modules.skin.SkinLayoutParams
        /* renamed from: a */
        public void mo3558a(View view, int i, int i2) {
            super.mo3558a(view, i, i2);
            int d = m3551d() - m3556b();
            int e = m3549e() - m3553c();
            view.setPadding(view.getPaddingLeft() + ValueParser.getSize(this.f6493b, d), view.getPaddingTop() + ValueParser.getSize(this.f6494c, e), ValueParser.getSize(this.f6495d, d) + view.getPaddingRight(), ValueParser.getSize(this.f6496e, e) + view.getPaddingBottom());
        }
    }
}
