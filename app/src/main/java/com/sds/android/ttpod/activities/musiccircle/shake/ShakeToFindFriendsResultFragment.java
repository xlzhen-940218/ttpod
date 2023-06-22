package com.sds.android.ttpod.activities.musiccircle.shake;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sds.android.cloudapi.ttpod.data.AroundTTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p073e.UserInfoViewHolder;
import com.sds.android.ttpod.adapter.p073e.UserListAdapter;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.UserListFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ShakeToFindFriendsResultFragment extends SlidingClosableFragment {
    public static final String EXTRA_AVATAR = "social_avatar";
    public static final String EXTRA_DISTANCE = "social_distance";
    public static final String EXTRA_FOLLOWER_COUNT = "social_follow_count";
    public static final String EXTRA_NICK_NAME = "social_nick_name";
    public static final String EXTRA_USER_ID = "social_user_id";
    public static final String EXTRA_USER_IDS = "social_user_ids";
    private ShakeToFindFriendsFragment.InterfaceC0845a mResumeShakeCallback;

    public void setResumeShakeFriendCallback(ShakeToFindFriendsFragment.InterfaceC0845a interfaceC0845a) {
        this.mResumeShakeCallback = interfaceC0845a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        initView();
        return layoutInflater.inflate(R.layout.musiccircle_shake_result_layout, viewGroup, false);
    }

    private void initView() {
        getActionBarController().m7193a("摇到的人");
        getChildFragmentManager().beginTransaction().replace(R.id.shake_result, Fragment.instantiate(getActivity(), ShakeResultFragment.class.getName(), getArguments())).commitAllowingStateLoss();
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void onSlidingClosed() {
        this.mResumeShakeCallback.mo7845a();
        super.onSlidingClosed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle convertAroundUserToBundle(AroundTTPodUser aroundTTPodUser) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_USER_ID, aroundTTPodUser.getUserId());
        bundle.putString(EXTRA_NICK_NAME, aroundTTPodUser.getNickName());
        bundle.putString(EXTRA_AVATAR, aroundTTPodUser.getAvatarUrl());
        bundle.putInt(EXTRA_FOLLOWER_COUNT, aroundTTPodUser.getFollowersCount());
        bundle.putInt(EXTRA_DISTANCE, aroundTTPodUser.getDistance());
        return bundle;
    }

    static AroundTTPodUser convertBundleToAroundUser(Bundle bundle) {
        AroundTTPodUser aroundTTPodUser = new AroundTTPodUser();
        aroundTTPodUser.setUserId(bundle.getLong(EXTRA_USER_ID, 0L));
        aroundTTPodUser.setNickName(bundle.getString(EXTRA_NICK_NAME));
        aroundTTPodUser.setFollowersCount(bundle.getInt(EXTRA_FOLLOWER_COUNT));
        aroundTTPodUser.setAvatarUrl(bundle.getString(EXTRA_AVATAR));
        aroundTTPodUser.setDistance(bundle.getInt(EXTRA_DISTANCE));
        return aroundTTPodUser;
    }

    /* loaded from: classes.dex */
    public static class ShakeResultFragment extends UserListFragment<AroundTTPodUser> {
        private List<AroundTTPodUser> mUsers;

        @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            Bundle arguments = getArguments();
            if (arguments != null) {
                long[] longArray = arguments.getLongArray(ShakeToFindFriendsResultFragment.EXTRA_USER_IDS);
                if (longArray == null) {
                    throw new IllegalArgumentException("no user id");
                }
                this.mUsers = new ArrayList();
                for (long j : longArray) {
                    this.mUsers.add(ShakeToFindFriendsResultFragment.convertBundleToAroundUser(arguments.getBundle(String.valueOf(j))));
                }
            }
        }

        @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
        protected UserListAdapter<AroundTTPodUser> onCreateAdapter(List<AroundTTPodUser> list) {
            return new C0846a(getActivity(), list);
        }

        @Override // com.sds.android.ttpod.fragment.musiccircle.UserListFragment
        protected void onRequestData(int i, int i2) {
            addData(this.mUsers, false);
        }

        @Override // com.sds.android.ttpod.framework.base.BaseFragment
        public void onLoadFinished() {
            super.onLoadFinished();
            reload();
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.musiccircle.shake.ShakeToFindFriendsResultFragment$a */
    /* loaded from: classes.dex */
    static class C0846a extends UserListAdapter<AroundTTPodUser> {
        public C0846a(Context context, List<AroundTTPodUser> list) {
            super(context, list);
        }

        /* renamed from: a */
        private String m7844a(int i) {
            if (i < 0) {
                return "天涯海角";
            }
            if (i <= 10) {
                return "%d米左右";
            }
            if (i <= 50) {
                return "50米内";
            }
            if (i <= 100) {
                return "100米内";
            }
            if (i <= 200) {
                return "200米内";
            }
            if (i <= 300) {
                return "300米内";
            }
            if (i <= 400) {
                return "400米内";
            }
            if (i <= 500) {
                return "500米内";
            }
            if (i <= 600) {
                return "600米内";
            }
            if (i <= 700) {
                return "700米内";
            }
            if (i <= 800) {
                return "800米内";
            }
            if (i <= 900) {
                return "900米内";
            }
            if (i <= 1000000) {
                return "1公里之内";
            }
            if (i <= 1000000) {
                return (i / 1000) + "公里之内";
            } else if (i <= 1000000) {
                return "";
            } else {
                return "千里之外";
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.p073e.UserListAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5434a(UserInfoViewHolder userInfoViewHolder, AroundTTPodUser aroundTTPodUser) {
            super.mo5434a(userInfoViewHolder,  aroundTTPodUser);
            TextView m7433d = userInfoViewHolder.m7433d();
            m7433d.setCompoundDrawablesWithIntrinsicBounds(R.drawable.img_musiccircle_location_point, 0, 0, 0);
            m7433d.setText(m7844a(aroundTTPodUser.getDistance()));
        }
    }
}
