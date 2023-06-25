package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p077a.HotSondAdapter;
import com.sds.android.ttpod.cmmusic.p077a.SearchRecommendAdapter;
import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import com.sds.android.ttpod.cmmusic.p079c.MusicSerchQuery;
import com.sds.android.ttpod.cmmusic.p079c.SearchRecommendKeyQuery;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class SearchListenFragment extends BaseFragment implements View.OnClickListener {
    private HotSondAdapter mAdapterbinding;
    private View mHeadViewRecommendList;
    private ListView mListView;
    private ListView mListViewSearch;
    private SearchRecommendAdapter mRecommendSearchKeyAdapter;
    private EditText mSearchName;
    private Thread mWebDataThread;
    private String mSearchKey = null;
    private boolean mIsNull = false;
    private ArrayList<HashMap<String, String>> mViewContentList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> mItemInfoListTemp = new ArrayList<>();
    private ArrayList<HashMap<String, String>> mSearchKeyArray = new ArrayList<>();
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.7
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (SearchListenFragment.this.isViewAccessAble()) {
                switch (message.what) {
                    case 1:
                        ArrayList arrayList = (ArrayList) message.obj;
                        if (arrayList != null) {
                            SearchListenFragment.this.mSearchKeyArray.addAll(arrayList);
                            SearchListenFragment.this.mRecommendSearchKeyAdapter.notifyDataSetChanged();
                            arrayList.clear();
                            return;
                        }
                        return;
                    case 2:
                        SearchListenFragment.this.mSearchKeyArray.removeAll(SearchListenFragment.this.mSearchKeyArray);
                        ArrayList arrayList2 = (ArrayList) message.obj;
                        SearchListenFragment.this.mSearchKeyArray.addAll(arrayList2);
                        SearchListenFragment.this.mRecommendSearchKeyAdapter.notifyDataSetChanged();
                        SearchListenFragment.this.mListViewSearch.setAdapter((ListAdapter) SearchListenFragment.this.mRecommendSearchKeyAdapter);
                        SearchListenFragment.this.mListViewSearch.setVisibility(View.VISIBLE);
                        arrayList2.clear();
                        return;
                    case 3:
                        Toast.makeText(SearchListenFragment.this.getActivity(), SearchListenFragment.this.getResources().getString(R.string.searcherror), Toast.LENGTH_SHORT).show();
                        return;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    default:
                        return;
                    case 10:
                        if (SearchListenFragment.this.mViewContentList != null) {
                            SearchListenFragment.this.mViewContentList.addAll(SearchListenFragment.this.mItemInfoListTemp);
                            SearchListenFragment.this.mAdapterbinding.notifyDataSetChanged();
                            SearchListenFragment.this.mIsNull = false;
                            SearchListenFragment.this.mItemInfoListTemp.clear();
                            return;
                        }
                        return;
                    case 11:
                        SearchListenFragment.this.mViewContentList.addAll(SearchListenFragment.this.mItemInfoListTemp);
                        SearchListenFragment.this.mListView.setVisibility(View.VISIBLE);
                        SearchListenFragment.this.mAdapterbinding.notifyDataSetChanged();
                        SearchListenFragment.this.mItemInfoListTemp.clear();
                        SearchListenFragment.this.mIsNull = false;
                        return;
                }
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
        View inflate = layoutInflater.inflate(R.layout.cmmusic_search_listen_activity, viewGroup, false);
        recomedKey();
        listViewInit(inflate);
        return inflate;
    }

    private void listViewInit(View view) {
        view.findViewById(R.id.btn_submit_searchpage_submit).setOnClickListener(this);
        this.mSearchName = (EditText) view.findViewById(R.id.edit_searchkey_searchpage);
        this.mListView = (ListView) view.findViewById(R.id.list_searchpage);
        this.mHeadViewRecommendList = LayoutInflater.from(getActivity()).inflate(R.layout.cmmusic_recommend_search_key_list_head, (ViewGroup) null);
        this.mListViewSearch = (ListView) view.findViewById(R.id.list_recommend_searchpage);
        this.mListViewSearch.addHeaderView(this.mHeadViewRecommendList, null, false);
        this.mRecommendSearchKeyAdapter = new SearchRecommendAdapter(this.mSearchKeyArray, getActivity());
        this.mListViewSearch.setAdapter((ListAdapter) this.mRecommendSearchKeyAdapter);
        this.mListViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                SearchListenFragment.this.mSearchName.setText((CharSequence) ((HashMap) SearchListenFragment.this.mRecommendSearchKeyAdapter.getItem(i - 1)).get("recommendKey"));
            }
        });
        this.mSearchName.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                SearchListenFragment.this.mListViewSearch.setVisibility(View.VISIBLE);
                SearchListenFragment.this.mListView.setVisibility(View.GONE);
                return false;
            }
        });
        this.mSearchName.addTextChangedListener(new TextWatcher() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.3
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SearchListenFragment.this.mListView.setVisibility(View.GONE);
                SearchListenFragment.this.mSearchKeyArray.removeAll(SearchListenFragment.this.mSearchKeyArray);
                TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String replace = SearchListenFragment.this.mSearchName.getText().toString().replace(" ", "");
                        if (replace != null) {
                            if (replace.equals("")) {
                                SearchListenFragment.this.mSearchKeyArray.removeAll(SearchListenFragment.this.mSearchKeyArray);
                                SearchListenFragment.this.recomedKey();
                                return;
                            }
                            ArrayList<HashMap<String, String>> m7318a = SearchRecommendKeyQuery.m7318a(replace);
                            if (m7318a != null && SearchListenFragment.this.mHandler != null) {
                                Message message = new Message();
                                message.obj = m7318a;
                                message.what = 2;
                                SearchListenFragment.this.mHandler.sendMessage(message);
                            }
                        }
                    }
                });
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SearchListenFragment.this.mListView.setVisibility(View.GONE);
                SearchListenFragment.this.mViewContentList.removeAll(SearchListenFragment.this.mViewContentList);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                SearchListenFragment.this.mSearchKeyArray.removeAll(SearchListenFragment.this.mSearchKeyArray);
                SearchListenFragment.this.mListView.setVisibility(View.GONE);
                SearchListenFragment.this.mListViewSearch.setVisibility(View.GONE);
            }
        });
        this.mAdapterbinding = new HotSondAdapter(getActivity(), this.mViewContentList, "SearchPage");
        this.mListView.setAdapter((ListAdapter) this.mAdapterbinding);
        this.mListView.setCacheColorHint(0);
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.4
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (!SearchListenFragment.this.mIsNull && i == 0 && absListView.getLastVisiblePosition() == absListView.getCount() - 1) {
                    if (SearchListenFragment.this.mWebDataThread == null || SearchListenFragment.this.mWebDataThread.isAlive()) {
                        SearchListenFragment.this.mWebDataThread = new Thread() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.4.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                if (SearchListenFragment.this.isViewAccessAble()) {
                                    if (SearchListenFragment.this.mListView.getCount() > 0) {
                                        SearchListenFragment.this.mIsNull = true;
                                        SearchListenFragment.this.onScrollAddData(SearchListenFragment.this.mSearchKey, Integer.valueOf(SearchListenFragment.this.mListView.getCount()));
                                    }
                                    Message message = new Message();
                                    message.what = 1;
                                    SearchListenFragment.this.mHandler.sendMessage(message);
                                }
                            }
                        };
                        SearchListenFragment.this.mWebDataThread.start();
                        SearchListenFragment.this.mWebDataThread = null;
                    }
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        });
        this.mListViewSearch.setVisibility(View.VISIBLE);
        this.mListView.setVisibility(View.GONE);
    }

    private void searchSubmit() {
        this.mListViewSearch.setVisibility(View.GONE);
        this.mViewContentList.clear();
        this.mAdapterbinding.notifyDataSetChanged();
        try {
            TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    if (SearchListenFragment.this.mSearchName.getText().toString().replace(" ", "").equals("")) {
                        Message message = new Message();
                        message.what = 3;
                        SearchListenFragment.this.mHandler.sendMessage(message);
                        return;
                    }
                    SearchListenFragment.this.mSearchKey = SearchListenFragment.this.mSearchName.getText().toString().replace(" ", "");
                    CmmusicStatistic.m7310b(SearchListenFragment.this.mSearchKey);
                    List<SondContentInfo> m7321a = MusicSerchQuery.m7321a(SearchListenFragment.this.mSearchKey, EnvironmentUtils.C0604c.getDeviceId(), EnvironmentUtils.C0604c.getSubscriberId());
                    if (SearchListenFragment.this.isViewAccessAble() && m7321a != null && m7321a.size() > 0) {
                        for (SondContentInfo sondContentInfo : m7321a) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("resource_name", sondContentInfo.m7342a());
                            hashMap.put("resource_songer", sondContentInfo.m7341b());
                            hashMap.put("music_id", sondContentInfo.m7338e());
                            hashMap.put("cailing_id", sondContentInfo.m7340c());
                            hashMap.put("zhenling_id", sondContentInfo.m7339d());
                            hashMap.put("time_out", sondContentInfo.m7337f());
                            SearchListenFragment.this.mItemInfoListTemp.add(hashMap);
                        }
                        m7321a.clear();
                        Message message2 = new Message();
                        message2.what = 11;
                        SearchListenFragment.this.mHandler.sendMessage(message2);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScrollAddData(String str, Integer num) {
        try {
            List<SondContentInfo> m7320a = MusicSerchQuery.m7320a(str, EnvironmentUtils.C0604c.getDeviceId(), EnvironmentUtils.C0604c.getSubscriberId(), String.valueOf(num));
            if (isViewAccessAble() && m7320a != null && m7320a.size() > 0) {
                for (SondContentInfo sondContentInfo : m7320a) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("resource_name", sondContentInfo.m7342a());
                    hashMap.put("resource_songer", sondContentInfo.m7341b());
                    hashMap.put("music_id", sondContentInfo.m7338e());
                    hashMap.put("cailing_id", sondContentInfo.m7340c());
                    hashMap.put("zhenling_id", sondContentInfo.m7339d());
                    hashMap.put("time_out", sondContentInfo.m7337f());
                    this.mItemInfoListTemp.add(hashMap);
                }
                Message message = new Message();
                message.what = 10;
                this.mHandler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recomedKey() {
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment.6
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<HashMap<String, String>> m7319a = SearchRecommendKeyQuery.m7319a();
                if (m7319a != null && SearchListenFragment.this.isViewAccessAble()) {
                    Message message = new Message();
                    message.obj = m7319a;
                    message.what = 1;
                    SearchListenFragment.this.mHandler.sendMessage(message);
                }
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.btn_submit_searchpage_submit == view.getId()) {
            try {
                searchSubmit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
