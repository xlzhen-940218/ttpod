package com.sds.android.ttpod.activities.cmmusic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.cmmusic.fragment.AdSeatContentFragment;
import com.sds.android.ttpod.cmmusic.fragment.PersonalListenControlFragment;
import com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;
import com.sds.android.ttpod.component.ActionBarController;

/* loaded from: classes.dex */
public class ListenControlActivity extends SlidingClosableActivity {
    private FragmentManager mFragmentManager;
    private int mPageCode;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cmmusic_listen_control_acitvity);
        setTitle(R.string.cailing);
        initActionBar();
        viewInit();
    }

    private void initActionBar() {
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7179d();
        actionBarController.m7180c(true);
        actionBarController.m7178d(R.drawable.cmmusic_img_mine).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.cmmusic.ListenControlActivity.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                CmmusicStatistic.m7304f(ListenControlActivity.this.mPageCode, 5);
                ListenControlActivity.this.mFragmentManager.beginTransaction().replace(R.id.layout_controlpage, new PersonalListenControlFragment()).commit();
                ListenControlActivity.this.mPageCode = 5;
            }
        });
        actionBarController.m7178d(R.drawable.cmmusic_search).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.cmmusic.ListenControlActivity.2
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                CmmusicStatistic.m7305e(ListenControlActivity.this.mPageCode, 6);
                ListenControlActivity.this.mFragmentManager.beginTransaction().replace(R.id.layout_controlpage, new SearchListenFragment()).commit();
                ListenControlActivity.this.mPageCode = 6;
            }
        });
    }

    private void viewInit() {
        this.mFragmentManager = getSupportFragmentManager();
        try {
            Bundle extras = getIntent().getExtras();
            String string = extras.getString("pagename");
            if (string.equals("SearchPage")) {
                this.mPageCode = 6;
                runFragment(new SearchListenFragment());
            } else if (string.equals("PersionalListenControl")) {
                this.mPageCode = 5;
                runFragment(new PersonalListenControlFragment());
            } else if (string.equals("AdSeatContent")) {
                this.mPageCode = 7;
                String string2 = extras.getString("href");
                AdSeatContentFragment adSeatContentFragment = new AdSeatContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("href", string2);
                adSeatContentFragment.setArguments(bundle);
                runFragment(adSeatContentFragment);
            } else {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runFragment(Fragment fragment) {
        this.mFragmentManager.beginTransaction().add(R.id.layout_controlpage, fragment).commit();
    }
}
