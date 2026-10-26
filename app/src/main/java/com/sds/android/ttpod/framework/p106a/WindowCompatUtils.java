package com.sds.android.ttpod.framework.p106a;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.Window;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

/**
 * 系统栏 / 全屏适配工具。
 *
 * targetSdk 35+ 在 Android 15 上强制 edge-to-edge，旧的 FLAG_FULLSCREEN 与 windowFullscreen
 * 已不能可靠隐藏系统栏，这里统一改用 WindowInsetsControllerCompat 处理。
 */
public final class WindowCompatUtils {

    private WindowCompatUtils() {
    }

    /**
     * 开启 edge-to-edge，并给内容根视图补充系统栏 padding。
     * 系统栏隐藏时 inset 自动为 0，内容会自然铺满全屏。
     */
    public static void applyEdgeToEdge(Activity activity) {
        if (activity == null) {
            return;
        }
        Window window = activity.getWindow();
        WindowCompat.setDecorFitsSystemWindows(window, false);
        View content = activity.findViewById(android.R.id.content);
        if (content != null) {
            ViewCompat.setOnApplyWindowInsetsListener(content, (view, insets) -> {
                Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                return insets;
            });
        }
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());
        if (controller != null) {
            controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            // 各 Activity 的窗口背景均为深色/黑色，统一使用浅色系统栏图标。
            controller.setAppearanceLightStatusBars(false);
            controller.setAppearanceLightNavigationBars(false);
        }
    }

    public static void setFullScreen(Activity activity, boolean fullScreen) {
        if (activity == null) {
            return;
        }
        applyFullScreen(activity.getWindow(), fullScreen);
    }

    public static void setFullScreen(Dialog dialog, boolean fullScreen) {
        if (dialog == null || dialog.getWindow() == null) {
            return;
        }
        applyFullScreen(dialog.getWindow(), fullScreen);
    }

    private static void applyFullScreen(Window window, boolean fullScreen) {
        if (window == null) {
            return;
        }
        WindowCompat.setDecorFitsSystemWindows(window, false);
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());
        if (controller == null) {
            return;
        }
        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        controller.setAppearanceLightStatusBars(false);
        controller.setAppearanceLightNavigationBars(false);
        if (fullScreen) {
            controller.hide(WindowInsetsCompat.Type.systemBars());
        } else {
            controller.show(WindowInsetsCompat.Type.systemBars());
        }
    }

    public static boolean isNightMode(Context context) {
        if (context == null) {
            return false;
        }
        int mode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return mode == Configuration.UI_MODE_NIGHT_YES;
    }
}
