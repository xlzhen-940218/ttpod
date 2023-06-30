package com.sds.android.ttpod.framework.modules.skin.serialskin;

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
    private SBitmap lineIcon;

    /* renamed from: d */
    private SBitmap dotIcon;

    /* renamed from: e */
    private int barColorBot;

    /* renamed from: j */
    private int barColorTop;

    /* renamed from: k */
    private int barColorSpire;

    /* renamed from: l */
    private int reflectionMaskStartColor;

    /* renamed from: m */
    private int reflectionMaskEndColor;

    public SAnalyzer(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.lineIcon = getSBitmap(hashMap, kXmlParser, "LineIcon");
        this.dotIcon = getSBitmap(hashMap, kXmlParser, "DotIcon");
        this.barColorBot = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "BarColorBot"), -7829368);
        this.barColorTop = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "BarColorTop"), -1);
        this.barColorSpire = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "BarColorSpire"), 47871);
        this.reflectionMaskStartColor = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "ReflectionMaskStartColor"), 553648127);
        this.reflectionMaskEndColor = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "ReflectionMaskEndColor"), 1895825407);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    protected SkinLayoutParams mo3772a(KXmlParser kXmlParser, int i) {
        return new SAnalyzerLayoutParams(kXmlParser, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public LineVisualization getIcon(Context context, SkinCache skinCache) {
        return new LineVisualization(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void setBackground(Context context, LineVisualization lineVisualization, SkinCache skinCache) {
        super.setBackground(context,  lineVisualization, skinCache);
        lineVisualization.setReflectionMaskColors(this.reflectionMaskStartColor, this.reflectionMaskEndColor);
        Resources resources = context.getResources();
        if (this.lineIcon != null) {
            lineVisualization.setLineDrawable(skinCache.getDrawable(resources, this.lineIcon));
        } else {
            lineVisualization.setLineDrawable(this.barColorTop == this.barColorBot ? new ColorDrawable(this.barColorTop) : new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{this.barColorTop, this.barColorBot}));
        }
        if (this.dotIcon != null) {
            lineVisualization.setDotDrawable(skinCache.getDrawable(resources, this.dotIcon));
        } else {
            lineVisualization.setDotDrawable(new ColorDrawable(this.barColorSpire));
        }
    }

    /* compiled from: SAnalyzer.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.b.c$a */
    /* loaded from: classes.dex */
    public static class SAnalyzerLayoutParams extends SkinLayoutParams {

        /* renamed from: a */
        private int reflectionHeight;

        /* renamed from: b */
        private int divideHeight;

        /* renamed from: c */
        private int lineDivideWidth;

        /* renamed from: d */
        private int lineWidth;

        /* renamed from: e */
        private int dotHeight;

        public SAnalyzerLayoutParams(KXmlParser kXmlParser, int i) {
            super(kXmlParser, i);
            this.lineWidth = ValueParser.parseCommon(kXmlParser.getAttributeValue(null, "LineWidth"), -1);
            this.dotHeight = ValueParser.parseCommon(kXmlParser.getAttributeValue(null, "DotHeight"), -1);
            this.lineDivideWidth = ValueParser.parseCommon(kXmlParser.getAttributeValue(null, "LineDivideWidth"), 2);
            this.reflectionHeight = ValueParser.parseCommon(kXmlParser.getAttributeValue(null, "ReflectionHeight"), -1);
            this.divideHeight = ValueParser.parseCommon(kXmlParser.getAttributeValue(null, "DivideHeight"), 2);
        }

        /* renamed from: f */
        public int getReflectionHeight(int i) {
            return this.reflectionHeight == -1 ? ValueParser.getSize(i, i) / 3 : ValueParser.getSize(this.reflectionHeight, i);
        }

        /* renamed from: g */
        public int getDivideHeight(int i) {
            return ValueParser.getSize(this.divideHeight, i);
        }

        /* renamed from: h */
        public int getLineDivideWidth(int i) {
            return ValueParser.getSize(this.lineDivideWidth, i);
        }

        /* renamed from: i */
        public int getLineWidth(int i) {
            return this.lineWidth == -1 ? ValueParser.getSize(i, i) / 128 : ValueParser.getSize(this.lineWidth, i);
        }

        /* renamed from: j */
        public int getDotHeight(int i) {
            return ValueParser.getSize(this.dotHeight, i);
        }
    }
}
