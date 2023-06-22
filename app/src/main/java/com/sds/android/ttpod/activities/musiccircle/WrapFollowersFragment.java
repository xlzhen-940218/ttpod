package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.FriendsFragment;
import com.sds.android.ttpod.framework.modules.CommandID;

/* loaded from: classes.dex */
public class WrapFollowersFragment extends SlidingClosableFragment {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.musiccircle_followers_layout, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FollowersFragment followersFragment = new FollowersFragment();
        followersFragment.setArguments(getArguments());
        getChildFragmentManager().beginTransaction().replace(R.id.musiccircle_followers, followersFragment).commitAllowingStateLoss();
        getActionBarController().m7193a("我的粉丝");
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    /* loaded from: classes.dex */
    public static class FollowersFragment extends FriendsFragment {
        @Override // com.sds.android.ttpod.fragment.musiccircle.FriendsFragment
        protected CommandID onLoadRequestDataCommandID() {
            return CommandID.REQUEST_FOLLOWER_FRIENDS;
        }

        @Override // com.sds.android.ttpod.fragment.musiccircle.FriendsFragment
        protected CommandID onLoadUpdateRequestDataCommandID() {
            return CommandID.UPDATE_FOLLOWER_FRIENDS;
        }
    }
}
