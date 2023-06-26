package com.sds.android.ttpod.activities.musiccircle.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.NewFollowers;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.NoticeAPI;
import com.sds.android.cloudapi.ttpod.result.NewFollowersListResult;
import com.sds.android.cloudapi.ttpod.result.NoticeListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.WrapFollowersFragment;
import com.sds.android.ttpod.adapter.p073e.p074a.NoticeAdapter;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.NoticeType;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class NoticeFragment extends SlidingClosableFragment implements AdapterView.OnItemClickListener {
    private static final int PAGE_SIZE = 20;
    private boolean mIsRefreshing;
    private DragUpdateListView mListView;
    private FrameLayout mNewFollowerLayout;
    private TextView mNewFollowerName;
    private View mNewFollowerView;
    private NoticeAdapter mNoticeAdapter;
    private SlidingClosableFragment mOriginFragment;
    private int mRequestCode;
    private int mResultCode;
    private StateView mStateView;
    private List<Notice> mRepostNotices = new ArrayList();
    private List<NewFollowers> mNewFollowerses = new ArrayList();
    private RequestState mRepostNoticeRequestState = RequestState.IDLE;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().m7189b(R.string.musiccircle_notice);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_layout, viewGroup, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.musiccircle_message_loading_view);
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnStartRefreshListener(new DragUpdateHelper.InterfaceC2273c() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.1
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
            public void onStartRefreshEvent() {
                NoticeFragment.this.requestNewFollowerNotices();
            }
        });
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (ListViewUtils.m8262b(i, i2, i3) && NoticeFragment.this.mRepostNoticeRequestState != RequestState.REQUESTING) {
                    NoticeFragment.this.requestRepostNotices();
                }
            }
        });
        this.mNewFollowerLayout = new FrameLayout(getActivity());
        this.mNewFollowerLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        this.mListView.addHeaderView(this.mNewFollowerLayout);
        this.mNewFollowerView = layoutInflater.inflate(R.layout.musiccircle_notice_new_followers, (ViewGroup) this.mListView, false);
        this.mNewFollowerView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TTPodUser m2954aq = Preferences.m2954aq();
                if (m2954aq != null) {
                    WrapFollowersFragment wrapFollowersFragment = new WrapFollowersFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putLong("user_id", m2954aq.getUserId());
                    wrapFollowersFragment.setArguments(bundle2);
                    NoticeFragment.this.launchFragment(wrapFollowersFragment);
                }
            }
        });
        this.mNewFollowerName = (TextView) this.mNewFollowerView.findViewById(R.id.tv_followers);
        resetFollowerLayout(false);
        this.mNoticeAdapter = new NoticeAdapter(getActivity(), this.mRepostNotices);
        this.mNoticeAdapter.m7545a(new SubPostDetailFragment.InterfaceC0795a() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.4
            @Override // com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment.InterfaceC0795a
            /* renamed from: a */
            public void mo7918a(Post post) {
                NoticeFragment.this.launchFragment(SubPostDetailFragment.createByPost(post, "new_follower_notice"));
            }
        });
        this.mNoticeAdapter.m7543a(new WrapUserPostListFragment.InterfaceC1704a() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.5
            @Override // com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.InterfaceC1704a
            /* renamed from: a */
            public void mo5428a(TTPodUser tTPodUser) {
                NoticeFragment.this.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(tTPodUser, "new_follower_notice"));
            }
        });
        this.mListView.setAdapter((ListAdapter) this.mNoticeAdapter);
        requestNewFollowerNotices();
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        this.mRequestCode = getArguments().getInt("id");
        this.mResultCode = 0;
        return inflate;
    }

    public void setOriginFragment(SlidingClosableFragment slidingClosableFragment) {
        this.mOriginFragment = slidingClosableFragment;
    }

    private void resetFollowerLayout(boolean z) {
        if (z) {
            this.mNewFollowerLayout.removeAllViews();
            this.mNewFollowerLayout.addView(this.mNewFollowerView);
            return;
        }
        this.mNewFollowerLayout.removeView(this.mNewFollowerView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_NEW_FOLLOWER_NOTICE_LIST, ReflectUtils.m8375a(getClass(), "updateNewFollowerNotices", NewFollowersListResult.class, String.class));
        map.put(CommandID.UPDATE_REPOST_NOTICE_LIST, ReflectUtils.m8375a(getClass(), "updateRepostNotices", NoticeListResult.class, String.class));
    }

    public void updateRepostNotices(NoticeListResult noticeListResult, String str) {
        if ("repost_notice".equals(str)) {
            ArrayList<Notice> dataList = noticeListResult.getDataList();
            if (dataList.isEmpty()) {
                this.mRepostNoticeRequestState = RequestState.REQUESTED_FAIL;
            } else {
                this.mRepostNoticeRequestState = RequestState.REQUESTED_SUCCESS;
                if (this.mIsRefreshing) {
                    this.mRepostNotices.clear();
                    final Request<BaseResult> m8873a = NoticeAPI.m8873a(Preferences.m2954aq().getAccessToken(), NoticeType.REPOST.value());
                    m8873a.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.6
                        @Override // com.sds.android.sdk.lib.request.RequestCallback
                        public void onRequestSuccess(BaseResult baseResult) {
                        }

                        @Override // com.sds.android.sdk.lib.request.RequestCallback
                        public void onRequestFailure(BaseResult baseResult) {
                            //ErrorStatistic.m5232g(m8873a.m8532e());
                        }
                    });
                }
                this.mRepostNotices.addAll(dataList);
                this.mNoticeAdapter.setDataList((List) this.mRepostNotices);
            }
            if (this.mNewFollowerses.isEmpty() && this.mRepostNotices.isEmpty()) {
                toggleFailedView();
            } else {
                this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
            }
            this.mIsRefreshing = false;
            this.mListView.m1336b();
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    private void loadNoDataView() {
        View inflate = View.inflate(getActivity(), R.layout.musiccircle_message_empty, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
        ((TextView) inflate.findViewById(R.id.note2)).setVisibility(View.VISIBLE);
    }

    private void loadNetworkErrorView() {
        View inflate = View.inflate(getActivity(), R.layout.loadingview_failed, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.DeviceConfig.isConnected()) {
                    NoticeFragment.this.mStateView.setState(StateView.EnumC2248b.LOADING);
                    NoticeFragment.this.requestNewFollowerNotices();
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_error);
            }
        });
    }

    private void toggleFailedView() {
        if (EnvironmentUtils.DeviceConfig.isConnected()) {
            loadNoDataView();
        } else {
            loadNetworkErrorView();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void onSlidingClosed() {
        super.onSlidingClosed();
        setResults();
    }

    private void setResults() {
        if (this.mOriginFragment != null) {
            this.mOriginFragment.onActivityResult(this.mRequestCode, this.mResultCode, new Intent());
        }
    }

    public void updateNewFollowerNotices(NewFollowersListResult newFollowersListResult, String str) {
        if ("new_follower_notice".equals(str)) {
            ArrayList<NewFollowers> dataList = newFollowersListResult.getDataList();
            if (this.mIsRefreshing) {
                this.mNewFollowerses.clear();
                this.mNewFollowerses = dataList;
            }
            if (!this.mNewFollowerses.isEmpty()) {
                int size = this.mNewFollowerses.size();
                if (size == 1) {
                    this.mNewFollowerName.setText(this.mNewFollowerses.get(0).getUser().getNickName());
                } else if (size == 2) {
                    this.mNewFollowerName.setText(this.mNewFollowerses.get(0).getUser().getNickName() + "、" + this.mNewFollowerses.get(1).getUser().getNickName());
                } else if (size > 2) {
                    this.mNewFollowerName.setText(this.mNewFollowerses.get(0).getUser().getNickName() + "、" + this.mNewFollowerses.get(1).getUser().getNickName() + "等" + dataList.size() + "人");
                }
                resetFollowerLayout(true);
            }
            requestRepostNotices();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestRepostNotices() {
        this.mRepostNoticeRequestState = RequestState.REQUESTING;
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_REPOST_NOTICES, Integer.valueOf(this.mRepostNotices.size()), 20, "repost_notice"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestNewFollowerNotices() {
        this.mIsRefreshing = true;
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_NEW_FOLLOWER_NOTICES, "new_follower_notice"));
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mNoticeAdapter.getCount());
        if (m8266a > -1) {
            final Notice item = this.mNoticeAdapter.getItem(m8266a);
            PopupsUtils.m6725a(getActivity(), new ActionItem[]{new ActionItem(1, 0, "删除")}, "消息", new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.8
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i2) {
                    TTPodUser m2954aq = Preferences.m2954aq();
                    if (m2954aq != null) {
                        final Request<BaseResult> m8870a = NoticeAPI.m8870a(m2954aq.getAccessToken(), item.getNoticeId());
                        m8870a.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.message.NoticeFragment.8.1
                            @Override // com.sds.android.sdk.lib.request.RequestCallback
                            public void onRequestSuccess(BaseResult baseResult) {
                                int size = NoticeFragment.this.mRepostNotices.size() - 1;
                                while (true) {
                                    if (size < 0) {
                                        break;
                                    } else if (((Notice) NoticeFragment.this.mRepostNotices.get(size)).getNoticeId() == item.getNoticeId()) {
                                        NoticeFragment.this.mRepostNotices.remove(size);
                                        break;
                                    } else {
                                        size--;
                                    }
                                }
                                NoticeFragment.this.mNoticeAdapter.setDataList(NoticeFragment.this.mRepostNotices);
                            }

                            @Override // com.sds.android.sdk.lib.request.RequestCallback
                            public void onRequestFailure(BaseResult baseResult) {
                                //ErrorStatistic.m5232g(m8870a.m8532e());
                                PopupsUtils.m6721a("删除失败");
                            }
                        });
                    }
                }
            });
        }
    }
}
