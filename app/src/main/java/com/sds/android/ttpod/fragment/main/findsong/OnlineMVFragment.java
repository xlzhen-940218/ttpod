package com.sds.android.ttpod.fragment.main.findsong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.cloudapi.ttpod.data.MVOnlineData;
import com.sds.android.sdk.core.p057a.ImageCache;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import com.sds.android.ttpod.widget.mediamenu.OnOnlineMVDownloadClickListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class OnlineMVFragment extends MVListFragment implements AbstractExpandableListAdapter.InterfaceC2279a {
    private static ActionBarController.C1070a mDownloadAction;
    private int mId;
    protected ActionExpandableListView mListView;
    private MVOnlineData mMVOnlineData;
    private MvPopupDialogCallBack mMVPopupDialogCallBack;
    private String mTitle;

    public OnlineMVFragment(String str, int i) {
        super(CommandID.GET_MV_LIST, null, null);
        this.mMVPopupDialogCallBack = new MvPopupDialogCallBack() { // from class: com.sds.android.ttpod.fragment.main.findsong.OnlineMVFragment.2
            @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
            /* renamed from: a */
            public void onSuccess() {
                OnlineMVFragment.this.playMv(OnlineMVFragment.this.mMVOnlineData);
            }

            @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
            /* renamed from: b */
            public void mo1218b() {
                OnlineMVFragment.downloadMv(OnlineMVFragment.this.getActivity(), OnlineMVFragment.this.mMVOnlineData);
            }
        };
        setAdapter(new C1546a());
        this.mId = i;
        this.mTitle = str;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(this.mTitle);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needMenuAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        //MVStatistic.m5076a("mv_channel");
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        //MVStatistic.m5076a("other_channel");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_online_mv, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected void initViews(View view) {
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.loading_view);
        this.mListView = (ActionExpandableListView) view.findViewById(R.id.lv_online_mv);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected void setupListView() {
        this.mListView.setEmptyView(this.mLoadingView);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnItemLongClickListener(this);
        this.mListView.mo1261a(this.mListAdapter, R.id.menu_view, R.id.expandable);
        this.mFooterView = new DataListFooterView(getActivity());
        this.mListView.addFooterView(this.mFooterView);
        this.mListView.setItemExpandCollapseListener(this);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((C1546a) this.mListAdapter).m5546a((Activity) getActivity());
        this.mListView.setVerticalScrollBarEnabled(false);
        mDownloadAction = getActionBarController().m7199a((Drawable) null);
        flushDownloadAction();
        mDownloadAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.main.findsong.OnlineMVFragment.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                Bundle bundle2 = new Bundle(1);
                bundle2.putInt(DownloadManagerFragment.DOWNLOAD_TYPE, DownloadTaskInfo.TYPE_VIDEO.intValue());
                OnlineMVFragment.this.launchFragment((BaseFragment) Fragment.instantiate(OnlineMVFragment.this.getActivity(), LocalMVFragment.class.getName(), bundle2));
                Preferences.m3041T(false);
                OnlineMVFragment.flushDownloadAction();

            }
        });
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
    public void onExpand(View view, int i) {
        View view2 = (View) view.getParent();
        updateMenuArrowView(view2, true);
        View findViewById = view2.findViewById(R.id.media_menu_download);
        if (findViewById != null) {
            findViewById.setOnClickListener(new OnOnlineMVDownloadClickListener(getActivity(), (MVOnlineData) view2.getTag(R.id.view_bind_data), this.mListView));
        }
    }

    @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
    public void onCollapse(View view, int i) {
        updateMenuArrowView((View) view.getParent(), false);
    }

    private void updateMenuArrowView(View view, boolean z) {
        ((IconTextView) view.findViewById(R.id.menu_icon_image)).setText(z ? R.string.icon_arrow_top : R.string.icon_arrow_down);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void flushDownloadAction() {
        ThemeUtils.m8170a(mDownloadAction, (int) R.string.icon_mv_download, ThemeElement.TOP_BAR_TEXT);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected String onLoadTitleText() {
        return this.mTitle;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected void requestDataList(int i) {
        if (this.mFooterView != null) {
            this.mFooterView.clearAnimation();
            this.mFooterView.m1877a();
        }
        VideoPlayManager.m5813a(getActivity(), null, null, EnvironmentUtils.DeviceConfig.getNetworkType() == 2);
        CommandCenter.getInstance().execute(new Command(this.mRequestId, Integer.valueOf(this.mId), Integer.valueOf(i)));
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mMVOnlineData = (MVOnlineData) view.getTag(R.id.view_bind_data);
        MvManager.m5560a(getActivity(), this.mMVPopupDialogCallBack, 0, 1);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(this.mListView, "BackgroundMaskColor");
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment
    protected void playMv(MVOnlineData mVOnlineData) {
        if (mVOnlineData != null) {
            String highQualityUrl = mVOnlineData.getHighQualityUrl();
            if (highQualityUrl == null || 2 != EnvironmentUtils.DeviceConfig.getNetworkType()) {
                highQualityUrl = mVOnlineData.getNormalQualityUrl();
            }
            //MVStatistic.m5076a("mv_channel");
            VideoPlayManager.m5814a(getActivity(), highQualityUrl, mVOnlineData.getName());
            //MusicLibraryStatistic.m5056e(mVOnlineData.getId(), mVOnlineData.getName());

        }
    }

    protected static void startDownloadMV(String str, String str2, MVOnlineData mVOnlineData) {
        DownloadTaskInfo m4760a = DownloadUtils.m4760a(str, str2, Long.valueOf(mVOnlineData.getId()), mVOnlineData.getName(), DownloadTaskInfo.TYPE_VIDEO, true, "mv_channel");
        m4760a.setTag(mVOnlineData);
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_DOWNLOAD_TASK, m4760a));
    }

    private static void saveDownloadMvThumbnail(Context context, MVOnlineData mVOnlineData) {
        String picUrl = mVOnlineData.getPicUrl();
        final String generateMvThumbnailPath = generateMvThumbnailPath(mVOnlineData, ".jpg");
        ImageCacheUtils.getImageCache().addThreadPool(picUrl, context.getResources().getDimensionPixelSize(R.dimen.mv_thumbnail_width), context.getResources().getDimensionPixelSize(R.dimen.mv_thumbnail_height), new ImageCache.ImageLoadedCallback() { // from class: com.sds.android.ttpod.fragment.main.findsong.OnlineMVFragment.3
            @Override // com.sds.android.sdk.core.p057a.ImageCache.InterfaceC0565a
            /* renamed from: a */
            public void loaded(String url, int width, int height, Bitmap bitmap) {
                File m8407e;
                if (bitmap != null && (m8407e = FileUtils.createFile(generateMvThumbnailPath)) != null) {
                    try {
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(m8407e));
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
                        try {
                            bufferedOutputStream.flush();
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        });
    }

    public static void downloadMv(Context context, MVOnlineData mVOnlineData) {
        String str = null;
        if (!StringUtils.isEmpty(mVOnlineData.getNormalQualityUrl())) {
            str = mVOnlineData.getNormalQualityUrl();
        } else if (!StringUtils.isEmpty(mVOnlineData.getHighQualityUrl())) {
            str = mVOnlineData.getHighQualityUrl();
        }
        if (StringUtils.isEmpty(str)) {
            PopupsUtils.m6721a("没有资源可以下载");
            return;
        }
        //SUserUtils.m4956a(SAction.ACTION_MV_DOWNLOAD_MV, SPage.PAGE_NONE);
        downloadMv(context, str, mVOnlineData);
    }

    private static void downloadMv(Context context, String str, MVOnlineData mVOnlineData) {
        String replace = generateLocalMvFilePath(str, mVOnlineData).replace(" ", "");
        if (FileUtils.isFile(replace)) {
            PopupsUtils.m6760a((int) R.string.mv_had_download);
            return;
        }
        startDownloadMV(str, replace, mVOnlineData);
        saveDownloadMvThumbnail(context, mVOnlineData);
        Preferences.m3041T(true);
        flushDownloadAction();
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.OnlineMVFragment$a */
    /* loaded from: classes.dex */
    protected class C1546a extends MVListFragment.AbstractView$OnClickListenerC1592a {
        protected C1546a() {
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment.AbstractView$OnClickListenerC1592a, com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = this.layoutInflater.inflate(R.layout.mv_list_item_online, viewGroup, false);
            inflate.setTag(new MVListFragment.AbstractView$OnClickListenerC1592a.C1594a(inflate));
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment.AbstractView$OnClickListenerC1592a, com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        public void buildDataUI(final View view, MVOnlineData mVOnlineData, int i) {
            super.buildDataUI(view, mVOnlineData, i);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.OnlineMVFragment.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OnlineMVFragment.this.mMVOnlineData = (MVOnlineData) view.getTag(R.id.view_bind_data);
                    if (OnlineMVFragment.this.mMVOnlineData != null) {
                        MvManager.m5560a(OnlineMVFragment.this.getActivity(), OnlineMVFragment.this.mMVPopupDialogCallBack, 0, 1);
                    }
                }
            });
            ThemeManager.m3269a(view, ThemeElement.TILE_BACKGROUND);
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment.AbstractView$OnClickListenerC1592a
        /* renamed from: a */
        protected ActionItem[] mo5544a(MVOnlineData mVOnlineData) {
            return new ActionItem[0];
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment.AbstractView$OnClickListenerC1592a
        /* renamed from: a */
        protected void mo5542a(ActionItem actionItem, int i, MVOnlineData mVOnlineData) {
        }
    }
}
