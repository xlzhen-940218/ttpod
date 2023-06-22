package com.sds.android.ttpod.framework.p106a.p107a;


import com.sds.android.sdk.core.statistic.KVStatisticEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.modules.skin.BackgroundListLoader;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.io.File;

/* renamed from: com.sds.android.ttpod.framework.a.a.t */
/* loaded from: classes.dex */
public class ThemeStatistic {

    /* renamed from: a */
    private static boolean f5620a = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThemeStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.t$a */
    /* loaded from: classes.dex */
    public enum EnumC1774a {
        FOLLOW_SKIN,
        LOCAL
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThemeStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.t$b */
    /* loaded from: classes.dex */
    public enum EnumC1775b {
        INTERNAL,
        LOCAL
    }

    /* renamed from: a */
    public static void m4899a(String str) {
        StatisticUtils.m4913a(310, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_RECOMMEND_DOWNLOAD.getValue(), SPage.PAGE_NONE.getValue()).append("skin_name", str).post();
    }

    /* renamed from: b */
    public static void m4896b(String str) {
        StatisticUtils.m4913a(316, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_RANK_DOWNLOAD.getValue(), SPage.PAGE_NONE.getValue()).append("skin_name", str).post();
    }

    /* renamed from: c */
    public static void m4894c(String str) {
        StatisticUtils.m4913a(317, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_CATEGORY_DOWNLOAD.getValue(), SPage.PAGE_NONE.getValue()).append("skin_name", str).post();
    }

    /* renamed from: a */
    public static void m4901a() {
        StatisticUtils.m4916a(132, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_MY_SET.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: b */
    public static void m4898b() {
        StatisticUtils.m4916a(133, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_MY_EDIT.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: c */
    public static void m4895c() {
        StatisticUtils.m4916a(309, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_RANK.getValue(), SPage.PAGE_THEME_BACKGROUND.getValue(), SPage.PAGE_THEME_RANK.getValue()).post();
        m4874r();
    }

    /* renamed from: d */
    public static void m4893d() {
        StatisticUtils.m4916a(129, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_MY_TAB.getValue(), SPage.PAGE_NONE.getValue()).post();
        m4874r();
    }

    /* renamed from: e */
    public static void m4891e() {
        StatisticUtils.m4916a(302, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_CATEGORY_TAB.getValue(), SPage.PAGE_NONE.getValue()).post();
        m4874r();
    }

    /* renamed from: d */
    public static void m4892d(String str) {
        StatisticUtils.m4913a(303, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_CATEGORY_ITEM.getValue(), SPage.PAGE_NONE.getValue()).append("skin_category_name", str).post();
    }

    /* renamed from: e */
    public static void m4890e(String str) {
        StatisticUtils.m4913a(341, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BKG_CATEGORY_ITEM.getValue(), SPage.PAGE_NONE.getValue()).append("background_category_name", str).post();
    }

    /* renamed from: f */
    public static void m4888f(String str) {
        StatisticUtils.m4913a(294, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_UPDATE_SKIN.getValue(), SPage.PAGE_NONE.getValue()).append("skin_name", str).post();
    }

    /* renamed from: g */
    public static void m4886g(String str) {
        StatisticUtils.m4910a("theme", "show", str);
    }

    /* renamed from: f */
    public static void m4889f() {
        File[] m4650a = SkinUtils.m4650a();
        StatisticUtils.m4909a("theme", "show", "update", m4650a != null ? m4650a.length : 0);
    }

    /* renamed from: g */
    public static void m4887g() {
        StatisticUtils.m4910a("theme", "show", "change");
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_CHANGE_SKIN.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: a */
    public static void m4900a(int i) {
        StatisticUtils.m4916a(318, (int) StatisticHelper.DELAY_SEND, i, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_DELETE.getValue(), SPage.PAGE_NONE.getValue()).append("skin_count", Integer.valueOf(i)).post();
    }

    /* renamed from: h */
    public static void m4885h() {
        StatisticUtils.m4916a(308, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BEAUTIFUL_BACKGROUND.getValue(), SPage.PAGE_THEME_BACKGROUND.getValue(), SPage.PAGE_NICE_BACKGROUND.getValue()).post();
        m4874r();
    }

    /* renamed from: i */
    public static void m4883i() {
        StatisticUtils.m4916a(314, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BKG_SET.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: j */
    public static void m4882j() {
        StatisticUtils.m4909a("theme", "show", "background-update", BackgroundListLoader.m3856a().size());
    }

    /* renamed from: k */
    public static void m4881k() {
        StatisticUtils.m4916a(311, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_SELECT_BKG_FROM_GALLERY.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: l */
    public static void m4880l() {
        StatisticUtils.m4916a(312, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_SELECT_BKG_FROM_CAMERA.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: b */
    public static void m4897b(int i) {
        StatisticUtils.m4916a(315, (int) StatisticHelper.DELAY_SEND, i, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BKD_DELETE.getValue(), SPage.PAGE_NONE.getValue()).append("background_count", Integer.valueOf(i)).post();
    }

    /* renamed from: h */
    public static void m4884h(String str) {
        StatisticUtils.m4913a(313, (int) StatisticHelper.DELAY_SEND, str, KVStatisticEvent.ValueOperateMode.UNIQUE);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BKG_DOWNLOAD.getValue(), SPage.PAGE_NONE.getValue()).append("background_name", str).post();
    }

    /* renamed from: m */
    public static void m4879m() {
        StatisticUtils.m4916a(342, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BKG_DOWNLOAD_FROM_CATEGORY.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: n */
    public static void m4878n() {
        String m3038V = Preferences.m3038V();
        String m4644b = SkinUtils.m4644b(StringUtils.m8346a(m3038V) ? Preferences.m3036W() : m3038V);
        EnumC1775b enumC1775b = EnumC1775b.LOCAL;
        if (m4644b == "assets://") {
            enumC1775b = EnumC1775b.INTERNAL;
        }
        StatisticUtils.m4915a(306, (int) StatisticHelper.DELAY_SEND, enumC1775b, KVStatisticEvent.ValueOperateMode.DEFAULT);
        String m8402j = FileUtils.m8402j(m3038V);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_DEFAULT_SKIN_TYPE.getValue(), SPage.PAGE_NONE.getValue()).append("status", Integer.valueOf((enumC1775b != EnumC1775b.INTERNAL || StringUtils.m8346a(m8402j)) ? 0 : Character.digit(m8402j.charAt(0), 10))).post();
    }

    /* renamed from: o */
    public static void m4877o() {
        EnumC1774a enumC1774a = Preferences.m3032Y() ? EnumC1774a.FOLLOW_SKIN : EnumC1774a.LOCAL;
        StatisticUtils.m4915a(307, (int) StatisticHelper.DELAY_SEND, enumC1774a, KVStatisticEvent.ValueOperateMode.DEFAULT);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_DEFAULT_BKG_TYPE.getValue(), SPage.PAGE_NONE.getValue()).append("status", Integer.valueOf(enumC1774a == EnumC1774a.FOLLOW_SKIN ? 0 : 1)).post();
    }

    /* renamed from: p */
    public static void m4876p() {
        /*if (f5620a) {
            f5620a = false;
            m4878n();
            m4877o();
            m4875q();
        }*/
    }

    /* renamed from: q */
    public static void m4875q() {
        File[] m4650a = SkinUtils.m4650a();
        new SUserEvent("PAGE_CLICK", SAction.ACTION_DOWNLOADED_SKIN_COUNT.getValue(), SPage.PAGE_NONE.getValue()).append("skin_count", Integer.valueOf(m4650a == null ? 0 : m4650a.length)).post();
    }

    /* renamed from: r */
    public static void m4874r() {
        StatisticUtils.m4916a(319, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_TAB.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: s */
    public static void m4873s() {
        StatisticUtils.m4916a(321, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_THEME_RECOMMEND_TAB.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: t */
    public static void m4872t() {
        StatisticUtils.m4916a(340, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BKG_CATEGORY.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: u */
    public static void m4871u() {
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BKG_RECOMMEND.getValue(), SPage.PAGE_NONE.getValue()).post();
    }

    /* renamed from: v */
    public static void m4870v() {
        StatisticUtils.m4916a(343, (int) StatisticHelper.DELAY_SEND, 1L, KVStatisticEvent.ValueOperateMode.ADD);
        new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_BACKGROUND_MY_TAB.getValue(), SPage.PAGE_NONE.getValue()).post();
    }
}
