package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.AlikeUserListResult;
import com.sds.android.cloudapi.ttpod.result.PostFileResult;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.cloudapi.ttpod.result.UserResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.PostMethodRequest;
import com.sds.android.sdk.lib.request.PostMethodRequestV2;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.LogUtils;


import java.io.File;

/* renamed from: com.sds.android.cloudapi.ttpod.a.ab */
/* loaded from: classes.dex */
public class UserSystemAPI {
    /* renamed from: a */
    public static Request<UserResult> m8952a(String str, String str2) {
        return new PostMethodRequest(UserResult.class, "http://v2.ttus.ttpod.com/ttus/user", "login").m8537b(User.KEY_USER_EMAIL, str).m8537b("password", str2);
    }

    /* renamed from: a */
    public static Request<UserResult> m8951a(String str, String str2, String str3) {
        return new PostMethodRequestV2(UserResult.class, "http://ttus.ttpod.com/third/login/qq").m8537b("access_token", str).m8537b("expires_in", str3).m8537b("openid", str2);
    }

    /* renamed from: b */
    public static Request<UserResult> m8946b(String str, String str2, String str3) {
        return new PostMethodRequestV2(UserResult.class, "http://ttus.ttpod.com/third/login/sina").m8537b("access_token", str).m8537b("expires_in", str3).m8537b("openid", str2);
    }

    /* renamed from: c */
    public static Request<UserResult> m8943c(String str, String str2, String str3) {
        return new PostMethodRequest(UserResult.class, "http://v2.ttus.ttpod.com/ttus/user", "register").m8537b(User.KEY_USER_EMAIL, str).m8537b("password", str2).m8537b(User.KEY_NICK_NAME, str3);
    }

    /* renamed from: b */
    public static Request<UserListResult> m8947b(String str, String str2) {
        return new PostMethodRequest(UserListResult.class, "http://v2.ttus.ttpod.com/ttus/user", "search").m8535c("access_token", str).m8537b(User.KEY_NICK_NAME, str2);
    }

    /* renamed from: a */
    public static Request<TTPodUserResult> m8956a(String str) {
        return new PostMethodRequest(TTPodUserResult.class, "http://v2.ttus.ttpod.com/ttus/user", "show").m8535c("access_token", str);
    }

    /* renamed from: a */
    public static Request<TTPodUserResult> m8955a(String str, long j) {
        return new PostMethodRequest(TTPodUserResult.class, "http://v2.ttus.ttpod.com/ttus/user", "info").m8535c("access_token", str).m8537b(User.KEY_USER_ID, Long.valueOf(j));
    }

    /* renamed from: b */
    public static Request<BaseResult> m8950b(String str) {
        return new PostMethodRequest(BaseResult.class, "http://v2.ttus.ttpod.com/ttus/user", "checkname").m8537b(User.KEY_USER_EMAIL, str);
    }

    /* renamed from: c */
    public static Request<BaseResult> m8945c(String str) {
        return new PostMethodRequest(BaseResult.class, "http://v2.ttus.ttpod.com/ttus/user", "find_pwd").m8537b(User.KEY_USER_EMAIL, str);
    }

    /* renamed from: d */
    public static Request<BaseResult> m8942d(String str) {
        return new PostMethodRequest(BaseResult.class, "http://v2.ttus.ttpod.com/ttus/user", "alter").m8535c("access_token", str);
    }

    /* renamed from: d */
    public static Request<BaseResult> m8941d(String str, String str2, String str3) {
        return new PostMethodRequest(BaseResult.class, "http://v2.ttus.ttpod.com/ttus/user", "loginbind").m8535c("access_token", str).m8537b(User.KEY_USER_EMAIL, str2).m8537b("password", str3);
    }

    /* renamed from: a */
    private static Request<BaseResult> m8958a(Request<BaseResult> request, String str, String str2) {
        return request.m8537b(User.KEY_USER_EMAIL, str).m8537b("password", str2);
    }

    /* renamed from: a */
    private static Request<BaseResult> m8959a(Request<BaseResult> request, String str) {
        return request.m8537b(User.KEY_NICK_NAME, str);
    }

    /* renamed from: a */
    public static Request<BaseResult> m8961a(Request<BaseResult> request, int i) {
        return request.m8537b(User.KEY_SEX, Integer.valueOf(i));
    }

    /* renamed from: a */
    public static Request<BaseResult> m8960a(Request<BaseResult> request, long j) {
        return request.m8537b(User.KEY_BIRTHDAY, Long.valueOf(j));
    }

    /* renamed from: e */
    public static Request<BaseResult> m8939e(String str, String str2, String str3) {
        Request<BaseResult> m8942d = m8942d(str);
        m8958a(m8942d, str2, str3);
        return m8942d;
    }

    /* renamed from: f */
    public static Request<BaseResult> m8938f(String str, String str2, String str3) {
        return new PostMethodRequest(BaseResult.class, "http://v2.ttus.ttpod.com/ttus/user", "alter_pwd").m8535c("access_token", str).m8537b("old_password", str2).m8537b("password", str3);
    }

    /* renamed from: c */
    public static Request<BaseResult> m8944c(String str, String str2) {
        Request<BaseResult> m8942d = m8942d(str);
        m8959a(m8942d, str2);
        return m8942d;
    }

    /* renamed from: a */
    public static Request<PostFileResult> m8953a(String str, File file, RequestCallback<PostFileResult> requestCallback) {
        if (m8957a(file)) {
            Request<PostFileResult> m8954a = m8954a(str, file);
            m8954a.m8544a(requestCallback);
            return m8954a;
        }
        requestCallback.onRequestFailure(new PostFileResult());
        return null;
    }

    /* renamed from: b */
    public static Request<PostFileResult> m8948b(String str, File file, RequestCallback<PostFileResult> requestCallback) {
        if (m8957a(file)) {
            Request<PostFileResult> m8949b = m8949b(str, file);
            m8949b.m8544a(requestCallback);
            return m8949b;
        }
        requestCallback.onRequestFailure(new PostFileResult());
        return null;
    }

    /* renamed from: a */
    private static Request<PostFileResult> m8954a(String str, File file) {
        return new PostMethodRequest(PostFileResult.class, "http://v2.ttus.ttpod.com/ttus/user", "cover").m8553a("image", file).m8535c("access_token", str).m8542a("alter_image/cover");
    }

    /* renamed from: b */
    private static Request<PostFileResult> m8949b(String str, File file) {
        return new PostMethodRequest(PostFileResult.class, "http://v2.ttus.ttpod.com/ttus/user", "avatar").m8553a("image", file).m8535c("access_token", str).m8542a("alter_image/avatar");
    }

    /* renamed from: a */
    private static boolean m8957a(File file) {
        if (file == null || !file.exists() || file.length() == 0 || file.length() > 1048576) {
            Object[] objArr = new Object[3];
            objArr[0] = file != null ? file.getPath() : "null";
            objArr[1] = Boolean.valueOf(file != null && file.exists());
            objArr[2] = Long.valueOf(file != null ? file.length() : -1L);
            LogUtils.m8380c("UserSystemAPI", "isValidFileForPost path=%s %b %d CropImageView", objArr);
            return false;
        }
        return true;
    }

    /* renamed from: e */
    public static Request<AlikeUserListResult> m8940e(String str) {
        return new GetMethodRequest(AlikeUserListResult.class, "http://v1.ard.y.itlily.com/user", "alike_infos").m8537b("access_token", str);
    }
}
