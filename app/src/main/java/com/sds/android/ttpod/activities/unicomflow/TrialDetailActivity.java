package com.sds.android.ttpod.activities.unicomflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

/* loaded from: classes.dex */
public class TrialDetailActivity extends UnicomFlowActivity implements View.OnClickListener {
    private Button mButtonFlowOpen;
    private TextView mTextViewFaq;
    private TextView mTextViewFlowDescribe;
    private TextView mTextViewFlowPhone;
    private TextView mTextViewServerDetail;
    private UnicomFlowStatistic.EnumC1779c mTrialOrigin = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unicom_flow_trial_detail);
        setTitle("天天动听-包流量畅听");
        this.mButtonFlowOpen = (Button) findViewById(R.id.button_open);
        this.mTextViewFlowDescribe = (TextView) findViewById(R.id.textview_flow_describe);
        this.mTextViewFlowPhone = (TextView) findViewById(R.id.unicom_trial_detail_phone);
        this.mTextViewFaq = (TextView) findViewById(R.id.textview_flow_faq);
        this.mTextViewServerDetail = (TextView) findViewById(R.id.textview_server_detail);
        this.mButtonFlowOpen.setOnClickListener(this);
        this.mTextViewServerDetail.setOnClickListener(this);
        this.mTrialOrigin = (UnicomFlowStatistic.EnumC1779c) UnicomFlowManager.m7761a(this, UnicomFlowStatistic.EnumC1779c.ORDER_DETAIL);
        UnicomFlowStatistic.m4826e(this.mTrialOrigin);
        bindView();
    }

    private void bindView() {
        this.mTextViewFlowPhone.setText(getResources().getString(R.string.unicom_trial_detail_phone, UnicomFlowUtil.m3940l()));
        this.mTextViewFaq.setText(Cache.m3218a().m3163g(getString(R.string.unicom_matters_attention)));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mButtonFlowOpen) {
            if (UnicomFlowUtil.m3942j() && !Cache.m3218a().m3235D() && Preferences.m2915bn()) {
                UnicomFlowManager.m7756a((UnicomFlowActivity) this, UnicomFlowStatistic.EnumC1777a.ORDER_DETAIL);
                Preferences.m2969af(false);
                return;
            }
            UnicomFlowStatistic.m4823f(this.mTrialOrigin);
            startOpenActivity(UnicomFlowStatistic.EnumC1778b.TRIAL_DETAIL, true);
        } else if (view == this.mTextViewServerDetail) {
            UnicomFlowStatistic.m4820g(this.mTrialOrigin);
            startActivity(new Intent(this, FaqActivity.class));
        }
    }
}
