package com.sds.android.ttpod.activities.musiccircle.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.SystemNotice;
import com.sds.android.cloudapi.ttpod.result.SystemNoticeListResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p073e.p074a.SystemMessageAdapter;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SystemMessageFragment extends SlidingClosableFragment {
    private static final int MESSAGE_SIZE = 20;
    private SystemMessageAdapter mAdapter;
    private DragUpdateListView mListView;
    private SlidingClosableFragment mOriginFragment;
    private StateView mStateView;
    private List<SystemNotice> mSystemNotices = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().m7189b(R.string.musiccircle_system_notice);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_layout, viewGroup, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.musiccircle_message_loading_view);
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mAdapter = new SystemMessageAdapter(getActivity(), this.mSystemNotices);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        requestSystemMessage();
        return inflate;
    }

    private void loadErrorView() {
        View inflate = View.inflate(getActivity(), R.layout.loadingview_failed, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.message.SystemMessageFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EnvironmentUtils.DeviceConfig.isConnected()) {
                    SystemMessageFragment.this.requestSystemMessage();
                } else {
                    PopupsUtils.m6760a((int) R.string.network_error);
                }
            }
        });
    }

    private void loadNoDataView() {
        View inflate = View.inflate(getActivity(), R.layout.musiccircle_message_empty, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
        ((TextView) inflate.findViewById(R.id.note2)).setVisibility(View.VISIBLE);
    }

    private void toggleFailedView() {
        if (EnvironmentUtils.DeviceConfig.isConnected()) {
            loadNoDataView();
        } else {
            loadErrorView();
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSystemMessage() {
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_SYSTEM_NOTICES, 0L, 20, "system_message"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SYSTEM_NOTICE_LIST, ReflectUtils.m8375a(getClass(), "updateSystemMessage", SystemNoticeListResult.class, String.class));
    }

    public void updateSystemMessage(SystemNoticeListResult systemNoticeListResult, String str) {
        if ("system_message".equals(str)) {
            ArrayList<SystemNotice> dataList = systemNoticeListResult.getDataList();
            if (dataList.isEmpty()) {
                toggleFailedView();
                return;
            }
            this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
            this.mSystemNotices.clear();
            this.mSystemNotices = dataList;
            this.mAdapter.setDataList((List) this.mSystemNotices);
        }
    }
}
