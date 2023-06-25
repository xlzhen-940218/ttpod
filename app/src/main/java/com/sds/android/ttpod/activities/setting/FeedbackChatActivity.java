package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.p060b.BaseResultRest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.utils.TimeUtils;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class FeedbackChatActivity extends SlidingClosableActivity {
    public static final String FEEDBACK_ITEM = "feedback_item";
    private C0877b mAdapter;
    private Button mBtnSend;
    private EditText mEtInput;
    private FeedbackItem mFeedbackItem;
    private boolean mIsRequestingMessage;
    private DragUpdateListView mListView;
    private Long mNewMessageTimestamp;
    private View mReloadView;
    private RequestState mRequestState = RequestState.IDLE;
    private StateView mStateView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFeedbackItem = (FeedbackItem) getIntent().getSerializableExtra(FEEDBACK_ITEM);
        setContentView(R.layout.activity_feedback_chat);
        getActionBarController().m7189b(R.string.add_feedback_description);
        getActionBarController().m7179d();
        initViews();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.REQUEST_FEEDBACK_MESSAGES_FINISH, ReflectUtils.m8375a(cls, "onRequestMessagesFinished", BaseResultRest.class, List.class, Boolean.class));
        map.put(CommandID.SEND_FEEDBACK_MESSAGE_FINISH, ReflectUtils.m8375a(cls, "onSendMessageFinish", BaseResultRest.class, FeedbackMessage.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (EnvironmentUtils.C0604c.m8474e()) {
            requestNewestMessage(this.mNewMessageTimestamp, null);
        } else {
            PopupsUtils.m6760a((int) R.string.network_error);
        }
    }

    public void onRequestMessagesFinished(BaseResultRest baseResultRest, List list, Boolean bool) {
        this.mListView.m1336b();
        if (baseResultRest.m8677d()) {
            if (bool.booleanValue()) {
                List<FeedbackMessage> b = this.mAdapter.m7662b();
                b.addAll(list);
                this.mAdapter.m7663a((List) b);
            } else {
                this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                this.mAdapter.m7663a(list);
                if (list.size() > 0) {
                    this.mNewMessageTimestamp = Long.valueOf(((FeedbackMessage) list.get(list.size() - 1)).getTimestamp() + 1);
                }
            }
        } else {
            PopupsUtils.m6760a((int) R.string.request_feedback_message_fail);
        }
        this.mRequestState = RequestState.IDLE;
        this.mIsRequestingMessage = false;
    }

    public void onSendMessageFinish(BaseResultRest baseResultRest, FeedbackMessage feedbackMessage) {
        this.mListView.m1336b();
        if (baseResultRest.m8678c() == 201) {
            synchronized (this.mAdapter) {
                this.mAdapter.m7662b().add(feedbackMessage);
                this.mNewMessageTimestamp = Long.valueOf(feedbackMessage.getTimestamp() + 1);
            }
            this.mAdapter.notifyDataSetChanged();
            this.mEtInput.setText("");
        } else {
            PopupsUtils.m6760a((int) R.string.send_feedback_message_fail);
        }
        this.mBtnSend.setClickable(true);
    }

    private void initViews() {
        this.mEtInput = (EditText) findViewById(R.id.et_input);
        this.mBtnSend = (Button) findViewById(R.id.btn_send);
        this.mBtnSend.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.setting.FeedbackChatActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.C0604c.m8474e()) {
                    String obj = FeedbackChatActivity.this.mEtInput.getText().toString();
                    if (!StringUtils.isEmpty(obj)) {
                        FeedbackChatActivity.this.mBtnSend.setClickable(false);
                        CommandCenter.getInstance().m4596b(new Command(CommandID.SEND_FEEDBACK_MESSAGE, new FeedbackMessage(FeedbackChatActivity.this.mFeedbackItem.getId(), FeedbackMessage.TYPE_TEXT, obj)));
                        return;
                    }
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_error);
            }
        });
        this.mStateView = (StateView) findViewById(R.id.chat_loadingview);
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mReloadView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.setting.FeedbackChatActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.C0604c.m8474e()) {
                    FeedbackChatActivity.this.mStateView.setState(StateView.EnumC2248b.LOADING);
                    FeedbackChatActivity.this.requestNewestMessage();
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_error);
            }
        });
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.setOnStartRefreshListener(new DragUpdateHelper.InterfaceC2273c() { // from class: com.sds.android.ttpod.activities.setting.FeedbackChatActivity.3
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
            public void onStartRefreshEvent() {
                FeedbackChatActivity.this.requestNewestMessage(FeedbackChatActivity.this.mNewMessageTimestamp, null);
            }
        });
        this.mListView.setTranscriptMode(2);
        this.mAdapter = new C0877b();
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestNewestMessage() {
        requestNewestMessage(null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestNewestMessage(Long l, Boolean bool) {
        if (!this.mIsRequestingMessage) {
            this.mRequestState = RequestState.REQUESTING;
            this.mIsRequestingMessage = true;
            CommandCenter.getInstance().m4596b(new Command(CommandID.REQUEST_FEEDBACK_MESSAGES, this.mFeedbackItem.getId(), l, bool));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.activities.setting.FeedbackChatActivity$b */
    /* loaded from: classes.dex */
    public class C0877b extends BaseListAdapter<FeedbackMessage> {
        C0877b() {
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = layoutInflater.inflate(R.layout.feedback_chat_item, (ViewGroup) null);
            inflate.setTag(new C0878c(inflate));
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5400a(View view, FeedbackMessage feedbackMessage, int i) {
            C0876a c0876a;
            C0878c c0878c = (C0878c) view.getTag();
            boolean z = 1 == feedbackMessage.getMsgSource();
            if (i == 0 || m7809a(feedbackMessage.getTimestamp(), getItem(i - 1).getTimestamp())) {
                c0878c.f2978c.setText(TimeUtils.m8157a(TimeUnit.MILLISECONDS.toSeconds(feedbackMessage.getTimestamp())));
                c0878c.f2978c.setVisibility(View.VISIBLE);
            } else {
                c0878c.f2978c.setVisibility(View.GONE);
            }
            if (z) {
                C0876a c0876a2 = c0878c.f2979d;
                c0878c.f2980e.f2972b.setVisibility(View.GONE);
                c0876a = c0876a2;
            } else {
                C0876a c0876a3 = c0878c.f2980e;
                c0878c.f2979d.f2972b.setVisibility(View.GONE);
                c0876a = c0876a3;
            }
            c0876a.f2972b.setVisibility(View.VISIBLE);
            c0876a.f2973c.setText(feedbackMessage.getContent());
            c0876a.f2974d.setText(TimeUtils.m8154b(TimeUnit.MILLISECONDS.toSeconds(feedbackMessage.getTimestamp())));
        }

        /* renamed from: a */
        private boolean m7809a(long j, long j2) {
            return new Date(j).getDate() > new Date(j2).getDate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.activities.setting.FeedbackChatActivity$c */
    /* loaded from: classes.dex */
    public class C0878c {

        /* renamed from: b */
        private TextView f2977b;

        /* renamed from: c */
        private TextView f2978c;

        /* renamed from: d */
        private C0876a f2979d;

        /* renamed from: e */
        private C0876a f2980e;

        public C0878c(View view) {
            this.f2977b = (TextView) view.findViewById(R.id.tv_staff_name);
            this.f2978c = (TextView) view.findViewById(R.id.tv_chat_date);
            this.f2979d = new C0876a(view.findViewById(R.id.chatting_left));
            this.f2980e = new C0876a(view.findViewById(R.id.chatting_right));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.activities.setting.FeedbackChatActivity$a */
    /* loaded from: classes.dex */
    public class C0876a {

        /* renamed from: b */
        private View f2972b;

        /* renamed from: c */
        private TextView f2973c;

        /* renamed from: d */
        private TextView f2974d;

        public C0876a(View view) {
            this.f2972b = view;
            this.f2973c = (TextView) view.findViewById(R.id.text_content);
            this.f2974d = (TextView) view.findViewById(R.id.text_time);
        }
    }
}
