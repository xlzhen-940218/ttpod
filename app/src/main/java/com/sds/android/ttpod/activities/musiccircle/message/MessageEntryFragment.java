package com.sds.android.ttpod.activities.musiccircle.message;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.NewNoticeCount;
import com.sds.android.cloudapi.ttpod.data.PrivateMessageOverView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.api.NoticeAPI;
import com.sds.android.cloudapi.ttpod.api.PrivateMessageAPI;
import com.sds.android.cloudapi.ttpod.result.NewNoticeCountResult;
import com.sds.android.cloudapi.ttpod.result.PrivateMessageOverViewListResult;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.p068a.CheckerManager;
import com.sds.android.ttpod.adapter.p073e.p074a.MessageEntryAdapter;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.NoticeType;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MessageEntryFragment extends SlidingClosableFragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final int PAGE_SIZE = 20;
    private static final long REFRESH_INTERVAL = 300000;
    private MessageEntryAdapter mAdapter;
    private C0804a mCommentNoticeHeader;
    private boolean mIsRefreshing;
    private DragUpdateListView mListView;
    private NewNoticeCount mNewNoticeCount;
    private SlidingClosableFragment mOriginFragment;
    private C0804a mRepostNoticeHeader;
    private int mRequestCode;
    private C0804a mSystemNoticeHeader;
    private List<PrivateMessageOverView> mPrivateMessages = new ArrayList();
    private Handler mRefreshHandler = new Handler();
    private RequestState mRequestState = RequestState.IDLE;
    private Runnable mRefreshNewNoticeCountRunnable = new Runnable() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment.2
        @Override // java.lang.Runnable
        public void run() {
            if (MessageEntryFragment.this.isAdded()) {
                MessageEntryFragment.this.mRefreshHandler.postDelayed(MessageEntryFragment.this.mRefreshNewNoticeCountRunnable, MessageEntryFragment.REFRESH_INTERVAL);
                MessageEntryFragment.this.refreshNewNoticeCount();
            }
        }
    };
    private View.OnClickListener mHeaderItemClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            switch (view.getId()) {
                case R.id.repost_notice /* 2131231838 */:
                    NoticeFragment noticeFragment = new NoticeFragment();
                    bundle.putInt("id", NoticeType.REPOST.value());
                    noticeFragment.setArguments(bundle);
                    noticeFragment.setOriginFragment(MessageEntryFragment.this);
                    MessageEntryFragment.this.launchFragment(noticeFragment);
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_MESSAGE_CLICK_NOTIFICATION.getValue(), SPage.PAGE_CIRCLE_MESSAGE.getValue()).post();
                    return;
                case R.id.comment_notice /* 2131231839 */:
                    MyCommentsFragment myCommentsFragment = new MyCommentsFragment();
                    bundle.putInt("id", NoticeType.COMMENT.value());
                    myCommentsFragment.setArguments(bundle);
                    myCommentsFragment.setOriginFragment(MessageEntryFragment.this);
                    MessageEntryFragment.this.launchFragment(myCommentsFragment);
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_MESSAGE_CLICK_COMMENT.getValue(), SPage.PAGE_CIRCLE_MESSAGE.getValue()).post();
                    return;
                case R.id.system_notice /* 2131231840 */:
                    MessageEntryFragment.this.launchFragment(new SystemMessageFragment());
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CIRCLE_MESSAGE_CLICK_SYS_INFORM.getValue(), SPage.PAGE_CIRCLE_MESSAGE.getValue()).post();
                    return;
                default:
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().m7189b(R.string.musiccircle_entry_title);
        this.mNewNoticeCount = (NewNoticeCount) getArguments().getSerializable("new_notice_count");
        this.mRequestCode = getArguments().getInt("id");
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_entry, viewGroup, false);
        View inflate2 = layoutInflater.inflate(R.layout.musiccircle_message_entry_header, (ViewGroup) null, false);
        this.mRepostNoticeHeader = new C0804a(inflate2.findViewById(R.id.repost_notice));
        this.mCommentNoticeHeader = new C0804a(inflate2.findViewById(R.id.comment_notice));
        this.mSystemNoticeHeader = new C0804a(inflate2.findViewById(R.id.system_notice));
        bindNoticeHeader(this.mRepostNoticeHeader, getString(R.string.musiccircle_notice), 0, R.drawable.img_musiccircle_message_entry_notice, this.mHeaderItemClickListener);
        bindNoticeHeader(this.mCommentNoticeHeader, getString(R.string.musiccircle_comment), 0, R.drawable.img_musiccircle_message_entry_comment, this.mHeaderItemClickListener);
        bindNoticeHeader(this.mSystemNoticeHeader, getString(R.string.musiccircle_system_notice), 0, R.drawable.img_musiccircle_message_entry_system, this.mHeaderItemClickListener);
        this.mListView = (DragUpdateListView) inflate.findViewById(R.id.message_listview);
        this.mListView.addHeaderView(inflate2);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnItemLongClickListener(this);
        this.mListView.setOnScrollListener(this);
        this.mListView.setOnStartRefreshListener(new DragUpdateHelper.InterfaceC2273c() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment.1
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
            public void onStartRefreshEvent() {
                if (MessageEntryFragment.this.mRequestState != RequestState.REQUESTING) {
                    MessageEntryFragment.this.requestPrivateMessages(0L);
                }
            }
        });
        this.mAdapter = new MessageEntryAdapter(getActivity(), this.mPrivateMessages);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        requestPrivateMessages(0L);
        return inflate;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        CheckerManager.m7949a().m7941c();
        refreshNewNoticeCount();
        this.mRefreshHandler.postDelayed(this.mRefreshNewNoticeCountRunnable, REFRESH_INTERVAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flushCountViewFlag() {
        if (this.mNewNoticeCount != null) {
            int newRepostCount = this.mNewNoticeCount.getNewRepostCount() + this.mNewNoticeCount.getNewFollowerCount();
            this.mRepostNoticeHeader.f2809b.setText(String.valueOf(newRepostCount));
            this.mRepostNoticeHeader.f2809b.setVisibility(newRepostCount > 0 ? View.VISIBLE : View.GONE);
            this.mCommentNoticeHeader.f2809b.setText(String.valueOf(this.mNewNoticeCount.getNewCommentCount()));
            this.mCommentNoticeHeader.f2809b.setVisibility(this.mNewNoticeCount.getNewCommentCount() > 0 ? View.VISIBLE : View.GONE);
            this.mSystemNoticeHeader.f2809b.setText(String.valueOf(this.mNewNoticeCount.getSystemNoticeCount()));
            this.mSystemNoticeHeader.f2809b.setVisibility(this.mNewNoticeCount.getSystemNoticeCount() <= 0 ? View.GONE : View.VISIBLE);
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mRefreshHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshNewNoticeCount() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            final Request<NewNoticeCountResult> m8874a = NoticeAPI.m8874a(m2954aq.getAccessToken());
            m8874a.m8544a(new RequestCallback<NewNoticeCountResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment.3
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(NewNoticeCountResult newNoticeCountResult) {
                    if (MessageEntryFragment.this.isViewAccessAble()) {
                        MessageEntryFragment.this.mNewNoticeCount = newNoticeCountResult.getData();
                        MessageEntryFragment.this.flushCountViewFlag();
                        MessageEntryFragment.this.mListView.m1336b();
                    }
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(NewNoticeCountResult newNoticeCountResult) {
                    if (MessageEntryFragment.this.isViewAccessAble()) {
                        MessageEntryFragment.this.mNewNoticeCount = null;
                        MessageEntryFragment.this.mListView.m1336b();
                        //ErrorStatistic.m5232g(m8874a.m8532e());
                    }
                }
            });
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        CheckerManager.m7949a().m7943b();
    }

    private void bindNoticeHeader(C0804a c0804a, String str, int i, int i2, View.OnClickListener onClickListener) {
        c0804a.f2811d.setImageResource(i2);
        c0804a.f2810c.setText(str);
        c0804a.f2809b.setText(String.valueOf(i));
        c0804a.f2812e.setOnClickListener(onClickListener);
        c0804a.f2809b.setVisibility(View.GONE);
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = true;
        super.onActivityResult(i, i2, intent);
        if (this.mNewNoticeCount != null) {
            if (NoticeType.REPOST.value() == i) {
                if (i2 != this.mNewNoticeCount.getNewRepostCount()) {
                    this.mNewNoticeCount.setNewRepostCount(i2);
                }
                z = false;
            } else {
                if (NoticeType.COMMENT.value() == i && i2 != this.mNewNoticeCount.getNewCommentCount()) {
                    this.mNewNoticeCount.setNewCommentCount(i2);
                }
                z = false;
            }
            if (z) {
                flushCountViewFlag();
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (m8266a > -1) {
            PrivateMessageFragment privateMessageFragment = new PrivateMessageFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", this.mAdapter.getItem(m8266a).getUser());
            privateMessageFragment.setArguments(bundle);
            launchFragment(privateMessageFragment);
        }
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (m8266a > -1) {
            final PrivateMessageOverView item = this.mAdapter.getItem(m8266a);
            PopupsUtils.m6725a(getActivity(), new ActionItem[]{new ActionItem(1, 0, "删除")}, "删除信息", new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment.5
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i2) {
                    TTPodUser m2954aq;
                    if (actionItem.m7005e() == 1 && (m2954aq = Preferences.m2954aq()) != null) {
                        final Request<BaseResult> m8842a = PrivateMessageAPI.m8842a(m2954aq.getAccessToken(), item.getUser().getUserId());
                        m8842a.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment.5.1
                            @Override // com.sds.android.sdk.lib.request.RequestCallback
                            public void onRequestSuccess(BaseResult baseResult) {
                                PopupsUtils.m6721a("删除成功");
                                int size = MessageEntryFragment.this.mPrivateMessages.size() - 1;
                                while (true) {
                                    if (size < 0) {
                                        break;
                                    } else if (((PrivateMessageOverView) MessageEntryFragment.this.mPrivateMessages.get(size)).getUser().getUserId() == item.getUser().getUserId()) {
                                        MessageEntryFragment.this.mPrivateMessages.remove(size);
                                        break;
                                    } else {
                                        size--;
                                    }
                                }
                                MessageEntryFragment.this.mAdapter.setDataList(MessageEntryFragment.this.mPrivateMessages);
                            }

                            @Override // com.sds.android.sdk.lib.request.RequestCallback
                            public void onRequestFailure(BaseResult baseResult) {
                                PopupsUtils.m6721a("删除失败");
                                //ErrorStatistic.m5232g(m8842a.m8532e());
                            }
                        });
                    }
                }
            });
        }
        return true;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (i3 > i2 && i + i2 + 1 >= i3 && this.mRequestState != RequestState.REQUESTING) {
            requestPrivateMessages(this.mPrivateMessages.isEmpty() ? 0L : this.mPrivateMessages.get(this.mPrivateMessages.size() - 1).getLastModified());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_PRIVATE_MESSAGE_LIST, ReflectUtils.loadMethod(getClass(), "updatePrivateMessages", PrivateMessageOverViewListResult.class, String.class));
    }

    public void updatePrivateMessages(PrivateMessageOverViewListResult privateMessageOverViewListResult, String str) {
        if ("private_message".equals(str)) {
            ArrayList<PrivateMessageOverView> dataList = privateMessageOverViewListResult.getDataList();
            if (!dataList.isEmpty()) {
                if (this.mIsRefreshing) {
                    this.mPrivateMessages.clear();
                }
                if (this.mPrivateMessages.size() == 0) {
                    this.mPrivateMessages = dataList;
                } else {
                    addMessages(dataList);
                }
                this.mAdapter.setDataList((List) this.mPrivateMessages);
                this.mRequestState = RequestState.REQUESTED_SUCCESS;
            } else {
                this.mRequestState = RequestState.REQUESTED_FAIL;
            }
            this.mIsRefreshing = false;
            this.mListView.m1336b();
        }
    }

    private void addMessages(ArrayList<PrivateMessageOverView> arrayList) {
        boolean z;
        ArrayList arrayList2 = new ArrayList();
        Iterator<PrivateMessageOverView> it = arrayList.iterator();
        while (it.hasNext()) {
            PrivateMessageOverView next = it.next();
            boolean z2 = true;
            Iterator<PrivateMessageOverView> it2 = this.mPrivateMessages.iterator();
            while (true) {
                z = z2;
                if (!it2.hasNext()) {
                    break;
                }
                z2 = it2.next().getLastModified() == next.getLastModified() ? false : z;
            }
            if (z) {
                arrayList2.add(next);
            }
        }
        this.mPrivateMessages.addAll(arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPrivateMessages(long j) {
        this.mIsRefreshing = j <= 0;
        this.mRequestState = RequestState.REQUESTING;
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_PRIVATE_MESSAGES, Long.valueOf(j), 20, "private_message"));
    }

    public void setOriginFragment(SlidingClosableFragment slidingClosableFragment) {
        this.mOriginFragment = slidingClosableFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void onSlidingClosed() {
        super.onSlidingClosed();
        setResults();
    }

    private void setResults() {
        if (this.mOriginFragment != null) {
            SlidingClosableFragment slidingClosableFragment = this.mOriginFragment;
            int i = this.mRequestCode;
            getActivity();
            slidingClosableFragment.onActivityResult(i, -1, new Intent().putExtra("new_notice_count", this.mNewNoticeCount));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment$a */
    /* loaded from: classes.dex */
    public class C0804a {

        /* renamed from: b */
        private TextView f2809b;

        /* renamed from: c */
        private TextView f2810c;

        /* renamed from: d */
        private ImageView f2811d;

        /* renamed from: e */
        private View f2812e;

        public C0804a(View view) {
            this.f2812e = view;
            this.f2809b = (TextView) view.findViewById(R.id.text_count);
            this.f2810c = (TextView) view.findViewById(R.id.tv_text);
            this.f2811d = (ImageView) view.findViewById(R.id.image_avatar);
        }
    }
}
