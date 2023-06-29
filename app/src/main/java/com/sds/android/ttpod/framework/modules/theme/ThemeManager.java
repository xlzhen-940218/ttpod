package com.sds.android.ttpod.framework.modules.theme;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.ClipBitmapDrawable;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.lang.reflect.Field;

/* renamed from: com.sds.android.ttpod.framework.modules.theme.c */
/* loaded from: classes.dex */
public class ThemeManager {

    /* renamed from: a */
    private static ThemeFramework.C2013b f6960a = null;

    /* compiled from: ThemeManager.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.c$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2019b {
        void onThemeLoaded();
    }

    /* renamed from: a */
    public static ColorDrawable m3273a() {
        if (f6960a == null) {
            m3257c();
        }
        return f6960a.m3316b();
    }

    /* renamed from: a */
    public static boolean m3269a(View view, String str) {
        if (view == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the theme id can not be empty");
        }
        String parsePanelId = ThemeElement.parsePanelId(str);
        String parseElementId = ThemeElement.parseElementId(str);
        if (ThemeElement.isTextElementId(parseElementId)) {
            return m3268a(view, parsePanelId, parseElementId);
        }
        if ((view instanceof ListView) && parseElementId.equals("Separator")) {
            return m3259b(view, parsePanelId, parseElementId);
        }
        if ((view instanceof ImageView) && ThemeElement.isImageElementId(parseElementId)) {
            return m3267a((ImageView) view, parsePanelId, parseElementId);
        }
        if ((view instanceof ProgressBar) && parseElementId.equals("ProgressBar")) {
            return m3266a((ProgressBar) view);
        }
        return m3255c(view, parsePanelId, parseElementId);
    }

    /* renamed from: a */
    public static Drawable m3265a(String str) {
        Object m3262a = m3262a(str, true, true);
        if (m3262a instanceof Drawable) {
            return (Drawable) m3262a;
        }
        return null;
    }

    /* renamed from: b */
    public static ThemeFramework.AbstractC2016e m3258b(String str) {
        return m3264a(ThemeElement.parsePanelId(str), ThemeElement.parseElementId(str), false);
    }

    /* renamed from: a */
    public static boolean m3271a(View view, int i, int i2, String str) {
        if (view == null) {
            return false;
        }
        String parsePanelId = ThemeElement.parsePanelId(str);
        String parseElementId = ThemeElement.parseElementId(str);
        ThemeFramework.AbstractC2016e m3258b = m3258b(str);
        if (m3258b != null) {
            int m3287e = m3258b.m3287e();
            int m3286f = m3258b.m3286f();
            if (m3287e > 0) {
                i = m3287e;
            }
            if (m3286f > 0) {
                i2 = m3286f;
            }
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = i;
            layoutParams.height = i2;
            view.setLayoutParams(layoutParams);
        }
        return m3255c(view, parsePanelId, parseElementId);
    }

    /* renamed from: c */
    public static ColorStateList m3254c(String str) {
        Object m3262a = m3262a(str, false, true);
        if (m3262a instanceof ColorStateList) {
            return (ColorStateList) m3262a;
        }
        return null;
    }

    /* renamed from: d */
    public static int m3253d(String str) {
        Object m3262a = m3262a(str, false, true);
        if (m3262a instanceof ColorStateList) {
            return ((ColorStateList) m3262a).getDefaultColor();
        }
        if (m3262a instanceof ColorDrawable) {
            return m3272a((ColorDrawable) m3262a);
        }
        if (m3262a instanceof StateListDrawable) {
            Drawable current = ((StateListDrawable) m3262a).getCurrent();
            if (current instanceof ColorDrawable) {
                return m3272a((ColorDrawable) current);
            }
        }
        return 0;
    }

    @TargetApi(11)
    /* renamed from: a */
    public static int m3272a(ColorDrawable colorDrawable) {
        if (SDKVersionUtils.sdkThan11()) {
            return colorDrawable.getColor();
        }
        try {
            Field declaredField = ColorDrawable.class.getDeclaredField("mState");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(colorDrawable);
            Field declaredField2 = obj.getClass().getDeclaredField("mUseColor");
            declaredField2.setAccessible(true);
            return ((Number) declaredField2.get(obj)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: a */
    public static Object m3262a(String str, boolean z, boolean z2) {
        return m3263a(ThemeElement.parsePanelId(str), ThemeElement.parseElementId(str), z, z2);
    }

    /* renamed from: a */
    private static Object m3263a(String title, String description, boolean z, boolean z2) {
        if (f6960a == null) {
            m3257c();
        }
        Drawable drawable = null;
        if (f6960a != null) {
            if (description.equals(ThemeElement.BACKGROUND_MASK)) {
                return f6960a.m3316b();
            }
            if (z) {
                drawable = f6960a.m3314b(title, description);
            }
            if (drawable == null) {
                Object m3311c = f6960a.m3311c(title, description);
                if (z2 && m3311c == null && !title.equals(ThemeElement.PANEL_COMMON)) {
                    return f6960a.m3317a(title, description);
                }
                return m3311c;
            }
            return drawable;
        }
        LogUtils.error("Theme", "no theme in the system");
        return null;
    }

    /* renamed from: a */
    private static ThemeFramework.AbstractC2016e m3264a(String str, String str2, boolean z) {
        if (f6960a == null) {
            m3257c();
        }
        if (f6960a != null && 0 == 0) {
            return f6960a.m3308d(str, str2);
        }
        LogUtils.error("Theme", "no theme in the system");
        return null;
    }

    /* renamed from: a */
    public static void m3270a(View view, Drawable drawable) {
        if (view == null || drawable == null) {
            LogUtils.error("ThemeManager", "ThemeManager.setViewBackground view or background drawable is null");
        } else {
            view.setBackground(drawable);
        }
    }

    /* renamed from: b */
    public static void m3260b(View view, Drawable drawable) {
        if (view != null && drawable != null) {
            ClipBitmapDrawable m3256c = m3256c(view, drawable);
            if (m3256c != null) {
                view.setBackground(m3256c);
            } else {
                view.setBackground(drawable);
            }
        }
    }

    /* renamed from: c */
    public static ClipBitmapDrawable m3256c(View view, Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            ClipBitmapDrawable clipBitmapDrawable = new ClipBitmapDrawable(BaseApplication.getApplication().getResources(), ((BitmapDrawable) drawable).getBitmap());
            clipBitmapDrawable.m3751a(view.getWidth(), view.getHeight());
            return clipBitmapDrawable;
        }
        return null;
    }

    /* renamed from: b */
    public static void m3261b() {
        if (f6960a != null) {
            f6960a.m3320a();
        }
        f6960a = null;
    }

    /* renamed from: c */
    private static void m3257c() {
        String m3038V = Preferences.m3038V();
        if (TextUtils.isEmpty(m3038V)) {
            m3038V = Preferences.getFirstSkinItemPath();
        }
        f6960a = ThemeFramework.C2013b.m3319a(new SkinCache(m3038V));
    }

    /* renamed from: a */
    private static boolean m3268a(View view, String str, String str2) {
        if (view instanceof TextView) {
            ColorStateList colorStateList = (ColorStateList) m3263a(str, str2, false, true);
            if (colorStateList != null) {
                ((TextView) view).setTextColor(colorStateList);
                return true;
            }
            LogUtils.error("Theme", "colorStateList is null " + str + " " + str2);
            return false;
        }
        throw new IllegalArgumentException("the view is not TextView, it can't supported to set text color");
    }

    /* renamed from: b */
    private static boolean m3259b(View view, String str, String str2) {
        Drawable drawable = (Drawable) m3263a(str, str2, false, true);
        if (drawable != null) {
            ((ListView) view).setDivider(drawable);
            ((ListView) view).setDividerHeight(1);
            return true;
        }
        LogUtils.error("Theme", "divider is null " + str + " " + str2);
        return false;
    }

    /* renamed from: a */
    private static boolean m3267a(ImageView imageView, String str, String str2) {
        Drawable drawable = (Drawable) m3263a(str, str2, true, true);
        if (drawable == null) {
            return false;
        }
        imageView.setImageDrawable(drawable);
        return true;
    }

    /* renamed from: a */
    private static boolean m3266a(ProgressBar progressBar) {
        boolean z;
        boolean z2 = false;
        Drawable progressDrawable = progressBar.getProgressDrawable();
        if (progressDrawable == null || !(progressDrawable instanceof LayerDrawable)) {
            return false;
        }
        LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;
        Drawable m3252e = m3252e("Progress");
        Drawable m3252e2 = m3252e("SecondProgress");
        Drawable m3265a = m3265a("ProgressBar");
        if (m3252e != null) {
            layerDrawable.setDrawableByLayerId(16908301, m3252e);
            z2 = true;
        }
        if (m3252e2 != null) {
            layerDrawable.setDrawableByLayerId(16908303, m3252e2);
            z = true;
        } else {
            z = z2;
        }
        if (m3265a != null) {
            m3270a(progressBar, m3265a);
            return true;
        }
        return z;
    }

    /* renamed from: e */
    private static Drawable m3252e(String str) {
        Drawable m3265a = m3265a(str);
        if (m3265a != null) {
            return new ClipDrawable(m3265a, 3, 1);
        }
        return null;
    }

    /* renamed from: c */
    private static boolean m3255c(View view, String str, String str2) {
        Drawable drawable = (Drawable) m3263a(str, str2, true, true);
        if (drawable != null) {
            m3270a(view, drawable);
            return true;
        }
        LogUtils.error("Theme", "background is null: " + str + " " + str2);
        return false;
    }

    /* compiled from: ThemeManager.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.c$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractC2018a {

        /* renamed from: a */
        private String f6961a = null;

        /* renamed from: a */
        protected abstract void mo3251a();

        /* renamed from: b */
        private boolean m3249b(String str) {
            return !StringUtils.equals(this.f6961a, str);
        }

        /* renamed from: a */
        public final void m3250a(String str) {
            if (str == null) {
                throw new NullPointerException("Theme file path can't be null.");
            }
            if (m3249b(str)) {
                this.f6961a = str;
                mo3251a();
            }
        }
    }
}
