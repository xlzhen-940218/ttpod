package com.sds.android.ttpod.fragment.main;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.sds.android.cloudapi.ttpod.result.FindSongModuleResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.OnPageSelectedListener;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.utils.FindSongFragmentFactory;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes.dex */
public class FindSongFragment extends BaseFragment implements OnPageSelectedListener {
    private static final int DATA_EXPIRED_TIME_MILLIS = 3600000;
    public static final String HOST_LIST_ORIGIN = "FindSongFragment";
    private static final String TAG = "FindSongFragment";
    private DragUpdateListView mDragUpdateListView;
    private NetworkLoadView mNetworkLoadingView;
    private FindSongModuleResult mResult;
    private View mRootView;
    private boolean mReloadTheme = true;
    private boolean mIsRequesting = false;
    private long mLastDataVersion = 0;
    private long mLastDoRequestTimeMillis = 0;
    private LinkedList<Fragment> mAddedFragments = new LinkedList<>();
    private final BaseListAdapter<Integer> mEmptyAdapter = new BaseListAdapter<Integer>(getActivity(), new ArrayList()) { // from class: com.sds.android.ttpod.fragment.main.FindSongFragment.1
        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void buildDataUI(View view, Integer num, int i) {
        }
    };
    private HandlerC1457b mMainHandler = new HandlerC1457b();

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mMainHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_ONLINE_FIND_SONG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        map.put(CommandID.UPDATE_RECOMMEND_CONTENT, ReflectUtils.m8375a(getClass(), "updateRecommendContent", FindSongModuleResult.class));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.findsongfragment_listview, viewGroup, false);
            View inflate = layoutInflater.inflate(R.layout.finsong_head, (ViewGroup) null, false);
            this.mDragUpdateListView = (DragUpdateListView) this.mRootView.findViewById(R.id.drag_update_list_view);
            this.mDragUpdateListView.addHeaderView(inflate);
            this.mDragUpdateListView.setAdapter((ListAdapter) this.mEmptyAdapter);
            this.mDragUpdateListView.setEnableDragUpdate(false);
            this.mDragUpdateListView.setOnStartRefreshListener(new DragUpdateHelper.InterfaceC2273c() { // from class: com.sds.android.ttpod.fragment.main.FindSongFragment.2
                @Override // com.sds.android.ttpod.widget.dragupdatelist.DragUpdateHelper.InterfaceC2273c
                public void onStartRefreshEvent() {
                    if (EnvironmentUtils.DeviceConfig.isConnected()) {
                        FindSongFragment.this.doRequest();
                        return;
                    }
                    PopupsUtils.m6760a((int) R.string.network_unavailable);
                    FindSongFragment.this.mDragUpdateListView.m1336b();
                }
            });
            loadDragRefreshTitleTheme(this.mDragUpdateListView);
            this.mNetworkLoadingView = (NetworkLoadView) this.mRootView.findViewById(R.id.loading_view);
            this.mNetworkLoadingView.setHideStyle(8);
            this.mNetworkLoadingView.setStartAnimationUntilVisibleToUser(true);
            this.mNetworkLoadingView.setOnStartLoadingListener(new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.FindSongFragment.3
                @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
                /* renamed from: a */
                public void mo1678a() {
                    FindSongFragment.this.doRequest();
                }
            });
            this.mNetworkLoadingView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
        }
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        doRequestUntilDataExpired();
    }

    @Override // com.sds.android.ttpod.fragment.OnPageSelectedListener
    public void onPageSelected() {
        if (this.mNetworkLoadingView != null) {
            this.mNetworkLoadingView.setIsVisibleToUser(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRequest() {
        if (!this.mIsRequesting) {
            this.mIsRequesting = true;
            this.mLastDoRequestTimeMillis = System.currentTimeMillis();
            CommandCenter.getInstance().execute(new Command(CommandID.GET_RECOMMEND_CONTENT, Long.valueOf(this.mLastDataVersion)));
        }
    }

    private void doRequestUntilDataExpired() {
        if (Math.abs(System.currentTimeMillis() - this.mLastDoRequestTimeMillis) > 3600000) {
            doRequest();
            this.mDragUpdateListView.m1337a();
        }
    }

    public void updateRecommendContent(FindSongModuleResult findSongModuleResult) {
        this.mResult = findSongModuleResult;
        ResultHelper.m5665a(this, findSongModuleResult, new C14534());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.fragment.main.FindSongFragment$4 */
    /* loaded from: classes.dex */
    public class C14534 implements ResultHelper.InterfaceC1510a<FindSongModuleResult> {
        C14534() {
        }

        @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5564a(final FindSongModuleResult findSongModuleResult) {
            new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.FindSongFragment.4.1
                @Override // java.lang.Runnable
                public void run() {
                    FindSongFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.FindSongFragment.4.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            FindSongFragment.this.updateView(findSongModuleResult);
                        }
                    });
                }
            }, 200L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateView(FindSongModuleResult findSongModuleResult) {
        if (findSongModuleResult != null && isViewAccessAble()) {
            this.mIsRequesting = false;
            this.mDragUpdateListView.m1336b();
            if (findSongModuleResult.isSuccess()) {
                this.mNetworkLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
                long version = findSongModuleResult.getVersion();
                if (version > 0 && this.mLastDataVersion == version) {
                    PopupsUtils.m6760a((int) R.string.already_latest);
                    return;
                }
                this.mLastDataVersion = version;
                clearFindSongSubFragment();
                addFindSongSubFragment(findSongModuleResult);
                this.mDragUpdateListView.setEnableDragUpdate(true);
                return;
            }
            PopupsUtils.m6760a((int) R.string.network_unavailable);
            if (this.mLastDataVersion == 0) {
                this.mNetworkLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
            }
        }
    }

    private void clearFindSongSubFragment() {
        if (!this.mAddedFragments.isEmpty()) {
            for (int size = this.mAddedFragments.size() - 1; size >= 0; size--) {
                getChildFragmentManager().beginTransaction().remove(this.mAddedFragments.get(size)).commitAllowingStateLoss();
            }
            this.mAddedFragments.clear();
        }
    }

    private void addFindSongSubFragment(FindSongModuleResult findSongModuleResult) {
        HandlerThread handlerThread = new HandlerThread("addFindSongSubFragment");
        handlerThread.start();
        HandlerC1456a handlerC1456a = new HandlerC1456a(findSongModuleResult, handlerThread.getLooper());
        findSongModuleResult.moveTo(-1);
        handlerC1456a.obtainMessage(0).sendToTarget();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        if (isViewAccessAble() && this.mReloadTheme) {
            this.mReloadTheme = false;
            loadTheme(this.mNetworkLoadingView);
            loadDragRefreshTitleTheme(this.mDragUpdateListView);
            ThemeManager.m3269a(this.mDragUpdateListView, ThemeElement.BACKGROUND_MASK);
        }
    }

    private void loadTheme(ThemeManager.InterfaceC2019b interfaceC2019b) {
        if (interfaceC2019b != null) {
            interfaceC2019b.onThemeLoaded();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onThemeChanged() {
        this.mReloadTheme = true;
        super.onThemeChanged();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updateRecommendContent(this.mResult);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mNetworkLoadingView = null;
            this.mReloadTheme = true;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!this.mAddedFragments.isEmpty()) {
            for (int size = this.mAddedFragments.size() - 1; size >= 0; size--) {
                this.mAddedFragments.get(size).setUserVisibleHint(z);
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public boolean isSupportOfflineMode() {
        return true;
    }

    private void loadDragRefreshTitleTheme(DragUpdateListView dragUpdateListView) {
        ColorStateList m3254c = ThemeManager.m3254c(ThemeElement.COMMON_CONTENT_TEXT);
        ColorStateList valueOf = ColorStateList.valueOf(-1);
        if (m3254c == null) {
            m3254c = valueOf;
        }
        dragUpdateListView.setLoadingTitleColor(m3254c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.FindSongFragment$a */
    /* loaded from: classes.dex */
    public final class HandlerC1456a extends Handler {

        /* renamed from: b */
        private final FindSongModuleResult f5064b;

        public HandlerC1456a(FindSongModuleResult findSongModuleResult, Looper looper) {
            super(looper);
            this.f5064b = findSongModuleResult;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i;
            if (FindSongFragment.this.mMainHandler != null) {
                int i2 = 0;
                while (this.f5064b.hasNext()) {
                    Fragment m8270a = FindSongFragmentFactory.m8270a(FindSongFragment.this.getActivity(), this.f5064b.next());
                    if (m8270a != null) {
                        ((FindSongBaseViewFragment) m8270a).setUserType(this.f5064b.getUserType());
                        Message.obtain(FindSongFragment.this.mMainHandler, 1, m8270a).sendToTarget();
                        i = i2 + 1;
                        if (i == 2) {
                            break;
                        }
                    } else {
                        i = i2;
                    }
                    i2 = i;
                }
                if (this.f5064b.hasNext()) {
                    sendMessageDelayed(obtainMessage(0), 300L);
                } else {
                    getLooper().quit();
                }
            }
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.FindSongFragment$b */
    /* loaded from: classes.dex */
    private class HandlerC1457b extends Handler {
        private HandlerC1457b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                Fragment fragment = (Fragment) message.obj;
                FindSongFragment.this.getChildFragmentManager().beginTransaction().add(R.id.find_song_fragment_container, fragment, fragment.getClass().getSimpleName()).commitAllowingStateLoss();
                FindSongFragment.this.mAddedFragments.add(fragment);
            }
        }
    }
}
