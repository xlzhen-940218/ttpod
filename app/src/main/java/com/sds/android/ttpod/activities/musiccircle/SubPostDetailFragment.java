package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;

/* loaded from: classes.dex */
public class SubPostDetailFragment extends SlidingClosableFragment {
    private static long mPostId;
    private final PostDetailFragment mPostDetailFragment;
    private View mRootView;

    /* renamed from: com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0795a {
        /* renamed from: a */
        void mo7918a(Post post);
    }

    public SubPostDetailFragment(PostDetailFragment postDetailFragment) {
        this.mPostDetailFragment = postDetailFragment;
        initBundle(SPage.PAGE_ONLINE_POST_DETAIL, String.valueOf(mPostId));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mPostDetailFragment, null).commitAllowingStateLoss();
        this.mPostDetailFragment.setOnSetTitleListener(getActionBarController());
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mRootView != null) {
            ThemeManager.m3269a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        }
    }

    public static SubPostDetailFragment createById(long j, String str) {
        mPostId = j;
        return new SubPostDetailFragment(PostDetailFragment.createById(j, str));
    }

    public static SubPostDetailFragment createByPost(Post post, String str) {
        mPostId = post.getPostId();
        return new SubPostDetailFragment(PostDetailFragment.createByPost(post, str));
    }

    public void setPersonalizedRecommendInfo(PostDetailFragment.Recomment c0794b) {
        this.mPostDetailFragment.setPersonalizedRecommendInfo(c0794b);
    }
}
