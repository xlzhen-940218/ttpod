package com.sds.android.ttpod.widget;

import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.File;

/* loaded from: classes.dex */
public class TTWebView extends WebView {

    /* renamed from: a */
    private EnumC2249a f8054a;

    /* renamed from: b */
    private boolean f8055b;

    public TTWebView(Context context) {
        super(context);
        this.f8055b = false;
        m1448a(context);
    }

    public TTWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8055b = false;
        m1448a(context);
    }

    public TTWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8055b = false;
        m1448a(context);
    }

    public TTWebView(Context context, AttributeSet attributeSet, int i, boolean z) {
        super(context, attributeSet, i, z);
        this.f8055b = false;
        m1448a(context);
    }

    /* renamed from: a */
    private void m1448a(Context context) {
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        setVisibility(View.VISIBLE);
        requestFocus(130);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setBuiltInZoomControls(false);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(context.getApplicationInfo().dataDir + File.separatorChar + "databases");
        m1447a(settings);
    }

    /* renamed from: a */
    private void m1447a(WebSettings webSettings) {
        try {
            //webSettings.setAppCacheEnabled(true);
            //webSettings.setAppCacheMaxSize(1059576L);
            webSettings.setDomStorageEnabled(true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.webkit.WebView
    public void onPause() {
        m1449a();
        try {
            pauseTimers();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onPause();
    }

    @Override // android.webkit.WebView
    public void onResume() {
        try {
            resumeTimers();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onResume();
    }

    /* renamed from: a */
    public void m1449a() {
        CookieSyncManager.getInstance().startSync();
        CookieSyncManager.getInstance().sync();
    }

    public void setState(EnumC2249a enumC2249a) {
        this.f8054a = enumC2249a;
    }

    public EnumC2249a getState() {
        return this.f8054a;
    }

    /* renamed from: com.sds.android.ttpod.widget.TTWebView$a */
    /* loaded from: classes.dex */
    public enum EnumC2249a {
        WEBVIEW_INIT(0),
        WEBVIEW_PAGE_START(1),
        WEBVIEW_PAGE_FINISHED(2),
        WEBVIEW_PAGE_ERROR(3);
        
        private final int mValue;

        EnumC2249a(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }

        public static EnumC2249a valueOf(int i) {
            return (i < WEBVIEW_INIT.value() || i > WEBVIEW_PAGE_ERROR.value()) ? WEBVIEW_INIT : values()[i];
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action;
        if (this.f8055b && ((action = motionEvent.getAction()) == 2 || action == 0)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setDisallowInterceptTouchEvents(boolean z) {
        this.f8055b = z;
    }
}
