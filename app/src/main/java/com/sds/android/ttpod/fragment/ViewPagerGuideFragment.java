package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class ViewPagerGuideFragment extends BaseGuideFragment {
    private static final int ANIMATION_DURATION = 1000;
    private static final int ANIMATION_INTERVAL = 5000;
    private static final float ANIMATION_LENGTH = 0.2f;
    private Handler mAnimationHandler = new Handler();
    private Runnable mAnimationRepeatRunnable = new Runnable() { // from class: com.sds.android.ttpod.fragment.ViewPagerGuideFragment.1
        @Override // java.lang.Runnable
        public void run() {
            Animation createAnimation = ViewPagerGuideFragment.this.createAnimation();
            if (ViewPagerGuideFragment.this.mHandView != null) {
                ViewPagerGuideFragment.this.mHandView.startAnimation(createAnimation);
            }
            ViewPagerGuideFragment.this.mAnimationHandler.postDelayed(this, 5000L);
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
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 2, ANIMATION_LENGTH, 0, 0.0f, 0, 0.0f);
        translateAnimation.setDuration(1000L);
        animationSet.addAnimation(translateAnimation);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 2, -0.4f, 0, 0.0f, 0, 0.0f);
        translateAnimation2.setStartOffset(1000L);
        translateAnimation2.setDuration(1000L);
        animationSet.addAnimation(translateAnimation2);
        TranslateAnimation translateAnimation3 = new TranslateAnimation(1, 0.0f, 2, ANIMATION_LENGTH, 0, 0.0f, 0, 0.0f);
        translateAnimation3.setStartOffset(2000L);
        translateAnimation3.setDuration(1000L);
        animationSet.addAnimation(translateAnimation3);
        return animationSet;
    }

    @Override // com.sds.android.ttpod.fragment.BaseGuideFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setSlidingCloseMode(3);
        setSlidingEnableScrollingMask(false);
        View inflate = layoutInflater.inflate(R.layout.view_pager_ui_guide, viewGroup, false);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.ViewPagerGuideFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FragmentManager fragmentManager = ViewPagerGuideFragment.this.getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().remove(ViewPagerGuideFragment.this).commitAllowingStateLoss();
                }
            }
        });
        this.mHandView = inflate.findViewById(R.id.image_view_hand);
        return inflate;
    }
}
