package com.sds.android.ttpod.share.p138c;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.share.p137b.AuthCallback;
import com.sds.android.ttpod.share.p139d.UrlUtil;


/* renamed from: com.sds.android.ttpod.share.c.a */
/* loaded from: classes.dex */
public class AuthDialog extends Dialog {

    /* renamed from: a */
    private WebView webView;

    /* renamed from: b */
    private ProgressDialog progressDialog;

    /* renamed from: c */
    private String url;

    /* renamed from: d */
    private AuthCallback authCallback;

    /* renamed from: e */
    private Activity activity;

    @SuppressLint("ResourceType")
    public AuthDialog(Context context, String str, AuthCallback authCallback) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.activity = (Activity) context;
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("authUrl can not be null.");
        }
        this.url = str;
        this.authCallback = authCallback;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initProgressDialog();
        requestWindowFeature(1);
        this.webView = new WebView(getContext());
        initWebView();
    }

    /* renamed from: a */
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new AuthWebViewClient());
        this.webView.setWebChromeClient(new AuthWebChromeClient());
        this.webView.loadUrl(this.url);
        this.webView.setVisibility(View.INVISIBLE);
        addContentView(this.webView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }

    /* renamed from: b */
    private void initProgressDialog() {
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(getContext());
            this.progressDialog.requestWindowFeature(1);
            this.progressDialog.setTitle(getContext().getString(R.string.share_waiting_dialog_title));
            this.progressDialog.setMessage(getContext().getString(R.string.share_waiting_dialog_message));
            this.progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sds.android.ttpod.share.c.a.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getRepeatCount() == 0) {
                        AuthDialog.this.onBackPressed();
                        return true;
                    }
                    return true;
                }
            });
        }
        if (!this.progressDialog.isShowing()) {
            this.progressDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AuthDialog.java */
    /* renamed from: com.sds.android.ttpod.share.c.a$b */
    /* loaded from: classes.dex */
    public class AuthWebViewClient extends WebViewClient {
        private AuthWebViewClient() {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(request.getUrl().getScheme().startsWith("wtloginmqq")){
                    Intent intent = new Intent(Intent.ACTION_VIEW,request.getUrl());
                    activity.startActivity(intent);
                }
                view.loadUrl(request.getUrl().toString());
            }

            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
        }

        /* renamed from: a */
        private boolean accessToken(String str, String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                try {
                    if (System.currentTimeMillis() < System.currentTimeMillis() + (Long.parseLong(str2) * 1000)) {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Bundle bundle = UrlUtil.urlToBundle(str);
            if (bundle.size() > 0 && accessToken(bundle.getString("access_token")
                    , bundle.getString("expires_in"))) {
                if (AuthDialog.this.authCallback != null) {
                    AuthDialog.this.authCallback.onSuccess(bundle);
                }
                webView.stopLoading();
                AuthDialog.this.dismiss();
                return;
            }
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AuthDialog.java */
    /* renamed from: com.sds.android.ttpod.share.c.a$a */
    /* loaded from: classes.dex */
    public class AuthWebChromeClient extends WebChromeClient {
        private AuthWebChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (i == 100) {
                if (AuthDialog.this.progressDialog.isShowing() && AuthDialog.this.activity != null && !AuthDialog.this.activity.isFinishing()) {
                    AuthDialog.this.progressDialog.dismiss();
                }
                AuthDialog.this.webView.setVisibility(View.VISIBLE);
            }
        }
    }
}
