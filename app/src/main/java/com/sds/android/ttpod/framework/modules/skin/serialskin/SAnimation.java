package com.sds.android.ttpod.framework.modules.skin.serialskin;

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
import com.sds.android.ttpod.framework.modules.skin.view.AnimationImageView;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.e */
/* loaded from: classes.dex */
public class SAnimation extends SImage<AnimationImageView> {

    /* renamed from: e */
    private int frameNum;

    /* renamed from: j */
    private float frameRate;

    /* renamed from: k */
    private boolean repeat;

    /* renamed from: l */
    private SBitmap staticIcon;

    public SAnimation(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.frameNum = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "FrameNum"), 1);
        this.frameRate = ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "FrameRate"), 5.0f);
        if (this.frameRate <= 0.0f) {
            this.frameRate = 5.0f;
        }
        this.repeat = ValueParser.stringToBoolean(kXmlParser.getAttributeValue(null, "Repeat"), true);
        this.staticIcon = getSBitmap(hashMap, kXmlParser, "StaticIcon");
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public AnimationImageView getIcon(Context context, SkinCache skinCache) {
        AnimationImageView animation = new AnimationImageView(context);
        Resources resources = context.getResources();
        animation.setScaleType(getScaleType(this.scaleType));
        animation.setImageDrawable(skinCache.getDrawable(resources, this.staticIcon));
        if (this.frameNum > 0) {
            DrawableCreator m3594a = skinCache.getStateListDrawableCreator(this.icon);
            AnimationDrawable animationDrawable = new AnimationDrawable();
            int i = (int) (1000.0f / this.frameRate);
            if (this.frameNum > 1 && m3594a != null && (m3594a instanceof BitmapDrawableCreator)) {
                Bitmap m3761a = ((BitmapDrawableCreator) m3594a).m3761a();
                if (m3761a != null) {
                    int width = m3761a.getWidth() / this.frameNum;
                    int height = m3761a.getHeight();
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.frameNum; i3++) {
                        int i4 = i2 + width;
                        animationDrawable.addFrame(new ClipBitmapDrawable(resources, m3761a, i2, 0, i4, height), i);
                        i2 = i4;
                    }
                    animationDrawable.setOneShot(this.repeat ? false : true);
                }
            } else {
                if (m3594a != null) {
                    animationDrawable.addFrame(m3594a.getDrawable(resources), i);
                }
                animationDrawable.setOneShot(true);
            }
            animation.setAnimationDrawable(animationDrawable);
            animation.startAnim();
        }
        return animation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void setBackground(Context context, AnimationImageView animation, SkinCache skinCache) {
        super.setBackground(context, animation, skinCache);
        animation.setDrawingCacheEnabled(false);
    }
}
