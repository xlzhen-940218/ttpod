package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.ViewWrapper;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.z */
/* loaded from: classes.dex */
public abstract class SText<T extends View> extends SComponent<T> {

    /* renamed from: c */
    protected SFont font;

    /* renamed from: d */
    protected int align;

    /* renamed from: e */
    protected int fontColor;

    /* renamed from: j */
    protected int fontColorPressed;

    /* renamed from: k */
    protected int fontColorDisable;

    /* renamed from: l */
    protected int fontColorSelected;

    /* renamed from: m */
    protected int fontColorFocused;

    /* renamed from: n */
    protected int fontShadowColor;

    /* renamed from: o */
    protected float fontShaowRadius;

    /* renamed from: p */
    protected float fontShaowDx;

    /* renamed from: q */
    protected float fontShaowDy;

    /* renamed from: r */
    protected int fadeEdgeLength;

    public SText(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, HashMap<String, SFont> hashMap2, int i) {
        super(kXmlParser, hashMap, i);
        this.name = kXmlParser.getAttributeValue(null, "TextContent");
        this.font = getSFont(hashMap2, kXmlParser.getAttributeValue(null, "Font")
                , ValueParser.parseInt(kXmlParser.getAttributeValue(null, "FontStyle"), -1)
                , ValueParser.parseInt(kXmlParser.getAttributeValue(null, "FontSize"), -1));
        this.fontColor = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FontColor"), -1);
        this.fontColorPressed = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FontColorPressed"), this.fontColor);
        this.fontColorDisable = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FontColorDisable"), this.fontColor);
        this.fontColorFocused = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FontColorFocused"), this.fontColor);
        this.fontColorSelected = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FontColorSelected"), this.fontColor);
        this.fontShadowColor = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "FontShadowColor"), 0xff000000);
        this.fontShaowRadius = ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "FontShadowRadius"), 0.0f);
        this.fontShaowDx = ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "FontShadowDx"), 0.0f);
        this.fontShaowDy = ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "FontShadowDy"), 0.0f);
        this.fadeEdgeLength = ValueParser.parseCommon(kXmlParser.getAttributeValue(null, "FadeEdgeLength"), 0);
        String align = kXmlParser.getAttributeValue(null, "Align");
        this.align = 0;
        if (align != null) {
            if (align.equals("Center") || align.contains("Center|")) {
                this.align = 17;
            } else {
                if (align.contains("Left")) {
                    this.align = 3;
                } else if (align.contains("Right")) {
                    this.align = 5;
                } else {
                    this.align = 1;
                }
                if (align.contains("Top")) {
                    this.align |= 48;
                } else if (align.contains("Bottom")) {
                    this.align |= 80;
                } else {
                    this.align |= 16;
                }
            }
        }
        if (this.align == 0) {
            this.align = 17;
        }
    }

    /* renamed from: a */
    static SFont getSFont(HashMap<String, SFont> hashMap, String str, int style, int size) {
        SFont sFont;
        if (hashMap == null || ((sFont = hashMap.get(str)) == null && (sFont = hashMap.get("Default")) == null)) {
            sFont = new SFont(str);
        } else if (style >= 0 || size >= 0) {
            sFont = new SFont(sFont);
        }
        if (style < 0) {
            style = sFont.style;
        }
        sFont.style = style;
        if (size < 0) {
            size = sFont.size;
        }
        sFont.size = size;
        return sFont;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m3770a(TextView textView, SkinCache skinCache) {
        if (skinCache.getTypeFace(this.font) != null) {
            textView.setTypeface(skinCache.getTypeFace(this.font));
        }
        if (this.fontColor == this.fontColorPressed && this.fontColor == this.fontColorDisable && this.fontColor == this.fontColorFocused && this.fontColor == this.fontColorSelected) {
            textView.setTextColor(this.fontColor);
        } else {
            textView.setTextColor(new ColorStateList(new int[][]{ViewWrapper.f6593x, ViewWrapper.f6571b, ViewWrapper.f6573d, ViewWrapper.f6572c, ViewWrapper.f6570a}, new int[]{this.fontColorPressed, this.fontColor, this.fontColorSelected, this.fontColorFocused, this.fontColorDisable}));
        }
        textView.setTextSize(this.font.size);
        if ((this.fontShadowColor & 0xff000000) != 0) {
            textView.setShadowLayer(this.fontShaowRadius, this.fontShaowDx, this.fontShaowDy, this.fontShadowColor);
        }
        textView.setGravity(this.align);
        textView.setText(this.name);
        int m3704a = ValueParser.getSize(this.fadeEdgeLength, 0);
        if (m3704a > 0) {
            textView.setHorizontalFadingEdgeEnabled(true);
            textView.setFadingEdgeLength(m3704a);
        }
        textView.setLines(1);
        textView.setSingleLine(true);
    }
}
