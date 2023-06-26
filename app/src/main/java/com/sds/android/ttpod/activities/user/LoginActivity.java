package com.sds.android.ttpod.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.result.UserResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.share.p137b.AuthCallback;
import com.sds.android.ttpod.share.p137b.AuthHandler;
import com.sds.android.ttpod.share.p137b.QQZoneAuthHandler;
import com.sds.android.ttpod.share.p137b.SinaWeiboAuthHandler;
import com.sds.android.ttpod.share.p139d.AccessTokenUtil;
import com.sds.android.ttpod.widget.AutoCompleteView;

import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class LoginActivity extends SlidingClosableActivity {
    private static final String TAG = "LoginActivity";
    private AutoCompleteView mAutoCompleteView;
    private View mFindPasswordView;
    private View mLoginView;
    private EditText mPassWordEditText;
    private AuthHandler mQQAuthHandler;
    private View mQQLoginHtmlView;
    private View mQQLoginView;
    private View mRegister;
    private AuthHandler mSinaAuthHandler;
    private View mSinaLoginView;
    private EditText mUserNameEditText;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.user.LoginActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_qq_login /* 2131230876 */:
                    LoginActivity.this.qqLogin();
                    return;
                case R.id.textview_sina_login /* 2131230877 */:
                    LoginActivity.this.sinaLogin();
                    return;
                case R.id.edittext_username /* 2131230878 */:
                case R.id.edittext_password /* 2131230879 */:
                case R.id.buttonLayout /* 2131230880 */:
                case R.id.register_layout /* 2131230883 */:
                case R.id.textview_qq_message /* 2131230885 */:
                default:
                    return;
                case R.id.textview_register /* 2131230881 */:
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class).putExtra("key_username", LoginActivity.this.mUserNameEditText.getText().toString()));
                    return;
                case R.id.textview_login /* 2131230882 */:
                    LoginActivity.this.login();
                    return;
                case R.id.textview_find_password /* 2131230884 */:
                    LoginActivity.this.findPassword();
                    return;
                case R.id.textview_qq_login_html /* 2131230886 */:
                    LoginActivity.this.qqLoginHtml();
                    return;
            }
        }
    };
    private AuthCallback mQQAuthCallback = new AuthCallback() { // from class: com.sds.android.ttpod.activities.user.LoginActivity.4
        @Override // com.sds.android.ttpod.share.p137b.AuthCallback
        /* renamed from: a */
        public void mo1976a(Bundle bundle) {
            if (LoginActivity.this.status() == 2) {
                PopupsUtils.m6748a(LoginActivity.this, (int) R.string.login_wait_message);
            }
            String string = bundle.getString("access_token");
            String string2 = bundle.getString("expires_in");
            String string3 = bundle.getString("openid");
            LogUtils.debug(LoginActivity.TAG, "mQQAuthCallback onAuthSuccess token=" + string + ",expiresIn=" + string2 + ",openId=" + string3);
            if (!StringUtils.isEmpty(string) && !StringUtils.isEmpty(string2) && !StringUtils.isEmpty(string3)) {
                AccessTokenUtil.m1940a(LoginActivity.this, "TENTCANT_TTPOD_TOKEN", bundle);
                CommandCenter.getInstance().execute(new Command(CommandID.QQ_LOGIN, string, string3, string2));
            }
        }

        @Override // com.sds.android.ttpod.share.p137b.AuthCallback
        /* renamed from: a */
        public void mo1975a(String str) {
            if (LoginActivity.this.status() == 2) {
                PopupsUtils.m6761a();
            }
            PopupsUtils.m6721a(str);
        }
    };

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.login_title);
        setContentView(R.layout.activity_login);
        this.mSinaLoginView = findViewById(R.id.textview_sina_login);
        this.mSinaLoginView.setOnClickListener(this.mOnClickListener);
        this.mSinaLoginView.setTag(new SinaWeiboAuthHandler(this));
        this.mQQLoginView = findViewById(R.id.textview_qq_login);
        this.mQQLoginView.setOnClickListener(this.mOnClickListener);
        this.mQQLoginView.setTag(new QQZoneAuthHandler(this));
        initUserNameView();
        this.mPassWordEditText = (EditText) findViewById(R.id.edittext_password);
        this.mPassWordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sds.android.ttpod.activities.user.LoginActivity.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                LoginActivity.this.login();
                return false;
            }
        });
        this.mRegister = findViewById(R.id.textview_register);
        this.mRegister.setOnClickListener(this.mOnClickListener);
        this.mLoginView = findViewById(R.id.textview_login);
        this.mLoginView.setOnClickListener(this.mOnClickListener);
        this.mFindPasswordView = findViewById(R.id.textview_find_password);
        this.mFindPasswordView.setOnClickListener(this.mOnClickListener);
        this.mQQLoginHtmlView = findViewById(R.id.textview_qq_login_html);
        this.mQQLoginHtmlView.setOnClickListener(this.mOnClickListener);
        this.mQQAuthHandler = new QQZoneAuthHandler(this);
        this.mSinaAuthHandler = new SinaWeiboAuthHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        PopupsUtils.m6723a(this.mAutoCompleteView);
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.LOGIN_FINISHED, ReflectUtils.m8375a(getClass(), "loginFinished", CommonResult.class));
    }

    private void initUserNameView() {
        this.mUserNameEditText = (EditText) findViewById(R.id.edittext_username);
        this.mAutoCompleteView = new AutoCompleteView(this);
        MyTextWatcher myTextWatcher = new MyTextWatcher(this, this.mUserNameEditText, this.mAutoCompleteView);
        this.mAutoCompleteView.m1437a(new AutoCompleteView.InterfaceC2253a() { // from class: com.sds.android.ttpod.activities.user.LoginActivity.3
            @Override // com.sds.android.ttpod.widget.AutoCompleteView.InterfaceC2253a
            /* renamed from: a */
            public void mo1426a(String str) {
                LoginActivity.this.mUserNameEditText.setText(str);
                LoginActivity.this.mUserNameEditText.setSelection(str.length());
                LoginActivity.this.mAutoCompleteView.m1431c();
            }
        });
        this.mUserNameEditText.addTextChangedListener(myTextWatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qqLogin() {
        this.mQQAuthHandler.m2072a(this.mQQAuthCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sinaLogin() {
        this.mSinaAuthHandler.m2072a(new AuthCallback() { // from class: com.sds.android.ttpod.activities.user.LoginActivity.5
            @Override // com.sds.android.ttpod.share.p137b.AuthCallback
            /* renamed from: a */
            public void mo1976a(Bundle bundle) {
                if (LoginActivity.this.status() == 2) {
                    PopupsUtils.m6748a(LoginActivity.this, (int) R.string.login_wait_message);
                }
                String string = bundle.getString("access_token");
                String string2 = bundle.getString("expires_in");
                String string3 = bundle.getString("uid");
                LogUtils.debug(LoginActivity.TAG, "mQQAuthCallback onAuthSuccess token=" + string + ",expiresIn=" + string2 + ",openId=" + string3);
                if (!StringUtils.isEmpty(string) && !StringUtils.isEmpty(string2) && !StringUtils.isEmpty(string3)) {
                    AccessTokenUtil.m1940a(LoginActivity.this, "SINA_TTPOD_TOKEN", bundle);
                    CommandCenter.getInstance().execute(new Command(CommandID.SINA_LOGIN, string, string3, string2));
                }
            }

            @Override // com.sds.android.ttpod.share.p137b.AuthCallback
            /* renamed from: a */
            public void mo1975a(String str) {
                if (LoginActivity.this.status() == 2) {
                    PopupsUtils.m6761a();
                }
                PopupsUtils.m6760a((int) R.string.auth_failed);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void login() {
        String obj = this.mUserNameEditText.getText().toString();
        String obj2 = this.mPassWordEditText.getText().toString();
        if (validate(obj, obj2)) {
            if (!EnvironmentUtils.DeviceConfig.isConnected()) {
                PopupsUtils.m6760a((int) R.string.network_unavailable);
                return;
            }
            PopupsUtils.m6748a(this, (int) R.string.login_wait_message);
            CommandCenter.getInstance().execute(new Command(CommandID.LOGIN, obj, obj2));
        }
    }

    private boolean validate(String str, String str2) {
        return ValidateUtil.m7704a(str, R.string.use_name_hint_text, R.string.email_format, this.mUserNameEditText, R.anim.shake, ValidateUtil.f3112b) && ValidateUtil.m7704a(str2, R.string.pass_word_hint_text, R.string.password_length, this.mPassWordEditText, R.anim.shake, ValidateUtil.f3114d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findPassword() {
        Intent intent = new Intent(this, FindPasswordActivity.class);
        intent.putExtra("key_username", this.mUserNameEditText.getText().toString());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qqLoginHtml() {
        this.mQQAuthHandler.m2069b(this.mQQAuthCallback);
    }

    public void loginFinished(CommonResult commonResult) {
        if (status() == 2) {
            PopupsUtils.m6761a();
        }
        if (commonResult.m4585a() == ErrCode.ErrNone) {
            finish();
        } else if (commonResult.m4585a() == ErrCode.ErrNotFound) {
            PopupsUtils.m6760a((int) R.string.login_username_not_exist);
        } else if (commonResult.m4585a() == ErrCode.ErrPathNotFound) {
            PopupsUtils.m6721a(commonResult.m4584b());
        } else if (((UserResult) commonResult.m4582d()).getCode() == 30301) {
            PopupsUtils.m6760a((int) R.string.auth_failure_tip);
        } else {
            PopupsUtils.m6760a((int) R.string.login_failure_tip_text);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mQQAuthHandler != null) {
            this.mQQAuthHandler.mo2065a(i, i2, intent);
        }
        if (this.mSinaAuthHandler != null) {
            this.mSinaAuthHandler.mo2065a(i, i2, intent);
        }
    }
}
