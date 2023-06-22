package com.sds.android.ttpod.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;
import com.sds.android.ttpod.activities.unicomflow.UnicomFlowManager;
import com.sds.android.ttpod.activities.unicomflow.WebViewProxy;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowModule;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.ApkUtils;
import com.sds.android.ttpod.widget.TTWebView;
import com.sds.android.ttpod.widget.TTWebViewClient;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class RecommendWebFragment extends SlidingClosableFragment implements TTWebViewClient.InterfaceC2289a {
    private static final String KEY_APP_NAME = "appName";
    private static final int MAX_PROGRESS = 100;
    private static final int PADING_BOTTON_SIZE = 50;
    public static final String PROTOCOL_TAOBAO = "taoappcenter://";
    private static List<DownloadTaskInfo> mTasks = new ArrayList();
    private String mAppName;
    private ActionBarController.C1070a mDownloadAction;
    private View mErrorPageView;
    private FrameLayout mFrameLayoutWebView;
    private ProgressBar mProgressBar;
    private TTWebView mWebView;
    private String mUrl = "http://rj.m.taobao.com/wap/appmark/appWall.htm?gid=34&channel=%s";
    private DownloadListener mDownloadListener = new DownloadListener() { // from class: com.sds.android.ttpod.activities.RecommendWebFragment.4
        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            DownloadTaskInfo generateDownloadTaskInfo = RecommendWebFragment.this.generateDownloadTaskInfo(str);
            if (generateDownloadTaskInfo != null) {
                CommandCenter.m4607a().m4606a(new Command(CommandID.ADD_DOWNLOAD_TASK, generateDownloadTaskInfo));
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_web_page, viewGroup, false);
        getActionBarController().m7189b(R.string.market_app);
        this.mFrameLayoutWebView = (FrameLayout) inflate.findViewById(R.id.web_view);
        this.mWebView = new TTWebView(getActivity());
        this.mFrameLayoutWebView.addView(this.mWebView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.mErrorPageView = inflate.findViewById(R.id.error_Page_layout);
        this.mProgressBar = (ProgressBar) inflate.findViewById(R.id.web_view_load_progress);
        this.mUrl = String.format(this.mUrl, "f" + EnvironmentUtils.C0602a.m8512b());
        init();
        load();
        UnicomFlowUtil.m3950b(getActivity());
        inflate.setPadding(0, 0, 0, DisplayUtils.m7229a(50));
        return inflate;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    private void init() {
        this.mProgressBar.setProgress(0);
        this.mProgressBar.setMax(100);
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.sds.android.ttpod.activities.RecommendWebFragment.1
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                if (RecommendWebFragment.this.mProgressBar == null) {
                    RecommendWebFragment.this.mProgressBar.setProgress(i);
                    if (i == 100) {
                        RecommendWebFragment.this.mProgressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
        this.mWebView.setWebViewClient(new TTWebViewClient(getActivity(), this, this.mErrorPageView) { // from class: com.sds.android.ttpod.activities.RecommendWebFragment.2
            @Override // com.sds.android.ttpod.widget.TTWebViewClient, android.webkit.WebViewClient
            public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
                if (WebViewProxy.m7740a() && HttpRequest.m8704b()) {
                    httpAuthHandler.proceed(UnicomFlowModule.USERNAME, UnicomFlowModule.PASSWORD);
                } else {
                    super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
                }
            }
        });
        this.mWebView.setDownloadListener(this.mDownloadListener);
        this.mDownloadAction = getActionBarController().m7178d(Preferences.m2993aN() ? R.drawable.img_download_highlight : R.drawable.img_download_normal);
        this.mDownloadAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.RecommendWebFragment.3
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                Bundle bundle = new Bundle(1);
                bundle.putInt(DownloadManagerFragment.DOWNLOAD_TYPE, DownloadTaskInfo.TYPE_APP.intValue());
                DownloadManagerFragment downloadManagerFragment = new DownloadManagerFragment();
                downloadManagerFragment.setArguments(bundle);
                RecommendWebFragment.this.launchFragment(downloadManagerFragment);
                Preferences.m3039U(false);
                RecommendWebFragment.this.mDownloadAction.m7153d(R.drawable.img_download_normal);
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", SAction.ACTION_MY_DOWNLOAD.getValue(), 0, SPage.PAGE_MY_DOWNLOAD_DOWNLOADED.getValue());
                sUserEvent.append(DownloadManagerFragment.DOWNLOAD_TYPE, DownloadTaskInfo.TYPE_APP);
                sUserEvent.setPageParameter(true);
                sUserEvent.post();
            }
        });
        UnicomFlowManager.m7760a((Context) getActivity());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.m8375a(getClass(), "updateDownloadTaskState", DownloadTaskInfo.class));
        map.put(CommandID.UPDATE_UNICOM_FLOW_STATUS, ReflectUtils.m8375a(getClass(), "updateUnicomFlowStatus", Boolean.class));
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        if (this.mFrameLayoutWebView != null) {
            this.mFrameLayoutWebView.removeAllViews();
        }
        if (this.mWebView != null) {
            this.mWebView.stopLoading();
            this.mWebView.removeAllViews();
            this.mWebView.setWebChromeClient(null);
            this.mWebView.setWebViewClient(null);
            this.mWebView.setDownloadListener(null);
        }
        super.onDestroyView();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mWebView != null) {
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    public void updateUnicomFlowStatus(Boolean bool) {
        UnicomFlowManager.m7760a((Context) getActivity());
    }

    public void updateDownloadTaskState(DownloadTaskInfo downloadTaskInfo) {
        if (downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_APP) {
            if (downloadTaskInfo.getState().intValue() == 4) {
                ApkUtils.m8311a(getActivity(), downloadTaskInfo.getSavePath());
            } else if (!mTasks.contains(downloadTaskInfo)) {
                mTasks.add(downloadTaskInfo);
                this.mDownloadAction.m7153d(R.drawable.img_download_highlight);
                Preferences.m3039U(true);
            } else if (mTasks.contains(downloadTaskInfo)) {
                mTasks.remove(downloadTaskInfo);
                if (mTasks.size() == 0) {
                    this.mDownloadAction.m7153d(R.drawable.img_download_normal);
                    Preferences.m3039U(false);
                }
            }
        }
    }

    private void load() {
        this.mWebView.loadUrl(this.mUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadTaskInfo generateDownloadTaskInfo(String str) {
        if (StringUtils.m8346a(str) || MimeTypeMap.getSingleton().getMimeTypeFromExtension("apk") == null) {
            return null;
        }
        String queryParameter = Uri.parse(str).getQueryParameter("appName");
        if (!StringUtils.m8346a(queryParameter)) {
            if (queryParameter.indexOf(".apk") < 0) {
                queryParameter = queryParameter + ".apk";
            }
        } else {
            queryParameter = FileUtils.m8402j(str);
        }
        String str2 = TTPodConfig.m5285w() + File.separator + queryParameter;
        int intValue = DownloadTaskInfo.TYPE_APP.intValue();
        if (StringUtils.m8346a(str2) || intValue < 0) {
            return null;
        }
        return DownloadUtils.m4760a(str, str2, 0L, queryParameter, Integer.valueOf(intValue), true, OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY);
    }

    private boolean isTaobaoProtocol(String str) {
        return !StringUtils.m8346a(str) && str.startsWith(PROTOCOL_TAOBAO);
    }

    private String getUrlByAppName(String str, String str2) {
        try {
            String query = new URL(str).getQuery();
            int indexOf = query.indexOf("&appName=");
            return URLDecoder.decode(query.substring("&appName=".length() + indexOf, query.indexOf("&", indexOf + 1)), str2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String appendAppNameUrl(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        if (!StringUtils.m8346a(str2)) {
            sb.append("&");
            sb.append("appName");
            sb.append("=");
            sb.append(this.mAppName);
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public boolean onOverrideUrlLoadingEvent(WebView webView, String str) {
        if (isTaobaoProtocol(str)) {
            return false;
        }
        this.mProgressBar.setVisibility(View.VISIBLE);
        String urlByAppName = getUrlByAppName(str, "gbk");
        if (StringUtils.m8346a(urlByAppName)) {
            this.mWebView.loadUrl(appendAppNameUrl(str, this.mAppName));
            return true;
        }
        this.mAppName = urlByAppName;
        return false;
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public void onPageStartedEvent(WebView webView, String str, Bitmap bitmap) {
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public void onPageFinishedEvent(WebView webView, String str) {
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public boolean onReceivedErrorEvent(WebView webView, int i, String str, String str2) {
        return !isTaobaoProtocol(str2);
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public void onReloadEvent() {
        load();
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public void onLoadResource(WebView webView, String str) {
    }

    public void onHttpRequestedErrorEvent(int i, String str, String str2) {
    }
}
