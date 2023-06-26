package com.sds.android.ttpod.fragment.downloadmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.OptionalDialog;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.findsong.LocalMVCompletedFragment;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.MediaListFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.NotificationUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DownloadManagerFragment extends SlidingClosableFragment {
    public static final String DOWNLOAD_TYPE = "download_type";
    public static final String FRAGMENT_TAB_ID = "fragment_tab_index";
    private static final int ID_FRAGMENT_COMPLETED = 0;
    private static final int ID_FRAGMENT_UNCOMPLETED = 1;
    private static final String TAG = "DownloadManagerFragment";
    private int mCurrentItem;
    private SlidingTabFragmentPagerAdapter mPagerAdapter;
    private SlidingTabHost mTabHost;
    private View mTopView;
    private ViewPager mViewPager;
    private int mTargetPage = 0;
    private Integer mDownloadType = DownloadTaskInfo.TYPE_AUDIO;
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment.1
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            //SUserUtils.m4956a(i == 0 ? SAction.ACTION_MY_DOWNLOAD_TO_DOWNLOADED : SAction.ACTION_MY_DOWNLOAD_TO_DOWNLOADING, i == 0 ? SPage.PAGE_MY_DOWNLOAD_DOWNLOADED : SPage.PAGE_MY_DOWNLOAD_DOWNLOADING);
            DownloadManagerFragment.this.mCurrentItem = i;
            DownloadManagerFragment.this.setCurrentPosition(i);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            if (getArguments().containsKey(FRAGMENT_TAB_ID)) {
                this.mTargetPage = getArguments().getInt(FRAGMENT_TAB_ID);
            }
            if (getArguments().containsKey(DOWNLOAD_TYPE)) {
                this.mDownloadType = Integer.valueOf(getArguments().getInt(DOWNLOAD_TYPE));
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needMenuAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.m7428a();
        }
        this.mTabHost.setOnPageChangeListener(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_downloadmanager, viewGroup, false);
        this.mTopView = inflate;
        getActionBarController().m7189b(R.string.mine_my_download);
        getActionBarController().m7175e(R.string.delete_all_download).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment.2
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                if (DownloadManagerFragment.this.mCurrentItem == 0) {
                    Fragment item = DownloadManagerFragment.this.mPagerAdapter.getItem(DownloadManagerFragment.this.mCurrentItem);
                    if (!(item instanceof LocalMVCompletedFragment)) {
                        DownloadManagerFragment.this.deleteDownloadedAudio();
                    } else {
                        ((LocalMVCompletedFragment) item).deleteAllCompleted();
                    }
                } else if (1 == DownloadManagerFragment.this.mCurrentItem) {
                    ((DownloadTaskListFragment) DownloadManagerFragment.this.mPagerAdapter.getItem(DownloadManagerFragment.this.mCurrentItem)).onDropDownMenuClicked(9);
                }
                //SUserUtils.m4956a(SAction.ACTION_MY_DOWNLOAD_DELETE_ALL, SPage.PAGE_NONE);
            }
        });
        this.mTabHost = (SlidingTabHost) inflate.findViewById(R.id.slidingtabhost_localmusic);
        this.mTabHost.setTabLayoutAverageSpace(true);
        this.mViewPager = (ViewPager) inflate.findViewById(R.id.pager_content);
        this.mPagerAdapter = new SlidingTabFragmentPagerAdapter(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mTabHost.setViewPager(this.mViewPager);
        this.mTabHost.setOnPageChangeListener(this.mOnPageChangeListener);
        this.mViewPager.setOffscreenPageLimit(this.mPagerAdapter.getCount());
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteDownloadedAudio() {
        PopupsUtils.m6746a(getActivity(), (int) R.string.download_remove_file_message, BaseApplication.getApplication().getString(R.string.delete_all_download), BaseApplication.getApplication().getString(R.string.download_remove_all_confirm_hint), new BaseDialog.InterfaceC1064a<OptionalDialog>() { // from class: com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment.3
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(OptionalDialog optionalDialog) {
                //SUserUtils.m4956a(SAction.ACTION_MY_DOWNLOAD_DELETE_ALL_SURE, SPage.PAGE_NONE);
                //LocalStatistic.m5152aB();
                CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_ALL_FINISHED_DOWNLOAD_TASK, DownloadManagerFragment.this.mDownloadType, Boolean.valueOf(optionalDialog.m6808b())));
            }
        }, (BaseDialog.InterfaceC1064a<OptionalDialog>) null);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mDownloadType == DownloadTaskInfo.TYPE_AUDIO) {
            cancelCompletedAndErrorNotification();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mDownloadType == DownloadTaskInfo.TYPE_AUDIO) {
            Preferences.m3043S(false);
            cancelCompletedAndErrorNotification();
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeUtils.m8166a(this.mTabHost);
    }

    public void setCurrentPage(int i) {
        if (this.mViewPager != null && i != this.mCurrentItem) {
            this.mViewPager.setCurrentItem(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDownloadType(Integer num) {
        this.mDownloadType = num;
    }

    private void cancelCompletedAndErrorNotification() {
        CommandCenter.getInstance().execute(new Command(CommandID.CLEAR_COMPLETE_TASK_COUNT, new Object[0]));
        NotificationUtils.m4696a(15121730);
        NotificationUtils.m4696a(15121740);
    }

    private List<SlidingTabFragmentPagerAdapter.C0998a> buildFragmentBinders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.download_completed, 0, getCompletedDownloadFragment()));
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.download_running, 0, getUncompletedDownloadFragment()));
        return arrayList;
    }

    protected Fragment getCompletedDownloadFragment() {
        Bundle bundle = new Bundle(1);
        if (this.mDownloadType == DownloadTaskInfo.TYPE_AUDIO) {
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_DOWNLOAD);
            MediaListFragment mediaListFragment = (MediaListFragment) Fragment.instantiate(getActivity(), MediaListFragment.class.getName(), bundle);
            mediaListFragment.setPage(SPage.PAGE_MY_DOWNLOAD_DOWNLOADED);
            mediaListFragment.setNoDataViewMessage(R.string.icon_male, R.string.no_song_go_recommend);
            return mediaListFragment;
        }
        bundle.putInt(DOWNLOAD_TYPE, this.mDownloadType.intValue());
        DownloadTaskListFragment downloadTaskListFragment = (DownloadTaskListFragment) Fragment.instantiate(getActivity(), CompletedDownloadFragment.class.getName(), bundle);
        downloadTaskListFragment.setPage(SPage.PAGE_MY_DOWNLOAD_DOWNLOADED);
        downloadTaskListFragment.setOnTaskCountChangeListener(new DownloadTaskListFragment.InterfaceC1436b() { // from class: com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment.4
            @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.InterfaceC1436b
            /* renamed from: a */
            public void mo5633a(int i) {
                DownloadManagerFragment.this.updateTabTitleAt(0, i == 0 ? DownloadManagerFragment.this.getString(R.string.download_completed) : DownloadManagerFragment.this.getString(R.string.download_completed_with_count, Integer.valueOf(i)));
            }
        });
        return downloadTaskListFragment;
    }

    protected Fragment getUncompletedDownloadFragment() {
        Bundle bundle = new Bundle(1);
        bundle.putInt(DOWNLOAD_TYPE, this.mDownloadType.intValue());
        DownloadTaskListFragment downloadTaskListFragment = (DownloadTaskListFragment) Fragment.instantiate(getActivity(), UncompletedDownloadFragment.class.getName(), bundle);
        downloadTaskListFragment.setPage(SPage.PAGE_MY_DOWNLOAD_DOWNLOADING);
        downloadTaskListFragment.setOnTaskCountChangeListener(new DownloadTaskListFragment.InterfaceC1436b() { // from class: com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment.5
            @Override // com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.InterfaceC1436b
            /* renamed from: a */
            public void mo5633a(int i) {
                if (DownloadManagerFragment.this.getActivity() != null) {
                    DownloadManagerFragment.this.updateTabTitleAt(1, i == 0 ? DownloadManagerFragment.this.getString(R.string.download_running) : DownloadManagerFragment.this.getString(R.string.download_running_with_count, Integer.valueOf(i)));
                }
            }
        });
        return downloadTaskListFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateTabTitleAt(int i, CharSequence charSequence) {
        this.mTabHost.m1475a(i, charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentPosition(int i) {
        int i2 = 0;
        if (isSlidingAtTheLeftEdge(i)) {
            i2 = 2;
        } else if (isSlidingAtTheRightEdge(i)) {
            i2 = 1;
        }
        setSlidingCloseMode(i2);
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return i == this.mPagerAdapter.getCount() + (-1);
    }
}
