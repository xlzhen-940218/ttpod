package com.sds.android.ttpod.utils;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeFramework;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.a.w */
/* loaded from: classes.dex */
public class ThemeUtils {

    /* renamed from: a */
    public static final ColorStateList f2532a = ColorStateList.valueOf(-1888741);

    /* renamed from: a */
    public static void m8168a(ActionBarController.C1070a c1070a, String str, int i, String str2) {
        if (c1070a != null) {
            Drawable m3265a = ThemeManager.m3265a(str);
            if (m3265a != null) {
                c1070a.setDrawable(m3265a);
            } else {
                m8170a(c1070a, i, str2);
            }
        }
    }

    /* renamed from: a */
    public static void m8170a(ActionBarController.C1070a c1070a, int i, String str) {
        if (i != 0) {
            c1070a.setImageText(i);
            ColorStateList colorStateList = (ColorStateList) ThemeManager.m3262a(str, false, true);
            if (colorStateList != null) {
                c1070a.setTextColor(colorStateList);
            }
        }
    }

    /* renamed from: b */
    public static void m8161b(ActionBarController.C1070a c1070a, String str, int i, String str2) {
        m8169a(c1070a, ThemeManager.m3265a(str), i, str2);
    }

    /* renamed from: a */
    public static void m8169a(ActionBarController.C1070a c1070a, Drawable drawable, int i, String str) {
        if (c1070a != null) {
            if (drawable != null) {
                c1070a.m7169a(drawable);
            } else if (i != 0) {
                c1070a.m7171a(i);
                ColorStateList colorStateList = (ColorStateList) ThemeManager.m3262a(str, false, true);
                if (colorStateList != null) {
                    c1070a.m7170a(colorStateList);
                }
            }
        }
    }

    /* renamed from: a */
    public static Drawable m8182a() {
        Drawable drawable = (Drawable) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_BACKGROUND, new Object[0]), Drawable.class);
        if (drawable == null) {
            return null;
        }
        return drawable;
    }

    /* renamed from: b */
    public static String m8163b() {
        return (String) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_SKIN_PROTOCOL_PATH, new Object[0]), String.class);
    }

    /* renamed from: a */
    public static void m8178a(View view, String str, String str2) {
        if (!ThemeManager.m3269a(view, str)) {
            ThemeManager.m3269a(view, str2);
        }
    }

    /* renamed from: a */
    public static Drawable m8165a(String str, int i) {
        Drawable m3265a = ThemeManager.m3265a(str);
        if (m3265a == null) {
            return BaseApplication.getApplication().getResources().getDrawable(i);
        }
        return m3265a;
    }

    /* renamed from: a */
    public static void m8176a(IconTextView iconTextView, int i, int i2, String str, String str2, String str3, String str4) {
        iconTextView.m7217a(i, i2);
        ColorStateList colorStateList = (ColorStateList) ThemeManager.m3262a(str, false, true);
        ColorStateList colorStateList2 = (ColorStateList) ThemeManager.m3262a(str2, false, true);
        if (colorStateList == null || colorStateList2 == null) {
            colorStateList = (ColorStateList) ThemeManager.m3262a(str3, false, true);
            colorStateList2 = (ColorStateList) ThemeManager.m3262a(str4, false, true);
        }
        iconTextView.m7215a(colorStateList, colorStateList2);
    }

    /* renamed from: a */
    public static boolean m8166a(SlidingTabHost slidingTabHost) {
        if (slidingTabHost == null) {
            return false;
        }
        slidingTabHost.setTextColor(ThemeManager.m3254c(ThemeElement.SUB_BAR_TEXT));
        slidingTabHost.setIndicatorColor(ThemeManager.m3253d(ThemeElement.SUB_BAR_INDICATOR));
        return ThemeManager.m3271a(slidingTabHost, -1, BaseApplication.getApplication().getResources().getDimensionPixelSize(R.dimen.tab_label_height), ThemeElement.SUB_BAR_BACKGROUND);
    }

    /* renamed from: a */
    public static boolean m8164a(ArrayList<SkinItem> arrayList, ArrayList<SkinItem> arrayList2) {
        if (arrayList == null || arrayList2 == null || arrayList.size() != arrayList2.size()) {
            return false;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (!arrayList.get(i).m3572a(arrayList2.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    public static int m8160c() {
        try {
            return BaseApplication.getApplication().getAssets().list("skin").length;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: d */
    public static int m8159d() {
        int intValue = Cache.getInstance().m3141w().intValue();
        if (intValue == 0) {
            int m8160c = m8160c();
            Cache.getInstance().m3208a(Integer.valueOf(m8160c));
            return m8160c;
        }
        return intValue;
    }

    /* renamed from: a */
    public static void m8179a(View view, ThemeFramework.AbstractC2016e abstractC2016e) {
        if (abstractC2016e != null) {
            int m3284h = abstractC2016e.m3284h();
            view.setPadding(m3284h, m3284h, m3284h, m3284h);
            return;
        }
        view.setPadding(0, 0, 0, 0);
    }

    /* renamed from: a */
    public static int m8167a(ThemeFramework.AbstractC2016e abstractC2016e, int i) {
        int i2 = 0;
        if (abstractC2016e != null) {
            i2 = abstractC2016e.m3286f();
        }
        return i2 > 0 ? i2 : i;
    }

    /* renamed from: a */
    public static void m8180a(View view, Drawable drawable) {
        if (view != null) {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    public static void m8177a(SeekBar seekBar) {
        Rect bounds = seekBar.getProgressDrawable().getBounds();
        Drawable m3265a = ThemeManager.m3265a(ThemeElement.COMMON_PROGRESS_BAR);
        if (m3265a == null) {
            m3265a = new ColorDrawable(ThemeFramework.C2014c.m3304a("#000000 0"));
        }
        seekBar.setBackgroundDrawable(m3265a);
        Drawable m3265a2 = ThemeManager.m3265a(ThemeElement.COMMON_PROGRESS_DRAWABLE);
        if (m3265a2 == null) {
            m3265a2 = new ColorDrawable(ThemeFramework.C2014c.m3304a("#4eccf6 80"));
        }
        ClipDrawable clipDrawable = new ClipDrawable(m3265a2, 19, 1);
        Drawable m3265a3 = ThemeManager.m3265a(ThemeElement.COMMON_SECOND_PROGRESS_DRAWABLE);
        if (m3265a3 == null) {
            m3265a3 = new ColorDrawable(ThemeFramework.C2014c.m3304a("#4eccf6 20"));
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{clipDrawable, new ClipDrawable(m3265a3, 19, 1)});
        layerDrawable.setId(0, 16908301);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setBounds(bounds);
        seekBar.setProgressDrawable(layerDrawable);
        seekBar.setProgress(0);
        seekBar.setSecondaryProgress(0);
    }

    /* renamed from: a */
    public static void m8173a(IconTextView iconTextView, String str) {
        ColorStateList colorStateList = (ColorStateList) ThemeManager.m3262a(str, false, true);
        if (colorStateList != null) {
            iconTextView.setTextColor(colorStateList);
        }
    }

    /* renamed from: a */
    public static void m8171a(IconTextView iconTextView, String str, String str2) {
        ColorStateList colorStateList = (ColorStateList) ThemeManager.m3262a(str, false, true);
        if (colorStateList == null) {
            m8173a(iconTextView, str2);
        } else {
            iconTextView.setTextColor(colorStateList);
        }
    }

    /* renamed from: a */
    public static void m8172a(IconTextView iconTextView, String str, int i, String str2) {
        Drawable m3265a = ThemeManager.m3265a(str);
        if (m3265a == null) {
            iconTextView.setText(i);
            m8173a(iconTextView, str2);
            return;
        }
        iconTextView.setImageDrawable(m3265a);
    }

    /* renamed from: a */
    public static void m8174a(IconTextView iconTextView, int i, String str, String str2) {
        iconTextView.setText(i);
        m8171a(iconTextView, str, str2);
    }

    /* renamed from: a */
    public static void m8175a(IconTextView iconTextView, int i, String str) {
        iconTextView.setText(i);
        m8173a(iconTextView, str);
    }

    /* renamed from: a */
    public static void m8181a(View view) {
        view.findViewById(R.id.actionbar_shadow).setVisibility(View.GONE);
        view.findViewById(R.id.action_bar_controller).setVisibility(View.GONE);
        ((ViewGroup.MarginLayoutParams) view.findViewById(R.id.activity_body).getLayoutParams()).topMargin = 0;
    }

    /* renamed from: b */
    public static void m8162b(View view) {
        ThemeManager.m3269a(view, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND);
        m8171a((IconTextView) view.findViewById(R.id.no_media_icon), ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        m8178a(view.findViewById(R.id.textview_load_failed), ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
    }

    /* renamed from: e */
    public static AnimationDrawable m8158e() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        int i = 0;
        while (true) {
            int i2 = i + 1;
            Drawable drawable = (Drawable) ThemeManager.m3262a("spectrum" + File.separator + i2, true, false);
            if (drawable != null) {
                animationDrawable.addFrame(drawable, 100);
                i = i2;
            } else {
                animationDrawable.setOneShot(false);
                return animationDrawable;
            }
        }
    }
}
