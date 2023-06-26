package com.sds.android.ttpod.framework.modules.core.p120f;


import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.p055a.UserSystemAPI;
import com.sds.android.cloudapi.ttpod.result.PostFileResult;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.cloudapi.ttpod.result.UserResult;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.core.f.a */
/* loaded from: classes.dex */
public final class UserSystemModule extends BaseModule {

    /* renamed from: a */
    private Request f6031a;

    /* renamed from: b */
    private Request f6032b;

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.USER_SYSTEM;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public long timeOutInMills() {
        return 15000L;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.RESISTER, ReflectUtils.m8375a(cls, "register", String.class, String.class, String.class));
        map.put(CommandID.LOGIN, ReflectUtils.m8375a(cls, "login", String.class, String.class));
        map.put(CommandID.QQ_LOGIN, ReflectUtils.m8375a(cls, "qqLogin", String.class, String.class, String.class));
        map.put(CommandID.SINA_LOGIN, ReflectUtils.m8375a(cls, "sinaLogin", String.class, String.class, String.class));
        map.put(CommandID.LOGOUT, ReflectUtils.m8375a(cls, "logout", new Class[0]));
        map.put(CommandID.FIND_PASSWORD, ReflectUtils.m8375a(cls, "findPassword", String.class));
        map.put(CommandID.MODIFY_NICKNAME, ReflectUtils.m8375a(cls, "modifyNickName", String.class));
        map.put(CommandID.MODIFY_USER_EMAIL, ReflectUtils.m8375a(cls, "modifyUserMail", String.class, String.class));
        map.put(CommandID.BIND_USER_EMAIL, ReflectUtils.m8375a(cls, "bindUserMail", String.class, String.class));
        map.put(CommandID.MODIFY_PASSWORD, ReflectUtils.m8375a(cls, "modifyPassword", String.class, String.class));
        map.put(CommandID.MODIFY_COVER, ReflectUtils.m8375a(cls, "modifyCover", String.class, Integer.class, Integer.class));
        map.put(CommandID.MODIFY_AVATAR, ReflectUtils.m8375a(cls, "modifyAvatar", String.class, Integer.class, Integer.class));
        map.put(CommandID.MODIFY_SEX_BIRTHDAY, ReflectUtils.m8375a(cls, "modifySexAndBirthday", TTPodUser.class, Boolean.class, Boolean.class));
        map.put(CommandID.REFRESH_INFORMATION, ReflectUtils.m8375a(cls, "refreshInformation", new Class[0]));
        map.put(CommandID.GET_USER_INFO_BY_ID, ReflectUtils.m8375a(cls, "getUserInfoById", String.class, Long.class));
    }

    public void register(String str, String str2, String str3) {
        final Request<UserResult> m8943c = UserSystemAPI.m8943c(str, str2, str3);
        m8943c.m8544a(new RequestCallback<UserResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.1
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UserResult userResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.RESISTER_FINISHED, new CommonResult(ErrCode.ErrNone, "register ok", userResult)), ModuleID.USER_SYSTEM);
                //UTAnalytics.getInstance().userRegister(userResult.getData().getNickName());
                UserSystemModule.this.m4167a(userResult);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UserResult userResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.RESISTER_FINISHED, new CommonResult(ErrCode.ErrGeneral, "register fail", userResult)), ModuleID.USER_SYSTEM);
                //ErrorStatistic.m5234e(m8943c.m8532e());
                //ErrorStatistic.m5239a("user", m8943c.m8532e());
            }
        });
    }

    public void login(final String str, final String str2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.7
            @Override // java.lang.Runnable
            public void run() {
                BaseResult m8531f = UserSystemAPI.m8950b(str).m8531f();
                if (m8531f.getCode() != 30307) {
                    if (m8531f.getCode() == -1) {
                        CommandCenter.getInstance().m4595b(new Command(CommandID.LOGIN_FINISHED, new CommonResult(ErrCode.ErrPathNotFound, m8531f.getMessage())), ModuleID.USER_SYSTEM);
                        return;
                    } else {
                        CommandCenter.getInstance().m4595b(new Command(CommandID.LOGIN_FINISHED, new CommonResult(ErrCode.ErrNotFound)), ModuleID.USER_SYSTEM);
                        return;
                    }
                }
                UserResult m8531f2 = UserSystemAPI.m8952a(str, str2).m8531f();
                if (m8531f2.isSuccess()) {
                    UserSystemModule.this.m4167a(m8531f2);
                } else {
                    UserSystemModule.this.m4164b(m8531f2);
                }
            }
        });
    }

    public void qqLogin(String str, String str2, String str3) {
        final Request<UserResult> m8951a = UserSystemAPI.m8951a(str, str2, str3);
        m8951a.m8544a(new RequestCallback<UserResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.8
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestFailure(UserResult userResult) {
                UserSystemModule.this.m4164b(userResult);
                //ErrorStatistic.m5234e(m8951a.m8532e());
                //ErrorStatistic.m5239a("user", m8951a.m8532e());
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestSuccess(UserResult userResult) {
                UserSystemModule.this.m4167a(userResult);
            }
        });
    }

    public void sinaLogin(String str, String str2, String str3) {
        final Request<UserResult> m8946b = UserSystemAPI.m8946b(str, str2, str3);
        m8946b.m8544a(new RequestCallback<UserResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.9
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(UserResult userResult) {
                UserSystemModule.this.m4167a(userResult);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(UserResult userResult) {
                UserSystemModule.this.m4164b(userResult);
                //ErrorStatistic.m5234e(m8946b.m8532e());
                //ErrorStatistic.m5239a("user", m8946b.m8532e());
            }
        });
    }

    public void findPassword(String str) {
        final Request<BaseResult> m8945c = UserSystemAPI.m8945c(str);
        m8945c.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.10
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestSuccess(BaseResult baseResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.FIND_PASSWORD_FINISHED, new CommonResult(ErrCode.ErrNone)), ModuleID.USER_SYSTEM);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestFailure(BaseResult baseResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.FIND_PASSWORD_FINISHED, new CommonResult(ErrCode.ErrGeneral)), ModuleID.USER_SYSTEM);
                //ErrorStatistic.m5234e(m8945c.m8532e());
                //ErrorStatistic.m5239a("user", m8945c.m8532e());
            }
        });
    }

    public void logout() {
        //UTAnalytics.getInstance().updateUserAccount("", "");
        Preferences.m3022a((User) null);
        EnvironmentUtils.C0603b.m8498a(0L);
        CommandCenter.getInstance().m4604a(new Command(CommandID.LOGOUT_FINISHED, new Object[0]), ModuleID.USER_SYSTEM);
    }

    public void modifyNickName(final String str) {
        final TTPodUser m2954aq = Preferences.m2954aq();
        final Request<BaseResult> m8944c = UserSystemAPI.m8944c(m2954aq.getAccessToken(), str);
        m8944c.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.11
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestSuccess(BaseResult baseResult) {
                if (Preferences.m2954aq() != null) {
                    m2954aq.setNickName(str);
                    Preferences.m3022a(m2954aq);
                    CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_NICKNAME_FINISHED, new CommonResult(ErrCode.ErrNone, str, baseResult)), ModuleID.USER_SYSTEM);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestFailure(BaseResult baseResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_NICKNAME_FINISHED, new CommonResult(ErrCode.ErrGeneral, str, baseResult)), ModuleID.USER_SYSTEM);
                //ErrorStatistic.m5234e(m8944c.m8532e());
                //ErrorStatistic.m5239a("user", m8944c.m8532e());
            }
        });
    }

    public void bindUserMail(final String str, String str2) {
        final TTPodUser m2954aq = Preferences.m2954aq();
        final Request<BaseResult> m8941d = UserSystemAPI.m8941d(m2954aq.getAccessToken(), str, str2);
        m8941d.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.12
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestSuccess(BaseResult baseResult) {
                if (Preferences.m2954aq() != null) {
                    m2954aq.setUserName(str);
                    Preferences.m3022a(m2954aq);
                    CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_USER_EMAIL_FINISHED, new CommonResult(ErrCode.ErrNone, str, baseResult)), ModuleID.USER_SYSTEM);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestFailure(BaseResult baseResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_USER_EMAIL_FINISHED, new CommonResult(ErrCode.ErrGeneral, str, baseResult)), ModuleID.USER_SYSTEM);
                //ErrorStatistic.m5234e(m8941d.m8532e());
                //ErrorStatistic.m5239a("user", m8941d.m8532e());
            }
        });
    }

    public void modifyUserMail(final String str, String str2) {
        final TTPodUser m2954aq = Preferences.m2954aq();
        final Request<BaseResult> m8939e = UserSystemAPI.m8939e(m2954aq.getAccessToken(), str, str2);
        m8939e.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.13
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestSuccess(BaseResult baseResult) {
                if (Preferences.m2954aq() != null) {
                    m2954aq.setUserName(str);
                    Preferences.m3022a(m2954aq);
                    CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_USER_EMAIL_FINISHED, new CommonResult(ErrCode.ErrNone, str, baseResult)), ModuleID.USER_SYSTEM);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestFailure(BaseResult baseResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_USER_EMAIL_FINISHED, new CommonResult(ErrCode.ErrGeneral, str, baseResult)), ModuleID.USER_SYSTEM);
                //ErrorStatistic.m5234e(m8939e.m8532e());
                //ErrorStatistic.m5239a("user", m8939e.m8532e());
            }
        });
    }

    public void modifyPassword(String str, String str2) {
        final Request<BaseResult> m8938f = UserSystemAPI.m8938f(Preferences.m2954aq().getAccessToken(), str, str2);
        m8938f.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.14
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestSuccess(BaseResult baseResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_PASSWORD_FINISHED, new CommonResult(ErrCode.ErrNone, "", "")), ModuleID.USER_SYSTEM);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestFailure(BaseResult baseResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_PASSWORD_FINISHED, new CommonResult(ErrCode.ErrGeneral, "", baseResult)), ModuleID.USER_SYSTEM);
                //ErrorStatistic.m5234e(m8938f.m8532e());
                //ErrorStatistic.m5239a("user", m8938f.m8532e());
            }
        });
    }

    public void modifyCover(final String str, final Integer num, final Integer num2) {
        final TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            this.f6031a = UserSystemAPI.m8953a(m2954aq.getAccessToken(), new File(str), new RequestCallback<PostFileResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.2
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(PostFileResult postFileResult) {
                    m2954aq.setProfileCoverUrl(postFileResult.getUrl());
                    Preferences.m3022a(m2954aq);
                    ImageCacheUtils.m4744a(m2954aq.getProfileCoverUrl(), str, num.intValue(), num2.intValue());
                    CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_COVER_FINISHED, new CommonResult(ErrCode.ErrNone, "", postFileResult)), ModuleID.USER_SYSTEM);
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(PostFileResult postFileResult) {
                    CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_COVER_FINISHED, new CommonResult(ErrCode.ErrGeneral, "", postFileResult)), ModuleID.USER_SYSTEM);
                    //ErrorStatistic.m5234e(UserSystemModule.this.f6031a == null ? str : UserSystemModule.this.f6031a.m8532e());
                    //ErrorStatistic.m5239a("user", UserSystemModule.this.f6031a == null ? str : UserSystemModule.this.f6031a.m8532e());
                }
            });
        }
    }

    public void modifyAvatar(final String str, final Integer num, final Integer num2) {
        final TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            this.f6032b = UserSystemAPI.m8948b(m2954aq.getAccessToken(), new File(str), new RequestCallback<PostFileResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.3
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(PostFileResult postFileResult) {
                    m2954aq.setAvatarUrl(postFileResult.getUrl());
                    Preferences.m3022a(m2954aq);
                    ImageCacheUtils.m4744a(m2954aq.getAvatarUrl(), str, num.intValue(), num2.intValue());
                    CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_AVATAR_FINISHED, new CommonResult(ErrCode.ErrNone, "", postFileResult)), ModuleID.USER_SYSTEM);
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(PostFileResult postFileResult) {
                    CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_AVATAR_FINISHED, new CommonResult(ErrCode.ErrGeneral, "", postFileResult)), ModuleID.USER_SYSTEM);
                    if (UserSystemModule.this.f6032b != null) {
                        //ErrorStatistic.m5234e(UserSystemModule.this.f6032b.m8532e());
                        //ErrorStatistic.m5239a("user", UserSystemModule.this.f6032b.m8532e());
                    }
                }
            });
        }
    }

    public void modifySexAndBirthday(final TTPodUser tTPodUser, Boolean bool, Boolean bool2) {
        if (bool.booleanValue() || bool2.booleanValue()) {
            final Request<BaseResult> m8942d = UserSystemAPI.m8942d(tTPodUser.getAccessToken());
            if (bool.booleanValue()) {
                UserSystemAPI.m8961a(m8942d, tTPodUser.getSex());
            }
            if (bool2.booleanValue()) {
                UserSystemAPI.m8960a(m8942d, tTPodUser.getBirthdayInSecond());
            }
            m8942d.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.4
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                public void onRequestSuccess(BaseResult baseResult) {
                    if (Preferences.m2954aq() != null) {
                        Preferences.m3022a(tTPodUser);
                        CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_SEX_BIRTHDAY_FINISHED, new CommonResult(ErrCode.ErrNone, "", baseResult)), ModuleID.USER_SYSTEM);
                    }
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                public void onRequestFailure(BaseResult baseResult) {
                    CommandCenter.getInstance().m4604a(new Command(CommandID.MODIFY_SEX_BIRTHDAY_FINISHED, new CommonResult(ErrCode.ErrGeneral, "", baseResult)), ModuleID.USER_SYSTEM);
                    //ErrorStatistic.m5234e(m8942d.m8532e());
                    //ErrorStatistic.m5239a("user", m8942d.m8532e());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4167a(UserResult userResult) {
        User data = userResult.getData();
        long userId = data.getUserId();
        Preferences.m3022a(data);
        EnvironmentUtils.C0603b.m8498a(userId);
        if (!MediaStorage.isGroupExisted(sContext, MediaStorage.buildOnlineFavGroupID())) {
            MediaStorage.insertGroup(sContext, MediaStorage.GROUP_NAME_FAV_ONLINE, MediaStorage.buildOnlineFavGroupID(), GroupType.CUSTOM_ONLINE);
        }
        //UTAnalytics.getInstance().updateUserAccount(data.getNickName(), String.valueOf(userId));
        CommandCenter.getInstance().m4595b(new Command(CommandID.LOGIN_FINISHED, new CommonResult(ErrCode.ErrNone, "", userResult)), ModuleID.USER_SYSTEM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m4164b(UserResult userResult) {
        Preferences.m3022a((User) null);
        CommandCenter.getInstance().m4595b(new Command(CommandID.LOGIN_FINISHED, new CommonResult(ErrCode.ErrGeneral, "", userResult)), ModuleID.USER_SYSTEM);
    }

    public void refreshInformation() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            final Request<TTPodUserResult> m8956a = UserSystemAPI.m8956a(m2954aq.getAccessToken());
            m8956a.m8544a(new RequestCallback<TTPodUserResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.5
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(TTPodUserResult tTPodUserResult) {
                    if (Preferences.m2954aq() != null) {
                        Preferences.m3022a(tTPodUserResult.getData());
                        CommandCenter.getInstance().m4604a(new Command(CommandID.REFRESH_INFORMATION_FINISHED, new Object[0]), ModuleID.USER_SYSTEM);
                    }
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(TTPodUserResult tTPodUserResult) {
                    //ErrorStatistic.m5234e(m8956a.m8532e());
                    //ErrorStatistic.m5239a("user", m8956a.m8532e());
                }
            });
        }
    }

    public void getUserInfoById(String str, Long l) {
        UserSystemAPI.m8955a(str, l.longValue()).m8544a(new RequestCallback<TTPodUserResult>() { // from class: com.sds.android.ttpod.framework.modules.core.f.a.6
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(TTPodUserResult tTPodUserResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_USER_INFO_BY_ID, tTPodUserResult), UserSystemModule.this.id());
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(TTPodUserResult tTPodUserResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_USER_INFO_BY_ID, tTPodUserResult), UserSystemModule.this.id());
            }
        });
    }
}
