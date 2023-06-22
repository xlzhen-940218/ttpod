package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.framework.a.a.c */
/* loaded from: classes.dex */
public class AudioEffectStatistic {
    /* renamed from: a */
    public static void m5271a() {
        StatisticUtils.m4909a("audio_effect", "show", "audio-effect-best", Preferences.m2974ad() ? 1L : 0L);
        if (Preferences.m2974ad()) {
            StatisticUtils.m4917a(17, (int) StatisticHelper.DELAY_SEND, 1L);
        } else {
            StatisticUtils.m4917a(18, (int) StatisticHelper.DELAY_SEND, 1L);
        }
    }

    /* renamed from: b */
    public static void m5270b() {
        StatisticUtils.m4917a(87, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: c */
    public static void m5269c() {
        StatisticUtils.m4917a(88, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: d */
    public static void m5268d() {
        StatisticUtils.m4917a(90, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: e */
    public static void m5267e() {
        StatisticUtils.m4917a(91, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: f */
    public static void m5266f() {
        StatisticUtils.m4917a(92, (int) StatisticHelper.DELAY_SEND, 1L);
        SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_DEFAULT_HANDPICK, SPage.PAGE_NONE, SPage.PAGE_NONE);
    }

    /* renamed from: g */
    public static void m5265g() {
        StatisticUtils.m4917a(93, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: h */
    public static void m5264h() {
        StatisticUtils.m4917a(94, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: i */
    public static void m5263i() {
        StatisticUtils.m4917a(95, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: j */
    public static void m5262j() {
        StatisticUtils.m4917a(96, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: k */
    public static void m5261k() {
        StatisticUtils.m4917a(97, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: l */
    public static void m5260l() {
        StatisticUtils.m4917a(98, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: m */
    public static void m5259m() {
        StatisticUtils.m4917a(99, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: n */
    public static void m5258n() {
        StatisticUtils.m4917a(100, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: o */
    public static void m5257o() {
        StatisticUtils.m4917a((int) ApShareReceiveFragment.WHAT_GET_SHARED_LIST_COMPLETE, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: p */
    public static void m5256p() {
        StatisticUtils.m4917a(103, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: q */
    public static void m5255q() {
        StatisticUtils.m4917a(104, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: r */
    public static void m5254r() {
        StatisticUtils.m4917a(105, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: s */
    public static void m5253s() {
        StatisticUtils.m4917a(106, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: t */
    public static void m5252t() {
        StatisticUtils.m4917a(107, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: u */
    public static void m5251u() {
        StatisticUtils.m4917a(108, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: v */
    public static void m5250v() {
        StatisticUtils.m4917a(109, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: w */
    public static void m5249w() {
        StatisticUtils.m4917a(110, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: x */
    public static void m5248x() {
        StatisticUtils.m4917a(111, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: y */
    public static void m5247y() {
        StatisticUtils.m4917a(112, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: z */
    public static void m5246z() {
        StatisticUtils.m4917a(113, (int) StatisticHelper.DELAY_SEND, 1L);
    }
}
