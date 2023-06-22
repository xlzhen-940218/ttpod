package com.sds.android.ttpod.framework.p106a.p107a;


import com.sds.android.sdk.core.statistic.KVStatisticEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowDialogType;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.a.a.w */
/* loaded from: classes.dex */
public class UnicomFlowStatistic {

    /* compiled from: UnicomFlowStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.w$a */
    /* loaded from: classes.dex */
    public enum EnumC1777a implements Serializable {
        TRIAL_DETAIL,
        ORDER_DETAIL
    }

    /* compiled from: UnicomFlowStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.w$b */
    /* loaded from: classes.dex */
    public enum EnumC1778b implements Serializable {
        POP_OPEN_DIALOG,
        POP_MONTH_DIALOG,
        ORDER_DETAIL,
        UNSUBSCRIBE_DETAIL,
        TRIAL_DETAIL,
        OPEN_AUTHORIZE
    }

    /* compiled from: UnicomFlowStatistic.java */
    /* renamed from: com.sds.android.ttpod.framework.a.a.w$c */
    /* loaded from: classes.dex */
    public enum EnumC1779c implements Serializable {
        TRIAL_DIALOG,
        ORDER_DETAIL
    }

    /* renamed from: a */
    public static void m4847a() {
        StatisticUtils.m4917a(178, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: b */
    public static void m4841b() {
        StatisticUtils.m4917a(180, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: c */
    public static void m4836c() {
        StatisticUtils.m4917a(179, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: d */
    public static void m4831d() {
        StatisticUtils.m4917a(181, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: a */
    public static void m4842a(UnicomFlowDialogType unicomFlowDialogType) {
        StatisticUtils.m4915a(182, (int) StatisticHelper.DELAY_SEND, unicomFlowDialogType, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: e */
    public static void m4828e() {
        StatisticUtils.m4917a(373, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: f */
    public static void m4825f() {
        StatisticUtils.m4917a(374, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: g */
    public static void m4822g() {
        StatisticUtils.m4917a(375, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: h */
    public static void m4819h() {
        StatisticUtils.m4917a(376, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: i */
    public static void m4818i() {
        StatisticUtils.m4917a(377, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: b */
    public static void m4837b(UnicomFlowDialogType unicomFlowDialogType) {
        StatisticUtils.m4915a(183, (int) StatisticHelper.DELAY_SEND, unicomFlowDialogType, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: c */
    public static void m4832c(UnicomFlowDialogType unicomFlowDialogType) {
        StatisticUtils.m4915a(184, (int) StatisticHelper.DELAY_SEND, unicomFlowDialogType, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: j */
    public static void m4817j() {
        StatisticUtils.m4917a(185, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: k */
    public static void m4816k() {
        StatisticUtils.m4917a(371, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: l */
    public static void m4815l() {
        StatisticUtils.m4913a(372, (int) StatisticHelper.DELAY_SEND, "close_home_unicom_entry", KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: m */
    public static void m4814m() {
        StatisticUtils.m4917a(370, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: n */
    public static void m4813n() {
        StatisticUtils.m4917a(187, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: o */
    public static void m4812o() {
        StatisticUtils.m4917a(188, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: p */
    public static void m4811p() {
        StatisticUtils.m4917a(189, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: q */
    public static void m4810q() {
        StatisticUtils.m4917a(186, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: a */
    public static void m4843a(EnumC1779c enumC1779c) {
        StatisticUtils.m4915a(190, (int) StatisticHelper.DELAY_SEND, enumC1779c, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: b */
    public static void m4838b(EnumC1779c enumC1779c) {
        StatisticUtils.m4915a(191, (int) StatisticHelper.DELAY_SEND, enumC1779c, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: c */
    public static void m4833c(EnumC1779c enumC1779c) {
        StatisticUtils.m4915a((int) 192, (int) StatisticHelper.DELAY_SEND, enumC1779c, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: d */
    public static void m4829d(EnumC1779c enumC1779c) {
        StatisticUtils.m4915a((int) 193, (int) StatisticHelper.DELAY_SEND, enumC1779c, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: e */
    public static void m4826e(EnumC1779c enumC1779c) {
        StatisticUtils.m4915a(194, (int) StatisticHelper.DELAY_SEND, enumC1779c, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: f */
    public static void m4823f(EnumC1779c enumC1779c) {
        StatisticUtils.m4915a(195, (int) StatisticHelper.DELAY_SEND, enumC1779c, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: g */
    public static void m4820g(EnumC1779c enumC1779c) {
        StatisticUtils.m4915a(196, (int) StatisticHelper.DELAY_SEND, enumC1779c, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: a */
    public static void m4845a(EnumC1777a enumC1777a) {
        StatisticUtils.m4915a(197, (int) StatisticHelper.DELAY_SEND, enumC1777a, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: b */
    public static void m4840b(EnumC1777a enumC1777a) {
        StatisticUtils.m4915a(198, (int) StatisticHelper.DELAY_SEND, enumC1777a, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: c */
    public static void m4835c(EnumC1777a enumC1777a) {
        StatisticUtils.m4915a(199, (int) StatisticHelper.DELAY_SEND, enumC1777a, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: a */
    public static void m4844a(EnumC1778b enumC1778b) {
        StatisticUtils.m4915a(200, (int) StatisticHelper.DELAY_SEND, enumC1778b, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: b */
    public static void m4839b(EnumC1778b enumC1778b) {
        StatisticUtils.m4915a(201, (int) StatisticHelper.DELAY_SEND, enumC1778b, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: c */
    public static void m4834c(EnumC1778b enumC1778b) {
        StatisticUtils.m4915a(202, (int) StatisticHelper.DELAY_SEND, enumC1778b, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: d */
    public static void m4830d(EnumC1778b enumC1778b) {
        StatisticUtils.m4915a(203, (int) StatisticHelper.DELAY_SEND, enumC1778b, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: e */
    public static void m4827e(EnumC1778b enumC1778b) {
        StatisticUtils.m4915a(204, (int) StatisticHelper.DELAY_SEND, enumC1778b, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: f */
    public static void m4824f(EnumC1778b enumC1778b) {
        StatisticUtils.m4915a(329, (int) StatisticHelper.DELAY_SEND, enumC1778b, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: g */
    public static void m4821g(EnumC1778b enumC1778b) {
        StatisticUtils.m4915a(205, (int) StatisticHelper.DELAY_SEND, enumC1778b, KVStatisticEvent.ValueOperateMode.UNIQUE);
    }

    /* renamed from: r */
    public static void m4809r() {
        StatisticUtils.m4917a(206, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: s */
    public static void m4808s() {
        StatisticUtils.m4917a(207, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: t */
    public static void m4807t() {
        StatisticUtils.m4917a(208, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: u */
    public static void m4806u() {
        StatisticUtils.m4917a(209, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: v */
    public static void m4805v() {
        StatisticUtils.m4917a(210, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: w */
    public static void m4804w() {
        StatisticUtils.m4917a(211, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: x */
    public static void m4803x() {
        StatisticUtils.m4917a(212, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: y */
    public static void m4802y() {
        StatisticUtils.m4917a(286, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: z */
    public static void m4801z() {
        StatisticUtils.m4917a(287, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: A */
    public static void m4863A() {
        StatisticUtils.m4917a(288, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: B */
    public static void m4862B() {
        StatisticUtils.m4917a(325, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: C */
    public static void m4861C() {
        StatisticUtils.m4917a(290, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: D */
    public static void m4860D() {
        StatisticUtils.m4917a(291, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: E */
    public static void m4859E() {
        StatisticUtils.m4917a(292, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: F */
    public static void m4858F() {
        StatisticUtils.m4917a(293, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: G */
    public static void m4857G() {
        StatisticUtils.m4917a(326, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: H */
    public static void m4856H() {
        StatisticUtils.m4917a(328, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: I */
    public static void m4855I() {
        StatisticUtils.m4917a(327, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: J */
    public static void m4854J() {
        StatisticUtils.m4917a(331, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: K */
    public static void m4853K() {
        StatisticUtils.m4917a(332, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: L */
    public static void m4852L() {
        StatisticUtils.m4917a(336, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: M */
    public static void m4851M() {
        StatisticUtils.m4917a(337, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: N */
    public static void m4850N() {
        StatisticUtils.m4917a(338, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: O */
    public static void m4849O() {
        StatisticUtils.m4917a(334, (int) StatisticHelper.DELAY_SEND, 1L);
    }

    /* renamed from: a */
    public static void m4846a(long j) {
        StatisticUtils.m4916a(333, (int) StatisticHelper.DELAY_SEND, j, KVStatisticEvent.ValueOperateMode.ADD);
    }

    /* renamed from: P */
    public static void m4848P() {
        StatisticUtils.m4917a(335, (int) StatisticHelper.DELAY_SEND, 1L);
    }
}
