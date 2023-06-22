package com.sds.android.ttpod.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.ttpod.activities.base.ThemeActivity;
import com.sds.android.ttpod.widget.GuideAnimatorView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GuideActivity extends ThemeActivity {
    private static final int ANIMATION_VIEW_MARGIN_BOTTOM = 38;
    private static final int CHICKEN_ANIMATION_PAGE = 2;
    private static final int DURATION_ANIM = 300;
    private static final int GUIDE_DOT_IMAGE_WIDTH = 14;
    private static final int NUMBER_RATIO_TOTAL = 5;
    private static final int NUMBER_RATIO_VALUE = 3;
    private static final int SCALE_ANIMATION_DURATION = 300;
    private static final String TAG = GuideActivity.class.getName();
    private static final int TEXT_ANIMATION = 1;
    private static final int TEXT_NO_ANIMATION = 0;
    private static final int TRANSLATE_HORIZONTAL_ANIMATION_DURATION = 1500;
    private static final int TRANSLATE_HORIZONTAL_ANIMATION_STARTOFFSET = 1000;
    private static final int TRANSLATE_VERTICAL_ANIMATION_DURATION = 500;
    private static final int TRANSLATE_VERTICAL_ANIMATION_STARTOFFSET = 500;
    private AnimationDrawable mAnimationDrawable;
    private ImageView mAnimationDuck;
    private GuideAnimatorView mAnimatorView;
    private boolean mAutoShowedChickenAnimation;
    private LinearLayout mGuideDotsView;
    private TextView mGuideEntry;
    private ImageView mImageDot;
    private int mOffset;
    private ViewPager mViewPager;
    private boolean mShowGuidePage = false;
    private int mDotPos = 0;
    private List<View> mGuideImages = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
