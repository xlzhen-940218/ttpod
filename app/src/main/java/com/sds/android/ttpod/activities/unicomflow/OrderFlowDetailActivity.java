package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.p126h.FlowTrialStatus;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

/* loaded from: classes.dex */
public class OrderFlowDetailActivity extends UnicomFlowActivity implements View.OnClickListener {
    private Button mButtonOpen;
    private Button mButtonTrial;
    private TextView mTextViewFaq;
    private TextView mTextViewServerDetail;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unicom_flow_order_detail);
        setTitle("天天动听-包流量畅听");
        this.mTextViewFaq = (TextView) findViewById(R.id.textview_flow_faq);
        this.mTextViewServerDetail = (TextView) findViewById(R.id.textview_server_detail);
        this.mButtonTrial = (Button) findViewById(R.id.button_trial);
        this.mButtonOpen = (Button) findViewById(R.id.button_open);
        bindView();
        UnicomFlowStatistic.m4810q();
    }

    private void bindView() {
        this.mTextViewFaq.setText(Cache.m3218a().m3163g(getString(R.string.unicom_matters_attention)));
        this.mButtonOpen.setOnClickListener(this);
        this.mButtonTrial.setOnClickListener(this);
        this.mTextViewServerDetail.setOnClickListener(this);
        this.mButtonTrial.setVisibility((Cache.m3218a().m3229J() && Cache.m3218a().m3139y() == FlowTrialStatus.UN_TRIAL.ordinal()) ? View.VISIBLE : View.GONE);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mButtonOpen) {
            startOpenActivity(UnicomFlowStatistic.EnumC1778b.ORDER_DETAIL, false);
        } else if (view == this.mButtonTrial) {
            UnicomFlowStatistic.m4813n();
            UnicomFlowManager.m7762a((Activity) this, TrialActivity.class, (Enum) UnicomFlowStatistic.EnumC1779c.ORDER_DETAIL, false);
        } else if (view == this.mTextViewServerDetail) {
            UnicomFlowStatistic.m4811p();
            new SUserEvent("PAGE_CLICK", 1102, 700, 714).post();
            startActivity(new Intent(this, FaqActivity.class));
        }
    }
}
