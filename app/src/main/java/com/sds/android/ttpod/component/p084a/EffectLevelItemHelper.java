package com.sds.android.ttpod.component.p084a;

import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.ttpod.R;

/* renamed from: com.sds.android.ttpod.component.a.b */
/* loaded from: classes.dex */
public class EffectLevelItemHelper {

    /* renamed from: a */
    private static final EffectLevelItem f3639a = new EffectLevelItem("普通音效达人", R.drawable.img_effect_level_normal, 0, 999);

    /* renamed from: b */
    private static final EffectLevelItem f3640b = new EffectLevelItem("初级音效达人", R.drawable.img_effect_level_junior, 1000, 1999);

    /* renamed from: c */
    private static final EffectLevelItem f3641c = new EffectLevelItem("高级音效达人", R.drawable.img_effect_level_senior, 2000, 4999);

    /* renamed from: d */
    private static final EffectLevelItem f3642d = new EffectLevelItem("资深音效达人", R.drawable.img_effect_level_professional, 5000, 9999);

    /* renamed from: e */
    private static final EffectLevelItem f3643e = new EffectLevelItem("音效发烧友", R.drawable.img_effect_level_fancier, 10000, VIPPolicy.Entry.MAX_LIMIT);

    /* renamed from: a */
    public static EffectLevelItem m7148a(int i) {
        if (i < 1000) {
            return f3639a;
        }
        if (i < 2000) {
            return f3640b;
        }
        if (i < 5000) {
            return f3641c;
        }
        if (i < 10000) {
            return f3642d;
        }
        return f3643e;
    }
}
