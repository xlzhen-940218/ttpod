package com.sds.android.ttpod.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
public class FindPasswordActivity extends SlidingClosableActivity {
    public static final String KEY_USERNAME = "key_username";
    private AutoCompleteView mAutoCompleteView;
    private View mConfirmView;
    private EditText mUserNameEditText;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.find_password);
        setContentView(R.layout.activity_user_findpassword);
        initUserNameView();
        this.mConfirmView = findViewById(R.id.textview_confirm);
        this.mConfirmView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.user.FindPasswordActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FindPasswordActivity.this.processFindPassword(FindPasswordActivity.this.mUserNameEditText.getText().toString(), FindPasswordActivity.this.mUserNameEditText);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.FIND_PASSWORD_FINISHED, ReflectUtils.m8375a(getClass(), "findPasswordFinished", CommonResult.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        PopupsUtils.m6723a(this.mAutoCompleteView);
        super.onStop();
    }

    private void initUserNameView() {
        this.mUserNameEditText = (EditText) findViewById(R.id.edittext_username);
        this.mAutoCompleteView = new AutoCompleteView(this);
        MyTextWatcher myTextWatcher = new MyTextWatcher(this, this.mUserNameEditText, this.mAutoCompleteView);
        this.mAutoCompleteView.m1437a(new AutoCompleteView.InterfaceC2253a() { // from class: com.sds.android.ttpod.activities.user.FindPasswordActivity.2
            @Override // com.sds.android.ttpod.widget.AutoCompleteView.InterfaceC2253a
            /* renamed from: a */
            public void mo1426a(String str) {
                FindPasswordActivity.this.mUserNameEditText.setText(str);
                FindPasswordActivity.this.mUserNameEditText.setSelection(str.length());
                FindPasswordActivity.this.mAutoCompleteView.m1431c();
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
    public void processFindPassword(String str, View view) {
        if (ValidateUtil.m7704a(str, R.string.use_name_hint_text, R.string.email_format, view, R.anim.shake, ValidateUtil.f3111a)) {
            if (!EnvironmentUtils.DeviceConfig.m8474e()) {
                PopupsUtils.m6760a((int) R.string.network_unavailable);
                return;
            }
            PopupsUtils.m6748a(this, (int) R.string.find_password_waiting);
            CommandCenter.getInstance().execute(new Command(CommandID.FIND_PASSWORD, str));
        }
    }

    public void findPasswordFinished(CommonResult commonResult) {
        if (status() == 2) {
            PopupsUtils.m6761a();
        }
        if (commonResult.m4585a() == ErrCode.ErrNone) {
            startActivity(new Intent(this, FindPasswordCompleteActivity.class));
            finish();
            return;
        }
        PopupsUtils.m6760a((int) R.string.find_password_error);
    }
}
