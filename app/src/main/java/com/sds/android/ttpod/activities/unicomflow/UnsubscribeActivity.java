package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sds.android.sdk.core.statistic.SUserEvent;
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
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class UnsubscribeActivity extends SlidingClosableActivity implements View.OnClickListener {
    private static final int DELAY_TIME = 1000;
    private static final int PHONE_NUMBER_LENGTH = 11;
    private Button mButtonUnsubscribe;
    private Button mButtonUse;
    private Button mButtonVerifyCode;
    private EditText mEditViewFaq;
    private EditText mEditViewPhone;
    private EditText mEditViewVerifyCode;
    private RadioGroup mRadioGroupFaq;
    private TextView mTextViewTitle;
    private Handler mHandler = new Handler();
    private int mWaitTime = 0;
    private Runnable mCountRunnable = new Runnable() { // from class: com.sds.android.ttpod.activities.unicomflow.UnsubscribeActivity.1
        @Override // java.lang.Runnable
        public void run() {
            UnsubscribeActivity.access$012(UnsubscribeActivity.this, 1);
            UnsubscribeActivity.this.mButtonVerifyCode.setText("" + (60 - UnsubscribeActivity.this.mWaitTime) + "s后可再次获取");
            if (UnsubscribeActivity.this.mWaitTime < 60) {
                UnsubscribeActivity.this.mHandler.postDelayed(this, 1000L);
                return;
            }
            UnsubscribeActivity.this.mButtonVerifyCode.setText("获取验证码");
            UnsubscribeActivity.this.mButtonVerifyCode.setEnabled(true);
        }
    };

    static /* synthetic */ int access$012(UnsubscribeActivity unsubscribeActivity, int i) {
        int i2 = unsubscribeActivity.mWaitTime + i;
        unsubscribeActivity.mWaitTime = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unicom_flow_unsubscribe);
        setTitle("退订");
        initView();
        UnicomFlowStatistic.m4809r();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UNSUBSCRIBE_UNICOM_FLOW_RESULT, ReflectUtils.m8375a(cls, "unsubscribeUnicomFlowResult", CommonResult.class));
        map.put(CommandID.SEND_VERIFY_CODE_RESULT, ReflectUtils.m8375a(cls, "sendVerifyCodeResult", CommonResult.class));
    }

    private void initView() {
        this.mTextViewTitle = (TextView) findViewById(R.id.text_title);
        this.mEditViewPhone = (EditText) findViewById(R.id.edit_phone);
        this.mButtonVerifyCode = (Button) findViewById(R.id.button_verify_code);
        this.mEditViewVerifyCode = (EditText) findViewById(R.id.edit_verify_code);
        this.mRadioGroupFaq = (RadioGroup) findViewById(R.id.radio_faq);
        this.mEditViewFaq = (EditText) findViewById(R.id.edit_faq);
        this.mButtonUnsubscribe = (Button) findViewById(R.id.button_unsubscribe);
        this.mButtonUse = (Button) findViewById(R.id.button_use);
        this.mButtonUnsubscribe.setOnClickListener(this);
        this.mButtonUse.setOnClickListener(this);
        this.mButtonVerifyCode.setOnClickListener(this);
        this.mEditViewPhone.setText(UnicomFlowUtil.m3940l());
        this.mTextViewTitle.setText(getString(R.string.unicom_unsubscribe_msg));
    }

    public void sendVerifyCodeResult(CommonResult commonResult) {
        if (ErrCode.ErrNone != commonResult.m4585a()) {
            new SUserEvent("PAGE_CLICK", 1114, 707).post();
            PopupsUtils.m6721a("发送验证码失败，请重新发送");
            resetVerifyCodeStatus();
            return;
        }
        new SUserEvent("PAGE_CLICK", 1113, 707).post();
    }

    public void unsubscribeUnicomFlowResult(CommonResult commonResult) {
        UnicomFlowManager.m7766a();
        if (ErrCode.ErrNone == commonResult.m4585a() || ErrCode.ErrAlreadyExists == commonResult.m4585a()) {
            UnicomFlowStatistic.m4806u();
            new SUserEvent("PAGE_CLICK", 1116, 707, 705).append("status", 1).post();
            UnicomFlowManager.m7755b();
            UnicomFlowManager.m7762a((Activity) this, UnsubscribeDetailActivity.class, (Enum) null, true);
            return;
        }
        new SUserEvent("PAGE_CLICK", 1116, 707, 705).append("status", 0).post();
        UnicomFlowStatistic.m4805v();
        PopupsUtils.m6721a(commonResult.m4584b());
    }

    private boolean validPhone(String str) {
        if (StringUtils.m8346a(str) || str.length() != 11) {
            PopupsUtils.m6721a("手机号输入有误");
            return false;
        }
        return true;
    }

    public boolean valid(String str, String str2) {
        if (StringUtils.m8346a(str2)) {
            PopupsUtils.m6721a("验证码有问题");
            return false;
        }
        return validPhone(str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String obj = this.mEditViewPhone.getText().toString();
        String obj2 = this.mEditViewVerifyCode.getText().toString();
        if (view == this.mButtonUnsubscribe && valid(obj, obj2)) {
            String obj3 = this.mEditViewFaq.getText().toString();
            int checkedRadioButtonId = this.mRadioGroupFaq.getCheckedRadioButtonId();
            Object obj4 = null;
            if (checkedRadioButtonId != -1) {
                obj4 = findViewById(checkedRadioButtonId).getTag();
            }
            int parseInt = obj4 == null ? 0 : Integer.parseInt(obj4.toString());
            UnicomFlowManager.m7759a((Context) this, "正在退订联通流量业务");
            UnicomFlowStatistic.m4808s();
            CommandCenter.m4607a().m4606a(new Command(CommandID.UNSUBSCRIBE_UNICOM_FLOW, obj, new Integer(parseInt), obj3, obj2));
        } else if (view == this.mButtonUse) {
            new SUserEvent("PAGE_CLICK", 1117, 707).post();
            UnicomFlowStatistic.m4807t();
            UnicomFlowManager.m7755b();
            finish();
        } else if (view == this.mButtonVerifyCode) {
            if (validPhone(obj)) {
                updateVerifyCodeButtonStatus();
                CommandCenter.m4607a().m4606a(new Command(CommandID.SEND_VERIFY_CODE, obj, new Integer(2)));
                return;
            }
            PopupsUtils.m6721a("手机号输入有误");
        }
    }

    private void updateVerifyCodeButtonStatus() {
        this.mWaitTime = 0;
        this.mButtonVerifyCode.setEnabled(false);
        this.mHandler.postDelayed(this.mCountRunnable, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    private void resetVerifyCodeStatus() {
        try {
            this.mWaitTime = 0;
            this.mButtonVerifyCode.setText("获取验证码");
            this.mButtonVerifyCode.setEnabled(true);
            this.mHandler.removeCallbacks(this.mCountRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        resetVerifyCodeStatus();
    }
}
