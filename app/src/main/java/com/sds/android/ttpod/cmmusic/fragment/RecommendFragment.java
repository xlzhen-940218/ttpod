package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p077a.HotSondAdapter;
import com.sds.android.ttpod.cmmusic.p077a.ImageAdapter;
import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import com.sds.android.ttpod.cmmusic.p079c.AdSeatInfoQuery;
import com.sds.android.ttpod.cmmusic.p079c.HotActivityContentJson;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;
import com.sds.android.ttpod.cmmusic.p081e.SondContentArrayPut;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class RecommendFragment extends BaseMusicFragment implements View.OnClickListener {
    private static final Integer PANDING = 20;
    private static final int TIMEOUT = 5;
    private HotSondAdapter mAdapter;
    private View mHeadView;
    private ImageAdapter mImageAdapter;
    private ImageView[] mIndicatorImgs;
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String, String>> mItemInfoListTemp;
    private LinearLayout mLayout;
    private ListView mListView;
    private View mRootView;
    private ScheduledExecutorService mScheduledExecutorService;
    private ViewPager mViewPager;
    private ArrayList<HashMap<String, String>> mViewContentList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> mAdSeatContentInfos = new ArrayList<>();
    private int mCurrentItem = 0;
    private Handler mHandlers = new Handler() { // from class: com.sds.android.ttpod.cmmusic.fragment.RecommendFragment.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (RecommendFragment.this.isViewAccessAble()) {
                switch (message.what) {
                    case 4:
                        RecommendFragment.this.mAdSeatContentInfos = (ArrayList) message.obj;
                        if (RecommendFragment.this.mAdSeatContentInfos != null && RecommendFragment.this.mAdSeatContentInfos.size() > 0) {
                            RecommendFragment.this.mIndicatorImgs = new ImageView[RecommendFragment.this.mAdSeatContentInfos.size()];
                            RecommendFragment.this.titleViewContentBind(RecommendFragment.this.mRootView);
                            return;
                        }
                        return;
                    case 5:
                        RecommendFragment.this.mViewPager.setCurrentItem(RecommendFragment.this.mCurrentItem);
                        return;
                    default:
                        return;
                }
            }
        }
    };

    @Override // com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mHandlers.removeCallbacksAndMessages(null);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.cmmusic_recommend_activity, viewGroup, false);
        mViewInit();
        if (EnvironmentUtils.C0604c.m8474e()) {
            this.mListView.setVisibility(View.VISIBLE);
            getDBViewContent();
        } else {
            this.mLayout.setVisibility(View.VISIBLE);
        }
        return this.mRootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        this.mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.mScheduledExecutorService.scheduleAtFixedRate(new RunnableC1046b(), 5L, 5L, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        this.mScheduledExecutorService.shutdown();
        super.onStop();
    }

    /* renamed from: com.sds.android.ttpod.cmmusic.fragment.RecommendFragment$b */
    /* loaded from: classes.dex */
    private class RunnableC1046b implements Runnable {
        private RunnableC1046b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RecommendFragment.this.isViewAccessAble()) {
                RecommendFragment.this.mCurrentItem = (RecommendFragment.this.mCurrentItem + 1) % RecommendFragment.this.mIndicatorImgs.length;
                Message message = new Message();
                message.what = 5;
                RecommendFragment.this.mHandlers.sendMessage(message);
            }
        }
    }

    private void getDBViewContent() {
        try {
            getAdseatInfo();
            new Thread(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.RecommendFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        RecommendFragment.this.mItemInfoListTemp = new ArrayList();
                        List<SondContentInfo> m7326a = HotActivityContentJson.m7326a("tag_1");
                        if (RecommendFragment.this.isViewAccessAble()) {
                            RecommendFragment.this.mItemInfoListTemp.addAll(SondContentArrayPut.m7272a(m7326a));
                            RecommendFragment.this.refresh();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAdseatInfo() {
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.RecommendFragment.2
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<HashMap<String, String>> m7336a = AdSeatInfoQuery.m7336a();
                if (RecommendFragment.this.isViewAccessAble()) {
                    Message message = new Message();
                    message.what = 4;
                    message.obj = m7336a;
                    RecommendFragment.this.mHandlers.sendMessage(message);
                }
            }
        });
    }

    @Override // com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment
    protected void onScrollAddData(int i) {
        try {
            this.mItemInfoListTemp = new ArrayList<>();
            List<SondContentInfo> m7325a = HotActivityContentJson.m7325a("tag_1", Integer.valueOf(i - 1));
            if (isViewAccessAble()) {
                this.mItemInfoListTemp.addAll(SondContentArrayPut.m7272a(m7325a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mViewInit() {
        CmmusicStatistic.m7314a(1, 1);
        this.mLayout = (LinearLayout) this.mRootView.findViewById(R.id.layout_recommpage);
        this.mRootView.findViewById(R.id.btn_tryagain_recommpage).setOnClickListener(this);
        this.mHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.cmmusic_recommend_activity_imagebanner_head, (ViewGroup) null);
        this.mListView = (ListView) this.mRootView.findViewById(R.id.Recommend_Activity_List);
        this.mAdapter = new HotSondAdapter(getActivity(), this.mViewContentList, "RecommendListenPage");
        this.mListView.addHeaderView(this.mHeadView);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        setListView(this.mListView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void titleViewContentBind(View view) {
        try {
            this.mViewPager = (ViewPager) view.findViewById(R.id.view_pager_recommendAcitvity);
            ArrayList arrayList = new ArrayList();
            this.mInflater = LayoutInflater.from(getActivity());
            for (int i = 0; i < this.mIndicatorImgs.length; i++) {
                arrayList.add(this.mInflater.inflate(R.layout.cmmusic_recommend_activity_imagebanner_item, (ViewGroup) null));
            }
            this.mImageAdapter = new ImageAdapter(this.mAdSeatContentInfos, arrayList, getActivity());
            this.mViewPager.setAdapter(this.mImageAdapter);
            this.mViewPager.setOnPageChangeListener(new C1045a());
            initIndicator(view);
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

    private void initIndicator(View view) {
        View findViewById = view.findViewById(R.id.indicator_recommendAcitvity);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.mIndicatorImgs.length) {
                ImageView imageView = new ImageView(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PANDING.intValue(), PANDING.intValue());
                layoutParams.setMargins(3, 2, 3, 2);
                imageView.setLayoutParams(layoutParams);
                this.mIndicatorImgs[i2] = imageView;
                if (i2 == 0) {
                    this.mIndicatorImgs[i2].setBackgroundResource(R.drawable.cmmusic_indicator_focused);
                } else {
                    this.mIndicatorImgs[i2].setBackgroundResource(R.drawable.cmmusic_indicator);
                }
                ((ViewGroup) findViewById).addView(this.mIndicatorImgs[i2]);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.cmmusic.fragment.RecommendFragment$a */
    /* loaded from: classes.dex */
    public class C1045a implements ViewPager.OnPageChangeListener {
        private C1045a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            for (int i2 = 0; i2 < RecommendFragment.this.mIndicatorImgs.length; i2++) {
                RecommendFragment.this.mIndicatorImgs[i2].setBackgroundResource(R.drawable.cmmusic_indicator);
            }
            RecommendFragment.this.mIndicatorImgs[i].setBackgroundResource(R.drawable.cmmusic_indicator_focused);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.btn_tryagain_recommpage == view.getId()) {
            if (EnvironmentUtils.C0604c.m8474e()) {
                sdkInitCheck();
                this.mListView.setVisibility(View.VISIBLE);
                this.mLayout.setVisibility(View.GONE);
                getDBViewContent();
                return;
            }
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.notNetworkPrompt), Toast.LENGTH_SHORT).show();
        }
    }
}
