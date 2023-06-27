package com.sds.android.ttpod.framework.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;





import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.common.p083b.AutoDelloc;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.SmartBarUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;

import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class BaseActivity extends FragmentActivity {
    private static final int ACTION_MOVE_GAP_PX = 48;
    private static final int MSG_BACKGROUND_STATE = 0;
    private static final String PAGE_NONE = "none";
    private static final int SET_BACKGROUND_STATE_DELAY_IN_MILLIS = 100;
    protected static final int STATUS_CREATED = 0;
    protected static final int STATUS_DESTROYED = 5;
    protected static final int STATUS_PAUSED = 3;
    protected static final int STATUS_RESUMED = 2;
    protected static final int STATUS_STARTED = 1;
    protected static final int STATUS_STOPPED = 4;
    private static final String TAG = "BaseActivity";
    private boolean mAllowFastClickTemporarily;
    private BaseFragment mCurrentFragment;
    private FragmentBackStackManager mFragmentBackStackManager;
    private long mLastClickTime;
    private float mTouchDownX;
    private float mTouchDownY;
    private static final long DOUBLE_CLICK_DURATION = TimeUnit.MILLISECONDS.toNanos(500);
    private static Handler mBackgroundHandler = new Handler() { // from class: com.sds.android.ttpod.framework.base.BaseActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean booleanValue = ((Boolean) message.obj).booleanValue();
            Preferences.m3033X(booleanValue);
            if (booleanValue) {
                SupportFactory.getInstance(BaseApplication.getApplication()).m2460p();
                CommandCenter.getInstance().execute(new Command(CommandID.SAVE_UNICOM_TOTAL_FLOW, new Object[0]));
                //StartupStatistic.m4922b();
                return;
            }
            SupportFactory.getInstance(BaseApplication.getApplication()).m2459q();
        }
    };
    private boolean mIsMoveAction = false;
    private int mStatus = 0;
    private String mSPage = "none";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SmartBarUtils.m4642a(getWindow().getDecorView());
        this.mStatus = 0;
        ActivityManager.m4618a().m4617a(this);
        Map<CommandID, Method> requestCommandMap = requestCommandMap();
        assertCommandMap(requestCommandMap);
        CommandCenter.getInstance().m4597a(this, requestCommandMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
    }

    public int status() {
        return this.mStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.mStatus = 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mStatus = 2;
        ActivityManager.m4618a().m4613c(this);
        setBackgroundState(false);
        //UmengStatisticUtils.m4868a(this);
        MediaButtonReceiver.m2252b();
        if (!"none".equals(this.mSPage)) {

        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mStatus = 3;
        ActivityManager.m4618a().m4612d(this);
        setBackgroundState(true);
        //UmengStatisticUtils.m4865b(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        switch (motionEvent.getAction()) {
            case 0:
                this.mTouchDownX = motionEvent.getX();
                this.mTouchDownY = motionEvent.getY();
                this.mIsMoveAction = false;
                break;
            case 1:
                if (!this.mAllowFastClickTemporarily && !this.mIsMoveAction && isClickTooFast()) {
                    motionEvent.setAction(3);
                }
                this.mAllowFastClickTemporarily = false;
                break;
            case 2:
                if (this.mIsMoveAction || isMoveAction(motionEvent)) {
                    z = true;
                }
                this.mIsMoveAction = z;
                break;
        }
        if (motionEvent.getAction() == 3) {
            try {
                return super.dispatchTouchEvent(motionEvent);
            } catch (IllegalArgumentException e) {
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setBackgroundState(boolean z) {
        mBackgroundHandler.removeMessages(0);
        Message obtainMessage = mBackgroundHandler.obtainMessage(0);
        obtainMessage.obj = Boolean.valueOf(z);
        mBackgroundHandler.sendMessageDelayed(obtainMessage, 100L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStatus = 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mStatus = 5;
        ActivityManager.m4618a().m4615b(this);
        if (this.mFragmentBackStackManager != null) {
            this.mFragmentBackStackManager.m4578b();
        }
        CommandCenter.getInstance().m4599a(this);
        super.onDestroy();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {

        if (this.mFragmentBackStackManager == null) {
            super.onBackPressed();
        } else if (this.mCurrentFragment != null && !this.mCurrentFragment.isChildFragmentBackStackEmpty()) {
            this.mCurrentFragment.processBackPressed();
        } else if (this.mFragmentBackStackManager.m4581a()) {
            super.onBackPressed();
        } else {
            this.mFragmentBackStackManager.m4576c().onBackPressed();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        if (!isFinishing()) {
            CommandCenter.getInstance().m4599a(this);
            super.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isFragmentBackStackEmpty() {
        if (this.mFragmentBackStackManager == null) {
            return true;
        }
        if (this.mCurrentFragment != null && !this.mCurrentFragment.isChildFragmentBackStackEmpty()) {
            return false;
        }
        return this.mFragmentBackStackManager.m4581a();
    }

    private Map<CommandID, Method> requestCommandMap() {
        HashMap hashMap = new HashMap();
        try {
            onLoadCommandMap(hashMap);
            return hashMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void assertCommandMap(Map<CommandID, Method> map) {
        if (EnvironmentUtils.AppConfig.getTestMode()) {
            for (CommandID commandID : map.keySet()) {
                if (commandID.getCommandType().equals(CommandType.TO_MODULE)) {
                    throw new IllegalArgumentException("the CommandID." + commandID.name() + " can not be registered in activity, because the CommandType is CommandType." + commandID.getCommandType().name() + "!");
                }
            }
        }
    }

    public final void launchFragment(BaseFragment baseFragment) {
        baseFragment.setFragmentHandler(this.mFragmentBackStackManager);
        this.mFragmentBackStackManager.mo4568c(baseFragment);
    }

    public final void setCurrentFragment(BaseFragment baseFragment) {
        assertFragmentBackStackManager();
        this.mCurrentFragment = baseFragment;
    }

    public final BaseFragment getCurrentFragment() {
        return this.mCurrentFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setPrimaryFragment(BaseFragment baseFragment) {
        this.mFragmentBackStackManager.m4579a(baseFragment);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BaseFragment getPrimaryFragment() {
        return this.mFragmentBackStackManager.m4574e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setLaunchFragmentAttr(int i, int i2, int i3) {
        if (this.mFragmentBackStackManager == null) {
            this.mFragmentBackStackManager = new FragmentBackStackManager(getSupportFragmentManager());
        }
        this.mFragmentBackStackManager.m4580a(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IFragmentHandler getFragmentHandler() {
        assertFragmentBackStackManager();
        return this.mFragmentBackStackManager;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AutoDelloc.m7234a((Activity) this);
    }

    public BaseFragment getTopFragment() {
        if (this.mFragmentBackStackManager != null) {
            return this.mFragmentBackStackManager.m4576c();
        }
        return null;
    }

    public void acquireFastClickSupport() {
        this.mAllowFastClickTemporarily = true;
    }

    private boolean isMoveAction(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX() - this.mTouchDownX) > 48.0f || Math.abs(motionEvent.getY() - this.mTouchDownY) > 48.0f;
    }

    private boolean isClickTooFast() {
        long nanoTime = System.nanoTime();
        if (nanoTime - this.mLastClickTime < DOUBLE_CLICK_DURATION) {
            return true;
        }
        this.mLastClickTime = nanoTime;
        return false;
    }

    private void assertFragmentBackStackManager() {
        if (this.mFragmentBackStackManager == null) {
            throw new IllegalArgumentException("you must call setLaunchFragmentAttr(int replaceResId, int enterAnimRes, int popExitAnimRes) first");
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {

        }
        return super.onKeyDown(i, keyEvent);
    }

    public void updatePage(String str) {
        setPage(str);

    }

    public void setPage(SPage sPage) {
        setPage(String.valueOf(sPage.getValue()));
    }

    public void setPage(String str) {
        this.mSPage = str;
    }

    public String getPage() {
        return this.mSPage;
    }
}
