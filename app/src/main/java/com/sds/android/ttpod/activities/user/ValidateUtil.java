package com.sds.android.ttpod.activities.user;

import android.view.View;
import android.view.animation.AnimationUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.util.regex.Pattern;

/* renamed from: com.sds.android.ttpod.activities.user.e */
/* loaded from: classes.dex */
public class ValidateUtil {

    /* renamed from: a */
    public static final InterfaceC0940a f3111a = new InterfaceC0940a() { // from class: com.sds.android.ttpod.activities.user.e.1
        @Override // com.sds.android.ttpod.activities.user.ValidateUtil.InterfaceC0940a
        /* renamed from: a */
        public boolean mo7703a(String str) {
            return StringUtils.m8339b(str);
        }
    };

    /* renamed from: b */
    public static final InterfaceC0940a f3112b = new InterfaceC0940a() { // from class: com.sds.android.ttpod.activities.user.e.2
        @Override // com.sds.android.ttpod.activities.user.ValidateUtil.InterfaceC0940a
        /* renamed from: a */
        public boolean mo7703a(String str) {
            return StringUtils.m8336c(str);
        }
    };

    /* renamed from: c */
    public static final InterfaceC0940a f3113c = new InterfaceC0940a() { // from class: com.sds.android.ttpod.activities.user.e.3
        @Override // com.sds.android.ttpod.activities.user.ValidateUtil.InterfaceC0940a
        /* renamed from: a */
        public boolean mo7703a(String str) {
            boolean z = true;
            if (Pattern.compile("[=<>!'@\"*?]").matcher(str).find()) {
                return false;
            }
            int i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char charAt = str.charAt(length);
                i += (TTTextUtils.isLetterOrDigit(charAt) || charAt == ' ' || charAt == '-' || charAt == '_') ? 1 : 2;
            }
            return (i < 1 || i > 30) ? false : false;
        }
    };

    /* renamed from: d */
    public static final InterfaceC0940a f3114d = new InterfaceC0940a() { // from class: com.sds.android.ttpod.activities.user.e.4
        @Override // com.sds.android.ttpod.activities.user.ValidateUtil.InterfaceC0940a
        /* renamed from: a */
        public boolean mo7703a(String str) {
            return StringUtils.m8345a(str, 4, 20);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ValidateUtil.java */
    /* renamed from: com.sds.android.ttpod.activities.user.e$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0940a {
        /* renamed from: a */
        boolean mo7703a(String str);
    }

    /* renamed from: a */
    public static boolean m7704a(String str, int i, int i2, View view, int i3, InterfaceC0940a interfaceC0940a) {
        if (interfaceC0940a == null) {
            return true;
        }
        if (StringUtils.isEmpty(str)) {
            m7705a(view, i3);
            PopupsUtils.m6760a(i);
            return false;
        } else if (interfaceC0940a.mo7703a(str)) {
            return true;
        } else {
            m7705a(view, i3);
            PopupsUtils.m6760a(i2);
            return false;
        }
    }

    /* renamed from: a */
    private static void m7705a(View view, int i) {
        if (view != null) {
            view.requestFocus();
            view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), i));
        }
    }
}
