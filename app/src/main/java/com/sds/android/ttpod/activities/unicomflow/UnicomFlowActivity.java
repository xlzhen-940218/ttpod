package com.sds.android.ttpod.activities.unicomflow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.UnicomFlow;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class UnicomFlowActivity extends SlidingClosableActivity {
    private boolean mIsNeedFinish;
    private UnicomFlowStatistic.EnumC1778b mOpenOrigin;

    public void startOpenActivity(UnicomFlowStatistic.EnumC1778b enumC1778b, boolean z) {
        this.mOpenOrigin = enumC1778b;
        this.mIsNeedFinish = z;
        if (EnvironmentUtils.C0604c.m8476d() == 0 || EnvironmentUtils.C0604c.m8476d() == 3 || EnvironmentUtils.C0604c.m8476d() == 1) {
            UnicomFlowManager.m7759a((Context) this, "数据加载中...");
            CommandCenter.m4607a().m4606a(new Command(CommandID.NET_AUTHORIZE, new Object[0]));
            return;
        }
        gotoOpenActivity(enumC1778b, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.NET_AUTHORIZE_RESULT, ReflectUtils.m8375a(getClass(), "netAuthorizeResult", CommonResult.class));
    }

    private void gotoOpenActivity(UnicomFlowStatistic.EnumC1778b enumC1778b, boolean z) {
        UnicomFlowStatistic.m4812o();
        new SUserEvent("PAGE_CLICK", 1101, 700, 702).post();
        UnicomFlowManager.m7762a(this, OpenActivity.class, enumC1778b, z);
    }

    private void gotoOpenAuthorizeActivity(UnicomFlow unicomFlow) {
        new SUserEvent("PAGE_CLICK", 1147, 700, 701).post();
        Intent intent = new Intent(this, OpenAuthorizeActivity.class);
        intent.putExtra(OpenAuthorizeActivity.KEY_PHONE, unicomFlow.getPhone());
        intent.putExtra(OpenAuthorizeActivity.KEY_TOKEN, unicomFlow.getToken());
        UnicomFlowManager.m7764a((Activity) this, intent, (Enum) this.mOpenOrigin, false);
    }

    public void netAuthorizeResult(CommonResult commonResult) {
        boolean z;
        UnicomFlow unicomFlow;
        UnicomFlowManager.m7766a();
        if (ErrCode.ErrNone != commonResult.m4585a() || !(commonResult.m4582d() instanceof UnicomFlow) || (unicomFlow = (UnicomFlow) commonResult.m4582d()) == null || StringUtils.m8346a(unicomFlow.getPhone()) || StringUtils.m8346a(unicomFlow.getToken())) {
            z = false;
        } else {
            gotoOpenAuthorizeActivity(unicomFlow);
            z = true;
        }
        if (!z) {
            gotoOpenActivity(this.mOpenOrigin, this.mIsNeedFinish);
        }
    }
}
