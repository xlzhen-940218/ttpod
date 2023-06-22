package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.res.ColorStateList;
import androidx.core.view.ViewCompat;
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
    protected SFont f6497c;

    /* renamed from: d */
    protected int f6498d;

    /* renamed from: e */
    protected int f6499e;

    /* renamed from: j */
    protected int f6500j;

    /* renamed from: k */
    protected int f6501k;

    /* renamed from: l */
    protected int f6502l;

    /* renamed from: m */
    protected int f6503m;

    /* renamed from: n */
    protected int f6504n;

    /* renamed from: o */
    protected float f6505o;

    /* renamed from: p */
    protected float f6506p;

    /* renamed from: q */
    protected float f6507q;

    /* renamed from: r */
    protected int f6508r;

    public SText(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, HashMap<String, SFont> hashMap2, int i) {
        super(kXmlParser, hashMap, i);
        this.f6431b = kXmlParser.getAttributeValue(null, "TextContent");
        this.f6497c = m3769a(hashMap2, kXmlParser.getAttributeValue(null, "Font"), ValueParser.m3702a(kXmlParser.getAttributeValue(null, "FontStyle"), -1), ValueParser.m3702a(kXmlParser.getAttributeValue(null, "FontSize"), -1));
        this.f6499e = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FontColor"), -1);
        this.f6500j = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FontColorPressed"), this.f6499e);
        this.f6501k = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FontColorDisable"), this.f6499e);
        this.f6503m = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FontColorFocused"), this.f6499e);
        this.f6502l = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FontColorSelected"), this.f6499e);
        this.f6504n = ValueParser.m3695c(kXmlParser.getAttributeValue(null, "FontShadowColor"), ViewCompat.MEASURED_STATE_MASK);
        this.f6505o = ValueParser.m3703a(kXmlParser.getAttributeValue(null, "FontShadowRadius"), 0.0f);
        this.f6506p = ValueParser.m3703a(kXmlParser.getAttributeValue(null, "FontShadowDx"), 0.0f);
        this.f6507q = ValueParser.m3703a(kXmlParser.getAttributeValue(null, "FontShadowDy"), 0.0f);
        this.f6508r = ValueParser.m3696b(kXmlParser.getAttributeValue(null, "FadeEdgeLength"), 0);
        String attributeValue = kXmlParser.getAttributeValue(null, "Align");
        this.f6498d = 0;
        if (attributeValue != null) {
            if (attributeValue.equals("Center") || attributeValue.contains("Center|")) {
                this.f6498d = 17;
            } else {
                if (attributeValue.contains("Left")) {
                    this.f6498d = 3;
                } else if (attributeValue.contains("Right")) {
                    this.f6498d = 5;
                } else {
                    this.f6498d = 1;
                }
                if (attributeValue.contains("Top")) {
                    this.f6498d |= 48;
                } else if (attributeValue.contains("Bottom")) {
                    this.f6498d |= 80;
                } else {
                    this.f6498d |= 16;
                }
            }
        }
        if (this.f6498d == 0) {
            this.f6498d = 17;
        }
    }

    /* renamed from: a */
    static SFont m3769a(HashMap<String, SFont> hashMap, String str, int i, int i2) {
        SFont sFont;
        if (hashMap == null || ((sFont = hashMap.get(str)) == null && (sFont = hashMap.get("Default")) == null)) {
            sFont = new SFont(str);
        } else if (i >= 0 || i2 >= 0) {
            sFont = new SFont(sFont);
        }
        if (i < 0) {
            i = sFont.f6452d;
        }
        sFont.f6452d = i;
        if (i2 < 0) {
            i2 = sFont.f6451c;
        }
        sFont.f6451c = i2;
        return sFont;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m3770a(TextView textView, SkinCache skinCache) {
        if (skinCache.m3593a(this.f6497c) != null) {
            textView.setTypeface(skinCache.m3593a(this.f6497c));
        }
        if (this.f6499e == this.f6500j && this.f6499e == this.f6501k && this.f6499e == this.f6503m && this.f6499e == this.f6502l) {
            textView.setTextColor(this.f6499e);
        } else {
            textView.setTextColor(new ColorStateList(new int[][]{ViewWrapper.f6593x, ViewWrapper.f6571b, ViewWrapper.f6573d, ViewWrapper.f6572c, ViewWrapper.f6570a}, new int[]{this.f6500j, this.f6499e, this.f6502l, this.f6503m, this.f6501k}));
        }
        textView.setTextSize(this.f6497c.f6451c);
        if ((this.f6504n & ViewCompat.MEASURED_STATE_MASK) != 0) {
            textView.setShadowLayer(this.f6505o, this.f6506p, this.f6507q, this.f6504n);
        }
        textView.setGravity(this.f6498d);
        textView.setText(this.f6431b);
        int m3704a = ValueParser.m3704a(this.f6508r, 0);
        if (m3704a > 0) {
            textView.setHorizontalFadingEdgeEnabled(true);
            textView.setFadingEdgeLength(m3704a);
        }
        textView.setLines(1);
        textView.setSingleLine(true);
    }
}
