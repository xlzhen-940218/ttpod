package com.sds.android.ttpod.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.ActivityManager;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;

/* renamed from: com.sds.android.ttpod.a.e */
/* loaded from: classes.dex */
public class EffectPickUtils {
    /* renamed from: a */
    public static void m8305a() {
        Activity m4614c = ActivityManager.getInstance().getActiveActivity();
        final ViewGroup viewGroup = (ViewGroup) m4614c.findViewById(android.R.id.content);
        ImageView imageView = (ImageView) viewGroup.findViewWithTag(FavoriteUtils.class);
        if (imageView != null) {
            imageView.clearAnimation();
            viewGroup.removeView(imageView);
        }
        final ImageView imageView2 = new ImageView(m4614c);
        imageView2.setTag(FavoriteUtils.class);
        imageView2.setImageResource(R.drawable.img_effect_recommand_pickcount_large);
        AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(m4614c, R.anim.scale_in_out_addfavorite);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.sds.android.ttpod.a.e.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                viewGroup.post(new Runnable() { // from class: com.sds.android.ttpod.a.e.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        viewGroup.removeView(imageView2);
                    }
                });
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = Gravity.CENTER;
        viewGroup.addView(imageView2, layoutParams);
        imageView2.setAnimation(animationSet);
        animationSet.start();
    }

    /* renamed from: a */
    public static void m8304a(String str) {
        CommandCenter.getInstance().execute(new Command(CommandID.PICK_EFFECT, str));
    }
}
