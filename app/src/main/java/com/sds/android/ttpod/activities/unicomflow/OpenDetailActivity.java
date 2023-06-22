package com.sds.android.ttpod.activities.unicomflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class OpenDetailActivity extends UnicomFlowActivity implements View.OnClickListener {
    private static final double FLOW_PRICE = 0.3d;
    private static final String TAG = OpenDetailActivity.class.getSimpleName();
    private Button mButtonShare;
    private UnicomFlowStatistic.EnumC1778b mOpenOrigin = null;
    private TextView mTextUnSubscribe;
    private TextView mTextViewAttention;
    private TextView mTextViewBeginTime;
    private TextView mTextViewFlow;
    private TextView mTextViewPrice;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unicom_flow_open_detail);
        setTitle("天天动听-包流量畅听");
        this.mButtonShare = (Button) findViewById(R.id.button_share);
        this.mTextViewFlow = (TextView) findViewById(R.id.textview_flow);
        this.mTextViewPrice = (TextView) findViewById(R.id.textview_price);
        this.mTextViewAttention = (TextView) findViewById(R.id.textview_attention);
        this.mTextViewBeginTime = (TextView) findViewById(R.id.textview_begin_time);
        this.mTextUnSubscribe = (TextView) findViewById(R.id.textview_unsubscribe);
        this.mTextViewBeginTime.setText("开启时间:" + Cache.m3218a().m3237B());
        this.mButtonShare.setOnClickListener(this);
        this.mTextViewAttention.setOnClickListener(this);
        this.mTextUnSubscribe.setOnClickListener(this);
        CommandCenter.m4607a().m4606a(new Command(CommandID.GET_UNICOM_TOTAL_FLOW, new Object[0]));
        this.mOpenOrigin = (UnicomFlowStatistic.EnumC1778b) UnicomFlowManager.m7761a(this, UnicomFlowStatistic.EnumC1778b.ORDER_DETAIL);
        LogUtils.m8388a(TAG, "unicom flow open detail isUse proxy:" + HttpRequest.m8704b());
        UnicomFlowStatistic.m4824f(this.mOpenOrigin);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.unicomflow.UnicomFlowActivity, com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.GET_UNICOM_TOTAL_FLOW_RESULT, ReflectUtils.m8375a(getClass(), "unicomTotalFlowResult", Long.class));
    }

    public void unicomTotalFlowResult(Long l) {
        double m3955a = UnicomFlowUtil.m3955a(l.longValue());
        double m3956a = UnicomFlowUtil.m3956a(FLOW_PRICE * m3955a);
        this.mTextViewFlow.setText(getString(R.string.unicom_open_flow_msg, new Object[]{String.valueOf(m3955a)}));
        this.mTextViewPrice.setText(getString(R.string.unicom_open_flow_price, new Object[]{String.valueOf(m3956a)}));
        CommandCenter.m4607a().m4606a(new Command(CommandID.SAVE_UNICOM_TOTAL_FLOW, new Object[0]));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mTextViewAttention) {
            UnicomFlowStatistic.m4821g(this.mOpenOrigin);
            new SUserEvent("PAGE_CLICK", 1110, 703, 706).post();
            startActivity(new Intent(this, AttentionActivity.class));
        } else if (view == this.mTextUnSubscribe) {
            UnicomFlowStatistic.m4818i();
            new SUserEvent("PAGE_CLICK", 1108, 703, 704).post();
            startActivity(new Intent(this, UnSubscribeGuideActivity.class));
        }
    }
}
