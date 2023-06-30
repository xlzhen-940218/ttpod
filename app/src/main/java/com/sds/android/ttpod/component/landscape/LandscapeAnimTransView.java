package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.Rotate3dAnimation;

/* loaded from: classes.dex */
public class LandscapeAnimTransView extends AnimTransView {
    public LandscapeAnimTransView(Context context) {
        super(context);
        setDefaultImageResource(R.raw.landscape_default);
    }

    public LandscapeAnimTransView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDefaultImageResource(R.raw.landscape_default);
    }

    public LandscapeAnimTransView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setDefaultImageResource(R.raw.landscape_default);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.view.AnimTransView
    /* renamed from: a */
    protected void loadAnim() {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(-90.0f, 0.0f, 0.0f, false);
        rotate3dAnimation.setDuration(500L);
        rotate3dAnimation.setFillAfter(true);
        rotate3dAnimation.setInterpolator(new DecelerateInterpolator());
        setInAnimation(rotate3dAnimation);
        Rotate3dAnimation rotate3dAnimation2 = new Rotate3dAnimation(0.0f, 90.0f, 0.0f, false);
        rotate3dAnimation2.setDuration(500L);
        rotate3dAnimation2.setFillAfter(true);
        rotate3dAnimation2.setInterpolator(new DecelerateInterpolator());
        setOutAnimation(rotate3dAnimation2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.modules.skin.view.AnimTransView, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        setReflectionHeight(i2 / 3);
        super.onSizeChanged(i, i2, i3, i4);
    }
}
