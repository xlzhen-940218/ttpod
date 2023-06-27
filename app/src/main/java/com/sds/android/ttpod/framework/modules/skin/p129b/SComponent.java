package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.SkinLayoutParams;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.j */
/* loaded from: classes.dex */
public abstract class SComponent<T extends View> extends SBase {

    /* renamed from: c */
    private SkinLayoutParams f6442c;

    /* renamed from: f */
    protected String f6443f;

    /* renamed from: g */
    protected int f6444g;

    /* renamed from: h */
    protected boolean f6445h;

    /* renamed from: i */
    protected SBitmap f6446i;

    /* renamed from: b */
    abstract T mo3771b(Context context, SkinCache skinCache);

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String getId() {
        return super.getId();
    }

    public SComponent(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser);
        this.f6442c = mo3772a(kXmlParser, i);
        if (this.f6442c == null) {
            throw new NullPointerException("SkinLayoutParams cannot be null.");
        }
        this.f6444g = ValueParser.m3698a(kXmlParser.getAttributeValue(null, "Visable"), true) ? 0 : 4;
        this.f6445h = ValueParser.m3698a(kXmlParser.getAttributeValue(null, "Enable"), true);
        this.f6443f = kXmlParser.getAttributeValue(null, "OnClick");
        String attributeValue = kXmlParser.getAttributeValue(null, "BackgroundColor");
        if (attributeValue == null) {
            this.f6446i = m3813a(hashMap, kXmlParser, "Background");
        } else {
            this.f6446i = new SBitmap(attributeValue);
        }
    }

    /* renamed from: a */
    protected SkinLayoutParams mo3772a(KXmlParser kXmlParser, int i) {
        return new SkinLayoutParams(kXmlParser, i);
    }

    /* renamed from: a */
    public static SBitmap m3813a(HashMap<String, SBitmap> hashMap, KXmlParser kXmlParser, String str) {
        String attributeValue = kXmlParser.getAttributeValue(null, str);
        if (TextUtils.isEmpty(attributeValue)) {
            attributeValue = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Normal" + str));
        }
        String m3812a = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Pressed" + str));
        String m3812a2 = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Disable" + str));
        String m3812a3 = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Selected" + str));
        String m3812a4 = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Focused" + str));
        if (TextUtils.isEmpty(attributeValue) && TextUtils.isEmpty(m3812a) && TextUtils.isEmpty(m3812a2) && TextUtils.isEmpty(m3812a3) && TextUtils.isEmpty(m3812a4)) {
            return null;
        }
        if (TextUtils.isEmpty(attributeValue)) {
            attributeValue = "#FF000000";
        }
        SBitmap sBitmap = new SBitmap(attributeValue);
        if (!TextUtils.isEmpty(m3812a)) {
            sBitmap.m3826b(m3812a);
        }
        if (!TextUtils.isEmpty(m3812a3)) {
            sBitmap.m3820e(m3812a3);
        }
        if (!TextUtils.isEmpty(m3812a4)) {
            sBitmap.m3822d(m3812a4);
        }
        if (!TextUtils.isEmpty(m3812a2)) {
            sBitmap.m3824c(attributeValue);
            sBitmap.m3828a(m3812a2);
        }
        sBitmap.m3827b();
        if (!sBitmap.m3816i() && hashMap != null && hashMap.containsKey(attributeValue)) {
            return hashMap.get(attributeValue);
        }
        if (sBitmap.m3816i() && TextUtils.isEmpty(sBitmap.m3823d())) {
            sBitmap.m3828a(attributeValue);
        }
        return sBitmap;
    }

    /* renamed from: a */
    private static String m3812a(HashMap<String, SBitmap> hashMap, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SBitmap sBitmap = hashMap.get(str);
        if (sBitmap != null) {
            return sBitmap.m3825c();
        }
        return str;
    }

    /* renamed from: c */
    public T m3811c(Context context, SkinCache skinCache) {
        T mo3771b = mo3771b(context, skinCache);
        mo3771b.setLayoutParams(this.f6442c);
        mo3775a(context, mo3771b, skinCache);
        return mo3771b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo3775a(Context context, T t, SkinCache skinCache) {
        t.setEnabled(this.f6445h);
        t.setVisibility(this.f6444g);
        t.setTag(this.id);
        t.setTag(R.id.tag_event_on_click, this.f6443f);
        t.setBackgroundDrawable(m3810d(context, skinCache));
    }

    /* renamed from: d */
    public Drawable m3810d(Context context, SkinCache skinCache) {
        return skinCache.m3596a(context.getResources(), this.f6446i);
    }
}
