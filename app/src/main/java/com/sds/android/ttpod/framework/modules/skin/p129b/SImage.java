package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.view.View;
import android.widget.ImageView;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.p */
/* loaded from: classes.dex */
public abstract class SImage<T extends View> extends SComponent<T> {

    /* renamed from: c */
    SBitmap icon;

    /* renamed from: d */
    int scaleType;

    public SImage(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.icon = getSBitmap(hashMap, kXmlParser, "Icon");
        this.scaleType = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "ScaleType"), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public ImageView.ScaleType getScaleType(int i) {
        switch (i) {
            case 1:
                return ImageView.ScaleType.FIT_CENTER;
            case 2:
                return ImageView.ScaleType.FIT_START;
            case 3:
                return ImageView.ScaleType.FIT_END;
            case 4:
                return ImageView.ScaleType.CENTER;
            case 5:
                return ImageView.ScaleType.CENTER_CROP;
            case 6:
                return ImageView.ScaleType.CENTER_INSIDE;
            default:
                return ImageView.ScaleType.FIT_XY;
        }
    }
}
