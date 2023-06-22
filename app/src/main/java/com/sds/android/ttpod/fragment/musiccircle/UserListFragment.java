package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.MusiccircleFooter;
import com.sds.android.ttpod.adapter.p073e.UserListAdapter;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class UserListFragment<Data extends TTPodUser> extends BaseFragment implements AdapterView.OnItemClickListener {
    public static final int PAGE_SIZE = 50;
    private UserListAdapter<Data> mAdapter;
    private MusiccircleFooter mFooter;
    private ListView mListView;
    private View mReloadView;
    private StateView mStateView;
    private List<Data> mUsers = new ArrayList();
    private Pager mPager = new Pager();
    private RequestState mRequestState = RequestState.IDLE;

    protected abstract UserListAdapter<Data> onCreateAdapter(List<Data> list);

    protected abstract void onRequestData(int i, int i2);

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            CommandCenter.m4607a().m4606a(new Command(CommandID.SET_LOGIN_UID, Long.valueOf(m2954aq.getUserId())));
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_user_list_layout, (ViewGroup) null, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.user_loadingview);
        this.mListView = (ListView) this.mStateView.findViewById(R.id.user_listview);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.UserListFragment.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (ListViewUtils.m8262b(i, i2, i3) && UserListFragment.this.mRequestState != RequestState.REQUESTING) {
                    int m4662d = UserListFragment.this.mPager.m4662d();
                    if (!UserListFragment.this.mPager.m4661d(m4662d)) {
                        UserListFragment.this.mPager.m4663c(m4662d);
                        UserListFragment.this.requestData(UserListFragment.this.mPager.m4669a(), 50);
                    }
                }
            }
        });
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mReloadView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.UserListFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.C0604c.m8474e()) {
                    UserListFragment.this.reload();
                } else {
                    PopupsUtils.m6760a((int) R.string.network_error);
                }
            }
        });
        this.mFooter = new MusiccircleFooter(layoutInflater, new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.UserListFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserListFragment.this.reload();
            }
        });
        this.mListView.addFooterView(this.mFooter.m7934a());
        this.mAdapter = onCreateAdapter(this.mUsers);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        return inflate;
    }

    public int getDataCount() {
        return this.mUsers.size();
    }

    public void addData(List<Data> list, boolean z) {
        if (list.isEmpty()) {
            this.mRequestState = RequestState.REQUESTED_FAIL;
            this.mFooter.m7932a(true, 8, "加载失败，点击重试...");
            setLoadingState(StateView.EnumC2248b.FAILED);
            return;
        }
        if (!z) {
            this.mUsers.clear();
        }
        this.mUsers.addAll(list);
        this.mAdapter.m7663a((List<Data>) this.mUsers);
        this.mRequestState = RequestState.REQUESTED_SUCCESS;
        this.mFooter.m7932a(false, 8, this.mUsers.size() + " 条数据");
        setLoadingState(StateView.EnumC2248b.SUCCESS);
    }

    public void notifyDataSetChanged() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void setPageTotal(int i) {
        if (i >= 1) {
            this.mPager.m4665b(i);
        }
    }

    public void setPageStart(int i) {
        this.mPager.m4668a(i);
    }

    public void setPageCurrent(int i) {
        this.mPager.m4663c(i);
    }

    public void setLoadingState(StateView.EnumC2248b enumC2248b) {
        this.mStateView.setState(enumC2248b);
    }

    public void requestData(int i, int i2) {
        this.mRequestState = RequestState.REQUESTING;
        this.mFooter.m7932a(false, 0, getString(R.string.loading));
        onRequestData(i, i2);
    }

    public void reload() {
        this.mUsers.clear();
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        requestData(this.mPager.m4669a(), 50);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (m8266a > -1) {
            this.mListView.getContext();
            if (Preferences.m2954aq() != null) {
                WrapUserPostListFragment createUserPostListFragmentByUser = WrapUserPostListFragment.createUserPostListFragmentByUser((TTPodUser) this.mAdapter.getItem(m8266a), onCreateOrigin());
                createUserPostListFragmentByUser.getArguments().putString("title", onCreateTitle());
                launchFragment(createUserPostListFragmentByUser);
                onItemClickEvent(adapterView, view, m8266a, j);
                return;
            }
            EntryUtils.m8297a(true);
        }
    }

    protected String onCreateOrigin() {
        return "";
    }

    protected String onCreateTitle() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_SYNC_FOLLOWING_FINISHED, ReflectUtils.m8375a(cls, "updateSyncFollowingFinished", new Class[0]));
        map.put(CommandID.UPDATE_FOLLOW_FRIEND, ReflectUtils.m8375a(cls, "updateFollow", BaseResult.class, String.class));
        map.put(CommandID.UPDATE_UNFOLLOW_FRIEND, ReflectUtils.m8375a(cls, "updateUnFollow", BaseResult.class, String.class));
    }

    public void updateFollow(BaseResult baseResult, String str) {
        this.mAdapter.notifyDataSetChanged();
    }

    public void updateUnFollow(BaseResult baseResult, String str) {
        this.mAdapter.notifyDataSetChanged();
    }

    public void updateSyncFollowingFinished() {
        this.mAdapter.notifyDataSetChanged();
    }
}
