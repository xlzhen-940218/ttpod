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
public class ListListenFragment extends BaseMusicFragment implements View.OnClickListener {
    private HotSondAdapter mAdapter;
    private ArrayList<HashMap<String, String>> mItemInfoListTemp;
    private LinearLayout mLayout;
    private ListView mList;
    private View mRootView;
    private ArrayList<HashMap<String, String>> mViewContentList = new ArrayList<>();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.cmmusic_list_listen_activity, viewGroup, false);
        listViewInit();
        if (EnvironmentUtils.C0604c.m8474e()) {
            this.mList.setVisibility(View.VISIBLE);
            dbViewContent();
        } else {
            this.mLayout.setVisibility(View.VISIBLE);
        }
        return this.mRootView;
    }

    private void listViewInit() {
        this.mList = (ListView) this.mRootView.findViewById(R.id.list_listlistenpage);
        this.mLayout = (LinearLayout) this.mRootView.findViewById(R.id.layout_listlistenpage);
        this.mRootView.findViewById(R.id.btn_tryagain_listlistenpage).setOnClickListener(this);
        this.mAdapter = new HotSondAdapter(getActivity(), this.mViewContentList, "RankListenPage");
        this.mList.setAdapter((ListAdapter) this.mAdapter);
        setListView(this.mList);
    }

    private void dbViewContent() {
        try {
            TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.ListListenFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    ListListenFragment.this.mItemInfoListTemp = new ArrayList();
                    List<SondContentInfo> m7326a = HotActivityContentJson.m7326a("tag_2");
                    if (ListListenFragment.this.isViewAccessAble()) {
                        ListListenFragment.this.mItemInfoListTemp.addAll(SondContentArrayPut.m7272a(m7326a));
                        ListListenFragment.this.refresh();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment
    protected void onScrollAddData(int i) {
        try {
            this.mItemInfoListTemp = new ArrayList<>();
            List<SondContentInfo> m7325a = HotActivityContentJson.m7325a("tag_2", Integer.valueOf(i));
            if (isViewAccessAble()) {
                this.mItemInfoListTemp.addAll(SondContentArrayPut.m7272a(m7325a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment
    protected void onDataChange() {
        if (this.mItemInfoListTemp != null && this.mItemInfoListTemp.size() > 0) {
            this.mViewContentList.addAll(this.mItemInfoListTemp);
            this.mAdapter.notifyDataSetChanged();
            this.mItemInfoListTemp.clear();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.btn_tryagain_listlistenpage == view.getId()) {
            if (EnvironmentUtils.C0604c.m8474e()) {
                this.mList.setVisibility(View.VISIBLE);
                this.mLayout.setVisibility(View.GONE);
                sdkInitCheck();
                dbViewContent();
                return;
            }
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.notNetworkPrompt), Toast.LENGTH_SHORT).show();
        }
    }
}
