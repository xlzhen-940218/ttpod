package com.sds.android.ttpod.activities.cmmusic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.cmmusic.fragment.DjListenFragment;
import com.sds.android.ttpod.cmmusic.fragment.FunnyListenFragment;
import com.sds.android.ttpod.cmmusic.fragment.ListListenFragment;
import com.sds.android.ttpod.cmmusic.fragment.RecommendFragment;
import com.sds.android.ttpod.cmmusic.fragment.UnderlineIndicator;
import com.sds.android.ttpod.cmmusic.p077a.C1025e;
import com.sds.android.ttpod.cmmusic.p078b.CmMusicSdkInitCode;
import com.sds.android.ttpod.cmmusic.p079c.UserSelectListenQuery;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;
import com.sds.android.ttpod.cmmusic.p081e.CMMusicUtils;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.ArrayList;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class ListenContentActivity extends SlidingClosableActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final int TIME_DELAY_TO_INIT = 10000;
    private UnderlineIndicator mIndicator;
    private ViewPager mViewPager;
    private int mPageCode = 1;
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.activities.cmmusic.ListenContentActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    String str = (String) ((Hashtable) message.obj).get("code");
                    if ("0".equals(str)) {
                        ListenContentActivity.this.updateIMSI();
                        return;
                    } else if ("3".equals(str)) {
                        PopupsUtils.m6760a((int) R.string.cardisnotcmcc);
                        return;
                    } else if ("2".equals(str)) {
                        PopupsUtils.m6760a((int) R.string.pleasechecknetwork);
                        return;
                    } else if (CMMusicUtils.m7276a() && !"0".equals(str)) {
                        CmMusicSdkInitCode.m7343a(ListenContentActivity.this);
                        ListenContentActivity.this.updateIMSI();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cmmusic_sound_content_activity);
        setTitle(R.string.cailing);
        initActionBar();
        final String m2905bx = Preferences.m2905bx();
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.activities.cmmusic.ListenContentActivity.1
            @Override // java.lang.Runnable
            public void run() {
                /*if (!StringUtils.m8344a(m2905bx, EnvironmentUtils.C0604c.m8481b())) {
                    Hashtable<String, String> m10389a = InitCmmInterface.m10389a(ListenContentActivity.this.getApplicationContext());
                    if (!ListenContentActivity.this.isFinishing()) {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = m10389a;
                        ListenContentActivity.this.mHandler.sendMessage(message);
                    }
                }*/
            }
        });
        this.mIndicator = (UnderlineIndicator) findViewById(R.id.indicator);
        this.mIndicator.setPageCount(4);
        this.mIndicator.setCurrentItem(0);
        this.mIndicator.setSelectedColor(Color.parseColor("#9CDCFF"));
        this.mViewPager = (ViewPager) findViewById(R.id.viewPager);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ArrayList arrayList = new ArrayList();
        RecommendFragment recommendFragment = new RecommendFragment();
        ListListenFragment listListenFragment = new ListListenFragment();
        FunnyListenFragment funnyListenFragment = new FunnyListenFragment();
        DjListenFragment djListenFragment = new DjListenFragment();
        arrayList.add(recommendFragment);
        arrayList.add(listListenFragment);
        arrayList.add(funnyListenFragment);
        arrayList.add(djListenFragment);
        this.mViewPager.setAdapter(new C1025e(supportFragmentManager, arrayList));
        this.mViewPager.setCurrentItem(0);
        this.mViewPager.setOffscreenPageLimit(3);
        findViewById(R.id.recommend).setOnClickListener(this);
        findViewById(R.id.dj).setOnClickListener(this);
        findViewById(R.id.funny).setOnClickListener(this);
        findViewById(R.id.rank).setOnClickListener(this);
        this.mViewPager.setOnPageChangeListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIMSI() {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.activities.cmmusic.ListenContentActivity.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(10000L);
                    if (UserSelectListenQuery.m7315b()) {
                        Preferences.m2820v(EnvironmentUtils.DeviceConfig.getSubscriberId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initActionBar() {
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7179d();
        actionBarController.m7180c(true);
        actionBarController.m7178d(R.drawable.cmmusic_img_mine).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.cmmusic.ListenContentActivity.3
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                CmmusicStatistic.m7304f(ListenContentActivity.this.mPageCode, 5);
                Intent intent = new Intent(ListenContentActivity.this, ListenControlActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "PersionalListenControl");
                intent.putExtras(bundle);
                ListenContentActivity.this.startActivity(intent);
            }
        });
        actionBarController.m7178d(R.drawable.cmmusic_search).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.cmmusic.ListenContentActivity.4
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                CmmusicStatistic.m7305e(ListenContentActivity.this.mPageCode, 6);
                Intent intent = new Intent(ListenContentActivity.this, ListenControlActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "SearchPage");
                intent.putExtras(bundle);
                ListenContentActivity.this.startActivity(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.recommend == id) {
            this.mIndicator.setCurrentItem(0);
            this.mViewPager.setCurrentItem(0);
        } else if (R.id.dj == id) {
            this.mIndicator.setCurrentItem(1);
            this.mViewPager.setCurrentItem(1);
        } else if (R.id.funny == id) {
            this.mIndicator.setCurrentItem(2);
            this.mViewPager.setCurrentItem(2);
        } else if (R.id.rank == id) {
            this.mIndicator.setCurrentItem(3);
            this.mViewPager.setCurrentItem(3);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                CmmusicStatistic.m7314a(this.mPageCode, 1);
                break;
            case 1:
                CmmusicStatistic.m7311b(this.mPageCode, 2);
                break;
            case 2:
                CmmusicStatistic.m7308c(this.mPageCode, 3);
                break;
            case 3:
                CmmusicStatistic.m7306d(this.mPageCode, 4);
                break;
        }
        this.mIndicator.setCurrentItem(i);
        this.mViewPager.setCurrentItem(i);
        this.mPageCode = i + 1;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }
}
