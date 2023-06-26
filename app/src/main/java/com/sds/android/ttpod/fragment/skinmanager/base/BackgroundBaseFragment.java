package com.sds.android.ttpod.fragment.skinmanager.base;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.core.download.Manager;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.skinmanager.ThemeViewHolder;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinThumbnailCreator;
import com.sds.android.ttpod.framework.modules.theme.BackgroundItem;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.ViewUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class BackgroundBaseFragment extends SlidingClosableFragment {
    private static final String BACKGROUND_DOWNLOAD_ISSUE = "background_download_issue";
    private static final int MAX_DOWNLOAD_COUNT = 4;
    private static final int REFRESH_INTERVAL = 500;
    private static final int REFRESH_MSG = 0;
    private static Manager mDownloadManager;
    protected ListView mBackgroundListView;
    private View mOfflineModeView;
    protected StateView mStateView;
    private static BackgroundItem sLastRequestItem = null;
    protected static ArrayList sBkgDownloadListenerList = new ArrayList();
    protected static ArrayList<BkgEditListener> sBkgEditListenerList = new ArrayList<>();
    protected C1752a mBackgroundAdapter = null;
    protected boolean mInEditMode = false;
    protected boolean mIsLoading = false;
    private Handler mRefreshHandler = new Handler() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    BackgroundBaseFragment.this.mRefreshHandler.removeMessages(0);
                    BackgroundBaseFragment.this.mBackgroundAdapter.notifyDataSetChanged();
                    if (BackgroundBaseFragment.this.isNeedRefresh()) {
                        BackgroundBaseFragment.this.mRefreshHandler.sendEmptyMessageDelayed(0, 500L);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mOnBackgroundClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BackgroundItem backgroundItem = (BackgroundItem) view.getTag();
            if (backgroundItem != null && !backgroundItem.toString().equals(BackgroundBaseFragment.this.mBackgroundAdapter.f5533a)) {
                BackgroundBaseFragment.this.checkNormalStateItem(backgroundItem);
            }
        }
    };

    protected abstract void initListViewFooter();

    protected abstract void initListViewHeader();

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBackgroundAdapter = initAdapter();
        mDownloadManager = Manager.m8744a();
        if (!mDownloadManager.m8743a(BACKGROUND_DOWNLOAD_ISSUE)) {
            mDownloadManager.m8742a(BACKGROUND_DOWNLOAD_ISSUE, 4);
        }
    }

    protected C1752a initAdapter() {
        return new C1752a(getActivity().getLayoutInflater(), Preferences.m3034X());
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        mDownloadManager.m8739b(BACKGROUND_DOWNLOAD_ISSUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_background_screen_layout, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7189b(R.string.change_background);
        this.mStateView = (StateView) view.findViewById(R.id.background_loadingview);
        this.mBackgroundListView = (ListView) view.findViewById(R.id.background_list);
        this.mBackgroundListView.setOnScrollListener(new ListViewUtils.C0631a());
        initListViewHeader();
        initListViewFooter();
        this.mBackgroundListView.setAdapter((ListAdapter) this.mBackgroundAdapter);
        if (isShowOfflineModeView()) {
            this.mOfflineModeView = OfflineModeUtils.m8253a(this.mStateView, new OfflineModeUtils.InterfaceC0635a() { // from class: com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment.2
                @Override // com.sds.android.ttpod.p067a.OfflineModeUtils.InterfaceC0635a
                /* renamed from: a */
                public void mo5379a() {
                    Preferences.m2814x(false);
                    BackgroundBaseFragment.this.mOfflineModeView.setVisibility(View.GONE);
                }
            });
        } else if (this.mOfflineModeView != null) {
            this.mOfflineModeView.setVisibility(View.GONE);
        }
        showLoadingView();
    }

    private void showLoadingView() {
        if (this.mBackgroundAdapter.getCount() == 0) {
            this.mStateView.setState(StateView.EnumC2248b.LOADING);
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.BACKGROUND_THUMBNAIL_CREATED, ReflectUtils.m8375a(cls, "backgroundThumbnailCreated", BackgroundItem.class));
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.m8375a(cls, "updateBkgDownloadingState", DownloadTaskInfo.class));
    }

    public void updateBkgDownloadingState(DownloadTaskInfo downloadTaskInfo) {
        if (DownloadTaskInfo.TYPE_BACKGROUND.equals(downloadTaskInfo.getType())) {
            this.mBackgroundAdapter.m5367a(downloadTaskInfo);
        }
    }

    public void backgroundThumbnailCreated(BackgroundItem backgroundItem) {
        if (this.mBackgroundAdapter != null && backgroundItem.m3326g() != null) {
            this.mBackgroundAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectedBackground(BackgroundItem backgroundItem) {
        String m3331b = backgroundItem.m3331b();
        if (backgroundItem.m3337a() == BackgroundItem.EnumC2011a.FOLLOW_SKIN || m3331b != null) {
            this.mBackgroundAdapter.m5364b(backgroundItem);
            saveBackgroundSettingToSystem(backgroundItem);
            //ThemeStatistic.m4883i();
            refreshEditButton();
        }
    }

    private void saveBackgroundSettingToSystem(BackgroundItem backgroundItem) {
        CommandCenter.getInstance().m4596b(new Command(CommandID.SET_BACKGROUND, backgroundItem.toString()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void performBkgDeleted(BackgroundItem backgroundItem) {
        Iterator<BkgEditListener> it = sBkgEditListenerList.iterator();
        while (it.hasNext()) {
            BkgEditListener next = it.next();
            if (next != null) {
                next.onBkgDeleted(backgroundItem);
            }
        }
    }

    protected List<DownloadTaskInfo> getBackgroundTaskList() {
        return (List) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_TASK_LIST_WITH_TYPE, DownloadTaskInfo.TYPE_BACKGROUND), List.class);
    }

    private String getStatisticOrigin() {
        return "recommend";
    }

    private void tryDownloadBackground(BackgroundItem backgroundItem) {
        OnlineSkinItem m3330c = backgroundItem.m3330c();
        if (m3330c != null) {
            if (!EnvironmentUtils.DeviceConfig.m8474e()) {
                PopupsUtils.m6760a((int) R.string.shake_error_hint);
            } else if (FileUtils.m8414b(backgroundItem.m3325h())) {
                PopupsUtils.m6760a((int) R.string.skin_file_already_existed);
            } else {
                DownloadTaskInfo m4760a = DownloadUtils.m4760a(m3330c.getSkinUrl(), backgroundItem.m3325h(), 0L, backgroundItem.m3331b(), DownloadTaskInfo.TYPE_BACKGROUND, false, getStatisticOrigin());
                List<DownloadTaskInfo> backgroundTaskList = getBackgroundTaskList();
                if (backgroundTaskList != null && backgroundTaskList.contains(m4760a)) {
                    PopupsUtils.m6760a((int) R.string.downloading_already);
                    return;
                }
                m4760a.setTag(backgroundItem);
                CommandCenter.getInstance().execute(new Command(CommandID.DELETE_DOWNLOAD_TASK, m4760a, Boolean.FALSE));
                CommandCenter.getInstance().execute(new Command(CommandID.ADD_DOWNLOAD_TASK, m4760a));
                backgroundItem.m3334a(m4760a);
                sLastRequestItem = backgroundItem;
                this.mRefreshHandler.sendEmptyMessage(0);
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mRefreshHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStatistic(OnlineSkinItem onlineSkinItem) {
        String pictureUrl = onlineSkinItem.getPictureUrl();
        if (pictureUrl != null && pictureUrl.startsWith("http://api.skin.ttpod.com/skin")) {
            //ThemeStatistic.m4884h(onlineSkinItem.getName());
        } else {
            //ThemeStatistic.m4879m();
        }
    }

    protected void refreshEditButton() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLocalBackground(BackgroundItem.EnumC2011a enumC2011a) {
        return BackgroundItem.EnumC2011a.ADD_BY_USER == enumC2011a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkNormalStateItem(BackgroundItem backgroundItem) {
        if (BackgroundItem.EnumC2011a.ONLINE_BACKGROUND == backgroundItem.m3337a()) {
            if (backgroundItem.m3329d() == null) {
                tryDownloadBackground(backgroundItem);
            }
            sLastRequestItem = backgroundItem;
            return;
        }
        setSelectedBackground(backgroundItem);
        sLastRequestItem = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyDataSetChanged() {
        if (this.mBackgroundAdapter != null) {
            this.mBackgroundAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLocalUnSelectedBackgroundItem(BackgroundItem backgroundItem) {
        return (backgroundItem == null || backgroundItem.m3337a() != BackgroundItem.EnumC2011a.ADD_BY_USER || this.mBackgroundAdapter.m5371a(backgroundItem)) ? false : true;
    }

    private boolean isShowOfflineModeView() {
        return isSupportOfflineMode() && OfflineModeUtils.m8256a();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public boolean isSupportOfflineMode() {
        return true;
    }

    public void updateBackgroundList(ArrayList<BackgroundItem> arrayList) {
    }

    public boolean isNeedRefresh() {
        List<DownloadTaskInfo> backgroundTaskList = getBackgroundTaskList();
        return backgroundTaskList != null && backgroundTaskList.size() > 0;
    }

    /* renamed from: com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment$a */
    /* loaded from: classes.dex */
    public class C1752a extends BaseAdapter {

        /* renamed from: a */
        private String f5533a;

        /* renamed from: b */
        protected ArrayList<BackgroundItem> f5534b = new ArrayList<>();

        /* renamed from: c */
        protected LayoutInflater f5535c;

        /* renamed from: e */
        private BackgroundItem f5537e;

        public C1752a(LayoutInflater layoutInflater, String str) {
            this.f5535c = null;
            this.f5535c = layoutInflater;
            this.f5533a = str;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return (int) Math.ceil((this.f5534b == null ? 0 : this.f5534b.size()) / 3.0d);
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public BackgroundItem getItem(int i) {
            if (this.f5534b == null || i >= this.f5534b.size()) {
                return null;
            }
            return this.f5534b.get(i);
        }

        /* renamed from: a */
        protected BackgroundItem m5376a(int i, int i2) {
            int i3 = (i * 3) + i2;
            if (i3 < this.f5534b.size()) {
                return this.f5534b.get(i3);
            }
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return m5375a(m5374a(view, viewGroup), i);
        }

        /* renamed from: a */
        protected View m5374a(View view, ViewGroup viewGroup) {
            if (view == null) {
                return m5373a(viewGroup);
            }
            return view;
        }

        /* renamed from: a */
        private View m5373a(ViewGroup viewGroup) {
            View inflate = this.f5535c.inflate(R.layout.background_list_item, viewGroup, false);
            ThemeViewHolder themeViewHolder = new ThemeViewHolder(inflate.findViewById(R.id.background_item1));
            ThemeViewHolder themeViewHolder2 = new ThemeViewHolder(inflate.findViewById(R.id.background_item2));
            ThemeViewHolder themeViewHolder3 = new ThemeViewHolder(inflate.findViewById(R.id.background_item3));
            themeViewHolder.m5386e().setVisibility(View.VISIBLE);
            themeViewHolder2.m5386e().setVisibility(View.VISIBLE);
            themeViewHolder3.m5386e().setVisibility(View.VISIBLE);
            themeViewHolder.m5386e().setText(R.string.setting_download);
            themeViewHolder2.m5386e().setText(R.string.setting_download);
            themeViewHolder3.m5386e().setText(R.string.setting_download);
            inflate.setTag(new ThemeViewHolder[]{themeViewHolder, themeViewHolder2, themeViewHolder3});
            return inflate;
        }

        /* renamed from: a */
        protected View m5375a(View view, int i) {
            ThemeViewHolder[] themeViewHolderArr = (ThemeViewHolder[]) view.getTag();
            mo5368a(m5376a(i, 0), themeViewHolderArr[0], true);
            mo5368a(m5376a(i, 1), themeViewHolderArr[1], true);
            mo5368a(m5376a(i, 2), themeViewHolderArr[2], true);
            return view;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public void mo5368a(BackgroundItem backgroundItem, ThemeViewHolder themeViewHolder, boolean z) {
            if (themeViewHolder != null) {
                View m5383h = themeViewHolder.m5383h();
                if (!z || backgroundItem == null) {
                    m5383h.setVisibility(View.INVISIBLE);
                    return;
                }
                m5383h.setVisibility(View.VISIBLE);
                m5383h.setTag(backgroundItem);
                m5383h.setOnClickListener(BackgroundBaseFragment.this.mOnBackgroundClickListener);
                m5360c(backgroundItem, themeViewHolder.m5389b());
                mo5370a(backgroundItem, themeViewHolder.m5390a());
                m5369a(backgroundItem, themeViewHolder);
                m5363b(backgroundItem, themeViewHolder.m5382i());
            }
        }

        /* renamed from: b */
        private void m5363b(BackgroundItem backgroundItem, ImageView imageView) {
            if (BackgroundItem.EnumC2011a.ONLINE_BACKGROUND != backgroundItem.m3337a()) {
                imageView.setVisibility(View.GONE);
            } else {
                ViewUtils.m4640a(backgroundItem.m3324i(), imageView);
            }
        }

        /* renamed from: c */
        private void m5360c(BackgroundItem backgroundItem, ImageView imageView) {
            if (backgroundItem.m3326g() != null && !backgroundItem.m3326g().isRecycled()) {
                imageView.setTag("localBackground");
                imageView.setImageBitmap(backgroundItem.m3326g());
            } else if (BackgroundItem.EnumC2011a.ONLINE_BACKGROUND == backgroundItem.m3337a() || backgroundItem.m3330c() != null) {
                ImageCacheUtils.m4752a(imageView, backgroundItem.m3330c().getPictureUrl(), SkinThumbnailCreator.f6693a, SkinThumbnailCreator.f6694c, (int) R.drawable.img_skin_default_thumbnail);
            } else {
                imageView.setImageResource(R.drawable.img_skin_default_thumbnail);
                CommandCenter.getInstance().execute(new Command(CommandID.DECODE_BACKGROUND_THUMBNAIL, backgroundItem));
            }
        }

        /* renamed from: a */
        protected void mo5370a(BackgroundItem backgroundItem, ImageView imageView) {
            if (imageView != null) {
                int i = 4;
                if (this.f5533a.equals(backgroundItem.toString())) {
                    i = 0;
                    m5361c(backgroundItem);
                }
                imageView.setVisibility(i);
            }
        }

        /* renamed from: a */
        private void m5369a(BackgroundItem backgroundItem, ThemeViewHolder themeViewHolder) {
            View m5384g = themeViewHolder.m5384g();
            ProgressBar m5388c = themeViewHolder.m5388c();
            TextView m5386e = themeViewHolder.m5386e();
            View m5385f = themeViewHolder.m5385f();
            if (BackgroundItem.EnumC2011a.ONLINE_BACKGROUND == backgroundItem.m3337a()) {
                m5384g.setVisibility(View.VISIBLE);
                TaskInfo m3329d = backgroundItem.m3329d();
                if (m3329d != null) {
                    m5388c.setVisibility(View.VISIBLE);
                    m5386e.setVisibility(View.INVISIBLE);
                    m5385f.setVisibility(View.INVISIBLE);
                    m5388c.setProgress(m3329d.getDownloadProgress().intValue());
                    return;
                }
                m5386e.setVisibility(View.VISIBLE);
                m5385f.setVisibility(View.VISIBLE);
                m5388c.setVisibility(View.INVISIBLE);
                return;
            }
            m5384g.setVisibility(View.INVISIBLE);
        }

        /* renamed from: a */
        public void m5366a(ArrayList<BackgroundItem> arrayList) {
            this.f5534b.addAll(arrayList);
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public boolean m5371a(BackgroundItem backgroundItem) {
            return this.f5533a.equals(backgroundItem.toString());
        }

        /* renamed from: b */
        public void m5364b(BackgroundItem backgroundItem) {
            this.f5533a = backgroundItem.toString();
            m5361c(backgroundItem);
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public BackgroundItem m5378a() {
            return this.f5537e;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: c */
        public void m5361c(BackgroundItem backgroundItem) {
            this.f5537e = backgroundItem;
        }

        /* renamed from: d */
        public void m5358d(BackgroundItem backgroundItem) {
            Bitmap m3326g = backgroundItem.m3326g();
            if (m3326g != null && !m3326g.isRecycled()) {
                m3326g.recycle();
                backgroundItem.m3335a((Drawable) null);
            }
            this.f5534b.remove(backgroundItem);
            notifyDataSetChanged();
        }

        /* renamed from: b */
        public ArrayList<BackgroundItem> m5365b() {
            return this.f5534b;
        }

        /* renamed from: c */
        public String m5362c() {
            return this.f5533a;
        }

        /* renamed from: d */
        public boolean m5359d() {
            String str = this.f5533a;
            this.f5533a = Preferences.m3034X();
            return !this.f5533a.equals(str);
        }

        /* renamed from: e */
        public void m5357e(BackgroundItem backgroundItem) {
            this.f5534b.add(backgroundItem);
        }

        /* renamed from: a */
        public void m5367a(DownloadTaskInfo downloadTaskInfo) {
            int intValue = downloadTaskInfo.getState().intValue();
            String fileName = downloadTaskInfo.getFileName();
            BackgroundItem backgroundItem = (BackgroundItem) downloadTaskInfo.getTag();
            if (intValue == 4 && backgroundItem.m3329d() != null) {
                backgroundItem.m3333a(BackgroundItem.EnumC2011a.ADD_BY_USER);
                backgroundItem.m3334a((TaskInfo) null);
                BackgroundBaseFragment.this.doStatistic(backgroundItem.m3330c());
                if (backgroundItem.equals(BackgroundBaseFragment.sLastRequestItem)) {
                    BackgroundBaseFragment.this.setSelectedBackground(backgroundItem);
                    BackgroundItem unused = BackgroundBaseFragment.sLastRequestItem = null;
                }
                PopupsUtils.m6721a(fileName + " " + BackgroundBaseFragment.this.getResources().getString(R.string.download_finished));
            } else if (intValue == 3 || intValue == 5) {
                PopupsUtils.m6721a(BackgroundBaseFragment.this.getString(R.string.unknown_error));
                backgroundItem.m3334a((TaskInfo) null);
                BackgroundItem unused2 = BackgroundBaseFragment.sLastRequestItem = null;
            }
        }
    }
}
