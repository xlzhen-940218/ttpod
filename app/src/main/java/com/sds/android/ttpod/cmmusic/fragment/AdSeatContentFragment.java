package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.sdk.lib.p065e.TaskScheduler;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p077a.HotSondAdapter;
import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import com.sds.android.ttpod.cmmusic.p079c.HotActivityContentJson;
import com.sds.android.ttpod.cmmusic.p081e.SondContentArrayPut;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class AdSeatContentFragment extends BaseFragment {
    private HotSondAdapter mAdapter;
    private String mHref;
    private ListView mListContent;
    private Thread mLoadWebDataThread;
    private ArrayList<HashMap<String, String>> mViewContentList = new ArrayList<>();
    private boolean mIsNull = false;
    private ArrayList<HashMap<String, String>> mItemInfoListTemp = new ArrayList<>();
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.cmmusic.fragment.AdSeatContentFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    AdSeatContentFragment.this.mViewContentList.addAll(AdSeatContentFragment.this.mItemInfoListTemp);
                    AdSeatContentFragment.this.mItemInfoListTemp.clear();
                    AdSeatContentFragment.this.mAdapter.notifyDataSetChanged();
                    AdSeatContentFragment.this.mIsNull = false;
                    return;
                default:
                    return;
            }
        }
    };

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cmmusic_ad_seat_content_activity, viewGroup, false);
        getIntentData();
        listViewInit(inflate);
        getDBViewContent();
        return inflate;
    }

    private void getIntentData() {
        try {
            this.mHref = getArguments().getString("href");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDBViewContent() {
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.AdSeatContentFragment.1
            @Override // java.lang.Runnable
            public void run() {
                AdSeatContentFragment.this.mItemInfoListTemp = new ArrayList();
                List<SondContentInfo> m7326a = HotActivityContentJson.m7326a(AdSeatContentFragment.this.mHref);
                if (AdSeatContentFragment.this.isViewAccessAble()) {
                    AdSeatContentFragment.this.mItemInfoListTemp.addAll(SondContentArrayPut.m7272a(m7326a));
                    Message message = new Message();
                    message.what = 1;
                    AdSeatContentFragment.this.mHandler.sendMessage(message);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScrollAddData(int i) {
        this.mItemInfoListTemp = new ArrayList<>();
        try {
            List<SondContentInfo> m7325a = HotActivityContentJson.m7325a(this.mHref, Integer.valueOf(i));
            if (isViewAccessAble()) {
                this.mItemInfoListTemp.addAll(SondContentArrayPut.m7272a(m7325a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    private void listViewInit(View view) {
        this.mListContent = (ListView) view.findViewById(R.id.ad_list_seatpage);
        this.mAdapter = new HotSondAdapter(getActivity(), this.mViewContentList, "AdSeatPage_" + this.mHref);
        this.mListContent.setAdapter((ListAdapter) this.mAdapter);
        this.mListContent.setCacheColorHint(0);
        this.mListContent.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.cmmusic.fragment.AdSeatContentFragment.3
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (!AdSeatContentFragment.this.mIsNull && i == 0 && absListView.getLastVisiblePosition() == absListView.getCount() - 1) {
                    if (AdSeatContentFragment.this.mLoadWebDataThread == null || AdSeatContentFragment.this.mLoadWebDataThread.isAlive()) {
                        AdSeatContentFragment.this.mLoadWebDataThread = new Thread() { // from class: com.sds.android.ttpod.cmmusic.fragment.AdSeatContentFragment.3.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                if (AdSeatContentFragment.this.mHandler != null) {
                                    if (AdSeatContentFragment.this.mListContent.getCount() > 0) {
                                        AdSeatContentFragment.this.onScrollAddData(AdSeatContentFragment.this.mListContent.getCount());
                                        AdSeatContentFragment.this.mIsNull = true;
                                    }
                                    Message message = new Message();
                                    message.what = 1;
                                    AdSeatContentFragment.this.mHandler.sendMessage(message);
                                }
                            }
                        };
                        AdSeatContentFragment.this.mLoadWebDataThread.start();
                        AdSeatContentFragment.this.mLoadWebDataThread = null;
                    }
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        });
    }
}
