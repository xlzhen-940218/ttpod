package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.util.AttributeSet;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;

/* loaded from: classes.dex */
public class SmallRotatePic extends RotatePic {
    public SmallRotatePic(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.sds.android.ttpod.widget.audioeffect.RotatePic
    /* renamed from: a */
    protected void mo1372a() {
        this.f8220b = DisplayUtils.dp2px(15) / 3;
        this.f8221c = DisplayUtils.dp2px(6) / 3;
        this.f8222d = R.drawable.effect_circle_green_small;
        this.f8219a = 5;
    }
}
