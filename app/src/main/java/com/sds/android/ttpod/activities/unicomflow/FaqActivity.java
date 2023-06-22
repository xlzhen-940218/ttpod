package com.sds.android.ttpod.activities.unicomflow;

import android.os.Bundle;
import android.webkit.WebView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;

/* loaded from: classes.dex */
public class FaqActivity extends SlidingClosableActivity {
    private WebView mWebView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("常见问题");
        setContentView(R.layout.activity_unicom_flow_faq_detail);
        this.mWebView = (WebView) findViewById(R.id.webview_detail);
        this.mWebView.loadUrl("http://m.dongting.com/help/unicom.html");
        UnicomFlowStatistic.m4849O();
    }
}
