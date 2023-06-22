package com.sds.android.ttpod.framework.modules.p126h;

import com.sds.android.cloudapi.ttpod.data.UnicomFlow;
import com.sds.android.cloudapi.ttpod.p055a.UnicomFlowAPI;
import com.sds.android.cloudapi.ttpod.result.UnicomFlowResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@ObserverCommandID(m4563a = {CommandID.NET_WORK_TYPE_CHANGED})
/* renamed from: com.sds.android.ttpod.framework.modules.h.c */
/* loaded from: classes.dex */
public class UnicomFlowModule extends BaseModule {
    public static final String PASSWORD = "BDAAAD9B739D3B3F";
    public static final String PROXY_HOST = "58.254.132.93";
    public static final String PROXY_WAP_HOST = "10.123.254.46";
    public static final String USERNAME = "3000004550";
    public static final Integer HTTP_PROXY_PORT = 8080;
    public static final Integer TCP_PROXY_PORT = 8143;

    /* renamed from: a */
    private static final String f6291a = UnicomFlowModule.class.getName();

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID mo3239id() {
        return ModuleID.UNICOM_FLOW;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public long timeOutInMills() {
        return 60000;
    }

    public void netWorkTypeChanged() {
        if (UnicomFlowUtil.m3946f() && EnvironmentUtils.C0604c.m8474e()) {
            UnicomFlowStatistic.m4854J();
            new SUserEvent("PAGE_CLICK", 1143, 0).post();
            HttpRequest.m8702b(UnicomFlowUtil.m3945g());
            m3977a(UnicomFlowUtil.m3948d());
            m3976b();
            m3973c();
            checkStatus();
            LogUtils.m8388a(f6291a, "unicom flow save imsi:" + EnvironmentUtils.C0604c.m8481b());
            Cache.m3218a().m3154k(EnvironmentUtils.C0604c.m8481b());
        }
    }

    public void checkUseGprsPopDialog() {
        boolean z = UnicomFlowUtil.m3944h() && ((new Date().getTime() > Cache.m3218a().m3232G().getTime() ? 1 : (new Date().getTime() == Cache.m3218a().m3232G().getTime() ? 0 : -1)) >= 0 && Cache.m3218a().m3234E()) && !UnicomFlowUtil.m3949c();
        LogUtils.m8388a(f6291a, "unicom flow check use popup dialog :" + z);
        if (z) {
            Cache.m3218a().m3186b(false);
            UnicomFlowUtil.m3935q();
            CommandCenter.m4607a().m4604a(new Command(CommandID.UNICOM_FLOW_POPUP_DIALOG, (Cache.m3218a().m3229J() && FlowTrialStatus.UN_TRIAL.ordinal() == Cache.m3218a().m3139y()) ? UnicomFlowDialogType.DIALOG_TRIAL_TYPE : UnicomFlowDialogType.DIALOG_OPEN_TYPE), ModuleID.UNICOM_FLOW);
        }
    }

    public void popupFlowGreaterThan30MDialog() {
        boolean z = UnicomFlowUtil.m3944h() && !UnicomFlowUtil.m3949c() && Cache.m3218a().m3233F();
        LogUtils.m8388a(f6291a, "unicom flow greater than 30M popup dialog :" + z);
        if (z) {
            Cache.m3218a().m3177c(false);
            CommandCenter.m4607a().m4604a(new Command(CommandID.UNICOM_FLOW_POPUP_DIALOG, UnicomFlowDialogType.DIALOG_30M_TYPE), ModuleID.UNICOM_FLOW);
        }
    }

    public void checkBeginMonthPopDialog() {
        boolean z = UnicomFlowUtil.m3957a() && ((new Date().getTime() > Cache.m3218a().m3189b(UnicomFlowUtil.m3937o()).getTime() ? 1 : (new Date().getTime() == Cache.m3218a().m3189b(UnicomFlowUtil.m3937o()).getTime() ? 0 : -1)) >= 0 && Cache.m3218a().m3235D() && UnicomFlowUtil.m3941k()) && Cache.m3218a().m3138z() != UnicomFlowStatus.OPEN.ordinal();
        LogUtils.m8388a(f6291a, "unicom flow checkStatus begin month popup dialog:" + z);
        if (z) {
            Cache.m3218a().m3197a(false);
            UnicomFlowUtil.m3936p();
            CommandCenter.m4607a().m4604a(new Command(CommandID.UNICOM_FLOW_POPUP_DIALOG, UnicomFlowDialogType.DIALOG_MONTH_TYPE), ModuleID.UNICOM_FLOW);
        }
    }

    /* renamed from: b */
    private void m3976b() {
        if (new Date().getTime() <= Preferences.m2920bi().getTime() + 86400000) {
            LogUtils.m8388a(f6291a, "unicom flow already request config:");
            return;
        }
        LogUtils.m8388a(f6291a, "unicom flow request config:");
        UnicomFlowAPI.m8823a().m8544a(new RequestCallback<UnicomFlowResult>() { // from class: com.sds.android.ttpod.framework.modules.h.c.1
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UnicomFlowResult unicomFlowResult) {
                boolean isValidOpen = unicomFlowResult.getUnicomFlow().isValidOpen();
                boolean isTrial = unicomFlowResult.getUnicomFlow().isTrial();
                boolean z = isValidOpen && Cache.m3218a().m3230I();
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow request config success: " + isValidOpen + " isEnable:" + z + "   trial:" + isTrial);
                Preferences.m2931b(new Date());
                Cache.m3218a().m3172d(isValidOpen);
                Cache.m3218a().m3165f(isTrial);
                Cache.m3218a().m3184c(UnicomFlowModule.this.m3979a(unicomFlowResult.getUnicomFlow().getPrice(), 9));
                Cache.m3218a().m3175d(UnicomFlowModule.this.m3979a(unicomFlowResult.getUnicomFlow().getTrialDay(), 3));
                Cache.m3218a().m3166f(unicomFlowResult.getUnicomFlow().getAttention());
                CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_UNICOM_FLOW_STATUS, Boolean.valueOf(z)), ModuleID.UNICOM_FLOW);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow request config fail:");
                CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_UNICOM_FLOW_STATUS, Boolean.valueOf(UnicomFlowUtil.m3957a())), ModuleID.UNICOM_FLOW);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int m3979a(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return i;
        }
    }

    /* renamed from: c */
    private boolean m3973c() {
        String m3223P = Cache.m3218a().m3223P();
        if (StringUtils.m8346a(m3223P) || StringUtils.m8346a(EnvironmentUtils.C0604c.m8481b()) || m3223P.equals(EnvironmentUtils.C0604c.m8481b())) {
            return false;
        }
        Cache.m3218a().m3174d(UnicomFlowUtil.m3939m());
        Cache.m3218a().m3195b(UnicomFlowStatus.UN_OPEN.ordinal());
        Cache.m3218a().m3217a(FlowTrialStatus.UN_TRIAL.ordinal());
        return true;
    }

    public void checkStatus() {
        String m3939m = UnicomFlowUtil.m3939m();
        if (StringUtils.m8346a(m3939m)) {
            m3939m = Cache.m3218a().m3238A();
        }
        if (StringUtils.m8346a(m3939m) && StringUtils.m8346a(EnvironmentUtils.C0604c.m8481b())) {
            LogUtils.m8388a(f6291a, "unicom flow already checkStatus imsi:" + EnvironmentUtils.C0604c.m8481b() + " phone:" + m3939m);
            return;
        }
        LogUtils.m8388a(f6291a, "unicom flow checkStatus");
        UnicomFlowAPI.m8818b(m3939m, EnvironmentUtils.C0604c.m8481b()).m8544a(new RequestCallback<UnicomFlowResult>() { // from class: com.sds.android.ttpod.framework.modules.h.c.2
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UnicomFlowResult unicomFlowResult) {
                Preferences.m3009a(new Date());
                String openTime = unicomFlowResult.getUnicomFlow().getOpenTime();
                String trialTime = unicomFlowResult.getUnicomFlow().getTrialTime();
                String serverTime = unicomFlowResult.getUnicomFlow().getServerTime();
                String unsubscribeTime = unicomFlowResult.getUnicomFlow().getUnsubscribeTime();
                int openStatus = unicomFlowResult.getUnicomFlow().getOpenStatus();
                int trialStatus = unicomFlowResult.getUnicomFlow().getTrialStatus();
                Cache.m3218a().m3195b(openStatus);
                Cache.m3218a().m3217a(trialStatus);
                if (!StringUtils.m8346a(openTime)) {
                    Cache.m3218a().m3160h(openTime);
                }
                if (!StringUtils.m8346a(trialTime)) {
                    Cache.m3218a().m3158i(trialTime);
                }
                if (!StringUtils.m8346a(unsubscribeTime)) {
                    Cache.m3218a().m3156j(unsubscribeTime);
                }
                if (!StringUtils.m8346a(serverTime)) {
                    UnicomFlowModule.this.m3980a(serverTime);
                    Cache.m3218a().m3152l(serverTime);
                }
                boolean isValidOpen = unicomFlowResult.getUnicomFlow().isValidOpen();
                Cache.m3218a().m3168e(isValidOpen);
                boolean z = isValidOpen && Cache.m3218a().m3231H();
                CommandCenter.m4607a().m4604a(new Command(CommandID.UPDATE_UNICOM_FLOW_STATUS, Boolean.valueOf(z)), ModuleID.UNICOM_FLOW);
                boolean m3948d = UnicomFlowUtil.m3948d();
                if (m3948d) {
                    UnicomFlowStatistic.m4853K();
                    new SUserEvent("PAGE_CLICK", 1144, 0).post();
                }
                UnicomFlowModule.this.m3977a(m3948d);
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow checkStatus success openTime:" + openTime + " trialTime:" + trialTime + " unsubscribeTime:" + unsubscribeTime + " openStatus:" + openStatus + " trialStatus:" + trialStatus + " valid:" + isValidOpen + " isEnable:" + z);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow checkStatus failure");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3980a(String str) {
        try {
            LogUtils.m8388a(f6291a, "unicom flow handler current month flow size:" + str);
            String m3222Q = Cache.m3218a().m3222Q();
            if (!StringUtils.m8346a(str) && !StringUtils.m8346a(m3222Q)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int month = simpleDateFormat.parse(str).getMonth();
                int month2 = simpleDateFormat.parse(m3222Q).getMonth();
                if (month != month2) {
                    LogUtils.m8388a(f6291a, "unicom flow handler change month clear flow size currentMonth:" + (month + 1) + "  lastMonth:" + (month2 + 1));
                    CommandCenter.m4607a().m4606a(new Command(CommandID.CLEAR_UNICOM_TOTAL_FLOW, new Object[0]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3977a(boolean z) {
        String str = UnicomFlowUtil.m3947e() ? PROXY_WAP_HOST : PROXY_HOST;
        HttpRequest.m8715a(str, HTTP_PROXY_PORT.intValue(), USERNAME, PASSWORD);
        HttpRequest.m8705a(z);
        LogUtils.m8388a(f6291a, "unicom flow set http proxy host:" + str + " isUseProxy:" + z);
        UnicomProxyData unicomProxyData = new UnicomProxyData();
        unicomProxyData.m3931a(str);
        unicomProxyData.m3932a(TCP_PROXY_PORT.intValue());
        unicomProxyData.m3929b(HTTP_PROXY_PORT.intValue());
        unicomProxyData.m3928b(USERNAME);
        unicomProxyData.m3926c(PASSWORD);
        SupportFactory.m2397a(sContext).m2499a(unicomProxyData, z);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.CHECK_STATUS, ReflectUtils.m8375a(cls, "checkStatus", new Class[0]));
        map.put(CommandID.SEND_VERIFY_CODE, ReflectUtils.m8375a(cls, "sendVerifyCode", String.class, Integer.class));
        map.put(CommandID.OPEN_UNICOM_FLOW, ReflectUtils.m8375a(cls, "open", String.class, String.class, String.class));
        map.put(CommandID.TRIAL_UNICOM_FLOW, ReflectUtils.m8375a(cls, "trial", String.class, String.class));
        map.put(CommandID.UNSUBSCRIBE_UNICOM_FLOW, ReflectUtils.m8375a(cls, "unsubscribe", String.class, Integer.class, String.class, String.class));
        map.put(CommandID.CHECK_USE_GPRS_POPUP_DIALOG, ReflectUtils.m8375a(cls, "checkUseGprsPopDialog", new Class[0]));
        map.put(CommandID.UNICOM_FLOW_30M_DIALOG, ReflectUtils.m8375a(cls, "popupFlowGreaterThan30MDialog", new Class[0]));
        map.put(CommandID.CHECK_BEGIN_MONTH_POPUP_DIALOG, ReflectUtils.m8375a(cls, "checkBeginMonthPopDialog", new Class[0]));
        map.put(CommandID.NET_AUTHORIZE, ReflectUtils.m8375a(cls, "netAuthorize", new Class[0]));
        map.put(CommandID.NET_WORK_TYPE_CHANGED, ReflectUtils.m8375a(cls, "netWorkTypeChanged", new Class[0]));
        map.put(CommandID.SAVE_UNICOM_TOTAL_FLOW, ReflectUtils.m8375a(cls, "saveTotalFlow", new Class[0]));
        map.put(CommandID.CLEAR_UNICOM_TOTAL_FLOW, ReflectUtils.m8375a(cls, "clearTotalFlow", new Class[0]));
        map.put(CommandID.GET_UNICOM_TOTAL_FLOW, ReflectUtils.m8375a(cls, "getTotalFlow", new Class[0]));
    }

    public void open(final String str, String str2, String str3) {
        UnicomFlowAPI.m8819a(str, str2, str3, EnvironmentUtils.C0604c.m8481b()).m8544a(new RequestCallback<UnicomFlowResult>() { // from class: com.sds.android.ttpod.framework.modules.h.c.3
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UnicomFlowResult unicomFlowResult) {
                CommandCenter.m4607a().m4604a(new Command(CommandID.OPEN_UNICOM_FLOW_RESULT, new CommonResult(ErrCode.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow())), ModuleID.UNICOM_FLOW);
                UnicomFlowModule.this.m3978a(str, unicomFlowResult.getUnicomFlow());
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UnicomFlowResult unicomFlowResult) {
                ErrCode errCode = unicomFlowResult.getCode() == 2 ? ErrCode.ErrAlreadyExists : ErrCode.ErrGeneral;
                if (ErrCode.ErrAlreadyExists == errCode) {
                    UnicomFlowModule.this.m3978a(str, unicomFlowResult.getUnicomFlow());
                }
                CommandCenter.m4607a().m4604a(new Command(CommandID.OPEN_UNICOM_FLOW_RESULT, new CommonResult(errCode, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow())), ModuleID.UNICOM_FLOW);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3978a(String str, UnicomFlow unicomFlow) {
        LogUtils.m8388a(f6291a, "unicom flow set open status");
        Cache.m3218a().m3174d(str);
        Cache.m3218a().m3154k(EnvironmentUtils.C0604c.m8481b());
        String openTime = unicomFlow.getOpenTime();
        if (!StringUtils.m8346a(openTime)) {
            Cache.m3218a().m3160h(openTime);
        }
        Cache.m3218a().m3195b(UnicomFlowStatus.OPEN.ordinal());
        Cache.m3218a().m3202a(UnicomFlowUtil.m3937o());
        CommandCenter.m4607a().m4606a(new Command(CommandID.CLEAR_UNICOM_TOTAL_FLOW, new Object[0]));
        m3977a(UnicomFlowUtil.m3948d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m3974b(String str, UnicomFlow unicomFlow) {
        LogUtils.m8388a(f6291a, "unicom flow set trial status");
        Cache.m3218a().m3174d(str);
        Cache.m3218a().m3154k(EnvironmentUtils.C0604c.m8481b());
        String trialTime = unicomFlow.getTrialTime();
        if (!StringUtils.m8346a(trialTime)) {
            Cache.m3218a().m3158i(trialTime);
        }
        Cache.m3218a().m3217a(FlowTrialStatus.TRIAL.ordinal());
        m3977a(UnicomFlowUtil.m3948d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3986a(UnicomFlow unicomFlow) {
        LogUtils.m8388a(f6291a, "unicom flow set unsubscribe status");
        String unsubscribeTime = unicomFlow.getUnsubscribeTime();
        if (!StringUtils.m8346a(unsubscribeTime)) {
            Cache.m3218a().m3156j(unsubscribeTime);
        }
        Cache.m3218a().m3195b(UnicomFlowStatus.UNSUBSCRIBE.ordinal());
        m3977a(UnicomFlowUtil.m3948d());
    }

    public void trial(final String str, String str2) {
        UnicomFlowAPI.m8820a(str, str2).m8544a(new RequestCallback<UnicomFlowResult>() { // from class: com.sds.android.ttpod.framework.modules.h.c.4
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow trial sucess");
                CommonResult commonResult = new CommonResult(ErrCode.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                UnicomFlowModule.this.m3974b(str, unicomFlowResult.getUnicomFlow());
                CommandCenter.m4607a().m4604a(new Command(CommandID.TRIAL_UNICOM_FLOW_RESULT, commonResult), ModuleID.UNICOM_FLOW);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow trial fail");
                ErrCode errCode = ErrCode.ErrGeneral;
                if (unicomFlowResult.getCode() == 2) {
                    UnicomFlowModule.this.m3974b(str, unicomFlowResult.getUnicomFlow());
                    errCode = ErrCode.ErrAlreadyExists;
                }
                CommandCenter.m4607a().m4604a(new Command(CommandID.TRIAL_UNICOM_FLOW_RESULT, new CommonResult(errCode, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow())), ModuleID.UNICOM_FLOW);
            }
        });
    }

    public void sendVerifyCode(String str, Integer num) {
        UnicomFlowAPI.m8822a(str, num.intValue()).m8544a(new RequestCallback<UnicomFlowResult>() { // from class: com.sds.android.ttpod.framework.modules.h.c.5
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow sendVerifyCode sucess");
                CommandCenter.m4607a().m4604a(new Command(CommandID.SEND_VERIFY_CODE_RESULT, new CommonResult(ErrCode.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow())), ModuleID.UNICOM_FLOW);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow sendVerifyCode fail");
                CommandCenter.m4607a().m4604a(new Command(CommandID.SEND_VERIFY_CODE_RESULT, new CommonResult(ErrCode.ErrGeneral, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow())), ModuleID.UNICOM_FLOW);
            }
        });
    }

    public void unsubscribe(String str, Integer num, String str2, String str3) {
        UnicomFlowAPI.m8821a(str, num.intValue(), str2, str3).m8544a(new RequestCallback<UnicomFlowResult>() { // from class: com.sds.android.ttpod.framework.modules.h.c.6
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow unsubscribe success");
                UnicomFlowModule.this.m3986a(unicomFlowResult.getUnicomFlow());
                CommandCenter.m4607a().m4604a(new Command(CommandID.UNSUBSCRIBE_UNICOM_FLOW_RESULT, new CommonResult(ErrCode.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow())), ModuleID.UNICOM_FLOW);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow unsubscribe fail");
                ErrCode errCode = unicomFlowResult.getCode() == 2 ? ErrCode.ErrAlreadyExists : ErrCode.ErrGeneral;
                if (ErrCode.ErrAlreadyExists == errCode) {
                    UnicomFlowModule.this.m3986a(unicomFlowResult.getUnicomFlow());
                }
                CommandCenter.m4607a().m4604a(new Command(CommandID.UNSUBSCRIBE_UNICOM_FLOW_RESULT, new CommonResult(errCode, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow())), ModuleID.UNICOM_FLOW);
            }
        });
    }

    public void netAuthorize() {
        UnicomFlowAPI.m8817c(m3972d(), EnvironmentUtils.C0604c.m8481b()).m8544a(new RequestCallback<UnicomFlowResult>() { // from class: com.sds.android.ttpod.framework.modules.h.c.7
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow net auth success");
                CommonResult commonResult = new CommonResult(ErrCode.ErrNone, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow());
                String phone = unicomFlowResult.getUnicomFlow().getPhone();
                String token = unicomFlowResult.getUnicomFlow().getToken();
                if (!StringUtils.m8346a(phone)) {
                    Cache.m3218a().m3174d(phone);
                }
                if (!StringUtils.m8346a(token)) {
                    Cache.m3218a().m3170e(token);
                }
                CommandCenter.m4607a().m4604a(new Command(CommandID.NET_AUTHORIZE_RESULT, commonResult), ModuleID.UNICOM_FLOW);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UnicomFlowResult unicomFlowResult) {
                LogUtils.m8388a(UnicomFlowModule.f6291a, "unicom flow net auth fail");
                CommandCenter.m4607a().m4604a(new Command(CommandID.NET_AUTHORIZE_RESULT, new CommonResult(ErrCode.ErrGeneral, unicomFlowResult.getMessage(), unicomFlowResult.getUnicomFlow())), ModuleID.UNICOM_FLOW);
            }
        });
    }

    public void saveTotalFlow() {
        if (HttpRequest.m8701c()) {
            long m2453w = SupportFactory.m2397a(sContext).m2453w() + HttpRequest.m8718a() + Cache.m3218a().m3227L();
            LogUtils.m8388a(f6291a, "unicom flow save total flow size:" + m2453w);
            HttpRequest.m8716a(0L);
            SupportFactory.m2397a(sContext).m2503a(0L);
            if (HttpRequest.m8704b()) {
                Cache.m3218a().m3183c(m2453w);
            } else {
                Cache.m3218a().m3194b(m2453w);
            }
        }
    }

    public void clearTotalFlow() {
        LogUtils.m8388a(f6291a, "unicom flow clear total flow size:");
        SupportFactory.m2397a(sContext).m2503a(0L);
        HttpRequest.m8716a(0L);
        Cache.m3218a().m3183c(0L);
        Cache.m3218a().m3194b(0L);
    }

    public void getTotalFlow() {
        long m2453w = SupportFactory.m2397a(sContext).m2453w();
        long m8718a = HttpRequest.m8718a();
        long m3227L = Cache.m3218a().m3227L();
        LogUtils.m8388a(f6291a, "unicom flow get Total supportFlow:" + m2453w + " httpFlow:" + m8718a + " cacheFlow:" + m3227L);
        UnicomFlowStatistic.m4846a(m2453w + m8718a);
        CommandCenter.m4607a().m4604a(new Command(CommandID.GET_UNICOM_TOTAL_FLOW_RESULT, new Long(m2453w + m8718a + m3227L)), ModuleID.UNICOM_FLOW);
    }

    /* renamed from: d */
    private String m3972d() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
