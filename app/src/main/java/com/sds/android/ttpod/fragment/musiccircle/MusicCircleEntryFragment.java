package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sds.android.cloudapi.ttpod.data.NewNoticeCount;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.NewNoticeCountResult;

import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.IdListResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.FavoritePostsFragment;
import com.sds.android.ttpod.activities.musiccircle.FindFriendsFragment;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment;
import com.sds.android.ttpod.activities.musiccircle.p068a.CheckObserver;
import com.sds.android.ttpod.activities.musiccircle.p068a.Checker;
import com.sds.android.ttpod.activities.musiccircle.p068a.CheckerManager;
import com.sds.android.ttpod.activities.musiccircle.p068a.MessageChecker;
import com.sds.android.ttpod.activities.musiccircle.p068a.MusicCircleChecker;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MusicCircleEntryFragment extends HeaderPostListFragment implements CheckObserver, PostListByIdFragment.InterfaceC1690a {
    private static final int POST_CACHE_SIZE = 10;
    private static final int REQUEST_CODE_MESSAGE = 1;
    private ActionBarController.C1070a mAddFriendsAction;
    private Handler mHandler = new Handler();
    private boolean mHasNewData;
    private NewNoticeCount mNewNoticeCount;
    private View mOfflineModeView;
    private ActionBarController.C1070a mRefreshAction;
    private TTPodUser mUser;

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mHasNewData = arguments.getBoolean("new_flag", false);
        }
        this.mUser = (TTPodUser) arguments.getSerializable("user");
        setOnRequestDataListener(this);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initActionBar();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (MusicCircleEntryFragment.this.isMe()) {
                    MusicCircleEntryFragment.this.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(Preferences.m2954aq(), "music-circle"));
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_CLICK_MY_HEADER.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).post();
                }
            }
        };
        getAvatar().setOnClickListener(onClickListener);
        getNickName().setOnClickListener(onClickListener);
    }

    private void initActionBar() {
        setActionVisible();
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7189b(R.string.music_circle_title);
        actionBarController.onThemeLoaded();
    }

    private void setActionVisible() {
        this.mRefreshAction.m7155c(false);
        this.mAddFriendsAction.m7155c(true);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mUser == null) {
            CheckerManager.m7949a().m7943b();
            CheckerManager.m7949a().m7942b(this);
            finish();
        } else {
            CheckerManager.m7949a().m7941c();
            CheckerManager.m7949a().m7947a(this);
        }
        //OnlineMediaStatistic.m5045a("music-circle");
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        CheckerManager.m7949a().m7943b();
        CheckerManager.m7949a().m7942b(this);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        super.onBackPressed();
        CommandCenter.getInstance().m4599a(this);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mOfflineModeView = null;
        this.mAddFriendsAction.m7167a((ActionBarController.InterfaceC1072b) null);
        setOnRequestDataListener(null);
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment
    protected View onLoadHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.musiccircle_entry_profile_header, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onLoginFinished(CommonResult commonResult) {
        super.onLoginFinished(commonResult);
        setUser(Preferences.m2954aq());
        onBindView();
        setActionVisible();
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.p068a.CheckObserver
    public void onCheckFinished(Checker checker, BaseResult baseResult) {
        if (checker instanceof MusicCircleChecker) {
            PopupsUtils.m6721a("有新的数据，可下拉刷新。");
        } else if (checker instanceof MessageChecker) {
            this.mNewNoticeCount = ((NewNoticeCountResult) baseResult).getData();
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            this.mNewNoticeCount = (NewNoticeCount) intent.getSerializableExtra("new_notice_count");
            if (this.mNewNoticeCount == null || this.mNewNoticeCount.getNewMessageTotalCount() == 0) {
                CheckerManager.m7949a().m7944a(MessageChecker.class);
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeUtils.m8161b(this.mRefreshAction, ThemeElement.TOP_BAR_REFRESH_IMAGE, R.string.icon_refresh_with_one_arrow, ThemeElement.TOP_BAR_TEXT);
        ThemeUtils.m8168a(this.mAddFriendsAction, ThemeElement.TOP_BAR_ADD_FRIENDS_IMAGE, (int) R.string.icon_add_friend, ThemeElement.TOP_BAR_TEXT);
        getActionBarController().onThemeLoaded();
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needMenuAction() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected void addCustomActions() {
        ActionBarController actionBarController = getActionBarController();
        this.mRefreshAction = actionBarController.m7178d(R.drawable.img_actionitem_refresh);
        this.mAddFriendsAction = actionBarController.m7178d(R.drawable.img_musiccircle_find_friend);
        this.mAddFriendsAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment.2
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                Preferences.m3059K(false);
                MusicCircleEntryFragment.this.setFindFriendGuideStubVisible(false);
                MusicCircleEntryFragment.this.launchFragment(new FindFriendsFragment());
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_FIND_FRIENDS.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).post();
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected Collection<ActionItem> onCreateDropDownMenu() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ActionItem(25, 0, (int) R.string.menu_my_info));
        arrayList.add(new ActionItem(26, 0, (int) R.string.menu_my_favorite));
        arrayList.add(new ActionItem(27, 0, (int) R.string.menu_message));
        arrayList.add(new ActionItem(28, 0, (int) R.string.menu_exit));
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        super.onDropDownMenuClicked(i, actionItem);
        switch (i) {
            case 25:
                EntryUtils.m8288g(getActivity());
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_MY_PROFILE.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).post();
                return;
            case 26:
                launchFragment(new FavoritePostsFragment());
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_MY_FAVORITE.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).post();
                return;
            case 27:
                MessageEntryFragment messageEntryFragment = new MessageEntryFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("new_notice_count", this.mNewNoticeCount);
                bundle.putInt("id", 1);
                messageEntryFragment.setArguments(bundle);
                messageEntryFragment.setOriginFragment(this);
                launchFragment(messageEntryFragment);
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_MESSAGE.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).post();
                return;
            case 28:
                CommandCenter.getInstance().execute(new Command(CommandID.LOGOUT, new Object[0]));
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment.InterfaceC1690a
    public void onRequestDataStarted() {
        startRefreshAnimation();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment.InterfaceC1690a
    public void onRequestDataFinished() {
        stopRefreshAnimation();
    }

    private void startRefreshAnimation() {
        if (this.mRefreshAction != null) {
            this.mRefreshAction.m7155c(true);
            this.mRefreshAction.m7152e();
        }
    }

    private void stopRefreshAnimation() {
        if (this.mRefreshAction != null) {
            this.mRefreshAction.m7149g();
            this.mRefreshAction.m7155c(false);
        }
    }

    public void setFindFriendGuideStubVisible(boolean z) {
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        onBindView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment
    public void onBindView() {
        if (OfflineModeUtils.m8256a()) {
            this.mOfflineModeView = OfflineModeUtils.m8253a(getRootView().findViewById(R.id.activity_body), new OfflineModeUtils.InterfaceC0635a() { // from class: com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment.3
                @Override // com.sds.android.ttpod.p067a.OfflineModeUtils.InterfaceC0635a
                /* renamed from: a */
                public void mo5379a() {
                    MusicCircleEntryFragment.super.onBindView();
                    MusicCircleEntryFragment.this.onLoadData();
                }
            });
            return;
        }
        if (this.mOfflineModeView != null) {
            this.mOfflineModeView.setVisibility(View.GONE);
        }
        super.onBindView();
        onLoadData();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onLoadData() {
        this.mUser = Preferences.m2954aq();
        if (this.mUser != null) {
            updateFooter(false, 0, getString(R.string.loading));
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    if (MusicCircleEntryFragment.this.mUser != null) {
                        final List<Post> m3216a = Cache.getInstance().m3216a(MusicCircleEntryFragment.this.mUser.getUserId());
                        if (MusicCircleEntryFragment.this.mHandler != null) {
                            MusicCircleEntryFragment.this.mHandler.post(new Runnable() { // from class: com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (!ListUtils.m4718a(m3216a)) {
                                        MusicCircleEntryFragment.this.addPosts(m3216a);
                                    }
                                }
                            });
                        }
                    }
                }
            });
            onRequestPostIds();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment
    public void onRequestPostIds() {
        super.onRequestPostIds();
        if (getUser() != null) {
            CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_TIMELINE_USER_POST_IDS, onLoadOrigin()));
        }
        CheckerManager m7949a = CheckerManager.m7949a();
        m7949a.m7944a(MusicCircleChecker.class);
        m7949a.m7939d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public String onLoadOrigin() {
        return "time_line_post";
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2284a
    public void onPullToRefresh(View view) {
        if (isMe()) {
            super.onPullToRefresh(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment, com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_TIMELINE_USER_POST_IDS, ReflectUtils.m8375a(getClass(), "updateTimelineUserPostIds", IdListResult.class, String.class));
    }

    public void updateTimelineUserPostIds(IdListResult idListResult, String str) {
        if (onLoadOrigin().equals(str)) {
            setPostIds(idListResult.getDataList(), true);
            setIsRefreshing(true);
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment
    protected void onSavePostInfo(List<Post> list) {
        if (list != null) {
            final long userId = this.mUser == null ? 0L : this.mUser.getUserId();
            final ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 10 && i < list.size(); i++) {
                arrayList.add(list.get(i));
            }
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    Cache.getInstance().m3215a(userId, arrayList);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onPlayEvent(Post post) {
        super.onPlayEvent(post);
        MusicCircleStatistic.m7984a(post.getPostId(), post.getUser().getNickName());
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_CLICK_PLAY_POST.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).post();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onEntryDetail(Post post) {
        super.onEntryDetail(post);
        MusicCircleStatistic.m7979b(post.getPostId(), post.getUser().getNickName());
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_CLICK_POST.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).post();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onFavoriteEvent(Post post, boolean z) {
        super.onFavoriteEvent(post, z);
        MusicCircleStatistic.m7976c(post.getPostId(), post.getUser().getNickName());
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_CLICK_POST_FAVOR.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).append("status", Integer.valueOf(z ? 1 : 0)).post();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onBindReplyClick(Post post) {
        super.onBindReplyClick(post);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_CLICK_POST_REPLY.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).post();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onBindRepostClick(Post post) {
        super.onBindRepostClick(post);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_CLICK_POST_FORWARD.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).post();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onUserAvatarClick() {
        super.onUserAvatarClick();
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_CLICK_USER_HEADER.getValue(), SPage.PAGE_CIRCLE_MY_HOME.getValue()).post();
    }
}
