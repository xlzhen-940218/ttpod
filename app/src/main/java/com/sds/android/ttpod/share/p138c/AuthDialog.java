package com.sds.android.ttpod.share.p138c;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
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
    private WebView f7372a;

    /* renamed from: b */
    private ProgressDialog f7373b;

    /* renamed from: c */
    private String f7374c;

    /* renamed from: d */
    private AuthCallback f7375d;

    /* renamed from: e */
    private Activity f7376e;

    @SuppressLint("ResourceType")
    public AuthDialog(Context context, String str, AuthCallback authCallback) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.f7376e = (Activity) context;
        if (StringUtils.m8346a(str)) {
            throw new IllegalArgumentException("authUrl can not be null.");
        }
        this.f7374c = str;
        this.f7375d = authCallback;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m2053b();
        requestWindowFeature(1);
        this.f7372a = new WebView(getContext());
        m2055a();
    }

    /* renamed from: a */
    private void m2055a() {
        this.f7372a.setVerticalScrollBarEnabled(false);
        this.f7372a.setHorizontalScrollBarEnabled(false);
        this.f7372a.getSettings().setJavaScriptEnabled(true);
        this.f7372a.setWebViewClient(new C2139b());
        this.f7372a.setWebChromeClient(new C2138a());
        this.f7372a.loadUrl(this.f7374c);
        this.f7372a.setVisibility(View.INVISIBLE);
        addContentView(this.f7372a, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        if (this.f7373b.isShowing()) {
            this.f7373b.dismiss();
        }
    }

    /* renamed from: b */
    private void m2053b() {
        if (this.f7373b == null) {
            this.f7373b = new ProgressDialog(getContext());
            this.f7373b.requestWindowFeature(1);
            this.f7373b.setTitle(getContext().getString(R.string.share_waiting_dialog_title));
            this.f7373b.setMessage(getContext().getString(R.string.share_waiting_dialog_message));
            this.f7373b.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sds.android.ttpod.share.c.a.1
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
        if (!this.f7373b.isShowing()) {
            this.f7373b.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AuthDialog.java */
    /* renamed from: com.sds.android.ttpod.share.c.a$b */
    /* loaded from: classes.dex */
    public class C2139b extends WebViewClient {
        private C2139b() {
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
        }

        /* renamed from: a */
        private boolean m2049a(String str, String str2) {
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
            Bundle m1926a = UrlUtil.m1926a(str);
            if (m1926a != null && m1926a.size() > 0 && m2049a(m1926a.getString("access_token"), m1926a.getString("expires_in"))) {
                if (AuthDialog.this.f7375d != null) {
                    AuthDialog.this.f7375d.mo1976a(m1926a);
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
    public class C2138a extends WebChromeClient {
        private C2138a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (i == 100) {
                if (AuthDialog.this.f7373b.isShowing() && AuthDialog.this.f7376e != null && !AuthDialog.this.f7376e.isFinishing()) {
                    AuthDialog.this.f7373b.dismiss();
                }
                AuthDialog.this.f7372a.setVisibility(View.VISIBLE);
            }
        }
    }
}
