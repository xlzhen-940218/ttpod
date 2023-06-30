package com.sds.android.ttpod.framework.modules.skin.serialskin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.g */
/* loaded from: classes.dex */
public abstract class SBaseView<T extends View, E> extends SComponent<T> implements Container<E> {

    /* renamed from: c */
    protected int transform;

    /* renamed from: d */
    protected boolean fullScreen;

    /* renamed from: e */
    protected E[] skinViews;

    public SBaseView(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.transform = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "Transform"), -1);
        if (this.transform < 0) {
            this.transform = "Landscape".equals(this.id) ? 1 : 0;
        }
        if (this.id == null || "Landscape".equals(this.id) || "Portrait".equals(this.id) || "Portait".equals(this.id)) {
            this.id = "Player";
        }
        this.fullScreen = ValueParser.stringToBoolean(kXmlParser.getAttributeValue(null, "FullScreen"), false);
    }

    /* renamed from: b */
    public boolean getFullScreen() {
        return this.fullScreen;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void setBackground(Context context, T t, SkinCache skinCache) {
        t.setEnabled(this.enable);
        t.setVisibility(this.visibility);
        t.setTag(this.id);
        Drawable d = getDrawable(context, skinCache);
        t.setBackground(d);
        if (d == null) {
            t.setWillNotDraw(true);
        }
    }

    /* renamed from: c */
    public E[] getSkinViews() {
        return this.skinViews;
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.Container
    /* renamed from: a */
    public void setSkinViews(E[] skinViews) {
        this.skinViews = skinViews;
    }
}
