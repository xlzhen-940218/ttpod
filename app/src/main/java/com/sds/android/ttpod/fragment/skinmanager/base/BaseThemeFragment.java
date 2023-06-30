package com.sds.android.ttpod.fragment.skinmanager.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.soundsearch.EditModeToggle;
import com.sds.android.ttpod.fragment.skinmanager.ThemeViewHolder;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.modules.skin.SkinThumbnailCreator;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import com.sds.android.ttpod.framework.p106a.ViewUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.StateView;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* loaded from: classes.dex */
public abstract class BaseThemeFragment extends BaseFragment implements EditModeToggle, SkinOperateListener {
    private static final int DOWNLOAD_MSG = 1;
    private static final int REFRESH_INTERVAL = 500;
    private static final int REFRESH_MSG = 0;
    private static final String UPDATE_TEMP_SUFFIX = "updatetmp";
    private static DownloadTaskInfo sDownloadingTask;
    protected static String sLastDownloadThemeName;
    protected boolean mCacheMode;
    protected ArrayList<SkinItem> mCachedSkinItems;
    protected CommandID mLoadDataCommandID;
    private View mOfflineModeView;
    protected StateView mStateView;
    protected String mSubClassTag;
    protected C1761a mThemeAdapter;
    protected ArrayList<SkinItem> mThemeData;
    protected ListView mThemeListView;
    protected static HashMap<String, SkinItem> sLocalSkinInfoMap = new HashMap<>();
    protected static HashMap<String, SkinItem> sOnlineSkinInfoMap = new HashMap<>();
    protected static HashMap<String, DownloadTaskInfo> sDownloadingSkinMap = new HashMap<>();
    private static Queue<DownloadTaskInfo> sDownloadingSkinQueue = new LinkedList();
    private static Handler sSkinDownloadHandler = new Handler() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            DownloadTaskInfo downloadTaskInfo;
            switch (message.what) {
                case 1:
                    if ((BaseThemeFragment.sDownloadingTask == null || BaseThemeFragment.sDownloadingTask.getState() == null || BaseThemeFragment.sDownloadingTask.getState().intValue() == 4) && (downloadTaskInfo = (DownloadTaskInfo) BaseThemeFragment.sDownloadingSkinQueue.poll()) != null) {
                        DownloadTaskInfo unused = BaseThemeFragment.sDownloadingTask = downloadTaskInfo;
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_DOWNLOAD_TASK, downloadTaskInfo, Boolean.FALSE));
                        CommandCenter.getInstance().execute(new Command(CommandID.ADD_DOWNLOAD_TASK, downloadTaskInfo));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    protected EditModeToggle mParentEditToggle = null;
    protected boolean mInEditMode = false;
    protected boolean mEnableRefreshProgressbar = true;
    protected int mInternalThemeCount = -1;
    private boolean mIsForLocal = false;
    private int mDownloadingCount = 0;
    private boolean mLoadingTheme = false;
    private Handler mRefreshHandler = new Handler() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    BaseThemeFragment.this.mThemeAdapter.notifyDataSetChanged();
                    if (BaseThemeFragment.sDownloadingTask != null) {
                        BaseThemeFragment.this.mRefreshHandler.sendEmptyMessageDelayed(0, 500L);
                        BaseThemeFragment.sSkinDownloadHandler.sendEmptyMessage(1);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mOnItemClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SkinItem skinItem = (SkinItem) view.getTag();
            if (skinItem != null && BaseThemeFragment.this.mThemeAdapter.f5554d != skinItem) {
                BaseThemeFragment.this.onThemeItemSelected(skinItem);
            }
        }
    };
    private View.OnLongClickListener mOnItemLongClickListener = new View.OnLongClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.4
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (!BaseThemeFragment.this.mInEditMode && BaseThemeFragment.this.mParentEditToggle != null && BaseThemeFragment.this.hasEditableContent()) {
                BaseThemeFragment.this.mParentEditToggle.toggleEditMode();
            }
            return true;
        }
    };
    private StateView.InterfaceC2247a mOnRetryRequestListener = new StateView.InterfaceC2247a() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.6
        @Override // com.sds.android.ttpod.widget.StateView.InterfaceC2247a
        /* renamed from: a */
        public void mo1450a() {
            BaseThemeFragment.this.loadData();
        }
    };
    private View.OnClickListener mUpdateClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SkinItem skinItem = (SkinItem) view.getTag();
            String m3565g = skinItem.getTitle();
            String m3569c = skinItem.getBrand();
            if (m3565g != null && m3569c != null) {
                String skinInfoMapKey = BaseThemeFragment.this.getSkinInfoMapKey(m3565g);
                if (!BaseThemeFragment.sDownloadingSkinMap.containsKey(m3565g)) {
                    //ThemeStatistic.m4888f(m3565g);
                    BaseThemeFragment.this.tryDownloadSkin(BaseThemeFragment.sOnlineSkinInfoMap.get(skinInfoMapKey), true);
                }
            }
        }
    };

    protected abstract CommandID getLoadDataCommandID();

    protected abstract String getStatisticOrigin();

    protected abstract ArrayList<SkinItem> loadDataFromCache();

    protected abstract void onThemeItemSelected(SkinItem skinItem);

    static /* synthetic */ int access$408(BaseThemeFragment baseThemeFragment) {
        int i = baseThemeFragment.mDownloadingCount;
        baseThemeFragment.mDownloadingCount = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getSkinInfoMapKey(SkinItem skinItem) {
        if (skinItem == null) {
            return null;
        }
        return skinItem.getTitle();
    }

    protected String getSkinInfoMapKey(String str) {
        return str;
    }

    protected static String getRawSkinPath() {
        return SkinUtils.m4647a(ThemeUtils.m8163b());
    }

    protected static void saveSkinToSystem(String str, int i) {
        CommandCenter.getInstance().execute(new Command(CommandID.SET_SKIN, str, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performSkinDownloaded(SkinItem skinItem) {
        ThemeListObserver.getInstance().m5320a(skinItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performSkinDownloadError(SkinItem skinItem) {
        ThemeListObserver.getInstance().m5315b(skinItem);
    }

    private void performSkinDownloading(SkinItem skinItem) {
        ThemeListObserver.getInstance().m5314c(skinItem);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void performSkinDeleted(SkinItem skinItem) {
        ThemeListObserver.getInstance().m5313d(skinItem);
    }

    private void performCurrentSkinChanged(String str) {
        ThemeListObserver.getInstance().m5319a(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void performSkinInfoLoaded() {
        ThemeListObserver.getInstance().m5317b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void performSkinItemStateChange(String str, int i) {
        ThemeListObserver.getInstance().m5318a(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAdapterDataSource(ArrayList<SkinItem> arrayList) {
        this.mThemeAdapter.m5339a(arrayList);
        if (!this.mIsForLocal) {
            setOnlineSkinInfoMap(arrayList);
        }
        this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnlineSkinInfoMap(ArrayList<SkinItem> arrayList) {
        Iterator<SkinItem> it = arrayList.iterator();
        while (it.hasNext()) {
            SkinItem next = it.next();
            sOnlineSkinInfoMap.put(next.getTitle(), new SkinItem(next.getOnlineSkinItem()));
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initThemeAdapter();
        ThemeListObserver.getInstance().m5321a(this);
        this.mLoadDataCommandID = getLoadDataCommandID();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ThemeListObserver.getInstance().m5316b(this);
        this.mRefreshHandler.removeCallbacksAndMessages(null);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_base_theme_layout, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStateView = (StateView) view.findViewById(R.id.theme_loadingview);
        this.mStateView.setOnRetryRequestListener(this.mOnRetryRequestListener);
        this.mThemeListView = (ListView) view.findViewById(R.id.theme_listview);
        initListViewHeader();
        initListViewFooter();
        this.mThemeListView.setAdapter((ListAdapter) this.mThemeAdapter);
        this.mThemeListView.setOnScrollListener(getOnScrollListener());
        if (isShowOfflineModeView()) {
            this.mOfflineModeView = OfflineModeUtils.m8254a((View) this.mStateView);
            return;
        }
        if (this.mOfflineModeView != null) {
            this.mOfflineModeView.setVisibility(View.GONE);
        }
        showLoadingView();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        loadData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.loadMethod(cls, "updateThemeDownloadingState", DownloadTaskInfo.class));
        map.put(CommandID.UPDATE_BACKGROUND, ReflectUtils.loadMethod(cls, "updateBackground", Drawable.class));
    }

    public void updateThemeDownloadingState(DownloadTaskInfo downloadTaskInfo) {
        if (DownloadTaskInfo.TYPE_SKIN.equals(downloadTaskInfo.getType())) {
            getAdapter().m5341a(downloadTaskInfo);
        }
    }

    public void updateBackground(Drawable drawable) {
        this.mLoadingTheme = false;
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof EditModeToggle) {
            this.mParentEditToggle = (EditModeToggle) activity;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.mThemeAdapter != null && this.mThemeAdapter.getCount() > 0 && this.mThemeAdapter.m5350a()) {
            this.mThemeAdapter.notifyDataSetChanged();
        }
        if (this.mIsForLocal && !z && isInEditMode()) {
            toggleEditMode();
        }
    }

    protected void showLoadingView() {
        if (this.mThemeAdapter.getCount() == 0) {
            this.mStateView.setState(StateView.EnumC2248b.LOADING);
        }
    }

    protected void loadData() {
        this.mCachedSkinItems = loadDataFromCache();
        if (this.mCachedSkinItems == null || this.mCachedSkinItems.size() == 0) {
            this.mStateView.setState(StateView.EnumC2248b.LOADING);
        } else {
            this.mCacheMode = true;
            setAdapterDataSource(this.mCachedSkinItems);
            refreshEditButton();
        }
        CommandCenter.getInstance().execute(new Command(this.mLoadDataCommandID, new Object[0]));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mStateView != null) {
            this.mStateView.onThemeLoaded();
        }
        this.mThemeAdapter.notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public boolean isInEditMode() {
        return this.mInEditMode;
    }

    public void toggleEditMode() {
        if (this.mThemeAdapter != null) {
            this.mThemeAdapter.m5350a();
            this.mThemeAdapter.notifyDataSetChanged();
        }
    }

    public boolean hasEditableContent() {
        return false;
    }

    protected void refreshEditButton() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkSkinItem(SkinItem skinItem) {
        if (!this.mLoadingTheme && skinItem != null && this.mThemeAdapter.m5337b(skinItem)) {
            //ThemeStatistic.m4901a();
            this.mLoadingTheme = true;
            refreshEditButton();
            saveSkinToSystem(skinItem.getPath(), skinItem.getType());
            Preferences.m2872i("follow_skin");
            performCurrentSkinChanged(skinItem.getPath());
        }
    }

    protected void initThemeAdapter() {
    }

    protected AbsListView.OnScrollListener getOnScrollListener() {
        return new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.5
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i) {
                    case 0:
                        BaseThemeFragment.this.mEnableRefreshProgressbar = true;
                        return;
                    case 1:
                        BaseThemeFragment.this.mEnableRefreshProgressbar = false;
                        return;
                    default:
                        return;
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C1761a getAdapter() {
        return this.mThemeAdapter;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.SkinOperateListener
    public void onCurrentSkinChanged(String str) {
        if (!str.equals(this.mThemeAdapter.m5338b())) {
            this.mThemeAdapter.m5350a();
            this.mThemeAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.SkinOperateListener
    public void onSkinDeleted(SkinItem skinItem) {
        this.mThemeAdapter.notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.SkinOperateListener
    public void onSkinDownloaded(SkinItem skinItem) {
        this.mThemeAdapter.notifyDataSetChanged();
        sDownloadingTask = null;
        sSkinDownloadHandler.sendEmptyMessage(1);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.SkinOperateListener
    public void onSkinDownloading(SkinItem skinItem) {
        this.mRefreshHandler.removeMessages(0);
        this.mRefreshHandler.sendEmptyMessage(0);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.SkinOperateListener
    public void onSkinDownloadError(SkinItem skinItem) {
        this.mThemeAdapter.notifyDataSetChanged();
        sDownloadingTask = null;
        sSkinDownloadHandler.sendEmptyMessage(1);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.SkinOperateListener
    public void onSkinInfoLoaded() {
        this.mThemeAdapter.notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.SkinOperateListener
    public void onSkinItemStateChange(String str, int i) {
        updateSkinInfoForThemeName(str, i);
    }

    private boolean isShowOfflineModeView() {
        return isSupportOfflineMode() && OfflineModeUtils.m8256a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void tryDownloadSkin(SkinItem skinItem, boolean z) {
        if (!EnvironmentUtils.DeviceConfig.isConnected()) {
            PopupsUtils.m6760a((int) R.string.shake_error_hint);
        } else if (FileUtils.m8414b(skinItem.getPath()) && !z) {
            PopupsUtils.m6760a((int) R.string.skin_file_already_existed);
        } else {
            String m3565g = skinItem.getTitle();
            if (sDownloadingSkinMap.containsKey(m3565g)) {
                PopupsUtils.m6760a((int) R.string.downloading_already);
                return;
            }
            SkinItem skinItem2 = sOnlineSkinInfoMap.get(m3565g);
            OnlineSkinItem m3566f = skinItem2 != null ? skinItem2.getOnlineSkinItem() : null;
            if (m3566f != null) {
                String skinUrl = m3566f.getSkinUrl();
                String str = TTPodConfig.getSkinPath() + File.separator + m3565g + ".tsk";
                if (z) {
                    str = str + UPDATE_TEMP_SUFFIX;
                }
                if (FileUtils.m8414b(str)) {
                    FileUtils.exists(str);
                }
                DownloadTaskInfo m4760a = DownloadUtils.m4760a(skinUrl, str, 0L, m3565g, DownloadTaskInfo.TYPE_SKIN, false, getStatisticOrigin());
                sDownloadingSkinQueue.offer(m4760a);
                performSkinItemStateChange(m3565g, 3);
                getAdapter().m5342a(skinItem, m4760a);
                sSkinDownloadHandler.sendEmptyMessage(1);
                performSkinDownloading(skinItem2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLocalSkinInfoMap(ArrayList<SkinItem> arrayList) {
        sLocalSkinInfoMap.clear();
        if (arrayList != null) {
            Iterator<SkinItem> it = arrayList.iterator();
            while (it.hasNext()) {
                SkinItem next = it.next();
                sLocalSkinInfoMap.put(next.getSkinKey(), next);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SkinItem getSkinItemForThemeName(String str) {
        ArrayList<SkinItem> themeDataList = getThemeDataList();
        if (themeDataList == null || str == null) {
            return null;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= themeDataList.size()) {
                return null;
            }
            SkinItem skinItem = themeDataList.get(i2);
            if (!skinItem.getTitle().equals(str)) {
                i = i2 + 1;
            } else {
                return skinItem;
            }
        }
    }

    protected void updateSkinInfoForThemeName(String str, int i) {
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public boolean isSupportOfflineMode() {
        return !this.mIsForLocal;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean checkIfReloadData(ArrayList<SkinItem> arrayList) {
        return !ThemeUtils.m8164a(arrayList, this.mThemeAdapter != null ? this.mThemeAdapter.m5332c() : null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArrayList<SkinItem> getThemeDataList() {
        if (this.mThemeAdapter != null) {
            return this.mThemeAdapter.m5332c();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setForLocal() {
        this.mIsForLocal = true;
    }

    protected void initListViewHeader() {
    }

    protected void initListViewFooter() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean checkUpdateForSkin(SkinItem skinItem) {
        SkinItem skinItem2 = sOnlineSkinInfoMap.get(skinItem.getTitle());
        SkinItem skinItem3 = sLocalSkinInfoMap.get(skinItem.getSkinKey());
        if (skinItem2 == null || skinItem3 == null) {
            return false;
        }
        return skinVersionCompare(skinItem2.getVersion(), skinItem3.getVersion());
    }

    private boolean skinVersionCompare(String str, String str2) {
        if (str2 == null && str == null) {
            return false;
        }
        if (str2 == null || str != null) {
            if (str2 != null || str == null) {
                return str.compareTo(str2) > 0;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLocalUnSelectedSkinItem(SkinItem skinItem) {
        return (skinItem == null || skinItem.getType() != 0 || this.mThemeAdapter.m5344a(skinItem)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyDataSetChanged() {
        if (this.mThemeAdapter != null) {
            this.mThemeAdapter.notifyDataSetChanged();
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment$a */
    /* loaded from: classes.dex */
    public class C1761a extends BaseAdapter {

        /* renamed from: a */
        private LayoutInflater f5551a;

        /* renamed from: b */
        protected final int f5552b;

        /* renamed from: c */
        protected final int f5553c;

        /* renamed from: d */
        protected SkinItem f5554d;

        /* renamed from: e */
        protected String f5555e = null;

        public C1761a() {
            m5350a();
            this.f5551a = LayoutInflater.from(BaseThemeFragment.this.getActivity());
            this.f5552b = SkinThumbnailCreator.f6693a;
            this.f5553c = SkinThumbnailCreator.f6694c;
        }

        /* renamed from: a */
        public boolean m5350a() {
            String str = this.f5555e;
            this.f5555e = BaseThemeFragment.getRawSkinPath();
            return !this.f5555e.equals(str);
        }

        /* renamed from: b */
        public String m5338b() {
            return this.f5555e;
        }

        /* renamed from: a */
        public boolean m5344a(SkinItem skinItem) {
            return this.f5555e.equals(skinItem.getPath());
        }

        /* renamed from: a */
        public void m5339a(ArrayList<SkinItem> arrayList) {
            if (arrayList != null) {
                m5329c(arrayList);
                BaseThemeFragment.this.mThemeData = arrayList;
                notifyDataSetChanged();
            }
        }

        /* renamed from: b */
        public void m5333b(ArrayList<SkinItem> arrayList) {
            if (arrayList != null) {
                m5329c(arrayList);
                BaseThemeFragment.this.mThemeData.addAll(arrayList);
                if (BaseThemeFragment.this.mDownloadingCount > 0) {
                    BaseThemeFragment.this.onSkinDownloading(null);
                }
                notifyDataSetChanged();
            }
        }

        /* renamed from: c */
        public ArrayList<SkinItem> m5332c() {
            return BaseThemeFragment.this.mThemeData;
        }

        /* renamed from: c */
        private void m5329c(ArrayList<SkinItem> arrayList) {
            Iterator<SkinItem> it = arrayList.iterator();
            while (it.hasNext()) {
                SkinItem next = it.next();
                String m3564h = next.getFileName();
                m5327d(next);
                if (BaseThemeFragment.sDownloadingSkinMap.containsKey(m3564h)) {
                    next.setType(3);
                    BaseThemeFragment.access$408(BaseThemeFragment.this);
                }
            }
        }

        /* renamed from: d */
        private void m5327d(SkinItem skinItem) {
            String m3564h = skinItem.getFileName();
            if (BaseThemeFragment.sDownloadingSkinMap.containsKey(m3564h)) {
                String savePath = BaseThemeFragment.sDownloadingSkinMap.get(m3564h).getSavePath();
                if (FileUtils.m8419a(savePath) && !savePath.endsWith(BaseThemeFragment.UPDATE_TEMP_SUFFIX)) {
                    skinItem.setType(0);
                    BaseThemeFragment.sDownloadingSkinMap.remove(m3564h);
                }
            }
        }

        /* renamed from: b */
        public boolean m5337b(SkinItem skinItem) {
            String m3571b = skinItem.getPath();
            if ((3 == skinItem.getType()) || m3571b == null || m3571b.equals(this.f5555e)) {
                return false;
            }
            this.f5554d = skinItem;
            this.f5555e = skinItem.getPath();
            notifyDataSetChanged();
            //ThemeStatistic.m4887g();
            return true;
        }

        /* renamed from: d */
        public SkinItem m5328d() {
            return this.f5554d;
        }

        /* renamed from: c */
        public void m5331c(SkinItem skinItem) {
            BaseThemeFragment.this.mThemeData.remove(skinItem);
            ImageCacheUtils.m4739b(skinItem.getPath(), this.f5552b, this.f5553c);
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public SkinItem getItem(int i) {
            if (BaseThemeFragment.this.mThemeData == null || i >= BaseThemeFragment.this.mThemeData.size()) {
                return null;
            }
            return BaseThemeFragment.this.mThemeData.get(i);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return (int) Math.ceil((BaseThemeFragment.this.mThemeData != null ? BaseThemeFragment.this.mThemeData.size() : 0) / 3.0d);
        }

        /* renamed from: e */
        public int m5326e() {
            if (BaseThemeFragment.this.mThemeData != null) {
                return BaseThemeFragment.this.mThemeData.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = m5345a(viewGroup);
            }
            return m5346a(view, i);
        }

        /* renamed from: a */
        private View m5346a(View view, int i) {
            ThemeViewHolder[] themeViewHolderArr = (ThemeViewHolder[]) view.getTag();
            m5347a(m5348a(i, 0), themeViewHolderArr[0]);
            m5347a(m5348a(i, 1), themeViewHolderArr[1]);
            m5347a(m5348a(i, 2), themeViewHolderArr[2]);
            return view;
        }

        /* renamed from: a */
        private int m5348a(int i, int i2) {
            return (i * 3) + i2;
        }

        /* renamed from: a */
        protected View m5345a(ViewGroup viewGroup) {
            View inflate = this.f5551a.inflate(R.layout.theme_list_item, viewGroup, false);
            inflate.setTag(new ThemeViewHolder[]{new ThemeViewHolder(inflate.findViewById(R.id.theme_item1)), new ThemeViewHolder(inflate.findViewById(R.id.theme_item2)), new ThemeViewHolder(inflate.findViewById(R.id.theme_item3))});
            return inflate;
        }

        /* renamed from: a */
        protected void m5347a(int i, ThemeViewHolder themeViewHolder) {
            SkinItem item = getItem(i);
            ThemeManager.m3269a(themeViewHolder.m5387d(), ThemeElement.COMMON_CONTENT_TEXT);
            View m5383h = themeViewHolder.m5383h();
            if (item != null) {
                m5383h.setVisibility(View.VISIBLE);
                m5383h.setTag(item);
                m5383h.setOnClickListener(BaseThemeFragment.this.mOnItemClickListener);
                m5383h.setOnLongClickListener(BaseThemeFragment.this.mOnItemLongClickListener);
                mo5325a(item, themeViewHolder.m5389b());
                mo5336b(item, themeViewHolder.m5390a());
                mo5324a(item, themeViewHolder);
                m5343a(item, themeViewHolder.m5387d());
                m5330c(item, themeViewHolder.m5382i());
                return;
            }
            m5383h.setVisibility(View.INVISIBLE);
        }

        /* renamed from: c */
        private void m5330c(SkinItem skinItem, ImageView imageView) {
            if (4 != skinItem.getType()) {
                imageView.setVisibility(View.GONE);
            } else {
                ViewUtils.m4640a(skinItem.getDateCreated(), imageView);
            }
        }

        /* renamed from: a */
        protected void mo5325a(SkinItem skinItem, ImageView imageView) {
        }

        /* renamed from: b */
        protected void mo5336b(SkinItem skinItem, ImageView imageView) {
            if (imageView != null) {
                int i = 4;
                if (this.f5555e.equals(skinItem.getPath())) {
                    this.f5554d = skinItem;
                    i = 0;
                }
                imageView.setVisibility(i);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public void mo5324a(SkinItem skinItem, ThemeViewHolder themeViewHolder) {
            int i;
            int i2 = 4;
            int i3 = 0;
            ProgressBar m5388c = themeViewHolder.m5388c();
            TextView m5386e = themeViewHolder.m5386e();
            View m5384g = themeViewHolder.m5384g();
            View m5381j = themeViewHolder.m5381j();
            m5381j.setTag(skinItem);
            m5381j.setVisibility(View.GONE);
            m5381j.setOnClickListener(null);
            View m5385f = themeViewHolder.m5385f();
            if (3 == skinItem.getType()) {
                if (skinItem.getFileName().equals(BaseThemeFragment.sDownloadingTask != null ? BaseThemeFragment.sDownloadingTask.getFileName() : null)) {
                    BaseThemeFragment.sDownloadingTask.setDownloadLength(((Integer) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_TASK_DOWNLOADED_LENGTH, BaseThemeFragment.sDownloadingTask), Integer.class)).intValue());
                    if (BaseThemeFragment.this.mEnableRefreshProgressbar) {
                        m5388c.setProgress(BaseThemeFragment.sDownloadingTask.getDownloadProgress().intValue());
                    }
                } else {
                    m5388c.setProgress(0);
                }
                m5385f.setVisibility(View.GONE);
                i = 0;
            } else if (4 == skinItem.getType()) {
                m5388c.setProgress(0);
                m5386e.setText(skinItem.getOnlineSkinItem().getFileSizeStr());
                m5385f.setBackgroundResource(R.drawable.img_background_skin_start_download);
                m5385f.setVisibility(View.VISIBLE);
                i = 4;
                i2 = 0;
            } else {
                SkinItem skinItem2 = BaseThemeFragment.sOnlineSkinInfoMap.get(skinItem.getTitle());
                SkinItem skinItem3 = BaseThemeFragment.sLocalSkinInfoMap.get(skinItem.getSkinKey());
                if (!BaseThemeFragment.this.mInEditMode && skinItem2 != null && skinItem3 != null) {
                    if (BaseThemeFragment.this.checkUpdateForSkin(skinItem)) {
                        m5388c.setProgress(0);
                        m5386e.setText(BaseThemeFragment.this.getResources().getString(R.string.update));
                        m5385f.setBackgroundResource(R.drawable.img_background_skin_update);
                        m5385f.setVisibility(View.VISIBLE);
                        m5381j.setVisibility(View.VISIBLE);
                        m5381j.setOnClickListener(BaseThemeFragment.this.mUpdateClickListener);
                        i = 4;
                        i2 = 0;
                    } else {
                        m5385f.setVisibility(View.GONE);
                        i = 4;
                        i3 = 8;
                    }
                } else {
                    m5385f.setVisibility(View.GONE);
                    i = 4;
                    i2 = 0;
                    i3 = 8;
                }
            }
            m5386e.setVisibility(i2);
            m5388c.setVisibility(i);
            m5384g.setVisibility(i3);
        }

        /* renamed from: a */
        private void m5343a(SkinItem skinItem, TextView textView) {
            if (3 == skinItem.getType() && skinItem.getOnlineSkinItem() != null) {
                textView.setText(skinItem.getOnlineSkinItem().getName());
            } else {
                textView.setText(skinItem.getTitle());
            }
            textView.setVisibility(View.VISIBLE);
        }

        /* renamed from: a */
        public void m5341a(DownloadTaskInfo downloadTaskInfo) {
            int intValue = downloadTaskInfo.getState().intValue();
            String fileName = downloadTaskInfo.getFileName();
            SkinItem skinItem = new SkinItem(BaseThemeFragment.sOnlineSkinInfoMap.get(fileName));
            if (intValue == 4) {
                BaseThemeFragment.this.updateSkinInfoForThemeName(fileName, 0);
                m5334b(fileName);
                if (m5340a(fileName)) {
                    m5335b(downloadTaskInfo);
                    PopupsUtils.m6721a(fileName + " " + BaseThemeFragment.this.getResources().getString(R.string.download_finished));
                }
                if (fileName.equals(BaseThemeFragment.sLastDownloadThemeName)) {
                    BaseThemeFragment.this.checkSkinItem(skinItem);
                    BaseThemeFragment.sLastDownloadThemeName = null;
                }
                BaseThemeFragment.this.performSkinDownloaded(skinItem);
            } else if (intValue == 3 || intValue == 5) {
                if (FileUtils.m8419a(downloadTaskInfo.getSavePath())) {
                    BaseThemeFragment.this.performSkinItemStateChange(fileName, 0);
                } else {
                    BaseThemeFragment.this.performSkinItemStateChange(fileName, 4);
                }
                BaseThemeFragment.sLastDownloadThemeName = null;
                m5340a(fileName);
                BaseThemeFragment.this.performSkinDownloadError(skinItem);
            }
        }

        /* renamed from: b */
        private void m5335b(DownloadTaskInfo downloadTaskInfo) {
            String savePath = downloadTaskInfo.getSavePath();
            if (savePath.endsWith(BaseThemeFragment.UPDATE_TEMP_SUFFIX)) {
                String substring = savePath.substring(0, savePath.length() - BaseThemeFragment.UPDATE_TEMP_SUFFIX.length());
                FileUtils.exists(substring);
                FileUtils.m8410c(savePath, substring);
                ImageCacheUtils.m4739b(substring, SkinThumbnailCreator.f6693a, SkinThumbnailCreator.f6694c);
            }
        }

        /* renamed from: b */
        private void m5334b(String str) {
            SkinItem skinItem = new SkinItem(BaseThemeFragment.sOnlineSkinInfoMap.get(str));
            BaseThemeFragment.sLocalSkinInfoMap.put(skinItem.getSkinKey(), skinItem);
        }

        /* renamed from: a */
        public void m5342a(SkinItem skinItem, DownloadTaskInfo downloadTaskInfo) {
            BaseThemeFragment.sDownloadingSkinMap.put(skinItem.getFileName(), downloadTaskInfo);
        }

        /* renamed from: a */
        public boolean m5340a(String str) {
            return BaseThemeFragment.sDownloadingSkinMap.remove(str) != null;
        }
    }
}
