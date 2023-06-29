package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.SkinLayoutParams;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.TTImageSwitcher;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.d */
/* loaded from: classes.dex */
public class SAnimTransImage extends SImage<View> {

    /* renamed from: e */
    private int reflectionMaskStartColor;

    /* renamed from: j */
    private int reflectionMaskEndColor;

    public SAnimTransImage(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        super(kXmlParser, hashMap, i);
        this.icon = getSBitmap(hashMap, kXmlParser, "DefaultImage");
        this.scaleType = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "ScaleType"), 1);
        this.reflectionMaskStartColor = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "ReflectionMaskStartColor"), 0);
        this.reflectionMaskEndColor = ValueParser.parseColor(kXmlParser.getAttributeValue(null, "ReflectionMaskEndColor"), 0);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    protected SkinLayoutParams mo3772a(KXmlParser kXmlParser, int i) {
        return new C1980a(kXmlParser, i);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: b */
    public View mo3771b(Context context, SkinCache skinCache) {
        return "AlbumCover".equals(this.id) ? new TTImageSwitcher(context) : new AnimTransView(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SComponent
    /* renamed from: a */
    public void setBackground(Context context, View view, SkinCache skinCache) {
        super.setBackground(context,  view, skinCache);
        if (view instanceof AnimTransView) {
            AnimTransView animTransView = (AnimTransView) view;
            animTransView.setDefaultImageDrawable(skinCache.m3596a(context.getResources(), this.icon));
            animTransView.setScaleType(getScaleType(this.scaleType));
            animTransView.m3514a(this.reflectionMaskStartColor, this.reflectionMaskEndColor);
            return;
        }
        LogUtils.debug("Image", "initView TTImageSwitcher");
        TTImageSwitcher tTImageSwitcher = (TTImageSwitcher) view;
        tTImageSwitcher.setDefaultImageDrawable(skinCache.m3596a(context.getResources(), this.icon));
        tTImageSwitcher.setFactory(tTImageSwitcher);
        tTImageSwitcher.setAllowStart(Preferences.m3003aD());
        if (SDKVersionUtils.m8368f()) {
            Animation loadAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            loadAnimation.setDuration(1000L);
            tTImageSwitcher.setInAnimation(loadAnimation);
            Animation loadAnimation2 = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
            loadAnimation2.setDuration(1000L);
            tTImageSwitcher.setOutAnimation(loadAnimation2);
        }
    }

    /* compiled from: SAnimTransImage.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.b.d$a */
    /* loaded from: classes.dex */
    public static class C1980a extends SkinLayoutParams {

        /* renamed from: a */
        private int reflectionHeight;

        /* renamed from: b */
        private int divideHeight;

        /* renamed from: c */
        private int switchAnimation;

        public C1980a(KXmlParser kXmlParser, int i) {
            super(kXmlParser, i);
            this.reflectionHeight = ValueParser.parseCommon(kXmlParser.getAttributeValue(null, "ReflectionHeight"), 0);
            this.divideHeight = ValueParser.parseCommon(kXmlParser.getAttributeValue(null, "DivideHeight"), 0);
            this.switchAnimation = ValueParser.parseInt(kXmlParser.getAttributeValue(null, "SwitchAnimation"), 1);
        }
    }
}
