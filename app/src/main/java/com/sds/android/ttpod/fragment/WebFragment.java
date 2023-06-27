package com.sds.android.ttpod.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.TextView;


import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;


import com.sds.android.ttpod.activities.web.JsCallback;
import com.sds.android.ttpod.activities.web.WebJsInterface;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowModule;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.StartAction;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.TTWebView;
import com.sds.android.ttpod.widget.TTWebViewClient;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Map;

/* loaded from: classes.dex */
public class WebFragment extends BaseFragment implements JsCallback, TTWebViewClient.InterfaceC2289a {
    public static final String EXTRA_ENABLE_SLIDING_CLOSABLE = "enable_sliding_closable";
    public static final String EXTRA_HINT_BANNER_SHOW = "extra_hint_banner_show";
    public static final String EXTRA_IS_SHOW_PLAY_CONTROL_BAR = "extra_is_show_play_control_bar";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_URL = "extra_url";
    private static final int MAX_PROGRESS = 100;
    private static final String TAG = "WebFragment";
    private static final String TTPOD = "TTPod";
    private DownloadListener mDownloadListener = new DownloadListener() { // from class: com.sds.android.ttpod.fragment.WebFragment.3
        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            DownloadTaskInfo generateDownloadTaskInfo = WebFragment.this.generateDownloadTaskInfo(str);
            if (generateDownloadTaskInfo != null) {
                CommandCenter.getInstance().execute(new Command(CommandID.ADD_DOWNLOAD_TASK, generateDownloadTaskInfo));
            }
        }
    };
    private FrameLayout mFrameLayoutWebView;
    private NetworkLoadView mLoadingView;
    private ProgressBar mProgressBar;
    private StartAction mStartAction;
    private TTWebViewClient mTTWebViewClient;
    private TTWebView mWebView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.layout_web_page, viewGroup, false);
        this.mFrameLayoutWebView = (FrameLayout) viewGroup2.findViewById(R.id.web_view);
        this.mWebView = new TTWebView(getActivity());
        this.mFrameLayoutWebView.addView(this.mWebView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.mProgressBar = (ProgressBar) viewGroup2.findViewById(R.id.web_view_load_progress);
        this.mProgressBar.setProgress(0);
        this.mProgressBar.setMax(100);
        this.mLoadingView = (NetworkLoadView) layoutInflater.inflate(R.layout.network_loadview, viewGroup, false);
        this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
        viewGroup2.addView(this.mLoadingView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.sds.android.ttpod.fragment.WebFragment.1
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                if (WebFragment.this.mProgressBar != null) {
                    WebFragment.this.mProgressBar.setProgress(i);
                    if (i == 100) {
                        WebFragment.this.mProgressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
        this.mTTWebViewClient = new TTWebViewClient(getActivity(), this, viewGroup2.findViewById(R.id.error_Page_layout)) { // from class: com.sds.android.ttpod.fragment.WebFragment.2
            @Override // com.sds.android.ttpod.widget.TTWebViewClient, android.webkit.WebViewClient
            public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
                if (HttpRequest.isProxy()) {
                    httpAuthHandler.proceed(UnicomFlowModule.USERNAME, UnicomFlowModule.PASSWORD);
                } else {
                    super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
                }
            }
        };
        this.mStartAction = new StartAction((BaseActivity) getActivity());
        this.mWebView.setWebViewClient(this.mTTWebViewClient);
        this.mWebView.setDownloadListener(this.mDownloadListener);
        this.mWebView.addJavascriptInterface(new WebJsInterface(this), TTPOD);
        Bundle arguments = getArguments();
        String string = arguments.getString(EXTRA_URL);
        boolean z = arguments.getBoolean(EXTRA_IS_SHOW_PLAY_CONTROL_BAR, false);
        if (!StringUtils.isEmpty(string)) {
            this.mWebView.loadUrl(string);
            if (z) {
                ((FrameLayout.LayoutParams) this.mWebView.getLayoutParams()).bottomMargin = (int) getResources().getDimension(R.dimen.playcontrol_bar_height);
            }
            ((TextView) viewGroup2.findViewById(R.id.hint_2)).setText(getString(R.string.online_search_hint_source, string));
            viewGroup2.findViewById(R.id.hint_banner).setVisibility(arguments.getBoolean(EXTRA_HINT_BANNER_SHOW, false) ? View.VISIBLE : View.GONE);
            //UnicomFlowManager.m7760a((Context) getActivity());
            UnicomFlowUtil.m3950b(getActivity());
            return viewGroup2;
        }
        throw new IllegalStateException("Url CAN NOT be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.PLAY_MEDIA_CHANGED, ReflectUtils.m8375a(cls, "playMediaChanged", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_UNICOM_FLOW_STATUS, ReflectUtils.m8375a(cls, "updateFlowStatus", Boolean.class));
    }

    public void updateFlowStatus(Boolean bool) {
        //UnicomFlowManager.m7760a((Context) getActivity());
    }

    public void playMediaChanged() {
        callJsPlayStatusChange(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m().toString());
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        callJsPlayStatusChange(playStatus.toString());
    }

    private void callJsPlayStatusChange(String str) {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull()) {
            this.mWebView.loadUrl("javascript:window.playStateChanged(" + m3225N.getSongID() + ", '" + str + "')");
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadTaskInfo generateDownloadTaskInfo(String str) {
        String mimeTypeFromExtension;
        String str2;
        if (StringUtils.isEmpty(str)) {
            LogUtils.debug(TAG, "url非法 url=" + str);
            return null;
        }
        String m8402j = FileUtils.getFilename(str);
        String m8399m = FileUtils.getSuffix(m8402j);
        if (m8399m.equals("tsk")) {
            mimeTypeFromExtension = "tsk/";
        } else {
            mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(m8399m);
        }
        if (mimeTypeFromExtension == null) {
            LogUtils.debug(TAG, "不支持的类型 url=" + str);
            return null;
        }
        Integer num = -1;
        if (mimeTypeFromExtension.startsWith("tsk/")) {
            str2 = TTPodConfig.getSkinPath() + File.separator + m8402j;
            num = DownloadTaskInfo.TYPE_SKIN;
        } else if (mimeTypeFromExtension.startsWith("audio/")) {
            str2 = Preferences.m3054N() + File.separator + m8402j;
            num = DownloadTaskInfo.TYPE_AUDIO;
        } else if (mimeTypeFromExtension.startsWith("application/")) {
            str2 = TTPodConfig.getAppPath() + File.separator + m8402j;
            num = DownloadTaskInfo.TYPE_APP;
        } else if (mimeTypeFromExtension.startsWith("video/")) {
            str2 = TTPodConfig.getMvPath() + File.separator + m8402j;
            num = DownloadTaskInfo.TYPE_VIDEO;
        } else {
            str2 = null;
        }
        LogUtils.debug(TAG, "download url=" + str + ",savePath=" + str2 + ",downloadType=" + num);
        if (StringUtils.isEmpty(str2) || num.intValue() < 0) {
            return null;
        }
        return DownloadUtils.m4760a(str, str2, 0L, m8402j, num, true, "skin");
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        stopJsPlay();
        if (this.mFrameLayoutWebView != null) {
            this.mFrameLayoutWebView.removeAllViews();
        }
        if (this.mTTWebViewClient != null) {
            this.mTTWebViewClient.m1236a();
            this.mTTWebViewClient.m1235a((TTWebViewClient.InterfaceC2289a) null);
        }
        if (this.mWebView != null) {
            this.mWebView.stopLoading();
            this.mWebView.removeAllViews();
            this.mWebView.setWebChromeClient(null);
            this.mWebView.setWebViewClient(null);
            this.mWebView.setDownloadListener(null);
            try {
                this.mWebView.removeJavascriptInterface(TTPOD);
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
            }
        }
        super.onDestroyView();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mWebView != null) {
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    private void stopJsPlay() {
        this.mWebView.loadUrl("javascript:KY.ine.stop()");
    }

    public void share() {
        this.mWebView.loadUrl("javascript:Ttpod.share.doShare()");
    }

    private boolean handleIntent(String str) {
        try {
            Intent parseUri = Intent.parseUri(str, Intent.URI_INTENT_SCHEME);
            parseUri.addCategory("android.intent.category.BROWSABLE");
            parseUri.setComponent(null);
            parseUri.putExtra("com.android.browser.application_id", getActivity().getPackageName());
            try {
                startActivity(parseUri);
                return true;
            } catch (ActivityNotFoundException e) {
                return false;
            }
        } catch (URISyntaxException e2) {
            return false;
        }
    }

    private boolean filterUrlScheme(String str) {
        return (str.startsWith("http://") || str.startsWith("https://")) ? false : true;
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public boolean onOverrideUrlLoadingEvent(WebView webView, String str) {
        if (filterUrlScheme(str)) {
            handleIntent(str);
            return true;
        }
        return false;
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public void onPageStartedEvent(WebView webView, String str, Bitmap bitmap) {
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public void onPageFinishedEvent(WebView webView, String str) {
        if (this.mLoadingView != null) {
            this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
        }
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public boolean onReceivedErrorEvent(WebView webView, int i, String str, String str2) {
        return false;
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public void onReloadEvent() {
        this.mWebView.reload();
    }

    @Override // com.sds.android.ttpod.widget.TTWebViewClient.InterfaceC2289a
    public void onLoadResource(WebView webView, String str) {
    }

    public void onHttpRequestedErrorEvent(int i, String str, String str2) {
    }

    @Override // com.sds.android.ttpod.activities.web.JsCallback
    public void doDownload(String str, String str2, int i) {
        if (this.mStartAction != null) {
            Bundle bundle = new Bundle();
            bundle.putString("action", "download");
            bundle.putString("uri", str);
            bundle.putString("name", str2);
            bundle.putInt("type", i);
            this.mStartAction.m8228a(bundle);
        }
    }

    public boolean canBackward() {
        return this.mWebView.canGoBack();
    }

    public void goBack() {
        this.mWebView.goBack();
    }
}
