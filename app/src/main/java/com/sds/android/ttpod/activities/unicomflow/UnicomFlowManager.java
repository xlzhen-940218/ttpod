package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.p088a.WaitingDialog;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowDialogType;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowModule;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.activities.unicomflow.b */
/* loaded from: classes.dex */
public class UnicomFlowManager {

    /* renamed from: b */
    private static WaitingDialog f3055b;

    /* renamed from: d */
    private static UnicomDialog f3057d;

    /* renamed from: a */
    private static final String f3054a = UnicomFlowManager.class.getSimpleName();

    /* renamed from: c */
    private static List<Activity> f3056c = new ArrayList();

    /* renamed from: a */
    public static void m7759a(Context context, String str) {
        f3055b = new WaitingDialog(context);
        f3055b.m6775a((CharSequence) str);
        f3055b.show();
    }

    /* renamed from: a */
    public static void m7766a() {
        if (f3055b != null) {
            f3055b.dismiss();
        }
    }

    /* renamed from: a */
    public static void m7758a(Context context, String str, int i, int i2, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a, int i3, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a2, View.OnClickListener onClickListener) {
        UnicomDialog unicomDialog = new UnicomDialog(context, str, i2, interfaceC1064a, i3, interfaceC1064a2, onClickListener);
        unicomDialog.setTitle(R.string.unicom_dialog_month_end_title);
        unicomDialog.show();
    }

    /* renamed from: a */
    public static void m7765a(final Activity activity) {
        f3057d = new UnicomDialog(activity, activity.getString(R.string.unicom_flow_trial_message), activity.getString(R.string.unicom_flow_trial_title), (int) R.string.unicom_dialog_button_trial, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog) {
                UnicomFlowStatistic.m4841b();
                unicomDialog.dismiss();
                UnicomFlowManager.m7762a(activity, TrialActivity.class, (Enum) UnicomFlowStatistic.EnumC1779c.TRIAL_DIALOG, false);
            }
        }, (int) R.string.unicom_dialog_button_not_need, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.4
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog) {
                unicomDialog.dismiss();
                UnicomFlowStatistic.m4831d();
            }
        }, new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.unicomflow.b.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UnicomFlowManager.f3057d.dismiss();
                UnicomFlowStatistic.m4836c();
                activity.startActivity(new Intent(activity, FaqActivity.class));
            }
        });
        f3057d.setTitle(R.string.unicom_trial_dialog_title);
        f3057d.setCanceledOnTouchOutside(false);
        f3057d.show();
        UnicomFlowStatistic.m4847a();
    }

    /* renamed from: a */
    public static void m7763a(final Activity activity, final UnicomFlowDialogType unicomFlowDialogType) {
        UnicomDialog unicomDialog = new UnicomDialog(activity, activity.getString(R.string.unicom_flow_open_message), R.string.unicom_dialog_detail, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.6
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog2) {
                unicomDialog2.dismiss();
                UnicomFlowManager.m7762a(activity, OrderFlowDetailActivity.class, (Enum) UnicomFlowStatistic.EnumC1778b.POP_OPEN_DIALOG, false);
                UnicomFlowStatistic.m4837b(unicomFlowDialogType);
                new SUserEvent("PAGE_CLICK", 1124, 700).post();
            }
        }, R.string.unicom_dialog_know, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.7
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog2) {
                unicomDialog2.dismiss();
                UnicomFlowStatistic.m4832c(unicomFlowDialogType);
                new SUserEvent("PAGE_CLICK", 1125, 0).post();
            }
        }, null);
        unicomDialog.setTitle(R.string.unicom_dialog_title);
        unicomDialog.setCanceledOnTouchOutside(false);
        unicomDialog.show();
        UnicomFlowStatistic.m4842a(unicomFlowDialogType);
        new SUserEvent("PAGE_CLICK", 1123, 0).post();
    }

    /* renamed from: b */
    public static void m7754b(final Activity activity, final UnicomFlowDialogType unicomFlowDialogType) {
        UnicomDialog unicomDialog = new UnicomDialog(activity, activity.getString(R.string.unicom_flow_month_message), R.string.unicom_dialog_detail, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.8
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog2) {
                unicomDialog2.dismiss();
                UnicomFlowManager.m7762a(activity, OrderFlowDetailActivity.class, (Enum) UnicomFlowStatistic.EnumC1778b.POP_OPEN_DIALOG, false);
                UnicomFlowStatistic.m4837b(unicomFlowDialogType);
                new SUserEvent("PAGE_CLICK", 1134, 700).post();
            }
        }, R.string.unicom_dialog_know, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.9
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog2) {
                unicomDialog2.dismiss();
                new SUserEvent("PAGE_CLICK", 1135, 0).post();
                UnicomFlowStatistic.m4832c(unicomFlowDialogType);
            }
        }, null);
        unicomDialog.setTitle(R.string.unicom_dialog_title);
        unicomDialog.setCanceledOnTouchOutside(false);
        unicomDialog.show();
        UnicomFlowStatistic.m4842a(unicomFlowDialogType);
        new SUserEvent("PAGE_CLICK", 1133, 0).post();
    }

    /* renamed from: c */
    public static void m7752c(final Activity activity, final UnicomFlowDialogType unicomFlowDialogType) {
        UnicomDialog unicomDialog = new UnicomDialog(activity, activity.getString(R.string.unicom_flow_use_dialog, new Object[]{Preferences.m2917bl() + "MB"}), R.string.unicom_dialog_detail, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.10
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog2) {
                UnicomFlowStatistic.m4825f();
                unicomDialog2.dismiss();
                UnicomFlowManager.m7762a(activity, OrderFlowDetailActivity.class, (Enum) UnicomFlowStatistic.EnumC1778b.POP_OPEN_DIALOG, false);
                UnicomFlowStatistic.m4837b(unicomFlowDialogType);
                new SUserEvent("PAGE_CLICK", 1131, 700).post();
            }
        }, R.string.unicom_dialog_know, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.11
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog2) {
                UnicomFlowStatistic.m4822g();
                new SUserEvent("PAGE_CLICK", 1132, 0).post();
                unicomDialog2.dismiss();
            }
        }, null);
        unicomDialog.setTitle(R.string.unicom_dialog_title);
        unicomDialog.setCanceledOnTouchOutside(false);
        unicomDialog.show();
        UnicomFlowStatistic.m4828e();
        new SUserEvent("PAGE_CLICK", 1130, 0).post();
    }

    /* renamed from: a */
    public static void m7757a(Context context, String str, String str2, int i, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a, int i2, BaseDialog.InterfaceC1064a<UnicomDialog> interfaceC1064a2, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        UnicomDialog unicomDialog = new UnicomDialog(context, str, i, interfaceC1064a, i2, interfaceC1064a2, true, onCheckedChangeListener);
        unicomDialog.setTitle(str2);
        unicomDialog.show();
    }

    /* renamed from: a */
    public static void m7756a(final UnicomFlowActivity unicomFlowActivity, UnicomFlowStatistic.EnumC1777a enumC1777a) {
        UnicomFlowStatistic.m4845a(enumC1777a);
        m7758a(unicomFlowActivity, unicomFlowActivity.getString(R.string.unicom_dialog_month_end_content, new Object[]{Integer.valueOf(UnicomFlowUtil.m3943i())}), (int) R.string.unicom_dialog_month_end_title, (int) R.string.unicom_dialog_month_end_button_open, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog) {
                UnicomFlowStatistic.m4840b(UnicomFlowStatistic.EnumC1777a.TRIAL_DETAIL);
                unicomDialog.dismiss();
                unicomFlowActivity.startOpenActivity(UnicomFlowStatistic.EnumC1778b.POP_MONTH_DIALOG, false);
            }
        }, (int) R.string.unicom_dialog_month_end_button_alert, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.activities.unicomflow.b.3
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog) {
                UnicomFlowStatistic.m4835c(UnicomFlowStatistic.EnumC1777a.TRIAL_DETAIL);
                Cache.m3218a().m3197a(true);
                unicomDialog.dismiss();
                unicomFlowActivity.finish();
            }
        }, (View.OnClickListener) null);
    }

    /* renamed from: a */
    public static void m7762a(Activity activity, Class cls, Enum r3, boolean z) {
        m7764a(activity, new Intent(activity, cls), r3, z);
    }

    /* renamed from: a */
    public static void m7764a(Activity activity, Intent intent, Enum r3, boolean z) {
        if (r3 != null) {
            intent.putExtra("origin_type", r3);
        }
        activity.startActivity(intent);
        if (z) {
            activity.finish();
        }
        f3056c.add(activity);
    }

    /* renamed from: b */
    public static void m7755b() {
        for (Activity activity : f3056c) {
            if (activity != null && !activity.isFinishing() && !(activity instanceof MainActivity)) {
                activity.finish();
            }
        }
        f3056c.clear();
    }

    /* renamed from: a */
    public static <T> T m7761a(Activity activity, T t) {
        Bundle extras = activity.getIntent().getExtras();
        Object obj = null;
        if (extras != null) {
            obj = extras.get("origin_type");
        }
        return obj == null ? t : (T) obj;
    }

    /* renamed from: a */
    public static void m7760a(Context context) {
        if (WebViewProxy.m7740a()) {
            if (HttpRequest.m8704b()) {
                WebViewProxy.m7738a(context, UnicomFlowModule.PROXY_HOST, UnicomFlowModule.HTTP_PROXY_PORT.intValue());
            } else {
                WebViewProxy.m7733b(context);
            }
        }
    }

    /* renamed from: d */
    public static void m7751d(Activity activity, UnicomFlowDialogType unicomFlowDialogType) {
        if (UnicomFlowDialogType.DIALOG_TRIAL_TYPE == unicomFlowDialogType) {
            m7765a(activity);
        } else if (UnicomFlowDialogType.DIALOG_OPEN_TYPE == unicomFlowDialogType) {
            m7763a(activity, unicomFlowDialogType);
        } else if (UnicomFlowDialogType.DIALOG_MONTH_TYPE == unicomFlowDialogType) {
            m7754b(activity, unicomFlowDialogType);
        } else if (UnicomFlowDialogType.DIALOG_30M_TYPE == unicomFlowDialogType) {
            m7752c(activity, unicomFlowDialogType);
        }
    }
}
