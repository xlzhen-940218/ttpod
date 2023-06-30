package com.sds.android.ttpod;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.activities.GuideActivity;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class EntryActivity extends BaseActivity {
    private static final long GUIDE_DELAY_START = 600;
    private static final long MAINACTIVITY_DELAY_START = 100;
    private static final String TAG = "EntryActivity";
    private View.OnClickListener mOnClickAudioEnableListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.EntryActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setBackgroundResource(!Preferences.m3028a() ? R.drawable.xml_background_button_splash_audio_disabled : R.drawable.xml_background_button_splash_audio_enabled);
            CommandCenter m4607a = CommandCenter.getInstance();
            CommandID commandID = CommandID.SET_AUDIO_ENABLED;
            Object[] objArr = new Object[1];
            objArr[0] = Boolean.valueOf(!Preferences.m3028a());
            m4607a.execute(new Command(commandID, objArr));
        }
    };
    private boolean mSentLoadSplashCommand;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_entry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (TTPodConfig.getFinishSplash()) {
            startMainActivity();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SPLASH, ReflectUtils.loadMethod(EntryActivity.class, "updateSplash", Bitmap.class, Bitmap.class, String.class, Boolean.class));
        map.put(CommandID.FINISH_SPLASH, ReflectUtils.loadMethod(EntryActivity.class, "finishSplash", new Class[0]));
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        LogUtils.error("start", "EntryActivity onWindowFocusChanged splash loaded test");
        if (z && !this.mSentLoadSplashCommand && !TTPodConfig.getFinishSplash()) {
            this.mSentLoadSplashCommand = true;
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.LOAD_SPLASH, Integer.valueOf((int) R.drawable.img_splash), Integer.valueOf((int) R.string.readme)));
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @TargetApi(11)
    public void updateSplash(Bitmap bitmap, Bitmap bitmap2, String str, Boolean bool) {
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(bitmap2 != null);
        LogUtils.error("start", "splash loaded splashBitmap != null ? %b", objArr);
        if (bitmap != null) {
            ImageView imageView = (ImageView) findViewById(R.id.imageview_channel_logo);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        }
        if (bitmap2 != null) {
            ImageView imageView2 = (ImageView) findViewById(R.id.imageview_splash);
            imageView2.setImageBitmap(bitmap2);
            BitmapUtils.m8447a(imageView2);
            if (bool.booleanValue()) {
                View findViewById = findViewById(R.id.button_voice);
                findViewById.setVisibility(View.VISIBLE);
                findViewById.setBackgroundResource(Preferences.m3028a() ? R.drawable.xml_background_button_splash_audio_enabled : R.drawable.xml_background_button_splash_audio_disabled);
                findViewById.setOnClickListener(this.mOnClickAudioEnableListener);
            }
        }
        if (FileUtils.m8414b(str)) {
            WebView webView = new WebView(this);
            webView.getSettings().setJavaScriptEnabled(true);
            if (SDKVersionUtils.sdkThan11()) {
                webView.setLayerType(1, null);
            }
            webView.setBackgroundColor(0);
            Drawable background = webView.getBackground();
            if (background != null) {
                background.setAlpha(1);
            }
            addContentView(webView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            webView.loadUrl("file://" + str);
        }
        getWindow().setFlags(1024, 1024);
    }

    public void finishSplash() {
        TTPodConfig.setFinishSplash();
        LogUtils.error("start", "finishSplash");
        startMainActivity();
    }

    private void startMainActivity() {
        if (Preferences.m3000aG()) {
            String m3038V = Preferences.m3038V();
            if (!StringUtils.isEmpty(m3038V) && "assets://".equals(SkinUtils.m4644b(m3038V)) && FileUtils.getFilename(m3038V).startsWith("1")) {
                Preferences.m2876h("");
            }
            Preferences.m2844p(EnvironmentUtils.AppConfig.getAppVersion());
            Cache.getInstance().m3142v();
        }
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.LOAD_BACKGROUND, new Object[0]));
        new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.EntryActivity.3
            @Override // java.lang.Runnable
            public void run() {
                EntryActivity.this.startActivity(new Intent(EntryActivity.this, MainActivity.class).setData(EntryActivity.this.getIntent().getData()).putExtras(EntryActivity.this.getIntent()));
                EntryActivity.this.finish();
            }
        }, MAINACTIVITY_DELAY_START);
    }

    /* renamed from: com.sds.android.ttpod.EntryActivity$2 */
    /* loaded from: classes.dex */
    class RunnableC06142 implements Runnable {
        RunnableC06142() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Preferences.m2844p(EnvironmentUtils.AppConfig.getAppVersion());
            EntryActivity.this.startActivity(new Intent(EntryActivity.this, GuideActivity.class));
            EntryActivity.this.finish();
        }
    }
}
