package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class SlidingGuideFragment extends BaseGuideFragment {
    private static final int ANIMATION_DURATION = 1000;
    private static final int ANIMATION_INTERVAL = 3000;
    private static final float ANIMATION_LENGTH = 0.5f;
    private Handler mAnimationHandler = new Handler();
    private Runnable mAnimationRepeatRunnable = new Runnable() { // from class: com.sds.android.ttpod.fragment.SlidingGuideFragment.1
        @Override // java.lang.Runnable
        public void run() {
            SlidingGuideFragment.this.mHandView.startAnimation(SlidingGuideFragment.this.createAnimation());
            SlidingGuideFragment.this.mAnimationHandler.postDelayed(this, 3000L);
        }
    };
    private View mHandView;

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mAnimationHandler.removeCallbacksAndMessages(null);
        this.mAnimationHandler.postDelayed(this.mAnimationRepeatRunnable, 1000L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mAnimationHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animation createAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 2, ANIMATION_LENGTH, 0, 0.0f, 0, 0.0f);
        translateAnimation.setDuration(1000L);
        return translateAnimation;
    }

    @Override // com.sds.android.ttpod.fragment.BaseGuideFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setSlidingCloseMode(2);
        setSlidingEnableScrollingMask(false);
        View inflate = layoutInflater.inflate(R.layout.sliding_ui_guide, viewGroup, false);
        this.mHandView = inflate.findViewById(R.id.image_view_hand);
        return inflate;
    }
}
