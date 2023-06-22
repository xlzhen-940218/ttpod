package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class OpenActivity extends SlidingClosableActivity implements View.OnClickListener {
    private static final int DELAY_TIME = 1000;
    private static final float MONTH_END_FONT_SCALE = 1.2f;
    private static final int MONTH_END_START_INDEX = 13;
    private static final int PHONE_NUMBER_LENGTH = 11;
    public static final int SEND_VERIFY_CODE_VALID_TIME = 60;
    private static final String TAG = OpenActivity.class.getName();
    private Button mButtonOpen;
    private Button mButtonVerifyCode;
    private EditText mEditViewPhone;
    private EditText mEditViewVerfidyCode;
    private TextView mTextViewFlowFaq;
    private Handler mTimeHandler = new Handler();
    private int mWaitTime = 0;
    private UnicomFlowStatistic.EnumC1778b mOpenOrigin = null;
    private Runnable mCountRunnable = new Runnable() { // from class: com.sds.android.ttpod.activities.unicomflow.OpenActivity.1
        @Override // java.lang.Runnable
        public void run() {
            OpenActivity.access$012(OpenActivity.this, 1);
            OpenActivity.this.mButtonVerifyCode.setText("" + (60 - OpenActivity.this.mWaitTime) + "s后可再次获取");
            if (OpenActivity.this.mWaitTime < 60) {
                OpenActivity.this.mTimeHandler.postDelayed(this, 1000L);
                return;
            }
            OpenActivity.this.mButtonVerifyCode.setText("获取验证码");
            OpenActivity.this.mButtonVerifyCode.setEnabled(true);
        }
    };

    static /* synthetic */ int access$012(OpenActivity openActivity, int i) {
        int i2 = openActivity.mWaitTime + i;
        openActivity.mWaitTime = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unicom_flow_open);
        setTitle("验证手机号");
        this.mEditViewPhone = (EditText) findViewById(R.id.edit_phone);
        this.mButtonVerifyCode = (Button) findViewById(R.id.button_verify_code);
        this.mEditViewVerfidyCode = (EditText) findViewById(R.id.edit_verify_code);
        this.mButtonOpen = (Button) findViewById(R.id.button_open);
        this.mTextViewFlowFaq = (TextView) findViewById(R.id.text_flow_faq);
        this.mButtonOpen.setBackgroundResource(getOpenButtonBackground());
        initOrigin();
        bindView();
    }

    private void checkShowMonthEndInformation() {
        findViewById(R.id.layout_month_end_hint_message).setVisibility(View.VISIBLE);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = getString(R.string.unicom_dialog_month_end_content, new Object[]{Integer.valueOf(UnicomFlowUtil.m3943i())});
        int length = String.valueOf(UnicomFlowUtil.m3943i()).length() + 13;
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#f1605e"));
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan((float) MONTH_END_FONT_SCALE);
        spannableStringBuilder.append((CharSequence) string);
        spannableStringBuilder.setSpan(foregroundColorSpan, 13, length, 33);
        spannableStringBuilder.setSpan(relativeSizeSpan, 13, length, 33);
        ((TextView) findViewById(R.id.text_flow_faq)).setText(spannableStringBuilder);
    }

    public void initOrigin() {
        this.mOpenOrigin = (UnicomFlowStatistic.EnumC1778b) UnicomFlowManager.m7761a(this, UnicomFlowStatistic.EnumC1778b.ORDER_DETAIL);
        UnicomFlowStatistic.m4844a(this.mOpenOrigin);
    }

    public int getOpenButtonBackground() {
        return R.drawable.unicom_open_button;
    }

    private void bindView() {
        this.mButtonOpen.setOnClickListener(this);
        this.mButtonVerifyCode.setOnClickListener(this);
        this.mEditViewPhone.setText(UnicomFlowUtil.m3940l());
        this.mButtonOpen.setText(getButtonText());
        UnicomFlowUtil.m3936p();
        if (UnicomFlowUtil.m3942j()) {
            checkShowMonthEndInformation();
        }
    }

    public String getButtonText() {
        return "确定开通";
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

    private void updateVerifyCodeButtonStatus() {
        this.mWaitTime = 0;
        this.mButtonVerifyCode.setEnabled(false);
        this.mTimeHandler.postDelayed(this.mCountRunnable, 1000L);
    }

    private void resetVerifyCodeStatus() {
        try {
            this.mWaitTime = 0;
            this.mButtonVerifyCode.setText("获取验证码");
            this.mButtonVerifyCode.setEnabled(true);
            this.mTimeHandler.removeCallbacks(this.mCountRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mTimeHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        resetVerifyCodeStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.OPEN_UNICOM_FLOW_RESULT, ReflectUtils.m8375a(cls, "openUnicomFlowResult", CommonResult.class));
        map.put(CommandID.SEND_VERIFY_CODE_RESULT, ReflectUtils.m8375a(cls, "sendVerifyCodeResult", CommonResult.class));
    }

    public void openUnicomFlowResult(CommonResult commonResult) {
        UnicomFlowManager.m7766a();
        if (ErrCode.ErrNone == commonResult.m4585a() || ErrCode.ErrAlreadyExists == commonResult.m4585a()) {
            UnicomFlowStatistic.m4834c(this.mOpenOrigin);
            new SUserEvent("PAGE_CLICK", 1107, 702, 703).append("status", 1).post();
            UnicomFlowUtil.m3954a(this);
            UnicomFlowManager.m7755b();
            UnicomFlowManager.m7762a((Activity) this, OpenDetailActivity.class, (Enum) this.mOpenOrigin, true);
            return;
        }
        new SUserEvent("PAGE_CLICK", 1107, 702, 703).append("status", 0).post();
        UnicomFlowStatistic.m4830d(this.mOpenOrigin);
        PopupsUtils.m6721a(commonResult.m4584b());
    }

    public void sendVerifyCodeResult(CommonResult commonResult) {
        if (ErrCode.ErrNone != commonResult.m4585a()) {
            new SUserEvent("PAGE_CLICK", 1106, 702).post();
            PopupsUtils.m6721a("发送验证码失败，请重新发送");
            resetVerifyCodeStatus();
            UnicomFlowStatistic.m4850N();
            return;
        }
        new SUserEvent("PAGE_CLICK", 1105, 702).post();
        UnicomFlowStatistic.m4851M();
    }

    public void openUnicomFlow(String str, String str2) {
        UnicomFlowManager.m7759a((Context) this, "正在开通,请等待...");
        UnicomFlowStatistic.m4839b(this.mOpenOrigin);
        CommandCenter.m4607a().m4606a(new Command(CommandID.OPEN_UNICOM_FLOW, str, str2, null));
    }

    public void sendVerifyCode(String str, int i) {
        CommandCenter.m4607a().m4606a(new Command(CommandID.SEND_VERIFY_CODE, str, new Integer(i)));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String obj = this.mEditViewPhone.getText().toString();
        String obj2 = this.mEditViewVerfidyCode.getText().toString();
        if (view == this.mButtonOpen) {
            if (valid(obj, obj2)) {
                openUnicomFlow(obj, obj2);
            }
        } else if (view == this.mButtonVerifyCode && validPhone(obj)) {
            UnicomFlowStatistic.m4852L();
            updateVerifyCodeButtonStatus();
            sendVerifyCode(obj, 1);
            this.mEditViewVerfidyCode.requestFocus();
        }
    }
}
