package com.sds.android.ttpod.framework.modules.skin.p129b;

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
    private SBitmap f6487c;

    /* renamed from: d */
    private SBitmap f6488d;

    /* renamed from: e */
    private SBitmap f6489e;

    /* renamed from: j */
    private SBitmap f6490j;

    /* renamed from: k */
    private int f6491k;

    public SSlide(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.f6487c = m3813a(hashMap, kXmlParser, "KnobIcon");
        this.f6488d = m3813a(hashMap, kXmlParser, "SlideIcon");
        this.f6489e = m3813a(hashMap, kXmlParser, "SlideBackground");
        if (this.f6489e == null) {
            this.f6489e = m3813a(hashMap, kXmlParser, "SlideBg");
        }
        this.f6490j = m3813a(hashMap, kXmlParser, "SlideSecondaryIcon");
        String attributeValue = kXmlParser.getAttributeValue(null, "Style");
        if ("Vertical".equals(attributeValue)) {
            this.f6491k = 1;
        } else if ("Horizontal".equals(attributeValue)) {
            this.f6491k = 0;
        } else {
            this.f6491k = ValueParser.m3702a(attributeValue, 0);
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    protected SkinLayoutParams mo3772a(KXmlParser kXmlParser, int i) {
        return new C1981a(kXmlParser, i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public SeekBarExpansion mo3771b(Context context, SkinCache skinCache) {
        return new SeekBarExpansion(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo3775a(Context context, SeekBarExpansion seekBarExpansion, SkinCache skinCache) {
        Resources resources = context.getResources();
        Drawable colorDrawable = new ColorDrawable(0);
        Drawable m3596a = skinCache.m3596a(resources, this.f6487c);
        if (m3596a == null) {
            m3596a = colorDrawable;
        }
        seekBarExpansion.setThumb(m3596a);
        Drawable m3596a2 = skinCache.m3596a(resources, this.f6489e);
        if (m3596a2 == null) {
            m3596a2 = colorDrawable;
        }
        Drawable m3596a3 = skinCache.m3596a(resources, this.f6488d);
        Drawable clipDrawable = m3596a3 != null ? new ClipDrawable(m3596a3, 19, 1) : colorDrawable;
        Drawable m3596a4 = skinCache.m3596a(resources, this.f6490j);
        if (m3596a4 != null) {
            colorDrawable = new ClipDrawable(m3596a4, 19, 1);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{m3596a2, clipDrawable, colorDrawable});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908301);
        layerDrawable.setId(2, 16908303);
        seekBarExpansion.setProgressDrawable(layerDrawable);
        seekBarExpansion.setOrientation(this.f6491k);
        super.mo3775a(context, seekBarExpansion, skinCache);
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
            Rect m3701a = ValueParser.m3701a(kXmlParser.getAttributeValue(null, "SlidePadding"), (Rect) null);
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
            view.setPadding(view.getPaddingLeft() + ValueParser.m3704a(this.f6493b, d), view.getPaddingTop() + ValueParser.m3704a(this.f6494c, e), ValueParser.m3704a(this.f6495d, d) + view.getPaddingRight(), ValueParser.m3704a(this.f6496e, e) + view.getPaddingBottom());
        }
    }
}
