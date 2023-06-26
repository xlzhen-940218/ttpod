package com.sds.android.ttpod.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.ActivityManager;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* renamed from: com.sds.android.ttpod.a.g */
/* loaded from: classes.dex */
public class FavoriteUtils {

    /* renamed from: a */
    private static AnimationSet f2495a = null;

    /* renamed from: a */
    public static void m8284a() {
        Activity m4614c = ActivityManager.m4618a().m4614c();
        final ViewGroup viewGroup = (ViewGroup) m4614c.findViewById(android.R.id.content);
        ImageView imageView = (ImageView) viewGroup.findViewWithTag(FavoriteUtils.class);
        if (imageView != null) {
            imageView.clearAnimation();
            viewGroup.removeView(imageView);
        }
        final ImageView imageView2 = new ImageView(m4614c);
        imageView2.setTag(FavoriteUtils.class);
        imageView2.setImageResource(R.drawable.img_favorite_large);
        if (f2495a == null) {
            f2495a = (AnimationSet) AnimationUtils.loadAnimation(m4614c, R.anim.scale_in_out_addfavorite);
        } else if (SDKVersionUtils.m8373a()) {
            f2495a.cancel();
        }
        f2495a.setAnimationListener(new Animation.AnimationListener() { // from class: com.sds.android.ttpod.a.g.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                viewGroup.post(new Runnable() { // from class: com.sds.android.ttpod.a.g.1.1
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
        imageView2.setAnimation(f2495a);
        f2495a.start();
    }

    /* renamed from: a */
    public static void m8283a(MediaItem mediaItem, boolean z) {
        if (z) {
            m8284a();
        }
        PopupsUtils.m6721a(BaseApplication.getApplication().getString(R.string.collect_favorite));
        CommandCenter.getInstance().execute(new Command(CommandID.ADD_FAVORITE_MEDIA_ITEM, mediaItem));
    }

    /* renamed from: b */
    public static void m8282b(MediaItem mediaItem, boolean z) {
        if (f2495a != null && SDKVersionUtils.m8373a()) {
            f2495a.cancel();
        }
        PopupsUtils.m6721a(BaseApplication.getApplication().getString(R.string.favorite_canceled));
        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_FAVORITE_MEDIA_ITEM, mediaItem, Boolean.FALSE));
    }
}
