package com.sds.android.ttpod.framework.base;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;



import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.common.p083b.AutoDelloc;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class BaseFragment extends Fragment implements ILoadFinished, ThemeManager.InterfaceC2019b {
    private static final int ANIMATION_END_DELAY = 100;
    public static final String KEY_PAGE = "key_page";
    public static final String KEY_SONG_LIST_ID = "song_list_id";
    protected static final String PAGE_NONE = "none";
    private static final String TAG = "BaseFragment";
    private FragmentBackStackManager mChildFragmentBackStackManager;
    private BaseFragment mCurrentChildFragment;
    private IFragmentHandler mFragmentHandler;
    private boolean mIsPage;

    private boolean mViewAccessAble = false;
    private boolean mOfflineModeConfirmed = false;
    private EnumC1800a mLoadState = EnumC1800a.NONE;
    private String mSPage = "none";

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.framework.base.BaseFragment$a */
    /* loaded from: classes.dex */
    public enum EnumC1800a {
        NONE,
        DOING_ANIM,
        WAITING_PARENT,
        FINISHED
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        AutoDelloc.m7233a((Fragment) this);
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Map<CommandID, Method> requestCommandMap = requestCommandMap();
        assertCommandMap(requestCommandMap);
        CommandCenter.getInstance().m4597a(this, requestCommandMap);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getString(KEY_PAGE) != null) {
            setPage(arguments.getString(KEY_PAGE));
        }
        if (arguments != null && arguments.getString(KEY_SONG_LIST_ID) != null) {

        }
    }

    @Override // android.support.v4.app.Fragment
    public final Animation onCreateAnimation(int i, boolean z, int i2) {
        if (z) {
            if (i2 != 0) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), i2);
                    loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sds.android.ttpod.framework.base.BaseFragment.1
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation) {
                            new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.framework.base.BaseFragment.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (BaseFragment.this.mLoadState == EnumC1800a.DOING_ANIM) {
                                        BaseFragment.this.checkLoadFinished();
                                    }
                                }
                            }, 100L);
                        }
                    });
                    this.mLoadState = EnumC1800a.DOING_ANIM;
                    return loadAnimation;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        return super.onCreateAnimation(i, z, i2);
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mViewAccessAble = true;
    }

    public void onPostViewCreated(View view, Bundle bundle) {
        onThemeLoaded();
    }

    protected boolean canLoadDataWhenResume() {
        return true;
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mLoadState == EnumC1800a.NONE && canLoadDataWhenResume()) {
            checkLoadFinished();
        }
        if (isPage()) {

        }
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        this.mViewAccessAble = false;
        super.onDestroyView();
    }

    public void onKeyPressed(int i, KeyEvent keyEvent) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLoadFinished() {
        if (isResumed() && getUserVisibleHint()) {
            BaseFragment baseFragment = (BaseFragment) getParentFragment();
            if (baseFragment == null || baseFragment.isLoadFinished()) {
                if (getFragmentHandler() != null) {
                    getFragmentHandler().mo4567d(this);
                }
                this.mLoadState = EnumC1800a.FINISHED;
                onLoadFinished();
                List<Fragment> fragments = getChildFragmentManager().getFragments();
                if (fragments != null) {
                    Iterator<Fragment> it = fragments.iterator();
                    while (it.hasNext()) {
                        ((BaseFragment) it.next()).onParentFragmentLaunchFinished();
                    }
                    return;
                }
                return;
            }
            this.mLoadState = EnumC1800a.WAITING_PARENT;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.mLoadState == EnumC1800a.NONE) {
            checkLoadFinished();
        }
    }

    public void onLoadFinished() {
    }

    @Override // com.sds.android.ttpod.framework.base.ILoadFinished
    public boolean isLoadFinished() {
        return this.mLoadState == EnumC1800a.FINISHED;
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        CommandCenter.getInstance().m4599a(this);
        if (this.mChildFragmentBackStackManager != null) {
            this.mChildFragmentBackStackManager.m4578b();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isViewAccessAble() {
        return this.mViewAccessAble;
    }

    private void onParentFragmentLaunchFinished() {
        if (this.mLoadState == EnumC1800a.WAITING_PARENT) {
            checkLoadFinished();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
    }

    private Map<CommandID, Method> requestCommandMap() {
        HashMap hashMap = new HashMap();
        try {
            onLoadCommandMap(hashMap);
            hashMap.put(CommandID.APP_THEME_CHANGED, ReflectUtils.loadMethod(getClass(), "onThemeChanged", new Class[0]));
            return hashMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void assertCommandMap(Map<CommandID, Method> map) {
        if (EnvironmentUtils.AppConfig.getTestMode()) {
            for (CommandID commandID : map.keySet()) {
                if (commandID.getCommandType().equals(CommandType.TO_MODULE)) {
                    throw new IllegalArgumentException("the CommandID." + commandID.name() + " can not be registered in fragment, because the CommandType is CommandType." + commandID.getCommandType().name() + " in " + getClass().getSimpleName() + " !");
                }
            }
        }
    }

    public final void setChildCurrentFragment(BaseFragment baseFragment) {
        assertChildFragmentBackStackManager();
        this.mCurrentChildFragment = baseFragment;
    }

    public final BaseFragment getChildCurrentFragment() {
        return this.mCurrentChildFragment;
    }

    protected final void setChildPrimaryFragment(BaseFragment baseFragment) {
        assertChildFragmentBackStackManager();
        this.mChildFragmentBackStackManager.m4577b(baseFragment);
    }

    protected final void setLaunchChildFragmentAttr(int i, int i2, int i3) {
        if (this.mChildFragmentBackStackManager == null || this.mChildFragmentBackStackManager.m4573f() != getChildFragmentManager()) {
            this.mChildFragmentBackStackManager = new FragmentBackStackManager(getChildFragmentManager());
        }
        this.mChildFragmentBackStackManager.m4580a(i, i2, i3);
    }

    private IFragmentHandler getFragmentHandler() {
        return this.mFragmentHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setFragmentHandler(IFragmentHandler iFragmentHandler) {
        this.mFragmentHandler = iFragmentHandler;
    }

    public final BaseFragment attachChildFragmentBackStackManager(BaseFragment baseFragment) {
        this.mChildFragmentBackStackManager = baseFragment.getChildFragmentBackStackManager();
        return this;
    }

    public final FragmentBackStackManager getChildFragmentBackStackManager() {
        return this.mChildFragmentBackStackManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void launchFragment(BaseFragment baseFragment) {
        if (isAdded()) {
            baseFragment.setFragmentHandler(((BaseActivity) getActivity()).getFragmentHandler());
            baseFragment.getFragmentHandler().mo4568c(baseFragment);
        }
    }

    protected final void launchChildFragment(BaseFragment baseFragment) {
        assertChildFragmentBackStackManager();
        baseFragment.setFragmentHandler(this.mChildFragmentBackStackManager);
        baseFragment.getFragmentHandler().mo4568c(baseFragment);
    }

    public void finish() {
        if (getFragmentHandler() != null) {
            getFragmentHandler().mo4566e(this);
        }
        CommandCenter.getInstance().m4599a(this);
    }

    protected void switchSubFragment(BaseFragment baseFragment) {
        throw new IllegalStateException("this function must be overrided");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void processBackPressed() {
        if (this.mChildFragmentBackStackManager == null) {
            onBackPressed();
        } else if (this.mCurrentChildFragment != null && !this.mCurrentChildFragment.isChildFragmentBackStackEmpty()) {
            this.mCurrentChildFragment.processBackPressed();
        } else if (this.mChildFragmentBackStackManager.m4581a()) {
            onBackPressed();
        } else {
            this.mChildFragmentBackStackManager.m4576c().onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBackPressed() {
        if (this.mChildFragmentBackStackManager == null || this.mChildFragmentBackStackManager.m4581a()) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                Iterator<Fragment> it = fragments.iterator();
                while (it.hasNext()) {
                    ((BaseFragment) it.next()).onBackPressed();
                }
            }
            finish();
            return;
        }
        this.mChildFragmentBackStackManager.m4576c().finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void showPreviousFragment() {
        if (getFragmentHandler() != null) {
            getFragmentHandler().mo4565g();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void hidePreviousFragment() {
        if (getFragmentHandler() != null) {
            getFragmentHandler().mo4564h();
        }
    }

    public void onThemeLoaded() {
    }

    public void onThemeChanged() {
        if (isViewAccessAble()) {
            onThemeLoaded();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getParent() {
        return getParentFragment() == null ? getActivity() : getParentFragment();
    }

    public final boolean isChildFragmentBackStackEmpty() {
        if (this.mChildFragmentBackStackManager == null) {
            return true;
        }
        if (this.mCurrentChildFragment != null && !this.mCurrentChildFragment.isChildFragmentBackStackEmpty()) {
            return false;
        }
        return this.mChildFragmentBackStackManager.m4581a();
    }

    private void assertChildFragmentBackStackManager() {
        if (EnvironmentUtils.AppConfig.getTestMode() && this.mChildFragmentBackStackManager == null) {
            throw new IllegalStateException("you must call setLaunchChildFragmentAttr(int containerViewRes, int enterAnimRes, int popExitAnimRes) or attachChildFragmentBackStackManager(BaseFragment fragment) first");
        }
    }

    public boolean isSupportOfflineMode() {
        return false;
    }

    public void pageBack() {

    }

    public void initBundle(String str, String str2) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            setArguments(arguments);
        }
        arguments.putString(KEY_PAGE, str);
        if (str2 != null) {
            arguments.putString(KEY_SONG_LIST_ID, String.valueOf(str2));
        }
    }

    public void initBundle(SPage sPage, String str) {
        initBundle(String.valueOf(sPage.getValue()), str);
    }

    public void setPage(SPage sPage) {
        if (SPage.PAGE_NONE.getValue() != sPage.getValue()) {
            setPage(String.valueOf(sPage.getValue()));
        }
    }

    public void setPage(String str) {
        if (!"none".equals(str)) {
            this.mSPage = str;
        }
    }

    public String getPage() {
        return this.mSPage;
    }

    public void updatePage(SPage sPage) {
        updatePage(String.valueOf(sPage.getValue()));
    }

    public void updatePage(String str) {
        setPage(str);

    }



    protected String getDescription() {
        return "";
    }

    public void markAsPage() {
        this.mIsPage = true;
    }

    public boolean isPage() {
        return this.mIsPage;
    }
}
