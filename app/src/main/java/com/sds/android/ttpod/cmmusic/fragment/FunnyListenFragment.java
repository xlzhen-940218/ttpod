package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p077a.HotSondAdapter;
import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import com.sds.android.ttpod.cmmusic.p079c.HotActivityContentJson;
import com.sds.android.ttpod.cmmusic.p081e.SondContentArrayPut;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class FunnyListenFragment extends BaseMusicFragment implements View.OnClickListener {
    private HotSondAdapter mAdapter;
    private ArrayList<HashMap<String, String>> mItemListTemp;
    private LinearLayout mLayout;
    private ListView mListView;
    private View mRootView;
    private ArrayList<HashMap<String, String>> mViewList = new ArrayList<>();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.cmmusic_funny_listen_activity, viewGroup, false);
        listViewInit();
        if (EnvironmentUtils.DeviceConfig.isConnected()) {
            this.mListView.setVisibility(View.VISIBLE);
            mViewContent();
        } else {
            this.mLayout.setVisibility(View.VISIBLE);
        }
        return this.mRootView;
    }

    private void listViewInit() {
        this.mListView = (ListView) this.mRootView.findViewById(R.id.list_funnylistenpage);
        this.mLayout = (LinearLayout) this.mRootView.findViewById(R.id.layout_funnypage);
        this.mRootView.findViewById(R.id.btn_tryagain_funnypage).setOnClickListener(this);
        this.mAdapter = new HotSondAdapter(getActivity(), this.mViewList, "FunnyListenPage");
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        setListView(this.mListView);
    }

    private void mViewContent() {
        try {
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.FunnyListenFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    FunnyListenFragment.this.mItemListTemp = new ArrayList();
                    List<SondContentInfo> m7326a = HotActivityContentJson.m7326a("tag_3");
                    if (FunnyListenFragment.this.isViewAccessAble()) {
                        FunnyListenFragment.this.mItemListTemp.addAll(SondContentArrayPut.m7272a(m7326a));
                        FunnyListenFragment.this.refresh();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment
    protected void onScrollAddData(int i) {
        this.mItemListTemp = new ArrayList<>();
        try {
            List<SondContentInfo> m7325a = HotActivityContentJson.m7325a("tag_3", Integer.valueOf(i));
            if (isViewAccessAble()) {
                this.mItemListTemp.addAll(SondContentArrayPut.m7272a(m7325a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment
    protected void onDataChange() {
        if (this.mItemListTemp != null && this.mItemListTemp.size() > 0) {
            this.mViewList.addAll(this.mItemListTemp);
            this.mAdapter.notifyDataSetChanged();
            this.mItemListTemp.clear();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.btn_tryagain_funnypage == view.getId()) {
            if (EnvironmentUtils.DeviceConfig.isConnected()) {
                sdkInitCheck();
                this.mListView.setVisibility(View.VISIBLE);
                this.mLayout.setVisibility(View.GONE);
                mViewContent();
                return;
            }
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.notNetworkPrompt), Toast.LENGTH_SHORT).show();
        }
    }
}
