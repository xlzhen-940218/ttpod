package com.sds.android.ttpod.fragment.downloadmanager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.OptionalDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.FileOpenUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CompletedDownloadFragment extends DownloadTaskListFragment {
    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTaskListView.removeHeaderView(getTopActionPanel());
        this.mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.downloadmanager.CompletedDownloadFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) CompletedDownloadFragment.this.mTaskAdapter.getItem(i);
                if (downloadTaskInfo.getState().intValue() == 4) {
                    CompletedDownloadFragment.this.open(downloadTaskInfo);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.m8375a(getClass(), "updateTaskState", DownloadTaskInfo.class));
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment
    public void updateTaskState(DownloadTaskInfo downloadTaskInfo) {
        if (downloadTaskInfo.getType().intValue() == this.mDownloadType && downloadTaskInfo.getState().intValue() == 4) {
            addTask(0, downloadTaskInfo);
        }
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment
    protected List<DownloadTaskInfo> readTaskList() {
        List<DownloadTaskInfo> list = (List) CommandCenter.m4607a().m4602a(new Command(CommandID.GET_TASK_LIST_WITH_STATE, 0), List.class);
        ArrayList<DownloadTaskInfo> arrayList = list == null ? new ArrayList(0) : (ArrayList) list;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (this.mDownloadType != arrayList.get(size).getType().intValue()) {
                arrayList.remove(size);
            }
        }
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment
    public void onDropDownMenuClicked(int i) {
        if (i == 10) {
            PopupsUtils.m6747a(getActivity(), (int) R.string.download_remove_file_message, BaseApplication.getApplication().getString(R.string.delete_all_download), BaseApplication.getApplication().getString(R.string.download_remove_all_confirm_hint), new BaseDialog.InterfaceC1064a<OptionalDialog>() { // from class: com.sds.android.ttpod.fragment.downloadmanager.CompletedDownloadFragment.2
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(OptionalDialog optionalDialog) {
                    CompletedDownloadFragment.this.deleteAllCompleted(optionalDialog.m6808b());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteAllCompleted(boolean z) {
        SUserUtils.m4956a(SAction.ACTION_MY_DOWNLOAD_DELETE_ALL_SURE, SPage.PAGE_NONE);
        clear();
        CommandCenter.m4607a().m4596b(new Command(CommandID.DELETE_ALL_FINISHED_DOWNLOAD_TASK, Integer.valueOf(this.mDownloadType), Boolean.valueOf(z)));
    }

    private void delete(DownloadTaskInfo downloadTaskInfo, boolean z) {
        removeTask(downloadTaskInfo);
        CommandCenter.m4607a().m4596b(new Command(CommandID.DELETE_DOWNLOAD_TASK, downloadTaskInfo, Boolean.valueOf(z)));
    }

    private void setRing(DownloadTaskInfo downloadTaskInfo) {
        PopupsUtils.m6740a((Context) getActivity(), MediaItemUtils.m4712a(downloadTaskInfo.getSavePath()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void open(DownloadTaskInfo downloadTaskInfo) {
        FileOpenUtils.m8281a(getActivity(), downloadTaskInfo);
    }
}
