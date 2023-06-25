package com.sds.android.ttpod.fragment.apshare;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p069a.ApSharingAdapter;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.apshare.ApShareUtils;
import com.sds.android.ttpod.component.apshare.ClientConnectListener;
import com.sds.android.ttpod.component.apshare.ClientModel;
import com.sds.android.ttpod.component.apshare.ShareSongServer;
import com.sds.android.ttpod.component.apshare.SocketClient;
import com.sds.android.ttpod.component.apshare.WifiAPManager;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.ActionListDialog;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.StateView;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes.dex */
public class ApSharingFragment extends ApShareBaseFragment implements ApSharingAdapter.InterfaceC0957a, ClientConnectListener {
    private static final int BACK_PRESS_DELAY = 200;
    private static final String TAG = "ApSharingFragment";
    private ApSharingAdapter mAdapter;
    private List<String> mClientBlackList;
    private Map<String, String> mClients;
    private Map<String, Integer> mDownloadCount;
    private ListView mListView;
    private ShareSongServer mServer;
    private ArrayList<MediaItem> mSharedList;
    private StateView mStateView;
    private TextView mTvConnected;
    private TextView mTvStopShare;
    private long mViewCreateTime;
    private WifiAPManager mWifiApManager;
    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private Handler mUIHandler = new Handler(Looper.getMainLooper());
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.apshare.ApSharingFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == ApSharingFragment.this.mTvStopShare) {
                ApSharingFragment.this.exitDialog();
            } else if (view == ApSharingFragment.this.mTvConnected) {
                if (ApSharingFragment.this.mClients.size() > 0) {
                    ApSharingFragment.this.popConnectedDeviceDialog();
                } else {
                    PopupsUtils.m6760a((int) R.string.share_prompt_no_connected_device);
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.apshare.ApShareBaseFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        getActionBarController().m7189b(R.string.share_sharing);
        View inflate = layoutInflater.inflate(R.layout.apshare_sharing_main, (ViewGroup) null);
        this.mStateView = (StateView) inflate.findViewById(R.id.stateview);
        this.mStateView.setState(StateView.EnumC2248b.NO_DATA);
        this.mListView = (ListView) this.mStateView.findViewById(R.id.listview);
        this.mTvStopShare = (TextView) inflate.findViewById(R.id.tv_stop_share);
        this.mTvConnected = (TextView) inflate.findViewById(R.id.tv_connected);
        this.mAdapter = new ApSharingAdapter(layoutInflater.getContext());
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mTvStopShare.setOnClickListener(this.mOnClickListener);
        this.mTvConnected.setText(getString(R.string.share_connected_device, 0));
        this.mTvConnected.setOnClickListener(this.mOnClickListener);
        this.mWifiApManager = WifiAPManager.m7053a(getActivity());
        this.mWifiApManager.m7044a(false);
        this.mClients = new HashMap();
        this.mClientBlackList = new ArrayList();
        this.mDownloadCount = new HashMap();
        this.mAdapter.m7643a(this);
        return inflate;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSharedList = getArguments().getParcelableArrayList("key_data");
        this.mTvStopShare.setText(getString(R.string.share_stop, Integer.valueOf(this.mSharedList.size())));
        this.mServer = new ShareSongServer(this.mSharedList, this.mAdapter, this, 5001);
        this.mTvStopShare.setText(getString(R.string.share_stop, Integer.valueOf(this.mSharedList.size())));
        this.mViewCreateTime = SystemClock.currentThreadTimeMillis();
    }

    @Override // com.sds.android.ttpod.fragment.apshare.ApShareBaseFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        exitDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitDialog() {
        FragmentActivity activity;
        if (SystemClock.currentThreadTimeMillis() - this.mViewCreateTime >= 200 && (activity = getActivity()) != null) {
            MessageDialog messageDialog = new MessageDialog(activity, (int) R.string.share_prompt_cancel, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.apshare.ApSharingFragment.2
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MessageDialog messageDialog2) {
                    TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.fragment.apshare.ApSharingFragment.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (ApSharingFragment.this.mWifiApManager != null && ApSharingFragment.this.mWifiApManager.m7038c()) {
                                ApSharingFragment.this.mWifiApManager.m7033f();
                            }
                            if (ApSharingFragment.this.mServer != null) {
                                ApSharingFragment.this.mServer.m7072a();
                            }
                            ApShareUtils.m7120a(ApSharingFragment.this.mWifiApManager);
                            if (ApSharingFragment.this.mWifiApManager != null) {
                                ApSharingFragment.this.mWifiApManager.m7046a(ApShareUtils.m7122a());
                            }
                        }
                    });
                    ApSharingFragment.this.finish();
                }
            }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
            messageDialog.setTitle(R.string.prompt_title);
            messageDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void popConnectedDeviceDialog() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.mClients.keySet()) {
            ActionItem actionItem = new ActionItem(0, 0, this.mClients.get(str), "下载了" + this.mDownloadCount.get(str) + "首");
            actionItem.m7009a(new ClientModel(str, this.mClients.get(str)));
            arrayList.add(actionItem);
        }
        ActionListDialog actionListDialog = new ActionListDialog(getActivity(), arrayList, new ActionListDialog.InterfaceC1142a() { // from class: com.sds.android.ttpod.fragment.apshare.ApSharingFragment.3
            @Override // com.sds.android.ttpod.component.p087d.p088a.ActionListDialog.InterfaceC1142a
            /* renamed from: a */
            public void mo5775a(ActionItem actionItem2) {
                ApSharingFragment.this.finishConnections((ClientModel) actionItem2.m7004f());
            }
        }, null, null);
        actionListDialog.m7254b(R.string.cancel, null);
        actionListDialog.setTitle(R.string.share_dlg_connected_device);
        actionListDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Future<Boolean> finishConnections(final ClientModel clientModel) {
        return this.mExecutor.submit(new Callable<Boolean>() { // from class: com.sds.android.ttpod.fragment.apshare.ApSharingFragment.4
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public Boolean call() throws Exception {
                try {
                    final SocketClient socketClient = new SocketClient(clientModel.m7126a(), 5002);
                    ApSharingFragment.this.mServer.m7066a(ApSharingFragment.this.mClientBlackList);
                    socketClient.m7061a(new SocketClient.InterfaceC1127b() { // from class: com.sds.android.ttpod.fragment.apshare.ApSharingFragment.4.1
                        @Override // com.sds.android.ttpod.component.apshare.SocketClient.InterfaceC1127b
                        /* renamed from: a */
                        public void mo5773a(OutputStream outputStream) {
                            PrintWriter printWriter = new PrintWriter(outputStream);
                            printWriter.print("finished\r\n");
                            printWriter.flush();
                            socketClient.m7060b();
                        }
                    });
                    ApSharingFragment.this.mClients.remove(clientModel.m7126a());
                    ApSharingFragment.this.mClientBlackList.add(clientModel.m7126a());
                    LogUtils.info(ApSharingFragment.TAG, "size of clients = " + ApSharingFragment.this.mClients.size() + ", black list size = " + ApSharingFragment.this.mClientBlackList.size());
                    ApSharingFragment.this.mUIHandler.post(new Runnable() { // from class: com.sds.android.ttpod.fragment.apshare.ApSharingFragment.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            PopupsUtils.m6721a(ApSharingFragment.this.getString(R.string.share_add_to_blacklist, clientModel.m7125b()));
                            ApSharingFragment.this.mTvConnected.setText(ApSharingFragment.this.getString(R.string.share_connected_device, Integer.valueOf(ApSharingFragment.this.mClients.size())));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    PopupsUtils.m6760a((int) R.string.share_finish_connection_error);
                }
                return true;
            }
        });
    }

    @Override // com.sds.android.ttpod.component.apshare.ClientConnectListener
    public void onConnected(ClientModel clientModel) {
        if (getActivity() != null) {
            this.mClients.put(clientModel.m7126a(), clientModel.m7125b());
            LogUtils.info(TAG, "add a client: " + clientModel.m7125b() + ", " + clientModel.m7126a() + ", size=" + this.mClients.size());
            PopupsUtils.m6721a(getString(R.string.share_add_a_client, clientModel.m7125b()));
            this.mDownloadCount.put(clientModel.m7126a(), 0);
            this.mTvConnected.setText(getString(R.string.share_connected_device, Integer.valueOf(this.mClients.size())));
        }
    }

    @Override // com.sds.android.ttpod.component.apshare.ClientConnectListener
    public void onDisconnected(ClientModel clientModel) {
        if (getActivity() != null) {
            this.mClients.remove(clientModel.m7126a());
            this.mTvConnected.setText(getString(R.string.share_connected_device, Integer.valueOf(this.mClients.size())));
            LogUtils.info(TAG, clientModel.m7125b() + " is leave , ip = " + clientModel.m7126a() + ", size =" + this.mClients.size());
        }
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApSharingAdapter.InterfaceC0957a
    public void onTransmitBegin(TransmittableMediaItem transmittableMediaItem) {
        this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApSharingAdapter.InterfaceC0957a
    public void onTransmitComplete(TransmittableMediaItem transmittableMediaItem) {
        if (!this.mDownloadCount.containsKey(transmittableMediaItem.m5760e())) {
            this.mDownloadCount.put(transmittableMediaItem.m5760e(), 0);
        }
        this.mDownloadCount.put(transmittableMediaItem.m5760e(), Integer.valueOf(this.mDownloadCount.get(transmittableMediaItem.m5760e()).intValue() + 1));
    }
}
