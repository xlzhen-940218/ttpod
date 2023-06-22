package com.sds.android.ttpod.activities.musiccircle.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.NoticeAPI;
import com.sds.android.cloudapi.ttpod.result.NoticeListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.CommentsFragment;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.adapter.p073e.p074a.CommentAdapter;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.NoticeType;
import com.sds.android.ttpod.framework.p106a.p107a.ErrorStatistic;
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
public class MyCommentsFragment extends SlidingClosableFragment implements AdapterView.OnItemClickListener {
    private static final int PAGE_SIZE = 20;
    private CommentAdapter mCommentAdapter;
    private DragUpdateListView mListView;
    private SlidingClosableFragment mOriginFragment;
    private int mRequestCode;
    private int mResultCode;
    private StateView mStateView;
    private List<Notice> mNotices = new ArrayList();
    private RequestState mRequestState = RequestState.IDLE;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().m7189b(R.string.musiccircle_comment);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_layout, viewGroup, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.musiccircle_message_loading_view);
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnStartRefreshListener(new DragUpdateHelper.InterfaceC2273c() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MyCommentsFragment.1
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
            public void onStartRefreshEvent() {
                MyCommentsFragment.this.requestComments();
            }
        });
        this.mCommentAdapter = new CommentAdapter(getActivity(), this.mNotices);
        this.mCommentAdapter.m7561a(new SubPostDetailFragment.InterfaceC0795a() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MyCommentsFragment.2
            @Override // com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment.InterfaceC0795a
            /* renamed from: a */
            public void mo7918a(Post post) {
                MyCommentsFragment.this.launchFragment(SubPostDetailFragment.createByPost(post, "comment_notice"));
            }
        });
        this.mCommentAdapter.m7559a(new WrapUserPostListFragment.InterfaceC1704a() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MyCommentsFragment.3
            @Override // com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.InterfaceC1704a
            /* renamed from: a */
            public void mo5428a(TTPodUser tTPodUser) {
                MyCommentsFragment.this.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(tTPodUser, "comment_notice"));
            }
        });
        this.mListView.setAdapter((ListAdapter) this.mCommentAdapter);
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        requestComments();
        this.mRequestCode = getArguments().getInt("id");
        this.mResultCode = 0;
        return inflate;
    }

    public void setOriginFragment(SlidingClosableFragment slidingClosableFragment) {
        this.mOriginFragment = slidingClosableFragment;
    }

    private void showFailedView() {
        if (EnvironmentUtils.C0604c.m8474e()) {
            loadNoDataView();
        } else {
            loadNetworkErrorView();
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void onSlidingClosed() {
        super.onSlidingClosed();
        setFragmentResults();
    }

    private void setFragmentResults() {
        if (this.mOriginFragment != null) {
            this.mOriginFragment.onActivityResult(this.mRequestCode, this.mResultCode, new Intent());
        }
    }

    private void loadNetworkErrorView() {
        View inflate = View.inflate(getActivity(), R.layout.loadingview_failed, null);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MyCommentsFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.C0604c.m8474e()) {
                    MyCommentsFragment.this.mStateView.setState(StateView.EnumC2248b.LOADING);
                    MyCommentsFragment.this.requestComments();
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_error);
            }
        });
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
    }

    private void loadNoDataView() {
        View inflate = View.inflate(getActivity(), R.layout.musiccircle_message_empty, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
        ((TextView) inflate.findViewById(R.id.note2)).setVisibility(View.VISIBLE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_COMMENT_NOTICE_LIST, ReflectUtils.m8375a(getClass(), "updateCommentNotices", NoticeListResult.class, String.class));
    }

    public void updateCommentNotices(NoticeListResult noticeListResult, String str) {
        if ("comment_notice".equals(str)) {
            ArrayList<Notice> dataList = noticeListResult.getDataList();
            if (dataList.isEmpty()) {
                this.mRequestState = RequestState.REQUESTED_FAIL;
                showFailedView();
            } else {
                this.mRequestState = RequestState.REQUESTED_SUCCESS;
                this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                this.mNotices = dataList;
                this.mCommentAdapter.m7663a((List) dataList);
                final Request<BaseResult> m8873a = NoticeAPI.m8873a(Preferences.m2954aq().getAccessToken(), NoticeType.COMMENT.value());
                m8873a.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MyCommentsFragment.5
                    @Override // com.sds.android.sdk.lib.request.RequestCallback
                    public void onRequestSuccess(BaseResult baseResult) {
                    }

                    @Override // com.sds.android.sdk.lib.request.RequestCallback
                    public void onRequestFailure(BaseResult baseResult) {
                        ErrorStatistic.m5232g(m8873a.m8532e());
                    }
                });
            }
            this.mListView.m1336b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestComments() {
        this.mRequestState = RequestState.REQUESTING;
        CommandCenter.m4607a().m4606a(new Command(CommandID.REQUEST_COMMENT_NOTICES, 0, 20, "comment_notice"));
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mCommentAdapter.getCount());
        if (m8266a > -1) {
            final Notice item = this.mCommentAdapter.getItem(m8266a);
            PopupsUtils.m6725a(getActivity(), new ActionItem[]{new ActionItem(0, 0, "回复"), new ActionItem(1, 0, "查看原内容"), new ActionItem(2, 0, "删除")}, "消息", new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MyCommentsFragment.6
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i2) {
                    switch (actionItem.m7005e()) {
                        case 0:
                        case 1:
                            if (item.getOriginPost() != null) {
                                MyCommentsFragment.this.launchFragment(CommentsFragment.createCommentsFragment(item.getOriginPost().getId(), item.getOriginPost().getUser().getUserId()));
                                return;
                            }
                            return;
                        case 2:
                            MyCommentsFragment.this.deleteComment(item);
                            return;
                        default:
                            return;
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteComment(final Notice notice) {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            final Request<BaseResult> m8870a = NoticeAPI.m8870a(m2954aq.getAccessToken(), notice.getNoticeId());
            m8870a.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MyCommentsFragment.7
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                public void onRequestSuccess(BaseResult baseResult) {
                    int size = MyCommentsFragment.this.mNotices.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        } else if (((Notice) MyCommentsFragment.this.mNotices.get(size)).getNoticeId() == notice.getNoticeId()) {
                            MyCommentsFragment.this.mNotices.remove(size);
                            break;
                        } else {
                            size--;
                        }
                    }
                    MyCommentsFragment.this.mCommentAdapter.m7663a(MyCommentsFragment.this.mNotices);
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                public void onRequestFailure(BaseResult baseResult) {
                    PopupsUtils.m6721a("删除失败");
                    ErrorStatistic.m5232g(m8870a.m8532e());
                }
            });
        }
    }
}
