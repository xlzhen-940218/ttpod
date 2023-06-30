package com.sds.android.ttpod.framework.modules.skin.serialskin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.BitmapDrawableCreator;
import com.sds.android.ttpod.framework.modules.skin.p130c.ClipBitmapDrawable;
import com.sds.android.ttpod.framework.modules.skin.p130c.DrawableCreator;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.Icon;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.o */
/* loaded from: classes.dex */
public class SIcon extends SImage<Icon> {

    /* renamed from: e */
    private int stateNum;

    /* renamed from: j */
    private int currentState;

    public SIcon(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.stateNum = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "StateNum"), 1);
        this.currentState = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "CurrentState"), 0);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public Icon getIcon(Context context, SkinCache skinCache) {
        return new Icon(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void setBackground(Context context, Icon icon, SkinCache skinCache) {
        if (this.stateNum > 0) {
            Resources resources = context.getResources();
            DrawableCreator m3594a = skinCache.getStateListDrawableCreator(this.icon);
            if (this.stateNum > 1 && m3594a != null && (m3594a instanceof BitmapDrawableCreator)) {
                Bitmap m3761a = ((BitmapDrawableCreator) m3594a).m3761a();
                if (m3761a != null) {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, m3761a);
                    int intrinsicWidth = bitmapDrawable.getIntrinsicWidth() / this.stateNum;
                    int intrinsicHeight = bitmapDrawable.getIntrinsicHeight();
                    int i = 0;
                    for (int i2 = 0; i2 < this.stateNum; i2++) {
                        int i3 = i + intrinsicWidth;
                        icon.m3488a(new ClipBitmapDrawable(resources, m3761a, i, 0, i3, intrinsicHeight));
                        i = i3;
                    }
                }
            } else {
                icon.m3488a(skinCache.getDrawable(resources, this.icon));
            }
        }
        icon.setScaleType(getScaleType(this.scaleType));
        super.setBackground(context, icon, skinCache);
    }
}
