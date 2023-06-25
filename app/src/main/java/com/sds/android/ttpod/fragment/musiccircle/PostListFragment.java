package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.CommentsFragment;
import com.sds.android.ttpod.activities.musiccircle.MusiccircleFooter;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.adapter.p073e.PostListAdapter;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ListViewUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class PostListFragment extends SlidingClosableFragment implements AdapterView.OnItemClickListener {
    public static final int PAGE_SIZE = 20;
    private PostListAdapter mAdapter;
    private MusiccircleFooter mFooter;
    private boolean mIsRefreshing;
    private ListView mListView;
    private RequestState mRequestState = RequestState.IDLE;
    protected boolean mIsFirstRequestNextPage = false;

    protected abstract View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected abstract void onLoadData();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String onLoadOrigin();

    protected abstract void onRequestNextPage();

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            CommandCenter.getInstance().m4606a(new Command(CommandID.SET_LOGIN_UID, Long.valueOf(m2954aq.getUserId())));
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mListView = null;
    }

    public void initView(LayoutInflater layoutInflater, ListView listView) {
        this.mListView = listView;
        this.mAdapter = onCreateAdapter(getActivity(), new ArrayList());
        View onCreateHeaderView = onCreateHeaderView(layoutInflater, this.mListView);
        if (onCreateHeaderView != null) {
            this.mListView.addHeaderView(onCreateHeaderView);
        }
        this.mFooter = new MusiccircleFooter(layoutInflater, new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.PostListFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PostListFragment.this.reload();
            }
        });
        this.mListView.addFooterView(this.mFooter.m7934a());
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.PostListFragment.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (ListViewUtils.m8262b(i, i2, i3) && PostListFragment.this.mRequestState != RequestState.REQUESTING && !PostListFragment.this.mIsFirstRequestNextPage) {
                    PostListFragment.this.onRequestNextPage();
                }
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            CommandCenter.getInstance().m4602a(new Command(CommandID.IS_FOLLOWED, Long.valueOf(m2954aq.getUserId())), Boolean.class);
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mFooter != null) {
            this.mFooter.onThemeLoaded();
        }
        refreshListViewTheme();
    }

    protected void refreshListViewTheme() {
        if (this.mListView != null && this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    protected PostListAdapter onCreateAdapter(Context context, List<Post> list) {
        PostListAdapter postListAdapter = new PostListAdapter(context, list, onLoadOrigin()) { // from class: com.sds.android.ttpod.fragment.musiccircle.PostListFragment.3
            @Override // com.sds.android.ttpod.adapter.p073e.PostListAdapter
            /* renamed from: a */
            protected void mo5430a(TTPodUser tTPodUser) {
                PostListFragment.this.onUserAvatarClick();
                PostListFragment.this.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(tTPodUser, PostListFragment.this.onLoadOrigin()));
            }

            @Override // com.sds.android.ttpod.adapter.p073e.PostListAdapter
            /* renamed from: a */
            protected void mo5432a(Post post) {
                PostListFragment.this.onPlayEvent(post);
            }

            @Override // com.sds.android.ttpod.adapter.p073e.PostListAdapter
            /* renamed from: a */
            protected void mo5431a(Post post, boolean z) {
                PostListFragment.this.onFavoriteEvent(post, z);
            }

            @Override // com.sds.android.ttpod.adapter.p073e.PostListAdapter
            /* renamed from: b */
            protected void mo5437b(Post post) {
                PostListFragment.this.onBindReplyClick(post);
            }

            @Override // com.sds.android.ttpod.adapter.p073e.PostListAdapter
            /* renamed from: c */
            protected void mo5436c(Post post) {
                PostListFragment.this.onBindRepostClick(post);
            }
        };
        postListAdapter.m7482a(new CommentsFragment.InterfaceC0785b() { // from class: com.sds.android.ttpod.fragment.musiccircle.PostListFragment.4
            @Override // com.sds.android.ttpod.activities.musiccircle.CommentsFragment.InterfaceC0785b
            /* renamed from: a */
            public void mo5429a(long j, long j2) {
                PostListFragment.this.launchFragment(CommentsFragment.createCommentsFragment(j, j2));
            }
        });
        return postListAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onEntryDetail(Post post) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPlayEvent(Post post) {
        //OnlineMediaStatistic.m5054a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onFavoriteEvent(Post post, boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBindReplyClick(Post post) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBindRepostClick(Post post) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onUserAvatarClick() {
    }

    public void reload() {
        this.mRequestState = RequestState.REQUESTING;
        this.mFooter.m7932a(false, 0, getString(R.string.loading));
    }

    public void refresh() {
        this.mIsRefreshing = true;
        this.mRequestState = RequestState.REQUESTING;
        this.mFooter.m7932a(false, 0, getString(R.string.loading));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        onLoadData();
    }

    public void setRequestState(RequestState requestState) {
        this.mRequestState = requestState;
    }

    public RequestState getRequestState() {
        return this.mRequestState;
    }

    public View getFooterView() {
        return this.mFooter.m7934a();
    }

    public void updateFooter(boolean z, int i, String str) {
        this.mFooter.m7932a(z, i, str);
    }

    public void addPosts(List<Post> list) {
        if (isAdded()) {
            if (list.isEmpty()) {
                this.mRequestState = RequestState.REQUESTED_FAIL;
                this.mFooter.m7932a(true, 8, getString(R.string.loading_failed));
            } else {
                this.mRequestState = RequestState.REQUESTED_SUCCESS;
                if (this.mIsRefreshing) {
                    this.mAdapter.m7663a((List) list);
                } else {
                    this.mAdapter.m7463b(list);
                }
                this.mFooter.m7932a(false, 8, getString(R.string.num_loaded_data, Integer.valueOf(this.mAdapter.getCount())));
            }
            this.mIsRefreshing = false;
        }
    }

    public void setIsRefreshing(boolean z) {
        this.mIsRefreshing = z;
    }

    public List<Post> getPosts() {
        return this.mAdapter == null ? new ArrayList() : this.mAdapter.m7662b();
    }

    public int getDataCount() {
        if (this.mAdapter == null) {
            return 0;
        }
        return this.mAdapter.getCount();
    }

    public void setPlayStatus(PlayStatus playStatus) {
        this.mAdapter.m7469a(playStatus);
    }

    public void updateReplyCount(Post post) {
        this.mAdapter.m7461e(post);
    }

    public void setPlayingSongId(Long l) {
        this.mAdapter.m7468a(l);
    }

    public void updateRepostCount(Post post) {
        this.mAdapter.m7460f(post);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "playMediaChanged", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_SYNC_FAVORITE_POST_FINISHED, ReflectUtils.m8375a(cls, "onSyncFavoritePostFinished", new Class[0]));
        map.put(CommandID.UPDATE_ADD_FAVORITE_POSTS, ReflectUtils.m8375a(cls, "onAddFavoriteFinished", BaseResult.class, String.class));
        map.put(CommandID.UPDATE_REMOVE_FAVORITE_POSTS, ReflectUtils.m8375a(cls, "onRemoveFavoriteFinished", BaseResult.class, String.class));
        map.put(CommandID.LOGIN_FINISHED, ReflectUtils.m8375a(cls, "onLoginFinished", CommonResult.class));
        map.put(CommandID.UPDATE_POST_REPLY_COUNT, ReflectUtils.m8375a(cls, "updatePostReplyCount", Post.class));
        map.put(CommandID.UPDATE_POST_REPOST_COUNT, ReflectUtils.m8375a(cls, "updatePostRepostCount", Post.class));
    }

    public void updatePostReplyCount(Post post) {
        this.mAdapter.m7461e(post);
    }

    public void updatePostRepostCount(Post post) {
        this.mAdapter.m7460f(post);
    }

    public void onLoginFinished(CommonResult commonResult) {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            CommandCenter.getInstance().m4606a(new Command(CommandID.SET_LOGIN_UID, Long.valueOf(m2954aq.getUserId())));
        }
    }

    public void onSyncFavoritePostFinished() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void onAddFavoriteFinished(BaseResult baseResult, String str) {
        this.mAdapter.notifyDataSetChanged();
    }

    public void onRemoveFavoriteFinished(BaseResult baseResult, String str) {
        this.mAdapter.notifyDataSetChanged();
    }

    public void playMediaChanged() {
        this.mAdapter.m7468a(Cache.getInstance().getCurrentPlayMediaItem().getSongID());
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        this.mAdapter.m7469a(playStatus);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a;
        if (this.mListView != null && this.mAdapter != null && (m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount())) > -1) {
            Post item = this.mAdapter.getItem(m8266a);
            this.mAdapter.mo7467a(item);
            launchFragment(SubPostDetailFragment.createByPost(item, onLoadOrigin()));
            onEntryDetail(PostUtils.m4029a(item));
        }
    }

    public ListView getListView() {
        return this.mListView;
    }
}
