package com.sds.android.ttpod.activities.ktv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.utils.ThemeUtils;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* loaded from: classes.dex */
public class FragmentLoaderActivity extends SlidingClosableActivity {
    private static final String FOLDER_APK = "apk";
    private static final String FOLDER_DEX = "dexout";
    private AssetManager mAssetManager;
    private ClassLoader mClassLoader;
    private Resources mResources;
    private Resources.Theme mTheme;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("连接KTV");
        init();
    }

    private void init() {
        try {
            FrameLayout frameLayout = new FrameLayout(this);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            frameLayout.setId(R.id.layout_primary);
            Drawable m8182a = ThemeUtils.m8182a();
            if (m8182a != null) {
                frameLayout.setBackground(m8182a);
            }
            setContentView(frameLayout);
            File file = new File(getFilesDir(), FOLDER_APK + File.separator + "Ktv.apk");
            File file2 = new File(getFilesDir(), FOLDER_DEX);
            file2.mkdir();
            this.mClassLoader = new DexClassLoader(file.getAbsolutePath(), file2.getAbsolutePath(), null, super.getClassLoader());
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            assetManager.getClass().getMethod("addAssetPath", String.class).invoke(assetManager, file.getAbsolutePath());
            this.mAssetManager = assetManager;
            Resources resources = super.getResources();
            this.mResources = new Resources(this.mAssetManager, resources.getDisplayMetrics(), resources.getConfiguration());
            this.mTheme = this.mResources.newTheme();
            this.mTheme.setTo(super.getTheme());
            String stringExtra = getIntent().getStringExtra("class");
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add(R.id.layout_primary, (Fragment) this.mClassLoader.loadClass(stringExtra).newInstance());
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.mAssetManager == null ? super.getAssets() : this.mAssetManager;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.mResources == null ? super.getResources() : this.mResources;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        return this.mTheme == null ? super.getTheme() : this.mTheme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        return this.mClassLoader == null ? super.getClassLoader() : this.mClassLoader;
    }

    public static void installPlugin(Context context, String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            File file = new File(context.getFilesDir(), FOLDER_APK);
            file.mkdir();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "Ktv.apk"));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            new File(context.getFilesDir(), FOLDER_DEX).mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startFragmentLoaderActivity(Context context, int i) {
        Intent intent = new Intent();
        intent.setClass(context, FragmentLoaderActivity.class);
        intent.putExtra("path", FOLDER_APK + File.separator + "Ktv.apk");
        intent.putExtra("class", "com.sds.android.ttpod.plugin.ktv.MyCaptureFragment");
        ((Activity) context).startActivityForResult(intent, i);
    }
}
