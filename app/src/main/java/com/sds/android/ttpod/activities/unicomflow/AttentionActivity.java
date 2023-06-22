package com.sds.android.ttpod.activities.unicomflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;

/* loaded from: classes.dex */
public class AttentionActivity extends SlidingClosableActivity implements View.OnClickListener {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("注意事项");
        setContentView(R.layout.activity_unicom_flow_attention_message);
        ((TextView) findViewById(R.id.textview_attention)).setOnClickListener(this);
        UnicomFlowStatistic.m4848P();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        SUserUtils.m4954a("PAGE_CLICK", 1111, 706, 714);
        startActivity(new Intent(this, FaqActivity.class));
    }
}
