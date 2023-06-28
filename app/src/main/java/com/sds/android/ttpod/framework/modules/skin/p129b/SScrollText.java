package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.text.TextUtils;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.AutoScrollableTextView;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.w */
/* loaded from: classes.dex */
public class SScrollText extends SText<AutoScrollableTextView> {

    /* renamed from: s */
    private boolean f6476s;

    /* renamed from: t */
    private String f6477t;

    public SScrollText(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, HashMap<String, SFont> hashMap2, int i) {
        super(kXmlParser, hashMap, hashMap2, i);
        this.f6476s = ValueParser.stringToBoolean(kXmlParser.getAttributeValue(null, "AutoScroll"), true);
        this.f6477t = kXmlParser.getAttributeValue(null, "TextContent");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public AutoScrollableTextView mo3771b(Context context, SkinCache skinCache) {
        return new AutoScrollableTextView(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void setBackground(Context context, AutoScrollableTextView autoScrollableTextView, SkinCache skinCache) {
        autoScrollableTextView.setAutoScrollable(this.f6476s);
        m3770a(autoScrollableTextView, skinCache);
        autoScrollableTextView.setFocusable(true);
        autoScrollableTextView.setFormatString(this.f6477t);
        autoScrollableTextView.m3490a(new CharSequence[0]);
        if (this.f6476s) {
            autoScrollableTextView.setMarqueeRepeatLimit(-1);
            autoScrollableTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        } else {
            autoScrollableTextView.setEllipsize(null);
        }
        super.setBackground(context, autoScrollableTextView, skinCache);
    }
}
