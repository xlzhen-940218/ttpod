package com.sds.android.ttpod.activities.musiccircle.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.sds.android.cloudapi.ttpod.data.PrivateMessageContent;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.PrivateMessageContentListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p073e.p074a.PrivateMessageAdapter;
import com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class PrivateMessageFragment extends SlidingClosableFragment implements EmoticonsWithInputLayout.InterfaceC1207a {
    private static final int PAGE_SIZE = 64;
    private PrivateMessageAdapter mAdapter;
    private EmoticonsWithInputLayout mEmoticonsWithInputLayout;
    private boolean mIsRequestNewestMessage;
    private DragUpdateListView mListView;
    private List<PrivateMessageContent> mPrivateMessageContents = new ArrayList();
    private View mReloadView;
    private StateView mStateView;
    private TTPodUser mUserCurrent;
    private TTPodUser mUserReplyTo;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mUserReplyTo = (TTPodUser) getArguments().getSerializable("user");
        View inflate = layoutInflater.inflate(R.layout.musiccircle_private_message, viewGroup, false);
        this.mUserCurrent = Preferences.m2954aq();
        this.mStateView = (StateView) inflate.findViewById(R.id.private_message_loadingview);
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mEmoticonsWithInputLayout = (EmoticonsWithInputLayout) inflate.findViewById(R.id.layout_private_message_input);
        initView();
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        requestNewestMessage();
        getActionBarController().m7193a((CharSequence) this.mUserReplyTo.getNickName());
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestMoreMessage() {
        this.mIsRequestNewestMessage = false;
        CommandCenter.getInstance().execute(new Command(CommandID.QUERY_PRIVATE_MESSAGES_CONTENT, Long.valueOf(this.mUserReplyTo.getUserId()), Long.valueOf(this.mPrivateMessageContents.isEmpty() ? 0L : this.mPrivateMessageContents.get(this.mPrivateMessageContents.size() - 1).getTimestamp()), 64, ""));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_PRIVATE_MESSAGE_CONTEXT_LIST, ReflectUtils.m8375a(getClass(), "onUpdatePrivateMessageContents", PrivateMessageContentListResult.class, String.class));
        map.put(CommandID.UPDATE_SEND_PRIVATE_MESSAGE, ReflectUtils.m8375a(getClass(), "updateSendPrivateMessage", BaseResult.class, String.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestNewestMessage() {
        this.mIsRequestNewestMessage = true;
        CommandCenter.getInstance().execute(new Command(CommandID.QUERY_PRIVATE_MESSAGES_CONTENT, Long.valueOf(this.mUserReplyTo.getUserId()), 0L, 64, ""));
    }

    public void updateSendPrivateMessage(BaseResult baseResult, String str) {
        if (baseResult.isSuccess()) {
            requestNewestMessage();
            this.mEmoticonsWithInputLayout.m6668a();
            this.mEmoticonsWithInputLayout.m6663b();
        } else {
            PopupsUtils.m6721a("发送失败，请重新发送");
        }
        this.mEmoticonsWithInputLayout.setSendEnable(true);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        getActionBarController().onThemeLoaded();
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        this.mStateView.onThemeLoaded();
        if (this.mEmoticonsWithInputLayout != null) {
            this.mEmoticonsWithInputLayout.m6656e();
        }
    }

    public void onUpdatePrivateMessageContents(PrivateMessageContentListResult privateMessageContentListResult, String str) {
        int i;
        this.mListView.m1336b();
        if (privateMessageContentListResult.isSuccess()) {
            this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
            if (this.mIsRequestNewestMessage) {
                this.mPrivateMessageContents.clear();
            }
            int size = privateMessageContentListResult.getDataList().size();
            if (size > 0) {
                this.mPrivateMessageContents.addAll(privateMessageContentListResult.getDataList());
                this.mAdapter.m7524b(this.mPrivateMessageContents);
                DragUpdateListView dragUpdateListView = this.mListView;
                if (this.mIsRequestNewestMessage) {
                    i = this.mAdapter.getCount() - 1;
                } else {
                    i = size < 64 ? size + 1 : 65;
                }
                dragUpdateListView.setSelection(i);
            }
        } else if (this.mIsRequestNewestMessage) {
            this.mStateView.setState(StateView.EnumC2248b.FAILED);
        }
    }

    private void initView() {
        this.mAdapter = new PrivateMessageAdapter(getActivity(), this.mPrivateMessageContents, this.mUserCurrent, this.mUserReplyTo);
        this.mAdapter.m7529a(new WrapUserPostListFragment.InterfaceC1704a() { // from class: com.sds.android.ttpod.activities.musiccircle.message.PrivateMessageFragment.1
            @Override // com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.InterfaceC1704a
            /* renamed from: a */
            public void mo5428a(TTPodUser tTPodUser) {
                PrivateMessageFragment.this.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(tTPodUser, ""));
            }
        });
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnStartRefreshListener(new DragUpdateHelper.InterfaceC2273c() { // from class: com.sds.android.ttpod.activities.musiccircle.message.PrivateMessageFragment.2
            @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
            public void onStartRefreshEvent() {
                PrivateMessageFragment.this.requestMoreMessage();
            }
        });
        this.mListView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.activities.musiccircle.message.PrivateMessageFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                PrivateMessageFragment.this.mEmoticonsWithInputLayout.m6663b();
                return false;
            }
        });
        this.mReloadView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.message.PrivateMessageFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.DeviceConfig.m8474e()) {
                    PrivateMessageFragment.this.mStateView.setState(StateView.EnumC2248b.LOADING);
                    PrivateMessageFragment.this.requestNewestMessage();
                    return;
                }
                PopupsUtils.m6760a((int) R.string.network_error);
            }
        });
        this.mEmoticonsWithInputLayout.m6664a(getSlidingContainer(), null, this);
    }

    @Override // com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.InterfaceC1207a
    public void onSend(String str) {
        CommandCenter.getInstance().execute(new Command(CommandID.SEND_PRIVATE_MESSAGE, Long.valueOf(this.mUserReplyTo.getUserId()), str, ""));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.mEmoticonsWithInputLayout.m6660c();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        this.mEmoticonsWithInputLayout.setmEmoticonsLayoutVisibility(8);
        if (!this.mEmoticonsWithInputLayout.m6658d()) {
            super.onBackPressed();
        }
    }
}
