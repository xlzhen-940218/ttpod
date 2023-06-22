package com.sds.android.ttpod.fragment.apshare;

import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p069a.ApShareReceiveAdapter;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.apshare.ApShareUtils;
import com.sds.android.ttpod.component.apshare.ClientModel;
import com.sds.android.ttpod.component.apshare.ReceiveSongClient;
import com.sds.android.ttpod.component.apshare.SocketServer;
import com.sds.android.ttpod.component.apshare.WifiAPManager;
import com.sds.android.ttpod.component.apshare.WifiStateImpl;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ApShareReceiveFragment extends ApShareBaseFragment {
    private static final int CONNECT_WIFI_TIMEOUT_IN_MILLIS = 20000;
    private static final int SCAN_WIFI_INTERVAL_IN_MILLIS = 4000;
    private static final int SCAN_WIFI_MAXIMUM_TIMES = 100;
    private static final int STATE_CONNECTED = 5;
    private static final int STATE_CONNECTING = 4;
    private static final int STATE_SCANNING = 1;
    private static final int STATE_SCAN_STOP = 0;
    private static final int STATE_SEARCH_MULTI = 3;
    private static final int STATE_SEARCH_SINGLE = 2;
    private static final String TAG = "ApShareReceiveFragment";
    public static final int WHAT_DOWNLOAD = 100;
    public static final int WHAT_DOWNLOAD_CANCELED = 102;
    public static final int WHAT_GET_SHARED_LIST_COMPLETE = 101;
    private ApShareReceiveAdapter mAdapter;
    private int mChooseIndex;
    private ReceiveSongClient mClient;
    private ViewGroup mLayoutBottom;
    private ViewGroup mLayoutSearch;
    private ListView mListView;
    private String mMyIp;
    private TextView mRescanButton;
    private String mSSID;
    private SocketServer mServer;
    private int mState;
    private TextView mTvCancelAll;
    private TextView mTvDeviceTop;
    private TextView[] mTvDevices;
    private TextView mTvReceiveAll;
    private TextView mTvSubTitle;
    private TextView mTvTitle;
    private WifiAPManager mWifiApManager;
    private int mScanCount = 0;
    private List<ScanResult> mShareDeviceList = new ArrayList();
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == ApShareReceiveFragment.this.mTvCancelAll) {
                if (ApShareReceiveFragment.this.mAdapter != null) {
                    ApShareReceiveFragment.this.mAdapter.m7648c();
                }
            } else if (view == ApShareReceiveFragment.this.mTvReceiveAll) {
                if (ApShareReceiveFragment.this.mAdapter != null) {
                    ApShareReceiveFragment.this.mAdapter.m7647d();
                }
            } else {
                Object tag = view.getTag();
                if (tag instanceof Number) {
                    ApShareReceiveFragment.this.mChooseIndex = ((Number) tag).intValue();
                    ApShareReceiveFragment.this.switchState(2);
                }
            }
        }
    };
    private boolean mIsDialogShow = false;
    private boolean mIsConnected = false;
    private boolean mIsExist = false;
    private Runnable mScanWifiTask = new Runnable() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.6
        @Override // java.lang.Runnable
        public void run() {
            if (ApShareReceiveFragment.this.mWifiApManager != null) {
                ApShareReceiveFragment.this.mWifiApManager.m7030i();
            }
        }
    };
    private Runnable mScanWifiTimeoutTask = new Runnable() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.7
        @Override // java.lang.Runnable
        public void run() {
            ApShareReceiveFragment.this.switchState(0);
            ApShareReceiveFragment.this.mHandler.removeCallbacks(ApShareReceiveFragment.this.mScanWifiTask);
        }
    };
    private Runnable mConnectWifiTimeoutTask = new Runnable() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.8
        @Override // java.lang.Runnable
        public void run() {
            if (ApShareReceiveFragment.this.mWifiApManager != null) {
                ApShareReceiveFragment.this.mWifiApManager.m7030i();
                ApShareReceiveFragment.this.switchState(1);
            }
        }
    };
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.9
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (ApShareReceiveFragment.this.getActivity() != null) {
                switch (message.what) {
                    case 4:
                        if (ApShareReceiveFragment.this.mShareDeviceList.size() == 0 && ApShareReceiveFragment.this.mState == 1) {
                            ApShareReceiveFragment.this.switchState(1);
                            return;
                        }
                        return;
                    case 5:
                        if (ApShareReceiveFragment.this.mState != 4) {
                            ApShareReceiveFragment.this.wifiScanFinished(message);
                            return;
                        }
                        return;
                    case 7:
                        ApShareReceiveFragment.this.wifiConnected(message);
                        return;
                    case 8:
                        ApShareReceiveFragment.this.wifiDisconnected();
                        return;
                    case 100:
                        ApShareReceiveFragment.this.startDownload(message);
                        return;
                    case ApShareReceiveFragment.WHAT_GET_SHARED_LIST_COMPLETE /* 101 */:
                        ApShareReceiveFragment.this.mTvReceiveAll.setText(ApShareReceiveFragment.this.getString(R.string.share_receive_all, (Integer) message.obj));
                        return;
                    case ApShareReceiveFragment.WHAT_DOWNLOAD_CANCELED /* 102 */:
                        ApShareReceiveFragment.this.mClient.m7084b();
                        return;
                    default:
                        return;
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.apshare.ApShareBaseFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        getActionBarController().m7189b(R.string.share_receive_media);
        View inflate = layoutInflater.inflate(R.layout.apshare_receiver_main, (ViewGroup) null);
        bindView(inflate);
        this.mWifiApManager = WifiAPManager.m7053a(getActivity());
        this.mWifiApManager.m7049a(new WifiStateImpl(this.mHandler));
        this.mWifiApManager.m7040b("TTPODShare-");
        this.mWifiApManager.m7044a(false);
        ApShareUtils.m7110b(this.mWifiApManager.m7054a());
        ApShareUtils.m7112a(this.mWifiApManager.m7036d());
        WifiInfo m7028k = this.mWifiApManager.m7028k();
        if (m7028k != null) {
            ApShareUtils.m7109c(m7028k.getSSID());
        }
        inflate.setKeepScreenOn(true);
        return inflate;
    }

    private void bindView(View view) {
        this.mLayoutSearch = (ViewGroup) view.findViewById(R.id.layout_search);
        this.mListView = (ListView) view.findViewById(R.id.listview);
        this.mLayoutBottom = (ViewGroup) view.findViewById(R.id.layout_bottom);
        this.mTvCancelAll = (TextView) this.mLayoutBottom.findViewById(R.id.tv_cancel_all);
        this.mTvReceiveAll = (TextView) this.mLayoutBottom.findViewById(R.id.tv_receive_all);
        this.mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        this.mTvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);
        this.mTvDevices = new TextView[3];
        this.mTvDeviceTop = (TextView) view.findViewById(R.id.tv_device_top);
        this.mTvDevices[0] = this.mTvDeviceTop;
        this.mTvDevices[1] = (TextView) view.findViewById(R.id.tv_device_center);
        this.mTvDevices[2] = (TextView) view.findViewById(R.id.tv_device_bottom);
        this.mRescanButton = (TextView) view.findViewById(R.id.tv_rescan_wifi);
        for (TextView textView : this.mTvDevices) {
            textView.setOnClickListener(this.mOnClickListener);
        }
        this.mTvCancelAll.setOnClickListener(this.mOnClickListener);
        this.mTvReceiveAll.setOnClickListener(this.mOnClickListener);
        this.mTvReceiveAll.setText(getString(R.string.share_receiving));
        this.mRescanButton.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ApShareReceiveFragment.this.mWifiApManager.m7030i();
                ApShareReceiveFragment.this.switchState(1);
                ApShareReceiveFragment.this.mScanCount = 0;
            }
        });
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mWifiApManager.m7030i();
        switchState(1);
        this.mScanCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchState(int i) {
        if (this.mAdapter != null) {
            this.mAdapter = null;
            this.mListView.setAdapter((ListAdapter) this.mAdapter);
        }
        this.mState = i;
        switch (this.mState) {
            case 0:
                this.mTvTitle.setText(R.string.share_searching_no_sharer);
                showScanWifiTimeoutView();
                return;
            case 1:
                showSearchView(true);
                this.mTvTitle.setText(R.string.share_searching);
                this.mTvSubTitle.setText(R.string.share_searching_subtitle);
                invisibleAllDeviceView();
                return;
            case 2:
                showSearchView(true);
                this.mTvTitle.setText(R.string.share_searched_connecting);
                this.mTvSubTitle.setText(R.string.share_receive_prompt_title);
                showSingleDevice();
                return;
            case 3:
                showSearchView(true);
                this.mTvTitle.setText(R.string.share_received_multi_device);
                this.mTvSubTitle.setText(R.string.share_receive_prompt_title);
                this.mTvDeviceTop.setBackgroundResource(R.drawable.apshare_bkg_device_enabled);
                showMultiDevice();
                return;
            case 4:
            default:
                return;
            case 5:
                showSearchView(false);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        exitDialog();
    }

    private void exitDialog() {
        MessageDialog messageDialog = new MessageDialog(getActivity(), (int) R.string.share_receive_cancel, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.3
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                if (ApShareReceiveFragment.this.mClient == null) {
                    if (ApShareReceiveFragment.this.mWifiApManager != null) {
                        ApShareReceiveFragment.this.mWifiApManager.m7040b("TTPODShare-");
                    }
                } else {
                    ApShareReceiveFragment.this.mClient.m7085a("disconnect", new ClientModel(ApShareReceiveFragment.this.mMyIp, Build.MODEL), new ReceiveSongClient.InterfaceC1118a() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.3.1
                        @Override // com.sds.android.ttpod.component.apshare.ReceiveSongClient.InterfaceC1118a
                        /* renamed from: a */
                        public void mo5779a() {
                            if (ApShareReceiveFragment.this.mWifiApManager != null) {
                                ApShareReceiveFragment.this.mWifiApManager.m7040b("TTPODShare-");
                            }
                        }
                    });
                }
                ApShareReceiveFragment.this.mClient = null;
                ApShareUtils.m7120a(ApShareReceiveFragment.this.mWifiApManager);
                ApShareReceiveFragment.this.mHandler.removeCallbacks(ApShareReceiveFragment.this.mScanWifiTask);
                if (ApShareReceiveFragment.this.mWifiApManager != null) {
                    ApShareReceiveFragment.this.mWifiApManager.m7046a(ApShareUtils.m7122a());
                }
                if (ApShareReceiveFragment.this.mServer != null) {
                    ApShareReceiveFragment.this.mServer.m7058a();
                    ApShareReceiveFragment.this.mServer = null;
                }
                ApShareReceiveFragment.this.mIsExist = true;
                ApShareReceiveFragment.this.finish();
            }
        }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.prompt_title);
        messageDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectedDialog(String str) {
        this.mIsDialogShow = true;
        if (getActivity() == null) {
            LogUtils.m8379d(TAG, "disconnectedDialog: getActivity() = true");
            reset();
            return;
        }
        MessageDialog messageDialog = new MessageDialog(getActivity(), str, (int) R.string.iknown, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.4
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                ApShareReceiveFragment.this.reset();
            }
        });
        messageDialog.setTitle(R.string.prompt_title);
        messageDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.mShareDeviceList.clear();
        this.mWifiApManager.m7030i();
        this.mHandler.removeCallbacks(this.mConnectWifiTimeoutTask);
        this.mClient = null;
        switchState(1);
        this.mIsDialogShow = false;
        this.mIsConnected = false;
        if (this.mServer != null) {
            this.mServer.m7058a();
            this.mServer = null;
        }
        this.mScanCount = 0;
    }

    private void showSingleDevice() {
        invisibleAllDeviceView();
        if (this.mChooseIndex < this.mShareDeviceList.size()) {
            this.mTvDeviceTop.setVisibility(View.VISIBLE);
            this.mTvDeviceTop.setBackgroundResource(R.drawable.apshare_bkg_device_disable);
            this.mTvDeviceTop.setEnabled(false);
            this.mTvDeviceTop.setText(this.mShareDeviceList.get(this.mChooseIndex).SSID.replace("TTPODShare-", ""));
            this.mWifiApManager.m7052a(this.mShareDeviceList.get(this.mChooseIndex));
            LogUtils.m8379d(TAG, "connect to ap: " + this.mShareDeviceList.get(this.mChooseIndex).SSID);
            this.mState = 4;
            LogUtils.m8379d(TAG, "add a mConnectWifiTimeoutTask");
            this.mHandler.postDelayed(this.mConnectWifiTimeoutTask, 20000L);
        }
    }

    private void showScanWifiTimeoutView() {
        invisibleAllDeviceView();
        this.mRescanButton.setVisibility(View.VISIBLE);
    }

    private void invisibleAllDeviceView() {
        for (TextView textView : this.mTvDevices) {
            textView.setVisibility(View.INVISIBLE);
        }
    }

    private void showMultiDevice() {
        invisibleAllDeviceView();
        for (int i = 0; i < this.mTvDevices.length && i < this.mShareDeviceList.size(); i++) {
            TextView textView = this.mTvDevices[i];
            textView.setVisibility(View.VISIBLE);
            textView.setTag(Integer.valueOf(i));
            textView.setText(this.mShareDeviceList.get(i).SSID.replace("TTPODShare-", ""));
        }
    }

    private void showSearchView(boolean z) {
        this.mLayoutSearch.setVisibility(z ? View.VISIBLE : View.GONE);
        this.mLayoutBottom.setVisibility(z ? View.GONE : View.VISIBLE);
        this.mListView.setVisibility(z ? View.GONE : View.VISIBLE);
        this.mRescanButton.setVisibility(View.GONE);
    }

    @Override // com.sds.android.ttpod.fragment.apshare.ApShareBaseFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    private void getMediaItemList() {
        this.mAdapter = new ApShareReceiveAdapter(getActivity(), this.mHandler);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        DhcpInfo dhcpInfo = this.mWifiApManager.m7029j().getDhcpInfo();
        this.mMyIp = ApShareUtils.m7121a(dhcpInfo.ipAddress);
        this.mClient = new ReceiveSongClient(ApShareUtils.m7121a(dhcpInfo.gateway), this.mHandler, this.mAdapter);
        try {
            if (this.mClient.m7085a("who_am_i", new ClientModel(this.mMyIp, Build.MODEL), (ReceiveSongClient.InterfaceC1118a) null).get().booleanValue()) {
                PopupsUtils.m6721a(getString(R.string.share_receive_force_disconnect, this.mSSID));
                switchState(1);
                this.mWifiApManager.m7030i();
            } else {
                this.mClient.m7096a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownload(Message message) {
        if (this.mClient != null) {
            TransmittableMediaItem transmittableMediaItem = (TransmittableMediaItem) message.obj;
            if (transmittableMediaItem == null) {
                PopupsUtils.m6721a(getString(R.string.share_data_transfer_error));
                return;
            }
            transmittableMediaItem.m5763c(Build.MODEL);
            transmittableMediaItem.m5761d(this.mMyIp);
            this.mClient.m7090a(transmittableMediaItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wifiScanFinished(Message message) {
        this.mShareDeviceList = (List) message.obj;
        this.mScanCount++;
        LogUtils.m8380c(TAG, "scan finished, size: %d, scan = %d", Integer.valueOf(this.mShareDeviceList.size()), Integer.valueOf(this.mScanCount));
        if (this.mShareDeviceList.size() == 0) {
            switchState(1);
        } else if (this.mShareDeviceList.size() == 1) {
            switchState(2);
        } else if (this.mShareDeviceList.size() > 1) {
            switchState(3);
        }
        if (this.mScanCount >= 100) {
            LogUtils.m8379d(TAG, "add mScanWifiTimeoutTask");
            this.mHandler.post(this.mScanWifiTimeoutTask);
            this.mState = 0;
        }
        if ((this.mShareDeviceList.size() == 0 || this.mShareDeviceList.size() > 1) && this.mState != 0) {
            LogUtils.m8380c(TAG, "rescan, state = %s, add mScanWifiTask", Integer.valueOf(this.mState));
            this.mHandler.postDelayed(this.mScanWifiTask, 4000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wifiConnected(Message message) {
        this.mIsConnected = true;
        switchState(5);
        this.mHandler.removeCallbacks(this.mConnectWifiTimeoutTask);
        this.mSSID = ((String) message.obj).replace("TTPODShare-", "");
        this.mTvTitle.setText(getString(R.string.share_connect_ap_success, this.mSSID));
        this.mHandler.removeCallbacks(this.mScanWifiTask);
        getMediaItemList();
        startClientSideServer();
        this.mScanCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wifiDisconnected() {
        boolean z;
        WifiInfo m7028k = this.mWifiApManager.m7028k();
        if (m7028k == null) {
            z = false;
        } else {
            z = m7028k.getSupplicantState() == SupplicantState.DISCONNECTED;
        }
        if (z && this.mState == 5 && !this.mIsDialogShow && this.mIsConnected && !this.mIsExist) {
            disconnectedDialog(getString(R.string.share_receive_sharer_finish_sharing, this.mSSID));
        }
    }

    private void startClientSideServer() {
        if (this.mServer != null) {
            this.mServer.m7058a();
        }
        this.mServer = new SocketServer(new SocketServer.InterfaceC1130b() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.5
            @Override // com.sds.android.ttpod.component.apshare.SocketServer.InterfaceC1130b
            /* renamed from: a */
            public void mo5777a(Socket socket) {
                try {
                    String readLine = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
                    LogUtils.m8379d(ApShareReceiveFragment.TAG, "receive message: " + readLine);
                    if (readLine.equals("finished")) {
                        ApShareReceiveFragment.this.mHandler.post(new Runnable() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ApShareReceiveFragment.this.disconnectedDialog(ApShareReceiveFragment.this.getString(R.string.share_receive_force_disconnect, ApShareReceiveFragment.this.mSSID));
                            }
                        });
                    }
                    socket.close();
                    ApShareReceiveFragment.this.mServer.m7058a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5002);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }
}
