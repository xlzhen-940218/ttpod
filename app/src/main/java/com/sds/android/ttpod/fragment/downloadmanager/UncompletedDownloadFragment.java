package com.sds.android.ttpod.fragment.downloadmanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class UncompletedDownloadFragment extends DownloadTaskListFragment {
    private static final int MSG_REFRESH_ALL = 1;
    private static final int MSG_REFRESH_PROGRESS = 0;
    private static final int REFRESH_TIME = 500;
    private Set<String> mDownloadingTaskIds = new HashSet();
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.fragment.downloadmanager.UncompletedDownloadFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (UncompletedDownloadFragment.this.mTaskAdapter != null) {
                        UncompletedDownloadFragment.this.mTaskAdapter.notifyDataSetChanged();
                    }
                    if (UncompletedDownloadFragment.this.mDownloadingTaskIds.size() > 0) {
                        sendEmptyMessageDelayed(0, 500L);
                        return;
                    }
                    return;
                case 1:
                    if (UncompletedDownloadFragment.this.mTaskAdapter != null) {
                        UncompletedDownloadFragment.this.mTaskAdapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private View mLeftActionView;
    private View mRightActionView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_LIST_RELOADED, ReflectUtils.m8375a(getClass(), "reloadDownloadTaskList", new Class[0]));
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mDownloadingTaskIds.size() > 0) {
            sendMessageDelayed(0, 500L);
        }
        this.mLeftActionView = this.mTopActionPanel.findViewById(R.id.left_action);
        this.mLeftActionView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.downloadmanager.UncompletedDownloadFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                //LocalStatistic.m5149aE();
                //SUserUtils.m4956a(SAction.ACTION_MY_DOWNLOAD_PAUSE_ALL, SPage.PAGE_NONE);
                UncompletedDownloadFragment.this.pauseAll();
            }
        });
        this.mRightActionView = this.mTopActionPanel.findViewById(R.id.right_action);
        this.mRightActionView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.downloadmanager.UncompletedDownloadFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                //LocalStatistic.m5150aD();
                //SUserUtils.m4956a(SAction.ACTION_MY_DOWNLOAD_START_ALL, SPage.PAGE_NONE);
                UncompletedDownloadFragment.this.startAll();
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment
    protected void updateListVisibility() {
        if (this.mTasks.isEmpty()) {
            this.mTaskListView.setVisibility(View.INVISIBLE);
            setTopActionPanelVisible(4);
            return;
        }
        this.mTaskListView.setVisibility(View.VISIBLE);
        setTopActionPanelVisible(0);
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mTopActionPanel.findViewById(R.id.left_action_name), ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeManager.m3269a(this.mTopActionPanel.findViewById(R.id.right_action_name), ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeUtils.m8173a((IconTextView) this.mTopActionPanel.findViewById(R.id.left_action_icon), ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeUtils.m8173a((IconTextView) this.mTopActionPanel.findViewById(R.id.right_action_icon), ThemeElement.SONG_LIST_ITEM_TEXT);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            //LocalStatistic.m5151aC();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        this.mHandler.removeMessages(0);
        super.onDestroyView();
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment
    public void updateTaskState(DownloadTaskInfo downloadTaskInfo) {
        if (downloadTaskInfo.getType().intValue() == this.mDownloadType) {
            if (downloadTaskInfo.getState().intValue() != 4) {
                if (!this.mTasks.contains(downloadTaskInfo)) {
                    addTask(downloadTaskInfo);
                } else if (downloadTaskInfo.getState().intValue() == 5 || downloadTaskInfo.getState().intValue() == 3) {
                    this.mDownloadingTaskIds.remove(downloadTaskInfo.getSavePath());
                }
                if (downloadTaskInfo.getState().intValue() == 2) {
                    this.mDownloadingTaskIds.add(downloadTaskInfo.getSavePath());
                    sendMessage(0);
                }
                sendMessage(1);
            } else if (this.mTasks.contains(downloadTaskInfo)) {
                this.mDownloadingTaskIds.remove(downloadTaskInfo.getSavePath());
                removeTask(downloadTaskInfo);
            }
        }
    }

    public void reloadDownloadTaskList() {
        this.mTasks = readTaskList();
        updateListVisibility();
        notifyTaskListChanged();
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment
    protected List<DownloadTaskInfo> readTaskList() {
        List<DownloadTaskInfo> list = (List) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_TASK_LIST_WITH_STATE, 1), List.class);
        ArrayList<DownloadTaskInfo> arrayList = list == null ? new ArrayList(0) : (ArrayList<DownloadTaskInfo>) list;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DownloadTaskInfo downloadTaskInfo = arrayList.get(size);
            if (this.mDownloadType != downloadTaskInfo.getType().intValue()) {
                arrayList.remove(size);
            } else if (downloadTaskInfo.getState().intValue() == 2) {
                this.mDownloadingTaskIds.add(downloadTaskInfo.getSavePath());
            }
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment
    public void onDropDownMenuClicked(int i) {
        switch (i) {
            case 9:
                MessageDialog messageDialog = new MessageDialog(getActivity(), (int) R.string.download_remove_all_confirm_hint, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.downloadmanager.UncompletedDownloadFragment.4
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(MessageDialog messageDialog2) {
                        if (UncompletedDownloadFragment.this.getActivity() != null) {
                            //SUserUtils.m4956a(SAction.ACTION_MY_DOWNLOAD_DELETE_ALL_SURE, SPage.PAGE_NONE);
                            messageDialog2.dismiss();
                            UncompletedDownloadFragment.this.deleteAllUncompleted();
                            //LocalStatistic.m5148aF();
                        }
                    }
                }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
                messageDialog.setTitle(R.string.download_remove_file_title);
                messageDialog.show();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAll() {
        for (DownloadTaskInfo downloadTaskInfo : this.mTasks) {
            if (downloadTaskInfo.getState().intValue() == 3 || downloadTaskInfo.getState().intValue() == 5) {
                CommandCenter.getInstance().m4596b(new Command(CommandID.ADD_DOWNLOAD_TASK, downloadTaskInfo));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseAll() {
        this.mHandler.removeMessages(0);
        for (DownloadTaskInfo downloadTaskInfo : this.mTasks) {
            if (downloadTaskInfo.getState().intValue() == 0 || downloadTaskInfo.getState().intValue() == 2 || downloadTaskInfo.getState().intValue() == 1) {
                CommandCenter.getInstance().m4596b(new Command(CommandID.CANCEL_DOWNLOAD_TASK, downloadTaskInfo));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteAllUncompleted() {
        Iterator<DownloadTaskInfo> it = this.mTasks.iterator();
        while (it.hasNext()) {
            CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_DOWNLOAD_TASK, it.next(), true));
        }
        this.mDownloadingTaskIds.clear();
        this.mHandler.removeMessages(0);
        clear();
    }

    private void sendMessage(int i) {
        sendMessageDelayed(i, 0L);
    }

    private void sendMessageDelayed(int i, long j) {
        if (!this.mHandler.hasMessages(i)) {
            this.mHandler.sendEmptyMessageDelayed(i, j);
        }
    }
}
