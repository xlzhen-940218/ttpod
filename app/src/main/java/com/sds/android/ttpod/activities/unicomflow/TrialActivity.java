package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class TrialActivity extends OpenActivity {
    private UnicomFlowStatistic.EnumC1779c mTrialOrigin = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.unicomflow.OpenActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.sds.android.ttpod.activities.unicomflow.OpenActivity
    public void initOrigin() {
        this.mTrialOrigin = (UnicomFlowStatistic.EnumC1779c) UnicomFlowManager.m7761a(this, UnicomFlowStatistic.EnumC1779c.ORDER_DETAIL);
        UnicomFlowStatistic.m4843a(this.mTrialOrigin);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.unicomflow.OpenActivity, com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.TRIAL_UNICOM_FLOW_RESULT, ReflectUtils.m8375a(getClass(), "trialUnicomFlowResult", CommonResult.class));
    }

    @Override // com.sds.android.ttpod.activities.unicomflow.OpenActivity
    public int getOpenButtonBackground() {
        return R.drawable.unicom_trial_button;
    }

    public void trialUnicomFlowResult(CommonResult commonResult) {
        UnicomFlowManager.m7766a();
        if (ErrCode.ErrNone == commonResult.m4585a()) {
            UnicomFlowStatistic.m4829d(this.mTrialOrigin);
            UnicomFlowManager.m7755b();
            UnicomFlowManager.m7762a((Activity) this, TrialDetailActivity.class, (Enum) this.mTrialOrigin, true);
            return;
        }
        UnicomFlowStatistic.m4833c(this.mTrialOrigin);
        PopupsUtils.m6721a(commonResult.m4584b());
        if (ErrCode.ErrAlreadyExists == commonResult.m4585a()) {
            UnicomFlowManager.m7755b();
            finish();
        }
    }

    @Override // com.sds.android.ttpod.activities.unicomflow.OpenActivity
    public String getButtonText() {
        return "确定试用";
    }

    @Override // com.sds.android.ttpod.activities.unicomflow.OpenActivity
    public void openUnicomFlow(String str, String str2) {
        UnicomFlowManager.m7759a((Context) this, "正在开通试用,请等待...");
        UnicomFlowStatistic.m4838b(this.mTrialOrigin);
        CommandCenter.m4607a().m4606a(new Command(CommandID.TRIAL_UNICOM_FLOW, str, str2));
    }

    @Override // com.sds.android.ttpod.activities.unicomflow.OpenActivity
    public void sendVerifyCode(String str, int i) {
        CommandCenter.m4607a().m4606a(new Command(CommandID.SEND_VERIFY_CODE, str, new Integer(3)));
    }
}
