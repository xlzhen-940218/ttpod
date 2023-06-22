package com.sds.android.ttpod.activities.audioeffect;

import android.graphics.Color;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.framework.base.BaseApplication;

/* renamed from: com.sds.android.ttpod.activities.audioeffect.a */
/* loaded from: classes.dex */
public class ActionBarUtils {
    /* renamed from: a */
    public static void m8130a(ActionBarController actionBarController) {
        actionBarController.m7200a(Color.parseColor("#1b1b1b"));
        actionBarController.m7183c(-1);
        IconTextView m7190b = actionBarController.m7190b();
        m7190b.setText(R.string.icon_arrow_left);
        m7190b.setTextColor(-1);
    }

    /* renamed from: b */
    public static void m8129b(ActionBarController actionBarController) {
        actionBarController.m7200a(Color.parseColor("#f6fafb"));
        actionBarController.m7183c(BaseApplication.getApplication().getResources().getColor(R.color.apshare_text_color_blue));
        actionBarController.m7190b().setText(R.string.icon_arrow_left);
    }
}
