package com.sds.android.ttpod.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.sds.android.cloudapi.ttpod.result.ResultCode;
import com.sds.android.cloudapi.ttpod.result.UserResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
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
import com.sds.android.ttpod.widget.AutoCompleteView;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class RegisterActivity extends SlidingClosableActivity {
    public static final String KEY_USERNAME = "key_username";
    private AutoCompleteView mAutoCompleteView;
    private EditText mNickNameEditText;
    private EditText mPassWordEditText;
    private View mRegisterView;
    private EditText mUserNameEditText;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.register_user);
        setContentView(R.layout.activity_user_register);
        initUserNameView();
        this.mNickNameEditText = (EditText) findViewById(R.id.edittext_nickname);
        this.mPassWordEditText = (EditText) findViewById(R.id.edittext_password);
        this.mRegisterView = findViewById(R.id.textview_register);
        this.mRegisterView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.user.RegisterActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegisterActivity.this.processRegister(RegisterActivity.this.mUserNameEditText.getText().toString(), RegisterActivity.this.mNickNameEditText.getText().toString(), RegisterActivity.this.mPassWordEditText.getText().toString());
            }
        });
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
        map.put(CommandID.RESISTER_FINISHED, ReflectUtils.m8375a(getClass(), "registerFinished", CommonResult.class));
    }

    private void initUserNameView() {
        this.mUserNameEditText = (EditText) findViewById(R.id.edittext_username);
        this.mAutoCompleteView = new AutoCompleteView(this);
        MyTextWatcher myTextWatcher = new MyTextWatcher(this, this.mUserNameEditText, this.mAutoCompleteView);
        this.mAutoCompleteView.m1437a(new AutoCompleteView.InterfaceC2253a() { // from class: com.sds.android.ttpod.activities.user.RegisterActivity.2
            @Override // com.sds.android.ttpod.widget.AutoCompleteView.InterfaceC2253a
            /* renamed from: a */
            public void mo1426a(String str) {
                RegisterActivity.this.mUserNameEditText.setText(str);
                RegisterActivity.this.mUserNameEditText.setSelection(str.length());
                RegisterActivity.this.mAutoCompleteView.m1431c();
            }
        });
        this.mUserNameEditText.addTextChangedListener(myTextWatcher);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("key_username");
            if (!StringUtils.isEmpty(stringExtra)) {
                this.mUserNameEditText.setText(stringExtra);
                this.mUserNameEditText.requestFocus();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRegister(String str, String str2, String str3) {
        if (validate(str, str2, str3)) {
            if (!EnvironmentUtils.DeviceConfig.m8474e()) {
                PopupsUtils.m6760a((int) R.string.network_unavailable);
                return;
            }
            PopupsUtils.m6748a(this, (int) R.string.begin_register);
            CommandCenter.getInstance().execute(new Command(CommandID.RESISTER, str, str3, str2));
        }
    }

    public void registerFinished(CommonResult commonResult) {
        if (status() == 2) {
            PopupsUtils.m6761a();
        }
        if (commonResult.m4585a() == ErrCode.ErrNone) {
            finish();
            return;
        }
        switch (((UserResult) commonResult.m4582d()).getCode()) {
            case ResultCode.ERROR_VALUE_EXIST /* 30307 */:
                this.mNickNameEditText.requestFocus();
                PopupsUtils.m6760a((int) R.string.nickname_has_exited);
                return;
            case ResultCode.ERROR_USERNAME_EXIST /* 30308 */:
                this.mUserNameEditText.requestFocus();
                PopupsUtils.m6760a((int) R.string.user_name_has_exited);
                return;
            case ResultCode.ERROR_NICKNAME_NON_STANDARD /* 30309 */:
                this.mNickNameEditText.requestFocus();
                PopupsUtils.m6760a((int) R.string.nickname_non_standard);
                return;
            case ResultCode.ERROR_USERNAME_MUST_BE_EMAIL /* 30310 */:
                this.mUserNameEditText.requestFocus();
                PopupsUtils.m6760a((int) R.string.user_name_must_email);
                return;
            case ResultCode.ERROR_PASSWORD_NON_STANDARD /* 30311 */:
                this.mPassWordEditText.requestFocus();
                PopupsUtils.m6760a((int) R.string.password_non_standard);
                return;
            default:
                return;
        }
    }

    private boolean validate(String str, String str2, String str3) {
        return ValidateUtil.m7704a(str, R.string.use_name_hint_text, R.string.email_format, this.mUserNameEditText, R.anim.shake, ValidateUtil.f3111a) && ValidateUtil.m7704a(str3, R.string.pass_word_hint_text, R.string.password_length, this.mPassWordEditText, R.anim.shake, ValidateUtil.f3114d) && ValidateUtil.m7704a(str2, R.string.nickname_hint_text, R.string.nick_name_restriction, this.mNickNameEditText, R.anim.shake, ValidateUtil.f3113c);
    }
}
