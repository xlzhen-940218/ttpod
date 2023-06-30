package com.sds.android.ttpod.fragment.musiccircle;

import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.Pager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class PostListByIdFragment extends PostListFragment {
    private static final int DEFAULT_PAGER_TOTAL = 1;
    private InterfaceC1690a mOnRequestDataListener;
    private Pager mPager = new Pager();
    private List<List<Long>> mPostIds = new ArrayList();

    /* renamed from: com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1690a {
        void onRequestDataFinished();

        void onRequestDataStarted();
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void reload() {
        super.reload();
        if (this.mPostIds.isEmpty()) {
            onRequestPostIds();
        } else {
            requestPosts(this.mPostIds.get(this.mPager.m4669a()));
        }
    }

    public void setOnRequestDataListener(InterfaceC1690a interfaceC1690a) {
        this.mOnRequestDataListener = interfaceC1690a;
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void refresh() {
        if (getRequestState() != RequestState.REQUESTING) {
            super.refresh();
            onRequestPostIds();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRequestPostIds() {
        setIsRefreshing(true);
        setRequestState(RequestState.REQUESTING);
        if (this.mOnRequestDataListener != null) {
            this.mOnRequestDataListener.onRequestDataStarted();
        }
    }

    public void setPostIds(List<Long> list, boolean z) {
        if (list.isEmpty()) {
            setRequestState(RequestState.REQUESTED_FAIL);
            updateFooter(true, 8, "加载失败，点击重试...");
            if (this.mOnRequestDataListener != null) {
                this.mOnRequestDataListener.onRequestDataFinished();
                return;
            }
            return;
        }
        this.mPostIds.clear();
        this.mPostIds.addAll(Pager.m4667a(list, 20));
        this.mPager.m4665b(this.mPostIds.size());
        this.mPager.m4668a(0);
        this.mPager.m4663c(0);
        if (z) {
            requestPosts(this.mPostIds.get(this.mPager.m4669a()));
        }
    }

    private void requestPosts(List<Long> list) {
        updateFooter(false, 0, getString(R.string.loading));
        setRequestState(RequestState.REQUESTING);
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_POST_INFOS_BY_ID, list, onLoadOrigin()));
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    protected void onRequestNextPage() {
        if (this.mPager.m4664c() != 1) {
            requestPosts(this.mPostIds.get(this.mPager.m4669a()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_POST_INFO_LIST_BY_ID, ReflectUtils.loadMethod(getClass(), "updatePostInfoList", PostResult.class, String.class));
    }

    public final void updatePostInfoList(PostResult postResult, String str) {
        if (onLoadOrigin().equals(str)) {
            addPosts(postResult.getDataList());
            onSavePostInfo(getPosts());
            if (this.mOnRequestDataListener != null) {
                this.mOnRequestDataListener.onRequestDataFinished();
            }
            if (postResult.isSuccess()) {
                if (!this.mPager.m4661d(this.mPager.m4662d())) {
                    this.mPager.m4663c(this.mPager.m4662d());
                } else {
                    getListView().setOnScrollListener(null);
                }
            }
        }
    }

    protected void onSavePostInfo(List<Post> list) {
    }
}
