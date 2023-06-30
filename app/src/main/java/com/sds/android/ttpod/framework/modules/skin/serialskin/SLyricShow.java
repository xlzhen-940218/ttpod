package com.sds.android.ttpod.framework.modules.skin.serialskin;

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
    private int fontSizeSelected;

    /* renamed from: t */
    private boolean vertical;

    /* renamed from: u */
    private boolean horizontal;

    /* renamed from: v */
    private int fadeColor;

    /* renamed from: w */
    private String mode;

    /* renamed from: x */
    private int fontColorUp;

    /* renamed from: y */
    private int fontColorUpSelected;

    /* renamed from: z */
    private int strokeColor;

    public SLyricShow(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, HashMap<String, SFont> hashMap2, int i) {
        super(kXmlParser, hashMap, hashMap2, i);
        this.fontSizeSelected = 15;
        this.fadeColor = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FadeColor"), 0);
        this.fontColorUp = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FontColorUp"), 0);
        this.fontColorUpSelected = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FontColorUpSelected"), 0);
        this.strokeColor = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "StrokeColor"), 0);
        String fadeEdge = kXmlParser.getAttributeValue(null, "FadeEdge");
        if (fadeEdge != null) {
            if (fadeEdge.contains("Vertical")) {
                this.vertical = true;
            }
            if (fadeEdge.contains("Horizontal")) {
                this.horizontal = true;
            }
        } else {
            this.vertical = true;
        }
        if ((this.align & 3) == 3) {
            this.align = 0;
        } else if ((this.align & 5) == 5) {
            this.align = 2;
        } else {
            this.align = 1;
        }
        this.mode = kXmlParser.getAttributeValue(null, "Mode");
        int fontSizeSelected = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "FontSizeSelected"), this.font.size);
        if (fontSizeSelected > 0) {
            this.fontSizeSelected = fontSizeSelected;
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: b */
    public View getIcon(Context context, SkinCache skinCache) {
        LyricView lyricView = new LyricView(context);
        Typeface m3593a = skinCache.getTypeFace(this.font);
        if (m3593a != null) {
            lyricView.setTypefaceHighlight(m3593a);
            lyricView.setTypefaceNormal(m3593a);
        }
        lyricView.setAlign(Paint.Align.values()[this.align]);
        lyricView.setColorNormal(this.fontColor);
        lyricView.setDefaultColorHighlight(this.fontColorSelected);
        lyricView.setColorHighlight(this.fontColorSelected);
        if ((this.fontShadowColor >> 24) != 0) {
            lyricView.setShadowLayer(this.fontShaowRadius, this.fontShaowDx, this.fontShaowDy, this.fontShadowColor);
        }
        int m3704a = ValueParser.getSize(this.fadeEdgeLength, 0);
        if (m3704a > 0) {
            lyricView.setFadeEdgeLength(m3704a);
        }
        if ("Mtv".equalsIgnoreCase(this.mode)) {
            lyricView.setDisplayMode(LyricView.LyricDisplayEnum.MTV);
            if (this.fontColorUp != 0 && this.fontColorUpSelected != 0) {
                lyricView.setColorGradientUHost(this.fontColorUpSelected);
                lyricView.setColorGradientUGuest(this.fontColorUp);
                lyricView.setColorBySkin(true);
            } else {
                lyricView.setMtvGradient(false);
            }
            if (this.strokeColor == 0) {
                lyricView.setMtvStroke(false);
            } else {
                lyricView.setColorStrokeNormal(this.strokeColor);
            }
            lyricView.setMtvOrSingle();
        } else if ("Single".equalsIgnoreCase(this.mode)) {
            lyricView.setDisplayMode(LyricView.LyricDisplayEnum.Single);
            lyricView.setMtvOrSingle();
        }
        if (this.font.size > 2) {
            lyricView.setTextSizeHighlight(this.fontSizeSelected);
            lyricView.setTextSizeNormal(this.font.size);
            lyricView.setDefaultFontSizeNormal(this.font.size);
            lyricView.setDefaultFontSizeHighlight(this.fontSizeSelected);
        }
        return lyricView;
    }
}
