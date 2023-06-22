package com.sds.android.ttpod.activities.cmmusic;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;

/* loaded from: classes.dex */
public class WebViewActivity extends SlidingClosableActivity {
    private String mHref;
    private WebView mWebView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cmmusic_webview_activity);
        setTitle(R.string.cailing);
        initActionBar();
        getIntentData();
        viewInit();
    }

    private void initActionBar() {
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7179d();
        actionBarController.m7180c(true);
        actionBarController.m7178d(R.drawable.cmmusic_img_mine).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.cmmusic.WebViewActivity.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                CmmusicStatistic.m7304f(SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue());
                Intent intent = new Intent(WebViewActivity.this, ListenControlActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "PersionalListenControl");
                intent.putExtras(bundle);
                WebViewActivity.this.startActivity(intent);
                WebViewActivity.this.finish();
            }
        });
        actionBarController.m7178d(R.drawable.cmmusic_search).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.cmmusic.WebViewActivity.2
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                CmmusicStatistic.m7305e(SPage.PAGE_NONE.getValue(), SPage.PAGE_NONE.getValue());
                Intent intent = new Intent(WebViewActivity.this, ListenControlActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "SearchPage");
                intent.putExtras(bundle);
                WebViewActivity.this.startActivity(intent);
                WebViewActivity.this.finish();
            }
        });
    }

    private void viewInit() {
        this.mWebView = (WebView) findViewById(R.id.webview_webviewpage);
        this.mWebView.setWebChromeClient(new WebChromeClient());
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setLoadsImagesAutomatically(true);
        if (this.mWebView != null) {
            this.mWebView.setWebViewClient(new WebViewClient() { // from class: com.sds.android.ttpod.activities.cmmusic.WebViewActivity.3
                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView webView, String str) {
                }

                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    webView.loadUrl(str);
                    return true;
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                    sslErrorHandler.proceed();
                }
            });
            if (this.mHref.length() > 0) {
                this.mWebView.loadUrl(this.mHref);
            } else {
                finish();
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        }
        finish();
        return false;
    }

    private void getIntentData() {
        try {
            this.mHref = getIntent().getExtras().getString("href");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
