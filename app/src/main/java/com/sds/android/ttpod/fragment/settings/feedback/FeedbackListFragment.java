package com.sds.android.ttpod.fragment.settings.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.p060b.BaseResultRest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.setting.FeedbackChatActivity;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.utils.TimeUtils;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class FeedbackListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private static final int LIST_DIVIDER_HEIGHT = 2;
    private C1732a mAdapter;
    private FeedbackItem mClickedFeedbackItem;
    private DragUpdateListView mListView;
    private View mReloadView;
    private RequestState mRequestState = RequestState.IDLE;
    private StateView mStateView;
    private TextView mTvHint;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.REQUEST_FEEDBACK_LIST_FINISH, ReflectUtils.m8375a(cls, "updateFeedbackList", BaseResultRest.class, List.class, Boolean.class));
        map.put(CommandID.PROPOSAL_FEEDBACK_FINISH, ReflectUtils.m8375a(cls, "onProposalFeedbackFinish", BaseResultRest.class, FeedbackItem.class));
        map.put(CommandID.SEND_FEEDBACK_MESSAGE_FINISH, ReflectUtils.m8375a(cls, "onSendMessageFinish", BaseResultRest.class, FeedbackMessage.class));
        map.put(CommandID.REQUEST_FEEDBACK_MESSAGES_FINISH, ReflectUtils.m8375a(cls, "onRequestMessagesFinished", BaseResultRest.class, List.class, Boolean.class));
        map.put(CommandID.NEW_REPLYIED_FEEDBACKS_RECEIVED, ReflectUtils.m8375a(cls, "onRequestNewRepliedMessagesFinished", List.class));
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_feedback_list, (ViewGroup) null);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTvHint = (TextView) view.findViewById(R.id.tv_feedback_history_hint);
        this.mStateView = (StateView) view.findViewById(R.id.feedback_list_loadingview);
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mReloadView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.settings.feedback.FeedbackListFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (EnvironmentUtils.C0604c.m8474e()) {
                    FeedbackListFragment.this.requestFeedbackList();
                } else {
                    PopupsUtils.m6760a((int) R.string.network_error);
                }
            }
        });
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.setHeaderDividersEnabled(true);
        this.mListView.setDivider(getResources().getDrawable(R.color.listDivider_feedback_list));
        this.mListView.setDividerHeight(2);
        this.mListView.setOnStartRefreshListener(new DragUpdateHelper.InterfaceC2273c() { // from class: com.sds.android.ttpod.fragment.settings.feedback.FeedbackListFragment.2
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
            public void onStartRefreshEvent() {
                if (EnvironmentUtils.C0604c.m8474e()) {
                    if (FeedbackListFragment.this.mRequestState != RequestState.REQUESTING) {
                        FeedbackListFragment.this.requestFeedbackList();
                        return;
                    }
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_error);
            }
        });
        this.mListView.setOnItemClickListener(this);
        this.mAdapter = new C1732a();
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        loadFeedbackCache();
        if (EnvironmentUtils.C0604c.m8474e()) {
            requestFeedbackList();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        HashMap hashMap = new HashMap();
        if (this.mAdapter != null) {
            for (FeedbackItem feedbackItem : this.mAdapter.m7662b()) {
                hashMap.put(feedbackItem.getId(), feedbackItem);
            }
        }
        Cache.m3218a().m3198a((Map<String, FeedbackItem>) hashMap);
        super.onDestroyView();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!EnvironmentUtils.C0604c.m8474e()) {
            PopupsUtils.m6760a((int) R.string.network_error);
            return;
        }
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (m8266a > -1) {
            this.mClickedFeedbackItem = this.mAdapter.getItem(m8266a);
            startActivity(new Intent(getActivity(), FeedbackChatActivity.class).putExtra(FeedbackChatActivity.FEEDBACK_ITEM, this.mClickedFeedbackItem));
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void updateFeedbackList(BaseResultRest baseResultRest, List list, Boolean bool) {
        this.mListView.m1336b();
        if (baseResultRest.m8677d()) {
            this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
            if (!bool.booleanValue()) {
                this.mRequestState = RequestState.IDLE;
                return;
            }
            if (list != null && list.size() > 0) {
                this.mTvHint.setText(R.string.feedback_history_hint);
            } else {
                this.mTvHint.setText(R.string.feedback_history_empty);
            }
            sortFeedbackItems(list);
            this.mAdapter.m7663a(list);
        } else {
            this.mStateView.setState(StateView.EnumC2248b.FAILED);
        }
        this.mRequestState = RequestState.IDLE;
    }

    public void onProposalFeedbackFinish(BaseResultRest baseResultRest, FeedbackItem feedbackItem) {
        this.mListView.m1336b();
        if (baseResultRest.m8677d()) {
            this.mTvHint.setText(R.string.feedback_history_hint);
            synchronized (this.mAdapter) {
                this.mAdapter.m7662b().add(0, feedbackItem);
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onRequestMessagesFinished(BaseResultRest baseResultRest, List list, Boolean bool) {
        if (baseResultRest.m8677d() && this.mClickedFeedbackItem.isUnread()) {
            this.mClickedFeedbackItem.setUnread(false);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onSendMessageFinish(BaseResultRest baseResultRest, FeedbackMessage feedbackMessage) {
        if (baseResultRest.m8677d()) {
            this.mClickedFeedbackItem.setLastUpdated(feedbackMessage.getTimestamp());
            this.mAdapter.m7662b().remove(this.mClickedFeedbackItem);
            this.mAdapter.m7662b().add(0, this.mClickedFeedbackItem);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onRequestNewRepliedMessagesFinished(List<FeedbackItem> list) {
        loadFeedbackCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFeedbackList() {
        this.mRequestState = RequestState.REQUESTING;
        CommandCenter m4607a = CommandCenter.m4607a();
        CommandID commandID = CommandID.REQUEST_FEEDBACK_LIST;
        Object[] objArr = new Object[2];
        objArr[0] = Long.valueOf(Cache.m3218a().m3147q() == null ? 0L : Preferences.m2908bu());
        objArr[1] = null;
        m4607a.m4596b(new Command(commandID, objArr));
    }

    private void loadFeedbackCache() {
        Map<String, FeedbackItem> m3147q = Cache.m3218a().m3147q();
        if (m3147q != null && m3147q.size() != 0) {
            this.mTvHint.setText(R.string.feedback_history_hint);
            ArrayList arrayList = new ArrayList(m3147q.values());
            sortFeedbackItems(arrayList);
            this.mAdapter.m7663a((List) arrayList);
            this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
        }
    }

    private void sortFeedbackItems(List<FeedbackItem> list) {
        Collections.sort(list, new Comparator<FeedbackItem>() { // from class: com.sds.android.ttpod.fragment.settings.feedback.FeedbackListFragment.3
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(FeedbackItem feedbackItem, FeedbackItem feedbackItem2) {
                return -m5405a(feedbackItem.getLastUpdated(), feedbackItem2.getLastUpdated());
            }

            /* renamed from: a */
            private int m5405a(long j, long j2) {
                if (j > j2) {
                    return 1;
                }
                if (j == j2) {
                    return 0;
                }
                return -1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.fragment.settings.feedback.FeedbackListFragment$a */
    /* loaded from: classes.dex */
    public class C1732a extends BaseListAdapter<FeedbackItem> {

        /* renamed from: e */
        private String[] f5496e;

        /* renamed from: f */
        private int[] f5497f = {R.color.text_background_feedback_status_waiting, R.color.text_background_feedback_status_solving, R.color.text_background_feedback_status_solved, R.color.text_background_feedback_status_recorded, R.color.text_background_feedback_status_not_now};

        C1732a() {
            this.f5496e = FeedbackListFragment.this.getResources().getStringArray(R.array.feedback_status);
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter, android.widget.Adapter
        public int getCount() {
            return this.f3158d.size();
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter, android.widget.Adapter
        /* renamed from: a */
        public FeedbackItem getItem(int i) {
            return (FeedbackItem) this.f3158d.get(i);
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = layoutInflater.inflate(R.layout.feedback_item, (ViewGroup) null);
            inflate.setTag(new C1733b((ViewGroup) inflate));
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5400a(View view, FeedbackItem feedbackItem, int i) {
            C1733b c1733b = (C1733b) view.getTag();
            c1733b.f5500c.setText(feedbackItem.getProposalContent());
            c1733b.f5501d.setText(TimeUtils.m8156a(feedbackItem.getLastUpdated(), "yyyy-MM-dd hh:mm:ss a"));
            c1733b.f5499b.setVisibility(feedbackItem.isUnread() ? View.VISIBLE : View.INVISIBLE);
            int status = feedbackItem.getStatus();
            c1733b.f5502e.setText(status < this.f5496e.length ? this.f5496e[status] : FeedbackListFragment.this.getResources().getString(R.string.unknown_status));
            c1733b.f5502e.setBackgroundResource(status < this.f5497f.length ? this.f5497f[status] : this.f5497f[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.fragment.settings.feedback.FeedbackListFragment$b */
    /* loaded from: classes.dex */
    public class C1733b {

        /* renamed from: b */
        private IconTextView f5499b;

        /* renamed from: c */
        private TextView f5500c;

        /* renamed from: d */
        private TextView f5501d;

        /* renamed from: e */
        private TextView f5502e;

        C1733b(ViewGroup viewGroup) {
            this.f5499b = (IconTextView) viewGroup.findViewById(R.id.new_flag);
            this.f5499b.setVisibility(View.INVISIBLE);
            this.f5500c = (TextView) viewGroup.findViewById(R.id.tv_feedback_topic);
            this.f5501d = (TextView) viewGroup.findViewById(R.id.tv_feedback_update_time);
            this.f5502e = (TextView) viewGroup.findViewById(R.id.tv_feedback_status);
        }
    }
}
