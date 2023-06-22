package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import com.sds.android.ttpod.framework.storage.p133a.Cache;

/* loaded from: classes.dex */
public class UnSubscribeGuideActivity extends SlidingClosableActivity {
    private UnicomFlowStatistic.EnumC1778b mOpenOrigin = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_unsubscribe_guide);
        setTitle(R.string.unicom_unsubscribe_title);
        this.mOpenOrigin = (UnicomFlowStatistic.EnumC1778b) UnicomFlowManager.m7761a(this, UnicomFlowStatistic.EnumC1778b.ORDER_DETAIL);
        bindViews();
        UnicomFlowStatistic.m4819h();
    }

    private void bindViews() {
        ((TextView) findViewById(R.id.text_number)).setText(UnicomFlowUtil.m3940l());
        ((TextView) findViewById(R.id.text_start_time)).setText(Cache.m3218a().m3237B());
    }

    public void unSubScribeOnClick(View view) {
        UnicomFlowStatistic.m4827e(this.mOpenOrigin);
        new SUserEvent("PAGE_CLICK", 1109, 704, 707).post();
        UnicomFlowManager.m7762a((Activity) this, UnsubscribeActivity.class, (Enum) null, false);
    }
}
