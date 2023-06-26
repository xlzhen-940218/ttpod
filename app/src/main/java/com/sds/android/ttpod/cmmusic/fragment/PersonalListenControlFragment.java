package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.sdk.lib.p065e.TaskScheduler;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p077a.PersionalListenControlAdapter;
import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import com.sds.android.ttpod.cmmusic.p079c.UserSelectListenQuery;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class PersonalListenControlFragment extends BaseFragment {
    private PersionalListenControlAdapter mAdapter;
    private LinearLayout mLayout;
    private ListView mListView;
    private ArrayList<HashMap<String, String>> mViewContentList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> mItemInfoListTemp = new ArrayList<>();
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.cmmusic.fragment.PersonalListenControlFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (PersonalListenControlFragment.this.mItemInfoListTemp != null && PersonalListenControlFragment.this.mItemInfoListTemp.size() > 0) {
                        PersonalListenControlFragment.this.mLayout.setVisibility(View.GONE);
                        PersonalListenControlFragment.this.mViewContentList.addAll(PersonalListenControlFragment.this.mItemInfoListTemp);
                        PersonalListenControlFragment.this.mItemInfoListTemp.clear();
                        PersonalListenControlFragment.this.mAdapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cmmusic_personal_listen_control_activity, viewGroup, false);
        linstwInit(inflate);
        initDBViewContent();
        return inflate;
    }

    private void initDBViewContent() {
        try {
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.PersonalListenControlFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    List<SondContentInfo> m7316a = UserSelectListenQuery.m7316a();
                    if (PersonalListenControlFragment.this.isViewAccessAble()) {
                        for (SondContentInfo sondContentInfo : m7316a) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("resource_name", sondContentInfo.m7342a());
                            hashMap.put("resource_songer", sondContentInfo.m7341b());
                            hashMap.put("cailing_id", sondContentInfo.m7340c());
                            hashMap.put("time_out", sondContentInfo.m7337f());
                            PersonalListenControlFragment.this.mItemInfoListTemp.add(hashMap);
                        }
                        if (PersonalListenControlFragment.this.mHandler != null) {
                            Message message = new Message();
                            message.what = 1;
                            PersonalListenControlFragment.this.mHandler.sendMessage(message);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void linstwInit(View view) {
        this.mLayout = (LinearLayout) view.findViewById(R.id.persional_listen_not_data);
        this.mListView = (ListView) view.findViewById(R.id.list_persionpage);
        this.mAdapter = new PersionalListenControlAdapter(getActivity(), this.mViewContentList);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
    }
}
