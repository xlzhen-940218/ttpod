package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment;
import com.sds.android.ttpod.fragment.downloadmanager.UncompletedDownloadFragment;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;

/* loaded from: classes.dex */
public class LocalMVFragment extends DownloadManagerFragment {
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7189b(R.string.my_mv);
        setDownloadType(DownloadTaskInfo.TYPE_VIDEO);
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment
    protected Fragment getCompletedDownloadFragment() {
        return new LocalMVCompletedFragment(new DownloadTaskListFragment.InterfaceC1436b() { // from class: com.sds.android.ttpod.fragment.main.findsong.LocalMVFragment.1
            @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.InterfaceC1436b
            /* renamed from: a */
            public void mo5633a(int i) {
                LocalMVFragment.this.updateTabTitleAt(0, LocalMVFragment.this.getString(R.string.download_completed, Integer.valueOf(i)));
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment
    protected Fragment getUncompletedDownloadFragment() {
        Bundle bundle = new Bundle(1);
        bundle.putInt(DownloadManagerFragment.DOWNLOAD_TYPE, DownloadTaskInfo.TYPE_VIDEO.intValue());
        DownloadTaskListFragment downloadTaskListFragment = (DownloadTaskListFragment) Fragment.instantiate(getActivity(), UncompletedDownloadFragment.class.getName(), bundle);
        downloadTaskListFragment.setOnTaskCountChangeListener(new DownloadTaskListFragment.InterfaceC1436b() { // from class: com.sds.android.ttpod.fragment.main.findsong.LocalMVFragment.2
            @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.InterfaceC1436b
            /* renamed from: a */
            public void mo5633a(int i) {
                LocalMVFragment.this.updateTabTitleAt(1, LocalMVFragment.this.getString(R.string.download_running, Integer.valueOf(i)));
            }
        });
        return downloadTaskListFragment;
    }

    @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needMenuAction() {
        return false;
    }
}
