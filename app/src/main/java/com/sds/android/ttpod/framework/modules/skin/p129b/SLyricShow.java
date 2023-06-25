package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.q */
/* loaded from: classes.dex */
public class SLyricShow extends SText {

    /* renamed from: s */
    private int f6458s;

    /* renamed from: t */
    private boolean f6459t;

    /* renamed from: u */
    private boolean f6460u;

    /* renamed from: v */
    private int f6461v;

    /* renamed from: w */
    private String f6462w;

    /* renamed from: x */
    private int f6463x;

    /* renamed from: y */
    private int f6464y;

    /* renamed from: z */
    private int f6465z;

    public SLyricShow(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, HashMap<String, SFont> hashMap2, int i) {
        super(kXmlParser, hashMap, hashMap2, i);
        this.f6458s = 15;
        this.f6461v = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FadeColor"), 0);
        this.f6463x = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FontColorUp"), 0);
        this.f6464y = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FontColorUpSelected"), 0);
        this.f6465z = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "StrokeColor"), 0);
        String attributeValue = kXmlParser.getAttributeValue(null, "FadeEdge");
        if (attributeValue != null) {
            if (attributeValue.contains("Vertical")) {
                this.f6459t = true;
            }
            if (attributeValue.contains("Horizontal")) {
                this.f6460u = true;
            }
        } else {
            this.f6459t = true;
        }
        if ((this.f6498d & 3) == 3) {
            this.f6498d = 0;
        } else if ((this.f6498d & 5) == 5) {
            this.f6498d = 2;
        } else {
            this.f6498d = 1;
        }
        this.f6462w = kXmlParser.getAttributeValue(null, "Mode");
        int m3702a = ValueParser.m3702a(kXmlParser.getAttributeValue(null, "FontSizeSelected"), this.f6497c.f6451c);
        if (m3702a > 0) {
            this.f6458s = m3702a;
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: b */
    public View mo3771b(Context context, SkinCache skinCache) {
        LyricView lyricView = new LyricView(context);
        Typeface m3593a = skinCache.getTypeFace(this.f6497c);
        if (m3593a != null) {
            lyricView.setTypefaceHighlight(m3593a);
            lyricView.setTypefaceNormal(m3593a);
        }
        lyricView.setAlign(Paint.Align.values()[this.f6498d]);
        lyricView.setColorNormal(this.f6499e);
        lyricView.setDefaultColorHighlight(this.f6502l);
        lyricView.setColorHighlight(this.f6502l);
        if ((this.f6504n >> 24) != 0) {
            lyricView.m3484a(this.f6505o, this.f6506p, this.f6507q, this.f6504n);
        }
        int m3704a = ValueParser.m3704a(this.f6508r, 0);
        if (m3704a > 0) {
            lyricView.setFadeEdgeLength(m3704a);
        }
        if ("Mtv".equalsIgnoreCase(this.f6462w)) {
            lyricView.setDisplayMode(LyricView.EnumC1996a.MTV);
            if (this.f6463x != 0 && this.f6464y != 0) {
                lyricView.setColorGradientUHost(this.f6464y);
                lyricView.setColorGradientUGuest(this.f6463x);
                lyricView.setColorBySkin(true);
            } else {
                lyricView.setMtvGradient(false);
            }
            if (this.f6465z == 0) {
                lyricView.setMtvStroke(false);
            } else {
                lyricView.setColorStrokeNormal(this.f6465z);
            }
            lyricView.m3451c();
        } else if ("Single".equalsIgnoreCase(this.f6462w)) {
            lyricView.setDisplayMode(LyricView.EnumC1996a.Single);
            lyricView.m3451c();
        }
        if (this.f6497c.f6451c > 2) {
            lyricView.setTextSizeHighlight(this.f6458s);
            lyricView.setTextSizeNormal(this.f6497c.f6451c);
            lyricView.setDefaultFontSizeNormal(this.f6497c.f6451c);
            lyricView.setDefaultFontSizeHighlight(this.f6458s);
        }
        return lyricView;
    }
}
