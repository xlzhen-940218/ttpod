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
    private SkinLayoutParams skinLayoutParams;

    /* renamed from: f */
    protected String onClick;

    /* renamed from: g */
    protected int visibility;

    /* renamed from: h */
    protected boolean enable;

    /* renamed from: i */
    protected SBitmap background;

    /* renamed from: b */
    abstract T getIcon(Context context, SkinCache skinCache);

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String getId() {
        return super.getId();
    }

    public SComponent(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser);
        this.skinLayoutParams = mo3772a(kXmlParser, i);
        if (this.skinLayoutParams == null) {
            throw new NullPointerException("SkinLayoutParams cannot be null.");
        }
        this.visibility = ValueParser.stringToBoolean(kXmlParser.getAttributeValue(null, "Visable"), true) ? 0 : 4;
        this.enable = ValueParser.stringToBoolean(kXmlParser.getAttributeValue(null, "Enable"), true);
        this.onClick = kXmlParser.getAttributeValue(null, "OnClick");
        String backgroundColor = kXmlParser.getAttributeValue(null, "BackgroundColor");
        if (backgroundColor == null) {
            this.background = getSBitmap(hashMap, kXmlParser, "Background");
        } else {
            this.background = new SBitmap(backgroundColor);
        }
    }

    /* renamed from: a */
    protected SkinLayoutParams mo3772a(KXmlParser kXmlParser, int i) {
        return new SkinLayoutParams(kXmlParser, i);
    }

    /* renamed from: a */
    public static SBitmap getSBitmap(HashMap<String, SBitmap> hashMap, KXmlParser kXmlParser, String str) {
        String normal = kXmlParser.getAttributeValue(null, str);
        if (TextUtils.isEmpty(normal)) {
            normal = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Normal" + str));
        }
        String pressed = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Pressed" + str));
        String disable = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Disable" + str));
        String selected = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Selected" + str));
        String focused = m3812a(hashMap, kXmlParser.getAttributeValue(null, "Focused" + str));
        if (TextUtils.isEmpty(normal) && TextUtils.isEmpty(pressed) && TextUtils.isEmpty(disable)
                && TextUtils.isEmpty(selected) && TextUtils.isEmpty(focused)) {
            return null;
        }
        if (TextUtils.isEmpty(normal)) {
            normal = "#FF000000";
        }
        SBitmap sBitmap = new SBitmap(normal);
        if (!TextUtils.isEmpty(pressed)) {
            sBitmap.setPoressed(pressed);
        }
        if (!TextUtils.isEmpty(selected)) {
            sBitmap.setChecked(selected);
        }
        if (!TextUtils.isEmpty(focused)) {
            sBitmap.setFocused(focused);
        }
        if (!TextUtils.isEmpty(disable)) {
            sBitmap.setEnabled(normal);
            sBitmap.setEmpty(disable);
        }
        sBitmap.m3827b();
        if (!sBitmap.m3816i() && hashMap != null && hashMap.containsKey(normal)) {
            return hashMap.get(normal);
        }
        if (sBitmap.m3816i() && TextUtils.isEmpty(sBitmap.getEmpty())) {
            sBitmap.setEmpty(normal);
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
            return sBitmap.getFile();
        }
        return str;
    }

    /* renamed from: c */
    public T m3811c(Context context, SkinCache skinCache) {
        T mo3771b = getIcon(context, skinCache);
        mo3771b.setLayoutParams(this.skinLayoutParams);
        setBackground(context, mo3771b, skinCache);
        return mo3771b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void setBackground(Context context, T t, SkinCache skinCache) {
        t.setEnabled(this.enable);
        t.setVisibility(this.visibility);
        t.setTag(this.id);
        t.setTag(R.id.tag_event_on_click, this.onClick);
        t.setBackground(getDrawable(context, skinCache));
    }

    /* renamed from: d */
    public Drawable getDrawable(Context context, SkinCache skinCache) {
        return skinCache.m3596a(context.getResources(), this.background);
    }
}
