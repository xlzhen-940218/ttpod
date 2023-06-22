package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.SkinLayoutParams;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.LineVisualization;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.c */
/* loaded from: classes.dex */
public class SAnalyzer extends SComponent<LineVisualization> {

    /* renamed from: c */
    private SBitmap f6409c;

    /* renamed from: d */
    private SBitmap f6410d;

    /* renamed from: e */
    private int f6411e;

    /* renamed from: j */
    private int f6412j;

    /* renamed from: k */
    private int f6413k;

    /* renamed from: l */
    private int f6414l;

    /* renamed from: m */
    private int f6415m;

    public SAnalyzer(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.f6409c = m3813a(hashMap, kXmlParser, "LineIcon");
        this.f6410d = m3813a(hashMap, kXmlParser, "DotIcon");
        this.f6411e = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "BarColorBot"), -7829368);
        this.f6412j = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "BarColorTop"), -1);
        this.f6413k = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "BarColorSpire"), 47871);
        this.f6414l = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "ReflectionMaskStartColor"), 553648127);
        this.f6415m = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "ReflectionMaskEndColor"), 1895825407);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    protected SkinLayoutParams mo3772a(KXmlParser kXmlParser, int i) {
        return new C1979a(kXmlParser, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public LineVisualization mo3771b(Context context, SkinCache skinCache) {
        return new LineVisualization(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo3775a(Context context, LineVisualization lineVisualization, SkinCache skinCache) {
        super.mo3775a(context,  lineVisualization, skinCache);
        lineVisualization.m3355a(this.f6414l, this.f6415m);
        Resources resources = context.getResources();
        if (this.f6409c != null) {
            lineVisualization.setLineDrawable(skinCache.m3596a(resources, this.f6409c));
        } else {
            lineVisualization.setLineDrawable(this.f6412j == this.f6411e ? new ColorDrawable(this.f6412j) : new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{this.f6412j, this.f6411e}));
        }
        if (this.f6410d != null) {
            lineVisualization.setDotDrawable(skinCache.m3596a(resources, this.f6410d));
        } else {
            lineVisualization.setDotDrawable(new ColorDrawable(this.f6413k));
        }
    }

    /* compiled from: SAnalyzer.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.b.c$a */
    /* loaded from: classes.dex */
    public static class C1979a extends SkinLayoutParams {

        /* renamed from: a */
        private int f6416a;

        /* renamed from: b */
        private int f6417b;

        /* renamed from: c */
        private int f6418c;

        /* renamed from: d */
        private int f6419d;

        /* renamed from: e */
        private int f6420e;

        public C1979a(KXmlParser kXmlParser, int i) {
            super(kXmlParser, i);
            this.f6419d = ValueParser.m3696b(kXmlParser.getAttributeValue(null, "LineWidth"), -1);
            this.f6420e = ValueParser.m3696b(kXmlParser.getAttributeValue(null, "DotHeight"), -1);
            this.f6418c = ValueParser.m3696b(kXmlParser.getAttributeValue(null, "LineDivideWidth"), 2);
            this.f6416a = ValueParser.m3696b(kXmlParser.getAttributeValue(null, "ReflectionHeight"), -1);
            this.f6417b = ValueParser.m3696b(kXmlParser.getAttributeValue(null, "DivideHeight"), 2);
        }

        /* renamed from: f */
        public int m3837f(int i) {
            return this.f6416a == -1 ? ValueParser.m3704a(i, i) / 3 : ValueParser.m3704a(this.f6416a, i);
        }

        /* renamed from: g */
        public int m3836g(int i) {
            return ValueParser.m3704a(this.f6417b, i);
        }

        /* renamed from: h */
        public int m3835h(int i) {
            return ValueParser.m3704a(this.f6418c, i);
        }

        /* renamed from: i */
        public int m3834i(int i) {
            return this.f6419d == -1 ? ValueParser.m3704a(i, i) / 128 : ValueParser.m3704a(this.f6419d, i);
        }

        /* renamed from: j */
        public int m3833j(int i) {
            return ValueParser.m3704a(this.f6420e, i);
        }
    }
}
