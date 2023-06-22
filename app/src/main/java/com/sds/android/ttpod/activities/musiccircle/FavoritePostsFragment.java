package com.sds.android.ttpod.activities.musiccircle;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MessageCollectItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p073e.FavoriteAdapter;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class FavoritePostsFragment extends SlidingClosableFragment implements AdapterView.OnItemClickListener, DragUpdateHelper.InterfaceC2273c {
    private FavoriteAdapter mFavoriteAdapter;
    private MusiccircleFooter mFooter;
    private DragUpdateListView mListView;
    private StateView mStateView;
    private List<Post> mFavoritePosts = new ArrayList();
    private List<List<MessageCollectItem>> mFavoriteMessages = new ArrayList();
    private RequestState mRequestState = RequestState.IDLE;
    private Pager mPager = new Pager();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_favorite_layout, viewGroup, false);
        initView(inflate);
        requestFavorites();
        return inflate;
    }

    private void loadNoDataView() {
        View inflate = View.inflate(getActivity(), R.layout.musiccircle_message_empty, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
        ((TextView) inflate.findViewById(R.id.note1)).setText("亲，你还没有收藏~");
        ((TextView) inflate.findViewById(R.id.note2)).setVisibility(View.VISIBLE);
    }

    private void loadNetworkErrorView() {
        View inflate = View.inflate(getActivity(), R.layout.loadingview_failed, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.FavoritePostsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.C0604c.m8474e()) {
                    FavoritePostsFragment.this.mStateView.setState(StateView.EnumC2248b.LOADING);
                    FavoritePostsFragment.this.requestFavorites();
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_error);
            }
        });
    }

    private void toggleFailedView() {
        if (EnvironmentUtils.C0604c.m8474e()) {
            loadNoDataView();
        } else {
            loadNetworkErrorView();
        }
    }

    private void initView(View view) {
        getActionBarController().m7189b(R.string.musiccircle_favorite_title);
        this.mStateView = (StateView) view.findViewById(R.id.musiccircle_favorite_loading_view);
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        this.mFavoriteAdapter = new FavoriteAdapter(getActivity(), this.mFavoritePosts);
        this.mFooter = new MusiccircleFooter(getActivity().getLayoutInflater(), new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.FavoritePostsFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                FavoritePostsFragment.this.requestFavoritePost((List) FavoritePostsFragment.this.mFavoriteMessages.get(FavoritePostsFragment.this.mPager.m4669a()));
            }
        });
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.addFooterView(this.mFooter.m7934a());
        this.mListView.setAdapter((ListAdapter) this.mFavoriteAdapter);
        this.mListView.setOnStartRefreshListener(this);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setVerticalScrollBarEnabled(false);
        this.mListView.setSelector(new ColorDrawable(0));
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.activities.musiccircle.FavoritePostsFragment.3
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (ListViewUtils.m8262b(i, i2, i3) && FavoritePostsFragment.this.mRequestState != RequestState.REQUESTING) {
                    int m4662d = FavoritePostsFragment.this.mPager.m4662d();
                    if (!FavoritePostsFragment.this.mPager.m4661d(m4662d)) {
                        FavoritePostsFragment.this.mPager.m4663c(m4662d);
                        FavoritePostsFragment.this.requestFavoritePost((List) FavoritePostsFragment.this.mFavoriteMessages.get(FavoritePostsFragment.this.mPager.m4669a()));
                    }
                }
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mFavoriteAdapter.getCount());
        if (m8266a > -1) {
            launchFragment(SubPostDetailFragment.createByPost(this.mFavoriteAdapter.getItem(m8266a), "favorite"));
        }
    }

    @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
    public void onStartRefreshEvent() {
        if (this.mRequestState != RequestState.REQUESTING) {
            requestFavorites();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_POST_INFO_LIST_BY_ID, ReflectUtils.m8375a(getClass(), "updatePosts", PostResult.class, String.class));
        map.put(CommandID.UPDATE_ADD_FAVORITE_POSTS, ReflectUtils.m8375a(getClass(), "onFavoritePostsChanged", BaseResult.class, String.class));
        map.put(CommandID.UPDATE_REMOVE_FAVORITE_POSTS, ReflectUtils.m8375a(getClass(), "onFavoritePostsChanged", BaseResult.class, String.class));
    }

    public void onFavoritePostsChanged(BaseResult baseResult, String str) {
        requestFavorites();
    }

    public void updatePosts(PostResult postResult, String str) {
        if ("favorite".equals(str)) {
            ArrayList<Post> dataList = postResult.getDataList();
            if (dataList.isEmpty()) {
                this.mFavoritePosts.clear();
                toggleFailedView();
                this.mRequestState = RequestState.REQUESTED_FAIL;
                this.mFooter.m7932a(true, 8, getString(R.string.loading_failed));
            } else {
                this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                this.mRequestState = RequestState.REQUESTED_SUCCESS;
                this.mFavoritePosts = dataList;
                this.mFavoriteAdapter.m7663a((List) this.mFavoritePosts);
                this.mFooter.m7932a(false, 8, getString(R.string.num_loaded_data, Integer.valueOf(this.mFavoriteAdapter.getCount())));
            }
            this.mListView.m1336b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFavoritePost(List<MessageCollectItem> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (MessageCollectItem messageCollectItem : list) {
            arrayList.add(Long.valueOf(messageCollectItem.getId()));
        }
        this.mFooter.m7932a(false, 0, getString(R.string.loading));
        CommandCenter.m4607a().m4606a(new Command(CommandID.REQUEST_POST_INFOS_BY_ID, arrayList, "favorite"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFavorites() {
        this.mRequestState = RequestState.REQUESTING;
        CommandCenter.m4607a().m4606a(new Command(CommandID.REQUEST_FAVORITE_SONG_LIST_POSTS, new Object[0]));
    }
}
