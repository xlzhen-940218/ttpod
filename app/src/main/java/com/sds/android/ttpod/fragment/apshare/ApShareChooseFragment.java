package com.sds.android.ttpod.fragment.apshare;

import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p069a.ApShareChooseAdapter;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.apshare.ApShareUtils;
import com.sds.android.ttpod.component.apshare.WifiAPManager;
import com.sds.android.ttpod.component.apshare.WifiStateImpl;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ApShareChooseFragment extends ApShareBaseFragment implements ApShareChooseAdapter.InterfaceC0954a {
    private static final String TAG = "ApShareChooseFragment";
    private ApShareChooseAdapter mAdapter;
    private ActionBarController.C1070a mChooseAction;
    private ListView mListView;
    private TextView mTvAction;
    private WifiAPManager mWifiApManager;
    private boolean mEnableBackPressed = true;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareChooseFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == ApShareChooseFragment.this.mTvAction) {
                LocalStatistic.m5099g();
                WifiInfo m7028k = ApShareChooseFragment.this.mWifiApManager.m7028k();
                if (m7028k != null) {
                    ApShareUtils.m7109c(m7028k.getSSID());
                }
                ApShareChooseFragment.this.startWifiAp();
            }
        }
    };
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareChooseFragment.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            LogUtils.m8379d(ApShareChooseFragment.TAG, "what= " + message.what);
            switch (message.what) {
                case 12:
                default:
                    return;
                case 13:
                    ApShareChooseFragment.this.mTvAction.setText(R.string.share_enabling_ap);
                    return;
                case 14:
                    if (ApShareChooseFragment.this.getActivity() == null) {
                        str = "已经打开热点：";
                    } else {
                        str = ApShareChooseFragment.this.getString(R.string.share_enabled_ap);
                    }
                    String str2 = (String) message.obj;
                    if (StringUtils.m8346a(str2)) {
                        ApShareChooseFragment.this.openApError();
                        return;
                    }
                    ApShareChooseFragment.this.mEnableBackPressed = true;
                    ApShareChooseFragment.this.mTvAction.setText(str + str2);
                    ApShareChooseFragment.this.launch();
                    return;
                case 15:
                    ApShareChooseFragment.this.mTvAction.setText(R.string.share_enable_ap_failed);
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void startWifiAp() {
        this.mTvAction.setText(R.string.share_creating);
        this.mTvAction.setEnabled(false);
        this.mListView.setEnabled(false);
        this.mAdapter.m7650f();
        this.mChooseAction.m7165a(false);
        this.mEnableBackPressed = false;
        if (this.mWifiApManager != null) {
            this.mWifiApManager.m7034e();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.apshare.ApShareBaseFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7189b(R.string.share_choose);
        View inflate = layoutInflater.inflate(R.layout.apshare_choose, (ViewGroup) null);
        this.mTvAction = (TextView) inflate.findViewById(R.id.tv_action);
        this.mListView = (ListView) inflate.findViewById(R.id.listview);
        this.mAdapter = new ApShareChooseAdapter(layoutInflater.getContext(), this);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareChooseFragment.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int headerViewsCount = i - ApShareChooseFragment.this.mListView.getHeaderViewsCount();
                if (ApShareChooseFragment.this.mEnableBackPressed && headerViewsCount < ApShareChooseFragment.this.mAdapter.getCount()) {
                    ApShareChooseFragment.this.mAdapter.m7652d(ApShareChooseFragment.this.mAdapter.getItem(headerViewsCount));
                }
            }
        });
        this.mTvAction.setOnClickListener(this.mOnClickListener);
        this.mChooseAction = actionBarController.m7178d(R.drawable.img_checkbox_multiselect_checked);
        this.mChooseAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareChooseFragment.3
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                boolean z = !ApShareChooseFragment.this.mAdapter.m7654c();
                ApShareChooseFragment.this.mChooseAction.m7153d(z ? R.drawable.img_checkbox_multiselect_checked : R.drawable.img_checkbox_multiselect_unchecked);
                ApShareChooseFragment.this.mAdapter.m7655a(z);
            }
        });
        this.mWifiApManager = WifiAPManager.m7053a(getActivity());
        if (this.mWifiApManager.m7038c()) {
            this.mWifiApManager.m7033f();
        }
        LogUtils.m8379d(TAG, "setWifiStateListener");
        this.mWifiApManager.m7049a(new WifiStateImpl(this.mHandler));
        ApShareUtils.m7110b(this.mWifiApManager.m7054a());
        ApShareUtils.m7112a(this.mWifiApManager.m7036d());
        return inflate;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        String string;
        super.onViewCreated(view, bundle);
        MediaItem mediaItem = null;
        Bundle arguments = getArguments();
        if (arguments != null && (mediaItem = MediaStorage.queryMediaItem(getActivity(), MediaStorage.GROUP_ID_ALL_LOCAL, (string = arguments.getString("key_media_id")))) == null) {
            mediaItem = MediaStorage.queryMediaItem(getActivity(), MediaStorage.GROUP_ID_FAV, string);
        }
        ArrayList arrayList = new ArrayList();
        if (mediaItem != null) {
            arrayList.add(new TransmittableMediaItem(mediaItem));
            this.mTvAction.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.apshare.ApShareChooseFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    ApShareChooseFragment.this.startWifiAp();
                }
            }, 1000L);
        } else {
            for (MediaItem mediaItem2 : MediaStorage.queryMediaItemList(getActivity(), MediaStorage.GROUP_ID_ALL_LOCAL, MediaStorage.MEDIA_ORDER_BY_FILE_NAME)) {
                arrayList.add(new TransmittableMediaItem(mediaItem2));
            }
        }
        this.mAdapter.mo7656a(arrayList);
        flushChoosedCountView();
    }

    private void flushChoosedCountView() {
        int m7653d = this.mAdapter.m7653d();
        this.mTvAction.setText(getString(R.string.share_start, Integer.valueOf(m7653d)));
        this.mTvAction.setEnabled(m7653d > 0);
    }

    @Override // com.sds.android.ttpod.adapter.p069a.ApShareChooseAdapter.InterfaceC0954a
    public void onChooseAmountChanged() {
        this.mChooseAction.m7153d(this.mAdapter.m7654c() ? R.drawable.img_checkbox_multiselect_checked : R.drawable.img_checkbox_multiselect_unchecked);
        flushChoosedCountView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launch() {
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(getActivity() != null);
        LogUtils.m8386a(TAG, "launch getActivity() != null %b", objArr);
        if (getActivity() != null) {
            this.mWifiApManager.m7049a((WifiAPManager.InterfaceC1132a) null);
            finish();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("key_data", this.mAdapter.m7651e());
            BaseFragment baseFragment = (BaseFragment) Fragment.instantiate(getActivity(), ApSharingFragment.class.getName(), bundle);
            if (baseFragment != null) {
                launchFragment(baseFragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openApError() {
        this.mWifiApManager.m7033f();
        PopupsUtils.m6760a((int) R.string.share_enable_ap_failed);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public void onTitleClicked() {
        if (this.mEnableBackPressed) {
            this.mWifiApManager.m7049a((WifiAPManager.InterfaceC1132a) null);
            super.onTitleClicked();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        if (this.mEnableBackPressed) {
            this.mWifiApManager.m7049a((WifiAPManager.InterfaceC1132a) null);
            super.onBackPressed();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        LogUtils.m8379d(TAG, "onPause");
        this.mWifiApManager.m7049a((WifiAPManager.InterfaceC1132a) null);
        finish();
        super.onPause();
    }
}
