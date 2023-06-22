package com.sds.android.ttpod.activities.unicomflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

/* loaded from: classes.dex */
public class UnsubscribeDetailActivity extends UnicomFlowActivity implements View.OnClickListener {
    private Button mButtonOpen;
    private TextView mTextViewAttention;
    private TextView mTextViewBeginTime;
    private TextView mTextViewEndTime;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unicom_flow_unsubscribe_detail);
        setTitle("天天动听-包流量畅听");
        this.mTextViewEndTime = (TextView) findViewById(R.id.text_end_time);
        this.mTextViewBeginTime = (TextView) findViewById(R.id.text_begin_time);
        this.mTextViewAttention = (TextView) findViewById(R.id.textview_attention);
        this.mButtonOpen = (Button) findViewById(R.id.button_open);
        this.mButtonOpen.setOnClickListener(this);
        this.mTextViewBeginTime.setText(Cache.m3218a().m3237B());
        this.mTextViewEndTime.setText(Cache.m3218a().m3236C());
        this.mTextViewAttention.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mButtonOpen) {
            UnicomFlowStatistic.m4804w();
            new SUserEvent("PAGE_CLICK", 1115, 704).post();
            startOpenActivity(UnicomFlowStatistic.EnumC1778b.UNSUBSCRIBE_DETAIL, false);
        } else if (view == this.mTextViewAttention) {
            UnicomFlowStatistic.m4803x();
            startActivity(new Intent(this, AttentionActivity.class));
        }
    }
}
