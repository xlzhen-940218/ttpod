package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.TTWebView;

/* renamed from: com.sds.android.ttpod.widget.h */
/* loaded from: classes.dex */
public class TTWebViewClient extends WebViewClient {

    /* renamed from: a */
    private String f8370a;

    /* renamed from: b */
    private Context f8371b;

    /* renamed from: c */
    private InterfaceC2289a f8372c;

    /* renamed from: d */
    private View f8373d;

    /* renamed from: e */
    private Button f8374e;

    /* renamed from: f */
    private long f8375f;

    /* renamed from: g */
    private long f8376g;

    /* renamed from: h */
    private boolean f8377h;

    /* renamed from: i */
    private Handler f8378i;

    /* compiled from: TTWebViewClient.java */
    /* renamed from: com.sds.android.ttpod.widget.h$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2289a {
        void onLoadResource(WebView webView, String str);

        boolean onOverrideUrlLoadingEvent(WebView webView, String str);

        void onPageFinishedEvent(WebView webView, String str);

        void onPageStartedEvent(WebView webView, String str, Bitmap bitmap);

        boolean onReceivedErrorEvent(WebView webView, int i, String str, String str2);

        void onReloadEvent();
    }

    public TTWebViewClient(Context context) {
        this.f8375f = 0L;
        this.f8376g = 0L;
        this.f8377h = true;
        this.f8378i = new Handler();
        this.f8371b = context;
    }

    public TTWebViewClient(Context context, InterfaceC2289a interfaceC2289a, View view) {
        this(context);
        this.f8372c = interfaceC2289a;
        this.f8373d = view;
        this.f8373d.setVisibility(View.INVISIBLE);
        this.f8374e = (Button) this.f8373d.findViewById(R.id.reload_button);
        this.f8374e.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.h.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (TTWebViewClient.this.f8372c != null) {
                    TTWebViewClient.this.f8372c.onReloadEvent();
                }
            }
        });
    }

    /* renamed from: a */
    public void m1236a() {
        if (this.f8374e != null) {
            this.f8374e.setOnClickListener(null);
        }
    }

    /* renamed from: a */
    public void m1235a(InterfaceC2289a interfaceC2289a) {
        this.f8372c = interfaceC2289a;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (EnvironmentUtils.C0604c.m8474e()) {
            if (this.f8370a == null || !this.f8370a.equals(str)) {
                this.f8370a = str;
                webView.getSettings().setBlockNetworkImage(true);
                if (this.f8372c != null) {
                    return this.f8372c.onOverrideUrlLoadingEvent(webView, str);
                }
                return false;
            }
            return true;
        }
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        if (this.f8372c != null) {
            this.f8372c.onLoadResource(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.f8372c != null) {
            this.f8372c.onPageStartedEvent(webView, str, bitmap);
        }
        ((TTWebView) webView).setState(TTWebView.EnumC2249a.WEBVIEW_PAGE_START);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        webView.getSettings().setBlockNetworkImage(false);
        this.f8370a = "";
        ((TTWebView) webView).m1449a();
        if (this.f8372c != null) {
            this.f8372c.onPageFinishedEvent(webView, str);
        }
        ((TTWebView) webView).setState(TTWebView.EnumC2249a.WEBVIEW_PAGE_FINISHED);
        this.f8376g = System.currentTimeMillis() - this.f8375f;
        if (this.f8376g > 1000) {
            if (this.f8373d.getVisibility() == View.VISIBLE) {
                webView.goBack();
            }
            this.f8378i.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.widget.h.2
                @Override // java.lang.Runnable
                public void run() {
                    TTWebViewClient.this.f8373d.setVisibility(View.GONE);
                }
            }, 1000L);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (this.f8372c != null) {
            this.f8377h = this.f8372c.onReceivedErrorEvent(webView, i, str, str2);
        }
        if (this.f8377h) {
            this.f8373d.setVisibility(View.VISIBLE);
            ((TTWebView) webView).setState(TTWebView.EnumC2249a.WEBVIEW_PAGE_ERROR);
            this.f8375f = System.currentTimeMillis();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        super.onReceivedLoginRequest(webView, str, str2, str3);
    }
}
