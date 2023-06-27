package com.sds.android.ttpod.activities.musiccircle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.PostResult;


import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.main.DiscoveryStatistic;
import com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment;
import com.sds.android.ttpod.fragment.musiccircle.IntroductionFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.MediaItemListResult;
import com.sds.android.ttpod.framework.modules.p124f.MusiccircleContentType;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ViewUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.widget.StateView;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public class PostDetailFragment extends ImageHeaderMusicListFragment implements ThemeManager.InterfaceC2019b {
    private String mOrigin;
    private Post mOriginPost;
    private Post mPost;
    private long mPostId;
    private MusiccircleFooter mSecondLoadView;
    private PostDetailHeader mPostDetailHeader = new PostDetailHeader.C0797a();
    private Recomment mPersonalizedRecommendInfo = new C0793a();
    private View.OnClickListener mHeaderButtonClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.PostDetailFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!ViewUtils.m4641a()) {
                switch (view.getId()) {
                    case R.id.post_header_back /* 2131231642 */:
                    case R.id.post_header_title /* 2131231643 */:
                        PostDetailFragment.this.onBackPressed();
                        return;
                    case R.id.post_header_user_click_bounds /* 2131231852 */:
                        PostDetailFragment.this.goUserHome();
                        return;
                    case R.id.post_header_tweet_bounds /* 2131231856 */:
                        PostDetailFragment.this.viewPostInfo();
                        return;
                    case R.id.post_header_favorite /* 2131231860 */:
                        PostDetailFragment.this.favoritePost();
                        return;
                    case R.id.post_header_comment /* 2131231862 */:
                        PostDetailFragment.this.viewComments();
                        return;
                    case R.id.post_header_share /* 2131231864 */:
                        PostDetailFragment.this.sharePost();
                        return;
                    case R.id.post_header_download /* 2131231866 */:
                        if (!PostDetailFragment.this.isSongListNotLoaded()) {
                            PostDetailFragment.this.downloadPost();
                            return;
                        }
                        return;
                    case R.id.post_header_play /* 2131231868 */:
                        if (!PostDetailFragment.this.isSongListNotLoaded()) {
                            PostDetailFragment.this.playPost();
                            return;
                        }
                        return;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }
    };

    public void setPostId(long j) {
        this.mPostId = j;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateContentView(layoutInflater, viewGroup, bundle);
    }



    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected String onLoadTitleText() {
        return "";
    }



    @SuppressLint("RestrictedApi")
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected void setupListHeader() {
        this.mPostDetailHeader = new PostDetailHeader(getActivity(), getLayoutInflater(null), this.mOnlineMediaListFragment.getListView());
        this.mOnlineMediaListFragment.getListView().addHeaderView(this.mPostDetailHeader.m7931a());
        this.mPostDetailHeader.m7930a(this.mHeaderButtonClickListener);
        this.mSecondLoadView = new MusiccircleFooter(getLayoutInflater(null), null);
        this.mOnlineMediaListFragment.getListView().addFooterView(this.mSecondLoadView.m7934a());
    }

    private String origin() {
        return getArguments().getString("origin") + "_" + this.mOriginPost.getId() + "_" + ((Object) this.mOriginPost.getTitleName());
    }

    public static PostDetailFragment createById(long j, String str) {
        PostDetailFragment postDetailFragment = new PostDetailFragment();
        Bundle bundle = new Bundle();
        postDetailFragment.mPostId = j;
        if (str == null) {
            str = "";
        }
        postDetailFragment.mOrigin = str;
        postDetailFragment.setArguments(bundle);
        return postDetailFragment;
    }

    public static PostDetailFragment createByPost(Post post, String str) {
        PostDetailFragment postDetailFragment = new PostDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", post);
        postDetailFragment.mPostId = post.getPostId();
        if (str == null) {
            str = "";
        }
        postDetailFragment.mOrigin = str;
        postDetailFragment.setArguments(bundle);
        return postDetailFragment;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void requestDataList(int i) {
        super.requestDataList(i);
        doGetPostDetail(getArguments());
    }

    protected void doGetPostDetail(Bundle bundle) {
        this.mOnlineMediaListFragment.setTotal(1);
        this.mOrigin = getArguments().getString("origin");
        Serializable serializable = bundle.getSerializable("post");
        if (serializable != null) {
            onNewPost((Post) serializable);
            requestPostForSaveCache();
        } else if (this.mPostId > 0) {
            requestPostDetail(this.mPostId);
        }
    }

    private void requestPostForSaveCache() {
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_POST_DETAIL_BY_ID, Arrays.asList(Long.valueOf(this.mPostId)), "we should different from getRequestId()"));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected void beforeOnlineFragmentOnMediaItemClicked(MediaItem mediaItem) {
        if (isPlayingItem() && StringUtils.equals(mediaItem.getID(), Preferences.getMediaId())) {
            if (SupportFactory.getInstance(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PAUSED) {
                CommandCenter.getInstance().execute(new Command(CommandID.ADD_LISTENER_COUNT, Long.valueOf(this.mPostId)));
                return;
            }
            return;
        }
        CommandCenter.getInstance().execute(new Command(CommandID.ADD_LISTENER_COUNT, Long.valueOf(this.mPostId)));
    }

    private void onNewPost(Post post) {
        this.mPost = post;
        this.mOriginPost = PostUtils.m4029a(this.mPost);
        setTitle(this.mOriginPost.getType() < MusiccircleContentType.DJ.value() ? this.mOriginPost.getMediaItem().getTitle() : this.mOriginPost.getSongListName());
        this.mPostDetailHeader.m7929a(this.mOriginPost);
        setPlayingGroupName(OnlinePlayingGroupUtils.m6916a(this.mOriginPost));
        updatePlayStatus(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
        this.mOnlineMediaListFragment.getStateView().setState(StateView.EnumC2248b.SUCCESS);
        this.mSecondLoadView.m7932a(false, 0, getString(R.string.loading));
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_POST_SONG_BY_IDS, PostUtils.m4025c(this.mPost), getRequestId()));
       // FindSongNewStatistic.m5227a(origin());
    }

    private void doStatistic() {
        if (this.mOrigin != null) {
            if (this.mOrigin.startsWith("discovery")) {
                DiscoveryStatistic.m5666a();
            } else if (this.mOrigin.startsWith("home-top")) {
               // FindSongNewStatistic.m5228a(this.mOriginPost.getId(), String.valueOf(this.mOriginPost.getSongListName()));
               // FindSongNewStatistic.m5225b();
            } else if (this.mOrigin.startsWith("first-publish")) {
               // FindSongNewStatistic.m5228a(this.mOriginPost.getId(), String.valueOf(this.mOriginPost.getSongListName()));
               // FindSongNewStatistic.m5223c();
            }
            //OnlineMediaStatistic.m5045a(this.mOrigin);
            //OnlineMediaStatistic.m5054a();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_POST_DETAIL_BY_ID, ReflectUtils.m8375a(cls, "updatePostDetail", PostResult.class, String.class));
        map.put(CommandID.UPDATE_POST_SONG_BY_IDS, ReflectUtils.m8375a(cls, "updatePostSong", MediaItemListResult.class, String.class));
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "playMediaChanged", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mPostDetailHeader.mo7925b();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void doStatisticMediaClick(MediaItem mediaItem) {
        super.doStatisticMediaClick(mediaItem);
       // FindSongNewStatistic.m5215h(origin());
        Preferences.setOnlineMediaListGroupName(OnlinePlayingGroupUtils.m6916a(this.mOriginPost));
        doStatistic();
    }

    public void updatePostSong(MediaItemListResult mediaItemListResult, String str) {
        if (getRequestId().equals(str)) {
            if (mediaItemListResult.m4517a() != null && !mediaItemListResult.m4517a().isEmpty()) {
                updateData(mediaItemListResult.m4517a(), 1);
                this.mSecondLoadView.m7932a(false, 8, "");
                this.mOnlineMediaListFragment.getListView().removeFooterView(this.mSecondLoadView.m7934a());
                return;
            }
            showNetworkError();
        }
    }

    public void updatePostDetail(PostResult postResult, String str) {
        if (str.equals(getRequestId())) {
            if (postResult.isSuccess()) {
                ArrayList<Post> dataList = postResult.getDataList();
                if (!dataList.isEmpty()) {
                    onNewPost(dataList.get(0));
                    return;
                }
                return;
            }
            showNetworkError();
        }
    }

    private String getRequestId() {
        return toString() + this.mPostId;
    }

    private void showNetworkError() {
        updateData(null, 1);
    }

    private void requestPostDetail(long j) {
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_POST_DETAIL_BY_ID, Arrays.asList(Long.valueOf(j)), getRequestId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goUserHome() {
        //createSUserEventAndPost(SAction.ACTION_CLICK_ONLINE_SONG_LIST_USER, SPage.PAGE_ONLINE_MUSIC_CIRCLE_USER_HOME);
        if (Preferences.m2954aq() == null) {
            EntryUtils.m8297a(true);
        } else {
            launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(this.mOriginPost.getUser(), getArguments().getString("origin")));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewPostInfo() {
       // FindSongNewStatistic.m5216g(origin());
        //createSUserEventAndPost(SAction.ACTION_CLICK_ONLINE_SONG_LIST_INTRODUCTION, SPage.PAGE_ONLINE_POST_DETAIL_INTRODUCTION);
        launchFragment((BaseFragment) Fragment.instantiate(getActivity(), IntroductionFragment.class.getName(), buildPostIntroductionArguments()));
    }

    private Bundle buildPostIntroductionArguments() {
        if (this.mPost == null) {
            throw new IllegalArgumentException("Post must cannot be null");
        }
        Bundle bundle = new Bundle();
        Post m4029a = PostUtils.m4029a(this.mPost);
        bundle.putString("name", m4029a.getSongListName());
        if (m4029a.getPicList() != null && m4029a.getPicList().size() > 0) {
            bundle.putString(User.KEY_AVATAR, m4029a.getPicList().get(0));
        }
        bundle.putString("desc", m4029a.getTweet());
        bundle.putString("tags", m4029a.getTags());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playPost() {
       // FindSongNewStatistic.m5224b(origin());
        //createSUserEventAndPost(SAction.ACTION_CLICK_ONLINE_SONG_LIST_PLAY_ALL, null);
        replayPlayMediaRepeat(this.mOriginPost.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadPost() {
       // FindSongNewStatistic.m5222c(origin());
        //createSUserEventAndPost(SAction.ACTION_CLICK_ONLINE_SONG_LIST_DOWNLOAD_ALL, null);
        new DownloadMenuHandler(getActivity()).m6926a(this.mediaItemList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void favoritePost() {
       // FindSongNewStatistic.m5220d(origin());
        if (!EnvironmentUtils.DeviceConfig.isConnected()) {
            PopupsUtils.m6760a((int) R.string.network_unavailable);
        } else if (Preferences.m2954aq() == null) {
            EntryUtils.m8297a(true);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(this.mOriginPost.getId()));
            boolean booleanValue = ((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_FAVORITE_POST, Long.valueOf(this.mOriginPost.getId())), Boolean.class)).booleanValue();
            //createSUserEvent(SAction.ACTION_CLICK_ONLINE_SONG_LIST_FAVORITE, null).append("status", Integer.valueOf(booleanValue ? 0 : 1)).post();
            if (booleanValue) {
                this.mOriginPost.decreaseFavoriteCount();
                CommandCenter.getInstance().execute(new Command(CommandID.REMOVE_FAVORITE_POSTS, arrayList, ""));
                PopupsUtils.m6760a((int) R.string.favorite_canceled);
            } else {
                this.mOriginPost.increaseFavoriteCount();
                CommandCenter.getInstance().execute(new Command(CommandID.ADD_FAVORITE_POSTS, arrayList, ""));
                PopupsUtils.m6760a((int) R.string.added_songlist_to_favorite);
            }
            this.mPostDetailHeader.m7927a(booleanValue ? false : true, this.mOriginPost.getFavoriteCount());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sharePost() {
       // FindSongNewStatistic.m5218e(origin());
       
        //createSUserEventAndPost(SAction.ACTION_CLICK_ONLINE_SONG_LIST_SHARE, null);
        PopupsUtils.m6758a((Activity) getActivity(), this.mOriginPost, this.mOrigin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewComments() {
       // FindSongNewStatistic.m5217f(origin());
        //createSUserEventAndPost(SAction.ACTION_CLICK_ONLINE_SONG_LIST_COMMENTS, SPage.PAGE_ONLINE_POST_DETAIL_COMMENTS);
        launchFragment(CommentsFragment.createCommentsFragment(this.mOriginPost.getId(), this.mOriginPost.getUser().getUserId()));
    }

    /* renamed from: com.sds.android.ttpod.activities.musiccircle.PostDetailFragment$b */
    /* loaded from: classes.dex */
    public static class Recomment {

        /* renamed from: a */
        private int userType;

        /* renamed from: b */
        private int recommentType;

        /* renamed from: c */
        private int recommentAlgorithm;

        public Recomment(int userType, int recommentType, int recommentAlgorithm) {
            this.userType = userType;
            this.recommentType = recommentType;
            this.recommentAlgorithm = recommentAlgorithm;
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.musiccircle.PostDetailFragment$a */
    /* loaded from: classes.dex */
    private class C0793a extends Recomment {
        public C0793a() {
            super(0, 0, 0);
        }
    }

    public void setPersonalizedRecommendInfo(Recomment c0794b) {
        this.mPersonalizedRecommendInfo = c0794b;
    }
}
