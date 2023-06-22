package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.BitmapDrawableCreator;
import com.sds.android.ttpod.framework.modules.skin.p130c.ClipBitmapDrawable;
import com.sds.android.ttpod.framework.modules.skin.p130c.DrawableCreator;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.e */
/* loaded from: classes.dex */
public class SAnimation extends SImage<Animation> {

    /* renamed from: e */
    private int f6426e;

    /* renamed from: j */
    private float f6427j;

    /* renamed from: k */
    private boolean f6428k;

    /* renamed from: l */
    private SBitmap f6429l;

    public SAnimation(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.f6426e = ValueParser.m3702a(kXmlParser.getAttributeValue(null, "FrameNum"), 1);
        this.f6427j = ValueParser.m3703a(kXmlParser.getAttributeValue(null, "FrameRate"), 5.0f);
        if (this.f6427j <= 0.0f) {
            this.f6427j = 5.0f;
        }
        this.f6428k = ValueParser.m3698a(kXmlParser.getAttributeValue(null, "Repeat"), true);
        this.f6429l = m3813a(hashMap, kXmlParser, "StaticIcon");
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public Animation mo3771b(Context context, SkinCache skinCache) {
        Animation animation = new Animation(context);
        Resources resources = context.getResources();
        animation.setScaleType(m3797a(this.f6457d));
        animation.setImageDrawable(skinCache.m3596a(resources, this.f6429l));
        if (this.f6426e > 0) {
            DrawableCreator m3594a = skinCache.m3594a(this.f6456c);
            AnimationDrawable animationDrawable = new AnimationDrawable();
            int i = (int) (1000.0f / this.f6427j);
            if (this.f6426e > 1 && m3594a != null && (m3594a instanceof BitmapDrawableCreator)) {
                Bitmap m3761a = ((BitmapDrawableCreator) m3594a).m3761a();
                if (m3761a != null) {
                    int width = m3761a.getWidth() / this.f6426e;
                    int height = m3761a.getHeight();
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.f6426e; i3++) {
                        int i4 = i2 + width;
                        animationDrawable.addFrame(new ClipBitmapDrawable(resources, m3761a, i2, 0, i4, height), i);
                        i2 = i4;
                    }
                    animationDrawable.setOneShot(this.f6428k ? false : true);
                }
            } else {
                if (m3594a != null) {
                    animationDrawable.addFrame(m3594a.mo3716a(resources), i);
                }
                animationDrawable.setOneShot(true);
            }
            animation.setAnimationDrawable(animationDrawable);
            animation.m3504a();
        }
        return animation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void mo3775a(Context context, Animation animation, SkinCache skinCache) {
        super.mo3775a(context, animation, skinCache);
        animation.setDrawingCacheEnabled(false);
    }
}
