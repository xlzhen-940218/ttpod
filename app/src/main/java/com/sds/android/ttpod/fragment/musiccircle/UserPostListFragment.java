package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.IdListResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.CommentsFragment;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.activities.musiccircle.WrapFollowersFragment;
import com.sds.android.ttpod.activities.musiccircle.WrapFollowingsFragment;
import com.sds.android.ttpod.activities.musiccircle.message.PrivateMessageFragment;
import com.sds.android.ttpod.activities.user.StaticUserInfoActivity;
import com.sds.android.ttpod.activities.user.UserInfoActivity;
import com.sds.android.ttpod.adapter.p073e.PostListAdapter;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.MusicCircleModule;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ViewUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class UserPostListFragment extends HeaderPostListFragment implements PostListByIdFragment.InterfaceC1690a {
    private ActionBarController.C1070a mEditAction;
    private TextView mFollowerCountView;
    private View mFollowerView;
    private TextView mFollowingCountView;
    private View mFollowingView;
    private View.OnClickListener mHeaderButtonClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.UserPostListFragment.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!ViewUtils.m4641a()) {
                switch (view.getId()) {
                    case R.id.image_avatar /* 2131231232 */:
                    case R.id.text_profile_nick_name /* 2131231824 */:
                        UserPostListFragment.this.showUserInfo();
                        return;
                    case R.id.following_layout /* 2131231945 */:
                        UserPostListFragment.this.viewFollowings();
                        return;
                    case R.id.follower_layout /* 2131231948 */:
                        UserPostListFragment.this.viewFollowers();
                        return;
                    case R.id.private_message /* 2131231951 */:
                        UserPostListFragment.this.sendPrivateMessage();
                        return;
                    case R.id.toggle_follow /* 2131231952 */:
                        UserPostListFragment.this.toggleFollow();
                        return;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }
    };
    private String mOrigin;
    private View mPrivateMessageView;
    private ActionBarController.C1070a mRefreshAction;
    private String mTitle;
    private Button mToggleFollowView;

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setOnRequestDataListener(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mOrigin = arguments.getString("origin");
            if (this.mOrigin == null) {
                this.mOrigin = "";
            }
            this.mTitle = arguments.getString("title");
            if (this.mTitle == null) {
                this.mTitle = "";
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment
    protected View onLoadHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_user_profile_header, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.user_profile_bottom);
        this.mFollowingView = findViewById.findViewById(R.id.following_layout);
        this.mFollowerView = findViewById.findViewById(R.id.follower_layout);
        this.mPrivateMessageView = findViewById.findViewById(R.id.private_message);
        this.mPrivateMessageView.setVisibility(View.VISIBLE);
        this.mToggleFollowView = (Button) findViewById.findViewById(R.id.toggle_follow);
        this.mToggleFollowView.setVisibility(View.VISIBLE);
        this.mFollowingCountView = (TextView) findViewById.findViewById(R.id.following_value);
        this.mFollowerCountView = (TextView) findViewById.findViewById(R.id.follower_value);
        this.mFollowingView.setOnClickListener(this.mHeaderButtonClickListener);
        this.mFollowerView.setOnClickListener(this.mHeaderButtonClickListener);
        this.mPrivateMessageView.setOnClickListener(this.mHeaderButtonClickListener);
        this.mToggleFollowView.setOnClickListener(this.mHeaderButtonClickListener);
        TTPodUser user = getUser();
        this.mFollowerCountView.setText(String.valueOf(user.getFollowersCount()));
        this.mFollowingCountView.setText(String.valueOf(user.getFollowingsCount()));
        bindFollowButton();
        if (isMe()) {
            this.mToggleFollowView.setVisibility(View.INVISIBLE);
            this.mPrivateMessageView.setVisibility(View.INVISIBLE);
        } else {
            this.mToggleFollowView.setVisibility(MusicCircleModule.TUID_TTPOD == user.getUserId() ? View.GONE : View.VISIBLE);
        }
        return inflate;
    }

    private void bindFollowButton() {
        boolean booleanValue = ((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_FOLLOWED, Long.valueOf(getUser().getUserId())), Boolean.class)).booleanValue();
        if (this.mToggleFollowView != null) {
            this.mToggleFollowView.setText(booleanValue ? R.string.remove_follow : R.string.add_follow);
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected void addCustomActions() {
        ActionBarController actionBarController = getActionBarController();
        this.mRefreshAction = actionBarController.m7199a((Drawable) null);
        if (isMe()) {
            this.mEditAction = actionBarController.m7199a((Drawable) null);
            this.mEditAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.musiccircle.UserPostListFragment.1
                @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
                /* renamed from: a */
                public void mo5433a(ActionBarController.C1070a c1070a) {
                    UserPostListFragment.this.startActivity(new Intent(UserPostListFragment.this.getActivity(), UserInfoActivity.class).putExtra(UserInfoActivity.BUNDLEKEY_LOGOUT_VISIBLE, false));
                }
            });
        }
        this.mRefreshAction.m7155c(false);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment.InterfaceC1690a
    public void onRequestDataStarted() {
        updateFooter(false, 0, getString(R.string.loading));
        startRefreshAnimation();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment.InterfaceC1690a
    public void onRequestDataFinished() {
        stopRefreshAnimation();
    }

    private void startRefreshAnimation() {
        if (this.mRefreshAction != null) {
            this.mRefreshAction.m7155c(true);
            this.mRefreshAction.m7169a(ThemeManager.m3265a(ThemeElement.TOP_BAR_REFRESH_IMAGE));
            this.mRefreshAction.m7152e();
        }
    }

    private void stopRefreshAnimation() {
        if (this.mRefreshAction != null) {
            this.mRefreshAction.m7149g();
            this.mRefreshAction.m7155c(false);
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getNickName().setOnClickListener(this.mHeaderButtonClickListener);
        getAvatar().setOnClickListener(this.mHeaderButtonClickListener);
        setTitle(getUser().getNickName());
    }

    private void setTitle(String str) {
        getActionBarController().m7193a((CharSequence) str);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mEditAction != null) {
            this.mEditAction.m7167a((ActionBarController.InterfaceC1072b) null);
        }
        setOnRequestDataListener(null);
        this.mFollowingView = null;
        this.mFollowerView = null;
        this.mPrivateMessageView = null;
        this.mToggleFollowView = null;
        this.mFollowingCountView = null;
        this.mFollowerCountView = null;
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment
    public void onRequestUserInfoFinished(TTPodUser tTPodUser) {
        super.onRequestUserInfoFinished(tTPodUser);
        if (this.mFollowingCountView != null && this.mFollowerCountView != null) {
            this.mFollowingCountView.setText(String.valueOf(getUser().getFollowingsCount()));
            this.mFollowerCountView.setText(String.valueOf(getUser().getFollowersCount()));
            if (StringUtils.isEmpty(getUser().getNickName())) {
                getUser().setNickName(tTPodUser.getNickName());
                getUser().setUserId(tTPodUser.getUserId());
                getNickName().setText(tTPodUser.getNickName());
                setTitle(tTPodUser.getNickName());
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_USER_POST_ID_LIST, ReflectUtils.loadMethod(cls, "updateUserPostIds", IdListResult.class, String.class));
        map.put(CommandID.UPDATE_SYNC_FOLLOWING_FINISHED, ReflectUtils.loadMethod(cls, "updateSyncFollowingFinished", new Class[0]));
        map.put(CommandID.UPDATE_FOLLOW_FRIEND, ReflectUtils.loadMethod(cls, "updateFollow", BaseResult.class, String.class));
        map.put(CommandID.UPDATE_UNFOLLOW_FRIEND, ReflectUtils.loadMethod(cls, "updateUnFollow", BaseResult.class, String.class));
        map.put(CommandID.LOGIN_FINISHED, ReflectUtils.loadMethod(cls, "onLoginFinished", CommonResult.class));
    }

    public void updateFollow(BaseResult baseResult, String str) {
        if (this.mFollowerCountView != null && this.mFollowingCountView != null) {
            TTPodUser user = getUser();
            if (isMe()) {
                user.setFollowingsCount(user.getFollowingsCount() + 1);
                this.mFollowingCountView.setText(String.valueOf(user.getFollowingsCount()));
            } else {
                user.setFollowersCount(user.getFollowersCount() + 1);
                this.mFollowerCountView.setText(String.valueOf(user.getFollowersCount()));
            }
            bindFollowButton();
        }
    }

    public void updateUnFollow(BaseResult baseResult, String str) {
        if (this.mFollowingCountView != null && this.mFollowerCountView != null) {
            TTPodUser user = getUser();
            if (isMe()) {
                user.setFollowingsCount(user.getFollowingsCount() - 1);
                this.mFollowingCountView.setText(String.valueOf(user.getFollowingsCount()));
            } else {
                user.setFollowersCount(getUser().getFollowersCount() - 1);
                this.mFollowerCountView.setText(String.valueOf(user.getFollowersCount()));
            }
            bindFollowButton();
        }
    }

    public void updateSyncFollowingFinished() {
        bindFollowButton();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onPlayEvent(Post post) {
        if (this.mOrigin.startsWith("recommend")) {
            MusicCircleStatistic.m7956s();
            //OnlineMediaStatistic.m5045a("music-circle");
        } else if (this.mOrigin.startsWith("rank")) {
            if (!TextUtils.isEmpty(this.mTitle)) {
                MusicCircleStatistic.m7975c(this.mTitle);
            }
            //OnlineMediaStatistic.m5045a("music-circle");
        } else if (this.mOrigin.startsWith("category")) {
            if (!TextUtils.isEmpty(this.mTitle)) {
                MusicCircleStatistic.m7969f(this.mTitle);
            }
            //OnlineMediaStatistic.m5045a("music-circle");
        } else {
            //OnlineMediaStatistic.m5045a(this.mOrigin);
        }
        //OnlineMediaStatistic.m5054a();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    protected PostListAdapter onCreateAdapter(Context context, List<Post> list) {
        PostListAdapter postListAdapter = new PostListAdapter(context, list, onLoadOrigin()) { // from class: com.sds.android.ttpod.fragment.musiccircle.UserPostListFragment.2
            @Override // com.sds.android.ttpod.adapter.p073e.PostListAdapter
            /* renamed from: a */
            protected void mo5430a(TTPodUser tTPodUser) {
                if (UserPostListFragment.this.getUser().getUserId() != tTPodUser.getUserId()) {
                    UserPostListFragment.this.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(tTPodUser, UserPostListFragment.this.onLoadOrigin()));
                }
            }

            @Override // com.sds.android.ttpod.adapter.p073e.PostListAdapter
            /* renamed from: a */
            protected void mo5432a(Post post) {
                UserPostListFragment.this.onPlayEvent(post);
            }

            @Override // com.sds.android.ttpod.adapter.p073e.PostListAdapter
            /* renamed from: a */
            protected void mo5431a(Post post, boolean z) {
            }
        };
        postListAdapter.m7482a(new CommentsFragment.InterfaceC0785b() { // from class: com.sds.android.ttpod.fragment.musiccircle.UserPostListFragment.3
            @Override // com.sds.android.ttpod.activities.musiccircle.CommentsFragment.InterfaceC0785b
            /* renamed from: a */
            public void mo5429a(long j, long j2) {
                UserPostListFragment.this.launchFragment(CommentsFragment.createCommentsFragment(j, j2));
            }
        });
        return postListAdapter;
    }

    public void updateUserPostIds(IdListResult idListResult, String str) {
        if (onLoadOrigin().equals(str)) {
            ArrayList<Long> dataList = idListResult.getDataList();
            setPostIds(dataList, true);
            if (idListResult.isSuccess() && dataList.isEmpty()) {
                updateFooter(false, 8, getDataCount() + "条消息");
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment
    public void onRequestPostIds() {
        super.onRequestPostIds();
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_USER_POST_IDS, Long.valueOf(getUser().getUserId()), onLoadOrigin()));
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public String onLoadOrigin() {
        return String.valueOf(getUser().getUserId());
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    protected void onLoadData() {
        onRequestPostIds();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onLoginFinished(CommonResult commonResult) {
        super.onLoginFinished(commonResult);
        onLoadFinished();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(getRootView(), "BackgroundMaskColor");
        ThemeUtils.m8168a(this.mRefreshAction, ThemeElement.TOP_BAR_REFRESH_IMAGE, (int) R.string.icon_refresh_with_two_arrow, ThemeElement.TOP_BAR_TEXT);
        ThemeUtils.m8168a(this.mEditAction, ThemeElement.TOP_BAR_EDIT_IMAGE, (int) R.string.icon_edit, ThemeElement.TOP_BAR_TEXT);
        getActionBarController().onThemeLoaded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewFollowings() {
        if (getUser().getFollowingsCount() <= 0) {
            PopupsUtils.m6760a((int) R.string.toast_no_following);
            return;
        }
        WrapFollowingsFragment wrapFollowingsFragment = new WrapFollowingsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", getUser().getUserId());
        wrapFollowingsFragment.setArguments(bundle);
        launchFragment(wrapFollowingsFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewFollowers() {
        if (getUser().getFollowersCount() <= 0) {
            PopupsUtils.m6760a((int) R.string.toast_no_follower);
            return;
        }
        WrapFollowersFragment wrapFollowersFragment = new WrapFollowersFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", getUser().getUserId());
        wrapFollowersFragment.setArguments(bundle);
        launchFragment(wrapFollowersFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleFollow() {
        if (EnvironmentUtils.DeviceConfig.isConnected()) {
            this.mToggleFollowView.setText(R.string.is_processing);
            TTPodUser user = getUser();
            long userId = user.getUserId();
            if (((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_FOLLOWED, Long.valueOf(user.getUserId())), Boolean.class)).booleanValue()) {
                CommandCenter.getInstance().execute(new Command(CommandID.UNFOLLOW_FRIEND, Long.valueOf(userId), ""));
            } else {
                CommandCenter.getInstance().execute(new Command(CommandID.FOLLOW_FRIEND, Long.valueOf(userId), ""));
            }
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_USER_FOLLOW.getValue(), SPage.PAGE_CIRCLE_USER_HOME.getValue()).post();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPrivateMessage() {
        PrivateMessageFragment privateMessageFragment = new PrivateMessageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", getUser());
        privateMessageFragment.setArguments(bundle);
        launchFragment(privateMessageFragment);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_USER_PRIVATE_MESSAGE.getValue(), SPage.PAGE_CIRCLE_USER_HOME.getValue()).post();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUserInfo() {
        if (isMe()) {
            MusicCircleStatistic.m7987a();
            startActivity(new Intent(getActivity(), UserInfoActivity.class).putExtra(UserInfoActivity.BUNDLEKEY_LOGOUT_VISIBLE, false));
            return;
        }
        startActivity(new Intent(getActivity(), StaticUserInfoActivity.class).putExtra("user", getUser()));
    }
}
