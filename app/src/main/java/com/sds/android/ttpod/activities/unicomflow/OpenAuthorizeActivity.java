package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
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
public class OpenAuthorizeActivity extends UnicomFlowActivity implements View.OnClickListener {
    private static final float FONT_SCALE = 1.5f;
    public static final String KEY_PHONE = "key_phone";
    public static final String KEY_TOKEN = "key_token";
    private static final float MONTH_END_FONT_SCALE = 1.2f;
    private static final int MONTH_END_START_INDEX = 13;
    private Button mButtonOpen;
    private String mPhone;
    private TextView mTextModifyPhone;
    private TextView mTextViewPhoneMessage;
    private String mToken;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unicom_flow_authorize_open);
        setTitle("天天动听-包流量畅听");
        this.mTextViewPhoneMessage = (TextView) findViewById(R.id.textview_phone);
        this.mButtonOpen = (Button) findViewById(R.id.button_open);
        this.mTextModifyPhone = (TextView) findViewById(R.id.textview_modify_phone);
        this.mTextModifyPhone.setOnClickListener(this);
        this.mButtonOpen.setOnClickListener(this);
        checkShowPhoneNumber();
        UnicomFlowStatistic.m4857G();
        UnicomFlowUtil.m3936p();
        if (UnicomFlowUtil.m3942j()) {
            checkShowMonthEndInformation();
        }
    }

    private void checkShowPhoneNumber() {
        this.mPhone = getIntentExtraValue(KEY_PHONE);
        this.mToken = getIntentExtraValue(KEY_TOKEN);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        StyleSpan styleSpan = new StyleSpan(1);
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan((float) FONT_SCALE);
        spannableStringBuilder.append((CharSequence) getString(R.string.unicom_phone_message));
        int length = spannableStringBuilder.length();
        if (!StringUtils.m8346a(this.mPhone)) {
            spannableStringBuilder.append((CharSequence) (this.mPhone + " "));
            spannableStringBuilder.setSpan(styleSpan, length, this.mPhone.length() + length, 18);
            spannableStringBuilder.setSpan(relativeSizeSpan, length, this.mPhone.length() + length, 33);
        }
        this.mTextViewPhoneMessage.setText(spannableStringBuilder);
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
        ((TextView) findViewById(R.id.textview_month_end_message)).setText(spannableStringBuilder);
    }

    private String getIntentExtraValue(String str) {
        if (getIntent() == null) {
            return "";
        }
        String stringExtra = getIntent().getStringExtra(str);
        return StringUtils.m8346a(stringExtra) ? "" : stringExtra;
    }

    @Override // com.sds.android.ttpod.activities.unicomflow.UnicomFlowActivity, com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.OPEN_UNICOM_FLOW_RESULT, ReflectUtils.m8375a(getClass(), "openUnicomFlowResult", CommonResult.class));
    }

    public void openUnicomFlowResult(CommonResult commonResult) {
        UnicomFlowManager.m7766a();
        if (ErrCode.ErrNone == commonResult.m4585a() || ErrCode.ErrAlreadyExists == commonResult.m4585a()) {
            UnicomFlowManager.m7755b();
            UnicomFlowStatistic.m4834c(UnicomFlowStatistic.EnumC1778b.OPEN_AUTHORIZE);
            new SUserEvent("PAGE_CLICK", 1104, 701, 703).append("status", 1).post();
            UnicomFlowManager.m7762a((Activity) this, OpenDetailActivity.class, (Enum) UnicomFlowStatistic.EnumC1778b.OPEN_AUTHORIZE, true);
            return;
        }
        new SUserEvent("PAGE_CLICK", 1104, 701, 703).append("status", 0).post();
        UnicomFlowStatistic.m4830d(UnicomFlowStatistic.EnumC1778b.OPEN_AUTHORIZE);
        PopupsUtils.m6721a(commonResult.m4584b());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mTextModifyPhone) {
            UnicomFlowStatistic.m4856H();
            new SUserEvent("PAGE_CLICK", 1103, 701, 702).post();
            UnicomFlowManager.m7762a((Activity) this, OpenActivity.class, (Enum) UnicomFlowStatistic.EnumC1778b.OPEN_AUTHORIZE, true);
        } else if (view == this.mButtonOpen) {
            UnicomFlowStatistic.m4855I();
            UnicomFlowManager.m7759a((Context) this, "正在开通,请等待...");
            CommandCenter.m4607a().m4606a(new Command(CommandID.OPEN_UNICOM_FLOW, this.mPhone, "", this.mToken));
        }
    }
}
